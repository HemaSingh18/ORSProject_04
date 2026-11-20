package in.co.rays.proj4.bean;

import java.sql.ResultSet;

/**
 * SubjectBean class represents subject information. It stores subject details
 * such as name, description and course ID. It extends BaseBean to inherit
 * common properties.
 *
 * @author Rays Technologies
 * @version 1.0
 */
public class SubjectBean extends BaseBean {

	/** Name of the subject. */
	private String name;

	/** Description of the subject. */
	private String description;

	/** Course ID associated with the subject. */
	private long courseId;

	/**
	 * Gets the name of the subject.
	 *
	 * @return name of the subject
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the subject.
	 *
	 * @param name name of the subject
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description of the subject.
	 *
	 * @return description of the subject
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the subject.
	 *
	 * @param description description of the subject
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the course ID associated with the subject.
	 *
	 * @return course ID
	 */
	public long getCourseId() {
		return courseId;
	}

	/**
	 * Sets the course ID associated with the subject.
	 *
	 * @param courseId course ID
	 */
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	/**
	 * Returns the value of the subject bean.
	 *
	 * @return null
	 */
	@Override
	public String getValue() {
		return null;
	}

	/**
	 * Populates the subject bean properties from the database ResultSet.
	 *
	 * @param rs ResultSet containing subject information
	 */
	public void setResultSet(ResultSet rs) {
		try {
			setName(rs.getString("name"));
			setDescription(rs.getString("description"));
			setCourseId(rs.getInt("course_id"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.setResultSet(rs);
	}

}