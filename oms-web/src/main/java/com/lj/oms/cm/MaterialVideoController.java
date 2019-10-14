package com.lj.oms.cm;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.GUID;
import com.lj.business.cm.dto.AddMaterialVideo;
import com.lj.business.cm.dto.wordsType.FindMaterialVideoPage;
import com.lj.business.cm.dto.wordsType.FindMaterialVideoPageReturn;
import com.lj.business.cm.service.IMaterialVideoService;
import com.lj.oms.common.BaseController;
import com.lj.oms.entity.sys.Office;
import com.lj.oms.service.sys.OfficeService;
import com.lj.oms.utils.UserUtils;

/**
 * 类说明:视频库
 *@author 贾光朝
 *@createDate 2019年4月22日上午11:27:55
 */
@Controller
@RequestMapping(value = "${adminPath}/cm/materialVideo")
public class MaterialVideoController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(MaterialVideoController.class);
	@Resource
	private IMaterialVideoService materialVideoService;
	@Resource
	private OfficeService officeService;
	
	@RequestMapping(value={"list", ""})
	public String list(FindMaterialVideoPage findMaterialVideoPage,Integer pageNo,Integer pageSize, Model model){
		try {
			if(pageNo!=null){
				findMaterialVideoPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findMaterialVideoPage.setLimit(pageSize);
			}
			if(StringUtils.isEmpty(findMaterialVideoPage.getParentId())){
				findMaterialVideoPage.setParentId("0");
			}
			findMaterialVideoPage.setMerchantNo(UserUtils.getMerchantNo());
			Page<FindMaterialVideoPageReturn> pageDto = materialVideoService.findMaterialVideoPage(findMaterialVideoPage);
			List<FindMaterialVideoPageReturn> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());
			
			if(!list.isEmpty()){
				for (FindMaterialVideoPageReturn findMaterialVideoPageReturn : list) {
					Office office = officeService.findOffice(findMaterialVideoPageReturn.getMerchantNo());
					if(null != office){
						findMaterialVideoPageReturn.setOfficeName(office.getName());
					}
				}
			}     
			com.ape.common.persistence.Page<FindMaterialVideoPageReturn> page=new com.ape.common.persistence.Page<FindMaterialVideoPageReturn>(pageNo==null?1:pageNo, pageDto.getLimit(), pageDto.getTotal(), list);
			page.initialize();
			model.addAttribute("page",page);
			model.addAttribute("findMaterialVideoPage",findMaterialVideoPage);
			model.addAttribute("merchantNo",findMaterialVideoPage.getMerchantNo());
		} catch (Exception e) {
			logger.error("获取视频库列表错误!",e);
		}
		return "modules/cm/materialVideoList";
	}
	
	@RequestMapping(value = "form")
	public String form(FindMaterialVideoPage findMaterialVideoPage, Model model) {
		model.addAttribute("parentId", findMaterialVideoPage.getParentId());
		model.addAttribute("parentIds", findMaterialVideoPage.getParentIds());
		return "modules/cm/newFolderForm";
	}
	
	@RequestMapping(value = "save")
	public String save(AddMaterialVideo addMaterialVideo, Model model) {
		try {
			if(StringUtils.isEmpty(addMaterialVideo.getCode())){
				addMaterialVideo.setCode(GUID.generateByUUID());
			}
			if(StringUtils.isEmpty(addMaterialVideo.getParentId())||("0".equals(addMaterialVideo.getParentId()))){
				addMaterialVideo.setParentId("0");
				addMaterialVideo.setParentIds("0");
			}else{
				String parentIds = materialVideoService.selectParentIds(addMaterialVideo.getParentId());
				addMaterialVideo.setParentIds(parentIds + "," + addMaterialVideo.getParentId());
			}
			addMaterialVideo.setUpdateTime(new Date());
			addMaterialVideo.setMerchantNo(UserUtils.getMerchantNo());
			materialVideoService.addMaterialVideo(addMaterialVideo);
			model.addAttribute("repMsg", "保存成功");
		} catch (Exception e) {
			logger.error("保存错误!", e);
			model.addAttribute("repMsg", "保存失败");
		}
		FindMaterialVideoPage findMaterialVideoPage = new FindMaterialVideoPage();
		if(null != addMaterialVideo.getParentId()){
			findMaterialVideoPage.setParentId(addMaterialVideo.getParentId());
		}
		return list(findMaterialVideoPage, null, null, model);
	}
	
	@RequestMapping(value = "upload")
	public String upload(String parentId,Model model){
		try {
			model.addAttribute("parentId", parentId);
		} catch (Exception e) {
			logger.error("上传发生错误!", e);
		}
		return "modules/cm/uploadForm";
	}
	
	@RequestMapping(value = "delete")
	public String delete(String ids,String parentId,Model model){
		try {
			String[] codes = ids.split(",");
			for (String code : codes) {
				AddMaterialVideo materialVideo = new AddMaterialVideo();
				materialVideo.setCode(code);
				materialVideo.setMerchantNo(UserUtils.getMerchantNo());
				materialVideoService.delete(materialVideo);
				if(StringUtils.isNotEmpty(parentId)&&!parentId.equals("0")){
					int count = materialVideoService.getCount(parentId);
					materialVideo.setCount(count);
					materialVideo.setCode(parentId);
					materialVideoService.updateCount(materialVideo);
				}
				model.addAttribute("repMsg", "删除成功");
			}
		} catch (Exception e) {
			logger.error("删除错误!", e);
			model.addAttribute("repMsg", "删除失败");
		}
		FindMaterialVideoPage findMaterialVideoPage = new FindMaterialVideoPage();
		if(StringUtils.isNotEmpty(parentId)){
			findMaterialVideoPage.setParentId(parentId);
		}
		return list(findMaterialVideoPage, null, null, model);
	}
	
	@RequestMapping(value={"listJson"})
	@ResponseBody
	public List<FindMaterialVideoPageReturn> listJson(FindMaterialVideoPage findMaterialVideoPage){
		List<FindMaterialVideoPageReturn> list = Lists.newArrayList();
		try {
			if(StringUtils.isEmpty(findMaterialVideoPage.getMerchantNo())) {
				findMaterialVideoPage.setMerchantNo(UserUtils.getMerchantNo());
			}
			findMaterialVideoPage.setLimit(Integer.MAX_VALUE);
			Page<FindMaterialVideoPageReturn> pageDto = materialVideoService.findMaterialVideoPage(findMaterialVideoPage);
			list.addAll(pageDto.getRows());
		} catch (Exception e) {
			logger.error("获取视频库列表错误!",e);
		}
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId,@RequestParam(required=false) String isShowHide) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		FindMaterialVideoPage findMaterialVideoPage = new FindMaterialVideoPage();
		findMaterialVideoPage.setParentId(extId);
		List<FindMaterialVideoPageReturn> sourcelist = this.listJson(findMaterialVideoPage);
		List<FindMaterialVideoPageReturn> list = Lists.newArrayList();
		sortList(list, sourcelist, "0", true);
		for (int i=0; i<list.size(); i++){
			FindMaterialVideoPageReturn e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getCode()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				if(isShowHide != null && isShowHide.equals("0")){
					continue;
				}
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getCode());
				map.put("pId", e.getParentId());
				map.put("name", e.getFolderName());
				map.put("view", e.getFirstView());
				map.put("url", e.getVideoLocation());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
	private void sortList(List<FindMaterialVideoPageReturn> list, List<FindMaterialVideoPageReturn> sourcelist, String parentId, boolean cascade){
		for (int i=0; i<sourcelist.size(); i++){
			FindMaterialVideoPageReturn e = sourcelist.get(i);
			if (parentId.equals(e.getParentId())){
				list.add(e);
				if (cascade){
					// 判断是否还有子节点, 有则继续获取子节点
					for (int j=0; j<sourcelist.size(); j++){
						FindMaterialVideoPageReturn child = sourcelist.get(j);
						if (StringUtils.isNotEmpty(child.getParentId()) && child.getParentId().equals(e.getCode())){
							sortList(list, sourcelist, e.getCode(), true);
							break;
						}
					}
				}
			}
		}
	}
}
