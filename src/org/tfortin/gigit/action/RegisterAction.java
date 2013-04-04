package org.tfortin.gigit.action;


import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.tfortin.gigit.controller.utils.SessionManager;
import org.tfortin.gigit.model.dao.User;
import org.tfortin.gigit.model.dao.UserManager;

public class RegisterAction extends GigitAction {

	private static final long serialVersionUID = 7192386735071302440L;
	private User user;
	private UserManager userManager;
	 
	public RegisterAction() {
		super();
		this.userManager = new UserManager();
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	public void setServletRequest(HttpServletRequest arg0) {
	}

	public String execute() {
		addActionMessage(getText("gigit.main.register.start"));
		return SUCCESS;
	}
	
	public String register() {
		if(this.user == null || "".equals(this.user.getLogin())) {
			addActionError(getText("gigit.register.error.emptyLogin"));
			return ERROR;
		}
		try {
			boolean exists = this.userManager.exists(this.user, false);
			
			if (!exists) {
				this.user.setActive(true);
				this.userManager.register(this.user);
	 
				SessionManager sm = new SessionManager();
				sm.setMessages(getText("gigit.register.success.label", new String[] { this.user.getLogin() }));
				return SUCCESS;
			}
			addActionError(getText("gigit.register.error.userExists"));
		} catch(HibernateException he) {
			addActionError(getText("gigit.global.error.dbConnection", new String[] { he.getMessage() }));
		}

		return ERROR;
	}

}
