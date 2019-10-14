<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${data.title}</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
	<script src="${ctxStatic}/common/plupload.full.min.js" type="text/javascript"></script>
	<link href="${ctxStatic}/admin/css/model.css" type="text/css" rel="stylesheet" />
	<link href="${ctxStatic}/admin/css/models/phone.css" type="text/css" rel="stylesheet" />
    
</head>
<body>
    <div class="container">
    	<div class="setting-container preview-container">
    		<div class="phone-head">
            <span class="phone-head-icon"></span>
        	</div>
        	<div class="phone-title-seciton">
            <p class="app-header">${data.title}</p>
        	</div>
        	
        	<div class="h5_info">
        	
        		<c:choose>
        			<c:when test="${temp!='' && temp!=null}">
        			${fns:unescapeHtml(data.content)}
        			</c:when>
        			<c:otherwise>
        				<div class="pro_detail">
			        		<c:if test="${not empty data.imgAddr}">
			        			<c:set var="imgAddr" value="${fn:split(data.imgAddr, ',')}" />
				        		<c:forEach items="${imgAddr }" var="imgAddr">
				        			<img alt="" src="${fns:getUploadUrl()}${imgAddr }">
				        		</c:forEach>
		        			</c:if>
		        				${fns:unescapeHtml(data.content)}
        				</div>
        		<%-- <c:if test="${not empty companyLogo}">
        		 <div class="info_title">
        		 
        			<div class="left logo"><img alt="" src="${fns:getUploadUrl()}${companyLogo}"></div>
        			<div class="left name">${companyName}</div>
        		</div>
        		</c:if>
        		<div class="introduce_cont">${companyRemarks }</div> --%>
        			</c:otherwise>
        		</c:choose>
        		
        	</div>
        	
        	
    	</div>
    </div>
    	
</body>
</html>