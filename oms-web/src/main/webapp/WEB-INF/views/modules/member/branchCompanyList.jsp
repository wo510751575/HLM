<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>经销商</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			//重置
			$("#btnReset").click(function() {
				$("#searchForm").find("input[type='text']").val("");
				$("#searchForm").find("select option").prop('selected', '');
			});
			//查看终端列表
			$('.shopList').click(function() {
				var companyNo = $(this).data("companyno");
				
				// 正常打开	
				top.$.jBox.open("iframe:${ctx}/member/shop/list?companyNo="+companyNo,"终端列表", 1200, 760,{//宽高
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
			$("#searchForm").attr("action","${ctx}/member/branchCompany");
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
	.tree-set{
		width: 117px;
	}
	</style>
</head>
<body>
<div class="container">
	
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/member/branchCompany">经销商列表</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/member/branchCompany" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<c:set var="beginIndex" value="${(page.pageNo - 1) * page.pageSize}"></c:set>
		<ul class="ul-form">

			<li><label>经销商代码：</label>
		    	<input type="text" name="dealerCode" value="${findBranchCompanyPage.dealerCode}" class="input-medium" maxlength="100" placeholder="经销商代码">
			<li><label>经销商名称：</label>
		    	<input type="text" name="companyName" value="${findBranchCompanyPage.companyName}" class="input-medium" maxlength="100" placeholder="经销商名称">
			</li>
			<li><label>芝华仕业务对接人：</label>	
				<input type="text" name="businessPerson" class="input-medium" maxlength="100" placeholder="业务对接人" value="${findBranchCompanyPage.businessPerson}">
			</li>
			<li><label>状态：</label>
				<select class="selectEnum"  name="status" id="status" style="width:80px">
                    <option value="">全部</option>
					<option value="NORMAL" <c:if test="${'NORMAL' eq findBranchCompanyPage.status}">selected="selected"</c:if> >正常</option>
					<option value="CANCEL" <c:if test="${'CANCEL' eq findBranchCompanyPage.status}">selected="selected"</c:if> >注销</option>
                </select>
			</li>
			<%-- <li><label>公司类型：</label>
				<select class="selectEnum"  name="companyType" id="companyType">
                    <option value="">全部</option>
                    <c:forEach items="${CompanyType}" var="item">
						<option value="${item.code}" <c:if test="${item.code eq findBranchCompanyPage.status}">selected="selected"</c:if> >${item.name}</option>
					</c:forEach>
                </select>
			</li> --%>
			<li><label>注册时间：</label>
				<input id="registerTimeBegin" name="registerTimeBegin" type="text" readonly="readonly" maxlength="20" style="width:150px" class="input-mini Wdate"
 				value="<fmt:formatDate value="${findBranchCompanyPage.registerTimeBegin}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> 
				<input id="registerTimeEnd" name="registerTimeEnd" type="text" readonly="readonly" maxlength="20" style="width:150px" class="input-mini Wdate" 
 				value="<fmt:formatDate value="${findBranchCompanyPage.registerTimeEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>&nbsp;&nbsp;
			</li>
			<li><label style="margin-left: 159px;">所在地区：</label>
				<tags:treeselect id="area" name="areaId" value="${areaId}" labelName="areaName" labelValue="${areaName}"
					title="所在地区" url="/sys/area/treeData" cssClass="required tree-set"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>
			<li class="btns"><input id="btnReset" class="btn btn-primary" type="button" value="重置"/></li>
			<li class="clearfix"></li>
		</ul> 
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>经销商名称</th>
				<th>经销商代码</th>
				<th>状态</th>
				<th>所在区域</th>
				<th>法人姓名</th>
				<th>芝华仕业务对接人</th>
				<th>负责人姓名</th>
				<th>负责人电话</th>
				<th>注册时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
				<td>
					${beginIndex + number.count}
				</td>
				<td>
					${item.companyName}				
				</td>
				<td>
					${item.dealerCode}			
				</td>
				<td>
					<c:if test="${item.status=='NORMAL'}">正常</c:if>
					<c:if test="${item.status=='CANCEL'}">注销</c:if>				
				</td>
				<%-- <td>
					<c:if test="${item.companyType eq 1}">分公司</c:if>
					<c:if test="${item.companyType eq 2}">经销商</c:if>				
				</td> --%>
				<td>
					${item.provinceName}${item.cityName}
				</td>
				<td>
					${item.legalPersonName}
				</td>
				<td>
					${item.businessPerson}
				</td>
				<td>
					${item.dealerResponsiblePerson}
				</td>
				<td>
					${item.dealerResponsibleMobile}
				</td>
				<td>
					<fmt:formatDate value="${item.registerTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				<shiro:hasPermission name="member:branchCompany:view">	
					<a href="${ctx}/member/branchCompany/view?code=${item.code}">查看详情</a>&nbsp;
					<a href="${ctx}/member/companyBankAccount?companyNo=${item.companyNo}&branchCompanyCode=${item.code}">银行账户</a>&nbsp;
				</shiro:hasPermission>
				<shiro:hasPermission name="member:shop:view">
					<a href="javascript:;" class="shopList"  data-companyno="${item.companyNo}">终端列表</a>
				</shiro:hasPermission>	
				<shiro:hasPermission name="member:branchCompany:edit">	
					<a href="${ctx}/member/branchCompany/form?code=${item.code}">编辑</a>&nbsp;
				</shiro:hasPermission>			
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
</div>	
</body>
</html>