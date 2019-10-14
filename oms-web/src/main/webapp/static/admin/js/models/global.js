// 图片上传方法
function ajaxFileUpload(fileId,bgImg) {
    $.ajaxFileUpload({
        url: $('#ctx').val() + '/file/upload?dirName=materialcommen', //用于文件上传的服务器端请求地址
        secureuri: false, //是否需要安全协议，一般设置为false
        fileElementId: fileId, //文件上传域的ID
        dataType: 'json', //返回值类型 一般设置为json
        type:'post',
        data:{
			fileType: 'image'
		},
        success: function (data, status) {//服务器成功响应处理函数
        	var src = $('#uploadUrlId').val()+"/oms" + data.url;
        	if(bgImg == 'bgImg'){ // 设置为背景
        		$('#'+fileId).val('').parent('.file-img').css({'background':'url('+ src +') no-repeat','background-size':'cover'});
        	}else{
        		$('#'+fileId).val('').siblings('.fImg').attr('src',src);
        	}
        }
    })
}

// 模板可编辑初始化
function initEditModel(){
	$('.contenteditable').attr('contenteditable','true');
	$('.file-upload-btn').show();
}

// 模板可编辑初始化
function disabledEditModel(){
	$('.contenteditable').attr('contenteditable','false');
	$('.file-upload-btn').hide();
}