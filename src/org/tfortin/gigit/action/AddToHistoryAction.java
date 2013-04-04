package org.tfortin.gigit.action;

import java.util.Date;

import org.hibernate.HibernateException;
import org.tfortin.gigit.controller.utils.SessionManager;
import org.tfortin.gigit.model.dao.History;
import org.tfortin.gigit.model.dao.HistoryManager;

public class AddToHistoryAction extends LoggedAction {

	private static final long serialVersionUID = 345715528003921597L;
	private History history;
	private String searchString;
	private boolean userSearch;
	
	public History getHistory() {
		return history;
	}
	public void setHistory(History history) {
		this.history = history;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public boolean isUserSearch() {
		return userSearch;
	}
	public void setUserSearch(boolean userSearch) {
		this.userSearch = userSearch;
	}
	
	public String addToHistory() {
		SessionManager sm = new SessionManager();
		try {
			if(this.history.getSearch() == null) {
				return INPUT;
			}
			this.history.setUser(this.getCurrentUser());
			this.history.setSearchTime(new Date());
			new HistoryManager().save(this.history);
		} catch(HibernateException he) {
			he.printStackTrace();
			sm.setErrors(getText("gigit.global.error.dbConnection", new String[] { he.getMessage() }));
			return INPUT;
		}
		sm.setMessages(getText("gigit.history.adding.success"));
		return INPUT;
	}

}
