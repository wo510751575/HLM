<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>朋友圈评论</title>
<meta name="decorator" content="default" />
<meta http-equiv=”content-type” content=”text/html; charset=UTF-8″ />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link href="${ctxStatic}/admin/css/im.css?v=11" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="${ctxStatic}/admin/css/swiper.min.css">
</head>
<body >
<input type="hidden" value="bk" id="LEFTNAVTYPEID"/><!-- 左边菜单类型 -->
<input type="hidden" value="comment" id="MATERIALLISTTYPEID"/><!-- 素材类型列表 -->
<input type="hidden" id="ctxStatic" value="${ctxStatic }" />
<input type="hidden" value="${ctx }" id="ctx" />
<input type="hidden" value="${merchantNo }" id="merchantNo" />
<input type="hidden" value="1" id="pageNo" />
<input type="hidden" value="" id="pageSize" />
<input type="hidden" value="" id="count" />
<input type="hidden" value="" id="AllCount" />
<input type="hidden" value="" id="replyCount" />
<input type="hidden" value="" id="total" />
<input type="hidden" value="0" id="start" />
<input type="hidden" value="" id="limit" />
<input type="hidden" value="" id="getHeader" />
<input type ="hidden" value="" class="nickNickName">
<input type ="hidden" value="" id="commentMemberNoGms">
<input type="hidden" value="" id="commentCountNum">
<input type ="hidden" value="" id="memberNos">
<div class="lj-main sendF">
	<%@ include file="/WEB-INF/views/modules/im/leftNav.jsp"%>
	<%@ include file="/WEB-INF/views/modules/im/friendsList.jsp"%>
	<div class="nav-wrapper">
			<div class="shop_nav perSonInfo" style="display: none;">
		    </div>
	<div class="shop_nav shop_navTwo">
			<div class="shop_search telescopicButton">
				<img src="${ctxStatic}/admin/images/imImages/hb-icon.png" class="telescopicImage up"/>
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
							<div class="shopDetail active" onclick="queryInfo(this)">
								<input type="hidden" value="${shop.wxNickname }" class="nickName">
								<input type="hidden" value="${shop.noWx }" class="noWx">
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
									<!-- <div class="pao"></div> -->
								</div>
								<c:choose>
									<c:when test="${shop.onlineFlag==1 }">
									<div class="shopInfo imshopName ">
								    	<p class="chat_name ">网点微信：${shop.wxNickname }</p>
								    </div>
									</c:when>
									<c:otherwise>
									 <div class="shopInfo imshopName pyShejiao">
										<p class="chat_name pyShejiaoP">网点微信：${shop.wxNickname }</p>
										<p class="chat_name pyShejiaoP offline">(离线)</p>
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
							<div class="shopDetail" onclick="queryInfo(this)">
								<input type="hidden" value="${shop.wxNickname }" class="nickName">
								<input type="hidden" value="${shop.noWx }" class="noWx">
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
									<!-- <div class="pao"></div> -->
								</div>
								
									<c:choose>
										<c:when test="${shop.onlineFlag==1 }">
										  	<div class="shopInfo imshopName">
									    	<p class="chat_name ">网点微信：${shop.wxNickname }</p>
									    	</div>
										</c:when>
										<c:otherwise>
											<div class="shopInfo imshopName pyShejiao">
										<p class="chat_name pyShejiaoP">网点微信：${shop.wxNickname }</p>
										<p class="chat_name pyShejiaoP offline">(离线)</p>
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
		
		<div class="shop_nav shop_navThree">
			<div class="titleBack" onclick="newsList()">返回</div>
			<div class="shop_search telescopicButton">
				<img src="${ctxStatic}/admin/images/imImages/hb-icon.png" class="telescopicImage up"/>
				<div class="search_bord">
					<input type="text" class="searchTh searchThTwo" name="" value="${ShopTidFromWeb.searchWords }" placeholder="客户姓名/微信昵称"/>
					<img alt="" src="${ctxStatic}/admin/images/imImages/search.png" onclick="clickNewsList('M')">
				</div>
				
			</div>
			<div class="search">
		        <span class="n">日期：</span>
		        <input id="startDate" name="startDate" type="text" placeholder="起始时间" readonly="readonly" maxlength="20" class="ipt input-mini Wdate doTime startDate" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true,maxDate:'%y-%M-%d',onpicking:function(dp){inputDateClickNewsList(dp.cal.getNewDateStr(),'')}});">	        
		        <span class="z">至</span>
		        <input id="endDate" name="endDate" type="text" placeholder="结束时间" readonly="readonly"  maxlength="20" class="ipt input-mini Wdate doTime endDate" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true,minDate:'#F{$dp.$D(\'startDate\',{d:0})}',maxDate:'%y-%M-%d',onpicking:function(dp){inputDateClickNewsList('',dp.cal.getNewDateStr())}});">			    
	    	</div>
			<%-- <div class="condition_all condition_allPyq">
				<ul>
					<li>
						<label style="padding-left:5px;">客户分组：</label>
						<select class="clientG">
							<option value=''>全部</option>
							<c:forEach var="pm" items="${pmType }">
								<option value="${pm.code}">${pm.typeName }</option>
							</c:forEach>
						</select>
					</li>
				</ul>
			</div> --%>
				<div class="newsListContent-wrarpper">
					<ul class="newsListContent">
					
					</ul>
				</div>
		</div>

		</div>
		
		<div class="con_right " id="topfS">
			<ul class="friendItem">
				<li onclick="hideCommentThree()" class="friendItemActive">全部</li>
				<li class="commentIcon" onclick="showCommentThree(this)">待评论</li>
				<li class="reply" onclick="showGetCommentFour(this)">待回复</li>
			</ul>
			<div class="FContent" id="friendScroll">
				<div class="con_right_title"> <span onclick="clickNewsList('Y')"></span></div>
				<div class="newsListIcon-wrapper">
					
				</div>
				<input type="hidden" class="noWxShopName" value="">
				<div class="comment_all commentOne">
				
				</div>
				<div class="comment_all commentTwo">
					
				</div>
				<div class="comment_all commentThree">
				 
				</div>
				<div class="comment_all commentFour">
				 	
				</div>
			</div>
		</div>
	
	<div class="beauty-list" id="beauty-main"></div>
</div>
<div class="loadFlag" id="loadFlag" onclick="$('#loadFlag').hide();window.clearInterval(fCycle);">
		<img alt="" src="${ctxStatic}/admin/images/imImages/load.gif">
	</div>
 <script src="${ctxStatic}/admin/js/im/imGlobal.js?v=11" type="text/javascript"></script>
 <script src="${ctxStatic}/admin/js/friendComment.js?v=11" type="text/javascript"></script> 
 <script src="${ctxStatic}/admin/js/im.js?v=11" type="text/javascript"></script>
 <script src="${ctxStatic}/admin/js/emojiChange.js?v=11" type="text/javascript"></script>
 <script src="${ctxStatic}/admin/js/emojiChangeFan.js?v=11" type="text/javascript"></script>
 <script src="${ctxStatic}/admin/js/swiper.min.js"></script>
</body>
</html>