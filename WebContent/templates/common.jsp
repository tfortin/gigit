<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<sj:head/>

<s:if test="hasActionErrors()">
	<div class="errors">
		<img src="images/error.png">
		<s:actionerror />
	</div>
</s:if>
<s:elseif test="hasActionMessages()">
	<div class="messages">
		<img src="images/message.png">
		<s:actionmessage />
	</div>
</s:elseif>
