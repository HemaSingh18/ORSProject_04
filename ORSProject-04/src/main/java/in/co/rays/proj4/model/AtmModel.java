package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.AtmBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DublicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class AtmModel extends BaseModel<AtmBean> {

	@Override
	public long add(AtmBean bean) throws ApplicationException, DublicateRecordException {

		AtmBean existBean = findByName(bean.getBankName());
		if (existBean != null) {

			throw new DublicateRecordException("Bank name already exist");
		}
		Connection conn = null;
		long Pk = 0;
		try {
			Pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, Pk);
			pstmt.setString(2, bean.getLocation());
			pstmt.setString(3, bean.getBankName());
			pstmt.setDouble(4, bean.getCashAvailable());
			pstmt.setString(5, bean.getStatus());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());

			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Data Added " + i + " row effected.");
		} catch (Exception e) {
			JDBCDataSource.trnRollBack(conn);
			e.printStackTrace();
		} finally {

			JDBCDataSource.closeConnection(conn);
		}

		return Pk;
	}

	@Override
	public void update(AtmBean bean) throws ApplicationException, DublicateRecordException {

		AtmBean existBean = findByName(bean.getBankName());

		if (existBean != null && existBean.getBankName().length() > 0) {

			throw new DublicateRecordException("Bank name already exist");

		}
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " SET location=?, bankName=?, cashAvailable=?, status=?, createdBy=?, modifiedBy=?, createdDatetime=?, ModifiedDatetime=? where id =?");

			pstmt.setString(1, bean.getLocation());
			pstmt.setString(2, bean.getBankName());
			pstmt.setDouble(3, bean.getCashAvailable());
			pstmt.setString(4, bean.getStatus());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.setLong(9, bean.getId());

			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Data Updated " + i + " row effected.");

		} catch (Exception e) {
			JDBCDataSource.trnRollBack(conn);
		} finally {

			JDBCDataSource.closeConnection(conn);
		}
	}

	@Override
	public String getWhereClause(AtmBean bean) {

		StringBuffer sql = new StringBuffer("");

		if (bean != null) {

			if (bean.getId() > 0) {

				sql.append(" and id =" + bean.getId());

			}
			if (bean.getLocation() != null && bean.getLocation().length() > 0) {

				sql.append(" and location like '" + bean.getLocation() + "%'");
			}
			if (bean.getBankName() != null && bean.getBankName().length() > 0) {

				sql.append(" and bankName like '" + bean.getBankName() + "%'");
			}
			if (bean.getCashAvailable() > 0) {

				sql.append(" and cashAvailable =" + bean.getCashAvailable());
			}
			if (bean.getStatus() != null && bean.getStatus().length() > 0) {

				sql.append(" and status like '" + bean.getStatus() + "%'");
			}
		}
		return sql.toString();
	}

	public AtmBean findByName(String bankName) {

		AtmBean bean = findByUniqueColumn("bankName", bankName);

		return bean;
	}

	@Override
	public String getTable() {

		return "st_atm";
	}

	@Override
	public AtmBean getBean() {

		return new AtmBean();
	}

}
