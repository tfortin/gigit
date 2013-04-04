<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:text name="gigit.bookmark.list.select" /><br /><br />
<s:iterator value="bookmarks">
	<ul class="bookmarksList">
		<s:url id="repoUrl" action="RepoDetails">
			<s:param name="repoId"><s:property value="project" /></s:param>
		</s:url>
		<li><img src="images/bookmark.png" /> <s:a href="%{repoUrl}"><s:property value="project" /></s:a>
		</li>
	</ul>
</s:iterator>

<s:if test="bookmarks.size == 0">
	<s:text name="gigit.bookmark.list.empty" />
</s:if>
