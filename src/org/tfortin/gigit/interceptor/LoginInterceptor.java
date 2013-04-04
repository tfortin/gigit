package org.tfortin.gigit.interceptor;

import org.tfortin.gigit.action.GigitAction;
import org.tfortin.gigit.action.LoggedAction;
import org.tfortin.gigit.controller.utils.ResourcesManager;
import org.tfortin.gigit.controller.utils.SessionManager;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.PreResultListener;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

public class LoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 3192668311100698570L;
	private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			SessionManager sm = new SessionManager();
			String auth = (String)sm.get("authenticate");
			GigitAction action = (GigitAction)invocation.getAction();
			if((action instanceof LoggedAction) && !"true".equals(auth)) {
				this.logger.info("User not authenticated.");
				action.addActionError(ResourcesManager.getText("gigit.global.login.error.label"));
				return "login";
			}
		} catch (Exception e) {
			invocation.setResultCode("exception");
		}
		
		invocation.addPreResultListener(new PreResultListener() {
			@Override
			public void beforeResult(ActionInvocation invocation,
					String resultCode) {
				SessionManager sm = new SessionManager();
				String auth = (String)sm.get("authenticate");
				GigitAction action = (GigitAction)invocation.getAction();
				if(!(action instanceof LoggedAction) && "true".equals(auth)) {
					invocation.setResultCode("main");
				}
			}
		});

		return invocation.invoke();
	}
	
}
