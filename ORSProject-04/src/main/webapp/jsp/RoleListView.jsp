<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.proj4.bean.RoleBean"%>
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
	List<RoleBean> list = ServletUtility.getList(request);
	int pageNo = ServletUtility.getPageNo(request);
	int pageSize = ServletUtility.getPageSize(request);
	int index = (pageNo - 1) * pageSize + 1;
	Iterator<RoleBean> it = list.iterator();
	%>
	

	<%@ include file="Header.jsp"%>

	<div align="center">

		<h1>Role List</h1>

		<form action="<%=ORSView.ROLE_LIST_CTL%>" method="post">

			<table border="1px" width="100%">

				<tr style="background: skyblue">
					<th>S.NO</th>
					<th>Name</th>
					<th>Description</th>
				</tr>

				<%
				while (it.hasNext()) {
					RoleBean bean = it.next();
				%>
				<tr align="center">
					<td><%=index++%></td>
					<td><%=bean.getName()%>
					<td><%=bean.getDescription()%></td>
					<%
					}
					%>
				
			</table>

		</form>

	</div>

	<%@ include file="Footer.jsp"%>
</body>
</html>