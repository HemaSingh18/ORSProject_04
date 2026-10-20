<%@page import="in.co.rays.proj4.controller.CollegeCtl"%>
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

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.CollegeBean"
		scope="request"></jsp:useBean>

	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);
	%>
	
	<%@ include file="Header.jsp"%>
	
	<div align="center">
	
		<h1><%=bean != null && bean.getId() > 0 ? "Update College" : "Add College"%></h1>
		
		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>
		

		<form action="<%=ORSView.COLLEGE_CTL%>" method="post">
		
			<table>
			
				<tr>
					<th>Name</th>
					<td><input type="text" name="name"
						placeholder="enter college name"
						value="<%=DataUtility.getStringData(bean.getName())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("name", request)%></td>
				</tr>

				<tr>
					<th>Address</th>
					<td><input type="text" name="address"
						placeholder="enter college address"
						value="<%=DataUtility.getStringData(bean.getAddress())%>"></td>

					<td style="color: red"><%=ServletUtility.getErrorMessage("address", request)%>
					</td>
				</tr>

				<tr>
					<th>State</th>
					<td><input type="text" name="state" placeholder="enter state"
						value="<%=DataUtility.getStringData(bean.getState())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("state", request)%></td>
				</tr>
				
					<tr>
					<th>City</th>
					<td><input type="text" name="city" placeholder="enter city"
						value="<%=DataUtility.getStringData(bean.getCity())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("city", request)%></td>
				</tr>

				<tr>
					<th>Phone Number</th>
					<td><input type="text" name="phoneNo" placeholder="enter phone number"
						value="<%=DataUtility.getStringData(bean.getPhoneNo())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("phoneNo", request)%></td>
				</tr>

				<th></th>
				<td><input type="submit" name="operation"
					value="<%=CollegeCtl.OP_SAVE%>"></td>

			</table>

		</form>

	</div>

	<%@ include file="Footer.jsp"%>
	
</body>
</html>