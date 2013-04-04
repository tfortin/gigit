package org.tfortin.gigit.action;

import org.eclipse.egit.github.core.client.GitHubClient;
import org.tfortin.gigit.controller.utils.SessionManager;
import org.tfortin.gigit.model.dao.User;
import org.tfortin.gigit.model.dao.UserManager;

public class LoggedAction extends GigitAction {

	private static final long serialVersionUID = -1420612757511297409L;
	private User currentUser;
	private GitHubClient currentClient;

	public LoggedAction() {
		SessionManager sm = new SessionManager();
		Long userId = (Long)sm.get("userId");
		this.currentClient = (GitHubClient)sm.get("gitClient");
		try {
			this.currentUser = new UserManager().findById(userId);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public User getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	public GitHubClient getCurrentClient() {
		return currentClient;
	}
	public void setCurrentClient(GitHubClient currentClient) {
		this.currentClient = currentClient;
	}

}
