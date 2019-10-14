<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${not empty data.code?'优惠券规则修改':'优惠券规则添加'}</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
	<link rel="stylesheet" href="${ctxStatic}/bootstrap-select/bootstrap-select.min.css">
	<script src="${ctxStatic}/bootstrap-select/bootstrap-select.min.js"></script>
	<script src="${ctxStatic}/bootstrap-select/defaults-zh_CN.min.js"></script>
    <script type="text/javascript">
    function duibi(a) {
        var arr = a.split("-");
        var endtime = new Date(arr[0], arr[1], arr[2]);
        var endtimes = endtime.getTime();
        
        var date = new Date();
       var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate(); 
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = year + seperator1 + month + seperator1 + strDate;
        var year = date.getFullYear();
        var month = date.getMonth()+1;
        var day = date.getDate();
        var now = new Date(year+'-'+month+'-'+strDate);
        
        var lktimes = now.getTime();
        if (Number(endtimes) < Number(lktimes)) {
        	$("#messageBox").text("结束时间需大于或等于当天时间");
        	alert("结束时间需大于或等于当天时间");
            return false;
        } else{
            return true;
        }
    }
    $(document).ready(function() {
    	
    	$("#couponTypeCode").change(function(){
    		$("#couponType").val($(this).find("option:selected").text());
        });
        $("#couponTypeCode").change();
        $("#inputForm").validate({
            submitHandler: function(form){
            	if(duibi($("#endDate").val())){
            		var selectVal = $('#codeListSec').val();
            		var selectText = "";
            		$("#btnSubmit").attr("disabled","disabled");form.submit()
                    return false;
            	}
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
	
.btn-group.bootstrap-select.show-tick.show-menu-arrow.form-control {
	margin-left: 20px;
	width: 284px
}


   </style>
</head>
<body>
<div class="container">
    <ul class="nav nav-tabs">
        <li><a href="${ctx}/cp/coupon/rule">优惠券规则列表</a></li>
	    <li class="active"><a href="${ctx}/cp/coupon/rule/form?code=${data.code}">${not empty data.code?'优惠券规则修改':'优惠券规则添加'}</a></li> 
    </ul>
     <tags:message content="${message}"/>
     <form id="inputForm" action="${ctx}/cp/coupon/rule/${not empty data.code?'edit':'save'}" method="post" enctype="multipart/form-data" class="form-horizontal">
        <input id="code" name="code" type="hidden" value="${data.code}"/>
        <input id="couponType" name="couponType" type="hidden" value="${data.couponType}"/>
        <input name="useNums" id="useNums" type="hidden"  value="${data.useNums}"/>
        <input name="isProduce" id="isProduce" type="hidden" value="${data.isProduce}"/>
        <input id="useScope" name="useScope" type="hidden" value="ALL"/>	<!-- 电商模式-使用范围固定所有 -->
        <div id="base_div" class="tab_div">    
             <div class="control-group">
                <label class="control-label">生成券数量:</label>
                <div class="controls">
                    <input type="number" id="couponAvgNum" name="couponAvgNum" max="9999" maxlength="4" class="required input-xlarge number" value="${data.couponAvgNum}"/>
                     <span class="help-inline"><font color="red">*</font></span>
                    <br>
                    <span class="help-inline"><font color="red">给每台终端优惠券的数量，最大：9999！</font></span>
                </div>
            </div>
            
            <div class="control-group">
                <label class="control-label">优惠券面额:</label>
                <div class="controls">
                    <input type="number" onkeypress="return( /[\d]/.test(String.fromCharCode(event.keyCode) ) )" id="couponNotes" name="couponNotes"  maxlength="50" class="required input-xlarge number" value="${data.couponNotes}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">优惠券开始时间:</label>
                <div class="controls">
                    <input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-xlarge Wdate required" 
				value="<fmt:formatDate value="${data.beginDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"  />
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">优惠券结束时间:</label>
                <div class="controls">
                    <input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-xlarge Wdate required" 
				value="<fmt:formatDate value="${data.endDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true,minDate:'#beginDate'});"  onfocus="WdatePicker({minDate:'%y-%M-{%d}'})"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">优惠券名称:</label>
                <div class="controls">
                    <input type="text" id="couponName" name="couponName"  maxlength="100" class="required input-xlarge" value="${data.couponName}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">使用金额门槛:</label>
                <div class="controls">
                    <input type="text" id="couponMax" name="couponMax"  maxlength="50" class="required input-xlarge number" value="${data.couponMax}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">优惠券类型:</label>
                <div class="controls">
                     <select name="couponTypeCode" id="couponTypeCode" class="required input-xlarge">
                     	<option value="" ></option>
			 			<c:forEach items="${couponTypes}" var="item">
			 				<option value="${item.code}" <c:if test="${item.code eq data.couponTypeCode}">selected="selected"</c:if> >
			 					<c:choose>
									<c:when test="${'FULL' eq  item.couponType}">满增/减</c:when>
									<c:when test="${'COUPON' eq  item.couponType}">体验券</c:when>
									<c:when test="${'CASH' eq  item.couponType}">代金券</c:when>
									<c:when test="${'DISCOUNT' eq  item.couponType}">折扣券</c:when>
									<c:otherwise>${item.couponType}</c:otherwise>
								</c:choose>
			 				</option>
			 			</c:forEach>
					 </select>
					 <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">优惠券规则:</label>
                <div class="controls">
                 <textarea class="editor input-xlarge required" rows="5" maxlength="200" name="couponRemark" >${data.couponRemark }</textarea>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            
            
            <div class="control-group">
                <label class="control-label">发券条件:</label>
                <div class="controls">
                     <select name="toCoupon" id="toCoupon" class="required">
                     	<option value="NONE" >无</option>
						<!--  <option value="INVITE" >邀请人数</option> -->
					 </select>
					 <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">是否实名:</label>
                <div class="controls">
                     <select name="realName" id="realName" class="required">
						<option value="YES" >实名</option>
                     	<option value="NO" >不实名</option>
					 </select>
					 <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">状态:</label>
                <div class="controls">
                     <select name="ruleStatus" id="ruleStatus" class="required">
                     	<option value="YES" <c:if test="${'YES' eq data.ruleStatus}">selected="selected"</c:if> >启用</option>
                     	<option value="NO" <c:if test="${'NO' eq data.ruleStatus}">selected="selected"</c:if>>禁用</option>
					 </select>
					 <span class="help-inline"><font color="red">*</font></span>
                </div> 
            </div>
<%--             <div class="control-group">
                <label class="control-label">是否核销:</label>
                <div class="controls">
                     <select name="isWriteoff" id="isWriteoff" class="required" >
                     	<option value="YES" <c:if test="${'YES' eq data.isWriteoff}">selected="selected"</c:if>>是</option>
                     	<option value="NO" <c:if test="${'NO' eq data.isWriteoff}">selected="selected"</c:if>>否</option>
					 </select>
					 <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div> --%>
          
            <div class="control-group">
                <label class="control-label">启用时间:</label>
                <div class="controls">
                    <input id="enableDate" name="enableDate" type="text" readonly="readonly" maxlength="20" class="input-xlarge Wdate required" 
					value="<fmt:formatDate value="${data.enableDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true,minDate:'#beginDate'});"  onfocus="WdatePicker({minDate:'%y-%M-{%d}'})"/>
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