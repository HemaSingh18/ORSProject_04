<%@page import="in.co.rays.proj4.util.MessageSource"%>
<html>
<head>

<style type="text/css">
#footer {
	position: fixed;
	left: 0;
	width: 100%;
	bottom: 0;
	background-color: white;
	color: black;
	text-align: center;
}
</style>

</head>
<body>

<%
MessageSource ms1 = MessageSource.getInstance();
%>
	<div id="footer">
		<hr>
		<h3><%=ms1.get("footer.title") %></h3>
		<!-- <H3>All rights reserved by Rays EdTech, SunilOS Infotech Pvt Ltd
			Copyright &copy; 2026</H3> -->
	</div>
</html>