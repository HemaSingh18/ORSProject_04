package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DublicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class CollegeModel extends BaseModel<CollegeBean>{

	@Override
	public long add(CollegeBean bean) throws ApplicationException, DublicateRecordException {
		int PK = 0;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into "+getTable()+" values(?,?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, nextPk());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getAddress());
			pstmt.setString(4, bean.getState());
			pstmt.setString(5, bean.getCity());
			pstmt.setString(6, bean.getPhoneNo());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
		int i =	pstmt.executeUpdate();
		conn.commit();
		System.out.println("Insert Data Successfully.. "+i);
		}catch(Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
		return PK;
	}

	@Override
	public void update(CollegeBean bean) throws ApplicationException, DublicateRecordException {
		
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("update "+getTable()+" SET name=?, address=?, state=?, city=?, phone_no=?, created_by=?, modified_by=?, created_datetime=?, modified_datetime=? where id =?");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getAddress());
			pstmt.setString(3, bean.getState());
			pstmt.setString(4, bean.getCity());
			pstmt.setString(5, bean.getPhoneNo());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			pstmt.setLong(10, bean.getId());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Update data... "+i);
		}catch(Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	@Override
	public String getWhereClause(CollegeBean bean) {
		
StringBuffer sql = new StringBuffer("");
		
		if(bean!=null) {
			if(bean.getId()>0) {
				sql.append(" and id ="+bean.getId());
			}	
			if(bean.getName()!=null && bean.getName().length()>0) {
				sql.append(" and name like '"+bean.getName()+"%'");
			}
			if(bean.getAddress()!=null && bean.getAddress().length()>0) {
				sql.append(" and address like '"+bean.getAddress()+"%'");
			}
			if(bean.getState()!=null && bean.getState().length()>0) {
				sql.append(" and state like '"+bean.getState()+"%'");
			}
			if(bean.getCity()!=null && bean.getCity().length()>0) {
				sql.append(" and city like '"+bean.getCity()+"%'");
			}
			if(bean.getPhoneNo()!=null && bean.getPhoneNo().length()<=10) {
				sql.append(" and phone_no ="+bean.getPhoneNo());
			}
			
		}
		return sql.toString();
		
	}

	@Override
	public String getTable() {
		return "st_college";
	}

	@Override
	public CollegeBean getBean() {
		return new CollegeBean();
	}

}
