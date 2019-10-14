<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${not empty data.code?'任务流程修改':'任务流程添加'}</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
    <script type="text/javascript">
    $(document).ready(function() {
    	
        $("#inputForm").validate({
            submitHandler: function(form){
                //loading('正在提交，请稍等...');
               /* $.ajax({
                    type:"POST",
                    url:$("#inputForm").attr("action"),
                    data:$(form).serialize(),
                    dataType:'JSON',
                    async:false,
                    success:function(result){
                        if(result.errorCode=='0'){
                            alertx("保存成功！");
                            window.location.href="${ctx}/business/materialtype?timestamp="+new Date().getTime();
                        }else{
                            alertx(result.errorMsg);
                        }
                    }
                }); */
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
        	$("#nameList").val(text);
        });
        
        $("#nxteCodeListSec").change(function(){
        	var text = $(this).find("option:selected").text();
        	$("#nextNameList").val(text);
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
        <li><a href="${ctx}/baseConfig/comTaskWorkflow">任务流程列表</a></li>
	    <li class="active"><a href="${ctx}/baseConfig/comTaskWorkflow/form?code=${data.code}">${not empty data.code?'任务流程修改':'任务流程添加'}</a></li> 
    </ul>
     <tags:message content="${message}"/>
     <form id="inputForm" action="${ctx}/baseConfig/comTaskWorkflow/${not empty data.code?'edit':'save'}" onsubmit="return false;" method="post" enctype="multipart/form-data" class="form-horizontal">
        <input id="code" name="code" type="hidden" value="${data.code}"/>
        <input id="merchantNo" name="merchantNo" type="hidden" value="${data.merchantNo}"/>    
       <input id="nameList" name="nameList" type="hidden" value="${data.nameList}" />
       <input id="nextNameList" name="nextNameList" type="hidden"value="${data.nextNameList}"/>
       
        <div id="base_div" class="tab_div"> 
                
             <div class="control-group">
                <label class="control-label">任务名称:</label>
                <div class="controls">
                    <select name="codeList" id="codeListSec" required>
			 			<option value="">全部</option>
			 			<c:forEach items="${comTaskChoose}" var="item">
			 			<option value="${item.codeList}" <c:if test="${item.codeList eq data.codeList}">selected="selected"</c:if> >${item.nameList}</option>
			 			 <span class="help-inline"><font color="red">*</font></span>	 
			 			</c:forEach>
					 </select>
					 <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
                         
            <div class="control-group">
                <label class="control-label">下一个任务名称:</label>
                <div class="controls">
                    <select name="nextCodeList" id="nxteCodeListSec" required>
			 			<option value="">全部</option>
			 			<c:forEach items="${comTaskChoose}" var="item">
			 			<option value="${item.codeList}" <c:if test="${item.codeList eq data.nextCodeList}">selected="selected"</c:if> >${item.nextNameList}</option>	 
			 			</c:forEach>
					 </select>
					 <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">备注:</label>
                <div class="controls">
                    <textarea class="editor" name="remark"  >${data.remark}</textarea>
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