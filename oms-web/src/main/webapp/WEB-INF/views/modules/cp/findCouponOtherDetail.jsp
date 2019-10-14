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
<div class="main" id="app" >
    <div class="coupons-detail">
        <img src="${ctxStatic}/admin/images/coupon/erf.png" class="img-url"/>
        <div class="cash">
            <div class="com-name">安家有限公司</div>
            <div class="m">
                <span>1000</span>
                <span class="y">¥</span>
            </div>
            <div class="d">买单立享减价，满1000元可用。</div>
        </div>
        <img src="${ctxStatic}/admin/images/coupon/rtf.png" class="img-url"/>
        <div class="detail">
            <p class="d">有效期：<fmt:formatDate value="${couponRule.beginDate}" pattern="yyyy.MM.dd"/> - <fmt:formatDate value="${couponRule.endDate}" pattern="yyyy.MM.dd"/></p>
            <p class="d">使用范围：${couponRule.shopName}</p>
            <p class="d">使用说明：${couponRule.couponRemark}</p>
        </div>
        <div class="codes">
            <div class="separate"></div>
            <p class="c">总数 500 张</p>
            <p class="c">剩余 50 张</p>
            <input type="button" value="分享" class="btn"/>
            <div class="ewm"><img src="${ctxStatic}/admin/images/coupon/erweima.png"/></div>
            <div class="d">安家股份有限公司 南山分店</div>
            <div class="d">扫我有惊喜！</div>
        </div>
    </div>
</div>
</body>
</html>