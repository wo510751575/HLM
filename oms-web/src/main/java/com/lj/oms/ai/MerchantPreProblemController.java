package com.lj.oms.ai;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.business.ai.dto.MerchantAutoReplyDto;
import com.lj.business.ai.dto.MerchantPreAnswerDto;
import com.lj.business.ai.dto.MerchantPreProblemDto;
import com.lj.business.ai.service.IMerchantPreProblemService;
import com.lj.business.ai.service.IProblemService;
import com.lj.business.member.dto.FindGuidMemberDto;
import com.lj.business.member.dto.GuidMemberReturnDto;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.oms.common.BaseController;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：商家预设问题Controller
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭俊霖
 *   
 * CreateDate: 2018年01月17日
 */
@Controller
@RequestMapping(value = "${adminPath}/ai/merchantPreProblem")
public class MerchantPreProblemController  extends BaseController{
	
	
	private static final Logger logger = LoggerFactory.getLogger(MerchantPreProblemController.class);

	@Resource
	private IMerchantPreProblemService merchantPreProblemService;			//商家预设问题服务
	@Resource
	private IProblemService problemService;			
	
	@Resource
	private IGuidMemberService guidMemberService; // 导购服务

	
	/**
	 * 
	 *
	 * 方法说明：商家预设问题列表
	 *
	 * @param findMerchantPreProblemPage
	 * @param pageNo
	 * @param pageSize
	 * @param model
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月17日
	 *
	 */
	@RequiresPermissions("ai:merchantPreProblem:view")
	@RequestMapping(value = {"list", ""})
	public String list(MerchantPreProblemDto findMerchantPreProblemPage,Integer pageNo,Integer pageSize, Model model) {
		try {
			
			if(pageNo!=null){
				findMerchantPreProblemPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findMerchantPreProblemPage.setLimit(pageSize);
			}
			findMerchantPreProblemPage.setMerchantNo(UserUtils.getMerchantNo());
			
			Page<MerchantPreProblemDto> pageDto = merchantPreProblemService.findMerchantPreProblemPage(findMerchantPreProblemPage);
			List<MerchantPreProblemDto> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());
			 
			com.ape.common.persistence.Page<MerchantPreProblemDto> page=new com.ape.common.persistence.Page<MerchantPreProblemDto>(pageNo==null?1:pageNo, pageDto.getLimit(), pageDto.getTotal(), list);
			page.initialize();
			model.addAttribute("page",page);
			model.addAttribute("findMerchantPreProblemPage",findMerchantPreProblemPage);
			
		} catch (Exception e) {
			logger.error("获取商家预设问题信息错误！",e);
		}
		return "modules/ai/merchantPreProblemList";
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查看商家预设问题
	 *
	 * @param findMerchantPreProblem
	 * @param model
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月17日
	 *
	 */
	@RequiresPermissions("ai:merchantPreProblem:view")
	@RequestMapping(value = "form")
	public String form(MerchantPreProblemDto findMerchantPreProblem, Model model) {
		try {
			if(findMerchantPreProblem!=null && findMerchantPreProblem.getCode()!=null){
				if(StringUtils.isNotBlank(findMerchantPreProblem.getCode())){
					MerchantPreProblemDto findMerchantPreProblemReturn= merchantPreProblemService.findMerchantPreProblem(findMerchantPreProblem);
					model.addAttribute("data",findMerchantPreProblemReturn);
				}else{
					//回显添加失败时的信息
					model.addAttribute("data", findMerchantPreProblem);
				}
			}
		} catch (Exception e) {
			logger.error("获取商家预设问题信息错误！",e);
		}
		
		return "modules/ai/merchantPreProblemForm";
	}
	
	/**
	 * 
	 *
	 * 方法说明：保存商家预设问题
	 *
	 * @param addMerchantPreProblem
	 * @param model
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月17日
	 *
	 */
	@RequiresPermissions("ai:merchantPreProblem:edit")
	@RequestMapping(value = "save")
	public String save(MerchantPreProblemDto addMerchantPreProblem, Model model, RedirectAttributes redirectAttributes) {
		try {
			addMerchantPreProblem.setMerchantNo(UserUtils.getMerchantNo());
			//HTML特殊字符转义
			MerchantPreAnswerDto answers = addMerchantPreProblem.getAnswers();
			String content= StringEscapeUtils.unescapeHtml4(answers.getAnswerContent()).toString();
			answers.setAnswerContent(content);
			addMerchantPreProblem.setAnswers(answers);
			addMerchantPreProblem.setRemark(content);
			merchantPreProblemService.addMerchantPreProblemService(addMerchantPreProblem);
			addMessage(redirectAttributes, "保存商家预设问题成功");
		} catch (Exception e) {
			logger.error("保存商家预设问题信息错误！",e);
		}
		return "redirect:" + adminPath + "/ai/merchantPreProblem/";
	}
	
	/**
	 * 
	 *
	 * 方法说明：编辑商家预设问题
	 *
	 * @param updateMerchantPreProblem
	 * @param model
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月17日
	 *
	 */
	@RequiresPermissions("ai:merchantPreProblem:edit")
	@RequestMapping(value = "edit")
	public String edit(MerchantPreProblemDto updateMerchantPreProblem, Model model, RedirectAttributes redirectAttributes) {
		MerchantPreProblemDto findMerchantPreProblemPage=new MerchantPreProblemDto();
		try {
			//HTML特殊字符转义
			MerchantPreAnswerDto answers = updateMerchantPreProblem.getAnswers();
			String content= StringEscapeUtils.unescapeHtml4(answers.getAnswerContent()).toString();
			answers.setAnswerContent(content);
			logger.info("修改内容："+ content);
			updateMerchantPreProblem.setAnswers(answers);
			updateMerchantPreProblem.setAnswerContent(content);
			merchantPreProblemService.updateMerchantPreProblem(updateMerchantPreProblem);
			model.addAttribute("repMsg", "编辑商家预设问题成功");
			
		
		} catch (Exception e) {
			logger.error("编辑商家预设问题信息错误！",e);
		}
		return list(findMerchantPreProblemPage, null, null, model);
	}
	
	/**
	 * 
	 *
	 * 方法说明：生成关键词
	 *
	 * @param content
	 * @return 
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月18日
	 *
	 */
	@RequestMapping(value ="createWords" )
	@ResponseBody
	public List<String> createWords(String content){
		List<String> keyWords = problemService.splitWords(content);
		return keyWords;
	}
	
	
	/**
	 * 查询商户所有已开启自动回复列表
	 */
	@RequiresPermissions("ai:merchantPreProblem:view")
	@RequestMapping(value = "selectAutoReplyList")
	public String selectAutoReplyList(HttpServletResponse response,HttpServletRequest request,String gmNo){
		
		String merchantNo = UserUtils.getMerchantNo(); 
		List<MerchantAutoReplyDto>  list = problemService.selectAutoReplyList(merchantNo, gmNo);
		request.setAttribute("list", list);
		return "modules/ai/addAutoReplyList";
	}
	
	/**
	 * 查询导购是否开启自动回复
	 */
	@RequiresPermissions("ai:merchantPreProblem:edit")
	@RequestMapping(value = "insertGmAutoStatus")
	@ResponseBody
	public Map<String,String> insertGmAutoStatus(String gmNo, String memberGmName){
		Map<String,String> map = new HashMap<String,String>();
		try {
			
			String merchantNo  = UserUtils.getMerchantNo();
			List<MerchantAutoReplyDto>  list = problemService.selectAutoReplyList(merchantNo, gmNo);
			if(list != null && list.size() > 0) {
				map.put("result", "false");
				map.put("message", "导购已开启");
				return map;
			}
		    problemService.insertGmAutoStatus(gmNo, merchantNo, memberGmName);
		    map.put("result", "true");
		    map.put("message", "开启成功");
		}catch(Exception e) {
			logger.error("开启自动回复错误：", e);
			map.put("result", "false");
			map.put("message", "开启自动回复错误");
		}
		return map;

	}
	

	
	
	/**
	 * 关闭自动回复
	 */
	@RequiresPermissions("ai:merchantPreProblem:edit")
	@RequestMapping(value = "deleteAutoReply")
	@ResponseBody
	public String  deleteAutoReplyList(String gmNo){
		try {
		    problemService.deleteAutoReplyList(gmNo);
		    return "true";
		}catch(Exception e) {
			logger.error("关闭自动回复错误：", e);
			return "false";
		}
	}
	
	/**
	 * 调到新增自动回复
	 */
	@RequiresPermissions("ai:merchantPreProblem:edit")
	@RequestMapping(value = "toAddAutoReply")
	public String toAddAutoReply(HttpServletResponse response,HttpServletRequest request) {
		String  merchantNo= UserUtils.getMerchantNo();

		FindGuidMemberDto findGuidMemberDto = new FindGuidMemberDto();	
		findGuidMemberDto.setMerchantNo(merchantNo);
		List<GuidMemberReturnDto> guidMemberList = guidMemberService.findGuidMemberSelective(findGuidMemberDto );
		request.setAttribute("list", guidMemberList);
		return "modules/ai/addAutoReply";
		
	}
	

}
