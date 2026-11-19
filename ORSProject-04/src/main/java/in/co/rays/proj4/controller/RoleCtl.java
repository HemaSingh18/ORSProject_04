
package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * RoleCtl is a controller class responsible for handling role management
 * operations. It validates role form data, populates the RoleBean object, and
 * provides the RoleModel and role view.
 *
 * @author Rays Technologies
 * @version 1.0
 */
@WebServlet("/ctl/RoleCtl")
public class RoleCtl extends BaseCtl<RoleBean, RoleModel> {

	private static Logger log = Logger.getLogger(RoleCtl.class);

	/**
	 * Validates the role form fields. Checks whether role name and description are
	 * provided or not.
	 *
	 * @param request HttpServletRequest object containing form parameters.
	 * @return true if all required fields are valid, otherwise false.
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("RoleCtl validate method started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			log.warn("Role name is required");
			request.setAttribute("name", "role name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("description"))) {
			log.warn("Role description is required");
			request.setAttribute("description", "description is required");
			pass = false;
		}

		if (pass) {
			log.info("Role validation completed successfully");
		} else {
			log.warn("Role validation failed");
		}

		log.debug("RoleCtl validate method completed");

		return pass;
	}

	/**
	 * Populates the RoleBean object with role details received from the HTTP
	 * request.
	 *
	 * @param request HttpServletRequest object containing role form data.
	 * @return RoleBean object containing populated role details.
	 */
	@Override
	protected RoleBean populateBean(HttpServletRequest request) {

		log.debug("RoleCtl populateBean method started");

		RoleBean bean = new RoleBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));

		populateDTO(bean, request);

		log.info("RoleBean populated successfully");

		return bean;
	}

	/**
	 * Creates and returns the RoleModel object.
	 *
	 * @return RoleModel object used for role management operations.
	 */
	@Override
	public RoleModel getModel() {

		log.debug("RoleCtl getModel method called");

		return new RoleModel();
	}

	/**
	 * Returns the view name of the role management page.
	 *
	 * @return Role view path.
	 */
	@Override
	public String getView() {

		log.debug("RoleCtl getView method called");

		return ORSView.ROLE_VIEW;
	}

}
