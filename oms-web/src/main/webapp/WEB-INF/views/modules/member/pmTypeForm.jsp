<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${not empty data.code?'客户分类修改':'客户分类添加'}</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
    <script type="text/javascript">
    jQuery.validator.addMethod("isContainsSpecialChar", function(value, element) {
        var reg = RegExp(/[(\ )(\`)(\~)(\!)(\@)(\#)(\$)(\%)(\^)(\&)(\*)()()(\+)(\=)(\|)(\{)(\})(\')(\:)(\;)(\')(',)()()(\.)(\<)(\>)(\/)(\?)(\~)(\！)(\@)(\#)(\￥)(\%)(\…)(\&)(\*)(\（)(\）)(\—)(\+)(\|)(\{)(\})(\【)(\】)(\‘)(\；)(\：)(\”)(\“)(\’)(\。)(\，)(\、)(\？)]+/);
        return this.optional(element) || !reg.test(value);
      }, "含有中英文特殊字符");

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
            },
            rules:{
              typeName: "isContainsSpecialChar",
            },
        });

        if($("#radioStatus").val()=='Y'){
        	$("#rdo1").prop("checked",true);
        }else if($("#radioStatus").val()=='N'){
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
        <li><a href="${ctx}/member/pmType">客户分类列表</a></li>
	    <li class="active"><a href="${ctx}/member/pmType/form?code=${data.code}">${not empty data.code?'客户分类修改':'客户分类添加'}</a></li>
    </ul><br/>
    <form id="inputForm" action="${ctx}/member/pmType/${not empty data.code?'edit':'save'}" onsubmit="return false;" method="post" enctype="multipart/form-data" class="form-horizontal">
        <!-- 会员号 -->
        <input id="code" name="code" type="hidden" value="${data.code}"/>
         <input id="merchantNo" name="merchantNo" type="hidden" value="${data.merchantNo}"/>
        <input id="memberNo" name="memberNo" type="hidden" value="${data.memberNo}"/>
        <input id="memberName" name="memberName" type="hidden" value="${data.memberName}"/>
        <input id="pmTypeType" name="pmNum" type="hidden" value="${data.pmTypeType}"/>
        <input id="createId" name="createId" type="hidden" value="${data.createId}"/>
         <input id="radioStatus" name="radioStatus" type="hidden" value="${data.status}"/>
         <input id="oldPmTypeName" name="oldPmTypeName" type="hidden" value="${data.typeName}"/>

        <tags:message content="${message}"/>
        <div id="base_div" class="tab_div">
<!-- 			<div class="control-group"> -->
<!--                <label class="control-label">分类编码:</label> -->
<!--                 <div class="controls"> -->
<%--                 	<c:if test="${not empty data.code}">${data.pmTypeType}</c:if> --%>
<%--                 	<c:if test="${empty data.code}"> --%>
<%--                     <input type="text" id="pmTypeType" name="pmTypeType"  maxlength="15" class="required input-xxlarge" value="${data.pmTypeType}"  --%>
<!--                     /> -->
<!--                     <span class="help-inline"><font color="red">*</font></span> -->
<%--                     </c:if> --%>
<!--                 </div> -->
<!--             </div> -->
            <div class="control-group">
               <label class="control-label">分类名称:</label>
                <div class="controls">
                    <input type="text" id="typeName" name="typeName"  maxlength="100" class="required input-xxlarge" value="${data.typeName}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>

  <%--           <div class="control-group">
               <label class="control-label">客户分类类型:</label>
                <div class="controls">
                	<select name="pmTypeType" class="required input-xxlarge" aria-required="true" aria-invalid="false">
							<c:forEach items="${pmTypes}" var="item">
								<option value="${item}" <c:if test="${item eq data.pmTypeType}">selected="selected"</c:if>>${item.name}</option>
							</c:forEach>
					</select>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
             --%>
             <div class="control-group">
               <label class="control-label">排序值:</label>
                <div class="controls">
                    <input id="seq" name="seq" htmlEscape="false" type="number" maxlength="10" class="required input-xxlarge" value="${data.seq}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>

<!--              <div class="control-group"> -->
<!--                <label class="control-label">跟进频率值:</label> -->
<!--                 <div class="controls"> -->
<%--                     <input id="seq" name="freValue" htmlEscape="false" type="number" maxlength="10" class="required input-xxlarge" value="${data.freValue}"/>小时 --%>
<!--                     <span class="help-inline"><font color="red">*</font></span> -->
<!--                 </div> -->
<!--             </div> -->

<!--          <div class="control-group">
	          <div>
	            <label class="control-label">状态选定:</label>
	            <input id="rdo1" name="status" type="radio"  value="Y"  checked="checked" class="required"/>启用
	            <input id="rdo2" name="status" type="radio" value="N"  class="required"/>禁用
	            <span class="help-inline"><font color="red">*</font></span>	checked="checked"
	          </div>
	       </div> -->

       </div>
        <div class="form-actions">
            <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
        </div>
    </form>
    </div>
</body>
</html>
