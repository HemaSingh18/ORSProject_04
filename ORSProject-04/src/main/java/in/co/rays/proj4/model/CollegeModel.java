package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DublicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * CollegeModel class provides database operations for College records. It
 * extends BaseModel and implements CRUD operations for CollegeBean.
 *
 * @author Rays Technologies
 * @version 1.0
 */
public class CollegeModel extends BaseModel<CollegeBean> {

	/**
	 * Adds a new college record to the database. Checks whether a college with the
	 * same name already exists.
	 *
	 * @param bean the CollegeBean containing college details
	 * @return the primary key of the inserted college record
	 * @throws ApplicationException     if an application error occurs
	 * @throws DublicateRecordException if a college with the same name already
	 *                                  exists
	 */
	@Override
	public long add(CollegeBean bean) throws ApplicationException, DublicateRecordException {
		int PK = 0;
		Connection conn = null;
		CollegeBean existBean = findByName(bean.getName());
		if (existBean != null) {

			throw new DublicateRecordException("Name already exist");
		}
		try {
			PK = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, nextPk());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getAddress());
			pstmt.setString(4, bean.getState());
			pstmt.setString(5, bean.getCity());
			pstmt.setString(6, bean.getPhoneNo());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Insert Data Successfully.. " + i);
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return PK;
	}

	/**
	 * Updates an existing college record in the database. Checks whether another
	 * college with the same name exists.
	 *
	 * @param bean the CollegeBean containing updated college details
	 * @throws ApplicationException     if an application error occurs
	 * @throws DublicateRecordException if another college has the same name
	 */
	@Override
	public void update(CollegeBean bean) throws ApplicationException, DublicateRecordException {

		Connection conn = null;

		CollegeBean existBean = findByName(bean.getName());
		if (existBean != null && existBean.getId() != bean.getId()) {

			throw new DublicateRecordException("Name already exist");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " SET name=?, address=?, state=?, city=?, phone_no=?, created_by=?, modified_by=?, created_datetime=?, modified_datetime=? where id =?");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getAddress());
			pstmt.setString(3, bean.getState());
			pstmt.setString(4, bean.getCity());
			pstmt.setString(5, bean.getPhoneNo());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			pstmt.setLong(10, bean.getId());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Update data... " + i);
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	/**
	 * Builds the WHERE clause based on the supplied college search criteria.
	 *
	 * @param bean the CollegeBean containing search criteria
	 * @return the generated SQL WHERE conditions as a string
	 */
	@Override
	public String getWhereClause(CollegeBean bean) {

		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id =" + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			if (bean.getAddress() != null && bean.getAddress().length() > 0) {
				sql.append(" and address like '" + bean.getAddress() + "%'");
			}
			if (bean.getState() != null && bean.getState().length() > 0) {
				sql.append(" and state like '" + bean.getState() + "%'");
			}
			if (bean.getCity() != null && bean.getCity().length() > 0) {
				sql.append(" and city like '" + bean.getCity() + "%'");
			}
			if (bean.getPhoneNo() != null && bean.getPhoneNo().length() <= 10) {
				sql.append(" and phone_no =" + bean.getPhoneNo());
			}

		}
		return sql.toString();

	}

	/**
	 * Finds a college record by its name.
	 *
	 * @param name the name of the college to search for
	 * @return the CollegeBean matching the specified name, or null if not found
	 */
	public CollegeBean findByName(String name) {

		CollegeBean bean = findByUniqueColumn("name", name);

		return bean;
	}

	/**
	 * Returns the name of the college database table.
	 *
	 * @return the table name "st_college"
	 */
	@Override
	public String getTable() {
		return "st_college";
	}

	/**
	 * Creates and returns a new CollegeBean object.
	 *
	 * @return a new CollegeBean instance
	 */
	@Override
	public CollegeBean getBean() {
		return new CollegeBean();
	}

}