package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.util.Date;

/**
 * StudentBean class represents student information. It stores student details
 * such as college ID, college name, first name, last name, date of birth,
 * mobile number and email. It extends BaseBean to inherit common properties.
 *
 * @author Rays Technologies
 * @version 1.0
 */
public class StudentBean extends BaseBean {

	/** ID of the college associated with the student. */
	private long collegeId;

	/** Name of the college associated with the student. */
	private String collegeName;

	/** First name of the student. */
	private String firstName;

	/** Last name of the student. */
	private String lastName;

	/** Date of birth of the student. */
	private Date dateOfBirth;

	/** Mobile number of the student. */
	private String mobileNo;

	/** Email address of the student. */
	private String email;

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
	 * Gets the first name of the student.
	 *
	 * @return first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the student.
	 *
	 * @param firstName first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name of the student.
	 *
	 * @return last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the student.
	 *
	 * @param lastName last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the date of birth of the student.
	 *
	 * @return date of birth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Sets the date of birth of the student.
	 *
	 * @param dateOfBirth date of birth
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Gets the mobile number of the student.
	 *
	 * @return mobile number
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * Sets the mobile number of the student.
	 *
	 * @param mobileNo mobile number
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * Gets the email address of the student.
	 *
	 * @return email address
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email address of the student.
	 *
	 * @param email email address
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Returns the value of the student bean.
	 *
	 * @return null
	 */
	@Override
	public String getValue() {
		return null;
	}

	/**
	 * Populates the student bean properties from the database ResultSet.
	 *
	 * @param rs ResultSet containing student information
	 */
	@Override
	public void setResultSet(ResultSet rs) {
		try {
			setCollegeId(rs.getLong("college_id"));
			setCollegeName(rs.getString("college_name"));
			setFirstName(rs.getString("first_name"));
			setLastName(rs.getString("last_name"));
			setDateOfBirth(rs.getDate("date_of_birth"));
			setMobileNo(rs.getString("mobile_no"));
			setEmail(rs.getString("email"));
			setCreatedBy(rs.getString("created_by"));
			setModifiedBy(rs.getString("modified_by"));
			setCreatedDatetime(rs.getTimestamp("created_datetime"));
			setModifiedDatetime(rs.getTimestamp("modified_datetime"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.setResultSet(rs);
	}

}