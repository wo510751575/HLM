 var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
		browse_button : 'image_btn',
		url : '${ctx}/file/upload?dirName=materialcommen',
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
			fileType: 'image'
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
			var h='<img  src="'+uploadUrl+'/oms'+response.url+'"/>'
			$(".modelOne .modelOne_logo").html(h);
			$("#image_btn").html(h);
			
			$(".close-Icon").on("click",function(e){
				imgClose(this,e);
				$(".modelOne .modelOne_logo").html("");
	        });
		
	}); 
	
	
	 var modelTWOimage_btn = new plupload.Uploader({ //实例化一个plupload上传对象
			browse_button : 'modelTWOimage_btn',
			url : '${ctx}/file/upload?dirName=materialcommen',
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
				fileType: 'image'
			}
		});
	 modelTWOimage_btn.init(); //初始化
	 modelTWOimage_btn.bind('FilesAdded',function(uploader,files){
			if(files.length>0){
				uploader.start();
			}
		});
	 modelTWOimage_btn.bind('Error',function(uploader,errObject){
			if(errObject.code!=-602){
				showTip(errObject.message,"info");
			}
		});
	 modelTWOimage_btn.bind('FileUploaded',function(uploader,file,responseObject){
				var response = $.parseJSON(responseObject.response);
				
				$("#modelTWOimage_btn").html('<img width="120px" height="120px" src="'+uploadUrl+'/oms'+response.url+'"/>');
				$(".modelTWO .md-t-topImg").html('<img class="img-url" src="'+uploadUrl+'/oms'+response.url+'"/>');
			
		}); 	
	
	 
	 var image_btn_shop = new plupload.Uploader({ //实例化一个plupload上传对象
			browse_button : 'image_btn_shop',
			url : '${ctx}/file/upload?dirName=materialcommen',
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
				fileType: 'image'
			}
		});
	 image_btn_shop.init(); //初始化
	 image_btn_shop.bind('FilesAdded',function(uploader,files){
			if(files.length>0){
				uploader.start();
			}
		});
	 image_btn_shop.bind('Error',function(uploader,errObject){
			if(errObject.code!=-602){
				showTip(errObject.message,"info");
			}
		});
	 image_btn_shop.bind('FileUploaded',function(uploader,file,responseObject){
		 var response = $.parseJSON(responseObject.response);
			var child=$("#image_btn_shop").children();
			var html="";
			if(child.length>0){
				html=$("#image_btn_shop").html();
			}
			html=html+'<div class="img_info"><span class="close-Icon" ></span><img width="120px" height="120px" src="'+uploadUrl+'/oms'+response.url+'"/></div>';
			$("#image_btn_shop").html(html);
			
			//预览终端图
			var shopPic=$(".modelTWO .md-t-img-list").children();
			var picHtml="";
			if(shopPic>0){
				picHtml=$(".modelTWO .md-t-img-list").html();
			}
			picHtml=picHtml+'<li><img src="'+uploadUrl+'/oms'+response.url+'" class="img-url"/></li>';
			$(".modelTWO .md-t-img-list").html(picHtml);
			
			$(".close-Icon").on("click",function(e){
				imgClose(this,e);
	        });
				
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
						
						var contChange="";
						
						contChange=$(".h5_info").html();
						
						$("#content").val(contChange);
						
						$("#input_image").val(imgInfo);
	                form.submit();
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
	        
	        $("[name='shopNo']").change(function(){
	    		$("#shopName").val($(this).find("option:selected").text());
	        });
	        $("[name='shopNo']").change();
	        
	        $(".close-Icon").on("click",function(e){
	        	imgClose(this,e);
	        	$(".modelOne .modelOne_logo").html("");
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
	        var tempId=$(".lbl").text();
	        
	        var content=$("#content").val();
	        if(tempId==1){
	        	if(content!=""){
	        		$("#modelOneInfo1").val($(".modelOne .head_eg_name").text());
	        		$("#modelOneInfo2").val($(".modelOne .head_ch_name").text());
	        		$("#modelOneInfo3").val($(".modelOne .name1").text());
	        		$("#modelOneInfo4").val($(".modelOne .name2").text());
	        		$("#modelOneInfo5").val($(".modelOne .modelOne_introduce_info").text());
	        		$("#modelOneInfo6").val($(".modelOne .modelOne_foot p").text());
	        	}
	        }else if(tempId==2){
	        	if(content!=""){
	        		$("#modelTwoInfo1").val($($(".modelTWO .md-t-phone .list li")[0]).find(".n").text());
	        		$("#modelTwoInfo2").val($($(".modelTWO .md-t-phone .list li")[0]).find(".d").text());
	        		$("#modelTwoInfo3").val($($(".modelTWO .md-t-phone .list li")[1]).find(".n").text());
	        		$("#modelTwoInfo4").val($($(".modelTWO .md-t-phone .list li")[1]).find(".d").text());
	        		$("#modelTwoInfo5").val($($(".modelTWO .md-t-phone .list li")[2]).find(".n").text());
	        		$("#modelTwoInfo6").val($($(".modelTWO .md-t-phone .list li")[2]).find(".d").text());
	        		$("#modelTWOimage_btn").html('<img width="120px" height="120px" src="'+$(".modelTWO .md-t-topImg .img-url").attr("src")+'"/>');
	        		
	        		var picImg=$(".md-t-img-list").children();
	    			var picHtml="";
	        		for(var m=0;m<picImg.length;m++){
	        			picHtml=picHtml+'<div class="img_info"><span class="close-Icon" ></span><img width="120px" height="120px" src="'+$($(".md-t-img-list li")[m]).find("img").attr("src")+'"/></div>';
	        		}
	        		$("#image_btn_shop").html(picHtml);
	        		
	        		//公交
	        		$("#modelTwoName0").val($($(".bus .list")[0]).find(".h").text());
	        		$("#modelTwoLine0").val($($(".bus .list")[0]).find(".xl").text());
	        		var busNum=$(".bus .list").length
	        		if(busNum>1){
	        			for(var i=1;i<busNum;i++){
	        				var m=$(".gongjiao");
	        				var gongjiao='<div class="model-product-detail-list gongjiao gongjiao'+m.length+'"><div class="control-group"><label class="control-label">公交名字:</label><div class="controls">'+
	        				'<input type="text" id="modelTwoName'+m.length+'" maxlength="127" class="required input-xxlarge  valid" value="">'+
	        				'<span class="help-inline"><font color="red">*</font></span></div><label class="control-label">具体路线:</label><div class="controls">'+
	        				'<input type="text" id="modelTwoLine'+m.length+'" maxlength="127" class="required input-xxlarge  valid" value="">'+
	        				'<span class="help-inline"><font color="red">*</font></span></div></div></div>';
	        				$(".form-actions1").before(gongjiao);
	        				
	        				$("#modelTwoName"+i).val($($(".bus .list")[i]).find(".h").text());
	    	        		$("#modelTwoLine"+i).val($($(".bus .list")[i]).find(".xl").text());
		        		}
	        		}
	        		//地铁.
	        		$("#modelTwoditieName0").val($($(".ditbus .list")[0]).find(".h").text());
	        		$("#modelTwoditieLine0").val($($(".ditbus .list")[0]).find(".xl").text());
	        		var ditbusNum=$(".ditbus .list").length
	        		if(ditbusNum>1){
	        			for(var i=1;i<ditbusNum;i++){
	        				var m=$(".ditie").length;
	        				var ditbus='<div class="model-product-detail-list ditie ditie'+m+'"><div class="control-group"><label class="control-label">地铁名字:</label><div class="controls">'+
	        				'<input type="text" id="modelTwoditieName'+m+'" maxlength="127" class="required input-xxlarge  valid" value="">'+
	        				'<span class="help-inline"><font color="red">*</font></span></div><label class="control-label">具体路线:</label><div class="controls">'+
	        				'<input type="text" id="modelTwoditieLine'+m+'" maxlength="127" class="required input-xxlarge  valid" value="">'+
	        				'<span class="help-inline"><font color="red">*</font></span></div></div></div>';
	        				$(".form-actions2").before(ditbus);
	        				
	        				$("#modelTwoditieName"+i).val($($(".ditbus .list")[i]).find(".h").text());
	    	        		$("#modelTwoditieLine"+i).val($($(".ditbus .list")[i]).find(".xl").text());
		        		}
	        		}
	        		
	        		//自驾.
	        		$("#modelTwozijaName0").val($($(".zijiabus .list")[0]).find(".h").text());
	        		$("#modelTwozijaLine0").val($($(".zijiabus .list")[0]).find(".xl").text());
	        		var zijiabusNum=$(".zijiabus .list").length
	        		if(zijiabusNum>1){
	        			for(var i=1;i<zijiabusNum;i++){
	        				var m=$(".zija").length;
	        				var gongjiao='<div class="model-product-detail-list zija zija'+m+'"><div class="control-group"><label class="control-label">自驾目的地:</label><div class="controls">'+
	        				'<input type="text" id="modelTwozijaName'+m+'" maxlength="127" class="required input-xxlarge  valid" value="">'+
	        				'<span class="help-inline"><font color="red">*</font></span></div><label class="control-label">具体路线:</label><div class="controls">'+
	        				'<input type="text" id="modelTwozijaLine'+m+'" maxlength="127" class="required input-xxlarge  valid" value="">'+
	        				'<span class="help-inline"><font color="red">*</font></span></div></div></div>';
	        				
	        				$(".form-actions3").before(gongjiao);
	        				
	        				$("#modelTwozijaName"+i).val($($(".zijiabus .list")[i]).find(".h").text());
	    	        		$("#modelTwozijaLine"+i).val($($(".zijiabus .list")[i]).find(".xl").text());
		        		}
	        		}
	        		
	        		//停车场.
	        		$("#stopCar0").val($($(".stop .list .h")[0]).text());
	        		var stopNum=$(".stop .list .h").length
	        		if(stopNum>1){
	        			for(var i=1;i<stopNum;i++){
	        				var m=$(".chechang").length;
	        				var gongjiao='<div class="model-product-detail-list chechang chechang'+m+'"><div class="control-group"><label class="control-label">附近停车场:</label>'+
	        				'<div class="controls"><input type="text" id="stopCar'+m+'" maxlength="127"class="required input-xxlarge  valid" value="">'+
	        				'<span class="help-inline"><font color="red">*</font></span></div></div></div>';
	        				
	        				$(".form-actions4").before(gongjiao);
	        				
	        				$("#stopCar"+i).val($($(".stop .list .h")[i]).text());
		        		}
	        		}
	        	}
	        }
	        
	        $("#modelOneInfo1").blur(function(){
	    		var info=$(this).val();
	    		if(info!=""){
	    			$(".modelOne .head_eg_name").text(info);
	    		}
	    	});
	    	$("#modelOneInfo2").blur(function(){
	    		var info=$(this).val();
	    		if(info!=""){
	    			$(".modelOne .head_ch_name").text(info);
	    		}
	    	});
	    	$("#modelOneInfo3").blur(function(){
	    		var info=$(this).val();
	    		if(info!=""){
	    			$(".modelOne .name1").text(info);
	    		}
	    	});
	    	$("#modelOneInfo4").blur(function(){
	    		var info=$(this).val();
	    		if(info!=""){
	    			$(".modelOne .name2").text(info);
	    		}
	    	});
	    	$("#modelOneInfo5").blur(function(){
	    		var info=$(this).val();
	    		if(info!=""){
	    			$(".modelOne .modelOne_introduce_info").text(info);
	    		}
	    	});
	    	$("#modelOneInfo6").blur(function(){
	    		var info=$(this).val();
	    		if(info!=""){
	    			$(".modelOne .modelOne_foot p").text(info);
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