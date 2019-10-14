<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报表项目管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//跟进事项选择
			$('.view_btn').click(function() {
				var id = $(this).attr("data-id");
				var type = $(this).attr("data-type");
				
				// 正常打开	
				top.$.jBox.open("iframe:${ctx}/baseConfig/stList/cfAnalyzeChoose","选择跟进事项", 920, 730,{//宽高
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
			//日工作简表
			$('.view_btn1').click(function() {
				var id = $(this).attr("data-id");
				var type = $(this).attr("data-type");
				
				// 正常打开	
				top.$.jBox.open("iframe:${ctx}/baseConfig/stList/workBrDayChoose","日工作简报", 920, 730,{//宽高
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
			//运营日报
			$('.view_btn2').click(function() {
				var id = $(this).attr("data-id");
				var type = $(this).attr("data-type");
				
				// 正常打开	
				top.$.jBox.open("iframe:${ctx}/baseConfig/stList/operationDayChoose","运营日报", 920, 730,{//宽高
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
			//优秀导购
			$('.view_btn3').click(function() {
				var id = $(this).attr("data-id");
				var type = $(this).attr("data-type");
				
				// 正常打开	
				top.$.jBox.open("iframe:${ctx}/baseConfig/stList/bestGmChoose","优秀导购", 920, 730,{//宽高
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
			//运营分析
			$('.view_btn4').click(function() {
				var id = $(this).attr("data-id");
				var type = $(this).attr("data-type");
				
				// 正常打开	
				top.$.jBox.open("iframe:${ctx}/baseConfig/stList/operationAnalysisDayChoose","运营分析", 920, 730,{//宽高
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
		
			/* $("#pageNo").val(1);
			$("#searchForm").submit(); */
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
		<li class="active"><a href="${ctx}/baseConfig/stList/">报表项目列表</a></li>
		<li><a href="javascript:;" class="view_btn" >跟进分析选择</a></li>
		<li><a href="javascript:;" class="view_btn1" >日工作简报选择</a></li>
		<li><a href="javascript:;" class="view_btn2" >运营日报选择</a></li>
		<li><a href="javascript:;" class="view_btn3" >优秀导购选择</a></li>
		<li><a href="javascript:;" class="view_btn4" >运营分析选择</a></li>
	<%-- 	<c:if test="${fns:getUser().admin}">  
		<li><a href="${ctx}/baseConfig/stList/initializePmTypeTota">初始化统计数据</a></li>
		</c:if> --%>
	</ul>
	<form id="searchForm" action="${ctx}/baseConfig/stList/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
			<label>项目名称：</label>       
			<input type="text" name="nameList" class="input-medium" maxlength="100" value="${findStListPage.nameList}" placeholder="项目名称">
			</li> 	
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>  
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>项目名称</th>
				<th>项目描述</th>
				<th>项目类型</th>
				<th>项目单位</th>
				<th>状态</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
				<td>
					${item.nameList}
				</td>
				<td>
					${item.desList}
				</td>	

				 <td>
				 <c:forEach items="${TypeLists}" var="type">
				 <c:if test="${item.typeList eq type}">${type.name}</c:if>
				 </c:forEach>
			    </td>
			    
			    <td>
				<c:forEach items="${unitLists}" var="status">
				<c:if test="${item.unitList eq status}">${status.name}</c:if>
				</c:forEach>
			   </td>
			    
				<td>   
					<span class="label label-${item.status eq 'Y'?'success':'danger'}">${item.status eq 'Y'?'启用':'禁用'}</span>
				</td>			
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>