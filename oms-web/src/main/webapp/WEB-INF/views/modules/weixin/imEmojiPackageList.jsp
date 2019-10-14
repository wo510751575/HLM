<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>表情包管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//表情配置
			$('.propertyConfig').click(function() {
				var id = $(this).data("id");
				
				// 正常打开	
				top.$.jBox.open("iframe:${ctx}/weixin/imEmoji/view?packageCode="+id,"表情配置", 920, 730,{//宽高
					id:9527,
					draggable: true,
					showClose: true,
					buttons:{},		//去除按钮
					iframeScrolling: 'no',
					loaded:function(h){
						top.$('.jbox-container .jbox-title-panel').css("height","35px").css('background','#376EA5');
						top.$('.jbox-container .jbox-title').css('line-height','35px').css("color","#ffffff");
					},
					closed: function () { 
					} /* 信息关闭后执行的函数 */
				});
		    });
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
		<li class="active"><a href="${ctx}/weixin/imEmojiPackage/">表情包列表</a></li>
			<li><a href="${ctx}/weixin/imEmojiPackage/form">表情包添加</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/weixin/imEmojiPackage/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		    <li><label>表情包名称：</label>
		    	<input type="text" name="packageName"  value="${findImEmojiPackagePage.packageName}" class="input-medium" maxlength="500" placeholder="表情包名称">
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
				<th>表情包名称</th>
				<th>表情包LOGO</th>
				<!-- <th>版本号</th> -->
				<th>状态</th>
				<!-- <th>创建人</th>
				<th>创建时间</th> -->
				<th>备注</th>
				<shiro:hasPermission name="weixin:imEmojiPackage:edit">
				<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
				<td>
					${item.showIndex}
				</td>
				<td>
					${item.packageName}
				</td>
				<td>
			    	<div class="lafen-group">
			    	<img class="img-small" src="${fns:getUploadUrl()}${item.packageLogo}" alt="">
			    	<div class="img-big">
			    		<img src="${fns:getUploadUrl()}${item.packageLogo}" alt="">
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
				    ${item.remark}
				</td>
				<td>
				  <a href="${ctx}/weixin/imEmojiPackage/form?code=${item.code}">修改</a>
				  <a href="javascript:;" class="propertyConfig" data-id="${item.code}">表情配置</a>
				</td>
			              
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>