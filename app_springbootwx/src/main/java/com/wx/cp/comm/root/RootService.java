package com.wx.cp.comm.root;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName BaseService
 * @Description TODO service根节点
 * @createdate 2019/5/15 星期三 11:14
 */
public abstract class RootService {
    @Autowired
    private HttpSession session;
    /**
     * 获取session中的值
     * @param name
     * @return
     */
    protected Object getSessionAttribute(String name){
        return session.getAttribute(name);
    }
    /**
     * 将值存入session中
     * @param name
     * @param object
     */
    protected void getSessionAttribute(String name,Object object){
        session.setAttribute(name,object);
    }
}
