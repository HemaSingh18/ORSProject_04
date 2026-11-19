package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * LibraryBean class represents library book information. It stores book details
 * such as title, author, price and availability. It extends BaseBean to inherit
 * common properties.
 *
 * @author Rays Technologies
 * @version 1.0
 */
public class LibraryBean extends BaseBean {

	/** Title of the book. */
	private String title;

	/** Author of the book. */
	private String author;

	/** Price of the book. */
	private double price;

	/** Availability status of the book. */
	private boolean availability;

	/**
	 * Gets the title of the book.
	 *
	 * @return title of the book
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the book.
	 *
	 * @param title title of the book
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the author of the book.
	 *
	 * @return author of the book
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Sets the author of the book.
	 *
	 * @param author author of the book
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Gets the price of the book.
	 *
	 * @return price of the book
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the price of the book.
	 *
	 * @param price price of the book
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Checks whether the book is available.
	 *
	 * @return true if the book is available, otherwise false
	 */
	public boolean isAvailability() {
		return availability;
	}

	/**
	 * Sets the availability status of the book.
	 *
	 * @param availability availability status of the book
	 */
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	/**
	 * Returns the value of the library bean.
	 *
	 * @return null
	 */
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Populates the library bean properties from the database ResultSet.
	 *
	 * @param rs ResultSet containing library book information
	 */
	@Override
	public void setResultSet(ResultSet rs) {

		try {
			setTitle(rs.getString("title"));
			setAuthor(rs.getString("author"));
			setPrice(rs.getDouble("price"));
			setAvailability(rs.getBoolean("availability"));

		} catch (SQLException e) {

			e.printStackTrace();
		}
		super.setResultSet(rs);
	}
}