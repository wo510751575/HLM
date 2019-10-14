package com.lj.business.api.controller.rw;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.oms.entity.sys.User;
import com.lj.oms.service.ISystemService;
import com.ye.business.rw.constant.ErrorCodeMyCollection;
import com.ye.business.rw.dto.ArticleShareDto;
import com.ye.business.rw.dto.FindMyCollectionPage;
import com.ye.business.rw.dto.MyCollectionDto;
import com.ye.business.rw.enums.RwState;
import com.ye.business.rw.kit.HtmlKit;
import com.ye.business.rw.service.IArticleShareService;
import com.ye.business.rw.service.IMyCollectionService;

/**
 * 我的收藏
 * 
 * @author sjiying
 *
 */
@RestController
@RequestMapping("/rw/myCollection")
public class MyCollectionAction extends Action {

	/**
	 * 我的收藏
	 */
	@Autowired
	private IMyCollectionService myCollectionService;
	@Autowired
	private IGuidMemberService guidMemberService;

	/**
	 * 文章分享
	 */
	@Autowired
	private IArticleShareService articleShareService;

	@Autowired
	private ISystemService systemService;

	/**
	 * 列表
	 * 
	 * @param param
	 * @param findMyCollectionPage
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "list.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Page<MyCollectionDto> list(MyCollectionDto param, FindMyCollectionPage findMyCollectionPage, String token) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(param);

		String userid = getLoginUserByToken(token);

		User user = systemService.getUser(userid);
		if (user.isAdmin()) {
			// 超级管理员，可以查看所有信息
		} else if (User.USERTYPE_2.equals(user.getUserType()) && user.getOffice() != null) {
			// 商户管理员
			param.setMerchantNo(user.getOffice().getId());
		} else {
			// 普通用户
			param.setMemberNoGuid(userid);
		}

		param.setRwState(RwState.normal.toString());

		findMyCollectionPage.setParam(param);

		findMyCollectionPage.setSortBy("dateDesc");

		Page<MyCollectionDto> rs = myCollectionService.findMyCollectionPage(findMyCollectionPage);

		if (!rs.getRows().isEmpty()) {
			rs.getRows().forEach(action -> action.setTitle(StringEscapeUtils.unescapeHtml4(action.getTitle())));
		}

		return rs;
	}

	/**
	 * 文章阅读量
	 * 
	 * @param param
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "info.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public MyCollectionDto info(MyCollectionDto param, HttpServletRequest request) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getCode(), "CODE不能为空");

		// 获取明细，阅读量新增一次
		myCollectionService.updateMyCollectionForReadNum(param.getCode());

		MyCollectionDto rs = myCollectionService.findMyCollection(param);
		AssertUtils.notNullAndEmpty(rs);
		if (StringUtils.isNotBlank(rs.getTitle())) {
			rs.setTitle(StringEscapeUtils.unescapeHtml4(rs.getTitle()));
		}

		// 根据来源写入点击量
		String memberNoGuidSource = param.getMemberNoGuidSource();

		if (StringUtils.isNoneEmpty(memberNoGuidSource)) {

			Date now = new Date();

			ArticleShareDto share = new ArticleShareDto();
			share.setArticleCode(param.getCode());
			share.setCreateId(param.getMemberNoGuid());
			share.setMemberNoGuid(memberNoGuidSource);
			share.setShopNo(param.getShopNoSource());
			share.setMerchantNo(param.getMerchantNoSource());
			share.setRemark(this.getRemoteHost(request));
			share.setRemark2("collection");

			// 点击用户
			share.setMemberNameGuidView(param.getMemberNoGuid());
			share.setShopNoView(param.getShopNo());
			share.setMerchantNoView(param.getMerchantNo());

			share.setCreateDate(now);
			share.setUpdateTime(now);

			articleShareService.addArticleShare(share);
		}

		return rs;
	}

	/**
	 * H5列表
	 * 
	 * @param param
	 * @param findMyCollectionPage
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "myCollectionList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse myCollectionList(MyCollectionDto param, FindMyCollectionPage findMyCollectionPage, String token) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(param);

		String userid = getLoginUserByToken(token);

		User user = systemService.getUser(userid);
		if (user.isAdmin()) {
			// 超级管理员，可以查看所有信息
		} else if (User.USERTYPE_2.equals(user.getUserType()) && user.getOffice() != null) {
			// 商户管理员
			param.setMerchantNo(user.getOffice().getId());
		} else {
			// 普通用户
			param.setMemberNoGuid(userid);
		}

		findMyCollectionPage.setParam(param);

		findMyCollectionPage.setSortBy("dateDesc");

		Page<MyCollectionDto> rs = myCollectionService.findMyCollectionPage(findMyCollectionPage);

		if (!rs.getRows().isEmpty()) {
			rs.getRows().forEach(action -> action.setTitle(StringEscapeUtils.unescapeHtml4(action.getTitle())));
		}

		return GeneralResponse.generateSuccessResponse(rs);
	}

	/**
	 * 
	 * @param param
	 * @param findMyCollectionPage
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "myCollectionForm.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse myCollectionForm(MyCollectionDto param, String token) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getCode(), "code不能为空");

		MyCollectionDto data = myCollectionService.findMyCollection(param);
		return GeneralResponse.generateSuccessResponse(data);
	}

	/**
	 * 编辑
	 * 
	 * @param param
	 * @param findMyCollectionPage
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "myCollectionEdit.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse myCollectionEdit(MyCollectionDto param, String token) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getCode(), "编号为空");

		String userid = getLoginUserByToken(token);

		param.setCreateId(userid);
		param.setCreateDate(new Date()); // 重置创建日期

		myCollectionService.updateMyCollection(param);
		return GeneralResponse.generateSuccessResponse();
	}

	/**
	 * 保存
	 * 
	 * @param param
	 * @param findMyCollectionPage
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "myCollectionSave.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse myCollectionSave(MyCollectionDto param, String token) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getMemberNoGuid(), "用户编号为空");
		AssertUtils.notNullAndEmpty(param.getMerchantNo(), "商户编号为空");
		AssertUtils.notNullAndEmpty(param.getMainImage(), "略缩图为空");
		AssertUtils.notNullAndEmpty(param.getTitle(), "标题为空");

		String userid = getLoginUserByToken(token);

		param.setCreateDate(new Date());
		param.setLikeNum(0);
		param.setReadNum(0);
		param.setCreateId(userid);

		myCollectionService.addMyCollection(param);
		return GeneralResponse.generateSuccessResponse();
	}

	/**
	 * 删除
	 * 
	 * @param param
	 * @param findMyCollectionPage
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "myCollectionDelete.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse myCollectionDelete(MyCollectionDto param, String token) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getCode());

		getLoginUserByToken(token);

		myCollectionService.removeByPrimaryKey(param.getCode());

		return GeneralResponse.generateSuccessResponse();
	}

	/**
	 * 
	 * *方法说明：批量删除用户收藏信息
	 *
	 * @param codes
	 * @param token
	 * @return
	 * @throws TsfaServiceException
	 * @author sjiying
	 * @CreateDate 2019年7月2日
	 */
	@RequestMapping(value = "remove.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse remove(String codes, String token) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(codes);

		String userid = getLoginUserByToken(token);
		
		MyCollectionDto param = new MyCollectionDto();
		
		User user = systemService.getUser(userid);
		if (user.isAdmin()) {
			// 超级管理员，可以查看所有信息
		} else if (User.USERTYPE_2.equals(user.getUserType()) && user.getOffice() != null) {
			// 商户管理员
			param.setMerchantNo(user.getOffice().getId());
		} else {
			// 普通用户
			param.setMemberNoGuid(userid);
		}

		param.setCodeList(Arrays.asList(codes.split(",")));
//		param.setMemberNoGuid(userid);

		myCollectionService.removeByExample(param);

		return GeneralResponse.generateSuccessResponse();
	}

	/**
	 * 添加
	 * 
	 * @param param
	 * @param paramJson
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "add.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String add(MyCollectionDto param, String paramJson, String token) throws TsfaServiceException {

		// paramJson 对象转换出现问题，手动转换获取
		@SuppressWarnings("unchecked")
		Map<String, String> record = (Map<String, String>) JSON.parse(paramJson);

		String userid = getLoginUserByToken(token);

		param.setMainImage(record.getOrDefault("mainImage", null));
		param.setTitle(record.getOrDefault("title", null));
		param.setType(record.getOrDefault("type", null));
		param.setTypeCode(record.getOrDefault("typeCode", null));
		param.setSource(record.getOrDefault("source", null));
		param.setRemark(record.getOrDefault("remark", null));
		param.setRwState(record.getOrDefault("rwState", null));
		param.setMerchantNo(record.getOrDefault("merchantNo", null));
		param.setMemberNoGuid(record.getOrDefault("memberNoGuid", null));
		param.setCreateId(userid);
		param.setArticleHtml(record.getOrDefault("articleHtml", null));

		AssertUtils.notNullAndEmpty(param);
//		AssertUtils.notNullAndEmpty(param.getType(), "我的收藏类型不能为空");
		AssertUtils.notNullAndEmpty(param.getMainImage(), "我的收藏主图不能为空");
		AssertUtils.notNullAndEmpty(param.getTitle(), "我的收藏标题不能为空");
		AssertUtils.notNullAndEmpty(param.getArticleHtml(), "我的收藏内容不能为空");
		AssertUtils.notNullAndEmpty(param.getMerchantNo(), "商户不能为空");
		AssertUtils.notNullAndEmpty(param.getMemberNoGuid(), "用户不能为空");
		AssertUtils.notNullAndEmpty(param.getRwState(), "状态不能为空");
//		AssertUtils.notNullAndEmpty(param.getCreateId(), "创建人不能为空");

		// 验证是否字符串转码
		if ("1".equals(record.getOrDefault("state", "-1"))) {
			try {
				param.setArticleHtml(URLDecoder.decode(param.getArticleHtml(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				throw new TsfaServiceException(ErrorCodeMyCollection.MYCOLLECTION_ADD_ERROR, "我的收藏内容解码失败");
			}
		}

		param.setCreateDate(new Date());
		param.setReadNum(0);
		param.setLikeNum(0);

		return myCollectionService.addMyCollection(param);
	}

	/**
	 * 抓取链接中的我的收藏内容
	 * 
	 * @param uri
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "climb.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse climb(String uri, String token) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(uri, "获取地址不能为空");

		String userid = getLoginUserByToken(token);

//		String rs = HttpKit.doGet(uri);
		Map<String, String> map = HtmlKit.doGetMap(uri);
		String rs = map.get("body");

		FindGuidMember findGuidMember = new FindGuidMember();
		findGuidMember.setMemberNo(userid);
		FindGuidMemberReturn gm = guidMemberService.findGuidMember(findGuidMember);

		MyCollectionDto param = new MyCollectionDto();

		param.setMainImage(map.getOrDefault("img", ""));
		param.setTitle(map.getOrDefault("title", ""));
		param.setSource(uri.length() > 1000 ? uri.substring(0, 1000) : uri);
		param.setRwState(RwState.normal.toString());
		param.setMerchantNo(gm.getMerchantNo());
		param.setMerchantName(gm.getMerchantName());
		param.setMemberNoGuid(gm.getMemberNo());
		param.setMemberNameGuid(gm.getMemberName());
		param.setCreateId(userid);
		param.setArticleHtml(rs);
		param.setCreateDate(new Date());
		param.setReadNum(0);
		param.setLikeNum(0);

		String code = myCollectionService.addMyCollection(param);

		param.setCode(code);

		Map<String, String> data = Maps.newHashMap();
		data.put("mainImage", param.getMainImage());
		data.put("title", param.getTitle());
		data.put("code", code);

		return GeneralResponse.generateSuccessResponse(data);
	}

}
