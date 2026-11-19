package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.LibraryBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DublicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * LibraryModel class provides database operations for Library records. It
 * extends BaseModel and implements CRUD operations for LibraryBean.
 *
 * @author Rays Technologies
 * @version 1.0
 */
public class LibraryModel extends BaseModel<LibraryBean> {

	/**
	 * Adds a new library record to the database. Checks whether a book with the
	 * same title already exists.
	 *
	 * @param bean the LibraryBean containing book details
	 * @return the primary key of the inserted library record
	 * @throws ApplicationException     if an application error occurs
	 * @throws DublicateRecordException if the book title already exists
	 */
	@Override
	public long add(LibraryBean bean) throws ApplicationException, DublicateRecordException {

		LibraryBean existbean = findbytitle(bean.getTitle());
		if (existbean != null) {

			throw new DublicateRecordException("tital already exist");
		}
		Connection conn = null;
		long PK = 0;
		try {
			PK = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?)");
			pstmt.setLong(1, PK);
			pstmt.setString(2, bean.getTitle());
			pstmt.setString(3, bean.getAuthor());
			pstmt.setDouble(4, bean.getPrice());
			pstmt.setBoolean(5, bean.isAvailability());

			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Insert Data " + i);
		} catch (Exception e) {

			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return PK;
	}

	/**
	 * Updates an existing library record in the database. Checks whether another
	 * book with the same title already exists.
	 *
	 * @param bean the LibraryBean containing updated book details
	 * @throws ApplicationException     if an application error occurs
	 * @throws DublicateRecordException if another book has the same title
	 */
	@Override
	public void update(LibraryBean bean) throws ApplicationException, DublicateRecordException {

		LibraryBean existbean = findbytitle(bean.getTitle());

		if (existbean != null && existbean.getId() != bean.getId()) {

			throw new DublicateRecordException("record already exist..");
		}
		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update " + getTable() + " SET title=?, author=?, price=?, availability=? where id=?");
			pstmt.setString(1, bean.getTitle());
			pstmt.setString(2, bean.getAuthor());
			pstmt.setDouble(3, bean.getPrice());
			pstmt.setBoolean(4, bean.isAvailability());
			pstmt.setLong(5, bean.getId());

			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Update data " + i);
		} catch (Exception e) {
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	/**
	 * Builds the WHERE clause using the supplied library search criteria.
	 *
	 * @param bean the LibraryBean containing search criteria
	 * @return the generated SQL WHERE conditions as a string
	 */
	@Override
	public String getWhereClause(LibraryBean bean) {

		StringBuffer sql = new StringBuffer(" ");

		if (bean != null) {

			if (bean.getId() > 0) {

				sql.append(" and id = " + bean.getId());
			}
			if (bean.getTitle() != null && bean.getTitle().length() > 0) {

				sql.append(" and title like '" + bean.getTitle() + "%'");
			}
			if (bean.getAuthor() != null && bean.getAuthor().length() > 0) {

				sql.append(" and author like '" + bean.getAuthor() + "%'");
			}
			if (bean.getPrice() != 0 && bean.getPrice() > 0) {

				sql.append(" and price = " + bean.getPrice());
			}
			if (bean.isAvailability()) {

				sql.append(" and availability = " + bean.isAvailability());
			}
		}

		return sql.toString();
	}

	/**
	 * Finds a library record by its title.
	 *
	 * @param title the title of the book to search for
	 * @return the LibraryBean matching the specified title, or null if not found
	 */
	public LibraryBean findbytitle(String title) {

		LibraryBean bean = findByUniqueColumn("title", title);

		return bean;

	}

	/**
	 * Returns the name of the library database table.
	 *
	 * @return the table name "st_library"
	 */
	@Override
	public String getTable() {

		return "st_library";
	}

	/**
	 * Creates and returns a new LibraryBean object.
	 *
	 * @return a new LibraryBean instance
	 */
	@Override
	public LibraryBean getBean() {

		return new LibraryBean();
	}

}