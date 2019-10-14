package com.lj.business.api.controller.ad;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ape.common.utils.Collections3;
import com.google.common.collect.Maps;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.exception.TsfaWebException;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberDto;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.dto.FindGuidMemberPageReturn;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.FindGuidMemberRwPage;
import com.lj.business.member.dto.GuidMemberReturnDto;
import com.lj.business.member.dto.GuidMemberRwDto;
import com.lj.business.member.service.IGuidMemberRwService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;
import com.ye.business.ad.dto.AdvertiseDto;
import com.ye.business.ad.dto.AdvertiseShowDto;
import com.ye.business.ad.dto.AdvertiseViewDto;
import com.ye.business.ad.dto.FindAdvertisePage;
import com.ye.business.ad.enums.AdvStatus;
import com.ye.business.ad.enums.RwState;
import com.ye.business.ad.service.IAdvertiseService;
import com.ye.business.ad.service.IAdvertiseShowService;
import com.ye.business.ad.service.IAdvertiseViewService;
import com.ye.business.rw.constant.RwConstant;

/**
 * 
 * *类说明：广告
 *
 * </p>
 * *详细描述：
 * 
 * @author sjiying
 * @CeateDate 2019年5月8日
 */
@RestController
@RequestMapping("/ad/advertise")
public class AdvertiseAction extends Action {

	/**
	 * 文章
	 */
	@Autowired
	private IAdvertiseService advertiseService;

	@Autowired
	private IAdvertiseViewService advertiseViewService;
	@Autowired
	private IAdvertiseShowService advertiseShowService;

	@Autowired
	private IGuidMemberService guidMemberService;

	@Autowired
	private IGuidMemberRwService guidMemberRwService;

	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;

	/**
	 * 
	 * *方法说明：广告列表： 扣除广告豆等记录
	 *
	 * @param param
	 * @param findAdvertisePage
	 * @param request
	 * @return
	 * @throws TsfaServiceException
	 * @author sjiying
	 * @CreateDate 2019年6月4日
	 */
	@RequestMapping(value = "list.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<AdvertiseDto> list(AdvertiseDto param, FindAdvertisePage findAdvertisePage, HttpServletRequest request) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(param);
		// AssertUtils.notNullAndEmpty(param.getMerchantNo(), "商户编号不能为空");
		// AssertUtils.notNullAndEmpty(param.getUpdateId(), "操作人ID不能为空");

		if (StringUtils.isNotEmpty(param.getUpdateId())) {

			FindGuidMemberDto findGuidMember = new FindGuidMemberDto();
			findGuidMember.setMemberNo(param.getUpdateId());
			GuidMemberReturnDto gm = guidMemberService.findGuid(findGuidMember);

			param.setCurrLoginName(gm.getLoginName());
			param.setCurrMemberName(gm.getMemberName());
			param.setCurrMemberNo(gm.getMemberNo());
			param.setCurrMerchantName(gm.getMerchantName());
			param.setCurrMerchantNo(gm.getMerchantNo());
		} else {
			param.setCurrLoginName("系统用户");
			param.setCurrMemberName("系统用户");
			param.setCurrMemberNo("1");
			param.setCurrMerchantName("系统用户");
			String officeId = localCacheSystemParams.getSystemParam(SystemAliasName.api.toString(), RwConstant.DEFAULT_COMPANY, RwConstant.OFFICE_ID);
			param.setCurrMerchantNo(officeId);
		}

		// 跟踪信息
		param.setRemark(getRemoteHost(request));

		findAdvertisePage.setParam(param);

		return advertiseService.findRandomRecordList(findAdvertisePage);
	}

	/**
	 * H5广告列表
	 * 
	 * @param param
	 * @param findAdvertisePage
	 * @param request
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "adList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse adList(AdvertiseDto param, FindAdvertisePage findAdvertisePage, String token) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(param);
		// AssertUtils.notNullAndEmpty(param.getMerchantNo(), "商户编号不能为空");
		// AssertUtils.notNullAndEmpty(param.getUpdateId(), "操作人ID不能为空");

		// 跟踪信息
		param.setHasTrack(true);

		getLoginUserByToken(token);

		findAdvertisePage.setParam(param);

		findAdvertisePage.setSortBy("dateDesc");

		Map<String, Object> rs = Maps.newHashMap();

		Page<AdvertiseDto> pageDto = advertiseService.findAdvertisePage(findAdvertisePage);

		rs.put("page", pageDto);

		List<String> memberNoList = pageDto.getRows().stream().filter(temp -> StringUtils.isNotEmpty(temp.getMemberNoGuid())).map(AdvertiseDto::getMemberNoGuid)
				.collect(Collectors.toList());

		if (!Collections3.isEmpty(memberNoList)) {
			// 商户记录中获取相关用户信息
			FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
			findGuidMemberPage.setMemberNoList(memberNoList);
			List<FindGuidMemberPageReturn> findGuidMemberPageReturnList = guidMemberService.findGuidMembers(findGuidMemberPage);
			Map<String, FindGuidMemberPageReturn> findGuidMemberPageReturnMap = findGuidMemberPageReturnList.stream()
					.collect(Collectors.toMap(FindGuidMemberPageReturn::getMemberNo, valueMapper -> {
						valueMapper.setPwd(null);
						valueMapper.setEncryptionCode(null);
						return valueMapper;
					}));
			rs.put("userMap", findGuidMemberPageReturnMap);

			// 获取客户扩展信息
			GuidMemberRwDto findGuidMemberRwDto = new GuidMemberRwDto();
			findGuidMemberRwDto.setCodeList(memberNoList);

			FindGuidMemberRwPage findGuidMemberRwPage = new FindGuidMemberRwPage();
			findGuidMemberRwPage.setParam(findGuidMemberRwDto);

			List<GuidMemberRwDto> findGuidMemberRwDtoReturn = guidMemberRwService.findGuidMemberRws(findGuidMemberRwPage);

			Map<String, GuidMemberRwDto> returnGuidMemberRwMap = findGuidMemberRwDtoReturn.stream()
					.collect(Collectors.toMap(GuidMemberRwDto::getCode, GuidMemberRwDto -> GuidMemberRwDto));

			rs.put("userRwMap", returnGuidMemberRwMap);
		}

		return GeneralResponse.generateSuccessResponse(rs);
	}

	/**
	 * 
	 * *方法说明：H5 广告Form
	 *
	 * @param param
	 * @param token
	 * @return
	 * @throws TsfaServiceException
	 * @author sjiying
	 * @CreateDate 2019年6月28日
	 */
	@RequestMapping(value = "adForm.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse adForm(AdvertiseDto param, String token) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getCode(), "广告CODE不能为空");
		// 验证用户是否登陆
		getLoginUserByToken(token);

		Map<String, Object> rs = Maps.newHashMap();

		AdvertiseDto data = advertiseService.findAdvertise(param);
		rs.put("data", data);

		if (data != null) {

			FindGuidMember findGuidMember = new FindGuidMember();
			findGuidMember.setMemberNo(data.getMemberNoGuid());
			FindGuidMemberReturn findGuidMemberReturn = guidMemberService.findGuidMember(findGuidMember);
			if (findGuidMemberReturn != null) {
				findGuidMemberReturn.setPwd(null);
				findGuidMemberReturn.setEncryptionCode(null);
			}

			rs.put("user", findGuidMemberReturn);

			// 获取客户扩展信息
			GuidMemberRwDto findGuidMemberRwDto = new GuidMemberRwDto();
			findGuidMemberRwDto.setCode(data.getMemberNoGuid());
			GuidMemberRwDto userRw = guidMemberRwService.findGuidMemberRw(findGuidMemberRwDto);
			rs.put("userRw", userRw);
		}

		return GeneralResponse.generateSuccessResponse(rs);
	}

	/**
	 * H5 广告保存
	 * 
	 * @param param
	 * @param findAdvertisePage
	 * @param request
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "adSave.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse adSave(AdvertiseDto param, String token) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getAdvLink(), "广告地址不能为空");
		AssertUtils.notNullAndEmpty(param.getAdvState(), "广告类型不能为空");
//		AssertUtils.notNullAndEmpty(param.getAdvStatus(), "广告状态不能为空");
		AssertUtils.notNullAndEmpty(param.getAdvType(), "广告地址不能为空");
		AssertUtils.notNullAndEmpty(param.getAdvTypeCode(), "广告类型code不能为空");
		AssertUtils.notNullAndEmpty(param.getLink(), "访问路径不能为空");
		AssertUtils.notNullAndEmpty(param.getType(), "类型不能为空");
		AssertUtils.notNullAndEmpty(param.getState(), "状态不能为空");
		AssertUtils.notNullAndEmpty(param.getUpDate(), "上架时间不能为空");

		AssertUtils.notNullAndEmpty(param.getMemberNoGuid(), "用戶不能为空");
		AssertUtils.notNullAndEmpty(param.getMerchantNo(), "商戶不能为空");
		
		if (RwState.pay.toString().equals(param.getAdvState())) {
			AssertUtils.notNullAndEmpty(param.getPriceSum(), "总积分不能为空");
			AssertUtils.notNullAndEmpty(param.getPriceClick(), "点击积分不能为空");
			AssertUtils.notNullAndEmpty(param.getPriceView(), "显示积分不能为空");
		}
		

		String userid = this.getLoginUserByToken(token);
		Date now = new Date();

		param.setCreateDate(now);
		param.setUpdateDate(now);
		param.setReleaseDate(now);
		param.setCreateId(userid);
		param.setUpdateId(userid);

		// 上架时间大于当前时间，则为上架状态；
		LocalDateTime sysNow = LocalDateTime.now();
		if (param.getUpDate() != null) {
			LocalDateTime up = param.getUpDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			if (up.isBefore(sysNow) || up.isEqual(sysNow)) {
				param.setAdvStatus(AdvStatus.up.toString());
			} else {
				param.setAdvStatus(AdvStatus.no.toString());
			}
		} else {
			param.setAdvStatus(AdvStatus.no.toString());
		}

		// 下架时间不为空，下架时间小于当前时间
		if (param.getDownDate() != null) {
			LocalDateTime down = param.getDownDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			if (down.isBefore(sysNow)) {
				param.setAdvStatus(AdvStatus.down.toString());
			}
		}

		advertiseService.addAdvertise(param);

		return GeneralResponse.generateSuccessResponse();
	}

	/**
	 * H5 广告编辑
	 * 
	 * @param param
	 * @param findAdvertisePage
	 * @param request
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "adEdit.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse adEdit(AdvertiseDto param, String token) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getCode(), "编号为空");

		String userid = getLoginUserByToken(token);

		Date now = new Date();

		// 上架时间大于当前时间，则为上架状态；
		LocalDateTime sysNow = LocalDateTime.now();
		if (param.getUpDate() != null) {
			LocalDateTime up = param.getUpDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			if (up.isBefore(sysNow) || up.isEqual(sysNow)) {
				param.setAdvStatus(AdvStatus.up.toString());
			} else {
				param.setAdvStatus(AdvStatus.no.toString());
			}
		} else {
			param.setAdvStatus(AdvStatus.no.toString());
		}

		// 下架时间不为空，下架时间小于当前时间
		if (param.getDownDate() != null) {
			LocalDateTime down = param.getDownDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			if (down.isBefore(sysNow)) {
				param.setAdvStatus(AdvStatus.down.toString());
			}
		}
		
//		if (!AdvStatus.down.toString().equals(param.getAdvStatus())) {
//			//未下架，不允许修改总金额数据
//			param.setPriceSum(null);
//		}

		param.setUpdateDate(now);
		param.setUpdateId(userid);

		advertiseService.updateAdvertise(param);
		return GeneralResponse.generateSuccessResponse();

	}

	/**
	 * H5 广告删除
	 * 
	 * @param param
	 * @param findAdvertisePage
	 * @param request
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "adDelete.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse adDelete(AdvertiseDto param, String token) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getCode(), "编号为空");

		getLoginUserByToken(token);

		advertiseService.removeByPrimaryKey(param.getCode());

		return GeneralResponse.generateSuccessResponse();
	}

	/**
	 * 
	 * *方法说明：广告点击记录，扣除广告豆等操作
	 *
	 * @param param
	 * @return
	 * @throws TsfaWebException
	 * @author sjiying
	 * @CreateDate 2019年5月15日
	 */
	@RequestMapping(value = "view.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AdvertiseDto view(AdvertiseDto param, HttpServletRequest request) throws TsfaWebException {

		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getCode(), "广告code不能为空");
//		AssertUtils.notNullAndEmpty(param.getUpdateId(), "操作人ID不能为空");

		if (StringUtils.isNotEmpty(param.getUpdateId())) {

			FindGuidMemberDto findGuidMember = new FindGuidMemberDto();
			findGuidMember.setMemberNo(param.getUpdateId());
			GuidMemberReturnDto gm = guidMemberService.findGuid(findGuidMember);

			param.setCurrLoginName(gm.getLoginName());
			param.setCurrMemberName(gm.getMemberName());
			param.setCurrMemberNo(gm.getMemberNo());
			param.setCurrMerchantName(gm.getMerchantName());
			param.setCurrMerchantNo(gm.getMerchantNo());
		} else {
			param.setCurrLoginName("系统用户");
			param.setCurrMemberName("系统用户");
			param.setCurrMemberNo("1");
			param.setCurrMerchantName("系统用户");
			String officeId = localCacheSystemParams.getSystemParam(SystemAliasName.api.toString(), RwConstant.DEFAULT_COMPANY, RwConstant.OFFICE_ID);
			param.setCurrMerchantNo(officeId);
		}

		param.setHasTrack(true);
		param.setRemark(getRemoteHost(request));

		return advertiseService.findAdvertiseForView(param);
	}

	/**
	 * 统计广告展示
	 * 
	 * @param param
	 * @param request
	 * @return
	 * @throws TsfaWebException
	 */
	@RequestMapping(value = "advertiseShow.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<AdvertiseDto> advertiseShow(AdvertiseDto param, HttpServletRequest request) throws TsfaWebException {

		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getCode(), "广告code不能为空");
//		AssertUtils.notNullAndEmpty(param.getUpdateId(), "操作人ID不能为空");

		/*
		 * if (StringUtils.isEmpty(param.getUpdateId())) { param.setUpdateId("-1"); }
		 */

		param.setHasTrack(true);
		param.setRemark(getRemoteHost(request));
		List<AdvertiseDto> list = new ArrayList<>();
		String[] codes = param.getCode().split(",");
		for (String code : codes) {
			param.setCode(code);
			AdvertiseDto advertiseDto = advertiseService.findAdvertise(param);
			list.add(advertiseDto);
		}
		return list;
	}

	/**
	 * 我的广告
	 * 
	 * @param param
	 * @param findAdvertisePage
	 * @return
	 * @throws TsfaWebException
	 */
	@RequestMapping(value = "advertiseShowPage.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse advertisePage(AdvertiseDto param, FindAdvertisePage findAdvertisePage) throws TsfaWebException {
		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getAdvState(), "广告类型不能为空");
		AssertUtils.notNullAndEmpty(param.getMerchantNo(), "商户编号不能为空");
		// AssertUtils.notNullAndEmpty(param.getUpdateId(), "操作人ID不能为空");
		
		param.setHasTrack(true);
		
		findAdvertisePage.setParam(param);
		try {
			
			Page<AdvertiseDto> pageDto = advertiseService.findAdvertisePage(findAdvertisePage);
//			Collection<AdvertiseDto> rows = pageDto.getRows();
			
//			List<String> codeList = rows.stream().map(AdvertiseDto::getCode).collect(Collectors.toList());
//			for (AdvertiseDto advertiseDto : rows) {
//				int numShow = advertiseViewService.selectAdvertiseViewCount(advertiseDto);
//				String createDate = DateUtils.formatDate(advertiseDto.getCreateDate(), "yyyy-MM-dd");
//				advertiseDto.setCreateDateStr(createDate);
//				advertiseDto.setNumShow(numShow);
//				advertiseDto.setPriceRemain(0);
//			}
			return GeneralResponse.generateSuccessResponse(pageDto);
		} catch (Exception e) {
			return GeneralResponse.generateFailureResponse();
		}

	}

	/**
	 * 广告效果
	 * 
	 * @param param
	 * @return
	 * @throws TsfaWebException
	 */
	@RequestMapping(value = "advertiseDetail.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse advertiseDetail(AdvertiseViewDto param) throws TsfaWebException {
		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getAdvertiseCode(), "广告编号不能为空");
		// AssertUtils.notNullAndEmpty(param.getMerchantNo(), "商户编号不能为空");
		// 统计时间为空就初始化默认时间
		if (null == param.getCheckTime() || com.lj.base.core.util.StringUtils.isEmpty(param.getCheckTime().toString())) {
			param.setCheckTime(new Date());
		}

		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> countList = new ArrayList<>();
		List<Map<String, Object>> priceList = new ArrayList<>();
		Calendar ca = Calendar.getInstance();
		ca.setTime(param.getCheckTime());
		ca.add(Calendar.DAY_OF_MONTH, -7);

		for (int i = 0; i < 7; i++) {
			ca.add(Calendar.DAY_OF_MONTH, 1);
			String startDate = DateUtils.formatDate(ca.getTime(), DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss);
			startDate = startDate.substring(0, 10) + " 00:00:00";
			String endDate = startDate.substring(0, 10) + " 23:59:59";
			try {
				param.setStartTime(DateUtils.parseDate(startDate, DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss));
				param.setEndTime(DateUtils.parseDate(endDate, DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss));
				int count = advertiseViewService.getCountView(param);
				Map<String, Object> countMap = new HashMap<>();
				countMap.put(startDate.substring(0, 10), count);
				countList.add(countMap);

				Map<String, Object> priceMap = new HashMap<>();
				priceMap.put(startDate.substring(0, 10), count * param.getPriceView());
				priceList.add(priceMap);
			} catch (ParseException e) {
				e.printStackTrace();
				logger.error("时间转换发生错误", e);
				return GeneralResponse.generateFailureResponse();
			}
		}
		map.put("count", countList);
		map.put("price", priceList);

		return GeneralResponse.generateSuccessResponse(map);
	}

	/**
	 * 
	 * *方法说明：广告统计
	 *
	 * @return
	 * @throws TsfaWebException
	 * @author sjiying
	 * @CreateDate 2019年7月1日
	 */
	@RequestMapping(value = "statistics.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse statistics(String token, AdvertiseDto param) throws TsfaWebException {

		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getCode(), "广告编号不能为空");

		getLoginUserByToken(token);

		Date now = new Date();

		if (param.getBeginDate() == null) {
			param.setBeginDate(now);
		}
		if (param.getEndDate() == null) {
			param.setEndDate(now);
		}
		if (StringUtils.isEmpty(param.getTimeDimension())) {
			param.setTimeDimension("day");
		}

		// 展示量
		List<AdvertiseViewDto> viewList = advertiseViewService.findGroupTotalByExample(param);

		List<AdvertiseShowDto> showList = advertiseShowService.findGroupTotalByExample(param);

		Map<String, Object> rs = Maps.newHashMap();

		rs.put("viewList", viewList);
		rs.put("showList", showList);

		return GeneralResponse.generateSuccessResponse(rs);
	}

}
