
package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * UserList Controller class handles user list-related operations. It populates
 * UserBean with user search criteria and provides the UserModel and user list
 * view.
 *
 * @author Rays Technologies
 * @version 1.0
 */
@WebServlet("/ctl/UserListCtl")
public class UserListCtl extends BaseListCtl<UserBean, UserModel> {

	private static Logger log = Logger.getLogger(UserListCtl.class);

	/**
	 * Populates UserBean with user search details received from the HTTP request.
	 *
	 * @param request HttpServletRequest object containing user search parameters
	 * @return populated UserBean object
	 */
	@Override
	protected UserBean populateBean(HttpServletRequest request) {

		log.debug("UserListCtl populateBean method started");

		UserBean bean = new UserBean();

		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));

		log.info("UserBean populated successfully for user search");

		return bean;
	}

	/**
	 * Creates and returns UserModel object.
	 *
	 * @return UserModel object
	 */
	@Override
	public UserModel getModel() {

		log.debug("UserListCtl getModel method called");

		return new UserModel();
	}

	/**
	 * Returns the user list view page.
	 *
	 * @return user list view path
	 */
	@Override
	public String getView() {

		log.debug("UserListCtl getView method called");

		return ORSView.USER_LIST_VIEW;
	}

}
