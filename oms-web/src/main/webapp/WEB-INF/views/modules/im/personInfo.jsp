<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="shop_search">
	<i><span class="back" onclick="clicksBack()">
		返回
	</span></i>
</div>
<div class="shop_list shop_listTwo" id="shop_list">
	<ul class="person_chat">
		<li onclick="material('person',this)" class="active text-center">个人资料</li>
		<c:if test="${ProductType eq 'INVITE'}">
		<c:choose>
			<c:when test="${isCf }">
			<li onclick="material('follow',this)" class="text-center">跟进记录</li>
			</c:when>
			<c:otherwise>
			<li onclick="material('keep',this)" class="text-center">维护记录</li>
			</c:otherwise>
		</c:choose>
		<li onclick="material('success',this)" class="text-center">成单记录</li>
		<li onclick="material('messagePush',this)" class="text-center">推送记录</li>
		</c:if>
		<c:if test="${ProductType eq 'NOINVITE'}">
		<li onclick="material('time',this,'${personMember.memberNoGm }','${personMember.memberNo }')" class="text-center" id="materialTabId">时光长廊</li>
		</c:if>
		
	</ul>
	<!-- 个人资料 -->
	<div class="person_chat_info">
	
					<div class="person_chat_head">
					<c:choose>
						<c:when test="${not empty personMember.headAddress  }">
							<c:choose>
								<c:when test="${!fns:startsWith(personMember.headAddress,'http')}">
									<img alt="" src="${fns:getUploadUrl()}${personMember.headAddress }">
								</c:when>
								<c:otherwise>
									<img alt="" src="${personMember.headAddress }">
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<img alt="" src="${ctxStatic}/admin/images/introduce/file.png">
						</c:otherwise>
					</c:choose>
						<span class="active">${personMember.memberName }</span>
					</div>
					<div class="oth_info">
					<input type="hidden" value="${personMember.noWx }" class="noWx" />
					<input type="hidden" value="${personMember.memberNoGm }" class="memberNoGm" />
					<input type="hidden" value="${personMember.memberNo }" class="memberNo" />
						<ul>
							<li><span>备注</span><input value="${personMember.memberName}" onblur="changeMemName(this)" class="memAppName"  type="text" maxlength="10"/></li>
							<li class="oneLine">
								<span>微信昵称</span><span class="nickNameWx">${personMember.nickNameWx }
								(<c:if test="${personMember.sex=='MALE'}">男</c:if>
								<c:if test="${personMember.sex=='FEMALE'}">女</c:if>
								<c:if test="${personMember.sex=='UNKNOWN'}">未知</c:if>)</span>
							</li>
							<li class="oneLine oneLineTwo"><span>微信号</span><span class="wxNum">
							<c:choose>
								<c:when test="${not empty personMember.noWxAlias  }">
									${personMember.noWxAlias }
								</c:when>
								<c:otherwise>
									${personMember.noWx }
								</c:otherwise>
							</c:choose>
							</span></li>
							<li class="editPhone"><span>手机号</span><span class="phoneNum">${personMember.mobile }</span><input type="text" value="${personMember.mobile }"></li>
							
							<%-- <li class="oneLine product"><span>所需产品</span><span class="productP" title="${personMember.bomName }">${personMember.bomName}</span>
							<textarea rows="" cols="">${personMember.bomName}</textarea>
							</li>
							<li class="oneLine clientSpecial"><span>客户特质</span><span class="clientSpecialP" title="${personMember.clientSpecial }">${personMember.clientSpecial }</span>
							<textarea rows="" cols="">${personMember.clientSpecial}</textarea>
							</li>
							<li class="oneLine oneLineCity">
								<span>所在地区</span>
								<span class="citySpan">${fns:getAreaName(personMember.provinceCode)}${fns:getAreaName(personMember.cityCode)}${fns:getAreaName(personMember.cityAreaCode)}</span>
								<input type="hidden" id="provinceCode" value="${personMember.provinceCode}"/>
								<input type="hidden" id="cidyCode" value="${personMember.cityCode}"/>
								<input type="hidden" id="areaCode" value="${personMember.cityAreaCode}"/>
								<span class="city region">
									<select class="regionSel province">
										 <option value="${personMember.provinceCode}">${fns:getAreaName(personMember.provinceCode)}</option> 
									</select>
									<select class="regionSel shi">
										  <option value="">全部</option>
									</select>
									<select class="regionSel qu">
									<option value="">全部</option>
									</select>
								</span>
							</li>
							<li class="oneLine houses"><span>所在楼盘</span><span class="housesP" title="${personMember.houses }">${personMember.houses }</span>
							<textarea rows="" cols="">${personMember.houses}</textarea>
							</li>
							<li class="oneLine spruce" style="display:flex;"><span>装修进度</span><span class="spruceP" title="${personMember.spruceUp }">
								<c:if test="${personMember.spruceUp=='NODO'}">未交房/未装修</c:if>
								<c:if test="${personMember.spruceUp=='HASDO'}">已装好/添置</c:if>
								<c:if test="${personMember.spruceUp=='DOING'}">装修中</c:if>
								<c:if test="${personMember.spruceUp=='CHANGE'}">换产品</c:if>
								<c:if test="${personMember.spruceUp=='OTHER'}">其他</c:if>
							</span>
							<select>
							  <option value ="" <c:if test="${personMember.spruceUp==''}">selected</c:if> >请选择</option>
							  <option value ="NODO" <c:if test="${personMember.spruceUp=='NODO'}">selected</c:if> >未交房/未装修</option>
							  <option value ="HASDO" <c:if test="${personMember.spruceUp=='HASDO'}">selected</c:if> >已装好/添置</option>
							  <option value ="DOING" <c:if test="${personMember.spruceUp=='DOING'}">selected</c:if>>装修中</option>
							  <option value="CHANGE" <c:if test="${personMember.spruceUp=='CHANGE'}">selected</c:if>>换产品</option>
							  <option value="OTHER" <c:if test="${personMember.spruceUp=='OTHER'}">selected</c:if>>其他</option>
							</select>
							</li>
							<li class="oneLine contrast" style="display:flex;"><span>对比品牌</span><span class="contrastP" title="${personMember.brandCompare }">${personMember.brandCompare }</span>
							<textarea rows="" cols="">${personMember.brandCompare}</textarea>
							</li> --%>
							<li class="oneLine remark_a"><span>备注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="remark_aP" title="${personMember.remark }">${personMember.remark }</span>
							<textarea rows="" cols="">${personMember.remark}</textarea>
							</li>
							<%-- <li class="customerLi">
								<span>客户来源</span>
								<span class="customer">
									<c:forEach items="${memerSources}" var="item">
										<c:if test="${item eq personMember.memberSrc}">${item.name}</c:if>										
									</c:forEach>
								</span>
								<select class="customerIpt">
									<c:forEach items="${memerSources}" var="item">
										<option value="${item}" <c:if test="${item eq personMember.memberSrc}"> selected="selected" </c:if> >${item.name}</option>
									</c:forEach>
								</select>
							</li>
							<li><span>添加方式</span><span class="addType">
								<c:if test="${personMember.addType eq 1}">导购扫码添加</c:if>
			                    <c:if test="${personMember.addType eq 2}">客户扫码添加</c:if>
			                    <c:if test="${personMember.addType eq 3}">导购手动新增</c:if>
			                    <c:if test="${personMember.addType eq 4}">微信自动导入</c:if>
			                    <c:if test="${personMember.addType eq 5}">手机号添加</c:if>
			                    <c:if test="${personMember.addType eq 6}">微信号添加</c:if>
			                    <c:if test="${personMember.addType eq 7}">QQ号添加</c:if>
							</span></li> --%>
<%-- 							<li><span>所属导购</span><span>${personMember.memberNameGm }</span></li> --%>
<%-- 							<li><span>跟进次数</span><span>${personMember.followNum}</span></li> --%>
							<%-- <li><span>维护次数</span><span>${personMember.keepNum}</span></li> --%>
							<%-- <li><span>成单次数</span><span>${personMember.successNum}</span></li> --%>
							<%-- <li><span>录入时间</span><span><fmt:formatDate value="${personMember.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span></li> --%>
							  <li class="periodCycle"><span>朋友圈提醒周期</span><input class="period" value='<c:if test="${personMember.cycle==null}">30</c:if>${personMember.cycle}' class="period"  type="number"/>
								<label class="red">*</label>
							</li>
							<li class="edit" onclick="clickEdit()"><b>编辑</b></li>
							<li class="saveCancel">
								<span class="save" onclick="clickSave()">保存</span>
								<span class="cancel" onclick="clickCancel()">取消</span>
							</li>
						</ul>
					</div>
				</div>
	<!-- 跟进记录、成单记录、维护记录 -->
	<div class="record" style="padding-top:0;">
		<div class="follow">
			<c:forEach items="${CfList}" var="item1" varStatus="sta">
				<c:if test="${sta.index==0 }">
					<c:set var="floneTime">  
					    <fmt:formatDate value="${item1.createDate }" pattern="yyyy-MM-dd " type="date"/>  
					</c:set>
					<div class="recordTime text-center">
						<fmt:formatDate value="${item1.createDate }" pattern="yyyy/MM/dd " type="date"/>
					</div>
				</c:if>
				<c:if test="${sta.index>0 }">
					<c:set var="flprevTime">  
					    <fmt:formatDate value="${item1.createDate }" pattern="yyyy-MM-dd " type="date"/>  
					</c:set>
					<c:if test="${floneTime !=flprevTime }">
						<c:set var="floneTime">  
						    <fmt:formatDate value="${item1.createDate }" pattern="yyyy-MM-dd " type="date"/>  
						</c:set>
						<div class="recordTime text-center">
							<fmt:formatDate value="${item1.createDate }" pattern="yyyy/MM/dd " type="date"/>
						</div>
					</c:if>
				</c:if>
				<div class="recordDetail">
					<div class="bgbox">
						<div class="bgbox_time"><fmt:formatDate value="${item1.followTime }" pattern="HH:mm" type="date"/></div>
						<div class="boundary">
							<span class="cicle"></span>
							<span class="cicle2"></span>
						</div>	
						<div class="recordInfo">
							<c:if test="${item1.comTaskType eq 'SYSTEM'}"><img alt="" src="${ctxStatic}/admin/images/imImages/sys.png"></c:if>
							<c:if test="${item1.comTaskType eq 'SHOP'}"><img alt="" src="${ctxStatic}/admin/images/imImages/shop.png"></c:if>
							<c:if test="${item1.comTaskType eq 'SMS'}"><img alt="" src="${ctxStatic}/admin/images/imImages/sms.png"></c:if>
							<c:if test="${item1.comTaskType eq 'WECHAT'}"><img alt="" src="${ctxStatic}/admin/images/imImages/wxLing.png"></c:if>
							<c:if test="${item1.comTaskType eq 'PHONE'}"><img alt="" src="${ctxStatic}/admin/images/imImages/customerLine.png"></c:if>
							<c:if test="${item1.comTaskType eq 'GROUP'}"><img alt="" src="${ctxStatic}/admin/images/imImages/group.png"></c:if>
							<c:if test="${item1.comTaskType eq 'CLIENT_EXP'}"><img alt="" src="${ctxStatic}/admin/images/imImages/client_exp.png"></c:if>
							<c:if test="${item1.comTaskType eq 'REMIND'}"><img alt="" src="${ctxStatic}/admin/images/imImages/kehutixing.png"></c:if>
							<c:if test="${item1.comTaskType eq 'INVITE'}"><img alt="" src="${ctxStatic}/admin/images/imImages/invite.png"></c:if>
							<c:if test="${item1.comTaskType eq 'FIRST_INTR'}"><img alt="" src="${ctxStatic}/admin/images/imImages/first_intr.png"></c:if>
							<c:if test="${item1.comTaskType eq 'COM_TASK'}"><img alt="" src="${ctxStatic}/admin/images/imImages/goutong.png"></c:if>
							<c:if test="${item1.comTaskType eq 'GUID_PM'}"><img alt="" src="${ctxStatic}/admin/images/imImages/yindao.png"></c:if>
							<div class="rdInDetail">
								<p class="clb tt">
									<c:choose>
										<c:when test="${item1.comTaskType eq 'CLIENT_EXP' && item1.clientExpNum==null}">
											未到店
										</c:when>
										<c:when test="${item1.comTaskType eq 'INVITE' && item1.clientInviteNum==null}">
											未应邀
										</c:when>
										<c:otherwise>
											${item1.comTaskTypeName}
										</c:otherwise>
									</c:choose>
								</p>
								 <c:choose>
								 	<c:when test="${item1.comTaskType =='INVITE'}">
								 		<c:if test="${not empty item1.followInfo}">
						        			<p><span class="clb">跟进结果：</span><span>${item1.followInfo}</span></p>
						        		</c:if>
						        		<c:if test="${not empty item1.notDealReason}">
											<p><span class="clb">未成单原因：</span><span>${item1.notDealReason }</span></p>
										</c:if>
										<c:if test="${not empty item1.nextDate && item1.clientInviteNum!=null}">
											<p ><span class="clb">预计到店时间：</span><span><fmt:formatDate value="${item1.nextDate}" pattern="yyyy-MM-dd"/></span></p>
										</c:if>
								 	</c:when>
								 	<c:when test="${item1.comTaskType =='WECHAT' ||item1.comTaskType =='SMS'}">
								 		<c:if test="${not empty item1.followInfo}">
							        		<p><span class="clb">跟进结果：</span><span>${item1.followInfo}</span></p>
							        	</c:if>
								 	</c:when>
								 	<c:otherwise>
								 	 	<c:if test="${not empty item1.followInfo }">
						        			<p>
						        			<c:if test="${item1.followType !='SYSTEM' }">
						        				<span class="clb">跟进结果：</span>
						        			</c:if>
						        				<span>${item1.followInfo}</span></p>
						        		</c:if>
						        		<c:if test="${not empty item1.notDealReason}">
											<p><span class="clb">未成单原因：</span><span>${item1.notDealReason }</span></p>
										</c:if>
										<c:if test="${not empty item1.nextDate && not empty item1.taskName}">
											<p><span class="clb">下一次跟进时间：</span><span><fmt:formatDate value="${item1.nextDate}" pattern="yyyy-MM-dd"/></span></p>
										</c:if>
										<c:if test="${not empty item1.taskName && not empty item1.nextDate}">
											<p ><span class="clb">下一次任务类型：</span><span>${item1.taskName}</span> </p>
										</c:if>
										<c:if test="${item1.comTaskType eq 'CLIENT_EXP' && item1.clientExpNum>0 }">
											<p style="cursor: pointer;" onclick="followMore('${item1.code }')">查看更多</p>
										</c:if>
								 	 </c:otherwise>
								 </c:choose>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		
		
		<div class="success">
			<c:forEach items="${buyRecord}" var="item1" varStatus="sta">
				<c:if test="${sta.index==0 }">
					<c:set var="sconeTime">  
					    <fmt:formatDate value="${item1.endDate }" pattern="yyyy-MM-dd " type="date"/>  
					</c:set>
					<div class="recordTime text-center">
						<fmt:formatDate value="${item1.endDate }" pattern="yyyy/MM/dd " type="date"/>
					</div>
				</c:if>
				<c:if test="${sta.index>0 }">
					<c:set var="scprevTime">  
					    <fmt:formatDate value="${item1.endDate }" pattern="yyyy-MM-dd " type="date"/>  
					</c:set>
					<c:if test="${sconeTime !=scprevTime }">
						<c:set var="sconeTime">  
						    <fmt:formatDate value="${item1.endDate }" pattern="yyyy-MM-dd " type="date"/>  
						</c:set>
						<div class="recordTime text-center">
							<fmt:formatDate value="${item1.endDate }" pattern="yyyy/MM/dd " type="date"/>
						</div>
					</c:if>
				</c:if>
				<div class="recordDetail">
					<div class="bgbox">
						<div class="bgbox_time"><fmt:formatDate value="${item1.endDate }" pattern="HH:mm" type="date"/></div>
						<div class="boundary">
							<span class="cicle"></span>
							<span class="cicle2"></span>
						</div>	
						<div class="recordInfo">
							<img alt="" src="${ctxStatic}/admin/images/imImages/buySuccess.png">
							<div class="rdInDetail">
								<p class="clb tt">成功购买</p>
								<p class="oneLine"><span class="clb">成单金额：</span><span><fmt:formatNumber value="${item1.orderAmount/100 }" pattern="#,##0.00"/></span></p>
								<p class="oneLine blclo">跟进${item1.followNum }次成单</p>
							</div>
						</div>
					</div>
				</div>
				 
				
			</c:forEach>
		</div>
		<div class="keep">
				<c:forEach items="${CkList}" var="item1" varStatus="sta">
				<c:if test="${sta.index==0 }">
					<c:set var="ckoneTime">  
					    <fmt:formatDate value="${item1.createDate }" pattern="yyyy-MM-dd " type="date"/>  
					</c:set>
					<div class="recordTime text-center">
						<fmt:formatDate value="${item1.createDate }" pattern="yyyy/MM/dd " type="date"/>
					</div>
				</c:if>
				<c:if test="${sta.index>0 }">
					<c:set var="ckprevTime">  
					    <fmt:formatDate value="${item1.createDate }" pattern="yyyy-MM-dd " type="date"/>  
					</c:set>
					<c:if test="${ckoneTime !=ckprevTime }">
						<c:set var="ckoneTime">  
						    <fmt:formatDate value="${item1.createDate }" pattern="yyyy-MM-dd " type="date"/>  
						</c:set>
						<div class="recordTime text-center">
							<fmt:formatDate value="${item1.createDate }" pattern="yyyy/MM/dd " type="date"/>
						</div>
					</c:if>
				</c:if>
				<div class="recordDetail">
					<div class="bgbox">
						<div class="bgbox_time"><fmt:formatDate value="${item1.createDate }" pattern="HH:mm" type="date"/></div>
						<div class="boundary">
							<span class="cicle"></span>
							<span class="cicle2"></span>
						</div>	
						<div class="recordInfo">
							<c:if test="${ item1.keepType eq 'SYSTEM'}"><img alt="" src="${ctxStatic}/admin/images/imImages/sys.png"></c:if>
							<%-- <c:if test="${item1.keepType eq 'SHOP'}"><img alt="" src="${ctxStatic}/admin/images/imImages/shop.png"></c:if>
							<c:if test="${item1.keepType eq 'SMS'}"><img alt="" src="${ctxStatic}/admin/images/imImages/sms.png"></c:if>
							<c:if test="${item1.keepType eq 'WECHAT'}"><img alt="" src="${ctxStatic}/admin/images/imImages/wxLing.png"></c:if>
							<c:if test="${item1.keepType eq 'PHONE'}"><img alt="" src="${ctxStatic}/admin/images/imImages/customerLine.png"></c:if>
							<c:if test="${item1.keepType eq 'GROUP'}"><img alt="" src="${ctxStatic}/admin/images/imImages/group.png"></c:if>
							<c:if test="${item1.keepType eq 'CLIENT_EXP'}"><img alt="" src="${ctxStatic}/admin/images/imImages/client_exp.png"></c:if>
							<c:if test="${item1.keepType eq 'REMIND'}"><img alt="" src="${ctxStatic}/admin/images/imImages/kehutixing.png"></c:if>
							<c:if test="${item1.keepType eq 'INVITE'}"><img alt="" src="${ctxStatic}/admin/images/imImages/invite.png"></c:if>
							<c:if test="${item1.keepType eq 'FIRST_INTR'}"><img alt="" src="${ctxStatic}/admin/images/imImages/first_intr.png"></c:if> --%>
							<c:if test="${item1.keepType!='SYSTEM'}"><img alt="" src="${ctxStatic}/admin/images/imImages/goutong.png"></c:if>
							<div class="rdInDetail">
								<p class="clb tt">
									<c:choose>
										<c:when test="${item1.keepType eq 'SYSTEM'}">${item1.keepTypeName}</c:when>
										<c:otherwise>
										沟通任务
										</c:otherwise>
									</c:choose>
									
								</p>
								<p class="oneLine">
									维护内容：${item1.keepContent }
								</p>
								<c:if test="${not empty  item1.nextDate }">
									<p class="oneLine"><span class="clb">下次维护时间：</span><span><fmt:formatDate value="${item1.nextDate }" pattern="yyyy-MM-dd HH:mm" type="date"/></span></p>
									<p class="oneLine"><span class="clb">下次维护类型：</span><span>沟通任务</span></p>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="messagePush">
			<input type="hidden" value="${messagePush.pageNo}" id="PushpageNo">
			<c:forEach items="${messagePush.rows}" var="item1" varStatus="sta">
				<c:if test="${sta.index==0 }">
					<c:set var="oneTime">  
					    <fmt:formatDate value="${item1.pushDate }" pattern="yyyy-MM-dd " type="date"/>  
					</c:set>
					<div class="recordTime text-center">
						<fmt:formatDate value="${item1.pushDate }" pattern="yyyy/MM/dd " type="date"/>
					</div>
				</c:if>
				<c:if test="${sta.index>0 }">
					<c:set var="prevTime">  
					    <fmt:formatDate value="${item1.pushDate }" pattern="yyyy-MM-dd " type="date"/>  
					</c:set>
					<c:if test="${oneTime !=prevTime }">
						<c:set var="oneTime">  
						    <fmt:formatDate value="${item1.pushDate }" pattern="yyyy-MM-dd " type="date"/>  
						</c:set>
						<div class="recordTime text-center">
							<fmt:formatDate value="${item1.pushDate }" pattern="yyyy/MM/dd " type="date"/>
						</div>
					</c:if>
				</c:if>
				<div class="recordDetail">
					<div class="bgbox">
						<div class="bgbox_time"><fmt:formatDate value="${item1.pushDate }" pattern="HH:mm" type="date"/></div>
						<div class="boundary">
							<span class="cicle"></span>
							<span class="cicle2"></span>
						</div>	
						<div class="recordInfo">
									<c:if test="${item1.msgType eq '1'}"><img alt="" src="${ctxStatic}/admin/images/imImages/experience.png"></c:if>
									<c:if test="${item1.msgType eq '2'}"><img alt="" src="${ctxStatic}/admin/images/imImages/customerLine.png"></c:if>
									<c:if test="${item1.msgType eq '3'}"><img alt="" src="${ctxStatic}/admin/images/imImages/sms.png"></c:if>
							<div class="rdInDetail">
								<p class="clb tt">
									<%-- <c:if test="${item1.msgType eq '1'}">优惠券推送</c:if>
									<c:if test="${item1.msgType eq '2'}">图文推送</c:if>
									<c:if test="${item1.msgType eq '3'}">短信推送</c:if> --%>
									${item1.msgTitle}
								</p>
								<%request.setAttribute("vEnter", "\n");%>
								<p>${fn:replace(item1.msgContent, vEnter, '<br>')}</p>
								<p>推送时间：<fmt:formatDate value='${item1.pushDate}' pattern='yyyy-MM-dd hh:mm:ss' type='date'/></p>
							</div>
						</div>
					</div>
				</div>
				<c:if test="${sta.last && fn:length(messagePush.rows)>=20}">
					<div class="more" onclick="More()">点击加载更多</div>
					<input type="hidden" value="<fmt:formatDate value='${item1.pushDate }' pattern='yyyy-MM-dd' type='date'/>" id="pushDate">
					<input type="hidden" value="${item1.merchantNo}" id="PumerchantNo">
					<input type="hidden" value="${item1.memberNoGm}" id="PumemberNoGm">
				</c:if>
			</c:forEach>
			
		</div>
		
		<div id="timeHome">
		
		</div>
		
	</div>
</div>

<div class="bigImg" id="bigImg" onclick="$('#bigImg').hide()">
	<img alt="" src="" style="height:67% !important">
</div>
	
<!-- 到店体验更多详情开始 -->
<div class="inShop"  onclick="om(event)">
	<div class="inShopContent">
	</div>
</div>
<!-- 到店体验更多详情结束 -->
<script>
$(document).ready(function() {

	$(".period").blur(function(){
		var self = this;
		var params=window.location.search.substring(1).split("&");
		var memberNo=$(".shop_nav .person_chat_info .memberNo").val();
		var cycle=$(this).val();
		if(/^[1-9]\d*$/.test(cycle)){
			$.ajax({
		        type:"POST",
		        url:$('#ctx').val()+"/im/updateFriendPointCycle",
		        data:{cycle:cycle,memberNo:memberNo},
		        dataType:'text',
		        success:function(result){
		        	if(result=="success"){	
		        	
		        	}
		        }
		    });
			$(this).next().css("display","none");
		}else{
			$(this).next().css("display","inline-block");
		}
		
		
	});
});

</script>
