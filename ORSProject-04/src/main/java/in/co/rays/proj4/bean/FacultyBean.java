package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.util.Date;

/**
 * FacultyBean class represents faculty information. It stores faculty details
 * such as college ID, college name, first name, last name, email, mobile
 * number, address, gender and date of birth. It extends BaseBean to inherit
 * common properties.
 *
 * @author Rays Technologies
 * @version 1.0
 */
public class FacultyBean extends BaseBean {

	/** ID of the college associated with the faculty. */
	private long collegeId;

	/** Name of the college associated with the faculty. */
	private String collegeName;

	/** First name of the faculty. */
	private String firstName;

	/** Last name of the faculty. */
	private String lastName;

	/** Email address of the faculty. */
	private String email;

	/** Mobile number of the faculty. */
	private String mobileNo;

	/** Residential address of the faculty. */
	private String address;

	/** Gender of the faculty. */
	private String gender;

	/** Date of birth of the faculty. */
	private Date dateOfBirth;

	/**
	 * Gets the college ID.
	 *
	 * @return college ID
	 */
	public long getCollegeId() {
		return collegeId;
	}

	/**
	 * Sets the college ID.
	 *
	 * @param collegeId college ID
	 */
	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}

	/**
	 * Gets the college name.
	 *
	 * @return college name
	 */
	public String getCollegeName() {
		return collegeName;
	}

	/**
	 * Sets the college name.
	 *
	 * @param collegeName college name
	 */
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	/**
	 * Gets the first name of the faculty.
	 *
	 * @return first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the faculty.
	 *
	 * @param firstName first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name of the faculty.
	 *
	 * @return last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the faculty.
	 *
	 * @param lastName last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the email address of the faculty.
	 *
	 * @return email address
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email address of the faculty.
	 *
	 * @param email email address
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the mobile number of the faculty.
	 *
	 * @return mobile number
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * Sets the mobile number of the faculty.
	 *
	 * @param mobileNo mobile number
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * Gets the address of the faculty.
	 *
	 * @return faculty address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address of the faculty.
	 *
	 * @param address faculty address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the gender of the faculty.
	 *
	 * @return gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender of the faculty.
	 *
	 * @param gender gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Gets the date of birth of the faculty.
	 *
	 * @return date of birth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Sets the date of birth of the faculty.
	 *
	 * @param dateOfBirth date of birth
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Returns the value of the faculty bean.
	 *
	 * @return null
	 */
	@Override
	public String getValue() {
		return null;
	}

	/**
	 * Populates the faculty bean properties from the database ResultSet.
	 *
	 * @param rs ResultSet containing faculty information
	 */
	public void setResultSet(ResultSet rs) {
		try {
			setCollegeId(rs.getLong("college_id"));
			setCollegeName(rs.getString("college_name"));
			setFirstName(rs.getString("first_name"));
			setLastName(rs.getString("last_name"));
			setEmail(rs.getString("email"));
			setMobileNo(rs.getString("mobile_no"));
			setAddress(rs.getString("address"));
			setGender(rs.getString("gender"));
			setDateOfBirth(rs.getDate("date_of_birth"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		super.setResultSet(rs);
	}
}