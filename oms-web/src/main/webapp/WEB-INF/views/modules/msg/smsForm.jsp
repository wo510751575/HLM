<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${not empty data.code?'短信群发列表':'短信群发添加'}</title>
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
        
        $('#codeListSec').select2();
        
        $(".select2-input").on("input propertychange",function(){
        	var title=$(this).val();
        	 if($.trim(title)!=''){
        		$.ajax({
                    type:"POST",
                    url:"${ctx}/msg/sms/materialList",
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
                 		   $("#codeListSec option").remove();
                 		   $("#codeListSec").append(html);
                 	   }
                    }
                });
        	} 
        });
    	
        
        $("#codeListSec").on("change",function(e){
     	   var txt=$(this).find('option:selected').val();
     	   $.ajax({
                type:"POST",
                url:"${ctx}/msg/sms/materialShow",
                data:{code:txt},
                dataType:'JSON',
                success:function(result){
             	   if(!isEmptyObject(result)){
             		   $(".text_content").empty();
             		   var html2="";
             		    var img=result.MaterialCommen.imgAddr.split(","); 
             		    for(var n=0;n<img.length;n++){
             		    	html2=html2+'<img alt="" src="'+img[n]+'">'
             		    }
             		    html2=html2+'<div class="info_title"><div class="left logo"><img alt="" src="'+result.company.logo+
             		    '"></div><div class="left name">'+result.company.name+'</div></div>'+
             		    '<div class="introduce_cont">'+result.company.remarks+'</div>'
             		    $(".text_content").append(html2);
             	   }
             	   
                }
            });
     	});
        
        
    });
    function isEmptyObject(e) {  
        var t;  
        for (t in e)  
            return !1;  
        return !0  
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
	.container{
	padding: 10px 30px;
    width: 100%;
    min-height: 800px;
    background: #fff;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
	}
	.top_stores .search-group .wd110 {
	width: 110px;
	}
	.active_config {
    display: inline-block;
    width: 580px;
    float: left;
	}
	.active_view {
    float: left;
    margin-left: 10%;
	}
	.clearfix {
    zoom: 1;
	}
	.clearfix:after {
    content: "";
    display: block;
    clear: both;
	}
	
	.pre-container {
    float: right;
    width: 354px;
    height: 630px;
    background: url(${ctxStatic}/images/iphone.png) no-repeat;
    background-size: 100% auto;
	}
	.text_content{
	width:240px;
	height:390px;
	margin:auto;
	overflow:auto;
	overflow-x:hidden;
	margin-top: 117px;
	}
	::-webkit-scrollbar {
    width: 4px;
    height: 4px;
	}
	.info_title {
    width: 100%;
    height: 100px;
    border-top: 1px solid #ddd;
    margin-top: 10px;
	}
	.info_title:before, .info_title:after {
	    content: " ";
	    display: table;
	}
	.info_title:after {
	    clear: both;
	}
	.logo {
	    height: 70px;
	    width: 70px;
	    margin-top: 15px;
	    margin-right: 1%;
	}
	.left {
	    float: left;
	}
	.logo img {
	    width: 70px;
	    height: 70px;
	}
	.name {
	    line-height: 100px;
	    color: black;
	}
	.introduce_cont {
	    width: 100%;
	    text-indent: 2em;
	    padding: 0px;
	    margin: 0px;
	}
   </style>
</head>
<body>
<div class="container">
    <ul class="nav nav-tabs">
        <li><a href="${ctx}/msg/sms">短信群发群发列表</a></li>
	    <li class="active"><a href="${ctx}/msg/sms/form?code=${data.code}">${not empty query.id?'短信群发修改':'新建短信群发'}</a></li> 
    </ul><br>
     <tags:message content="${message}"/>
      <div class="active_config">
     <form id="inputForm" action="${ctx}/msg/sms/${not empty data.code?'edit':'save'}" onsubmit="return false;" method="post" enctype="multipart/form-data" class="form-horizontal">    
                  
              <div class="control-group">           
				<label class="control-label">选择时间:</label>
				<div class="controls">
				<input id="beginDate" name="startTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="${param.startTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>	
				--
				<input id="endDate" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="${param.endTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>&nbsp;&nbsp;
				</div>
			  </div>
            
             <div class="control-group">
                <label class="control-label">选择素材:</label>
                <div class="controls">
                   <select name="codeList" id="codeListSec"  style="width:220px">
					<option value="" >全部</option>
			 			<c:forEach items="${commenList}" var="item">
			 			<option class="MaterialInfo" value="${item.code}">${item.title}</option>
			 			</c:forEach>
					</select>
                </div>
            </div>
                         
            <div class="control-group">
                <label class="control-label">选择客户分组:</label>
                <div class="controls">
                    <select name="nxteCodeList" id="nxteCodeListSec">
			 			<option value="">全部</option>
			 			<c:forEach items="${pmType}" var="item">
			 			<option >${item.typeName}</option>
			 			</c:forEach>
					 </select>
                </div>
            </div>
       		
			 
              <div class="control-group">
               <label class="control-label">所在地区:</label>
                <div class="controls">
                    <input type="text" id="area" name="area"  maxlength="150" class="input-medium" value="${paramMember.area}" placeholder="省/市"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
              </div>
              
              
             <div class="form-actions">
           <!--  <input id="btnSubmit" class="btn btn-primary" type="submit" value="发送" />&nbsp; -->
            <input id="btnCancel" class="btn btn-primary" type="button" value="发送" onclick="history.go(-1)">
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
        </div>
        </form>
		</div>
            <div class="active_view">
    	<div class="pre-container">
    		<div class="text_content">
    		
    			<div>
    			</div>
    		</div>
    	</div>
    </div>
    </div>
    
    
</body>
</html>