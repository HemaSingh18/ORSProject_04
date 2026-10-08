<%@page import="in.co.rays.proj4.controller.UserCtl"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);
	%>

	<%@ include file="Header.jsp"%>
	<div align="center">
		<h1>Add User</h1>

		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>

		<form action="<%=ORSView.USER_CTL%>" method="post">
			<table>
				<tr>
					<th>First Name</th>
					<td><input type="text" name="firstName"
						placeholder="enter your first name" value=""></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage(request)%></td>
				</tr>
				<tr>
					<th>Last Name</th>
					<td><input type="text" name="lastName"
						placeholder="enter your last name" value=""></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage(request)%></td>
				</tr>
				<tr>
					<th>Login</th>
					<td><input type="email" name="login"
						placeholder="enter your email" value=""></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage(request)%></td>
				</tr>
				<tr>
					<th>Password</th>
					<td><input type="password" name="password"
						placeholder="enter your password" value=""></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage(request)%></td>
				</tr>
				<tr>
					<th>Gender</th>
					<td><select name="gender">
							<option value="">-- Select Gender --</option>
							<option value="Male">Male</option>
							<option value="Female">Female</option>
							<option value="Other">Other</option>
					</select></td>
				</tr>
				<th>DOB</th>
				<td><input type="date" name="dob" value=""></td>
				<td style="color: red"><%=ServletUtility.getErrorMessage(request)%></td>

				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=UserCtl.OP_SAVE%>"></td>
			</table>
		</form>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>