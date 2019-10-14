<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${not empty data.code?'话术修改':'话术添加'}</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
    <script type="text/javascript">
    $(document).ready(function() {
    	var repMsg=$("#repMsg").val();
		if(repMsg){
			showTip(repMsg);
			$("#repMsg").val("");
		}
    	
    	$("input[name=typeCode]").click(function(){
    		var typeName=$(this).data("name");
    		$('#typeName').val(typeName);
    	})
    	
        $("#inputForm").validate({
            submitHandler: function(form){
            	if(!$("input[name=typeCode]:checked").val()){
            		alertx("请先添加话术类型");
            		return false;
            	}
            	
                $("#btnSubmit").attr("disabled","disabled");form.submit();
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
	<input id="repMsg" name="repMsg" style="display:none" value="${repMsg}"/>
    <ul class="nav nav-tabs">
        <li><a href="${ctx}/cm/wordsInfo/">话术列表</a></li>
	    <li class="active"><a href="${ctx}/cm/commonWordsInfo/form?code=${data.code}">${not empty data.code?'话术修改':'话术添加'}</a></li> 
    </ul>
     <tags:message content="${message}"/>
     <form id="inputForm" action="${ctx}/cm/commonWordsInfo/${not empty data.code?'edit':'save'}" method="post" enctype="multipart/form-data" class="form-horizontal">
        <input id="code" name="code" type="hidden" value="${data.code}"/>
        <input id="typeName" name="typeName" type="hidden" value="${data.typeName}"/>
        <div id="base_div" class="tab_div">  
          
         	 <div class="control-group" >
	              <label class="control-label">话术类型:</label>
	                <div class="controls">
	                	<c:forEach items="${typeList}" var="item">
							<span>
							<input name="typeCode" class="required" type="radio" value="${item.code}" data-name="${item.typeName}" <c:if test="${item.code eq data.typeCode}">checked="checked"</c:if>>
							<label >${item.typeName}</label>
							</span>
	                	</c:forEach>
	                	<span class="help-inline"><font color="red">*</font></span>
					</div>
	         </div> 
            <div class="control-group" id="flagRadio">
	              <label class="control-label">是否默认:</label>
	                <div class="controls">
							<span>
							<input name="defaultFlag" class="required" type="radio" value=1 <c:if test="${1 eq data.defaultFlag}">checked="checked"</c:if>>
							<label >是</label>
							</span>
							<span>
							<input name="defaultFlag" class="required" type="radio" value=0 <c:if test="${1 ne data.defaultFlag}">checked="checked"</c:if>>
							<label >否</label>
							</span>
							<span class="help-inline"><font color="red">*</font></span>
					</div>
	         </div>
            <div class="control-group">
                <label class="control-label">话术内容:</label>
                <div class="controls">
                 <textarea class="editor input-xxlarge required" rows="5" maxlength="100" name="content" >${data.content }</textarea>
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