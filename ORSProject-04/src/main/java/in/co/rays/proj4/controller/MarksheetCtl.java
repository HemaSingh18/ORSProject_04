package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.model.MarksheetModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/MarksheetCtl")
public class MarksheetCtl extends BaseCtl<MarksheetBean, MarksheetModel> {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(DataUtility.getString(request.getParameter("rollNo")))) {

			request.setAttribute("rollNo", "roll number is required");
			pass = false;
		}
		if (DataValidator.isNull(DataUtility.getString(request.getParameter("studentId")))) {

			request.setAttribute("studentId", "student id is required");
			pass = false;
		}
		if (DataValidator.isNull(DataUtility.getString(request.getParameter("name")))) {

			request.setAttribute("name", "name is required");
			pass = false;
		}
		if (DataValidator.isNull(DataUtility.getString(request.getParameter("physics")))) {

			request.setAttribute("physics", "physics marks is required");
			pass = false;

		}
		if (DataValidator.isNull(DataUtility.getString(request.getParameter("chemistry")))) {

			request.setAttribute("chemistry", "chemistry marks is required");
			pass = false;
		}
		if (DataValidator.isNull(DataUtility.getString(request.getParameter("maths")))) {

			request.setAttribute("maths", "maths marks is required");
			pass = false;

		}
		return pass;
	}

	@Override
	protected MarksheetBean populateBean(HttpServletRequest request) {

		MarksheetBean bean = new MarksheetBean();

		bean.setRollNo(DataUtility.getString(request.getParameter("rollNo")));
		bean.setStudentId(DataUtility.getLong(request.getParameter("studentId")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setPhysics(DataUtility.getInt(request.getParameter("physics")));
		bean.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));
		bean.setMaths(DataUtility.getInt(request.getParameter("maths")));
		
		populateDTO(bean, request);

		return bean;
	}

	@Override
	public MarksheetModel getModel() {

		return new MarksheetModel();
	}

	@Override
	public String getView() {

		return ORSView.MARKSHEET_VIEW;
	}

}
