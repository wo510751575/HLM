package com.lj.business.member.service.impl;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.member.dto.chatroom.FindChatRoomPage;
import com.lj.business.member.dto.chatroom.FindChatRoomPageReturn;
import com.lj.business.member.service.IChatRoomService;


/**
 * 
 * 
 * 类说明：群信息表测试类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2018年03月22日
 */
public class ChatRoomServiceImplTest extends SpringTestCase{

	@Resource
	private IChatRoomService chatRoomService;

	@Test
	public void findChatRoomPage() throws TsfaServiceException{
		FindChatRoomPage findChatRoomPage = new FindChatRoomPage();
//		findChatRoomPage.setMemberNoGm("LJ_61379cf91ccd4c71b4d58b4d42fe031a");
		findChatRoomPage.setMerchantNo("e96f64d736564a768c9ab9f23f4962df");
//		findChatRoomPage.setShopNo("LJ_bb0a003e802b4617999bd8bd8e9fec30");
		Page<FindChatRoomPageReturn> page = chatRoomService.findChatRoomPage(findChatRoomPage);
		Assert.assertNotNull(page);
		System.out.println(page);
	}
}
