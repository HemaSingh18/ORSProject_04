package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LibraryBean extends BaseBean{
	
	private String title;
	private String author;
	private double price;
	private boolean availability;
	
	

	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getAuthor() {
		return author;
	}



	public void setAuthor(String author) {
		this.author = author;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public boolean isAvailability() {
		return availability;
	}



	public void setAvailability(boolean availability) {
		this.availability = availability;
	}



	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setResultSet(ResultSet rs)    {
		
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
