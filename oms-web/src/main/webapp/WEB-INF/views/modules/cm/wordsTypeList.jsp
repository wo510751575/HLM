<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>个人话术类型</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		//是否包含字符串
		function isContains(str, substr) {
		    return str.indexOf(substr) >= 0;
		}
		$(document).ready(function() {
			if(localStorage.getItem("wordsTypeTips")){
				var info=localStorage.getItem("wordsTypeTips");
				showTip(info);
				localStorage.removeItem("wordsTypeTips");
			}
			var repMsg=$("#repMsg").val();
			if(repMsg){
				if(isContains(repMsg,"成功")&& (isContains(repMsg,"保存")||isContains(repMsg,"编辑"))){
					localStorage.setItem("wordsTypeTips","保存话术类型成功");
					window.parent.frames[0].location.href="${ctx}/cm/wordsType/";//刷新指定iframe
					top.$.jBox.close(true);
				}else{
					showTip(repMsg);
					$("#repMsg").val("");
				}
			} 
			
			//保存
			$('.typeSave').click(function() {
				var code = $(this).data("code")||"";
				// 正常打开	
				top.$.jBox.open("iframe:${ctx}/cm/wordsType/form?code="+code,"", 380, 180,{//宽高
					id:7777,
					top:'25%',
					title:null,
					//height:'auto',
					draggable: true,
					showClose: false,
					showScrolling:false,
					buttons:{},		//去除按钮
					iframeScrolling: 'no',
					loaded:function(h){
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
	</script>
	<style type="text/css">
	.container{
	padding: 10px 30px;
    width: 100%;
    min-height: 800px;
    background: #fff;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
	}
	</style>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cm/wordsType/">个人话术类型列表</a></li>		
			<%-- <li><a href="${ctx}/cm/wordsType/form">话术类型添加</a></li> --%>	
			<li><a href="javascript:;" class="typeSave">新增个人话术类型</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/cm/wordsType/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form>
	<input id="repMsg" name="repMsg" style="display:none" value="${repMsg}"/>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>排序</th>
				<th>话术类型</th>
				<th>创建组织</th>
				<th>创建人</th>
				<shiro:hasPermission name="cm:wordsType:edit">
				<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>		
				<td>
				  	 ${item.seq}
				</td>
				<td>
					 ${item.typeName}
				</td>
				<td>
					<c:choose>
						<c:when test="${empty item.officeName}">
							总管账号
						</c:when>
						<c:otherwise>
							${item.officeName}
						</c:otherwise>
					</c:choose>	
				</td>
				<td>
					${item.createId}
				</td>
				
				<!-- 总管账号创建内容不允许修改删除 -->
				<c:choose>
					<c:when test="${empty merchantNo}">
						<td>
							&nbsp;&nbsp;<a href="javascript:;" class="typeSave" data-code="${item.code}">修改</a>&nbsp;&nbsp;
							&nbsp;&nbsp;<a href="${ctx}/cm/wordsType/delete?code=${item.code}&typeName=${item.typeName}" onclick="return confirmx('删除话术类型后，对应话术类型下的所有话术会被删除。仍确认要删除该话术类型吗？', this.href)">删除</a>&nbsp;&nbsp;
						</td>
					</c:when>
					<c:otherwise>
						<td>
							<c:if test="${not empty item.merchantNo}">
								&nbsp;&nbsp;<a href="javascript:;" class="typeSave" data-code="${item.code}">修改</a>&nbsp;&nbsp;
								&nbsp;&nbsp;<a href="${ctx}/cm/wordsType/delete?code=${item.code}&typeName=${item.typeName}" onclick="return confirmx('删除话术类型后，对应话术类型下的所有话术会被删除。仍确认要删除该话术类型吗？', this.href)">删除</a>&nbsp;&nbsp;
							</c:if>
						</td>
					</c:otherwise>
				</c:choose>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>