<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>导购列表</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/jquery/jquery.form.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#submitBtn").click(function(){
				var code = '${code}';
				var noWxGm = $('#noWxGmSel').val();
				var wxAddType = '${wxAddType}';
				
				$("#addWxFriendsForm").attr("action","${ctx}/member/addWxFriends/edit?code="+code+"&noWxGm="+noWxGm+"&wxAddType="+wxAddType);
				$("#addWxFriendsForm").ajaxSubmit(function(option){
					window.parent.frames[0].location.href="${ctx}/member/addWxFriends/list?addWxFriends=1";//刷新指定iframe
					setTimeout(function(){
						top.$.jBox.tip(option.msg, option.type==1?"error":"info", {opacity:0,timeout:2000});//提示信息
					},2000);
					setTimeout(function(){
						top.$.jBox.close(true);//关闭窗口
					},2000);
				});
			});
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
	<form id="addWxFriendsForm" action="${ctx}/member/addWxFriends/edit" method="post" >
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>请选择终端微信号：</th>
			</tr>
		</thead>
		<tbody id="infolist">
			<tr>
			    <td>
					<select name="noWxGm" id="noWxGmSel">
						<c:forEach items="${shopTerminalList}" var="shopTerminal" varStatus="number"> 
							<option value="${shopTerminal.noWx }" <c:if test="${noWxGm eq shopTerminal.noWx}">selected</c:if> >${shopTerminal.noWx }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
			    <td style="text-align:right;">
					<input type="button" value="提交" id="submitBtn">
				</td>
			</tr>
		</tbody>
	</table>
</div>	
	
</body>
</html>