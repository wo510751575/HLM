package com.lj.business.api.controller.imh5;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaException;
import com.lj.business.cm.service.IFriendsImageMaterialService;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopReturn;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.service.IGmAssistantShopService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.weixin.dto.FindSendFriendsJobPage;
import com.lj.business.weixin.dto.FindSendFriendsJobPageReturn;
import com.lj.business.weixin.dto.friendsjob.AddSendFriendsJob;
import com.lj.business.weixin.service.IImFriendsFacade;
import com.lj.business.weixin.service.IImFriendsInfoService;
import com.lj.business.weixin.service.ISendFriendsJobService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;

/**
 * 
 * 
 * 类说明：发送朋友圈任务
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 许新龙
 * 
 *         CreateDate: 2017年12月23日
 */
@Controller
@RequestMapping(value = "/imh5/sendfriendsjob")
public class ImSendFriendsJobController{

    @Autowired
    private IShopTerminalService shopTerminalService;
    
    @Autowired 
    private ISendFriendsJobService sendFriendsJobService;
    
    @Autowired 
    private IImFriendsInfoService imFriendsInfoService;
    
    @Autowired 
    private IImFriendsFacade imFriendsFacade;
    
    @Autowired
	private IFriendsImageMaterialService friendsImageMaterialService;//朋友圈图片素材服务
    @Resource
    private LocalCacheSystemParamsFromCC localCacheSystemParams;
	@Autowired
	private IGmAssistantShopService gmAssistantShopService;
    
    
    private static final Logger logger = LoggerFactory.getLogger(ImSendFriendsJobController.class);
    
    
    @RequestMapping(value={"/addSendFriendsJob.do"})
    @ResponseBody
    public Map<String, Object> addFriendsJob(String paramJson) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try {
        	
        	AddSendFriendsJob addSendFriendsJob = new AddSendFriendsJob();
        	
        	Map maps = (Map)JSON.parse(paramJson);
        	String memberNoGm = maps.get("memberNoGm") == null ? null : maps.get("memberNoGm").toString();
        	String merchantNo = maps.get("merchantNo") == null ? null : maps.get("merchantNo").toString();
			String content = maps.get("content") == null ? null : maps.get("content").toString();
			String noWxs = maps.get("noWxs") == null ? null : maps.get("noWxs").toString();
			String executeTime =maps.get("executeTime") == null ? null : maps.get("executeTime").toString();
			String type = maps.get("type") == null ? null : maps.get("type").toString();
			String imgAddrs =maps.get("imgAddrs") == null ? null : maps.get("imgAddrs").toString();
			String resourcePath = maps.get("resourcePath") == null ? null : maps.get("resourcePath").toString();
			String shareTitle = maps.get("shareTitle") == null ? null : maps.get("shareTitle").toString();
			String autoContent = maps.get("autoContent") == null ? null : maps.get("autoContent").toString();
			AssertUtils.notNullAndEmpty(merchantNo, "商户编号不能为空");
			AssertUtils.notNullAndEmpty(memberNoGm, "导购编号不能为空");
			AssertUtils.notNullAndEmpty(executeTime, "執行時間不能为空");
			AssertUtils.notNullAndEmpty(noWxs, "微信不能为空");
			AssertUtils.notNullAndEmpty(type, "類型不能为空");
			
			Map<String, String> map = localCacheSystemParams.getSystemParamGroup("weixin", "imfile");

			addSendFriendsJob.setMerchantNo(merchantNo);
			
			if(imgAddrs !=null && !imgAddrs.equals("")) {
				String uploadUrl =map.get("uploadUrl");
				imgAddrs = imgAddrs.replace(uploadUrl, "");
			}
			
			if(resourcePath !=null && !resourcePath.equals("")) {
				String uploadUrl =map.get("uploadUrl");
				resourcePath = resourcePath.replace(uploadUrl, "");
			}
			String format = "yyyy-MM-dd HH:mm:ss";
			String []arrayNoWxs = noWxs.split(",");
			for(String noWx :arrayNoWxs) {
				addSendFriendsJob.setContent(content);
				addSendFriendsJob.setNoWxs(noWx);
				addSendFriendsJob.setType(type);
				addSendFriendsJob.setCreateUserLevel("2");
				addSendFriendsJob.setShareTitle(shareTitle);
				if(arrayNoWxs.length == 1) {
					logger.info("定时任务时间："+executeTime);
					ParsePosition pos = new ParsePosition(0);
			
					Date testdate = new SimpleDateFormat(format).parse(executeTime,pos);
					logger.info("定时任务时间2："+testdate.toLocaleString());
					addSendFriendsJob.setExecuteTime(new SimpleDateFormat(format).parse(executeTime));
				}
				
				Date dd = null;
				//为了防止同时发同一微信内容被封号，需要根据微信数量定义发送时间
				//随机延迟1-30分钟
				if(arrayNoWxs.length > 1 && (arrayNoWxs.length < 100 || arrayNoWxs.length == 100)) {
					int x=1+(int)(Math.random()*30);
					dd = new SimpleDateFormat(format).parse(executeTime);
               	    dd = DateUtils.addMinutes(dd, x);
               	    addSendFriendsJob.setExecuteTime(dd);
				}
				//60分钟
                if(arrayNoWxs.length > 100  && (arrayNoWxs.length < 300 || arrayNoWxs.length == 300)) {
                	int x=1+(int)(Math.random()*60);
                	dd = new SimpleDateFormat(format).parse(executeTime);
               	    dd = DateUtils.addMinutes(dd, x);
               	    addSendFriendsJob.setExecuteTime(dd);
				}
                //120分钟
                if(arrayNoWxs.length > 300) {
                	 int x=1+(int)(Math.random()*120);
                	 dd = new SimpleDateFormat(format).parse(executeTime);
                	 dd = DateUtils.addMinutes(dd, x);
                	 addSendFriendsJob.setExecuteTime(dd);
				}
                Date now = new Date();
            	logger.info(now.compareTo(addSendFriendsJob.getExecuteTime())+"");
    			if(now.compareTo(addSendFriendsJob.getExecuteTime()) > 0) {
    				resultMap.put("result", 0);
    	            resultMap.put("msg", "时间必须大于当前时间");
    	            return resultMap;
    	        }
    			//查詢是否為老闆
    			FindGmAssistantShop findGmAssistantShop = new FindGmAssistantShop();
    			findGmAssistantShop.setAssistantNo(memberNoGm);
    			findGmAssistantShop.setNoWx(noWx);
    			
    			List<FindGmAssistantShopReturn> assList = gmAssistantShopService.findGmAssistantShopList(findGmAssistantShop);
    			for(FindGmAssistantShopReturn gmass : assList) {
    				if(StringUtils.isNotBlank(gmass.getSource())) {
    					addSendFriendsJob.setCreateUserLevel("1"); //存在下属微信为老板
    					break;
    				}
    			}
    			addSendFriendsJob.setCreateId(memberNoGm);
				addSendFriendsJob.setImgAddr(imgAddrs);
				addSendFriendsJob.setResourcePath(resourcePath);
				addSendFriendsJob.setAutoContent(autoContent);
	            sendFriendsJobService.addSendFriendsJob(addSendFriendsJob);
			}
            resultMap.put("result", 1);
            resultMap.put("msg", "发送朋友圈任务添加成功");
        } catch (TsfaException e) {
            logger.error("添加朋友圈任务失败-{}", e);
            logger.error(e.getMessage());
            resultMap.put("result", 0);
            resultMap.put("msg", e.getExceptionInfo());
        } catch (Exception e) {
            logger.error("添加朋友圈任务失败-{}", e);
            logger.error(e.getMessage());
            resultMap.put("result", 0);
            resultMap.put("msg", "系统异常，请稍后重试");
        }

        return resultMap;
    }

    
    @RequestMapping(value = "/findAllShopTerminalByMerchantNo.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> findAllShopTerminalByMerchantNo(String merchantNo) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try {
        	//String merchantNo = merchantNo;
        	List<FindShopTerminalReturn>  list =shopTerminalService.findAllShopTerminalByMerchantNo(merchantNo);
            resultMap.put("result", true);
            resultMap.put("data", list);
        } catch (TsfaException e) {
            logger.error("添加朋友圈任务失败-{}", e);
            logger.error(e.getMessage());
            resultMap.put("result", 0);
            resultMap.put("msg", e.getExceptionInfo());
        } catch (Exception e) {
            logger.error("添加朋友圈任务失败-{}", e);
            logger.error(e.getMessage());
            resultMap.put("result", 0);
            resultMap.put("msg", "系统异常，请稍后重试");
        }

        return resultMap;
    }

    
    /**
     * OMS-朋友圈
     * @param redirectAttributes
     * @param findSendFriendsJobPage
     * @param pageNo
     * @param pageSize
     * @param model
     * @return
     *
     * @author zlh
     * @CreateDate 2018年6月21日下午2:53:57
     */
	@RequestMapping(value={"/list"})
	@ResponseBody
	public Map<String,Object> list(FindSendFriendsJobPage findSendFriendsJobPage,String paramJson){
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	try {
    		Map maps = (Map)JSON.parse(paramJson);
	
			String merchantNo = maps.get("merchantNo") == null ? null : maps.get("merchantNo").toString();
			String jobState = maps.get("jobState") == null ? null : maps.get("jobState").toString();
			String createDateBegin =maps.get("createDateBegin") == null ? null : maps.get("createDateBegin").toString();
			String createDateEnd = maps.get("createDateEnd") == null ? null : maps.get("createDateEnd").toString();
			Integer pageNo =maps.get("pageNo") == null ? 1 : Integer.valueOf(maps.get("pageNo").toString());
			Integer pageSize = maps.get("pageSize") == null ? 10 : Integer.valueOf(maps.get("pageSize").toString());
			String noWxs = maps.get("noWxs") == null ? null : maps.get("noWxs").toString();
			String content = maps.get("content") == null ? null : maps.get("content").toString();
			
    		
    	
			
			if(pageNo !=null){
				findSendFriendsJobPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize !=null){
				findSendFriendsJobPage.setLimit(pageSize);
			}
			if(noWxs !=null && !noWxs.equals("")) {
			   findSendFriendsJobPage.setNoWxs(noWxs);
			}
			if(content !=null && !content.equals("")) {
				   findSendFriendsJobPage.setContent(content);
			}
			if(jobState !=null && !jobState.equals("")) {
			   findSendFriendsJobPage.setJobState(jobState);
			}
			findSendFriendsJobPage.setMerchantNo(merchantNo);
			
			if(createDateBegin != null && !createDateBegin.equals("")) {
			   findSendFriendsJobPage.setCreateDateBegin(createDateBegin);
			}
			
			if(createDateEnd != null && !createDateEnd.equals("")) {
			    findSendFriendsJobPage.setCreateDateEnd(createDateEnd);
			}
			
			Page<FindSendFriendsJobPageReturn> pages=sendFriendsJobService.findSendFriendsJobPage(findSendFriendsJobPage);
			List<FindSendFriendsJobPageReturn> list=Lists.newArrayList();
			list.addAll(pages.getRows());
			
            Map<String, String> map = localCacheSystemParams.getSystemParamGroup("weixin", "imfile");
            for(FindSendFriendsJobPageReturn jobRecord : list) {
            	if(jobRecord.getImgAddr() != null ) {
            		String sArray[] = jobRecord.getImgAddr().split(",");
            		for(String s : sArray) {
            			if(StringUtils.isNotEmpty(s) && !s.toUpperCase().startsWith("HTTP")) {
            				jobRecord.setImgAddr(jobRecord.getImgAddr().replace(s, map.get("uploadUrl")+s));
            			}
            		}
            	}
            }

			Page<FindSendFriendsJobPageReturn> page =
					new Page<FindSendFriendsJobPageReturn>(new ArrayList<>(pages.getRows()), pages.getTotal(),pages.getStart(),pages.getLimit() );

			
			resultMap.put("page", page);
			resultMap.put("findSendFriendsJobPage", findSendFriendsJobPage);
			
	
			resultMap.put("message", "成功");
			resultMap.put("result", "true");
		} catch (Exception e) {
			resultMap.put("result", "false");
			resultMap.put("message", "加载数据失败,系统出现异常！");
			logger.error(e.getMessage(),e);
		}
		return resultMap;
    }
    

    

    
    
    @RequestMapping(value={"/deleteFriendsJob.do"})
    @ResponseBody
    public Map<String, Object> deleteFriendsJob( String paramJson) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try {
        	
        	AddSendFriendsJob addSendFriendsJob = new AddSendFriendsJob();
        	
        	Map maps = (Map)JSON.parse(paramJson);
        	
        	String code = maps.get("code") == null ? null : maps.get("code").toString();
        	if(code == null || code.equals("")) {
        		resultMap.put("result", "false");
                resultMap.put("message", "code 为空");
                return resultMap;
        	}
        	addSendFriendsJob.setCode(code);
	        sendFriendsJobService.delSendFriendsJob(addSendFriendsJob);
			
            resultMap.put("result", "true");
            resultMap.put("message", "删除朋友圈任务成功");
        } catch (TsfaException e) {
            logger.error("删除朋友圈任务失败-{}", e);
            logger.error(e.getMessage());
            resultMap.put("result", "false");
            resultMap.put("msg", e.getExceptionInfo());
        } catch (Exception e) {
            logger.error("删除朋友圈任务失败-{}", e);
            logger.error(e.getMessage());
            resultMap.put("result", "false");
            resultMap.put("msg", "系统异常，请稍后重试");
        }

        return resultMap;
    }
}
