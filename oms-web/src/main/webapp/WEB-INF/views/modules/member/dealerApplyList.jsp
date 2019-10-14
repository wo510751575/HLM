<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>经销商入驻</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			$(".audit_btn").click(function(){
				$("textarea[name='remark']").val('');
				var code = $(this).data("code");
				$("#code").val(code);
			});
			//modal提交
			$(".myModalSubmit").click(function(){
				var selectAudit1 = $("#auditResult1").prop("checked");
				var selectAudit2 = $("#auditResult2").prop("checked");
				
				if(!selectAudit1 && !selectAudit2){
					alertx("请选择审核意见!");
					return false;
				}
				var auditResult;
				if(selectAudit1) {
					auditResult = 1;
				} else {
					auditResult = 0;
				}
				var remark=$("#remark").val()||"";
				loading("正在提交，请稍等...");
	        	var code = $("#code").val()||"";
	        	$.ajax({
	                type:"POST",
	                url:"${ctx}/dealerapply/audit",
	                data:{code:code,auditResult:auditResult,remark:remark},
	                success:function(result){
	                	closeLoading();
	                	$(".myModalClose").click();
	                	showTip3(result.msg);
	                },
	                error : function(error) {  
	                	closeLoading();
	                	$(".myModalClose").click();
	                	showTip3(error.responseText);
	                }
	            });	
			});
			
			//提示并延迟刷新(使用setTimeout第三个参数)
			function showTip3(mess) {
				resetTip();
	        	setTimeout(function(){
	        		top.$.jBox.tip(mess, 'info' , {opacity:0,timeout:2000});
	        	}, 500,setTimeout(function() {
	                window.location.reload();
	            }, 2500));
			}
			
			//分配列表
			$('.allot_view').click(function() {
				var shopNo = $(this).data("shopno");
				var noWx = $(this).data("nowx");
				var code   = $(this).data("code");
				var memberNoGm   = $(this).data("membernogm");
				
				// 正常打开	
				top.$.jBox.open("iframe:${ctx}/member/allotView?shopNo="+shopNo+"&code="+code+"&noWx="+noWx+"&memberNoGm="+memberNoGm ,"导购列表", 520, 400,{//宽高
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
						$("#searchForm").attr("action","${ctx}/member/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过10M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
			//批量添加标签
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
			//批量分组
			$("#btnBatchChangePmType").click(function(){
				//获取勾选的值
				var pmCodeArr = new Array;
			    $("#infolist :checkbox[checked]").each(function(i){
			    	pmCodeArr[i] = $(this).data("code");
			    });
				var pmCodes = pmCodeArr.join(',');//转换为逗号隔开的字符串
				if(!pmCodes){
					alertx("请选择客户!");
					return false;
				}
				
				// 正常打开	
				top.$.jBox.open("iframe:${ctx}/member/batchChangePmTypeSelect?pmCodes="+pmCodes+"&merchantNo=${merchantNo}" ,"批量分组", 520, 230,{//宽高
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
			//积分明细
			$(".integral_btn").click(function(){
				var memberNo = $(this).data("member");
				
				// 正常打开	
				top.$.jBox.open("iframe:${ctx}/iem/member/integralList?memberNo="+memberNo ,"积分明细", 800, 500,{//宽高
					id:1112,
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
			//订单列表
			$(".order_btn").click(function(){
				var memberNo = $(this).data("member");
				
				// 正常打开	
				top.$.jBox.open("iframe:${ctx}/iem/member/orderList?memberNo="+memberNo ,"订单", 1000, 600,{//宽高
					id:1112,
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
				var code = $(this).data("basecode");
				var memberNoGM = $(this).data("membernogm");
				
				var url = '${ctx}/member/doListApplayFriend';
				var params = {
						code: code,
						memberNoGM: memberNoGM
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
		
		/* $(".btn-default").click(function(){
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
		  });*/
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
			$("#searchForm").attr("action","${ctx}/dealerapply/list/");
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
	
	
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/dealerapply/list/">经销商入驻</a></li>
	</ul>
	
	
	<form id="searchForm" action="${ctx}/dealerapply/list/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<c:set var="beginIndex" value="${(page.pageNo - 1) * page.pageSize}"></c:set>
		<ul class="ul-form">
		    <li><label>经销商代码：</label>
		    	<input type="text" name="dealerCode" value="${paramInfo.dealerCode}" class="input-medium" maxlength="100" placeholder="经销商代码">
			</li>
			<li><label>经销商名称：</label>
		    	<input type="text" name="dealerName" value="${paramInfo.dealerName}" class="input-medium" maxlength="100" placeholder="经销商名称">
			</li>
			<li><label>负责人姓名：</label>
		    	<input type="text" name="dealerResponsiblePerson" value="${paramInfo.dealerResponsiblePerson}" class="input-medium" maxlength="100" placeholder="负责人姓名">
			</li>
			<li><label>负责人手机号：</label>
				<input type="text" name="dealerResponsibleMobile" value="${paramInfo.dealerResponsibleMobile}" class="input-medium" maxlength="11" placeholder="负责人手机号">
			</li>
			</ul>
			<ul class="ul-form">
			<li><label>审核状态：</label>
				<select style="width: 177px;" name="applyStatus">
                    <option value="">全部</option>
                    <c:forEach items="${applyStatusList}" var="item">
						<option value="${item.code}"
							<c:if test="${item.code eq paramInfo.applyStatus}">selected="selected"</c:if>>${item.name}</option>
					</c:forEach>
                </select>
			</li>
			<li>
				<label>申请时间：</label>
				<input id="beginDate" name="startTime" type="text"  readonly="readonly" maxlength="30" style="width:150px" class="input-mini Wdate"
				value="<fmt:formatDate value="${paramInfo.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
				-- 
				<input id="endDate" name="endTime" type="text"  readonly="readonly" maxlength="30" style="width:150px" class="input-mini Wdate"
				value="<fmt:formatDate value="${paramInfo.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,minDate:'#beginDate'});"
				/>&nbsp;&nbsp;
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>
			<!-- <li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li> -->
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<!-- <th>全选<input type="checkbox" id="allcheck"/></th> -->
				<th>序号</th>
				<th>经销商名称</th>
				<th>经销商代码</th>
				
				<th>法人姓名</th>
				<th>芝华仕业务对接人</th>
				<th>负责人姓名</th>
				<th>负责人电话</th>
				
				<th>申请时间</th>
				<th>审核状态</th>
				<th>审核人</th>
				<th>审核时间</th>
				
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="infolist">
		<c:forEach items="${page.list}" var="member" varStatus="number"> 
			<tr>
				<%-- <td>
					<input type="checkbox" value="${member.memberNo}" data-code="${member.code}" />
				</td> --%>
				<td>
					${beginIndex + number.count}
				</td>
				<td>
					${member.dealerName}
				</td>
			    <td>
					${member.dealerCode}
				</td>
				<td>
					${member.legalPersonName}
				</td>
				<td>
					${member.businessPerson}
				</td>
				<td>
					${member.dealerResponsiblePerson}
				</td>
				<td>
					${member.dealerResponsibleMobile}
				</td>
				<td>
					<fmt:formatDate value="${member.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:choose>
						<c:when test="${member.applyStatus == 0}">
							待审核
						</c:when>
						<c:when test="${member.applyStatus == 1}">
							审核通过
						</c:when>
						<c:otherwise>
							审核未通过
						</c:otherwise>
					</c:choose>
				</td>
				
				<td>
					${member.auditName}
				</td>
				<td>
					<fmt:formatDate value="${member.auditTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td class="textCenter" width="150px">
					<shiro:hasPermission name="member:dealerapply:view">
						<a href="${ctx}/dealerapply/detail?code=${member.code}">查看详情</a>&nbsp;
					</shiro:hasPermission>
					<c:if test="${member.applyStatus == 0 }">
						<shiro:hasPermission name="member:dealerapply:edit">
							<%-- <a href="${ctx}/dealerapply/editView?code=${member.code}">编辑</a>&nbsp; --%>
							<a class="audit_btn" href="javascript:;" data-toggle="modal" data-target="#myModal" data-remote="" data-code="${member.code}">立即审核</a>
						</shiro:hasPermission>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</div>	

<!-- Modal Start -->
<div class="modal fade hide" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 600px;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					审核
				</h4>
			</div>
			<form id="inputForm" action="${ctx}/dealerapply/audit" method="post" enctype="multipart/form-data" onkeydown="if(event.keyCode==13){$('.myModalSubmit').click();return false;}">
				<div class="modal-body">
					<input id="code" name="code" type="hidden"/>
							<div class="control-group">
						        <label class="control-label"><span class="help-inline"><font color="red">*</font></span>审核意见:</label>
				                <div class="controls">
											<input type="radio" name="auditResult" id="auditResult1">通过 &nbsp;&nbsp;&nbsp;
											<input type="radio" name="auditResult" id="auditResult2">不通过
				                </div>
						    </div>
							<div class="control-group" >
						        <label class="control-label">备注:</label>
				                <div class="controls">
				                	<textarea style="width:500px;height:60px" id="remark" name="remark"></textarea>
				                </div>
						    </div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default myModalClose" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary myModalSubmit">提交</button>
				</div>
			</form>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<!-- Modal End -->
	
</body>
</html>