<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:iterator value="repos">
	<s:url id="repoHome" value="%{url}" />
	<ul class="resultList">
		<li><s:url id="repoDetails" action="RepoDetails">
				<s:param name="repoId">%{id}</s:param>
			</s:url> <s:a href="%{repoDetails}">
				<s:property value="id" />
			</s:a> <s:a href="%{repoHome}"
				tooltip="%{getText('gigit.result.goTo.webPage')}"
				title="%{getText('gigit.result.goTo.webPage')}">
				<img src="images/project_home.png" />
			</s:a></li>
	</ul>
</s:iterator>