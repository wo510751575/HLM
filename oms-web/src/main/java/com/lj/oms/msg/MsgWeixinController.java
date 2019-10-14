package com.lj.oms.msg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.oms.common.BaseController;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.business.cm.dto.FindMaterialCommen;
import com.lj.business.cm.dto.FindMaterialCommenPage;
import com.lj.business.cm.dto.FindMaterialCommenPageReturn;
import com.lj.business.cm.dto.FindMaterialCommenReturn;
import com.lj.business.cm.service.IMaterialCommenService;
import com.lj.business.member.dto.FindPmTypePageReturn;
import com.lj.business.member.service.IPmTypeService;
import com.lj.oms.entity.sys.Office;
import com.lj.oms.service.sys.OfficeService;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：微信消息Controller
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2017年7月11日
 */
@Controller
@RequestMapping(value = "${adminPath}/msg/weixin")
public class MsgWeixinController  extends BaseController{
	
    @Resource //公用素材
	private IMaterialCommenService materialcommenService;

	@Resource //客户分组
	private IPmTypeService pmTypeService;
	
	/**
	 * 机构服务
	 */
	@Autowired
	private OfficeService officeService;
	
	/**
	 * 
	 *
	 * 方法说明：微信已发送列表
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月14日
	 *
	 */
	@RequestMapping(value={"list", ""})
	public String  list(Model model,Integer pageNo,Integer pageSize){
		 		
		return "modules/msg/weixinList";
	}
	
	/**
	 * 
	 *
	 * 方法说明：多维度选择客户发送
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月14日
	 *
	 */
	@RequestMapping(value="form")
	public String form(Model model,Integer pageNo,Integer pageSize){
		try {
			//获取公用素材下拉框信息
			FindMaterialCommenPage findMaterialCommenPage=new FindMaterialCommenPage();
			findMaterialCommenPage.setMerchantNo(UserUtils.getUser().getCompany().getId());
			findMaterialCommenPage.setLimit(100);
			Page<FindMaterialCommenPageReturn> pages = materialcommenService.findMaterialCommenPage(findMaterialCommenPage);
			List<FindMaterialCommenPageReturn> commenList = Lists.newArrayList();
			commenList.addAll(pages.getRows());	  
			model.addAttribute("commenList",commenList);
			//获取客户分组下拉信息
			FindPmTypePageReturn findPmTypePageReturn=new FindPmTypePageReturn();
			findPmTypePageReturn.setStatus("Y");
			findPmTypePageReturn.setMerchantNo(UserUtils.getUser().getCompany().getId());
			List<FindPmTypePageReturn> pmType=pmTypeService.findPmTypePages(findPmTypePageReturn);
			model.addAttribute("pmType",pmType);
		} catch (Exception e) {
			logger.error("",e);
		}
		return "modules/msg/weixinForm";	
	}
	
	
	/**
	 * 
	 *
	 * 方法说明：公共素材列表信息
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月14日
	 *
	 */
	@RequestMapping(value="materialList")
	@ResponseBody
	public List<FindMaterialCommenPageReturn> findMaterial(Model model,Integer pageNo,Integer pageSize,FindMaterialCommenPage findMaterialCommenPage){
		try {
			//获取公用素材下拉框信息
			findMaterialCommenPage.setMerchantNo(UserUtils.getUser().getCompany().getId());
			List<FindMaterialCommenPageReturn> commenList=materialcommenService.findMaterialCommenList(findMaterialCommenPage);
			return commenList;
		} catch (Exception e) {
			logger.error("",e);
		}
		return new ArrayList<FindMaterialCommenPageReturn>();	
	}
	
	/**
	 * 公共素材展示
	 * @param findMaterialCommen
	 * @param model
	 * @return
	 */
	@RequestMapping(value="materialShow")
	@ResponseBody
	public Map<String,Object> viewH5(FindMaterialCommen findMaterialCommen, Model model) {
		Map<String,Object> MaterialInfo=new HashMap<String,Object>();
		try {
			if(findMaterialCommen!=null && findMaterialCommen.getCode()!=null){
				FindMaterialCommenReturn findMaterialCommenReturn= materialcommenService.findMaterialCommen(findMaterialCommen);
				MaterialInfo.put("MaterialCommen", findMaterialCommenReturn);
				
				if(!findMaterialCommenReturn.getMerchantNo().isEmpty()){
					Office company= officeService.get(officeService.get(findMaterialCommenReturn.getMerchantNo()));
					MaterialInfo.put("company", company);
				}
			}
		} catch (Exception e) {
			logger.error("",e);
		}		
		return MaterialInfo;
	}
	
}
