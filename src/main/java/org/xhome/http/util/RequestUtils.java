package org.xhome.http.util;

import javax.servlet.http.HttpServletRequest;

import org.xhome.common.constant.Agent;
import org.xhome.util.StringUtils;

/**
 * @project xhome-http
 * @author jhat
 * @email cpf624@126.com
 * @date Aug 13, 201311:02:00 PM
 * @description 
 */
public class RequestUtils {
	
	/**
	 * 获取浏览器类型
	 * @param request
	 * @return
	 */
	public static short getRequestAgent(HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent");
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
