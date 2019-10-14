<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>店员列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/message/jquery.my-message.1.1.js"></script>
	<link rel="stylesheet"href="${ctxStatic}/message/jquery.my-message.1.1.css" type="text/css"/>
	<script type="text/javascript">
		$(document).ready(function() {  
			var jBoxConfig = {};
			jBoxConfig.defaults = {
				width: 380,
			    height: 'auto',
			}
			$.jBox.setDefaults(jBoxConfig);
			
			var repMsg=$("#repMsg").val();
			if(repMsg){
				showTip(repMsg, "error", 5000);
				$("#repMsg").val("");
			}
			
			var importMsg=$("#importMsg").val();
			if(importMsg){
				$.jBox.success(importMsg, '提示');
				$("#importMsg").val("");
			}
			
			//批量删除
			$("#batchDelete").click(function(){
				
				var ids=[];
				$.each($("#infolist :checkbox[checked='checked']"),function(i,item){  
					ids.push(item.value);
				});
				if(!ids.length){
					alertx("请选择店员");
					return false;
				}
				if(!confirm("确认删除"+ids.length+"个员工?")){
				    return false;
				}
				loading("正在删除，请稍等...");
	        	$.ajax({
	                type:"POST",
	                url:"${ctx}/member/guid/batchDelete",
	                data:{ids:ids},
	                success:function(result){
	                	closeLoading();
                		showTip3(result.msg);
	                }
	            });
			});
			
		     
			//全选、全不选
		    $("#allcheck").click(function(){
		    	  if(this.checked){   
		    	        $("#infolist :checkbox").prop("checked", true);  
		    	    }else{   
		    			$("#infolist :checkbox").prop("checked", false);
		    	    }  
		    });
		    
		  //设置全选复选框
		    $("#infolist :checkbox").click(function(){
		    	allchk();
		    });
			
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
				top.$.jBox.confirm("确认要导出店员数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/member/guid/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
			});
			
			 var returnMessages =$("#returnMessage").val();
			  if(returnMessages !=null && returnMessages!="" ){
				  var message = new MyMessage.message({
					    
					    iconFontSize: "30px", //图标大小
					    messageFontSize: "15px", //信息字体大小
					    showTime: 1000, //显示时间
					    align: "right", //显示的位置类型center,right,left
					    positions: { //放置信息距离周边的距离
					        top: "300px",
					        bottom: "300px",
					        right: "600px",
					        left: "700px"
					    },
					    message: "消息", 
					    type: "warning", //消息的类型success,error,warning默认为normal
					});
				  message.add(returnMessages,"success"); 
			  }
			
			$("#status").each(function(){
				var status=$("#status").val();
				if(status =='离职'){
				  $(this).css('background','#ff0000');
				}
				
			});
			
			
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
			$("#searchForm").attr("action","${ctx}/member/guid/");
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
		//提示并延迟刷新
		function showTip3(mess) {
			resetTip();
        	setTimeout(function(){
        		top.$.jBox.tip(mess, 'info' , {opacity:0,timeout:3000});
        	}, 500,setTimeout(function(){
        		window.location.href = '${ctx}/member/guid/';
			},3000));
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
		<form id="importForm" action="${ctx}/member/guid/import" method="post" enctype="multipart/form-data"
			style="padding-left:20px;text-align:center;" class="form-search" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/member/guid/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/member/guid/">店员列表</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/member/guid/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="returnMessage" name="returnMessage" type="hidden" value="${returnMessage}"/>
		<ul class="ul-form">
		    <li><label>员工姓名：</label>
		    	<input type="text" name="memberName" class="input-medium" maxlength="100" placeholder="店员姓名" value="${paramGuid.memberName}">
			</li>
			<li><label>手机号：</label>
				<input type="text" name="mobile" class="input-medium" maxlength="11" placeholder="手机号" value="${paramGuid.mobile}">
			</li>
           <li><label>所属区域：</label>
				<select name="areaCode"  style="width: 177px;">
					<option value="">全部</option>
					<c:forEach items="${fns:getDictList('erp_dict_1')}" var="item">
						<option value="${item.value}"  <c:if test="${item.value eq paramGuid.areaCode}">selected="selected"</c:if>>${item.label}</option>
					</c:forEach>					
				</select>
			</li>
		</ul>
		<ul class="ul-form">
			<li><label>所属终端：</label>
		    	<input type="text" name="shopName" class="input-medium" maxlength="100" placeholder="所属终端" value="${paramGuid.shopName}">
			</li>
			<li><label>状态：</label>
				<select style="width: 177px;" id="statusSec" name="status">
                        <option value="">全部</option>
                        <c:forEach items="${memberStatus}" var="item">
							<option value="${item}" <c:if test="${item eq paramGuid.status}">selected="selected"</c:if> >${item.name}</option>
						</c:forEach>
                    </select>
				</li>
			<li>
				<label>入职时间：</label>
				<input id="startTime" name="startTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${paramGuid.startTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				--
				<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${paramGuid.endTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>&nbsp;&nbsp;
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>
            <li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li class="btns"><input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
			<shiro:hasPermission name="member:guid:batchDelete">
				<li class="btns"><input id="batchDelete" class="btn btn-primary" type="button" value="批量删除"/></li>
			</shiro:hasPermission>
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<td>全选<input type="checkbox" id="allcheck"/></td>
				<th>员工姓名</th>
				<th>手机号</th>
				<!-- <th>手机串码</th> -->
				<th>工作微信号</th>
				<th>个人微信号</th>
				<th>性别</th>
		<!-- 		<th>出生年月日</th>
				<th>年龄</th> -->
				<th>入职时间</th>
				<th>所属区域</th>
			<!-- 	<th>职位</th> -->
				<th>所属终端</th>
				<th>状态</th>
				<shiro:hasPermission name="member:guid:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="infolist">
		<c:forEach items="${page.list}" var="member" varStatus="number"> 
			<tr>
				<td>
					<input type="checkbox"  class="" value="${member.memberNo}" />
				</td>
			    <td>
					<a href="${ctx}/member/guid/form?code=${member.code}">${member.memberName}</a>	
				</td>
				<td>
					${member.mobile}
				</td>
				<%-- <td>
					${member.imei}
				</td> --%>
				<td>
					${member.noWx}
				</td>
				<td>
					${member.noWxPersonal}
				</td>
				<td>
					${member.gender eq 'FEMALE'?'女':member.gender eq 'MALE'?'男':member.gender}
				</td>
	<%-- 			<td>
					1989-01-20
				</td>
				<td>
					${member.age}
				</td> --%>
				<td>
					<fmt:formatDate value="${member.workDate}" pattern="yyyy-MM-dd"/>
				</td>
				
				<td>
					${fns:getDictLabel(member.areaCode, "erp_dict_1", "")}			
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
				<shiro:hasPermission name="member:guid:edit"><td>
					<a href="${ctx}/member/guid/form?code=${member.code}&pageNo=${page.pageNo}">修改</a>	
					 <c:if test="${member.status ne 'LEAVE'}">
					<a href="${ctx}/member/guid/guidPromotion?code=${member.code}&memberNo=${member.memberNo}&shopNo=${member.shopNo}&status=${member.status}&mobile=${member.mobile}"onclick="return confirmx('确认要进行升职操作 吗？', this.href)">升职</a>						
				    </c:if>
				    <c:if test="${member.status ne 'LEAVE'}">
				 <a href="${ctx}/member/guid/status?code=${member.code}&memberNo=${member.memberNo}&status=${member.status eq 'FREEZE'?'NORMAL':'FREEZE'}"onclick="return confirmx('确认要${member.status eq 'FREEZE'?'解冻':'冻结'}该员工账号吗？', this.href)">${member.status eq 'FREEZE'?'解冻':'冻结'}</a>	
				 </c:if>
				 <c:if test="${member.status ne 'LEAVE'}">
				 <a href="${ctx}/member/guid/edit?code=${member.code}&memberNo=${member.memberNo}&status=${member.status eq 'LEAVE'?'NORMAL':'LEAVE'}"onclick="return confirmx('确认要${member.status eq 'LEAVE'?'复职':'离职'}该员工吗？', this.href)">${member.status eq 'LEAVE'?'复职':'离职'}</a>	
				</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>