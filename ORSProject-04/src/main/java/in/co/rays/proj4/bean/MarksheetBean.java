package in.co.rays.proj4.bean;

import java.sql.ResultSet;

/**
 * MarksheetBean class represents student marksheet information. It stores
 * marksheet details such as roll number, student ID, student name, and marks in
 * Physics, Chemistry and Mathematics. It extends BaseBean to inherit common
 * properties.
 *
 * @author Rays Technologies
 * @version 1.0
 */
public class MarksheetBean extends BaseBean {

	/** Roll number of the student. */
	private String rollNo;

	/** Unique ID of the student. */
	private long studentId;

	/** Name of the student. */
	private String name;

	/** Marks obtained in Physics. */
	private int physics;

	/** Marks obtained in Chemistry. */
	private int chemistry;

	/** Marks obtained in Mathematics. */
	private int maths;

	/**
	 * Gets the roll number of the student.
	 *
	 * @return student roll number
	 */
	public String getRollNo() {
		return rollNo;
	}

	/**
	 * Sets the roll number of the student.
	 *
	 * @param rollNo student roll number
	 */
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	/**
	 * Gets the student ID.
	 *
	 * @return student ID
	 */
	public long getStudentId() {
		return studentId;
	}

	/**
	 * Sets the student ID.
	 *
	 * @param studentId student ID
	 */
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	/**
	 * Gets the name of the student.
	 *
	 * @return student name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the student.
	 *
	 * @param name student name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the Physics marks.
	 *
	 * @return Physics marks
	 */
	public int getPhysics() {
		return physics;
	}

	/**
	 * Sets the Physics marks.
	 *
	 * @param physics Physics marks
	 */
	public void setPhysics(int physics) {
		this.physics = physics;
	}

	/**
	 * Gets the Chemistry marks.
	 *
	 * @return Chemistry marks
	 */
	public int getChemistry() {
		return chemistry;
	}

	/**
	 * Sets the Chemistry marks.
	 *
	 * @param chemistry Chemistry marks
	 */
	public void setChemistry(int chemistry) {
		this.chemistry = chemistry;
	}

	/**
	 * Gets the Mathematics marks.
	 *
	 * @return Mathematics marks
	 */
	public int getMaths() {
		return maths;
	}

	/**
	 * Sets the Mathematics marks.
	 *
	 * @param maths Mathematics marks
	 */
	public void setMaths(int maths) {
		this.maths = maths;
	}

	/**
	 * Returns the value of the marksheet bean.
	 *
	 * @return null
	 */
	@Override
	public String getValue() {
		return name;
		
	}

	/**
	 * Populates the marksheet bean properties from the database ResultSet.
	 *
	 * @param rs ResultSet containing marksheet information
	 */
	public void setResultSet(ResultSet rs) {
		try {
			setRollNo(rs.getString("roll_no"));
			setStudentId(rs.getLong("student_id"));
			setName(rs.getString("name"));
			setPhysics(rs.getInt("physics"));
			setChemistry(rs.getInt("chemistry"));
			setMaths(rs.getInt("maths"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.setResultSet(rs);
	}

}