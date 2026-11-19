
package in.co.rays.proj4.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * UploadPhotoCtl handles uploading and displaying user profile photos. It
 * processes multipart form requests, saves uploaded photos, updates the photo
 * information in the database, and displays the uploaded photo or a default
 * image.
 *
 * @author Rays Technologies
 * @version 1.0
 */
@WebServlet("/ctl/uploadphoto")
@MultipartConfig
public class UploadPhotoCtl extends HttpServlet {

	private static Logger log = Logger.getLogger(UploadPhotoCtl.class);

	/**
	 * Handles incoming HTTP requests and delegates processing to the appropriate
	 * HTTP method.
	 *
	 * @param request HttpServletRequest object containing request data
	 * @param resp    HttpServletResponse object used to send the response
	 * @throws ServletException if a servlet-related error occurs
	 * @throws IOException      if an input or output error occurs
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

		log.debug("UploadPhotoCtl service method started");

		RoleModel model = new RoleModel();
		try {

		} catch (ApplicationException e) {
			log.error("Application exception occurred in service method", e);
		}
		super.service(request, resp);

		log.debug("UploadPhotoCtl service method completed");
	}

	/**
	 * Retrieves and displays the user's profile photo. If the user photo is
	 * unavailable, the default image is displayed.
	 *
	 * @param request  HttpServletRequest object containing the user ID
	 * @param response HttpServletResponse object used to send the image
	 * @throws ServletException if a servlet-related error occurs
	 * @throws IOException      if an input or output error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("UploadPhotoCtl doGet method started");

		long id = DataUtility.getLong(request.getParameter("id"));

		log.info("Retrieving profile photo for user ID: " + id);

		UserModel model = new UserModel();
		UserBean bean = null;

		try {
			bean = model.findByPK(id);
			log.info("User details retrieved successfully");
		} catch (Exception e) {
			log.error("Error while retrieving user details", e);
			e.printStackTrace();
		}

		String photoPath = PropertyReader.getValue("photoPath");

		// User photo available
		if (bean != null && DataValidator.isNotNull(bean.getPhoto())) {

			File file = new File(photoPath, bean.getPhoto());

			if (file.exists()) {

				log.info("User profile photo found");

				response.setContentType(getServletContext().getMimeType(file.getName()));

				InputStream in = new java.io.FileInputStream(file);
				OutputStream out = response.getOutputStream();

				byte[] buffer = new byte[4096];
				int bytes;

				while ((bytes = in.read(buffer)) != -1) {
					out.write(buffer, 0, bytes);
				}

				in.close();
				out.close();

				log.debug("User profile photo displayed successfully");

				return;
			}
		}

		log.warn("User profile photo not found, displaying default image");

		// Default photo
		InputStream in = getServletContext().getResourceAsStream("/img/logo.png");

		response.setContentType("image/png");

		OutputStream out = response.getOutputStream();

		byte[] buffer = new byte[4096];
		int bytes;

		while ((bytes = in.read(buffer)) != -1) {
			out.write(buffer, 0, bytes);
		}

		in.close();
		out.close();

		log.info("Default profile photo displayed successfully");

	}

	/**
	 * Handles the photo upload request. Reads the uploaded photo, saves it to the
	 * configured folder, and updates the user's photo information in the database.
	 *
	 * @param request  HttpServletRequest object containing the uploaded photo
	 * @param response HttpServletResponse object used to send the response
	 * @throws ServletException if a servlet-related error occurs
	 * @throws IOException      if an input or output error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("UploadPhotoCtl doPost method started");

		long id = DataUtility.getLong(request.getParameter("id"));
		log.info("Processing photo upload for user ID: " + id);

		UserModel model = new UserModel();
		UserBean bean = model.findByPK(id);
		ServletUtility.setBean(bean, request);
		// Get uploaded photo
		Part part = request.getPart("photo");

		if (part == null || part.getSize() == 0) {
			log.warn("Photo upload failed because no photo was selected");
			ServletUtility.setErrorMessage("Photo is required", request);
			ServletUtility.forward(getView(), request, response);
			return;
		}

		String fileName = part.getSubmittedFileName();

		System.out.println("image name: " + fileName);

		log.info("Photo received for upload");

		String photoPath = PropertyReader.getValue("photoPath");

		System.out.println("folder path: " + photoPath);

		File folder = new File(photoPath);

		if (!folder.exists()) {
			log.debug("Photo folder does not exist, creating folder");
			folder.mkdirs();
		}

		File destFile = new File(folder, fileName);

		InputStream input = part.getInputStream();
		FileOutputStream output = new FileOutputStream(destFile);

		byte[] buffer = new byte[4096];

		int bytesRead = input.read(buffer);

		while (bytesRead != -1) {
			output.write(buffer, 0, bytesRead);
			bytesRead = input.read(buffer);
		}

		input.close();
		output.close();

		log.info("Photo file saved successfully");

		try {

			model.updatePhoto(id, fileName);
			ServletUtility.setSuccessMessage("photo upload successfully", request);

			System.out.println("Photo uploaded successfully: " + fileName);

			log.info("Photo information updated successfully in database");

		} catch (ApplicationException e) {
			log.error("Application exception occurred while updating photo information", e);
			e.printStackTrace();
			ServletUtility.setErrorMessage("Photo upload failed", request);
			ServletUtility.forward(getView(), request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);

		log.debug("UploadPhotoCtl doPost method completed");
	}

	/**
	 * Returns the view page for user photo operations.
	 *
	 * @return user view path
	 */
	public String getView() {

		log.debug("UploadPhotoCtl getView method called");

		return ORSView.USER_VIEW;
	}
}
