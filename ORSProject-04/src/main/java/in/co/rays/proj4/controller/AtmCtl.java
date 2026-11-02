package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.AtmBean;
import in.co.rays.proj4.model.AtmModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/AtmCtl")
public class AtmCtl extends BaseCtl<AtmBean, AtmModel> {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("location"))) {

			request.setAttribute("location", "location is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("bankName"))) {

			request.setAttribute("bankName", "bank Name is required");
		}
		if (DataValidator.isNull(request.getParameter("cashAvailable"))) {

			request.setAttribute("cashAvailable", "cash availableis required");
		}
		if (DataValidator.isNull(request.getParameter("status"))) {

			request.setAttribute("status", "status is required");
		}

		return pass;
	}

	@Override
	protected AtmBean populateBean(HttpServletRequest request) {
		

		AtmBean bean = new AtmBean();

		bean.setLocation(DataUtility.getString(request.getParameter("location")));
		bean.setBankName(DataUtility.getString(request.getParameter("bankName")));
		bean.setCashAvailable(DataUtility.getInt(request.getParameter("cashAvailable")));
		bean.setStatus(DataUtility.getString(request.getParameter("status")));

		populateDTO(bean, request);

		return bean;
	}

	@Override
	public AtmModel getModel() {

		return new AtmModel();
	}

	@Override
	public String getView() {

		return ORSView.ATM_VIEW;
	}

}
