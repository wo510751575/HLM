<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>暂停跟进客户</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {

	
		  
		});
		function allchk(){
			var chknum = $("#infolist :checkbox").size();//选项总个数
			var chk = 0;
			$("#infolist :checkbox").each(function () {  
		        if($(this).prop("checked")==true){
					chk++;
				}
		    });
			if(chknum==chk){//全选
				$("#allcheck").prop("checked",true);
			}else{//不全选
				$("#allcheck").prop("checked",false);
			}
		}
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
	</style>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/member/giveUpList/">暂停跟进客户</a></li>
		<li><a href="${ctx}/member/abandonList/">暂停跟进客户记录</a></li>
	</ul>
	
	
	<form id="searchForm" action="${ctx}/member/abandonList/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		    <li><label>客户姓名：</label>
		    	<input type="text" name="memberName" value="${findPmAbandonPage.memberName}" class="input-medium" maxlength="100" placeholder="客户姓名">
			</li>
		 <li>
				<label>暂停时间：</label>
				<input id="startTime" name="startTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${findPmAbandonPage.startTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				-- 
				<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${findPmAbandonPage.endTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>&nbsp;&nbsp;
			</li>
		</ul>
		<ul class="ul-form">
			<li><label>所属导购：</label>
				<input type="text" name="memberNameGm" value="${findPmAbandonPage.memberNameGm}" class="input-medium" maxlength="100" placeholder="导购姓名">
			</li>
			<li><label>审批状态：</label>
				<select style="width: 177px;" name="checkResult">
                    <option value="">全部</option>
                    <c:forEach items="${CheckResult}" var="item">
						<option value="${item}"
							<c:if test="${item eq findPmAbandonPage.checkResult}">selected="selected"</c:if>>${item.name}</option>
					</c:forEach>
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
				<th style="min-width: 45px;">头像</th>
				<th>客户姓名</th>
				<th>微信昵称</th>
				<th>所属导购</th>
				<th>暂停原因</th>
				<th>回访时间</th>
				<th>审批人</th>
				<th>审批时间</th>
				<th>审批状态</th>
				<th>所需产品</th>
				<th>暂停时间</th>
			</tr>
		</thead>
		<tbody id="infolist">
		<c:forEach items="${page.list}" var="member" varStatus="number"> 
			<tr>
				<td>
						<c:if test="${empty member.headAddress}">
							<img class="img-circle" src="${ctxStatic}/admin/images/introduce/file.png">	
						</c:if>
						<c:if test="${not empty member.headAddress}">
							<c:if test="${fns:startsWith(member.headAddress,'http')}">
								<img class="img-circle" src="${member.headAddress}">
							</c:if>
							<c:if test="${!fns:startsWith(member.headAddress,'http')}">
								<img class="img-circle" src="${fns:getUploadUrl()}${member.headAddress}">
							</c:if>
						</c:if> 
					</a>
				</td>
				
				<td>
				${member.memberName}
				</td>
				<td>
				${member.nickNameWx}
				</td>
				<td>
				${member.memberNameGm}
				</td>
				<td>
				${member.resean}
				</td>
				<td>
				 <fmt:formatDate value="${member.followDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				${member.checker}
				</td>
				<td>
				<fmt:formatDate value="${member.checkDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:forEach items="${CheckResult}" var="item">
				 		<c:if test="${item eq member.checkResult}">${item.name}</c:if>
					</c:forEach>
				</td>
				<td>
				${member.bomName}
				</td>
				<td>
				<fmt:formatDate value="${member.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
</div>	
</body>
</html>