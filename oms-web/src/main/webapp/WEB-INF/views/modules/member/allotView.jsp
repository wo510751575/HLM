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
				var contentTable = window.parent.frames["mainFrame"].document.getElementById("contentTable");
				//获取勾选的值
				var valArr = new Array;
				$(contentTable).children("#infolist").find(":checkbox[checked]").each(function(i){
					valArr[i] = $(this).val();
			    });  
				
				var code = valArr.join(',');//转换为逗号隔开的字符串
				if(!code){
					alertx("请选择客户!");
					return false;
				}
				
				var sourceFlag=$(this).data("source")||'';
				var memberName=$(this).data("membername")||'';
				var memberNoGm =  $(this).data("membernogm");
				if (confirm("确认要将客户分配给导购"+memberName+"吗？")){
					$("#memberNoGm").val(memberNoGm);
					$("#code").val(code);
					$("#allotForm").ajaxSubmit(function(option){
						window.parent.frames[0].location.href="${ctx}/member/addFriends/"+sourceFlag;//刷新指定iframe
						showTip3(option.msg);
					});
					
				}
			});
		});
		//提示并延迟关闭
		function showTip3(mess) {
			resetTip();
        	setTimeout(function(){
        		top.$.jBox.tip(mess, 'info' , {opacity:0,timeout:2000});
        	}, 500,setTimeout(function(){
				top.$.jBox.close(true);//关闭窗口
			},2000));
		}
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
	
	<form id="searchForm"  action="${ctx}/member/addFriends/view" method="post" class="breadcrumb form-search">
		<input id="sourceFlag" name="sourceFlag" type="hidden" value="${sourceFlag}"/>
		<input id="noWxGm" name="noWxGm" type="hidden" value="${data.noWx}"/>
		<ul class="ul-form">
		    <li><label>导购：</label>
		    	<input type="text" name="searchVal" id="searchVal" value="${data.searchVal}" class="input-medium" maxlength="100" >
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" /></li>
		</ul>
	</form>
	<form id="allotForm" action="${ctx}/member/addFriends/allot" method="post" >
		<input id="memberNoGm" name="memberNoGm" type="hidden"/>
		<input id="code" name="code" type="hidden" />
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>导购姓名</th>
				<th>登录帐号</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="infolist">
		<c:forEach items="${list}" var="data" varStatus="number"> 
			<tr>
			    <td>
					${data.assistantName}
				</td>
				<td>
					${data.loginName}
				</td>
				<td>
					<a href="javascript:;" 
					data-membername="${data.assistantName}" data-source="${sourceFlag}" data-membernogm="${data.assistantNo}" 
					class="allot">分配</a> 
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>	
	
</body>
</html>