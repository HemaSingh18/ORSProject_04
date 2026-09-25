package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.model.CourseModel;

public class TestCourseModel {

	public static CourseModel model = new CourseModel();
	
	public static void main(String[] args) {
		//testAdd();
		//testUpdate();
		//testDelete();
		//testFindByPK();
		testSearch();
	}

	private static void testAdd() {
		
		CourseBean bean = new CourseBean();
		//bean.setId(1);
		bean.setName("Computer Science");
		bean.setDescription("Study of computers, software, programming, databases, algorithms, networking, operating systems.");
		bean.setDuration("Six month");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.add(bean);
	}

	private static void testUpdate() {
		
		CourseBean bean = new CourseBean();
		bean.setId(4);
		bean.setName("Computer Science");
		bean.setDescription("Study of computers, software, programming, databases, algorithms, networking, operating systems.");
		bean.setDuration("nine month");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.update(bean);
	}

	private static void testDelete() {
		model.delete(5);
		
	}

	private static void testFindByPK() {
		CourseBean bean = model.findByPK(2);
		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getDescription());
		System.out.println(bean.getDuration());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());
		
	}

	private static void testSearch() {
		
		CourseBean bean = new CourseBean();
		List<CourseBean> list = model.search(bean, 1, 5);
		Iterator<CourseBean> it = list.iterator();
		while(it.hasNext()) {
			bean = it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
			System.out.println(bean.getDuration());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			System.out.println("-------------------------------");
		}
	}


}
