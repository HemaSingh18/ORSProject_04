package in.co.rays.proj4.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * User Controller class handles user-related operations. It validates user
 * details, populates UserBean, and loads role information for the user form.
 *
 * @author Rays Technologies
 * @version 1.0
 */
@WebServlet("/ctl/UserCtl")
public class UserCtl extends BaseCtl<UserBean, UserModel> {

	private static final Logger log = LogManager.getLogger(UserCtl.class);

	/**
	 * Validates the required user form fields. Checks first name, last name, login,
	 * password, gender, date of birth, and role ID.
	 *
	 * @param request HttpServletRequest object containing form parameters
	 * @return true if all required fields are valid, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.info("UserCtl validate method started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", "first name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", "last name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", "login is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", "password is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", "gender is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", "date of birth is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("roleId"))) {
			request.setAttribute("roleId", "role is required");
			pass = false;
		}

		if (pass) {
			log.info("User validation successful");
		} else {
			log.warn("User validation failed");
		}

		return pass;
	}

	/**
	 * Populates UserBean with user details received from the HTTP request.
	 *
	 * @param request HttpServletRequest object containing user form data
	 * @return populated UserBean object
	 */
	@Override
	protected UserBean populateBean(HttpServletRequest request) {
		log.info("UserCtl populateBean method started");

		UserBean bean = new UserBean();

		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		// bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		bean.setRoleId(DataUtility.getLong(request.getParameter("roleId")));

		log.info("UserBean populated successfully");
		populateDTO(bean, request);

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		return bean;
	}

	/**
	 * Loads the available roles from RoleModel and stores the role list in the
	 * request for displaying the role dropdown.
	 *
	 * @param request HttpServletRequest object used to store the role list
	 */
	@Override
	protected void preload(HttpServletRequest request) {
		log.info("UserCtl preload method started");

		RoleModel rmodel = new RoleModel();
		List<RoleBean> roleList = rmodel.search(null, 0, 0);
		request.setAttribute("roleList", roleList);

		log.info("Role list loaded successfully");
	}

	/**
	 * Creates and returns UserModel object.
	 *
	 * @return UserModel object
	 */
	@Override
	public UserModel getModel() {
		log.info("UserCtl getModel method called");
		return new UserModel();
	}

	/**
	 * Returns the user view page.
	 *
	 * @return user view path
	 */
	@Override
	public String getView() {
		log.info("UserCtl getView method called");
		return ORSView.USER_VIEW;
	}
}