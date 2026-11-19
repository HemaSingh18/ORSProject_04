
package in.co.rays.proj4.controller;

import java.io.IOException;

import org.apache.log4j.Logger;

import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.MessageSource;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

/**
 * LanguageFilter is a servlet filter used to manage the application language.
 * It checks the language parameter from the HTTP request and updates the
 * application locale using MessageSource.
 *
 * @author Rays Technologies
 * @version 1.0
 */
@WebFilter("/*")
public class LanguageFilter implements Filter {

	private static Logger log = Logger.getLogger(LanguageFilter.class);

	/**
	 * Initializes the language filter.
	 *
	 * @param filterConfig FilterConfig object containing filter configuration.
	 * @throws ServletException if an error occurs during filter initialization.
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		log.info("LanguageFilter initialization started");

		Filter.super.init(filterConfig);

		log.info("LanguageFilter initialized successfully");
	}

	/**
	 * Intercepts incoming requests and checks whether a language parameter is
	 * provided. If the language parameter is not null, it updates the application
	 * locale before passing the request to the next filter or servlet in the filter
	 * chain.
	 *
	 * @param request  ServletRequest object containing client request data.
	 * @param response ServletResponse object used to send the response.
	 * @param chain    FilterChain object used to pass the request forward.
	 * @throws IOException      if an input or output error occurs.
	 * @throws ServletException if a servlet-related error occurs.
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		log.debug("LanguageFilter doFilter method started");

		HttpServletRequest req = (HttpServletRequest) request;
		MessageSource messageSource = MessageSource.getInstance();

		log.debug("Retrieving language parameter from request");

		String lang = DataUtility.getString(req.getParameter("lang"));

		if (DataValidator.isNotNull(lang)) {

			log.info("Language parameter received. Updating application locale");

			messageSource.setLocale(lang);

			log.info("Application locale updated successfully");

		} else {

			log.debug("No language parameter provided. Using existing application locale");
		}

		chain.doFilter(req, response);

		log.debug("LanguageFilter doFilter method completed");
	}

	/**
	 * Destroys the language filter and releases any resources.
	 */
	@Override
	public void destroy() {

		log.info("LanguageFilter destroyed");
	}

}
