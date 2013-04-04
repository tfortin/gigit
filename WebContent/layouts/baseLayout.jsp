<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}<tiles:getAsString name='cssFile'/>" />
</head>
<body>
	<div class="baseLayout">
		<div class="headerLayout">
			<tiles:insertAttribute name="header" />
		</div>
		<div class="menuLayout">
			<tiles:insertAttribute name="menu" />
		</div>
		<div class="bodyLayout">
			<tiles:insertAttribute name="common" />
			<div class="contentLayout">
				<tiles:insertAttribute name="content" />
			</div>
		</div>
		<div class="footerLayout">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
</body>
</html>