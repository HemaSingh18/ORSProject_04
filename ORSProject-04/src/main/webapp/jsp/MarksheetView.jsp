<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.MarksheetCtl"%>
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
	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.MarksheetBean"
		scope="request"></jsp:useBean>
	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);
	%>
	<%@include file="Header.jsp"%>

	<div align="center">

		<h1>Add MarkSheet</h1>
		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>

		<form action="<%=ORSView.MARKSHEET_CTL%>" method="post">
			<table>
				<tr>
					<th>Roll No.</th>
					<td><input type="number" name="rollNo"
						placeholder="enter your roll no"
						value="<%=DataUtility.getStringData(bean.getRollNo())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("rollNo", request)%></td>
				</tr>

				<tr>
					<th>Student Id</th>
					<td><input type="number" name="studentId"
						placeholder="enter your id"
						value="<%=DataUtility.getStringData(bean.getStudentId())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("studentId", request)%></td>
				</tr>

				<tr>
					<th>Name</th>
					<td><input type="text" name="name"
						placeholder="enter your name"
						value="<%=DataUtility.getStringData(bean.getName())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("name", request)%></td>
				</tr>

				<tr>
					<th>Physics</th>
					<td><input type="number" name="physics"
						placeholder="enter your physics marks"
						value="<%=DataUtility.getStringData(bean.getPhysics())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("physics", request)%></td>
				</tr>

				<tr>
					<th>Chemistry</th>
					<td><input type="number" name="chemistry"
						placeholder="enter your chemistry marks"
						value="<%=DataUtility.getStringData(bean.getChemistry())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("chemistry", request)%></td>
				</tr>

				<tr>
					<th>Maths</th>
					<td><input type="number" name="maths"
						placeholder="enter your maths marks"
						value="<%=DataUtility.getStringData(bean.getMaths())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("maths", request)%></td>
				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=MarksheetCtl.OP_SAVE%>"></td>
				</tr>
			</table>
		</form>
	</div>
	<%@include file="Footer.jsp"%>
</body>
</html>