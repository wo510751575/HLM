<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${not empty data.code?'修改':'添加'}</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
	<script src="${ctxStatic}/common/plupload.full.min.js" type="text/javascript"></script>
    <script type="text/javascript">
    $(document).ready(function() {
    	
        $("#inputForm").validate({
            submitHandler: function(form){
                //loading('正在提交，请稍等...');
/*                 $.ajax({
                    type:"POST",
                    url:$("#inputForm").attr("action"),
                    data:$(form).serialize(),
                    dataType:'JSON',
                    async:false,
                    success:function(result){
                        if(result.errorCode=='0'){
                            alertx("保存成功！");
                            window.location.href="${ctx}/business/line?timestamp="+new Date().getTime();
                        }else{
                            alertx(result.errorMsg);
                        }
                    }
                }); */
                //$("#btnSubmit").attr("disabled","disabled");form.submit()
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
    </style>
</head>
<body>
    <ul class="nav nav-tabs">
	    <li><a href="${ctx}/baseConfig/exGuider/">优秀员工列表</a></li>
		<shiro:hasPermission name="baseConfig:exGuider:edit">
			<li  class="active"><a href="${ctx}/baseConfig/exGuider/form">优秀员工添加</a></li>
		</shiro:hasPermission>
    </ul><br/>

    <form id="inputForm" action="${ctx}/lbaseConfig/exGuider/${not empty data.code?'edit':'save'}" onsubmit="return false;" method="post" enctype="multipart/form-data" class="form-horizontal">
        
        <input id="code" name="code" type="hidden" value="${data.code}"/>
        <input id="code" name="merchanCode" type="hidden" value="${data.merchantCode}"/>
        
        <tags:message content="${message}"/>
         <div id="base_div" class="tab_div">

              <div class="control-group">
                <label class="control-label">名称:</label>
                <div class="controls">
                    <input type="text" id="name" name="name"  maxlength="100" class="required input-xxlarge" value="${data.name}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
          <div class="control-group">
                <label class="control-label">备注:</label>
                <div class="controls">
                    <input type="text" id="name" name="remarks"  maxlength="100" class="required input-xxlarge" value="${data.remarks}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
        </div>
        <div class="form-actions">
            <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
        </div>
    </form>
</body>
</html>