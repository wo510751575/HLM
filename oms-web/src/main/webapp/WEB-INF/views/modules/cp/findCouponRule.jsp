<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
<html>
<head>
<title>${couponRule.couponType}</title>
<meta name="decorator" content="default"/>
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link rel="stylesheet" href="${ctxStatic}/admin/css/coupon.css" />
</head>
<body>
<div class="main">
    <div class="coupon">
    	<div class="comName">${couponRule.merchantName}</div>
    	<img src="${ctxStatic}/admin/images/coupon/kh.png" class="kh"/>
        <div class="top">
        	<div class="l">
	            <div class="p">
		            <span class="yen">&yen;</span>
		            <span class="price"><fmt:formatNumber value="${couponRule.couponNotes}" pattern="0"/></span>
	            </div>
	            <div class="y">满<fmt:formatNumber value="${couponRule.couponMax}" pattern="0"/>元可用</div>
        	</div>
        	<div class="r">
        		<span class="yhj">优惠券</span>
        		<img src="${ctxStatic}/admin/images/coupon/new-tag.png" class="tag"/>
        	</div>
        </div>
        <div class="shareBtn" id="shareBtn" onclick="$('#shareTag').show()">
        	<img src="${ctxStatic}/admin/images/coupon/share-btn.png" class="b"/>
        </div>
        <div class="btm">
            <div class="addr">使用规则</div>
            <div class="d">有效期：<fmt:formatDate value="${couponRule.beginDate}" pattern="yyyy.MM.dd"/>至<fmt:formatDate value="${couponRule.endDate}" pattern="yyyy.MM.dd"/></div>
            <div class="addr">使用范围：${couponRule.shopName}</div>
            <div class="y">使用说明：<span>${couponRule.couponRemark}</span></div>
        </div>
    </div>
    <div id="shareTag" class="shareTag" onclick="$('#shareTag').hide()">
    	<img src="${ctxStatic}/admin/images/coupon/shareTag.png" class="st"/>
    </div>
</div>
</body>
</html>