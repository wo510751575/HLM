package com.lj.oms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ape.common.config.Global;
import com.ape.common.utils.StringUtils;
import com.google.common.collect.Lists;
import com.lj.base.core.util.AssertUtils;
import com.lj.business.member.dto.AddMerchant;
import com.lj.business.member.dto.FindMerchantPage;
import com.lj.business.member.dto.FindMerchantPageReturn;
import com.lj.business.member.dto.UpdateMerchant;
import com.lj.business.member.dto.UpdateMerchantReturn;
import com.lj.business.member.emus.ProductType;
import com.lj.business.member.emus.Status;
import com.lj.business.member.service.IMerchantService;
import com.lj.oms.dao.sys.RoleDao;
import com.lj.oms.emus.UseStatus;
import com.lj.oms.entity.dto.kuaitui.KuaiTuiDto;
import com.lj.oms.entity.dto.kuaitui.KuaiTuiDtoReturn;
import com.lj.oms.entity.sys.Area;
import com.lj.oms.entity.sys.Office;
import com.lj.oms.entity.sys.Role;
import com.lj.oms.entity.sys.User;
import com.lj.oms.service.KuaiTuiHessianService;
import com.lj.oms.service.sys.OfficeService;
import com.lj.oms.service.sys.SystemService;
import com.lj.oms.utils.DictUtils;
import com.lj.oms.utils.UserUtils;
import com.lj.oms.utils.Validator;

/**
 * 
 * 
 * 类说明：快推对外服务实现类
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 * 
 *         CreateDate: 2018年11月5日
 */
@Service
public class KuaiTuiHessianServiceImpl implements KuaiTuiHessianService {
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private OfficeService officeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private IMerchantService merchantService;
	@Autowired
	private UserCacheService userCacheService;
	@Autowired
	private RoleDao roleDao;
	
	@Transactional(readOnly = false)
	@Override
	public KuaiTuiDtoReturn createUserToKen(KuaiTuiDto kuaiTuiDto) {
		logger.info("createUserToKen------KuaiTuiDto kuaiTuiDto={}",kuaiTuiDto);
		try {
			AssertUtils.notNullAndEmpty(kuaiTuiDto.getMobile(), "手机号不能为空");
			AssertUtils.notNullAndEmpty(kuaiTuiDto.getMerchantNo(), "商户编号不能为空");
			AssertUtils.notNullAndEmpty(kuaiTuiDto.getMerchantName(), "商户昵称不能为空");
			if (!Validator.isMobile(kuaiTuiDto.getMobile())) {
				logger.error("参数错误！！");
				return KuaiTuiDtoReturn.failResp(KuaiTuiDtoReturn.MOBILE_ERROR_CODE, "手机号格式错误!!");
			}

			/**
			 * 用户是否已存在，返回原Token
			 */
			User user = UserUtils.getByLoginName(kuaiTuiDto.getMobile());
			if (null != user) {
				String oldToken = userCacheService.getTokenByUserId(user.getId());
				logger.error("用户已存在，返回原Token={}",oldToken);
				if(StringUtils.isEmpty(oldToken)) {
					String newToken =userCacheService.addUserToken(user);
					logger.error("原Token为空，生成新token",newToken);
					oldToken = newToken;
				}
				Map<String,Object> returnMap = new HashMap<>();
				returnMap.put("token", oldToken);
				returnMap.put("id", user.getOffice().getId());
				return KuaiTuiDtoReturn.successResp(returnMap);
			}
			/**
			 * 获取固定角色ID
			 */
			String roleId = DictUtils.getDictValue(KuaiTuiDto.ROLE_KEY,DictUtils.OS_ROLE, "");
			if (StringUtils.isBlank(roleId)) {
				logger.error("获取角色失败！！ROLE_KEY={}", KuaiTuiDto.ROLE_KEY);
				return KuaiTuiDtoReturn.failResp(KuaiTuiDtoReturn.ROLE_ERROR_CODE, "获取角色失败!!");
			}
			Role role = systemService.getRole(roleId);
			
			
			/**
			 * 创建机构
			 */
			Office office = createOffice(kuaiTuiDto);
			/**
			 * 创建用户
			 */
			user = createUser(kuaiTuiDto, office, role);

			/**
			 * 同步商户
			 */
			createMerchant(office);
			/**
			 * 生成token
			 */
			String token = userCacheService.addUserToken(user);
			
			Map<String,Object> returnMap = new HashMap<>();
			returnMap.put("token", token);
			returnMap.put("id", office.getId());
			return KuaiTuiDtoReturn.successResp(returnMap);
		} catch (Exception e) {
			logger.error("创建商户错误!!e={}",e);
			return KuaiTuiDtoReturn.failResp("", e.getMessage());
		}
		
	}

	private User createUser(KuaiTuiDto kuaiTuiDto, Office office, Role role) {
		User user= new User(role);
		user.setCompany(office);
		user.setOffice(office);
		user.setUserType(User.USERTYPE_2); // 商户管理员
		user.setNo(kuaiTuiDto.getMerchantNo());
		user.setName(kuaiTuiDto.getMerchantName());
		user.setLoginName(kuaiTuiDto.getMobile());
		user.setPhone(kuaiTuiDto.getMobile());
		String pwd = kuaiTuiDto.getMobile().substring(
				kuaiTuiDto.getMobile().length() - 6);
		user.setPassword(SystemService.entryptPassword(pwd));
		user.setLoginFlag(UseStatus.YES.getValue());
		// 角色数据有效性验证，过滤不在授权内的角色
		List<Role> roleList = Lists.newArrayList();
		List<String> roleIdList = user.getRoleIdList();
		roleIdList.add(role.getId());
		for (Role r : roleDao.findAllList(new Role())) {
			if (roleIdList.contains(r.getId())) {
				roleList.add(r);
			}
		}
		user.setRoleList(roleList);
		user.setLayout(User._DARKBLUE);
		user.setCreateBy(UserUtils.get("1"));
		user.setUpdateBy(UserUtils.get("1"));
		systemService.saveUser(user);
		return user;
	}

	/**
	 * 方法说明：创建商户
	 * @param office
	 */
	private void createMerchant(Office office) {
		AddMerchant addMerchant = new AddMerchant();
		addMerchant.setMerchantNo(office.getId());
		addMerchant.setMerchantName(office.getName());
		addMerchant.setProductType(ProductType.INVITE.toString());
		addMerchant.setStatus(Status.NORMAL);
		merchantService.addMerchant(addMerchant);
	}

	/**
	 * 方法说明：创建机构
	 * @param kuaiTuiDto
	 * @return
	 */
	private Office createOffice(KuaiTuiDto kuaiTuiDto) {
		Office office = new Office();
		office.setParent(new Office(Office.ROOT_ID));
		office.setArea(new Area(Area.ROOT_ID));
		office.setName(KuaiTuiDto.OFFICE_NAME_PRE + kuaiTuiDto.getMerchantNo());
		office.setCode(kuaiTuiDto.getMerchantNo());
		office.setType(Office.TYPE_COMPANY);
		office.setGrade(Office.GRADE_2);
		office.setUseable(UseStatus.YES.getValue());
		office.setCreateBy(UserUtils.get(User.USERTYPE_1));
		office.setUpdateBy(UserUtils.get(User.USERTYPE_1));
		officeService.save(office);
		return office;
	}

	@Override
	public KuaiTuiDtoReturn getMerchant(FindMerchantPage findMerchantPage) {
		logger.info("getMerchant(FindMerchantPage findMerchantPage)={}",findMerchantPage);
		try {
			AssertUtils.notNullAndEmpty(findMerchantPage.getMerchantNo(), "商户编号不能为空");
			List<FindMerchantPageReturn> list= merchantService.findMerchants(findMerchantPage);
			return KuaiTuiDtoReturn.successResp(list.get(0));
		} catch (Exception e) {
			logger.error("获取商户信息错误!!e={}",e.getMessage());
			return KuaiTuiDtoReturn.failResp("", e.getMessage());
		}
	}

	@Override
	public KuaiTuiDtoReturn editMerchantStatus(UpdateMerchant updateMerchant) {
		logger.info("editMerchantStatus(UpdateMerchant updateMerchant)={}",updateMerchant);
		try {
			AssertUtils.notNullAndEmpty(updateMerchant.getMerchantNo(), "商户编号不能为空");
			AssertUtils.notNullAndEmpty(updateMerchant.getStatus(), "商户状态不能为空");
			
			UpdateMerchant update = new UpdateMerchant();
			update.setMerchantNo(updateMerchant.getMerchantNo());
			update.setStatus(updateMerchant.getStatus());
			UpdateMerchantReturn updateMerchantReturn = merchantService.updateByMerchantNo(update);
			
			/**
			 * 并且禁用商户下所有管理账户
			 */
			List<User> list= systemService.findUserByOfficeIdNoCache(updateMerchant.getCode());
			String loginFlag = updateMerchant.getStatus().equals(Status.CANCEL)?Global.NO:Global.YES;
			for (User user : list) {
				user.setLoginFlag(loginFlag);
				systemService.updateUserInfo(user);
			}
			return KuaiTuiDtoReturn.successResp(updateMerchantReturn);
		} catch (Exception e) {
			logger.error("修改商户状态错误!!e={}",e.getMessage());
			return KuaiTuiDtoReturn.failResp("", e.getMessage());
		}
	}
	
}
