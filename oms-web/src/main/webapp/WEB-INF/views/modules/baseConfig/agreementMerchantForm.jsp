<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${not empty data.code?'商户服务协议修改':'商户服务协议添加'}</title>
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
        <li><a href="${ctx}/baseConfig/agreementMerchant">商户服务协议列表</a></li>
	    <li class="active"><a href="${ctx}/baseConfig/agreementMerchant/form?code=${data.code}">商户服务协议${not empty query.id?'修改':'添加'}</a></li> 
    </ul><br/>
    <form id="inputForm" action="${ctx}/baseConfig/agreementMerchant/${not empty data.code?'edit':'save'}" onsubmit="return false;" method="post" enctype="multipart/form-data" class="form-horizontal">
        
        <input id="code" name="code" type="hidden" value="${data.code}"/>
        <input id="merchantNo" name="merchantNo" type="hidden" value="${data.merchantNo}"/>
        <input id="createId" name="createId" type="hidden" value="${data.createId}"/>
        <input id="createDate" name="createDate" type="hidden" value="${data.createDate}"/>
        <input id="updateId" name="updateId" type="hidden" value="${data.updateId}"/>
        <input id="updateDate" name="updateDate" type="hidden" value="${data.updateDate}"/>
    
        <tags:message content="${message}"/>
        <div id="base_div" class="tab_div">
             <div class="control-group">
                <label class="control-label">服务协议:</label>
                <div class="controls">
                     <textarea class="editor" name="agreement" id="editor">${data.agreement }</textarea>
                </div>
            </div>
        </div>
        
           <div class="control-group">
               <label class="control-label">商户协议类型:</label>
                <div class="controls">
                  <select name=agreementType id="agreementType"  class="required">
			 		<c:forEach items="${AgreementType}" var="item">			 		
			 		<option value="${item}" <c:if test="${item eq data.agreementType}">selected="selected"</c:if>>${item.name}</option>
			 		</c:forEach>
				</select>
                     <span class="help-inline"><font color="red">*</font></span>
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