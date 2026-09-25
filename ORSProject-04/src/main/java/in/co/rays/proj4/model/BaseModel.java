package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DublicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public abstract class BaseModel<T extends BaseBean> {

	public abstract long add(T bean) throws ApplicationException, DublicateRecordException;

	public abstract void update(T bean) throws ApplicationException, DublicateRecordException;

	public abstract String getWhereClause(T bean);

	public abstract String getTable();

	public abstract T getBean();

	public Integer nextPk() {
		Connection conn = null;
		int PK = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select MAX(id) from " + getTable());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				PK = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return PK + 1;
	}

	public void delete(long id) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("delete from " + getTable() + " where id=?");
			pstmt.setLong(1, id);
			int i = pstmt.executeUpdate();
			// conn.commit();
			System.out.println("Record Deleted..." + i);
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public T findByPK(long id) {
		T bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from " + getTable() + " where id =?");
			pstmt.setLong(1, id);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = getBean();
				bean.setResultSet(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;

	}

	public List<T> search(T bean, int pageNo, int pageSize) {
		Connection conn = null;
		List<T> list = new ArrayList<T>();

		StringBuffer sql = new StringBuffer("select * from " + getTable() + " where 1=1");

		sql.append(getWhereClause(bean));

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}
		System.out.println("sql===>" + sql.toString());
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				bean = getBean();
				bean.setResultSet(rs);
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}
}
