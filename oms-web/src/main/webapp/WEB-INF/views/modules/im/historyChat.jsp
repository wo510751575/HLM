<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>历史记录</title>
<meta name="decorator" content="default" />
<meta http-equiv=”content-type” content=”text/html; charset=UTF-8″ />
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<link href="${ctxStatic}/admin/css/im.css?v=11" type="text/css" rel="stylesheet" />
</head>
<body>
	<input type="hidden" value="${ctx}" id="ctx"> 
	<input type="hidden" value="10" id="limit">
	<input type="hidden" value="0" id="start">
	<input type="hidden" value="${ctxStatic}" id="ctxStatic">
	<input type="hidden" value="${fns:getUploadUrl()}" id="UploadUrl"> 
	<c:choose>
		<c:when test="${not empty findGuidMemberReturn.headAddress}">
			<input type="hidden" value="${fns:getUploadUrl()}${findGuidMemberReturn.headAddress }" id="gudHeadAddress"> 
		</c:when>
		<c:otherwise>
			<input type="hidden" value="${fns:getUploadUrl()}/logo/guid_default_head.png" id="gudHeadAddress"> 
		</c:otherwise>
	</c:choose>
	<div class="imcontent" id="historyChat">
		<input type="hidden" value="rp" id="LEFTNAVTYPEID"/><!-- 左边菜单类型 -->
		<%@ include file="/WEB-INF/views/modules/im/leftNav.jsp"%>
		<div class="shop_nav hideTelescopicButton">
			<div class="shop_search">
				<span class="back" onclick="ImIndex()">
					返回
				</span>
			</div>
			<div class="shop_list">
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
						<ul>
							<li><span>备注</span><input class="memAppName"  value="${personMember.memberName}" type="text"/></li>
							<li><span>微信昵称</span><span>${personMember.nickNameWx }</span></li>
							<li><span>微信号</span><span class="noWx">
							<c:choose>
								<c:when test="${not empty personMember.noWxAlias  }">
									${personMember.noWxAlias }
								</c:when>
								<c:otherwise>
									${personMember.noWx }
								</c:otherwise>
							</c:choose>
							</span></li>
							<li><span>手机号</span><span>${personMember.mobile }</span></li>
<%-- 							<li><span>所属导购</span><span>${personMember.memberNameGm }</span></li> 
							<li class="oneLine"><span>所需产品</span><span title="${personMember.bomName }">${personMember.bomName }</span></li>
							<li class="oneLine"><span>客户特质</span><span title="${personMember.clientSpecial }">${personMember.clientSpecial }</span></li>
							<li class="oneLine"><span>所在地区</span><span>${fns:getAreaName(personMember.provinceCode)}${fns:getAreaName(personMember.cityCode)}</span></li>
							<li class="oneLine"><span>所在楼盘</span><span title="${personMember.houses }">${personMember.houses }</span></li>--%>
							<li class="oneLine"><span>备注</span><span title="${personMember.remark }">${personMember.remark }</span></li>
							<%-- <li><span>客户来源</span><span>
								<c:forEach items="${memerSources}" var="item">
									<c:if test="${item eq personMember.memberSrc}">${item.name}</c:if>
								</c:forEach>
								</span></li>
							<li><span>添加方式</span><span>
								<c:if test="${personMember.addType eq 1}">导购扫码添加</c:if>
			                    <c:if test="${personMember.addType eq 2}">客户扫码添加</c:if>
			                    <c:if test="${personMember.addType eq 3}">导购手动新增</c:if>
			                    <c:if test="${personMember.addType eq 4}">微信自动导入</c:if>
			                    <c:if test="${personMember.addType eq 5}">手机号添加</c:if>
			                    <c:if test="${personMember.addType eq 6}">微信号添加</c:if>
			                    <c:if test="${personMember.addType eq 7}">QQ号添加</c:if>
							</span></li> --%>
							<c:if test="${ProductType eq 'INVITE'}">
							<li><span>跟进次数</span><span>${personMember.followNum}</span></li>
							<%-- <li><span>维护次数</span><span>${personMember.keepNum}</span></li> --%>
							</c:if>
							<%-- <li><span>成单次数</span><span>${personMember.successNum}</span></li>
							<li><span>录入时间</span><span><fmt:formatDate value="${personMember.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span></li> --%>
						</ul>
					</div>
				</div>
				
			</div>
		</div>
		<div class="con_nav">
			<div class="con_search">
				<input id="startTime" name="startTime" type="text"  readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="" onclick="WdatePicker({onpicked:cDayFunc(this),dateFmt:'yyyy-MM-dd',isShowClear:true,startDate:'%y',maxDate:'%y-%M-%d'});" />
				<img alt="" src="${ctxStatic}/admin/images/imImages/ce.png" class="dateCe">
				<div class="choseDate">选择日期</div>
				<div class=" historyTitle text-center">
					聊天记录
				</div>
			</div>
			<div class="person">
				
				<label class="sign"></label>
			</div>
			<!-- 分页 -->
			<div class="pagination">
				<span>总计<span class="total"></span>条记录，共<span class="totalPage"></span>页，当前第 <input value="1" class="pageShow" type="tel"> 页，每页
					<select>
						<option>10</option>
						<option>20</option>
						<option>30</option>
						<option>50</option>
					</select>
				条</span>
				<img alt="" onclick="changePage('first')" class="first" src="${ctxStatic}/admin/images/imImages/arrow_back1.png">
				<img alt="" onclick="changePage('prev')" class="prev" src="${ctxStatic}/admin/images/imImages/arrow_double_b1.png">
				<img alt="" onclick="changePage('next')" class="next" src="${ctxStatic}/admin/images/imImages/arrow_doublef1.png">
				<img alt="" onclick="changePage('last')" class="last" src="${ctxStatic}/admin/images/imImages/arrow_font1.png">		
			</div>
		</div>
		<div class="yulan">
			<div class="top_yulan text-center" >
				<!-- <div id="yulan"></div> -->
				<iframe id="yulan" src=""></iframe>
			</div>
		</div>
		
	</div>
	<div class="loadFlag" id="loadFlag" onclick="$('#loadFlag').hide();window.clearInterval(intCycle);">
		<img alt="" src="${ctxStatic}/admin/images/imImages/load.gif">
	</div>
	<div class="bigImg" id="bigImg" onclick="$('#bigImg').hide()">
		<img alt="" src="" style="height:67% !important">
		<img alt="" src="" class="hideImg"  style="height:67% !important;display:none;">
	</div>
	<script src="${ctxStatic}/admin/js/im.js?v=11" type="text/javascript"></script>
	<script src="${ctxStatic}/admin/js/historyChat.js?v=11" type="text/javascript"></script>
	<script src="${ctxStatic}/admin/js/emojiChange.js?v=11" type="text/javascript"></script>
	<script src="${ctxStatic}/admin/js/emojiChangeFan.js?v=11" type="text/javascript"></script>
	<script src="${ctxStatic}/admin/js/im/imGlobal.js?v=11" type="text/javascript"></script>
	<script charset="utf-8" src="http://map.qq.com/api/js?v=6.exp"></script>
	<script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
	<script src="${ctxStatic}/admin/js/im/exif.js" type="text/javascript"></script>
</body>
</html>