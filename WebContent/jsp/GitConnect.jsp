<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="GitConnect" method="post" theme="css_xhtml"
	focusElement="login">
	<s:textfield id="login" name="login"
		label="%{getText('gigit.loginForm.login.label')}"
		value="%{user.login}" size="20" cssClass="inputs" />
	<s:password id="pwd" name="pwd"
		label="%{getText('gigit.loginForm.pwd.label')}" size="20"
		cssClass="inputs" />
	<s:submit value="%{getText('gigit.loginForm.action.label')}"
		align="center" cssClass="buttons" />
</s:form>