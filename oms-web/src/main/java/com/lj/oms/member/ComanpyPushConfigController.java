package com.lj.oms.member;

import java.util.List;
import javax.annotation.Resource;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ape.common.config.Global;
import com.lj.oms.common.BaseController;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.business.common.CommonConstant;
import com.lj.business.member.dto.company.AddComanpyPushConfig;
import com.lj.business.member.dto.company.DelComanpyPushConfig;
import com.lj.business.member.dto.company.FindBranchCompany;
import com.lj.business.member.dto.company.FindBranchCompanyReturn;
import com.lj.business.member.dto.company.FindComanpyPushConfig;
import com.lj.business.member.dto.company.FindComanpyPushConfigPageReturn;
import com.lj.business.member.dto.company.FindCompanyPushConfigPage;
import com.lj.business.member.dto.company.FindCompanyPushConfigReturn;
import com.lj.business.member.dto.company.UpdateComanpyPushConfig;
import com.lj.business.member.emus.CompanyPushConfigType;
import com.lj.business.member.service.IBranchCompanyService;
import com.lj.business.member.service.IComanpyPushConfigService;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPage;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPageReturn;
import com.lj.business.weixin.service.IImEmojiService;
import com.lj.oms.entity.sys.User;
import com.lj.oms.utils.CcUtils;
import com.lj.oms.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/member/comanpyPushConfig")
public class ComanpyPushConfigController  extends BaseController{
    
    @Resource
    private IComanpyPushConfigService comanpyPushConfigService;
    @Resource
    private IBranchCompanyService branchCompanyService;
    @Resource
    private IImEmojiService imEmojiService;
    
    @RequiresPermissions("member:comanpyPushConfig:view")
    @RequestMapping(value={"list",""})
    public String list(Model model,Integer pageNo,Integer pageSize,FindCompanyPushConfigPage findCompanyPushConfigPage){
        try {
            if(pageNo!=null){
                findCompanyPushConfigPage.setStart((pageNo-1)*pageSize);
            }
            if(pageSize!=null){
                findCompanyPushConfigPage.setLimit(pageSize);
            }
            findCompanyPushConfigPage.setMerchantNo(UserUtils.getMerchantNo());
            User user = UserUtils.getUser();
            logger.info("comanpyPushConfig Add GRADE_ORG：[[]]", user.getOffice().getGrade());
            if(CommonConstant.GRADE_ORG.equals(user.getOffice().getGrade())){
                logger.info("comanpyPushConfig Add GRADE_ORG Office Id：[[]]", user.getOffice().getId());
                findCompanyPushConfigPage.setCompanyNo(user.getOffice().getId());
            }
            
            Page<FindComanpyPushConfigPageReturn> pageDto = comanpyPushConfigService.findComanpyPushConfigPage(findCompanyPushConfigPage);
            List<FindComanpyPushConfigPageReturn> list = Lists.newArrayListWithExpectedSize(pageDto.getRows().size());
            list.addAll(pageDto.getRows());

            com.ape.common.persistence.Page<FindComanpyPushConfigPageReturn> page = new com.ape.common.persistence.Page<FindComanpyPushConfigPageReturn>(pageNo == null ? 1 : pageNo, pageDto.getLimit(),
                    pageDto.getTotal(), list);
            page.initialize();
            
            model.addAttribute("page", page);
            model.addAttribute("companyPushConfigTypes", CompanyPushConfigType.values());
            model.addAttribute("findCompanyPushConfigPage", findCompanyPushConfigPage);
        } catch (Exception e) {
            logger.error("分页查询经销商推送配置失败！",e);
        }
        return "modules/member/comanpyPushConfigList";
    }
    
    @RequiresPermissions("member:comanpyPushConfig:edit")
    @RequestMapping(value={"addView"})
    public String addView(Model model){
        try {
            FindImEmojiPage findImEmojiPage = new FindImEmojiPage();
            findImEmojiPage.setStatus(1);
            Page<FindImEmojiPageReturn> imEmojiPage = imEmojiService.findImEmojiPage(findImEmojiPage);
            
            StringBuilder emojis = new StringBuilder("");
            for (FindImEmojiPageReturn findImEmojiPageReturn : imEmojiPage.getRows()) {
                emojis.append(findImEmojiPageReturn.getEmojiUrl());
                emojis.append(",");
            }
            
            model.addAttribute("emojis", emojis.toString());
            model.addAttribute("companyPushConfigTypes", CompanyPushConfigType.values());
        } catch (Exception e) {
            logger.error("跳转到添加经销商推送配置失败！",e);
        }
        return "modules/member/comanpyPushConfigAdd";
    }
    
    @RequiresPermissions("member:comanpyPushConfig:edit")
    @RequestMapping(value={"add"})
    public String add(Model model, AddComanpyPushConfig addComanpyPushConfig){
        try {
            addComanpyPushConfig.setMerchantNo(UserUtils.getMerchantNo());
            User user = UserUtils.getUser();
            logger.info("comanpyPushConfig Add GRADE_ORG：[[]]", user.getOffice().getGrade());
            if(CommonConstant.GRADE_ORG.equals(user.getOffice().getGrade())){
                logger.info("comanpyPushConfig Add GRADE_ORG Office Id：[[]]", user.getOffice().getId());
                FindBranchCompany findBranchCompany = new FindBranchCompany();
                findBranchCompany.setCompanyNo(user.getOffice().getId());
                FindBranchCompanyReturn branchCompanyReturn = branchCompanyService.findBranchCompanyByCompanyNo(findBranchCompany);
                
                if (branchCompanyReturn != null) {
                    addComanpyPushConfig.setCompanyNo(branchCompanyReturn.getCompanyNo());
                    addComanpyPushConfig.setCompanyName(branchCompanyReturn.getCompanyName());
                }
            }
            
            if (CompanyPushConfigType.IMAGE.name().equals(addComanpyPushConfig.getType())) {
                addComanpyPushConfig.setContent(addComanpyPushConfig.getImg());
            } else if (CompanyPushConfigType.MGR_CARD.name().equals(addComanpyPushConfig.getType())) {
                addComanpyPushConfig.setContent(null);
            }
            
            comanpyPushConfigService.addComanpyPushConfig(addComanpyPushConfig);
        } catch (Exception e) {
            logger.error("添加经销商推送配置失败！",e);
        }
        return "redirect:" +Global.getAdminPath() + "/member/comanpyPushConfig/list";
    }
    
    @RequiresPermissions("member:comanpyPushConfig:edit")
    @RequestMapping(value={"editView"})
    public String editView(Model model, FindComanpyPushConfig findComanpyPushConfig){
        try {
            FindCompanyPushConfigReturn comanpyPushConfig = comanpyPushConfigService.findComanpyPushConfig(findComanpyPushConfig);
            
            FindImEmojiPage findImEmojiPage = new FindImEmojiPage();
            findImEmojiPage.setStatus(1);
            Page<FindImEmojiPageReturn> imEmojiPage = imEmojiService.findImEmojiPage(findImEmojiPage);
            
            StringBuilder emojis = new StringBuilder("");
            for (FindImEmojiPageReturn findImEmojiPageReturn : imEmojiPage.getRows()) {
                emojis.append(findImEmojiPageReturn.getEmojiUrl());
                emojis.append(",");
            }
            
            model.addAttribute("emojis", emojis.toString());
            
            model.addAttribute("data", comanpyPushConfig);
            model.addAttribute("code", findComanpyPushConfig.getCode());
            model.addAttribute("companyPushConfigTypes", CompanyPushConfigType.values());
        } catch (Exception e) {
            logger.error("跳转到编辑经销商推送配置失败！",e);
        }
        return "modules/member/comanpyPushConfigEdit";
    }
    
    @RequiresPermissions("member:comanpyPushConfig:edit")
    @RequestMapping(value={"edit"})
    public String edit(Model model, UpdateComanpyPushConfig updateComanpyPushConfig){
        try {
            if (CompanyPushConfigType.IMAGE.name().equals(updateComanpyPushConfig.getType())) {
                updateComanpyPushConfig.setContent(updateComanpyPushConfig.getImg());
            } else if (CompanyPushConfigType.MGR_CARD.name().equals(updateComanpyPushConfig.getType())) {
                updateComanpyPushConfig.setContent("");
            }
            
            comanpyPushConfigService.updateComanpyPushConfig(updateComanpyPushConfig);
        } catch (Exception e) {
            logger.error("编辑经销商推送配置失败！",e);
        }
        return "redirect:" +Global.getAdminPath() + "/member/comanpyPushConfig/list";
    }
    
    @RequiresPermissions("member:comanpyPushConfig:edit")
    @RequestMapping(value={"delete"})
    public String delete(Model model, DelComanpyPushConfig delComanpyPushConfig){
        try {
            comanpyPushConfigService.delComanpyPushConfig(delComanpyPushConfig);
        } catch (Exception e) {
            logger.error("删除经销商推送配置失败！",e);
        }
        return "redirect:" +Global.getAdminPath() + "/member/comanpyPushConfig/list";
    }
}
