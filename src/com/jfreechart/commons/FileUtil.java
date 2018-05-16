package com.jfreechart.commons;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class FileUtil {

    /**
     * 获得web项目根目录
     */
    public static String getWebRootPath() throws Exception {
	ActionContext actionContext = ActionContext.getContext();
	ServletContext servletContext = (ServletContext) actionContext.get(ServletActionContext.SERVLET_CONTEXT);
	String rootPath = servletContext.getRealPath("/");
	return rootPath;
    }
}
