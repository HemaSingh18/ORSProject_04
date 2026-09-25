package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.UserModel;

public class TestUserModel {

	public static UserModel model = new UserModel();
	
	public static void main(String[] args) throws ParseException {
		//testAdd();
		//testUpdate();
		//testDelete();
		//testFindByPk();
		testSearch();
	}

	private static void testAdd() throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		UserBean bean = new UserBean();
		//bean.setId(1);
		bean.setFirstName("Vikas");
		bean.setLastName("Yadav");
		bean.setLogin("vikas");
		bean.setPassword("vikas123");
		bean.setDob(new java.sql.Date(format.parse("1996-07-12").getTime()));
		bean.setMobileNo("9345678901");
		bean.setRoleId(2);
		bean.setUnsuccessfulLogin(0);
		bean.setGender("Male");
		bean.setLastLogin(new java.sql.Date(format.parse("2026-09-21").getTime()));
		bean.setUserLock("N");
		bean.setRegisteredIp("192.168.1.14");
		bean.setLastLoginIp("192.168.1.14");
		bean.setCreatedBy("Hema");
		bean.setModifiedBy("Hema");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.add(bean);
	}
	private static void testUpdate() throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		UserBean bean = new UserBean();
		
		bean.setId(6);
		bean.setFirstName("Vikas");
		bean.setLastName("Yadav");
		bean.setLogin("vikas");
		bean.setPassword("vikas123");
		bean.setDob(new java.sql.Date(format.parse("1996-07-12").getTime()));
		bean.setMobileNo("9345678901");
		bean.setRoleId(2);
		bean.setUnsuccessfulLogin(0);
		bean.setGender("Male");
		bean.setLastLogin(new java.sql.Date(format.parse("2026-09-21").getTime()));
		bean.setUserLock("N");
		bean.setRegisteredIp("192.168.1.14");
		bean.setLastLoginIp("192.168.1.14");
		bean.setCreatedBy("ex");
		bean.setModifiedBy("ex");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.update(bean);
		
	}
	private static void testDelete() {
		model.delete(6);
	}
	private static void testFindByPk() {
		
		UserBean bean =model.findByPK(2);
		
		System.out.println(bean.getId());
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getLogin());
		System.out.println(bean.getPassword());
		System.out.println(bean.getGender());
		System.out.println(bean.getMobileNo());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		
	}
	private static void testSearch() {
		
		UserBean bean = new UserBean();
	//	bean.setFirstName("Amit");
		List<UserBean> list = model.search(bean, 1, 5);
		
		Iterator<UserBean> it = list.iterator();
		
		while(it.hasNext()) {
			bean = it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getUnsuccessfulLogin());
			System.out.println(bean.getGender());
			System.out.println(bean.getLastLogin());
			System.out.println(bean.getUserLock());
			System.out.println(bean.getRegisteredIp());
			System.out.println(bean.getLastLoginIp());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			
			System.out.println("---------------------------");
		}
	}
}
