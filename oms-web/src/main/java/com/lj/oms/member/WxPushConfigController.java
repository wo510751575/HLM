package com.lj.oms.member;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ape.common.config.Global;
import com.ape.common.utils.StringUtils;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.business.member.dto.pushconfig.AddWxPushConfig;
import com.lj.business.member.dto.pushconfig.DelWxPushConfig;
import com.lj.business.member.dto.pushconfig.FindWxPushConfig;
import com.lj.business.member.dto.pushconfig.FindWxPushConfigPage;
import com.lj.business.member.dto.pushconfig.FindWxPushConfigPageReturn;
import com.lj.business.member.dto.pushconfig.FindWxPushConfigReturn;
import com.lj.business.member.dto.pushconfig.UpdateWxPushConfig;
import com.lj.business.member.dto.shopterminal.FindShopTerminal;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.emus.PushConfigType;
import com.lj.business.member.emus.PushTime;
import com.lj.business.member.emus.ShopTerminalStatus;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.member.service.IWxPushConfigService;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPage;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPageReturn;
import com.lj.business.weixin.service.IImEmojiService;
import com.lj.oms.common.BaseController;
import com.lj.oms.emus.GradeType;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：终端微信推送配置
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年8月10日
 */
@Controller
@RequestMapping(value = "${adminPath}/member/wxPushConfig")
public class WxPushConfigController  extends BaseController{
    
    @Resource
    private IWxPushConfigService wxPushConfigService;
//    @Resource
//    private IShopService shopService;
    @Resource
    private IImEmojiService imEmojiService;
	@Resource
	private IShopTerminalService shopTerminalService;	//终端终端服务
    
    /**
     * 
     *
     * 方法说明：列表查询
     *
     * @param model
     * @param pageNo
     * @param pageSize
     * @param findWxPushConfigPage
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2018年8月10日
     *
     */
    @RequiresPermissions("member:wxPushConfig:view")
    @RequestMapping(value={"list",""})
    public String list(Model model,Integer pageNo,Integer pageSize,FindWxPushConfigPage findWxPushConfigPage){
        try {
            if(pageNo!=null){
                findWxPushConfigPage.setStart((pageNo-1)*pageSize);
            }
            if(pageSize!=null){
                findWxPushConfigPage.setLimit(pageSize);
            }
            findWxPushConfigPage.setMerchantNo(UserUtils.getMerchantNo());
            
            Page<FindWxPushConfigPageReturn> pageDto = wxPushConfigService.findWxPushConfigPage(findWxPushConfigPage);
            List<FindWxPushConfigPageReturn> list = Lists.newArrayListWithExpectedSize(pageDto.getRows().size());
            list.addAll(pageDto.getRows());

            com.ape.common.persistence.Page<FindWxPushConfigPageReturn> page = new com.ape.common.persistence.Page<FindWxPushConfigPageReturn>(pageNo == null ? 1 : pageNo, pageDto.getLimit(),
                    pageDto.getTotal(), list);
            page.initialize();
            
            model.addAttribute("page", page);
            model.addAttribute("pushConfigTypes", PushConfigType.values());
            model.addAttribute("pushTimes", PushTime.values());
            model.addAttribute("findWxPushConfigPage", findWxPushConfigPage);
            
            // 终端列表
            FindShopTerminal findShopTerminal = new FindShopTerminal();
            findShopTerminal.setMerchantNo(UserUtils.getMerchantNo());
            findShopTerminal.setStatus(ShopTerminalStatus.NORMAL.getValue());
            List<FindShopTerminalReturn> findShopTerminalReturns= shopTerminalService.findShopTerminalSelect(findShopTerminal);
            model.addAttribute("findShopTerminalReturns", findShopTerminalReturns);
        } catch (Exception e) {
            logger.error("分页查询终端微信推送配置失败！",e);
        }
        return "modules/member/wxPushConfigList";
    }
    
    /**
     * 
     *
     * 方法说明：添加终端微信推送配置
     *
     * @param model
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2018年8月10日
     *
     */
    @RequiresPermissions("member:wxPushConfig:edit")
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
            model.addAttribute("pushTimes", PushTime.values());
            model.addAttribute("pushConfigTypes", PushConfigType.values());
            model.addAttribute("pushTimes", PushTime.values());

            // 终端列表
            FindShopTerminal findShopTerminal = new FindShopTerminal();
            findShopTerminal.setMerchantNo(UserUtils.getMerchantNo());
            findShopTerminal.setStatus(ShopTerminalStatus.NORMAL.getValue());
            List<FindShopTerminalReturn> findShopTerminalReturns= shopTerminalService.findShopTerminalSelect(findShopTerminal);
            model.addAttribute("findShopTerminalReturns", findShopTerminalReturns);
        } catch (Exception e) {
            logger.error("跳转到添加终端微信推送配置失败！",e);
        }
        return "modules/member/wxPushConfigAdd";
    }
    
    /**
     * 
     *
     * 方法说明：新增终端微信推送配置
     *
     * @param model
     * @param addWxPushConfig
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2018年8月10日
     *
     */
    @RequiresPermissions("member:wxPushConfig:edit")
    @RequestMapping(value={"add"})
    public String add(Model model, AddWxPushConfig addWxPushConfig){
        try {
        	if(StringUtils.isNotEmpty(addWxPushConfig.getNoWx())) {
    			String[] noWxs = addWxPushConfig.getNoWx().split(",");
    			String[] remarks = addWxPushConfig.getRemark().split(",");
    			for (int i = 0; i < remarks.length; i++) {
    				addWxPushConfig.setNoWx(noWxs[i]);
    				String remark = StringUtils.isNotEmpty(remarks[i])?remarks[i]:"";
    				addWxPushConfig.setRemark(remark);
    				addWxPushConfig.setContent(StringEscapeUtils.unescapeHtml4(addWxPushConfig.getContent()).toString());
    	            addWxPushConfig.setMerchantNo(UserUtils.getMerchantNo());
    	            addWxPushConfig.setOrgType(GradeType.SHOP.name());
    	            wxPushConfigService.addWxPushConfig(addWxPushConfig);
				}
        	}
            
        } catch (Exception e) {
            logger.error("添加终端微信推送配置失败！",e);
        }
        return "redirect:" +Global.getAdminPath() + "/member/wxPushConfig/list";
    }
    
    /**
     * 
     *
     * 方法说明：跳转到编辑页面
     *
     * @param model
     * @param findWxPushConfig
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2018年8月10日
     *
     */
    @RequiresPermissions("member:wxPushConfig:edit")
    @RequestMapping(value={"editView"})
    public String editView(Model model, FindWxPushConfig findWxPushConfig){
        try {
            FindWxPushConfigReturn wxPushConfig = wxPushConfigService.findWxPushConfig(findWxPushConfig);
            
            FindImEmojiPage findImEmojiPage = new FindImEmojiPage();
            findImEmojiPage.setStatus(1);
            Page<FindImEmojiPageReturn> imEmojiPage = imEmojiService.findImEmojiPage(findImEmojiPage);
            
            StringBuilder emojis = new StringBuilder("");
            for (FindImEmojiPageReturn findImEmojiPageReturn : imEmojiPage.getRows()) {
                emojis.append(findImEmojiPageReturn.getEmojiUrl());
                emojis.append(",");
            }
           
            
            model.addAttribute("emojis", emojis.toString());
            model.addAttribute("data", wxPushConfig);
            model.addAttribute("code", findWxPushConfig.getCode());
            model.addAttribute("pushConfigTypes", PushConfigType.values());
            model.addAttribute("pushTimes", PushTime.values());
            
            // 终端列表
            FindShopTerminal findShopTerminal = new FindShopTerminal();
            findShopTerminal.setMerchantNo(UserUtils.getMerchantNo());
            findShopTerminal.setStatus(ShopTerminalStatus.NORMAL.getValue());
            List<FindShopTerminalReturn> findShopTerminalReturns= shopTerminalService.findShopTerminalSelect(findShopTerminal);
            model.addAttribute("findShopTerminalReturns", findShopTerminalReturns);
        } catch (Exception e) {
            logger.error("跳转到编辑终端微信推送配置失败！",e);
        }
        return "modules/member/wxPushConfigEdit";
    }
    
    /**
     * 
     *
     * 方法说明：编辑终端微信推送配置
     *
     * @param model
     * @param updateWxPushConfig
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2018年8月10日
     *
     */
    @RequiresPermissions("member:wxPushConfig:edit")
    @RequestMapping(value={"edit"})
    public String edit(Model model, UpdateWxPushConfig updateWxPushConfig){
        try {
        	updateWxPushConfig.setContent(StringEscapeUtils.unescapeHtml4(updateWxPushConfig.getContent()).toString());

            wxPushConfigService.updateWxPushConfig(updateWxPushConfig);
        } catch (Exception e) {
            logger.error("编辑终端微信推送配置失败！",e);
        }
        return "redirect:" +Global.getAdminPath() + "/member/wxPushConfig/list";
    }
    
    /**
     * 
     *
     * 方法说明：删除配置
     *
     * @param model
     * @param delWxPushConfig
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2018年8月10日
     *
     */
    @RequiresPermissions("member:wxPushConfig:edit")
    @RequestMapping(value={"delete"})
    public String delete(Model model, DelWxPushConfig delWxPushConfig){
        try {
            wxPushConfigService.delWxPushConfig(delWxPushConfig);
        } catch (Exception e) {
            logger.error("删除终端微信推送配置失败！",e);
        }
        return "redirect:" +Global.getAdminPath() + "/member/wxPushConfig/list";
    }
    
    /**
     * 
     *
     * 方法说明：获取终端微信列表
     *
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2018年8月10日
     *
     */
    @RequestMapping(value={"shopWxList"})
    @ResponseBody
    public List<FindShopTerminalReturn> findShopWxList(FindShopTerminal findShopTerminal) {
    	findShopTerminal.setMerchantNo(UserUtils.getMerchantNo());
    	findShopTerminal.setStatus(ShopTerminalStatus.NORMAL.getValue());
//    	List<String> shopNoList = CcUtils.getShopNoList();
//    	findShopTerminal.setShopNos(shopNoList);
    	List<FindShopTerminalReturn> shopTerminalList = shopTerminalService.findShopTerminalSelect(findShopTerminal);
		 //终端(工作)微信下拉列表
    	return shopTerminalList;
    }
}
