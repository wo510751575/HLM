package com.lj.oms.member;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lj.oms.common.BaseController;
import com.lj.base.core.pagination.Page;
import com.lj.business.member.dto.guidCard.FindGuidCardPage;
import com.lj.business.member.dto.guidCard.FindGuidCardPageReturn;
import com.lj.business.member.service.IGuidCardService;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：导购名片controller
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 梅宏博
 *   
 * CreateDate: 2017年11月13日
 */
@Controller
@RequestMapping(value = "${adminPath}/member/guidCard")
public class GuidCardController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(GuidCardController.class);
	
	@Resource
	private IGuidCardService guidCardService;
	
	/**
	 * 
	 *
	 * 方法说明：导购名片列表
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param findGuidMemberPage
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年11月13日
	 *
	 */
	@RequiresPermissions("member:guidCard:view")
	@RequestMapping(value = {"list", ""})
	public String list( Model model,Integer pageNo,Integer pageSize,FindGuidCardPage findGuidCardPage) {
		try {
			findGuidCardPage.setMerchantNo(UserUtils.getMerchantNo());
//			List<String> shopList = CcUtils.getShopNoList();
//		    if(shopList.size() > 0){
//			  findGuidCardPage.setShopNos(shopList);
//		    }
			
			if(pageNo!=null){
				findGuidCardPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findGuidCardPage.setLimit(pageSize);
			}
			Page<FindGuidCardPageReturn> guidCardPage = guidCardService.findGuidCardPage(findGuidCardPage);
			com.ape.common.persistence.Page<FindGuidCardPageReturn> page =
					new com.ape.common.persistence.Page<>(pageNo==null?1:pageNo, guidCardPage.getLimit(), guidCardPage.getTotal(), new ArrayList<>(guidCardPage.getRows()));
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("param",findGuidCardPage);
		} catch (Exception e) {
			logger.error("获取导购名片信息错误！", e);
		}
		
		return "modules/member/guidCardList";
	}

}
