package com.lj.oms.weixin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.pagination.PageSortType;
import com.lj.business.member.dto.FindPersonMemberBase;
import com.lj.business.member.dto.FindPersonMemberBaseReturn;
import com.lj.business.member.service.IPersonMemberBaseService;
import com.lj.business.weixin.domain.ImFriendsInfo;
import com.lj.business.weixin.dto.FindImFriendsInfoPage;
import com.lj.business.weixin.dto.FindWxCommentInfoPage;
import com.lj.business.weixin.dto.FindWxFriendsInfoPage;
import com.lj.business.weixin.dto.FindWxFriendsInfoPageReturn;
import com.lj.business.weixin.service.IImFriendsInfoService;
import com.lj.business.weixin.service.IWxCommentInfoService;
import com.lj.business.weixin.service.IWxFriendsInfoService;
import com.lj.business.weixin.service.IWxLikeInfoService;
import com.lj.oms.common.BaseController;
import com.lj.oms.utils.UserUtils;
import com.lj.oms.utils.excel.ExportExcel;
import com.lj.oms.utils.excel.dto.CountFriendsInfoDto;

/**
 * 
 * 
 * 类说明：朋友圈信息@Controller
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2017年7月24日
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/friendsInfo")
public class FriendsInfoController extends BaseController{

	@Resource
	private IWxFriendsInfoService wxFriendsInfoService;
	
	@Resource
	private IImFriendsInfoService imFriendsInfoService;
	@Resource
	private IWxCommentInfoService wxCommentInfoService;
	@Resource
	private IWxLikeInfoService wxLikeInfoService;
	@Resource
	private IPersonMemberBaseService personMemberBaseService;
	
	@RequestMapping(value = {"list", ""})
	public String list(Model model,Integer pageNo,Integer pageSize,FindWxFriendsInfoPage findWxFriendsInfoPage) {
		try {
			if(pageNo!=null){
				findWxFriendsInfoPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findWxFriendsInfoPage.setLimit(pageSize);
			}
			findWxFriendsInfoPage.setSortDir(PageSortType.desc);
			Page<FindWxFriendsInfoPageReturn> pageDto= wxFriendsInfoService.findWxFriendsInfoPage(findWxFriendsInfoPage);
			List<FindWxFriendsInfoPageReturn> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());
		    System.out.println(pageDto);
		    //获取评论和点赞
			for (FindWxFriendsInfoPageReturn findWxFriendsInfoPageReturn : list) {
				FindWxCommentInfoPage findWxCommentInfoPage = new FindWxCommentInfoPage();
				findWxCommentInfoPage.setIdWx(findWxFriendsInfoPageReturn.getIdWx());
				findWxFriendsInfoPageReturn.setCommentInfos(wxCommentInfoService.findWxCommentInfos(findWxCommentInfoPage));
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("idWx", findWxFriendsInfoPageReturn.getIdWx());
				findWxFriendsInfoPageReturn.setLikeInfos(wxLikeInfoService.findWxLikeInfos(map));
				
				//获取客户头像和昵称
				FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
				findPersonMemberBase.setNoWx(findWxFriendsInfoPageReturn.getAuthorid());
				FindPersonMemberBaseReturn member=  personMemberBaseService.findPersonMemberBase(findPersonMemberBase);
				if(member!=null){
					findWxFriendsInfoPageReturn.setPmPhoto(member.getHeadAddress());
				}
				findWxFriendsInfoPageReturn.setCreateDate(new Date(Long.valueOf(findWxFriendsInfoPageReturn.getTimestamp()+"000")));
			}
		  	com.ape.common.persistence.Page<FindWxFriendsInfoPageReturn> page	=new com.ape.common.persistence.Page<FindWxFriendsInfoPageReturn>(pageNo==null?1:pageNo, pageDto.getLimit(), pageDto.getTotal(), list);
		  	page.initialize();
			model.addAttribute("page",page);
			//参数
			model.addAttribute("parmMap",findWxFriendsInfoPage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "modules/weixin/friendsInfoView";
	}
	
	/**
	 * 朋友圈统计信息
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param findImFriendsInfoPage
	 * @return
	 */
	@RequestMapping(value = {"/countList", ""})
	public String list(Model model,Integer pageNo,Integer pageSize,FindImFriendsInfoPage findImFriendsInfoPage) {
		try {
			if(pageNo!=null){
				findImFriendsInfoPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findImFriendsInfoPage.setLimit(pageSize);
			}
			findImFriendsInfoPage.setMerchantNo(UserUtils.getMerchantNo());
			Page<ImFriendsInfo> pageData =imFriendsInfoService.selectFriendsDatasPage(findImFriendsInfoPage);
			List<ImFriendsInfo> list = Lists.newArrayList();
			list.addAll(pageData.getRows());

		  	com.ape.common.persistence.Page<ImFriendsInfo> page	=new com.ape.common.persistence.Page<ImFriendsInfo>(pageNo==null?1:pageNo, pageData.getLimit(), pageData.getTotal(), list);
		  	page.initialize();
			model.addAttribute("page",page);
			model.addAttribute("param",findImFriendsInfoPage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "modules/weixin/imFriendInfoCountList";
	}
	
	
	/**
	 * 导出excel
	 */
	@RequestMapping("/exportExcel")
	public void exportExcel(Model model,FindImFriendsInfoPage findImFriendsInfoPage,HttpServletResponse response) {
		
		try {
			
			
		    String str ="";
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String dateStr = sdf.format(new Date());
			String fileName = null;
		
			findImFriendsInfoPage.setLimit(1000);
			findImFriendsInfoPage.setMerchantNo(UserUtils.getMerchantNo());
			Page<ImFriendsInfo> pageData =imFriendsInfoService.selectFriendsDatasPage(findImFriendsInfoPage);
			List<ImFriendsInfo> list = Lists.newArrayList();
			list.addAll(pageData.getRows());
			str="pengyouquantongji";
			fileName = str + dateStr + ".xlsx";
			new ExportExcel(str, CountFriendsInfoDto.class, 2).setDataList(list).write(response, fileName).dispose();
			
			
	
	        
		}catch(Exception e) {
			logger.error("【导出朋友圈统计】",e);
		}
	
	}
	
}
