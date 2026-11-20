<%@page import="in.co.rays.proj4.bean.StudentBean"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj4.bean.MarksheetBean"%>
<%@page import="java.util.List"%>
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
	List<StudentBean> studentList = (List) request.getAttribute("studentList");
	%>
	<%@include file="Header.jsp"%>
	<div align="center">
		<h1><%=bean != null && bean.getId()>0 ? "Update Marksheet" : "Add Marksheet"%></h1>
		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>
		<form action="<%=ORSView.MARKSHEET_CTL%>" method="post">
			<table>
				<tr>
					<th>Roll No.<font color="red">*</font></th>
					<td><input type="number" name="rollNo"
						placeholder="enter your roll no"
						value="<%=DataUtility.getStringData(bean.getRollNo())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("rollNo", request)%></td>
				</tr>

				<tr>
					<th>Student Name<font color="red">*</font></th>
					<td><%=HTMLUtility.getList("studentId", DataUtility.getStringData(bean.getStudentId()),studentList)%></td>
						<td style="color: red"><%=ServletUtility.getErrorMessage("studentId", request)%></td>
				
				</tr>
				<tr>
					<th>Physics<font color="red">*</font></th>
					<td><input type="number" name="physics"
						placeholder="enter your physics marks"
						value="<%=DataUtility.getStringData(bean.getPhysics())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("physics", request)%></td>
				</tr>

				<tr>
					<th>Chemistry<font color="red">*</font></th>
					<td><input type="number" name="chemistry"
						placeholder="enter your chemistry marks"
						value="<%=DataUtility.getStringData(bean.getChemistry())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("chemistry", request)%></td>
				</tr>

				<tr>
					<th>Maths<font color="red">*</font></th>
					<td><input type="number" name="maths"
						placeholder="enter your maths marks"
						value="<%=DataUtility.getStringData(bean.getMaths())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("maths", request)%></td>
				</tr>
				
				<tr>
					<td><input type="hidden" name="id" value="<%=bean.getId()%>"></td>
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