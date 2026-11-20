
package in.co.rays.proj4.controller;

import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.model.CollegeModel;
import in.co.rays.proj4.model.StudentModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Student Controller class handles student-related operations. It validates
 * student details and populates StudentBean with data received from the HTTP
 * request.
 *
 * @author Hema Singh
 * @version 1.0
 */
@WebServlet("/ctl/StudentCtl")
public class StudentCtl extends BaseCtl<StudentBean, StudentModel> {

	private static Logger log = Logger.getLogger(StudentCtl.class);

	/**
	 * Validates the required student form fields. Checks college ID, first name,
	 * last name, date of birth, mobile number, and email.
	 *
	 * @param request HttpServletRequest object containing form data
	 * @return true if all required fields are valid, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("StudentCtl validate method started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("collegeId"))) {

			log.warn("College ID is required");
			request.setAttribute("collegeId", "college id is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("firstName"))) {

			log.warn("First name is required");
			request.setAttribute("firstName", "first name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {

			log.warn("Last name is required");
			request.setAttribute("lastName", "last name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dateOfBirth"))) {

			log.warn("Date of birth is required");
			request.setAttribute("dateOfBirth", "date of birth required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobileNo"))) {

			log.warn("Mobile number is required");
			request.setAttribute("mobileNo", "mobile no is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("email"))) {

			log.warn("Email ID is required");
			request.setAttribute("email", "email id is required");
			pass = false;
		}

		if (pass) {
			log.info("Student validation completed successfully");
		} else {
			log.warn("Student validation failed");
		}

		log.debug("StudentCtl validate method completed");

		return pass;
	}

	/**
	 * Populates StudentBean with student details received from the HTTP request.
	 *
	 * @param request HttpServletRequest object containing student details
	 * @return populated StudentBean object
	 */
	@Override
	protected StudentBean populateBean(HttpServletRequest request) {

		log.debug("StudentCtl populateBean method started");

		StudentBean bean = new StudentBean();

		bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));
		bean.setCollegeName(DataUtility.getString(request.getParameter("collegeName")));
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setDateOfBirth(DataUtility.getDate(request.getParameter("dateOfBirth")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));

		populateDTO(bean, request);

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		log.info("StudentBean populated successfully");

		return bean;
	}

	@Override
	protected void preload(HttpServletRequest request) {
		CollegeModel model = new CollegeModel();
		List<CollegeBean> collegeList = model.search(null, 0, 0);
		request.setAttribute("collegeList", collegeList);
	}

	/**
	 * Creates and returns StudentModel object.
	 *
	 * @return StudentModel object
	 */
	@Override
	public StudentModel getModel() {

		log.debug("StudentCtl getModel method called");

		return new StudentModel();
	}

	/**
	 * Returns the student view page.
	 *
	 * @return student view path
	 */
	@Override
	public String getView() {

		log.debug("StudentCtl getView method called");

		return ORSView.STUDENT_VIEW;
	}

}
