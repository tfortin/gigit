<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<fieldset>
	<legend><s:text name="gigit.loginForm.login.legend" /></legend>
	<s:form action="LogIn" method="post" theme="css_xhtml" focusElement="login">
		<s:textfield id="login" name="user.login"
			label="%{getText('gigit.loginForm.login.label')}"
			value="%{user.login}" size="20" cssClass="inputs" />
		<s:password id="pwd" name="user.pwd"
			label="%{getText('gigit.loginForm.pwd.label')}" size="20"
			cssClass="inputs" />
		<s:submit value="%{getText('gigit.loginForm.action.label')}"
			align="center" cssClass="buttons" />
	</s:form>
</fieldset>
