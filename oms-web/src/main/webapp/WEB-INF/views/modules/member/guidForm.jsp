<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${not empty data.code?'客户分类修改':'客户分类添加'}</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
    <script type="text/javascript">
    $(document).ready(function() {
    	var repMsg=$("#returnMassage").val();
    	if(repMsg){
    		showTip(repMsg);
    		$("#repMsg").val("");
    	}
    	
        $("#inputForm").validate({
            submitHandler: function(form){
            	if(!$("#input_image").val()){
            		alertx("请先添加终端终端二维码图片!");
            		return false;
            	}
                $("#btnSubmit").attr("disabled","disabled");form.submit()
                return false;
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
        
        $("#shopNo").change(function(){
    		$("#shopName").val($(this).find("option:selected").text());
        });
        $("#shopNo").change();
        
        //工作微信联动终端二维码
//         $("#noWx").change(function(){
//         	var noWx=$(this).val();
//         	$.ajax({
//                 type:"POST",
//                 url:"${ctx}/member/shopTerminal/getByNoWx",
//                 data:{noWx:noWx},
//                 success:function(qcord){
//                 	if(qcord) {
// 	                	$("#image_btn").html('<img width="120px" height="120px" src="'+uploadUrl+qcord+'"/>');
// 						$("#input_image").val(qcord);
//                 	}
//                 }
//             });		    		

//         });
//         $("#noWx").change();
        
        if($("#radioStatus").val()=='NORMAL'){
        	$("#rdo1").prop("checked",true);
        }else if($("#radioStatus").val()=='FREEZE'){
        	$("#rdo2").prop("checked",true);
        }
        
    });
    

    function tabChange(id,ths){
        $(".tab_div").hide();
        $(".nav-child li").removeClass("active");
        $(id).show();
        $(ths).addClass("active");
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
	img {
	    border: 0 none;
	    height: 80px;
	    max-width: 100%;
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
    	<style type="text/css">
	.container{
	padding: 20px 30px;
    width: 100%;
    min-height: 800px;
    background: #fff;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
	}
	.nav-tabs > li > a {
    padding-top: 0px;
}
	</style>
<script src="${ctxStatic}/common/plupload.full.min.js" type="text/javascript"></script>
</head>
<body>
<div class="container">
    <ul class="nav nav-tabs">
        <li><a href="${ctx}/member/guid">店员列表</a></li>
	    <li class="active"><a href="${ctx}/member/guid/form?code=${data.code}">${not empty data.code?'店员信息修改':'店员列表'}</a></li> 
    </ul><br/>
    <form id="inputForm" action="${ctx}/member/guid/${not empty data.code?'edit':'save'}" onsubmit="return false;" method="post" enctype="multipart/form-data" class="form-horizontal">
        <input id="code" name="code" type="hidden" value="${data.code}"/>
         <input id="radioStatus" name="radioStatus" type="hidden" value="${data.status}"/>
         <input id="shopName" name="shopName" type="hidden" value="${data.shopName}"/>
         <input id="memberNo" name="memberNo" type="hidden" value="${data.memberNo}"/>
          <input id="returnMassage" name="returnMassage" type="hidden" value="${returnMassage}"/>
           <input id="pageNo" name="pageNo" type="hidden" value="${data.pageNo}"/>
        <tags:message content="${massage}"/>
        <div id="base_div" class="tab_div">
        
        
          <div class="control-group">
               <label class="control-label">姓名:</label>
                <div class="controls">
                    <input type="text" id="memberName" name="memberName"  maxlength="100" class="required input-xxlarge" value="${data.memberName}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
           
           
               <div class="control-group">
               <label class="control-label">手机号码:</label>
                <div class="controls">
                    <input type="number" id="mobile" name="mobile"  maxlength="11" class="required input-xxlarge" value="${data.mobile}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            
             <div class="control-group">
               <label class="control-label">工作微信号:</label>
                <div class="controls">
                	<%-- 
                		当前版本敏华移动终端不再使用中控，所以不用对中控微信进行有效性校验	
                		update by 	zengchuiyu 
                		update date	2018-06-04
                	<select name="noWx" id="noWx" class="select valid " aria-required="true" aria-invalid="false">
						<c:forEach items="${shopTerminals}" var="item">
						<option value="${item.noWx}" <c:if test="${item.noWx eq data.noWx}">selected="selected"</c:if>>${item.noWx}</option>
					    </c:forEach>
					</select> --%>
                    <input type="text" id="noWx" name="noWx"  maxlength="100" class="required input-xxlarge" value="${data.noWx}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
             <div class="control-group">
               <label class="control-label">个人微信号:</label>
                <div class="controls">
                    <input type="text" id="noWxPersonal" name="noWxPersonal"  maxlength="100" class="input-xxlarge" value="${data.noWxPersonal}"/>
                </div>
            </div>
            
 <%--             <div class="control-group">
               <label class="control-label">手机号码:</label>
                <div class="controls">
                    <label class="lbl">${data.mobile}</label>
                </div>
            </div> --%>
            
           <div class="control-group">
               <label class="control-label">手机串号:</label>
                <div class="controls">
                    <input type="number" id="imei" name="imei"  maxlength="100" class="input-xxlarge" value="${data.imei}"/>
                    <span class="help-inline"><font color="red"></font></span>
                </div>
            </div>
            
<%--              <div class="control-group">
               <label class="control-label">手机串号:</label>
                <div class="controls">
                    <label class="lbl">${data.imei}</label>
                </div>
            </div> --%>
            
            
             <div class="control-group">
               <label class="control-label">性别:</label>
                <div class="controls">
                  <select name="gender" class="select valid" aria-required="true" aria-invalid="false">
<%-- 						<c:forEach items="${genders}" var="item"> --%>
<%-- 						<option value="${item}" <c:if test="${item eq data.gender}">selected="selected"</c:if>>${item.name}</option> --%>
<%-- 					    </c:forEach> --%>
							<option value="MALE" <c:if test="${'MALE' eq data.gender}">selected="selected"</c:if>>男</option>
							<option value="FEMALE" <c:if test="${'FEMALE' eq data.gender}">selected="selected"</c:if>>女</option>
					</select>
                </div>
            </div>
            
             <div class="control-group">
               <label class="control-label">年龄:</label>
                <div class="controls">
                    <input type="text" id="age" name="age"  maxlength="3"  value="${data.age}" class="required number"/>
		    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            
            
                                       
             <div class="control-group">
               <label class="control-label">入职时间:</label>
                <div class="controls">
                    <input type="text" id="workDate" name="workDate"  maxlength="100" class="required input-xxlarge" value='<fmt:formatDate value="${data.workDate}" pattern="yyyy-MM-dd"/>' />
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            
             <div class="control-group">
               <label class="control-label">所属区域:</label>
                <div class="controls">
                	<select name="areaCode" class="select valid" aria-required="true" aria-invalid="false">
					    <c:forEach items="${fns:getDictList('erp_dict_1')}" var="item">
						<option value="${item.value}"  <c:if test="${item.value eq data.areaCode}">selected="selected"</c:if>>${item.label}</option>
					</c:forEach>
					</select>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
             <div class="control-group">
               <label class="control-label">职位:</label>
                <div class="controls">
                   <label class="lbl">导购</label>
                </div>
            </div>
             <div class="control-group">
               <label class="control-label">所属终端：</label>
                <div class="controls">
                	<select name="shopNo" id="shopNo" class="select valid" aria-required="true" aria-invalid="false" <c:if test="${not empty data.code}">disabled="disabled"</c:if>>
						<c:forEach items="${shops}" var="item">
						<option value="${item.shopNo}" <c:if test="${item.shopNo eq data.shopNo}">selected="selected"</c:if>>${item.shopName}</option>
					    </c:forEach>
					</select>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            
       
            <div class="control-group">
	          <div>
	            <label class="control-label">状态选定:</label>
	             <div class="controls">
	            <input id="rdo1" name="status" type="radio"  value="NORMAL" checked="checked" class="required"/>正常
	            <input id="rdo2" name="status" type="radio" value="FREEZE"  class="required"/>冻结         
	             <input id="rdo3" name="status" type="radio" value="LEAVE"  class="required"/>离职    
	            </div>
	          </div>
	       </div>
	       
	      <div class="control-group">
	          <div>
	            <label class="control-label">是否初始化密码:</label>
	             <div class="controls">
	            <input id="rdo1" name="initial" type="radio"  value="Yes"  class="required"/>是
	            <input id="rdo2" name="initial" type="radio" value="No"  class="required" checked="checked"/>否       
	            </div>
	          </div>
	       </div>

            <div class="control-group">
				<!-- <label class="control-label">二维码图片(仅展示):</label> -->
				<label class="control-label">二维码图片(仅展示):</label>
				<div class="controls">
					<div id="image_btn"
						style="border: 1px solid #e0e6eb; width: 120px; height: 120px; line-height: 100px; text-align: center;float: left;">
						<c:if test="${!empty data.qcord}">
							<img width="120px" height="120px" src="${fns:getUploadUrl()}${data.qcord}" />
						</c:if>
						<c:if test="${empty data.qcord}">
					                                  选择图片
					       </c:if>
<%-- 						<c:if test="${empty data.qcord}">
					                                  请先在终端终端添加
					       </c:if> --%>
					</div>
					<span class="help-inline" style="width: 10px; height: 120px; line-height: 100px; text-align: center;float: left;"><font color="red">*</font></span>
					<input id="input_image" name="qcord" type="hidden"value="${data.qcord}" />
				</div>
			</div>
            
       
       </div>
        <div class="form-actions">
            <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
        </div>
    </form>
    </div>
    	<script>
		var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
			browse_button : 'image_btn',
			url : '${ctx}/file/upload?dirName=tidQcord',
			multi_selection : false,
			auto_start : true,
			flash_swf_url : '${ctxStatic}/common/Moxie.swf',
			silverlight_xap_url : '${ctxStatic}/common/Moxie.xap',
			filters : {
				mime_types : [ //只允许上传图片文件
				{
					title : "图片文件",
					extensions : "jpg,gif,png"
				} ],
				max_file_size : '5120kb',
				prevent_duplicates : true
			},
			multipart_params : {
				fileType : 'image',
				width : 1080,
				height : 1030
			}
		});
		uploader.init(); //初始化
		uploader.bind('FilesAdded', function(uploader, files) {
			if (files.length > 0) {
				uploader.start();
			}
		});
		uploader.bind('Error', function(uploader, errObject) {
			alert(errObject.message);
		});

		uploader.bind('FileUploaded',
						function(uploader, file, responseObject) {
							var response = $.parseJSON(responseObject.response);
							$("#image_btn").html('<img width="120px" height="120px" src="'+uploadUrl+'/oms'+response.url+'"/>');
							$("#input_image").val("/oms" + response.url);
						});
	</script>
</body>
</html>