<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${data.name}</title>
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
	}
	 .container {
	    width: 100%;
	    background: #fff;
	    padding: 0px;
	}
	
	.md1{
		width: 100%;
		height: 100vh;
		background: url(${ctxStatic}/admin/images/introduce/md1bg.jpg) no-repeat 0 0;
		background-size:100% 100%;
		
	}
	.md1 .md1_head{
		width: 65%;
		height: 4.7vh;
		padding-top: 1.5vh;
		font-size: 2.8vh;
		color: #e4edfc;
		text-align: center;
		line-height: 4.7vh;
		margin: 0 auto;
		overflow: hidden;
	    text-overflow: ellipsis;
	    -webkit-line-clamp: 1;
	    -webkit-box-orient: vertical;
	    white-space: nowrap;
	}	
	.md1 .md1_center .md1_center_headImg{
		width: 100%;
		height: 21.5vh;
		margin-top: 5.5vh;
		text-align: center;
	}
	.md1 .md1_center .md1_center_headImg img{
		width: 21.2vh;
		height: 21.5vh;
		box-shadow: 1px 2px 2px 1px #ccc;
		border-radius: 100%;
	}
	.md1 .md1_center .md1_center_title{
		height: 8vh;
		line-height: 8vh;
	}
	.md1 .md1_center .md1_center_title p{
		font-size: 2.8vh;
	    text-align: center;
	    color: #222;
	    letter-spacing: 3px;
	    overflow: hidden;
	    text-overflow: ellipsis;
	    -webkit-line-clamp: 1;
	    -webkit-box-orient: vertical;
	    white-space: nowrap;
	    width: 50%;
    	margin: 0 auto;
	    
	}
	.md1 .md1_center .md1_center_pingjia{
		height: 10vh;
		border-bottom: 1px solid #1e9077;
		border-top: 1px solid #1e9077;
		width: 66%;
    	margin: 0 auto;
	}
	.md1 .md1_center .md1_center_pingjia p .xing img{
		width: 2vh;
		margin-top: -5px;
		
	}
	.md1 .md1_center .md1_center_pingjia p{
		margin: 0 auto;
		text-align: center;
		height: 3.5vh;
		line-height: 3.5vh;
	}
	.md1 .md1_center .md1_center_pingjia p span{
		color: #1e9077;
		font-size: 2.2vh;
	}
	.md1 .content_pingjia{
		height: 7vh;
		margin-top: 1.5vh;
	}
	.md1 .md1_center .detail{
		width: 76%;
		margin: 0 auto;
		margin-top: 7vh;
		height: 38vh;
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
		top:-2vh;
		left: 35%;
		background: #d7ebf6;
		text-align: center;
		color: #222;
		font-size: 2.5vh;
	}
	.md1 .md1_center .detail .info{
		width: 90%;
		margin: 0 auto;
		color: #222;
		font-size: 2.5vh;
	}
	.md1 .md1_center .detail .info .info_single{
		width: 90%;
		margin: 3vh auto;
		display: flex;
	}
	.md1 .md1_center .detail .info .info_single img{
		width: 3vh;
		height: 3vh;
		margin-right: 10px;
		margin-top: 1px;
	}
	.md1 .md1_center .detail .info .info_single div{
		overflow: hidden;
	    text-overflow: ellipsis;
	    display: -webkit-box;
	    -webkit-line-clamp: 2;
	    -webkit-box-orient: vertical;
	}
	
    </style>
    
</head>
<body>
    <div class="container">
    	<div class="setting-container preview-container">
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
        		
        		<!--模板2  -->
        		<%-- <c:if test="${temp==2 }">
        		<link href="${ctxStatic}/admin/css/models/temp2.css" type="text/css"
					rel="stylesheet" />
        			<%@ include file="/WEB-INF/views/modules/models/introduce/temp2.jsp"%>
        		</c:if> --%>
        	</div>
    	</div>
    </div>
	<script type="text/javascript">

	 $(document).ready(function() {
			
		 	
	    });
</script>
    	
</body>
</html>