<%@page import="com.fasterxml.jackson.databind.deser.Deserializers.Base"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.proj4.bean.CollegeBean"%>
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

	List<CollegeBean> list = ServletUtility.getList(request);

	int pageNo = ServletUtility.getPageNo(request);
	int pageSize = ServletUtility.getPageSize(request);
	int index = (pageNo - 1) * pageSize + 1;

	Iterator<CollegeBean> it = list.iterator();
	%>

	<%@ include file="Header.jsp"%>

	<div align="center">
		<h1>College List</h1>
		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>

		<form action="<%=ORSView.COLLEGE_LIST_CTL%>" method="post">

			<table border="1px" width="100%">

				<tr style="background: skyblue">

					<th><input type="checkbox"
						onclick="document.querySelectorAll('input[name=ids]').forEach(c=>c.checked=this.checked)">Select
						All</th>

					<th>S No.</th>
					<th>College Name</th>
					<th>Address</th>
					<th>State</th>
					<th>City</th>
					<th>Phone Number</th>
					<th>Edit</th>
				</tr>

				<%
				while (it.hasNext()) {
					CollegeBean bean = it.next();
				%>
				<tr align="center">
					<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=bean.getName()%></td>
					<td><%=bean.getAddress()%></td>
					<td><%=bean.getState()%></td>
					<td><%=bean.getCity()%></td>
					<td><%=bean.getPhoneNo()%></td>
					<td><a href="<%=ORSView.COLLEGE_CTL + "?id=" + bean.getId()%>">Edit</a></td>
				</tr>
				<%
				}
				%>
			</table>

			<%@ include file="ListFooter.jsp"%>
			<!-- 			<table width="100%"> -->
			<%-- 				<input type="hiddern" name="pageNo" value="<%=pageNo%>"> --%>
			<!-- 				<tr> -->
			<!-- 					<td><input type="submit" name="operation" -->
			<%-- 						<%=pageNo == 1 ? "disabled" : ""%> --%>
			<%-- 						value="<%=BaseCtl.OP_PREVIOUS%>"></td> --%>

			<!-- 					<td align="center"><input type="submit" name="operation" -->
			<%-- 						value="<%=BaseCtl.OP_DELETE%>"></td> --%>

			<!-- 					<td align="right"><input type="submit" name="operation" -->
			<%-- 						<%=pageNo == 0 ? "disabled" : ""%> value="<%=BaseCtl.OP_NEXT%>"></td>. --%>
			<!-- 				</tr> -->


			<!-- 			</table> -->

		</form>

	</div>

	<%@ include file="Footer.jsp"%>
</body>
</html>