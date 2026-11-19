
package in.co.rays.proj4.controller;

import java.io.IOException;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * ChangePasswordCtl is a controller class responsible for handling user
 * password change requests.
 * 
 * It validates user input, retrieves user information and communicates with
 * UserModel to change the password.
 * 
 * @author Hema Singh
 * @version 1.0
 */
@WebServlet("/ctl/ChangePasswordCtl")
public class ChangePasswordCtl extends BaseCtl<UserBean, UserModel> {

	public static final String OP_CHANGE_MY_PROFILE = "Change My Profile";

	private static Logger log = Logger.getLogger(ChangePasswordCtl.class);

	/**
	 * Validates the input parameters submitted by the user.
	 * 
	 * Checks whether login, new password and old password are provided or not.
	 * 
	 * @param request HttpServletRequest object
	 * @return true if all input fields are valid, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("ChangePasswordCtl validate() started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("login"))) {
			log.warn("Login field is empty");
			request.setAttribute("login", "login is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("newPassword"))) {
			log.warn("New password field is empty");
			request.setAttribute("newPassword", "newPassword is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("oldPassword"))) {
			log.warn("Old password field is empty");
			request.setAttribute("oldPassword", "oldPassword is required");
			pass = false;
		}

		if (pass) {
			log.info("Change password validation successful");
		} else {
			log.warn("Change password validation failed");
		}

		return pass;
	}

	/**
	 * Populates UserBean with user login and old password values received from the
	 * HTTP request.
	 * 
	 * @param request HttpServletRequest object
	 * @return populated UserBean object
	 */
	@Override
	protected UserBean populateBean(HttpServletRequest request) {

		log.debug("ChangePasswordCtl populateBean() started");

		UserBean bean = new UserBean();

		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("oldPassword")));

		log.info("UserBean populated successfully");

		return bean;
	}

	/**
	 * Handles HTTP POST requests for changing the user password.
	 * 
	 * Retrieves user details, calls the changePassword method of UserModel and
	 * displays a success or error message depending on the result.
	 * 
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if servlet-related error occurs
	 * @throws IOException      if input/output error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("ChangePasswordCtl doPost() started");

		UserBean bean = populateBean(request);
		UserModel model = getModel();

		log.info("Calling changePassword method");

		bean = model.changePassword(request.getParameter("newPassword"), bean.getPassword(), bean.getLogin());

		if (bean != null) {
			log.info("Password changed successfully");
			ServletUtility.setSuccessMessage("your password has been changed check you emial", request);
		} else {
			log.warn("Password change failed due to incorrect email");
			ServletUtility.setErrorMessage("your email is incorect", request);
		}

		log.debug("Calling BaseCtl doPost method");

		super.doPost(request, response);

		log.debug("ChangePasswordCtl doPost() completed");
	}

	/**
	 * Creates and returns the UserModel object.
	 * 
	 * @return UserModel object
	 */
	@Override
	public UserModel getModel() {

		log.debug("Creating UserModel object");

		return new UserModel();
	}

	/**
	 * Returns the JSP view path for the change password page.
	 * 
	 * @return change password view path
	 */
	@Override
	public String getView() {

		log.debug("Returning change password view");

		return ORSView.CHANGE_PASSWORD_VIEW;
	}

}
