package com.lj.business.api.utils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import com.lj.business.enums.SourceChannel;

public class CommonUtils {

	/**
	 * 
	 *
	 * 方法说明：获取请求来源渠道
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-7-11
	 * 
	 * @param request
	 * @return
	 */
	public static SourceChannel getSource(HttpServletRequest request) {
		String header = request.getHeader("user-agent").toLowerCase();
		if(header.indexOf("android") != -1 || header.indexOf("iphone") != -1 || header.indexOf("ipad") != -1 || header.indexOf("ipod") != -1){
			return SourceChannel.APP;
		}else {
			return SourceChannel.WEB;
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：移动客户端操作系统
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-4-28
	 * 
	 * @param request
	 * @return
	 */
	public static String getMobileSystem(HttpServletRequest request) {
		String header = request.getHeader("user-agent").toLowerCase();
		if(header.indexOf("iphone") != -1 || header.indexOf("ipad") != -1 || header.indexOf("ipod") != -1){
			return "IOS";
		}else {
			return "ANDROID";
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：下载文档，如图片等
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-4-7
	 * 
	 * @param filePath
	 * @param response
	 * @throws Throwable 
	 */
	public static void download(String filePath, HttpServletResponse response) throws Throwable {
		OutputStream os = null;
		try {  
			os = response.getOutputStream();  
			response.reset();  
			response.setContentType("image/png");	// 设置相应类型,告诉浏览器输出的内容为图片
			os.write(FileUtils.readFileToByteArray(new File(filePath))) ; 
			os.flush();  
		} catch(Throwable e){
			throw e;
		} finally {  
			if (os != null) { // 关闭输出流 
				try {
					os.close();
				} catch (IOException e) {
					throw e;
				}  
			}  
		}
	}
}
