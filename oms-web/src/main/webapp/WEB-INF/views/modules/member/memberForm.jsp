<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户表管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/common/plupload.full.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			
			//判断是否显示div
			function IsShow(){
				var val=$("#type").val();
				if(val==""){
					$(".company").hide();
					$(".person").hide();
				}else if(val=="0"){
					$(".company").hide();
					$(".person").show();
				}else if(val=="1"){
					$(".company").show();
					$(".person").hide();
				}
			}
			//执行
			IsShow();
			//改变类型重新判断
			$("#type").change(function(){
				IsShow();
			});
			
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					$("#btnSubmit").attr("disabled","disabled");form.submit()
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		
			function initUploader(uploaderHead,memberHead,image_btn,input_image) {
				var uploaderHead = new plupload.Uploader({ //实例化一个plupload上传对象
					browse_button : image_btn,
					url : '${ctx}/file/upload',
					multi_selection:false,
					auto_start : true,
					flash_swf_url : '${ctxStatic}/common/Moxie.swf',
					silverlight_xap_url : '${ctxStatic}/common/Moxie.xap',
					filters: {
					  mime_types : [ //只允许上传图片文件
					    { title : "图片文件", extensions : "jpg,gif,png" }
					  ],
					  max_file_size : '1024kb',
					  prevent_duplicates : true 
					},
					multipart_params: {
						fileType: 'image',
						dirName: memberHead,
						width:400,
						height:400
					}
				});
				uploaderHead.init(); //初始化
				uploaderHead.bind('FilesAdded',function(uploader,files){
					if(files.length>0){
						uploader.start();
					}
				});
				uploaderHead.bind('Error',function(uploader,errObject){
					alert(errObject.message);
				});
				
				uploaderHead.bind('FileUploaded',function(uploader,file,responseObject){
						var response = $.parseJSON(responseObject.response);
						$("#"+image_btn).html('<img width="120px" height="120px" src="'+response.url+'"/>');
						$("#"+input_image).val(response.url);
				});
			}
			
					//初始化上传图片实例
					initUploader("uploaderHead","memberHead","image_btn","input_image");
					initUploader("IdCardIs","IdCardIsImage","image_btn_IdCardIs","input_image_IdCardIs");
					initUploader("IdCardThe","IdCardTheImage","image_btn_IdCardThe","input_image_IdCardThe");
					initUploader("uploaderBl","bl","image_btn_bl","input_image_bl");
					initUploader("uploaderTrc","trc","image_btn_trc","input_image_trc");
					initUploader("uploaderOc","oc","image_btn_oc","input_image_oc");
		});
		
		function openWindow(){
			top.$.jBox.open("iframe:${ctx}/member/saler/salerSelectList", "", 900, 600, {
	            buttons:{"确定":"ok", "关闭":true}, submit:function(v, h, f){
	                if (v=="ok"){
	                	var win = h.find("iframe")[0].contentWindow
	                	//alert(win.document.getElementById("searchForm").innerHTML);
	                	var sonId=win.document.getElementById("salerId");
	                	var sonName=win.document.getElementById("salerName");
	                	$("#proId").val(sonId.value);
	        			$("#proName").val(sonName.value);
	        			$("#proName").valid();
	                }else{
	                	
	                }
	            }, loaded:function(h){
	                $(".jbox-content", top.document).css("overflow-y","hidden");
	            }
	        });
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/member/member/">客户列表</a></li>
		<li class="active"><a href="${ctx}/member/member/form?id=${member.id}">客户<shiro:hasPermission name="member:member:edit">${not empty member.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="member:member:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="member" action="${ctx}/member/member/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
<%-- 		<sys:message content="${message}"/> --%>
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">头像：</label>
			<div class="controls" >
			  <div id="image_btn" style="border: 1px solid #e0e6eb;width:120px;height:120px;line-height:100px;text-align:center">
				<c:if test="${!empty member.photo}">
			       <img width="120px" height="120px" src="${member.photo}"/>
			     </c:if>
			      <c:if test="${empty member.photo}">
			            选择头像
		       	</c:if>
		       	</div>
			</div>
			<form:hidden id="input_image" path="photo" htmlEscape="false" maxlength="255" class="input-xlarge"/>
		</div>		
		<div class="control-group">
			<label class="control-label">用户名：</label>
			<div class="controls">
				<form:input path="username" htmlEscape="false" maxlength="100" class="input-xlarge required username"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge required realName"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机：</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="20" class="input-xlarge required mobile"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">登录密码：</label>
			<div class="controls">
				<%-- <form:password path="password" htmlEscape="false" maxlength="64" class="input-xlarge "/> --%>
				<input type="password" value="${member.password}" id="password" name="password"  class="input-xlarge required">
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">性别:</label>
			<div class="controls">
				<form:select path="gender" class="input-xlarge ">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">生日：</label>
			<div class="controls">
				<input name="birthday" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${member.birthday}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">QQ：</label>
			<div class="controls">
				<form:input path="qq" htmlEscape="false" maxlength="20" class="input-xlarge number qq"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">邮箱：</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">微信号：</label>
			<div class="controls">
				<form:input path="wechat" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		 <div class="control-group">
			<label class="control-label">所在区域：</label>
			<div class="controls">
				<tags:treeselect id="area" name="area.id" value="${member.area.id}" labelName="area.name" labelValue="${member.area.name}"
					title="区域" url="/sys/area/treeData" cssClass="" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">详细地址：</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所在行业：</label>
			<div class="controls">
			  <%-- <form:input path="industry.id" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/> --%>
			  <tags:treeselect id="industry" name="industry.id" value="${member.industry.id}" labelName="industry.name" labelValue="${member.industry.name}"
					title="行业" url="/setting/industry/treeData" cssClass="" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">个性签名：</label>
			<div class="controls">
				<%-- <form:input path="signature" htmlEscape="false" maxlength="255" class="input-xlarge "/> --%>
				<form:textarea path="signature" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
			<label class="control-label">总积分：</label>
			<div class="controls">
				<form:input path="point" htmlEscape="false" maxlength="11" class="input-xlarge required number"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">可用积分：</label>
			<div class="controls">
				<form:input path="pointBalance" htmlEscape="false" maxlength="11" class="input-xlarge required number"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">信用额度：</label>
			<div class="controls">
				<form:input path="credit" htmlEscape="false" maxlength="11" class="input-xlarge required number"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">可用信用：</label>
			<div class="controls">
				<form:input path="creditBalance" htmlEscape="false" maxlength="11" class="input-xlarge required number"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">等级归类：</label>
			<div class="controls">
				<%-- <form:input path="grade" htmlEscape="false" maxlength="10" class="input-xlarge "/> --%>
				<form:select path="grade" class="input-xlarge required">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('member_leve')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline">销售划分的等级</span>
			</div>
		</div>
		

		<div class="control-group">
			<label class="control-label">关联销售业务员：</label>
			<div class="controls">
				<%-- <form:input path="saler.id" htmlEscape="false" maxlength="20" class="input-xlarge required digits"/> --%>
				<%-- <tags:treeselect id="saler" name="saler.id" value="${member.saler.id}" labelName="saler.name" labelValue="${member.saler.name}"
					title="销售业务员" url="/member/saler/treeData" cssClass="" allowClear="true" notAllowSelectParent="true"/> --%>
			<div class="input-append">
				<!-- <input id="salerId" name="saler.id" class="" type="hidden" value="">
				<input id="salerName" name="saler.name" readonly="readonly" type="text" value="" data-msg-required="" class="" style=""> -->
				<form:hidden path="saler.id" id="proId"/>
				<form:input path="saler.name" id="proName" readonly="true"  class="input-xlarge required" style="width:209px;"/>
				<a id="salerButton" href="javascript:openWindow();" class="btn  " style="">&nbsp;<i class="icon-search"></i>&nbsp;</a>&nbsp;&nbsp;
			</div>
			<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户等级：</label>
			<div class="controls">
				<%-- <form:input path="memberRank.id" htmlEscape="false"/> --%>
				<form:select path="memberRank.id" class="input-xlarge required">
					<form:option value="" label="请选择"/>
					<form:options items="${rankList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group" >
			<label class="control-label">类型：</label>
			<div class="controls">
				 <form:select path="type" name="type" id="type" class="input-xlarge required">
					<form:option value="" label="请选择"/>
					<form:option value="0" label="个人" />
					<form:option value="1" label="企业" />
					<%-- <form:option value="100" label="销售"/> --%>
				 </form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group company">
			<label class="control-label">公司名称：</label>
			<div class="controls">
				<form:input path="company" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group company">
			<label class="control-label">简称：</label>
			<div class="controls">
				<form:input path="shortName" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group company">
			<label class="control-label">营业执照：</label>
			<div class="controls" id="image_btn_bl">
				<%-- <form:input path="bl" htmlEscape="false" maxlength="255" class="input-xlarge "/> --%>
				<c:if test="${!empty member.bl}">
			       <img width="120px" height="120px" src="${member.bl}"/>
			     </c:if>
			      <c:if test="${empty member.bl}">
			           <input  class="btn" type="button" value="点击上传" />
		       	</c:if>
		       	<form:hidden id="input_image_bl" path="bl" htmlEscape="false" maxlength="255" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group company">
			<label class="control-label">营业执照号：</label>
			<div class="controls">
				<form:input path="blSn" htmlEscape="false" maxlength="30" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group company">
			<label class="control-label">组织机构代码证：</label>
			<div class="controls" id="image_btn_oc">
			    <c:if test="${!empty member.oc}">
			       <img width="120px" height="120px" src="${member.oc}"/>
			     </c:if>
			      <c:if test="${empty member.oc}">
			           <input  class="btn" type="button" value="点击上传" />
		       	</c:if>
		       	<form:hidden id="input_image_oc" path="oc" htmlEscape="false" maxlength="255" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group company">
			<label class="control-label">组织机构代码：</label>
			<div class="controls">
				<form:input path="ocSn" htmlEscape="false" maxlength="30" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group company" >
			<label class="control-label">税务登记证：</label>
			<div class="controls" id="image_btn_trc">
				<%-- <form:input path="trc" htmlEscape="false" maxlength="255" class="input-xlarge "/> --%>
				<c:if test="${!empty member.trc}">
			       <img width="120px" height="120px" src="${member.trc}"/>
			     </c:if>
			      <c:if test="${empty member.trc}">
			           <input  class="btn" type="button" value="点击上传" />
		       	</c:if>
		       	<form:hidden id="input_image_trc" path="trc" htmlEscape="false" maxlength="255" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group company">
			<label class="control-label">税务登记证号：</label>
			<div class="controls">
				<form:input path="trcSn" htmlEscape="false" maxlength="30" class="input-xlarge "/>
			</div>
		</div>
		
		<div class="control-group company">
			<label class="control-label">联系人名称：</label>
			<div class="controls">
				<form:input path="contactName" htmlEscape="false" maxlength="50" class="input-xlarge number"/>
			</div>
		</div>
		<div class="control-group company">
			<label class="control-label">联系人电话：</label>
			<div class="controls">
				<form:input path="contactPhone" htmlEscape="false" maxlength="20" class="input-xlarge number"/>
			</div>
		</div>
		<div class="control-group person">
			<label class="control-label">身份证号：</label>
			<div class="controls">
				<form:input path="idCardSn" htmlEscape="false" maxlength="20" class="input-xlarge card"/>
			</div>
		</div>
		<div class="control-group person">
			<label class="control-label">身份证正面：</label>
			<div class="controls" id="image_btn_IdCardIs">
				<%-- <form:input path="idCardFront" htmlEscape="false" maxlength="255" class="input-xlarge "/> --%>
				<c:if test="${!empty member.idCardFront}">
			       <img width="120px" height="120px" src="${member.idCardFront}"/>
			     </c:if>
			       <c:if test="${empty member.idCardFront}">
			           <input  class="btn" type="button" value="点击上传" />
		       	</c:if>
			</div>
			<form:hidden id="input_image_IdCardIs" path="idCardFront" htmlEscape="false" maxlength="255" class="input-xlarge"/>
		</div>
		<div class="control-group person">
			<label class="control-label">身份证背面：</label>
			<div class="controls" id="image_btn_IdCardThe">
				<%-- <form:input path="idCardBack" htmlEscape="false" maxlength="255" class="input-xlarge "/> --%>
				<c:if test="${!empty member.idCardBack}">
			       <img width="120px" height="120px" src="${member.idCardBack}"/>
			     </c:if>
			      <c:if test="${empty member.idCardBack}">
			           <input  class="btn" type="button" value="点击上传" />
		       	</c:if>
			</div>
			<form:hidden id="input_image_IdCardThe" path="idCardBack" htmlEscape="false" maxlength="255" class="input-xlarge"/>
		</div>
		<!-- <div class="control-group">
			<label class="control-label">注册渠道：</label>
			<div class="controls"> -->
				<form:hidden path="regChannel" htmlEscape="false" value="4" maxlength="11" class="input-xlarge "/>
		<%-- 	<form:select path="regChannel" >
					<form:option value="" label="请选择"/>
					<form:option value="0" label="pc"/>
					<form:option value="1" label="微信"/>
					<form:option value="2" label="app"/>
					<form:option value="3" label="h5手机板"/>
					<form:option value="4" label="销售代注册"/>
					<form:option value="5" label="erp导入"/>
				</form:select> --%>
		<!-- 	</div>
		</div> -->
		<div class="control-group">
			<label class="control-label">是否有框架协议：</label>
			<div class="controls">
				<%-- <form:input path="isContract" htmlEscape="false" maxlength="1" class="input-xlarge required"/> --%>
				<form:select path="isContract" class="input-xlarge ">
					<form:option value="0" label="请选择"/>
					<form:option value="true" label="有"/>
					<form:option value="false" label="否"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">协议有效期：</label>
			<div class="controls">
				<input name="contractExpireDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${member.contractExpireDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">协议编号：</label>
			<div class="controls">
				<form:input path="contractSn" htmlEscape="false" maxlength="30" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审核状态：</label>
			<div class="controls">
<%-- 				<form:input path="auditStatus" htmlEscape="false" maxlength="11" class="input-xlarge required"/>--%>			
 			<form:select path="auditStatus" class="input-xlarge required">
				<form:option value="0" label="待审核"/>
				<form:option value="1" label="审核通过"/>
				<form:option value="2" label="审核不通过"/>
			</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否启用：</label>
			<div class="controls">
				<%-- <form:input path="isEnabled" htmlEscape="false" maxlength="1" class="input-xlarge required"/> --%>
				<form:select path="isEnabled" class="input-xlarge required">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('enable_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">系统备注：</label>
			<div class="controls">
				<%-- <form:input path="sysRemarks" htmlEscape="false" maxlength="255" class="input-xlarge "/> --%>
				<form:textarea path="sysRemarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="member:member:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>