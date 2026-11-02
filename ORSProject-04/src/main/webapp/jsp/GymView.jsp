
<%@page import="in.co.rays.proj4.controller.GymCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
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
<%@include file ="Header.jsp"%>

	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);
	%>

	<div align="center">

		<h1>Add</h1>
		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>

		<form action="<%=ORSView.GYM_CTL%>" method="post">
			<table>
				<tr>
                    <th>Name</th>
                    <td><input type ="text" name="name" placeholder = "Enter your name" value=""></td>
                    <td style ="color: red"><%=ServletUtility.getErrorMessage("name",request) %></td>
				</tr>
				
				<tr>
				   <th>Membership Type</th>
				   <td><input type ="text" name="membershipType" placeholder ="Enter your Membership" value=""></td>
				   <td style ="color: red"><%=ServletUtility.getErrorMessage("membershipType",request) %></td>
				</tr>
				
				<tr>
				   <th>Joining Date</th>
				   <td><input type ="text" name="joiningDate" placeholder ="Enter your joining date" value=""></td>
				   <td style="color: red"><%=ServletUtility.getErrorMessage("joiningDate",request)%></td>
				</tr>
				<tr>
				    <th>Trainer Name</th>
				    <td><input type ="text" name = "trainerName" placeholder = "Enter trainer name" value=""></td>
				    <td style="color: red"><%=ServletUtility.getErrorMessage("trainerName",request) %></td>
				</tr>
				
				<tr>
				  <th></th>
				  <td><input type ="submit" name="operation" value="<%=GymCtl.OP_SAVE%>"></td>
				</tr>
			</table>
		</form>
	</div>
	<%@include file = "Footer.jsp" %>
</body>
</html>