package com.lj.oms.st;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.util.StringUtils;
import com.lj.business.member.domain.ShopTerminal;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.dto.addfriends.CountAddFriendsEntity;
import com.lj.business.member.dto.addfriends.FindAddFriendsPage;
import com.lj.business.member.dto.shopterminal.FindShopTerminal;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.service.IAddFriendsService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.st.service.IGetCountAddFriendsService;
import com.lj.oms.common.BaseController;
import com.lj.oms.utils.UserUtils;
import com.lj.oms.utils.excel.ExportExcel;
import com.lj.oms.utils.excel.dto.CountGmAddFriendsDto;
import com.lj.oms.utils.excel.dto.CountShopAddFriendsDto;

/**
 * 
 * 
 * 类说明：获取 客户信息数据
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 龚文学
 * 
 * CreateDate: 2018年12月18日
 */

@Controller
@RequestMapping(value = "${adminPath}/st/countAddFriends")
public class  GetCustomerInfoController extends BaseController{

//	 @Resource
//	 private IShopService shopService; //门诊
	 @Resource
	 private  IGuidMemberService guidMemberService;//员工
	 @Resource
	 private IShopTerminalService shopTerminalService;//微信
	 @Resource
	 private IAddFriendsService addFriendsService;//客户
	 @Resource
	 private IGetCountAddFriendsService getCountService;
	 
	 
	 
	/**
	 *
	 * 方法说明：获取 门诊，员工，微信，客户总数
	 *
	 * @author gongwenxue CreateDate: 2018年12月18日
	 *
	 */
	@RequestMapping("/toCountPage")
	public String  getInfoCount(HttpServletRequest request ) {
		
//		Shop shopEntity = new Shop();
//		shopEntity.setMemberNoMerchant(UserUtils.getMerchantNo());//传参商户编码
		
		ShopTerminal shopTerminalEntity = new ShopTerminal();
		shopTerminalEntity.setMerchantNo(UserUtils.getMerchantNo());
		shopTerminalEntity.setStatus(1);//正常
		
		FindGuidMemberPage menberEntity = new FindGuidMemberPage();
		menberEntity.setMerchantNo(UserUtils.getMerchantNo());
		menberEntity.setStatus("NORMAL");//会员状态正常
		
		FindAddFriendsPage friendsEntiry = new FindAddFriendsPage();
		friendsEntiry.setMerchantNo(UserUtils.getMerchantNo());
		friendsEntiry.setAddStatus("Y");//添加微信好友成功。
		
		//远程调用member服务，获取总数
//		Integer shopCount = shopService.getShopCount(shopEntity);
		Integer shopTerminalCount = shopTerminalService.getShopTerminalCount(shopTerminalEntity);
		Integer guidCount = guidMemberService.findGuidMemberByMerchantNo(menberEntity);
		Integer customerCount = addFriendsService.findAddWxFriendsCount(friendsEntiry);
		

		//新增排行
		List<CountAddFriendsEntity> addlist = addFriendsService.selectShopOrderByaddFriends(UserUtils.getMerchantNo(), null, null, "10");

	    //门诊维护排行
		List<CountAddFriendsEntity> servicelist = addFriendsService.selectShopOrderByServiceFriends(UserUtils.getMerchantNo(), null, null, "10");
		
		//导购新增排行
		List<CountAddFriendsEntity> gmAddlist = addFriendsService.selectGuidOrderByaddFriends(UserUtils.getMerchantNo(), null, null, "10");

		//导购维护排行
		List<CountAddFriendsEntity> gmServicelist = addFriendsService.selectGuidOrderByServiceFriends(UserUtils.getMerchantNo(), null, null, "10");

//		request.setAttribute("shopCount", shopCount);
		request.setAttribute("guidCount", guidCount);
		request.setAttribute("shopTerminalCount", shopTerminalCount);
		request.setAttribute("addFriendsCount", customerCount);
		
		
		request.setAttribute("servicelist", servicelist);
		request.setAttribute("addlist", addlist);
		request.setAttribute("gmAddlist", gmAddlist);
		request.setAttribute("gmServicelist", gmServicelist);
		
		return "modules/index/count";
	}
	
	/**
	 *
	 * 方法说明：获取昨天，近七天，近30天，自定义天数 -- 新增客户总量。
	 * 	逻辑：1.当参数为“昨天，自定义时间”时，需要返回两个数据，一个是按每小时的新增量；第二个是按每小时累计新增的总数；
	 * 		2.当参数为“近七天，30天”时，只需要返回每天新增的总量，不需要累计。
	 *  	注意：没种数据用数组，并且需要配对另一个对应时间的数组。比如按7天，返回的数据应该是两个数组 [第一天，第二天……]，[10,12,15,60,34……]
	 * @param intervalTime:昨天，7天，30天
	 * @param startTime:开始时间；endTime：结束时间 （intervalTime和startTime不能同存）
	 * @author gongwenxue CreateDate: 2018年12月19日
	 *
	 */
	 @ResponseBody
	@RequestMapping("/getGroupByTime")
	public HashMap<String, List<String>> getAddFriendsCount(String intervalTime,String startTime,String endTime,String noWx) {
		 List<com.lj.business.st.domain.CountAddFriendsEntity> entities = null;
		if(noWx == null || noWx.equals("")) {
			//调用 st 获取当天的统计的新增客户总数
			System.out.println("调用getCount");
			entities = getCountService.getCountByTime(intervalTime,startTime,endTime,UserUtils.getMerchantNo(),null, "1");
			System.out.println("获取的list" + entities);
		}
		if(noWx != null && !noWx.equals("")) {
			//调用 st 获取当天的统计的新增客户总数
			System.out.println("调用getCount");
			entities = getCountService.getCountByTime(intervalTime,startTime,endTime,UserUtils.getMerchantNo(),noWx, "2");
			System.out.println("获取的list" + entities);
		}
		List<String> times = new ArrayList<>(); 
		List<String> counts = new ArrayList<>(); 
		for (com.lj.business.st.domain.CountAddFriendsEntity entity : entities) {
			times.add(entity.getCountDate());
			counts.add(entity.getCount()+"");
		}
		HashMap<String, List<String>> map = new HashMap<>();
		map.put("time", times);
		map.put("counts", counts);
		
        return map;
	}
	
	
	/**
	 * 门诊排行榜
	 * @param intervalTime
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAddFriendOrder")
	public Map<String, List<CountAddFriendsEntity>> getAddFriendOrder(String intervalTime,String startTime,String endTime) {
		
		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
		
		if(startTime == null || startTime.equals("")) {
			
			//昨天数据
	        if(intervalTime.equals("interval1")) {
	        	
	            calendar.add(Calendar.DATE, -1);    //得到前一天

	            startTime = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(calendar.getTime());
	            endTime = new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(calendar.getTime());

	         
			}
	        //7天数据
			if(intervalTime.equals("interval7")) {
				calendar.add(Calendar.DATE, -7);    //得到前一天
				startTime = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(calendar.getTime());
				endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			}
			
			
	        if(intervalTime.equals("interval30")) {
	        	   calendar.add(Calendar.MONTH, -1);    //得到前一个月
	        	   startTime = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(calendar.getTime());
	        	   endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			}
	        
	        if(intervalTime.equals("intervalAll")) {
	        	startTime = null;
	        	endTime = null;
	        }
		}
		
		
		
		//新增排行
		List<CountAddFriendsEntity> addlist = addFriendsService.selectShopOrderByaddFriends(UserUtils.getMerchantNo(), startTime, endTime, "10");

	    //门诊维护排行
		List<CountAddFriendsEntity> servicelist = addFriendsService.selectShopOrderByServiceFriends(UserUtils.getMerchantNo(), startTime, endTime, "10");

		//导购新增排行
		List<CountAddFriendsEntity> gmAddlist = addFriendsService.selectGuidOrderByaddFriends(UserUtils.getMerchantNo(), startTime, endTime, "10");

		//导购维护排行
		List<CountAddFriendsEntity> gmServicelist = addFriendsService.selectGuidOrderByServiceFriends(UserUtils.getMerchantNo(), startTime, endTime, "10");

		
		Map<String, List<CountAddFriendsEntity>> map = new HashMap<String, List<CountAddFriendsEntity>>();
		
		map.put("shopAddlist", addlist);
		map.put("shopServicelist", servicelist);
		map.put("gmAddlist", gmAddlist);
		map.put("gmServicelist", gmServicelist);
		
        return map;
	}
	
	
	/**
	 * 导出excel
	 */
	@RequestMapping("/exportExcel")
	public void exportExcel(String type,String intervalTime, String startTime, String endTime,HttpServletResponse response) {
		
		try {
			
			Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
			
			if(startTime == null || startTime.equals("")) {
				
				//昨天数据
		        if(intervalTime.equals("interval1")) {
		        	
		            calendar.add(Calendar.DATE, -1);    //得到前一天

		            startTime = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(calendar.getTime());
		            endTime = new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(calendar.getTime());

		         
				}
		        //7天数据
				if(intervalTime.equals("interval7")) {
					calendar.add(Calendar.DATE, -7);    //得到前一天
					startTime = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(calendar.getTime());
					endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				}
				
				
		        if(intervalTime.equals("interval30")) {
		        	   calendar.add(Calendar.MONTH, -1);    //得到前一个月
		        	   startTime = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(calendar.getTime());
		        	   endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				}
		        
		        if(intervalTime.equals("intervalAll")) {
		        	startTime = null;
		        	endTime = null;
		        }
			}
			
			
			
			List<CountAddFriendsEntity> list = null;
		    String str ="";
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String dateStr = sdf.format(new Date());
			String fileName = null;
			if(type.equals("1")) {
			    
			    //商户下所有终端列表
				FindShopTerminal findShopTerminal = new FindShopTerminal();
				findShopTerminal.setMerchantNo(UserUtils.getMerchantNo());
			    List<FindShopTerminalReturn> listShop= shopTerminalService.findShopTerminalSelect(findShopTerminal);
			    
			    Map<String, String> shopNameMap = new HashMap<String, String>();
			    for (FindShopTerminalReturn findShopTerminalReturn : listShop) {
			    	String shopName =StringUtils.isEmpty(findShopTerminalReturn.getShopName())?"":findShopTerminalReturn.getShopName();
			    	shopNameMap.put(findShopTerminalReturn.getNoWx(), shopName);
				}
			    //新增排行
			    list = addFriendsService.selectShopOrderByaddFriends(UserUtils.getMerchantNo(), startTime, endTime, "5000");
			    for (CountAddFriendsEntity item : list) {
			    	item.setShopName(shopNameMap.get(item.getNoWx()));
				}
			    
			    str="门诊新增排行";
			    fileName = str + dateStr + ".xlsx";
			    new ExportExcel(str, CountShopAddFriendsDto.class, 2).setDataList(list).write(response, fileName).dispose();
			}
			
			if(type.equals("2")) {
		         
			    //商户下所有终端列表
				FindShopTerminal findShopTerminal = new FindShopTerminal();
				findShopTerminal.setMerchantNo(UserUtils.getMerchantNo());
			    List<FindShopTerminalReturn> listShop= shopTerminalService.findShopTerminalSelect(findShopTerminal);
			    
			    Map<String, String> shopNameMap = new HashMap<String, String>();
			    for (FindShopTerminalReturn findShopTerminalReturn : listShop) {
			    	String shopName =StringUtils.isEmpty(findShopTerminalReturn.getShopName())?"":findShopTerminalReturn.getShopName();
			    	shopNameMap.put(findShopTerminalReturn.getNoWx(), shopName);
				}
			    //门店维护排行
			    list = addFriendsService.selectShopOrderByServiceFriends(UserUtils.getMerchantNo(), startTime, endTime, "5000");
			    for (CountAddFriendsEntity item : list) {
			    	item.setShopName(shopNameMap.get(item.getNoWx()));
				}
			    str="门店维护排行";
			    fileName = str + dateStr + ".xlsx";
			    new ExportExcel(str, CountShopAddFriendsDto.class, 2).setDataList(list).write(response, fileName).dispose();
	
			}
			
			if(type.equals("3")) {
			     //导购新增排行
			     list = addFriendsService.selectGuidOrderByaddFriends(UserUtils.getMerchantNo(), startTime, endTime, "5000");
			     str="导购新增排行";
			     fileName = str + dateStr + ".xlsx";
			     new ExportExcel(str, CountGmAddFriendsDto.class, 2).setDataList(list).write(response, fileName).dispose();
			}
			
			if(type.equals("4")) {
			     //导购维护排行
			    list = addFriendsService.selectGuidOrderByServiceFriends(UserUtils.getMerchantNo(), startTime, endTime, "5000");
			    str="导购维护排行";
			    fileName = str + dateStr + ".xlsx";
			    new ExportExcel(str, CountGmAddFriendsDto.class, 2).setDataList(list).write(response, fileName).dispose();
			}
			
	
	        
		}catch(Exception e) {
			logger.error("【导出加好友排行】",e);
		}
	
	}
	

	
	
}
