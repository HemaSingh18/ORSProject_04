
package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * RoleListCtl is a controller class responsible for handling role list
 * operations. It retrieves search parameters from the HTTP request, populates
 * the RoleBean object, and provides the RoleModel and role list view.
 *
 * @author Rays Technologies
 * @version 1.0
 */
@WebServlet("/ctl/RoleListCtl")
public class RoleListCtl extends BaseListCtl<RoleBean, RoleModel> {

	private static Logger log = Logger.getLogger(RoleListCtl.class);

	/**
	 * Populates the RoleBean object with search details received from the HTTP
	 * request.
	 *
	 * @param request HttpServletRequest object containing search parameters.
	 * @return RoleBean object containing populated role details.
	 */
	@Override
	protected RoleBean populateBean(HttpServletRequest request) {

		log.debug("RoleListCtl populateBean method started");

		RoleBean bean = new RoleBean();
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));

		log.info("RoleBean populated successfully for role search");

		return bean;
	}

	/**
	 * Creates and returns the RoleModel object.
	 *
	 * @return RoleModel object used for role list operations.
	 */
	@Override
	public RoleModel getModel() {

		log.debug("RoleListCtl getModel method called");

		return new RoleModel();
	}

	/**
	 * Returns the view name of the role list page.
	 *
	 * @return Role list view path.
	 */
	@Override
	public String getView() {

		log.debug("RoleListCtl getView method called");

		return ORSView.ROLE_LIST_VIEW;
	}

}
