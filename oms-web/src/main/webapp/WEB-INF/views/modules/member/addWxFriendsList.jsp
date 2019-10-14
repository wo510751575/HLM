<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var repMsg=$("#repMsg").val();
			if(repMsg){
				showTip(repMsg);
				$("#repMsg").val("");
			}
			
			//客户详情
			$('.view_btn').click(function() {
				var id = $(this).attr("data-id");
				var type = $(this).attr("data-type");
				
				// 正常打开	
				top.$.jBox.open("iframe:${ctx}/member/view?code="+id+"&pmTypeType="+type,"客户详情", 920, 730,{//宽高
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
		    
			$("#btnExport").click(function(){
				if(!$("#infolist tr").length){
					alertx("没有数据无法导出!");
					return false;
				}
				top.$.jBox.confirm("确认要导出客户数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/member/exportAddWxFriends");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过10M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
			
			$("#btnBatchAddTags").click(function(){
				//获取勾选的值
				var memberNoArr = new Array;
			    $("#infolist :checkbox[checked]").each(function(i){
			    	memberNoArr[i] = $(this).val();
			    });
				var memberNos = memberNoArr.join(',');//转换为逗号隔开的字符串
				if(!memberNos){
					alertx("请选择客户!");
					return false;
				}
				
				// 正常打开	
				top.$.jBox.open("iframe:${ctx}/member/tagView?single=0&memberNos="+memberNos+"&merchantNo=${merchantNo}" ,"标签选择", 520, 400,{//宽高
					id:1111,
					draggable: true,
					showClose: true,
					buttons:{},		//去除按钮
					iframeScrolling: 'no',
					loaded:function(h){
						top.$('.jbox-container .jbox-title-panel').css("height","35px").css('background','#376EA5');
						top.$('.jbox-container .jbox-title').css('line-height','35px').css("color","#ffffff");
					},
					closed: function () { 
					} 
				});
			});
			//修改
			$(".edit_btn").click(function(){
				var code = $(this).data("id");
				var merchantNo = $(this).data("merchant");
				var noWxGm = $(this).data("nowxgm");
				var wxAddType = $(this).data("wxaddtype");
				
				// 正常打开	
				top.$.jBox.open("iframe:${ctx}/member/addWxFriends/editView?code=" + code + "&merchantNo=" + merchantNo + "&noWxGm=" + noWxGm + "&wxAddType=" + wxAddType,"编辑", 520, 230,{//宽高
					id:1102,
					draggable: true,
					showClose: true,
					buttons:{},		//去除按钮
					iframeScrolling: 'no',
					loaded:function(h){
						top.$('.jbox-container .jbox-title-panel').css("height","35px").css('background','#376EA5');
						top.$('.jbox-container .jbox-title').css('line-height','35px').css("color","#ffffff");
					},
					closed: function () { 
					} 
				});
			});
			
			//添加好友
			$(".addFriends_btn").click(function(){
				var code = $(this).data("code");
				
				var url = '${ctx}/im/doListApplayFriend';
				var params = {
						code: code
						};
				$.post(url,params,function(data){
					if(data.success) {
						$.jBox.success('好友申请已发出', '提示');
					} else {
						$.jBox.info(data.msg, '友情提示');
					}
				},'json');
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
		  //发送素材
		    $("#sendMaterial").click(function(){
				var chk = 0;
				$("#infolist :checkbox").each(function () {  
			        if($(this).prop("checked")==true){
						chk++;
					}
			    });
				if(chk==0){
					showTip("请选择要发送的客户!","info");
				}else{
					//获取勾选的值
					var valArr = new Array;
				    $("#infolist :checkbox[checked]").each(function(i){
						valArr[i] = $(this).val();
				    });
					var vals = valArr.join(',');//转换为逗号隔开的字符串
					
					//弹出框
					top.$.jBox.open("","素材选择", 920, 730, {//宽高
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
				}
		  });
		  
		  //发送短信
		  $("#sendSms").click(function(){
			  var chk = 0;
				$("#infolist :checkbox").each(function () {  
			        if($(this).prop("checked")==true){
						chk++;
					}
			    });
				if(chk==0){
					showTip("请选择要发送的客户!","info");
				}else{
					//获取勾选的值
					var valArr = new Array;
				    $("#infolist :checkbox[checked]").each(function(i){
						valArr[i] = $(this).val();
				    });
					var vals = valArr.join(',');//转换为逗号隔开的字符串
					
					//弹出框
					top.$.jBox.open("","素材选择", 920, 730, {//宽高
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
				}
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
		                      if(result=="true"){
		                          window.location.href="${ctx}/weixin/imChatInfo?talker= "+noWx+"&memberNoGm="+memberNoGm;
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
			$("#searchForm").attr("action","${ctx}/member/addWxFriends/list?addWxFriends=1");
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
		function validate() {
			$('#btnImportSubmit').attr("disabled", 'disabled');
			return false;
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
<input id="repMsg" name="repMsg" style="display:none" value="${repMsg}"/>
<div class="container">
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/member/importAddWxFriends" method="post" onsubmit="$.jBox.tip('正在处理....', 'loading');" enctype="multipart/form-data"
			style="padding-left:20px;text-align:center;" class="form-search" ><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/member/import/templateAddWxFriends">下载模板</a>
			
		</form>
	</div>
	
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/member/addWxFriends/list?addWxFriends=1">客户列表</a></li>
	</ul>
	
	<form id="searchForm" action="${ctx}/member/addWxFriends/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="addWxFriends" name="addWxFriends" type="hidden" value="1"/>
		<ul class="ul-form">
		    <li><label>客户姓名：</label>
		    	<input type="text" name="memberName" value="${paramMember.memberName}" class="input-medium" maxlength="100" placeholder="客户姓名">
			</li>
			<li><label>手机号：</label>
				<input type="text" name="mobile" value="${paramMember.mobile}" class="input-medium" maxlength="11" placeholder="手机号">
			</li>
			<li><label>QQ号：</label>
				<input type="text" name="noQQ" value="${paramMember.noQQ}" class="input-medium" maxlength="11" placeholder="QQ号">
			</li>
			<li><label>微信openID：</label>
				<input type="text" name="wxOpenId" value="${paramMember.wxOpenId}" class="input-medium" placeholder="微信openID">
			</li>
			<li><label>添加状态：</label>
				<select name="addStatus" maxlength="11">
					<c:choose>
						<c:when test="${empty paramMember.addStatus}">
							<option selected value="">全部</option>
							<option value="Y">已添加</option>
							<option value="N">未添加</option>
						</c:when>
						<c:when test="${'Y' eq paramMember.addStatus}">
							<option value="">全部</option>
							<option selected value="Y">已添加</option>
							<option value="N">未添加</option>
						</c:when>
						<c:otherwise>
							<option value="">全部</option>
							<option value="Y">已添加</option>
							<option selected value="N">未添加</option>
						</c:otherwise>
					</c:choose>
				</select>
			</li>
			<li><label>标签：</label>
				<input type="text" name="labelName" value="${paramMember.labelName}" class="input-medium" maxlength="20" placeholder="标签">
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
		</ul>
		
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>全选<input type="checkbox" id="allcheck"/></th>
				<th style="min-width: 45px;">头像</th>
				<th>客户姓名</th>
				<th>微信OpendID</th>
				<th>手机号</th>
				<th>QQ号</th>
				<th>性别</th>
				<th>标签</th>
				<th>客户来源</th>
				<th>终端微信号</th>
				
				<th>添加状态</th>
				
				<th>导入时间</th>
				<%-- <shiro:hasPermission name="member:member:edit"><th>操作</th></shiro:hasPermission> --%>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="infolist">
		<c:forEach items="${page.list}" var="member" varStatus="number"> 
			<tr>
				<td>
					<input type="checkbox" value="${member.memberNo}"/>
				</td>
				<td>
					<a  href="javascript:;" class="" data-id="${member.code}" >
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
				
			    <td >
					<a  href="javascript:;" class="" >${member.memberName}</a>
				</td>
				<td>
					${member.wxOpenId}
				</td>
				<td>
					${fn:replace(member.mobile,",","<br>")}
				</td>
				<td>
					${member.noQQ}
				</td>
				<td>
					<c:if test="${member.sex=='MALE'}">男</c:if> 
					<c:if test="${member.sex=='FEMALE'}">女</c:if> 
				</td>
				
				<td>
					${member.labelName}
				</td>
				
				<td>
					${member.memberSrc}
				</td>
				
				<td>
					${member.noWxGm}
				</td>
				
				<td>
                    	<c:if test="${'Y' eq member.addStatus}">已添加</c:if>
                    	<c:if test="${'Y' != member.addStatus}">未添加</c:if>
                </td>
				<td>
					<fmt:formatDate value="${member.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<shiro:hasPermission name="member:addWxFriends:edit">
						<c:if test="${empty member.remark4}">
							<c:if test="${'Y' != member.addStatus}">
								<a href="javascript:;" class="edit_btn" data-id="${member.code}" data-merchant="${member.merchantNo}" data-noWxGm="${member.noWxGm}" data-wxAddType="${member.wxAddType}">修改</a>
								<a href="javascript:;" class="addFriends_btn" data-code="${member.code}">添加好友</a>
							</c:if>
						</c:if>
						<c:if test="${not empty member.remark4}">
							${member.remark4}
						</c:if>
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</div>	
<script>
function load() {
	$('#importBox').hide();
}
function importForm(elem){
	$(elem).attr("disabled", 'disabled');
	$('#importForm').submit();
}
</script>
	
</body>
</html>