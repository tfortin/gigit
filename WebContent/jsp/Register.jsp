<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<fieldset>
	<legend>
		<s:text name="gigit.registerForm.register.legend" />
	</legend>
	<s:form action="CreateUser" method="post" theme="css_xhtml">
		<s:textfield name="user.login" value="%{user.login}"
			label="%{getText('gigit.loginForm.login.label')}" size="20"
			cssClass="inputs" />
		<s:password name="user.pwd"
			label="%{getText('gigit.loginForm.pwd.label')}" size="20"
			cssClass="inputs" />
		<s:textfield name="user.firstName" value="%{user.firstName}"
			label="%{getText('gigit.registerForm.firstName.label')}" size="20"
			cssClass="inputs" />
		<s:textfield name="user.lastName" value="%{user.lastName}"
			label="%{getText('gigit.registerForm.lastName.label')}" size="20"
			cssClass="inputs" />
		<s:submit value="%{getText('gigit.registerForm.action.label')}"
			align="center" cssClass="buttons" />
	</s:form>
</fieldset>
