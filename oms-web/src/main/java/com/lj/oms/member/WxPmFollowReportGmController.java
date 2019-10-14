//package com.lj.oms.member;
//
//import java.util.Date;
//import java.util.List;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.ape.common.config.Global;
//import com.lj.oms.common.BaseController;
//import com.google.common.collect.Lists;
//import com.lj.base.core.pagination.Page;
//import com.lj.base.core.util.DateUtils;
//import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportCompanyPage;
//import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportCompanyPageReturn;
//import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportGmPage;
//import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportGmPageReturn;
//import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportShopPage;
//import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportShopPageReturn;
//import com.lj.business.st.service.IWxPmFollowReportCompanyService;
//import com.lj.business.st.service.IWxPmFollowReportGmService;
//import com.lj.business.st.service.IWxPmFollowReportShopService;
//import com.lj.oms.utils.UserUtils;
//import com.lj.oms.utils.excel.ExportExcel;
//import com.lj.oms.utils.excel.dto.WxPmFollowReportCompanyDto;
//import com.lj.oms.utils.excel.dto.WxPmFollowReportGmDto;
//import com.lj.oms.utils.excel.dto.WxPmFollowReportShopDto;
//
//@Controller
//@RequestMapping(value = "${adminPath}/member/wxPmFollow")
//public class WxPmFollowReportGmController  extends BaseController{
//    
////    @Autowired
////    private IWxPmFollowReportGmService wxPmFollowReportGmService;
////    @Autowired
////    private IWxPmFollowReportShopService wxPmFollowReportShopService;
////    @Autowired
////    private IWxPmFollowReportCompanyService wxPmFollowReportCompanyService;
//    
//    @RequiresPermissions("member:wxPmFollowGm:view")
//    @RequestMapping("/gm/list")
//    public String wxPmFollowGmList(Model model,Integer pageNo,Integer pageSize,FindWxPmFollowReportGmPage findWxPmFollowReportGmPage){
//        try {
//            if(pageNo!=null){
//                findWxPmFollowReportGmPage.setStart((pageNo-1)*pageSize);
//            }
//            if(pageSize!=null){
//                findWxPmFollowReportGmPage.setLimit(pageSize);
//            }
//            if (findWxPmFollowReportGmPage.getReportDate() == null) {
//                findWxPmFollowReportGmPage.setReportDate(DateUtils.addDays(new Date(), -1));
//            }
//            findWxPmFollowReportGmPage.setMerchantNo(UserUtils.getMerchantNo());
//            
//            Page<FindWxPmFollowReportGmPageReturn> pageDto = wxPmFollowReportGmService.findWxPmFollowReportGmPage(findWxPmFollowReportGmPage);
//            List<FindWxPmFollowReportGmPageReturn> list = Lists.newArrayListWithExpectedSize(pageDto.getRows().size());
//            list.addAll(pageDto.getRows());
//
//            com.ape.common.persistence.Page<FindWxPmFollowReportGmPageReturn> page = new com.ape.common.persistence.Page<FindWxPmFollowReportGmPageReturn>(pageNo == null ? 1 : pageNo, pageDto.getLimit(),
//                    pageDto.getTotal(), list);
//            page.initialize();
//            
//            model.addAttribute("page", page);
//            model.addAttribute("findWxPmFollowReportGmPage", findWxPmFollowReportGmPage);
//        } catch (Exception e) {
//            logger.error("分页查询导购微信客户跟踪日报错误！",e);
//        }
//        return "modules/member/wxPmFollowReportGmList";
//    }
//    
//    @RequiresPermissions("member:wxPmFollowGm:view")
//    @RequestMapping("/gm/export")
//    public String wxPmFollowGmExport(HttpServletResponse response, Integer pageNo,Integer pageSize,FindWxPmFollowReportGmPage findWxPmFollowReportGmPage, RedirectAttributes redirectAttributes){
//        try {
//            findWxPmFollowReportGmPage.setStart(0);
//            findWxPmFollowReportGmPage.setMaxLimit();
//            findWxPmFollowReportGmPage.setMerchantNo(UserUtils.getMerchantNo());
//            
//            Page<FindWxPmFollowReportGmPageReturn> pageDto = wxPmFollowReportGmService.findWxPmFollowReportGmPage(findWxPmFollowReportGmPage);
//            List<FindWxPmFollowReportGmPageReturn> list = Lists.newArrayListWithExpectedSize(pageDto.getRows().size());
//            list.addAll(pageDto.getRows());
//            
//            String fileName = "导购微信客户跟踪日报.xlsx";
//            new ExportExcel("导购微信客户跟踪日报导出", WxPmFollowReportGmDto.class, 2).setDataList(list).write(response, fileName).dispose();
//            return null;
//        } catch (Exception e) {
//            logger.error("导出导购微信客户跟踪日报", e);
//            addMessage(redirectAttributes, "导购微信客户跟踪日报导出失败！失败信息：" + e.getMessage());
//        }
//        return "redirect:" + Global.getAdminPath() + "/member/wxPmFollow/gm/list?repage";
//    }
//    
//    @RequiresPermissions("member:wxPmFollowShop:view")
//    @RequestMapping("/shop/list")
//    public String wxPmFollowShopList(Model model,Integer pageNo,Integer pageSize,FindWxPmFollowReportShopPage findWxPmFollowReportShopPage){
//        try {
//            if(pageNo!=null){
//                findWxPmFollowReportShopPage.setStart((pageNo-1)*pageSize);
//            }
//            if(pageSize!=null){
//                findWxPmFollowReportShopPage.setLimit(pageSize);
//            }
//            if (findWxPmFollowReportShopPage.getReportDate() == null) {
//                findWxPmFollowReportShopPage.setReportDate(DateUtils.addDays(new Date(), -1));
//            }
//            findWxPmFollowReportShopPage.setMerchantNo(UserUtils.getMerchantNo());
//            
//            Page<FindWxPmFollowReportShopPageReturn> pageDto = wxPmFollowReportShopService.findWxPmFollowReportShopPage(findWxPmFollowReportShopPage);
//            List<FindWxPmFollowReportShopPageReturn> list = Lists.newArrayListWithExpectedSize(pageDto.getRows().size());
//            list.addAll(pageDto.getRows());
//
//            com.ape.common.persistence.Page<FindWxPmFollowReportShopPageReturn> page = new com.ape.common.persistence.Page<FindWxPmFollowReportShopPageReturn>(pageNo == null ? 1 : pageNo, pageDto.getLimit(),
//                    pageDto.getTotal(), list);
//            page.initialize();
//            
//            model.addAttribute("page", page);
//            model.addAttribute("findWxPmFollowReportShopPage", findWxPmFollowReportShopPage);
//        } catch (Exception e) {
//            logger.error("分页查询终端微信客户跟踪日报错误！",e);
//        }
//        return "modules/member/wxPmFollowReportShopList";
//    }
//    
//    @RequiresPermissions("member:wxPmFollowShop:view")
//    @RequestMapping("/shop/export")
//    public String wxPmFollowShopExport(HttpServletResponse response, Integer pageNo,Integer pageSize,FindWxPmFollowReportShopPage findWxPmFollowReportShopPage, RedirectAttributes redirectAttributes){
//        try {
//            findWxPmFollowReportShopPage.setStart(0);
//            findWxPmFollowReportShopPage.setMaxLimit();
//            findWxPmFollowReportShopPage.setMerchantNo(UserUtils.getMerchantNo());
//            
//            Page<FindWxPmFollowReportShopPageReturn> pageDto = wxPmFollowReportShopService.findWxPmFollowReportShopPage(findWxPmFollowReportShopPage);
//            List<FindWxPmFollowReportShopPageReturn> list = Lists.newArrayListWithExpectedSize(pageDto.getRows().size());
//            list.addAll(pageDto.getRows());
//            
//            String fileName = "终端微信客户跟踪日报.xlsx";
//            new ExportExcel("终端微信客户跟踪日报导出", WxPmFollowReportShopDto.class, 2).setDataList(list).write(response, fileName).dispose();
//            return null;
//        } catch (Exception e) {
//            logger.error("导出终端微信客户跟踪日报", e);
//            addMessage(redirectAttributes, "终端微信客户跟踪日报导出失败！失败信息：" + e.getMessage());
//        }
//        return "redirect:" + Global.getAdminPath() + "/member/wxPmFollow/shop/list?repage";
//    }
//    
//    @RequiresPermissions("member:wxPmFollowCompany:view")
//    @RequestMapping("/company/list")
//    public String wxPmFollowCompanyList(Model model,Integer pageNo,Integer pageSize,FindWxPmFollowReportCompanyPage findWxPmFollowReportCompanyPage){
//        try {
//            if(pageNo!=null){
//                findWxPmFollowReportCompanyPage.setStart((pageNo-1)*pageSize);
//            }
//            if(pageSize!=null){
//                findWxPmFollowReportCompanyPage.setLimit(pageSize);
//            }
//            if (findWxPmFollowReportCompanyPage.getReportDate() == null) {
//                findWxPmFollowReportCompanyPage.setReportDate(DateUtils.addDays(new Date(), -1));
//            }
//            findWxPmFollowReportCompanyPage.setMerchantNo(UserUtils.getMerchantNo());
//            
//            Page<FindWxPmFollowReportCompanyPageReturn> pageDto = wxPmFollowReportCompanyService.findWxPmFollowReportCompanyPage(findWxPmFollowReportCompanyPage);
//            List<FindWxPmFollowReportCompanyPageReturn> list = Lists.newArrayListWithExpectedSize(pageDto.getRows().size());
//            list.addAll(pageDto.getRows());
//
//            com.ape.common.persistence.Page<FindWxPmFollowReportCompanyPageReturn> page = new com.ape.common.persistence.Page<FindWxPmFollowReportCompanyPageReturn>(pageNo == null ? 1 : pageNo, pageDto.getLimit(),
//                    pageDto.getTotal(), list);
//            page.initialize();
//            
//            model.addAttribute("page", page);
//            model.addAttribute("findWxPmFollowReportCompanyPage", findWxPmFollowReportCompanyPage);
//        } catch (Exception e) {
//            logger.error("分页查询经销商微信客户跟踪日报错误！",e);
//        }
//        return "modules/member/wxPmFollowReportCompanyList";
//    }
//    
//    @RequiresPermissions("member:wxPmFollowCompany:view")
//    @RequestMapping("/company/export")
//    public String wxPmFollowCompanyExport(HttpServletResponse response, Integer pageNo,Integer pageSize,FindWxPmFollowReportCompanyPage findWxPmFollowReportCompanyPage, RedirectAttributes redirectAttributes){
//        try {
//            findWxPmFollowReportCompanyPage.setStart(0);
//            findWxPmFollowReportCompanyPage.setMaxLimit();
//            findWxPmFollowReportCompanyPage.setMerchantNo(UserUtils.getMerchantNo());
//            
//            Page<FindWxPmFollowReportCompanyPageReturn> pageDto = wxPmFollowReportCompanyService.findWxPmFollowReportCompanyPage(findWxPmFollowReportCompanyPage);
//            List<FindWxPmFollowReportCompanyPageReturn> list = Lists.newArrayListWithExpectedSize(pageDto.getRows().size());
//            list.addAll(pageDto.getRows());
//            
//            String fileName = "经销商微信客户跟踪日报.xlsx";
//            new ExportExcel("经销商微信客户跟踪日报导出", WxPmFollowReportCompanyDto.class, 2).setDataList(list).write(response, fileName).dispose();
//            return null;
//        } catch (Exception e) {
//            logger.error("导出经销商微信客户跟踪日报", e);
//            addMessage(redirectAttributes, "经销商微信客户跟踪日报导出失败！失败信息：" + e.getMessage());
//        }
//        return "redirect:" + Global.getAdminPath() + "/member/wxPmFollow/company/list?repage";
//    }
//    
//}
