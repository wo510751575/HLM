package com.lj.oms.st;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.config.Global;
import com.lj.oms.common.BaseController;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.pagination.PageSortType;
import com.lj.base.core.util.DateUtils;
import com.lj.business.st.dto.mec.FindShopOperationDayReportPage;
import com.lj.business.st.dto.mec.FindShopOperationDayReportPageReturn;
import com.lj.business.st.service.IShopOperationDayReportService;
import com.lj.oms.emus.GradeType;
import com.lj.oms.entity.sys.User;
import com.lj.oms.st.excel.OperationExportExcel;
import com.lj.oms.utils.CcUtils;
import com.lj.oms.utils.UserUtils;
import com.lj.oms.utils.excel.dto.ShopOperationDayReportExportDto;

/**
 * 门诊运营日报
 * 
 *
 * @author 彭俊霖
 * @CreateDate 2018年6月16日
 */
@Controller
@RequestMapping(value = "${adminPath}/st/shopOperationDayReport")
public class ShopOperationDayReportController extends BaseController{
	
	private static final String LIST="modules/st/shopOperationDayReportList";
	
	@Resource
	private IShopOperationDayReportService shopOperationDayReportService;
	
	/**
	 * 
	 *
	 * 方法说明：门诊运营日报报表
	 *
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年06月16日
	 *
	 */
	@RequiresPermissions("st:shopOperationDayReport:view")
	@RequestMapping(value={"/list",""})
	public String list(RedirectAttributes redirectAttributes,FindShopOperationDayReportPage findShopOperationDayReportPage,Integer pageNo,Integer pageSize, Model model){
		try {
			pageSize=pageSize==null?50:pageSize;
			if(pageNo !=null){
				findShopOperationDayReportPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize !=null){
				findShopOperationDayReportPage.setLimit(pageSize);
			}
			
			findShopOperationDayReportPage.setMerchantNo(UserUtils.getMerchantNo());
			//过滤门诊
			User user = UserUtils.getUser();
			Boolean isCompany=false;
			Boolean isShop=false;
			if(GradeType.DEALER.getValue().equals(user.getOffice().getGrade())){
				//起始&结束日期为空时默认昨天
				if(findShopOperationDayReportPage.getReportDateBegin()==null){
					findShopOperationDayReportPage.setReportDateBegin(DateUtils.addDays(new Date(), -1));
				}
				if(findShopOperationDayReportPage.getReportDateEnd()==null){
					findShopOperationDayReportPage.setReportDateEnd(DateUtils.addDays(new Date(), -1));
				}
				findShopOperationDayReportPage.setCompanyNo(user.getOffice().getId());
//				findShopOperationDayReportPage.setShopNos(CcUtils.getShopNoList());
				isCompany=true;
				model.addAttribute("isCompany", isCompany);
			}else if(GradeType.SHOP.getValue().equals(user.getOffice().getGrade())){
				//起始&结束日期为空时默认昨天
				if(findShopOperationDayReportPage.getReportDateBegin()==null){
					findShopOperationDayReportPage.setReportDateBegin(DateUtils.addDays(new Date(), -1));
				}
				if(findShopOperationDayReportPage.getReportDateEnd()==null){
					findShopOperationDayReportPage.setReportDateEnd(DateUtils.addDays(new Date(), -1));
				}
				findShopOperationDayReportPage.setCompanyNo(user.getOffice().getId());
//				findShopOperationDayReportPage.setShopNos(CcUtils.getShopNoList());
				isShop=true;
				model.addAttribute("isShop", isShop);
			}else{
				//日期为空时默认昨天
				if(findShopOperationDayReportPage.getReportDate()==null){
					findShopOperationDayReportPage.setReportDate(DateUtils.addDays(new Date(), -1));
				}
			}
			
			Page<FindShopOperationDayReportPageReturn> pages=shopOperationDayReportService.findShopOperationDayReportPage(findShopOperationDayReportPage);
			List<FindShopOperationDayReportPageReturn> list=Lists.newArrayList();
			list.addAll(pages.getRows());
			
			com.ape.common.persistence.Page<FindShopOperationDayReportPageReturn> page	
			=new com.ape.common.persistence.Page<FindShopOperationDayReportPageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			
			model.addAttribute("page", page);
			model.addAttribute("findShopOperationDayReportPage", findShopOperationDayReportPage);
			
		} catch (Exception e) {
			addMessage(redirectAttributes, "加载数据失败,系统出现异常！");
			logger.error(e.getMessage(),e);
		}
		return LIST;
    }
	
	/**
	 * 方法说明：导出
	 * @param response
	 * @param findShopOperationDayReportPage
	 * @param pageNo
	 * @param pageSize
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 彭俊霖
	 * @CreateDate 2018年6月16日
	 */
	@RequiresPermissions("st:shopOperationDayReport:view")
    @RequestMapping(value = "export")
    public String export(HttpServletResponse response, FindShopOperationDayReportPage findShopOperationDayReportPage, Integer pageNo, Integer pageSize, RedirectAttributes redirectAttributes) {
        try {
        	findShopOperationDayReportPage.setSortDir(PageSortType.desc);
        	findShopOperationDayReportPage.setStart(0);
        	findShopOperationDayReportPage.setLimit(999999);
        	findShopOperationDayReportPage.setMerchantNo(UserUtils.getMerchantNo());
        	
        	//拼装标题栏日期字符串
        	String dateStr="";
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        	//过滤门诊
			User user = UserUtils.getUser();
			if(GradeType.DEALER.getValue().equals(user.getOffice().getGrade()) || GradeType.SHOP.getValue().equals(user.getOffice().getGrade())){
				//起始&结束日期为空时默认昨天
				if(findShopOperationDayReportPage.getReportDateBegin()==null){
					findShopOperationDayReportPage.setReportDateBegin(DateUtils.addDays(new Date(), -1));
				}
				if(findShopOperationDayReportPage.getReportDateEnd()==null){
					findShopOperationDayReportPage.setReportDateEnd(DateUtils.addDays(new Date(), -1));
				}
	            Date reportDateBegin = findShopOperationDayReportPage.getReportDateBegin();
				Date reportDateEnd = findShopOperationDayReportPage.getReportDateEnd();
				if(DateUtils.isSameDay(reportDateBegin, reportDateEnd)){
	            	dateStr=sdf.format(reportDateBegin);
	            }else{
	            	dateStr=sdf.format(reportDateBegin)+"-"+sdf.format(reportDateEnd);
	            }
				findShopOperationDayReportPage.setCompanyNo(user.getOffice().getId());
//				findShopOperationDayReportPage.setShopNos(CcUtils.getShopNoList());
			}else{
				//日期为空时默认昨天
				if(findShopOperationDayReportPage.getReportDate()==null){
					findShopOperationDayReportPage.setReportDate(DateUtils.addDays(new Date(), -1));
				}
				dateStr=sdf.format(findShopOperationDayReportPage.getReportDate());
			}
        	
        	Page<FindShopOperationDayReportPageReturn> pages=shopOperationDayReportService.findShopOperationDayReportPage(findShopOperationDayReportPage);
			List<FindShopOperationDayReportPageReturn> list=Lists.newArrayList();
			list.addAll(pages.getRows());
            
            //转换数据
            for (FindShopOperationDayReportPageReturn modr : list) {
            	//金额，分->元,转换精度(四舍五入,保留两位小数)
            	modr.setOrdOrderAmountStr(Long.valueOf("0").equals(modr.getOrdOrderAmount())?"0.00":new BigDecimal(modr.getOrdOrderAmount() / 100.00).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            	modr.setOrdCustomerPriceStr(Long.valueOf("0").equals(modr.getOrdCustomerPrice())?"0.00":new BigDecimal(modr.getOrdCustomerPrice() / 100.00).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            	modr.setOrdActivitieAmountStr(Long.valueOf("0").equals(modr.getOrdActivitieAmount())?"0.00":new BigDecimal(modr.getOrdActivitieAmount() / 100.00).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            	modr.setOrdPayAmountStr(Long.valueOf("0").equals(modr.getOrdPayAmount())?"0.00":new BigDecimal(modr.getOrdPayAmount() / 100.00).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            	modr.setSuccOrderAmountStr(Long.valueOf("0").equals(modr.getSuccOrderAmount())?"0.00":new BigDecimal(modr.getSuccOrderAmount() / 100.00).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            	modr.setSuccCustomerPriceStr(Long.valueOf("0").equals(modr.getSuccCustomerPrice())?"0.00":new BigDecimal(modr.getSuccCustomerPrice() / 100.00).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            	modr.setSuccActivitieAmountStr(Long.valueOf("0").equals(modr.getSuccActivitieAmount())?"0.00":new BigDecimal(modr.getSuccActivitieAmount() / 100.00).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            	modr.setSuccPayAmountStr(Long.valueOf("0").equals(modr.getSuccPayAmount())?"0.00":new BigDecimal(modr.getSuccPayAmount() / 100.00).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            	//名称转义
            	modr.setShopName(StringEscapeUtils.unescapeHtml4(modr.getShopName()));
            }
            
            String fileName = "门诊运营日报"+dateStr+".xlsx";
            new OperationExportExcel("门诊运营日报("+dateStr+")", ShopOperationDayReportExportDto.class, 2,GradeType.SHOP.getValue()).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            logger.error("导出门诊运营日报数据失败", e);
            addMessage(redirectAttributes, "门诊运营日报数据导出失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + Global.getAdminPath() + "/st/shopOperationDayReport/list?repage";
    }

}
