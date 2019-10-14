<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>敏感词管理</title>
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
		<li class="active"><a href="${ctx}/weixin/imSensitiveWords">敏感词列表</a></li>
			<li><a href="${ctx}/weixin/imSensitiveWords/form">敏感词添加</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/weixin/imSensitiveWords/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		    <li><label>敏感词：</label>
		    	<input type="text" name="word"  value="${findImSensitiveWordsPage.word}" class="input-medium" maxlength="50" placeholder="敏感词">
			</li>
		    <li><label>状态：</label>
		    	<select  name="status"  class="input-medium" >
					<option value="">请选择</option>
					<option value="1" <c:if test="${findImSensitiveWordsPage.status eq '1'}">selected</c:if> >有效</option>
					<option value="0" <c:if test="${findImSensitiveWordsPage.status eq '0' }">selected</c:if>>无效</option>
				 </select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
 	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<!-- <th>显示序号</th> -->
				<th>敏感词</th>
				<!-- <th>版本号</th> -->
				<th>状态</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="page" varStatus="number"> 
			<tr>
				<%-- <td>
					${page.showIndex}
				</td> --%>
				<td>
					${page.word}
				</td>
			<%-- 	<td>
					${page.version}
				</td> --%>
				<td>
					${page.status eq '0'?"无效":"有效"}
				</td>
				<td>
				   <fmt:formatDate value="${page.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				  <a href="${ctx}/weixin/imSensitiveWords/form?code=${page.code}">修改</a>
				   <a href="${ctx}/weixin/imSensitiveWords/delect?code=${page.code}" onclick="return confirmx('确认要删除该敏感词吗？', this.href)">删除</a>
				</td>
			              
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>