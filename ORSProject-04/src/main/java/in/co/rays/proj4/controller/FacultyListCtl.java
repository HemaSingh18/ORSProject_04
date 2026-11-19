
package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.model.FacultyModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * FacultyListCtl is a controller class responsible for handling faculty list
 * operations such as searching, displaying and managing faculty records.
 *
 * It extends BaseListCtl to reuse common list functionality such as searching,
 * deleting records and pagination.
 *
 * @author Rays Technologies
 * @version 1.0
 */
@WebServlet("/ctl/FacultyListCtl")
public class FacultyListCtl extends BaseListCtl<FacultyBean, FacultyModel> {

	private static Logger log = Logger.getLogger(FacultyListCtl.class);

	/**
	 * Populates FacultyBean with faculty details received from the HTTP request.
	 *
	 * Retrieves college name, first name, last name, email, mobile number, address,
	 * gender, date of birth and college ID from request parameters.
	 *
	 * Also calls populateDTO() to populate common bean properties.
	 *
	 * @param request HttpServletRequest object
	 * @return populated FacultyBean object
	 */
	@Override
	protected FacultyBean populateBean(HttpServletRequest request) {

		log.debug("FacultyListCtl populateBean() started");

		FacultyBean bean = new FacultyBean();

		bean.setCollegeName(DataUtility.getString(request.getParameter("collegeName")));
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDateOfBirth(DataUtility.getDate(request.getParameter("dateOfBirth")));
		bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));

		log.debug("Faculty search details populated in FacultyBean");

		populateDTO(bean, request);

		log.info("FacultyBean populated successfully for faculty list");

		return bean;
	}

	/**
	 * Creates and returns the FacultyModel object.
	 *
	 * FacultyModel is used to perform database operations related to faculty
	 * records.
	 *
	 * @return FacultyModel object
	 */
	@Override
	public FacultyModel getModel() {

		log.debug("Creating FacultyModel object for faculty list");

		return new FacultyModel();
	}

	/**
	 * Returns the JSP view path for the faculty list page.
	 *
	 * @return faculty list view path
	 */
	@Override
	public String getView() {

		log.debug("Returning faculty list view");

		return ORSView.FACULTY_LIST_VIEW;
	}

}
