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
public class BlockDirectiveModel implements TemplateDirectiveModel {

	public final static String DIRECTIVE_NAME = "block";

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		String name = FreeMarkertTemplateUtils.getRequiredParam(params, "name");
		TemplateDirectiveBodyOverrideWraper overrideBody = FreeMarkertTemplateUtils
				.getOverrideBody(env, name);
		if (overrideBody == null) {
			if (body != null) {
				body.render(env.getOut());
			}
		} else {
			FreeMarkertTemplateUtils.setTopBodyForParentBody(env,
					new TemplateDirectiveBodyOverrideWraper(body, env),
					overrideBody);
			overrideBody.render(env.getOut());
		}
	}

}
