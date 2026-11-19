package in.co.rays.proj4.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;

//Singleton & Combopool datasource.
//singleTonClass 
//Advantage:-
//1. provide Connection re-useablity
//2. provide Reliable Connection with database.
//3. provide Maximum Connection limitation with database.
/**
 * JDBCDataSource class manages database connections using the C3P0 connection
 * pool. It follows the Singleton design pattern to provide a single shared
 * datasource instance.
 * 
 * @author Hema Singh
 * @version 1.0
 */
public final class JDBCDataSource {

	private static JDBCDataSource jdbc = null;
	private ComboPooledDataSource cpds = null;

	private static ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.proj4.bundle.System");

	/**
	 * Private constructor initializes the C3P0 connection pool. Loads database
	 * configuration properties from the ResourceBundle.
	 */
	private JDBCDataSource() {

		// comobopool data source ki class predefine class hai. and 4 properties follow
		// kiya
		// Reliable connection ho raha hai (connection + limitation )
		cpds = new ComboPooledDataSource();
		try {
			cpds.setDriverClass(rb.getString("driver"));
			cpds.setJdbcUrl(rb.getString("url"));
			cpds.setUser(rb.getString("username"));
			cpds.setPassword(rb.getString("password"));
			cpds.setMaxPoolSize(30);
			cpds.setMinPoolSize(10);
			cpds.setAcquireIncrement(5);
			cpds.setInitialPoolSize(10);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	/**
	 * Returns the single instance of JDBCDataSource. Creates the instance if it
	 * does not already exist.
	 *
	 * @return Singleton instance of JDBCDataSource.
	 */
	private static JDBCDataSource getInstance() {

		if (jdbc == null) {
			jdbc = new JDBCDataSource();
			return jdbc;
		}
		return jdbc;
	}

	/**
	 * Obtains a database connection from the connection pool.
	 *
	 * @return A database Connection, or null if a connection cannot be obtained.
	 */
	public static Connection getConnection() {

		try {
			return getInstance().cpds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Closes the specified database connection.
	 *
	 * @param conn Connection to be closed.
	 */
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Rolls back the current transaction for the specified connection.
	 *
	 * @param conn Connection on which the rollback operation is performed.
	 */
	public static void trnRollBack(Connection conn) {
		if (conn != null) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}