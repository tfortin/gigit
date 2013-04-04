package org.tfortin.gigit.interceptor;

import org.tfortin.gigit.action.GigitAction;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class BeforeInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 3192668311100698570L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Object o = invocation.getAction();
		try {
			if (o instanceof GigitAction) {
				((GigitAction) o).doBefore();
			}
		} catch (Exception e) {
			invocation.setResultCode("error");
		}
		
		return invocation.invoke();
	}
	
}
