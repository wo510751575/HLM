<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
<html>
<head>
<title>${data.couponName}</title>
<meta name="decorator" content="default"/>
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link rel="stylesheet" href="${ctxStatic}/admin/css/couponnew.css?v=<%=new Date().getTime()%>" />
</head>
<body>
<div class="main" id="app" >
    <div class="coupons-detail">
        <img src="${ctxStatic}/admin/images/couponnew/erf.png" class="img-url"/>
        <div class="cash">
            <div class="logo"><img src="${merchantInfo.logoAddr }"></div>
            <div class="m">
                <span>${data.couponNotes }</span>
                <span class="y">¥</span>
            </div>
            <div class="d">买单立享减价，满${data.couponMax}元可用</div>
        </div>
        <img src="${ctxStatic}/admin/images/couponnew/rtf.png" class="img-url"/>
        <div class="detail">
            <p class="d">有效期：<fmt:formatDate value="${data.beginDate}" pattern="yyyy.MM.dd"/> - <fmt:formatDate value="${data.endDate}" pattern="yyyy.MM.dd"/></p>
            <p class="d">使用范围：${data.shopName}</p>
            <p class="d">使用对象：新老用户</p>
        </div>
        <div class="codes">
            <div class="separate"></div>
            <div class="pwd">
            	<img src="${ctxStatic}/admin/images/couponnew/gds.png" class="w"/>
            	<div class="f">领取密码： ${inviteCode }</div>
            	<img src="${ctxStatic}/admin/images/couponnew/fdr.png" class="w"/>
            </div>
            <div class="d">请牢记您的领取密码，并且长按加终端信息，以便获取终端地址</div>
            
            <div class="ewm">
            	<img src="${qcord}" class="i"/>
            	<img src="${ctxStatic}/admin/images/couponnew/zrew.png" class="h"/>
            </div>
            <div class="z">使用规则：<div id="couponRemark">${data.couponRemark}</div></div>
        </div>
    </div>
</div>
<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js?v=<%=new Date().getTime()%>"></script>
<script src="${ctxStatic}/admin/js/couponnew.js?v=<%=new Date().getTime()%>"></script>
<script>
repalceUrl();
</script>
</body>
</html>