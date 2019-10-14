package com.lj.business.api.controller.cm;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.pagination.Page;
import com.lj.business.api.controller.Action;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoPage;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoPageReturn;
import com.lj.business.cm.dto.wordsType.FindWordsTypePage;
import com.lj.business.cm.dto.wordsType.FindWordsTypePageReturn;
import com.lj.business.cm.service.ICommonWordsInfoService;
import com.lj.business.cm.service.ICommonWordsTypeService;
import com.lj.business.cm.service.IWordsInfoService;
import com.lj.business.cm.service.IWordsTypeService;

/**
 * 话术接口
 * @author hp
 *
 */
@Controller
@RequestMapping(value = "/words/")
public class WordsAction extends Action {

	@Resource
	private IWordsTypeService wordsTypeService;						//个人话术类型服务
	@Resource
	private IWordsInfoService wordsInfoService;						//个人话术服务
	@Resource
	private ICommonWordsTypeService commonWordsTypeService;			//公司话术类型服务
	@Resource
	private ICommonWordsInfoService commonWordsInfoService;			//公司话术服务
	
	/**
	 * 方法说明:查询个人话术类型分页-APP用
	 * @return
	 */
	@RequestMapping(value = "/findWordsTypeApp.do",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Page<FindWordsTypePageReturn> findWordsTypeApp(FindWordsTypePage findWordsTypePage){
		Page<FindWordsTypePageReturn> wordsTypePage = wordsTypeService.findWordsTypePage(findWordsTypePage);
		return wordsTypePage;
		
	}
	
	/**
	 * 方法说明:查询公司话术类型分页-APP用
	 * @return
	 */
	@RequestMapping(value = "/findCommonWordsTypeApp.do",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Page<FindWordsTypePageReturn> findCommonWordsTypeApp(FindWordsTypePage findWordsTypePage){
		Page<FindWordsTypePageReturn> commonWordsTypePage = commonWordsTypeService.findWordsTypePage(findWordsTypePage);
		return commonWordsTypePage;
	}
	
	/**
	 * 方法说明:查询个人话术类型下的话术分页-APP用
	 * @param findWordsInfoPage
	 * @return
	 */
	@RequestMapping(value = "/findWordsInfoApp.do",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Page<FindWordsInfoPageReturn> findWordsInfoApp(FindWordsInfoPage findWordsInfoPage){
		Page<FindWordsInfoPageReturn> pageDto = wordsInfoService.findWordsInfoPage(findWordsInfoPage);
		return pageDto;
	}
	
	/**
	 * 方法说明:查询公司话术类型下的话术分页-APP用
	 * @param findWordsInfoPage
	 * @return
	 */
	@RequestMapping(value = "/findCommonWordsInfoApp.do",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Page<FindWordsInfoPageReturn> findCommonWordsInfoApp(FindWordsInfoPage findWordsInfoPage){
		Page<FindWordsInfoPageReturn> pageDto = commonWordsInfoService.findWordsInfoPage(findWordsInfoPage);
		return pageDto;
	}
}
