<%@page import="in.co.rays.proj4.controller.AtmCtl"%>
<%@page import="in.co.rays.proj4.bean.AtmBean"%>
<%@page import="in.co.rays.proj4.controller.FacultyCtl"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.bean.FacultyBean"%>
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

	Iterator<AtmBean> it = list.iterator();
	%>

	<%@ include file="Header.jsp"%>

	<div align="center">
		<h1>Atm List</h1>
		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>

		<form action="<%=ORSView.ATM_LIST_CTL%>" method="post">

			<table>
				<tr>
					<th>Bank Name</th>
					<td><input type="text" name="bankName"
						placeholder="search by bank name" value=""><input
						type="submit" name="operation" value="<%=AtmCtl.OP_SEARCH%>"></td>
				</tr>

			</table>

			<table border="5px" width="100%">

				<tr style="background: skyblue">
					<th><input type="checkbox"
						onclick="document.querySelectorAll('input[name=ids]').forEach(c=>c.checked=this.checked)">Select
						All</th>
					<th>S.No.</th>
					<th>Location</th>
					<th>Bank Name</th>
					<th>Cash Available</th>
					<th>Status</th>
					<th>Edit</th>
				</tr>

				<%
				while (it.hasNext()) {
					AtmBean bean = it.next();
				%>

				<tr align="center">
					<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=bean.getLocation()%></td>
					<td><%=bean.getBankName()%></td>
					<td><%=bean.getCashAvailable()%></td>
					<td><%=bean.getStatus()%></td>

					<td><a href="<%=ORSView.ATM_CTL + "?id=" + bean.getId()%>">Edit</a></td>

				</tr>
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