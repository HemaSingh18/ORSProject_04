package in.co.rays.proj4.bean;

import java.sql.ResultSet;

/**
 * CourseBean class represents course information. It stores course details such
 * as name, description and duration. It extends BaseBean to inherit common
 * properties.
 *
 * @author Rays Technologies
 * @version 1.0
 */
public class CourseBean extends BaseBean {

	/** Name of the course. */
	private String name;

	/** Description of the course. */
	private String description;

	/** Duration of the course. */
	private String duration;

	/**
	 * Gets the name of the course.
	 *
	 * @return name of the course
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the course.
	 *
	 * @param name name of the course
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description of the course.
	 *
	 * @return description of the course
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the course.
	 *
	 * @param description description of the course
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the duration of the course.
	 *
	 * @return duration of the course
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * Sets the duration of the course.
	 *
	 * @param duration duration of the course
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * Returns the value of the course bean.
	 *
	 * @return null
	 */
	@Override
	public String getValue() {
		return null;
	}

	/**
	 * Populates the course bean properties from the database ResultSet.
	 *
	 * @param rs ResultSet containing course information
	 */
	public void setResultSet(ResultSet rs) {
		try {
			setName(rs.getString("name"));
			;
			setDescription(rs.getString("description"));
			setDuration(rs.getString("duration"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.setResultSet(rs);
	}

}