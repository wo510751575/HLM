
package com.lj.oms.cm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lj.oms.common.BaseController;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.constant.ErrorCode;
import com.lj.business.cm.dto.FriendsImageMaterialDto;
import com.lj.business.cm.dto.MaterialVariableDto;
import com.lj.business.cm.service.IFriendsImageMaterialService;
import com.lj.business.cm.service.IMaterialVariableService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;
import com.lj.oms.utils.FileUtil;
import com.lj.oms.utils.FileUtils;
import com.lj.oms.utils.UserUtils;

/**
 * 类说明：朋友圈图片素材@Controller
 * <p>
 * 详细描述：朋友圈图片素材的控制器
 * @Company: 扬恩科技有限公司
 * @author 李端强
 * CreateDate: 2017年12月20日19:04:27
 */
@Controller
@RequestMapping(value = "${adminPath}/cm/friendsimagemateria")
public class FriendsImageMaterialController  extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(FriendsImageMaterialController.class);
	
	@Autowired
	private IFriendsImageMaterialService friendsImageMaterialService;//朋友圈图片素材服务
	@Autowired
	private IMaterialVariableService materialVariableService;//素材变量服务
	@Autowired
	private LocalCacheSystemParamsFromCC localCacheSystemParams;//图片配置
	
	/**
	 * 方法说明：跳转朋友圈图片素材列表
	 * @param friendsImageMaterial
	 * @param model
	 * @return
	 * @author 李端强 CreateDate: 2017年12月20日
	 */
	@RequestMapping(value = "show")
	public String listH5(Model model) {
		String merchantNo = UserUtils.getUser().getCompany().getId();//商户编号
		model.addAttribute("merchantNo", merchantNo);
		return "modules/im/friendsimagemateriaListH5";
	}
	
	/**
	 * 方法说明：获取朋友圈图片素材列表数据
	 * @param friendsImageMaterial
	 * @param model
	 * @return
	 * @author 李端强 CreateDate: 2017年12月20日
	 */
	@RequestMapping(value = {"list", ""})
	@ResponseBody
	public Page<FriendsImageMaterialDto> list(Model model,Integer pageNo,Integer pageSize,FriendsImageMaterialDto friendsImageMaterial) {
		Page<FriendsImageMaterialDto> retPage = null;
		try {
			if(friendsImageMaterial==null) {
				friendsImageMaterial = new FriendsImageMaterialDto();
			}
			if(pageNo!=null){
				friendsImageMaterial.setStart((pageNo-1)*pageSize);//起始
			}
			if(pageSize!=null){
				friendsImageMaterial.setLimit(pageSize);//尺寸
			}
			//处理时间，开始时间为00:00:00，结束时间为23:59:59
			//处理时间，开始时间为00:00:00，结束时间为23:59:59
            if (org.apache.commons.lang3.StringUtils.isNotBlank(friendsImageMaterial.getStartDate())) {
                friendsImageMaterial.setStartDate(friendsImageMaterial.getStartDate() + " 00:00:00");
            }
            if (org.apache.commons.lang3.StringUtils.isNotBlank(friendsImageMaterial.getEndDate())) {
                friendsImageMaterial.setEndDate(friendsImageMaterial.getEndDate() + " 23:59:59");
            }
			retPage = friendsImageMaterialService.findFriendsImageMaterialPage(friendsImageMaterial);
		} catch (Exception e) {
			throw new TsfaServiceException(ErrorCode.FRIENDS_IMAGE_MATERIAL_FIND_ERROR,"查询朋友圈图片素材表信息错误！",e);
		}
		return retPage;
	}
	
	/**
	 *
	 * 方法说明：查看单条朋友圈图片素材
	 * @param friendsImageMaterial
	 * @param model
	 * @return
	 * @author 李端强 CreateDate: 2017年12月20日
	 *
	 */
	@RequestMapping(value = "viewH5")
	public String viewH5(FriendsImageMaterialDto friendsImageMaterial, Model model) {
		String merchantNo = UserUtils.getUser().getCompany().getId();//商户编号
		model.addAttribute("merchantNo", merchantNo);
		if(friendsImageMaterial!=null && friendsImageMaterial.getCode()!=null) {
			//查询单条
			model.addAttribute("data",friendsImageMaterialService.findFriendsImageMaterialByKey(friendsImageMaterial));
		}
		return "modules/im/friendsimagemateriaViewH5";
	}
	
	/**
	 *
	 * 方法说明：新增单条朋友圈图片素材
	 * @param dto
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @author 李端强 CreateDate: 2017年12月20日
	 *
	 */
	@RequestMapping(value = "saveH5")
	public String save(FriendsImageMaterialDto dto, Model model) {
		try {
		    dto.setTitle(StringEscapeUtils.unescapeHtml4(dto.getTitle()).toString());
		    dto.setContent(StringEscapeUtils.unescapeHtml4(dto.getContent()).toString());
		    if (dto.getAutoComment() == 1) {
		        dto.setCommentContent(StringEscapeUtils.unescapeHtml4(dto.getCommentContent()).toString());
            }
		    logger.debug("新增朋友圈图片素材，转换后的内容：{}" + dto);
		    
			dto.setCode(GUID.getPreUUID());//指定主键
			dto.setCreateId(UserUtils.getUser().getId());//创建者ID
			dto.setCreateDate(new Date());//创建时间
			dto.setDeleteFlag(0);//默认保存有效
			friendsImageMaterialService.addFriendsImageMaterial(dto);
		} catch (Exception e) {
			throw new TsfaServiceException(ErrorCode.FRIENDS_IMAGE_MATERIAL_ADD_ERROR,"保存朋友圈图片素材表信息错误！",e);
		}	
		return "redirect:" + adminPath + "/cm/friendsimagemateria/show";//列表
	}
	
	/**
	 *
	 * 方法说明：上传图片并返回图片地址
	 * @param model
	 * @param imgFiles 多图
	 * @return
	 * @author 李端强 CreateDate: 2017年12月21日
	 */
	@RequestMapping(value = "uploadImg")
	@ResponseBody
	public Map<String, Object> uploadImg(Model model, List<MultipartFile> imgFiles) {
		String merchantNo = UserUtils.getUser().getCompany().getId();//商户编号
		StringBuffer imgAddr = new StringBuffer();//多图路径整合
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			if(imgFiles!=null && imgFiles.size()>0){
				for (MultipartFile file : imgFiles) {
					if (file==null || !FileUtils.fileSizeVaild(file)){
						throw new TsfaServiceException(ErrorCode.FRIENDS_IMAGE_MATERIAL_ADD_ERROR,"朋友圈图片素材新增图片上传异常!");
				    }else{
				    	String fileName = file.getOriginalFilename();
						logger.debug("oms cm uploadImg(filename = {})", fileName);
						// 判断文件格式
						String fileType = FileUtil.getFileType(fileName);
						if(StringUtils.isEmpty(fileType)) {
							logger.error("不支持的文件格式: {}", fileName);
							fileType = "file";	// 默认为file格式
						}
						Map<String, String> map = localCacheSystemParams.getSystemParamGroup(SystemAliasName.weixin.name(), "imfile");
						String uploadPath = map.get("uploadPath"); 
						String filePath = merchantNo + "/" + fileType.toLowerCase() + "/";
						String imageFolder = uploadPath+filePath;
						// 保存文件
						String fileInputName= FileUtil.saveFile(imageFolder, file);
						//String url = map.get("uploadUrl") + filePath + fileInputName;
				        imgAddr.append(",").append(filePath + fileInputName);//前端负责拼接完整http路径
				    }
				}
			}
		} catch (Exception e) {
			retMap.put("success", false);
			throw new TsfaServiceException(ErrorCode.FRIENDS_IMAGE_MATERIAL_ADD_ERROR,"保存朋友圈图片素材表信息错误！",e);
		}
		retMap.put("success", true);
		retMap.put("imgAddr", imgAddr.toString().replaceFirst(",", ""));
		return retMap;//图片地址
	}
	
	/**
	 *
	 * 方法说明：上传图片并返回图片地址
	 * @param model
	 * @param imgFiles 多图(字符串集合)
	 * @param type 文件类型
	 * @return
	 * @author 彭俊霖 CreateDate: 2018年01月25日
	 */
	@SuppressWarnings("restriction")
	@RequestMapping(value = "uploadImgString")
	@ResponseBody
	public Map<String, Object> uploadImgString(Model model, String imgFiles,String type) {
		String merchantNo = UserUtils.getUser().getCompany().getId();//商户编号
//		String merchantNo = "fcecbfa097944565a58134d170f474af";
		StringBuffer imgAddr = new StringBuffer();//多图路径整合 
		Map<String, Object> retMap = new HashMap<String, Object>();
		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
		FileOutputStream outputStream = null;
		type=StringUtils.isNotEmpty(type)?type:"jpg";//默认jpg格式
		try {
			
			if (StringUtils.isNotEmpty(imgFiles)){
				try {
					String[] files=imgFiles.split(",");
					for (String imgFile : files) {
						
						if (StringUtils.isEmpty(imgFile)){
							throw new TsfaServiceException(ErrorCode.FRIENDS_IMAGE_MATERIAL_ADD_ERROR,"朋友圈图片素材新增图片上传异常!");
					    }else{
					    	String postfix = "."+type;
					    	String fileName = GUID.generateByUUID() + postfix;
							logger.debug("oms cm uploadImg(filename = {})", fileName);
							Map<String, String> map = localCacheSystemParams.getSystemParamGroup(SystemAliasName.weixin.name(), "imfile");
							String uploadPath = map.get("uploadPath"); 
							String filePath = merchantNo + "/" + type + "/";
							String imageFolder = uploadPath+filePath;
							
							// 保存文件
							String imgPath = imageFolder+fileName;
							File imageFile = new File(imgPath);
							if (imageFile.getParentFile() != null && imageFile.getParentFile().exists() == false) {
								if (imageFile.getParentFile().mkdirs() == false) {
									throw new IOException("Destination '" + imageFile + "' directory cannot be created");
								}
							}
							outputStream = new FileOutputStream(imageFile);
							// 获得一个图片文件流，我这里是从flex中传过来的
							byte[] result = decoder.decodeBuffer(imgFile);//解码
							for (int k = 0; k < result.length; ++k) {
								if (result[k] < 0) {// 调整异常数据
									result[k] += 256;
								}
							}
							outputStream.write(result);
							imgAddr.append(",").append(filePath + fileName);//前端负责拼接完整http路径
					        
					    }
						
					}
					
				}catch (Exception e){
					logger.error("保存朋友圈图片素材表信息错误!", e);
				}finally {
					if(outputStream != null) {
						outputStream.flush();
						outputStream.close();
					}
				}
			}
			
		} catch (Exception e) {
			retMap.put("success", false);
			throw new TsfaServiceException(ErrorCode.FRIENDS_IMAGE_MATERIAL_ADD_ERROR,"保存朋友圈图片素材表信息错误！",e);
		}
		retMap.put("success", true);
		retMap.put("imgAddr", imgAddr.toString().replaceFirst(",", ""));
		return retMap;//图片地址
	}
	
	/**
	 *
	 * 方法说明：修改单条朋友圈图片素材
	 * @param dto
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @author 李端强 CreateDate: 2017年12月20日
	 *
	 */
	@RequestMapping(value = "updateH5")
	public String update(FriendsImageMaterialDto dto, Model model) {
		try {
		    dto.setTitle(StringEscapeUtils.unescapeHtml4(dto.getTitle()).toString());
            dto.setContent(StringEscapeUtils.unescapeHtml4(dto.getContent()).toString());
            if (dto.getAutoComment() == 1) {
                dto.setCommentContent(StringEscapeUtils.unescapeHtml4(dto.getCommentContent()).toString());
            }
            logger.debug("修改朋友圈图片素材，转换后的内容：{}" + dto);
            
			friendsImageMaterialService.updateFriendsImageMaterial(dto);
		} catch (Exception e) {
			throw new TsfaServiceException(ErrorCode.FRIENDS_IMAGE_MATERIAL_UPDATE_ERROR,"修改朋友圈图片素材表信息错误！",e);
		}	
		return "redirect:" + adminPath + "/cm/friendsimagemateria/show";//列表
	}
	
	/**
	 *
	 * 方法说明：逻辑删除单条朋友圈图片素材
	 * @param dto
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @author 李端强 CreateDate: 2017年12月20日
	 *
	 */
	@RequestMapping(value = "deleteH5")
	public String delete(FriendsImageMaterialDto dto, Model model) {
		try {
			friendsImageMaterialService.delFriendsImageMaterial(dto);
		} catch (Exception e) {
			throw new TsfaServiceException(ErrorCode.FRIENDS_IMAGE_MATERIAL_DEL_ERROR,"逻辑删除朋友圈图片素材表信息错误！",e);
		}	
		return "redirect:" + adminPath + "/cm/friendsimagemateria/show";//列表
	}
	
	/**
	 * 方法说明：逻辑删除单条朋友圈图片素材ajax
	 * @param dto
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @author 李端强 CreateDate: 2017年12月20日
	 */
	@RequestMapping(value = "deleteH5a")
	@ResponseBody
	public Map<String, Object> deleteH5a(String code,String merchantNo, Model model) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			if(StringUtils.isNullOrEmpty(code) || StringUtils.isNullOrEmpty(merchantNo)) {
				retMap.put("success", false);
				retMap.put("msg", "素材code和商户编号不能为空.");
				return retMap;
			}
			FriendsImageMaterialDto dto = new FriendsImageMaterialDto();
			dto.setCode(code);
			dto.setMerchantNo(merchantNo);
			dto.setDeleteFlag(1);//逻辑删除
			friendsImageMaterialService.delFriendsImageMaterial(dto);
		} catch (Exception e) {
			retMap.put("success", false);
			retMap.put("msg", "删除失败.");
			return retMap;
		}
		retMap.put("success", true);
		return retMap;
	}
	
	/**
	 *
	 * 方法说明：校验该商户新添加的图片素材时,变量值的乘积
	 * @param dto
	 * @return 变量值的乘积>1000 true
	 * @author 李端强 CreateDate: 2017年12月21日
	 */
	@RequestMapping(value = "checkMaterialVal",method=RequestMethod.POST)
	@ResponseBody
	public Boolean checkMaterialVal(FriendsImageMaterialDto dto){
		AssertUtils.notNull(dto);
		AssertUtils.notNull(dto.getMerchantNo(),"朋友圈素材变量查询商户编号不能为空");
		MaterialVariableDto reqDto = new MaterialVariableDto();
		reqDto.setMerchantNo(UserUtils.getUser().getCompany().getId());//商户编号
		List<MaterialVariableDto> retList = materialVariableService.findMaterialVariableList(reqDto);//所有素材变量
		Integer varCount = 1;//变量值基数
		if(retList!=null && retList.size()>0) {
			String ctx = dto.getContent();//素材内容
			Pattern p = Pattern.compile("\\$\\{\\w+\\}");
			Matcher m = p.matcher(ctx);
			while (m.find()) {//查询内容中的变量名
				String group = m.group();//匹配到的内容
				for (MaterialVariableDto valDto : retList) {
					if(valDto.getVarName().equals(group)) {
						if(valDto.getVarCount()!=null && valDto.getVarCount()>0) {
							varCount *= valDto.getVarCount();//变量值乘积
						}
					}
				}
			}
		}
		return varCount >=1000? true : false ;
	}
	
	public static void main(String[] args) {
		String str = "你是来自北方的一个人${abc},我已经在此等待了N年${abcdeii},谁能告诉我你是怎样的经历${woshi2wo}好的开始";
		Pattern p = Pattern.compile("\\$\\{\\w+\\}");
		Matcher m = p.matcher(str);
		while (m.find()) {
			System.out.println(m.group());
		}
	}
	
	
}
