<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${not empty data.code?'服务项目修改':'服务项目添加'}</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
	<link href="${ctxStatic}/common/select2.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript">
    $(document).ready(function() {
    	$('#shopNo').select2();
    	
        $("#inputForm").validate({
            submitHandler: function(form){
            	if(!$("#shopNo").val()){
            		alertx("请选择终端!");
            		return false;
            	}
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
        
        $("#shopNo").change(function(){
    		$("#shopName").val($(this).find("option:selected").text());
        });
        $("#shopNo").change();
        
        
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
</head>
<body>
<div class="container">
    <ul class="nav nav-tabs">
        <li><a href="${ctx}/member/serviceProject">服务项目列表</a></li>
	    <li class="active"><a href="${ctx}/member/serviceProject/form?code=${data.code}">${not empty data.code?'服务项目修改':'服务项目添加'}</a></li> 
    </ul>
     <tags:message content="${message}"/>
     <form id="inputForm" action="${ctx}/member/serviceProject/${not empty data.code?'edit':'save'}" method="post" enctype="multipart/form-data" class="form-horizontal">
        <input id="code" name="code" type="hidden" value="${data.code}"/>
        <input id="shopName" name="shopName" type="hidden" value="${data.shopName}"/>
        
        <div id="base_div" class="tab_div">   
                        
            <div class="control-group">
                <label class="control-label">显示序号:</label>
                <div class="controls">
                    <input type="text" id="showIndex" name="showIndex"  maxlength="11" class="required input-xlarge number digits" min="1" value="${data.showIndex}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
             
            <div class="control-group">
                <label class="control-label">项目名称:</label>
                <div class="controls">
                    <input type="text" id="projectName" name="projectName"  maxlength="40" class="required input-xlarge" value="${data.projectName}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
                         
              <div class="control-group">
                <label class="control-label">选择终端:</label>
                <div class="controls">
                 <div class="form-group form-group-select2">
					<select name="shopNo" id="shopNo"  style="width:220px">
			 			<c:forEach items="${shopList}" var="item">
			 			<option value="${item.shopNo}"<c:if test="${item.shopNo eq data.shopNo}">selected="selected"</c:if>>${item.shopName}</option>
			 			</c:forEach>
					</select>
					</div>
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