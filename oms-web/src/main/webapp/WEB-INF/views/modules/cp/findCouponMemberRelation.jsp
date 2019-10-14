<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
<html>
<head>
<title>${couponMemberRelationVo.couponType}</title>
<meta name="decorator" content="default"/>
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link rel="stylesheet" href="${ctxStatic}/admin/css/coupon.css" />
</head>
<body>
<div class="main">
    <div class="coupon-detail">
    	<img src="${ctxStatic}/admin/images/coupon/kh.png" class="kh"/>
        <div class="head">
        	<c:if test="${couponMemberRelationVo.headAddress != null}">
        		<div class="h" style="background: url(${couponMemberRelationVo.headAddress}) no-repeat; background-size: cover;"></div>
        	</c:if>
        	<c:if test="${couponMemberRelationVo.headAddress == null}">
        		<div class="h" style="background: url(${ctxStatic}/admin/images/coupon/per.png) no-repeat; background-size: cover;"></div>
        	</c:if>
        </div>
        <div class="gx">恭喜！您本次可以优惠</div>
        
        <div class="yhj">
            <div class="bg">
                <img src="${ctxStatic}/admin/images/coupon/yhj-bg.png" class="b"/>
                <div class="amt">
                    <div class="f">
                        <span class="y">&yen;</span>
                        <span class="p"><fmt:formatNumber value="${couponMemberRelationVo.couponNotes}" pattern="0"/></span>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- <div class="tip">
            <div class="t">- 活动专柜消费任意金额可用 -</div>
        </div> -->
        <div class="btm">
            <div class="addr">使用规则</div>
            <div class="d">有效期：<fmt:formatDate value="${couponMemberRelationVo.beginDate}" pattern="yyyy.MM.dd"/>至<fmt:formatDate value="${couponMemberRelationVo.endDate}" pattern="yyyy.MM.dd"/></div>
            <div class="addr">使用范围：${couponMemberRelationVo.shopName}</div>
            <div class="y">使用说明：<span>${couponMemberRelationVo.couponRemark}</span></div>
        </div>
    </div>
</div>
</body>
</html>