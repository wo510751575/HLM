<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>接入客户</title>
<meta name="decorator" content="default" />
<meta http-equiv=”content-type” content=”text/html; charset=UTF-8″ />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link href="${ctxStatic}/admin/css/im.css?v=11" type="text/css" rel="stylesheet" />
</head>
<body>
   <%--  <div class="loadFlag" id="loadFlayTwo" onclick="$('#loadFlayTwo').hide();">
		<img alt="" src="${ctxStatic}/admin/images/imImages/load.gif">
	</div> --%>
	<input type="hidden" value="${ctx}" id="ctx"> 
	<input type="hidden" value="${fns:getUploadUrl()}" id="UploadUrl"> 
	<input type="hidden" value="${ctxStatic}" id="ctxStatic"> 
	<input type="hidden" value="${findGuidMemberReturn.noWx}" id="guideWxId"> 
	<input type="hidden" value="${personMember.baseCode}" id="baseCode">
	<c:choose>
		<c:when test="${findGuidMemberReturn.headAddress !='' && findGuidMemberReturn.headAddress!=null}">
			<c:if test="${!fns:startsWith(findGuidMemberReturn.headAddress,'http')}">
				<input type="hidden" value="${fns:getUploadUrl()}${findGuidMemberReturn.headAddress }" id="gudHeadAddress"> 
			</c:if>
			<c:if test="${fns:startsWith(findGuidMemberReturn.headAddress,'http')}">
				<input type="hidden" value="${findGuidMemberReturn.headAddress }" id="gudHeadAddress"> 
			</c:if>
		</c:when>
		<c:otherwise>
			<input type="hidden" value="${fns:getUploadUrl()}/logo/guid_default_head.png" id="gudHeadAddress"> 
		</c:otherwise>
	</c:choose>
	
	<div class="imcontent" id="chat">
	<input type="hidden" value="ybm" id="LEFTNAVTYPEID"/><!-- 左边菜单类型 -->
	<%@ include file="/WEB-INF/views/modules/im/leftNav.jsp"%>
		<div class="shop_nav hideTelescopicButton">
			<div class="shop_search">
				<span class="back" onclick="window.location.href='${ctx}/member/forecastName/list';window.localStorage.removeItem('prechatstart');window.localStorage.removeItem('preChatTimeEnd');">
					返回
				</span>
			</div>
			<div class="shop_list" id="shop_list">
				<ul class="person_chat">
					<li onclick="material('person',this)" class="active text-center">报名信息</li>
					
				</ul>
				<!-- 个人资料 --> 
				<div class="person_chat_info">
					<div class="oth_info">
					<input type="hidden" value="${personMember.noWx }" class="noWx" />
					<input type="hidden" value="${forecastName.code }" id="travelCode" />
						<ul>
							<li class="oneLine"><span>姓名</span><input value="${forecastName.name }" disabled="disabled" class="Name" type="text" maxlength="10"></li>
							<li ><span>电话</span><input disabled="disabled"  type="number" class="memMobile" maxlength="12" value="${forecastName.mobile }"></li>
							<li class="oneLine"><span>性别</span><select  class="sexChose" disabled="disabled">
								<option value="MALE"  <c:if test="${'MALE' eq forecastName.sex}">selected="selected"</c:if> >男</option>
								<option value="FEMALE" <c:if test="${'FEMALE' eq forecastName.sex}">selected="selected"</c:if>>女</option>
								<option value="UNKNOWN" <c:if test="${'UNKNOWN' eq forecastName.sex}">selected="selected"</c:if>>未知</option>
							</select></li>
							<li><span>意向旅游地</span><input disabled="disabled"  value="${forecastName.intentAddress }" class="wantTravel" type="text" maxlength="20"></li>
							<li><span>出游人数</span><input disabled="disabled"  value="${forecastName.personCount }" class="travelNum" type="number" maxlength="3"></li>
							<li><span>出发地</span><input disabled="disabled"  value="${forecastName.startAddress }" class="travelSite" type="text" maxlength="20"></li>
							<li><span>费用预算</span><input disabled="disabled"  value="${forecastName.budget/100 }" class="travelMoney" type="text" maxlength="20"></li>
							<li><span>出游时长</span><input disabled="disabled"  value="${forecastName.tourtime }" class="travelDul" type="text" maxlength="20"></li>
							<li><span>行程标准</span><input disabled="disabled"  value="${forecastName.travelStandard }" class="travelDist" type="text" maxlength="50"></li>
							<li><span>曾出游地</span><input disabled="disabled"  value="${forecastName.onceAddress }" class="travelBefore" type="text" maxlength="20"></li>
							<li><span style=" width: 26%;">备注</span>
							<textarea class="remark" rows="" cols="" disabled="disabled" maxlength="300">${forecastName.remark }</textarea>
							</li>
							<li class="edit edtbtn" id="edit"><b>编辑</b></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="con_nav">
			<div class="con_search">
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
			</div>
			<div class="person">
				<label class="sign"></label>
			</div>
			
			<div class="sendInfoDetail">
				<div class="emoticon">
					<ul>
						<li><img alt="" title="表情" class="smile" src="${ctxStatic}/admin/images/imImages/smile.png" onclick="showEmoji()" /></li>
						<li>
							<img alt="" class="pic" src="${ctxStatic}/admin/images/imImages/pic.png">
							<input type="file" title="图片" name="uploadFile" id="file1" accept="image/jpeg,image/jpg,image/png" onchange="upImg('file1',this)" class="upload"/>
						</li>
						<li><img title="素材" alt="" class="file" src="${ctxStatic}/admin/images/imImages/file.png" onclick="huodong('huodong1')"></li>
						<li><img title="活动" alt="" class="act" src="${ctxStatic}/admin/images/imImages/act.png" onclick="huodong('huodong2')"></li>
						<li><img title="优惠券" alt="" class="youhuiquan" src="${ctxStatic}/admin/images/imImages/youhuiquan.png" onclick="huodong('huodong3')"></li>
						<li><img title="名片" alt="" class="personal" src="${ctxStatic}/admin/images/imImages/personal.png" onclick="personCard()"></li>
						<li><img title="位置" alt="" class="map" src="${ctxStatic}/admin/images/imImages/map.png" onclick="huodong('huodong4')"></li>
						<li><img title="话术" alt="" class="chat" src="${ctxStatic}/admin/images/imImages/chat.png" onclick="showWord()">
							<div class="words">
								
							</div>
						</li>
				<!-- 		<li class="trusteeshipIcon" onclick="clickTrusteeship()"><img title="托管" alt="" class="diannao" src="${ctxStatic}/admin/images/imImages/diannao.png" onclick="">
							
						</li> -->
						<div class="trusteeship">
							<div class="trusteeship-left">
								<i></i>
								<span>您的账号已经开启托管功能，系统会自动为您回复！</span>
							</div>
							<div class="trusteeship-right" onclick="exitTrusteeship()">退出托管</div>	
						</div>
						<c:if test="${ eshop.eshopStatus && not empty eshop.eshopUrl }">
						<li><img title="商城" alt="" class="shopIcon" src="${ctxStatic}/admin/images/imImages/shopIcon.png" onclick="sendShop('${eshop.eshopUrl}')"></li>
						</c:if>
						
					</ul>
				</div>
				<div class="sendInfo" id="txtContent"  contentEditable="true" >
				
				</div>
				
				<div class="send">
					<lable class="desc">按下Ctrl+Enter换行</lable>
					<span onclick="sendSub(1)">发送</span>
				</div>
			</div>
		</div>
		
		<!-- <div class="redEnvelopes"> 发红包
			 <div class="redEnvelopes-left"></div>
			 <div class="redEnvelopes-right">
			 	<h2>恭喜发财，大吉大利</h2>
			 	<p>¥2.00</p>
			 </div>		
		</div> -->
		<div class="zhegai">
			<!-- 表情 -->
			<div class="emoji">
				<div class="showEmoji">
					
				</div>
				<div class="emojiList">
					<div class="emojitab">
						<ul>
						</ul>
					</div>
					<div class="emojiMore">
						<img src="${ctxStatic}/admin/images/imImages/arrowqa.png" class="prev" onclick="prevTab()">
						<img src="${ctxStatic}/admin/images/imImages/arrowqa1.png" class="next" onclick="nextTab()">
					</div>
				</div>
			</div>
			
			<!-- 素材 -->
			<div class="huodong  huodong1">
				<div class="hd_title text-center">素材</div>
				<div class="hd_con">
					<div class="hd_list">
						<div class="hdtitle">
							<ul>
								<c:choose>
									<c:when test="${not empty vrMaterialType }">
										<li class="active" id="VR_mate"  onclick="tree('VR_mate',this)">VR素材</li>
										<li id="common_mate"  onclick="tree('common_mate',this)">公共素材</li>
									</c:when>
									<c:otherwise>
										<li id="common_mate"  class="active" style="display: bock;"  onclick="tree('common_mate',this)">公共素材</li>
									</c:otherwise>
								</c:choose>
								<li id="personnal" onclick="tree('personnal',this)">个人素材</li>
							</ul>
						</div>
						<c:if test="${not empty vrMaterialType  }">
							<input type="hidden" value="${shopType}" id="shopType">
							<div class="VR_mate">
								<div class="vr_style">
									<c:forEach items="${vrMaterialType }" var="vrMeType">
										<div class="vr_style_type">
											<p class="vr_style_name active"> ${vrMeType.typeName }</p>
											<select>
												<option value="">全部</option>
												<c:forEach items="${vrMeType.list }" var="vrMeTypelist">
													<option class="oneLine" value="${vrMeTypelist.code }">${vrMeTypelist.categoryName }</option>
												</c:forEach>
											</select>
										</div>
									
									</c:forEach>
								</div>
								<div class="vr_info">
								</div>
								
							</div>
						</c:if>
						<div class="common_mate">
							
						</div>
						<div class="personnal">
						</div>
						
					</div>
					<div class="hd_detail">
						<div class="hd_detail_th text-center">素材详情</div>
						<!-- <div class="hd_detail_tbd"  id='hd1'></div> -->
						<iframe class="hd_detail_tbd" id='hd1' ></iframe>
						<div class="send">
							<span class="hdSend" onclick="hdSend('huodong1')">发送</span>
						</div>
					</div>
				</div>
			</div>
			
			<!-- 活动 -->
			<div class="huodong  huodong2">
				<div class="hd_title text-center">活动</div>
				<div class="hd_con">
					<div class="hd_list">
					
					</div>
					<div class="hd_detail">
						<div class="hd_detail_th text-center">活动详情</div>
						<!-- <div class="hd_detail_tbd"  id='target'>
						
						</div> -->
						<iframe class="hd_detail_tbd" id='target' ></iframe>
						<div class="send">
							<span class="hdSend" onclick="hdSend('huodong2')">发送</span>
						</div>
					</div>
				</div>
			</div>
			
			<!-- 优惠券 -->
			<div class="huodong  huodong3">
				<div class="hd_title text-center">优惠券</div>
				<div class="hd_con">
					<div class="hd_list" >
						
					</div>
					<div class="hd_detail">
						<div class="hd_detail_th text-center">优惠券详情</div>
						<!-- <div class="hd_detail_tbd"  id='hd3' style="background: #21b964;"></div> -->
						<iframe class="hd_detail_tbd"  id='hd3' src=""></iframe>
						<div class="send">
							<span class="hdSend" onclick="hdSend('huodong3')">发送</span>
						</div>
					</div>
				</div>
			</div>
			
			<!-- 位置 -->
				<div class="huodong huodong4">
					<div class="search">						
						<iframe id="mapPage" width="100%" height="100%" frameborder=0
              				src="">
						</iframe>
					</div>
				</div>
		</div>
		<div class="yulan">
			<div class="top_yulan text-center" >
				<!-- <div id="yulan"></div> -->
				<iframe id="yulan" src=""></iframe>
			</div>
		</div>
		
		<!-- 话术 -->
		<div class="moreWordBox">
			<div class="wordsContent">
				<div class="top">
					<span class="n">话术类型</span>
				</div>
				<div id="talkContent">
				</div>
				<div class="button-list" style="text-align:right; margin-right:15px;">
		            <input type="button" value="确定" class="save g-btn" onclick="words(event,1)">
		            <input type="button" value="取消" class="doNow b-btn" onclick="$('.moreWordBox').hide()">
		        </div>
			</div>
		</div>
		
	</div>
	<div class="loadFlag" id="loadFlag" onclick="$('#loadFlag').hide();window.clearInterval(intCycle);">
		<img alt="" src="${ctxStatic}/admin/images/imImages/load.gif">
	</div>

	<div class="bigImg" id="bigImg" onclick="$('#bigImg').hide()">
		<img alt="" src="" style="height:67% !important">
	</div>
	
	<script src="${ctxStatic}/admin/js/im.js?v=11" type="text/javascript"></script>
	<script src="${ctxStatic}/admin/js/im/prechat.js?v=11" type="text/javascript"></script>
	<script src="${ctxStatic}/admin/js/emojiChange.js?v=11" type="text/javascript"></script>
	<script src="${ctxStatic}/admin/js/emojiChangeFan.js?v=11" type="text/javascript"></script>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js?v=11"></script>
	<script src="${ctxStatic}/admin/js/im/imGlobal.js?v=11" type="text/javascript"></script>
	<script src="http://pv.sohu.com/cityjson?ie=utf-8"></script> 
	<script charset="utf-8" src="http://map.qq.com/api/js?v=6.exp"></script>
	<script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
	<script src="${ctxStatic}/admin/js/im/exif.js" type="text/javascript"></script>
</body>
</html>