package org.tfortin.gigit.action;

import org.hibernate.HibernateException;
import org.tfortin.gigit.controller.utils.SessionManager;
import org.tfortin.gigit.model.dao.Bookmark;
import org.tfortin.gigit.model.dao.BookmarkManager;

public class BookmarkAction extends LoggedAction {

	private static final long serialVersionUID = 345715528003921597L;
	private Bookmark bookmark;
	private String repoId;
	
	public Bookmark getBookmark() {
		return bookmark;
	}
	public void setBookmark(Bookmark bookmark) {
		this.bookmark = bookmark;
	}
	public String getRepoId() {
		return repoId;
	}
	public void setRepoId(String repoId) {
		this.repoId = repoId;
	}
	
	public String bookmark() {
		SessionManager sm = new SessionManager();
		try {
			if(this.bookmark.getProject() == null) {
				return INPUT;
			}
			BookmarkManager bm = new BookmarkManager();
			this.repoId = this.bookmark.getProject();
			if(bm.existsForUserAndProject(this.getCurrentUser().getId(), this.repoId)) {
				sm.setErrors(getText("gigit.bookmark.error.exists"));
				return INPUT;
			}
			this.bookmark.setUser(this.getCurrentUser());
			bm.save(this.bookmark);
		} catch(HibernateException he) {
			he.printStackTrace();
			sm.setErrors(getText("gigit.global.error.dbConnection", new String[] { he.getMessage() } ));
			return INPUT;
		}
		sm.setMessages(getText("gigit.bookmark.adding.success"));
		return INPUT;
	}

}
