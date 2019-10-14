 <%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>IM首页</title>
<meta http-equiv=”content-type” content=”text/html; charset=UTF-8″ />
<meta name="decorator" content="default" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link href="${ctxStatic}/admin/css/im.css?v=11" type="text/css" rel="stylesheet" />
</head>
<body>
	<input type="hidden" value="${ctx}" id="ctx"> 
	<input type="hidden" value="${ctxStatic}" id="ctxStatic"> 
	<input type="hidden" value="${fns:getUploadUrl()}" id="UploadUrl"> 
	<div class="imcontent">
	<input type="hidden" value="" id="pageNo">
	<input type="hidden" value="" id="pageSize">
	<input type="hidden" value="${findShopTidFromWeb.merchantNo }" id="merchantNoIndex">
	
	<input type="hidden" value="rp" id="LEFTNAVTYPEID"/><!-- 左边菜单类型 -->
	<%@ include file="/WEB-INF/views/modules/im/leftNav.jsp"%>
		<%-- <div class="left_nav">
			<div class="member">
				<c:if test="${!empty user_photo}">
		      	 	 <img alt="" src="${fns:getUploadUrl()}${user_photo}"/>
		       	</c:if>
		       	<c:if test="${empty user_photo}">   
		             <img alt="" src="${ctxStatic}/admin/images/user-logo.jpg">
		       	</c:if>
				<div class="merName">
					<shiro:principal property="name"/>
				</div>
			</div>
			<div class="chatRoom">
				<img alt="" src="${ctxStatic}/admin/images/imImages/chat.png">
			</div>
		</div> --%>
		<div class="shop_nav hideTelescopicButton">
			<div class="shop_search">
				<div class="search_bord">
					<input type="text" class="searchTh" name="" value="${ShopTidFromWeb.searchWords }" placeholder="中控微信"/>
					<img alt="" src="${ctxStatic}/admin/images/imImages/search.png" onclick="storeSearch()">
				</div>
			</div>
			<div class="shop_search_condition">
<!-- 				<div class="condition_title"> -->
<!-- 					<label>网点微信：</label> -->
<!-- 					<select  name="codeList" id="codeListSec"  style="width:220px"> -->
<!-- 						<option value="" >全部</option> -->
<%-- 						<c:forEach var="shop" items="${shops }"> --%>
<%-- 							<option value="${shop.shopNo }" <c:if test="${shop.shopNo==ShopTidFromWeb.shopNo }"> selected="selected" </c:if> >${shop.shopName }</option> --%>
<%-- 						</c:forEach> --%>
<!-- 					</select> -->
<!-- 				</div> -->
				<div class="condition_all">
					<label>客户分组：</label>
					<select class="storeClient">
						<option value=''>全部</option>
						<c:forEach var="pm" items="${pmType }">
							<c:if test="${pm.code!='TODAY'&& pm.code!='WEEK' && pm.code!='MONTH'}">
							<option value="${pm.code }" <c:if test="${pm.code==ShopTidFromWeb.pmTypeCode }"> selected="selected" </c:if>>${pm.typeName }</option>
							</c:if>
						</c:forEach>
						
					</select>
				</div>
				<%-- <div class="region">
					<label>地区：</label>
					<div>
						<input type="hidden" id="hidProvince" value="${pm.provinceCode}"/>					
						<input type="hidden" id="hidCity" value="${pm.cityCode}"/>					
						<input type="hidden" id="hidArea" value="${pm.areaCode}"/>					
						<select class="regionSel province"></select>
						<select class="regionSel shi">
							<option value="">全部</option>
						</select>
						<select class="regionSel qu">
							<option value="">全部</option>
						</select>
					</div>
				</div> --%>
				
			</div>
			<div class="shop_list">
				<c:forEach var="shop" items="${shopTids }"  varStatus="shopStatus">
					<c:choose>
						<c:when test="${shopStatus.index==0 }">
						<input type="hidden" value="${shop.noWx }" id="noWx1">
						<input type="hidden" value="${shop.wxNickname }" id="wxNickname1">
						
							<div class="shopDetail" onclick="shopMenmber('${shop.noWx}','${shop.wxNickname }',${shopStatus.index },'${shop.headAddress}',this)">
								<input type="hidden" value="${shop.noWx }" class="noWx">
								<input type="hidden" value="${shop.wxNickname }" class="wxNickname">
								<div class="shopInfo">
									<c:if test="${empty shop.headAddress}">
										<img class="img-circle" src="${ctxStatic}/admin/images/introduce/file.png">	
									</c:if>
									<c:if test="${not empty shop.headAddress}">
										<c:if test="${fns:startsWith(shop.headAddress,'http')}">
											<img  src="${shop.headAddress}" />
										</c:if>
										<c:if test="${!fns:startsWith(shop.headAddress,'http')}">
											<img  src="${fns:getUploadUrl()}${shop.headAddress}"/>
										</c:if>
									</c:if>
									<div class="pao"></div>
								</div>
								<div class="shopInfo imshopName ">
<%-- 											<p class="oneLine ">${shop.shopName }</p> --%>
									<p class="chat_name ">网点微信：${shop.wxNickname }</p>
									<p class="chat_name ">${shop.source}</p>
									<c:if test="${shop.onlineFlag==0}"><p class="chat_name sheJiaoName offline offline">(离线)</p></c:if>
								</div>
								
								<div class="shopInfo shopNum">
									<p>${shop.terminalCode }</p>
									<p>${fns:getAreaName(shop.cityCode)}</p>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="shopDetail" onclick="shopMenmber('${shop.noWx}','${shop.wxNickname }',${shopStatus.index },'${shop.headAddress}',this)">
								<input type="hidden" value="${shop.noWx }" class="noWx">
								<input type="hidden" value="${shop.wxNickname }" class="wxNickname">
								<div class="shopInfo">
									<c:if test="${empty shop.headAddress}">
										<img class="img-circle" src="${ctxStatic}/admin/images/introduce/file.png">	
									</c:if>
									<c:if test="${not empty shop.headAddress}">
										<c:if test="${fns:startsWith(shop.headAddress,'http')}">
											<img  src="${shop.headAddress}" />
										</c:if>
										<c:if test="${!fns:startsWith(shop.headAddress,'http')}">
											<img  src="${fns:getUploadUrl()}${shop.headAddress}"/>
										</c:if>
									</c:if>
									<div class="pao"></div>
								</div>
								<div class="shopInfo imshopName ">
<%-- 											<p class="oneLine ">${shop.shopName }</p> --%>
									<p class="chat_name ">网点微信：${shop.wxNickname }</p>
									<p class="chat_name ">${shop.source}</p>
									<c:if test="${shop.onlineFlag==0}"><p class="chat_name sheJiaoName offline offline">(离线)</p></c:if>
								</div>
								<div class="shopInfo shopNum">
									<p>${shop.terminalCode }</p>
									<c:if test="${not empty shop.cityCode}">
										<p>${fns:getAreaName(shop.cityCode)}</p>
									</c:if>
									<c:if test="${empty shop.cityCode}">
										<p>${fns:getAreaName(shop.provinceCode)}</p>
									</c:if>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
					
				</c:forEach>
			</div>
		</div>
		<div class="con_nav telescopicButton">
			<img src="${ctxStatic}/admin/images/imImages/hb-icon.png" class="telescopicImage"/>
			<div class="con_search">
				<div class="con_memberName oneLine">
					【<span class="wxName"></span>】好友
				</div>
				<div class="con_bord">
					<input type="text" class="searchTh" name="" placeholder="微信昵称/客户姓名/手机号"/>
					<img alt="" src="${ctxStatic}/admin/images/imImages/search.png" onclick="changePage('first')">
				</div>
			</div>
			<div class="con_search_condition">
				<div class="condition_title" onclick="resetQuery()">全部客户</div>
				<div class="condition_all">
				<ul>
						<li>
							<div class="search">
						        <span class="n" style="font-size:13px">日期：</span>
						        <input id="startDate" name="startDate" type="text" placeholder="起始时间" readonly="readonly" maxlength="20" class="ipt input-mini Wdate doTime startDate" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true,maxDate:'%y-%M-%d',onpicking:function(dp){inputDateClickNewsList(dp.cal.getNewDateStr(),'')}});">	        
						        <span class="z">至</span>
						        <input id="endDate" name="endDate" type="text" placeholder="结束时间" readonly="readonly"  maxlength="20" class="ipt input-mini Wdate doTime endDate" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true,minDate:'#F{$dp.$D(\'startDate\',{d:0})}',maxDate:'%y-%M-%d',onpicking:function(dp){inputDateClickNewsList('',dp.cal.getNewDateStr())}});">			    
					    	</div>
						</li>
						<li>
							<label>客户分组：</label>
							<select class="clientG">
								<option value=''>全部</option>
								<c:forEach var="pm" items="${pmType }">
									<option value="${pm.code}">${pm.typeName }</option>
								</c:forEach>
								<option value="CHATROOM" >群聊</option>
							</select>
						</li>
					</ul>
					<ul>
<!-- 						<li> -->
<!-- 							<label>导购筛选：</label> -->
<!-- 							<select class="guideG"> -->
<!-- 								<option value=''>全部</option> -->
<%-- 								<c:forEach var="guidMem" items="${guidMembers }"> --%>
<%-- 									<option value="${guidMem.memberNo}">${guidMem.memberName }</option> --%>
<%-- 								</c:forEach> --%>
<!-- 							</select> -->
<!-- 						</li> -->
						<li>
							客户数：(<span class="kehuNum">0</span>)		群聊数：(<span class="chatRoomNum">0</span>)
						</li>
					</ul>
				</div>
			</div>
			
			<div class="person">
			<label class="sign"></label>
			</div>
			<!-- 分页 -->
			<div class="pagination">
				<p>总计<span class="total"></span>条记录，共<span class="totalPage"></span>页，当前第 <input value="1"  class="pageShow" type="tel"> 页，每页
					<select>
<!-- 						<option >10</option> -->
<!-- 						<option>20</option> -->
<!-- 						<option>30</option> -->
						<option selected="selected">50</option>
					</select>
				条</p>
				<img alt="" onclick="changePage('first')" class="first" src="${ctxStatic}/admin/images/imImages/arrow_back1.png">
				<img alt="" onclick="changePage('prev')" class="prev" src="${ctxStatic}/admin/images/imImages/arrow_double_b1.png">
				<img alt="" onclick="changePage('next')" class="next" src="${ctxStatic}/admin/images/imImages/arrow_doublef1.png">
				<img alt="" onclick="changePage('last')" class="last" src="${ctxStatic}/admin/images/imImages/arrow_font1.png">	
			</div>
		</div>
		<div class="info_nav">
			<div class="shop_search">
			今日新增客户数(<span id="todayCount"></span>)
			</div>
			<div class="basicInfo" id="gropInfo">
				<div class="basicInfo_title">
					<ul>
						<li class="active" style="width: 100%;border-right: 0px	">基本信息</li>
					</ul>
				</div>
				<div id="bsc_info" >
					<input type="hidden" value="" id="code">
					<ul>
						<li class="bsInfo_title">
							<img alt="" class="memHead" src="">
							<div class="bsPer">
								<p class="oneLine memWxnick"></p>
							</div>
						</li>
						<li>
							<span class="serName">创建时间</span>
							<span class="recodTime"></span>
						</li>
					</ul>
				</div>
			</div>
			<div class="basicInfo" id="pInfo">
				<div class="basicInfo_title">
					<ul>
						<li onclick="changeInfo('bsc_info',this)" class="active">基本信息</li>
						<li onclick="changeInfo('service',this)">服务备注</li>
					</ul>
				</div>
				<div id="bsc_info" >
					<input type="hidden" value="" id="code">
					<ul>
						<li class="bsInfo_title">
							<img alt="" class="memHead" src="">
							<div class="bsPer">
								<p class="oneLine memWxnick"></p>
								<p class="oneLine">微信号：<span class="memWxNo" title=""></span></p>
							</div>
						</li>
						<li class="oneLine">备注名：<input type="text" class="memAppName"  value="" maxlength="10"/></li>
						<li>客户分组：<span class="pmtype"></span></li>
<!-- 						<li>所属导购：<span class="guideName"></span></li> -->
<!-- 						<li>导购电话：<span class="guideMobile"></span></li> 
						<li class="oneLine">所需产品：<span class="needPro"></span></li>
						<li class="oneLine">客户特质：<span class="peculiarity"></span></li>
						<li class="oneLine">地区：<span class="memAddr"></span></li>
						<li class="oneLine">所在楼盘：<span class="houses"></span></li>-->
						<li class="oneLine">备注：<span class="remark"></span></li>
					</ul>
				</div>
				<div id="service" style="display: none;">
					<ul>
						<li>
							<span class="serName">客户来源</span>
							<span class="memSrc"></span>
						</li>
						<li>
							<span class="serName">添加方式</span>
							<span class="addtype"></span>
						</li>
						<c:if test="${ProductType eq 'INVITE'}">
						<li>
							<span class="serName">跟进次数</span>
							<span class="followNum">0</span>
						</li>
						<li>
							<span class="serName">维护次数</span>
							<span class="keepNum">0</span>
						</li>
						</c:if>
						<!-- <li>
							<span class="serName">成单次数</span>
							<span class="sucNum">0</span>
						</li> -->
						
						<li>
							<span class="serName">录入时间</span>
							<span class="recodTime"></span>
						</li>
						<!-- <li>
							<span class="serName">朋友圈提醒周期</span>
							<span class="cycle"></span>
						</li> -->
					</ul>
				</div>
			</div>
			
			<div class="social">
				<div class="social_title">
					网点微信号信息
				</div>
				<div class="social_info" >
					<c:forEach var="shop" items="${shopTids }"  varStatus="shopStatus">
						<ul class="shoptid${shopStatus.index }  shoptid">
							<li class="bsInfo_title " >
								<c:if test="${empty shop.headAddress}">
									<img class="img-circle" src="${ctxStatic}/admin/images/introduce/file.png">	
								</c:if>
								<c:if test="${not empty shop.headAddress}">
									<c:if test="${fns:startsWith(shop.headAddress,'http')}">
										<img  src="${shop.headAddress}" />
									</c:if>
									<c:if test="${!fns:startsWith(shop.headAddress,'http')}">
										<img  src="${fns:getUploadUrl()}${shop.headAddress}"/>
									</c:if>
								</c:if>
								<div class="bsPer">
									<p class="oneLine" title="${shop.wxNickname }">网点微信：${shop.wxNickname }</p>
									<p class="oneLine" title="${shop.alias }">网点微信号：${shop.alias }</p>
								</div>
							</li>
							<li class="oneLine">地区：${fns:getAreaName(shop.provinceCode)}${fns:getAreaName(shop.cityCode)}</li>
<%-- 							<li class="oneLine">所属终端：${shop.shopName }</li> --%>
							<li>资产编号：${shop.terminalCode }</li>
							<li class="oneLine">设备号：${shop.imei }</li>
						</ul>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<script src="${ctxStatic}/admin/js/im.js?v=11" type="text/javascript"></script>
	<script src="${ctxStatic}/admin/js/imIndex.js?v=11" type="text/javascript"></script>
	<script src="${ctxStatic}/admin/js/im/imGlobal.js?v=11" type="text/javascript"></script>
</body>
</html>