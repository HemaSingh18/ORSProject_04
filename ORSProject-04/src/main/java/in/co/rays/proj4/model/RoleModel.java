package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DublicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * RoleModel class provides database operations for Role records. It extends
 * BaseModel to perform CRUD operations on RoleBean objects.
 * 
 * @author Rays Technologies
 * @version 1.0
 */
public class RoleModel extends BaseModel<RoleBean> {

	/**
	 * Inserts a new role record into the database. Checks whether a role with the
	 * same name already exists.
	 *
	 * @param bean RoleBean object containing role details.
	 * @return Primary key of the inserted role.
	 * @throws ApplicationException     if a database operation fails.
	 * @throws DublicateRecordException if the role name already exists.
	 */
	@Override
	public long add(RoleBean bean) throws ApplicationException, DublicateRecordException {
		Connection conn = null;
		long PK = 0;

		RoleBean existBean = findByName(bean.getName());
		if (existBean != null) {
			throw new DublicateRecordException("Name already exist");
		}
		try {
			PK = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?)");

			pstmt.setLong(1, PK);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("insert successfully..." + i);
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return PK;
	}

	/**
	 * Updates an existing role record in the database. Checks whether the role name
	 * is already used by another role.
	 *
	 * @param bean RoleBean object containing updated role details.
	 * @throws ApplicationException     if a database operation fails.
	 * @throws DublicateRecordException if the role name is already used by another
	 *                                  record.
	 */
	@Override
	public void update(RoleBean bean) throws ApplicationException, DublicateRecordException {

		Connection conn = null;

		RoleBean existBean = findByName(bean.getName());
		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DublicateRecordException("Name already exist");
		}
		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " SET name=?,description=?,created_by=?,modified_by=?,created_datetime=?,modified_datetime=? where id=?");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDescription());
			pstmt.setString(3, bean.getCreatedBy());
			pstmt.setString(4, bean.getModifiedBy());
			pstmt.setTimestamp(5, bean.getCreatedDatetime());
			pstmt.setTimestamp(6, bean.getModifiedDatetime());
			pstmt.setLong(7, bean.getId());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Records updated..." + i);

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	/**
	 * Builds the SQL WHERE clause based on the provided role search criteria.
	 *
	 * @param bean RoleBean object containing search criteria.
	 * @return SQL WHERE conditions as a String.
	 */
	@Override
	public String getWhereClause(RoleBean bean) {

		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id =" + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" and description like '" + bean.getDescription() + "%'");
			}
			if (bean.getCreatedBy() != null && bean.getCreatedBy().length() > 0) {
				sql.append(" and created_by like '" + bean.getCreatedBy() + "%'");
			}
			if (bean.getModifiedBy() != null && bean.getModifiedBy().length() > 0) {
				sql.append(" and modified_by like '" + bean.getModifiedBy() + "%'");
			}
		}

		return sql.toString();
	}

	/**
	 * Finds a role record by its name.
	 *
	 * @param name Name of the role to search.
	 * @return RoleBean object if found; otherwise, null.
	 */
	public RoleBean findByName(String name) {

		RoleBean bean = findByUniqueColumn("name", name);

		return bean;
	}

	/**
	 * Returns the name of the role database table.
	 *
	 * @return Database table name.
	 */
	@Override
	public String getTable() {

		return "st_role";
	}

	/**
	 * Creates and returns a new RoleBean object.
	 *
	 * @return A new RoleBean instance.
	 */
	@Override
	public RoleBean getBean() {

		return new RoleBean();
	}

}