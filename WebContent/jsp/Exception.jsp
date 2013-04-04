<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="exceptionTitle"><s:text name="gigit.global.exception.title" /></div>
<br />
<s:text name="gigit.global.exception.label">
	<s:param>
		<s:property value="exception" />
	</s:param>
</s:text>
<br /><br />
<div class="exceptionStack"><s:property value="exceptionStack" /></div>
