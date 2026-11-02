package in.co.rays.proj4.test;

import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.LibraryBean;
import in.co.rays.proj4.model.LibraryModel;

public class TestLibraryModel {

	public static LibraryModel model = new LibraryModel();

	public static void main(String[] args) {

		// testadd();
		 testupdate();
		//testdelete();
		//testsearch();

	}

	private static void testadd() {

		LibraryBean bean = new LibraryBean();

		 bean.setId(6);
		bean.setTitle("Effective Java");
		bean.setAuthor("Joshua Bloch");
		bean.setPrice(899);
		bean.setAvailability(true);

		model.add(bean);

	}

	private static void testupdate() {

		LibraryBean bean = new LibraryBean();
		bean.setId(7);
		bean.setTitle("Clean Code");
		bean.setAuthor("Robert C. Martin");
		bean.setPrice(899);
		bean.setAvailability(false);
		model.update(bean);

	}

	private static void testdelete() {

		model.delete(2);

	}

	private static void testsearch() {

		LibraryBean bean = new LibraryBean();
		List<LibraryBean> list = model.search(bean, 1, 5);

		Iterator<LibraryBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getTitle());
			System.out.println(bean.getAuthor());
			System.out.println(bean.getPrice());
			System.out.println(bean.isAvailability());
			System.out.println("-----------------------------");
		}

	}
}
