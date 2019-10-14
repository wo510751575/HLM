<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${not empty data.code?'敏感词修改':'敏感词添加'}</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
	<link href="${ctxStatic}/common/select2.css" type="text/css" rel="stylesheet" />
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
.select2-container-multi .select2-choices{
    width: 281px;
}


   </style>
   <script src="${ctxStatic}/common/plupload.full.min.js" type="text/javascript"></script>
</head>
<body>
<div class="container">
    <ul class="nav nav-tabs">
        <li><a href="${ctx}/weixin/imSensitiveWords">敏感词列表</a></li>
	    <li class="active"><a href="${ctx}/weixin/imSensitiveWords/form?code=${data.code}">${not empty data.code?'敏感词修改':'敏感词添加'}</a></li> 
    </ul>
     <tags:message content="${message}"/>
     <form id="inputForm" action="${ctx}/weixin/imSensitiveWords/${not empty data.code?'edit':'save'}" method="post" enctype="multipart/form-data" class="form-horizontal">
        <input id="code" name="code" type="hidden" value="${data.code}"/>
        <div id="base_div" class="tab_div">
        
             <%-- <div class="control-group">
                <label class="control-label">显示序号:</label>
                <div class="controls">
                    <input type="text" id="showIndex" name="showIndex"  maxlength="11" class="required input-xlarge number digits" min="1" value="${data.showIndex}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div> --%>
                
        
          <div class="control-group">
                <label class="control-label">敏感词:</label>
                <div class="controls">
                    <input type="text" id="word" name="word"  maxlength="50" class="required input-xlarge" value="${data.word}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
			
			<div class="control-group" >
			<label class="control-label">状态：</label>
			<div class="controls">
				 <select  name="status" id="status" class="input-xlarge required" >
					<option value=1 <c:if test="${data.status eq 1}">selected</c:if> >有效</option>
					<option value=0 <c:if test="${data.status eq 0}">selected</c:if>>无效</option>
				 </select>
				<span class="help-inline"><font color="red">*</font> </span>
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