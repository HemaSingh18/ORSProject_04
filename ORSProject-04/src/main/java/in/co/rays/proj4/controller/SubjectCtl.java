package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.model.SubjectModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/SubjectCtl")
public class SubjectCtl extends BaseCtl<SubjectBean, SubjectModel> {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {

			request.setAttribute("name", "name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("description"))) {

			request.setAttribute("description", "Description is required");
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("courseId"))) {

			request.setAttribute("courseId", "course id is required");
			pass = false;

		}
		return pass;
	}

	@Override
	protected SubjectBean populateBean(HttpServletRequest request) {

		SubjectBean bean = new SubjectBean();
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));

		populateDTO(bean, request);

		return bean;
	}

	@Override
	public SubjectModel getModel() {

		return new SubjectModel();
	}

	@Override
	public String getView() {

		return ORSView.SUBJECT_VIEW;
	}

}
