<%@page import="in.co.rays.proj4.controller.FacultyCtl"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.FacultyBean"
		scope="request"></jsp:useBean>
	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);
	%>

	<%@ include file="Header.jsp"%>
	<div align="center">
		<h1><%=bean != null && bean.getId() > 0 ? "Update Faculty" : "Add Faculty"%></h1>
		<h3 style="color: succ"><%=succ%></h3>
		<h3 style="color: error"><%=error%></h3>

		<form action="<%=ORSView.FACULTY_CTL%>" method="post">
			<table>
				<tr>
					<th>College</th>
					<td><select name="collegeId">
							<option value="">-- Select College --</option>
							<option value="1">SGSIT</option>
							<option value="2">Indore Christian College</option>
							<option value="3">SABVG</option>
							<option value="4">Government Holkar Science College</option>
							<option value="5">DAVV</option>
					</select></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("collegeId", request)%></td>
				<tr>
					<th>First Name</th>
					<td><input type="text" name="firstName"
						placeholder="enter first name"
						value="<%=DataUtility.getStringData(bean.getFirstName())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("firstName", request)%></td>
				</tr>
				<tr>
					<th>Last Name</th>
					<td><input type="text" name="lastName"
						placeholder="enter last name"
						value="<%=DataUtility.getStringData(bean.getLastName())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("lastName", request)%></td>
				</tr>
				<tr>
					<th>Email</th>
					<td><input type="text" name="email" placeholder="enter email"
						value="<%=DataUtility.getStringData(bean.getEmail())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("email", request)%></td>
				</tr>
				<tr>
					<th>Address</th>
					<td><input type="text" name="address"
						placeholder="enter address"
						value="<%=DataUtility.getStringData(bean.getAddress())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("address", request)%></td>
				</tr>
				<tr>
					<th>Gender<font color="red">*</font></th>
					<td><select name='gender'>
							<option selected value=''>------------Select-------------</option>
							<option value='female'>female</option>
							<option value='male'>male</option>
					</select></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("gender", request)%></td>
				</tr>
				<tr>
					<th>Date Of Birth</th>
					<td><input type="Date" name="dateOfBirth"
						placeholder="enter date of birth"
						value="<%=DataUtility.getStringData(bean.getDateOfBirth())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("dateOfBirth", request)%></td>
				</tr>
				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=FacultyCtl.OP_SAVE%>"></td>
				</tr>
			</table>

		</form>

	</div>

	<%@ include file="Footer.jsp"%>
</body>
</html>