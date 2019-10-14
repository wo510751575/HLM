<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>今日新增客户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
						$("#searchForm").attr("action","${ctx}/member/todayExport?export=1");
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
			
			$(".addTag_btn").click(function(){
				var memberNos = $(this).data("member");
				
				// 正常打开	
				top.$.jBox.open("iframe:${ctx}/member/tagView?single=1&memberNos="+memberNos+"&merchantNo=${merchantNo}" ,"标签选择", 520, 400,{//宽高
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
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/member/import" method="post" enctype="multipart/form-data"
			style="padding-left:20px;text-align:center;" class="form-search" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
				<a href="${ctx}/member/import/template">下载模板</a>
			
		</form>
	</div>
	
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/member/todayList/">今日新增客户列表</a></li>
	</ul>
	
	
	<form id="searchForm" action="${ctx}/member/todayList/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		    <li><label>客户姓名：</label>
		    	<input type="text" name="memberName" value="${paramMember.memberName}" class="input-medium" maxlength="100" placeholder="客户姓名">
			</li>
			<li><label>手机号：</label>
				<input type="text" name="mobile" value="${paramMember.mobile}" class="input-medium" maxlength="11" placeholder="手机号">
			</li>
			<%-- <li><label>所在地区：</label>
				<input type="text" name="province" value="${provinceSel}" class="input-mini" maxlength="100" placeholder="省/直辖市">
				<input type="text" name="city" value="${citySel}" class="input-mini" maxlength="100" placeholder="市">
				<input type="text" name="region" value="${regionSel}" class="input-mini" maxlength="100" placeholder="区">
			</li> --%>
			<li><label>所在地区：</label>
				<tags:treeselect id="area" name="areaId" value="${areaId}" labelName="areaName" labelValue="${areaName}"
					title="所在地区" url="/sys/area/treeData" cssClass="required"/>
			</li>
			<%-- <li>
				<label>录入时间：</label>
				<input id="beginDate" name="startTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${paramMember.startTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				-- 
				<input id="endDate" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${paramMember.endTime}" pattern="yyyy-MM-dd"/>" 
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"
				/>&nbsp;&nbsp;
			</li> --%>
		</ul>
		<ul class="ul-form">
			<li><label>所属导购：</label>
				<input type="text" name="memberNameGm" value="${paramMember.memberNameGm}" class="input-medium" maxlength="100" placeholder="导购姓名">
			</li>
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
		<!-- 	<li class="btns"><input id="sendMaterial" class="btn btn-primary" type="reset"  value="批量发送素材"/></li>
			<li class="btns"><input id="sendSms" class="btn btn-primary" type="reset"  value="批量发送短信"/></li> -->
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			
			<li class="btns"><input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
			<li class="btns"><input id="btnBatchAddTags" class="btn btn-primary" type="button" value="批量添加标签"/></li>
			
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>全选<input type="checkbox" id="allcheck"/></th>
				<th style="min-width: 45px;">头像</th>
				<th>客户姓名</th>
				<th>微信微信</th>
				<th>微信昵称</th>
				<th>手机号</th>
				<th>性别</th>
				<th>所属导购</th>
				<th>所属终端</th>
				<th>所在地区</th>
				<th>标签</th>
				<th>所需产品</th>
				<th>所在楼盘</th>
				<th>客户来源</th>
				<th>跟进次数</th>
				<th>维护次数</th>
				<th>成单次数</th>
				<th>添加方式</th>
				<th>录入时间</th>
				<shiro:hasPermission name="member:member:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="infolist">
		<c:forEach items="${page.list}" var="member" varStatus="number"> 
			<tr>
				<td>
					<input type="checkbox" value="${member.memberNo}"/>
				</td>
				<td>
					<a  href="javascript:;" class="view_btn" data-id="${member.code}" data-type="${member.pmTypeType}">
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
					<a  href="javascript:;" class="view_btn" data-id="${member.code}" data-type="${member.pmTypeType}">${member.memberName}</a>
				</td>
				<td>
					${not empty member.noWxAlias?member.noWxAlias:member.noWx}
				</td>
				<td>
					${member.nickNameWx}
				</td>
				<td>
					${fn:replace(member.mobile,",","<br>")}
				</td>
				<td>
					<c:if test="${member.sex=='MALE'}">男</c:if> 
					<c:if test="${member.sex=='FEMALE'}">女</c:if> 
				</td>
				
				<td>
					${member.memberNameGm}
				</td>
				
				<td>
					<%-- ${member.provinceWx}${member.cityWx} --%>
					<!-- update by 杨杰  2017-09-09 修改 -->
					${fns:getAreaName(member.provinceCode)}${fns:getAreaName(member.cityCode)}${fns:getAreaName(member.cityAreaCode)}
				</td>
				<td>
					${member.remark}
				</td>
				<td>
					${member.bomName}
				</td>
				<td>
					${member.houses}
				</td>
				
				<td>
					<c:forEach items="${memerSources}" var="item">
						<c:if test="${item eq member.memberSrc}">${item.name}</c:if>
					</c:forEach>
				</td>
				<td>
					<a href="${ctx}/cf/list?memberNo=${member.memberNo}&memberNoGm=${member.memberNoGm}">${member.followNum}</a>
				</td>
				<td>
					<a href="${ctx}/clientKeep/list?memberNo=${member.memberNo}&memberNoGm=${member.memberNoGm}">${member.keepNum}</a>
				</td>
				<td> 
					<a href="${ctx}/clientFollowSummary/list?memberNoGm=${member.memberNoGm}&memberNo=${member.memberNo}">${member.successNum}</a>
				</td>
				<td>
                    <c:if test="${member.addType eq 1}">导购扫码添加</c:if>
                    <c:if test="${member.addType eq 2}">客户扫码添加</c:if>
                    <c:if test="${member.addType eq 3}">导购手动新增</c:if>
                    <c:if test="${member.addType eq 4}">微信自动导入</c:if>
                    <c:if test="${member.addType eq 5}">手机号添加</c:if>
                    <c:if test="${member.addType eq 6}">微信号添加</c:if>
                    <c:if test="${member.addType eq 7}">QQ号添加</c:if>
                </td>
				<td>
					<fmt:formatDate value="${member.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="member:member:edit"><td>
					<c:if test="${!empty member.noWx}">
					<a href="javascript:;" data-wx="${member.noWx}" data-memberNoGm="${member.memberNoGm}" class="btn-default">聊天记录</a>
					</c:if>
					<a href="javascript:;" class="view_btn" data-id="${member.code}" data-type="${member.pmTypeType}">查看</a>
					<a href="javascript:;" class="addTag_btn" data-member="${member.memberNo}">添加标签</a>
					<c:if test="${not empty member.mobile and empty member.noWx}">
						<a href="javascript:;" class="addFriends_btn" data-basecode="${member.baseCode}" data-memberNoGm="${member.memberNoGm}">添加好友</a>
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