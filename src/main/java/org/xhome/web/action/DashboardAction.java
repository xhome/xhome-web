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
public class DashboardAction extends AbstractAction {

	public final static String RM_DASHBOARD = "dashboard";

	/**
	 * 用户后台管理界面
	 * 
	 * @return
	 */
	@RequestMapping(value = RM_DASHBOARD, method = RequestMethod.GET)
	public Object dashboard() {
		return RM_DASHBOARD;
	}

}
