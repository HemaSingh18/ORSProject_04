<%@page import="in.co.rays.proj4.model.RoleModel"%>
<%@page import="in.co.rays.proj4.controller.UserListCtl"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="java.util.HashMap"%>
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
	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.UserBean"
		scope="request"></jsp:useBean>
	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);
	List<UserBean> list = ServletUtility.getList(request);
	int pageNo = ServletUtility.getPageNo(request);
	int pageSize = ServletUtility.getPageSize(request);
	int index = (pageNo - 1) * pageSize + 1;
	Iterator<UserBean> it = list.iterator();

	/* static dropdown */
	HashMap map = new HashMap();
	map.put("male", "male");
	map.put("female", "female");
	map.put("other", "other");
	%>
	<%@ include file="Header.jsp"%>
	<div align="center">
		<h1>User List</h1>
		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>

		<form action="<%=ORSView.USER_LIST_CTL%>" method="post">

			<table>
				<tr>
					<th>First Name</th>
					<td><input type="text" name="firstName" value="">
					
					 <th>Date Of Birth</th>
                    <td><input type="Date" name="dob" value="">
					
					<th>Gender</th>
					<td><%=HTMLUtility.getList("gender", DataUtility.getStringData(bean.getGender()), map)%><input
						type="submit" name="operation" value="<%=UserListCtl.OP_SEARCH%>"></td> 

				</tr>

			</table>

			<table border="5px" width="100%">
				<tr style="background: skyblue">
					<th><input type="checkbox"
						onclick="document.querySelectorAll('input[name=ids]').forEach(c=>c.checked=this.checked)">Select
						All</th>
					<th>S No.</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Login</th>
					<th>Gender</th>
					<th>Dob</th>
					<th>Role Name</th>
					<th>Edit</th>

				</tr>

				<%
				while (it.hasNext()) {
					bean = it.next();
					
					RoleModel rmodel = new RoleModel();
					
					RoleBean rbean = rmodel.findByPK(bean.getRoleId());
				%>
				<tr align="center">
					<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=bean.getFirstName()%></td>
					<td><%=bean.getLastName()%></td>
					<td><%=bean.getLogin()%></td>
					<td><%=bean.getGender()%></td>
					<td><%=bean.getDob()%></td>
					<td><%=rbean.getName()%></td>
					<td><a href="<%=ORSView.USER_CTL + "?id=" + bean.getId()%>">Edit</a></td>

				</tr>
				<%
				}
				%>
			</table>
			<%@include file="ListFooter.jsp"%>
		</form>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>