package com.lj.oms.common;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lj.base.core.util.GUID;
import com.lj.business.cm.dto.AddMaterialPhoto;
import com.lj.business.cm.dto.AddMaterialVideo;
import com.lj.business.cm.service.IMaterialPhotoService;
import com.lj.business.cm.service.IMaterialVideoService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.oms.utils.FileUtils;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：文件工具处理层
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 * 
 * CreateDate: 2017年7月14日
 */
@Controller
@RequestMapping({ "${adminPath}/file" })
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	private static ObjectMapper objectMapper = new ObjectMapper();

	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	@Resource
	private IMaterialVideoService materialVideoService;
	@Resource
	private IMaterialPhotoService materialPhotoService;

	/**
	 * 
	 *
	 * 方法说明：上传文件公共处理
	 *
	 * @param fileType
	 * @param dirName
	 * @param width
	 * @param height
	 * @param file
	 * @param response
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 *
	 * @author 段志鹏 CreateDate: 2017年7月14日
	 *
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = { "/upload" }, method = RequestMethod.POST)
	public void upload(String fileType, String dirName, Integer width, Integer height, MultipartFile file, HttpServletResponse response)
			throws JsonGenerationException, JsonMappingException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		HashMap localHashMap = new HashMap();
		if (file == null || !FileUtils.fileTypeVaild(fileType, file.getOriginalFilename()) || !FileUtils.fileSizeVaild(file)) {
			objectMapper.writeValue(response.getWriter(), "上传文件格式或大小不正确！");
		} else {
			String str = null;
//	      if("image".equals(fileType)&&(width!=null || height!=null) ){
			if ("image".equals(fileType)) {
				str = FileUtils.imgScale(dirName, file, height, width, false);
			} else {
				str = FileUtils.upload(fileType, dirName, file);
				//video，增加返回第一帧图片地址
				if("video".equals(fileType)) {
					String firstView = getFirstView(dirName, file);
					localHashMap.put("firstView", firstView);
				}	
			}
			if (str == null) {
				objectMapper.writeValue(response.getWriter(), "上传失败！");
			}
			localHashMap.put("url", str);
			localHashMap.put("message", "上传成功");
			objectMapper.writeValue(response.getWriter(), localHashMap);
		}
	}

	/**
	 * 获取视频第一帧
	 * @param dirName
	 * @param file
	 * @return
	 */
	private String getFirstView(String dirName, MultipartFile file) {
		InputStream inputStream = null;
		try {
			inputStream = file.getInputStream();
			if (inputStream != null) {
				AddMaterialVideo record = new AddMaterialVideo();
				// 写入视频时长,截取图片
				readVideo(inputStream, dirName, record);
				//上传视频成功,返回第一帧画面
				return record.getFirstView();
			}
		} catch (IOException e) {
			logger.error("视频转换错误：", e);
		}
		return null;
	}
	
	@RequestMapping(value = { "/batchUpload" }, method = RequestMethod.POST)
	public void batchUpload(String parentId, String fileType, String dirName, Integer width, Integer height,
			List<MultipartFile> files, HttpServletResponse response,Model model)
			throws JsonGenerationException, JsonMappingException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		String str = null;

		for (MultipartFile file : files) {
			if (file == null || !FileUtils.fileTypeVaild(fileType, file.getOriginalFilename()) || !FileUtils.fileSizeVaild(file)) {
				objectMapper.writeValue(response.getWriter(), "上传文件格式或大小不正确！");
			} else {
				if ("image".equals(fileType)) {
					// 添加图片
					str = addPhoto(dirName, width, height, parentId, file);
				} else {
					str = addVideo(fileType, dirName, parentId, file);
				}
			}
		}
		if (str == null) {
			objectMapper.writeValue(response.getWriter(), "上传失败！");
		}
		HashMap<String, String> localHashMap = new HashMap<>();
		localHashMap.put("url", str);
		localHashMap.put("message", "上传成功");
		objectMapper.writeValue(response.getWriter(), localHashMap);
		model.addAttribute("repMsg", "上传成功");
		
	}

	/**
	 * 
	 * *方法说明：
	 *
	 * @param dirName 保存路径
	 * @param width 图片宽度
	 * @param height 图片高度
	 * @param parentId 父级code
	 * @param file 文件
	 * @return
	 * @author sjiying
	 * @throws IOException 
	 * @CreateDate 2019年4月25日
	 */
	private String addPhoto(String dirName, Integer width, Integer height, String parentId, MultipartFile file) throws IOException {
		// 保存原图
		String rs = FileUtils.imgScaleIm(dirName, file, height, width, false);
		String small = FileUtils.imgScaleIm(dirName, file, 400, 400, false); // 压缩图片宽高
		double lengthPhoto = 0;
		double widthPhoto = 0;
		try {
			double ratio = 1.0; // 缩放比例
			BufferedImage bi = ImageIO.read(file.getInputStream());
			int widthTemp = bi.getWidth();
			int heightTemp = bi.getHeight();
			if(widthTemp> 400 || heightTemp > 400){
				if (widthTemp > 400) {
					ratio = new Integer(400).doubleValue() / widthTemp;
				} else if (heightTemp > widthTemp) {
					ratio = new Integer(400).doubleValue() / heightTemp;
				} else {
					ratio = new Integer(400).doubleValue() / widthTemp;
				}
			}
			lengthPhoto = heightTemp*ratio;
			widthPhoto = widthTemp*ratio;
			
		} catch (Exception e) {
			
		}
		

		AddMaterialPhoto record = new AddMaterialPhoto();
		record.setLength((int) lengthPhoto);
		record.setWidth((int) widthPhoto);
		record.setCode(GUID.generateByUUID());
		record.setUpdateTime(new Date());
		record.setMerchantNo(UserUtils.getMerchantNo());

		if (StringUtils.isEmpty(parentId) || "0".equals(parentId)) {
			record.setParentId("0");
			record.setParentIds(String.format("0,%s", record.getCode()));
		} else {
			record.setParentId(parentId);
			String parentIds = materialPhotoService.selectParentIds(parentId);
			int count = materialPhotoService.getCount(parentId);
			AddMaterialPhoto parent = new AddMaterialPhoto();
			parent.setCode(parentId);
			parent.setCount(count+1);
			materialPhotoService.updateCount(parent);
			if(StringUtils.isEmpty(parentIds)){
				parentIds = "0";
			}
			record.setParentIds(String.format("%s,%s", parentIds, parentId));
		}

		record.setPhotoName(file.getOriginalFilename());
		record.setSize(Double.parseDouble((file.getSize() / 1024) + ""));
		record.setPhotoLocation((rs));
		record.setSmallPhotoLocation((small));
		materialPhotoService.addMaterialPhoto(record);

		return rs;
	}

	/**
	 * 
	 * *方法说明：
	 *
	 * @param fileType 视频类型：video
	 * @param dirName 保存路径
	 * @param parentId 父级code
	 * @param file 文件
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年4月25日
	 */
	private String addVideo(String fileType, String dirName, String parentId, MultipartFile file) {
		InputStream inputStream = null;
		try {
			inputStream = file.getInputStream();
		} catch (IOException e) {
			logger.error("获取文件流失败：", e);
		}

		String rs ="im"+ FileUtils.uploadIm(fileType, dirName, file);

		AddMaterialVideo record = new AddMaterialVideo();
		record.setCode(GUID.generateByUUID());
		record.setUpdateTime(new Date());
		record.setMerchantNo(UserUtils.getMerchantNo());

		if (StringUtils.isEmpty(parentId) || "0".equals(parentId)) {
			record.setParentId("0");
			record.setParentIds(String.format("0,%s", record.getCode()));
		} else {
			record.setParentId(parentId);
			String parentIds = materialVideoService.selectParentIds(parentId);
			int count = materialVideoService.getCount(parentId);
			AddMaterialVideo parent = new AddMaterialVideo();
			parent.setCode(parentId);
			parent.setCount(count+1);
			materialVideoService.updateCount(parent);
			if(StringUtils.isEmpty(parentIds)){
				parentIds = "0";
			}
			record.setParentIds(String.format("%s,%s", parentIds, parentId));
		}
		record.setVideoName(file.getOriginalFilename());
		record.setSize(Double.parseDouble((file.getSize() / 1024) + ""));
		record.setVideoLocation((rs));

		try {
			if (inputStream != null) {
				// 写入视频时长,截取图片
				readVideo(inputStream, dirName, record);
			}
		} catch (IOException e) {
			logger.error("视频转换错误：", e);
		}

		materialVideoService.addMaterialVideo(record);
		//上传视频成功,返回第一帧画面
		String firstView = record.getFirstView();
		return firstView;
	}

	/**
	 * 
	 * *方法说明：获取视频时长，截取第一帧图片保存
	 *
	 * @param inputStream
	 * @param dirName
	 * @param record
	 * @throws Exception
	 * @author sjiying
	 * @CreateDate 2019年4月26日
	 */
	@SuppressWarnings("resource")
	private void readVideo(InputStream inputStream, String dirName, AddMaterialVideo record) throws Exception {
		FFmpegFrameGrabber ff = new FFmpegFrameGrabber(inputStream);
		ff.start();

		long ls = ff.getLengthInTime() / 1000 / 1000;
		long hour = ls / 3600; // 小时
		long minute = ls % 3600 / 60; // 分钟
		long second = ls - hour * 3600 - minute * 60;
		String videoLength = String.format("%d:%d:%d", hour, minute, second);
		record.setVideoLength(videoLength);

		// 直接去第一帧
		Frame frame = null;// ff.grabFrame();
		int len = ff.getLengthInFrames();
		int i = 0;
		while (i < len) {
			frame = ff.grabFrame();
			if (frame.image != null) {
				break;
			}
			i++;
		}

		// 截取的帧图片
		Java2DFrameConverter converter = new Java2DFrameConverter();
		BufferedImage srcImage = converter.getBufferedImage(frame);
		int width = srcImage.getWidth();
		int height = srcImage.getHeight();
		record.setWidth(width);
		record.setLength(height);
		String firstView = FileUtils.imgScaleIm(dirName, srcImage, null, null);
		record.setFirstView(firstView);

		ff.stop();
	}
	
	/**
	 * 
	 * *方法说明：文件上传，图片，视频，不做任何处理。
	 *
	 * @param fileType
	 * @param dirName
	 * @param file
	 * @param response
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @author sjiying
	 * @CreateDate 2019年5月10日
	 */
	@RequestMapping(value = { "/uploadNotLimit" }, method = RequestMethod.POST)
	public void uploadNotLimit(String fileType, String dirName, MultipartFile file, HttpServletResponse response)
			throws JsonGenerationException, JsonMappingException, IOException {

		response.setContentType("text/html; charset=UTF-8");

		long maxSize = 50485760;// 50M文件大小

		// 文件为空，后缀格式，文件大小限制
		if (file == null || !FileUtils.fileTypeVaild(fileType, file.getOriginalFilename()) || file.getSize() > maxSize) {
			objectMapper.writeValue(response.getWriter(), "上传文件格式或大小不正确！");
			return;
		}

		String rs = FileUtils.upload(fileType, dirName, file);

		if (rs == null) {
			objectMapper.writeValue(response.getWriter(), "上传失败！");
		}

		HashMap<String, String> localHashMap = new HashMap<>();

		localHashMap.put("url", rs);
		localHashMap.put("message", "上传成功");

		objectMapper.writeValue(response.getWriter(), localHashMap);
	}

}
