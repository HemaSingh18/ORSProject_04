<%@page import="in.co.rays.proj4.controller.AtmCtl"%>
<%@page import="in.co.rays.proj4.controller.CollegeCtl"%>
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

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.AtmBean"
		scope="request"></jsp:useBean>

	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);
	%>
	
	<%@ include file="Header.jsp"%>
	
	<div align="center">
	
		<h1><%=bean !=null && bean.getId()> 0 ? "Update Atm" : "Add Atm"%></h1>
		
		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>
		

		<form action="<%=ORSView.ATM_CTL%>" method="post">
		
			<table>
			
				<tr>
					<th>Location</th>
					<td><input type="text" name="location"
						placeholder="enter location name"
						value="<%=DataUtility.getStringData(bean.getLocation())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("location", request)%></td>
				</tr>

				<tr>
					<th>Bank Name</th>
					<td><input type="text" name="bankName"
						placeholder="enter Bank Name"
						value="<%=DataUtility.getStringData(bean.getBankName())%>"></td>

					<td style="color: red"><%=ServletUtility.getErrorMessage("bankName", request)%>
					</td>
				</tr>

				<tr>
					<th>CashAvailable</th>
					<td><input type="text" name=cashAvailable placeholder="enter cash Available"
						value="<%=DataUtility.getStringData(bean.getCashAvailable())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("cashAvailable", request)%></td>
				</tr>
				
					<tr>
					<th>Status</th>
					<td><input type="text" name="status" placeholder="enter status"
						value="<%=DataUtility.getStringData(bean.getStatus())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("status", request)%></td>
				</tr>

				

				<th></th>
				<td><input type="submit" name="operation"
					value="<%=AtmCtl.OP_SAVE%>"></td>

			</table>

		</form>

	</div>

	<%@ include file="Footer.jsp"%>
	
</body>
</html>