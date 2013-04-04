package org.tfortin.gigit.action;

import java.util.Collection;

import org.tfortin.gigit.controller.utils.SessionManager;

import com.opensymphony.xwork2.ActionSupport;

public class GigitAction extends ActionSupport {

	private static final long serialVersionUID = -3830553549726360778L;

	public void doBefore() {
		SessionManager sm = new SessionManager();
		Collection<String> messages = sm.getMessages();
		if(messages != null && messages.size() > 0) {
			for(String message : messages) {
				addActionMessage(message);
			}
			sm.remove("messages");
		}
		Collection<String> errors = sm.getErrors();
		if(errors != null && errors.size() > 0) {
			for(String error : errors) {
				addActionError(error);
			}
			sm.remove("errors");
		}
	}

}
