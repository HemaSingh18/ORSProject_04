package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.AtmBean;
import in.co.rays.proj4.model.AtmModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/AtmListCtl")
public class AtmListCtl extends BaseListCtl<AtmBean, AtmModel> {

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

		return ORSView.ATM_LIST_VIEW;
	}

}
