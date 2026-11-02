package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.GymManagementBean;
import in.co.rays.proj4.model.GymManagementModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.http.HttpServletRequest;

public class GymListCtl extends BaseListCtl<GymManagementBean, GymManagementModel> {

	@Override
	protected GymManagementBean populateBean(HttpServletRequest request) {

		GymManagementBean bean = new GymManagementBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setMembershipType(DataUtility.getString(request.getParameter("membershipType")));
		bean.setJoiningDate(DataUtility.getString(request.getParameter("joiningDate")));
		bean.setTrainerName(DataUtility.getString(request.getParameter("trainerName")));
		return bean;
	}

	@Override
	public GymManagementModel getModel() {

		return new GymManagementModel();
	}

	@Override
	public String getView() {

		return ORSView.GYM_LIST_VIEW;
	}

}
