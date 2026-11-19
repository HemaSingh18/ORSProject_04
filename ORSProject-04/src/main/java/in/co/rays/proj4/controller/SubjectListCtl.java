
package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.model.SubjectModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * SubjectList Controller class handles subject list-related operations. It
 * populates SubjectBean with subject search criteria and provides the
 * SubjectModel and subject list view.
 *
 * @author Rays Technologies
 * @version 1.0
 */
@WebServlet("/ctl/SubjectListCtl")
public class SubjectListCtl extends BaseListCtl<SubjectBean, SubjectModel> {

	private static Logger log = Logger.getLogger(SubjectListCtl.class);

	/**
	 * Populates SubjectBean with subject search details received from the HTTP
	 * request.
	 *
	 * @param request HttpServletRequest object containing subject search parameters
	 * @return populated SubjectBean object
	 */
	@Override
	protected SubjectBean populateBean(HttpServletRequest request) {

		log.debug("SubjectListCtl populateBean method started");

		SubjectBean bean = new SubjectBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));

		log.info("SubjectBean populated successfully for subject search");

		return bean;
	}

	/**
	 * Creates and returns SubjectModel object.
	 *
	 * @return SubjectModel object
	 */
	@Override
	public SubjectModel getModel() {

		log.debug("SubjectListCtl getModel method called");

		return new SubjectModel();
	}

	/**
	 * Returns the subject list view page.
	 *
	 * @return subject list view path
	 */
	@Override
	public String getView() {

		log.debug("SubjectListCtl getView method called");

		return ORSView.SUBJECT_LIST_VIEW;
	}

}
