package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class StudentBean extends BaseBean{
	
	private long collegeId;
	private String collegeName;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String mobileNo;
	private String email;
	
	public long getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String getValue() {
		return null;
	}
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
		setMobileNo(rs.getString("modified_by"));
		setCreatedDatetime(rs.getTimestamp("created_datetime"));
		setModifiedDatetime(rs.getTimestamp("modified_datetime"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		super.setResultSet(rs);
	}

	
}
