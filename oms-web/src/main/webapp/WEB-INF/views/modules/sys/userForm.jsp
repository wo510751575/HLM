<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/bootstrap-select/bootstrap-select.min.css">
	<script src="${ctxStatic}/common/plupload.full.min.js" type="text/javascript"></script>
	<script src="${ctxStatic}/bootstrap-select/bootstrap-select.min.js"></script>
	<script src="${ctxStatic}/bootstrap-select/defaults-zh_CN.min.js"></script>
	<script type="text/javascript">
	  
		$(document).ready(function() {
			// 不能输入中文 
			jQuery.validator.addMethod("noChinese", function(value, element) { var chrnum = /^[a-zA-Z0-9_]{0,}$/; return this.optional(element) || (chrnum.test(value)); }, "不能输入中文");
			 
			$("#no").focus();
			$("#inputForm").validate({
				rules: {
					loginName: {remote: "${ctx}/sys/user/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')}
				},
				messages: {
					loginName: {remote: "用户登录名已存在"},
					confirmNewPassword: {equalTo: "输入与上面相同的密码"}
				},
				submitHandler: function(form){
					var pwd=$("#newPassword").val();
					var pwd2=$("#confirmNewPassword").val();
					if(pwd && !testPwd(pwd)){
						return false;
					}
					if(pwd2 && !testPwd(pwd2)){
						return false;
					}
					
					var userLevel = $('#userLevel').val();
					if (userLevel == 2 || userLevel == 3) {
						if ($('#openLevelDate').val() == '' || $('#endLevelDate').val() == '') {
							alertx("请填写会员开通与结束时间!");
							return false;
						}
					}
					
					loading('正在提交，请稍等...');
					$("#btnSubmit").prop("disabled", true);
					form.submit() 
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
			
		});
		//js正则验证密码(字母+数字组合)
		function testPwd(pwd) {
			var reg = /^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]{6,20})$/;
			if (!reg.test(pwd)) {
				alertx("密码请输入字母加数字的组合!");
				return false;
			}
			return true;
		}
	
		
	</script>
	
    <style type="text/css">
    .nav-child li a{
          line-height: 10px;
    }
    .nav-child li.active a{
          border: 1px dotted #ddd;
          border-bottom-color: transparent;
    }
    .photo-file{
	 	position: absolute;
	    top: 350px;
	    left: 190px;
	    opacity: 0;
	    filter: alpha(opacity:0);
	    cursor:pointer;
	}
	.container{
	padding: 10px 30px;
    width: 100%;
    min-height: 800px;
    background: #fff;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
	}
	
.btn-group.bootstrap-select.show-tick.show-menu-arrow.form-control {
	margin-left: 20px;
	width: auto;
}

   </style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/user/list">用户列表</a></li>
		<li class="active"><a href="${ctx}/sys/user/form?id=${user.id}">用户<shiro:hasPermission name="sys:user:edit">${not empty user.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:user:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input id="merchantNo" name="merchantNo" type="hidden" value="${user.company.id}"/>
		<input id="companyNoshop" name="companyNoshop" type="hidden" value="${user.companyNoshop}"/>
		<input name="searchCompanyId" type="hidden" value="${searchCompanyId }">
		<input name="searchOfficeId" type="hidden" value="${searchOfficeId }">
		<input name="searchName" type="hidden" value="${searchName }">
		<input name="searchLoginName" type="hidden" value="${searchLoginName }">
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">头像:</label>
			<div class="controls">
			      <div id="image_btn" style="border: 1px solid #e0e6eb;width:120px;height:120px;line-height:100px;text-align:center">
				       <c:if test="${!empty user.photo}">
				       		<img width="120px" height="120px" src="${fns:getUploadUrl()}${user.photo}"/>
				       </c:if>
				       <c:if test="${empty user.photo}">
				                                  选择头像
				       </c:if>
				    </div>
				<form:hidden id="input_image" path="photo" htmlEscape="false" maxlength="255" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">归属公司:</label>
			<div class="controls">
                <tags:treeselect id="company" name="company.id"  value="${user.company.id}"  labelName="company.name" labelValue="${user.company.name}"
					title="公司" url="/sys/office/treeData?type=1" cssClass="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">归属部门:</label>
			<div class="controls" id="test001">
                <tags:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="${user.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="required" notAllowSelectParent="false"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">用户类型:</label>
			<div class="controls">
				<select name="userType" id="userType"  class="input-xlarge userType required">
					<c:forEach items="${userTypeMap}" var="item">
						<option value="${item.key}" class="MaterialInfo"
							<c:if test="${item.key eq user.userType}">selected="selected"</c:if> 
						>${item.value}</option>
					</c:forEach>
				</select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">工号:</label>
			<div class="controls">
				<form:input path="no" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">姓名:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">会员类型:</label>
			<div class="controls">
				<select name="userLevel" id="userLevel"  class="input-xlarge userLevel required">
					<option ${not empty guidMemberRw and guidMemberRw.userLevel eq '1' ? 'selected="selected"' : '' } value="1">普通会员</option>
					<option ${not empty guidMemberRw and guidMemberRw.userLevel eq '2' ? 'selected="selected"' : '' } value="2">推广会员</option>
					<option ${not empty guidMemberRw and guidMemberRw.userLevel eq '3' ? 'selected="selected"' : '' } value="3">企业会员</option>
				</select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开通时间:</label>
			<div class="controls">
				<input name="openLevelDate" id="openLevelDate" type="text" readonly="readonly" maxlength="20" class="input-xlarge Wdate"
					value="<fmt:formatDate value="${guidMemberRw.openLevelDate }" pattern="yyyy-MM-dd"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				--
				<input name="endLevelDate" id="endLevelDate" type="text" readonly="readonly" maxlength="20" class="input-xlarge Wdate"
					value="<fmt:formatDate value="${guidMemberRw.endLevelDate }" pattern="yyyy-MM-dd"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">登录名:</label>
			<div class="controls">
				<input id="oldLoginName" name="oldLoginName" type="hidden" value="${user.loginName}">
				<form:input path="loginName" htmlEscape="false" maxlength="50" class="required userName noChinese"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">密码初始化:</label>
			<div class="controls">
				<input name="resetPwd" type="radio" <c:if test="${empty user.id}"> checked="checked" </c:if> id="resetPwd1" onclick="$('#newPassword').val('a123456');$('#confirmNewPassword').val('a123456')"/>  <label for="resetPwd1">是</label>
				<input name="resetPwd" type="radio" <c:if test="${not empty user.id}">checked="checked" </c:if>id="resetPwd2" onclick="$('#newPassword').val('');$('#confirmNewPassword').val('')"/>   <label for="resetPwd2">否 </label>
			</div>
		</div>
		<div class="control-group" style="display: none;">
			<label class="control-label">密码:</label>
			<div class="controls">
				<input id="newPassword" name="newPassword" type="password" value="" maxlength="20" minlength="6" class="${empty user.id?'required':''} onlyNumAlpha"/>
				<c:if test="${empty user.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
				<c:if test="${not empty user.id}"><span class="help-inline">若不修改密码，请留空。</span></c:if>
			</div>
		</div>
		<div class="control-group" style="display: none;">
			<label class="control-label">确认密码:</label>
			<div class="controls">
				<input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="20" minlength="6" equalTo="#newPassword" class="onlyNumAlpha"/>
				<c:if test="${empty user.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">邮箱:</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="100" class="email"/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">座机:</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="11" type="number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机:</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="11" type="number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否允许登录:</label>
			<div class="controls">
				<form:select path="loginFlag">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> “是”代表此账号允许登录，“否”则表示此账号不允许登录</span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">微信号:</label>
			<div class="controls">
				<input name="noWx" type="text" maxlength="20" class="input-xlarge" value="${guidMember.noWx }" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">性别:</label>
			<div class="controls">
				<span><label><input name="gender" type="radio" value="male" ${'male' eq guidMember.gender ? 'checked="checked"' : '' }> 男</label></span>
                <span><label><input name="gender" type="radio" value="female" ${'female' eq guidMember.gender ? 'checked="checked"' : '' }> 女</label></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出生年月:</label>
			<div class="controls">
				<input name="birthDate" type="text" readonly="readonly" maxlength="20" class="input-xlarge Wdate"
					value="<fmt:formatDate value="${guidMemberRw.birthDate }" pattern="yyyy-MM-dd"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">喜好:</label>
			<div class="controls">
				<input name="userLike" type="text" maxlength="200" class="input-xlarge" value="${guidMemberRw.userLike }" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所在地区:</label>
			<div class="controls">
				<input name="address" type="text" maxlength="200" class="input-xlarge" value="${guidMember.address }" />
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">用户角色:</label>
			<div class="controls">
				<form:checkboxes path="roleIdList" items="${allRoles}" itemLabel="name" itemValue="id" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<c:if test="${not empty user.id}">
			<div class="control-group">
				<label class="control-label">创建时间:</label>
				<div class="controls">
					<label class="lbl"><fmt:formatDate value="${user.createDate}" type="both" dateStyle="full"/></label>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">最后登陆:</label>
				<div class="controls">
					<label class="lbl">IP: ${user.loginIp}&nbsp;&nbsp;&nbsp;&nbsp;时间：<fmt:formatDate value="${user.loginDate}" type="both" dateStyle="full"/></label>
				</div>
			</div>
		</c:if>
		
		<div class="form-actions">
			<shiro:hasPermission name="sys:user:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
	</form:form>
	  <script>
	  
	  
	  
		var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
			browse_button : 'image_btn',
			url : '${ctx}/file/upload?dirName=photo',
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
				width:400,
				height:400
			}
		});
		uploader.init(); //初始化
		uploader.bind('FilesAdded',function(uploader,files){
			if(files.length>0){
				uploader.start();
			}
		});
		uploader.bind('Error',function(uploader,errObject){
			if(errObject.code!=-602){
				showTip(errObject.message,"info");
			}
		});
		
		uploader.bind('FileUploaded',function(uploader,file,responseObject){
				var response = $.parseJSON(responseObject.response);
				$("#image_btn").html('<img width="120px" height="120px" src="'+uploadUrl+'/oms'+response.url+'"/>');
				$("#input_image").val("/oms" + response.url);
		});
		
		//初始化密码窗口
		function resetPwdInit(){
			var userId=$("#id").val();
			if(userId==""){
				$("#resetPwd1").click();
			}
		}
		resetPwdInit();
    </script>

</body>
</html>