package cn.et.contorller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.UUID;

import cn.et.model.News;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class MyTimerTask extends TimerTask {
    News news = new News();
	@Override
	public void run() {
		List<Map> list = news.queryNews();
		try {
		//Éú³Éhtml
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File("src/main/resources"));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		
		Map root = new HashMap();
		root.put("tableList", list);
		
		Template template = cfg.getTemplate("/index.ftl");
		String ftlFilePath = "src/main/webapp/index.html";
		Writer out = new OutputStreamWriter(new FileOutputStream(ftlFilePath));
		
		template.process(root, out);
		out.flush();
		out.close();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
