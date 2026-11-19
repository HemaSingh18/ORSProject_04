
package in.co.rays.proj4.controller;

import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.model.CollegeModel;
import in.co.rays.proj4.model.FacultyModel;
import in.co.rays.proj4.model.StudentModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * FacultyCtl is a controller class responsible for handling faculty-related
 * operations such as adding and updating faculty information.
 *
 * It validates faculty details, populates FacultyBean with request data and
 * communicates with FacultyModel.
 *
 * @author Hema Singh
 * @version 1.0
 */
@WebServlet("/ctl/FacultyCtl")
public class FacultyCtl extends BaseCtl<FacultyBean, FacultyModel> {

	private static Logger log = Logger.getLogger(FacultyCtl.class);

	/**
	 * Validates the faculty form input fields.
	 *
	 * Checks whether college name, first name, last name, email, mobile number,
	 * address, gender, date of birth and college ID are provided correctly.
	 *
	 * @param request HttpServletRequest object
	 * @return true if all fields are valid, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("FacultyCtl validate() started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("collegeName"))) {

			log.warn("College name is missing");

			request.setAttribute("collegeName", "college name is required");

			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("firstName"))) {

			log.warn("Faculty first name is missing");

			request.setAttribute("firstName", "first name is required");

			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {

			log.warn("Faculty last name is missing");

			request.setAttribute("lastName", "last name is required");

			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("email"))) {

			log.warn("Faculty email is missing");

			request.setAttribute("email", "emial is required");

			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobileNo"))) {

			log.warn("Faculty mobile number is missing");

			request.setAttribute("mobileNo", "mobile number is required");

			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("address"))) {

			log.warn("Faculty address is missing");

			request.setAttribute("address", "address is required");

			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("gender"))) {

			log.warn("Faculty gender is missing");

			request.setAttribute("gender", "gender is required");

			pass = false;
		}
		if (DataValidator.isDate(request.getParameter("dateOfBirth"))) {

			log.warn("Faculty date of birth is invalid or missing");

			request.setAttribute("dateOfBirth", "date of birth is required");

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
			log.info("Faculty validation successful");
		} else {
			log.warn("Faculty validation failed");
		}

		return pass;
	}

	/**
	 * Populates FacultyBean with faculty details received from the HTTP request.
	 *
	 * Converts request parameters into appropriate data types using DataUtility and
	 * calls populateDTO() to populate common bean properties.
	 *
	 * @param request HttpServletRequest object
	 * @return populated FacultyBean object
	 */
	@Override
	protected FacultyBean populateBean(HttpServletRequest request) {

		log.debug("FacultyCtl populateBean() started");

		FacultyBean bean = new FacultyBean();
        
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setCollegeName(DataUtility.getString(request.getParameter("collegeName")));
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDateOfBirth(DataUtility.getDate(request.getParameter("dateOfBirth")));
		bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));

		log.debug("Faculty details populated in FacultyBean");

		populateDTO(bean, request);

		log.info("FacultyBean populated successfully");

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

		log.debug("Creating FacultyModel object");

		return new FacultyModel();
	}

	/**
	 * Returns the JSP view path for the faculty form.
	 *
	 * @return faculty view path
	 */
	@Override
	public String getView() {

		log.debug("Returning faculty view");

		return ORSView.FACULTY_VIEW;
	}
    
	@Override
	protected void preload(HttpServletRequest request) {
	 CollegeModel model = new CollegeModel();
	 List<CollegeBean> collegeList = model.search(null, 0, 0);
	 request.setAttribute("collegeList", collegeList); 
	}
}
