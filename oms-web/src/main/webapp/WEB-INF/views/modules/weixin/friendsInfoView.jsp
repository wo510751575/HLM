<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>朋友圈详情</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});
</script>
<style type="text/css">
.img-circle {
	width: 40px;
	height: 40px;
	border-radius: 20px;
	vertical-align: middle;
}
.modal.fade.in {
    top: 5%;
}
#chatInfo{
	color:black;
	position: fixed;
    top: 0px;
    width: 500px;
    height:100%;
    background: url("${ctxStatic}/images/iphone2.png") no-repeat;
    background-size:100% 100%;
    left:25%;
     z-index: 100;
}
#chatInfo #chatDetail{
	width: 337px;
    height: 60%;
    position: relative;
    z-index: 200;
    margin-left: 82px;
    top: 18%;
   overflow: auto;
    overflow-x: hidden;
    background-color: white;
}
#chatDetail ul{
width: 97%;
height: auto;
list-style: none;
margin: 2%;
overflow: hidden;
}
#chatDetail ul li{
margin-bottom: 10px;
float:left;
overflow:hidden;
}
#chatDetail ul li:nth-child(1){
width: 20%;
}
#chatDetail ul li:nth-child(2){
width: 80%;
}
#chatDetail ul li p{
margin-bottom: 0px;
}
.Infos{
	background: #e6e4e4;
	position: relative;
	margin-top: 10px;
	padding: 5px;
}

.Infos:before {
    content: '\25E3';
    position: absolute;
    display: inline-block;
    color: #e6e4e4;
    top: -2px;
    left: 11px;
    width: 10px;
    height: 10px;
    font-size: 19px;
    line-height: 20px;
    -webkit-transform: rotate(135deg);
    -moz-transform: rotate(135deg);
    -ms-transform: rotate(135deg);
    -o-transform: rotate(135deg);
}
.commentInfos{
	width: 100%;
    line-height: 2;
}
.likeInfos{
	width: 100%;
    line-height: 2;
    border-bottom: 1px solid #dedbdb;
}
.noContent{
	text-align: center;
    padding-top: 100px;
    font-size: 16px;
}
</style>
</head>
<body>

	<div class="container">
		<div id="chatInfo">
		<div id="chatDetail">
		<c:choose>
			<c:when test="${not empty page.list }">
				<c:forEach items="${page.list }" var="item">
			<ul>
				<li class="left"><img src="${item.pmPhoto }" width="80%"></li>
				<li class="left">
					<p style="color:#666 ">${item.authorname } </p>
					<p>${item.content }</p>
					<p style="color:#ccc;font-size: 11px; ">
						<fmt:formatDate value="${item.createDate }" pattern="yyyy-MM-dd HH:mm"/>
					</p>
					<c:if test="${not empty item.commentInfos || not empty item.likeInfos}">
						<div class="Infos">
							<c:if test="${not empty item.likeInfos }">  <!-- 朋友圈点赞 -->
							<div class="likeInfos">
								<c:forEach var="like" items="${item.likeInfos }" varStatus="indStatus">
									${like.NICKNAME }<c:if test="${indStatus.count < item.likeInfos.size()}">,</c:if>
								</c:forEach>
							</div>
							</c:if>
							<c:if test="${not empty item.commentInfos }">  <!-- 朋友圈评论 -->
							<div class="commentInfos">
								<c:forEach var="comment" items="${item.commentInfos }">
									<p>	${comment.nickname }
									<c:if test="${not empty comment.tonickname && comment.tonickname!='null'}">
									回复 ${comment.tonickname }
									</c:if>
									:${comment.content }</p>
								</c:forEach>
							</div>
							</c:if>
							
						</div>
					</c:if>
				</li>
			</ul>
			</c:forEach>
			</c:when>
			<c:otherwise>
				<div class="noContent">
					暂无数据！
				</div>
			</c:otherwise>
		</c:choose>
		
		</div>
	</div>
	
	</div>
</body>
</html>