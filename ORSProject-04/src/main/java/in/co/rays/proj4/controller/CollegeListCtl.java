
package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.model.CollegeModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * CollegeListCtl is a controller class responsible for handling college list
 * operations such as searching, displaying and managing college records.
 *
 * It extends BaseListCtl to reuse common list functionality such as pagination,
 * searching and deleting records.
 *
 * @author Rays Technologies
 * @version 1.0
 */
@WebServlet("/ctl/CollegeListCtl")
public class CollegeListCtl extends BaseListCtl<CollegeBean, CollegeModel> {

	private static Logger log = Logger.getLogger(CollegeListCtl.class);

	/**
	 * Populates CollegeBean with college details received from the HTTP request.
	 *
	 * Retrieves college name, address, state, city and phone number from request
	 * parameters.
	 *
	 * @param request HttpServletRequest object
	 * @return populated CollegeBean object
	 */
	@Override
	protected CollegeBean populateBean(HttpServletRequest request) {

		log.debug("CollegeListCtl populateBean() started");

		CollegeBean bean = new CollegeBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setState(DataUtility.getString(request.getParameter("state")));
		bean.setCity(DataUtility.getString(request.getParameter("city")));
		bean.setPhoneNo(DataUtility.getString(request.getParameter("phoneNo")));

		log.info("College search bean populated successfully");

		return bean;
	}

	/**
	 * Creates and returns the CollegeModel object.
	 *
	 * CollegeModel is used to perform database operations related to college
	 * records.
	 *
	 * @return CollegeModel object
	 */
	@Override
	public CollegeModel getModel() {

		log.debug("Creating CollegeModel object for college list");

		return new CollegeModel();
	}

	/**
	 * Returns the JSP view path for the college list page.
	 *
	 * @return college list view path
	 */
	@Override
	public String getView() {

		log.debug("Returning college list view");

		return ORSView.COLLEGE_LIST_VIEW;
	}

}
