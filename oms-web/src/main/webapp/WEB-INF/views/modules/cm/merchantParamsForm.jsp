<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${not empty data.code?'商户配置修改':'商户配置添加'}</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
    <script type="text/javascript">
    $(document).ready(function() {
    	
        $("#inputForm").validate({
            submitHandler: function(form){
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
        $("#merchantNo").change(function(){
    		$("#merchantName").val($(this).find("option:selected").text());
        });
        $("#merchantNo").change();
        
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
        <li><a href="${ctx}/cm/merchantParams/">商户配置列表</a></li>
	    <li class="active"><a href="${ctx}/cm/merchantParams/form?code=${data.code}">${not empty data.code?'商户配置修改':'商户配置添加'}</a></li> 
    </ul>
     <tags:message content="${message}"/>
     <form id="inputForm" action="${ctx}/cm/merchantParams/${not empty data.code?'edit':'save'}" method="post" enctype="multipart/form-data" class="form-horizontal">
        <input id="code" name="code" type="hidden" value="${data.code}"/>
        <input id="merchantName" name="merchantName" type="hidden" value="${data.merchantName}"/> 
        <div id="base_div" class="tab_div">  
         
          
          <div class="control-group">
                <label class="control-label">选择商户:</label>
                <div class="controls">
                     <select name="merchantNo" id="merchantNo" class="required" <c:if test="${not empty data.code}">disabled="disabled"</c:if>>
                     	<option value="" ></option>
			 			<c:forEach items="${list}" var="item">
			 				<option value="${item.merchantNo}" <c:if test="${item.merchantNo eq data.merchantNo}">selected="selected"</c:if> >${item.merchantName}</option>
			 			</c:forEach>
					 </select>
					 <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div> 
                        
            <div class="control-group">
                <label class="control-label">系统参数名:</label>
                <div class="controls">
                    <input type="text" id="sysParamName" name="sysParamName"  maxlength="30" class="required input-xlarge" value="${data.sysParamName}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">分组信息:</label>
                <div class="controls">
                    <input type="text" id="groupName" name="groupName"  maxlength="100" class="required input-xxlarge" value="${data.groupName}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">系统参数值:</label>
                <div class="controls">
                 <textarea class="editor input-xxlarge required" rows="5" maxlength="2000" name="sysParamValue" >${data.sysParamValue }</textarea>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">参数信息备注:</label>
                <div class="controls">
                 <textarea class="editor input-xxlarge required" rows="5" maxlength="100" name="remark" >${data.remark }</textarea>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">是否仅运维能修改:</label>
                <div class="controls">
                     <select name="onlyAdminModify" id="onlyAdminModify" class="required" <c:if test="${not empty data.onlyAdminModify}">disabled="disabled"</c:if>>
                     	<option value="1" >是</option>
                     	<option value="0" >否</option>
					 </select>
					 <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            
        </div>
        <div class="form-actions">
            <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
        </div>
    </form>
    </div>
</body>
</html>