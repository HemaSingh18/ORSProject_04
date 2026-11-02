package in.co.rays.proj4.test;

import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.GymManagementBean;
import in.co.rays.proj4.model.GymManagementModel;

public class TestGymManagementModel {

	public static GymManagementModel model = new GymManagementModel();

	public static void main(String[] args) {

		//testadd();
	    testupdate();
		//testdelete();
		//testsearch();

	}

	private static void testadd() {

		GymManagementBean bean = new GymManagementBean();

		// bean.setId(1);
		bean.setName("Karuna");
		bean.setMembershipType("NRK Gym three month");
		bean.setJoiningDate("2026-02-03");
		bean.setTrainerName("Richa");
		model.add(bean);

	}

	private static void testupdate() {

		GymManagementBean bean = new GymManagementBean();
		
		bean.setId(2);
		bean.setName("Karuna");
		bean.setMembershipType("NRK Gym three month");
		bean.setJoiningDate("2026-02-03");
		bean.setTrainerName("Richa");
		model.update(bean);
	}

	private static void testdelete() {
		
		model.delete(3);

	}

	private static void testsearch() {
		GymManagementBean bean = new GymManagementBean();
		
		List<GymManagementBean>list = model.search(bean, 1, 5);
		
		Iterator<GymManagementBean> it = list.iterator();
		
		while(it.hasNext()) {
			
			bean = it.next();
			
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getMembershipType());
			System.out.println(bean.getJoiningDate());
			System.out.println(bean.getTrainerName());
			System.out.println("-----------------------------------------");
		}
	}

	}

