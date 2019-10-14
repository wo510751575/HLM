package com.lj.business.api.controller.imh5;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ape.common.utils.DateUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.api.controller.Action;
import com.lj.business.cm.dto.MaterialVariableDto;
import com.lj.business.cm.service.IMaterialVariableService;
import com.lj.distributecache.RedisCache;
import com.lj.oms.entity.sys.User;

/**
 * 类说明：朋友圈素材变量@Controller
 * <p>
 * 详细描述：朋友圈素材变量表的控制器
 * @Company: 扬恩科技有限公司
 * @author 李端强
 * CreateDate: 2017年12月21日16:35:12
 */
@Controller
@RequestMapping(value = "/imh5/materialVariable/")
public class MaterialVariableController extends Action {
	private static Logger logger = LoggerFactory.getLogger(MaterialVariableController.class);
	@Autowired 
    private RedisCache redisCache;
	
	@Autowired
	private IMaterialVariableService materialVariableService;//朋友圈素材变量服务
	
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
	
	/**
	 * 方法说明：获取素材变量列表数据
	 * @param MaterialVariableDto
	 * @param model
	 * @return
	 * @author 李端强 CreateDate: 2017年12月20日
	 */
	@RequestMapping(value = {"list.do"})
	@ResponseBody
	public Map<String, Object> list(Integer pageNo,Integer pageSize,MaterialVariableDto materialVariable, String userId) {
	    Map<String, Object> retMap = Maps.newHashMapWithExpectedSize(3);
		Page<MaterialVariableDto> retPage = null;
		try {
			if(materialVariable==null) {
				materialVariable = new MaterialVariableDto();
			}
			if(pageNo!=null){
				materialVariable.setStart((pageNo-1)*pageSize);
			} else {
                pageNo = 1;
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
			materialVariable.setMerchantNo(getUserByCache(userId).getCompany().getId());
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
//			com.ape.common.persistence.Page<MaterialVariableDto> page=new com.ape.common.persistence.Page<MaterialVariableDto>(pageNo==null?1:pageNo, retPage.getLimit(), retPage.getTotal(), list);
//			page.initialize();
			retMap.put("success", Boolean.TRUE);
			retMap.put("data", retPage);
		} catch (Exception e) {
		    logger.error("查询朋友圈素材变量表信息错误！", e);
			retMap.put("success", Boolean.FALSE);
			retMap.put("msg", "查询朋友圈素材变量表信息错误");
		}
		return retMap;
	}
    /**
     * 
     *
     * 方法说明：变量说明，变量列表条件查询
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
	@RequestMapping(value ="returnList.do")
	@ResponseBody
	public Map<String, Object> returnList(Integer pageNo,Integer pageSize,MaterialVariableDto materialVariable) {
	    Map<String, Object> retMap = Maps.newHashMapWithExpectedSize(3);
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
			
			retMap.put("success", Boolean.TRUE);
            retMap.put("data", retPage);
		} catch (Exception e) {
			logger.error("朋友圈素材变量表操作--查询异常", e);
            retMap.put("success", Boolean.FALSE);
            retMap.put("msg", "朋友圈素材变量表操作--查询异常");
		}
		return retMap;
	}	
	
	
	/**
	 *
	 * 方法说明：查询单个素材变量
	 * @param friendsImageMaterial
	 * @param model
	 * @return
	 * @author 李端强 CreateDate: 2017年12月20日
	 *
	 */
	@RequestMapping(value = "findByCode.do")
	@ResponseBody
	public Map<String, Object> form(MaterialVariableDto materialVariable) {
	    Map<String, Object> retMap = Maps.newHashMapWithExpectedSize(3);
		try {
			if(materialVariable!=null && materialVariable.getCode()!=null) {
				//查询单条
			    MaterialVariableDto materialVariableDto = materialVariableService.findMaterialVariableByKey(materialVariable.getCode());
			    retMap.put("success", Boolean.TRUE);
	            retMap.put("data", materialVariableDto);
			}
		} catch (Exception e) {
			logger.error("查询变量错误！", e);
			retMap.put("success", Boolean.FALSE);
            retMap.put("msg", "查询变量错误");
		}
		
		return retMap;
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
	@RequestMapping(value = "save.do")
	@ResponseBody
	public Map<String, Object> save(MaterialVariableDto dto, String userId) {
	    Map<String, Object> retMap = Maps.newHashMapWithExpectedSize(3);
		try {
			dto.setMerchantNo(getUserByCache(userId).getCompany().getId());
			dto.setCode(GUID.getPreUUID());
			dto.setCreateDate(new Date());
			dto.setUpateDate(new Date());
			dto.setSysFlag(0);
			String[] str = dto.getVarContent().toString().split("\\$");
			int i = str.length;
		    dto.setVarCount(i);
			materialVariableService.addMaterialVariable(dto);
			
			retMap.put("success", Boolean.TRUE);
		} catch (Exception e) {
			logger.error("保存单条朋友圈素材变量表信息错误", e);
            retMap.put("success", Boolean.FALSE);
            retMap.put("msg", "保存单条朋友圈素材变量表信息错误");
		}	
		return  retMap;
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
	@RequestMapping(value="editVarName.do")
	@ResponseBody
	public Map<String, Object> editVarName(String varName){
	    Map<String, Object> retMap = Maps.newHashMapWithExpectedSize(1);
        if(varName.contains("${")){
            retMap.put("success", Boolean.TRUE);
        } else {
            retMap.put("success", Boolean.FALSE);
        }
        return retMap;
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
	@RequestMapping(value = "edit.do")
	@ResponseBody
	public Map<String, Object> update(MaterialVariableDto dto) {
	    Map<String, Object> retMap = Maps.newHashMapWithExpectedSize(3);
		try {
			if(dto !=null && dto.getCode()!= null){
				String[] str= dto.getVarContent().split("\\$");
				int i= str.length;
				dto.setVarCount(i);
				dto.setUpateDate(new Date());
				materialVariableService.updateMaterialVariable(dto);
				
				retMap.put("success", Boolean.TRUE);
			} else {
			    retMap.put("success", Boolean.FALSE);
	            retMap.put("msg", "参数错误");
            }
		} catch (Exception e) {
			logger.error("修改单条朋友圈素材变量表信息错误", e);
            retMap.put("success", Boolean.FALSE);
            retMap.put("msg", "修改单条朋友圈素材变量表信息错误");
		}	
		return retMap;
	}
	
	/**
	 * 方法说明：删除单条素材变量
	 * @param dto
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @author 李端强 CreateDate: 2017年12月20日
	 */
	@RequestMapping(value = "deleteH5.do")
	public Map<String, Object> delete(MaterialVariableDto dto) {
	    Map<String, Object> retMap = Maps.newHashMapWithExpectedSize(3);
		try {
			if(dto.getCode()!=null){
				materialVariableService.delMaterialVariable(dto);
				
				retMap.put("success", Boolean.TRUE);
			} else {
			    retMap.put("success", Boolean.FALSE);
                retMap.put("msg", "参数错误");
            }
		} catch (Exception e) {
			logger.error("删除朋友圈素材变量表信息错误", e);
            retMap.put("success", Boolean.FALSE);
            retMap.put("msg", "删除朋友圈素材变量表信息错误");
		}	
		return retMap;
	}
}
