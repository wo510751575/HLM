package com.lj.oms.utils.audioUtil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ws.schild.jave.AudioAttributes;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.EncodingAttributes;
import ws.schild.jave.InputFormatException;
import ws.schild.jave.MultimediaObject;



/**
 * 
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * 
 * @author 彭阳
 *   
 * CreateDate: 2012-6-9
 */
public class AmrToMp3Util {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(AmrToMp3Util.class);


	public static void main(String[] args){
		File source = new File("D:\\607546490502201881.amr"); 
//			changeTemp();
		encoder(source);
	} 

	// read an input-stream into a String   
	static String loadStream(InputStream in) throws IOException {   
		int ptr = 0;   
		in = new BufferedInputStream(in);   
		StringBuffer buffer = new StringBuffer();   
		while( (ptr = in.read()) != -1 ) {   
			buffer.append((char)ptr);   
		}   
		return buffer.toString();   
	}   

	
	/**
	 * 测试案例
	 * @throws InputFormatException
	 */
	public static void changeTemp() throws InputFormatException {
	    File target = new File("D:\\607546490502201881.mp3");   
	    File source = new File("D:\\607546490502201881.amr"); 
	    AudioAttributes audio = new AudioAttributes();
	    audio.setCodec("libmp3lame");
	    EncodingAttributes attrs = new EncodingAttributes();
	    attrs.setFormat("mp3");
	    attrs.setAudioAttributes(audio);
	    Encoder encoder = new Encoder();
	    MultimediaObject multimediaObject = new MultimediaObject(source);
	    try {
	      encoder.encode(multimediaObject, target, attrs);
	    } catch (IllegalArgumentException | EncoderException e) {
	      e.printStackTrace();
	    }
	 
	  }
	
	/**
	 * 
	 * @param source 原amr文件
	 * @return
	 */
	public static String encoder(File source){
		logger.debug("encoder(String source={}) - start", source.getPath()); 
		String fileName = source.getName();
		if(fileName.toLowerCase().lastIndexOf(".amr") == -1){
			throw new IllegalArgumentException("请填入AMR文件!");
		}
		String fileNamePre  = fileName.substring(0, fileName.indexOf("."));
		String fileNameMp3 = fileNamePre +".mp3";
		try {    
			long start=System.currentTimeMillis();
			File target = new File(source.getParentFile().getPath()+"/"+fileNameMp3);  
			//目标已存在，直接返回
			if(target.exists()) {
				return fileNameMp3;
			}
		    AudioAttributes audio = new AudioAttributes();
		    audio.setCodec("libmp3lame");
		    EncodingAttributes attrs = new EncodingAttributes();
		    attrs.setFormat("mp3");
		    attrs.setAudioAttributes(audio);
		    Encoder encoder = new Encoder();
		    MultimediaObject multimediaObject = new MultimediaObject(source);
		    encoder.encode(multimediaObject, target, attrs);
		    
			long end=System.currentTimeMillis();    
			logger.debug(" amr 转 mp3 success, costs:"+(end-start)+"ms");  
		} catch (EncoderException e) {
			logger.error("encoder(String)	e={}", e); 
		}  
		logger.debug("encoder(String) - end"); 
		return fileNameMp3;
	}
	
}
