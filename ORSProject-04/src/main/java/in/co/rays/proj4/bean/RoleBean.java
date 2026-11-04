package in.co.rays.proj4.bean;

import java.sql.ResultSet;

public class RoleBean extends BaseBean{

	public static final int ADMIN = 1;
	public static final int STUDENT =2;
	public static final int FACULTY =3;
	public static final int COLLEGE =4;
	public static final int KIOSK =5;

	
	private String name;
	private String description;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String getValue() {
		return name;
	}
	@Override
	public void setResultSet(ResultSet rs) {
		try {
			
			setName(rs.getString("name"));
			setDescription(rs.getString("description"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		super.setResultSet(rs);
	}
}
