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
    <div class="coupon-des">
        <div class="top">
        	<div class="logo"><img src="${couponRule.merchantLogoUrl}" ></div>
            <p class="n"><fmt:formatNumber value="${couponRule.couponMax}" pattern="0"/>元减<fmt:formatNumber value="${couponRule.couponNotes}" pattern="0"/>元立减金</p>
            <p class="p">( 有效期：<fmt:formatDate value="${couponRule.beginDate}" pattern="yyyy.MM.dd"/>至<fmt:formatDate value="${couponRule.endDate}" pattern="yyyy.MM.dd"/> )</p>
        </div>
        <div class="btm">
<%--             <div class="addr">使用范围：${couponRule.shopName}</div> --%>
            <div class="price">使用说明：${couponRule.couponRemark}</div>
            <div class="circle-p">
                <div class="ct">
                    <span class="ct-tag">消费满</span>
                    <div class="mn">
                        <span class="ct-p"><fmt:formatNumber value="${couponRule.couponMax}" pattern="0"/></span>
                        <span class="ct-y">元</span>
                    </div>
                </div>
                <div class="cb">
                    <span class="cb-tag">奖券类型</span>
                    <span class="cb-type">(${couponRule.couponType})</span>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>