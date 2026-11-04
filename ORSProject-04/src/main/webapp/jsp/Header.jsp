<%@page import="in.co.rays.proj4.controller.LoginCtl"%>
<%@page import="in.co.rays.proj4.util.MessageSource"%>
<%@page import="in.co.rays.proj4.bean.RoleBean"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>



<head>

<meta charset="UTF-8">

<title>ORS Project</title>

</head>



<body>



	<%
	MessageSource ms = MessageSource.getInstance();

	UserBean userBean = (UserBean) session.getAttribute("user");

	String role = (String) session.getAttribute("role");

	boolean isLogin = userBean != null;

	String welcomeMsg = "Hi, ";

	String locale = ms.getLanguage();

	boolean isAdmin = isLogin && userBean.getRoleId() == RoleBean.ADMIN;

	boolean isStudent = isLogin && userBean.getRoleId() == RoleBean.STUDENT;

	boolean isFaculty = isLogin && userBean.getRoleId() == RoleBean.FACULTY;

	boolean isCollege = isLogin && userBean.getRoleId() == RoleBean.COLLEGE;

	boolean isKiosk = isLogin && userBean.getRoleId() == RoleBean.KIOSK;
	%>



	<table width="100%">



		<!-- Welcome Message -->

		<tr>

			<td width="85%" valign="top">

				<h3>

					<%=isLogin ? welcomeMsg + userBean.getFirstName() + " (" + role + ")" : welcomeMsg + "Guest"%>

				</h3>

			</td>



			<!-- Logo -->

			<td rowspan="2" valign="top" align="right">

				<h1>

					<img src="<%=ORSView.APP_CONTEXT%>/img/rayslogo.png" width="175"
						height="50">

				</h1>

			</td>

		</tr>



		<!-- Main Navigation -->

		<tr>

			<td valign="top"><a href="<%=ORSView.WELCOME_CTL%>">Welcome</a>
				| <%
			if (isLogin) {
			%> <a
				href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">

					LogOut </a> <%
 } else {
 %> <a href="<%=ORSView.LOGIN_CTL%>">LogIn</a> | <a
				href="<%=ORSView.USER_REGISTRATION_CTL%>">SignUp</a> <%
 }
 %></td>

		</tr>



		<!-- Role Based Navigation -->

		<%
		if (isLogin) {
		%>



		<tr>

			<td colspan="2"><font style="font-size: 18px"> <a
					href="<%=ORSView.MY_PROFILE_CTL%>">My Profile</a> | <a
					href="<%=ORSView.CHANGE_PASSWORD_CTL%>">Change Password</a> | <%
 if (isAdmin) {
 %> <a href="<%=ORSView.ROLE_CTL%>">Add Role</a> | <a
					href="<%=ORSView.ROLE_LIST_CTL%>">Role List</a> | <a
					href="<%=ORSView.USER_CTL%>">Add User</a> | <a
					href="<%=ORSView.USER_LIST_CTL%>">User List</a> | <a
					href="<%=ORSView.COLLEGE_CTL%>">Add College</a> | <a
					href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</a> | <a
					href="<%=ORSView.COURSE_CTL%>">Add Course</a> | <a
					href="<%=ORSView.COURSE_LIST_CTL%>">Course List</a> | <a
					href="<%=ORSView.FACULTY_CTL%>">Add Faculty</a> | <a
					href="<%=ORSView.FACULTY_LIST_CTL%>">Faculty List</a> | <a
					href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet</a> | <a
					href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List</a> | <a
					href="<%=ORSView.STUDENT_CTL%>">Add Student</a> | <a
					href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</a> | <a
					href="<%=ORSView.SUBJECT_CTL%>">Add Subject</a> | <a
					href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject List</a> | <a
					href="<%=ORSView.PRODUCT_CTL%>">Add Product</a> | <a
					href="<%=ORSView.PRODUCT_LIST_CTL%>">Product List</a> | <a
					href="<%=ORSView.ATM_CTL%>">Add ATM</a> | <a
					href="<%=ORSView.ATM_LIST_CTL%>">ATM List</a> | <a
					href="<%=ORSView.JAVA_DOC_VIEW%>" target="_blank">Java Doc</a> <%
 }

 if (isStudent) {
 %> <a href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</a> | <a
					href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</a> | <a
					href="<%=ORSView.COURSE_LIST_CTL%>">Course List</a> | <a
					href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject List</a> | <a
					href="<%=ORSView.FACULTY_LIST_CTL%>">Faculty List</a> | <%
 }

 if (isKiosk) {
 %> <a href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</a> | <a
					href="<%=ORSView.COURSE_LIST_CTL%>">Course List</a> <%
 }

 if (isFaculty) {
 %> <a href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet</a> | <a
					href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List</a> | <a
					href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</a> | <a
					href="<%=ORSView.STUDENT_CTL%>">Add Student</a> | <a
					href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</a> | <a
					href="<%=ORSView.COURSE_LIST_CTL%>">Course List</a> | <a
					href="<%=ORSView.SUBJECT_CTL%>">Add Subject</a> | <a
					href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject List</a> | <%
 }

 if (isCollege) {
 %> <a href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet</a> | <a
					href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List</a> | <a
					href="<%=ORSView.STUDENT_CTL%>">Add Student</a> | <a
					href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</a> | <a
					href="<%=ORSView.FACULTY_LIST_CTL%>">Faculty List</a> | <a
					href="<%=ORSView.COURSE_LIST_CTL%>">Course List</a> <%
 }
 %>



			</font></td>

		</tr>



		<%
		}
		%>



	</table>



	<!-- Language Dropdown -->



	<div align="right">



		<table>

			<tr>

				<td style="width: 120px; text-align: center;">



					<form style="margin: 0;">



						<select name="lang" onchange="this.form.submit()">



							<option value="en" <%=("en".equals(locale)) ? "selected" : ""%>>

								English</option>



							<option value="hi" <%=("hi".equals(locale)) ? "selected" : ""%>>

								Hindi</option>



						</select>



					</form>



				</td>

			</tr>

		</table>



	</div>



	<hr>



</body>

</html>