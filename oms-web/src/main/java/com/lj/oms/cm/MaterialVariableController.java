package com.lj.oms.cm;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ape.common.config.Global;
import com.ape.common.utils.DateUtils;
import com.lj.oms.common.BaseController;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.constant.ErrorCode;
import com.lj.business.cm.dto.MaterialVariableDto;
import com.lj.business.cm.service.IMaterialVariableService;
import com.lj.oms.utils.UserUtils;

/**
 * 类说明：朋友圈素材变量@Controller
 * <p>
 * 详细描述：朋友圈素材变量表的控制器
 * @Company: 扬恩科技有限公司
 * @author 李端强
 * CreateDate: 2017年12月21日16:35:12
 */
@Controller
@RequestMapping(value = "${adminPath}/cm/materialVariable")
public class MaterialVariableController  extends BaseController{
	
	@Autowired
	private IMaterialVariableService materialVariableService;//朋友圈素材变量服务
	
	/**
	 * 方法说明：跳转朋友圈图片素材列表
	 * @return
	 * @author 李端强 CreateDate: 2017年12月20日
	 */
	@RequestMapping(value = "show")
	public String listH5(Model model) {
		return "modules/im/materialVariableListH5";
	}
	
	/**
	 * 方法说明：获取素材变量列表数据
	 * @param MaterialVariableDto
	 * @param model
	 * @return
	 * @author 李端强 CreateDate: 2017年12月20日
	 */
	@RequiresPermissions("cm:materialVariable:view")
	@RequestMapping(value = {"list",""})
	public String list(Model model,Integer pageNo,Integer pageSize,MaterialVariableDto materialVariable) {
		Page<MaterialVariableDto> retPage = null;
		try {
			if(materialVariable==null) {
				materialVariable = new MaterialVariableDto();
			}
			if(pageNo!=null){
				materialVariable.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				materialVariable.setLimit(pageSize);
			}
			if (materialVariable.getStartDate() != null) {
				Calendar calendar = DateUtils.toCalendar(materialVariable.getStartDate());
				// 将小时至0
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				// 将分钟至0
				calendar.set(Calendar.MINUTE, 0);
				// 将秒至0
				calendar.set(Calendar.SECOND, 0);
				Date startDate = calendar.getTime();
				materialVariable.setStartDate(startDate);
			}
			if (materialVariable.getEndDate() != null) {
				Calendar calendar = DateUtils.toCalendar(materialVariable.getEndDate());
				// 将小时至23
				calendar.set(Calendar.HOUR_OF_DAY, 23);
				// 将分钟至59
				calendar.set(Calendar.MINUTE, 59);
				// 将秒至59
				calendar.set(Calendar.SECOND, 59);
				Date endDate = calendar.getTime();
				materialVariable.setEndDate(endDate);
			}
			materialVariable.setMerchantNo(UserUtils.getUser().getCompany().getId());
			retPage = materialVariableService.findMaterialVariablePage(materialVariable);
			List<MaterialVariableDto>  list= Lists.newArrayList();
			list.addAll(retPage.getRows());
			
			for(MaterialVariableDto materialVariableDto : list){
				List<String> countList=Lists.newArrayList();
				String[] string = materialVariableDto.getVarContent().split("\\$");
				for(String str :string){
					countList.add(str);
				}
				materialVariableDto.setCountList(countList);
			}
			com.ape.common.persistence.Page<MaterialVariableDto> page=new com.ape.common.persistence.Page<MaterialVariableDto>(pageNo==null?1:pageNo, retPage.getLimit(), retPage.getTotal(), list);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("materialVariable", materialVariable);
		} catch (Exception e) {
			throw new TsfaServiceException(ErrorCode.MATERIAL_VARIABLE_FIND_ERROR,"查询朋友圈素材变量表信息错误！",e);
		}
		return "modules/cm/materialVariableList";
	}
    /**
     * 
     *
     * 方法说明：
     *
     * @param model
     * @param pageNo
     * @param pageSize
     * @param materialVariable
     * @return
     *
     * @author 罗书明 CreateDate: 2017年12月27日
     *
     */
	@RequestMapping(value ="returnList")
	@ResponseBody
	public Page<MaterialVariableDto> returnList(Model model,Integer pageNo,Integer pageSize,MaterialVariableDto materialVariable) {
		Page<MaterialVariableDto> retPage = null;
		try {
			if(materialVariable==null) {
				materialVariable = new MaterialVariableDto();
			}
			if(pageNo!=null){
				materialVariable.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				materialVariable.setLimit(pageSize);
			}
			
			List<MaterialVariableDto> list = Lists.newArrayList();
			retPage = materialVariableService.findMaterialVariablePage(materialVariable);
			list.addAll(retPage.getRows());
			for(MaterialVariableDto materialVariableDto : list){
				List<String> countList=Lists.newArrayList();
				String[] string = materialVariableDto.getVarContent().split("\\$");
				for(String str :string){
					countList.add(str);
				}
				materialVariableDto.setCountList(countList);
			}
			retPage.setRows(list);
		} catch (Exception e) {
			throw new TsfaServiceException(ErrorCode.MATERIAL_VARIABLE_FIND_ERROR,"朋友圈素材变量表操作--查询异常",e);
		}
		return retPage;
	}	
	
	
	/**
	 *
	 * 方法说明：素材变量
	 * @param friendsImageMaterial
	 * @param model
	 * @return
	 * @author 李端强 CreateDate: 2017年12月20日
	 *
	 */
	@RequiresPermissions("cm:materialVariable:edit")
	@RequestMapping(value = "form")
	public String form(MaterialVariableDto materialVariable, Model model) {
		try {
			if(materialVariable!=null && materialVariable.getCode()!=null) {
				//查询单条
			    MaterialVariableDto materialVariableDto = materialVariableService.findMaterialVariableByKey(materialVariable.getCode());
				model.addAttribute("data",materialVariableDto);
			}
		} catch (Exception e) {
			logger.error("查询变量错误！");
		}
		
		return "modules/cm/materialVariableForm";
	}
	
    /**
     * 
     *
     * 方法说明：新增方法
     *
     * @param dto
     * @param model
     * @return
     *
     * @author 罗书明 CreateDate: 2017年12月23日
     *
     */
	@RequiresPermissions("cm:materialVariable:edit")
	@RequestMapping(value = "save")
	public String save(MaterialVariableDto dto, Model model) {
		try {
			dto.setMerchantNo(UserUtils.getUser().getCompany().getId());
			dto.setCode(GUID.getPreUUID());
			dto.setCreateDate(new Date());
			dto.setUpateDate(new Date());
			dto.setSysFlag(0);
			String[] str = dto.getVarContent().toString().split("\\$");
			int i = str.length;
		    dto.setVarCount(i);
			materialVariableService.addMaterialVariable(dto);
		} catch (Exception e) {
			throw new TsfaServiceException(ErrorCode.MATERIAL_VARIABLE_ADD_ERROR,"保存单条朋友圈素材变量表信息错误！",e);
		}	
		return  "redirect:" +Global.getAdminPath() + "/cm/materialVariable";
	}
	
	/**
	 * 
	 *
	 * 方法说明：变量名判断
	 *
	 * @param varName
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年12月25日
	 *
	 */
	@RequestMapping(value="editVarName")
	@ResponseBody
	public String editVarName(String varName){
        if(varName.contains("${")){
        	return "true";
        }
          return "false";
	}
	
	
	/**
	 *
	 * 方法说明：修改单条素材变量
	 * @param dto
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @author 李端强 CreateDate: 2017年12月20日
	 */
	@RequiresPermissions("cm:materialVariable:edit")
	@RequestMapping(value = "edit")
	public String update(MaterialVariableDto dto, Model model) {
		try {
			if(dto !=null && dto.getCode()!= null){
				String[] str= dto.getVarContent().split("\\$");
				int i= str.length;
				dto.setVarCount(i);
				dto.setUpateDate(new Date());
				materialVariableService.updateMaterialVariable(dto);
			}
		} catch (Exception e) {
			throw new TsfaServiceException(ErrorCode.MATERIAL_VARIABLE_UPDATE_ERROR,"修改单条朋友圈素材变量表信息错误！",e);
		}	
		return  "redirect:" +Global.getAdminPath() + "/cm/materialVariable";
	}
	
	/**
	 * 方法说明：删除单条素材变量
	 * @param dto
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @author 李端强 CreateDate: 2017年12月20日
	 */
	@RequiresPermissions("cm:materialVariable:edit")
	@RequestMapping(value = "deleteH5")
	public String delete(MaterialVariableDto dto, Model model) {
		try {
			if(dto.getCode()!=null){
				materialVariableService.delMaterialVariable(dto);
			}
		} catch (Exception e) {
			throw new TsfaServiceException(ErrorCode.MATERIAL_VARIABLE_DEL_ERROR,"删除朋友圈素材变量表信息错误！",e);
		}	
		return  "redirect:" +Global.getAdminPath() + "/cm/materialVariable";
	}
}
