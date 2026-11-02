package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.GymManagementBean;
import in.co.rays.proj4.model.GymManagementModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/GymCtl")
public class GymCtl extends BaseCtl<GymManagementBean, GymManagementModel>{

	
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
		
		return ORSView.GYM_VIEW;
	}

	
	
}
