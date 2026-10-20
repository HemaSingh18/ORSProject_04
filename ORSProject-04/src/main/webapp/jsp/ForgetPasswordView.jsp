<%@page import="in.co.rays.proj4.controller.ForgetPasswordCtl"%>
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

	<%@ include file="Header.jsp"%>

	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);
	%>

	<div align="center">

		<h1>Forget Password</h1>
		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>

		<form action="<%=ORSView.FORGET_PASSWORD_CTL%>" method="post">

			<table>
				<tr>
					<th>Login</th>
					<td><input type="email" name="login"
						placeholder="enter your email" value=""></td>

				</tr>

				<tr>
					
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=ForgetPasswordCtl.OP_SAVE%>"></td>
					
				</tr>

			</table>

		</form>

	</div>

	<%@ include file="Footer.jsp"%>
</body>
</html>