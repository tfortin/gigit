<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<sj:head jqueryui="true" />

<div class="actionMenu">
	<div>
		<s:a href="Search" title="%{getText('gigit.menu.button.search')}"
			tooltip="%{getText('gigit.menu.button.search')}">
			<img src="images/search.png" />
		</s:a>
	</div>
	<s:if test="%{currentClient == null}">
		<div>
			<sj:a cssClass="button" openDialog="connectToGitDialog"
				title="%{getText('gigit.menu.button.git.connect')}"
				tooltip="%{getText('gigit.menu.button.git.connect')}">
				<img src="images/connect.png" />
			</sj:a>
			<s:url var="gitConnectUrl" action="GitConnect" />
			<sj:dialog id="connectToGitDialog" autoOpen="false" modal="true"
				showEffect="slide" hideEffect="explode" href="%{gitConnectUrl}"
				title="%{getText('gigit.menu.button.git.connect')}"><img src="images/loader.gif" /></sj:dialog>
		</div>
	</s:if>
	<s:else>
		<div>
			<s:a href="GitDisconnect"
				title="%{getText('gigit.menu.button.git.disconnect')}"
				tooltip="%{getText('gigit.menu.button.git.disconnect')}">
				<img src="images/disconnect.png" />
			</s:a>
		</div>
		<div>
			<s:a href="SearchUserRepos"
				title="%{getText('gigit.menu.button.userRepo', {currentClient.user})}"
				tooltip="%{getText('gigit.menu.button.userRepo', {currentClient.user})}">
				<img src="images/folder_repo.png" />
			</s:a>
		</div>
	</s:else>
	<s:if test="%{searchString != null && searchString != ''}">
		<div>
			<s:form action="AddToHistory" method="post" theme="simple">
				<s:hidden name="history.search" value="%{searchString}" />
				<s:hidden name="history.userSearch" value="%{userSearch}" />
				<s:submit type="image" src="images/history.png"
					tooltip='%{getText("gigit.menu.button.history", {searchString})}'
					title='%{getText("gigit.menu.button.history", {searchString})}' />
			</s:form>
		</div>
	</s:if>
	<div>
		<sj:a cssClass="button" openDialog="browseFavoritesDialog"
			title="%{getText('gigit.menu.button.historyList')}"
			tooltip="%{getText('gigit.menu.button.historyList')}">
			<img src="images/folder_history.png" />
		</sj:a>
		<s:url var="browseFavoritesUrl" action="BrowseFavorites" />
		<sj:dialog id="browseFavoritesDialog" autoOpen="false" modal="true"
			showEffect="slide" height="300" width="500" hideEffect="explode" href="%{browseFavoritesUrl}"
			title="%{getText('gigit.menu.button.historyList')}"><img src="images/loader.gif" /></sj:dialog>
	</div>
	<div>
		<sj:a cssClass="button" openDialog="browseBookmarksDialog"
			title="%{getText('gigit.menu.button.bookmarkList')}"
			tooltip="%{getText('gigit.menu.button.bookmarkList')}">
			<img src="images/folder_bookmark.png" />
		</sj:a>
		<s:url var="browseBookmarksUrl" action="BrowseBookmarks" />
		<sj:dialog id="browseBookmarksDialog" autoOpen="false" modal="true"
			showEffect="slide" height="300" width="500" hideEffect="explode" href="%{browseBookmarksUrl}"
			title="%{getText('gigit.menu.button.bookmarkList')}"><img src="images/loader.gif" /></sj:dialog>
	</div>
	<div>
		<sj:a cssClass="button" openDialog="helpDialog"
			title="%{getText('gigit.menu.button.help')}"
			tooltip="%{getText('gigit.menu.button.help')}">
			<img src="images/help.png" />
		</sj:a>
		<s:url var="helpUrl" action="Help" />
		<sj:dialog id="helpDialog" autoOpen="false" modal="true"
			showEffect="slide" height="500" width="700" hideEffect="explode" href="%{helpUrl}"
			title="%{getText('gigit.menu.button.help')}"><img src="images/loader.gif" /></sj:dialog>
	</div>
</div>