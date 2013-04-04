<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:url id="indexEN" namespace="/" action="ChangeLocale">
	<s:param name="request_locale">en</s:param>
</s:url>
<s:url id="indexFR" namespace="/" action="ChangeLocale">
	<s:param name="request_locale">fr</s:param>
</s:url>

<div class="leftAlign">
	<s:a href="Welcome">
		<img src="images/home.png" />
		<s:text name="gigit.menu.home" />
	</s:a>
	-
	<s:a href="%{indexEN}" tooltip="English" title="English"><img src="images/lang/en.png" /></s:a>
	<s:a href="%{indexFR}" tooltip="Français" title="Français"><img src="images/lang/fr.png" /></s:a>
</div>

<div class="rightAlign">
	<s:if test="#session.authenticate != 'true'">
		<s:a href="Register">
			<img src="images/register.png" />
			<s:text name="gigit.menu.register" />
		</s:a>
	</s:if>
	<s:else>
		<s:text name="gigit.menu.login.label" />
		<s:property value="currentUser.login" />
		-
		<s:a href="LogOut">
			<s:text name="gigit.menu.logout" />
			<img src="images/logout.png" />
		</s:a>
	</s:else>
</div>