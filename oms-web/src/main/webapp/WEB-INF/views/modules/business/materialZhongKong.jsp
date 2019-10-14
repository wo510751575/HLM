<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${data.memberName}</title>
    <meta name="decorator" content="default"/>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
	<script src="${ctxStatic}/common/plupload.full.min.js" type="text/javascript"></script>
	<link href="${ctxStatic}/admin/css/model.css" type="text/css" rel="stylesheet" />
    <style type="text/css">
    html, body {
    margin: 0;
    padding: 0;
    width: 100%;
    height: 100%;
    line-height: 1.5;
    font-family: "微软雅黑", Arial;
    color: #666;
    font-size: 15px;
    background: #f5f5f5;
    text-align: center;
	}
	 .container {
	    width: 100%;
	    background: #f5f5f5;
	    padding: 0px;
	}
	.banner img{
		width: 100%;
		height: auto;
	}
	.userInfo{
		background: #fff;
		padding-bottom: 16px;
	}
	.userInfo p{
		width: 100%;
		text-align: center;
	}
	.userInfo .headImg img{
		border-radius:50%;
		width: 80px;
		height: 80px;
		margin-top:-40px;
	    border: 3px solid white;
	}
	.userInfo .userName{
		color: #333;
		font-size: 17px;
		margin-top: 16px;
	}
	.userInfo .shopName{
		color: #999;
		font-size: 15px;
		margin-top: 15px;
	}
	.userInfo .position{
		color: #b3b3b3;
		font-size: 12px;
		margin-top: 16px;
		margin-bottom: 0;
	}
	.userInfo .position span{
		display: inline-block;
		padding: 0 10px;
		height: 15px;
		border: 1px solid #b3b3b3;
		border-radius:10px;
		line-height: 15px;
	}
	.infoDetail{
		width: 100%;
		height:auto;
		margin-top: 10px;
		background: white;
		padding-bottom: 14px;
	}
	.infoDetail .titleDetail{
		color: #333;
		font-size: 18px;
		width: 100%;
		padding-top: 23px;
		margin-bottom: 24px;
	}
	.infoDetail .shop_detail{
		width: 60%;
		margin: 0 auto;
		margin-bottom:22px;
		display: flex;
    	justify-content: space-between;
	}
	.infoDetail .shop_detail span:nth-child(2){
		display: inline-block;
		width: 80%;
		text-align: left;
		color: #333;
		font-size: 14px;
	}
	.infoDetail .shop_detail img{
		width: 14px;
		height: 14px;
		margin-top: -5px;
	}
    </style>
    
</head>
<body>
    <div class="container">
    	<div class="banner">
    		<img alt="" src="${ctxStatic}/admin/images/shop_banner.png">
    	</div>
    	<div class="userInfo">
    		<p class="headImg">
    			<c:choose>
					<c:when test="${!empty data.headAddress}">
						<img alt="" src="${fns:getUploadUrl()}${data.headAddress}">
					</c:when>
					<c:otherwise>
						<img alt="" src="${ctxStatic}/admin/images/introduce/file.png">	
					</c:otherwise>
				</c:choose>
    		</p>
    		<p class="userName">${data.memberName }</p>
    		<p class="shopName">${data.shopName }</p>
    		<p class="position"><span>
    		<c:if test="${position eq 'GUID'}">导购</c:if>
    		<c:if test="${position eq 'PERSON'}">个人</c:if>
    		<c:if test="${position eq 'SHOP'}">店长</c:if>
    		<c:if test="${position eq 'AREA_MAN'}">区域经理</c:if>
    		<c:if test="${position eq 'BOSS'}">老板</c:if>
    		</span></p>
    	</div>
    	<div class="infoDetail">
    		<p class="titleDetail">名片信息</p>
    		<p class="shop_detail">
    			<span><img alt="" src="${ctxStatic}/admin/images/mobile_shop.png"> </span>
    			<span id="mobile">${data.mobile }</span>
    		</p>
    		<p class="shop_detail">
    			<span><img alt="" src="${ctxStatic}/admin/images/shopDoor.png"> </span>
    			<span>${data.shopName }</span>
    		</p>
    		<p class="shop_detail">
    			<span><img alt="" src="${ctxStatic}/admin/images/addr_shop.png"> </span>
    			<span>${companyAddress }</span>
    		</p>
    	</div>
    </div>
	<script type="text/javascript">
	function doFilter(value) {
	    var temp = [];
	    temp[0] = value.slice(0, 3);
	    temp[1] = value.slice(3, 7);
	    temp[2] = value.slice(7);
	    return temp.join(" ");
	}
	 $(document).ready(function() {
		 	var m=$("#mobile").text();
			$("#mobile").text("+86 "+doFilter(m));
		 	
	    });
</script>
    	
</body>
</html>