<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${not empty data.code?'VR素材修改':'VR素材添加'}</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
	<script src="${ctxStatic}/common/plupload.full.min.js" type="text/javascript"></script>
	<link href="${ctxStatic}/common/select2.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript">
    $(document).ready(function() {
    	//显示渠道
    	$('input[type=radio][name=showChannel]').change(function(){
    		if($(this).prop("checked")==true&&$(this).val()=='CHANNEL'){
    			$("#changeShopType").show();
    			$(".changeShop").hide();
    		}else if($(this).prop("checked")==true&&$(this).val()=='PART'){
    			$("#changeShopType").hide();
    			$(".changeShop").show();
    		}else if($(this).prop("checked")==true&&$(this).val()=='ALL'){
    			$("#changeShopType").hide();
    			$(".changeShop").hide();
    		}
    	});
    	$('input[type=radio][name=showChannel]').change();
    	
      //绑定使用范围
		$('#btnTerminal').click(function() {
			var shopNos = $("#shopNo").val();
			window.localStorage.setItem("c_shopNo",$("#shopNo").val());
			window.localStorage.setItem("c_shopName",$("#shopName").val());
			
			// 正常打开	
			var url = "iframe:${ctx}/mec/mcAd/mecShopShow";
			top.$.jBox.open(url,"绑定终端", 880, 560,{//宽高
				id:9527,
				draggable: true,
				showClose: true,
				buttons:{"确定":"ok", "关闭":true}, submit:function(v, h, f){
	                /* if (v=="ok"){
	                	var win = h.find("iframe")[0].contentWindow
	                	var form=win.document.getElementById("submitForm");
	                	var shopInfos=win.submitAjax();
	                	$("#shopNo").val(shopInfos.shopNos);
		                $("#shopName").val(shopInfos.shopNames);
 		                $("#remark").val(shopInfos.shopNames);
	                } */
	            }, 
				iframeScrolling: 'no',
				loaded:function(h){
					top.$('.jbox-container .jbox-title-panel').css("height","35px").css('background','#376EA5');
					top.$('.jbox-container .jbox-title').css('line-height','35px').css("color","#ffffff");
				},
				closed: function () { 
					//window.location.reload();
				} // 信息关闭后执行的函数
			});
	    });
		//使用范围 end
        
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
        <li><a href="${ctx}/cm/vrMaterialCommen/">VR素材列表</a></li>
	    <li class="active"><a href="${ctx}/cm/vrMaterialCommen/from?code=${data.code}">${not empty data.code?'VR素材修改':'VR素材添加'}</a></li> 
    </ul>
     <tags:message content="${message}"/>
     <form id="inputForm" action="${ctx}/cm/vrMaterialCommen/${not empty data.code?'edit':'save'}" method="post" enctype="multipart/form-data" class="form-horizontal">
        <input id="code" name="code" type="hidden" value="${data.code}"/>
        <input type="hidden" name="showChannel" value="ALL" />	<!-- 电商模式默认全部 -->
           <input id="categoryReturns" name="categoryReturns" type="hidden" value="${categoryReturns}"/>
        <div id="base_div" class="tab_div">   
		        <div class="control-group">
                <label class="control-label">标题:</label>
                <div class="controls">
                    <input type="text" id="title" name="title"  maxlength="127" class="required input-xxlarge" value="${data.title}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
		 
		     <%-- <div class="control-group">
                <label class="control-label">终端类型:</label>
                <div class="controls">
                 <div class="form-group form-group-select2">
                     <select name="shopType" id="shopType"style="width:220px">
                     	<option value="全部" >全部</option>
			 			<c:forEach items="${shopType}" var="item">
			 				<option value="${item.shopType}" <c:if test="${item.shopType eq data.shopType}">selected="selected"</c:if> >${item.shopType}</option>
			 			</c:forEach>
					 </select>
                </div>
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
            </div> --%>
            
            <div class="control-group">
            <input type="hidden" value="" name="vrInfo" id="vrInfo">
                <label class="control-label">VR素材类型:</label>
                <div class="controls">
<!--                  <select name="vrType" class="vrType" style="width:220px">  -->
<!--  	                     		<option value="" >--请选择--</option>  -->
<!--  						 	</select> -->
		               <c:forEach items="${vrType}" var="item">
		               		<label class="">${item.typeName }:</label>
               		 		<input type="hidden" value="${item.code }">
	                     	<select name="vrType" class="vrType required" style="width:220px"<c:if test="${not empty data.code}">disabled="disabled"</c:if>>
	                     		<option value="" >--请选择--</option>
						 	</select>
					 	</c:forEach>
					 	
<!--                     <span class="help-inline"><font color="red">*</font></span> -->
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
                    <input type="text" id="title" name="linkUrl"  maxlength="500" class=" input-xxlarge" value="${data.linkUrl}"/><br>
                     <span class="help-inline"><font color="#A9A9A9">样板：https://www.baidu.com/</font></span>
                </div>
            </div>
            
            <%-- <div class="control-group" id="adTypeDiv">
	          <div>
	            <label class="control-label">显示渠道:</label>
	             <div class="controls">
					<c:forEach items="${ShowChannel}" var="ad">
						<input type="radio" name="showChannel" value="${ad}" class="" <c:if test="${ad == data.showChannel || empty data.showChannel && ad=='ALL'}">checked</c:if> />${ad.name}
					</c:forEach>
	            </div>
	          </div>
	       </div> --%>
	       
	       <div id="changeShopType" class="control-group hide">
	          <div>
	            <label class="control-label">系列:</label>
	             <div class="controls">
	             	<c:forEach items="${mecShopChannel}" var="item">
						<label class="channelLabel"><input name="shopType" value="${item.value}" class="input-medium" maxlength="100" type="checkbox" <c:if test="${fn:contains(data.shopType,item.value)}">checked="checked"</c:if>/>${item.label}</label>
					</c:forEach>
	            </div>
	          </div>
	       </div>
	       
	   <div class="control-group changeShop hide">
		<label class="control-label">选择终端:</label>
		   <div class="controls">
            <span style="padding-left:5px;"><input id="btnTerminal" class="btn btn-primary" type="button" value="选择终端" /></span>
             </div>
        </div>
        
         <div class="control-group changeShop hide" >
			    <div class="controls">
			     <textarea readonly style="width:500px;height:120px" id="remark" name="remark" >${data.shopName }</textarea>
			    </div>
		 </div>
            
            <div class="control-group">
                <label class="control-label">缩略图:</label>
                <div class="controls">
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
                     <input id="input_image" type="hidden" name="imgAddr" value="${data.imgAddr}">
                     <span class="help-inline">建议图片尺寸1080*1540</span>
                </div>
            </div>
            
        
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
		url : '${ctx}/file/upload?dirName=vrMaterialcommen',
		multi_selection:false,
		auto_start : true,
		flash_swf_url : '${ctxStatic}/common/Moxie.swf',
		silverlight_xap_url : '${ctxStatic}/common/Moxie.xap',
		filters: {
		  mime_types : [ //只允许上传图片文件
		    { title : "图片文件", extensions : "jpg" }
		  ],
		  max_file_size : '4096kb',
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