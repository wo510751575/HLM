package com.lj.business.member.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.IPersonMemberExtDao;
import com.lj.business.member.domain.PersonMemberExt;
import com.lj.business.member.dto.MecMemberNoReturn;
import com.lj.business.member.dto.PersonMemberExtDto;
import com.lj.business.member.service.IPersonMemberExtService;

/**
 * 
 * 
 * 类说明：客户-微信openid关联表
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年6月5日
 */
@Service
public class PersonMemberExtServiceImpl implements IPersonMemberExtService {
	
	private static Logger logger = LoggerFactory.getLogger(PersonMemberExtServiceImpl.class);
	
	@Resource
	private IPersonMemberExtDao personMemberExtDao;

	@Override
	public int findCountByMemberNo(String memberNo) {
		return personMemberExtDao.findCountByMemberNo(memberNo);
	}

	@Override
	public void addPersonMemberExt(PersonMemberExtDto dto) {
		PersonMemberExt personMemberExt = new PersonMemberExt();
		personMemberExt.setCode(GUID.getPreUUID());
		personMemberExt.setCreateTime(new Date());
		personMemberExt.setMemberNo(dto.getMemberNo());
		personMemberExt.setOpenId(dto.getOpenId());
		personMemberExt.setRealName(dto.getRealName());
		personMemberExt.setRemark(dto.getRemark());
		personMemberExt.setRemark1(dto.getRemark1());
		personMemberExt.setRemark2(dto.getRemark2());
		personMemberExt.setRemark3(dto.getRemark3());
		personMemberExt.setRemark4(dto.getRemark4());
		personMemberExtDao.insertSelective(personMemberExt);
	}

	@Override
	public MecMemberNoReturn findPmbByOpenId(String openId) {
		MecMemberNoReturn mecMemberNoReturn = personMemberExtDao.findPmbByOpenId(openId);
		return mecMemberNoReturn;
	}

	/**
	 * 
	 *
	 * 方法说明：客户与openid绑定:
	 * 1、同一客户可绑定多个openid
	 * 2、一个openid最多只能绑定一个客户
	 *
	 * @param memberNo
	 * @param openId
	 *
	 * @author 曾垂瑜 CreateDate: 2018年6月5日
	 *
	 */
	@Override
	public void bindMemberNo(String memberNo, String openId) {
		logger.debug("bindMemberNo(String memberNo={}, String openId={}) - start", memberNo, openId);
		
		// 查询openid是否已绑定客户
		MecMemberNoReturn memberByOpenId = personMemberExtDao.findPmbByOpenId(openId);
		if(memberByOpenId == null) {	// 没绑定过，则新增绑定记录
			this.addPersonMemberExt(memberNo, openId);
		} else if(!memberByOpenId.getMemberNo().equals(memberNo)) {	// 已绑定客户与当前待绑定客户不同，则先与原客户解绑：即更新绑定关系
			PersonMemberExt update = new PersonMemberExt();
			update.setCode(memberByOpenId.getCode());
			update.setMemberNo(memberNo);
			update.setCreateTime(new Date());
			personMemberExtDao.updateByPrimaryKeySelective(update);
			logger.debug("已更新绑定关系: {}", memberByOpenId.getCode());
		}

		logger.debug("bindMemberNo(String memberNo, String openId) - end");
	}
	
	private void addPersonMemberExt(String memberNo, String openId) {
		PersonMemberExtDto dto = new PersonMemberExtDto();
		dto.setMemberNo(memberNo);
		dto.setOpenId(openId);
		this.addPersonMemberExt(dto);
		logger.debug("客户信息[{}]", dto);
	}

	@Override
	public void updatePersonMemberExt(PersonMemberExtDto dto) {
		AssertUtils.notAllNullAndEmpty(dto.getCode(), dto.getMemberNo(), "code和memberNo不能同时为空");
		PersonMemberExt personMemberExt = new PersonMemberExt();
		personMemberExt.setCode(dto.getCode());
		personMemberExt.setMemberNo(dto.getMemberNo());
		personMemberExt.setOpenId(dto.getOpenId());
		personMemberExt.setRealName(dto.getRealName());
		personMemberExt.setRemark(dto.getRemark());
		personMemberExt.setRemark1(dto.getRemark1());
		personMemberExt.setRemark2(dto.getRemark2());
		personMemberExt.setRemark3(dto.getRemark3());
		personMemberExt.setRemark4(dto.getRemark4());
		personMemberExtDao.updateByPrimaryKeySelective(personMemberExt);
	}

	@Override
	public PersonMemberExtDto findByMemberNo(String memberNo) {
		logger.debug("findByMemberNo(String memberNo)={}) - start", memberNo); 

		AssertUtils.notNullAndEmpty(memberNo, "客户编号不能为空");
		
		PersonMemberExtDto personMemberExtDto = null;
		try {
			PersonMemberExt personMemberExt = personMemberExtDao.findByMemberNo(memberNo);
			if(personMemberExt != null){
				personMemberExtDto = new PersonMemberExtDto();
				//find数据录入
				personMemberExtDto.setCode(personMemberExt.getCode());
				personMemberExtDto.setMemberNo(memberNo);
				personMemberExtDto.setOpenId(personMemberExt.getOpenId());
				personMemberExtDto.setRealName(personMemberExt.getRealName());
				personMemberExtDto.setRemark(personMemberExt.getRemark());
				personMemberExtDto.setRemark1(personMemberExt.getRemark1());
				personMemberExtDto.setRemark2(personMemberExt.getRemark2());
				personMemberExtDto.setRemark3(personMemberExt.getRemark3());
				personMemberExtDto.setRemark4(personMemberExt.getRemark4());
			}
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("根据客户编号查询客户扩展信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_BASE_FIND_ERROR,"根据客户编号查询客户扩展信息错误！",e);
		}
		
		logger.debug("findByMemberNo(String) - end - return value={}", personMemberExtDto); 
		return personMemberExtDto;
	}
	
}
