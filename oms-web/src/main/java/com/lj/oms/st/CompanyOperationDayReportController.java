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
import com.lj.business.st.dto.mec.FindCompanyOperationDayReportPage;
import com.lj.business.st.dto.mec.FindCompanyOperationDayReportPageReturn;
import com.lj.business.st.service.ICompanyOperationDayReportService;
import com.lj.oms.emus.GradeType;
import com.lj.oms.entity.sys.User;
import com.lj.oms.st.excel.OperationExportExcel;
import com.lj.oms.utils.CcUtils;
import com.lj.oms.utils.UserUtils;
import com.lj.oms.utils.excel.dto.CompanyOperationDayReportExportDto;

/**
 * 经销商运营日报
 * 
 *
 * @author 彭俊霖
 * @CreateDate 2018年6月15日上午10:31:13
 */
@Controller
@RequestMapping(value = "${adminPath}/st/companyOperationDayReport")
public class CompanyOperationDayReportController extends BaseController{
	
	private static final String LIST="modules/st/companyOperationDayReportList";
	
	@Resource
	private ICompanyOperationDayReportService companyOperationDayReportService;
	
	/**
	 * 
	 *
	 * 方法说明：经销商运营日报报表
	 *
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年06月15日
	 *
	 */
	@RequiresPermissions("st:companyOperationDayReport:view")
	@RequestMapping(value={"/list",""})
	public String list(RedirectAttributes redirectAttributes,FindCompanyOperationDayReportPage findCompanyOperationDayReportPage,Integer pageNo,Integer pageSize, Model model){
		try {
			pageSize=pageSize==null?50:pageSize;
			if(pageNo !=null){
				findCompanyOperationDayReportPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize !=null){
				findCompanyOperationDayReportPage.setLimit(pageSize);
			}
			
			findCompanyOperationDayReportPage.setMerchantNo(UserUtils.getMerchantNo());
			//过滤经销商
			User user = UserUtils.getUser();
			Boolean isCompany=false;
			if(GradeType.DEALER.getValue().equals(user.getOffice().getGrade())){
				//起始&结束日期为空时默认昨天
				if(findCompanyOperationDayReportPage.getReportDateBegin()==null){
					findCompanyOperationDayReportPage.setReportDateBegin(DateUtils.addDays(new Date(), -1));
				}
				if(findCompanyOperationDayReportPage.getReportDateEnd()==null){
					findCompanyOperationDayReportPage.setReportDateEnd(DateUtils.addDays(new Date(), -1));
				}
				findCompanyOperationDayReportPage.setCompanyNo(user.getOffice().getId());
				isCompany=true;
				model.addAttribute("isCompany", isCompany);
			}else{
				//日期为空时默认昨天
				if(findCompanyOperationDayReportPage.getReportDate()==null){
					findCompanyOperationDayReportPage.setReportDate(DateUtils.addDays(new Date(), -1));
				}
			}
			
			Page<FindCompanyOperationDayReportPageReturn> pages=companyOperationDayReportService.findCompanyOperationDayReportPage(findCompanyOperationDayReportPage);
			List<FindCompanyOperationDayReportPageReturn> list=Lists.newArrayList();
			list.addAll(pages.getRows());
			
			com.ape.common.persistence.Page<FindCompanyOperationDayReportPageReturn> page	
			=new com.ape.common.persistence.Page<FindCompanyOperationDayReportPageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			
			model.addAttribute("page", page);
			model.addAttribute("findCompanyOperationDayReportPage", findCompanyOperationDayReportPage);
			
		} catch (Exception e) {
			addMessage(redirectAttributes, "加载数据失败,系统出现异常！");
			logger.error(e.getMessage(),e);
		}
		return LIST;
    }
	
	/**
	 * 方法说明：导出
	 * @param response
	 * @param findCompanyOperationDayReportPage
	 * @param pageNo
	 * @param pageSize
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 彭俊霖
	 * @CreateDate 2018年6月15日下午2:13:54
	 */
	@RequiresPermissions("st:companyOperationDayReport:view")
    @RequestMapping(value = "export")
    public String export(HttpServletResponse response, FindCompanyOperationDayReportPage findCompanyOperationDayReportPage, Integer pageNo, Integer pageSize, RedirectAttributes redirectAttributes) {
        try {
        	findCompanyOperationDayReportPage.setSortDir(PageSortType.desc);
        	findCompanyOperationDayReportPage.setStart(0);
        	findCompanyOperationDayReportPage.setLimit(999999);
        	findCompanyOperationDayReportPage.setMerchantNo(UserUtils.getMerchantNo());
        	
        	//拼装标题栏日期字符串
        	String dateStr="";
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        	//过滤经销商
			User user = UserUtils.getUser();
			if(GradeType.DEALER.getValue().equals(user.getOffice().getGrade())){
				findCompanyOperationDayReportPage.setCompanyNo(user.getOffice().getId());
				
	            Date reportDateBegin = findCompanyOperationDayReportPage.getReportDateBegin();
				Date reportDateEnd = findCompanyOperationDayReportPage.getReportDateEnd();
	            if(DateUtils.isSameDay(reportDateBegin, reportDateEnd)){
	            	dateStr=sdf.format(reportDateBegin);
	            }else{
	            	dateStr=sdf.format(reportDateBegin)+"-"+sdf.format(reportDateEnd);
	            }
			}else{
				dateStr=sdf.format(findCompanyOperationDayReportPage.getReportDate());
			}
        	
        	Page<FindCompanyOperationDayReportPageReturn> pages=companyOperationDayReportService.findCompanyOperationDayReportPage(findCompanyOperationDayReportPage);
			List<FindCompanyOperationDayReportPageReturn> list=Lists.newArrayList();
			list.addAll(pages.getRows());
            
            //转换数据
            for (FindCompanyOperationDayReportPageReturn modr : list) {
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
            
            String fileName = "经销商运营日报"+dateStr+".xlsx";
            new OperationExportExcel("经销商运营日报("+dateStr+")", CompanyOperationDayReportExportDto.class, 2,GradeType.DEALER.getValue()).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            logger.error("导出经销商运营日报数据失败", e);
            addMessage(redirectAttributes, "经销商运营日报数据导出失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + Global.getAdminPath() + "/st/companyOperationDayReport/list?repage";
    }

}
