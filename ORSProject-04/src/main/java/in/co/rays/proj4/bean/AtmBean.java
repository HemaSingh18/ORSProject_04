package in.co.rays.proj4.bean;

import java.sql.ResultSet;

public class AtmBean extends BaseBean {

	private String location;
	private String bankName;
	private double cashAvailable;
	private String status;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public double getCashAvailable() {
		return cashAvailable;
	}

	public void setCashAvailable(double cashAvailable) {
		this.cashAvailable = cashAvailable;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String getValue() {

		return null;
	}

	@Override
	public void setResultSet(ResultSet rs) {
		try {
			setLocation(rs.getString("location"));
			setBankName(rs.getString("bankName"));
			setCashAvailable(rs.getDouble("cashAvailable"));
			setStatus(rs.getString("status"));

		} catch (Exception e) {

			e.printStackTrace();
		}
		super.setResultSet(rs);

	}

}
