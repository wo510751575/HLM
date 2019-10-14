<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>系统参数修改</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
    <script type="text/javascript">
    $(document).ready(function() {
    	
        $("#inputForm").validate({
            submitHandler: function(form){
                form.submit();
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
        
        if($("#radioStatus").val()=='NORMAL'){
        	$("#rdo1").prop("checked",true);
        }else{
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
</head>
<body>
<div class="container">
    <ul class="nav nav-tabs">
        <li><a href="${ctx}/cc/systemParams/">系统参数列表</a></li>
    </ul><br/>
    <form id="inputForm" action="${ctx}/cc/systemParams/${not empty data.groupName?'edit':'save'}" onsubmit="return false;" method="post" enctype="multipart/form-data" class="form-horizontal">
        <input id="groupName" name="groupName" type="hidden" value="${data.groupName}"/>
         <input id="sysParamName" name="sysParamName" type="hidden" value="${data.sysParamName}"/>
         <input id="systemAliasName" name="systemAliasName" type="hidden" value="${data.systemAliasName}"/>
         <input id="onlyAdminModify" name="onlyAdminModify" type="hidden" value="${data.onlyAdminModify}"/>
        <tags:message content="${message}"/>
        <div id="base_div" class="tab_div">
        
<!--            <div class="control-group"> -->
<!--                <label class="control-label">系统参数名:</label> -->
<!--                 <div class="controls"> -->
<%--                     <input type="text" id="sysParamName" name="sysParamName"  maxlength="100" class="required input-xxlarge" value="${data.sysParamName}"/> --%>
<!--                     <span class="help-inline"><font color="red">*</font></span> -->
<!--                 </div> -->
<!--             </div> -->
            
<!--               <div class="control-group"> -->
<!--                <label class="control-label">分组信息:</label> -->
<!--                 <div class="controls"> -->
<%--                     <input type="text" id="groupName" name="groupName"  maxlength="100" class="required input-xxlarge" value="${data.groupName}"/> --%>
<!--                     <span class="help-inline"><font color="red">*</font></span> -->
<!--                 </div> -->
<!--             </div> -->

            <div class="control-group">
               <label class="control-label">系统参数值:</label>
                <div class="controls">
                    <input type="text" id="sysParamValue" name="sysParamValue"  maxlength="100" class="required input-xxlarge" value="${data.sysParamValue}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
               
            
            
             <div class="control-group">
               <label class="control-label">参数信息备注:</label>
                <div class="controls">
                    <input type="text" id="remark" name="remark"  maxlength="100" class="required input-xxlarge" value="${data.remark}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
             </div>
             
<!--               <div class="control-group"> -->
<!--                <label class="control-label">所属系统:</label> -->
<!--                 <div class="controls"> -->
<%--                     <input type="text" id="systemAliasName" name="systemAliasName"  maxlength="100" class="required input-xxlarge" value="${data.systemAliasName}"/> --%>
<!--                     <span class="help-inline"><font color="red">*</font></span> -->
<!--                 </div> -->
<!--              </div> -->
             
<!--              <div class="control-group"> -->
<!--                <label class="control-label">是否运维可修改:</label> -->
<!--                 <div class="controls"> -->
<%--                     <input type="text" id="onlyAdminModify" name="onlyAdminModify"  maxlength="100" class="required input-xxlarge" value="${data.onlyAdminModify}"/> --%>
<!--                     <span class="help-inline"><font color="red">*</font></span> -->
<!--                 </div> -->
<!--              </div> -->

        </div>
        <div class="form-actions">
            <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
        </div>
    </form>
    </div>
</body>
</html>