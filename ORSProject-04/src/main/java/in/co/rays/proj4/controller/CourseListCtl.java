
package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.model.CourseModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * CourseListCtl is a controller class responsible for handling course list
 * operations such as searching, displaying and managing course records.
 *
 * It extends BaseListCtl to reuse common list functionality such as searching,
 * deleting records and pagination.
 *
 * @author Rays Technologies
 * @version 1.0
 */
@WebServlet("/ctl/CourseListCtl")
public class CourseListCtl extends BaseListCtl<CourseBean, CourseModel> {

	private static Logger log = Logger.getLogger(CourseListCtl.class);

	/**
	 * Populates CourseBean with course details received from the HTTP request.
	 *
	 * Retrieves course name, description and duration from request parameters.
	 *
	 * @param request HttpServletRequest object
	 * @return populated CourseBean object
	 */
	@Override
	protected CourseBean populateBean(HttpServletRequest request) {

		log.debug("CourseListCtl populateBean() started");

		CourseBean bean = new CourseBean();
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setDuration(DataUtility.getString(request.getParameter("duration")));

		log.info("Course search bean populated successfully");

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
	 * Returns the JSP view path for the course list page.
	 *
	 * @return course list view path
	 */
	@Override
	public String getView() {

		log.debug("Returning course list view");

		return ORSView.COURSE_LIST_VIEW;
	}

}
