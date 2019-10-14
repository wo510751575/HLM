package com.lj.business.api.controller.hx;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.pagination.Page;
import com.lj.business.api.controller.Action;
import com.ye.business.hx.dto.params.ClueParams;
import com.ye.business.hx.dto.vo.ClueListVo;
import com.ye.business.hx.dto.vo.shopServerListVo;
import com.ye.business.hx.service.IHxClueService;

/**
 * 焕新线索接口。
 * 
 * @author wnb
 *
 */
@Controller
@RequestMapping(value = "/hx/clue/")
public class HxClueAction extends Action {

	@Resource
	private IHxClueService hxClueService;

	/**
	 * 客户线索数量
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "quantity.do", produces = "application/json;charset=UTF-8")
	public Map<String, Object> quantity() {
		return hxClueService.clueQuantity();
	}

	/**
	 * 客户线索列表
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "list.do", produces = "application/json;charset=UTF-8")
	public Page<ClueListVo> list(ClueParams params) {
		return hxClueService.list(params);
	}

	/**
	 * 已接诊客户列表
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "acceptlist.do", produces = "application/json;charset=UTF-8")
	public Page<ClueListVo> acceptlist(ClueParams params) {
		return hxClueService.acceptlist(params);
	}

	/**
	 * 店铺服务列表
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "shopserverlist.do", produces = "application/json;charset=UTF-8")
	public List<shopServerListVo> shopserverlist(ClueParams params) {
		return hxClueService.shopserverlist(params);
	}

	/**
	 * 接诊
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "visiting.do", produces = "application/json;charset=UTF-8")
	public String visiting(ClueParams params) {
		return hxClueService.visiting(params);
	}

	/**
	 * 到店
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "toshop.do", produces = "application/json;charset=UTF-8")
	public String toshop(ClueParams params) {
		return hxClueService.toshop(params);
	}

	/**
	 * 线索播报
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "broadcast.do", produces = "application/json;charset=UTF-8")
	public List<ClueListVo> broadcast() {
		return hxClueService.broadcast();
	}
	
	/**
	 * 推荐客户数量
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "clientnum.do", produces = "application/json;charset=UTF-8")
	public int clientnum(ClueParams params) {
		return hxClueService.clientnum(params);
	}

}
