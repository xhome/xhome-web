package org.xhome.web.freemarker;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Component;

import freemarker.cache.TemplateCache;
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
public class ExtendsDirectiveModel implements TemplateDirectiveModel {

	public final static String DIRECTIVE_NAME = "extends";

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {

		String name = FreeMarkertTemplateUtils.getRequiredParam(params, "name");
		String encoding = FreeMarkertTemplateUtils.getParam(params, "encoding",
				null);
		String includeTemplateName = TemplateCache.getFullTemplatePath(env,
				getTemplatePath(env), name);
		env.include(includeTemplateName, encoding, true);
	}

	private String getTemplatePath(Environment env) {
		String templateName = env.getTemplate().getName();
		return templateName.lastIndexOf('/') == -1 ? "" : templateName
				.substring(0, templateName.lastIndexOf('/') + 1);
	}

}
