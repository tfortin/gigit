package org.tfortin.gigit.controller.utils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

public class ResourcesManager extends LocalizedTextUtil {

	public static String getText(String key, String ... args) {
		String text = findDefaultText(key, ActionContext.getContext().getLocale(), args);
		if(text != null) {
			return text;
		}
		return key;
	}

}
