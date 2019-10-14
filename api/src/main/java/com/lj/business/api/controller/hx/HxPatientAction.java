package com.lj.business.api.controller.hx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.pagination.IPage;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.member.dto.PersonMemberDto;
import com.lj.business.member.dto.addfriends.FindAddFriends;
import com.lj.business.member.dto.addfriends.FindAddFriendsReturn;
import com.lj.business.member.dto.addfriends.FindClaimMemberPage;
import com.lj.business.member.dto.addfriends.FindClaimMemberPageReturn;
import com.lj.business.member.service.IAddFriendsService;
import com.lj.business.member.service.IPersonMemberService;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.domain.HxPatient;
import com.ye.business.hx.dto.HxPatientDto;
import com.ye.business.hx.dto.PatientServiceAdvisoryDto;
import com.ye.business.hx.dto.PatientServiceDto;
import com.ye.business.hx.dto.params.PatientParams;
import com.ye.business.hx.service.IHxPatientService;

/**
 * 患者接口。
 * 
 * @author wnb
 *
 */
@Controller
@RequestMapping(value = "/hx/patient/")
public class HxPatientAction extends Action {
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(HxPatientAction.class);

	@Resource
	private IHxPatientService hxPatientService;
	
	@Resource
	private IAddFriendsService addFriendsService;
	
	@Resource
	private IPersonMemberService personMemberService ;
	
	/**
	 * 添加/编辑患者
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "save.do", produces = "application/json;charset=UTF-8")
	public String save(HxPatient patient) {
		return hxPatientService.save(patient);
	}

	/**
	 * 患者列表
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "list.do", produces = "application/json;charset=UTF-8")
	public Page<HxPatientDto> list(PatientParams params) {
		return hxPatientService.list(params);
	}

	/**
	 * 患者详情
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "details.do", produces = "application/json;charset=UTF-8")
	public HxPatientDto details(HxPatientDto dto) {
		return hxPatientService.findHxPatient(dto);
	}
	
	/**
	 * 咨询记录
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "advisoryrecords.do", produces = "application/json;charset=UTF-8")
	public List<PatientServiceAdvisoryDto> advisoryrecords(String code) {
		return hxPatientService.advisoryrecords(code);
	}
	
	/**
	 * 就诊记录
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "visitrecords.do", produces = "application/json;charset=UTF-8")
	public List<PatientServiceDto> visitrecords(String code) {
		return hxPatientService.visitrecords(code);
	}

	/**
	 * 患者绑定直通车客户信息.
	 * <p>场景1，明确指定患者和微信，直接绑定
	 * <p>场景2，微信客户认领后，根据姓名手机号等信息查患者进行绑定
	 * @param patientDto
	 * @return
	 * @author lhy 2019.07.04
	 */
	@ResponseBody
	@RequestMapping(value = "bindWx.do", produces = "application/json;charset=UTF-8")
	public GeneralResponse bindWx(HxPatientDto patientDto,String claimCode) {
		if(StringUtils.isNotEmpty(claimCode)&& StringUtils.isEmpty(patientDto.getMemberNo())) {
			FindAddFriends findAddFriends =new FindAddFriends();
			findAddFriends.setCode(claimCode);
			FindAddFriendsReturn addF=addFriendsService.findAddFriends(findAddFriends);
			if(null!=addF&&StringUtils.isNotEmpty(addF.getMemberNo())) {
				patientDto.setMemberNo(addF.getMemberNo());
				logger.info("查找认领信息：claimCode:{},memberNo:{}",claimCode,addF.getMemberNo());
			}else {
				throw new TsfaServiceException(ErrorCode.HX_PATIENT_UPDATE_ERROR, "该客户还未认领！");
			}
		}
		hxPatientService.bindWx(patientDto);
		return GeneralResponse.generateSuccessResponse();
	}
	
	/**
	 * 根据直通车客户编号查询患者
	 * @param patientDto
	 * @return
	 * @author lhy 2019年7月4日
	 */
	@ResponseBody
	@RequestMapping(value = "findByMemberNo.do", produces = "application/json;charset=UTF-8")
	public GeneralResponse findByMemberNo(HxPatientDto patientDto) {
		AssertUtils.notNullAndEmpty(patientDto.getMemberNo(),"客户编号不能为空！");
		AssertUtils.notNullAndEmpty(patientDto.getMerchantNo(),"商户号不能为空！");
		HxPatientDto rt = hxPatientService.findHxPatientByMemberNo(patientDto);
		return GeneralResponse.generateSuccessResponse(rt);
	}
	
	/**
	 * 手机号查客户直通车用户
	 * @param patientDto
	 * phone 手机号必填
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findZtcMbrByPhone.do", produces = "application/json;charset=UTF-8")
	public GeneralResponse findZtcMbrByPhone(HxPatientDto patientDto) {
		AssertUtils.notNullAndEmpty(patientDto.getPhone(),"手机号不能为空！");
		AssertUtils.notNullAndEmpty(patientDto.getMerchantNo(),"商户号不能为空！");
		AssertUtils.notNullAndEmpty(patientDto.getNoWxGm(),"导购微信号不能为空！");
		AssertUtils.notNullAndEmpty(patientDto.getMemberNoGm(),"导购编号不能为空！");
		
		Map<String, Object> rtData=new HashMap<String, Object>();
		
		//1.根据手机号查本商户好友
		PersonMemberDto memberDto =new PersonMemberDto();
		memberDto.setMerchantNo(patientDto.getMerchantNo());
		memberDto.setShopWx(patientDto.getNoWxGm());
		memberDto.setMobile(patientDto.getPhone());
		List<PersonMemberDto> data = personMemberService.findPersonMemberByMoblies(memberDto);
		if(data!=null && data.size()>0) {
			rtData.put("status", "FRIEND");//已是好友
			rtData.put("member", data.get(0));
//			AssertUtils.notNullAndEmpty(patientDto.getCode(),"患者编号不能为空！");
//			HxPatientDto bindWxDto=new HxPatientDto();
//			bindWxDto.setCode(patientDto.getCode());
//			bindWxDto.setMemberNo(data.get(0).getMemberNo());
//			hxPatientService.bindWx(patientDto); //是否绑定则要看客户意愿，系统先不自动绑定
		}else {
			//2.根据手机号查待认领列表
			FindClaimMemberPage findClaimMemberPage=new FindClaimMemberPage();
			findClaimMemberPage.setStart(0);
			findClaimMemberPage.setLimit(1);
			findClaimMemberPage.setFlag(false); //待认领
			findClaimMemberPage.setMobile(patientDto.getPhone());//手机号查找
			findClaimMemberPage.setMemberNoGm(patientDto.getMemberNoGm());
			IPage<FindClaimMemberPageReturn> pages = addFriendsService.findClaimMemberPage(findClaimMemberPage, false,patientDto.getNoWxGm());
//			
			if(pages.getTotal()>0) {
				rtData.put("status", "NOCLAIM");//未认领 
				List<FindClaimMemberPageReturn> list = new ArrayList<FindClaimMemberPageReturn>();
				list.addAll(pages.getRows());
				rtData.put("member",list.get(0) );
				//未认领时，客户信息没有memberNo 无法绑定
			}else {
				//无则 提示不是好友
				rtData.put("status", "NOTFRIEND");//不是好友
			}
		}
 			
		return GeneralResponse.generateSuccessResponse(rtData);
	}
}
