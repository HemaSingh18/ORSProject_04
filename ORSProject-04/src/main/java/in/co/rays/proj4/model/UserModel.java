package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DublicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class UserModel extends BaseModel<UserBean>{

	@Override
	public long add(UserBean bean) throws ApplicationException, DublicateRecordException {
		Connection conn = null;
		long PK=0;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into "+getTable()+" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			pstmt.setLong(1, nextPk());
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setString(4, bean.getLogin());
			pstmt.setString(5, bean.getPassword());
			pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(7, bean.getMobileNo());
			pstmt.setLong(8, bean.getRoleId());
			pstmt.setInt(9, bean.getUnsuccessfulLogin());;
			pstmt.setString(10, bean.getGender());
			pstmt.setDate(11, new java.sql.Date(bean.getLastLogin().getTime()));
			pstmt.setString(12, bean.getUserLock());
			pstmt.setString(13, bean.getRegisteredIp());
			pstmt.setString(14, bean.getLastLoginIp());
			pstmt.setString(15, bean.getCreatedBy());
			pstmt.setString(16, bean.getModifiedBy());
			pstmt.setTimestamp(17, bean.getCreatedDatetime());
			pstmt.setTimestamp(18, bean.getModifiedDatetime());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Insert Data successfully ...."+i);
		}catch(Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
		
		return PK;
	}

	@Override
	public void update(UserBean bean) throws ApplicationException, DublicateRecordException {
		
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("update "+getTable()+" SET first_name=?, last_name=?, login=?, password=?, dob=?, mobile_no=?, role_id=?, unsuccessful_login=?, gender=?, last_login=?, user_lock=?, registered_ip=?, last_login_ip=?, created_by=?, modified_by=?, created_datetime=?, modified_datetime=? where id=?" );
			
			
			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setString(3, bean.getLogin());
			pstmt.setString(4, bean.getPassword());
			pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setLong(7, bean.getRoleId());
			pstmt.setInt(8, bean.getUnsuccessfulLogin());;
			pstmt.setString(9, bean.getGender());
			pstmt.setDate(10, new java.sql.Date(bean.getLastLogin().getTime()));
			pstmt.setString(11, bean.getUserLock());
			pstmt.setString(12, bean.getRegisteredIp());
			pstmt.setString(13, bean.getLastLoginIp());
			pstmt.setString(14, bean.getCreatedBy());
			pstmt.setString(15, bean.getModifiedBy());
			pstmt.setTimestamp(16, bean.getCreatedDatetime());
			pstmt.setTimestamp(17, bean.getModifiedDatetime());
			pstmt.setLong(18, bean.getId());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Updated successfully...."+i);
		}catch(Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	@Override
	public String getWhereClause(UserBean bean) {
		
		StringBuffer sql = new StringBuffer("");
		
		if(bean!=null) {
			if(bean.getId()>0) {
				sql.append(" and id ="+bean.getId());
			}	
			if(bean.getFirstName()!=null && bean.getFirstName().length()>0) {
				sql.append(" and first_name like '"+bean.getFirstName()+"%'");
			}
			if(bean.getLastName()!=null && bean.getFirstName().length()>0) {
				sql.append(" and last_name like '"+bean.getLastName()+"%'");
			}
			if(bean.getLogin()!=null && bean.getLogin().length()>0) {
				sql.append(" and login like '"+bean.getLogin()+"%'");
			}
			if(bean.getPassword()!=null && bean.getPassword().length()>0) {
				sql.append(" and password like '"+bean.getPassword()+"%'");
			}
			if(bean.getMobileNo()!=null && bean.getMobileNo().length()<=10) {
				sql.append(" and mobile_no ="+bean.getMobileNo());
			}
			if(bean.getGender()!=null && bean.getGender().length()>0) {
				sql.append(" and gender like '"+bean.getGender()+"'");
			}
		}
		return sql.toString();
	}

	@Override
	public String getTable() {
		return "st_user";
	}

	@Override
	public UserBean getBean() {
		return new UserBean();
	}

}
