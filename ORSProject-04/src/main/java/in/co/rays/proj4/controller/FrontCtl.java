package in.co.rays.proj4.controller;

import java.io.IOException;

import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/ctl/*")
public class FrontCtl implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		HttpSession session = req.getSession();

		if (session.getAttribute("user") == null) {

			ServletUtility.setErrorMessage("session has been expired please re-login", req);
			ServletUtility.forward(ORSView.LOGIN_VIEW, req, resp);
			return;
		}
		chain.doFilter(request, response);// call next config filter/controller in the chain

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		Filter.super.init(filterConfig);
	}

	@Override
	public void destroy() {

		Filter.super.destroy();
	}

}
