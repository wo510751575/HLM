<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${not empty data.code?'VR素材类型修改':'VR素材类型添加'}</title>
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
    	
    	$(".addconfig").click(function(){
			
			var newCp='<div class="controls newCategory">'
			+'<input type="text"  maxlength="50" class="required copyLine"/>'
			+'<span class="help-inline"><font color="red">'+'&nbsp;'+'*</font></span>'
            +'</div>'+'&nbsp;';
			
			$(".category").prepend(newCp);
			
		});
    	
        $("#inputForm").validate({
            submitHandler: function(form){
            	//封装素材类型分类对象
            	var category={};
            	var categoryList=[];
    			$(".copyLine").each(function(){
    				var code=$(this).data("code");
    				var categoryName=$(this).val();
            		var c={code:code,categoryName:categoryName};
    				categoryList.push(c);
    			});
    			category.categoryList=categoryList;
    			//console.log(JSON.stringify(category));return false;
    		    $("#categoryName").val(JSON.stringify(category));
            	
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
        <li><a href="${ctx}/cm/vrMaterialType/">VR素材类型列表</a></li>
	    <li class="active"><a href="${ctx}/cm/vrMaterialType/from?code=${data.code}">${not empty data.code?'VR素材类型修改':'VR素材类型添加'}</a></li> 
    </ul>
     <tags:message content="${message}"/>
     <form id="inputForm" action="${ctx}/cm/vrMaterialType/${not empty data.code?'edit':'save'}" method="post" enctype="multipart/form-data" class="form-horizontal">
     	<input id="repMsg" name="repMsg" style="display:none" value="${repMsg}"/>
        <input id="code" name="code" type="hidden" value="${data.code}"/>
        <input id="merchantNo" name="merchantNo" type="hidden" value="${data.merchantNo}"/>
        <input id="categoryName" name="categoryName" type="hidden" />
        <div id="base_div" class="tab_div">                   
<%--             <c:if test="${not empty data.code}">readonly="readonly"</c:if> --%>
              <div class="control-group">
                <label class="control-label">素材类型名称:</label>
                <div class="controls">
                    <input type="text" id="typeName" name="typeName"  maxlength="50" class="required " value="${data.typeName}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
                <label class="control-label">素材类型分类:</label>
              <div class="control-group category">
              	<c:if test="${not empty categoryNameList}">
	              	<c:forEach items="${categoryNameList}" var="cg" varStatus="number">
	              		<div class="controls newCategory">
							<input type="text"  maxlength="50"  class="required copyLine" value="${cg.categoryName}" data-code="${cg.code}"/>
							<span class="help-inline"><font color="red">&nbsp;*</font></span>
			            </div>&nbsp;
	              	</c:forEach>
	              	<div class="controls">
	                    <input class="btn btn-primary addconfig" class="required" type="button" value="+"/>
                	</div>
              	</c:if>
                <c:if test="${empty categoryNameList}">
                	<div class="controls">
	                    <input type="text"  maxlength="50" class="required copyLine"/>
	                    <span class="help-inline"><font color="red">*</font></span>
	                    <input class="btn btn-primary addconfig" type="button"  value="+"/>
                    </div>
                </c:if>
            </div>
            
              <div class="control-group">
                <label class="control-label">分类排序:</label>
                <div class="controls">
                    <input type="number" id="showIndex" name="showIndex"  maxlength="80" class="required " value="${data.showIndex}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                    <span class="help-inline"><font color="#A9A9A9">注释：排列顺序，升序（数值越大越靠后)</font></span>
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