package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.model.StudentModel;

public class TestStudentModel {

	public static StudentModel model = new StudentModel();
	
	public static void main(String[] args) throws ParseException {
		
		testAdd();
		//testUpdate();
		//testDelete();
		//testFindByPk();
		//testSearch();
	}
	private static void testAdd() throws ParseException {
		StudentBean bean = new StudentBean();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		bean.setId(1);
		bean.setCollegeId(4);
		bean.setCollegeName("Government Holkar Science College");
		bean.setFirstName("Niharika");
		bean.setLastName("Panchal");
		bean.setDateOfBirth(format.parse("11-08-1998"));
		bean.setMobileNo("9958922255");
		bean.setEmail("niharika@gmail.com");
		bean.setCreatedBy("Hema");
		bean.setModifiedBy("Hema");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.add(bean);
	}
	private static void testUpdate() throws ParseException {
		
	StudentBean bean = new StudentBean();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	bean.setId(6);
	bean.setCollegeId(4);
	bean.setCollegeName("Government Holkar Science College");
	bean.setFirstName("Niharika");
	bean.setLastName("Panchal");
	bean.setDateOfBirth(format.parse("11-08-1998"));
	bean.setMobileNo("9958922255");
	bean.setEmail("niharika@gmail.com");
	bean.setCreatedBy("Hema");
	bean.setModifiedBy("Hema");
	bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
	bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
	model.update(bean);
 }
	private static void testDelete() {
		model.delete(2);
	}
	private static void testFindByPk() {
		StudentBean bean = model.findByPK(1);
		System.out.println(bean.getId());
		System.out.println(bean.getCollegeId());
		System.out.println(bean.getCollegeName());
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getDateOfBirth());
		System.out.println(bean.getMobileNo());
		System.out.println(bean.getEmail());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		
	}
	private static void testSearch() {
		StudentBean bean = new StudentBean();
		List<StudentBean> list = model.search(bean, 1, 5);
		
		Iterator<StudentBean> it = list.iterator();
		while(it.hasNext()) {
			bean = it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getCollegeId());
			System.out.println(bean.getCollegeName());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getDateOfBirth());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getEmail());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			System.out.println("-------------------------");
		}
	}
}
