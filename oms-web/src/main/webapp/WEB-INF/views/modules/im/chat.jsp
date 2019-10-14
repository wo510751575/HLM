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
	<input type="hidden" value="10" id="limit">
	<input type="hidden" value="0" id="start">
	<input type="hidden" value="${fns:getUploadUrl()}" id="UploadUrl"> 
	<input type="hidden" value="${ctxStatic}" id="ctxStatic"> 
	<input type="hidden" value="${findShopTerminalReturn.noWx}" id="guideWxId"> 
	<input type="hidden" value="${personMember.baseCode}" id="baseCode">
	<input type="hidden" value="${findShopTerminalReturn.headurl}" id="gudHeadAddress"> 
	
	<div class="imcontent" id="chat">
	<input type="hidden" value="rp" id="LEFTNAVTYPEID"/><!-- 左边菜单类型 -->
	<%@ include file="/WEB-INF/views/modules/im/leftNav.jsp"%>
		<div class="shop_nav hideTelescopicButton">
			<div class="shop_search">
				<span class="back" onclick="ImIndex()">
					返回
				</span>
			</div>
			<div class="shop_list" id="shop_list">
				<ul class="person_chat">
					<li onclick="material('person',this)" class="active text-center">个人资料</li>
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
						<ul>
							<li>
								<span>备注名</span>
								<span class="remarkName" style="overflow: inherit;text-overflow: clip;white-space: normal;">${personMember.memberName}</span>
								<input style="display: none" value="${personMember.memberName}" class="memAppName"  type="text" maxlength="16"/></li>
							<li class="oneLine">
								<span>微信昵称</span><span class="nickNameWx">${personMember.nickNameWx }
								</span><span class="peSex">(<c:if test="${personMember.sex=='MALE'}">男</c:if>
								<c:if test="${personMember.sex=='FEMALE'}">女</c:if>
								<c:if test="${personMember.sex=='UNKNOWN'}">未知</c:if>)</span>
								
							</li>
							<li class="oneLine oneLineTwo"><span>微信号</span>
								<span class="wxNum">${not empty personMember.noWxAlias?personMember.noWxAlias:personMember.noWx}</span>
							</li>
							<li class="editPhone">
								<span>手机号</span>
								<span class="phoneNum">${personMember.mobile }</span>
								<input style="display: none" type="text" value="${personMember.mobile }">
							</li>
							<li class="oneLine remark_a">
								<span>补充资料</span>
								<span class="remark_aP" style="height: auto;overflow: inherit;text-overflow: clip;white-space: normal;" title="${personMember.remark }">${personMember.remark }</span>
								<textarea rows="" cols="" maxlength="50">${personMember.remark}</textarea>
							</li>
							  <li class="periodCycle">
							  	<span>朋友圈提醒周期</span>
							  	<span class="periodSpan">${empty personMember.cycle?'30':personMember.cycle}</span>
							  	<input style="display: none" class="period" value="${empty personMember.cycle?'30':personMember.cycle}" class="period"  type="number"/>
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
						<li><img title="名片" alt="" class="personal" src="${ctxStatic}/admin/images/imImages/personal.png" onclick="showAllCard()">
							<div class="allCard">
								<dl>
<!-- 									<dd onclick="personCard()" class="twoLine">我的名片</dd> -->
									<dd onclick="personCardOther(0)" class="twoLine">联系人名片</dd>
									<dd onclick="personCardOther(8)" class="twoLine">公众号名片</dd>
								</dl>
							</div>
						</li>
						<li><img title="位置" alt="" class="map" src="${ctxStatic}/admin/images/imImages/map.png" onclick="huodong('huodong4')"></li>
						<li><img title="话术" alt="" class="chat" src="${ctxStatic}/admin/images/imImages/chat.png" onclick="showWord()">
							<div class="words">
								
							</div>
						</li>
				<!-- 	<li class="trusteeshipIcon" onclick="clickTrusteeship()"><img title="托管" alt="" class="diannao" src="${ctxStatic}/admin/images/imImages/diannao.png" onclick="">
						</li> -->
						<li>
							<img alt="" class="videoChat" src="${ctxStatic}/admin/images/imImages/videoChat.png">
							<input type="file" title="视频" name="uploadFile" id="videoFile" accept="video/mp4" onchange="upVideo('videoFile',this)" class="upload"/>
							<video style="display:none;" controls="controls" id="aa" oncanplaythrough="myFunction(this)"></video>
						</li>
						<li><img title="小程序" alt="" class="xcxIcon_arr" src="${ctxStatic}/admin/images/imImages/xcxIcon.png" onclick="showXCX(1)">
						</li>
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
		<!-- 联系人名片或公众号名片 -->
		<div class="personCardBox">
			<div class="personCardContent">
				<div class="top">
					<input type="hidden" value="0" class="certFlag"/>
					<span class="n">联系人名片列表</span>
					<div class="querymp">
						<div class="mp">
							<span>名称：</span>
							<input type="text" placeholder="名称" value=""/>
						</div>
						<input type="button" value="查询" class="doquery" onclick="queryCard()"/>
					</div>
				</div>
				<div id="cardContent">
					<ul></ul>
				</div>
				<div class="pagination">
					<p>总计<span class="total"></span>条记录，共<span class="totalPage"></span>页，当前第 <input value="1"  class="pageShow" type="tel"> 页，每页
						<select>
							<option selected="selected">10</option>
							<option>20</option>
							<option>30</option>
							<option>50</option>
						</select>
					条</p>
					<img alt="" onclick="changeCardPage('first')" class="first" src="${ctxStatic}/admin/images/imImages/arrow_back1.png">
					<img alt="" onclick="changeCardPage('prev')" class="prev" src="${ctxStatic}/admin/images/imImages/arrow_double_b1.png">
					<img alt="" onclick="changeCardPage('next')" class="next" src="${ctxStatic}/admin/images/imImages/arrow_doublef1.png">
					<img alt="" onclick="changeCardPage('last')" class="last" src="${ctxStatic}/admin/images/imImages/arrow_font1.png">	
				</div>
				<div class="button-list" style="text-align:right; margin-right:15px;">
		            <input type="button" value="确定" class="save g-btn" onclick="sendSub(42,event)">
		            <input type="button" value="取消" class="doNow b-btn" onclick="$('.personCardBox').hide()">
		        </div>
			</div>
		</div>
		
		<!-- 小程序 -->
		<div class="wxSmallProgramBox">
			<div class="wxSmallProgramContent">
				<div class="top">
					<input type="hidden" value="0" class="certFlag"/>
					<span class="n">小程序列表</span>
					<div class="queryXcx">
						<div class="xcxYhu">
							<span>类型：</span>
							<select id="queryType">
							</select>
						</div>
						<div class="xcxM">
							<span>名称：</span>
							<input type="text" placeholder="小程序名称" value=""/>
						</div>
						<input type="button" value="查询" class="doquery" onclick="queryXCX()"/>
					</div>
				</div>
				<div id="wxSmallContent">
					<ul></ul>
				</div>
				<div class="pagination">
					<p>总计<span class="total"></span>条记录，共<span class="totalPage"></span>页，当前第 <input value="1"  class="pageShow" type="tel"> 页，每页
						<select>
							<option selected="selected">10</option>
							<option>20</option>
							<option>30</option>
							<option>50</option>
						</select>
					条</p>
					<img alt="" onclick="changeSmallProPage('first')" class="first" src="${ctxStatic}/admin/images/imImages/arrow_back1.png">
					<img alt="" onclick="changeSmallProPage('prev')" class="prev" src="${ctxStatic}/admin/images/imImages/arrow_double_b1.png">
					<img alt="" onclick="changeSmallProPage('next')" class="next" src="${ctxStatic}/admin/images/imImages/arrow_doublef1.png">
					<img alt="" onclick="changeSmallProPage('last')" class="last" src="${ctxStatic}/admin/images/imImages/arrow_font1.png">	
				</div>
				<div class="button-list" style="text-align:right; margin-right:15px;">
		            <input type="button" value="确定" class="save g-btn" onclick="sendSub(494,event)">
		            <input type="button" value="取消" class="doNow b-btn" onclick="$('.wxSmallProgramBox').hide()">
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
	
	<!-- 到店体验更多详情开始 -->
	<div class="inShop" onclick="om(event)">
		<div class="inShopContent">
			
		</div>
	</div>
	<!-- 到店体验更多详情结束 -->
	<style type="text/css">
			.chexiaoInfo{padding: 5px 10px;border:1px solid #d1d1d1;cursor: pointer;border-radius: 6px;margin-left: 10px;}
	.chexiaoInfo:hover{background-color: #d1d1d1;}
	</style>
	<script src="${ctxStatic}/admin/js/im.js?v=11" type="text/javascript"></script>
	<script src="${ctxStatic}/admin/js/chat.js?v=11" type="text/javascript"></script>
	<script src="${ctxStatic}/admin/js/emojiChange.js?v=11" type="text/javascript"></script>
	<script src="${ctxStatic}/admin/js/emojiChangeFan.js?v=11" type="text/javascript"></script>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js?v=11"></script>
	<script src="${ctxStatic}/admin/js/im/imGlobal.js?v=11" type="text/javascript"></script>
	<script src="http://pv.sohu.com/cityjson?ie=utf-8"></script> 
	<script charset="utf-8" src="http://map.qq.com/api/js?v=6.exp"></script>
	<script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
	<script src="${ctxStatic}/jquery/jQuery-rotate.js" type="text/javascript"></script>
	<script src="${ctxStatic}/admin/js/im/exif.js" type="text/javascript"></script>
</body>
</html>