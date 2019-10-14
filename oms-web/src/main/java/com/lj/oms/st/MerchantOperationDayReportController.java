package com.lj.oms.st;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
import com.lj.business.st.dto.mec.FindMerchantOperationDayReportPage;
import com.lj.business.st.dto.mec.FindMerchantOperationDayReportPageReturn;
import com.lj.business.st.service.IMerchantOperationDayReportService;
import com.lj.oms.emus.GradeType;
import com.lj.oms.st.excel.OperationExportExcel;
import com.lj.oms.utils.CcUtils;
import com.lj.oms.utils.UserUtils;
import com.lj.oms.utils.excel.dto.MerchantOperationDayReportExportDto;

/**
 * 商城运营日报
 * 
 *
 * @author 彭俊霖
 * @CreateDate 2018年6月15日上午10:31:13
 */
@Controller
@RequestMapping(value = "${adminPath}/st/merchantOperationDayReport")
public class MerchantOperationDayReportController extends BaseController{
	
	private static final String LIST="modules/st/merchantOperationDayReportList";
	
	@Resource
	private IMerchantOperationDayReportService merchantOperationDayReportService;
	
	/**
	 * 
	 *
	 * 方法说明：商城运营日报报表
	 *
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年06月15日
	 *
	 */
	@RequiresPermissions("st:merchantOperationDayReport:view")
	@RequestMapping(value={"/list",""})
	public String list(RedirectAttributes redirectAttributes,FindMerchantOperationDayReportPage findMerchantOperationDayReportPage,Integer pageNo,Integer pageSize, Model model){
		try {
			pageSize=pageSize==null?50:pageSize;
			if(pageNo !=null){
				findMerchantOperationDayReportPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize !=null){
				findMerchantOperationDayReportPage.setLimit(pageSize);
			}
			//起始&结束日期为空时默认昨天
			if(findMerchantOperationDayReportPage.getReportDateBegin()==null){
				findMerchantOperationDayReportPage.setReportDateBegin(DateUtils.addDays(new Date(), -1));
			}
			if(findMerchantOperationDayReportPage.getReportDateEnd()==null){
				findMerchantOperationDayReportPage.setReportDateEnd(DateUtils.addDays(new Date(), -1));
			}
			
			findMerchantOperationDayReportPage.setMerchantNo(UserUtils.getMerchantNo());
			
			Page<FindMerchantOperationDayReportPageReturn> pages=merchantOperationDayReportService.findMerchantOperationDayReportPage(findMerchantOperationDayReportPage);
			List<FindMerchantOperationDayReportPageReturn> list=Lists.newArrayList();
			list.addAll(pages.getRows());
			
			com.ape.common.persistence.Page<FindMerchantOperationDayReportPageReturn> page	
			=new com.ape.common.persistence.Page<FindMerchantOperationDayReportPageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			
			model.addAttribute("page", page);
			model.addAttribute("findMerchantOperationDayReportPage", findMerchantOperationDayReportPage);
			
		} catch (Exception e) {
			addMessage(redirectAttributes, "加载数据失败,系统出现异常！");
			logger.error(e.getMessage(),e);
		}
		return LIST;
    }
	
	/**
	 * 方法说明：导出
	 * @param response
	 * @param findMerchantOperationDayReportPage
	 * @param pageNo
	 * @param pageSize
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 彭俊霖
	 * @CreateDate 2018年6月15日下午2:13:54
	 */
	@RequiresPermissions("st:merchantOperationDayReport:view")
    @RequestMapping(value = "export")
    public String export(HttpServletResponse response, FindMerchantOperationDayReportPage findMerchantOperationDayReportPage, Integer pageNo, Integer pageSize, RedirectAttributes redirectAttributes) {
        try {
        	//结束日期为空时默认昨天
			if(findMerchantOperationDayReportPage.getReportDateEnd()==null){
				findMerchantOperationDayReportPage.setReportDateEnd(DateUtils.addDays(new Date(), -1));
			}
        	
        	findMerchantOperationDayReportPage.setSortDir(PageSortType.desc);
        	findMerchantOperationDayReportPage.setStart(0);
        	findMerchantOperationDayReportPage.setLimit(999999);
        	findMerchantOperationDayReportPage.setMerchantNo(UserUtils.getMerchantNo());
        	
        	Page<FindMerchantOperationDayReportPageReturn> pages=merchantOperationDayReportService.findMerchantOperationDayReportPage(findMerchantOperationDayReportPage);
			List<FindMerchantOperationDayReportPageReturn> list=Lists.newArrayList();
			list.addAll(pages.getRows());
            
            //转换数据
            for (FindMerchantOperationDayReportPageReturn modr : list) {
            	//金额，分->元,转换精度(四舍五入,保留两位小数)
            	modr.setOrdOrderAmountStr(Long.valueOf("0").equals(modr.getOrdOrderAmount())?"0.00":new BigDecimal(modr.getOrdOrderAmount() / 100.00).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            	modr.setOrdCustomerPriceStr(Long.valueOf("0").equals(modr.getOrdCustomerPrice())?"0.00":new BigDecimal(modr.getOrdCustomerPrice() / 100.00).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            	modr.setOrdActivitieAmountStr(Long.valueOf("0").equals(modr.getOrdActivitieAmount())?"0.00":new BigDecimal(modr.getOrdActivitieAmount() / 100.00).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            	modr.setOrdPayAmountStr(Long.valueOf("0").equals(modr.getOrdPayAmount())?"0.00":new BigDecimal(modr.getOrdPayAmount() / 100.00).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            	modr.setSuccOrderAmountStr(Long.valueOf("0").equals(modr.getSuccOrderAmount())?"0.00":new BigDecimal(modr.getSuccOrderAmount() / 100.00).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            	modr.setSuccCustomerPriceStr(Long.valueOf("0").equals(modr.getSuccCustomerPrice())?"0.00":new BigDecimal(modr.getSuccCustomerPrice() / 100.00).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            	modr.setSuccActivitieAmountStr(Long.valueOf("0").equals(modr.getSuccActivitieAmount())?"0.00":new BigDecimal(modr.getSuccActivitieAmount() / 100.00).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            	modr.setSuccPayAmountStr(Long.valueOf("0").equals(modr.getSuccPayAmount())?"0.00":new BigDecimal(modr.getSuccPayAmount() / 100.00).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            }
            
            //拼装标题栏日期字符串
            String dateStr="";
            Date reportDateBegin = findMerchantOperationDayReportPage.getReportDateBegin();
			Date reportDateEnd = findMerchantOperationDayReportPage.getReportDateEnd();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			if(DateUtils.isSameDay(reportDateBegin, reportDateEnd)){
            	dateStr=sdf.format(reportDateBegin);
            }else{
            	dateStr=sdf.format(reportDateBegin)+"-"+sdf.format(reportDateEnd);
            }
            
            String fileName = "商城运营日报"+dateStr+".xlsx";
            new OperationExportExcel("商城运营日报("+dateStr+")", MerchantOperationDayReportExportDto.class, 2,GradeType.MERCHANT.getValue()).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            logger.error("导出商城运营日报数据失败", e);
            addMessage(redirectAttributes, "商城运营日报数据导出失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + Global.getAdminPath() + "/st/merchantOperationDayReport/list?repage";
    }

}
