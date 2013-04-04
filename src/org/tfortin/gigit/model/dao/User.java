package org.tfortin.gigit.model.dao;

// Generated 4 avr. 2013 02:35:38 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable {

	private static final long serialVersionUID = -5872771793952223497L;
	private Long id;
	private String login;
	private String pwd;
	private String firstName;
	private String lastName;
	private Boolean active;
	private Set bookmarks = new HashSet(0);
	private Set histories = new HashSet(0);

	public User() {
	}

	public User(Long id, String login) {
		this.id = id;
		this.login = login;
	}

	public User(Long id, String login, String pwd, String firstName,
			String lastName, Boolean active, Set bookmarks, Set histories) {
		this.id = id;
		this.login = login;
		this.pwd = pwd;
		this.firstName = firstName;
		this.lastName = lastName;
		this.active = active;
		this.bookmarks = bookmarks;
		this.histories = histories;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Set getBookmarks() {
		return this.bookmarks;
	}

	public void setBookmarks(Set bookmarks) {
		this.bookmarks = bookmarks;
	}

	public Set getHistories() {
		return this.histories;
	}

	public void setHistories(Set histories) {
		this.histories = histories;
	}

}
