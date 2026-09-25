package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.MarksheetModel;

public class TestMarksheetModel {

	private static MarksheetModel modell = new MarksheetModel();

	public static void main(String[] args) {
		// testAdd();
		// testUpdate();
		// testDelete();
		// testFindByPk();
		testSearch();
	}

	private static void testAdd() {
		MarksheetBean bean = new MarksheetBean();
		// bean.setId(2);
		bean.setRollNo("105");
		bean.setStudentId(5);
		bean.setName("Arti Patel");
		bean.setPhysics(55);
		bean.setChemistry(45);
		bean.setMaths(25);
		bean.setCreatedBy("Hema");
		bean.setModifiedBy("Hema");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		modell.add(bean);

	}

	private static void testUpdate() {
		MarksheetBean bean = new MarksheetBean();
		bean.setId(5);
		bean.setRollNo("105");
		bean.setStudentId(5);
		bean.setName("Arti Patel");
		bean.setPhysics(30);
		bean.setChemistry(55);
		bean.setMaths(65);
		bean.setCreatedBy("Hema");
		bean.setModifiedBy("Hema");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		modell.update(bean);
	}

	private static void testDelete() {
		modell.delete(7);
	}

	private static void testFindByPk() {
		
		MarksheetBean bean =modell.findByPK(2);
		System.out.println(bean.getId());
		System.out.println(bean.getRollNo());
		System.out.println(bean.getName());
		System.out.println(bean.getPhysics());
		System.out.println(bean.getChemistry());
		System.out.println(bean.getMaths());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());
	}

	private static void testSearch() {
		
		MarksheetBean bean = new MarksheetBean();
			bean.setName("Arti");
		
			List<MarksheetBean> list = modell.search(bean, 1, 5);
			
			Iterator<MarksheetBean> it = list.iterator();
			
			while(it.hasNext()) {
				bean = it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getRollNo());
				System.out.println(bean.getPhysics());
				System.out.println(bean.getChemistry());
				System.out.println(bean.getMaths());
				System.out.println(bean.getChemistry());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getCreatedDatetime());
				System.out.println(bean.getModifiedDatetime());
				
				System.out.println("---------------------------------");
         }
	}
}
