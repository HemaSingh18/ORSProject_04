
package in.co.rays.proj4.controller;

import java.io.IOException;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.DublicateRecordException;
import in.co.rays.proj4.model.BaseModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.MessageSource;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Base Controller class of the ORS Project.
 * 
 * This class provides common controller functionality such as validation,
 * preloading dropdown data, populating beans, handling HTTP requests, saving,
 * updating and forwarding requests to the appropriate view.
 * 
 * All application controllers inherit this class.
 * 
 * @param <B> Bean class extending BaseBean
 * @param <M> Model class extending BaseModel
 * 
 * @author Hema Singh
 * @version 1.0
 * @since 2026
 */
public abstract class BaseCtl<B extends BaseBean, M extends BaseModel> extends HttpServlet {

	/** Operation name for saving a record. */
	public static final String OP_SAVE = "Save";

	/** Operation name for cancelling an operation. */
	public static final String OP_CANCEL = "Cancel";

	/** Operation name for deleting a record. */
	public static final String OP_DELETE = "Delete";

	/** Operation name for displaying the list of records. */
	public static final String OP_LIST = "List";

	/** Operation name for searching records. */
	public static final String OP_SEARCH = "Search";

	/** Operation name for viewing a record. */
	public static final String OP_VIEW = "View";

	/** Operation name for navigating to the next page. */
	public static final String OP_NEXT = "Next";

	/** Operation name for navigating to the previous page. */
	public static final String OP_PREVIOUS = "Previous";

	/** Operation name for creating a new record. */
	public static final String OP_NEW = "New";

	/** Operation name for navigating to a specific page. */
	public static final String OP_GO = "Go";

	/** Operation name for navigating back. */
	public static final String OP_BACK = "Back";

	/** Operation name for logging out. */
	public static final String OP_LOG_OUT = "Logout";

	/** Request attribute indicating whether an error exists. */
	public static final String HAS_ERROR = "haserror";

	/** Request attribute used to store messages. */
	public static final String MESSAGE = "message";

	/** Success message status. */
	public static final String MSG_SUCCESS = "success";

	/** Error message status. */
	public static final String MSG_ERROR = "error";

	/**
	 * Validates user input received from the request.
	 * 
	 * Child controllers override this method to implement their own validation
	 * rules.
	 * 
	 * @param request HttpServletRequest containing user input
	 * @return true if validation is successful, otherwise false
	 */
	protected boolean validate(HttpServletRequest request) {
		return true;
	}

	/**
	 * Preloads data required by the view.
	 * 
	 * Child controllers can override this method to load dropdown lists and other
	 * required data.
	 * 
	 * @param request HttpServletRequest to store preloaded data
	 */
	protected void preload(HttpServletRequest request) {
	}

	/**
	 * Populates a bean with data received from the request.
	 * 
	 * Child controllers override this method to read request parameters and set
	 * them into the appropriate bean.
	 * 
	 * @param request HttpServletRequest containing form data
	 * @return populated bean object
	 */
	protected B populateBean(HttpServletRequest request) {
		return null;
	}

	/**
	 * Populates common audit information into the bean.
	 * 
	 * Sets createdBy, modifiedBy, createdDatetime and modifiedDatetime properties.
	 * 
	 * @param dto     BaseBean object to populate
	 * @param request HttpServletRequest containing session and form data
	 * @return updated BaseBean object
	 */
	protected BaseBean populateDTO(BaseBean dto, HttpServletRequest request) {

		String createdBy = request.getParameter("createdBy");
		String modifiedBy = null;

		UserBean userbean = (UserBean) request.getSession().getAttribute("user");

		if (userbean == null) {
			createdBy = "root";
			modifiedBy = "root";
		} else {
			modifiedBy = userbean.getLogin();

			// If record is created first time
			if ("null".equalsIgnoreCase(createdBy) || DataValidator.isNull(createdBy)) {
				createdBy = modifiedBy;
			}
		}

		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);

		long cdt = DataUtility.getLong(request.getParameter("createdDatetime"));

		if (cdt > 0) {
			dto.setCreatedDatetime(DataUtility.getTimestamp(cdt));
		} else {
			dto.setCreatedDatetime(DataUtility.getCurrentTimestamp());
		}

		dto.setModifiedDatetime(DataUtility.getCurrentTimestamp());

		return dto;
	}

	/**
	 * Handles HTTP GET requests.
	 * 
	 * Retrieves a record using its ID, if provided, and forwards the request to the
	 * appropriate view.
	 * 
	 * @param request  HttpServletRequest containing the record ID
	 * @param response HttpServletResponse used to send the response
	 * @throws ServletException if servlet processing fails
	 * @throws IOException      if an input/output error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long id = DataUtility.getLong(request.getParameter("id"));

		M model = getModel();

		if (id > 0) {
			BaseBean bean = model.findByPK(id);
			ServletUtility.setBean(bean, request);
		}

		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Handles HTTP POST requests.
	 * 
	 * Populates the bean and performs insert or update operations based on the bean
	 * ID. Displays success or duplicate record messages.
	 * 
	 * @param request  HttpServletRequest containing submitted form data
	 * @param response HttpServletResponse used to send the response
	 * @throws ServletException if servlet processing fails
	 * @throws IOException      if an input/output error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		B bean = populateBean(request);
		M model = getModel();

		try {
			if (bean.getId() > 0) {
				model.update(bean);
				ServletUtility.setSuccessMessage("record updated successfully", request);
			} else {
				model.add(bean);
				ServletUtility.setSuccessMessage("record saved successfully ", request);
			}
		} catch (DublicateRecordException e) {
			ServletUtility.setErrorMessage("record already exist", request);
		}

		ServletUtility.setBean(bean, request);
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Overrides the service method to perform common preprocessing before handling
	 * HTTP requests.
	 * 
	 * Loads message resources, preloads view data and validates POST requests
	 * before forwarding them to doGet() or doPost().
	 * 
	 * @param request  HttpServletRequest received from the client
	 * @param response HttpServletResponse sent to the client
	 * @throws ServletException if servlet processing fails
	 * @throws IOException      if an input/output error occurs
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		getMessageSource(request);

		System.out.println("service method");

		preload(request);

		if (request.getMethod().equals("POST") && validate(request) == false) {
			ServletUtility.forward(getView(), request, response);
			return;
		}

		super.service(request, response);
	}

	/**
	 * Returns the model object associated with the controller.
	 * 
	 * Each child controller must implement this method.
	 * 
	 * @return model object
	 */
	public abstract M getModel();

	/**
	 * Returns the JSP view path associated with the controller.
	 * 
	 * Each child controller must implement this method.
	 * 
	 * @return JSP view path
	 */
	public abstract String getView();

	/**
	 * Returns the MessageSource instance.
	 * 
	 * Used to access application message resources.
	 * 
	 * @param request HttpServletRequest object
	 * @return MessageSource instance
	 */
	public MessageSource getMessageSource(HttpServletRequest request) {

		MessageSource messageSource = MessageSource.getInstance();

		return messageSource;
	}

}