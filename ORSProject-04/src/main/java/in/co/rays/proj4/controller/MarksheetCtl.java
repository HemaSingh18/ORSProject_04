
package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.model.MarksheetModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * MarksheetCtl is a controller class responsible for handling marksheet-related
 * operations. It validates marksheet form data, populates the MarksheetBean
 * object, and provides the MarksheetModel and marksheet view.
 *
 * @author Rays Technologies
 * @version 1.0
 */
@WebServlet("/ctl/MarksheetCtl")
public class MarksheetCtl extends BaseCtl<MarksheetBean, MarksheetModel> {

	private static Logger log = Logger.getLogger(MarksheetCtl.class);

	/**
	 * Validates the marksheet form fields. Checks whether roll number, student ID,
	 * name, physics, chemistry, and maths marks are provided or not.
	 *
	 * @param request HttpServletRequest object containing form parameters.
	 * @return true if all required fields are valid, otherwise false.
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("MarksheetCtl validate method started");

		boolean pass = true;

		if (DataValidator.isNull(DataUtility.getString(request.getParameter("rollNo")))) {

			log.warn("Roll number is required");
			request.setAttribute("rollNo", "roll number is required");
			pass = false;
		}
		if (DataValidator.isNull(DataUtility.getString(request.getParameter("studentId")))) {

			log.warn("Student ID is required");
			request.setAttribute("studentId", "student id is required");
			pass = false;
		}
		if (DataValidator.isNull(DataUtility.getString(request.getParameter("name")))) {

			log.warn("Student name is required");
			request.setAttribute("name", "name is required");
			pass = false;
		}
		if (DataValidator.isNull(DataUtility.getString(request.getParameter("physics")))) {

			log.warn("Physics marks are required");
			request.setAttribute("physics", "physics marks is required");
			pass = false;

		}
		if (DataValidator.isNull(DataUtility.getString(request.getParameter("chemistry")))) {

			log.warn("Chemistry marks are required");
			request.setAttribute("chemistry", "chemistry marks is required");
			pass = false;
		}
		if (DataValidator.isNull(DataUtility.getString(request.getParameter("maths")))) {

			log.warn("Maths marks are required");
			request.setAttribute("maths", "maths marks is required");
			pass = false;

		}

		if (pass) {
			log.info("Marksheet validation completed successfully");
		} else {
			log.warn("Marksheet validation failed");
		}

		return pass;
	}

	/**
	 * Populates the MarksheetBean object with marksheet details received from the
	 * HTTP request. Converts student ID and subject marks into their respective
	 * data types.
	 *
	 * @param request HttpServletRequest object containing marksheet data.
	 * @return MarksheetBean object containing populated marksheet details.
	 */
	@Override
	protected MarksheetBean populateBean(HttpServletRequest request) {

		log.debug("MarksheetCtl populateBean method started");

		MarksheetBean bean = new MarksheetBean();

		bean.setRollNo(DataUtility.getString(request.getParameter("rollNo")));
		bean.setStudentId(DataUtility.getLong(request.getParameter("studentId")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setPhysics(DataUtility.getInt(request.getParameter("physics")));
		bean.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));
		bean.setMaths(DataUtility.getInt(request.getParameter("maths")));

		populateDTO(bean, request);

		log.info("MarksheetBean populated successfully");

		return bean;
	}

	/**
	 * Creates and returns the MarksheetModel object.
	 *
	 * @return MarksheetModel object used for marksheet operations.
	 */
	@Override
	public MarksheetModel getModel() {

		log.debug("MarksheetCtl getModel method called");

		return new MarksheetModel();
	}

	/**
	 * Returns the view name of the marksheet page.
	 *
	 * @return Marksheet view path.
	 */
	@Override
	public String getView() {

		log.debug("MarksheetCtl getView method called");

		return ORSView.MARKSHEET_VIEW;
	}

}
