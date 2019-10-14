package com.ye.business.rw.kit;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * *类说明：Http工具
 *
 * </p>
 * *详细描述：
 * 
 * @author sjiying
 * @CeateDate 2019年4月29日
 */
public class HttpKit {

	private static final String DefaultCharset = "UTF-8";

	private static Logger log = LoggerFactory.getLogger(HttpKit.class);

	/**
	 * 
	 * *方法说明：GET
	 *
	 * @param uri
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年4月29日
	 */
	public static String doGet(String uri) {

		log.info("HTTP GET 请求开始：{}", uri);

		String rs = "";

		HttpGet httpGet = new HttpGet(uri);

		try (CloseableHttpClient httpclient = HttpClients.createDefault(); CloseableHttpResponse response = httpclient.execute(httpGet);) {

			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				log.error("GET 请求失败");
				return rs;
			}

			return EntityUtils.toString(response.getEntity(), DefaultCharset);

		} catch (ClientProtocolException e) {
			log.error("GET 请求失败：", e);
		} catch (IOException e) {
			log.error("GET 网络请求异常：", e);
		}
		return rs;
	}

	/**
	 * 
	 * *方法说明：POST
	 *
	 * @param uri
	 * @param param
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年4月29日
	 */
	public static String doPost(String uri, Map<String, String> param) {

		log.info("HTTP POST 请求开始：{}，参数：{}", uri, param);

		String rs = "";

		HttpPost httpPost = new HttpPost(uri);

		if (param != null && !param.isEmpty()) {

			List<NameValuePair> nvps = new ArrayList<>();
			param.forEach((key, value) -> {
				nvps.add(new BasicNameValuePair(key, value));
			});
			try {
				httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			} catch (UnsupportedEncodingException e) {
				log.error("POST 内容转换错误：", e);
			}
		}

		try (CloseableHttpClient httpclient = HttpClients.createDefault(); CloseableHttpResponse response = httpclient.execute(httpPost);) {

			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				log.error("POST 请求失败");
				return rs;
			}

			return EntityUtils.toString(response.getEntity(), DefaultCharset);

		} catch (ClientProtocolException e) {
			log.error("请求失败：", e);
		} catch (IOException e) {
			log.error("POST 网络请求异常：", e);
		}
		return rs;
	}

}
