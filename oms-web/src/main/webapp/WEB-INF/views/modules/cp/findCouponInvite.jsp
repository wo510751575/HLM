<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
<html>
<head>
<title>优惠券</title>
<meta name="decorator" content="default"/>
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link rel="stylesheet" href="${ctxStatic}/admin/css/couponnew.css?v=<%=new Date().getTime()%>" />
</head>
<body style="background:#fff;">
<input type="hidden" value="${ctxStatic}" id="ctxStatic" />
<div class="main" id="app" style="display:none;">
    <div class="coupons-detail">
        <img src="${ctxStatic}/admin/images/couponnew/erf.png" class="img-url"/>
        <div class="cash">
        	<div class="logo"><img src="${merchantInfo.logoAddr }"></div>
        	<div class="com-name">${merchantInfo.merchantName}</div>
        </div>
        <img src="${ctxStatic}/admin/images/couponnew/rtf.png" class="img-url"/>
        <div class="codes">
        	<input type="text" class="yqm-txt" id="couponCode"/>
        	<div class="d" style="text-align:center;">输入您的领取密码</div>
        	<input type="button" value="提交" class="btn" onclick="receiveCouponB(this)" style="margin-top:50px;"/>
        </div>
    </div>
</div>
<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js?v=<%=new Date().getTime()%>"></script>
<script src="${ctxStatic}/admin/js/couponnew.js?v=<%=new Date().getTime()%>"></script>
<script>
repalceUrl();
receiveInitCouponB();
</script>
</body>
</html>