package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DublicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class FacultyModel extends BaseModel<FacultyBean> {

	@Override
	public long add(FacultyBean bean) throws ApplicationException, DublicateRecordException {
		Connection conn = null;
		long PK = 0;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, nextPk());
			pstmt.setLong(2, bean.getCollegeId());
			pstmt.setString(3, bean.getCollegeName());
			pstmt.setString(4, bean.getFirstName());
			pstmt.setString(5, bean.getLastName());
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setString(7, bean.getEmail());
			pstmt.setString(8, bean.getAddress());
			pstmt.setString(9, bean.getGender());
			pstmt.setDate(10, new java.sql.Date((bean.getDateOfBirth()).getTime()));
			pstmt.setString(11, bean.getCreatedBy());
			pstmt.setString(12, bean.getModifiedBy());
			pstmt.setTimestamp(13, bean.getCreatedDatetime());
			pstmt.setTimestamp(14, bean.getModifiedDatetime());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("record added..." + i);

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return PK;
	}

	@Override
	public void update(FacultyBean bean) throws ApplicationException, DublicateRecordException {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("update " + getTable() + " SET college_id=?, college_name=?, first_name=?, last_name=?, email=?, mobile_no=?, address=?, gender=?, date_of_birth=?, created_by=?, modified_by=?, created_datetime=?, modified_datetime=? where id=?");
		
			pstmt.setLong(1, bean.getCollegeId());
			pstmt.setString(2, bean.getCollegeName());
			pstmt.setString(3, bean.getFirstName());
			pstmt.setString(4, bean.getLastName());
			pstmt.setString(5, bean.getMobileNo());
			pstmt.setString(6, bean.getEmail());
			pstmt.setString(7, bean.getAddress());
			pstmt.setString(8, bean.getGender());
			pstmt.setDate(9, new java.sql.Date((bean.getDateOfBirth()).getTime()));
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDatetime());
			pstmt.setTimestamp(13, bean.getModifiedDatetime());
			pstmt.setLong(14, bean.getId());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Record Updated..." + i);

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		
	}

	@Override
	public String getWhereClause(FacultyBean bean) {
		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id =" + bean.getId());
			}
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" and first_name like '" + bean.getFirstName()+"%'");
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" and last_name '" + bean.getLastName() + "%'");
			}
			if(bean.getCollegeId() !=0) {
				sql.append(" and college_id ="+bean.getCollegeId());
			}
			if (bean.getCollegeName() != null && bean.getCollegeName().length()>0) {
				sql.append(" and college_name =" + bean.getCollegeName());
			}
			if(bean.getGender() !=null && bean.getGender().length()>0) {
				sql.append(" and gender like '"+bean.getGender()+"%'");
			}
			if(bean.getEmail() !=null && bean.getEmail().length()>0) {
				sql.append(" and email like '"+bean.getEmail()+"%'");
			}
			if(bean.getDateOfBirth() !=null) {
				sql.append(" and date_of_birth like ="+bean.getDateOfBirth());
				
			}
		}
		
		return sql.toString();
	}

	@Override
	public String getTable() {
		return "st_faculty";
	}

	@Override
	public FacultyBean getBean() {
		return new FacultyBean();
	}

}
