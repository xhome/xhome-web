package org.xhome.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @project xhome-web
 * @author jhat
 * @email cpf624@126.com
 * @date Aug 13, 201311:01:17 PM
 * @description
 */
public class SessionUtils {

    /**
     * 获取Session变量（不移除）
     * 
     * @param request
     * @param name
     * @return
     */
    public static Object getSessionAttribute(HttpServletRequest request,
                    String name) {
        return request != null ? getSessionAttribute(request, name, false)
                        : null;
    }

    /**
     * 获取Session变量（可移除）
     * 
     * @param request
     * @param name
     * @param remove
     *            true: 移除 false: 不移除
     * @return
     */
    public static Object getSessionAttribute(HttpServletRequest request,
                    String name, boolean remove) {
        if (request == null) {
            return null;
        }
        HttpSession session = request.getSession();
        Object result = session.getAttribute(name);
        if (remove) {
            session.removeAttribute(name);
        }
        return result;
    }

    /**
     * 设置Session变量
     * 
     * @param request
     * @param name
     * @param value
     */
    public static void setSessionAttribute(HttpServletRequest request,
                    String name, Object value) {
        if (request != null) {
            HttpSession session = request.getSession();
            if (value != null) {
                session.setAttribute(name, value);
            } else {
                session.removeAttribute(name);
            }
        }
    }

    /**
     * 移除Session变量
     * 
     * @param request
     * @param name
     */
    public static void removeSessionAttribute(HttpServletRequest request,
                    String name) {
        if (request != null) {
            HttpSession session = request.getSession();
            session.removeAttribute(name);
        }
    }

}
