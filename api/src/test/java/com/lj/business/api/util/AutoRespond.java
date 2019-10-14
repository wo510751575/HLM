package com.lj.business.api.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class AutoRespond {

	private static final String URL = "http://talker180428.oicp.net:2308/search?q=";
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		String question = "今天星期几";
		String chat = chat(question);
		System.out.println("自动回复:"+chat);

	}
	
	public static String chat(String ask) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet(URL+ask);
		CloseableHttpResponse response = httpClient.execute(get);
		StringBuilder sb = new StringBuilder();
		if(response!=null){
			HttpEntity entity = response.getEntity();
			InputStream inputStream = entity.getContent();
			byte[] b = new byte[1024];
			int size = 0;
			while((size=inputStream.read(b))>0){
				String str = new String(b, 0, size, "gb2312");
				sb.append(str);
			}
			inputStream.close();
			
		}
		return sb.toString();
		
	}

}
