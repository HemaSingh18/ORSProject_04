package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.model.CourseModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/CourseListCtl")
public class CourseListCtl extends BaseListCtl<CourseBean, CourseModel>{

	
	@Override
	protected CourseBean populateBean(HttpServletRequest request) {
		
		CourseBean bean = new CourseBean();
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setDuration(DataUtility.getString(request.getParameter("duration")));
		
		return bean;
	}
	@Override
	public CourseModel getModel() {
		
		return new CourseModel();
	}

	@Override
	public String getView() {

		return ORSView.COURSE_LIST_VIEW;
	}

}
