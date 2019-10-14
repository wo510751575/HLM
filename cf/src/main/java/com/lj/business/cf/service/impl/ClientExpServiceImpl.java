package com.lj.business.cf.service.impl;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.cf.constant.ErrorCode;
import com.lj.business.cf.dao.IClientExpDao;
import com.lj.business.cf.domain.ClientExp;
import com.lj.business.cf.dto.clientExp.ClientExpContent;
import com.lj.business.cf.dto.clientExp.ClientExpDto;
import com.lj.business.cf.dto.clientExp.FindClientExpPage;
import com.lj.business.cf.dto.clientExp.FindClientExpReturn;
import com.lj.business.cf.dto.clientFollow.AddClientFollow;
import com.lj.business.cf.dto.clientFollow.AddClientFollowReturn;
import com.lj.business.cf.dto.comTask.GenerateNextDate;
import com.lj.business.cf.dto.comTaskChoose.ComTaskChooseReturnDto;
import com.lj.business.cf.dto.comTaskChoose.FindComTaskChoose;
import com.lj.business.cf.dto.comTaskList.FindComTaskList;
import com.lj.business.cf.dto.comTaskList.FindComTaskListReturn;
import com.lj.business.cf.emus.ComTaskType;
import com.lj.business.cf.emus.ExpResult;
import com.lj.business.cf.service.IClientExpService;
import com.lj.business.cf.service.IClientFollowService;
import com.lj.business.cf.service.IClientInviteService;
import com.lj.business.cf.service.IComTaskChooseService;
import com.lj.business.cf.service.IComTaskListService;
import com.lj.business.cf.service.IComTaskService;
import com.lj.business.common.CommonConstant;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.FindPersonMemberBase;
import com.lj.business.member.dto.FindPersonMemberBaseReturn;
import com.lj.business.member.emus.MemberType;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IPersonMemberBaseService;
import com.lj.business.member.service.IPmTypeService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;



/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 * 
 * 
 *         CreateDate: 2017-06-14
 */
@Service
public class ClientExpServiceImpl implements IClientExpService {

	private static final Logger logger = LoggerFactory.getLogger(ClientExpServiceImpl.class);

	@Resource
	private IClientExpDao clientExpDao;
	
	@Resource
	private IClientFollowService clientFollowService;
	
	@Resource
	private IComTaskListService comTaskListService;
	
	@Resource
	private IComTaskService comTaskService;
	@Resource
	public IComTaskChooseService comTaskChooseService;
    @Resource
    private IClientInviteService clientInviteService;
    @Resource
    private IPersonMemberBaseService personMemberBaseService;
    @Resource
    private IPmTypeService pmTypeService;
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	@Resource
	private IGuidMemberService guidMemberService;

	/* (non-Javadoc)
	 * @see com.lj.business.cf.service.IClientExpService#addClientExp(com.lj.business.cf.dto.clientExp.ClientExpDto)
	 */
	@Override
	public ClientExpDto addClientExp(ClientExpDto clientExpDto) throws TsfaServiceException {
		
		logger.debug("addClientExp(ClientExpDto addClientExp={}) - start", clientExpDto);

		AssertUtils.notNull(clientExpDto);
		try {
			ClientExp clientExp = new ClientExp();
			clientExp.setCode(GUID.generateCode());
			clientExp.setExpFb(clientExpDto.getExpFb());
//			clientExp.setExpTime(clientExpDto.getExpTime());
			if(StringUtils.isNotEmpty(clientExpDto.getExpTime())){
				clientExp.setExpTime(DateUtils.parseDate(clientExpDto.getExpTime(),DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss));
			}
			clientExp.setImgs(clientExpDto.getImgs());
			clientExp.setResourcesUrl(clientExpDto.getResourcesUrl());
			clientExp.setMemberName(clientExpDto.getMemberName());
			clientExp.setMemberNameGm(clientExpDto.getMemberNameGm());
			clientExp.setMemberNo(clientExpDto.getMemberNo());
			clientExp.setMemberNoGm(clientExpDto.getMemberNoGm());
			clientExp.setMerchantNo(clientExpDto.getMerchantNo());
			clientExp.setShopName(clientExpDto.getShopName());
			clientExp.setShopNo(clientExpDto.getShopNo());
			clientExp.setExpResult(clientExpDto.getExpResult());
			clientExp.setFailReason(clientExpDto.getFailReason());
			clientExp.setCfNo(clientExpDto.getCfNo());
			clientExp.setCfCode(clientExpDto.getCfCode());
			clientExp.setRemark1(clientExpDto.getRemark1());
			clientExp.setRemark2(clientExpDto.getRemark2());
			clientExp.setRemark3(clientExpDto.getRemark3());
			clientExp.setRemark4(clientExpDto.getRemark4());
			clientExpDao.addClientExp(clientExp);
			logger.debug("addClientFollow(addClientExp) - end - return value={}", clientExpDto);
			return clientExpDto;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增到店体验表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.CLIENT_FOLLOW_ADD_ERROR, "新增到店体验表信息错误！", e);
		}
	}

	/* (non-Javadoc)
	 * @see com.lj.business.cf.service.IClientExpService#findClientExp(com.lj.business.cf.dto.clientExp.ClientExpDto)
	 */
	@Override
	public ClientExpDto findClientExp(ClientExpDto clientExpDto) throws TsfaServiceException {
		logger.debug("findClientExp(ClientExpDto clientExpDto={}) - start", clientExpDto); //$NON-NLS-1$

		AssertUtils.notNull(clientExpDto);
		AssertUtils.notAllNull(clientExpDto.getCode(), "Code不能为空");
		try {
			ClientExp clientExp = clientExpDao.selectByCode(clientExpDto.getCode());
			if (clientExp == null) {
				throw new TsfaServiceException(ErrorCode.CLIENT_FOLLOW_NOT_EXIST_ERROR, "到店体验表信息不存在");
			}
			ClientExpDto dto = new ClientExpDto();
			// find数据录入
			dto.setCode(clientExp.getCode());
			dto.setExpFb(clientExp.getExpFb());
//			dto.setExpTime(clientExp.getExpTime());
			if(StringUtils.isNotEmpty(clientExpDto.getExpTime())){
				dto.setExpTime(DateUtils.formatDate(clientExp.getExpTime(),DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss));
			}
			dto.setImgs(clientExp.getImgs());
			dto.setResourcesUrl(clientExp.getResourcesUrl());
			dto.setMemberName(clientExp.getMemberName());
			dto.setMemberNameGm(clientExp.getMemberNameGm());
			dto.setMemberNo(clientExp.getMemberNo());
			dto.setMemberNoGm(clientExp.getMemberNoGm());
			dto.setMerchantNo(clientExp.getMerchantNo());
			dto.setShopName(clientExp.getShopName());
			dto.setShopNo(clientExp.getShopNo());
			dto.setExpResult(clientExp.getExpResult());
			dto.setFailReason(clientExp.getFailReason());
			dto.setCfNo(clientExp.getCfNo());
			dto.setCfCode(clientExp.getCfCode());
			dto.setRemark1(clientExp.getRemark1());
			dto.setRemark2(clientExp.getRemark2());
			dto.setRemark3(clientExp.getRemark3());
			dto.setRemark4(clientExp.getRemark4());
			logger.debug("findClientExp(ClientExpDto) - end - return value={}", dto); //$NON-NLS-1$
			return dto;
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找到店体验表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.CLIENT_FOLLOW_FIND_ERROR, "查找到店体验表信息错误！", e);
		}
	}

	/* (non-Javadoc)
	 * @see com.lj.business.cf.service.IClientExpService#findClientExpPage(com.lj.business.cf.dto.clientExp.FindClientExpPage)
	 */
	@Override
	public Page<ClientExpDto> findClientExpPage(FindClientExpPage findClientExpPage) throws TsfaServiceException {
		logger.debug("findClientFollowPage(FindClientExpPage findClientExpPage={}) - start", //$NON-NLS-1$
				findClientExpPage);
		AssertUtils.notNull(findClientExpPage);
		List<ClientExpDto> returnList;
		int count = 0;
		try {
			returnList = clientExpDao.findClientExpPage(findClientExpPage);
			count = clientExpDao.findClientExpPageCount(findClientExpPage);
		} catch (Exception e) {
			logger.error("到店体验表信息分页查询错误", e);
			throw new TsfaServiceException(ErrorCode.CLIENT_FOLLOW_FIND_PAGE_ERROR, "到店体验表信息分页查询错误.！", e);
		}
		Page<ClientExpDto> returnPage = new Page<ClientExpDto>(returnList, count, findClientExpPage);
        
		logger.debug("findClientFollowPage(FindClientFollowPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return returnPage;
	}

	/* (non-Javadoc)
	 * @see com.lj.business.cf.service.IClientExpService#clientExpHistory(com.lj.business.cf.dto.clientExp.ClientExpDto)
	 */
	@Override
	public List<ClientExpDto> clientExpHistory(ClientExpDto clientExpDto) throws TsfaServiceException {
		logger.debug("clientExpHistory(FindClientFollow clientExpHistory={}) - start", clientExpDto); //$NON-NLS-1$

		List<ClientExpDto> returnList;
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("memberNo", clientExpDto.getMemberNo());
			map.put("memberGMCode", clientExpDto.getMemberNoGm());
			returnList = clientExpDao.clientExpHistory(map);
		} catch (Exception e) {
			logger.error("到店体验表信息分页查询错误", e);
			throw new TsfaServiceException(ErrorCode.CLIENT_FOLLOW_FIND_PAGE_ERROR, "到店体验表信息分页查询错误.！", e);
		}

		logger.debug("clientExpHistory(clientExpHistory) - end - return value={}", returnList); //$NON-NLS-1$
		return returnList;
	}

	@Override
	public List<ClientExpDto> findClientExps(FindClientExpPage findClientExpPage)
			throws TsfaServiceException {
		logger.debug("findClientExps(FindClientExpPage findClientExpPage={}) - start", //$NON-NLS-1$
				findClientExpPage);
		AssertUtils.notNull(findClientExpPage);
		List<ClientExpDto> returnList;
		try {
			returnList = clientExpDao.findClientExps(findClientExpPage);
		} catch (Exception e) {
			logger.error("到店体验表信息查询错误", e);
			throw new TsfaServiceException(ErrorCode.CLIENT_FOLLOW_FIND_ERROR, "到店体验表信息查询错误.！", e);
		}
		logger.debug("findClientExps(FindClientFollowPage) - end - return value={}", returnList); //$NON-NLS-1$
		return returnList;
	}

	@Override
	public int findClientExpPageCount(FindClientExpPage findClientExpPage) {
		logger.debug("findClientExpPageCount(FindClientExpPage findClientExpPage={}) - start",findClientExpPage);
		AssertUtils.notNull(findClientExpPage);
		int count = 0;
		try {
			count = clientExpDao.findClientExpPageCount(findClientExpPage);
		} catch (Exception e) {
			logger.error("到店体验表信息查询错误", e);
			throw new TsfaServiceException(ErrorCode.CLIENT_FOLLOW_FIND_ERROR, "到店体验表信息查询错误.！", e);
		}
		logger.debug("findClientExpPageCount(FindClientFollowPage) - end - return value={}", count); //$NON-NLS-1$
		return count;
	}
	
	/* (non-Javadoc)
	 * @see com.lj.business.cf.service.IClientExpService#clientExpCount(com.lj.business.cf.dto.clientExp.ClientExpDto)
	 */
	@Override
	public int clientExpCount(ClientExpDto clientExpDto) throws TsfaServiceException {
		int count = 0;
		try {
			count = clientExpDao.clientExpCount(clientExpDto);
		} catch (Exception e) {
			logger.error("到店体验表信息查询错误", e);
			throw new TsfaServiceException(ErrorCode.CLIENT_FOLLOW_FIND_ERROR, "到店体验表信息查询错误.！", e);
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see com.lj.business.cf.service.IClientExpService#addClientExpRecord(com.lj.business.cf.dto.clientExp.ClientExpDto)
	 */
	@Override
	public ClientExpDto addClientExpRecord(ClientExpDto clientExpDto) throws TsfaServiceException {
		logger.debug("addClientExpRecord(ClientExpDto clientExpDto={}) - start", clientExpDto); //$NON-NLS-1$

		AssertUtils.notNull(clientExpDto);
		AssertUtils.notNullAndEmpty(clientExpDto.getMerchantNo(), "商户编号不能为空");
		AssertUtils.notNullAndEmpty(clientExpDto.getMemberNo(), "客户编号不能为空");
		AssertUtils.notNullAndEmpty(clientExpDto.getMemberNoGm(), "导购编号不能为空");
		AssertUtils.notNullAndEmpty(clientExpDto.getShopNo(), "分店编号不能为空");
		AssertUtils.notNullAndEmpty(clientExpDto.getExpResult(), "到店结果不能为空");
		AssertUtils.notNullAndEmpty(clientExpDto.getCfNo(), "跟进编号不能为空");
		try{
			ClientExpDto returnData = null;
			if("Y".equals(clientExpDto.getExpResult())){
				AssertUtils.notNullAndEmpty(clientExpDto.getExpTime(), "体验时间不能为空");
				//到店成功产生跟进记录、引导任务
				AddClientFollow addClientFollow = new AddClientFollow();
				addClientFollow.setCfNo(clientExpDto.getCfNo());
				addClientFollow.setMemberNo(clientExpDto.getMemberNo());
				addClientFollow.setMemberNoGm(clientExpDto.getMemberNoGm());
				addClientFollow.setMerchantNo(clientExpDto.getMerchantNo());
				if(StringUtils.isNotEmpty(clientExpDto.getExpTime())){
					addClientFollow.setFollowTime(DateUtils.parseDate(clientExpDto.getExpTime(),DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss));
				}
				
				//获取频率
				FindComTaskChoose findComTaskChoose = new FindComTaskChoose();
				findComTaskChoose.setMerchantNo(addClientFollow.getMerchantNo());
				findComTaskChoose.setComTaskType(ComTaskType.CLIENT_EXP.toString());
				ComTaskChooseReturnDto comTaskChooseReturnDto = comTaskChooseService.findComTaskChoose(findComTaskChoose );
				addClientFollow.setNextDate(DateUtils.addHours(new Date(), Integer.valueOf(comTaskChooseReturnDto.getFreValue())));
				
				
				//查询引导客户的code
				FindComTaskList findComTaskList = new FindComTaskList();
				findComTaskList.setComTaskType(ComTaskType.GUID_PM);
				findComTaskList.setMerchantNo(clientExpDto.getMerchantNo());
				FindComTaskListReturn findComTaskListReturn = comTaskListService.findComTaskList(findComTaskList);
				if(findComTaskListReturn != null){
					addClientFollow.setTaskCode(findComTaskListReturn.getCode());
				}
				addClientFollow.setComTaskType(ComTaskType.GUID_PM.toString());
				addClientFollow.setComTaskTypeCf(ComTaskType.CLIENT_EXP.toString());
				int count = clientExpCount(clientExpDto);
				addClientFollow.setFollowInfo("第" + (count + 1) + "次到店体验" );
				
				//彭阳加的三个参数  完善任务处理
				addClientFollow.setDis(clientExpDto.getDis());
				addClientFollow.setComTaskCode(clientExpDto.getComTaskCode());
				
				addClientFollow.setClientExpNum(count+1);
				
				addClientFollow.setRemarkCom(CommonConstant.REPLACE_REMARK_COM +"第" + (count + 1) + "次到店体验" );
				addClientFollow.setLastResultDate(new Date());
				if(StringUtils.isEmpty(addClientFollow.getComTaskCode()))//手工录入到店记录，没有 客户沟通任务Code 
					addClientFollow.setFinishAllComTask(true);//关闭所有任务
				AddClientFollowReturn addClientFollowReturn = clientFollowService.addCFOrder(addClientFollow, "0",true,true);
				//添加到店体验记录
				clientExpDto.setCfCode(addClientFollowReturn.getCode());
				clientExpDto.setShopName(addClientFollowReturn.getShopName());
				clientExpDto.setMemberName(addClientFollowReturn.getMemberName());
				clientExpDto.setMemberNameGm(addClientFollowReturn.getMemberNameGm());
				returnData = addClientExp(clientExpDto);
				
				//分类变动
				/*ChangePmType changePmType = new ChangePmType();
				changePmType.setMemberNo(clientExpDto.getMemberNo());
				changePmType.setMerchantNo(clientExpDto.getMerchantNo());
				changePmType.setMemberNoGm(clientExpDto.getMemberNoGm());
				changePmType.setPmTypeType(PmTypeType.INTENTION);
				pmTypeService.changePmType(changePmType);*/
				
			}else if("N".equals(clientExpDto.getExpResult())){
				 //到店失败
				AddClientFollow addClientFollow = new AddClientFollow();
				addClientFollow.setCfNo(clientExpDto.getCfNo());
				addClientFollow.setMemberNo(clientExpDto.getMemberNo());
				addClientFollow.setMemberNoGm(clientExpDto.getMemberNoGm());
				addClientFollow.setMerchantNo(clientExpDto.getMerchantNo());
				//彭阳加的三个参数  完善任务处理
				addClientFollow.setDis(clientExpDto.getDis());
				addClientFollow.setComTaskCode(clientExpDto.getComTaskCode());//原业务任务CODE
				
				//查询到店体验的code
				FindComTaskList findComTaskList = new FindComTaskList();
				findComTaskList.setComTaskType(ComTaskType.INVITE);
				findComTaskList.setMerchantNo(clientExpDto.getMerchantNo());
				FindComTaskListReturn findComTaskListReturn = comTaskListService.findComTaskList(findComTaskList);
				if(findComTaskListReturn != null){
					addClientFollow.setTaskCode(findComTaskListReturn.getCode());
				}
				addClientFollow.setComTaskType(ComTaskType.INVITE.toString());
				addClientFollow.setComTaskTypeCf(ComTaskType.CLIENT_EXP.toString());
				
				Date now = new Date();
				addClientFollow.setFollowTime(now);
				
				//客户未应邀，产生下周四的邀约任务
				GenerateNextDate generateNextDate = new GenerateNextDate();
				generateNextDate.setMemberNo(clientExpDto.getMemberNo());
				generateNextDate.setMemberNoGm(clientExpDto.getMemberNoGm());
				generateNextDate.setNextDate(now);
				generateNextDate.setNextWeek(true);
				Date reachTime = comTaskService.generateNextDate(generateNextDate);
				addClientFollow.setFollowTime(now);
				addClientFollow.setNextDate(reachTime);
				
				addClientFollow.setFollowInfo(clientExpDto.getFailReason());
				addClientFollow.setRemarkCom(CommonConstant.REPLACE_REMARK_COM +"客户应邀，未到店" );
				addClientFollow.setLastResultDate(new Date());
				AddClientFollowReturn addClientFollowReturn = clientFollowService.addCFOrder(addClientFollow, "0",true,true);
				//添加到店记录
				//添加到店体验记录
				clientExpDto.setCfCode(addClientFollowReturn.getCode());
				clientExpDto.setShopName(addClientFollowReturn.getShopName());
				clientExpDto.setMemberName(addClientFollowReturn.getMemberName());
				clientExpDto.setMemberNameGm(addClientFollowReturn.getMemberNameGm());
				
				if(StringUtils.isNotEmpty(clientExpDto.getCode())){
					returnData = updateClientExp(clientExpDto);
				}else{
					returnData = addClientExp(clientExpDto);
				}
				
				/*//到店失败生成提醒任务
				AddComTask addComTask = new AddComTask();
				addComTask.setMerchantNo(clientExpDto.getMerchantNo());
				addComTask.setMemberNoGm(clientExpDto.getMemberNoGm());
				addComTask.setCfNo(clientExpDto.getCfNo());
				addComTask.setNoType(FollowNoType.FOLLOW.toString());
				addComTask.setWorkDate(addClientFollow.getNextDate());
				addComTask.setCodeList(addClientFollow.getTaskCode());
				addComTask.setMemberNo(clientExpDto.getMemberNo());
				addComTask.setMemberName(addClientFollowReturn.getMemberName());
				addComTask.setComTaskType(ComTaskType.REMIND);
				
				addComTask.setOrgComTaskCode(addClientFollow.getComTaskCode());
				addComTask.setDis(addClientFollow.getDis());
				addComTask.setFinishOrgComTask(addClientFollow.getFinishOrgComTask());
				addComTask.setRemarkCom(addClientFollow.getRemarkCom());//任务备注
				addComTask.setLastResultDate(addClientFollow.getLastResultDate());
				
				comTaskService.addComTaskWithFinish(addComTask,"0");*/
				
			}

			logger.debug("addClientExpRecord(ClientExpDto) - end - return value={}", returnData); //$NON-NLS-1$
			return returnData;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("新增客户邀约表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_INVITE_ADD_ERROR,"新增客户邀约表信息错误！",e);
		}
	}

	@Override
	public List<Map<String, Object>> findExpResults(Map<String, Object> parmMap) {
		logger.debug("findExpResults(Map<String, Object> parmMap={}) - start",parmMap);
		AssertUtils.notNull(parmMap);
		AssertUtils.notAllNull(parmMap.get("merchantNo"),"商户不能为空");
		List<Map<String, Object>> list;
		try{
			list = clientExpDao.findExpResults(parmMap);
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找到店体验人数错误！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_INVITE_FIND_ERROR,"查找到店体验人数错误！",e);
		}
		return list;
	}

	@Override
	public ClientExpDto findClientExpByCfCode(String cfCode) {
		logger.debug("findClientExpByCfCode(String cfCode={}) - start",cfCode);
		AssertUtils.notNull(cfCode,"跟进编号不能为空");
		List<ClientExpDto> returnList;
		ClientExpDto clientExpDto=null;
		try {
			FindClientExpPage findClientExpPage = new FindClientExpPage();
			findClientExpPage.setCfCode(cfCode);
			returnList = this.findClientExps(findClientExpPage);
			if(returnList.size()<=0){
				logger.error("到店体验表信息查询错误");
				throw new TsfaServiceException(ErrorCode.CLIENT_FOLLOW_FIND_ERROR, "到店体验表信息查询错误.！");
			}
			clientExpDto =returnList.get(0);
			
			/**
			 *  构建语音类记录语音文件的访问路径
			 *  按文件倒序去构建路径：安卓修改时语音文件是全量上传，会造成语音文件路径resourcesUrl比语音类记录多的情况
			 */
			String resourcesUrl = clientExpDto.getResourcesUrl();
			if(StringUtils.isNotEmpty(clientExpDto.getExpFb())) {
				@SuppressWarnings("unchecked")
				List<ClientExpContent> expContentList = JsonUtils.listFromJson(clientExpDto.getExpFb(), ClientExpContent.class);
				
				if(StringUtils.isNotEmpty(resourcesUrl)) {
					// 文件访问域名
					String uploadUrl = localCacheSystemParams.getSystemParam(SystemAliasName.ms.name(), "upload", "uploadUrl");
					
					String[] rs = resourcesUrl.substring(0, resourcesUrl.length() - 1).split(",");
					int urlCount = rs.length;	// 语音文件数
					int urlIndex = urlCount - 1;	// 语音文件倒数
					for(int i = expContentList.size()-1; i>=0; i--) {	// 倒序循环
						ClientExpContent content = expContentList.get(i);
						if("1".equals(content.getType())) {	// 语音类型，则取对应语音文件路径数组值组装成访问地址
							content.setUrl(uploadUrl + rs[urlIndex]);
							urlIndex--;
						}
					}
				}
				clientExpDto.setExpFb(JsonUtils.jsonFromList(expContentList));
			}
			
			String extpTime = clientExpDto.getExpTime();
			if(!StringUtils.isEmpty(extpTime)){
				if(extpTime.length() == 21){
					clientExpDto.setExpTime(extpTime.substring(0, 19));
				}
			}
			
		} catch (Exception e) {
			logger.error("到店体验表信息查询错误", e);
			throw new TsfaServiceException(ErrorCode.CLIENT_FOLLOW_FIND_ERROR, "到店体验表信息查询错误.！", e);
		}
		logger.debug("findClientExpByCfCode(FindClientFollowPage) - end - return value={}", returnList); //$NON-NLS-1$
		return clientExpDto;
	}
	
	
	@Override
	public ClientExpDto updateClientExp(ClientExpDto clientExpDto) throws TsfaServiceException {
		logger.debug("updateClientExp(ClientExpDto clientExpDto={}) - start", clientExpDto); //$NON-NLS-1$

		AssertUtils.notNull(clientExpDto);
		AssertUtils.notNullAndEmpty(clientExpDto.getCode(), "Code不能为空");
		try {
			ClientExp find = clientExpDao.selectByCode(clientExpDto.getCode());
			if (find == null) {
				throw new TsfaServiceException(ErrorCode.CLIENT_FOLLOW_NOT_EXIST_ERROR, "到店体验表信息不存在");
			}
			
			ClientExp clientExp = new ClientExp();
			clientExp.setCode(clientExpDto.getCode());
			clientExp.setExpFb(clientExpDto.getExpFb());
//			clientExp.setExpTime(clientExpDto.getExpTime());
			if(StringUtils.isNotEmpty(clientExpDto.getExpTime())){
				clientExp.setExpTime(DateUtils.parseDate(clientExpDto.getExpTime(),DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss));
			}
			clientExp.setImgs(clientExpDto.getImgs());
			clientExp.setResourcesUrl(clientExpDto.getResourcesUrl());
			clientExp.setMemberName(clientExpDto.getMemberName());
			clientExp.setMemberNameGm(clientExpDto.getMemberNameGm());
			clientExp.setMemberNo(clientExpDto.getMemberNo());
			clientExp.setMemberNoGm(clientExpDto.getMemberNoGm());
			clientExp.setMerchantNo(clientExpDto.getMerchantNo());
			clientExp.setShopName(clientExpDto.getShopName());
			clientExp.setShopNo(clientExpDto.getShopNo());
			clientExp.setExpResult(clientExpDto.getExpResult());
			clientExp.setFailReason(clientExpDto.getFailReason());
			clientExp.setCfNo(clientExpDto.getCfNo());
			clientExp.setCfCode(clientExpDto.getCfCode());
			clientExp.setRemark1(clientExpDto.getRemark1());
			clientExp.setRemark2(clientExpDto.getRemark2());
			clientExp.setRemark3(clientExpDto.getRemark3());
			clientExp.setRemark4(clientExpDto.getRemark4());
			
			AssertUtils.notUpdateMoreThanOne(clientExpDao.updateByPrimaryKeySelective(clientExp));
			logger.debug("updateClientExp(ClientExpDto) - end - return value={}", clientExpDto); //$NON-NLS-1$
			return clientExpDto;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("客户体验表信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.CLIENT_FOLLOW_UPDATE_ERROR, "客户体验表信息更新信息错误！", e);
		}
	}

	@Override
	public Map<String, Object> findExpCountForMemberType(String merchantNo,String memberType,String shopNo,String memberNoGm,String searchNoGm, String nowDate) throws TsfaServiceException {
		logger.debug("findExpCountForMemberType(merchantNo ={} memberType={}) - start", merchantNo,memberType); //$NON-NLS-1$
		AssertUtils.notAllNull(merchantNo,"商户不能为空");
		AssertUtils.notAllNull(memberType,"会员类型不能为空");
		AssertUtils.notAllNullAndEmpty(shopNo, memberNoGm, "门店编号和导购不能同时为空");
		Map<String, Object> returnMap = new HashMap<>();
		try{
			
			Map<String,Object> parmMap = new HashMap<String, Object>();
			parmMap.put("merchantNo", merchantNo);
			parmMap.put("expResult", ExpResult.Y.toString());
			parmMap.put("startTime", nowDate);
			parmMap.put("endTime",nowDate);
			
			List<Map<String,Object>> pmList = new ArrayList<Map<String,Object>>();
			
			/*导购*/
			if(memberType.equals(MemberType.GUID.toString())){
				parmMap.put("memberNoGm", memberNoGm);
			}else{/*店长*/
				if(StringUtils.isNotEmpty(searchNoGm)){
					parmMap.put("memberNoGm", searchNoGm);
				}
				parmMap.put("shopNo", shopNo);
				parmMap.put("isShopMemberNoGm", memberNoGm);
			}
			
			/*到店统计	start*/
			List<String> expStr = new ArrayList<>();
			
			List<Map<String,Object>> listExp = findExpResults(parmMap);
			returnMap.put("expResult", listExp.size());	//到店人数
			/*获取资料完善度、微信昵称*/
			for (Map<String, Object> map : listExp) {
				String pmMemberNo = map.get("memberNo").toString();
				expStr.add(pmMemberNo);		//添加到店客户集合
				FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
				findPersonMemberBase.setMemberNo(pmMemberNo);
				findPersonMemberBase.setMemberNoGm(StringUtils.isNotEmpty(searchNoGm) ? searchNoGm : memberNoGm);
				FindPersonMemberBaseReturn baseReturn= personMemberBaseService.findPersonMemberBaseParams(findPersonMemberBase);
				/*完善度*/
				map.put("ratioClientInfo", baseReturn.getRatioClientInfo()==null?0:baseReturn.getRatioClientInfo());	
				/*微信昵称*/
				map.put("nickName", baseReturn.getNickNameWx());
				/*到店*/
				map.put("isExp",1);	
				/*昵称备注_本地*/
				map.put("nickNameRemarkLocal",baseReturn.getNickNameRemarkLocal());
			}
			/*到店统计	end*/
			
			/*应到店/邀约统计 start*/
			
			//获取到店体验任务的codeList
			FindComTaskList findComTaskList = new FindComTaskList();
			findComTaskList.setComTaskType(ComTaskType.CLIENT_EXP);
			findComTaskList.setMerchantNo(merchantNo);
			FindComTaskListReturn findComTaskListReturn = comTaskListService.findComTaskList(findComTaskList);
			if(findComTaskListReturn==null){
				logger.error("获取到店体验任务错误！");
				throw new TsfaServiceException(ErrorCode.COMTASKLIST_FIND_ERROR,"获取到店体验任务错误！");
			}else{
				parmMap.put("codeList", findComTaskListReturn.getCode());
			}
			
			/*同一客户只保留到店记录*/
			List<Map<String,Object>> listComTask =comTaskService.findExpResults(parmMap);
			List<Map<String,Object>> listComTaskReturn = new ArrayList<>();
			for (Map<String, Object> map : listComTask) {
				String pmMemberNo = map.get("memberNo").toString();
				if(expStr.contains(pmMemberNo)){
					listComTaskReturn.add(map);
				}
			}
			int inviteResult = listComTask.size();//先记录好到店体验任务的人数
			listComTask.removeAll(listComTaskReturn);
			/*应到店人数=实际到店+到店体验任务*/
//			returnMap.put("inviteResult", listComTask.size()+listExp.size());
			returnMap.put("inviteResult", inviteResult);/*2018/04/17修改，应到店人数=到店体验任务*/
			
			/*获取资料完善度、微信昵称*/
			for (Map<String, Object> map : listComTask) {
				String pmMemberNo = map.get("memberNo").toString();
				FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
				findPersonMemberBase.setMemberNo(pmMemberNo);
				findPersonMemberBase.setMemberNoGm(StringUtils.isNotEmpty(searchNoGm) ? searchNoGm : memberNoGm);
				FindPersonMemberBaseReturn baseReturn= personMemberBaseService.findPersonMemberBaseParams(findPersonMemberBase);
				map.put("ratioClientInfo", baseReturn.getRatioClientInfo()==null?0:baseReturn.getRatioClientInfo());	
				map.put("nickName", baseReturn.getNickNameWx());
				map.put("isExp",0);	
				map.put("nickNameRemarkLocal",baseReturn.getNickNameRemarkLocal());
			}
			/*应到店/邀约统计	end*/
			
			pmList.addAll(listExp);						//到店列表
			pmList.addAll(listComTask);						//邀约到店列表
			
			/* 同步导购名称 */
//			if(!memberType.equals(MemberType.GUID.toString())){
				if(!pmList.isEmpty()){
					for (Map<String, Object> pm : pmList) {
						FindGuidMember findGuidMember = new FindGuidMember();
						findGuidMember.setMemberNo(pm.get("memberNoGm").toString());
						FindGuidMemberReturn gm = guidMemberService.findGuidMember(findGuidMember);
						pm.put("memberNameGm", gm.getMemberName());
					}
				}
//			}
			
			returnMap.put("pmList", pmList);
			
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找到店体验人数错误！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_INVITE_FIND_ERROR,"查找到店体验人数错误！",e);
		}
		return returnMap;
	}

	@Override
	public int findCountVisitByGm(Map<String, Object> map) {
		logger.debug("findCountVisitByGm(Map<String, Object> map={}) - start", map); //$NON-NLS-1$

		AssertUtils.notNull(map);
		int count = 0;
		try {
			count = clientExpDao.findCountVisitByGm(map);
		} catch (Exception e) {
			logger.error("查询客户体验量错误", e);
		}
		logger.debug("findCountVisitByGm(count) - end - return value={}", count);
		return count;
	}

	@Override
	public List<Map<String, Object>> findExpNum(Map<String, Object> map) {
		logger.debug("findExpNum(Map<String, Object> map={}) - start", map); //$NON-NLS-1$
		return clientExpDao.findExpNum(map);
	}
	
	@Override
	public FindClientExpReturn findClientExpTodayNum(ClientExpDto clientExpDto)
			throws TsfaServiceException {
		AssertUtils.notAllNull(clientExpDto.getMemberNo(),"客戶编号不能为空");
		AssertUtils.notAllNull(clientExpDto.getMemberNoGm(),"导购编号不能为空");
		 FindClientExpReturn list=null;
	  try {
		list=clientExpDao.findClientExpTodayNum(clientExpDto);
	} catch (Exception e) {
		logger.error("查找到店体验人数错误！",e);
		throw new TsfaServiceException(ErrorCode.CLIENT_INVITE_FIND_ERROR,"查找到店体验人数错误！",e);
	}
		return list;
	}

}

