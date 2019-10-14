<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${not empty data.code?'优惠券类型修改':'优惠券类型添加'}</title>
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
        $("#codeListSec").change(function(){
        	var text = $(this).find("option:selected").text();
        	$("#taskName").val(text);
        });
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
        <li><a href="${ctx}/cp/couponType">优惠券类型列表</a></li>
	    <li class="active"><a href="${ctx}/cp/couponType/form?code=${data.code}">${empty data.code?'优惠券类型添加':'优惠券类型修改'}</a></li> 
    </ul>
     <tags:message content="${message}"/>
     <form id="inputForm" action="${ctx}/cp/couponType/${not empty data.code?'edit':'save'}" onsubmit="return false;" method="post" enctype="multipart/form-data" class="form-horizontal">
        <input id="code" name="code" type="hidden" value="${data.code}"/>
       
        <div id="base_div" class="tab_div">                   
                         
              <div class="control-group">
               <label class="control-label">优惠券类型:</label>
                <div class="controls">
                    <input type="text" id="couponType" name="couponType"  maxlength="150" class="required input-xxlarge" value="${data.couponType}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            
           <%--     <div class="control-group">
                <label class="control-label">类型说明:</label>
                <div class="controls">
                     <textarea class="editor" name="couponRemark" id="editor">${data.couponRemark}</textarea>
                </div>
            </div> --%>
            
                <div class="control-group">
                <label class="control-label">类型说明:</label>
                <div class="controls">
                 <textarea class="editor input-xxlarge required"  name="couponRemark" >${data.couponRemark }</textarea>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            
            <div class="control-group">
	          <div>
	            <label class="control-label">状态选定:</label>
	            <input id="rdo1" name="useEnable" type="radio"  value="YES"  checked="checked" class="required"/>启用
	            <input id="rdo2" name="useEnable" type="radio" value="NO"  class="required"/>禁用
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