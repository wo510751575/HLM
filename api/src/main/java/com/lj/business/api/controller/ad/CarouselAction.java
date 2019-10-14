package com.lj.business.api.controller.ad;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ape.common.utils.Collections3;
import com.google.common.collect.Maps;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.exception.TsfaWebException;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.dto.FindGuidMemberPageReturn;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.FindGuidMemberRwPage;
import com.lj.business.member.dto.GuidMemberRwDto;
import com.lj.business.member.service.IGuidMemberRwService;
import com.lj.business.member.service.IGuidMemberService;
import com.ye.business.ad.dto.CarouselDto;
import com.ye.business.ad.dto.CarouselShowDto;
import com.ye.business.ad.dto.CarouselViewDto;
import com.ye.business.ad.dto.FindCarouselPage;
import com.ye.business.ad.enums.AdvStatus;
import com.ye.business.ad.service.ICarouselService;
import com.ye.business.ad.service.ICarouselShowService;
import com.ye.business.ad.service.ICarouselViewService;
import com.ye.business.rw.enums.RwState;

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
@RequestMapping("/ad/carousel")
public class CarouselAction extends Action {

	/**
	 * 文章
	 */
	@Autowired
	private ICarouselService carouselService;

	@Autowired
	private IGuidMemberService guidMemberService;

	@Autowired
	private IGuidMemberRwService guidMemberRwService;

	@Autowired
	private ICarouselViewService carouselViewService;
	@Autowired
	private ICarouselShowService carouselShowService;

	/**
	 * 列表
	 * 
	 * @param param
	 * @param findCarouselPage
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "list.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CarouselDto> list(CarouselDto param, FindCarouselPage findCarouselPage, HttpServletRequest request) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(param);
		// AssertUtils.notNullAndEmpty(param.getMerchantNo(), "商户编号不能为空");
		// AssertUtils.notNullAndEmpty(param.getUpdateId(), "操作人ID不能为空");

		param.setState(RwState.normal.toString());

		// 跟踪信息
		param.setHasTrack(true);
		param.setRemark(getRemoteHost(request));

		findCarouselPage.setParam(param);

		findCarouselPage.setSortBy("numOrder");
		List<CarouselDto> list = carouselService.findCarousels(findCarouselPage);

		return list;
	}

	/**
	 * H5广告列表
	 * 
	 * @param param
	 * @param findCarouselPage
	 * @param request
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "carouseList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse carouseList(CarouselDto param, FindCarouselPage findCarouselPage, String token) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(param);
//		AssertUtils.notNullAndEmpty(param.getMerchantNo(), "商户编号不能为空");
//		AssertUtils.notNullAndEmpty(param.getUpdateId(), "操作人ID不能为空");

		// 跟踪信息
		param.setHasTrack(true);

		getLoginUserByToken(token);

		findCarouselPage.setParam(param);

		findCarouselPage.setSortBy("dateDesc");

		Map<String, Object> rs = Maps.newHashMap();

		Page<CarouselDto> pageDto = carouselService.findCarouselPage(findCarouselPage);

		rs.put("page", pageDto);

		List<String> memberNoList = pageDto.getRows().stream().filter(temp -> StringUtils.isNotEmpty(temp.getMemberNoGuid())).map(CarouselDto::getMemberNoGuid)
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
	 * H5 轮播广告Form
	 * 
	 * @param param
	 * @param findCarouselPage
	 * @param request
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "carouseForm.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse carouseForm(CarouselDto param, String token) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getCode(), "CODE不能为空");
		// 验证用户是否登陆
		getLoginUserByToken(token);

		Map<String, Object> rs = Maps.newHashMap();

		CarouselDto data = carouselService.findCarousel(param);
		rs.put("data", data);

		if (data != null && StringUtils.isNotEmpty(data.getMemberNoGuid())) {

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
	 * H5 轮播广告编辑
	 * 
	 * @param param
	 * @param findCarouselPage
	 * @param request
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "carouseEdit.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse carouseEdit(CarouselDto param, String token) throws TsfaServiceException {
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

		param.setUpdateDate(now);
		param.setUpdateId(userid);

		carouselService.updateCarousel(param);
		return GeneralResponse.generateSuccessResponse();
	}

	/**
	 * H5 轮播广告保存
	 * 
	 * @param param
	 * @param findCarouselPage
	 * @param request
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "carouseSave.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse carouseSave(CarouselDto param, String token) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(param);
//		AssertUtils.notNullAndEmpty(param.getAdvLink(), "广告地址不能为空");
		AssertUtils.notNullAndEmpty(param.getState(), "广告类型不能为空");
		AssertUtils.notNullAndEmpty(param.getAdvType(), "广告类型不能为空");
		AssertUtils.notNullAndEmpty(param.getAdvTypeCode(), "广告类型code不能为空");
		AssertUtils.notNullAndEmpty(param.getLink(), "访问路径不能为空");
		AssertUtils.notNullAndEmpty(param.getState(), "状态不能为空");
		AssertUtils.notNullAndEmpty(param.getUpDate(), "上架时间不能为空");

		AssertUtils.notNullAndEmpty(param.getMemberNoGuid(), "用戶不能为空");
		AssertUtils.notNullAndEmpty(param.getMerchantNo(), "商戶不能为空");

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

		carouselService.addCarousel(param);
		return GeneralResponse.generateSuccessResponse();
	}

	/**
	 * H5 轮播广告删除
	 * 
	 * @param param
	 * @param findCarouselPage
	 * @param request
	 * @return
	 * @throws TsfaServiceException
	 */
	@RequestMapping(value = "carouseDelete.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse carouseDelete(CarouselDto param, String token) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getCode(), "编号为空");

		getLoginUserByToken(token);
		carouselService.removeByPrimaryKey(param.getCode());
		return GeneralResponse.generateSuccessResponse();
	}

	/**
	 * 
	 * *方法说明：广告点击记录
	 *
	 * @param param
	 * @return
	 * @throws TsfaWebException
	 * @author sjiying
	 * @CreateDate 2019年5月15日
	 */
	@RequestMapping(value = "view.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public CarouselDto view(CarouselDto param, HttpServletRequest request) throws TsfaWebException {

		AssertUtils.notNullAndEmpty(param);
		AssertUtils.notNullAndEmpty(param.getCode(), "广告code不能为空");
//		AssertUtils.notNullAndEmpty(param.getUpdateId(), "操作人ID不能为空");

		param.setHasTrack(true);
		param.setRemark(getRemoteHost(request));

		return carouselService.findCarousel(param);
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
	public GeneralResponse statistics(String token, CarouselDto param) throws TsfaWebException {

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
		List<CarouselViewDto> viewList = carouselViewService.findGroupTotalByExample(param);

		List<CarouselShowDto> showList = carouselShowService.findGroupTotalByExample(param);

		Map<String, Object> rs = Maps.newHashMap();

		rs.put("viewList", viewList);
		rs.put("showList", showList);

		return GeneralResponse.generateSuccessResponse(rs);
	}

}
