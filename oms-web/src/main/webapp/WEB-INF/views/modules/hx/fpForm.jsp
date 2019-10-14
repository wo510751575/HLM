<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${not empty data.code?'修改节日问候':'新增节日问候'}</title>
    <script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
    <script src="${ctxStatic}/common/plupload.full.min.js" type="text/javascript"></script>
	<link href="${ctxStatic}/common/select2.css" type="text/css" rel="stylesheet" />
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
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
	img {
	    border: 0 none;
	    height: 80px;
	    max-width: 100%;
	    vertical-align: middle;
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
        <li ><a href="${ctx}/hx/fp/list">节日问候列表</a></li>
	    <li class="active"><a href="${ctx}/hx/fp/form?code=${data.code}">新增节日问候</a></li> 
    </ul><br/>
    <form id="inputForm" action="${ctx}/hx/fp/${not empty data.code?'edit':'save'}"  method="post" class="form-horizontal">
        
        	<input id="code" name="code" type="hidden" value="${data.code}"/>
            
         	<div class="control-group">
               <label class="control-label"><font color="red">*</font>节日类型:</label>
                <div class="controls">
                	 <input name="typeName" maxlength="20"  class="required input-xxlarge" type="text" style="width: 500px;" value="${data.typeName }" />
                     <span class="help-inline"> <font>限定最多20个字符</font> </span>
                </div>
            </div>
        	
        
          <div class="control-group">
                <label class="control-label"><span class="help-inline"><font color="red">*</font></span>图片素材:</label>
              <div class="controls"   style="display: flex; align-items: center;">
            		<div id="image_btn" style="border: 1px solid #ddd;width:50%;line-height:100px;text-align:center;overflow: auto;">
                	<c:choose>
                		<c:when test="${data.imgs==null ||  data.imgs==''}">
                			选择图片
                		</c:when>
                		<c:when test="${data.imgs!=null &&  data.imgs!=''}">
                			<c:forEach items="${data.imgs.split(',') }" var="imgaddr">
	                			<div class="img_info">
	                			<span class="close-Icon"></span>
	                				<img src="${fns:getUploadUrl()}${imgaddr}"  height="120px" width="120px">
	                			</div>
                			</c:forEach>
                		</c:when>
                	</c:choose>
		    		</div>
                     <input id="input_image" type="hidden" name="imgs" value="${data.imgs}">
                     <span class="help-inline"> <font>最多9张图片</font> </span
                </div>
            </div>
           
        <div class="form-actions">
            <input id="btnSubmit" class="btn" type="submit" value="保存"/>&nbsp;
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
        </div>
    </form>
    </div>
    
<script>
        
    var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
		browse_button : 'image_btn',
		url : '${ctx}/file/upload?dirName=shoporder',
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
			fileType: 'image'/* ,
			width:1080,
			height:1540 */
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
	
	 $(document).ready(function() {
	        $("#inputForm").validate({
	            submitHandler: function(form){
	            	var show=$("#image_btn").children();
	            	if(!show.length){
	            		alertx("请选择图片!");
	            		return false;
	            	}
	            	if(show.length>9){
	            		alertx("最多9张图片!");
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