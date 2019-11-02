/**  
 * All rights Reserved, Designed By www.kehuzhitongche.com
 * @Title:  GumCheckAction.java   
 * @Package com.lj.business.api.controller.hx   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 贾光朝     
 * @date:   2019-10-28 16:37   
 * @version V1.0 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
package com.lj.business.api.controller.hx;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.ye.business.hx.dto.FindGumCheckPage;
import com.ye.business.hx.dto.GumCheckDto;
import com.ye.business.hx.dto.GumCheckVo;
import com.ye.business.hx.service.IGumCheckService;

/**   
 * @ClassName:  GumCheckAction   
 * @Description:TODO(牙周检查)   
 * @author: 贾光朝 
 * @date:   2019-10-28 16:37   
 *     
 * @Copyright: 2019 www.kehuzhitongche.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
@Controller
@RequestMapping(value="/hx/gumCheck")
public class GumCheckAction extends Action {

	@Resource
	private IGumCheckService gumCheckService;
	
	@ResponseBody
	@RequestMapping(value="/add.do",produces="application/json;charset=UTF-8")
	public GeneralResponse add(GumCheckDto gumCheckDto) {
		gumCheckDto.setCode(GUID.generateByUUID());
		gumCheckDto.setCreateDate(new Date());
		gumCheckService.addGumCheck(gumCheckDto);
		return GeneralResponse.generateSuccessResponse();
	}
	
	@ResponseBody
	@RequestMapping(value="/edit.do",produces="application/json;charset=UTF-8")
	public GeneralResponse edit(GumCheckDto gumCheckDto) {
		AssertUtils.notNullAndEmpty(gumCheckDto);
		AssertUtils.notNullAndEmpty(gumCheckDto.getCode(),"code不能为空!");
		gumCheckService.updateGumCheck(gumCheckDto);
		return GeneralResponse.generateSuccessResponse();
	}
	
	@ResponseBody
	@RequestMapping(value="/list.do",produces="application/json;charset=UTF-8")
	public GeneralResponse list(FindGumCheckPage findGumCheckPage,GumCheckDto param) {
		AssertUtils.notNullAndEmpty(param);
		findGumCheckPage.setParam(param);
		List<GumCheckDto> list = gumCheckService.findGumChecks(findGumCheckPage);
		//组装数据
		Map<String,List<GumCheckDto>> map = new HashMap<>();
		map.put("1L", null);
		map.put("1B", null);
		map.put("2L", null);
		map.put("2B", null);
		map.put("3L", null);
		map.put("3B", null);
		map.put("4L", null);
		map.put("4B", null);
		List<GumCheckDto> oneL = new ArrayList<>();
		List<GumCheckDto> oneB = new ArrayList<>();
		List<GumCheckDto> twoL = new ArrayList<>();
		List<GumCheckDto> twoB = new ArrayList<>();
		List<GumCheckDto> threeL = new ArrayList<>();
		List<GumCheckDto> threeB = new ArrayList<>();
		List<GumCheckDto> fourL = new ArrayList<>();
		List<GumCheckDto> fourB = new ArrayList<>();
		for (GumCheckDto gumCheckDto : list) {
			String remark = gumCheckDto.getRemark();
			if(remark!=null) {
				switch (remark) {
				case "1L":
					oneL.add(gumCheckDto);
					map.put(remark, oneL);
					break;
				case "1B":
					oneB.add(gumCheckDto);
					map.put(remark, oneB);
					break;
				case "2L":
					twoL.add(gumCheckDto);
					map.put(remark, twoL);
					break;
				case "2B":
					twoB.add(gumCheckDto);
					map.put(remark, twoB);
					break;
				case "3L":
					threeL.add(gumCheckDto);
					map.put(remark, threeL);
					break;
				case "3B":
					threeB.add(gumCheckDto);
					map.put(remark, threeB);
					break;
				case "4L":
					fourL.add(gumCheckDto);
					map.put(remark, fourL);
					break;
				case "4B":
					fourB.add(gumCheckDto);
					map.put(remark, fourB);
					break;

				default:
					break;
				}
			}
		}
		//解析map组装到List<GumCheckVo>
		List<GumCheckVo> gumCheckVoList = new ArrayList<>();
		for(String key:map.keySet()) {
			GumCheckVo gumCheckVo = new GumCheckVo();
			gumCheckVo.setLocation(key);
			gumCheckVo.setList(map.get(key));
			gumCheckVoList.add(gumCheckVo);
		}
		
		return GeneralResponse.generateSuccessResponse(gumCheckVoList);
	}
	
}
