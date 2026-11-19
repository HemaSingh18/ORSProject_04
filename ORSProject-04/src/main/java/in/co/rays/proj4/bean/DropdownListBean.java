package in.co.rays.proj4.bean;

/**
 * DropdownListBean interface defines methods required to represent data in a
 * dropdown list.
 *
 * It provides methods to retrieve the key and value of an item displayed in a
 * dropdown list.
 *
 * @author Rays Technologies
 * @version 1.0
 */
public interface DropdownListBean {

	/**
	 * Gets the key of the dropdown list item.
	 *
	 * @return key of the item
	 */
	public String getKey();

	/**
	 * Gets the value of the dropdown list item.
	 *
	 * @return value of the item
	 */
	public String getValue();

}