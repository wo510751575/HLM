<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${not empty data.code?'敏感词修改':'敏感词添加'}</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
	<script src="${ctxStatic}/common/plupload.full.min.js" type="text/javascript"></script>
    <script type="text/javascript">
   
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
<div class="container">
    <ul class="nav nav-tabs">
        <li><a href="${ctx}/comment/sensitiveWord">敏感词列表</a></li>
	    <li class="active"><a href="${ctx}/comment/sensitiveWord/form?code=${data.code}&pageNo=${pageNo}&pageSize=${pageSize}">${not empty data.code?'敏感词修改':'敏感词添加'}</a></li>
    </ul><br/>

    <form id="inputForm" action="${ctx}/comment/sensitiveWord/${not empty data.code?'edit':'save'}"  method="post" enctype="multipart/form-data" class="form-horizontal">
        <input id="code" name="code" type="hidden" value="${data.code}"/>
        <input id="pageNo" name="pageNo" type="hidden" value="${pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${pageSize}"/>
		
        <tags:message content="${message}"/>
        <div id="base_div" class="tab_div">
        	<div class="control-group">
                <label class="control-label">敏感词:</label>
                <div class="controls">
                    <input type="text" id="name" name="name"  maxlength="127" class="required input-xxlarge" value="${data.name}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            
            <c:if test="${!empty data.code}">
	            <div class="control-group">
	                <label class="control-label">创建时间:</label>
	                <div class="controls">
	               		 <fmt:formatDate value="${data.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
	                </div>
	            </div>
	            
	            <div class="control-group">
	                <label class="control-label">创建人:</label>
	                <div class="controls">
	               		 ${data.createId}
	                </div>
	            </div>
            </c:if>
            
        </div>
        <div class="form-actions">
            <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
        </div>
    </form>
   </div> 
    
    <script>
    var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
		browse_button : 'image_btn',
		url : '${ctx}/file/upload?dirName=activity',
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
			height:1540
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
// 			$("#image_btn").html('<img width="120px" height="120px" src="'+uploadUrl+'/oms'+response.url+'"/>');
// 			$("#input_image").val("/oms" + response.url);
	});
	
	
	var uploader_show = new plupload.Uploader({ //实例化一个plupload上传对象
		browse_button : 'showImgAddr_btn',
		url : '${ctx}/file/upload?dirName=activity',
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
			height:500
		}
	});
	uploader_show.init(); //初始化
	uploader_show.bind('FilesAdded',function(uploader_show,files){
		if(files.length>0){
			uploader_show.start();	
		}
	});
	uploader_show.bind('Error',function(uploader_show,errObject){
		if(errObject.code!=-602){
			showTip(errObject.message,"info");
		}
	});
	uploader_show.bind('FileUploaded',function(uploader_show,file,responseObject){
			var response = $.parseJSON(responseObject.response);
			$("#showImgAddr_btn").html('<img width="120px" height="120px" src="'+uploadUrl+'/oms'+response.url+'"/>');
			$("#input_showImgAddr").val("/oms" + response.url);
	});
	
	 $(document).ready(function() {
	    	
	        $("#inputForm").validate({
	            submitHandler: function(form){
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
	    function tabChange(id,ths){
	        $(".tab_div").hide();
	        $(".nav-child li").removeClass("active");
	        $(id).show();
	        $(ths).addClass("active");
	    }
    </script>
</body>
</html>