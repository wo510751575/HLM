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
	 <div class="loadFlag" id="loadFlayTwo">
		<img alt="" src="${ctxStatic}/admin/images/imImages/load.gif">
	</div>
	<input type="hidden" value="${ctx}" id="ctx"> 
	<input type="hidden" value="${fns:getUploadUrl()}" id="UploadUrl"> 
	<input type="hidden" value="sendFlag" id="sendFlag" />
	<div class="imcontent" id="guideChat">
		<input type="hidden" value="rp" id="LEFTNAVTYPEID"/><!-- 左边菜单类型 -->
		<%@ include file="/WEB-INF/views/modules/im/leftNav.jsp"%>
		<div class="shop_nav hideTelescopicButton">
			<div class="shop_search">
				<span class="back" onclick="ImIndex()">
					返回
				</span>
			</div>
			<c:choose>
				<c:when test="${not empty guidMember.headAddress}">
					<c:if test="${fns:startsWith(guidMember.headAddress,'http')}">
						<input type="hidden" value="${guidMember.headAddress}"  id="gudHeadAddress" >
					</c:if>
					<c:if test="${!fns:startsWith(guidMember.headAddress,'http')}">
						<input type="hidden" value="${fns:getUploadUrl()}${guidMember.headAddress}"  id="gudHeadAddress" >
					</c:if>
				</c:when>
				<c:otherwise>
					<input type="hidden" value="${fns:getUploadUrl()}/logo/guid_default_head.png"  id="gudHeadAddress" >
				</c:otherwise>
			</c:choose>
			<div class="shop_list">
					<div class="shopDetail active" id="guide">
						<input type="hidden" value="${personMemberByGm.memberNoGm}" class="guidMemNo">
						<input type="hidden" value="${personMemberByGm.memberNo}" class="clientMemNo"> 
						<input type="hidden" value="${personMemberByGm.noWx }" class="noWx">
						<input type="hidden" value="" class="chatTime">
						<div class="shopInfo">
							<c:choose>
								<c:when test="${not empty personMemberByGm.headAddress}">
									<c:if test="${fns:startsWith(personMemberByGm.headAddress,'http')}">
										<img  src="${personMemberByGm.headAddress}" />
									</c:if>
									<c:if test="${!fns:startsWith(personMemberByGm.headAddress,'http')}">
										<img  src="${fns:getUploadUrl()}${personMemberByGm.headAddress}" />
									</c:if>
								</c:when>
								<c:otherwise>
									<img alt="" src="${fns:getUploadUrl()}/logo/guid_default_head.png" >
								</c:otherwise>
							</c:choose>
							<div class="pao">
							</div>
						</div>
						<div class="shopInfo imshopName">
							<p class="oneLine">导购：${guidMember.memberName }</p>
						</div>
					</div>
					
					<div class="shopDetail" id="client">
						<input type="hidden" value="${personMember.memberNoGm}" class="guidMemNo">
						<input type="hidden" value="${personMember.memberNo}" class="clientMemNo"> 
						<input type="hidden" value="${personMember.noWx }" class="noWx">
						<input type="hidden" value="" class="chatTime">
						<div class="shopInfo">
							<c:choose>
								<c:when test="${not empty personMember.headAddress}">
									<c:if test="${fns:startsWith(personMember.headAddress,'http')}">
										<img  src="${personMember.headAddress}" />
									</c:if>
									<c:if test="${!fns:startsWith(personMember.headAddress,'http')}">
										<img  src="${fns:getUploadUrl()}${personMember.headAddress}"/>
									</c:if>
								</c:when>
								<c:otherwise>
									<img alt="" src="${fns:getUploadUrl()}/logo/guid_default_head.png" id="memberHead">
								</c:otherwise>
							</c:choose>
							<div class="pao">
							</div>
						</div>
						<div class="shopInfo imshopName">
							<p class="oneLine">客户：<span class="active">${personMember.memberName }</span></p>
						</div>
					</div>
			</div>
		</div>
		<div class="con_nav ">
			<div class="con_search">
				<div class="person_chat_head">
						<c:choose>
							<c:when test="${not empty personMemberByGm.headAddress}">
								<c:if test="${fns:startsWith(personMemberByGm.headAddress,'http')}">
									<c:if test="${fns:startsWith(personMemberByGm.headAddress,'http')}">
										<img  src="${personMemberByGm.headAddress}" />
									</c:if>
									<c:if test="${!fns:startsWith(personMemberByGm.headAddress,'http')}">
										<img  src="${fns:getUploadUrl()}${personMemberByGm.headAddress}"/>
									</c:if>
								</c:if>
								<c:if test="${!fns:startsWith(personMemberByGm.headAddress,'http')}">
									<img class="img-circle" src="${fns:getUploadUrl()}${personMemberByGm.headAddress}">
								</c:if>
							</c:when>
							<c:otherwise>
								<img alt="" src="${fns:getUploadUrl()}/admin/images/introduce/file.png">
							</c:otherwise>
						</c:choose>
						<span>导购：${guidMember.memberName }</span>
					</div>
			</div>
			<div class="person">
				<label class="sign"></label>
			</div>
			
			<div class="sendInfoDetail">
				<div class="emoticon">
					<div class="guideEmoji">
						<ul>
							<li><img alt="" title="表情" class="smile" src="${ctxStatic}/admin/images/imImages/smile.png" onclick="showEmoji()" /></li>
							<li>
								<img alt="" class="pic" src="${ctxStatic}/admin/images/imImages/pic.png">
								<input type="file" title="图片" name="uploadFile" id="file1" accept="image/gif,image/jpeg,image/jpg,image/png,image/svg" onchange="upImg('file1',this)" class="upload"/>
							</li>
						</ul>
					</div>
					<div class="clientEmoji">
						<ul>
							<li><img alt="" title="表情" class="smile" src="${ctxStatic}/admin/images/imImages/smile.png" onclick="showEmoji()" /></li>
							<li>
								<img alt="" class="pic" src="${ctxStatic}/admin/images/imImages/pic.png">
								<input type="file" title="图片" name="uploadFile" id="file2" accept="image/gif,image/jpeg,image/jpg,image/png,image/svg" onchange="upImg('file2',this)" class="upload"/>
							</li>
							<li><img title="素材" alt="" class="file" src="${ctxStatic}/admin/images/imImages/file.png" onclick="huodong('huodong1')"></li>
							<li><img title="活动" alt="" class="act" src="${ctxStatic}/admin/images/imImages/act.png" onclick="huodong('huodong2')"></li>
							<li><img title="优惠券" alt="" class="youhuiquan" src="${ctxStatic}/admin/images/imImages/youhuiquan.png" onclick="huodong('huodong3')"></li>
							<li><img title="名片" alt="" class="personal" src="${ctxStatic}/admin/images/imImages/personal.png" onclick="showAllCard()">
								<div class="allCard">
									<dl>
<!-- 										<dd onclick="personCard()" class="twoLine">我的名片</dd> -->
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
				<!-- 		<li class="trusteeshipIcon" onclick="clickTrusteeship()"><img title="托管" alt="" class="diannao" src="${ctxStatic}/admin/images/imImages/diannao.png" onclick="">
							
						</li> -->
						<li>
							<img alt="" class="videoChat" src="${ctxStatic}/admin/images/imImages/videoChat.png">
							<input type="file" title="视频" name="uploadFile" id="videoFile" accept="video/mp4" onchange="upVideo('videoFile',this)" class="upload"/>
							<video style="display:none;" controls="controls" id="aa" oncanplaythrough="myFunction(this)"></video>
						</li>
						<li><img title="小程序" alt="" class="personal" src="${ctxStatic}/admin/images/imImages/personal.png" onclick="showXCX(1)">
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
				</div>
				<div class="sendInfo" id="txtContent" contenteditable="true">
				
				</div>
				
				<div class="send">
					<lable class="desc">按下Ctrl+Enter换行</lable>
					<span onclick="sendSub(1)">发送</span>
				</div>
			</div>
		</div>
		
		<div class="info_nav">
			<div class="shop_search">
				<span class="info_nav_title text-center">名下客户信息</span>
			</div>
			<div class="basicInfo">
				<div class="basicInfo_title">
					<ul>
						<li onclick="changeInfo('bsc_info',this)" class="active">基本信息</li>
						<li onclick="changeInfo('service',this)">服务备注</li>
					</ul>
				</div>
				<div id="bsc_info" >
					<ul>
						<li class="bsInfo_title">
							<c:choose>
								<c:when test="${not empty personMember.headAddress}">
									<c:if test="${fns:startsWith(personMember.headAddress,'http')}">
										<c:if test="${fns:startsWith(personMember.headAddress,'http')}">
											<img  src="${personMember.headAddress}" />
										</c:if>
										<c:if test="${!fns:startsWith(personMember.headAddress,'http')}">
											<img  src="${fns:getUploadUrl()}${personMember.headAddress}"/>
										</c:if>
									</c:if>
									<c:if test="${!fns:startsWith(personMember.headAddress,'http')}">
										<img class="img-circle" src="${fns:getUploadUrl()}${personMember.headAddress}">
									</c:if>
								</c:when>
								<c:otherwise>
									<img alt="" src="${fns:getUploadUrl()}/logo/guid_default_head.png">
								</c:otherwise>
							</c:choose>
							<div class="bsPer">
								<p class="oneLine" title="${personMember.nickNameWx}">${personMember.nickNameWx}</p>
								<p class="oneLine" title="${personMember.noWxAlias}">网点微信号：${personMember.noWxAlias}</p>
							</div>
						</li>
						<li class="oneLine">备注名：<input type="text" class="memAppName"  value="${personMember.memberName}"/></li>
						<li>客户分组：${personMember.pmTypeName}</li>
<%-- 						<li>所属导购：${personMember.memberNameGm}</li> 
						<li>导购电话：${personMember.mobileGm}</li>
						<li class="oneLine" title="${personMember.bomName }">所需产品：${personMember.bomName}</li>
						<li class="oneLine" title="${personMember.clientSpecial }">客户特质：${personMember.clientSpecial}</li>
						<li class="oneLine">地区：${fns:getAreaName(personMember.provinceCode)}${fns:getAreaName(personMember.cityCode)}</li> 
						<li class="oneLine" title="${personMember.houses }">所在楼盘：${personMember.houses}</li>--%>
						<li class="oneLine" title="${personMember.remark }">备注：${personMember.remark}</li>
					</ul>
				</div>
				<div id="service" style="display: none;">
					<ul>
						<li>
							<span class="serName">客户来源</span>
							<span>
							<c:forEach items="${memerSources}" var="item">
								<c:if test="${item eq personMember.memberSrc}">${item.name}</c:if>
							</c:forEach>
							</span>
						</li>
						<li>
							<span class="serName">添加方式</span>
							<span>
								<c:if test="${personMember.addType eq 1}">导购扫码添加</c:if>
			                    <c:if test="${personMember.addType eq 2}">客户扫码添加</c:if>
			                    <c:if test="${personMember.addType eq 3}">导购手动新增</c:if>
			                    <c:if test="${personMember.addType eq 4}">微信自动导入</c:if>
			                    <c:if test="${personMember.addType eq 5}">手机号添加</c:if>
			                    <c:if test="${personMember.addType eq 6}">微信号添加</c:if>
			                    <c:if test="${personMember.addType eq 7}">QQ号添加</c:if>
							</span>
						</li>
						<c:if test="${ProductType eq 'INVITE'}">
						<li>
							<span class="serName">跟进次数</span>
							<span>${personMember.followNum}</span>
						</li>
						<li>
							<span class="serName">维护次数</span>
							<span>${personMember.keepNum}</span>
						</li>
						</c:if>
						<li>
							<span class="serName">成单次数</span>
							<span>${personMember.successNum}</span>
						</li>
						<li>
							<span class="serName">录入时间</span>
							<span><fmt:formatDate value="${personMember.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
						</li>
						<li>
							<span class="serName">朋友圈提醒周期</span>
							<span>${personMember.remark4}</span>
						</li>
					</ul>
				</div>
			</div>
			
			<div class="social">
				<input type="hidden" value="${shopTid.noWx}" class="noWxShop">
				<div class="social_title">
					网点微信号信息
				</div>
				<div class="social_info" >
					<ul>
						<li class="bsInfo_title">
							<c:if test="${empty shopTid.headAddress}">
									<img class="img-circle" src="${ctxStatic}/admin/images/introduce/file.png">	
								</c:if>
								<c:if test="${not empty shopTid.headAddress}">
									<c:if test="${fns:startsWith(shopTid.headAddress,'http')}">
										<img  src="${shopTid.headAddress}" />
									</c:if>
									<c:if test="${!fns:startsWith(shopTid.headAddress,'http')}">
										<img  src="${fns:getUploadUrl()}${shopTid.headAddress}"/>
									</c:if>
								</c:if>
							<div class="bsPer">
								<p class="oneLine" title="${shopTid.wxNickname }">社交名称：${shopTid.wxNickname }</p>
								<p class="oneLine" title="${shopTid.alias }">网点微信号：${shopTid.alias }</p>
							</div>
						</li>
<%-- 						<li class="oneLine">地区：${fns:getAreaName(shopTid.provinceCode)}${fns:getAreaName(shopTid.cityCode)}</li> --%>
						<li class="oneLine">所属终端：${shopTid.shopName }</li>
						<li>资产编号：${shopTid.terminalCode }</li>
						<li class="oneLine">设备号：${shopTid.imei }</li>
					</ul>
				</div>
			</div>
		</div>
		
		<div class="zhegai">
			<!-- 表情 -->
			<div class="emoji">
				<div class="showEmoji">
					
				</div>
				<div class="emojiList">
					<div class="emojitab">
						<ul>
							<li class="active text-center"><img src="${ctxStatic}/admin/images/emoji/ic_wx_emoji.png"></li>
							<li class="text-center"><img src="${ctxStatic}/admin/images/emoji/ic_wx_emoji.png"></li>
							<li class="text-center"><img src="${ctxStatic}/admin/images/emoji/ic_wx_emoji.png"></li>
							<li class="text-center"><img src="${ctxStatic}/admin/images/emoji/ic_wx_emoji.png"></li>
							<li class="text-center"><img src="${ctxStatic}/admin/images/emoji/ic_wx_emoji.png"></li>
							<li class="text-center"><img src="${ctxStatic}/admin/images/emoji/ic_wx_emoji.png"></li>
							<li class="text-center"><img src="${ctxStatic}/admin/images/emoji/ic_wx_emoji.png"></li>
							<li class="text-center"><img src="${ctxStatic}/admin/images/emoji/ic_wx_emoji.png"></li>
							<li class="text-center"><img src="${ctxStatic}/admin/images/emoji/ic_wx_emoji.png"></li>
							<li class="text-center"><img src="${ctxStatic}/admin/images/emoji/ic_wx_emoji.png"></li>
							<li class="text-center"><img src="${ctxStatic}/admin/images/emoji/ic_wx_emoji.png"></li>
							<li class="text-center"><img src="${ctxStatic}/admin/images/emoji/ic_wx_emoji.png"></li>
							<li class="text-center"><img src="${ctxStatic}/admin/images/emoji/ic_wx_emoji.png"></li>
							<li class="text-center"><img src="${ctxStatic}/admin/images/emoji/ic_wx_emoji.png"></li>
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
						<!-- <div class="hd_detail_tbd"  id='hd1'>
						
						</div> -->
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
						<!-- <div class="hd_detail_tbd"  id='hd3' style="background: #21b964;">
						
						</div> -->
						<iframe class="hd_detail_tbd" id='hd3' ></iframe>
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
	<script src="${ctxStatic}/admin/js/im.js?v=11" type="text/javascript"></script>
	<script src="${ctxStatic}/admin/js/guideChat.js?v=11" type="text/javascript"></script>
	<script src="${ctxStatic}/admin/js/emojiChange.js?v=11" type="text/javascript"></script>
	<script src="${ctxStatic}/admin/js/emojiChangeFan.js?v=11" type="text/javascript"></script>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js?v=6"></script>
	<script src="${ctxStatic}/admin/js/im/imGlobal.js?v=11" type="text/javascript"></script>
	<script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
	<script charset="utf-8" src="http://map.qq.com/api/js?v=6.exp"></script>
</body>
</html>