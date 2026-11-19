
package in.co.rays.proj4.controller;

import java.io.IOException;

import org.apache.log4j.Logger;

import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Front Controller Filter class.
 * 
 * This filter checks whether the user session is valid before allowing access
 * to controller URLs mapped under /ctl/*. If the user is not logged in, it
 * forwards the request to the login page.
 * 
 * @author Rays Technologies
 * @version 1.0
 */
@WebFilter("/ctl/*")
public class FrontCtl implements Filter {

	private static Logger log = Logger.getLogger(FrontCtl.class);

	/**
	 * Filters incoming requests and checks whether the user is logged in.
	 * 
	 * If the user session does not contain a user object, an error message is
	 * displayed and the request is forwarded to the login page. Otherwise, the
	 * request is passed to the next filter or controller.
	 * 
	 * @param request  the incoming servlet request
	 * @param response the servlet response
	 * @param chain    the filter chain used to pass the request forward
	 * @throws IOException      if an input or output error occurs
	 * @throws ServletException if a servlet-related error occurs
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		log.debug("FrontCtl doFilter method started");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		HttpSession session = req.getSession();

		log.debug("Checking user session");

		if (session.getAttribute("user") == null) {

			log.warn("User session has expired. Redirecting to login page");

			ServletUtility.setErrorMessage("session has been expired please re-login", req);
			ServletUtility.forward(ORSView.LOGIN_VIEW, req, resp);

			log.info("Request forwarded to login page");

			return;
		}

		log.info("User session is valid");

		chain.doFilter(request, response);// call next config filter/controller in the chain

		log.debug("FrontCtl doFilter method completed");

	}

	/**
	 * Initializes the filter.
	 * 
	 * @param filterConfig configuration information for the filter
	 * @throws ServletException if filter initialization fails
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		log.info("FrontCtl filter initialization started");

		Filter.super.init(filterConfig);

		log.info("FrontCtl filter initialized successfully");
	}

	/**
	 * Destroys the filter and releases its resources.
	 */
	@Override
	public void destroy() {

		log.info("FrontCtl filter destruction started");

		Filter.super.destroy();

		log.info("FrontCtl filter destroyed successfully");
	}

}
