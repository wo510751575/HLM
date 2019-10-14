<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${not empty data.code?'商家预设问题修改':'商家预设问题添加'}</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
    <script type="text/javascript">
  //是否包含字符串
	function isContains(str, substr) {
	    return str.indexOf(substr) >= 0;
	}
    $(document).ready(function() {
    	var radioIsAll = $('input[type=radio][name=isAll]:checked').val();
    	
    	if(radioIsAll == 2){
    		$("#problemWord").val('全局');
    		$("#problemContent").val('全局');
    		
    		$("#problem").hide();
    		$("#word").hide();
    	}
    	
    	$('input[type=radio][name=isAll]').change(function() {
    		var radioIsAll = $("input[type=radio][name=isAll]:checked").val();
        	
        	if(radioIsAll == 2){
        		$("#problemWord").val('全局');
        		$("#problemContent").val('全局');
        		
        		$("#problem").hide();
        		$("#word").hide();
        	}
        	if(radioIsAll == 1){
        		$("#problemWord").val('');
        		$("#problemContent").val('');
        		$("#problem").show();
        		$("#word").show();
        	}
    	});
    	  
    	$("#createWords").click(function(){
    		loading("正在生成，请稍等...");
        	var content = $("textarea[name=problemContent]").val()||"";
        	$.ajax({
                type:"POST",
                url:"${ctx}/ai/merchantPreProblem/createWords",
                data:{content:content},
                success:function(result){
                	closeLoading();
                	$("#problemWord").val(result);
                }
            });		    		
        });
    	
    	var repMsg=$("#repMsg").val();
		if(repMsg){
			showTip(repMsg);
			$("#repMsg").val("");
		}
    	
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

   </style>
</head>
<body>
<div class="container">
	<input id="repMsg" name="repMsg" style="display:none" value="${repMsg}"/>
    <ul class="nav nav-tabs">
        <li><a href="${ctx}/ai/merchantPreProblem/">商家预设问题列表</a></li>
	    <li class="active"><a href="${ctx}/ai/merchantPreProblem/form?code=${data.code}">${not empty data.code?'商家预设问题修改':'商家预设问题添加'}</a></li>
	    <li ><a href="${ctx}/ai/merchantPreProblem/selectAutoReplyList">托管列表</a></li>
		<li ><a href="${ctx}/ai/merchantPreProblem/toAddAutoReply">新增托管</a></li> 
    </ul>
     <tags:message content="${message}"/>
     <form id="inputForm" action="${ctx}/ai/merchantPreProblem/${not empty data.code?'edit':'save'}" method="post" enctype="multipart/form-data" class="form-horizontal">
        <input id="code" name="code" type="hidden" value="${data.code}"/>
        <input id="merchantNo" name="merchantNo" type="hidden" value="${data.merchantNo}"/>
        <input id="answers.code" name="answers.code" type="hidden" value="${data.answers.code}"/>
        <div id="base_div" class="tab_div">  
          
            <div class="control-group" id="flagRadio">
	              <label class="control-label">状态:</label>
	                <div class="controls">
							<span>
							<input name="status" class="required" type="radio" value="1" <c:if test="${'1' eq data.status}">checked="checked"</c:if>>
							<label >正常</label>
							</span>
							<span>
							<input name="status" class="required" type="radio" value="2" <c:if test="${'2' eq data.status}">checked="checked"</c:if>>
							<label >禁用</label>
							</span>
							<span class="help-inline"><font color="red">*</font></span>
					</div>
	         </div>
	         <div class="control-group" id="flagRadio2">
	              <label class="control-label">范围:</label>
	                <div class="controls">
							<span>
							<input name="isAll" class="required" type="radio" value="1" <c:if test="${'1' eq data.isAll}">checked="checked"</c:if>>
							<label >非全局</label>
							</span>
							<span>
							<input name="isAll" class="required" type="radio" value="2" <c:if test="${'2' eq data.isAll}">checked="checked"</c:if>>
							<label >全局</label>
							</span>
							<span class="help-inline"><font color="red">*&nbsp;&nbsp;(全局：忽略问题，直接回复客户下面配置内容)</font></span>
					</div>
	         </div>
            <div class="control-group" id="problem">
                <label class="control-label">问题表述:</label>
                <div class="controls">
                 <textarea class="editor input-xxlarge required" rows="5" maxlength="2000" name="problemContent" id="problemContent">${data.problemContent }</textarea>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            
            <div class="control-group" id="word">
                <label class="control-label">关键词:</label>
                <div class="controls">
                    <input type="text" id="problemWord" name="problemWord" maxlength="2000" class="required input-xlarge"
                    value="<c:forEach items='${data.words}' var='preWord' varStatus='number'><c:if test='${number.last}'><c:if test='${not empty preWord.word}'>${preWord.word}</c:if></c:if><c:if test='${not number.last}'><c:if test='${not empty preWord.word}'>${preWord.word},</c:if></c:if></c:forEach>"
                    />
                    <input id="createWords" class="btn btn-primary" type="hidden" value="生成关键词"/>
                    <span class="help-inline"><font color="red">*&nbsp;&nbsp;(多个请用英文逗号","分开)</font></span>
                </div>
            </div>
            <div class="control-group" >
                <label class="control-label">回答内容:</label>
                <div class="controls">
                 <textarea class="editor input-xxlarge required" rows="5" maxlength="2000" name="answers.answerContent" >${data.answerContent }</textarea>
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