
package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.model.MarksheetModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * MarksheetListCtl is a controller class responsible for handling marksheet
 * list operations. It retrieves search parameters from the HTTP request,
 * populates the MarksheetBean object, and provides the MarksheetModel and
 * marksheet list view.
 *
 * @author Rays Technologies
 * @version 1.0
 */
@WebServlet("/ctl/MarksheetListCtl")
public class MarksheetListCtl extends BaseListCtl<MarksheetBean, MarksheetModel> {

	private static Logger log = Logger.getLogger(MarksheetListCtl.class);

	/**
	 * Populates the MarksheetBean object with search details received from the HTTP
	 * request.
	 *
	 * @param request HttpServletRequest object containing search parameters.
	 * @return MarksheetBean object containing populated marksheet details.
	 */
	@Override
	protected MarksheetBean populateBean(HttpServletRequest request) {

		log.debug("MarksheetListCtl populateBean method started");

		MarksheetBean bean = new MarksheetBean();

		bean.setRollNo(DataUtility.getString(request.getParameter("rollNo")));

		bean.setStudentId(DataUtility.getLong(request.getParameter("studentId")));

		bean.setName(DataUtility.getString(request.getParameter("name")));

		bean.setPhysics(DataUtility.getInt(request.getParameter("physics")));

		bean.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));

		bean.setMaths(DataUtility.getInt(request.getParameter("maths")));

		log.info("MarksheetBean populated successfully for search");

		return bean;

	}

	/**
	 * Creates and returns the MarksheetModel object.
	 *
	 * @return MarksheetModel object used for marksheet list operations.
	 */
	@Override
	public MarksheetModel getModel() {

		log.debug("MarksheetListCtl getModel method called");

		return new MarksheetModel();

	}

	/**
	 * Returns the view name of the marksheet list page.
	 *
	 * @return Marksheet list view path.
	 */
	@Override
	public String getView() {

		log.debug("MarksheetListCtl getView method called");

		return ORSView.MARKSHEET_LIST_VIEW;

	}

}
