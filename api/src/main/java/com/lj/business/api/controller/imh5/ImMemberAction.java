package com.lj.business.api.controller.imh5;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.member.dto.ChangePmLabel;
import com.lj.business.member.dto.ChangePmTypeHc;
import com.lj.business.member.dto.FindPmLabel;
import com.lj.business.member.dto.FindPmType;
import com.lj.business.member.dto.PmLabelDto;
import com.lj.business.member.service.IPmLabelService;
import com.lj.business.member.service.IPmTypeService;

@Controller
@RequestMapping(value="/imh5/member/")
public class ImMemberAction extends Action{

	private static final Logger logger = LoggerFactory.getLogger(ImMemberAction.class);
	
	@Resource
	private IPmLabelService pmLabelService; // 客户标签服务
	@Resource
	private IPmTypeService pmTypeService; // 客户分类服务
	
	/**
	 * 
	 *@Desc 查询客户标签
	 *@param memberNo
	 *@param merchantNo
	 *@param shopWx
	 *@return
	 *@return GeneralResponse
	 *@author 贾光朝
	 *@createDate 2019年4月15日下午5:01:32
	 */
	@RequestMapping(value="/findTagsByMemberNo.do")
	@ResponseBody
	public GeneralResponse findTagsByMemberNo(String memberNo,String merchantNo,String shopWx){
		List<PmLabelDto> labels = null;
		try {
			FindPmLabel findPmLabel = new FindPmLabel();
			findPmLabel.setMemberNo(memberNo);
			findPmLabel.setMerchantNo(merchantNo);
			findPmLabel.setShopWx(shopWx);
			labels = pmLabelService.findPmLabelByMemberNoAndMerchantNo(findPmLabel);
		} catch (Exception e) {
			logger.error("获取个人标签错误!", e);
		}
		return GeneralResponse.generateSuccessResponse(labels);
	}
	
	
	
	/**
	 * 
	 *@Desc 查询商户下所有标签
	 *@param merchantNo
	 *@return
	 *@return GeneralResponse
	 *@author 贾光朝
	 *@createDate 2019年4月9日下午5:45:37
	 */
	@RequestMapping(value="/selectTags.do")
	@ResponseBody
	public GeneralResponse selectTags(String merchantNo){
		List<PmLabelDto> labels = null;
		try {
			labels = pmLabelService.findPmLabelByMerchantNo(merchantNo);
		} catch (Exception e) {
			logger.error("查询商户下标签错误！");
			return GeneralResponse.generateFailureResponse(e);
		}
		return GeneralResponse.generateSuccessResponse(labels);
	}
	
	
	/**
	 * 
	 *@Desc 批量添加标签
	 *@param tags
	 *@param names
	 *@param memberNos
	 *@return
	 *@return GeneralResponse
	 *@author 贾光朝
	 *@createDate 2019年4月9日下午5:35:20
	 */
	@RequestMapping(value="/addTags.do")
	@ResponseBody
	public GeneralResponse addTags(String tags,String names, String memberNos,String merchantNo,String noWxGm){
		try {
            if (org.apache.commons.lang3.StringUtils.isNotBlank(memberNos)) {
                List<PmLabelDto> labels = new ArrayList<>();
                if (org.apache.commons.lang3.StringUtils.isNotBlank(tags)) {
                    String[] labelCodeArr = tags.split(",");
                    String[] labelNameArr = names.split(",");
                    for (int i = 0; i < labelCodeArr.length; i++) {
                    	PmLabelDto pmLabelDto = new PmLabelDto();
                        pmLabelDto.setCode(labelCodeArr[i]);
                        pmLabelDto.setLabelName(labelNameArr[i]);
                        labels.add(pmLabelDto );
					}
                }
                String[] memberNoArr = memberNos.split(",");
                
                ChangePmLabel changePmLabel = new ChangePmLabel();
                for (String memberNo : memberNoArr) {
                    changePmLabel.setMemberNo(memberNo);
                    changePmLabel.setMerchantNo(merchantNo);
                    changePmLabel.setLabels(labels);
                    changePmLabel.setShopWx(noWxGm);
                    pmLabelService.changePmLabel(changePmLabel );
                }
                
            }
        } catch (Exception e) {
            logger.error("获取商户下的标签列表失败！",e);
            return GeneralResponse.generateFailureResponse(e);
        }
		return GeneralResponse.generateSuccessResponse();
	}
	
	/**
	 * 
	 *@Desc 查询商户下分组信息
	 *@param merchantNo
	 *@return
	 *@return GeneralResponse
	 *@author 贾光朝
	 *@createDate 2019年4月9日下午6:41:06
	 */
	@RequestMapping(value="/selectPmTypes.do")
	@ResponseBody
	public GeneralResponse selectPmTypes(String merchantNo){
		try {
			List<FindPmType> pmTypeList = pmTypeService.inqueryMemberOutOffGroupInfo(merchantNo, null, null, "1");
			return GeneralResponse.generateSuccessResponse(pmTypeList);
		} catch (Exception e) {
			logger.error("查询商户下分组信息失败！");
			return GeneralResponse.generateFailureResponse(e);
		}
	}
	
	/**
	 * 
	 *@Desc 批量添加客户分组
	 *@param pmCodes
	 *@param pmTypeCode
	 *@param merchantNo
	 *@return
	 *@return GeneralResponse
	 *@author 贾光朝
	 *@createDate 2019年4月9日下午6:13:32
	 */
	@RequestMapping(value="/addPmType.do")
	@ResponseBody
	public GeneralResponse addPmType(String pmCodes,String memberNoGm, String pmTypeCode,String noWxGm, String merchantNo){
		try {
	            if (org.apache.commons.lang3.StringUtils.isNotBlank(pmCodes)) {
	                String[] pmCodeArr = pmCodes.split(",");
	                ChangePmTypeHc[] changePmTypeHcArr = new ChangePmTypeHc[pmCodeArr.length];
	                for (int i = 0; i < pmCodeArr.length; i++) {
	                    ChangePmTypeHc changePmTypeHc = new ChangePmTypeHc();
	                    changePmTypeHc.setMemberNoGm(memberNoGm);
	                    changePmTypeHc.setMemberNo(pmCodeArr[i]);
	                    changePmTypeHc.setPmTypeCode(pmTypeCode);
	                    changePmTypeHc.setMerchantNo(merchantNo);
	                    changePmTypeHc.setShopWx(noWxGm);
	                    changePmTypeHcArr[i] = changePmTypeHc;
	                }
	                
	                for (ChangePmTypeHc changePmTypeHc : changePmTypeHcArr) {
	                    pmTypeService.changePmTypeHc(changePmTypeHc);
	                }
	                return GeneralResponse.generateSuccessResponse();
	            } else {
	            	return GeneralResponse.generateFailureResponse();
	            }
	        } catch (Exception e) {
	            logger.error("批量分组错误！", e);
	            return GeneralResponse.generateFailureResponse(e);
	        }
	}
	
}
