package com.lj.oms.sys;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ape.common.utils.StringUtils;
import com.lj.oms.common.BaseController;
import com.lj.oms.entity.sys.Area;
import com.lj.oms.service.sys.AreaService;
import com.lj.oms.utils.UserUtils;

/**
 * 区域Controller
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/area")
public class AreaController extends BaseController{

	@Autowired
	private AreaService areaService;
	
	@ModelAttribute("area")
	public Area get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return areaService.get(id);
		}else{
			return new Area();
		}
	}

	@RequiresPermissions("sys:area:view")
	@RequestMapping(value = {"list", ""})
	public String list(Area area, Model model) {
//		User user = UserUtils.getUser();
//		if(user.isAdmin()){
			area.setId(Area.ROOT_ID);
//		}else{
//			area.setId(user.getArea().getId());
//		}
		model.addAttribute("area", area);
		List<Area> list = Lists.newArrayList();
		List<Area> sourcelist = areaService.selectProvince();
		Area.sortList(list, sourcelist, area.getId());
        model.addAttribute("list", list);
		return "modules/sys/areaList";
	}

	@RequiresPermissions("sys:area:view")
	@RequestMapping(value = "form")
	public String form(Area area, Model model) {
		if (area.getParent()==null||area.getParent().getId()==null){
			area.setParent(UserUtils.getUser().getOffice().getArea());
		}
		if(area.getParent()!=null){
			area.setParent(areaService.get(area.getParent().getId()));
		}
		model.addAttribute("area", area);
		return "modules/sys/areaForm";
	}
	
	@RequiresPermissions("sys:area:edit")
	@RequestMapping(value = "save")
	public String save(Area area, Model model, RedirectAttributes redirectAttributes) {
//		if (!beanValidator(model, area)){
//			return form(area, model);
//		}
		areaService.save(area);
		addMessage(redirectAttributes, "保存区域'" + area.getName() + "'成功");
		return "redirect:" + adminPath + "/sys/area/";
	}
	
	@RequiresPermissions("sys:area:edit")
	@RequestMapping(value = "delete")
	public String delete(Area area, RedirectAttributes redirectAttributes) {
		areaService.delete(area);
		addMessage(redirectAttributes, "删除区域成功");
		return "redirect:" + adminPath + "/sys/area/";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Area> list = areaService.findAll();
		for (int i=0; i<list.size(); i++){
			Area e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
	@RequiresUser
	@ResponseBody
	@RequestMapping(value = "findChilds")
	public List<Map<String, Object>> findChilds(String parentId, HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		List<Map<String, Object>> list = areaService.findChild(parentId);
		return list;
	}
	
	@RequiresUser
    @ResponseBody
    @RequestMapping(value = "provinceList")
    public List<Area> provinceList() {
        List<Area> provinceList = areaService.selectProvince();
        
        return provinceList;
    }
	
	@RequiresUser
	@ResponseBody
	@RequestMapping(value = "findCityListByProvinceCode")
	public List<Area> findCityListByProvinceCode(String provinceCode, HttpServletResponse response) {
	    Area area = areaService.findProvinceByCode(provinceCode);
        return areaService.findCityByProvinceId(area.getId());
	}
}
