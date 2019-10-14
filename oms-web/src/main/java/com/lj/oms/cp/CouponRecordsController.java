package com.lj.oms.cp;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lj.oms.common.BaseController;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.business.cp.couponRecords.FindCouponRecordsPage;
import com.lj.business.cp.couponRecords.FindCouponRecordsPageReturn;
import com.lj.business.cp.service.ICouponRecordsService;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：消费记录
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2018年1月24日
 */
@Controller
@RequestMapping(value="${adminPath}/cp/couponRecords")
public class CouponRecordsController extends BaseController{
	  
	private static final Logger logger = LoggerFactory.getLogger(CouponRecordsController.class);
	/**
	 * 列表地址
	 */
	private static final String LIST = "modules/cp/couponRecordsList";
	
	private static final String PAGE = "page";

	
	@Resource
	private  ICouponRecordsService couponRecordsService;
	
	
	@RequestMapping(value={"list",""})
	public String list(Model model,Integer pageNo,Integer pageSize,FindCouponRecordsPage findCouponRecordsPage){
		try {
			
			findCouponRecordsPage.setStart(pageNo == null ? 0:(pageNo-1)*pageSize);
			findCouponRecordsPage.setLimit(pageSize == null ? 10:pageSize);
			/**获取缓存用户信息*/
			findCouponRecordsPage.setMerchantNo(UserUtils.getMerchantNo());
//			findCouponRecordsPage.setShopNos(CcUtils.getShopNoList());
			/***
			 * 分页查询并组装分页参数
			 */
			Page<FindCouponRecordsPageReturn> pages =couponRecordsService.findCouponRecordsPage(findCouponRecordsPage);
			List<FindCouponRecordsPageReturn> list = Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<FindCouponRecordsPageReturn> page
			= new com.ape.common.persistence.Page<FindCouponRecordsPageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list); 
			page.initialize();
			model.addAttribute(PAGE,page);
			model.addAttribute("findCouponRecordsPage", findCouponRecordsPage);
		} catch (Exception e) {
		  logger.error("查询消费信息错误！",e);
		}
		return LIST;
	}

}
