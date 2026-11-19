
package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DublicateRecordException;
import in.co.rays.proj4.util.EmailBuilder;
import in.co.rays.proj4.util.EmailMessage;
import in.co.rays.proj4.util.EmailUtility;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * UserModel class provides database operations and business logic for User
 * records. It extends BaseModel to perform CRUD operations on UserBean objects.
 *
 * @author Rays Technologies
 * @version 1.0
 */
public class UserModel extends BaseModel<UserBean> {

	/** Logger instance for UserModel class. */
	private static final Logger log = LogManager.getLogger(UserModel.class);

	/**
	 * Inserts a new user record into the database.
	 *
	 * @param bean UserBean object containing user details.
	 * @return Primary key of the inserted user.
	 * @throws ApplicationException     if a database operation fails.
	 * @throws DublicateRecordException if the login already exists.
	 */
	@Override
	public long add(UserBean bean) throws ApplicationException, DublicateRecordException {

		log.trace("Entering add() method");

		Connection conn = null;
		UserBean existBean = findByLogin(bean.getLogin());

		if (existBean != null) {
			log.warn("Duplicate user login detected");
			throw new DublicateRecordException("login already exist");
		}

		long PK = 0;

		try {
			PK = nextPk();
			log.debug("Generated user primary key: {}", PK);

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn
					.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, PK);
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setString(4, bean.getLogin());
			pstmt.setString(5, bean.getPassword());
			pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(7, bean.getMobileNo());
			pstmt.setLong(8, bean.getRoleId());
			pstmt.setInt(9, bean.getUnsuccessfulLogin());
			pstmt.setString(10, bean.getGender());
			pstmt.setTimestamp(11, bean.getLastLogin());
			pstmt.setString(12, bean.getUserLock());
			pstmt.setString(13, bean.getRegisteredIp());
			pstmt.setString(14, bean.getLastLoginIp());
			pstmt.setString(15, bean.getCreatedBy());
			pstmt.setString(16, bean.getModifiedBy());
			pstmt.setTimestamp(17, bean.getCreatedDatetime());
			pstmt.setTimestamp(18, bean.getModifiedDatetime());
			pstmt.setString(19, bean.getPhoto());

			int i = pstmt.executeUpdate();
			conn.commit();

			log.info("User inserted successfully. Records affected: {}", i);

		} catch (Exception e) {
			log.fatal("Critical failure during user insertion", e);
			JDBCDataSource.trnRollBack(conn);

		} finally {
			JDBCDataSource.closeConnection(conn);
			log.trace("Exiting add() method");
		}

		return PK;
	}

	/**
	 * Updates an existing user record in the database.
	 *
	 * @param bean UserBean object containing updated user details.
	 * @throws ApplicationException     if a database operation fails.
	 * @throws DublicateRecordException if the login is already used.
	 */
	@Override
	public void update(UserBean bean) throws ApplicationException, DublicateRecordException {

		log.trace("Entering update() method");

		Connection conn = null;
		UserBean existBean = findByLogin(bean.getLogin());

		if (existBean != null && existBean.getId() != bean.getId()) {
			log.warn("Duplicate login detected during user update");
			throw new DublicateRecordException("login already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " SET first_name=?, last_name=?, login=?, password=?, dob=?, mobile_no=?, role_id=?, unsuccessful_login=?, gender=?, last_login=?, user_lock=?, registered_ip=?, last_login_ip=?, created_by=?, modified_by=?, created_datetime=?, modified_datetime=? where id=?");

			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setString(3, bean.getLogin());
			pstmt.setString(4, bean.getPassword());
			pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setLong(7, bean.getRoleId());
			pstmt.setInt(8, bean.getUnsuccessfulLogin());
			pstmt.setString(9, bean.getGender());
			pstmt.setTimestamp(10, bean.getLastLogin());
			pstmt.setString(11, bean.getUserLock());
			pstmt.setString(12, bean.getRegisteredIp());
			pstmt.setString(13, bean.getLastLoginIp());
			pstmt.setString(14, bean.getCreatedBy());
			pstmt.setString(15, bean.getModifiedBy());
			pstmt.setTimestamp(16, bean.getCreatedDatetime());
			pstmt.setTimestamp(17, bean.getModifiedDatetime());
			pstmt.setLong(18, bean.getId());

			int i = pstmt.executeUpdate();
			conn.commit();

			log.info("User updated successfully. Records affected: {}", i);

		} catch (Exception e) {

			log.error("Error while updating user record", e);

			JDBCDataSource.trnRollBack(conn);

			throw new ApplicationException("User update failed: " + e.getMessage());

		} finally {
			JDBCDataSource.closeConnection(conn);
			log.trace("Exiting update() method");
		}
	}

	
	// My profile update
	public void updateProfile(UserBean bean) throws ApplicationException {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = JDBCDataSource.getConnection();

			pstmt = conn.prepareStatement("UPDATE " + getTable() + " SET first_name=?, last_name=?, gender=?, dob=?, "
					+ "modified_by=?, modified_datetime=? WHERE id=?");

			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setString(3, bean.getGender());
			pstmt.setDate(4, new java.sql.Date(bean.getDob().getTime()));
			/*
			 * if (bean.getDob() != null) { pstmt.setDate(4, new
			 * java.sql.Date(bean.getDob().getTime())); } else { pstmt.setNull(4,
			 * java.sql.Types.DATE); }
			 */

			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getModifiedDatetime());
			pstmt.setLong(7, bean.getId());

			int rows = pstmt.executeUpdate();

			log.info("Profile update rows affected: " + rows);

			// 0 rows does not always mean record not found.
			// Data may already be unchanged.

		} catch (Exception e) {

			log.error("Profile update failed", e);

			throw new ApplicationException("Profile update failed: " + e.getMessage());

		} finally {

			JDBCDataSource.closeConnection(conn);
		}
	}

	/**
	 * Builds the SQL WHERE clause based on search criteria.
	 *
	 * @param bean UserBean object containing search criteria.
	 * @return SQL WHERE conditions as a String.
	 */
	@Override
	public String getWhereClause(UserBean bean) {

		log.trace("Entering getWhereClause() method");

		StringBuffer sql = new StringBuffer("");

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" and id =" + bean.getId());
			}

			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" and first_name like '" + bean.getFirstName() + "%'");
			}

			if (bean.getLastName() != null && bean.getFirstName().length() > 0) {
				sql.append(" and last_name like '" + bean.getLastName() + "%'");
			}

			if (bean.getLogin() != null && bean.getLogin().length() > 0) {
				sql.append(" and login like '" + bean.getLogin() + "%'");
			}

			if (bean.getPassword() != null && bean.getPassword().length() > 0) {
				sql.append(" and password like '" + bean.getPassword() + "%'");
			}

			if (bean.getMobileNo() != null && bean.getMobileNo().length() <= 10) {
				sql.append(" and mobile_no =" + bean.getMobileNo());
			}

			if (bean.getGender() != null && bean.getGender().length() > 0) {
				sql.append(" and gender like '" + bean.getGender() + "'");
			}

			if (bean.getDob() != null && bean.getDob().getTime() > 0) {
				sql.append(" and dob = '" + new java.sql.Date(bean.getDob().getTime()) + "'");
			}
		}

		log.debug("User search WHERE clause generated");
		log.trace("Exiting getWhereClause() method");

		return sql.toString();
	}

	/**
	 * Finds a user record using the login ID.
	 *
	 * @param login Login ID of the user to search.
	 * @return UserBean object if found; otherwise, null.
	 */
	public UserBean findByLogin(String login) {

		log.trace("Entering findByLogin() method");

		UserBean bean = findByUniqueColumn("login", login);

		if (bean != null) {
			log.debug("User found by login");
		} else {
			log.warn("No user found for supplied login");
		}

		return bean;
	}

	/**
	 * Authenticates a user using login ID and password.
	 *
	 * @param loginId  Login ID of the user.
	 * @param password Password entered by the user.
	 * @return UserBean object if credentials are valid; otherwise, null.
	 */
	public UserBean authenticate(String loginId, String password) {

		log.trace("Entering authenticate() method");

		UserBean bean = findByLogin(loginId);

		if (bean != null && bean.getPassword().equals(password)) {
			log.info("User authentication successful");
			return bean;
		}

		log.warn("User authentication failed");
		return null;
	}

	/**
	 * Changes the user's password after verifying the old password.
	 *
	 * @param newPassword New password to set.
	 * @param oldPassword Current password for verification.
	 * @param login       Login ID of the user.
	 * @return Updated UserBean object if successful; otherwise, null.
	 */
	public UserBean changePassword(String newPassword, String oldPassword, String login) {

		log.trace("Entering changePassword() method");

		UserBean bean = findByLogin(login);

		if (bean != null && bean.getPassword().equals(oldPassword)) {

			bean.setPassword(newPassword);
			update(bean);

			HashMap<String, String> map = new HashMap<String, String>();
			EmailMessage msg = new EmailMessage();

			map.put("login", bean.getLogin());
			map.put("password", bean.getPassword());
			map.put("firstName", bean.getFirstName());
			map.put("lastName", bean.getLastName());

			msg.setTo(map.get("login"));
			msg.setSubject("Password Change");
			msg.setMessage(EmailBuilder.getChangePasswordMessage(map));
			msg.setMessageType(EmailMessage.HTML_MSG);

			EmailUtility.sendMail(msg);

			log.info("Password changed successfully");
			return bean;
		}

		log.warn("Password change failed: user not found or old password incorrect");
		return null;
	}

	/**
	 * Retrieves password details using the login ID and sends recovery email.
	 *
	 * @param login Login ID of the user.
	 * @return UserBean object if the user exists; otherwise, null.
	 */
	public UserBean forgotPassword(String login) {

		log.trace("Entering forgotPassword() method");

		UserBean bean = findByLogin(login);

		if (bean != null) {

			HashMap<String, String> map = new HashMap<String, String>();
			EmailMessage msg = new EmailMessage();

			map.put("login", bean.getLogin());
			map.put("password", bean.getPassword());
			map.put("firstName", bean.getFirstName());
			map.put("lastName", bean.getLastName());

			msg.setTo(map.get("login"));
			msg.setSubject("Password Changed");
			msg.setMessage(EmailBuilder.getForgetPasswordMessage(map));
			msg.setMessageType(EmailMessage.HTML_MSG);

			EmailUtility.sendMail(msg);

			log.info("Forgot password email sent successfully");
			return bean;
		}

		log.warn("Forgot password failed: user not found");
		return null;
	}

	/**
	 * Updates the photo path of a user in the database.
	 *
	 * @param id    User ID.
	 * @param photo Photo path or file name.
	 */
	public void updatePhoto(long id, String photo) {

		log.trace("Entering updatePhoto() method");

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("update " + getTable() + " set photo=? where id = ? ");

			pstmt.setString(1, photo);
			pstmt.setLong(2, id);

			int i = pstmt.executeUpdate();
			conn.commit();

			log.info("User photo updated. Records affected: {}", i);

		} catch (Exception e) {
			log.error("Error while updating user photo", e);
			JDBCDataSource.trnRollBack(conn);
			throw new ApplicationException("Exception in updating User Photo");

		} finally {
			JDBCDataSource.closeConnection(conn);
			log.trace("Exiting updatePhoto() method");
		}
	}

	/**
	 * Registers a new user and sends registration information by email.
	 *
	 * @param bean UserBean object containing registration details.
	 * @return Primary key of the newly registered user.
	 */
	public long register(UserBean bean) {

		log.trace("Entering register() method");

		long pk = add(bean);

		HashMap<String, String> map = new HashMap<String, String>();
		EmailMessage msg = new EmailMessage();

		map.put("login", bean.getLogin());
		map.put("password", bean.getPassword());

		msg.setTo(map.get("login"));
		msg.setSubject("User Registration Information");
		msg.setMessage(EmailBuilder.getUserRegistrationMessage(map));
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);

		log.info("User registration email sent successfully");

		return pk;
	}

	/**
	 * Returns the name of the user database table.
	 *
	 * @return Database table name.
	 */
	@Override
	public String getTable() {

		log.trace("Entering getTable() method");

		return "st_user";
	}

	/**
	 * Creates and returns a new UserBean object.
	 *
	 * @return A new UserBean instance.
	 */
	@Override
	public UserBean getBean() {

		log.trace("Entering getBean() method");

		return new UserBean();
	}
}
