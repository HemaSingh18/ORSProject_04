
package in.co.rays.proj4.controller;

import java.io.IOException;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * LoginCtl is a controller class responsible for handling user login and logout
 * operations. It validates login credentials, authenticates users, manages user
 * sessions, and redirects users to the appropriate pages.
 *
 * @author Rays Technologies
 * @version 1.0
 */
@WebServlet("/LoginCtl")
public class LoginCtl extends BaseCtl<UserBean, UserModel> {

	private static Logger log = Logger.getLogger(LoginCtl.class);
	/**
	 * Operation constant used to identify the sign-in operation.
	 */
	public final static String OP_SIGNIN = "SignIn";

	/**
	 * Validates the login form input fields. Checks whether login and password are
	 * provided or not.
	 *
	 * @param request HttpServletRequest object containing form parameters.
	 * @return true if all required fields are valid, otherwise false.
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("LoginCtl validate method started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("login"))) {
			log.warn("Login field is empty");
			request.setAttribute("login", "login is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("password"))) {
			log.warn("Password field is empty");
			request.setAttribute("password", "password is required");
			pass = false;
		}

		log.debug("Validate run successfully");
		log.info("Login validation completed");

		return pass;

	}

	/**
	 * Populates the UserBean object with login details received from the HTTP
	 * request.
	 *
	 * @param request HttpServletRequest object containing login parameters.
	 * @return UserBean object containing login and password.
	 */
	@Override
	protected UserBean populateBean(HttpServletRequest request) {

		log.debug("LoginCtl populateBean method started");

		UserBean bean = new UserBean();
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));

		log.info("UserBean populated with login details");

		return bean;

	}

	/**
	 * Handles HTTP GET requests. If an operation parameter is provided, it logs out
	 * the user by invalidating the current session. Finally, it forwards the
	 * request to the login view.
	 *
	 * @param request  HttpServletRequest object.
	 * @param response HttpServletResponse object.
	 * @throws ServletException if a servlet-related error occurs.
	 * @throws IOException      if an input or output error occurs.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("LoginCtl doGet method started");

		String op = request.getParameter("operation");

		if (op != null) {

			log.info("User logout operation started");

			HttpSession session = request.getSession();
			ServletUtility.setSuccessMessage("user logout succesfull..", request);
			session.invalidate();

			log.info("User session invalidated successfully");
		}

		log.debug("Forwarding request to login view");

		ServletUtility.forward(getView(), request, response);

		log.debug("LoginCtl doGet method completed");

	}

	/**
	 * Handles HTTP POST requests. Authenticates the user using the supplied login
	 * and password. If authentication is successful, it stores user and role
	 * details in the session and redirects to the welcome page. Otherwise, it
	 * displays an invalid login error message.
	 *
	 * @param request  HttpServletRequest object containing login details.
	 * @param response HttpServletResponse object used to send the response.
	 * @throws ServletException if a servlet-related error occurs.
	 * @throws IOException      if an input or output error occurs.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("LoginCtl doPost method started");

		String op = DataUtility.getString(request.getParameter("operation"));

		HttpSession session = request.getSession();
		UserModel model = getModel();
		UserBean bean = populateBean(request);

		RoleModel rmodel = new RoleModel();

		if (OP_SIGNIN.equalsIgnoreCase(op)) {

			log.info("User sign-in operation started");

			bean = model.authenticate(bean.getLogin(), bean.getPassword());

			if (bean != null) {

				log.info("User authentication successful");

				session.setAttribute("user", bean);
				RoleBean rbean = rmodel.findByPK(bean.getRoleId());

				System.out.println(bean.getId());

				session.setAttribute("role", rbean.getName());

				log.info("User and role details stored in session");

				ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);

				log.info("User redirected to welcome page");

				return;

			} else {

				log.warn("User authentication failed");

				ServletUtility.setErrorMessage("Invalid Login and Password", request);
			}
		}

		log.debug("Forwarding request to login view");

		ServletUtility.forward(getView(), request, response);

		log.debug("LoginCtl doPost method completed");
	}

	/**
	 * Creates and returns the UserModel object.
	 *
	 * @return UserModel object used for user authentication.
	 */
	@Override
	public UserModel getModel() {

		log.debug("LoginCtl getModel method called");

		return new UserModel();
	}

	/**
	 * Returns the login view path.
	 *
	 * @return Login view path.
	 */
	@Override
	public String getView() {

		log.debug("LoginCtl getView method called");

		return ORSView.LOGIN_VIEW;
	}

}
