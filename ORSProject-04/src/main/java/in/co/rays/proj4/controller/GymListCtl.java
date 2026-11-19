package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.GymManagementBean;
import in.co.rays.proj4.model.GymManagementModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.http.HttpServletRequest;

/**
 * GymListCtl is a controller class used to handle gym management list operations.
 * It retrieves search parameters from the HTTP request, populates the
 * GymManagementBean, provides the GymManagementModel, and returns the gym list view.
 *
 * @author Rays Technologies
 * @version 1.0
 */
public class GymListCtl extends BaseListCtl<GymManagementBean, GymManagementModel> {

	/**
	 * Populates the GymManagementBean object with search data received
	 * from the HTTP request.
	 *
	 * @param request HttpServletRequest object containing request parameters.
	 * @return GymManagementBean object containing populated search data.
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
	 * Returns the view name of the gym management list page.
	 *
	 * @return Gym list view path.
	 */
	@Override
	public String getView() {

		return ORSView.GYM_LIST_VIEW;
	}

}