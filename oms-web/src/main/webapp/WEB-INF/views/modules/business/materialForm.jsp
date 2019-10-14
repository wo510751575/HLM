<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${not empty data.code?'导购素材修改':'导购素材添加'}</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
	<script src="${ctxStatic}/common/plupload.full.min.js" type="text/javascript"></script>
	<link href="${ctxStatic}/common/select2.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript">
    $(document).ready(function() {
    	
        $("#inputForm").validate({
            submitHandler: function(form){
            	var show=$("#image_btn").children();
            	if(!show.length){
            		alertx("请选择缩略图!");
            		return false;
            	}
            	var imgInfo="";
				var imgJi=$("#image_btn img");
				for(var i=0;i<imgJi.length;i++){
					if(i==imgJi.length-1){
						imgInfo=imgInfo+imgJi[i].src.split(uploadUrl)[1];
					}else{
						imgInfo=imgInfo+imgJi[i].src.split(uploadUrl)[1]+",";
					}
				}
				$("#input_image").val(imgInfo);
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
        
        $("#materialTypeCode").change(function(){
    		$("#materialTypeName").val($(this).find("option:selected").text());
        });
        $("#materialTypeCode").change();
        
        $("[name='memberNoGm']").change(function(){
    		$("#memberNameGm").val($(this).find("option:selected").text());
        });
        $("#materialTypeCode").change();
        $("[name='memberNoGm']").change();
        
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
	.pre-container {
    float: right;
    width: 354px;
    height: 630px;
    background: url(${ctxStatic}/images/iphone.png) no-repeat;
    background-size: 100% auto;
	}
	
	.container{
	padding: 10px 30px;
    width: 100%;
    min-height: 800px;
    background: #fff;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
	}
	#image_btn img{
	float: left;
	}
	.close-Icon{
	position: absolute;
    background: url(/oms-web/static/images/closeImg.png) no-repeat;
    z-index: 100;
    width: 20px;
    height: 20px;
    background-size: 100%;
    right: -7px;
    top: -7px;
       cursor: pointer;
	}
	.img_info{
	position:relative;
	width: 120px;
	height: 120px;
	float: left;
	margin: 10px;
	
	}
    </style>
</head>
<body>
    <ul class="nav nav-tabs">
        <li><a href="${ctx}/business/material">导购素材列表</a></li>
	    <li class="active"><a href="${ctx}/business/material/form?id=${query.id}">${not empty query.id?'导购素材修改':'导购素材添加'}</a></li>
    </ul><br/>

    <form id="inputForm" action="${ctx}/business/material/${not empty data.code?'edit':'save'}" onsubmit="return false" method="post" enctype="multipart/form-data" class="form-horizontal">
        <input id="couponId" name="code" type="hidden" value="${data.code}"/>
        <input id="materialTypeName" name="materialTypeName" type="hidden" value="${data.materialTypeName}"/>
         <input id="memberNameGm" name="memberNameGm" type="hidden" value="${data.memberNameGm}"/>
        <tags:message content="${message}"/>
        <div id="base_div" class="tab_div">
              <div class="control-group">
                <label class="control-label">素材类型名称:</label>
                <div class="controls">
                     <select name="materialTypeCode" id="materialTypeCode" class="required" <c:if test="${not empty data.code}">disabled="disabled"</c:if>>
			 			<c:forEach items="${materialType }" var="item">
			 				<option value="${item.code}" <c:if test="${item.code eq data.materialTypeCode}">selected="selected"</c:if> >${item.typeName}</option>
			 			</c:forEach>
					 </select>
					 <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div> 

             <%-- <div class="control-group">
                <label class="control-label">选择导购:</label>
                <div class="controls">
                 <div class="form-group form-group-select2">
					<select name="memberNoGm" id="codeListSec"  style="width:220px" class="required" >
					<!-- <option value="" >全部</option> -->
			 			<c:forEach items="${guids}" var="item">
			 			<option class="MaterialInfo" value="${item.memberNo}"<c:if test="${item.memberNo eq data.memberNoGm}">selected="selected"</c:if>>${item.memberName}</option>
			 			</c:forEach>
					</select>
					 <span class="help-inline"><font color="red">*</font></span>
					</div>
                </div>
            </div> --%>
            
            
            
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
                <label class="control-label">网址链接:</label>
                <div class="controls">
                    <input type="text" id="linkUrl" name="linkUrl"  maxlength="500" class=" input-xxlarge" value="${data.linkUrl}"/>
                </div>
            </div>
            <div class="control-group">
            	<label class="control-label">开始时间:</label>
                <div class="controls">
                    <input id="startTime" name="startTime" type="text" required="required" readonly="readonly" maxlength="20" class="input-mini Wdate"
					value="<fmt:formatDate value="${data.startTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
					<span class="help-inline"><font color="red">*</font></span>
                </div>
                <label class="control-label">结束时间:</label>
                <div class="controls">
                    <input id="endTime" name="endTime" type="text" required="required" readonly="readonly" maxlength="20" class="input-mini Wdate"
					value="<fmt:formatDate value="${data.endTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
					<span class="help-inline"><font color="red">*结束时间必须晚于开始时间</font></span>
                </div>
            </div>
             <div class="control-group">
                <label class="control-label">详情图:</label>
                <div class="controls"   style="display: flex; align-items: center;">
            		<div id="image_btn" style="border: 1px solid #ddd;width:50%;line-height:100px;text-align:center;overflow: auto;">
                	<c:choose>
                		<c:when test="${data.imgAddr==null ||  data.imgAddr==''}">
                			选择图片
                		</c:when>
                		<c:when test="${data.imgAddr!=null &&  data.imgAddr!=''}">
                			<c:forEach items="${data.imgAddr.split(',') }" var="imgaddr">
	                			<div class="img_info">
	                			<span class="close-Icon"></span>
	                				<img src="${fns:getUploadUrl()}${imgaddr}"  height="120px" width="120px">
	                			</div>
                			</c:forEach>
                		</c:when>
                	</c:choose>
		    		</div>
		    		<span class="help-inline"><font color="red">*</font></span>
                     <input id="input_image" type="hidden" name="imgAddr" value="${data.imgAddr}">
                     <span class="help-inline">建议图片尺寸1080*1540</span>
                     	
                </div>
            </div>
<!--             <div class="control-group"> -->
<!--                 <label class="control-label">内容:</label> -->
<!--                 <div class="controls"> -->
<%--                      <textarea class="editor" name="description" id="editor">${data.description }</textarea> --%>
<!--                 </div> -->
<!--             </div> -->
        </div>
        <div class="form-actions">
            <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
        </div>
    </form>
    <script>
  		var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
		browse_button : 'image_btn',
		url : '${ctx}/file/upload?dirName=material',
		multi_selection:false,
		auto_start : true,
		flash_swf_url : '${ctxStatic}/common/Moxie.swf',
		silverlight_xap_url : '${ctxStatic}/common/Moxie.xap',
		filters: {
		  mime_types : [ //只允许上传图片文件
		    { title : "图片文件", extensions : "jpg,gif,png" }
		  ],
		  max_file_size : '10240kb',
		  prevent_duplicates : true 
		},
		multipart_params: {
			fileType: 'image',
			width:1080,
			height:1080
		}
	});
	uploader.init(); //初始化
	uploader.bind('FilesAdded',function(uploader,files){
		if(files.length>0){
			uploader.start();
		}
	});
	uploader.bind('Error',function(uploader,errObject){
		if(errObject.code!=-602){
			showTip(errObject.message,"info");
		}
	});
	
	uploader.bind('FileUploaded',function(uploader,file,responseObject){
		var response = $.parseJSON(responseObject.response);
		var child=$("#image_btn").children();
		var html="";
		if(child.length>0){
			html=$("#image_btn").html();
		}
		html=html+'<div class="img_info"><span class="close-Icon" ></span><img width="120px" height="120px" src="'+uploadUrl+'/oms'+response.url+'"/></div>';
		$("#image_btn").html(html);
		
		$(".close-Icon").on("click",function(e){
			imgClose(this,e);
        });
	}); 
	
	function imgClose(event,e){
		$(event).parent().remove();
    	var show=$("#image_btn").children();
    	if(show.length==0){
    		$("#image_btn").html("选择图片");
    	}
    	e.stopPropagation(); 
    	return false;
	}	
    </script>
</body>
</html>