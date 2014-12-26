<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Registered</title>
</head>
<body>
<%
	Cookie firstName = new Cookie("first_name",request.getParameter("user.firstName"));
	Cookie lastName = new Cookie("last_name",request.getParameter("user.lastName"));
	Cookie role = new Cookie("role",request.getParameter("user.role"));
	
	firstName.setMaxAge(60*60*24);
	lastName.setMaxAge(60*60*24);
	role.setMaxAge(60*60*24);
	
	response.addCookie(firstName);
	response.addCookie(lastName);
	response.addCookie(role);
%>
<jsp:forward page="/DataController?action=getHomePageData&data=${user.role}" />
</body>
</html>