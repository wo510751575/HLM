<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title></title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
	<script src="${ctxStatic}/common/plupload.full.min.js" type="text/javascript"></script>
	<link href="${ctxStatic}/admin/css/model.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript">
    $(document).ready(function() {
    });
   
    </script>
    <style type="text/css">
    html, body {
    margin: 0;
    padding: 0;
    width: 100%;
    height: 100%;
    min-width: 1250px;
    line-height: 1.5;
    font-family: "微软雅黑", Arial;
    color: #666;
    font-size: 14px;
	}
	    .container {
	    padding: 30px;
	    width: 100%;
	    min-height: 800px;
	    background: #fff;
	    -webkit-box-sizing: border-box;
	    box-sizing: border-box;
	}
	.preview-container {
	    width: 350px;
	    margin: 20px auto 0;
	}
	
	.setting-container {
	    margin-top: 20px;
	    padding-bottom: 64px;
	    width: 350px;
	    min-height: 100px;
	    border-radius: 18px;
	    border: 1px solid #dddddd;
	    position: relative;
	    overflow: hidden;
	}
	.phone-head {
    padding-top: 55px;
    line-height: 0;
    text-align: center;
	}
	
	.phone-head-icon {
	    display: inline-block;
	    width: 60px;
	    height: 8px;
	    border-radius: 10px;
	    background: #ddd;
	    position: relative;
	    transform: translateX(8px);
	}
	.phone-head-icon:before {
	    position: absolute;
	    left: -22px;
	    top: 0;
	    content: '';
	    width: 8px;
	    height: 8px;
	    border-radius: 50%;
	    background: #ddd;
	}
	.phone-head-icon:after {
	    margin-left: -7px;
	    position: absolute;
	    left: 50%;
	    top: -25px;
	    content: '';
	    width: 14px;
	    height: 14px;
	    border-radius: 50%;
	    background: #ddd;
	}
	.phone-title-seciton {
	    margin: 20px auto 0;
	    padding-top: 27px;
	    width: 320px;
	    height: 37px;
	    background: url(${fns:getUploadUrl()}/oms/image/materialcommen/phone_head_bg.png) no-repeat 0 0;
	    background-size: 100%;
	}
	.app-header {
	    color: #fff;
	    line-height: 30px;
	    text-align: center;
	    font-size: 18px;
	    width: 160px;
	    margin-left: 80px;
	    white-space: nowrap;
	    word-break: break-all;
	    text-overflow: ellipsis;
	    overflow: hidden;
	}
	.h5_info{
	margin: 0 auto;
    padding: 0 0 20px 0;
    width: 320px;
    overflow: hidden; 
    height: 570px;
    background-color: rgb(250, 250, 250);
	}
	.h5_info:after {
		margin-left: -25px;
	    position: absolute;
	    left: 50%;
	    bottom: 8px;
	    content: '';
	    width: 45px;
	    height: 45px;
	    border-radius: 50%;
	    border:1px solid #ddd;
	}
	
	.md1{
		width: 100%;
		height: 570px;
		background: url(${ctxStatic}/admin/images/introduce/md1bg.jpg) no-repeat 0 0;
		background-size:100% 100%;
		
	}
	.md1 .md1_head{
		width: 65%;
		height: 27.25px;
		padding-top: 8.7px;
		font-size: 16.24px;
		color: #e4edfc;
		text-align: center;
		line-height: 27.26px;
		margin: 0 auto;
	}
	.md1 .md1_center .md1_center_headImg{
		width: 100%;
		height: 124.6px;
		margin-top: 31.6px;
		text-align: center;
	}
	.md1 .md1_center .md1_center_headImg img{
		width: 123.1px;
		height: 124.9px;
		box-shadow: 1px 2px 2px 1px #ccc;
		border-radius: 100%;
	}
	.md1 .md1_center .md1_center_title{
		height: 46.4px;
		line-height: 46.4px;
	}
	.md1 .md1_center .md1_center_title p{
		font-size: 16.2px;
	    text-align: center;
	    color: #222;
	    letter-spacing: 3px;
	    
	}
	.md1 .md1_center .md1_center_pingjia{
		height: 58px;
		border-bottom: 1px solid #1e9077;
		border-top: 1px solid #1e9077;
		width: 66%;
    	margin: 0 auto;
	}
	.md1 .md1_center .md1_center_pingjia p .xing img{
		width: 11px;
		margin-top: -5px;
		
	}
	.md1 .md1_center .md1_center_pingjia p{
		margin: 0 auto;
		text-align: center;
		height: 20px;
		line-height: 20px;
	}
	.md1 .md1_center .md1_center_pingjia p span{
		color: #1e9077;
		font-size: 12.78px;
	}
	.md1 .content_pingjia{
		height: 40.65px;
		margin-top: 8.7px;
	}
	.md1 .md1_center .detail{
		width: 76%;
		margin: 0 auto;
		margin-top: 40.6px;
		height: 220.7px;
		position: relative;
	}
	.md1 .md1_center .detail hr{
	    border: 1px solid #1e9077;
	    border-top: 0px;
	    width: 65%;
	    margin: 0 auto;
	}
	.md1 .md1_center .detail_title{
		position: absolute;
		width: 30%;
		top:-11px;
		left: 35%;
		background: #d7ebf6;
		text-align: center;
		color: #222;
		font-size: 14.5px;
	}
	.md1 .md1_center .detail .info{
		width: 90%;
		margin: 0 auto;
		color: #222;
		font-size: 14.5px;
	}
	.md1 .md1_center .detail .info .info_single{
		width: 90%;
		margin: 17.43px auto;
		display: flex;
	}
	.md1 .md1_center .detail .info .info_single img{
		width: 17.4px;
		height: 17.4px;
		margin-right: 10px;
		margin-top: 1px;
	}
	
	
	
    </style>
    
</head>
<body>
    <div class="container">
    	<div class="setting-container preview-container">
    		<div class="phone-head">
            <span class="phone-head-icon"></span>
        	</div>
        	<div class="phone-title-seciton">
            <p class="app-header"></p>
        	</div>
        	
        	<div class="h5_info">
        		<!--模板1  -->
        	<c:if test="${temp==1 }">
        		<div class="md1">
        			<div class="md1_head">
        				${data.name }
        			</div>
        			<div class="md1_center">
        				<div class="md1_center_headImg">
        					<c:choose>
        						<c:when test="${!empty data.headAddress}">
        							<img alt="" src="${fns:getUploadUrl()}${data.headAddress}">
        						</c:when>
        						<c:otherwise>
        							<img alt="" src="${ctxStatic}/admin/images/introduce/file.png">	
        						</c:otherwise>
        					</c:choose>
        				</div>
        				<div class="md1_center_title">
        					<p>${data.position }</p>
        				</div>
        				<div class="md1_center_pingjia">
        					<div class="content_pingjia">
        						<p>
        						<span>服务星级:</span>
        						<span class="xing">
        							<c:forEach begin="1" end="5">
        								<img alt="" src="${ctxStatic}/admin/images/introduce/md1_xing.png">
        							</c:forEach>
        						</span>
        					</p>
        					<p>
        						<span>专业水平:</span>
        						<span class="xing">
        							<c:forEach begin="1" end="5">
        								<img alt="" src="${ctxStatic}/admin/images/introduce/md1_xing.png">
        							</c:forEach>
        						</span>
        					</p>
        					</div>
        				</div>
        				
        				<div class="detail">
        					<hr>
        					<div class="detail_title">
        						详细信息
        					</div>
        					<div class="info">
        						<div class="info_single">
        							<img alt="" src="${ctxStatic}/admin/images/introduce/md1Phone.png">
        							<div>${data.mobile }</div>
        						</div>
        						<div class="info_single">
        							<img alt="" src="${ctxStatic}/admin/images/introduce/md1addr.png">
        							<div>${data.companyAddress }</div>
        						</div>
        						<div class="info_single">
        							<img alt="" src="${ctxStatic}/admin/images/introduce/md1dian.png">
        							<div>${data.shopName }</div>
        						</div>
        						<div class="info_single">
        							<img alt="" src="${ctxStatic}/admin/images/introduce/md1info.png">
        							<div>${data.slogan }</div>
        						</div>	
        					
        					</div>
        				</div>
        				
        			</div>
        		</div>
        		</c:if>
        		<!-- 模板1結束 -->
        		
        		<!-- 模板2开始   -->
        			<%-- <c:if test="${temp==2 }">
        			<link href="${ctxStatic}/admin/css/models/temp2view.css" type="text/css"
						rel="stylesheet" />
        				<%@ include file="/WEB-INF/views/modules/models/introduce/temp2.jsp"%>
        			</c:if> --%>
        		<!-- 模板2结束 -->
        		
        	</div>
        	
        	
    	</div>
    </div>
    	
</body>
</html>