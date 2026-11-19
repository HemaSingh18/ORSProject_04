
package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.model.BaseModel;
import jakarta.servlet.annotation.WebServlet;

/**
 * Welcome Controller class. Handles requests for the welcome page.
 * 
 * @author Rays Technologies
 * @version 1.0
 */
@WebServlet("/WelcomeCtl")
public class WelcomeCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(WelcomeCtl.class);

	/**
	 * Returns the model associated with this controller.
	 * 
	 * @return null because no model is associated with this controller
	 */
	@Override
	public BaseModel getModel() {

		log.debug("WelcomeCtl getModel method called");

		log.info("No model is associated with WelcomeCtl");

		return null;
	}

	/**
	 * Returns the view name of the welcome page.
	 * 
	 * @return the welcome view
	 */
	@Override
	public String getView() {

		log.debug("WelcomeCtl getView method called");

		log.info("Returning welcome page view");

		return ORSView.WELCOME_VIEW;
	}

}
