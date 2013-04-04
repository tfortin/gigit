<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:text name="gigit.favorite.list.select" /><br /><br />
<s:iterator value="favorites">
	<ul class="favoritesList">
		<s:if test="userSearch == true">
			<s:text id="label" name="gigit.favorite.userName.label">
				<s:param><s:property value="search" /></s:param>
			</s:text>
			<s:url id="searchUrl" action="SearchReposByUserName">
				<s:param name="searchString"><s:property value="search" /></s:param>
			</s:url>
		</s:if>
		<s:else>
			<s:text id="label" name="gigit.favorite.repoName.label">
				<s:param><s:property value="search" /></s:param>
			</s:text>
			<s:url id="searchUrl" action="SearchReposByName">
				<s:param name="searchString"><s:property value="search" /></s:param>
			</s:url>
		</s:else>
		<li><img src="images/history.png" /> <s:a href="%{searchUrl}"><s:text name="label" /></s:a>
		</li>
	</ul>
</s:iterator>

<s:if test="favorites.size == 0">
	<s:text name="gigit.favorite.list.empty" />
</s:if>
