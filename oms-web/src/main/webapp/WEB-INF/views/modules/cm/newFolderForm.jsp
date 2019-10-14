<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>新建文件夹</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
	<script src="${ctxStatic}/jquery/jquery.form.js" type="text/javascript"></script>
    <script type="text/javascript">
    $(document).ready(function() {
    	var repMsg=$("#repMsg").val();
		if(repMsg){
			showTip(repMsg);
			$("#repMsg").val("");
		} 
    });
    
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
});
    
    
    
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
	padding: 10px 10px;
    width: 100%;
    height:100%;
    min-height: 800px;
    background: #fff;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
	}
	#base_div label{width: 71px;}
	#base_div .controls{margin-left: 89px;}
	.buttons{padding-left: 110px !important;}
	.controls label.error {width: auto !important;}
	.control-group {margin-bottom: 1px;}
   </style>
</head>
<body>
<div class="container">
	 <input id="repMsg" name="repMsg" style="display:none" value="${repMsg}"/>
     <tags:message content="${message}"/>
     <form id="inputForm" action="${ctx}/cm/materialVideo/save" method="post" enctype="multipart/form-data" class="form-horizontal">
        <input id="code" name="parentId" type="hidden" value="${parentId}"/>
        <div id="base_div" class="tab_div">  
          
            <div class="control-group">
                <label class="control-label">文件名:</label>
                <div class="controls">
                    <input type="text"  name="folderName"  maxlength="40" class="input required " value=""/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            
        </div>
        <div class="buttons">
            <input id="btnSubmit" class="btn btn-primary" type="submit"/>
        </div>
    </form>
    </div>
</body>
</html>