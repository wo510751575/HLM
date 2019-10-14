
package com.lj.business.api.controller.imh5;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Maps;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.api.controller.Action;
import com.lj.business.api.utils.FileUtil;
import com.lj.business.cm.constant.ErrorCode;
import com.lj.business.cm.dto.FriendsImageMaterialDto;
import com.lj.business.cm.dto.MaterialVariableDto;
import com.lj.business.cm.friendsLinkMaterial.AddFriendsLinkMaterial;
import com.lj.business.cm.friendsLinkMaterial.FindFriendsLinkMaterial;
import com.lj.business.cm.friendsLinkMaterial.FindFriendsLinkMaterialPage;
import com.lj.business.cm.friendsLinkMaterial.FindFriendsLinkMaterialPageReturn;
import com.lj.business.cm.friendsLinkMaterial.FindFriendsLinkMaterialReturn;
import com.lj.business.cm.friendsLinkMaterial.UpdateFriendsLinkMaterial;
import com.lj.business.cm.service.IFriendsImageMaterialService;
import com.lj.business.cm.service.IFriendsLinkMaterialService;
import com.lj.business.cm.service.IMaterialVariableService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;
import com.lj.distributecache.RedisCache;
import com.lj.oms.entity.sys.User;

/**
 * 类说明：朋友圈素材@Controller
 * <p>
 * 详细描述：朋友圈图片素材的控制器
 * @Company: 扬恩科技有限公司
 * @author 李端强
 * CreateDate: 2017年12月20日19:04:27
 */
@Controller
@RequestMapping(value = "/imh5/friendsmateria/")
public class ImFriendsMaterialController extends Action {
	private static final Logger logger = LoggerFactory.getLogger(ImFriendsMaterialController.class);
	
	@Autowired
	private IFriendsImageMaterialService friendsImageMaterialService;//朋友圈图片素材服务
	@Autowired
	private IMaterialVariableService materialVariableService;//素材变量服务
	@Autowired
	private LocalCacheSystemParamsFromCC localCacheSystemParams;//图片配置
	@Resource
	private IFriendsLinkMaterialService friendsLinkMaterialService;//链接素材
	@Autowired 
	private RedisCache redisCache;
	
	/**
	 * 方法说明：获取朋友圈图片素材列表数据
	 * @param friendsImageMaterial
	 * @param model
	 * @return
	 * @author 李端强 CreateDate: 2017年12月20日
	 */
	@RequestMapping(value={"listH5image.do"})
	@ResponseBody
	public Page<FriendsImageMaterialDto> listH5image(Integer pageNo,Integer pageSize,FriendsImageMaterialDto friendsImageMaterial) {
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
	@RequestMapping(value = "viewH5image.do")
	@ResponseBody
	public Map<String, Object> viewH5image(FriendsImageMaterialDto friendsImageMaterial) {
		Map<String, Object> retMaps = Maps.newHashMap();
		try {
			if(friendsImageMaterial!=null && friendsImageMaterial.getCode()!=null) {
				//查询单条
				retMaps.put("data", friendsImageMaterialService.findFriendsImageMaterialByKey(friendsImageMaterial));
			}
		} catch (Exception e) {
			retMaps.put("success", false);
			logger.error("查看单条朋友圈图片素材异常",e);
			return retMaps;
		}
		retMaps.put("success", true);
		return retMaps;
	}
	
	/**
	 *
	 * 方法说明：查询单条连接素材
	 * @param code
	 * @return
	 * @author 李端强 CreateDate: 2018年2月5日
	 */
	@RequestMapping(value="friendsLinkMaterialForm.do")
	@ResponseBody
	public Map<String, Object> friendsLinkMaterialForm(String code){
		Map<String, Object> retMap = Maps.newHashMap();
		if(StringUtils.isNotEmpty(code)){
			FindFriendsLinkMaterial findFriendsLinkMaterial = new FindFriendsLinkMaterial();
			findFriendsLinkMaterial.setCode(code);
			FindFriendsLinkMaterialReturn findFriendsLinkMaterialReturn  = friendsLinkMaterialService.findFriendsLinkMaterial(findFriendsLinkMaterial);
			retMap.put("data", findFriendsLinkMaterialReturn);
		}else {
			retMap.put("data", "");
		}
		return retMap;
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
	@RequestMapping(value = "saveH5image.do")
	@ResponseBody
	public boolean saveH5image(FriendsImageMaterialDto dto,String userId) {
		try {
			dto.setCode(GUID.getPreUUID());//指定主键
			dto.setCreateId(userId);//创建者ID
			dto.setCreateDate(new Date());//创建时间
			dto.setDeleteFlag(0);//默认保存有效
			dto.setMerchantNo(getUserByCache(userId).getCompany().getId());//商户编号
			friendsImageMaterialService.addFriendsImageMaterial(dto);
		} catch (Exception e) {
			logger.error("保存朋友圈图片素材表信息错误",e);
			return false;
		}	
		return true;
	}
	
	/**
	 *
	 * 方法说明：上传图片并返回图片地址
	 * @param model
	 * @param imgFiles 多图
	 * @return
	 * @author 李端强 CreateDate: 2017年12月21日
	 */
	@RequestMapping(value = "uploadImg.do")
	@ResponseBody
	public Map<String, Object> uploadImg(List<MultipartFile> imgFiles,String userId) {
		String merchantNo = getUserByCache(userId).getCompany().getId();//商户编号
		StringBuffer imgAddr = new StringBuffer();//多图路径整合
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			if(imgFiles!=null && imgFiles.size()>0){
				for (MultipartFile file : imgFiles) {
					if (file==null){
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
	 * 方法说明：修改单条朋友圈图片素材
	 * @param dto
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @author 李端强 CreateDate: 2017年12月20日
	 *
	 */
	@RequestMapping(value = "updateH5image.do")
	@ResponseBody
	public boolean updateH5image(FriendsImageMaterialDto dto) {
		try {
			friendsImageMaterialService.updateFriendsImageMaterial(dto);
		} catch (Exception e) {
			logger.error("修改朋友圈图片素材表信息错误",e);
			return false;
		}	
		return true;
	}
	
	/**
	 * 方法说明：逻辑删除单条朋友圈图片素材ajax
	 * @param dto
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @author 李端强 CreateDate: 2017年12月20日
	 */
	@RequestMapping(value = "deleteH5image.do")
	@ResponseBody
	public Map<String, Object> deleteH5image(String code,String merchantNo) {
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
	@RequestMapping(value="checkMaterialVal.do", method=RequestMethod.POST)
	@ResponseBody
	public Boolean checkMaterialVal(FriendsImageMaterialDto dto){
		AssertUtils.notNull(dto);
		AssertUtils.notNull(dto.getMerchantNo(),"朋友圈素材变量查询商户编号不能为空");
		MaterialVariableDto reqDto = new MaterialVariableDto();
		reqDto.setMerchantNo(dto.getMerchantNo());//商户编号
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
	
	/**
     *
     * 方法说明：链接素材分页查询
     * @return page
     * @author 罗书明 CreateDate: 2018年1月9日
     */
	@RequestMapping(value="findFriendsLinkMaterialPage.do")
	@ResponseBody
	public Page<FindFriendsLinkMaterialPageReturn> findFriendsLinkMaterialPage(Integer pageNo,Integer pageSize
			,FindFriendsLinkMaterialPage findFriendsLinkMaterialPage,String userId){
		Page<FindFriendsLinkMaterialPageReturn> page = null;
		try {
			if(pageNo !=null){
				findFriendsLinkMaterialPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize !=null){
				findFriendsLinkMaterialPage.setLimit(pageSize);
			}
			findFriendsLinkMaterialPage.setMerchantNo(getUserByCache(userId).getCompany().getId());
			page = friendsLinkMaterialService.findFriendsLinkMaterialPage(findFriendsLinkMaterialPage);
		} catch (Exception e) {
			logger.error("分页查询链接素材错误！",e);
		}
		return page;
	}
	
	/**
	 *
	 * 方法说明：新增链接素材 
	 * @param addFriendsLinkMaterial
	 * @return boolean
	 * @author 罗书明 CreateDate: 2018年1月9日
	 */
	@RequestMapping(value="addFriendsLinkMaterial.do")
	@ResponseBody
	public boolean addFriendsLinkMaterial(AddFriendsLinkMaterial addFriendsLinkMaterial,String userId){
		try {
			addFriendsLinkMaterial.setMerchantNo(getUserByCache(userId).getCompany().getId());
			addFriendsLinkMaterial.setDeleteFlag(0);
			addFriendsLinkMaterial.setCreateId(userId);
			friendsLinkMaterialService.addFriendsLinkMaterial(addFriendsLinkMaterial);
		} catch (Exception e) {
			logger.error("新增链接素材错误！",e);
			return false;
		}
		return true;
	}
	
	/**
	 * 方法说明：逻辑删除链接素材
	 * @return
	 * @author 罗书明 CreateDate: 2018年1月10日
	 */
	@RequestMapping(value="delectMaterial.do")
	@ResponseBody
    public Map<String,Object> delectMaterial(UpdateFriendsLinkMaterial updateFriendsLinkMaterial){
		Map<String,Object> map = Maps.newHashMap();
		try {
			if(StringUtils.isEmpty(updateFriendsLinkMaterial.getCode())){
				map.put("success", false);
				map.put("msg","CODE不能为空！");
			}
			updateFriendsLinkMaterial.setDeleteFlag(1);//1是已删除
			friendsLinkMaterialService.updateFriendsLinkMaterial(updateFriendsLinkMaterial);
			map.put("success", true);
			map.put("msg","删除链接素材成功！");
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg","删除链接素材失败,系统出现异常！");
			logger.error("删除链接素材失败！",e);
		}
		return map;
    }
	
	/**
	 * 方法说明：修改链接素材
	 * @param model
	 * @param updateFriendsLinkMaterial
	 * @return
	 * @author 罗书明 CreateDate: 2018年1月10日
	 */
	@RequestMapping(value="updataMaterial.do")
	@ResponseBody
	public Map<String, Object> updataMaterial(UpdateFriendsLinkMaterial updateFriendsLinkMaterial){
		Map<String, Object> retMap = Maps.newHashMap();
		try {
			if(updateFriendsLinkMaterial.getCode()!=null){
				friendsLinkMaterialService.updateFriendsLinkMaterial(updateFriendsLinkMaterial);
				retMap.put("data", "updateFriendsLinkMaterial");
			}
		} catch (Exception e) {
			logger.error("修改链接素材异常",e);
			retMap.put("success",false);
			return retMap;
		}
		retMap.put("success",true);
		return  retMap;
		
	}
	
	/**
	 * 方法说明：缓存获取用户登录信息
	 * @param userId
	 * @return
	 * @author 李端强 CreateDate: 2018年1月29日
	 *
	 */
	private User getUserByCache(String userId) {
		AssertUtils.notNullAndEmpty(userId,"登录用户ID不能为空");
		String userStr = redisCache.get(userId);
		User user = (User) JsonUtils.objectFromJson(userStr, User.class);
		return user;
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
