//package com.lj.business.api.controller;
//
//import javax.annotation.Resource;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.lj.business.st.dto.readDetail.AddReadDetail;
//import com.lj.business.st.dto.readDetail.AddReadDetailReturn;
//import com.lj.business.st.service.IReadDetailService;
//
///**
// * 
// * 
// * 类说明：会员相关信息处理action 修改人 修改日期 内容
// * -------------------------------------------------------- rain 2017-07-03
// * 添加客户管理首页接口queryMbr,urgentMbr,urgentOpMbr,
// * unGroupMbr,favorMbr,unfavorMbr,orderMbr,abandonMbr， repeatMbr rain 2017-07-04
// * 添加客户管理跟进接口cfHistory,addCFRecord，addCFOrder，abandonMember rain 2017-07-05
// * 添加客户管理跟进接口inqueryComTaskList
// * ，addClientKeep,clientKeepHistory，abandonHistory，orderHistory
// * 
// * --------------------------------------------------------
// * <p>
// * 详细描述：
// * 
// * 
// * @author 彭阳
// * 
// * 
// *         CreateDate: 2017年7月3日
// */
//@Controller
//@RequestMapping(value = "/read/")
//public class ReadDetailAction extends Action {
//
//	private static Logger logger = LoggerFactory.getLogger(ReadDetailAction.class);
//
//	@Resource
//	public IReadDetailService readDetailService;
//
//	
//	/**
//	 * 
//	 *
//	 * 方法说明：
//	 *
//	 * @param addReadDetail
//	 * @return
//	 *
//	 * @author 彭阳 CreateDate: 2018年2月1日
//	 *
//	 */
//	@RequestMapping(value = "addReadDetail.do", produces = "application/json;charset=UTF-8")
//	@ResponseBody
//	public AddReadDetailReturn addReadDetail(AddReadDetail addReadDetail){
//		logger.debug("addReadDetail(AddReadDetail addReadDetail={}) - start", addReadDetail); 
//
//		AddReadDetailReturn returnAddReadDetailReturn = readDetailService.addReadDetail(addReadDetail);
//		logger.debug("addReadDetail(AddReadDetail) - end - return value={}", returnAddReadDetailReturn); 
//		return returnAddReadDetailReturn;
//	}
//	
//}
