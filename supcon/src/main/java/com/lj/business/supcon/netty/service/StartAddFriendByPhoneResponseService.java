package com.lj.business.supcon.netty.service;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.business.common.CommonConstant;
import com.lj.business.member.dto.AddFriendsTaskDetailDto;
import com.lj.business.member.dto.AddFriendsTaskDto;
import com.lj.business.member.emus.AddfriendsTaskStatus;
import com.lj.business.member.service.IAddFriendsTaskDetailService;
import com.lj.business.member.service.IAddFriendsTaskService;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.contacts.StartAddFriendByPhoneResponse;

import io.netty.channel.Channel;
/**
 * 中控使用通讯录开始添加好友时反馈信息（不是最终结果）
 * @author zlh
 *
 */
@Service
public class StartAddFriendByPhoneResponseService implements IService<StartAddFriendByPhoneResponse> {
	private static Logger logger = LoggerFactory.getLogger(StartAddFriendByPhoneResponseService.class);
	@Resource
	private IAddFriendsTaskService addFriendsTaskService;
	@Resource
	private IAddFriendsTaskDetailService addFriendsTaskDetailService;
	
	@Override
	public void process(StartAddFriendByPhoneResponse request, Message message, Channel channel) {
		AssertUtils.notNullAndEmpty(request.getMobile(),"电话号码不能为空!");
		IoSession session = ChannelUtils.getSession(channel);
		
			logger.info("中控客户端反馈通过通讯录加好好友情况：{},{}", request);
			AddFriendsTaskDetailDto addFriendsTaskDetailDto = new AddFriendsTaskDetailDto();
			addFriendsTaskDetailDto.setNoWxGm(session.getNoWxGm());
			addFriendsTaskDetailDto.setPhone(request.getMobile());
			AddFriendsTaskDetailDto friendsTaskDetailDto= addFriendsTaskDetailService.findByCond(addFriendsTaskDetailDto);
			if(null ==friendsTaskDetailDto) {
				logger.error("任务详情已删除！code={}",request.getCode());
				return;
			}
			
			AddFriendsTaskDetailDto updateDetailDto = new AddFriendsTaskDetailDto();
			updateDetailDto.setCode(friendsTaskDetailDto.getCode());
			updateDetailDto.setAddfriendsDate(new Date(request.getStartTime()));
			updateDetailDto.setStatus(AddfriendsTaskStatus.SUCCESS.getCode());
			String detail ="已发好友请求";
			if(!request.isResult()) {
				//添加失败message中包含失败内容
				detail = request.getMessage();
			}
			updateDetailDto.setDetail(detail);
			addFriendsTaskDetailService.updateAddFriendsTaskDetail(updateDetailDto);
			//主任务成功数+1
			AddFriendsTaskDto addFriendsTaskDto = new AddFriendsTaskDto();
			addFriendsTaskDto.setCode(friendsTaskDetailDto.getTaskCode());
			AddFriendsTaskDto taskDto= addFriendsTaskService.findAddFriendsTask(addFriendsTaskDto);
			if(null ==taskDto) {
				logger.error("主任务已删除！code={}",request.getCode());
				return;
			}
			if(String.valueOf(CommonConstant.I_NO).equals(taskDto.getStatus())) {
				AddFriendsTaskDto updateDto = new AddFriendsTaskDto();
				updateDto.setCode(taskDto.getCode());
				updateDto.setCompleteNum(taskDto.getCompleteNum()+1);
				if(updateDto.getCompleteNum() == taskDto.getTotalPhonenum()) {
					//全部已完成
					updateDto.setStatus(String.valueOf(CommonConstant.I_YES));
				}
				addFriendsTaskService.updateAddFriendsTask(updateDto);
			}
		
	}

}
