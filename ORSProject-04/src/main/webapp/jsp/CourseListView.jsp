<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.proj4.bean.CourseBean"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	List<CourseBean> list = ServletUtility.getList(request);
	int pageNo = ServletUtility.getPageNo(request);
	int pageSize = ServletUtility.getPageSize(request);
	int index = (pageNo - 1) * pageSize + 1;

	Iterator<CourseBean> it = list.iterator();
	%>

	<%@ include file="Header.jsp"%>

	<div align="center">
		<h1>Course List</h1>
		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>

		<form action="<%=ORSView.COURSE_LIST_CTL%>" method="post">

			<table border="1px" width="100%">

				<tr style="background: skyblue">

					<th><input type="checkbox"
						onclick="document.querySelectorAll('input[name=ids]').forEach(c=>c.checked=this.checked)">Select
						All</th>

					<th>S No.</th>
					<th>Course Name</th>
					<th>Description</th>
					<th>Duration</th>
					<th>Edit</th>
				</tr>

				<%
				while (it.hasNext()) {
					CourseBean bean = it.next();
				%>
				<tr align="center">
					<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>
<%--  					 <td><%=bean.getId()%></td> 
 --%>					<td><%=bean.getName()%></td>
					<td><%=bean.getDescription()%></td>
					<td><%=bean.getDuration()%></td>

					<td><a href="<%=ORSView.COURSE_CTL + "?id=" + bean.getId()%>">Edit</a></td>

					<%
					}
					%>
				
			</table>
			<%@ include file="ListFooter.jsp"%>
		</form>
	</div>
	<%@ include file="Footer.jsp"%>

</body>
</html>