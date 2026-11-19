package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DublicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * CourseModel class provides database operations for Course records. It extends
 * BaseModel and implements CRUD operations for CourseBean.
 *
 * @author Rays Technologies
 * @version 1.0
 */
public class CourseModel extends BaseModel<CourseBean> {

	/**
	 * Adds a new course record to the database. Checks whether a course with the
	 * same name already exists.
	 *
	 * @param bean the CourseBean containing course details
	 * @return the primary key of the inserted course record
	 * @throws ApplicationException     if an application error occurs
	 * @throws DublicateRecordException if a course with the same name already
	 *                                  exists
	 */
	@Override
	public long add(CourseBean bean) throws ApplicationException, DublicateRecordException {

		Connection conn = null;
		CourseBean existBean = findByName(bean.getName());
		if (existBean != null) {
			throw new DublicateRecordException("Name already exist");
		}
		long PK = 0;
		PK = nextPk();
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, nextPk());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getDuration());
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
	 * Updates an existing course record in the database. Checks whether another
	 * course with the same name already exists.
	 *
	 * @param bean the CourseBean containing updated course details
	 * @throws ApplicationException     if an application error occurs
	 * @throws DublicateRecordException if another course has the same name
	 */
	@Override
	public void update(CourseBean bean) throws ApplicationException, DublicateRecordException {

		Connection conn = null;

		CourseBean existBean = findByName(bean.getName());
		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DublicateRecordException("Name already exist");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " SET name=?, description=?, duration=?, created_by=?, modified_by=?, created_datetime=?, modified_datetime=? where id =?");

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDescription());
			pstmt.setString(3, bean.getDuration());
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
	 * Builds the WHERE clause using the supplied course search criteria.
	 *
	 * @param bean the CourseBean containing search criteria
	 * @return the generated SQL WHERE conditions as a string
	 */
	@Override
	public String getWhereClause(CourseBean bean) {

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
			if (bean.getDuration() != null && bean.getDuration().length() > 0) {
				sql.append(" and duration =" + bean.getDuration());
			}

		}
		return sql.toString();
	}

	/**
	 * Finds a course record by its name.
	 *
	 * @param name the name of the course to search for
	 * @return the CourseBean matching the specified name, or null if not found
	 */
	public CourseBean findByName(String name) {

		CourseBean bean = findByUniqueColumn("name", name);

		return bean;
	}

	/**
	 * Returns the name of the course database table.
	 *
	 * @return the table name "st_course"
	 */
	@Override
	public String getTable() {
		return "st_course";
	}

	/**
	 * Creates and returns a new CourseBean object.
	 *
	 * @return a new CourseBean instance
	 */
	@Override
	public CourseBean getBean() {
		return new CourseBean();
	}

}