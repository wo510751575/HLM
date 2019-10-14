<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>发红包</title>
<meta name="decorator" content="default" />
<meta http-equiv=”content-type” content=”text/html; charset=UTF-8″ />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link href="${ctxStatic}/admin/css/im.css?v=11" type="text/css" rel="stylesheet" />
</head>
<body>
<input type="hidden" value="bk" id="LEFTNAVTYPEID"/><!-- 左边菜单类型 -->
<input type="hidden" value="red" id="MATERIALLISTTYPEID"/><!-- 素材类型列表 -->
<input type="hidden" id="ctxStatic" value="${ctxStatic }" />
<input type="hidden" value="${ctx }" id="ctx" />
<input type="hidden" value="${fns:getUploadUrl()}" id="UploadUrl"> 
<input type="hidden" value="1" id="pageNo">
<input type="hidden" value="10" id="pageSize">

<div class="lj-main imcontent">
	<%@ include file="/WEB-INF/views/modules/im/leftNav.jsp"%>
	
	<div class="shop_nav hideTelescopicButton">
			<div class="shop_search">
				<div class="search_bord">
					<input type="text" class="searchTh" name="" value="${ShopTidFromWeb.searchWords }" placeholder="社交名称"/>
					<img alt="" src="${ctxStatic}/admin/images/imImages/search.png" onclick="storeSearch()">
				</div>
			</div>
			<div class="shop_search_condition">
				<div class="condition_title">
					<label>网点名称：</label>
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
							<c:if test="${pm.code!='1' }">
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
							<div class="shopDetail active" onclick="redList('${shop.noWx}','${redpackDetailYears[0]}','${shop.shopName}','${fns:getUploadUrl()}${shop.headAddress}',this)"> 
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
					
								</div>
								
								<c:choose>
									<c:when test="${shop.onlineFlag==1 }">
										<div class="shopInfo imshopName ">
											<p class="oneLine ">${shop.shopName }</p>
											<p class="chat_name ">社交名称：${shop.wxNickname }</p>
										</div>
									
									</c:when>
									<c:otherwise>
										<div class="shopInfo imshopName sheJiao">
											<p class="oneLine sheJiaoName">${shop.shopName }</p>
											<p class="chat_name sheJiaoName">社交名称：${shop.wxNickname }</p>
											<p class="chat_name sheJiaoName offline">(离线)</p>
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
							<div class="shopDetail" onclick="redList('${shop.noWx}','${redpackDetailYears[0]}','${shop.shopName}','${fns:getUploadUrl()}${shop.headAddress}',this)">
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
				
								</div>
								<c:choose>
									<c:when test="${shop.onlineFlag==1 }">
										<div class="shopInfo imshopName ">
											<p class="oneLine ">${shop.shopName }</p>
											<p class="chat_name ">社交名称：${shop.wxNickname }</p>
										</div>
									
									</c:when>
									<c:otherwise>
										<div class="shopInfo imshopName sheJiao">
											<p class="oneLine sheJiaoName">${shop.shopName }</p>
											<p class="chat_name sheJiaoName">社交名称：${shop.wxNickname }</p>
											<p class="chat_name sheJiaoName offline">(离线)</p>
										</div>
									</c:otherwise>
								</c:choose>
								<div class="shopInfo shopNum">
									<p>${shop.terminalCode }</p>
									<p>${fns:getAreaName(shop.cityCode)}</p>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
					
				</c:forEach>
			</div>
		</div>
		
	<div class="lj-right telescopicButton">
		<div class="recordListBox">
			<div class="topBox">
				<span onclick="backRed()">返回</span>
				<h3>发出的红包</h3>
			</div>
			<div class="midBox">
				<div class="photo"><img src="http://wx.qlogo.cn/mmhead/ver_1/M8t0AymxAeda2hbYibwFkaFp3j0mhwLmMzeUMpSuAxcnxX4DEoC1FMU1puUbJIynXke8KThqhTqSq6icTgicfZ7AprEZXYN5WUicmTdzL10t6KQ/96"></div>
				<div class="contentInfo">
					<h4></h4>
					<p>
						<span>发出红包 <i class="totalNum"></i></span>
						<span>金额总计 （已领取）<i class="amount"></i></span>
					</p>
				</div>
				<div class="selectTimeBox">
					<select class="timeBox" onchange="selectTime()">
						<option value="" selected="selected">时间</option>
						<c:forEach var="shop" items="${redpackDetailYears}">
							<option value="${shop}">${shop}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="bottomListBox" id="table-list">
				<%-- <table>
					<thead>
						<tr>
							<th>
								<select class="timeBox" onchange="selectTime()">
									<option value="" selected="selected">时间</option>
									<c:forEach var="shop" items="${redpackDetailYears}">
										<option value="${shop}">${shop}</option>
									</c:forEach>
								</select>
							</th>
							<th>客户昵称</th>
							<th>金额</th>
							<th>状态</th>
						</tr>
					</thead>
					<tbody>
						<!-- <tr>
							<td class="grayColor">2017-12-24</td>
							<td>程珊珊</td>
							<td>100.25元</td>
							<td class="grayColor">已领完</td>
						</tr> -->
					</tbody>
				</table> --%>
			</div>
		
			
		</div>
    </div>
    
</div>
<script src="${ctxStatic}/admin/js/im.js?v=11" type="text/javascript"></script>
<script src="${ctxStatic}/admin/js/redRecordList.js?v=11" type="text/javascript"></script>
<script src="${ctxStatic}/admin/js/im/imGlobal.js?v=11" type="text/javascript"></script>
<script>
$(document).ready(function(){
	var noWx = '${shopTids[0].noWx}';
	var year = '${redpackDetailYears[0]}';
	var addr = '${fns:getUploadUrl()}${shopTids[0].headAddress}';
	var shopName = '${shopTids[0].shopName}';
	$('.midBox .photo').find('img').attr('src',addr);
	$('.midBox .contentInfo').find('h4').text(shopName);
	redList(noWx,year,shopName,addr);
	
	$('.shop_list').find('.shopDetail').on('click',function(){
		$(this).addClass('active').siblings().removeClass('active');
	})
	
})
</script>
</body>
</html>