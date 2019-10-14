<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title></title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
	<script src="${ctxStatic}/common/plupload.full.min.js" type="text/javascript"></script>
	<link href="${ctxStatic}/common/select2.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript">
    $(document).ready(function() {
    	
    	$("#btnBack").click(function(){
			var parentId = $("#code").val();
			location.href = "${ctx}/cm/materialVideo/list?parentId="+parentId;
		})
    	
    	
        
        //vr素材类型加载
        $(".vrType").each(function(){
        	var code=$(this).prev().val();
        	var self=this;
        	if($(this).children().length<=1){
        		$.ajax({
        	        type:"POST",
        	        url:$('#ctx').val()+"/cm/vrMaterialCommen/findType",
        	        data:{typeCode:code},
        	        dataType:'json',
        	        success:function(result){
        	        	var html='';
        	        	var categoryReturns = $("#categoryReturns").val();
        	        	categoryReturns=categoryReturns.replace("[","");
        	        	categoryReturns=categoryReturns.replace("]","");
        	        	categoryReturns=categoryReturns.split(",");
        	        	if(categoryReturns.length > 0){
        	        		for(var j =0;j< categoryReturns.length;j++){
        	        		    var typeCode=categoryReturns[j];
        	        		    typeCode= $.trim(typeCode);
        	        			for(var i= 0;i<result.length;i++){
        	        				if(typeCode == result[i].code){
        	            	           html +='<option value="'+result[i].code+'" selected="selected">'+result[i].categoryName+'</option>';
        	        				}else{
        	        					 html +='<option value="'+result[i].code+'">'+result[i].categoryName+'</option>';
        	        				}
        	        		     }
        	        			
        	        		}
        	        		 $(self).find("option:last").after(html);
        	        	}else{
        	        		for(var i=0;i<result.length;i++){
            	        		html +='<option value="'+result[i].code+'">'+result[i].categoryName+'</option>';
            	        	}
            	        	$(self).find("option:last").after(html);
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
<div class="container">
	<input type="hidden" value="${ctx }" id="ctx">
    <ul class="nav nav-tabs">
        <li><a href="${ctx}/cm/materialVideo/">返回</a></li>
    </ul>
     <tags:message content="${message}"/>
     <form id="inputForm" action="" method="post" enctype="multipart/form-data" class="form-horizontal">
        <input id="code" name="parentId" type="hidden" value="${parentId}"/>
        
        <div id="base_div" class="tab_div">   
            <div class="control-group">
                <label class="control-label">缩略图:</label>
                <div class="controls">
            		<div id="image_btn" style="border: 1px solid #ddd;width:50%;line-height:100px;text-align:center;overflow: auto;">
                	<c:choose>
                		<c:when test="${data.firstView==null ||  data.firstView==''}">
                			选择视频
                		</c:when>
                		<c:when test="${data.firstView!=null &&  data.firstView!=''}">
                			<c:forEach items="${data.firstView.split(',') }" var="firstView">
	                			<div class="img_info">
	                			<span class="close-Icon"></span>
	                			<img src="${fns:getUploadUrl()}${firstView}"  height="120px" width="120px">
	                			</div>
                			</c:forEach>
                		</c:when>
                	</c:choose>
		    		</div>
                     <input id="input_image" type="hidden" name="firstView" value="${data.firstView}">
                     <span class="help-inline">建议视频小于10MB</span>
                </div>
            </div>
            
        
        </div>
        <div class="form-actions">
            <input id="btnBack" class="btn btn-primary" type="button" value="完成"/>&nbsp;
        </div>
    </form>
    </div>
        <script>
    var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
		browse_button : 'image_btn',
		url : '${ctx}/file/batchUpload?dirName=video',
		file_data_name:'files',
		multi_selection:true,
		auto_start : true,
		flash_swf_url : '${ctxStatic}/common/Moxie.swf',
		silverlight_xap_url : '${ctxStatic}/common/Moxie.xap',
		filters: {
		  mime_types : [ //只允许上传视频文件
		    { title : "视频文件", extensions : "mp4,avi,rm,3gp,flv,mov" }
		  ],
		  max_file_size : '4096kb',
		  prevent_duplicates : true 
		},
		multipart_params: {
			fileType: 'video',
			parentId: '${parentId}',
			
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
			html=html+'<div class="img_info"><span class="close-Icon" ></span><img width="120px" height="120px" src="'+uploadUrl+response.url+'"/></div>';
			$("#image_btn").html(html);
			
			$(".close-Icon").on("click",function(e){
				imgClose(this,e);
	        });
	}); 
	
	 $(document).ready(function() {
	        $("#inputForm").validate({
	            submitHandler: function(form){
	            	//系列必填校验
	            	var selectFlag=true;
	            	if($("input[type=radio][name=showChannel][value='CHANNEL']").prop("checked")){
	            		selectFlag=false;
	            		$.each($("input[type=checkbox][name=shopType]"),function(i,item){  
	            			if($(this).prop("checked")==true){
	            				selectFlag=true;
	            				return false;
	            			}
	            		});
	            		
	            	}
	            	if(!selectFlag){
	            		alertx("请选择系列!");
	            		return false;
	            	}
	            	
	            	//终端必填校验
	            	if($("input[type=radio][name=showChannel][value='PART']").prop("checked") && $("#shopNo").val()==''){
	           			alertx("请选择终端!");
	           			return false;
	            	}
	            	
	            	
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
					
					var vrinfo="";
					$(".vrType").each(function(){
						var e="";
			        	var vv=$(this).find("option:selected");
			        	if($(vv).val()!=""){
			        		var code=$(this).prev().val();
			        		e=code+","+$(vv).val();
			        		vrinfo +=e+";";
			        	}
					});
					$("#vrInfo").val(vrinfo);
						
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
	        
	        $(".close-Icon").on("click",function(e){
	        	imgClose(this,e);
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
	        
	    });
	 
		function imgClose(event,e){
			$(event).parent().remove();
        	var show=$("#image_btn").children();
        	if(show.length==0){
        		$("#image_btn").html("选择视频");
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