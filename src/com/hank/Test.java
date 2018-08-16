package com.hank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class Test {
	public static void main(String[] args) {
		try {
			// 1.发起post请求，获取需要的html文件
			String response = getHTML();
			// 2.解析html文件，将需要的数据塞入到java类型的变量中
			// 此处的例子是解析本文件相同目录下的test.html中的html片段
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 发起post请求，获取需要的html文档
	 * @return
	 * @throws Exception
	 */
	public static String getHTML() throws Exception{
		// 1.构建post所提交的数据
		Map<String, String> postData = new HashMap<String, String>();
		postData.put("key1","value1"); // 根据实际情况添加这里的数据
		// 2.建立链接
		Connection connection = Jsoup.connect("http://www.test.com");// 根据实际情况填写url
		// 3.发起请求
		Response response = connection.method(Method.POST).data(postData).execute();
		// 4.打印执行结果
		System.out.println(response.body());
		return response.body();
	}
	
	private List<HashMap<String, String>> resolveData(String response){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> hashMap = null;
		// 1.先解析成document对象
		Document doucuDocument = Jsoup.parse(response);
		// 2.利用select获取表单
		Element table = doucuDocument.select("dataList").first();
		// 3.获取表单中的<tr>标签
		Elements trs = table.getElementsByTag("tr");
		for (Element tr:trs) {
			// 4.获取tr下的td
			Elements tds = tr.getElementsByTag("td");
			if (tds.size() != 0) { // 剔除只包含有th的tr标签
				hashMap = new HashMap<String, String>();
				hashMap.put("phone",tds.get(0).text());
				hashMap.put("content",tds.get(1).text());
				list.add(hashMap);
			}
		}
		return list;
	}
	
}
