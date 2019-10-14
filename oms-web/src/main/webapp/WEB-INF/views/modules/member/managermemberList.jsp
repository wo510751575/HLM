<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>店长管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	
$(document).ready(function() {
	var jBoxConfig = {};
	jBoxConfig.defaults = {
		width: 380,
	    height: 'auto',
	}
	$.jBox.setDefaults(jBoxConfig);
	
	var importMsg=$("#importMsg").val();
	if(importMsg){
		$.jBox.success(importMsg, '提示');
		$("#importMsg").val("");
	}
	
	var repMsg=$("#repMsg").val();
	if(repMsg){
		showTip(repMsg);
		$("#repMsg").val("");
	}
	
	//终端详情
	$('.view_btn').click(function() {
		var shopNo = $(this).attr("data-shopNo");
		
		// 正常打开	
		top.$.jBox.open("iframe:${ctx}/member/shop/view?shopNo="+shopNo,"终端详情", 800, 730,{//宽高
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
	
	$("#btnImport").click(function(){
		$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
			bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
	});
	$("#btnExport").click(function(){
		top.$.jBox.confirm("确认要导出店长数据吗？","系统提示",function(v,h,f){
			if(v=="ok"){
				$("#searchForm").attr("action","${ctx}/member/managermember/export");
				$("#searchForm").submit();
			}
		},{buttonsFocus:1});
	});
});
	
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/member/managermember/");
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
<input id="repMsg" name="repMsg" style="display:none" value="${repMsg}"/>
<input id="importMsg" name="importMsg" style="display:none" value="${importMsg}"/>
<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/member/managermember/import" method="post" enctype="multipart/form-data"
			style="padding-left:20px;text-align:center;" class="form-search" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/member/managermember/import/template">下载模板</a>
		</form>
	</div>

	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/member/managermember/">店长列表</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/member/managermember/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>店长姓名：</label>
				<input type="text" name="memberName" class="input-medium" maxlength="100" value="${paramManager.memberName}" placeholder="姓名">
			</li> 
			<li><label>手机号：</label>
				<input type="text" name="mobile" class="input-medium" maxlength="11" value="${paramManager.mobile}" placeholder="手机号">
			</li>
       <li>
        <label>所属区域：</label>
				<select name="areaCode"  style="width: 177px;">
					<option value="">全部</option>
					<c:forEach items="${fns:getDictList('erp_dict_1')}" var="item">
						<option value="${item.value}"  <c:if test="${item.value eq paramManager.areaCode}">selected="selected"</c:if>>${item.label}</option>
					</c:forEach>					
				</select>
			</li>
		</ul>
		<ul class="ul-form">
			<li><label>所属终端：</label>
		    	<input type="text" name="memberNameShop" class="input-medium" maxlength="100" placeholder="所属终端" value="${paramManager.memberNameShop}">
			</li>
			<li><label>状态：</label>
				<select  style="width: 177px;" id="statusSec" name="status">
                        <option value="">全部</option>
                       <c:forEach items="${memberStatus}" var="item">
							<option value="${item}" <c:if test="${item eq paramManager.status}">selected="selected"</c:if> >${item.name}</option>
						</c:forEach>
                    </select>
			</li>
			<li>
				<label>入职时间：</label>
				<input id="startTime" name="startTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${paramManager.startTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				--
				<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${paramManager.endTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>&nbsp;&nbsp;
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li class="btns"><input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			   
				<th>员工姓名</th>
				<th>手机号</th>
				<th>手机串码</th>
				<th>微信账号</th>
				<th>性别</th>
				<th>入职时间</th>
				<th>所属区域</th>
			<!-- 	<th>职位</th>  -->
				<th>所属终端</th>
				<th>状态</th>
				<shiro:hasPermission name="member:managermember:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="member" varStatus="number"> 
			<tr>
				
			    <td>
					<a href="${ctx}/member/managermember/form?code=${member.code}">${member.memberName}</a>
				</td>
				<td>
					${member.mobile}
				</td>
				<td>
					${member.imei}
				</td>
				<td>
					${member.noWx}
				</td>
				<td>
					<c:forEach items="${genders}" var="item">
				 		<c:if test="${item eq member.sex}">${item.name}</c:if>
					</c:forEach>
				</td>
				<td>
					<fmt:formatDate value="${member.workDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>				
					${fns:getDictLabel(member.areaCode, "erp_dict_1", "")}		
				</td>
				<td>
					<%-- <a href="${ctx}/member/shop?shopName=${member.memberNameShop}">${member.memberNameShop}</a> --%>
					<a href="javascript:;" class="view_btn" data-shopNo="${member.memberNoShop}">${member.memberNameShop}</a>
				</td>

					<td id="status">
					 <c:forEach items="${memberStatus}" var="item" >
					 		<c:if test="${item eq member.status}">
					 		 <c:if test="${member.status ne 'NORMAL'}">
					 		  <font color="red"> ${item.name}</font>
					 		 </c:if>
					 		 <c:if test="${member.status == 'NORMAL'}">
					 		 ${item.name}
					 		 </c:if>
					 		</c:if>
						</c:forEach>   
				</td>
				
				<shiro:hasPermission name="member:managermember:edit"><td>
					<a href="${ctx}/member/managermember/form?code=${member.code}">修改</a>
					 <a href="${ctx}/member/managermember/status?code=${member.code}&memberNo=${member.memberNo}&mobile=${member.mobile}&status=${member.status eq 'FREEZE'?'NORMAL':'FREEZE'}"onclick="return confirmx('确认要${member.status eq 'FREEZE'?'解冻':'冻结'}该员工账号吗？', this.href)">${member.status eq 'FREEZE'?'解冻':'冻结'}</a>
				   <%--   <a href="${ctx}/member/managermember/promotion?code=${member.code}&shopNo=${member.memberNoShop}&mobile=${member.mobile}" onclick="return confirmx('确认要进行升职操作吗？', this.href)">升职</a> --%>
				      <a href="${ctx}/member/managermember/demotion?code=${member.code}&shopNo=${member.memberNoShop}&memberNo=${member.memberNo}&mobile=${member.mobile}" onclick="return confirmx('确认要进行降职操作吗？', this.href)">降职</a>
				      <a href="${ctx}/member/managermember/dimission?code=${member.code}&memberNo=${member.memberNo}&mobile=${member.mobile}&shopNo=${member.memberNoShop}" onclick="return confirmx('确认要进行离职操作吗？', this.href)">离职</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>