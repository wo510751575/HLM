<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>表情管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function CancelQuery(){
			$(':input','#searchForm')  
			 .not(':button, :submit, :reset')  
			 .val('')  
			 .removeAttr('checked')  
			 .removeAttr('selected'); 
		
        }
	</script>
	<style type="text/css">
.img-small {
	width: 30px;
	height: 30px;
}

.container {
	padding: 20px 30px;
	width: 100%;
	min-height: 800px;
	background: #fff;
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
}

.page_header {
	font-size: 32px;
	font-weight: normal;
	line-height: 1;
	padding-bottom: 40px;
	color: #666;
}
.nav-tabs > li > a {
    padding-top: 0px;
}
.lafen-group {
    position: relative;
}
.lafen-group .img-big {
    position: absolute;
    left: 50%;
    top: 50%;
    margin-top: -75px;
    margin-left: 35px;
    opacity: 0;
    transform: scale(.2, .2);
    transition: all .2s ease-in-out;
    width: 130px;
    height: 130px;
}
.lafen-group .img-small:hover + .img-big {
    transform: scale(1, 1);
    opacity: 1;
}
.lafen-group .img-big img {
    width: 130px;
    height: 130px;
}
	</style>
	<style type="text/css">
	.container{
	padding: 10px 30px;
    width: 100%;
    min-height: 800px;
    background: #fff;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
	}
	.label-success{
	background-color: #2ecc71;
	}
	.label-danger{
	background-color: #e74c3c;
	}
	</style>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/weixin/imEmoji/?packageCode=${findImEmojiPage.packageCode}">表情列表</a></li>
			<li><a href="${ctx}/weixin/imEmoji/form?packageCode=${findImEmojiPage.packageCode}">表情添加</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/weixin/imEmoji/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="packageCode" name="packageCode" type="hidden" value="${findImEmojiPage.packageCode}"/>
		<ul class="ul-form">
		    <li><label>表情名称：</label>
		    	<input type="text" name="emojiName"  value="${findImEmojiPage.emojiName}" class="input-medium" maxlength="50" placeholder="表情名称">
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
 	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>显示序号</th>
				<th>表情名称</th>
				<th>图片</th>
				<!-- <th>版本号</th> -->
				<th>状态</th>
				<!-- <th>创建人</th>
				<th>创建时间</th> -->
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
				<td>
					${item.showIndex}
				</td>
				<td>
					${item.emojiName}
				</td>
				 <td>
			    	<div class="lafen-group">
			    	<img class="img-small" src="${fns:getUploadUrl()}${item.emojiUrl}" alt="">
			    	<div class="img-big">
			    		<img src="${fns:getUploadUrl()}${item.emojiUrl}" alt="">
			    	</div>
			    	</div>
				</td>
			     <%-- <td>
					${item.version}
				</td> --%>
				<td>
				 	<c:if test="${item.status eq 0}">无效</c:if> 
				 	<c:if test="${item.status ne 0}">有效</c:if>
				</td>
				<%-- <td>
				    ${item.createId}
				</td>
				<td>
				   <fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td> --%>
				<td>
				  <a href="${ctx}/weixin/imEmoji/form?code=${item.code}&packageCode=${findImEmojiPage.packageCode}">修改</a>
				</td>
			              
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>