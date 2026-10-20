<%@page import="in.co.rays.proj4.bean.MarksheetBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
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
	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);

	List list = ServletUtility.getList(request);
	int pageNo = ServletUtility.getPageNo(request);
	int pageSize = ServletUtility.getPageSize(request);
	int index = (pageNo - 1) * pageSize + 1;

	Iterator<MarksheetBean> it = list.iterator();
	%>

	<%@ include file="Header.jsp"%>

	<div align="center">
		<h1>MarkSheet List</h1>
		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>

		<form action="<%=ORSView.MARKSHEET_LIST_CTL%>" method="post">

			<table border="1px" width="100%">

				<tr style="background: skyblue">
					<th><input type="checkbox"
						onclick="document.querySelectorAll('input[name=ids]').forEach(c=>c.checked=this.checked)">Select
						All</th>
					<th>S.No.</th>
					<th>Roll No</th>
					<th>Student Id</th>
					<th>Name</th>
					<th>Physics</th>
					<th>Chemistry</th>
					<th>Maths</th>
					<th>Edit</th>
				</tr>

				<%
				while (it.hasNext()) {
					MarksheetBean bean = it.next();
				%>

				<tr align="center">
					<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=bean.getRollNo()%></td>
					<td><%=bean.getStudentId()%></td>
					<td><%=bean.getName()%></td>
					<td><%=bean.getPhysics()%></td>
					<td><%=bean.getChemistry()%></td>
					<td><%=bean.getMaths()%></td>

					<td><a
						href="<%=ORSView.MARKSHEET_CTL + "?id" + bean.getId()%>">Edit</a></td>

				</tr>
				<%
				}
				%>
			</table>
			<%@ include file ="ListFooter.jsp" %>
		</form>
	</div>
	<%@ include file="Footer.jsp"%>

</body>
</html>