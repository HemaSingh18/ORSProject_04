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
 * UploadPhoto Controller handles uploading, retrieving, and displaying user
 * profile photos. It supports multipart file upload and displays a default
 * image when the user photo is not available.
 * 
 * @author Hema singh
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
@WebServlet("/ctl/UploadPhotoCtl")
@MultipartConfig
public class UploadPhotoCtl extends HttpServlet {

	private static Logger log = Logger.getLogger(UploadPhotoCtl.class);

	/**
	 * Handles HTTP requests by calling the parent service method.
	 * 
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-related error occurs
	 * @throws IOException      if an input/output error occurs
	 */

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("UploadPhotoCtl service method started");

		RoleModel model = new RoleModel();
		try {
			List l = model.search(null, 0, 0);
			request.setAttribute("roleList", l);
			log.debug("Role list loaded successfully");
		} catch (ApplicationException e) {
			log.error("Unable to load role list", e);
		}

		super.service(request, response);

		log.debug("UploadPhotoCtl service method completed");

		/*
		 * RoleModel model = new RoleModel();
		 * 
		 * List<RoleBean> roleList = model.search(null, 0, 0);
		 * 
		 * request.setAttribute("roleList", roleList);
		 * 
		 * super.service(request, response);
		 */
	}

	/*
	 * @Override protected void service(HttpServletRequest request,
	 * HttpServletResponse response) throws ServletException, IOException {
	 * super.service(request, response); }
	 */

	/**
	 * Retrieves and displays the user's profile photo. If the user's photo is
	 * available, it displays that photo. Otherwise, it displays the default logo
	 * image.
	 * 
	 * @param request  HttpServletRequest object containing user ID
	 * @param response HttpServletResponse object used to display the image
	 * @throws ServletException if a servlet-related error occurs
	 * @throws IOException      if an input/output error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("UploadPhotoCtl doGet method started");

		long id = DataUtility.getLong(request.getParameter("id"));

		UserModel model = new UserModel();
		UserBean bean = null;

		try {
			bean = model.findByPK(id);
			log.debug("User details retrieved for photo display");
		} catch (Exception e) {
			log.error("Unable to retrieve user details", e);
			e.printStackTrace();
		}

		String photoPath = PropertyReader.getValue("photoPath");

		// User photo available
		if (bean != null && DataValidator.isNotNull(bean.getPhoto())) {

			File file = new File(photoPath, bean.getPhoto());

			if (file.exists()) {

				log.debug("User profile photo found");

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

				log.info("User profile photo displayed successfully");

				return;
			}
		}

		log.debug("Displaying default profile photo");

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
	 * Handles the photo upload request. Reads the uploaded photo, saves it into the
	 * configured photo directory, updates the user's photo name in the database,
	 * and displays the appropriate success or error message.
	 * 
	 * @param request  HttpServletRequest object containing uploaded photo
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-related error occurs
	 * @throws IOException      if an input/output error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("UploadPhotoCtl doPost method started");

		long id = DataUtility.getLong(request.getParameter("id"));
		UserModel model = new UserModel();
		UserBean bean = model.findByPK(id);
		ServletUtility.setBean(bean, request);

		// get uploaded photo
		Part part = request.getPart("photo");

		if (part == null || part.getSize() == 0) {
			log.warn("Photo upload attempted without selecting a file");
			ServletUtility.setErrorMessage("photo is required", request);
			ServletUtility.forward(getView(), request, response);
			return;
		}

		String fileName = part.getSubmittedFileName();
		log.info("Photo upload started");
		System.out.println("image name: " + fileName);

		String photoPath = PropertyReader.getValue("photoPath");
		System.out.println("folder path: " + photoPath);

		File folder = new File(photoPath);
		if (!folder.exists()) {
			folder.mkdirs();
			log.debug("Photo directory created");
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

		try {
			model.updatePhoto(id, fileName);
			ServletUtility.setSuccessMessage("photo uploaded successfully", request);

			log.info("Photo uploaded and database updated successfully");
			System.out.println("photo uploaded successfully " + fileName);
		} catch (ApplicationException e) {
			log.error("Photo upload database update failed", e);
			e.printStackTrace();
			ServletUtility.setErrorMessage("photo upload failed", request);
			ServletUtility.forward(getView(), request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);

		log.debug("UploadPhotoCtl doPost method completed");
	}

	/**
	 * Returns the User View page.
	 * 
	 * @return User View path
	 */
	public String getView() {

		log.debug("UploadPhotoCtl getView method called");

		return ORSView.USER_VIEW;
	}

}