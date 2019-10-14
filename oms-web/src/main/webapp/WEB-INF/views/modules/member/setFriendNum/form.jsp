<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${not empty data.code?'修改':'新增'}</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
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
                var alias =  $("#noWxSec option:selected").text();
                $("#alias").val(alias);
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
	    <li><a href="${ctx}/setFriendNum/">好友设置列表</a></li>
		<li class="active"><a href="${ctx}/setFriendNum/form?code=${data.code}">${not empty query.id?'好友设置修改':'好友设置新增'}</a></li> 
    </ul><br/>

    <form id="inputForm" action="${ctx}/setFriendNum/${not empty data.code?'edit':'save'}" onsubmit="return false;" method="post" enctype="multipart/form-data" class="form-horizontal">
        <input id="code" name="code" type="hidden" value="${data.code}"/>
        <input id="alias" name="alias" type="hidden" />
        
        <tags:message content="${message}"/>
        
         <div id="base_div" class="tab_div">
            
            <div class="control-group">
                <label class="control-label">微信号:</label>
                <div class="controls">
<%--                     <input type="text" class="ipt" id="alias" name="alias" value="${data.alias}" readonly="readonly"/> --%>
<!--                     <input type="button" class="i-btn" value="选择" style="margin-top: 5px;" onclick="$('#lVarWxno').css('display','flex')"/> -->
					<select class="input-xxlarge" id="noWxSec" name="noWx">
                        <c:forEach items="${shopTids}" var="item">
							<option value="${item.noWx}" <c:if test="${item.noWx eq data.noWx}">selected="selected"</c:if> >${item.alias}</option>
						</c:forEach>
                 	</select>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
          	<div class="control-group">
                <label class="control-label">好友通过时间段:</label>
                <div class="controls">
                    <input id="startDate" name="startDateStr" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
				value="<fmt:formatDate value="${data.startDate}" pattern="HH:mm"/>" onclick="WdatePicker({dateFmt:'HH:mm',isShowClear:true});"/>
				-- 
				<input id="endDate" name="endDateStr" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required" 
				value="<fmt:formatDate value="${data.endDate}" pattern="HH:mm"/>" 
				onclick="WdatePicker({dateFmt:'HH:mm',isShowClear:true});" />&nbsp;&nbsp;
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            <div class="control-group">
               <label class="control-label">好友通过数量:</label>
                <div class="controls">
                    <input type="number" id="num" name="num"  maxlength="3" class="required input-xxlarge" value="${data.num}"/>
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