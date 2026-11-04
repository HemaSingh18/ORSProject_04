
package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DublicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * BaseModel is an abstract parent class of all Model classes in the ORS
 * Project.
 *
 * It provides common database operations such as adding, updating, deleting,
 * searching and retrieving records.
 *
 * It uses JDBC to communicate with the database.
 *
 * All application Model classes inherit this class and implement its abstract
 * methods according to their respective database tables.
 *
 * @param <T> Bean class extending BaseBean
 *
 * @author Hema Singh
 * @version 1.0
 * @since 2026
 */
public abstract class BaseModel<T extends BaseBean> {

	/**
	 * Inserts a new record into the database.
	 *
	 * Child Model classes implement this method to insert records into their
	 * respective database tables.
	 *
	 * @param bean bean object containing record data
	 * @return generated primary key of the inserted record
	 * @throws ApplicationException     if a database operation fails
	 * @throws DublicateRecordException if a duplicate record exists
	 */
	public abstract long add(T bean) throws ApplicationException, DublicateRecordException;

	/**
	 * Updates an existing record in the database.
	 *
	 * Child Model classes implement this method to update records in their
	 * respective database tables.
	 *
	 * @param bean bean object containing updated record data
	 * @throws ApplicationException     if a database operation fails
	 * @throws DublicateRecordException if a duplicate record exists
	 */
	public abstract void update(T bean) throws ApplicationException, DublicateRecordException;

	/**
	 * Generates the WHERE condition for database search queries.
	 *
	 * Child Model classes implement this method to create search conditions
	 * according to their requirements.
	 *
	 * @param bean bean object containing search criteria
	 * @return SQL WHERE condition as a String
	 */
	public abstract String getWhereClause(T bean);

	/**
	 * Returns the database table name associated with the Model.
	 *
	 * Child Model classes implement this method to provide their respective table
	 * names.
	 *
	 * @return database table name
	 */
	public abstract String getTable();

	/**
	 * Creates and returns a new Bean object.
	 *
	 * Child Model classes implement this method to create their respective Bean
	 * objects.
	 *
	 * @return new Bean object
	 */
	public abstract T getBean();

	/**
	 * Generates the next primary key value for the database table.
	 *
	 * Retrieves the maximum ID from the respective table and returns the next
	 * available ID.
	 *
	 * @return next primary key value
	 */
	public Integer nextPk() {
		Connection conn = null;
		int PK = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select MAX(id) from " + getTable());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				PK = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return PK + 1;
	}

	/**
	 * Deletes a record from the database using its primary key.
	 *
	 * Executes a DELETE SQL query on the respective database table.
	 *
	 * @param id primary key of the record to be deleted
	 */
	public void delete(long id) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("delete from " + getTable() + " where id=?");
			pstmt.setLong(1, id);
			int i = pstmt.executeUpdate();
			// conn.commit();
			System.out.println("Record Deleted..." + i);
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	/**
	 * Retrieves a single record from the database using its primary key.
	 *
	 * Executes a SELECT query and populates the Bean object with the retrieved
	 * database record.
	 *
	 * @param id primary key of the record
	 * @return Bean object if the record is found, otherwise null
	 */
	public T findByPK(long id) {
		T bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from " + getTable() + " where id =?");
			pstmt.setLong(1, id);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = getBean();
				bean.setResultSet(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;

	}

	/**
	 * Searches database records using the given Bean search criteria.
	 *
	 * Supports pagination to retrieve records page by page. The WHERE condition is
	 * generated using getWhereClause().
	 *
	 * @param bean     bean object containing search criteria
	 * @param pageNo   current page number
	 * @param pageSize number of records per page
	 * @return list of matching Bean objects
	 */
	public List<T> search(T bean, int pageNo, int pageSize) {
		Connection conn = null;
		ArrayList<T> list = new ArrayList<T>();

		StringBuffer sql = new StringBuffer("select * from " + getTable() + " where 1=1");

		sql.append(getWhereClause(bean));

		if (pageSize > 0) {
			int index = (pageNo - 1) * pageSize;
			sql.append(" limit " + index + ", " + pageSize);
		}
		System.out.println("sql===>" + sql.toString());
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = getBean();
				bean.setResultSet(rs);
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

	/**
	 * Retrieves a record using a unique database column and its value.
	 *
	 * Executes a SELECT query using the specified column and searches for the
	 * matching record.
	 *
	 * @param column name of the database column
	 * @param value  value to search in the specified column
	 * @return matching Bean object if found, otherwise null
	 */
	public T findByUniqueColumn(String column, String value) {
		Connection conn = null;
		T bean = null;

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn
					.prepareStatement("select * from " + getTable() + " where " + column + " = ?");

			pstmt.setString(1, value);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = getBean();
				bean.setResultSet(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;

	}

	/**
	 * Retrieves all records from the database with pagination.
	 *
	 * This method does not apply any search filter. It retrieves records according
	 * to the given page number and page size.
	 *
	 * @param pageNo   current page number
	 * @param pageSize number of records per page
	 * @return list of Bean objects
	 * @throws ApplicationException if an error occurs while retrieving records
	 */
	// search record with pagination only, without filter(getWhereClause())
	public List<T> list(int pageNo, int pageSize) throws ApplicationException {

		ArrayList<T> list = new ArrayList<T>();
		Connection conn = null;

		StringBuffer sql = new StringBuffer("select * from " + getTable());

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				T bean = getBean();
				bean.setResultSet(rs);
				;
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting list of users");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;

	}

	/**
	 * Retrieves all records from the database without pagination and without
	 * applying any search filter.
	 *
	 * Internally calls list(0, 0) to retrieve all records.
	 *
	 * @return list of all Bean objects
	 * @throws ApplicationException if an error occurs while retrieving records
	 */
	// search all records without pagination without filter
	public List<T> list() throws ApplicationException {
		return list(0, 0);
	}
}
