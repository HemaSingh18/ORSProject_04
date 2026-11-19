<!DOCTYPE html>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.UserRegistrationCtl"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.UserBean"
		scope="request"></jsp:useBean>

	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);
	HashMap<String, String> map = new HashMap<String, String>();
	map.put("male", "male");
	map.put("female", "female");
	map.put("other", "other");
	%>

	<%@ include file="Header.jsp"%>

	<form action="<%=ORSView.USER_REGISTRATION_CTL%>" method="post">

		<div align="center">

			<h1><%=ms.get("register.title")%></h1>

			<h3 style="color: green"><%=succ%></h3>
			<h3 style="color: green"><%=error%></h3>
			<table>

				<tr>
					<th><%=ms.get("register.firstName")%><font color="red">*</font></th>
					<td><input type="text" name="firstName" value=""
						placeholder="enter your firstName"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("firstName", request)%></td>
				</tr>

				<tr>
					<th><%=ms.get("register.lastName")%><font color="red">*</font></th>
					<td><input type="text" name="lastName" value=""
						placeholder="enter your lastName"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("lastName", request)%></td>
				</tr>

				<tr>
					<th><%=ms.get("register.login")%><font color="red">*</font></th>
					<td><input type="text" name="login" value=""
						placeholder="enter an emial"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("login", request)%></td>
				</tr>

				<tr>
					<th><%=ms.get("register.password")%><font color="red">*</font></th>
					<td><input type="password" name="password" value=""
						placeholder="enter an password"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("password", request)%></td>
				</tr>

				<tr>
					<th><%=ms.get("register.confirmPassword")%><font color="red">*</font></th>
					<td><input type="password" name="confirmPassword" value=""
						placeholder="re-enter your password"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("confirmPassword", request)%></td>
				</tr>

				<tr>
					<th><%=ms.get("register.gender")%><font color="red">*</font></th>
					<td><%=HTMLUtility.getList("gender", DataUtility.getStringData(bean.getGender()), map)%></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("gender", request)%></td>
				</tr>

				<tr>
					<th><%=ms.get("register.dob")%><font color="red">*</font></th>
					<td><input type="date" name="dob" value=""></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("dob", request)%></td>
				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=UserRegistrationCtl.OP_SIGN_UP%>"></td>
				</tr>

			</table>

		</div>

	</form>
	<%@ include file="Footer.jsp"%>
</body>
</html>