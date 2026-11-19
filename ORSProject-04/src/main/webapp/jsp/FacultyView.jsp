<%@page import="in.co.rays.proj4.bean.CollegeBean"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.bean.FacultyBean"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.FacultyCtl"%>
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
	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.FacultyBean"
		scope="request"></jsp:useBean>
	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);
	List<CollegeBean> collegeList  = (List) request.getAttribute("collegeList");
	HashMap<String, String> map = new HashMap<String, String>();
	map.put("male", "male");
	map.put("female", "female");
	map.put("other", "other");
	%>

	<%@include file="Header.jsp"%>
	<div align="center">
		<h1><%=bean != null && bean.getId() > 0 ? "Update Faculty" : "Add Faculty"%></h1>
		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>
		<form action="<%=ORSView.FACULTY_CTL%>" method="post">
			<table>
				<tr>
					<th>College<font color="red">*</font></th>
					<td><%=HTMLUtility.getList("collegeId", DataUtility.getStringData(bean.getCollegeId()), collegeList)%><td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("name", request)%></td>
				</tr>

				<tr>
					<th>First Name<font color="red">*</font></th>
					<td><input type="text" name="firstName"
						placeholder="enter your first name"
						value="<%=DataUtility.getStringData(bean.getFirstName())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("firstName", request)%></td>
				</tr>

				<tr>
					<th>Last Name<font color="red">*</font></th>
					<td><input type="text" name="lastName"
						placeholder="enter your last name"
						value="<%=DataUtility.getStringData(bean.getLastName())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("lastName", request)%></td>
				</tr>

				<tr>
					<th>Email<font color="red">*</font></th>
					<td><input type="email" name="email"
						placeholder="enter your email"
						value="<%=DataUtility.getStringData(bean.getEmail())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("email", request)%></td>
				</tr>

				<tr>
					<th>Mobile No.<font color="red">*</font></th>
					<td><input type="number" name="mobileNo"
						placeholder="enter your mobile no"
						value="<%=DataUtility.getStringData(bean.getMobileNo())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("mobileNo", request)%></td>
				</tr>

				<tr>
					<th>Address<font color="red">*</font></th>
					<td><input type="text" name="address"
						placeholder="enter your address"
						value="<%=DataUtility.getStringData(bean.getAddress())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("address", request)%></td>
				</tr>

				<tr>
					<th>Gender<font color="red">*</font></th>
					<td><%=HTMLUtility.getList("gender", DataUtility.getStringData(bean.getGender()), map)%></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("gender", request)%></td>
				</tr>

				<tr>
					<th>DOB<font color="red">*</font></th>
					<td><input type="date" name="dob"
						placeholder="enter your date of birth"
						value="<%=DataUtility.getStringData(bean.getDateOfBirth())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("dob", request)%></td>
				</tr>

				<tr>
					<td><input type="hidden" name="id" value="<%=bean.getId()%>"></td>
				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=FacultyCtl.OP_SAVE%>"></td>
				</tr>
			</table>
		</form>
	</div>
	<%@include file="Footer.jsp"%>
</body>
</html>