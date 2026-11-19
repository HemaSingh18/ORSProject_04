package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DublicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * MarksheetModel class provides database operations for Marksheet records. It
 * extends BaseModel and implements CRUD operations for MarksheetBean.
 *
 * @author Rays Technologies
 * @version 1.0
 */
public class MarksheetModel extends BaseModel<MarksheetBean> {

	/**
	 * Adds a new marksheet record to the database. Checks whether the roll number
	 * already exists and retrieves student details.
	 *
	 * @param bean the MarksheetBean containing marksheet details
	 * @return the primary key of the inserted marksheet record
	 * @throws ApplicationException     if an application error occurs
	 * @throws DublicateRecordException if the roll number already exists
	 */
	@Override
	public long add(MarksheetBean bean) throws ApplicationException, DublicateRecordException {
		long PK = 0;

		MarksheetBean existBean = findByRollNumber(bean.getRollNo());
		if (existBean != null) {
			throw new DublicateRecordException("Rollnumber already exist");
		}

		Connection conn = null;
		StudentModel smodel = new StudentModel();
		StudentBean sbean = smodel.findByPK(bean.getStudentId());

		try {
			PK = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, nextPk());
			pstmt.setString(2, bean.getRollNo());
			pstmt.setLong(3, bean.getStudentId());
			pstmt.setString(4, sbean.getFirstName() + " " + sbean.getLastName());
			pstmt.setInt(5, bean.getPhysics());
			pstmt.setInt(6, bean.getChemistry());
			pstmt.setInt(7, bean.getMaths());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("inserted data..." + i);
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return PK;
	}

	/**
	 * Updates an existing marksheet record in the database. Checks for duplicate
	 * roll numbers and retrieves student details.
	 *
	 * @param bean the MarksheetBean containing updated marksheet details
	 * @throws ApplicationException     if an application error occurs
	 * @throws DublicateRecordException if another marksheet has the same roll
	 *                                  number
	 */
	@Override
	public void update(MarksheetBean bean) throws ApplicationException, DublicateRecordException {

		Connection conn = null;
		StudentModel smodel = new StudentModel();
		StudentBean sbean = smodel.findByPK(bean.getStudentId());

		MarksheetBean existBean = findByRollNumber(bean.getRollNo());
		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DublicateRecordException("Rollnumber already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " SET roll_no=?, student_id=?, name=?, physics=?, chemistry=?, maths=?, created_by=?, modified_by=?, created_datetime=?, modified_datetime=? where id =?");

			pstmt.setString(1, bean.getRollNo());
			pstmt.setLong(2, bean.getStudentId());
			pstmt.setString(3, sbean.getFirstName() + " " + sbean.getLastName());
			pstmt.setInt(4, bean.getPhysics());
			pstmt.setInt(5, bean.getChemistry());
			pstmt.setInt(6, bean.getMaths());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			pstmt.setLong(11, bean.getId());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Updated data..." + i);
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	/**
	 * Builds the WHERE clause using the supplied marksheet search criteria.
	 *
	 * @param bean the MarksheetBean containing search criteria
	 * @return the generated SQL WHERE conditions as a string
	 */
	@Override
	public String getWhereClause(MarksheetBean bean) {

		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id =" + bean.getId());
			}
			if (bean.getRollNo() != null && bean.getRollNo().length() > 0) {
				sql.append(" and roll_no like '" + bean.getRollNo() + "%'");
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			if (bean.getStudentId() != 0) {
				sql.append(" and student_id =" + bean.getStudentId());
			}
			if (bean.getPhysics() != 0) {
				sql.append(" and physics =" + bean.getPhysics());
			}
			if (bean.getChemistry() != 0) {
				sql.append(" and chemistry =" + bean.getChemistry());
			}
			if (bean.getMaths() != 0) {
				sql.append(" and maths =" + bean.getMaths());
			}
		}
		return sql.toString();
	}

	/**
	 * Finds a marksheet record by its roll number.
	 *
	 * @param rollNo the roll number to search for
	 * @return the MarksheetBean matching the specified roll number, or null if not
	 *         found
	 */
	public MarksheetBean findByRollNumber(String rollNo) {

		MarksheetBean bean = findByUniqueColumn("rollNo", rollNo);

		return bean;

	}

	/**
	 * Returns the name of the marksheet database table.
	 *
	 * @return the table name "st_marksheet"
	 */
	@Override
	public String getTable() {
		return "st_marksheet";
	}

	/**
	 * Creates and returns a new MarksheetBean object.
	 *
	 * @return a new MarksheetBean instance
	 */
	@Override
	public MarksheetBean getBean() {
		return new MarksheetBean();
	}

}