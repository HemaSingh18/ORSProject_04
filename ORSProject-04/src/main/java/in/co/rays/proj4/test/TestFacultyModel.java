package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.model.FacultyModel;

public class TestFacultyModel {
    public static FacultyModel model = new  FacultyModel();
	public static void main(String[] args) throws ParseException {
		//testAdd();
		//testUpdate();
		//testDelete();
		//testFindByPK();
		testSearch();
	}

	private static void testAdd() throws ParseException {
		FacultyBean bean = new FacultyBean();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//bean.setId(1);
		bean.setCollegeId(3);
		bean.setCollegeName("Shri Atal Bihari Vajpayee Government Arts And Commerce College");
		bean.setFirstName("Rohit");
		bean.setLastName("Pandy");
		bean.setMobileNo("7896524338");
		bean.setEmail("rohit@gmail.com");
		bean.setAddress("Vijay nagar indore");
		bean.setGender("Male");
		bean.setDateOfBirth(new java.sql.Date(format.parse("1988-03-18").getTime()));
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.add(bean);
		
	}

	private static void testUpdate() throws ParseException {
		
		FacultyBean bean = new FacultyBean();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		bean.setId(4);
		bean.setCollegeId(4);
		bean.setCollegeName("Shri Atal Bihari Vajpayee Government Arts And Commerce College");
		bean.setFirstName("Priya");
		bean.setLastName("Choudhari");
		bean.setMobileNo("9006524318");
		bean.setEmail("priya@gmail.com");
		bean.setAddress("regal squer indore");
		bean.setGender("Female");
		bean.setDateOfBirth(new java.sql.Date(format.parse("1991-09-21").getTime()));
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.update(bean);
	}

	private static void testDelete() {
	
		model.delete(4);
		
	}

	private static void testFindByPK() {
	 FacultyBean bean= model.findByPK(3);
		System.out.println(bean.getId());
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getAddress());
		System.out.println(bean.getCollegeId());
		System.out.println(bean.getCollegeName());
		System.out.println(bean.getEmail());
		System.out.println(bean.getMobileNo());
		System.out.println(bean.getGender());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());
	}

	private static void testSearch() {
		FacultyBean bean = new FacultyBean();
		List<FacultyBean> list = model.search(bean, 1, 5);
		Iterator<FacultyBean> it = list.iterator();
		while(it.hasNext()) {
			bean = it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getCollegeId());
			System.out.println(bean.getCollegeName());
			System.out.println(bean.getAddress());
			System.out.println(bean.getEmail());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getGender());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			System.out.println("--------------------------------");
		}
	}
}
