package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DublicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * SubjectModel class provides database operations for Subject records. It
 * extends BaseModel to perform CRUD operations on SubjectBean objects.
 *
 * @author Rays Technologies
 * @version 1.0
 */
public class SubjectModel extends BaseModel<SubjectBean> {

	/**
	 * Inserts a new subject record into the database. Checks whether a subject with
	 * the same name already exists.
	 *
	 * @param bean SubjectBean object containing subject details.
	 * @return Primary key of the inserted subject.
	 * @throws ApplicationException     if a database operation fails.
	 * @throws DublicateRecordException if the subject name already exists.
	 */
	@Override
	public long add(SubjectBean bean) throws ApplicationException, DublicateRecordException {

		Connection conn = null;

		SubjectBean existBean = findByName(bean.getName());
		if (existBean != null) {
			throw new DublicateRecordException("Name already exist");
		}
		long PK = 0;
		try {
			PK = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, nextPk());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setLong(4, bean.getCourseId());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Inserted data..." + i);
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return PK;

	}

	/**
	 * Updates an existing subject record in the database. Checks whether the
	 * subject name is already used by another subject.
	 *
	 * @param bean SubjectBean object containing updated subject details.
	 * @throws ApplicationException     if a database operation fails.
	 * @throws DublicateRecordException if the subject name is already used by
	 *                                  another record.
	 */
	@Override
	public void update(SubjectBean bean) throws ApplicationException, DublicateRecordException {

		Connection conn = null;

		SubjectBean existBean = findByName(bean.getName());
		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DublicateRecordException("Name already exist");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " SET name=?, description=?, course_id=?, created_by=?, modified_by=?, created_datetime=?, modified_datetime=? where id =?");

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDescription());
			pstmt.setLong(3, bean.getCourseId());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.setLong(8, bean.getId());
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
	 * Builds the SQL WHERE clause based on the provided subject search criteria.
	 *
	 * @param bean SubjectBean object containing search criteria.
	 * @return SQL WHERE conditions as a String.
	 */
	@Override
	public String getWhereClause(SubjectBean bean) {

		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id =" + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" and description '" + bean.getDescription() + "%'");
			}
			if (bean.getCourseId() != 0) {
				sql.append(" and course_Id =" + bean.getCourseId());
			}

		}
		return sql.toString();
	}

	/**
	 * Finds a subject record by its name.
	 *
	 * @param name Name of the subject to search.
	 * @return SubjectBean object if found; otherwise, null.
	 */
	public SubjectBean findByName(String name) {

		SubjectBean bean = findByUniqueColumn("name", name);

		return bean;

	}

	/**
	 * Returns the name of the subject database table.
	 *
	 * @return Database table name.
	 */
	@Override
	public String getTable() {
		return "st_subject";
	}

	/**
	 * Creates and returns a new SubjectBean object.
	 *
	 * @return A new SubjectBean instance.
	 */
	@Override
	public SubjectBean getBean() {
		return new SubjectBean();
	}

}