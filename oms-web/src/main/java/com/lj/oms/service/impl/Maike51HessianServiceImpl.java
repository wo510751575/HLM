package com.lj.oms.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ape.common.utils.StringUtils;
import com.google.common.collect.Lists;
import com.lj.base.core.util.AssertUtils;
import com.lj.business.member.dto.AddMerchant;
import com.lj.business.member.emus.ProductType;
import com.lj.business.member.emus.Status;
import com.lj.business.member.service.IMerchantService;
import com.lj.oms.dao.sys.RoleDao;
import com.lj.oms.emus.UseStatus;
import com.lj.oms.entity.dto.Maike51Dto;
import com.lj.oms.entity.dto.Maike51DtoReturn;
import com.lj.oms.entity.sys.Area;
import com.lj.oms.entity.sys.Office;
import com.lj.oms.entity.sys.Role;
import com.lj.oms.entity.sys.User;
import com.lj.oms.service.Maike51HessianService;
import com.lj.oms.service.sys.OfficeService;
import com.lj.oms.service.sys.SystemService;
import com.lj.oms.utils.DictUtils;
import com.lj.oms.utils.UserUtils;
import com.lj.oms.utils.Validator;

/**
 * 
 * 
 * 类说明：针对卖客星球对外服务实现类
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 * 
 *         CreateDate: 2018年1月29日
 */
@Service
public class Maike51HessianServiceImpl implements Maike51HessianService {
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
	public Maike51DtoReturn createUserToKen(Maike51Dto maike51Dto) {
		logger.info("createUserToKen------Maike51Dto maike51Dto={}",
				maike51Dto.toString());
		try {
			AssertUtils.notNullAndEmpty(maike51Dto.getPhone(), "手机号不能为空");
			AssertUtils.notNullAndEmpty(maike51Dto.getUserNo(), "用户编号不能为空");
			AssertUtils.notNullAndEmpty(maike51Dto.getShopId(), "终端ID不能为空");
			AssertUtils.notNullAndEmpty(maike51Dto.getNickname(), "用户昵称不能为空");
			if (!Validator.isMobile(maike51Dto.getPhone())) {
				logger.error("参数错误！！");
				return Maike51DtoReturn.failResp(Maike51DtoReturn.MOBILE_ERROR_CODE, "手机号格式错误!!");
			}

			/**
			 * 用户是否已存在，返回原Token
			 */
			User user = UserUtils.getByLoginName(maike51Dto.getPhone());
			if (null != user) {
				String oldToken = userCacheService.getTokenByUserId(user.getId());
				logger.error("用户已存在，返回原Token={}",oldToken);
				return Maike51DtoReturn.successResp(oldToken);
			}
			/**
			 * 获取卖客星球固定角色ID
			 */
			String roleId = DictUtils.getDictValue(Maike51Dto.ROLE_KEY,
					DictUtils.OS_ROLE, "");
			if (StringUtils.isBlank(roleId)) {
				logger.error("获取角色失败！！ROLE_KEY={}", Maike51Dto.ROLE_KEY);
				return Maike51DtoReturn.failResp(Maike51DtoReturn.ROLE_ERROR_CODE, "获取角色失败!!");
			}
			Role role = systemService.getRole(roleId);
			
			
			/**
			 * 创建机构
			 */
			Office office = createOffice(maike51Dto);
			/**
			 * 创建用户
			 */
			user = createUser(maike51Dto, office, role);

			/**
			 * 同步商户
			 */
			createMerchant(office);
			/**
			 * 生成token
			 */
			String token = userCacheService.addUserToken(user);
			
			return Maike51DtoReturn.successResp(token);
		} catch (Exception e) {
			logger.error("创建商户错误!!e={}",e.getMessage());
			return Maike51DtoReturn.failResp("", e.getMessage());
		}
		
	}

	private User createUser(Maike51Dto maike51Dto, Office office, Role role) {
		User user= new User(role);
		user.setCompany(office);
		user.setOffice(office);
		user.setUserType(User.USERTYPE_2); // 商户管理员
		user.setNo(maike51Dto.getUserNo());
		user.setName(maike51Dto.getNickname());
		user.setLoginName(maike51Dto.getPhone());
		user.setPhone(maike51Dto.getPhone());
		String pwd = maike51Dto.getPhone().substring(
				maike51Dto.getPhone().length() - 6);
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
	 * 
	 *
	 * 方法说明：创建商户
	 *
	 * @param office
	 *
	 * @author 段志鹏 CreateDate: 2018年1月29日
	 *
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
	 * 
	 *
	 * 方法说明：创建机构
	 *
	 * @param maike51Dto
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2018年1月29日
	 *
	 */
	private Office createOffice(Maike51Dto maike51Dto) {
		Office office = new Office();
		office.setParent(new Office(Office.ROOT_ID));
		office.setArea(new Area(Area.ROOT_ID));
		office.setName(Maike51Dto.OFFICE_NAME_PRE + maike51Dto.getShopId());
		office.setCode(maike51Dto.getShopId());
		office.setType(Office.TYPE_COMPANY);
		office.setGrade(Office.GRADE_2);
		office.setUseable(UseStatus.YES.getValue());
		office.setCreateBy(UserUtils.get(User.USERTYPE_1));
		office.setUpdateBy(UserUtils.get(User.USERTYPE_1));
		officeService.save(office);
		return office;
	}
	
	
	
	

}
