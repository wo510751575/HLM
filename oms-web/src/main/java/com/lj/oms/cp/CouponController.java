package com.lj.oms.cp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.config.Global;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.business.cp.dto.coupon.FindCoupon;
import com.lj.business.cp.dto.coupon.FindCouponPage;
import com.lj.business.cp.dto.coupon.FindCouponPageReturn;
import com.lj.business.cp.emus.CouponStatus;
import com.lj.business.cp.service.ICouponService;
import com.lj.oms.common.BaseController;
import com.lj.oms.utils.DateUtil;
import com.lj.oms.utils.UserUtils;
import com.lj.oms.utils.excel.ExportExcel;
import com.lj.oms.utils.excel.dto.CouponOutDto;
/**
 * 
 * 
 * 类说明：优惠券表
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年9月15日
 */
@Controller
@RequestMapping(value = "${adminPath}/cp/coupon")
public class CouponController  extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(CouponController.class);
	
	@Resource
	private ICouponService couponService; 
	
	/**列表返回地址*/
	private static final String LIST = "modules/cp/couponList";
	
	/**重定向*/
	private static final String EDIT = "redirect:"+Global.getAdminPath()+"/member/manager/?repage";
	
	/**商户编号*/
	private static final String MERCHANT_NO = "merchantNo";
	
	/**终端集合*/
	private static final String SHOP_NO_S   = "shopNos";
	
	/**返回分页参数*/
	private static final String PAGE = "page"; 
	
	/**返回参数*/
	private static final String COUPON_STATUSS = "couponStatuss";
	/**开始时间*/
	private static final String START_TIME = "startTime";
	
	/**结束时间*/
	private static final String END_TIME = "endTime";
	
	/**
	 * 
	 *
	 * 方法说明：分页查询
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param findCouponPage
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年9月15日
	 *
	 */
	@RequestMapping(value = {"list", ""})
	public String list(Model model,Integer pageNo,Integer pageSize,FindCouponPage findCouponPage ){
		try {
			if(pageNo!=null){
				findCouponPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findCouponPage.setLimit(pageSize);
			}
		  findCouponPage.setMerchantNo(UserUtils.getMerchantNo());
		  model.addAttribute("findCouponPage",findCouponPage);//返回原始查询条件
		  
		 Page<FindCouponPageReturn>	pages=couponService.findCouponPage(findCouponPage);
		 System.out.println(pages);
		 List<FindCouponPageReturn> list = Lists.newArrayList();
		 list.addAll(pages.getRows());
		 com.ape.common.persistence.Page<FindCouponPageReturn> page=new com.ape.common.persistence.Page<FindCouponPageReturn>(pageNo==null?0:pageNo, pages.getLimit(), pages.getTotal(), list); 
		 page.initialize();
		 model.addAttribute(PAGE ,page); 
		 model.addAttribute(COUPON_STATUSS ,CouponStatus.values());
		} catch (Exception e) {
			logger.error("获取优惠券信息错误！",e);
		}
		return LIST;
	} 
	/**
	 * 
	 *
	 * 方法说明：优惠券数据导出
	 *
	 * @param response
	 * @param findManagerMemberPage
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年9月16日
	 *
	 */
	@RequiresPermissions("cp:coupon:view")
    @RequestMapping(value = "export")
    public String export(HttpServletResponse response,FindCoupon findCoupon, RedirectAttributes redirectAttributes) {
		try {
			  findCoupon.setMerchantNo(UserUtils.getMerchantNo());
//			  findCoupon.setShopNos(CcUtils.getShopNoList());
			  Map<String,Date> returnDate =  DateUtil.initDate(findCoupon.getStartTime(), findCoupon.getEndTime());
			  findCoupon.setStartTime(returnDate.get(START_TIME));
			  findCoupon.setEndTime(returnDate.get(END_TIME));
			   //枚举转义
			  if(StringUtils.isNotBlank(findCoupon.getCouponStatus())){
					for (CouponStatus item : CouponStatus.values()) {
						if (item.getName().equals(findCoupon.getCouponStatus().trim())) {
							findCoupon.setCouponStatus(item.toString());
							break;
						}
					}
			  }
			 List<FindCouponPageReturn>  list= couponService.findCouponReturnList(findCoupon);
			 //枚举转义
			 for(FindCouponPageReturn couponPageReturn:list){
				 for(CouponStatus couponStatus:CouponStatus.values()){
						if(couponStatus.name().equals(couponPageReturn.getCouponStatus())){
							couponPageReturn.setCouponStatus(couponStatus.getName());
							break;
				 }
			     }
			 }
    		String fileName = "优惠券数据导出.xlsx";
    		new ExportExcel("优惠券数据导出", CouponOutDto.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "优惠券数据导出失败！失败信息："+e.getMessage());
		}
		
		return EDIT;
    }

}
