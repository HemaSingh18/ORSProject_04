package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.GymManagementBean;
import in.co.rays.proj4.model.GymManagementModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * GymCtl is a controller class used to handle gym management operations.
 * It validates user input, populates the GymManagementBean object,
 * provides the GymManagementModel, and returns the gym view.
 * 
 * @author Rays Technologies
 * @version 1.0
 */
@WebServlet("/ctl/GymCtl")
public class GymCtl extends BaseCtl<GymManagementBean, GymManagementModel>{

	/**
	 * Validates the input fields submitted through the gym form.
	 * Checks whether name, membership type, joining date, and trainer name
	 * are provided or not.
	 * 
	 * @param request HttpServletRequest object containing form parameters.
	 * @return true if all fields are valid, otherwise false.
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if(DataValidator.isNull(request.getParameter("name"))) {

			request.setAttribute("name", "name is required");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("membershipType"))) {

			request.setAttribute("membershipType", "membership Type is required");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("joiningDate"))) {

			request.setAttribute("joiningDate", "joining data is required");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("trainerName"))) {

			request.setAttribute("trainerName", "trainer name is required");
			pass = false;
		}

		return pass;
	}

	/**
	 * Populates the GymManagementBean object with data received
	 * from the HTTP request.
	 * 
	 * @param request HttpServletRequest object containing gym form data.
	 * @return GymManagementBean object containing populated form data.
	 */
	@Override
	protected GymManagementBean populateBean(HttpServletRequest request) {

		GymManagementBean bean = new GymManagementBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setMembershipType(DataUtility.getString(request.getParameter("membershipType")));
		bean.setJoiningDate(DataUtility.getString(request.getParameter("joiningDate")));
		bean.setTrainerName(DataUtility.getString(request.getParameter("trainerName")));

		return bean;
	}


	/**
	 * Creates and returns the GymManagementModel object.
	 * 
	 * @return GymManagementModel object.
	 */
	@Override
	public GymManagementModel getModel() {

		return new GymManagementModel();
	}

	/**
	 * Returns the view name of the gym management page.
	 * 
	 * @return Gym view path.
	 */
	@Override
	public String getView() {

		return ORSView.GYM_VIEW;
	}

}