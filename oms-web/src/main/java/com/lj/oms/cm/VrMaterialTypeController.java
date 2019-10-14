package com.lj.oms.cm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.config.Global;
import com.lj.oms.common.BaseController;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.GUID;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.cm.dto.vrMaterialType.AddVrMaterialType;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialType;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialTypePage;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialTypePageReturn;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialTypeReturn;
import com.lj.business.cm.dto.vrMaterialType.UpdateVrMaterialType;
import com.lj.business.cm.dto.vrMaterialTypeCategory.AddVrMaterialTypeCategory;
import com.lj.business.cm.dto.vrMaterialTypeCategory.FindVrMaterialTypeCategory;
import com.lj.business.cm.dto.vrMaterialTypeCategory.FindVrMaterialTypeCategoryReturn;
import com.lj.business.cm.dto.vrMaterialTypeCategory.UpdateVrMaterialTypeCategory;
import com.lj.business.cm.service.IVrMaterialTypeCategoryService;
import com.lj.business.cm.service.IVrMaterialTypeService;
import com.lj.oms.utils.DictUtils;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：vr素材类型/素材action
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年12月9日
 */

@RequestMapping(value="${adminPath}/cm/vrMaterialType")
@Controller
public class VrMaterialTypeController {
	
	private static final Logger logger = LoggerFactory.getLogger(VrMaterialTypeController.class);
	
	@Resource
	private IVrMaterialTypeService vrMaterialTypeService;
	
	@Resource
	private  IVrMaterialTypeCategoryService vrMaterialTypeCategoryService;
	/**
	 * 重定向页面
	 */
	private static final String FORM ="redirect:" +Global.getAdminPath()+ "/cm/vrMaterialType/";
	/**
	 * 编辑页面
	 */
	private static final String EDIT = "modules/cm/vrMaterialTypeForm";
	/**
	 * 列表地址
	 */
	private static final String LIST ="modules/cm/vrMaterialTypeList";
	/**
	 * json参数定义
	 */
	private static final String CATEGORY_LIST = "categoryList";
	/**
	 * 返回前端数据参数名
	 */
	private static final String DATA = "data";
	
	/**
	 * 返回前端数据分页参数名
	 */
	private static final String PAGE = "page";
	/**
	 * 消息返回参数
	 * 
	 */
	private static final String REP_MSG = "repMsg";
	
	
	
	/**
	 * 列表数据查询  （分页）
	 * @param model
	 * @param findVrMaterialTypePage
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value={"list",""})
	public String list(Model model,FindVrMaterialTypePage findVrMaterialTypePage,Integer pageNo ,Integer pageSize){
		try {
			if(pageNo!=null){
				findVrMaterialTypePage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findVrMaterialTypePage.setLimit(pageSize);
			}
			findVrMaterialTypePage.setMerchantNo(UserUtils.getUser().getCompany().getId());
		    Page<FindVrMaterialTypePageReturn> pages = vrMaterialTypeService.findVrMaterialTypePage(findVrMaterialTypePage);
		    List<FindVrMaterialTypePageReturn> list = Lists.newArrayList();
		    list.addAll(pages.getRows());
		    com.ape.common.persistence.Page<FindVrMaterialTypePageReturn> page
		    =new com.ape.common.persistence.Page<FindVrMaterialTypePageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);
		    page.initialize();
		    
		    model.addAttribute(PAGE, page);
		    model.addAttribute("findVrMaterialTypePage", findVrMaterialTypePage);
		} catch (Exception e) {
			logger.error("获取VR素材类型信息错误！");
		}
		return LIST;
		
	}
	/**
	 * 编辑页面数据
	 * @param model
	 * @param findVrMaterialType
	 * @return
	 */
	@RequestMapping(value="from")
     public String from(Model model,FindVrMaterialType findVrMaterialType){
    	 try {
    		 if(findVrMaterialType !=null && findVrMaterialType.getCode()!=null){
    			 //code为空则直接返回空数据
    			 if(StringUtils.isNotBlank(findVrMaterialType.getCode())){
    				 
	    			 FindVrMaterialTypeReturn findVrMaterialTypeReturn= vrMaterialTypeService.findVrMaterialType(findVrMaterialType);
	    			 FindVrMaterialTypeCategory findVrMaterialTypeCategory = new FindVrMaterialTypeCategory();
	    			 findVrMaterialTypeCategory.setTypeCode(findVrMaterialTypeReturn.getCode());
	    			 List<FindVrMaterialTypeCategoryReturn> list =  vrMaterialTypeCategoryService.findVrMaterialTypeCategoryReturnList(findVrMaterialTypeCategory);
	    			 model.addAttribute("categoryNameList",list);
	    			 model.addAttribute(DATA, findVrMaterialTypeReturn);
    			 }else{
    				//回显添加失败时的信息
    				 model.addAttribute(DATA, findVrMaterialType);
    			 }
    		 }
			 
		} catch (Exception e) {
			logger.error("获取VR素材类型信息错误！");
		}
         return  EDIT;
     }
	/**
	 * 新增方法
	 * @param redirectAttributes
	 * @param addVrMaterialType
	 * @param model
	 * @return
	 */
	@RequestMapping(value="save")
	public String save(RedirectAttributes redirectAttributes,AddVrMaterialType addVrMaterialType, Model model){
		try {
			//验证显示序号不能重复
			FindVrMaterialType findVrMaterialType=new FindVrMaterialType();
			findVrMaterialType.setMerchantNo(UserUtils.getUser().getCompany().getId());
			List<Integer> showIndexList = vrMaterialTypeService.findVrMaterialTypeShowIndexList(findVrMaterialType);
			if ("true".equals(DictUtils.hasShowIndex(showIndexList,addVrMaterialType.getShowIndex()))){
				model.addAttribute(REP_MSG, "保存VR素材类型'" + addVrMaterialType.getTypeName() + "'失败，显示序号已存在");
				BeanUtils.copyProperties(addVrMaterialType, findVrMaterialType);
				return from(model, findVrMaterialType);
			}
			
			String createName = UserUtils.getUser().getName();
			addVrMaterialType.setMerchantNo(UserUtils.getUser().getCompany().getId());
			addVrMaterialType.setCode(GUID.getPreUUID());
			if(addVrMaterialType.getCategoryName() != null ){
				//过滤HTML标签
			    String categoryNames= StringEscapeUtils.unescapeHtml4(addVrMaterialType.getCategoryName()).toString();
			    
				Map<String, Class> map = new HashMap<>();
				map.put(CATEGORY_LIST, AddVrMaterialTypeCategory.class);
				//解析json串
				AddVrMaterialTypeCategory addVrMaterialTypeCategory =(AddVrMaterialTypeCategory)JsonUtils.objectFromJson(categoryNames, AddVrMaterialTypeCategory.class,map);
				
				List<AddVrMaterialTypeCategory> list = addVrMaterialTypeCategory.getCategoryList();
				int i=list.size();
				for(AddVrMaterialTypeCategory category :list){
		        	addVrMaterialTypeCategory.setTypeCode(addVrMaterialType.getCode());
		        	addVrMaterialTypeCategory.setCategoryName(category.getCategoryName());
		        	addVrMaterialTypeCategory.setShowIndex(i--);
		        	addVrMaterialTypeCategory.setCreateId(createName);
		        	vrMaterialTypeCategoryService.addVrMaterialTypeCategory(addVrMaterialTypeCategory);
				}
			}
			addVrMaterialType.setCreateId(createName);
			vrMaterialTypeService.addVrMaterialType(addVrMaterialType);
		} catch (Exception e) {
			logger.error("保存VR素材类型信息错误！",e);
		}
		return FORM;
	}
	/**
	 * 修改方法
	 * @param redirectAttributes
	 * @param updateVrMaterialType
	 * @param model
	 * @return
	 */
	@RequestMapping(value="edit")
	public String edit(RedirectAttributes redirectAttributes,UpdateVrMaterialType updateVrMaterialType, Model model){
		try {
			if(updateVrMaterialType.getCode() !=null){
				//如果本次修改的显示序号有变才校验
				FindVrMaterialType findVrMaterialType=new FindVrMaterialType();
				findVrMaterialType.setCode(updateVrMaterialType.getCode());
				FindVrMaterialTypeReturn findVrMaterialTypeReturn = vrMaterialTypeService.findVrMaterialType(findVrMaterialType);
				
				if(!updateVrMaterialType.getShowIndex().equals(findVrMaterialTypeReturn.getShowIndex())){
					findVrMaterialType.setMerchantNo(UserUtils.getUser().getCompany().getId());
					List<Integer> showIndexList=vrMaterialTypeService.findVrMaterialTypeShowIndexList(findVrMaterialType);
					
					if ("true".equals(DictUtils.hasShowIndex(showIndexList,updateVrMaterialType.getShowIndex()))){
						model.addAttribute(REP_MSG, "修改VR素材类型'" + updateVrMaterialType.getTypeName() + "'失败，显示序号已存在");
						return from(model, findVrMaterialType);
					}
				}
				
				String createName = UserUtils.getUser().getName();
				//过滤HTML标签
				String categoryNames= StringEscapeUtils.unescapeHtml4(updateVrMaterialType.getCategoryName()).toString();
			    Map<String, Class> map = new HashMap<>();
			    map.put(CATEGORY_LIST, UpdateVrMaterialType.class);
				UpdateVrMaterialType updateVrMaterialType1 =(UpdateVrMaterialType)JsonUtils.objectFromJson(categoryNames, UpdateVrMaterialType.class,map);
					
				List<UpdateVrMaterialType> list = updateVrMaterialType1.getCategoryList();
				int i=list.size();
				for(UpdateVrMaterialType materialType:list){
					UpdateVrMaterialTypeCategory updateVrMaterialTypeCategory =  new UpdateVrMaterialTypeCategory();
					//code为空则为新增数据
					if(StringUtils.isBlank(materialType.getCode())){
						AddVrMaterialTypeCategory addVrMaterialTypeCategory = new AddVrMaterialTypeCategory();
						addVrMaterialTypeCategory.setTypeCode(updateVrMaterialType.getCode());
				        addVrMaterialTypeCategory.setCategoryName(materialType.getCategoryName());
				        addVrMaterialTypeCategory.setShowIndex(i--);
				        addVrMaterialTypeCategory.setCreateId(createName);
				        vrMaterialTypeCategoryService.addVrMaterialTypeCategory(addVrMaterialTypeCategory);
					}else{
						updateVrMaterialTypeCategory.setCode(materialType.getCode());
						updateVrMaterialTypeCategory.setCategoryName(materialType.getCategoryName());
						vrMaterialTypeCategoryService.updateVrMaterialTypeCategory(updateVrMaterialTypeCategory);
					}
					
					}
				vrMaterialTypeService.updateVrMaterialType(updateVrMaterialType);
			}
		} catch (Exception e) {
			logger.error("保存VR素材类型信息错误！",e);
		}
		return FORM;
	}
	
}
