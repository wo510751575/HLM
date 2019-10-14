<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${not empty data.code?'区域经理修改':'区域经理修改'}</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
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
        
        if($("#radioStatus").val()=='NORMAL'){
        	$("#rdo1").prop("checked",true);
        }else{
        	$("#rdo2").prop("checked",true);
        }
        
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
        <li><a href="${ctx}/member/manager/">区域经理列表</a></li>
	    <li class="active"><a href="${ctx}/member/manager/form?code=${data.code}">${not empty data.code?'区域经理修改':'区域经理修改'}</a></li> 
    </ul><br/>
    <form id="inputForm" action="${ctx}/member/manager/${not empty data.code?'edit':'save'}" onsubmit="return false;" method="post" enctype="multipart/form-data" class="form-horizontal">
        <input id="code" name="code" type="hidden" value="${data.code}"/>
         <input id="radioStatus" name="radioStatus" type="hidden" value="${data.status}"/>
        <tags:message content="${message}"/>
        <div id="base_div" class="tab_div">
        
           <div class="control-group">
               <label class="control-label">姓名:</label>
                <div class="controls">
                    <input type="text" id="memberName" name="memberName"  maxlength="100" class="required input-xxlarge" value="${data.memberName}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            
           <div class="control-group">
               <label class="control-label">手机号码:</label>
                <div class="controls">
                    <input type="text" id="mobile" name="mobile"  maxlength="11" type="number" class="required input-xxlarge" value="${data.mobile}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
           
            <div class="control-group">
               <label class="control-label">手机串号:</label>
                <div class="controls">
                    <label class="lbl">${data.imei}</label>
                </div>
            </div>
               
               
            <div class="control-group">
               <label class="control-label">性别:</label>
                <div class="controls">
                  <select name="sex" class="select valid" aria-required="true" aria-invalid="false">
						<c:forEach items="${genders}" var="item">
						<option value="${item}" <c:if test="${item eq data.sex}">selected="selected"</c:if>>${item.name}</option>
					    </c:forEach>
					</select>
                </div>
            </div>
            
            
             <div class="control-group">
               <label class="control-label">入职时间:</label>
                <div class="controls">
                    <input type="text" id="workDate" name="workDate"  maxlength="100" class="required input-xxlarge" value='<fmt:formatDate value="${data.workDate}" pattern="yyyy-MM-dd"/>' />
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
             </div>
             
              <div class="control-group">
               <label class="control-label">所属区域:</label>
                <div class="controls">
                	<select name="areaCode" class="select valid" aria-required="true" aria-invalid="false">
					    <c:forEach items="${fns:getDictList('erp_dict_1')}" var="item">
						<option value="${item.value}"  <c:if test="${item.value eq data.areaCode}">selected="selected"</c:if>>${item.label}</option>
					</c:forEach>
					</select>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            
             <div class="control-group">
               <label class="control-label">职位:</label>
                <div class="controls">
                    <label class="lbl">区域经理</label>
                </div>
            </div>
            
<%--              <div class="control-group">
               <label class="control-label">所属终端：</label>
                <div class="controls">
                	<select name="shopName" class="select valid" aria-required="true" aria-invalid="false">
						<option value="">全部</option>
						<c:forEach items="${shops}" var="item">
						<option value="${item.shopName}" <c:if test="${item.shopName eq data.memberNameShop}">selected="selected"</c:if>>${item.shopName}</option>
					    </c:forEach>
					</select>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div> --%>
    
            <div class="control-group">
	          <div>
	            <label class="control-label">状态选定:</label>
	             <div class="controls">
	            <input id="rdo1" name="status" type="radio"  value="NORMAL"  class="required"/>正常
	            <input id="rdo2" name="status" type="radio" value="FREEZE"  class="required"/>冻结         
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