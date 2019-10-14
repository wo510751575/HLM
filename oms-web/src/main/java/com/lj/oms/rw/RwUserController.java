package com.lj.oms.rw;

import java.util.Date;
import java.util.List;

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
import com.lj.base.core.encryption.MD5;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.oms.common.BaseController;
import com.lj.oms.service.sys.SystemService;
import com.lj.oms.utils.UserUtils;
import com.ye.business.rw.dto.FindRwUserPage;
import com.ye.business.rw.dto.RwUserDto;
import com.ye.business.rw.service.IRwUserService;

/**
 * 
 * *类说明：文章用户
 *
 * </p>
 * *详细描述：
 * 
 * @author sjiying
 * @CeateDate 2019年5月5日
 */
@Controller
@RequestMapping(value = "${adminPath}/rw/rwUser")
public class RwUserController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(RwUserController.class);

	@Autowired
	private IRwUserService rwUserService;

	/**
	 * 
	 * *方法说明：热文分享-分页
	 *
	 * @param findRwUserPage
	 * @param param
	 * @param pageNo
	 * @param pageSize
	 * @param model
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年5月5日
	 */
	@RequiresPermissions("rw:rwUser:view")
	@RequestMapping(value = { "list", "" })
	public String list(FindRwUserPage findRwUserPage, RwUserDto param, Integer pageNo, Integer pageSize, Model model) {
		try {

			if (pageNo != null) {
				findRwUserPage.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				findRwUserPage.setLimit(pageSize);
			}

			findRwUserPage.setParam(param);

			Page<RwUserDto> pageDto = rwUserService.findRwUserPage(findRwUserPage);

			List<RwUserDto> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());

			com.ape.common.persistence.Page<RwUserDto> page = new com.ape.common.persistence.Page<RwUserDto>(pageNo == null ? 1 : pageNo, pageDto.getLimit(), pageDto.getTotal(),
					list);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("findRwUserPage", findRwUserPage);

		} catch (Exception e) {
			logger.error("获取用户信息错误！", e);
		}
		return "modules/rw/rwUserList";
	}
	
	/**
	 * 
	 * *方法说明：热文分享-分页
	 *
	 * @param findRwUserPage
	 * @param param
	 * @param pageNo
	 * @param pageSize
	 * @param model
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年5月5日
	 */
	@RequestMapping(value = { "selectList", "" })
	public String selectList(FindRwUserPage findRwUserPage, RwUserDto param, Integer pageNo, Integer pageSize, Model model) {
		try {

			if (pageNo != null) {
				findRwUserPage.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				findRwUserPage.setLimit(pageSize);
			}

			findRwUserPage.setParam(param);

			Page<RwUserDto> pageDto = rwUserService.findRwUserPage(findRwUserPage);

			List<RwUserDto> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());

			com.ape.common.persistence.Page<RwUserDto> page = new com.ape.common.persistence.Page<RwUserDto>(pageNo == null ? 1 : pageNo, pageDto.getLimit(), pageDto.getTotal(),
					list);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("findRwUserPage", findRwUserPage);

		} catch (Exception e) {
			logger.error("获取用户信息错误！", e);
		}
		return "modules/rw/rwUserListToSelect";
	}

	/**
	 * 
	 * *方法说明：查看用户信息
	 *
	 * @param param
	 * @param model
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年5月5日
	 */
	@RequiresPermissions("rw:rwUser:view")
	@RequestMapping(value = "form")
	public String form(RwUserDto param, Model model) {
		try {

			if (param != null && param.getCode() != null) {
				if (StringUtils.isNotBlank(param.getCode())) {
					RwUserDto data = rwUserService.findRwUser(param);
					model.addAttribute("data", data);
				} else {
					// 回显添加失败时的信息
					model.addAttribute("data", param);
				}
			}
			
			model.addAttribute("param", param);
			
		} catch (Exception e) {
			logger.error("获取用户信息错误！", e);
		}

		return "modules/rw/rwUserForm";
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
	@RequiresPermissions("rw:rwUser:edit")
	@RequestMapping(value = "save")
	public String save(RwUserDto param, Model model, RedirectAttributes redirectAttributes) {
		try {
			param.setMerchantNo(UserUtils.getMerchantNo());

			Date now = new Date();
			String userCode = UserUtils.getUser().getId();
			
//			param.setPwd(SystemService.entryptPassword(param.getPwd()));
			String pwd = MD5.encryptByMD5(param.getPwd());
			param.setPwd(MD5.encryptByMD5(pwd));
			// 生成唯一memberNo
			param.setMemberNoGuid(GUID.getPreUUID());
//			param.setMemberNameGuid(param.getNickName());
			
			param.setCreateDate(now);
			param.setCreateId(userCode);
			param.setUpdateDate(now);
			param.setUpdateId(userCode);

			rwUserService.addRwUser(param);
			addMessage(redirectAttributes, "保存用户成功");
		} catch (Exception e) {
			logger.error("保存用户信息错误！", e);
		}
		return "redirect:" + adminPath + "/rw/rwUser/list";
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
	@RequiresPermissions("rw:rwUser:edit")
	@RequestMapping(value = "edit")
	public String edit(RwUserDto param, Model model, RedirectAttributes redirectAttributes) {
		try {

			AssertUtils.notNullAndEmpty(param);
			AssertUtils.notNullAndEmpty(param.getCode(), "编号为空");

			param.setCreateId(UserUtils.getUser().getLoginName());
			param.setCreateDate(new Date()); // 重置创建日期
			
			if (StringUtils.isNoneBlank(param.getPwd())) {
				String pwd = MD5.encryptByMD5(param.getPwd());
				param.setPwd(MD5.encryptByMD5(pwd));
				//param.setPwd(SystemService.entryptPassword(param.getPwd()));
			}

			rwUserService.updateRwUser(param);

			addMessage(redirectAttributes, "编辑用户成功");
		} catch (Exception e) {
			logger.error("编辑用户信息错误！", e);
		}
		return "redirect:" + adminPath + "/rw/rwUser/list";
	}

//	/**
//	 * 
//	 * *方法说明：删除
//	 *
//	 * @param code
//	 * @return
//	 * @author sjiying
//	 * @CreateDate 2019年5月5日
//	 */
//	@RequiresPermissions("rw:rwUser:edit")
//	@RequestMapping(value = "delete")
//	public String delete(String code, RedirectAttributes redirectAttributes) {
//		try {
//			AssertUtils.notNullAndEmpty(code);
//			rwUserService.removeByPrimaryKey(code);
//
//			addMessage(redirectAttributes, "删除用户成功");
//
//		} catch (Exception e) {
//			logger.error("删除用户信息错误！", e);
//		}
//		return "redirect:" + adminPath + "/rw/rwUser/";
//	}
}
