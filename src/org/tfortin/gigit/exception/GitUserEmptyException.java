package org.tfortin.gigit.exception;

import org.tfortin.gigit.controller.utils.ResourcesManager;

public class GitUserEmptyException extends Exception {

	private static final long serialVersionUID = 6785264117775840342L;
	
	public GitUserEmptyException(String message) {
		super(ResourcesManager.getText("gigit.github.error.search.userEmpty", new String [] { message }));
	}

}
