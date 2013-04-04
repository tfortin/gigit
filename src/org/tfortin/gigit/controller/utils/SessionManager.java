package org.tfortin.gigit.controller.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

public class SessionManager {

	private static final Map<String, Object> session;
	
	static {
		if(ActionContext.getContext().getSession() != null) {
			session = ActionContext.getContext().getSession();
		} else {
			session = new HashMap<String, Object>();
		}
	}

	public Object put(String key, Object value) {
		return session.put(key, value);
	}

	public Object get(String key) {
		return session.get(key);
	}

	public Object remove(String key) {
		return session.remove(key);
	}

	@SuppressWarnings("unchecked")
	public Collection<String> getMessages() {
		return (Collection<String>)this.get("messages");
	}

	public void setMessages(String ... messages) {
		Collection<String> messagesCol = new HashSet<String>();
		for(String message : messages) {
			messagesCol.add(message);
		}
		this.put("messages", messagesCol);
	}

	@SuppressWarnings("unchecked")
	public Collection<String> getErrors() {
		return (Collection<String>)this.get("errors");
	}

	public void setErrors(String ... errors) {
		Collection<String> errorsCol = new HashSet<String>();
		for(String error : errors) {
			errorsCol.add(error);
		}
		this.put("errors", errorsCol);
	}

}
