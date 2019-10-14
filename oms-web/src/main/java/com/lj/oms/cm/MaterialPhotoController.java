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
import com.lj.business.cm.dto.AddMaterialPhoto;
import com.lj.business.cm.dto.wordsType.FindMaterialPhotoPage;
import com.lj.business.cm.service.IMaterialPhotoService;
import com.lj.oms.common.BaseController;
import com.lj.oms.entity.sys.Office;
import com.lj.oms.service.sys.OfficeService;
import com.lj.oms.utils.UserUtils;

/**
 * 类说明:图片库
 *@author 贾光朝
 *@createDate 2019年4月22日上午11:27:55
 */
@Controller
@RequestMapping(value = "${adminPath}/cm/materialPhoto")
public class MaterialPhotoController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(MaterialPhotoController.class);
	@Resource
	private IMaterialPhotoService materialPhotoService;
	@Resource
	private OfficeService officeService;
	
	@RequestMapping(value={"list", ""})
	public String list(FindMaterialPhotoPage findMaterialPhotoPage,Integer pageNo,Integer pageSize, Model model){
		try {
			if(pageNo!=null){
				findMaterialPhotoPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findMaterialPhotoPage.setLimit(pageSize);
			}
			if(StringUtils.isEmpty(findMaterialPhotoPage.getParentId())){
				findMaterialPhotoPage.setParentId("0");
			}
			findMaterialPhotoPage.setMerchantNo(UserUtils.getMerchantNo());
			Page<FindMaterialPhotoPage> pageDto = materialPhotoService.findMaterialPhotoPage(findMaterialPhotoPage);
			List<FindMaterialPhotoPage> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());
			
			if(!list.isEmpty()){
				for (FindMaterialPhotoPage page : list) {
					Office office = officeService.findOffice(page.getMerchantNo());
					if(null != office){
						page.setOfficeName(office.getName());
					}
				}
			}    
			com.ape.common.persistence.Page<FindMaterialPhotoPage> page=new com.ape.common.persistence.Page<FindMaterialPhotoPage>(pageNo==null?1:pageNo, pageDto.getLimit(), pageDto.getTotal(), list);
			page.initialize();
			model.addAttribute("page",page);
			model.addAttribute("findMaterialPhotoPage",findMaterialPhotoPage);
			model.addAttribute("merchantNo",findMaterialPhotoPage.getMerchantNo());
		} catch (Exception e) {
			logger.error("获取视频库列表错误!",e);
		}
		return "modules/cm/materialPhotoList";
	}
	
	@RequestMapping(value = "form")
	public String form(FindMaterialPhotoPage findMaterialPhotoPage, Model model) {
		model.addAttribute("parentId", findMaterialPhotoPage.getParentId());
		model.addAttribute("parentIds", findMaterialPhotoPage.getParentIds());
		return "modules/cm/newPhotoFolderForm";
	}
	
	@RequestMapping(value = "save")
	public String save(AddMaterialPhoto addMaterialPhoto, Model model) {
		try {
			if(StringUtils.isEmpty(addMaterialPhoto.getCode())){
				addMaterialPhoto.setCode(GUID.generateByUUID());
			}
			if(StringUtils.isEmpty(addMaterialPhoto.getParentId())||("0".equals(addMaterialPhoto.getParentId()))){
				addMaterialPhoto.setParentId("0");
				addMaterialPhoto.setParentIds("0");
			}else{
				String parentIds = materialPhotoService.selectParentIds(addMaterialPhoto.getParentId());
				addMaterialPhoto.setParentIds(parentIds+","+addMaterialPhoto.getParentId());
			}
			addMaterialPhoto.setUpdateTime(new Date());
			addMaterialPhoto.setMerchantNo(UserUtils.getMerchantNo());
			materialPhotoService.addMaterialPhoto(addMaterialPhoto);
			model.addAttribute("repMsg", "保存成功");
		} catch (Exception e) {
			logger.error("保存错误!", e);
			model.addAttribute("repMsg", "保存失败");
		}
		FindMaterialPhotoPage  findMaterialPhotoPage = new FindMaterialPhotoPage();
		if(null != addMaterialPhoto.getParentId()){
			findMaterialPhotoPage.setParentId(addMaterialPhoto.getParentId());
		}
		return list( findMaterialPhotoPage,null,null,model);
	}
	
	
	@RequestMapping(value = "upload")
	public String upload(String parentId,Model model){
		try {
			model.addAttribute("parentId", parentId);
		} catch (Exception e) {
			logger.error("上传发生错误!", e);
		}
		return "modules/cm/uploadPhotoForm";
	}
	
	@RequestMapping(value = "delete")
	public String delete(String ids,String parentId,Model model){
		try {
			String[] codes = ids.split(",");
			for (String code : codes) {
				AddMaterialPhoto addMaterialPhoto = new AddMaterialPhoto();
				addMaterialPhoto.setCode(code);
				addMaterialPhoto.setMerchantNo(UserUtils.getMerchantNo());
				materialPhotoService.delete(addMaterialPhoto);
				if(StringUtils.isNotEmpty(parentId)&&!parentId.equals("0")){
					int count = materialPhotoService.getCount(parentId);
					addMaterialPhoto.setCount(count);
					addMaterialPhoto.setCode(parentId);
					materialPhotoService.updateCount(addMaterialPhoto);
				}
				model.addAttribute("repMsg", "删除成功");
			}
		} catch (Exception e) {
			logger.error("删除错误!", e);
			model.addAttribute("repMsg", "删除失败");
		}
		FindMaterialPhotoPage  findMaterialPhotoPage = new FindMaterialPhotoPage();
		if(StringUtils.isNotEmpty(parentId)){
			findMaterialPhotoPage.setParentId(parentId);
		}
		return list( findMaterialPhotoPage,null,null,model);
	}
	
	@RequestMapping(value={"listJson"})
	@ResponseBody
	public List<FindMaterialPhotoPage> listJson(FindMaterialPhotoPage findMaterialPhotoPage){
		List<FindMaterialPhotoPage> list = Lists.newArrayList();
		try {
//			if(StringUtils.isEmpty(findMaterialPhotoPage.getParentId())) {
//				findMaterialPhotoPage.setParentId("0");
//			}
			if(StringUtils.isEmpty(findMaterialPhotoPage.getMerchantNo())) {
				findMaterialPhotoPage.setMerchantNo(UserUtils.getMerchantNo());
			}
			findMaterialPhotoPage.setLimit(Integer.MAX_VALUE);
			Page<FindMaterialPhotoPage> pageDto = materialPhotoService.findMaterialPhotoPage(findMaterialPhotoPage);
			
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
		FindMaterialPhotoPage findMaterialPhotoPage = new FindMaterialPhotoPage();
		findMaterialPhotoPage.setParentId(extId);
		List<FindMaterialPhotoPage> sourcelist = this.listJson(findMaterialPhotoPage);
		List<FindMaterialPhotoPage> list = Lists.newArrayList();
		sortList(list, sourcelist, "0", true);
		for (int i=0; i<list.size(); i++){
			FindMaterialPhotoPage e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getCode()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				if(isShowHide != null && isShowHide.equals("0")){
					continue;
				}
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getCode());
				map.put("pId", e.getParentId());
				map.put("name", e.getFolderName());
				map.put("url", e.getPhotoLocation());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
	private void sortList(List<FindMaterialPhotoPage> list, List<FindMaterialPhotoPage> sourcelist, String parentId, boolean cascade){
		for (int i=0; i<sourcelist.size(); i++){
			FindMaterialPhotoPage e = sourcelist.get(i);
			if (parentId.equals(e.getParentId())){
				list.add(e);
				if (cascade){
					// 判断是否还有子节点, 有则继续获取子节点
					for (int j=0; j<sourcelist.size(); j++){
						FindMaterialPhotoPage child = sourcelist.get(j);
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
