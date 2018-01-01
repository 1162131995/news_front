package cn.et.model;

import java.util.List;
import java.util.Map;

public class News {
	/**
	 * 
	 * 插入新闻
	 * @param title：标题
	 * @param content：内容
	 * @param htmlPath：生成的html
	 * @param createTime：创建时间
	 */
	public void insertNews(String title, String content, String htmlPath, String createTime){
		String sql = "INSERT INTO news(title,content,htmlpath,createtime) VALUES('"+title+"','"+content+"','"+htmlPath+"','"+createTime+"')";
		DbUtils.execute(sql);
	}
	
	/**
	 * 获取新闻
	 * @return
	 */
	public List<Map> queryNews(){ 
		String sql = "select * from news";
		List<Map> list = DbUtils.query(sql);
		return list;
	}
}
