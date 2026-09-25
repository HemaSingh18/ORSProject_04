package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.model.SubjectModel;

public class TestSubjectModel {

	public static SubjectModel model = new SubjectModel();
	
	public static void main(String[] args) {
		//testAdd();
		//testUpdate();
		//testDelete();
		//testFindByPK();
		testSearch();
	}

	private static void testAdd() {
		SubjectBean bean = new SubjectBean();
		//bean.setId(1);
		bean.setName("Computer Science");
		bean.setDescription("Study of computers, software, programming, databases, algorithms, networking, operating systems.");
		bean.setCourseId(4);
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.add(bean);
		
	}

	private static void testUpdate() {
		SubjectBean bean = new SubjectBean();
		bean.setId(4);
		bean.setName("Social Science");
		bean.setDescription("Social description..");
		bean.setCourseId(4);
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
		SubjectBean bean = model.findByPK(2);
		
		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getCourseId());
		System.out.println(bean.getDescription());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());
	}

	private static void testSearch() {
		
		SubjectBean bean = new SubjectBean();
	//	bean.setCourseId(2);
		List<SubjectBean> list = model.search(bean, 1, 5);
		
		Iterator<SubjectBean> it = list.iterator();
		while(it.hasNext()) {
			bean = it.next();
			
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getCourseId());
			System.out.println(bean.getDescription());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			System.out.println("---------------------------------");
		}
	}
}
