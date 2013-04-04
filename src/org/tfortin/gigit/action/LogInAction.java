package org.tfortin.gigit.action;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.tfortin.gigit.controller.utils.SessionManager;
import org.tfortin.gigit.model.dao.User;
import org.tfortin.gigit.model.dao.UserManager;

public class LogInAction extends GigitAction {

	private static final long serialVersionUID = -7121049769108945363L;
	private UserManager userManager;
	private User user;
	 
	private boolean isLoggedIn;
	
	public LogInAction() {
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
	
	public String logIn() {
		try {
			this.isLoggedIn = this.userManager.exists(this.user);
			
			if (this.isLoggedIn) {
				SessionManager sm = new SessionManager();
				sm.put("authenticate", "true");
				sm.put("userId", this.user.getId());
				
				String displayName = this.user.getLogin();
				if(this.user.getFirstName() != null && !"".equals(this.user.getFirstName()) &&
						this.user.getLastName() != null && !"".equals(this.user.getLastName())) {
					displayName += " (" + this.user.getFirstName() + " " + this.user.getLastName().toUpperCase() + ")";
				}
				sm.setMessages(getText("gigit.main.login.label", new String[] { displayName }),
						getText("gigit.main.login.start"));
	 
				return SUCCESS;
			}
			if("".equals(this.user.getLogin())) {
				addActionError(getText("gigit.login.error.emptyLogin"));
			} else {
				addActionError(getText("gigit.login.error.failedLogin"));
			}
		} catch(HibernateException he) {
			addActionError(getText("gigit.global.error.dbConnection", new String[] { he.getMessage() }));
		}
		return ERROR;
	}

}
