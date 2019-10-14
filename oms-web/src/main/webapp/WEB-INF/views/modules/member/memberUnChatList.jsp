<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>未联系客户管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/common/select2.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript">
		$(document).ready(function() {
			
			//重置
			$("#btnReset").click(function() {
				$("#searchForm").find("input[type='text']").val("");
				$("#searchForm").find("select option").prop('selected', '');
				$(".form-group-select-css").select2("val","");
			});
			
			$('.form-group-select-css').select2();
			
			$(".setFlag").click(function(){
				var flag=$(this).data('flag');
				$("#flag").val(flag);
				page();
			});
			
		  //聊天记录验证
		$(".close").click(function(){
			$("#passwords").hide();
			});
		
		$(".btn-default").click(function(){
			 var noWx=$(this).attr("data-wx");
	    	 var memberNoGm=$(this).attr("data-memberNoGm");
			var html = "<div style='padding:10px;'>查看密码：<input type='password' id='password' name='password' /></div>"; 
			  $.jBox(html, { title: "请输入查看密码？", submit: function (v, h, f) {
			      if (f.password == '') {
			          $.jBox.tip("请输入您的密码。", 'error', { focusId: "password" }); // 关闭设置 yourname 为焦点
			          return false;
			      }else{
			    	  
			    	  var password=$("#password").val();
					  $.ajax({
		                  type:"POST",
		                  url:"${ctx}/weixin/chatInfo/psw",
		                  data:{psw:password},
		                  success:function(result){
		                      if(result){
		                          window.location.href="${ctx}/weixin/imChatInfo?talker= "+noWx+"&memberNoGm="+memberNoGm
		                        		  +"&startTime=<fmt:formatDate value='${paramMember.chatBeginTime}' pattern='yyyy-MM-dd HH:mm:ss'/>"
		                        		  +"&endTime=<fmt:formatDate value='${paramMember.chatEndTime}' pattern='yyyy-MM-dd HH:mm:ss'/>"
		                        		  ;
		                      }else{
		                          showTip("密码错误！","error");
		                      }
		                  }
		              });  
			      }
			      return true;
			  	}
			  });	
		  });
		});
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/member/unChatlist")
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
#wechart_info .personal_header .hrf{
    width:35px;
    height:18px;
    margin:0px 15px;
    background:url("${ctxStatic}/images/hrf.png") no-repeat 50%;
}
	.container{
	padding: 10px 30px;
    width: 100%;
    min-height: 800px;
    background: #fff;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
	}
	.fix{
	position:fixed; 
	top:0; 
	width:1500px; 
	height: 130px; 
	background-color: #FFF;
	border-color: #FFF;
	z-index: 9999px;
	}
	.table th{
		width: 150px !important;
		white-space: normal;
	}
	.table td{
		box-sizing: border-box;
		width: 150px !important;
		word-break: break-all !important;
	}
	</style>
</head>
<body>
<div class="container">
	<div class="fix" >
	<ul class="nav nav-tabs">
		<li class="tabs <c:if test="${empty paramMember.flag ||paramMember.flag == 0}">active</c:if>"><a href="javaScript:;" class="setFlag" data-flag="0">24小时内新增未联系客户</a></li>
		<li class="tabs <c:if test="${paramMember.flag == 1}">active</c:if>"><a href="javaScript:;" class="setFlag" data-flag="1">超过24小时未联系客户</a></li>
		<li class="tabs <c:if test="${paramMember.flag == 2}">active</c:if>"><a href="javaScript:;" class="setFlag" data-flag="2">超过7天未联系客户</a></li>
		<li class="tabs <c:if test="${paramMember.flag == 3}">active</c:if>"><a href="javaScript:;" class="setFlag" data-flag="3">超过14天未联系客户</a></li>
		<li class="tabs <c:if test="${paramMember.flag == 4}">active</c:if>"><a href="javaScript:;" class="setFlag" data-flag="4">超过1个月未联系客户</a></li>
	</ul>
	
	<form id="searchForm" action="${ctx}/member/unChatlist/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="flag" name="flag" type="hidden" value="${paramMember.flag}"/>
		<c:set var="beginIndex" value="${(page.pageNo - 1) * page.pageSize}"></c:set>
		<ul class="ul-form">
		    <li><label>客户姓名：</label>
		    	<input type="text" name="memberName" value="${paramMember.memberName}" class="input-medium" maxlength="100" placeholder="客户姓名">
			</li>
			<li><label>手机号：</label>
				<input type="text" name="mobile" value="${paramMember.mobile}" class="input-medium" maxlength="11" placeholder="手机号">
			</li>
			<li><label>所属终端：</label>
				<select name="shopNo" id="shopNo" class="required form-group-select-css"  style="width:177px">
					<option value="">全部</option>
					<c:forEach items="${shops}" var="item">
					<option value="${item.shopNo}" class="MaterialInfo" <c:if test="${item.shopNo eq paramMember.shopNo}">selected="selected"</c:if> >${item.shopName}</option>
					</c:forEach>
				</select>
			</li>
			<%-- <li>
				<label>注册时间：</label>
				<input id="registerBeginTime" name="registerBeginTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${paramMember.registerBeginTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				-- 
				<input id="registerEndTime" name="registerEndTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${paramMember.registerEndTime}" pattern="yyyy-MM-dd"/>" 
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"
				/>&nbsp;&nbsp;
			</li> --%>
			<li><label>客户来源：</label>
				<select style="width: 177px;" name="memberSrc">
                    <option value="">全部</option>
                    <c:forEach items="${memerSources}" var="item">
						<option value="${item}"
							<c:if test="${item eq param.memberSrc}">selected="selected"</c:if>>${item.name}</option>
					</c:forEach>
                </select>
				</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>
			<li class="btns"><input id="btnReset" class="btn btn-primary" type="button" value="重置"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<table>
		<thead class="table table-condensed">
			<tr>
				<th>序号</th>
				<th>头像</th>
				<th>客户姓名</th>
				<th>微信昵称</th>
				<th>手机号</th>
				<th>所属终端</th>
				<th>客户来源</th>
				<th>注册时间</th>
				<th>聊天记录数</th>
			</tr>
		</thead>
	</table>
	</div>
	<div style="height: 130px;"></div>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-condensed" style="width: 1500px;">
		<!-- <thead>
			<tr>
				<th>序号</th>
				<th>头像</th>
				<th>客户姓名</th>
				<th>微信昵称</th>
				<th>手机号</th>
				<th>所属终端</th>
				<th>客户来源</th>
				<th>注册时间</th>
				<th>聊天记录数</th>
			</tr>
		</thead> -->
		<tbody id="infolist">
		<c:forEach items="${page.list}" var="member" varStatus="number"> 
			<tr>
				<td>
					${beginIndex + number.count}
				</td>
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
				</td>
				
			    <td >
					${member.memberName}
				</td>
				<td>
					${member.nickNameWx}
				</td>
				<td>
					${member.mobile}
				</td>
				<%-- <td>
					<c:if test="${member.sex=='MALE'}">男</c:if> 
					<c:if test="${member.sex=='FEMALE'}">女</c:if> 
					<c:if test="${member.sex=='UNKNOWN'}">未知</c:if> 
				</td> --%>
				<td>
					<c:forEach items="${memerSources}" var="item">
						<c:if test="${item eq member.memberSrc}">${item.name}</c:if>
					</c:forEach>
				</td>
				<td>
					<fmt:formatDate value="${member.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<a href="javascript:;" data-wx="${member.noWx}" data-memberNoGm="${member.memberNoGm}" class="btn-default">${member.chatCount}</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<%-- <div class="pagination">${page}</div> --%>
</div>	
</body>
</html>