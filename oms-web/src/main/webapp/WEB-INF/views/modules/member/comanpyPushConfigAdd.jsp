<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>经销商推送配置</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/common/plupload.full.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#label").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					$("#btnSubmit").attr("disabled","disabled");form.submit()
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
			
			$('input[name="content"]').on('input propertychange', function() {
				preV()
			})

		});
		function preV() {
			var shopUrl = 'http://www.xxx.com/index';
			var shopName = '芝华仕商城';
			var mgrName = '张店长';
			var emoji = '${emojis}'.split(',');
			
			var content = $('#content').val();
			content = content.replace(/{shopUrl}/g, shopUrl);
			content = content.replace(/{shopName}/g, shopName);
			content = content.replace(/{mgrName}/g, mgrName);
			
			if (content.indexOf("{emoji}") >= 0) {
				var count = patch('{emoji}',content);
				for (var i = 0; i < count; i++) {
					randomIndex = parseInt(Math.random() * (emoji.length - 1));
					content = content.replace(/{emoji}/, '<img src="${fns:getUploadUrl()}'+emoji[randomIndex]+'" style="width:30px" />');
				}
			}
			
			$('.textContent').html(content);
		}
		function patch(re,s){
			re=eval("/"+re+"/ig")
			return s.match(re).length;
		}
		/*
		function insertImg(str,id){
			try{
				var selection= window.getSelection ? window.getSelection() : document.selection;
				var range= selection.createRange ? selection.createRange() : selection.getRangeAt(0);
				
				if (!window.getSelection){
					var selection= window.getSelection ? window.getSelection() : document.selection;
					var range= selection.createRange ? selection.createRange() : selection.getRangeAt(0);
					range.pasteHTML(str);
					range.collapse(false);
					range.select();
				}else{
					range.collapse(false);
					var hasR = range.createContextualFragment(str);
					var hasR_lastChild = hasR.lastChild;
					while (hasR_lastChild && hasR_lastChild.nodeName.toLowerCase() == "br" && hasR_lastChild.previousSibling && hasR_lastChild.previousSibling.nodeName.toLowerCase() == "br") {
						var e = hasR_lastChild;
						hasR_lastChild = hasR_lastChild.previousSibling;
						hasR.removeChild(e)
					}
					if(range.commonAncestorContainer.parentNode.id == id){
						range.insertNode(hasR);
						if (hasR_lastChild) {
							range.setEndAfter(hasR_lastChild);
							range.setStartAfter(hasR_lastChild)
						}
						selection.removeAllRanges();
						selection.addRange(range)
					}else{
						var tt=$("#"+id).html();
						$("#"+id).html(tt+str);
					}
				}
			}catch(e){}
			
		}*/

	</script>
	<style type="text/css">
		#preView .controls{
			width: 460px;
			height: 100px;
			border: 1px solid #ccc;
			padding: 10px;
			word-break: break-all;
		}
		#preView .controls .headImg{
			width:50px;
			float: left;
		}
		#preView .controls  .headImg img {
			width: 50px;
			height: 50px;
		}
		
		#preView .controls .textContent{
			margin-left: 10px;
			margin-top: 10px;
			float: left;
			width: 400px;
		}
		
	</style>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/member/comanpyPushConfig/list">经销商推送配置列表</a></li>
		<li class="active"><a href="${ctx}/member/comanpyPushConfig/addView">经销商推送配置添加</a></li>
	</ul><br/>
	<form id="inputForm" action="${ctx}/member/comanpyPushConfig/add" method="post" class="form-horizontal">
		<%-- <input type="hidden" name="id" value="${data.id}"/> --%>
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">类型:</label>
			<div class="controls">
				<select style="width: 150px;" name="type" onchange="show(this)">
	             <c:forEach items="${companyPushConfigTypes}" var="item">
					<option value="${item}">${item.name}</option>
				</c:forEach>
             </select>
			</div>
		</div>
		<div class="control-group" id="txt_content">
			<label class="control-label">内容:</label>
			<div class="controls">
				<!-- <div class="sendInfo" id="txtContent"  contentEditable="true" ></div> -->
				<textarea rows="5" name="content" id="content"  maxlength="500" class="required  input-xxlarge"></textarea><br/><br/>
				请选择标签变量：<input id="shopUrl" class="btn" type="button" value="商城地址" onclick="addContent('shopUrl')"/>&nbsp;
				<input id="shopName" class="btn" type="button" value="终端名称" onclick="addContent('shopName')"/>&nbsp;
				<input id="mgrName" class="btn" type="button" value="店长姓名" onclick="addContent('mgrName')"/>&nbsp;
				<input id="emoji" class="btn" type="button" value="微信表情" onclick="addContent('emoji')"/>
			</div>
		</div>
		<div class="control-group" id="preView">
			<label class="control-label">预览:</label>
			<div class="controls">
				<div class="headImg">
					<img alt="" src="${ctxStatic}/admin/images/introduce/file.png">
				</div>
				<div class="textContent"></div>
			</div>
		</div>
		<div class="control-group" id="img_div" style="display: none">
                <label class="control-label">图片:</label>
                <div class="controls">
					<div id="showImgAddr_btn" style="border: 1px solid #e0e6eb; width: 120px; height: 120px; line-height: 100px; text-align: center">
						<c:if test="${!empty data.value}">
							<img width="120px" height="120px" src="${fns:getUploadUrl()}${data.value}" />
						</c:if>
						<c:if test="${empty data.value}">选择图片</c:if>
					</div>
					<input id="img" type="hidden" name="img" value="${data.value}">
				</div>
            </div>
		<div class="control-group">
			<label class="control-label">排序:</label>
			<div class="controls">
				<input name="sort" value="" maxlength="11" class="required digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态:</label>
			<div class="controls">
				<input id="checkbox" name="status" type="radio"  value="USE" checked="checked" class="required"/>有效
				<input id="checkbox" name="status" type="radio" value="STOP"  class="required"/>无效
			</div>
		</div>
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form>
	</div>
	<script type="text/javascript">
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
				fileType: 'image'
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
				$("#img").val("/oms" + response.url);
		});
		function addContent(id) {
			var oldVal = $('input[name=content]').val();
			if (oldVal == undefined) {
				oldVal = '';
			}
			var tag = '{' + id + '}';
			$('input[name=content]').val(oldVal + tag);
			/* insertImg('{' + id + '}', "txtContent"); */
			preV();
		}
		function show(obj) {
			if (obj.value == 'GREET') {
				$('#txt_content').css('display', 'block')
				$('#img_div').css('display', 'none')
				$('#preView').css('display', 'block')
			} else if (obj.value == 'IMAGE') {
				$('#txt_content').css('display', 'none')
				$('#img_div').css('display', 'block')
				$('#preView').css('display', 'none')
			} else {
				$('#txt_content').css('display', 'none')
				$('#img_div').css('display', 'none')
				$('#preView').css('display', 'none')
			}
		}
		
	</script>
</body>
</html>