
package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * BaseBean is an abstract parent class of all Bean classes in the ORS Project.
 * 
 * It contains common properties such as id, createdBy, modifiedBy,
 * createdDatetime and modifiedDatetime.
 * 
 * It also provides getter and setter methods to access and modify common bean
 * properties.
 * 
 * It implements DropdownListBean interface to provide dropdown list
 * functionality.
 * 
 * @author Hema Singh
 * @version 1.0
 * @since 2026
 */
public abstract class BaseBean implements DropdownListBean {

	/** Unique ID of the record. */
	protected long id;

	/** Login ID of the user who created the record. */
	protected String createdBy;

	/** Login ID of the user who last modified the record. */
	protected String modifiedBy;

	/** Date and time when the record was created. */
	protected Timestamp createdDatetime;

	/** Date and time when the record was last modified. */
	protected Timestamp modifiedDatetime;

	/**
	 * Returns the ID of the record.
	 * 
	 * @return record ID
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the ID of the record.
	 * 
	 * @param id unique ID of the record
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Returns the login ID of the user who created the record.
	 * 
	 * @return createdBy user login ID
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the login ID of the user who created the record.
	 * 
	 * @param createdBy user login ID
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Returns the login ID of the user who last modified the record.
	 * 
	 * @return modifiedBy user login ID
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * Sets the login ID of the user who modified the record.
	 * 
	 * @param modifiedBy user login ID
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * Returns the creation date and time of the record.
	 * 
	 * @return createdDatetime timestamp
	 */
	public Timestamp getCreatedDatetime() {
		return createdDatetime;
	}

	/**
	 * Sets the creation date and time of the record.
	 * 
	 * @param createdDatetime creation timestamp
	 */
	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	/**
	 * Returns the last modification date and time of the record.
	 * 
	 * @return modifiedDatetime timestamp
	 */
	public Timestamp getModifiedDatetime() {
		return modifiedDatetime;
	}

	/**
	 * Sets the last modification date and time of the record.
	 * 
	 * @param modifiedDatetime modification timestamp
	 */
	public void setModifiedDatetime(Timestamp modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}

	/**
	 * Returns the record ID as a String.
	 * 
	 * This method is implemented from DropdownListBean interface and is used for
	 * dropdown list functionality.
	 * 
	 * @return record ID as String
	 */
	@Override
	public String getKey() {
		return id + "";
	}

	/**
	 * Populates bean properties using database ResultSet.
	 * 
	 * Retrieves common record information such as ID, createdBy, modifiedBy,
	 * createdDatetime and modifiedDatetime from the ResultSet.
	 * 
	 * @param rs ResultSet containing database record data
	 */
	public void setResultSet(ResultSet rs) {
		try {
			setId(rs.getLong("id"));
			setCreatedBy(rs.getString("created_by"));
			setModifiedBy(rs.getString("modified_by"));
			setCreatedDatetime(rs.getTimestamp("created_datetime"));
			setModifiedDatetime(rs.getTimestamp("modified_datetime"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
