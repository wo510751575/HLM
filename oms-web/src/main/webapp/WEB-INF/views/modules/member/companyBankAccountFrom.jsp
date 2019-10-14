<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${not empty data.code?'经销商账户修改':'经销商账户添加'}</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<link href="${ctxStatic}/common/select2.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript">
    $(document).ready(function() {
    	
    	$.ajax({
            type:"POST",
            url:"${ctx}/sys/area/provinceList",
            data:{},
            success:function(result){
            	for (var i = 0; i < result.length; i++) {
            		if (result[i].code == '${data.provinceCode}') {
            			$("#provinceCode").append("<option selected value='" + result[i].code + "'>" + result[i].name + "</option>")
            			$("#provinceName").val(result[i].name);
					} else {
	            		$("#provinceCode").append("<option value='" + result[i].code + "'>" + result[i].name + "</option>")
					}
				}
            	var provinceCode = $("#provinceCode").val();
            	$.ajax({
                    type:"POST",
                    url:"${ctx}/sys/area/findCityListByProvinceCode",
                    data:{provinceCode:provinceCode},
                    success:function(result){
                    	$("#cityCode").html('');
                    	for (var i = 0; i < result.length; i++) {
                    		if (result[i].code == '${data.cityCode}') {
                    			$("#cityCode").append("<option selected value='" + result[i].code + "'>" + result[i].name + "</option>")
                    			$("#cityName").val(result[i].name);
        					} else {
        	            		$("#cityCode").append("<option value='" + result[i].code + "'>" + result[i].name + "</option>")
        					}
                    		
        				}
                    }
                });
            }
        });	
    	
    	$("#provinceCode").change(function(){
    		var provinceCode = $(this).val();
    		var selectedProvinceName = $("#provinceCode").find("option:selected").text();
    		$("#provinceName").val(selectedProvinceName);
        	$.ajax({
                type:"POST",
                url:"${ctx}/sys/area/findCityListByProvinceCode",
                data:{provinceCode:provinceCode},
                success:function(result){
                	$("#cityCode").html('');
                	for (var i = 0; i < result.length; i++) {
                		if (i == 0) {
                			$("#cityName").val(result[i].name);
						}
                		$("#cityCode").append("<option value='" + result[i].code + "'>" + result[i].name + "</option>")
    				}
                }
            });	
		});
    	
    	$("#cityCode").change(function(){
    		var selectedCityName = $("#cityCode").find("option:selected").text();
    		$("#cityName").val(selectedCityName);
		});
    	
    	initView();
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
        
        /* if($("#radioStatus").val()=='NORMAL'){
        	$("#rdo1").prop("checked",true);
        }else{
        	$("#rdo2").prop("checked",true);
        } */
        
        
    	$("[name='companyNo']").change(function(){
        	var select = $(this).find("option:selected");
    		$("#companyName").val(select.text());
        });
        $("[name='companyNo']").change();
        
    });
    function initView(){
    	$('.form-group-select-css').select2();
    }

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
	
	.select2-container-multi .select2-choices{
	    width: 281px;
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
        <li><a href="${ctx}/member/companyBankAccount?companyNo=${companyNo}">银行账户列表</a></li>
	    <li class="active"><a href="${ctx}/member/companyBankAccount/form?companyNo=${companyNo}&code=${data.code}">${not empty data.code?'编辑银行账户':'添加银行账户'}</a></li>
    </ul><br/>
    <form id="inputForm" action="${ctx}/member/companyBankAccount/${not empty data.code?'edit':'save'}?companyNo=${companyNo}" onsubmit="return false;" method="post" enctype="multipart/form-data" class="form-horizontal">
        <input id="code" name="code" type="hidden" value="${data.code}"/>
        <input id="companyName" name="companyName" type="hidden" value="${data.companyName}"/>
        <tags:message content="${message}"/>
        <div id="base_div" class="tab_div">
        
           <div class="control-group">
                <label class="control-label">经销商:</label>
                <div class="controls">
	                 <div class="form-group form-group-select2">
						${company.companyName }
					</div>
                </div>
            </div>
        
          <div class="control-group">
               <label class="control-label">开户银行:</label>
                <div class="controls">
                  <select name="bankCode" class="select valid" aria-required="true" aria-invalid="false">
						<c:forEach items="${BankCodes}" var="entry">
						<option value="${entry.key}" <c:if test="${entry.key eq data.bankCode}">selected="selected"</c:if>>${entry.value}</option>
						</c:forEach>
					</select>
                </div>
            </div>
		
          <div class="control-group">
              <label class="control-label">银行卡卡号:</label>
               <div class="controls">
                   <input type="text" id="bankcardNo" name="bankcardNo"  maxlength="20"  class="required number digits" value="${data.bankcardNo}"/>
                   <span class="help-inline"><font color="red">*</font></span>
               </div>
           </div>
            
          <div class="control-group">
               <label class="control-label">开户行所在省市:</label>
                <div class="controls">
                    <select name="provinceCode" id="provinceCode"></select>
					<input id="provinceName" name="provinceName" type="hidden"/>
					<select name="cityCode"  id="cityCode"></select>
					<input id="cityName" name="cityName" type="hidden"/>
                </div>
            </div>
            
          <%--  <div class="control-group">
				<label class="control-label">开户行所在省市:</label>
				<div class="controls">
					<tags:treeselect id="area" name="cityCode" value="${data.cityCode}"
						labelName="cityName" labelValue="${data.cityName}" title="省市"
						url="/sys/area/treeData" cssClass="required"/>
					<span class="help-inline"><font color="red">*</font></span>
				</div>
			</div> --%>
           
            <div class="control-group">
               <label class="control-label">开户支行:</label>
                <div class="controls">
                    <input type="text" id="branch" name="branch"  maxlength="30"  class="required input-xxlarge" value="${data.branch}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            
            <div class="control-group">
               <label class="control-label">持卡人:</label>
                <div class="controls">
                    <input id="custName" name="custName"  maxlength="30" type="text"  class="required " value="${data.custName}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            
            <div class="control-group">
	          <div>
	            <label class="control-label">默认账户:</label>
	             <div class="controls">
	             <c:if test="${not empty data.code}">
	             	<c:choose>
	             		<c:when test="${data.isDefault == 1}">
	             			<input id="rdo1" name="isDefault" type="radio"  value="true"  class="required"  checked="checked"/>是
		            		<input id="rdo2" name="isDefault" type="radio" value="false"  class="required"/>否 
	             		</c:when>
	             		<c:otherwise>
	             			<input id="rdo1" name="isDefault" type="radio"  value="true"  class="required"/>是
		            		<input id="rdo2" name="isDefault" type="radio" value="false"  class="required" checked="checked"/>否 
	             		</c:otherwise>
	             	</c:choose>
	             </c:if>
	             <c:if test="${empty data.code}">
		            <input id="rdo1" name="isDefault" type="radio"  value="true"  class="required"/>是
		            <input id="rdo2" name="isDefault" type="radio" value="false"  class="required" checked="checked"/>否 
	             </c:if>
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