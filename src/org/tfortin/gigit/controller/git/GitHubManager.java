package org.tfortin.gigit.controller.git;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.SearchRepository;
import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.client.RequestException;
import org.eclipse.egit.github.core.service.CollaboratorService;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.eclipse.egit.github.core.service.UserService;
import org.tfortin.gigit.controller.utils.ResourcesManager;
import org.tfortin.gigit.controller.utils.SessionManager;
import org.tfortin.gigit.exception.GitUserEmptyException;
import org.tfortin.gigit.exception.GitUserNotFoundException;

public class GitHubManager {

	private UserService userService;
	private RepositoryService repoService;
	private CollaboratorService collabService;
	private CommitService commitService;

	public GitHubManager() {
		this((GitHubClient)new SessionManager().get("gitClient"));
	}

	public GitHubManager(GitHubClient client) {
		if(client != null) {
			this.userService = new UserService(client);
			this.repoService = new RepositoryService(client);
			this.collabService = new CollaboratorService(client);
			this.commitService = new CommitService(client);
		} else {
			this.userService = new UserService();
			this.repoService = new RepositoryService();
			this.collabService = new CollaboratorService();
			this.commitService = new CommitService();
		}
	}

	public boolean userExists(String login) throws IOException {
		User gitUser = null;
		gitUser = this.userService.getUser(login);
		return gitUser != null;
	}

	public boolean userIsValid() throws IOException {
		User gitUser = null;
		gitUser = this.userService.getUser();
		return gitUser != null;
	}

	public List<SearchRepository> getUserRepos(String login)
			throws GitUserNotFoundException, IOException, GitUserEmptyException {
		try {
			if (!userExists(login)) {
				throw new GitUserNotFoundException(login, ResourcesManager.getText("gigit.github.error.search.userNotFound.2"));
			}
		} catch (IllegalArgumentException iar) {
			throw new GitUserEmptyException(iar.getMessage());
		} catch(RequestException re) {
			throw new GitUserNotFoundException(login, re.getMessage());
		}
		Map<String, String> criterias = new HashMap<String, String>();
		criterias.put("user", login);
		return this.repoService.searchRepositories(criterias);
	}

	public List<SearchRepository> searchForReposByName(String name)
			throws IOException {
		return this.repoService.searchRepositories(name);
	}

	public Repository searchRepoById(String id) throws IOException {
		RepositoryId repoId = RepositoryId.createFromId(id);
		return this.repoService.getRepository(repoId);
	}

	public List<User> getRepoCollaborators(String id) throws IOException {
		RepositoryId repoId = RepositoryId.createFromId(id);
		return this.collabService.getCollaborators(repoId);
	}

	public List<RepositoryCommit> getRepoCommits(String id) throws IOException {
		RepositoryId repoId = RepositoryId.createFromId(id);
		return this.commitService.getCommits(repoId);
	}

	public User getUserByLogin(String login) throws IOException {
		return this.userService.getUser(login);
	}

}
