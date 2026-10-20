<%@page import="in.co.rays.proj4.controller.CourseCtl"%>
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

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.CourseBean"
		scope="request"></jsp:useBean>

	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);
	%>


	<%@ include file="Header.jsp"%>

	<div align="center">


		<h1><%=bean != null && bean.getId() > 0 ? "Update Course" : "Add Course"%></h1>
		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>

		<form action="<%=ORSView.COURSE_CTL%>" method="post">
			<table>
				<tr>
					<th>Name</th>
					<td><input type="text" name="name"
						placeholder="enter course name"
						value="<%=DataUtility.getStringData(bean.getName())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("name", request)%>
					</td>

				</tr>

				<tr>
					<th>Description</th>
					<td><input type="text" name="description"
						placeholder="enter description"
						value="<%=DataUtility.getStringData(bean.getDescription())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("description", request)%></td>
				</tr>

				<tr>
					<th>Duration</th>
					<td><input type="text" name="duration"
						placeholder="enter duration"
						value="<%=DataUtility.getStringData(bean.getDuration())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("duration", request)%></td>
				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=CourseCtl.OP_SAVE%>"></td>
				</tr>

			</table>

		</form>

	</div>

	<%@ include file="Footer.jsp"%>
</body>
</html>