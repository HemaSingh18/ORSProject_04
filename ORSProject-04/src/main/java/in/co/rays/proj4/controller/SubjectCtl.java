
package in.co.rays.proj4.controller;

import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.model.CourseModel;
import in.co.rays.proj4.model.SubjectModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Subject Controller class handles subject-related operations. It validates
 * subject details and populates SubjectBean with data received from the HTTP
 * request.
 *
 * @author Hema Singh
 * @version 1.0
 */
@WebServlet("/ctl/SubjectCtl")
public class SubjectCtl extends BaseCtl<SubjectBean, SubjectModel> {

	private static Logger log = Logger.getLogger(SubjectCtl.class);

	/**
	 * Validates the required subject form fields. Checks whether name, description,
	 * and course ID are provided.
	 *
	 * @param request HttpServletRequest object containing form parameters
	 * @return true if all required fields are valid, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("SubjectCtl validate method started");

		boolean pass = true;

		/*
		 * if (DataValidator.isNull(request.getParameter("name"))) {
		 * 
		 * log.warn("Subject name is required"); request.setAttribute("name",
		 * "name is required"); pass = false; }
		 */
		if (DataValidator.isNull(request.getParameter("description"))) {

			log.warn("Subject description is required");
			request.setAttribute("description", "Description is required");
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("courseId"))) {

			log.warn("Course ID is required");
			request.setAttribute("courseId", "course id is required");
			pass = false;

		}

		if (pass) {
			log.info("Subject validation successful");
		} else {
			log.warn("Subject validation failed");
		}

		return pass;
	}

	/**
	 * Populates SubjectBean with subject details received from the HTTP request.
	 *
	 * @param request HttpServletRequest object containing subject form data
	 * @return populated SubjectBean object
	 */
	@Override
	protected SubjectBean populateBean(HttpServletRequest request) {

		log.debug("SubjectCtl populateBean method started");

		SubjectBean bean = new SubjectBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));

		populateDTO(bean, request);

		log.info("SubjectBean populated successfully");

		return bean;
	}

	/**
	 * Creates and returns SubjectModel object.
	 *
	 * @return SubjectModel object
	 */

	@Override
	protected void preload(HttpServletRequest request) {

		CourseModel model = new CourseModel();
		List<CourseBean> courseList = model.search(null, 0, 0);
		request.setAttribute("courseList", courseList);
	}

	@Override
	public SubjectModel getModel() {

		log.debug("SubjectCtl getModel method called");

		return new SubjectModel();
	}

	/**
	 * Returns the subject view page.
	 *
	 * @return subject view path
	 */
	@Override
	public String getView() {

		log.debug("SubjectCtl getView method called");

		return ORSView.SUBJECT_VIEW;
	}

}
