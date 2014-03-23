package org.xhome.web.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @project xhome-web
 * @author jhat
 * @email cpf624@126.com
 * @homepage http://pfchen.org
 * @date Mar 17, 2014
 * @describe ServletContext工具类
 */
public class ServletContextUtils {

    /**
     * 设置ServletContext变量
     * 
     * @param session
     * @param name
     * @param value
     */
    public static void setAttribute(HttpSession session, String name,
                    Object value) {
        if (session != null) {
            ServletContext context = session.getServletContext();
            if (value != null) {
                context.setAttribute(name, value);
            } else {
                context.removeAttribute(name);
            }
        }
    }

    /**
     * 设置ServletContext变量
     * 
     * @param request
     * @param name
     * @param value
     */
    public static void setAttribute(HttpServletRequest request, String name,
                    Object value) {
        if (request != null) {
            setAttribute(request.getSession(), name, value);
        }
    }

    /**
     * 移除ServletContext变量
     * 
     * @param session
     * @param name
     */
    public static void removeAttribute(HttpSession session, String name) {
        if (session != null) {
            ServletContext context = session.getServletContext();
            context.removeAttribute(name);
        }
    }

    /**
     * 移除ServletContext变量
     * 
     * @param request
     * @param name
     */
    public static void removeAttribute(HttpServletRequest request, String name) {
        if (request != null) {
            removeAttribute(request.getSession(), name);
        }
    }

    /**
     * 获取ServletContext变量（不移除）
     * 
     * @param session
     * @param name
     * @return
     */
    public static Object getAttribute(HttpSession session, String name) {
        return getAttribute(session, name, false);
    }

    /**
     * 获取ServletContext变量（不移除）
     * 
     * @param request
     * @param name
     * @return
     */
    public static Object getAttribute(HttpServletRequest request, String name) {
        return getAttribute(request, name, false);
    }

    /**
     * 获取ServletContext变量（可移除）
     * 
     * @param session
     * @param name
     * @param remove
     *            true: 移除 false: 不移除
     * @return
     */
    public static Object getAttribute(HttpSession session, String name,
                    boolean remove) {
        if (session == null) {
            return null;
        }
        ServletContext context = session.getServletContext();
        Object result = context.getAttribute(name);
        if (remove) {
            context.removeAttribute(name);
        }
        return result;
    }

    /**
     * 获取ServletContext变量（可移除）
     * 
     * @param request
     * @param name
     * @param remove
     *            true: 移除 false: 不移除
     * @return
     */
    public static Object getAttribute(HttpServletRequest request, String name,
                    boolean remove) {
        return request != null ? getAttribute(request.getSession(), name,
                        remove) : null;
    }

}
