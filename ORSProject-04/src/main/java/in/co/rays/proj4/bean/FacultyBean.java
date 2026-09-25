package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.util.Date;

public class FacultyBean extends BaseBean{

	private long collegeId;
	private String collegeName;
	private String firstName;
	private String lastName;
	private String email;
	private String mobileNo;
	private String address;
	private String gender;
	private Date dateOfBirth;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	@Override
	public String getValue() {
		return null;
	}
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
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		super.setResultSet(rs);
	}
}
