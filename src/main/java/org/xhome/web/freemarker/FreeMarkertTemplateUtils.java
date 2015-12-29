package org.xhome.web.freemarker;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.xhome.common.util.StringUtils;
import org.xhome.web.freemarker.OverrideDirectiveModel.TemplateDirectiveBodyOverrideWraper;

import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;

/**
 * @project xauth-web
 * @author jhat
 * @email cpf624@126.com
 * @homepage http://pfchen.org
 * @date Feb 12, 2014
 * @describe 模板工具类
 */
public class FreeMarkertTemplateUtils {

	public final static String BLOCK = "__ftl_override__";
	public final static String OVERRIDE_CURRENT_NODE = "__ftl_override_current_node";

	private FreeMarkertTemplateUtils() {
	}

	/**
	 * @param templatePath
	 *            模板文件存放目录
	 * @param templateName
	 *            模板文件名称
	 * @param root
	 *            数据模型根对象
	 * @param templateEncoding
	 *            模板文件的编码方式
	 * @param out
	 *            输出流
	 */
	public static void processTemplate(String templatePath,
			String templateName, String templateEncoding, Map<?, ?> root,
			Writer out) {
		try {
			Configuration config = new Configuration();
			File file = new File(templatePath);
			// 设置要解析的模板所在的目录，并加载模板文件
			config.setDirectoryForTemplateLoading(file);
			// 设置包装器，并将对象包装为数据模型
			config.setObjectWrapper(new DefaultObjectWrapper());

			// 获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
			Template template = config.getTemplate(templateName,
					templateEncoding);
			// 合并数据模型与模板

			template.process(root, out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	public static void processTemplate(String templatePath,
			String templateName, Map<?, ?> root, Writer out) {
		processTemplate(templatePath, templateName, "UTF-8", root, out);
	}

	public static String getOverrideVariableName(String name) {
		return BLOCK + name;
	}

	@SuppressWarnings("rawtypes")
	public static String getRequiredParam(Map params, String key)
			throws TemplateException {
		Object value = params.get(key);
		if (value == null || StringUtils.isEmpty(value.toString())) {
			throw new TemplateModelException("not found required parameter:"
					+ key + " for directive");
		}
		return value.toString();
	}

	@SuppressWarnings("rawtypes")
	public static String getParam(Map params, String key, String defaultValue)
			throws TemplateException {
		Object value = params.get(key);
		return value == null ? defaultValue : value.toString();
	}

	public static TemplateDirectiveBodyOverrideWraper getOverrideBody(
			Environment env, String name) throws TemplateModelException {
		TemplateDirectiveBodyOverrideWraper value = (TemplateDirectiveBodyOverrideWraper) env
				.getVariable(getOverrideVariableName(name));
		return value;
	}

	public static void setTopBodyForParentBody(Environment env,
			TemplateDirectiveBodyOverrideWraper topBody,
			TemplateDirectiveBodyOverrideWraper overrideBody) {
		TemplateDirectiveBodyOverrideWraper parent = overrideBody;
		while (parent.parentBody != null) {
			parent = parent.parentBody;
		}
		parent.parentBody = topBody;
	}

	public static void eaxposeAllMacros(Configuration conf) {
		conf.setSharedVariable(IncludeDirectiveModel.DIRECTIVE_NAME,
				new IncludeDirectiveModel());
		conf.setSharedVariable(BlockDirectiveModel.DIRECTIVE_NAME,
				new BlockDirectiveModel());
		conf.setSharedVariable(ExtendsDirectiveModel.DIRECTIVE_NAME,
				new ExtendsDirectiveModel());
		conf.setSharedVariable(OverrideDirectiveModel.DIRECTIVE_NAME,
				new OverrideDirectiveModel());
		conf.setSharedVariable(SuperDirectiveModel.DIRECTIVE_NAME,
				new SuperDirectiveModel());
	}

}
