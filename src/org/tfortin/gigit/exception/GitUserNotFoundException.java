package org.tfortin.gigit.exception;

import org.tfortin.gigit.controller.utils.ResourcesManager;

public class GitUserNotFoundException extends Exception {

	private static final long serialVersionUID = 6785264117775840342L;
	
	public GitUserNotFoundException(String login, String message) {
		super(ResourcesManager.getText("gigit.github.error.search.userNotFound", new String[] { login, message }));
	}

}
