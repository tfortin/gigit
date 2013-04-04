package org.tfortin.gigit.action;


public class WelcomeAction extends GigitAction {
	
	private static final long serialVersionUID = -2268764501330191789L;
	
	public String execute() {
		if(!hasActionMessages()) {
			addActionMessage(getText("gigit.home.welcome"));
		}
		return NONE;
	}

}
