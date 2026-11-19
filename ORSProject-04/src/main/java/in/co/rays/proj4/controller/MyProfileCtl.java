
package in.co.rays.proj4.controller;

import java.io.IOException;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * My Profile functionality Controller. Performs operation for update your
 * Profile
 * 
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

@WebServlet("/ctl/MyProfileCtl")
public class MyProfileCtl extends BaseCtl<UserBean, UserModel> {

	private static Logger log = Logger.getLogger(MyProfileCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("MyProfileCtl Method validate Started");

		boolean pass = true;

		String op = DataUtility.getString(request.getParameter("operation"));

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			log.warn("First name is required");
			request.setAttribute("firstName", "first name is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("lastName"))) {
			log.warn("Last name is required");
			request.setAttribute("lastName", "last name is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("gender"))) {
			log.warn("Gender is required");
			request.setAttribute("gender", "gender is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("dob"))) {
			log.warn("Date of birth is required");
			request.setAttribute("dob", "Date of birth is required");
			pass = false;
		}

		log.debug("MyProfileCtl Method validate Ended");

		return pass;

	}

	@Override
	protected UserBean populateBean(HttpServletRequest request) {

		long id = DataUtility.getLong(request.getParameter("id"));

		UserModel model = getModel();

		UserBean bean = model.findByPK(id);

		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));

		populateDTO(bean, request);

		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("MyProfileCtl doGet method started");

		HttpSession session = request.getSession(true);

		UserBean userBean = (UserBean) session.getAttribute("user");

		long id = userBean.getId();

		log.info("Fetching profile details for user ID: " + id);

		UserModel model = getModel();

		UserBean bean = model.findByPK(id);

		ServletUtility.setBean(bean, request);

		log.info("User profile details fetched successfully");

		ServletUtility.forward(getView(), request, response);

		log.debug("MyProfileCtl doGet method completed");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("MyProfileCtl doPost method started");

		UserModel model = getModel();

		UserBean bean = populateBean(request);
		model.updateProfile(bean);

		UserBean updatedBean = model.findByPK(bean.getId());

		ServletUtility.setBean(updatedBean, request);

		ServletUtility.setSuccessMessage("Data is successfully saved", request);

		ServletUtility.forward(getView(), request, response);
		log.debug("MyProfileCtl doPost method completed");
	}

	@Override
	public String getView() {

		log.debug("MyProfileCtl getView method called");

		return ORSView.MY_PROFILE_VIEW;
	}

	/*
	 * @Override protected String getView(String op) { return
	 * ORSView.MY_PROFILE_VIEW; }
	 */

	@Override
	public UserModel getModel() {

		log.debug("MyProfileCtl getModel method called");

		return new UserModel();
	}

}
