<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>素材模板管理</title>
    <meta name="decorator" content="default"/>
	
	<link href="${ctxStatic}/admin/css/model.css" type="text/css" rel="stylesheet" />
	<link href="${ctxStatic}/admin/css/models/phone.css" type="text/css" rel="stylesheet" />
</head>
<body>
<input type="hidden" value="${ctx}" id="ctx"> 
<input type="hidden" id="uploadUrlId" value="${fns:getUploadUrl()}">
<div class="container">
    <ul class="nav nav-tabs">
    <li ><a href="${ctx}/business/materialcommen">公用素材列表</a></li>
		<shiro:hasPermission name="business:materialcommen:edit">
			<li><a href="${ctx}/business/materialcommen/form">公用素材添加</a></li>
			<li class="active"><a href="${ctx}/business/materialcommen/form?code=${data.code}&tempId=${data.tempId}">${not empty data.tempId?'模版修改':'模版添加'}</a></li>
		</shiro:hasPermission>
    </ul><br/>

    <form id="inputForm" action="${ctx}/business/materialcommen/${not empty data.tempId?'edit':'save'}"  method="post" enctype="multipart/form-data" class="form-horizontal">
       
        <input id="materialTypeName" name="materialTypeName" type="hidden" value="${data.materialTypeName}"/>
        <input id="shopName" name="shopName" type="hidden" value="${data.shopName}"/>
        <input id="content" name="content" type="hidden" value="${data.content}"/>
         <input id="code" name="code" type="hidden" value="${data.code}"/>
          <input id="tempId" name="tempId" type="hidden" value="${temp.id}"/>
        
        <tags:message content="${message}"/>
           
        <div id="base_div" class="tab_div">
        
          <div class="control-group">
                <label class="control-label">素材类型名称:</label>
                <div class="controls">
                     <select name="materialTypeCode" id="materialTypeCode" class="required" <c:if test="${not empty data.code}">disabled="disabled"</c:if>>
                     	<option value="" ></option>
			 			<c:forEach items="${materialType }" var="item">
			 				<option value="${item.code}" <c:if test="${item.code eq data.materialTypeCode}">selected="selected"</c:if> >${item.typeName}</option>
			 			</c:forEach>
					 </select>
					 <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div> 
            
            
              <div class="control-group">
                <label class="control-label">终端类型:</label>
                <div class="controls">
                     <select name="shopType" id="shopType" >
                     	<option value="" >全部</option>
			 			<c:forEach items="${shopTypes}" var="item">
			 				<option value="${item.shopType}" <c:if test="${item.shopType eq data.shopType}">selected="selected"</c:if> >${item.shopType}</option>
			 			</c:forEach>
					 </select>
					 
                </div>
            </div> 
            
            
            <div class="control-group">
                <label class="control-label">选择终端:</label>
                <div class="controls">
                 <div class="form-group form-group-select2">
					<select name="shopNo" id="codeListSec"  style="width:220px">
					<option value="" >全部</option>
			 			<c:forEach items="${shops}" var="item">
			 			<option class="MaterialInfo" value="${item.shopNo}"<c:if test="${item.shopNo eq data.shopNo}">selected="selected"</c:if>>${item.shopName}</option>
			 			</c:forEach>
					</select>
					</div>
                </div>
            </div>
            
            <div class="control-group">
                <label class="control-label">标题:</label>
                <div class="controls">
                    <input type="text" id="title" name="title"  maxlength="127" class="required input-xxlarge" value="${data.title}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
             <div class="control-group">
                <label class="control-label">简介:</label>
                <div class="controls">
                 <textarea class="editor input-xxlarge required" rows="5" name="brief" >${data.brief }</textarea>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            
             <div class="control-group">
                <label class="control-label">模版:</label>
                <div class="controls">
                    <label class="lbl">${temp.label}</label>
                </div>
            </div>
        </div>
        <div class="form-actions">
            <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
        </div>
    </form>
    
    
    <!--素材  -->
    <div class="content">
    
    <div class="setting-container preview-container">
    		<div class="phone-head">
            	<span class="phone-head-icon"></span>
        	</div>
        	<div class="phone-title-seciton">
            	<p class="app-header">${data.title}</p>
        	</div>
        	<%-- <div class="h5_info" id="h5_info">
        		<!-- 公司介绍 -->
	        	<c:if test="${temp.label=='1' }">
	       				<c:choose>  
						   	<c:when test="${empty data.content}">
		        				<%@ include file="/WEB-INF/views/modules/models/companyIntroduce.jsp"%>
						   	</c:when>  
						   	<c:otherwise>
						   		${fns:unescapeHtml(data.content)}
						   	</c:otherwise>  
						</c:choose>  
	        		</c:if>
	        	<!-- 路线指引 -->	
	        	<c:if test="${temp.label=='2' }">
	       				<c:choose>  
						   	<c:when test="${empty data.content}">
		        				<%@ include file="/WEB-INF/views/modules/models/roadMap.jsp"%>
						   	</c:when>  
						   	<c:otherwise>
						   		${fns:unescapeHtml(data.content)}
						   	</c:otherwise>  
						</c:choose>  
	        		</c:if>
        		阿里妈妈公司介绍
        		<c:if test="${temp.label=='3' }">
       				<c:choose>  
					   	<c:when test="${empty data.content}">
	        				<%@ include file="/WEB-INF/views/modules/models/amiCompany.jsp"%>
					   	</c:when>  
					   	<c:otherwise>
					   		${fns:unescapeHtml(data.content)}
					   	</c:otherwise>  
					</c:choose>  
        		</c:if>
        		芝华士产品介绍
        		<c:if test="${temp.label=='4' }">
       				<c:choose>  
					   	<c:when test="${empty data.content}">
	        				<%@ include file="/WEB-INF/views/modules/models/zhsProducts.jsp"%>
					   	</c:when>
					   	<c:otherwise>
					   		${fns:unescapeHtml(data.content)}
					   	</c:otherwise>    
					</c:choose>  
        		</c:if>
        		
        		阿米妈妈菜单
        		<c:if test="${temp.label=='5' }">
       				<c:choose>  
					   	<c:when test="${empty data.content}">
	        				<%@ include file="/WEB-INF/views/modules/models/amiMenu.jsp"%>
					   	</c:when>
					   	<c:otherwise>
					   		${fns:unescapeHtml(data.content)}
					   	</c:otherwise>    
					</c:choose>  
        		</c:if>
        		
        		阿米妈妈单品介绍1
        		<c:if test="${temp.label=='6' }">
       				<c:choose>  
					   	<c:when test="${empty data.content}">
	        				<%@ include file="/WEB-INF/views/modules/models/introduction.jsp"%>
					   	</c:when>
					   	<c:otherwise>
					   		${fns:unescapeHtml(data.content)}
					   	</c:otherwise>    
					</c:choose>  
        		</c:if>
        		
        		宏博培训-产品介绍1
        		<c:if test="${temp.label=='7' }">
       				<c:choose>  
					   	<c:when test="${empty data.content}">
	        				<%@ include file="/WEB-INF/views/modules/models/management.jsp"%>
					   	</c:when>
					   	<c:otherwise>
					   		${fns:unescapeHtml(data.content)}
					   	</c:otherwise>    
					</c:choose>  
        		</c:if>
        		方太产品介绍
        		<c:if test="${temp.label=='8' }">
       				<c:choose>  
					   	<c:when test="${empty data.content}">
	        				<%@ include file="/WEB-INF/views/modules/models/ftGoods.jsp"%>
					   	</c:when>
					   	<c:otherwise>
					   		${fns:unescapeHtml(data.content)}
					   	</c:otherwise>    
					</c:choose>  
        		</c:if>
        		阿米妈妈菜单1
        		<c:if test="${temp.label=='9' }">
       				<c:choose>  
					   	<c:when test="${empty data.content}">
					   	<link href="${ctxStatic}/admin/css/models/ami-pro1view.css" type="text/css" rel="stylesheet" />
	        				<%@ include file="/WEB-INF/views/modules/models/amiPro1.jsp"%>
					   	</c:when>
					   	<c:otherwise>
					   		${fns:unescapeHtml(data.content)}
					   	</c:otherwise>    
					</c:choose>  
        		</c:if>
        		
        		阿米妈妈菜单5
        		<c:if test="${temp.label=='10' }">
       				<c:choose>  
					   	<c:when test="${empty data.content}">
					   	<link href="${ctxStatic}/admin/css/models/ami-pro5view.css" type="text/css" rel="stylesheet" />
	        				<%@ include file="/WEB-INF/views/modules/models/amiPro5.jsp"%>
					   	</c:when>
					   	<c:otherwise>
					   		${fns:unescapeHtml(data.content)}
					   	</c:otherwise>    
					</c:choose>  
        		</c:if>
        		芝华士产品介绍2
        		<c:if test="${temp.label=='11' }">
       				<c:choose>  
					   	<c:when test="${empty data.content}">
					   	<link href="${ctxStatic}/admin/css/models/ami-pro5view.css" type="text/css" rel="stylesheet" />
	        				<%@ include file="/WEB-INF/views/modules/models/zhsProductsTwo.jsp"%>
					   	</c:when>
					   	<c:otherwise>
					   		${fns:unescapeHtml(data.content)}
					   	</c:otherwise>    
					</c:choose>  
        		</c:if>
        		方太产品介绍2
        		<c:if test="${temp.label=='12' }">
       				<c:choose>  
					   	<c:when test="${empty data.content}">
	        				<%@ include file="/WEB-INF/views/modules/models/ftGoods2.jsp"%>
					   	</c:when>
					   	<c:otherwise>
					   		${fns:unescapeHtml(data.content)}
					   	</c:otherwise>    
					</c:choose>  
        		</c:if>
        		阿米妈妈火锅
        		<c:if test="${temp.label=='13' }">
       				<c:choose>  
					   	<c:when test="${empty data.content}">
	        				<%@ include file="/WEB-INF/views/modules/models/amihuoguo.jsp"%>
					   	</c:when>
					   	<c:otherwise>
					   		${fns:unescapeHtml(data.content)}
					   	</c:otherwise>    
					</c:choose>  
        		</c:if>
        		阿米妈妈麻辣烫
        		<c:if test="${temp.label=='14' }">
       				<c:choose>  
					   	<c:when test="${empty data.content}">
	        				<%@ include file="/WEB-INF/views/modules/models/amimalatang.jsp"%>
					   	</c:when>
					   	<c:otherwise>
					   		${fns:unescapeHtml(data.content)}
					   	</c:otherwise>    
					</c:choose>  
        		</c:if>
        		阿米妈妈烧烤
        		<c:if test="${temp.label=='15' }">
       				<c:choose>  
					   	<c:when test="${empty data.content}">
	        				<%@ include file="/WEB-INF/views/modules/models/amishaokao.jsp"%>
					   	</c:when>
					   	<c:otherwise>
					   		${fns:unescapeHtml(data.content)}
					   	</c:otherwise>    
					</c:choose>  
        		</c:if>
        		阿米妈妈炸鸡
        		<c:if test="${temp.label=='16' }">
       				<c:choose>  
					   	<c:when test="${empty data.content}">
	        				<%@ include file="/WEB-INF/views/modules/models/amizhaji.jsp"%>
					   	</c:when>
					   	<c:otherwise>
					   		${fns:unescapeHtml(data.content)}
					   	</c:otherwise>    
					</c:choose>  
        		</c:if>
        		方太品牌使
        		<c:if test="${temp.label=='17' }">
       				<c:choose>  
					   	<c:when test="${empty data.content}">					  
	        				<%@ include file="/WEB-INF/views/modules/models/brandHistory.jsp"%>
					   	</c:when>
					   	<c:otherwise>
					   		${fns:unescapeHtml(data.content)}
					   	</c:otherwise>    
					</c:choose>  
        		</c:if>
        			方太产品介绍2
        		<c:if test="${temp.label=='18' }">
       				<c:choose>  
					   	<c:when test="${empty data.content}">
	        				<%@ include file="/WEB-INF/views/modules/models/ftProducts2.jsp"%>
					   	</c:when>
					   	<c:otherwise>
					   		${fns:unescapeHtml(data.content)}
					   	</c:otherwise>    
					</c:choose>  
        		</c:if>
        			阿米妈妈菜单3
        		<c:if test="${temp.label=='19' }">
       				<c:choose>  
					   	<c:when test="${empty data.content}">
					   		<link href="${ctxStatic}/admin/css/models/ami-pro3view.css" type="text/css" rel="stylesheet" />
	        				<%@ include file="/WEB-INF/views/modules/models/amiPro3.jsp"%>
					   	</c:when>
					   	<c:otherwise>
					   		${fns:unescapeHtml(data.content)}
					   	</c:otherwise>    
					</c:choose>  
        		</c:if>
        			新版方太路线
        		<c:if test="${temp.label=='20' }">
       				<c:choose>  
					   	<c:when test="${empty data.content}">
	        				<%@ include file="/WEB-INF/views/modules/models/ftroad.jsp"%>
					   	</c:when>
					   	<c:otherwise>
					   		${fns:unescapeHtml(data.content)}
					   	</c:otherwise>    
					</c:choose>  
        		</c:if>
        			新版方太蒸微一体机
        		<c:if test="${temp.label=='21' }">
       				<c:choose>  
					   	<c:when test="${empty data.content}">
	        				<%@ include file="/WEB-INF/views/modules/models/ftzwytj.jsp"%>
					   	</c:when>
					   	<c:otherwise>
					   		${fns:unescapeHtml(data.content)}
					   	</c:otherwise>    
					</c:choose>  
        		</c:if>
        	</div> --%>
    	</div>
	</div>
    
   </div> 
<script src="${ctxStatic}/admin/js/models/global.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
<script src="${ctxStatic}/common/plupload.full.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/admin/js/models/twoModel.js" type="text/javascript"></script>
<script src="${ctxStatic}/admin/js/ajaxfileupload.js" type="text/javascript"></script>
<script>
initEditModel(); // 初始化可编辑

$("#materialTypeCode").change(function(){
	$("#materialTypeName").val($(this).find("option:selected").text());
});
$("#materialTypeCode").change();

$("[name='shopNo']").change(function(){
	$("#shopName").val($(this).find("option:selected").text());
});
$("[name='shopNo']").change();

$(".close-Icon").on("click",function(e){
	imgClose(this,e);
	$(".modelOne .modelOne_logo").html("");
});
$("#shopType").change(function(){
	var shopTypes=$("#shopType").val();
		  $.ajax({
              type:"POST",
              url:"${ctx}/business/materialcommen/froms",
              data:{shopTypes:shopTypes},
              success:function(result){
            	 var codeListSec = $("#codeListSec");
            	/*   location.reload(); */
            	codeListSec.empty();
            	codeListSec.append("<option value='' selected='selected'>全部</option>");
            	if(result){
            		for(var i = 0; i < result.length; i++){
                    	codeListSec.append("<option class='" + result[i].shopNo + "' value='" + result[i].shopNo + "' >" + result[i].shopName + "</option>");
            		}
            		$("#s2id_codeListSec .select2-chosen").text("全部")
            		$("#shopName").val("全部");
            	}
              }
          }); 
});
$('#codeListSec').select2(); 
$(".select2-input").on("input propertychange",function(){
   	var title=$(this).val();
   	 if($.trim(title)!=''){
   		$.ajax({
               type:"POST",
               url:"${ctx}/msg/weixin/materialList",
               data:{title:title},
               dataType:'JSON',
               success:function(result){
            	   if(result.length>0){
            		   var html="";
            		   var html3="";
            		   for(var i=0;i<result.length;i++){
            			   html=html+'<option class="MaterialInfo" value="'+result[i].code+'">'+result[i].title+'</option>';
            			     if(i==0){
            				   html3=html3+'<li class="select2-results-dept-0 select2-result select2-result-selectable MaterialInfo select2-highlighted">'+
                    		   '<div class="select2-result-label"><span class="select2-match"></span>'+result[i].title+'</div></li>';
            			   }else{
            				   html3=html3+'<li class="select2-results-dept-0 select2-result select2-result-selectable MaterialInfo">'+
                    		   '<div class="select2-result-label"><span class="select2-match"></span>'+result[i].title+'</div></li>'; 
                  			   }
            		   }
            		   //$("#codeListSec option").remove();
            		   //$(".select2-results").empty();
            		   $("#codeListSec").html(html);
            		   /* $(".select2-results").html(html3); */
           
            	   }
               }
           });
   	} 
   }); 

$("#inputForm").validate({
    submitHandler: function(form){
		/* var imgInfo="";
		var imgJi=$("#image_btn img");
		for(var i=0;i<imgJi.length;i++){
			if(i==imgJi.length-1){
				imgInfo=imgInfo+imgJi[i].src.split(uploadUrl)[1];
			}else{
				imgInfo=imgInfo+imgJi[i].src.split(uploadUrl)[1]+",";
			}
		} */
		
		disabledEditModel(); // 禁止编辑区域
		var contChange = $(".h5_info").html();
		$("#content").val(contChange);
		
// 		$("#input_image").val(imgInfo);
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
</script>
</body>
</html>