package org.xhome.web.freemarker;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.springframework.stereotype.Component;

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
public class OverrideDirectiveModel implements TemplateDirectiveModel {

	public final static String DIRECTIVE_NAME = "override";

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		String name = FreeMarkertTemplateUtils.getRequiredParam(params, "name");
		String overrideVariableName = FreeMarkertTemplateUtils
				.getOverrideVariableName(name);

		TemplateDirectiveBodyOverrideWraper override = FreeMarkertTemplateUtils
				.getOverrideBody(env, name);
		TemplateDirectiveBodyOverrideWraper current = new TemplateDirectiveBodyOverrideWraper(
				body, env);
		if (override == null) {
			env.setVariable(overrideVariableName, current);
		} else {
			FreeMarkertTemplateUtils.setTopBodyForParentBody(env, current,
					override);
		}
	}

	static class TemplateDirectiveBodyOverrideWraper implements
			TemplateDirectiveBody, TemplateModel {
		private TemplateDirectiveBody body;
		public TemplateDirectiveBodyOverrideWraper parentBody;
		public Environment env;

		public TemplateDirectiveBodyOverrideWraper(TemplateDirectiveBody body,
				Environment env) {
			super();
			this.body = body;
			this.env = env;
		}

		public void render(Writer out) throws TemplateException, IOException {
			if (body == null)
				return;
			TemplateDirectiveBodyOverrideWraper preOverridy = (TemplateDirectiveBodyOverrideWraper) env
					.getVariable(FreeMarkertTemplateUtils.OVERRIDE_CURRENT_NODE);
			try {
				env.setVariable(FreeMarkertTemplateUtils.OVERRIDE_CURRENT_NODE,
						this);
				body.render(out);
			} finally {
				env.setVariable(FreeMarkertTemplateUtils.OVERRIDE_CURRENT_NODE,
						preOverridy);
			}
		}
	}

}
