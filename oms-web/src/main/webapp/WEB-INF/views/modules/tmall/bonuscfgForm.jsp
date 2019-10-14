<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${not empty data.code?'订单红包配置修改':'订单红包配置管理添加'}</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
    $(document).ready(function() {
    	jQuery.validator.addMethod("gt", function(value, element, param) {
            return parseFloat(value) >  parseFloat($(param).val());
        }, $.validator.format('输入值必须大于起始值!'));
    	
        $("#inputForm").validate({
        	rules: {
        		'bonuxMaxDec': {gt:"#bMinid"},
        		'ordAmtMaxDec':{gt:'#ordMinid'}
			},
			messages: {
				'bonuxMaxDec': {gt:"结束红包金额必须大于起始金额！"},
				'ordAmtMaxDec':{gt:"结束订单金额必须大于起始金额！"}
				 
			},
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
        <li ><a href="${ctx}/tmall/bonuscfg/list">订单红包配置列表</a></li>
	    <li class="active"><a href="${ctx}/tmall/bonuscfg/form?code=${data.code}">订单红包配置${not empty tmallBonusConfigDto.code?'修改':'添加'}</a></li> 
    </ul><br/>
    <form id="inputForm" action="${ctx}/tmall/bonuscfg/${not empty data.code?'edit':'save'}"  method="post" class="form-horizontal">
        
        <input id="code" name="code" type="hidden" value="${data.code}"/>
        
           <div class="control-group">
               <label class="control-label">订单金额:</label>
                <div class="controls">
                	<c:if test="${not empty data}">
                		<input required="required" class="input required number" id="ordMinid" name="ordAmtMinDec" value='<fmt:formatNumber value="${data.ordAmtMin/100}" pattern="#,##0.00"/>'/> -
                  		<input required="required" class="input required number" id="ordMaxid" name="ordAmtMaxDec" value='<fmt:formatNumber value="${data.ordAmtMax/100}" pattern="#,##0.00"/>'/>
                	</c:if>
                  	<c:if test="${empty data}">	
                  		<input required="required"  name="ordAmtMinDec" id="ordMinid"  class="input required number" /> -
                  		<input required="required" name="ordAmtMaxDec" id="ordMaxid" class="input required number"/>
                  	</c:if>
                     
                     	元<span class="help-inline"><font color="red"> *</font></span>
                </div>
            </div>
         	<div class="control-group">
               <label class="control-label">红包金额:</label>
                <div class="controls">
                	<c:if test="${not empty data}">
                  		<input required="required" name="bonuxMinDec" id="bMinid" class="input required number" value='<fmt:formatNumber value="${data.bonuxMin/100}" pattern="#,##0.00"/>'/> -
                  		<input required="required" name="bonuxMaxDec" id="bMaxid" class="input required number" value='<fmt:formatNumber value="${data.bonuxMax/100}" pattern="#,##0.00"/>'/>
                     </c:if>
                     <c:if test="${empty data}">
                     	<input required="required" name="bonuxMinDec" id="bMinid" class="input required number" /> -
                  		<input required="required" name="bonuxMaxDec" id="bMaxid"  class="input required number" />	
                     </c:if>
                     	元<span class="help-inline"><font color="red">*</font></span>
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