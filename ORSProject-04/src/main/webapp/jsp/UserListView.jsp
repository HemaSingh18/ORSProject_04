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
	 List<UserBean> list = ServletUtility.getList(request);
	 int pageNo = ServletUtility.getPageNo(request);
	 int pageSize = ServletUtility.getPageSize(request);
	 int index = (pageNo - 1) * pageSize + 1;
	 Iterator<UserBean> it = list.iterator();
	%>
	<%@ include file="Header.jsp"%>
	<div align="center">
		<h1>User List</h1>

		<form action="<%=ORSView.USER_LIST_CTL%>" method="post">
			<table border="1px" width="100%">
				<tr style="background: skyblue">
					<th>S No.</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Login</th>
					<th>Gender</th>
					<th>Dob</th>
				</tr>

				<%
					while(it.hasNext()){
						UserBean bean = it.next();
						%>
				<tr align="center">
					<td><%=index++ %></td>
					<td><%=bean.getFirstName() %></td>
					<td><%=bean.getLastName() %></td>
					<td><%=bean.getLogin() %></td>
					<td><%=bean.getGender() %></td>
					<td><%=bean.getDob() %></td>
				</tr>
				<% 
				}
				%>
			</table>
		</form>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>