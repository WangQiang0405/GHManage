package com.jfreechart.commons;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class FileUtil {

    /**
     * ���web��Ŀ��Ŀ¼
     */
    public static String getWebRootPath() throws Exception {
	ActionContext actionContext = ActionContext.getContext();
	ServletContext servletContext = (ServletContext) actionContext.get(ServletActionContext.SERVLET_CONTEXT);
	String rootPath = servletContext.getRealPath("/");
	return rootPath;
    }
}
