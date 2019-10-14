/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.oms.im;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ape.common.config.Global;
import com.ape.common.utils.StringUtils;
import com.lj.oms.common.BaseController;
import com.google.common.collect.Maps;
import com.lj.base.core.pagination.Page;
import com.lj.business.cm.dto.friends.AddFriendsVideoMaterial;
import com.lj.business.cm.dto.friends.FindFriendsVideoMaterial;
import com.lj.business.cm.dto.friends.FindFriendsVideoMaterialPage;
import com.lj.business.cm.dto.friends.FindFriendsVideoMaterialPageReturn;
import com.lj.business.cm.dto.friends.FindFriendsVideoMaterialReturn;
import com.lj.business.cm.dto.friends.UpdateFriendsVideoMaterial;
import com.lj.business.cm.service.IFriendsVideoMaterialService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 类说明：朋友圈素材
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年7月31日
 */
@Controller
@RequestMapping(value = "${adminPath}/im/")
public class FriendsMaterialController {

    private static final Logger logger = LoggerFactory.getLogger(FriendsMaterialController.class);
    
	@Resource
	private IFriendsVideoMaterialService friendsVideoMaterialService;
	
	@Autowired
	private LocalCacheSystemParamsFromCC localCacheSystemParams;

	/**
	 * 
	 *
	 * 方法说明：新增视频素材
	 *
	 * @param model
	 * @param addFriendsVideoMaterial
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年7月31日
	 *
	 */
	@RequestMapping(value="addFriendsVideoMaterial")
	public String addFriendsVideoMaterial(Model model,AddFriendsVideoMaterial addFriendsVideoMaterial/*, MultipartFile file*/){
		try {
			/*if (file==null || !FileUtils.fileSizeVaild(file)){
				throw new TsfaServiceException(ErrorCode.FRIENDS_VIDEO_MATERIAL_ADD_ERROR,"朋友圈视频素材新增上传异常!");
		    }else{
		    	String fileName = file.getOriginalFilename();
				logger.debug("addFriendsVideoMaterial upload(filename = {})", fileName);
				// 判断文件格式
				String fileType = FileUtil.getFileType(fileName);
				if(StringUtils.isEmpty(fileType)) {
					logger.error("不支持的文件格式: {}", fileName);
					fileType = "file";	// 默认为file格式
				}
				Map<String, String> map = localCacheSystemParams.getSystemParamGroup(SystemAliasName.weixin.name(), "imfile");
				String uploadPath = map.get("uploadPath"); 
				String filePath = UserUtils.getMerchantNo() + "/" + fileType.toLowerCase() + "/";
				String imageFolder = uploadPath+filePath;
				// 保存文件
				String fileInputName= FileUtil.saveFile(imageFolder, file);
				//String url = map.get("uploadUrl") + filePath + fileInputName;
				addFriendsVideoMaterial.setLinkUrl(filePath + fileInputName);//前端负责拼接完整http路径
		    }*/
			
            addFriendsVideoMaterial.setTitle(StringEscapeUtils.unescapeHtml4(addFriendsVideoMaterial.getTitle()).toString());
            addFriendsVideoMaterial.setShareTitle(StringEscapeUtils.unescapeHtml4(addFriendsVideoMaterial.getShareTitle()).toString());
            addFriendsVideoMaterial.setContent(StringEscapeUtils.unescapeHtml4(addFriendsVideoMaterial.getContent()).toString());
            if (addFriendsVideoMaterial.getAutoComment() == 1) {
                addFriendsVideoMaterial.setCommentContent(StringEscapeUtils.unescapeHtml4(addFriendsVideoMaterial.getCommentContent()).toString());
            }
            logger.debug("新增视频素材，转换后的内容：{}" + addFriendsVideoMaterial);
		    
			addFriendsVideoMaterial.setMerchantNo(UserUtils.getMerchantNo());
			addFriendsVideoMaterial.setDeleteFlag(0);
			String uploadUrl = localCacheSystemParams.getSystemParam(SystemAliasName.weixin.name(), "imfile", "uploadUrl");
            addFriendsVideoMaterial.setLinkUrl(uploadUrl + addFriendsVideoMaterial.getLinkUrl());	// 视频访问地址使用全路径
			friendsVideoMaterialService.addFriendsVideoMaterial(addFriendsVideoMaterial);
		} catch (Exception e) {
			logger.error("新增视频素材错误！",e);
		}
		return  "redirect:" +Global.getAdminPath() + "/im/findFriendsVideoMaterialList";
	}
	  
	/**
	 * 
	 *
	 * 方法说明：去编辑视频素材
	 *
	 * @param model
	 * @param code
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年7月31日
	 *
	 */
	@RequestMapping(value="friendsVideoMaterialForm")
	public String friendsVideoMaterialForm(Model model,String code){
		if(StringUtils.isNotEmpty(code)){
			FindFriendsVideoMaterial findFriendsVideoMaterial = new FindFriendsVideoMaterial();
			findFriendsVideoMaterial.setCode(code);
			FindFriendsVideoMaterialReturn findFriendsVideoMaterialReturn  = friendsVideoMaterialService.findFriendsVideoMaterial(findFriendsVideoMaterial);
			model.addAttribute("data", findFriendsVideoMaterialReturn);
		}
		String merchantNo = UserUtils.getUser().getCompany().getId();//商户编号
		model.addAttribute("merchantNo", merchantNo);
		return "modules/im/friendsvideomateriaViewH5";
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除视频素材
	 *
	 * @param updateFriendsVideoMaterial
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年7月31日
	 *
	 */
	@RequestMapping(value="delectVideoMaterial")
	@ResponseBody
    public Map<String,Object> delectMaterial(UpdateFriendsVideoMaterial updateFriendsVideoMaterial){
		Map<String,Object> map = Maps.newHashMap();
		try {
			if(StringUtils.isBlank(updateFriendsVideoMaterial.getCode())){
				map.put("success", false);
				map.put("msg","CODE不能为空！");
			}
			friendsVideoMaterialService.updateFriendsVideoMaterial(updateFriendsVideoMaterial);
			map.put("success", true);
			map.put("msg","删除视频素材成功！");
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg","删除视频素材失败,系统出现异常！");
			logger.error("删除视频素材失败！",e);
		}
		return map;
    }


	/**
	 * 
	 *
	 * 方法说明：修改视频素材
	 *
	 * @param model
	 * @param updateFriendsVideoMaterial
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年7月31日
	 *
	 */
	@RequestMapping(value="updataVideoMaterial")
	public String updataMaterial(Model model,UpdateFriendsVideoMaterial updateFriendsVideoMaterial){
		 
		
		try {
			if(updateFriendsVideoMaterial.getCode()!=null){
			    updateFriendsVideoMaterial.setTitle(StringEscapeUtils.unescapeHtml4(updateFriendsVideoMaterial.getTitle()).toString());
			    updateFriendsVideoMaterial.setShareTitle(StringEscapeUtils.unescapeHtml4(updateFriendsVideoMaterial.getShareTitle()).toString());
			    updateFriendsVideoMaterial.setContent(StringEscapeUtils.unescapeHtml4(updateFriendsVideoMaterial.getContent()).toString());
	            if (updateFriendsVideoMaterial.getAutoComment() == 1) {
	                updateFriendsVideoMaterial.setCommentContent(StringEscapeUtils.unescapeHtml4(updateFriendsVideoMaterial.getCommentContent()).toString());
	            }
	            logger.debug("修改视频素材，转换后的内容：{}" + updateFriendsVideoMaterial);
			    
				friendsVideoMaterialService.updateFriendsVideoMaterial(updateFriendsVideoMaterial);
				model.addAttribute("data", updateFriendsVideoMaterial);
			}
		} catch (Exception e) {
			logger.error("修改视频素材");
		}
		return  "redirect:" +Global.getAdminPath() + "/im/findFriendsVideoMaterialList";
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：列表返回地址
	 *
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年7月31日
	 *
	 */
	@RequestMapping(value="findFriendsVideoMaterialList")
	public String findFriendsVideoMaterialList(){
		return "modules/im/friendsvideomateriaListH5";
	}
	
    /**
     * 
     *
     * 方法说明：视频素材分页查询
     *
     * @param model
     * @param pageNo
     * @param pageSize
     * @param findFriendsVideoMaterialPage
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2018年7月31日
     *
     */
	@RequestMapping(value="findFriendsVideoMaterialPage")
	@ResponseBody
	public Page<FindFriendsVideoMaterialPageReturn> findFriendsVideoMaterialPage(Model model,Integer pageNo,Integer pageSize, FindFriendsVideoMaterialPage findFriendsVideoMaterialPage){
		Page<FindFriendsVideoMaterialPageReturn> page = null;
		try {
			if(pageNo !=null){
				findFriendsVideoMaterialPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize !=null){
				findFriendsVideoMaterialPage.setLimit(pageSize);
			}
			findFriendsVideoMaterialPage.setMerchantNo(UserUtils.getMerchantNo());
			page = friendsVideoMaterialService.findFriendsVideoMaterialPage(findFriendsVideoMaterialPage);
		} catch (Exception e) {
			logger.error("分页查询视频素材错误！",e);
		}
		return page;
	}

}
