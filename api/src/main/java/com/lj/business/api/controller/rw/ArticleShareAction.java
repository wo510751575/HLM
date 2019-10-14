package com.lj.business.api.controller.rw;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ape.common.utils.Collections3;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaRuntimeException;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.dto.FindGuidMemberPageReturn;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.oms.entity.sys.User;
import com.lj.oms.service.ISystemService;
import com.ye.business.ad.dto.AdvertiseShowDto;
import com.ye.business.ad.service.IAdvertiseShowService;
import com.ye.business.rw.dto.ArticleDto;
import com.ye.business.rw.dto.ArticleShareDto;
import com.ye.business.rw.dto.ArticleViewDto;
import com.ye.business.rw.dto.FindArticlePage;
import com.ye.business.rw.dto.FindArticleSharePage;
import com.ye.business.rw.dto.FindArticleViewPage;
import com.ye.business.rw.dto.FindRwUserPage;
import com.ye.business.rw.dto.RwUserDto;
import com.ye.business.rw.service.IArticleService;
import com.ye.business.rw.service.IArticleShareService;
import com.ye.business.rw.service.IArticleViewService;
import com.ye.business.rw.service.IRwUserService;

/**
 * 热文分享记录
 * 
 * @author sjiying
 *
 */
@RestController
@RequestMapping("/rw/articleShare")
public class ArticleShareAction extends Action {
	/**
	 * 文章分享
	 */
	@Autowired
	private IArticleShareService articleShareService;
	@Autowired
	private IRwUserService rwUserService;
	/**
	 * 文章
	 */
	@Autowired
	private IArticleService articleService;

	@Autowired
	private IGuidMemberService guidMemberService;
	@Autowired
	private ISystemService systemService;
	@Autowired
	private IAdvertiseShowService advertiseShowService;
	@Autowired
	private IArticleViewService articleViewService;

	/**
	 * 分享统计
	 * 
	 * @param param
	 * @param findArticleSharePage
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "shareList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse shareList(ArticleDto param, FindArticlePage findArticlePage) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getMemberNoGuid(), "用户编号不能为空");
		findArticlePage.setParam(param);
		Page<ArticleDto> rs = articleService.findArticleShareReadPage(findArticlePage);
		return GeneralResponse.generateSuccessResponse(rs);

	}

	/**
	 * 列表分组统计点击量
	 * 
	 * @param param
	 * @param findArticlePage
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "list.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Page<ArticleShareDto> list(ArticleShareDto param, FindArticleSharePage findArticleSharePage) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getMemberNoGuid(), "用户编号不能为空");

		param.setHasMemberNoGuidViewNotEmpty(param.getMemberNoGuid());

		findArticleSharePage.setParam(param);

		findArticleSharePage.setSortBy("dateDesc");

		Page<ArticleShareDto> rs = articleShareService.findArticleSharePageForGroup(findArticleSharePage);

		return rs;
	}

	/**
	 * 
	 * *方法说明：我的分享阅读明细
	 *
	 * @param param
	 * @param findArticleSharePage
	 * @return
	 * @throws TsfaServiceException
	 * @author sjiying
	 * @CreateDate 2019年6月5日
	 */
	@RequestMapping(value = "listInfo.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Page<ArticleShareDto> listInfo(ArticleShareDto param, FindArticleSharePage findArticleSharePage) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getMemberNoGuid(), "用户编号不能为空");
		AssertUtils.notNullAndEmpty(param.getArticleCode(), "文章编号不能为空");

		// param.setHasMemberNoGuidViewNotEmpty(param.getMemberNoGuid());

		findArticleSharePage.setParam(param);

		findArticleSharePage.setSortBy("dateDesc");

		Page<ArticleShareDto> rs = articleShareService.findArticleSharePageForView(findArticleSharePage);

		if (CollectionUtils.isNotEmpty(rs.getRows())) {
			List<String> memberNoGuidList = rs.getRows().stream().map(ArticleShareDto::getMemberNoGuidView).collect(Collectors.toList());
			if (CollectionUtils.isNotEmpty(memberNoGuidList)) {

				FindRwUserPage findRwUserPage = new FindRwUserPage();
				RwUserDto rwUser = new RwUserDto();

				rwUser.setMemberNoGuidList(memberNoGuidList);

				findRwUserPage.setParam(rwUser);

				List<RwUserDto> rwUserRecord = rwUserService.findRwUsers(findRwUserPage);
				Map<String, RwUserDto> rwUserRecordMap = rwUserRecord.stream().collect(Collectors.toMap(RwUserDto::getMemberNoGuid, RwUserDto -> RwUserDto));
				rs.getRows().forEach(action -> {
					if (rwUserRecordMap.containsKey(action.getMemberNoGuid())) {
						RwUserDto temp = rwUserRecordMap.get(action.getMemberNoGuid());
						action.setHeadAddress(temp.getHeadAddress());
						action.setNickName(temp.getNickName());
					}
				});
			}
		}

		return rs;
	}

	/**
	 * 
	 * *方法说明：员工分享H5
	 *
	 * @param findGuidMemberPage
	 * @param token
	 * @return
	 * @throws TsfaRuntimeException
	 * @author sjiying
	 * @CreateDate 2019年7月3日
	 */
	@RequestMapping(value = "shareGroupList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse shareGroupList(FindGuidMemberPage findGuidMemberPage, String token) throws TsfaRuntimeException {

		String userid = getLoginUserByToken(token);

		User user = systemService.getUser(userid);

		if (user.isAdmin()) {
			// 管理员查看所有
		} else if (User.USERTYPE_2.equals(user.getUserType())) {
			// 查询当前商户下的所有用户
			FindGuidMember findGuidMember = new FindGuidMember();
			findGuidMember.setMemberNo(userid);
			FindGuidMemberReturn gm = guidMemberService.findGuidMember(findGuidMember);

			findGuidMemberPage.setMerchantNo(gm.getMerchantNo());
		} else {
			findGuidMemberPage.setMemberNo(userid);
		}

		findGuidMemberPage.setImei(null); // 置空，Android 会传递此值，导致查询不出结果
		
		Page<FindGuidMemberPageReturn> guidPage = guidMemberService.findGuidMemberPage(findGuidMemberPage);

		List<String> memberNoList = guidPage.getRows().stream().map(temp -> temp.getMemberNo()).collect(Collectors.toList());

		if (!Collections3.isEmpty(memberNoList)) {

			// 员工分享文章记录统计
			ArticleShareDto param = new ArticleShareDto();
			param.setMemberNoGuidList(memberNoList);
//			param.setRemark4(RwConstant.SHARE);
			param.setStartTime(findGuidMemberPage.getStartTime());
			param.setEndTime(findGuidMemberPage.getEndTime());
			List<ArticleShareDto> shareList = articleShareService.findArticleShareForGroupList(param);
			Map<String, ArticleShareDto> shareMap = shareList.stream().collect(Collectors.toMap(ArticleShareDto::getMemberNoGuid, ArticleShareDto -> ArticleShareDto));

			// 员工分享文章，显示广告统计
			AdvertiseShowDto paramShow = new AdvertiseShowDto();
			paramShow.setMemberNoGuidList(memberNoList);
			paramShow.setStartTime(findGuidMemberPage.getStartTime());
			paramShow.setEndTime(findGuidMemberPage.getEndTime());
			List<AdvertiseShowDto> showList = advertiseShowService.findAdvertiseShowForGroupList(paramShow);
			Map<String, AdvertiseShowDto> showMap = showList.stream().collect(Collectors.toMap(AdvertiseShowDto::getCreateId, AdvertiseShowDto -> AdvertiseShowDto));

			// 员工分享文章，点击统计
			ArticleViewDto view = new ArticleViewDto();
			view.setMemberNoGuidList(memberNoList);
			view.setStartTime(findGuidMemberPage.getStartTime());
			view.setEndTime(findGuidMemberPage.getEndTime());
			List<ArticleViewDto> viewList = articleViewService.findArticleViewForGroupList(view);
			Map<String, ArticleViewDto> viewMap = viewList.stream().collect(Collectors.toMap(ArticleViewDto::getMemberNoGuid, ArticleViewDto -> ArticleViewDto));

			guidPage.getRows().forEach(temp -> {

				if (shareMap.containsKey(temp.getMemberNo())) {
					temp.setShareDate(shareMap.get(temp.getMemberNo()).getCreateDate());
					temp.setShareNum(shareMap.get(temp.getMemberNo()).getReadNum());
				}
				if (showMap.containsKey(temp.getMemberNo())) {
					temp.setAdShowDate(showMap.get(temp.getMemberNo()).getCreateDate());
					temp.setAdShowNum(showMap.get(temp.getMemberNo()).getNum());
				}
				if (viewMap.containsKey(temp.getMemberNo())) {
					temp.setShowDate(viewMap.get(temp.getMemberNo()).getCreateDate());
					temp.setShowNum(viewMap.get(temp.getMemberNo()).getReadNum());
				}
			});
		}

		return GeneralResponse.generateSuccessResponse(guidPage);
	}

	/**
	 * 
	 * *方法说明：员工分享明细H5
	 *
	 * @param param
	 * @param findArticleSharePage
	 * @param token
	 * @return
	 * @throws TsfaRuntimeException
	 * @author sjiying
	 * @CreateDate 2019年7月3日
	 */
	@RequestMapping(value = "shareGroupInfoList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse shareGroupInfoList(ArticleShareDto param, FindArticleSharePage findArticleSharePage, String token) throws TsfaRuntimeException {

		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getMemberNoGuid(), "用户编号不能为空");

		findArticleSharePage.setParam(param);

		Page<ArticleShareDto> shareList = articleShareService.findArticleShareInfoForGroupList(findArticleSharePage);
		List<String> articleCodeList = shareList.getRows().stream().map(as -> as.getArticleCode()).collect(Collectors.toList());

		if (!Collections3.isEmpty(articleCodeList)) {

			AdvertiseShowDto paramShow = new AdvertiseShowDto();
			paramShow.setCreateId(param.getMemberNoGuid());
			paramShow.setStartTime(param.getStartTime());
			paramShow.setEndTime(param.getEndTime());
			List<AdvertiseShowDto> showList = advertiseShowService.findAdvertiseShowInfoForGroupList(paramShow);
			Map<String, AdvertiseShowDto> showMap = showList.stream().collect(Collectors.toMap(key -> key.getCreateId() + key.getArticleCode(), AdvertiseShowDto -> AdvertiseShowDto));

			ArticleViewDto view = new ArticleViewDto();
			view.setArticleCodeList(articleCodeList);
			FindArticleViewPage findArticleViewPage = new FindArticleViewPage();
			findArticleViewPage.setParam(view);
			Page<ArticleViewDto> viewList = articleViewService.findArticleViewInfoForGroupList(findArticleViewPage);
//			Map<String, ArticleViewDto> viewMap = Maps.newHashMap();
//			if (!Collections3.isEmpty(viewList.getRows())) {
//				viewList.getRows().forEach(action -> {
//					viewMap.put(action.getMemberNoGuid(), action);
//				});
//			}
			Map<String, ArticleViewDto> viewMap = viewList.getRows().stream().collect(Collectors.toMap(key -> key.getMemberNoGuid() + key.getArticleCode(), ArticleViewDto -> ArticleViewDto));
					

			shareList.getRows().forEach(temp -> {
				
				String key = temp.getMemberNoGuid() + temp.getArticleCode();
				
				if (showMap.containsKey(key)) {
					temp.setAdShowDate(showMap.get(key).getCreateDate());
					temp.setAdShowNum(showMap.get(key).getNum());
				}
				if (viewMap.containsKey(key)) {
					temp.setShowDate(viewMap.get(key).getCreateDate());
					temp.setShowNum(viewMap.get(key).getReadNum());
				}
			});
		}

		return GeneralResponse.generateSuccessResponse(shareList);

	}

}
