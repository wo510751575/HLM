<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>导购列表</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/jquery/jquery.form.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".allot").click(function(){
				var memberName=$(this).data("membername")||'';
				var code =  $(this).data("code");
				var memberNoGm =  $(this).data("membernogm");
				if (confirm("确认要将此客户分配给导购"+memberName+"吗？")){
					$("#allotForm").attr("action","${ctx}/member/allot?code="+code+"&memberNoGm="+memberNoGm+"&memberNameGm="+memberName);
					$("#allotForm").ajaxSubmit(function(option){
						window.parent.frames[0].location.href="${ctx}/member/";//刷新指定iframe
						top.$.jBox.tip(option.msg, option.type==1?"error":"success");//提示信息
						top.$.jBox.close(true);//关闭窗口
					});
					
				}
			})
		});
	</script>
	<style type="text/css">
		.img-circle {
    width: 40px;
    height: 40px;
    border-radius: 20px;
    vertical-align: middle;
}
	.container{
	padding: 10px 30px;
    width: 100%;
    min-height: 800px;
    background: #fff;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
	}
		#myModal{
		position: fixed;
	    top: 10%;
	    left: 50%;
	    z-index: 1050;
	    width: 560px;
	    margin-left: -280px;
	    background-color: #fff;
	    border: 1px solid #999;
	    border: 1px solid rgba(0,0,0,0.3);
	    -webkit-border-radius: 6px;
	    -moz-border-radius: 6px;
	    border-radius: 6px;
	    outline: 0;
	    -webkit-box-shadow: 0 3px 7px rgba(0,0,0,0.3);
	    -moz-box-shadow: 0 3px 7px rgba(0,0,0,0.3);
	    box-shadow: 0 3px 7px rgba(0,0,0,0.3);
	    -webkit-background-clip: padding-box;
	    -moz-background-clip: padding-box;
	    background-clip: padding-box;
		}
		
	</style>
</head>
<body>
<div class="container">
	
	<form id="searchForm" action="${ctx}/member/allotView" method="post" class="breadcrumb form-search">
		<input id="code" name="code" type="hidden" value="${personMemberCode}"/>
	</form>
	<form id="allotForm" action="${ctx}/member/allot" method="post" >
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>导购姓名</th>
				<th>手机号</th>
				<shiro:hasPermission name="member:member:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="infolist">
		<c:forEach items="${allotList}" var="guidMember" varStatus="number"> 
			<tr>
			    <td>
					${guidMember.memberName}
				</td>
				<td>
					${guidMember.mobile}
				</td>
				<shiro:hasPermission name="member:member:edit"><td>
					<a href="javascript:;" data-membername="${guidMember.memberName}" data-code="${personMemberCode}" data-membernogm="${guidMember.memberNo}" class="allot">分配</a> 
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>	
	
</body>
</html>