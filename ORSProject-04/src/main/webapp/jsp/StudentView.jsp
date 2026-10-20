<%@page import="in.co.rays.proj4.controller.StudentCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.StudentBean"
		scope="request"></jsp:useBean>
	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);
	%>
	<%@ include file="Header.jsp"%>

	<div align="center">

		<h1><%=bean != null && bean.getId() > 0 ? "Update Student" : "Add Student"%></h1>
		<h3 style="color: red"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>

		<form action="<%=ORSView.STUDENT_CTL%>" method="post">

			<table>
				<%-- <tr>
					<th>College Id</th>
					<td><input type="number" name="collegeId"
						placeholder="enter your college id"
						value="<%=DataUtility.getStringData(bean.getCollegeId())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("collegeId", request)%></td>
				</tr> --%>

				<tr>
					<th>College Name</th>
					<td><select name="collegeId">
							<option value="">-- Select College --</option>
							<option value="1">1 - SGSIT</option>
							<option value="2">2 - Indore Christian College</option>
							<option value="3">3 - SABVG</option>
							<option value="4">4 - Government Holkar Science College</option>
							<option value="5">5 - DAVV</option>
					</select></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("collegeName" ,request)%></td>
				</tr>

				<tr>
					<th>First Name</th>
					<td><input type="text" name="firstName"
						placeholder="enter your first name"
						value="<%=DataUtility.getStringData(bean.getFirstName())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("firstName", request)%></td>
				</tr>

				<tr>
					<th>Last Name</th>
					<td><input type="text" name="lastName"
						placeholder="enter your last name"
						value="<%=DataUtility.getStringData(bean.getLastName())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("lastName", request)%></td>
				</tr>

				<tr>
					<th>Date Of Birth</th>
					<td><input type="Date" name="dateOfBirth"
						value="<%=DataUtility.getStringData(bean.getDateOfBirth())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("dateOfBirth", request)%></td>
				</tr>

				<tr>
					<th>Mobile No</th>
					<td><input type="tel" name="mobileNo" pattern="[6-9][0-9]{9}"
						maxlength="10" minlength="10" required
						placeholder="Enter a valid 10-digit mobile number"
						value="<%=DataUtility.getStringData(bean.getMobileNo())%>"></td>
					<td style ="color: red"><%=ServletUtility.getErrorMessage("mobileNo" ,request) %></td>	
				</tr>
				
				<tr>
				   <th>Email</th>
					<td><input type="text" name="email" placeholder="enter your email"
						value="<%=DataUtility.getStringData(bean.getEmail())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("email", request)%></td>
				</tr>
				
				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=StudentCtl.OP_SAVE%>"></td>
			</table>

		</form>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>