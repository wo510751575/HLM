<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户标签管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var repMsg=$("#repMsg").val();
			if(repMsg){
				showTip(repMsg);
				$("#repMsg").val("");
			}
			
			//全选、全不选
			$("#allcheck").click(function() {
				if (this.checked) {
					$("#infolist :checkbox").prop("checked", true);
					$("#btnDel").prop("disabled", false).removeClass("disabled");
				} else {
					$("#infolist :checkbox").prop("checked", false);
					$("#btnDel").prop("disabled", true).addClass("disabled");
				}
			});

			//设置全选复选框
			$("#infolist :checkbox").click(function() {
				allchk();
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
		
// 			$("#pageNo").val(1);
// 			$("#searchForm").submit();
        }
		function allchk() {
			var chknum = $("#infolist :checkbox").size();//选项总个数
			var chk = 0;
			$("#infolist :checkbox").each(function() {
				if ($(this).prop("checked") == true) {
					chk++;
				}
			});
			if (chknum == chk) {//全选
				$("#allcheck").prop("checked", true);
			} else {//不全选
				$("#allcheck").prop("checked", false);
			}
			if(chk>0){
				$("#btnDel").prop("disabled", false).removeClass("disabled");
			}else{
				$("#btnDel").prop("disabled", true).addClass("disabled");
			}
		}
		function del(){
			var checkID = [];//定义一个空数组
			$("#infolist input:checkbox:checked").each(function(i){//把所有被选中的复选框的值存入数组
			 	checkID[i] =$(this).val();
			});
			if(checkID.length <=0){
				alertx("至少选择一个！");
			}
			
			confirmx("确认删除？", function(){
				$.ajax({
					   type:"POST",
				        url:"${ctx}/member/pmLabel/del",
				        data:{codes : checkID.join(",")},
				        success:function(result){
			        		showTip(result.msg,result.type);
			        		setTimeout(function() {
			        			window.location.reload();
							},2000);
				        }
				   });
			}, closed);
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
		<li class="active"><a href="${ctx}/member/pmLabel/">客户标签列表</a></li>
		<shiro:hasPermission name="member:pmLabel:edit">
			<li><a href="${ctx}/member/pmLabel/form">新增客户标签</a></li>
		</shiro:hasPermission>
	</ul>
	<form id="searchForm" action="${ctx}/member/pmLabel/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label style="width: 100px;">标签名称：</label>
				<input type="text" name="labelName" class="input-medium" maxlength="100" value="${findPmLabelPage.labelName}" placeholder="标签名称">
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<shiro:hasPermission name="member:pmLabel:edit">
			<li class="btns"><input id="btnDel" class="btn btn-primary disabled" type="button" onclick="del()" value="批量删除" disabled="disabled"/></li>
			</shiro:hasPermission>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="reset" onclick="CancelQuery()" value="取消查询"/></li> -->
			<li class="clearfix"></li>
		</ul>
	</form>
	<%-- <tags:message content="${message}"/> --%>
	<input id="repMsg" name="repMsg" style="display:none" value="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width:10px;"><label for="allcheck">全选<input type="checkbox" id="allcheck" /></label></th>
				<th>标签名称</th>
				<th>描述</th>
				<shiro:hasPermission name="member:pmLabel:edit">
				<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="infolist">
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
				<td>
					<input type="checkbox" value="${item.code}" />
				</td>
				<td>
					${item.labelName}
				</td>
				<td>
					${item.remark}
				</td>
				<shiro:hasPermission name="member:pmLabel:edit">
				<td>
					<a href="${ctx}/member/pmLabel/form?code=${item.code}">修改</a>
				</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>