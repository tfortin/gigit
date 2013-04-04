package org.tfortin.gigit.action;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.SearchRepository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.tfortin.gigit.controller.git.GitHubManager;
import org.tfortin.gigit.controller.utils.SessionManager;
import org.tfortin.gigit.exception.GitUserEmptyException;
import org.tfortin.gigit.exception.GitUserNotFoundException;
import org.tfortin.gigit.model.dao.Bookmark;
import org.tfortin.gigit.model.dao.BookmarkManager;
import org.tfortin.gigit.model.dao.History;
import org.tfortin.gigit.model.dao.HistoryManager;
import org.tfortin.gigit.model.dao.User;

public class MainAction extends LoggedAction {

	private static final long serialVersionUID = -3647283069507854772L;
	
	private static final String LOGOUT_RETURN = "logout";
	private static final String LOGIN_RETURN = "login";
	private String searchString;
	private boolean userSearch;
	private List<SearchRepository> repos;
	private String login;
	private String pwd;
	private List<History> favorites;
	private List<Bookmark> bookmarks;

	public String searchReposByName() {
		GitHubManager ghm = new GitHubManager();
		try {
			this.repos = ghm.searchForReposByName(this.searchString);
		} catch (IOException e) {
			addActionError(getText("gigit.github.error.search.unknownError", new String[] { this.searchString, e.getMessage() }));
			return ERROR;
		}
		int counter = this.repos.size();
		if(counter == 0) {
			addActionMessage(getText("gigit.searchForm.result.repo.noResult", new String[] { this.searchString }));
			return ERROR;
		}
		this.userSearch = false;
		addActionMessage(getText("gigit.searchForm.result.repo.label", new String[] { this.searchString, "" + counter }));
		return SUCCESS;
	}

	public String searchReposByUserName() {
		GitHubManager ghm = new GitHubManager();
		try {
			this.repos = ghm.getUserRepos(this.searchString);
		} catch (IOException e) {
			addActionError(getText("gigit.github.error.search.unknownError", new String[] { this.searchString, e.getMessage() }));
			return ERROR;
		} catch (GitUserNotFoundException e) {
			addActionError(e.getMessage());
			return ERROR;
		} catch (GitUserEmptyException e) {
			addActionError(e.getMessage());
			return ERROR;
		}
		int counter = this.repos.size();
		if(counter == 0) {
			addActionMessage(getText("gigit.searchForm.result.user.noResult", new String[] { this.searchString }));
			return ERROR;
		}
		this.userSearch = true;
		addActionMessage(getText("gigit.searchForm.result.user.label", new String[] { this.searchString, "" + counter }));
		return SUCCESS;
	}

	public String searchUserRepos() {
		this.searchString = this.getCurrentClient().getUser();
		return this.searchReposByUserName();
	}

	public String gitConnect() {
		SessionManager sm = new SessionManager();
		if (this.login != null && this.pwd != null) {
			try {
				GitHubClient client = new GitHubClient();
				client.setCredentials(this.login, this.pwd);
				GitHubManager ghm = new GitHubManager(client);
				ghm.userIsValid();
				
				sm.put("gitClient", client);
				sm.setMessages(getText("gigit.gitConnect.success",
						new String[] { this.login }));
				return SUCCESS;
			} catch (Exception e) {
				sm.setErrors(getText("gigit.gitConnect.error",
						new String[] { e.getMessage() }));
				return SUCCESS;
			}
		}
		return LOGIN_RETURN;
	}

	public String gitDisconnect() {
		SessionManager sm = new SessionManager();
		sm.remove("gitClient");
		sm.setMessages(getText("gigit.gitDisonnect.success"));
		return SUCCESS;
	}

	public String logOut() {
		SessionManager sm = new SessionManager();
		sm.remove("authenticate");
		sm.remove("userId");
		sm.remove("gitClient");
		sm.setMessages(getText("gigit.main.logout.message"));
		return LOGOUT_RETURN;
	}

	public String browseFavorites() {
		User currentUser = this.getCurrentUser();
		this.favorites = new HistoryManager().findByUserId(currentUser.getId());
		return SUCCESS;
	}

	public String browseBookmarks() {
		User currentUser = this.getCurrentUser();
		this.bookmarks = new BookmarkManager().findByUserId(currentUser.getId());
		return SUCCESS;
	}

	public String help() {
		return SUCCESS;
	}

	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public List<SearchRepository> getRepos() {
		return repos;
	}
	public void setRepos(List<SearchRepository> repos) {
		this.repos = repos;
	}
	public boolean isUserSearch() {
		return userSearch;
	}
	public void setUserSearch(boolean userSearch) {
		this.userSearch = userSearch;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public List<History> getFavorites() {
		return favorites;
	}
	public void setFavorites(List<History> favorites) {
		this.favorites = favorites;
	}
	public List<Bookmark> getBookmarks() {
		return bookmarks;
	}
	public void setBookmarks(List<Bookmark> bookmarks) {
		this.bookmarks = bookmarks;
	}

}
