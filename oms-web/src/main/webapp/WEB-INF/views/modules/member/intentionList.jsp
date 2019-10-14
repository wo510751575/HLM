<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${title}</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//客户详情
			$('.view_btn').click(function() {
				var id = $(this).attr("data-id");
				var type = $(this).attr("data-type");
				// 正常打开	
				top.$.jBox.open("iframe:${ctx}/member/view?code="+id+"&pmTypeType="+type,"客户详情", 920, 730, {//宽高
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
		<li class="active"><a href="${ctx}/member/${action}/">${title}</a></li>
	</ul>
	
	
	<form id="searchForm" action="${ctx}/member/${action}/" method="post" class="breadcrumb form-search">
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
			</li> --%>
			<li><label>所在地区：</label>
				<tags:treeselect id="area" name="areaId" value="${areaId}" labelName="areaName" labelValue="${areaName}"
					title="所在地区" url="/sys/area/treeData" cssClass="required"/>
			</li>
			<li>
				<label>录入时间：</label>
				<input id="beginDate" name="startTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${paramMember.startTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')||\'2020-10-01\'}',dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				-- 
				<input id="endDate" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${paramMember.endTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true,minDate:'#F{$dp.$D(\'beginDate\')}'});"/>&nbsp;&nbsp;
			</li>
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
				
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
	<!-- 		<li class="btns"><input id="sendMaterial" class="btn btn-primary" type="reset"  value="批量发送素材"/></li>
			<li class="btns"><input id="sendSms" class="btn btn-primary" type="reset"  value="批量发送短信"/></li> -->
			<li class="clearfix"></li>
		</ul>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
<!-- 				<th>全选<input type="checkbox" id="allcheck"/></th> -->
				<th style="min-width: 45px;">头像</th>
				<th>客户姓名</th>
				<th>微信OpendID</th>
				<th>微信昵称</th>
				<th>手机号</th>
				<th>性别</th>
				<th>所属导购</th>
				<th>所属终端</th>
				<th>所在地区</th>
				<th>所需产品</th>
				<th>所在楼盘</th>
				<th>客户来源</th>
				<th>跟进次数</th>
				<th>录入时间</th>
				<shiro:hasPermission name="member:member:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="infolist">
		<c:forEach items="${page.list}" var="member" varStatus="number"> 
			<tr>
<!-- 				<td> -->
<%-- 					<input type="checkbox" value="${member.code}"/> --%>
<!-- 				</td> -->
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
				
			   <td>
					<a  href="javascript:;" class="view_btn" data-id="${member.code}" data-type="${member.pmTypeType}">${member.memberName}</a>
				</td>
				<td>
					<c:if test="${not empty member.noWx}">${member.remark}${member.noWx}${member.remark2}</c:if>
				</td>
				<td>
					${member.nickNameWx}
				</td>
				<td>
					${member.mobile}
				</td>
				<td>
					<c:if test="${member.sex=='MALE'}">男</c:if> 
					<c:if test="${member.sex=='FEMALE'}">女</c:if> 
					<c:if test="${member.sex eq 'UNKNOWN'}">未知</c:if>
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
					${member.bomName}
				</td>
				<td>
					${fns:getAreaName(member.provinceCode)}${fns:getAreaName(member.cityCode)}${fns:getAreaName(member.cityAreaCode)}${member.houses}
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
					<fmt:formatDate value="${member.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="member:member:edit"><td>
<%-- 					<a href="${ctx}/weixin/chatInfo?talker=${member.noWx}">聊天记录</a> --%>
					<a href="javascript:;" class="view_btn" data-id="${member.code}" data-type="${member.pmTypeType}">查看</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	</div>
</body>
</html>