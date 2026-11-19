
package in.co.rays.proj4.controller;

import java.io.IOException;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.DublicateRecordException;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * UserRegistration Controller handles user registration operations. It
 * validates user registration details, populates UserBean, registers new users,
 * and handles duplicate login records.
 *
 * @author Rays Technologies
 * @version 1.0
 */
@WebServlet("/UserRegistrationCtl")
public class UserRegistrationCtl extends BaseCtl<UserBean, UserModel> {

	private static Logger log = Logger.getLogger(UserRegistrationCtl.class);

	/**
	 * Operation constant used to identify the user sign-up request.
	 */
	public static final String OP_SIGN_UP = "SignUp";

	/**
	 * Validates the user registration form fields. Checks first name, last name,
	 * email, password, confirm password, gender, date of birth, and password
	 * confirmation.
	 *
	 * @param request HttpServletRequest object containing registration parameters
	 * @return true if all fields are valid, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("UserRegistrationCtl validate method started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			log.warn("First name is required");
			request.setAttribute("firstName", "firstName is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			log.warn("Last name is required");
			request.setAttribute("lastName", "lastName is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("login"))) {
			log.warn("Login is required");
			request.setAttribute("login", "login is required");
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("login"))) {
			log.warn("Login email format is invalid");
			request.setAttribute("login", "login is not in valid formate");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("password"))) {
			log.warn("Password is required");
			request.setAttribute("password", "password is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
			log.warn("Confirm password is required");
			request.setAttribute("confirmPassword", "confirmPassword is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("gender"))) {
			log.warn("Gender is required");
			request.setAttribute("gender", "gender is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dob"))) {
			log.warn("Date of birth is required");
			request.setAttribute("dob", "dob is required");
			pass = false;
		}
		if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			log.warn("Password and confirm password do not match");
			request.setAttribute("confirmPassword", "Confirm  Password  should not be matched.");
			pass = false;
		}

		if (pass) {
			log.info("User registration validation successful");
		} else {
			log.warn("User registration validation failed");
		}

		return pass;
	}

	/**
	 * Populates UserBean with registration details received from the HTTP request
	 * and assigns the default role ID.
	 *
	 * @param request HttpServletRequest object containing registration data
	 * @return populated UserBean object
	 */
	@Override
	protected UserBean populateBean(HttpServletRequest request) {

		log.debug("UserRegistrationCtl populateBean method started");

		UserBean bean = new UserBean();

		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		bean.setRoleId(2L);

		populateDTO(bean, request);

		log.info("UserBean populated successfully for registration");

		return bean;
	}

	/**
	 * Processes the user registration request. Adds and registers the user when the
	 * sign-up operation is selected. Handles duplicate login records and forwards
	 * the request to the view.
	 *
	 * @param request  HttpServletRequest object containing registration data
	 * @param response HttpServletResponse object used to send the response
	 * @throws ServletException if a servlet-related error occurs
	 * @throws IOException      if an input or output error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("UserRegistrationCtl doPost method started");

		String op = DataUtility.getString(request.getParameter("operation"));

		log.info("User registration operation: " + op);

		UserBean bean = populateBean(request);
		UserModel model = getModel();

		if (OP_SIGN_UP.equalsIgnoreCase(op)) {

			log.debug("User sign-up operation started");

			try {
				model.add(bean);
				model.register(bean);

				ServletUtility.setSuccessMessage("User is registred, Login now", request);

				log.info("User registered successfully");

			} catch (DublicateRecordException e) {
				log.warn("User registration failed because login ID already exists");
				ServletUtility.setErrorMessage("Login id already exsist", request);
			}

		}

		ServletUtility.forward(getView(), request, response);

		log.debug("UserRegistrationCtl doPost method completed");
	}

	/**
	 * Creates and returns UserModel object.
	 *
	 * @return UserModel object
	 */
	@Override
	public UserModel getModel() {

		log.debug("UserRegistrationCtl getModel method called");

		return new UserModel();
	}

	/**
	 * Returns the user registration view page.
	 *
	 * @return user registration view path
	 */
	@Override
	public String getView() {

		log.debug("UserRegistrationCtl getView method called");

		return ORSView.USER_REGISTRATION_VIEW;
	}

}
