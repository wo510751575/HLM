package com.lj.oms.ad;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.dto.FindGuidMemberPageReturn;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.oms.common.BaseController;
import com.lj.oms.utils.UserUtils;
import com.ye.business.ad.dto.AdvertiseTypeDto;
import com.ye.business.ad.dto.CarouselDto;
import com.ye.business.ad.dto.FindAdvertiseTypePage;
import com.ye.business.ad.dto.FindCarouselPage;
import com.ye.business.ad.service.IAdvertiseTypeService;
import com.ye.business.ad.service.ICarouselService;
import com.ye.business.rw.dto.FindRwUserPage;
import com.ye.business.rw.dto.RwUserDto;
import com.ye.business.rw.service.IRwUserService;

/**
 * 
 * *类说明：轮播广告
 *
 * </p>
 * *详细描述：
 * 
 * @author sjiying
 * @CeateDate 2019年5月8日
 */
@Controller
@RequestMapping(value = "${adminPath}/ad/carousel")
public class CarouselController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(CarouselController.class);

	@Autowired
	private ICarouselService carouselService;
	@Autowired
	private IAdvertiseTypeService advertiseTypeService;
	@Autowired
	private IRwUserService rwUserService;
	@Autowired
	private IGuidMemberService guidMemberService;

	/**
	 * 
	 * *方法说明：轮播广告-分页
	 *
	 * @param findCarouselPage
	 * @param param
	 * @param pageNo
	 * @param pageSize
	 * @param model
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年5月5日
	 */
	@RequiresPermissions("ad:carousel:view")
	@RequestMapping(value = { "list", "" })
	public String list(FindCarouselPage findCarouselPage, CarouselDto param, Integer pageNo, Integer pageSize, Model model) {
		try {

			// 分类列表
			getAdvertiseTypeList(model);

			if (pageNo != null) {
				findCarouselPage.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				findCarouselPage.setLimit(pageSize);
			}

			findCarouselPage.setParam(param);

			Page<CarouselDto> pageDto = carouselService.findCarouselPage(findCarouselPage);

			List<CarouselDto> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());

			if (list.size() > 0) {

				List<String> memberNoList = list.stream().filter(temp -> StringUtils.isNoneBlank(temp.getMemberNoGuid())).map(CarouselDto::getMemberNoGuid)
						.collect(Collectors.toList());
				if (memberNoList != null && memberNoList.size() > 0) {
					// 从会员记录中获取相关用户信息
					RwUserDto userParam = new RwUserDto();
					FindRwUserPage findRwUserPage = new FindRwUserPage();
					findRwUserPage.setParam(userParam);
					Map<String, RwUserDto> findRwUserMap = new HashMap<>();
					List<RwUserDto> findRwUserPageRturn = rwUserService.findRwUsers(findRwUserPage);
					for (RwUserDto rwUser : findRwUserPageRturn) {
						findRwUserMap.put(rwUser.getMemberNoGuid(), rwUser);
					}
					//Map<String, RwUserDto> findRwUserMap = findRwUserPageRturn.stream().collect(Collectors.toMap(RwUserDto::getMemberNoGuid, rwUserDto -> rwUserDto));
					model.addAttribute("findRwUserMap", findRwUserMap);

					// 商户记录中获取相关用户信息
					FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
					findGuidMemberPage.setMemberNoList(memberNoList);
					List<FindGuidMemberPageReturn> findGuidMemberPageReturnList = guidMemberService.findGuidMembers(findGuidMemberPage);
					Map<String, FindGuidMemberPageReturn> findGuidMemberPageReturnMap = findGuidMemberPageReturnList.stream()
							.collect(Collectors.toMap(FindGuidMemberPageReturn::getMemberNo, valueMapper -> valueMapper));
					model.addAttribute("findGuidMemberPageReturnMap", findGuidMemberPageReturnMap);
				}
			}

			com.ape.common.persistence.Page<CarouselDto> page = new com.ape.common.persistence.Page<CarouselDto>(pageNo == null ? 1 : pageNo, pageDto.getLimit(),
					pageDto.getTotal(), list);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("findCarouselPage", findCarouselPage);

		} catch (Exception e) {
			logger.error("获取轮播广告信息错误！", e);
		}
		return "modules/ad/carouselList";
	}

	/**
	 * 
	 * *方法说明：查看轮播广告信息
	 *
	 * @param param
	 * @param model
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年5月5日
	 */
	@RequiresPermissions("ad:carousel:view")
	@RequestMapping(value = "form")
	public String form(CarouselDto param, Model model) {
		try {

			// 分类列表
			getAdvertiseTypeList(model);

			if (param != null && param.getCode() != null) {
				if (StringUtils.isNotBlank(param.getCode())) {
					CarouselDto data = carouselService.findCarousel(param);
					model.addAttribute("data", data);
				} else {
					// 回显添加失败时的信息
					model.addAttribute("data", param);
				}
			}
		} catch (Exception e) {
			logger.error("获取轮播广告信息错误！", e);
		}

		return "modules/ad/carouselForm";
	}

	/**
	 * 
	 * *方法说明：封装广告分类列表
	 *
	 * @param model
	 * @author sjiying
	 * @CreateDate 2019年5月14日
	 */
	private void getAdvertiseTypeList(Model model) {
		// 分类列表
		FindAdvertiseTypePage findAdvertiseTypePage = new FindAdvertiseTypePage();
		findAdvertiseTypePage.setSortBy("numOrder");
		List<AdvertiseTypeDto> findAdvertiseTypeList = advertiseTypeService.findAdvertiseTypes(findAdvertiseTypePage);
		model.addAttribute("findAdvertiseTypeList", findAdvertiseTypeList);
	}

	/**
	 * 
	 * *方法说明：
	 *
	 * @param param
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年5月5日
	 */
	@RequiresPermissions("ad:carousel:edit")
	@RequestMapping(value = "save")
	public String save(CarouselDto param, Model model, RedirectAttributes redirectAttributes) {
		try {
			param.setMerchantNo(UserUtils.getMerchantNo());

			Date now = new Date();
			String loginName = UserUtils.getUser().getLoginName();

			param.setCreateDate(now);
			param.setUpdateDate(now);
			param.setReleaseDate(now);
			param.setCreateId(loginName);
			param.setUpdateId(loginName);

			carouselService.addCarousel(param);
			addMessage(redirectAttributes, "保存轮播广告成功");
		} catch (Exception e) {
			logger.error("保存轮播广告信息错误！", e);
		}
		return "redirect:" + adminPath + "/ad/carousel/";
	}

	/**
	 * 
	 * *方法说明：编辑
	 *
	 * @param param
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年5月5日
	 */
	@RequiresPermissions("ad:carousel:edit")
	@RequestMapping(value = "edit")
	public String edit(CarouselDto param, Model model, RedirectAttributes redirectAttributes) {
		try {

			AssertUtils.notNullAndEmpty(param);
			AssertUtils.notNullAndEmpty(param.getCode(), "编号为空");

			Date now = new Date();
			String loginName = UserUtils.getUser().getLoginName();

			param.setUpdateDate(now);
			param.setUpdateId(loginName);

			carouselService.updateCarousel(param);

			addMessage(redirectAttributes, "编辑轮播广告成功");
		} catch (Exception e) {
			logger.error("编辑轮播广告信息错误！", e);
		}
		return "redirect:" + adminPath + "/ad/carousel/";
	}

	/**
	 * 
	 * *方法说明：删除
	 *
	 * @param code
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年5月5日
	 */
	@RequiresPermissions("ad:carousel:edit")
	@RequestMapping(value = "delete")
	public String delete(String code, RedirectAttributes redirectAttributes) {
		try {
			AssertUtils.notNullAndEmpty(code);
			carouselService.removeByPrimaryKey(code);

			addMessage(redirectAttributes, "删除轮播广告成功");

		} catch (Exception e) {
			logger.error("删除轮播广告信息错误！", e);
		}
		return "redirect:" + adminPath + "/ad/carousel/";
	}

}
