
package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.model.CourseModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * CourseCtl is a controller class responsible for handling course-related
 * operations such as adding and updating course information.
 *
 * It validates user input, populates CourseBean with request data and
 * communicates with CourseModel.
 *
 * @author Rays Technologies
 * @version 1.0
 */
@WebServlet("/ctl/CourseCtl")
public class CourseCtl extends BaseCtl<CourseBean, CourseModel> {

	private static Logger log = Logger.getLogger(CourseCtl.class);

	/**
	 * Validates the course form input fields.
	 *
	 * Checks whether course name, description and duration are provided by the
	 * user.
	 *
	 * @param request HttpServletRequest object
	 * @return true if all fields are valid, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("CourseCtl validate() started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {

			log.warn("Course name is missing");

			request.setAttribute("name", "course name is required ");

			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("description"))) {

			log.warn("Course description is missing");

			request.setAttribute("description", "description is required");

			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("duration"))) {

			log.warn("Course duration is missing");

			request.setAttribute("duration", "duration is required");

			pass = false;
		}

		if (pass) {
			log.info("Course validation successful");
		} else {
			log.warn("Course validation failed");
		}

		return pass;
	}

	/**
	 * Populates CourseBean with course details received from the HTTP request.
	 *
	 * Also calls populateDTO() to populate common bean properties.
	 *
	 * @param request HttpServletRequest object
	 * @return populated CourseBean object
	 */
	@Override
	protected CourseBean populateBean(HttpServletRequest request) {

		log.debug("CourseCtl populateBean() started");

		CourseBean bean = new CourseBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setDuration(DataUtility.getString(request.getParameter("duration")));

		log.debug("Course details populated in CourseBean");

		populateDTO(bean, request);

		log.info("CourseBean populated successfully");

		return bean;
	}

	/**
	 * Creates and returns the CourseModel object.
	 *
	 * CourseModel is used to perform database operations related to course records.
	 *
	 * @return CourseModel object
	 */
	@Override
	public CourseModel getModel() {

		log.debug("Creating CourseModel object");

		return new CourseModel();
	}

	/**
	 * Returns the JSP view path for the course form.
	 *
	 * @return course view path
	 */
	@Override
	public String getView() {

		log.debug("Returning course view");

		return ORSView.COURSE_VIEW;
	}

}
