<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>发朋友圈</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script src="${ctxStatic}/common/plupload.full.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			
			$("#merchantName").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					$("#btnSubmit").attr("disabled","disabled");
					form.submit();
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
			
			//商户选择
			$("#btnMerchant").click(function() {
				$.jBox("id:contentTable", { 
					title: "&nbsp;选择商户",
					buttons: {
				        "确定": "1",
				        "取消": "0"
				    },
				    submit: function(v, o, f) {
						//1确定
						if(v == 1){
							//如果没选
							if(!f.code){
								alertx("请选择商户！");
								return false;	
							}
							//赋值
							var checked= $(o).find("input[name='code']:checked");
							$("input[name='merchantNo']").val(checked.data("code"));
							$("input[name='merchantName']").val(checked.data("name"));
						}
				        return true
				    },
				    loaded: function(a) {
				    	$('.jbox-container .jbox-content').css("height","350px").css('overflow','auto');
				    }
				});
		    });
			
			//微信选择
			$("#btnNoWx").click(function() {
				//获取勾选的值
				var merchantNo =$("input[name='merchantNo']").val();
				if(!merchantNo){
					alertx("请先选择商户!");
					return false;
				}

				$.jBox("get:${ctx}/member/shopTerminal/findShopTermianlToFlow?merchantNo="+merchantNo, { 
					title: "&nbsp;选择微信",
					buttons: {
				        "确定": "1",
				        "取消": "0"
				    },
				    submit: function(v, o, f) {
						//1确定
						if(v == 1){
							//如果没选
							if(!f.noWx){
								alertx("请选择微信！");
								return false;	
							}
							//赋值
							var valArr = new Array;
							$.each($(o).find("input[name='noWx']:checked"), function(i) {
								valArr[i] =$(this).data("nowx");
							});
							var noWxs = valArr.join(',');//转换为逗号隔开的字符串
							$("input[name='noWxs']").val(noWxs);
						}
				        return true
				    },
				    loaded: function(a) {
				    	var json =$(".jbox-container #jbox-content").html();
				    	var html = '<table class="table table-striped table-bordered table-condensed"><thead><tr><th>操作</th><th>微信号</th><th>微信昵称</th></tr></thead><tbody id="infolist">';
				    	$.each($.parseJSON(json), function(idx, obj) {
				    		html+='<tr>';
				    		html+='<td><input type="checkbox" name="noWx" data-nowx="'+obj.noWx+'" ></td>';
				    		html+='<td>'+obj.noWx+'</td>';
				    		html+='<td>'+obj.nickname+'</td>';
			    			html+='</tr>';
						});
				    	html+='</tbody></table>';
				    	$(".jbox-container #jbox-content").html(html);
				    	$(".jbox-container .jbox-content").css("height","350px").css('overflow','auto');
				    }
				});
		    });
			
			//类型选择
			$("#typeSec").change(function() {
				var val =$(this).val();
				//IMAGE("1", "图文"), VIDEO("2", "视频"),LINK("3","链接"),TEXT("4","文字");
				$(".type").hide();
				if(val != "4"){
					$("#TEXT").show();
				}
				if(val == "1"){
					$("#IMAGE").show();
				}
				if(val == "2"){
					$("#VIDEO").show();
				}
				if(val == "3"){
					$("#LINK").show();
				}
				$(':input','.type')  
				 .not(':button, :submit, :reset')  
				 .val('')
			});
			
			//文本素材
			$("#btnText").click(function() {
				text();
		    });
			
			//素材库选择
			$("#btnMaterial").click(function() {
				var val =$("#typeSec").val();
				//IMAGE("1", "图文"), VIDEO("2", "视频"),LINK("3","链接"),TEXT("4","文字");
				if(val == "1"){
					image();
				}
				if(val == "2"){
					video();
				}
				if(val == "3"){
					link();
				}
		    });
			
			
			function text(){
				//获取勾选的值
				var merchantNo =$("input[name='merchantNo']").val();
				if(!merchantNo){
					alertx("请先选择商户!");
					return false;
				}
				$.jBox("get:${ctx}/cm/materialText/listJson?merchantNo="+merchantNo, { 
					title: "&nbsp;选择文本",
					buttons: {
				        "确定": "1",
				        "取消": "0"
				    },
				    submit: function(v, o, f) {
						//1确定
						if(v == 1){
							//赋值
							var checked= $(o).find("input[name='content']:checked");
							$("#content").val(checked.data("content"));
						}
				        return true
				    },
				    loaded: function(a) {
				    	var json =$(".jbox-container #jbox-content").html();
				    	var html = '<table class="table table-striped table-bordered table-condensed"><thead><tr><th>操作</th><th>内容</th></tr></thead><tbody id="infolist">';
				    	$.each($.parseJSON(json), function(idx, obj) {
				    		html+='<tr>';
				    		html+='<td><input type="checkbox" name="content" data-content="'+obj.content+'" ></td>';
				    		html+='<td>'+obj.content+'</td>';
			    			html+='</tr>';
						});
				    	html+='</tbody></table>';
				    	$(".jbox-container #jbox-content").html(html);
				    	$(".jbox-container .jbox-content").css("height","350px").css('overflow','auto');
				    }
				});
			}
			
			function video(){
				//获取勾选的值
				var merchantNo =$("input[name='merchantNo']").val();
				if(!merchantNo){
					alertx("请先选择商户!");
					return false;
				}
				$.jBox("get:${ctx}/cm/materialVideo/treeData?merchantNo="+merchantNo, { 
					title: "&nbsp;选择视频",
					buttons: {
				        "确定": "1",
				        "取消": "0"
				    },
				    submit: function(v, o, f) {
						//1确定
						if(v == 1){
							//赋值
							var checked= $(o).find("input[name='url']:checked");
							$("#video_btn").html('<video id="video" src="'+uploadUrl+checked.data("url")+'" width="100%" height="100%" controls="controls" preload="auto" poster="'+uploadUrl+checked.data("view")+'">');
							$("#input_video").val(checked.data("url"));
							$("#input_image").val(checked.data("view"));
						}
				        return true
				    },
				    loaded: function(a) {
				    	var json =$(".jbox-container #jbox-content").html();
// 				    	var html = '<table class="table table-striped table-bordered table-condensed"><thead><tr><th>操作</th><th>第一帧</th></tr></thead><tbody id="infolist">';
				    	
				    	var html = '<table id="treeTable" class="table table-striped table-bordered table-condensed ">';
				    	html+='<thead><tr><th style="width: 100px;">操作</th><th>第一帧</th></tr></thead><tbody>';
				    	$.each($.parseJSON(json), function(idx, obj) {
				    		html+='<tr id="'+obj.id+'" pId="'+obj.pId+'">';
				    		if(obj.name){
				    			html+='<td>'+obj.name+'</td>';
				    		}else{
				    			html+='<td><input type="radio" name="url" data-url="'+obj.url+'" data-view="'+obj.view+'"></td>';
				    		}
				    		var url = "${ctxStatic}/admin/images/folder.png"; 
				    		if(obj.view){
				    			url =uploadUrl+obj.view;
				    		}
				    		html+='<td><img src="'+url+'" style="max-width: 100%;max-height: 100%;"></td>';
			    			html+='</tr>';
						});
				    	html+='</tbody></table>';
				    	$(".jbox-container #jbox-content").html(html);
				    	$(".jbox-container .jbox-content").css("height","350px").css('overflow','auto');
				    	$("#treeTable").treeTable({});
				    }
				});
			}
			
			function image(){
				//获取勾选的值
				var merchantNo =$("input[name='merchantNo']").val();
				if(!merchantNo){
					alertx("请先选择商户!");
					return false;
				}
				$.jBox("get:${ctx}/cm/materialPhoto/treeData?merchantNo="+merchantNo, { 
					title: "&nbsp;选择图片",
					buttons: {
				        "确定": "1",
				        "取消": "0"
				    },
				    submit: function(v, o, f) {
						//1确定
						if(v == 1){
							//赋值
							var checked= $(o).find("input[name='url']:checked");
							
							$.each(checked, function(idx, obj) {
								var url = $(obj).data("url");
								
								var child=$("#image_btn").children();
								var html="";
								if(child.length>0){
									html=$("#image_btn").html();
								}
								html=html+'<div class="img_info"><span class="close-Icon" ></span><img width="120px" height="120px" src="'+uploadUrl+url+'"/></div>';
								$("#image_btn").html(html);
								$(".close-Icon").on("click",function(e){
									imgClose(this,e);
						        });
								var val =$("#input_image").val();
								if(val){
									$("#input_image").val(val+","+url);
								}else{
									$("#input_image").val(url);
								}
							});
						}
				        return true
				    },
				    loaded: function(a) {
				    	var json =$(".jbox-container #jbox-content").html();

				    	var html = '<table id="treeTable" class="table table-striped table-bordered table-condensed ">';
				    	html+='<thead><tr><th style="width: 100px;">操作</th><th>图片</th></tr></thead><tbody>';
				    	$.each($.parseJSON(json), function(idx, obj) {
				    		html+='<tr id="'+obj.id+'" pId="'+obj.pId+'">';
				    		if(obj.name){
				    			html+='<td>'+obj.name+'</td>';
				    		}else{
				    			html+='<td><input type="checkbox" name="url" data-url="'+obj.url+'" ></td>';
				    		}
				    		var url = "${ctxStatic}/admin/images/folder.png"; 
				    		if(obj.url){
				    			url =uploadUrl+obj.url;
				    		}
				    		html+='<td><img src="'+url+'"  style="max-width: 100%;max-height: 100%;"></td>';
			    			html+='</tr>';
						});
				    	html+='</tbody></table>';
				    	$(".jbox-container #jbox-content").html(html);
				    	$(".jbox-container .jbox-content").css("height","350px").css('overflow','auto');
				    	
				    	$("#treeTable").treeTable({});
				    }
				});
			}
			
			function link(){
				//获取勾选的值
				var merchantNo =$("input[name='merchantNo']").val();
				if(!merchantNo){
					alertx("请先选择商户!");
					return false;
				}
				$.jBox("get:${ctx}/cm/materialLink/listJson?merchantNo="+merchantNo, { 
					title: "&nbsp;选择链接",
					buttons: {
				        "确定": "1",
				        "取消": "0"
				    },
				    submit: function(v, o, f) {
						//1确定
						if(v == 1){
							//赋值
							var checked= $(o).find("input[name='url']:checked");
							$("input[name='linkUrl']").val(checked.data("url"));
						}
				        return true
				    },
				    loaded: function(a) {
				    	var json =$(".jbox-container #jbox-content").html();
				    	var html = '<table class="table table-striped table-bordered table-condensed"><thead><tr><th style="width: 100px;">操作</th><th>标题</th><th>链接</th></tr></thead><tbody id="infolist">';
				    	$.each($.parseJSON(json), function(idx, obj) {
				    		html+='<tr>';
				    		html+='<td><input type="radio" name="url" data-url="'+obj.url+'" ></td>';
				    		html+='<td>'+obj.title+'</td>';
				    		html+='<td>'+obj.url+'</td>';
			    			html+='</tr>';
						});
				    	html+='</tbody></table>';
				    	$(".jbox-container #jbox-content").html(html);
				    	$(".jbox-container .jbox-content").css("height","350px").css('overflow','auto');
				    }
				});
			}
			
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
		<li><a href="${ctx}/weixin/merchantSendFriendsJob/list">朋友圈列表</a></li>
		<li class="active"><a href="${ctx}/weixin/merchantSendFriendsJob/form">发朋友圈</a></li>
	</ul><br/>
	<form id="inputForm" action="${ctx}/weixin/merchantSendFriendsJob/save" method="post" class="form-horizontal">
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label"><font color="red">*</font>商户:</label>
			<div class="controls">
				<input type="hidden" name="merchantNo"  class="input-xxlarge" maxlength="50">
				<input type="text" name="merchantName"  class="input-xxlarge required" maxlength="50" readonly="readonly">
				<input id="btnMerchant" class="btn btn-primary" type="button" value="选择"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*</font>微信号:</label>
			<div class="controls">
				<input type="text" name="noWxs"  class="input-xxlarge required" maxlength="500" readonly="readonly">
				<input id="btnNoWx" class="btn btn-primary" type="button" value="选择"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*</font>执行时间:</label>
			<div class="controls">
				<input id="beginDate" name="executeTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required" 
				value="<fmt:formatDate value="${findPage.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd H:m:s',isShowClear:false,minDate: '%y-%M-%d %H:%m:%s'});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*</font>类型:</label>
			<div class="controls">
				<select id="typeSec" style="width: 150px;" name="type" >
		             <c:forEach items="${materialType}" var="item">
	             		<option value="${item.type}" >${item.name}</option>
					</c:forEach>
	             </select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分享内容:</label>
			<div class="controls">
				<textarea id="content" class="editor input-xxlarge required" rows="5" maxlength="2000" name="content" >${data.content }</textarea>
				<input id="btnText" class="btn btn-primary" type="button" value="文本素材"/>
			</div>
		</div>
		
		<div id="TEXT" class="control-group type">
			<label class="control-label">素材库:</label>
			<div class="controls">
				<input id="btnMaterial" class="btn btn-primary" type="button" value="选择"/>
			</div>
		</div>
		<!-- 分享图片 -->
		<div id="IMAGE" class="control-group type" >
			<label class="control-label">分享图片</label>
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
		       	<input id="input_image" type="hidden" name="imgAddr"  class="input-xxlarge" maxlength="50">
			</div>
		</div>
		<!-- 分享视频 -->
		<div id="VIDEO" class="control-group type" style="display: none;">
			<label class="control-label">分享视频</label>
			<div class="controls">
				<div id="video_btn" style="border: 1px solid #e0e6eb;width:200px;height:200px;line-height:200px;text-align:center">
					<c:if test="${!empty data.linkUrl}">
				       <video id="myvideo" src="" width="100%" height="100%" controls="controls" preload="auto" poster="">
				       		您的浏览器不支持 video 标签。
				       </video>
				     </c:if>
				      <c:if test="${empty data.linkUrl}">
				            选择视频
			       	</c:if>
		       	</div>
		       	<font color="red">视频时间最长10秒</font>
		       	<input id="input_video" type="hidden" name="linkUrl"  class="input-xxlarge" maxlength="50">
			</div>
		</div>
		<!-- 链接地址 -->
		<div id="LINK" class="control-group auto type" style="display: none;">
			<label class="control-label"><font color="red">*</font>链接地址:</label>
			<div class="controls">
				<input type="text" name="linkUrl"  class="input-xxlarge" maxlength="150">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">自动评论:</label>
			<div class="controls">
				<textarea class="editor input-xxlarge" rows="5" maxlength="100" name="autoContent" >${data.autoContent}</textarea>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form>
	
	
</div>
<div id="contentTable"  class="container hide">
<table class="table table-striped table-bordered table-condensed">
	<thead>
		<tr>
			<th>操作</th>
			<th>机构编码</th>
			<th>机构名称</th>
		</tr>
	</thead>
	<tbody id="infolist">
	<c:forEach items="${merchants}" var="data" varStatus="number"> 
		<tr>
			<td><input type="radio" name="code" data-code="${data.id}" data-name="${data.name}"></td>
		    <td>
				${data.code}
			</td>
			<td>
				${data.name}
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</div>

<script>
var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
	browse_button : 'image_btn',
	url : '${ctx}/file/upload?dirName=merchantSendFriendsJob',
	multi_selection : true,
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
		var child=$("#image_btn").children();
		var html="";
		if(child.length>0){
			html=$("#image_btn").html();
		}
		var responseUrl = '/oms'+response.url;
		html=html+'<div class="img_info"><span class="close-Icon" ></span><img width="120px" height="120px" src="'+uploadUrl+responseUrl+'"/></div>';
		$("#image_btn").html(html);
		$(".close-Icon").on("click",function(e){
			imgClose(this,e);
        });
		var val =$("#input_image").val();
		if(val){
			$("#input_image").val(val+","+responseUrl);
		}else{
			$("#input_image").val(responseUrl);
		}
}); 

</script>
<script>
var uploaderVideo = new plupload.Uploader({ //实例化一个plupload上传对象
	browse_button : 'video_btn',
	url : '${ctx}/file/upload?dirName=merchantSendFriendsJob',
	multi_selection:false,
	auto_start : true,
	flash_swf_url : '${ctxStatic}/common/Moxie.swf',
	silverlight_xap_url : '${ctxStatic}/common/Moxie.xap',
	filters: {
	  mime_types : [ //只允许上传图片文件
	    { title : "视频文件", extensions : "mp4,avi,Ogg,ogg" }
	  ],
	  max_file_size : '10240kb',
	  prevent_duplicates : true 
	},
	multipart_params: {
		fileType: 'video'
	}
});
uploaderVideo.init(); //初始化
uploaderVideo.bind('FilesAdded',function(uploaderVideo,files){
	if(files.length>0){
		uploaderVideo.start();
	}
});
uploaderVideo.bind('Error',function(uploaderVideo,errObject){
	if(errObject.code!=-602){
		showTip(errObject.message,"info");
	}
});
uploaderVideo.bind('FileUploaded',function(uploaderVideo,file,responseObject){
	var response = $.parseJSON(responseObject.response);
	var responseUrl = '/oms'+response.url;
	$("#video_btn").html('<video id="video" src="'+uploadUrl+responseUrl+'" width="100%" height="100%" controls="controls" preload="auto" poster="'+response.firstView+'">');
	$("#input_video").val(responseUrl);
	$("#input_image").val(response.firstView);
}); 

</script>
</body>
</html>