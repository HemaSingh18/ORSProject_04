
package in.co.rays.proj4.controller;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.model.BaseModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * BaseListCtl is an abstract controller class used to manage list operations
 * such as searching, deleting records and pagination.
 * 
 * It provides common functionality for all list controllers.
 * 
 * @param <B> Bean class that extends BaseBean
 * @param <M> Model class that extends BaseModel
 * 
 * @author hema singh
 * @version 1.0
 */
public abstract class BaseListCtl<B extends BaseBean, M extends BaseModel> extends BaseCtl<B, M> {

	private static Logger log = Logger.getLogger(BaseListCtl.class);

	/**
	 * Handles HTTP GET requests.
	 * 
	 * Retrieves records from the database using default pagination values and
	 * forwards the records to the corresponding view.
	 * 
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if servlet-related error occurs
	 * @throws IOException      if input/output error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("BaseListCtl doGet() started");

		String op = DataUtility.getString(request.getParameter("operation"));
		log.info("Operation received in doGet: " + op);

		M model = getModel();
		B bean = populateBean(request);

		int pageNo = 1;
		int pageSize = 5;

		log.debug("Fetching records for pageNo: " + pageNo + " and pageSize: " + pageSize);

		List<B> list = model.search(bean, pageNo, pageSize);
		List<B> nextList = model.search(bean, pageNo + 1, pageSize);

		log.info("Records fetched successfully in doGet");

		request.setAttribute("nextList", nextList);
		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);

		log.debug("Forwarding request to view: " + getView());

		ServletUtility.forward(getView(), request, response);

		log.debug("BaseListCtl doGet() completed");
	}

	/**
	 * Handles HTTP POST requests.
	 * 
	 * Performs list operations such as deleting selected records, navigating to the
	 * next page, navigating to the previous page and searching records.
	 * 
	 * After performing the requested operation, it retrieves the records according
	 * to the current page number and page size and forwards them to the
	 * corresponding view.
	 * 
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if servlet-related error occurs
	 * @throws IOException      if input/output error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("BaseListCtl doPost() started");

		String op = DataUtility.getString(request.getParameter("operation"));
		log.info("Operation received in doPost: " + op);

		M model = getModel();
		B bean = populateBean(request);
		int pageNo = 1;
		int pageSize = 5;

		// Delete selected records
		if (OP_DELETE.equalsIgnoreCase(op)) {

			log.info("Delete operation started");

			String[] ids = request.getParameterValues("ids");

			if (ids != null && ids.length > 0) {

				for (String id : ids) {
					log.debug("Deleting record with ID: " + id);

					model.delete(DataUtility.getInt(id));

					ServletUtility.setSuccessMessage("record deleted successfully", request);
				}

				log.info("Selected records deleted successfully");

			} else {

				log.warn("No records selected for deletion");

				ServletUtility.setErrorMessage("select at least one record to delete", request);
			}
		}

		// Navigate to the next page
		if (OP_NEXT.equalsIgnoreCase(op)) {

			log.info("Next page operation started");

			pageNo = DataUtility.getInt(request.getParameter("pageNo"));
			pageNo++;

			log.debug("Navigating to next page: " + pageNo);
		}

		// Navigate to the previous page
		if (OP_PREVIOUS.equalsIgnoreCase(op)) {

			log.info("Previous page operation started");

			pageNo = DataUtility.getInt(request.getParameter("pageNo"));
			pageNo--;

			log.debug("Navigating to previous page: " + pageNo);
		}

		// Reset pagination when searching records
		if (OP_SEARCH.equalsIgnoreCase(op)) {

			log.info("Search operation started");

			pageNo = 1;

			log.debug("Page number reset to: " + pageNo);
		}

		log.debug("Fetching records for pageNo: " + pageNo + " and pageSize: " + pageSize);

		List<B> list = model.search(bean, pageNo, pageSize);
		List<B> nextList = model.search(bean, pageNo + 1, pageSize);

		log.info("Records fetched successfully in doPost");

		request.setAttribute("nextList", nextList);
		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);

		log.debug("Forwarding request to view: " + getView());

		ServletUtility.forward(getView(), request, response);

		log.debug("BaseListCtl doPost() completed");
	}

}
