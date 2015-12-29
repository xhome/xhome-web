package org.xhome.web.freemarker;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.xhome.web.freemarker.OverrideDirectiveModel.TemplateDirectiveBodyOverrideWraper;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @project xhome-web
 * @author jhat
 * @email cpf624@126.com
 * @home_page https://jhat.pw
 * @date Dec 29, 2015
 * @description
 */
@Component
public class SuperDirectiveModel implements TemplateDirectiveModel {

	public final static String DIRECTIVE_NAME = "super";

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {

		TemplateDirectiveBodyOverrideWraper current = (TemplateDirectiveBodyOverrideWraper) env
				.getVariable(FreeMarkertTemplateUtils.OVERRIDE_CURRENT_NODE);
		if (current == null) {
			throw new TemplateException(
					"<@super/> direction must be child of override", env);
		}
		TemplateDirectiveBody parent = current.parentBody;
		if (parent == null) {
			throw new TemplateException("not found parent for <@super/>", env);
		}
		parent.render(env.getOut());

	}

}
