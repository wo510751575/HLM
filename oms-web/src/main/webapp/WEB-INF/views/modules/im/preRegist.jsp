 <%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>IM预报名</title>
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
	
	<input type="hidden" value="ybm" id="LEFTNAVTYPEID"/><!-- 左边菜单类型 -->
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
					<input type="text" class="searchTh" name="" value="${ShopTidFromWeb.searchWords }" placeholder="网点微信"/>
					<img alt="" src="${ctxStatic}/admin/images/imImages/search.png" onclick="storeSearch()">
				</div>
			</div>
			<div class="shop_search_condition">
				<div class="condition_title">
					<label>网点微信：</label>
<!-- 					<select  name="codeList" id="codeListSec"  style="width:220px"> -->
<!-- 						<option value="" >全部</option> -->
<%-- 						<c:forEach var="shop" items="${shops }"> --%>
<%-- 							<option value="${shop.shopNo }" <c:if test="${shop.shopNo==ShopTidFromWeb.shopNo }"> selected="selected" </c:if> >${shop.shopName }</option> --%>
<%-- 						</c:forEach> --%>
<!-- 					</select> -->
				</div>
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
							<div class="shopDetail" onclick="shopMenmber('${shop.noWx}','${shop.wxNickname }',${shopStatus.index },'',this)">
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
								</div>
								
								<c:choose>
									<c:when test="${shop.onlineFlag==1 }">
										<div class="shopInfo imshopName ">
											<p class="oneLine ">${shop.shopName }</p>
											<p class="chat_name ">网点微信：${shop.wxNickname }</p>
										</div>
									
									</c:when>
									<c:otherwise>
										<div class="shopInfo imshopName sheJiao">
											<p class="oneLine sheJiaoName">${shop.shopName }</p>
											<p class="chat_name sheJiaoName">网点微信：${shop.wxNickname }</p>
											<p class="chat_name sheJiaoName offline offline">(离线)</p>
										</div>
									</c:otherwise>
								</c:choose>
								
								<div class="shopInfo shopNum">
									<p>${shop.terminalCode }</p>
									<p>${fns:getAreaName(shop.cityCode)}</p>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="shopDetail" onclick="shopMenmber('${shop.noWx}','${shop.wxNickname }',${shopStatus.index },'',this)">
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
								</div>
								<c:choose>
									<c:when test="${shop.onlineFlag==1 }">
										<div class="shopInfo imshopName ">
											<p class="oneLine ">${shop.shopName }</p>
											<p class="chat_name ">网点微信：${shop.wxNickname }</p>
										</div>
									
									</c:when>
									<c:otherwise>
										<div class="shopInfo imshopName sheJiao">
											<p class="oneLine sheJiaoName">${shop.shopName }</p>
											<p class="chat_name sheJiaoName">网点微信：${shop.wxNickname }</p>
											<p class="chat_name sheJiaoName offline">(离线)</p>
										</div>
									</c:otherwise>
								</c:choose>
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
					<ul style="margin-top: 10px">
						<li style="height: 40px">
							<label >客户分组：</label>
							<select class="clientG" style="height: 40px">
								<option value=''>全部</option>
								<c:forEach var="pm" items="${pmType }">
									<option value="${pm.code}">${pm.typeName }</option>
								</c:forEach>
							</select>
						</li>
<!-- 						<li style="height: 40px"> -->
<!-- 							<label >导购筛选：</label> -->
<!-- 							<select class="guideG" style="height: 40px"> -->
<!-- 								<option value=''>全部</option> -->
<%-- 								<c:forEach var="guidMem" items="${guidMembers }"> --%>
<%-- 									<option value="${guidMem.memberNo}">${guidMem.memberName }</option> --%>
<%-- 								</c:forEach> --%>
<!-- 							</select> -->
<!-- 						</li> -->
						<li>
							客户数：(<span class="kehuNum">0</span>)
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
						<option selected="selected">10</option>
						<option>20</option>
						<option>30</option>
						<option>50</option>
					</select>
				条</p>
				<img alt="" onclick="changePage('first')" class="first" src="${ctxStatic}/admin/images/imImages/arrow_back1.png">
				<img alt="" onclick="changePage('prev')" class="prev" src="${ctxStatic}/admin/images/imImages/arrow_double_b1.png">
				<img alt="" onclick="changePage('next')" class="next" src="${ctxStatic}/admin/images/imImages/arrow_doublef1.png">
				<img alt="" onclick="changePage('last')" class="last" src="${ctxStatic}/admin/images/imImages/arrow_font1.png">	
			</div>
		</div>
		
	</div>
	<script src="${ctxStatic}/admin/js/im.js?v=11" type="text/javascript"></script>
	<script src="${ctxStatic}/admin/js/im/preRegist.js?v=11" type="text/javascript"></script>
	<script src="${ctxStatic}/admin/js/im/imGlobal.js?v=11" type="text/javascript"></script>
</body>
</html>