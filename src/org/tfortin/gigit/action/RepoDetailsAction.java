package org.tfortin.gigit.action;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import org.eclipse.egit.github.core.CommitUser;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.User;
import org.tfortin.gigit.controller.git.GitHubManager;

public class RepoDetailsAction extends LoggedAction {

	private static final long serialVersionUID = 2865642993949675363L;
	private Repository repo;
	private List<RepositoryCommit> commits;
	private TreeMap<Integer, List<User>> collaborators;
	private HashMap<Integer, String> commitStats;
	private int collaboratorsNumber;
	private TreeMap<Date, Integer> graphData;
	private String repoId;
	private String minTime;
	private String maxTime;
	private int maxCommit;
	private String trickSize;
	private String timeFormat;

	public String execute() {
		try {
			GitHubManager ghm = new GitHubManager();
			this.repo = ghm.searchRepoById(this.repoId);
			List<User> collaborators = ghm.getRepoCollaborators(this.repoId);
			this.commits = ghm.getRepoCommits(this.repoId);
			this.collaboratorsNumber = collaborators.size();
			this.calculateCommits(collaborators);
			this.generateGraphData();
		} catch (IOException e) {
			addActionError(getText("gigit.repoDetails.error",
					new String[] { e.getMessage() }));
			return ERROR;
		}
		addActionMessage(getText("gigit.repoDetails.label",
				new String[] { this.repo.getName() }));
		return SUCCESS;
	}

	private void calculateCommits(List<User> collaborators) {
		this.collaborators = new TreeMap<Integer, List<User>>(
				Collections.reverseOrder());
		this.commitStats = new HashMap<Integer, String>();
		List<RepositoryCommit> commits = new ArrayList<RepositoryCommit>(
				this.commits);
		GitHubManager ghm = new GitHubManager();
		for (User user : collaborators) {
			try {
				user = ghm.getUserByLogin(user.getLogin());
			} catch (Exception e) {
			}
			Integer commitNb = 0;
			for (int i = 0; i < commits.size(); i++) {
				RepositoryCommit commit = commits.get(i);
				User author = commits.get(i).getAuthor();
				if(compareUsers(user, author, commit.getCommit().getCommitter())) {
					commitNb++;
					i--;
					commits.remove(commit);
				}
			}
			List<User> users = this.collaborators.get(commitNb);
			if (users == null)
				users = new ArrayList<User>();
			users.add(user);
			this.collaborators.put(commitNb, users);
			float commitStat = commitNb * 100f / this.commits.size();
			DecimalFormat df = new DecimalFormat("##0.##");
			this.commitStats.put(user.getId(), df.format(commitStat));
		}
	}

	private static boolean compareUsers(User u1, User u2, CommitUser cu2) {
		if (u2 != null) {
			if (u1.getLogin().equals(u2.getLogin())) {
				return true;
			} else if (u1.getLogin().equals(cu2.getName())
					|| (u1.getName() != null && u1.getName().equals(
							cu2.getName()))) {
				return true;
			}
		} else if (u1.getLogin().equals(cu2.getName())
				|| (u1.getName() != null && u1.getName().equals(cu2.getName()))) {
			return true;
		}
		return false;
	}

	private void generateGraphData() {
		this.graphData = new TreeMap<Date, Integer>();
		for (RepositoryCommit commit : this.commits) {
			Date commitDate = getDateWithoutTime(commit.getCommit().getAuthor()
					.getDate());
			Integer commitNb = this.graphData.get(commitDate);
			if (commitNb == null)
				commitNb = 0;
			commitNb++;
			if (commitNb > this.maxCommit)
				this.maxCommit = commitNb;
			this.graphData.put(commitDate, commitNb);
		}
		this.maxCommit++;
		long minTime = this.graphData.firstEntry().getKey().getTime();
		long maxTime = this.graphData.lastEntry().getKey().getTime();
		this.minTime = "" + minTime;
		this.maxTime = "" + maxTime;
		
		if (maxTime - minTime > 31104000000l) {
			this.trickSize = "[1, 'year']";
			this.timeFormat = "%Y";
		} else if (maxTime - minTime > 2592000000l) {
			this.trickSize = "[1, 'month']";
			this.timeFormat = "%m.%y";
		} else if (maxTime - minTime > 86400000l) {
			this.trickSize = "[1, 'week']";
			this.timeFormat = "%d.%m";
		} else {
			this.trickSize = "[1, 'day']";
			this.timeFormat = "%d.%m";
		}
	}

	private static Date getDateWithoutTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	public Repository getRepo() {
		return repo;
	}

	public void setRepo(Repository repo) {
		this.repo = repo;
	}

	public String getRepoId() {
		return repoId;
	}

	public void setRepoId(String repoId) {
		this.repoId = repoId;
	}

	public List<RepositoryCommit> getCommits() {
		return commits;
	}

	public void setCommits(List<RepositoryCommit> commits) {
		this.commits = commits;
	}

	public TreeMap<Integer, List<User>> getCollaborators() {
		return collaborators;
	}

	public void setCollaborators(TreeMap<Integer, List<User>> collaborators) {
		this.collaborators = collaborators;
	}

	public int getCollaboratorsNumber() {
		return collaboratorsNumber;
	}

	public void setCollaboratorsNumber(int collaboratorsNumber) {
		this.collaboratorsNumber = collaboratorsNumber;
	}

	public TreeMap<Date, Integer> getGraphData() {
		return graphData;
	}

	public void setGraphData(TreeMap<Date, Integer> graphData) {
		this.graphData = graphData;
	}

	public String getMinTime() {
		return minTime;
	}

	public void setMinTime(String minTime) {
		this.minTime = minTime;
	}

	public String getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(String maxTime) {
		this.maxTime = maxTime;
	}

	public int getMaxCommit() {
		return maxCommit;
	}

	public void setMaxCommit(int maxCommit) {
		this.maxCommit = maxCommit;
	}

	public String getTrickSize() {
		return trickSize;
	}

	public void setTrickSize(String trickSize) {
		this.trickSize = trickSize;
	}

	public String getTimeFormat() {
		return timeFormat;
	}

	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}

	public HashMap<Integer, String> getCommitStats() {
		return commitStats;
	}

	public void setCommitStats(HashMap<Integer, String> commitStats) {
		this.commitStats = commitStats;
	}

}
