package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.model.CollegeModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/CollegeCtl")
public class CollegeCtl extends BaseCtl<CollegeBean, CollegeModel> {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {

			request.setAttribute("name", "college name is required");

			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("address"))) {

			request.setAttribute("address", "address is required");

			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("state"))) {

			request.setAttribute("state", "state is required");

			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("city"))) {

			request.setAttribute("city", "city is required");

			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("phoneNo"))) {

			request.setAttribute("phoneNo", "phone number is required");

			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("collegeId"))) {

			request.setAttribute("collegeId", "college is required");

			pass = false;
		}

		return pass;
	}

	@Override
	protected CollegeBean populateBean(HttpServletRequest request) {

		CollegeBean bean = new CollegeBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setState(DataUtility.getString(request.getParameter("state")));
		bean.setCity(DataUtility.getString(request.getParameter("city")));
		bean.setPhoneNo(DataUtility.getString(request.getParameter("phoneNo")));

		populateDTO(bean, request);

		return bean;
	}

	@Override
	public CollegeModel getModel() {

		return new CollegeModel();
	}

	@Override
	public String getView() {

		return ORSView.COLLEGE_VIEW;
	}

}
