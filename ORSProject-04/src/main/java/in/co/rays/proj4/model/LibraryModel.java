package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.LibraryBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DublicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class LibraryModel extends BaseModel<LibraryBean>{

	@Override
	public long add(LibraryBean bean) throws ApplicationException, DublicateRecordException {
	
		LibraryBean existbean = findbytitle(bean.getTitle());
		if(existbean != null) {
			
			throw new DublicateRecordException("tital already exist");
		}
		Connection conn = null;
		long PK = 0;
		try {
			PK = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn .prepareStatement("insert into "+getTable()+" values(?,?,?,?,?)");
			pstmt.setLong(1, PK);
			pstmt.setString(2, bean.getTitle());
			pstmt.setString(3, bean.getAuthor());
			pstmt.setDouble(4, bean.getPrice());
			pstmt.setBoolean(5, bean.isAvailability());
			
			int i= pstmt.executeUpdate();
			conn.commit();
			System.out.println("Insert Data "+i);
		}catch(Exception e) {
			
			JDBCDataSource.trnRollBack(conn);
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
		return PK;
	}

	@Override
	public void update(LibraryBean bean) throws ApplicationException, DublicateRecordException {
      
		LibraryBean existbean = findbytitle(bean.getTitle());
		
		if(existbean !=null && existbean.getId()!=bean.getId()) {
			
			throw new DublicateRecordException("record already exist..");
		}
		Connection conn = null;
		
		try {
			
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("update "+getTable()+" SET title=?, author=?, price=?, availability=? where id=?");
			pstmt.setString(1, bean.getTitle());
			pstmt.setString(2, bean.getAuthor());
			pstmt.setDouble(3, bean.getPrice());
			pstmt.setBoolean(4, bean.isAvailability());
			pstmt.setLong(5, bean.getId());
			
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Update data "+i);
		}catch(Exception e) {
			JDBCDataSource.trnRollBack(conn);
		}finally {
			JDBCDataSource.closeConnection(conn);
		}

		
	}

	@Override
	public String getWhereClause(LibraryBean bean) {

      StringBuffer sql = new StringBuffer(" ");
      
      if(bean!=null) {
    	  
    	  if(bean.getId()>0) {
    		  
    		  sql.append(" and id = "+bean.getId());
    	  }
    	  if(bean.getTitle() != null && bean.getTitle().length()>0) {
    		  
    		  sql.append(" and title like '"+ bean.getTitle() +"%'");
    	  }
    	  if(bean.getAuthor() !=null && bean.getAuthor().length()>0) {
    		  
    		  sql.append(" and author like '"+bean.getAuthor()+"%'");
    	  }
    	  if(bean.getPrice() !=0 && bean.getPrice()>0) {
    		  
    		  sql.append(" and price = "+bean.getPrice());
    	  }
    	  if(bean.isAvailability()) {
    		  
    		  sql.append(" and availability = "+bean.isAvailability());
    	  }
      }
		
		return sql.toString();
	}

	public LibraryBean findbytitle(String title) {
		
		LibraryBean  bean = findByUniqueColumn("title", title);
		
		return bean;
		
	}
	@Override
	public String getTable() {
		
		return "st_library";
	}

	@Override
	public LibraryBean getBean() {

		return new LibraryBean();
	}

}
