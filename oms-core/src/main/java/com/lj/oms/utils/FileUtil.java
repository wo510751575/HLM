package com.lj.oms.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.lj.base.core.util.GUID;


public class FileUtil {
	
	/**
	 * 分页查询LIMIT
	 */
	public static final int LIMIT = 500;
	
	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	private static Map<String, String> extMap = new HashMap<String, String>();
	static {
		extMap.put("image", "jpg,jpeg,png,bmp,gif");
//		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
		extMap.put("audio", "amr,pcm,mp4,avi,Ogg,ogg");
	}

	/**
	 * 
	 *
	 * 方法说明：获取文件类型
	 *
	 * @param fileName
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月31日
	 *
	 */
	public static String getFileType(String fileName) {
		Iterator<Map.Entry<String, String>> it = extMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			if (Arrays.<String> asList(entry.getValue().split(",")).contains(FilenameUtils.getExtension(fileName.toLowerCase()))) {// 检查扩展名
				return entry.getKey();
			}
		}
		return null;
	}
	
	/**
	 * 
	 *
	 * 方法说明：保存图片并压缩
	 *
	 * @param filePath
	 * @param postfix
	 * @param fileNamePre
	 * @param fileInputName
	 * @param fileInput
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 *
	 * @author 彭阳 CreateDate: 2017年8月1日
	 *
	 */
	public static String thumbnailFile(String filePath, MultipartFile fileTemp)
			throws IOException {
		String fileName = fileTemp.getOriginalFilename();
		logger.debug("【添加图片】上传文件，filePath : " + filePath + ",fileName:" + fileName);
		String postfix = fileName.indexOf(".") == -1 ? "" : fileName.substring(fileName.indexOf("."));
		String fileNamePre = GUID.generateByUUID();
		String fileInputName = fileNamePre + postfix;
		logger.debug("【添加图片】保存到本地，fileInputName:" + fileInputName);
		/*File fileInput = new File(filePath + fileInputName);
		if (fileInput.getParentFile() != null && fileInput.getParentFile().exists() == false) {
			if (fileInput.getParentFile().mkdirs() == false) {
				throw new IOException("Destination '" + fileInput + "' directory cannot be created");
			}
		}*/
		//fileTemp.transferTo(fileInput);
		//图片缩略，转存
		BufferedImage sourceImg =ImageIO.read(fileTemp.getInputStream());
		int width = sourceImg.getWidth();
		logger.debug("width :" +width);
		int height = sourceImg.getHeight();
		logger.debug("Height :" +height);
		int size = height;
		if(width < height){
			size = width;
		}
		String thumbnailFileName = fileNamePre + "_l" + postfix ;
		logger.debug("thumbnailFileName :" + thumbnailFileName);
		Thumbnails.of(fileTemp.getInputStream()).sourceRegion(Positions.CENTER, size, size)
		.size(size, size)
		.toFile(new File(filePath + thumbnailFileName));
		return thumbnailFileName;
	}
	
	/**
	 * 
	 *
	 * 方法说明：保存图片
	 *
	 * @param filePath
	 * @param postfix
	 * @param fileNamePre
	 * @param fileInputName
	 * @param fileInput
	 * @return 图片名称
	 * @throws IOException
	 * @throws FileNotFoundException
	 *
	 * @author 彭阳 CreateDate: 2017年8月1日
	 *
	 */
	public static String saveFile(String filePath, MultipartFile fileTemp)
			throws IOException {
		String fileName = fileTemp.getOriginalFilename();
		logger.debug("上传文件，filePath : " + filePath + ",fileName:" + fileName);
		String postfix = fileName.indexOf(".") == -1 ? "" : fileName.substring(fileName.indexOf("."));
		String fileInputName = GUID.generateByUUID() + postfix;
		logger.debug("保存到本地，fileInputName:" + fileInputName);
		File fileInput = new File(filePath + fileInputName);
		if (fileInput.getParentFile() != null && fileInput.getParentFile().exists() == false) {
			if (fileInput.getParentFile().mkdirs() == false) {
				throw new IOException("Destination '" + fileInput + "' directory cannot be created");
			}
		}
		fileTemp.transferTo(fileInput);
		return fileInputName;
	}

}
