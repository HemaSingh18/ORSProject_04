
package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.model.CollegeModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * CollegeCtl is a controller class responsible for handling college-related
 * operations such as adding and updating college information.
 * 
 * It validates user input, populates CollegeBean with request data and
 * communicates with CollegeModel.
 * 
 * @author Rays Technologies
 * @version 1.0
 */
@WebServlet("/ctl/CollegeCtl")
public class CollegeCtl extends BaseCtl<CollegeBean, CollegeModel> {

	private static Logger log = Logger.getLogger(CollegeCtl.class);

	/**
	 * Validates the college form input fields.
	 * 
	 * Checks whether college name, address, state, city, phone number and college
	 * ID are provided.
	 * 
	 * @param request HttpServletRequest object
	 * @return true if all fields are valid, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("CollegeCtl validate() started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {

			log.warn("College name is missing");

			request.setAttribute("name", "college name is required");

			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("address"))) {

			log.warn("College address is missing");

			request.setAttribute("address", "address is required");

			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("state"))) {

			log.warn("College state is missing");

			request.setAttribute("state", "state is required");

			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("city"))) {

			log.warn("College city is missing");

			request.setAttribute("city", "city is required");

			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("phoneNo"))) {

			log.warn("College phone number is missing");

			request.setAttribute("phoneNo", "phone number is required");

			pass = false;
		}
		/*
		 * if (DataValidator.isNull(request.getParameter("collegeId"))) {
		 * 
		 * log.warn("College ID is missing");
		 * 
		 * request.setAttribute("collegeId", "college is required");
		 * 
		 * pass = false; }
		 */

		if (pass) {
			log.info("College validation successful");
		} else {
			log.warn("College validation failed");
		}

		return pass;
	}

	/**
	 * Populates CollegeBean with college details received from the HTTP request.
	 * 
	 * Also calls populateDTO() to populate common bean properties.
	 * 
	 * @param request HttpServletRequest object
	 * @return populated CollegeBean object
	 */
	@Override
	protected CollegeBean populateBean(HttpServletRequest request) {

		log.debug("CollegeCtl populateBean() started");

		CollegeBean bean = new CollegeBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setState(DataUtility.getString(request.getParameter("state")));
		bean.setCity(DataUtility.getString(request.getParameter("city")));
		bean.setPhoneNo(DataUtility.getString(request.getParameter("phoneNo")));

		log.debug("College details populated in CollegeBean");

		populateDTO(bean, request);

		log.info("CollegeBean populated successfully");

		return bean;
	}

	/**
	 * Creates and returns the CollegeModel object.
	 * 
	 * @return CollegeModel object
	 */
	@Override
	public CollegeModel getModel() {

		log.debug("Creating CollegeModel object");

		return new CollegeModel();
	}

	/**
	 * Returns the JSP view path for the college form.
	 * 
	 * @return college view path
	 */
	@Override
	public String getView() {

		log.debug("Returning college view");

		return ORSView.COLLEGE_VIEW;
	}

}
