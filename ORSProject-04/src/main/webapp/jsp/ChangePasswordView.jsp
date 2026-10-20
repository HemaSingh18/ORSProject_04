
<%@page import="in.co.rays.proj4.controller.ChangePasswordCtl"%>
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
	<%@include file="Header.jsp"%>

	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);
	%>
	<div align="center">

		<h1>Change Password</h1>
		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>
		<form action="<%=ORSView.CHANGE_PASSWORD_CTL%>" method="post">

			<table>
				<tr>
					<th>Login</th>
					<td><input type="email" name="login"
						placeholder="enter your email" value=""></td>
				</tr>

				<tr>
					<th>Password</th>
					<td><input type="password" name="oldPassword"
						placeholder="enter your old password" value=""></td>
				</tr>

				<tr>
					<th>New Password</th>
					<td><input type="password" name="newPassword"
						placeholder="enter your new password" value=""></td>
				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=ChangePasswordCtl.OP_SAVE%>"></td>
				</tr>
			</table>
		</form>
	</div>
	<%@include file="Footer.jsp"%>
</body>
</html>