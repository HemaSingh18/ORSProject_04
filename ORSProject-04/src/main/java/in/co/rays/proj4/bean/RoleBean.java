package in.co.rays.proj4.bean;

import java.sql.ResultSet;

/**
 * RoleBean class represents role information. It stores role details such as
 * role name and description. It also defines constant values for different user
 * roles. It extends BaseBean to inherit common properties.
 *
 * @author Rays Technologies
 * @version 1.0
 */
public class RoleBean extends BaseBean {

	/** Constant representing the Admin role. */
	public static final int ADMIN = 1;

	/** Constant representing the Student role. */
	public static final int STUDENT = 2;

	/** Constant representing the Faculty role. */
	public static final int FACULTY = 3;

	/** Constant representing the College role. */
	public static final int COLLEGE = 4;

	/** Constant representing the Kiosk role. */
	public static final int KIOSK = 5;

	/** Name of the role. */
	private String name;

	/** Description of the role. */
	private String description;

	/**
	 * Gets the name of the role.
	 *
	 * @return name of the role
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the role.
	 *
	 * @param name name of the role
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description of the role.
	 *
	 * @return description of the role
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the role.
	 *
	 * @param description description of the role
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the name of the role as its value.
	 *
	 * @return role name
	 */
	@Override
	public String getValue() {
		return name;
	}

	/**
	 * Populates the role bean properties from the database ResultSet.
	 *
	 * @param rs ResultSet containing role information
	 */
	@Override
	public void setResultSet(ResultSet rs) {
		try {
			setName(rs.getString("name"));
			setDescription(rs.getString("description"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.setResultSet(rs);
	}

}