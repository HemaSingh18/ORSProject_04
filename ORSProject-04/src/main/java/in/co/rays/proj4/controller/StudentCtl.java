package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.model.StudentModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/StudentCtl")
public class StudentCtl extends BaseCtl<StudentBean, StudentModel> {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("collegeId"))) {

			request.setAttribute("collegeId", "college id is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("collegeName"))) {

			request.setAttribute("collegeName", "college name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("firstName"))) {

			request.setAttribute("firstName", "first name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {

			request.setAttribute("lastName", "last name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dateOfBirth"))) {

			request.setAttribute("dateOfBirth", "date of birth required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobileNo"))) {

			request.setAttribute("mobileNo", "mobile no is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("email"))) {

			request.setAttribute("email", "email id is required");
			pass = false;
		}
		return pass;
	}

	@Override
	protected StudentBean populateBean(HttpServletRequest request) {

		StudentBean bean = new StudentBean();
		
		bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));
		bean.setCollegeName(DataUtility.getString(request.getParameter("collegeName")));
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setDateOfBirth(DataUtility.getDate(request.getParameter("dateOfBirth")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		
		populateDTO(bean, request);
		
		return bean;
	}

	@Override
	public StudentModel getModel() {

		return new StudentModel();
	}

	@Override
	public String getView() {

		return ORSView.STUDENT_VIEW;
	}

}
