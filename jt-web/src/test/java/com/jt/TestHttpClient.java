package com.jt;




import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TestHttpClient {
	@Test
	public void testGet() throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		String url = "https://item.jd.com/1221882.html";
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse response = client.execute(get);
		//判断响应是否正确
		if(response.getStatusLine().getStatusCode()==200) {
			//从中获取响应数据json
			String result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
		}else {
			System.out.println("请求失败");
		}
		
		
	}

}
