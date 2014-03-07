package org.xhome.web.util;

import javax.servlet.http.HttpServletRequest;

import org.xhome.common.constant.Agent;
import org.xhome.util.StringUtils;

/**
 * @project xhome-web
 * @author jhat
 * @email cpf624@126.com
 * @date Aug 13, 201311:02:00 PM
 * @description
 */
public class RequestUtils {

	/**
	 * 获取请求路径（去除ContextPath）
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestURI(HttpServletRequest request) {
		String context = request.getContextPath();
		return request.getRequestURI().substring(context.length());
	}

	/**
	 * 获取客户端名称
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestUserAgentName(HttpServletRequest request) {
		return request.getHeader("User-Agent");
	}

	/**
	 * 获取客户端类型
	 * 
	 * @param request
	 * @return
	 */
	public static short getRequestUserAgent(HttpServletRequest request) {
		String userAgent = getRequestUserAgentName(request);
		if (userAgent == null) {
			return Agent.OTHER;
		}

		userAgent = userAgent.toLowerCase();
		if (userAgent.contains("msie")) {
			return Agent.IE;
		}
		if (userAgent.contains("opera")) {
			return Agent.OPERA;
		}
		if (userAgent.contains("chrome")) {
			return Agent.CHROME;
		}
		if (userAgent.contains("safari")) {
			return Agent.SAFARI;
		}

		return Agent.OTHER;
	}

	/**
	 * 获取客户端IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestAddress(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
