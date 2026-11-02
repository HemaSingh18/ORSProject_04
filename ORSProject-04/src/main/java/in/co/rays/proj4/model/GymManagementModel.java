package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.GymManagementBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DublicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class GymManagementModel extends BaseModel<GymManagementBean> {

	@Override
	public long add(GymManagementBean bean) throws ApplicationException, DublicateRecordException {

		Connection conn = null;
		long PK = 0;

		GymManagementBean existbean = findByName(bean.getName());
		
		if(existbean !=null) {
			
			throw new DublicateRecordException("name already exist");
		}
		try {

			PK =nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("insert into " +getTable() +" values(?,?,?,?,?)");
			ps.setLong(1, PK);
			ps.setString(2, bean.getName());
			ps.setString(3, bean.getMembershipType());
			ps.setString(4, bean.getJoiningDate());
			ps.setString(5, bean.getTrainerName());
			
			int i = ps.executeUpdate();
			conn.commit();
			System.out.println("insert data "+i);
		} catch (Exception e) {
             e.printStackTrace(); 
			JDBCDataSource.trnRollBack(conn);
		} finally {
          JDBCDataSource.closeConnection(conn);
		}
		return PK;
	}

	@Override
	public void update(GymManagementBean bean) throws ApplicationException, DublicateRecordException {
		
		Connection conn = null;
		
		GymManagementBean existbean = findByName(bean.getName());
		if(existbean !=null && existbean.getId()!=bean.getId()) {
			throw new DublicateRecordException("name already exist");
		}

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("update " +getTable() +" set name=?, membershipType=?, joiningDate=?, trainerName=? where id = ?");
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getMembershipType());
			ps.setString(3, bean.getJoiningDate());
			ps.setString(4, bean.getTrainerName());
			ps.setLong(5, bean.getId());
			
			int i = ps.executeUpdate();
			conn.commit();
			System.out.println("Data updated "+i+" row effected");
		} catch (Exception e) {

			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);

		}

	}

	@Override
	public String getWhereClause(GymManagementBean bean) {
		
		StringBuffer sql = new StringBuffer("");
		
		if(bean!=null) {
			
			if(bean.getId()>0) {
				sql.append(" and id = "+bean.getId());
			}
			if(bean.getName() !=null && bean.getName().length()>0) {
				
				sql.append(" and name like '"+bean.getName()+"%'");
				
			}
			if(bean.getMembershipType() != null && bean.getMembershipType().length()>0) {
				
				sql.append(" and membershipType like '"+bean.getMembershipType()+"%'");
			}
			if(bean.getJoiningDate() !=null && bean.getJoiningDate().length()>0) {
				
				sql.append(" and joiningDate like '"+ bean.getJoiningDate()+"%'");
			}
			if(bean.getTrainerName() !=null && bean.getTrainerName().length()>0) {
				
				sql.append(" and trainerName like '"+bean.getTrainerName()+"%'");
			}
		}
		return sql.toString();
	}

	public GymManagementBean findByName(String name) {
		
		GymManagementBean bean = findByUniqueColumn("name", name);
		
		return bean;
	}
	
	@Override
	public String getTable() {
		
		return "st_gym";
	}

	@Override
	public GymManagementBean getBean() {

		return new GymManagementBean();
	}

}
