<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<fieldset>
	<legend>
		<s:text name="gigit.searchForm.userName.legend" />
	</legend>
	<s:form action="SearchReposByUserName" method="post" theme="css_xhtml"
		focusElement="searchByUser">
		<s:textfield id="searchByUser" name="searchString" value=""
			label="%{getText('gigit.searchForm.userName.label')}" size="20"
			cssClass="inputs" />
		<s:submit value="%{getText('gigit.searchForm.action.label')}"
			align="center" cssClass="buttons" />
	</s:form>
</fieldset>
<fieldset>
	<legend>
		<s:text name="gigit.searchForm.repoName.legend" />
	</legend>
	<s:form action="SearchReposByName" theme="css_xhtml" method="post">
		<s:textfield name="searchString" value=""
			label="%{getText('gigit.searchForm.repoName.label')}" size="20"
			cssClass="inputs" />
		<s:submit value="%{getText('gigit.searchForm.action.label')}"
			align="center" cssClass="buttons" />
	</s:form>
</fieldset>