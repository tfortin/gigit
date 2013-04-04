<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sjc" uri="/struts-jquery-chart-tags"%>

<div class="backButton">
	<s:a href="javascript:history.back();">
		<img src="images/back.png" />
		<s:text name="gigit.menu.button.back" />
	</s:a>
</div>
<div class="projectTitle">
	<s:form cssClass="bookmarkForm" action="Bookmark" method="post" theme="simple">
		<s:hidden name="bookmark.project" value="%{repoId}" />
		<s:submit type="image" src="images/bookmark.png"
			tooltip='%{getText("gigit.menu.button.bookmark", {repoId})}'
			title='%{getText("gigit.menu.button.bookmark", {repoId})}' />
	</s:form>
	<s:text name="gigit.repoDetails.title.label">
		<s:param>
			<s:property value="repoId" />
		</s:param>
	</s:text>
</div>
<br />
<br />
<div class="collaboratorsList">
	<s:if test="%{collaborators.size() > 0}">
		<s:text name="gigit.repoDetails.collabList.label">
			<s:param>
				<s:property value="collaboratorsNumber" />
			</s:param>
		</s:text>
		<s:iterator value="collaborators">
			<s:iterator value="value">
				<ul class="userList">
					<li><img class="avatar"
						src="<s:text name='avatarUrl' />" /> <s:if
							test="%{htmlUrl != null}">
							<s:a href="%{htmlUrl}">
								<s:property value="login" />
							</s:a>
							<s:if test="%{name != null}">(<s:property value="name" />)</s:if>
						</s:if> <s:else>
							<s:property value="login" />
						</s:else>
						:<br />
						<s:text name="gigit.repoDetails.collabList.commitNb">
							<s:param>
								<s:property value="key" />
							</s:param>
						</s:text>
						<img src="images/arrow.png" />
						<s:property value="commitStats[id]" />%</li>
				</ul>
			</s:iterator>
		</s:iterator>
	</s:if>
	<s:else>
		<s:text name="gigit.repoDetails.collabList.empty" />
	</s:else>
</div>
<div class="commitsList">
	<s:if test="%{commits.size() > 0}">
		<s:text name="gigit.repoDetails.commitList.label">
			<s:param>
				<s:property value="commits.size()" />
			</s:param>
		</s:text>
		<s:iterator value="commits">
			<s:url id="commitUrl" value="%{htmlUrl}" />
			<s:url id="authorMail" value="" />
			<ul class="resultList">
				<li>[<s:property value="commit.author.date" />] <s:a
						href="mailto:%{commit.author.email}">
						<s:property value="commit.author.name" />
					</s:a> : <s:a href="%{commitUrl}">
						<s:property value="commit.message" />
					</s:a></li>
			</ul>
		</s:iterator>
	</s:if>
	<s:else>
		<s:text name="gigit.repoDetails.commitList.empty" />
	</s:else>
</div>
<s:if test="%{commits.size() > 0}">
	<script type="text/javascript">
		$.subscribe('chartHover', function(event, data) {
			commitIndex = event.originalEvent.item.dataIndex + 1;
			commitDate = event.originalEvent.item.datapoint[0];
			commitNb = event.originalEvent.item.datapoint[1];
			if (commitNb > 1)
				s = "s";
			else
				s = "";
			date = new Date(commitDate);

			$("#graphHoverResult").text(
					"[" + date.toLocaleDateString() + "] " + commitNb
							+ " commit" + s);
		});
	</script>
	<s:text id="graphLabel" name="gigit.repoDetails.commitGraph.label" />
	<sjc:chart id="chartDate" xaxisMode="time" legendPosition="ne"
		xaxisTimeformat="%{timeFormat}" xaxisMin="%{minTime}"
		xaxisMax="%{maxTime}" xaxisColor="#666" xaxisTickSize="%{trickSize}"
		xaxisTickColor="#aaa" xaxisPosition="top" yaxisPosition="left"
		yaxisTickSize="%{maxCommit}" yaxisTickDecimals="0"
		onHoverTopics="chartHover" cssClass="graph">
		<sjc:chartData label="%{graphLabel}" list="graphData" color="#ffaa00"
			lines="{ show: true }" points="{ show: true }" />
	</sjc:chart>
	<div class="graphHoverResult">
		<img src="images/info.png" />
		<s:text name="gigit.repoDetails.commitGraph.hover" />
		<div id="graphHoverResult"></div>
	</div>
</s:if>
<s:else>
	<s:text name="gigit.repoDetails.commitGraph.noData" />
</s:else>
