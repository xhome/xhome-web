package org.xhome.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @project xhome-web
 * @author jhat
 * @email cpf624@126.com
 * @homepage http://pfchen.org
 * @date Jan 31, 2014
 * @describe
 */
@Controller
public class CommonAction extends AbstractAction {

	public final static String RM_DASHBOARD = "dashboard"; // 后台管理
	public final static String RM_INDEX = "index"; // 首页
	public final static String RM_ABOUT = "about"; // 关于页面

	public final static String RM_401 = "401"; // 无权访问
	public final static String RM_404 = "404"; // 页面不存在
	public final static String RM_503 = "503"; // 无法访问
	public final static String RM_ERROR = "error"; // 系统异常

	/**
	 * 用户后台管理界面
	 * 
	 * @return
	 */
	@RequestMapping(value = RM_DASHBOARD, method = RequestMethod.GET)
	public Object dashboardPage() {
		return RM_DASHBOARD;
	}

	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping(value = RM_INDEX, method = RequestMethod.GET)
	public Object indexPage() {
		return RM_INDEX;
	}

	/**
	 * 关于页面
	 * 
	 * @return
	 */
	@RequestMapping(value = RM_ABOUT, method = RequestMethod.GET)
	public Object aboutPage() {
		return RM_ABOUT;
	}

	/**
	 * 无权访问提示页面
	 * 
	 * @return
	 */
	@RequestMapping(value = RM_401, method = RequestMethod.GET)
	public Object page401() {
		return RM_401;
	}

	/**
	 * 页面不存在提示页面
	 * 
	 * @return
	 */
	@RequestMapping(value = RM_404, method = RequestMethod.GET)
	public Object page404() {
		return RM_404;
	}

	/**
	 * 无法访问提示页面
	 * 
	 * @return
	 */
	@RequestMapping(value = RM_503, method = RequestMethod.GET)
	public Object page503() {
		return RM_503;
	}

	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping(value = RM_ERROR, method = RequestMethod.GET)
	public Object errorPage() {
		return RM_ERROR;
	}

}
