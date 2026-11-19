
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
 * ForgetPasswordCtl is a controller class responsible for handling forgot
 * password requests.
 *
 * It validates the user's login ID, communicates with UserModel to retrieve
 * password-related information and displays success or error messages.
 *
 * @author Rays Technologies
 * @version 1.0
 */
@WebServlet("/ForgetPasswordCtl")
public class ForgetPasswordCtl extends BaseCtl<UserBean, UserModel> {

	private static Logger log = Logger.getLogger(ForgetPasswordCtl.class);

	/**
	 * Validates the forgot password form input.
	 *
	 * Checks whether the login field is provided by the user.
	 *
	 * @param request HttpServletRequest object
	 * @return true if login is valid, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("ForgetPasswordCtl validate() started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("login"))) {

			log.warn("Login field is missing");

			request.setAttribute("login", "login is required");

			pass = false;
		}

		if (pass) {
			log.info("Forgot password validation successful");
		} else {
			log.warn("Forgot password validation failed");
		}

		return pass;

	}

	/**
	 * Populates UserBean with login details received from the HTTP request.
	 *
	 * @param request HttpServletRequest object
	 * @return populated UserBean object
	 */
	@Override
	protected UserBean populateBean(HttpServletRequest request) {

		log.debug("ForgetPasswordCtl populateBean() started");

		UserBean bean = new UserBean();

		bean.setLogin(DataUtility.getString(request.getParameter("login")));

		log.info("UserBean populated successfully");

		return bean;
	}

	/**
	 * Handles HTTP POST requests for forgot password functionality.
	 *
	 * Retrieves the user's login ID, calls the forgotPassword() method of UserModel
	 * and displays a success or error message depending on whether the user record
	 * is found.
	 *
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if servlet-related error occurs
	 * @throws IOException      if input/output error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("ForgetPasswordCtl doPost() started");

		UserBean bean = populateBean(request);
		UserModel model = getModel();

		log.info("Calling forgotPassword method");

		bean = model.forgotPassword(bean.getLogin());

		if (bean != null) {

			log.info("Forgot password request processed successfully");

			ServletUtility.setSuccessMessage("your password has been forget, check you email", request);
		} else {

			log.warn("Forgot password request failed: user record not found");

			ServletUtility.setErrorMessage("your email is incorect", request);
		}

		log.debug("Calling BaseCtl doPost method");

		super.doPost(request, response);

		log.debug("ForgetPasswordCtl doPost() completed");
	}

	/**
	 * Creates and returns the UserModel object.
	 *
	 * UserModel is used to perform database operations related to user account
	 * information.
	 *
	 * @return UserModel object
	 */
	@Override
	public UserModel getModel() {

		log.debug("Creating UserModel object");

		return new UserModel();
	}

	/**
	 * Returns the JSP view path for the forgot password page.
	 *
	 * @return forgot password view path
	 */
	@Override
	public String getView() {

		log.debug("Returning forgot password view");

		return ORSView.FORGET_PASSWORD_VIEW;
	}

}
