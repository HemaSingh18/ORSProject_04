package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

/**
 * UserBean class represents the user information. It stores user details such
 * as name, login, password, date of birth, mobile number, role, gender, login
 * status, IP address and photo. It extends BaseBean to inherit common
 * properties.
 *
 * @author Rays Technologies
 * @version 1.0
 */
public class UserBean extends BaseBean {

	/** First name of the user. */
	private String firstName;
	

	/** Last name of the user. */
	private String lastName;

	/** Login ID or email address of the user. */
	private String login;

	/** Password of the user. */
	private String password;

	/** Confirm password entered by the user. */
	private String confirmPassword;

	/** Date of birth of the user. */
	private Date dob;

	/** Mobile number of the user. */
	private String mobileNo;

	/** Role ID assigned to the user. */
	private long roleId;

	/** Number of unsuccessful login attempts. */
	private int unsuccessfulLogin;

	/** Gender of the user. */
	private String gender;

	/** Date and time of the user's last login. */
	private Timestamp lastLogin;

	/** User account lock status. */
	private String userLock;

	/** IP address from which the user registered. */
	private String registeredIp;

	/** IP address from which the user last logged in. */
	private String lastLoginIp;

	/** Photo file name or path of the user. */
	private String photo;

	/**
	 * Gets the first name of the user.
	 *
	 * @return first name of the user
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the user.
	 *
	 * @param firstName first name of the user
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name of the user.
	 *
	 * @return last name of the user
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the user.
	 *
	 * @param lastName last name of the user
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the login ID of the user.
	 *
	 * @return login ID of the user
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Sets the login ID of the user.
	 *
	 * @param login login ID of the user
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Gets the password of the user.
	 *
	 * @return password of the user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the user.
	 *
	 * @param password password of the user
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the confirmation password.
	 *
	 * @return confirmation password
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * Sets the confirmation password.
	 *
	 * @param confirmPassword confirmation password
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * Gets the date of birth of the user.
	 *
	 * @return date of birth
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * Sets the date of birth of the user.
	 *
	 * @param dob date of birth
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * Gets the mobile number of the user.
	 *
	 * @return mobile number
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * Sets the mobile number of the user.
	 *
	 * @param mobileNo mobile number
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * Gets the role ID assigned to the user.
	 *
	 * @return role ID
	 */
	public long getRoleId() {
		return roleId;
	}

	/**
	 * Sets the role ID assigned to the user.
	 *
	 * @param roleId role ID
	 */
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	/**
	 * Gets the number of unsuccessful login attempts.
	 *
	 * @return number of unsuccessful login attempts
	 */
	public int getUnsuccessfulLogin() {
		return unsuccessfulLogin;
	}

	/**
	 * Sets the number of unsuccessful login attempts.
	 *
	 * @param unsuccessfulLogin number of unsuccessful login attempts
	 */
	public void setUnsuccessfulLogin(int unsuccessfulLogin) {
		this.unsuccessfulLogin = unsuccessfulLogin;
	}

	/**
	 * Gets the gender of the user.
	 *
	 * @return gender of the user
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender of the user.
	 *
	 * @param gender gender of the user
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Gets the last login date and time.
	 *
	 * @return last login timestamp
	 */
	public Timestamp getLastLogin() {
		return lastLogin;
	}

	/**
	 * Sets the last login date and time.
	 *
	 * @param lastLogin last login timestamp
	 */
	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * Gets the account lock status.
	 *
	 * @return user account lock status
	 */
	public String getUserLock() {
		return userLock;
	}

	/**
	 * Sets the account lock status.
	 *
	 * @param userLock user account lock status
	 */
	public void setUserLock(String userLock) {
		this.userLock = userLock;
	}

	/**
	 * Gets the registered IP address of the user.
	 *
	 * @return registered IP address
	 */
	public String getRegisteredIp() {
		return registeredIp;
	}

	/**
	 * Sets the registered IP address of the user.
	 *
	 * @param registeredIp registered IP address
	 */
	public void setRegisteredIp(String registeredIp) {
		this.registeredIp = registeredIp;
	}

	/**
	 * Gets the last login IP address of the user.
	 *
	 * @return last login IP address
	 */
	public String getLastLoginIp() {
		return lastLoginIp;
	}

	/**
	 * Sets the last login IP address of the user.
	 *
	 * @param lastLoginIp last login IP address
	 */
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	/**
	 * Gets the photo file name or path of the user.
	 *
	 * @return photo file name or path
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * Sets the photo file name or path of the user.
	 *
	 * @param photo photo file name or path
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
	 * Returns the value of the user bean.
	 *
	 * @return null
	 */
	@Override
	public String getValue() {
		return null;
	}

	/**
	 * Populates the user bean properties from the database ResultSet.
	 *
	 * @param rs ResultSet containing user information
	 */
	public void setResultSet(ResultSet rs) {
		try {
			setFirstName(rs.getString("first_name"));
			setLastName(rs.getString("last_name"));
			setLogin(rs.getString("login"));
			setPassword(rs.getString("password"));
			setDob(rs.getDate("dob"));
			setMobileNo(rs.getString("mobile_no"));
			setRoleId(rs.getLong("role_id"));
			setUnsuccessfulLogin(rs.getInt("unsuccessful_login"));
			setGender(rs.getString("gender"));
			setLastLogin(rs.getTimestamp("last_login"));
			setUserLock(rs.getString("user_lock"));
			setRegisteredIp(rs.getString("registered_ip"));
			setLastLoginIp(rs.getString("last_login_ip"));
			setPhoto(rs.getString("photo"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		super.setResultSet(rs);
	}

}