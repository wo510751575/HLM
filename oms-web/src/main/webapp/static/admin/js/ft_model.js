var FILEUPLOADCOUNT = 2;
// 添加技术参数列表
function addParamsList(elem){
	var tags = '<div class="control-group">'+
					'<label class="control-label">参数名:</label>'+
					'<div class="controls">'+
						'<input type="text" maxlength="127" class="required input-xxlarge modelJsName valid" oninput="editHtmlParams(this)" value="">'+
						'<input type="text" maxlength="127" class="required input-xxlarge modelJsName valid" oninput="editHtmlParams(this)" value="">'+
						'<input id="btnSubmit" class="btn btn-primary" type="" value="删除" onclick="delParamsList(this)" style="width:60px;">'+
					'</div>'
				'</div>';
	$(elem).parent('.form-actions').siblings('.js-list').append(tags);
}

// 初始化技术参数列表
function initParamsList(){
	var list = $('#model-md-jszc-list').find('li');
	var tags = '';
	$.each($(list),function(i){
		tags += '<div class="control-group">'+
					'<label class="control-label">参数名:</label>'+
					'<div class="controls">'+
						'<input type="text" maxlength="127" class="required input-xxlarge modelJsName valid" oninput="editHtmlParams(this)" value="'+ $(this).find('.n').text() +'">'+
						'<input type="text" maxlength="127" class="required input-xxlarge modelJsName valid" oninput="editHtmlParams(this)" value="'+ $(this).find('.d	').text() +'">'+
						'<input id="btnSubmit" class="btn btn-primary" type="" value="删除" onclick="delParamsList(this)" style="width:60px;">'+
					'</div>'+
				'</div>';	
	});
	
	$('#model-product-detail-js-list').find('.js-list').html(tags);
}

// 删除技术参数列表
function delParamsList(elem){
	$(elem).parents('.control-group').remove();
	editHtmlParams(elem);
}

// 更新模板的技术参数列表
function editHtmlParams(elem){
	var tags = '';
	var list = $('#model-product-detail-js-list').find('.js-list .control-group');
	if(list.length > 0){
		tags += '<div class="md-jszc">'+
					'<h3 class="t">技术参数</h3>'+
					'<ul class="l">';	
	}
	$.each($(list),function(i){
		tags += '<li><span class="n">'+ $(this).find('.modelJsName:eq(0)').val() +'</span><span class="d">'+ $(this).find('.modelJsName:eq(1)').val() +'</span></li>'
	});
	if(list.length > 0){
		tags += '</ul></div>';
	}
	$('#model-md-jszc-list').html(tags);
}

// 添加模板图片详情1
function addImageDetailOne(elem){
	var tags = '<div class="control-group">' +
					'<label class="control-label">上传产品图详情模板1:</label>'+
					'<div class="controls" style="position: relative;">'+
						'<input id="modes_image_btn'+ FILEUPLOADCOUNT +'" class="btn btn-primary" type="button" value="选择图片">'+
						'<div class="file-upload-images"></div>'+
					'</div>'+
					'<label class="control-label">标题:</label>'+
					'<div class="controls">'+
						'<input type="text" maxlength="127" class="required input-xxlarge modelJsName valid" oninput="editImageDetailOne(this)" value="">'+
						'<span class="help-inline"><font color="red">*</font></span>'+
					'</div>'+
					'<label class="control-label">摘要:</label>'+
					'<div class="controls">'+
						'<input type="text" maxlength="127" class="required input-xxlarge modelJsName valid" oninput="editImageDetailOne(this)" value="">'+
						'<span class="help-inline"><font color="red">*</font></span>'+
					'</div>'+
					'<label class="control-label">详情:</label>'+
					'<div class="controls">'+
						'<input type="text" maxlength="127" class="required input-xxlarge modelJsName valid" oninput="editImageDetailOne(this)" value="">'+
						'<span class="help-inline"><font color="red">*</font></span>'+
						'<input id="btnSubmit" class="btn btn-primary" type="button" value="删除" onclick="delImageDetailOne(this)">'+
					'</div>'+
				'</div>';
	$(elem).parent('.form-actions').siblings('.product-images-list').append(tags);
	addFileMoreImages();
	FILEUPLOADCOUNT += 1;
}

// 删除模板图片详情1
function delImageDetailOne(elem){
	$(elem).parents('.control-group').remove();
	editImageDetailOne(elem);
}

// 更新模板图片详情1
function editImageDetailOne(elem){
	var tags = '';
	var list = $('#model-product-detail-one').find('.product-images-list .control-group');
	$.each(list,function(i){
		tags += '<div class="md-cxw">' +
					'<div class="l">'+
						'<p class="md-f">'+ $(this).find('.modelJsName:eq(0)').val() +'</p>'+
						'<span class="md-s">'+ $(this).find('.modelJsName:eq(1)').val() +'</span>'+
						'<p class="md-t">'+ $(this).find('.modelJsName:eq(2)').val() +'</p>'+
					'</div>'+
					'<div class="r">'+
						'<img src="'+ ($(this).find('.img_info:eq(0) img').attr('src')) +'" class="img-url"/>'+
					'</div>'+
				'</div>';
	});
	$('#model-main-md-cxw').html(tags);
}

// 初始化模板图片详情1
function initImageDetailOne(){
	var list = $('#model-main-md-cxw').find('.md-cxw');
	var tags = '';
	$.each($(list),function(i){
		tags += '<div class="control-group">' +
					'<label class="control-label">上传产品图详情模板1:</label>'+
					'<div class="controls" style="position: relative;">'+
						'<input id="modes_image_btn'+ FILEUPLOADCOUNT +'" class="btn btn-primary" type="button" value="选择图片">'+
						'<div class="file-upload-images">'+
							'<div class="img_info">'+
		            			'<span class="close-model-Icon"></span>'+
		            			'<img src="'+ $(this).find('.img-url').attr('src') +'"  height="120px" width="120px">'+
	            			'</div>'+
						'</div>'+
					'</div>'+
					'<label class="control-label">标题:</label>'+
					'<div class="controls">'+
						'<input type="text" maxlength="127" class="required input-xxlarge modelJsName valid" oninput="editImageDetailOne(this)" value="'+ $(this).find('.md-f').text() +'">'+
						'<span class="help-inline"><font color="red">*</font></span>'+
					'</div>'+
					'<label class="control-label">摘要:</label>'+
					'<div class="controls">'+
						'<input type="text" maxlength="127" class="required input-xxlarge modelJsName valid" oninput="editImageDetailOne(this)" value="'+ $(this).find('.md-s').text() +'">'+
						'<span class="help-inline"><font color="red">*</font></span>'+
					'</div>'+
					'<label class="control-label">详情:</label>'+
					'<div class="controls">'+
						'<input type="text" maxlength="127" class="required input-xxlarge modelJsName valid" oninput="editImageDetailOne(this)" value="'+ $(this).find('.md-t').text() +'">'+
						'<span class="help-inline"><font color="red">*</font></span>'+
						'<input id="btnSubmit" class="btn btn-primary" type="button" value="删除" onclick="delImageDetailOne(this)">'+
					'</div>'+
				'</div>';
		FILEUPLOADCOUNT++;
	});
	$('#model-product-detail-one').find('.product-images-list').html(tags);
}

//添加图片详情模板2
function addImageDetailTwo(elem){
	var tags = '<div class="control-group">' +
					'<label class="control-label">上传产品图详情模板2:</label>'+
					'<div class="controls" style="position: relative;">'+
						'<input id="modes_image_btn'+ FILEUPLOADCOUNT +'" class="btn btn-primary" type="button" value="选择图片">'+
						'<div class="file-upload-images">选择图片</div>'+
					'</div>'+
					'<label class="control-label">标题:</label>'+
					'<div class="controls">'+
						'<input type="text" maxlength="127" class="required input-xxlarge modelJsName valid" oninput="editImageDetailTwo(this)" value="">'+
						'<span class="help-inline"><font color="red">*</font></span>'+
					'</div>'+
					'<label class="control-label">描述:</label>'+
					'<div class="controls">'+
					'<input type="text" maxlength="127" class="required input-xxlarge modelJsName valid" oninput="editImageDetailTwo(this)" value="">'+
					'<span class="help-inline"><font color="red">*</font></span>'+
					'</div>'+
					'<label class="control-label">摘要:</label>'+
					'<div class="controls">'+
						'<input type="text" maxlength="127" class="required input-xxlarge modelJsName valid" oninput="editImageDetailTwo(this)" value="">'+
						'<span class="help-inline"><font color="red">*</font></span>'+
					'</div>'+
					'<label class="control-label">详情:</label>'+
					'<div class="controls">'+
						'<input type="text" maxlength="127" class="required input-xxlarge modelJsName valid" oninput="editImageDetailTwo(this)" value="">'+
						'<span class="help-inline"><font color="red">*</font></span>'+
						'<input id="btnSubmit" class="btn btn-primary" type="button" value="删除" onclick="delImageDetailTwo(this)">'+
					'</div>'+
				'</div>';
	$(elem).parent('.form-actions').siblings('.product-images-list').append(tags);
	addFileMoreImages();
	FILEUPLOADCOUNT += 1;
}

// 删除图片详情模板2
function delImageDetailTwo(elem){
	$(elem).parents('.control-group').remove();
	editImageDetailTwo(elem);
}

// 更新图片详情模板2
function editImageDetailTwo(elem){
	var tags = '';
	var list = $('#model-product-detail-list').find('.product-images-list .control-group');
	$.each($(list),function(i){
		tags += '<div class="md-detail">'+
					'<img src="'+ ($(this).find('.img_info:eq(0) img').attr('src')) +'" class="img-url"/>'+
					'<div class="cnt">'+
						'<div class="lr">'+
							'<h3 class="t">'+
								'<i>'+ $(this).find('.modelJsName:eq(0)').val() +'</i>'+
								'<span>'+ $(this).find('.modelJsName:eq(1)').val() +'</span>'+
							'</h3>'+
							'<p class="d">'+ $(this).find('.modelJsName:eq(2)').val() +'</p>'+
							'<p class="sm">'+ $(this).find('.modelJsName:eq(3)').val() +'</p>'+
							'<span class="l"></span>'+
							'<span class="r"></span>'+
						'</div>'+
					'</div>'+
				'</div>';
	});
	$('#model-main-md-detail').html(tags);
}

// 初始化图片详情模板2
function initImageDetailTwo(){
	var list = $('#model-main-md-detail').find('.md-detail');
	var tags = '';
	$.each($(list),function(i){
		tags += '<div class="control-group">' +
					'<label class="control-label">上传产品图详情模板2:</label>'+
					'<div class="controls" style="position: relative;">'+
						'<input id="modes_image_btn'+ FILEUPLOADCOUNT +'" class="btn btn-primary" type="button" value="选择图片">'+
						'<div class="file-upload-images">'+
							'<div class="img_info">'+
		            			'<span class="close-model-Icon"></span>'+
		            			'<img src="'+ $(this).find('.img-url').attr('src') +'"  height="120px" width="120px">'+
	            			'</div>'+
						'</div>'+
					'</div>'+
					'<label class="control-label">标题:</label>'+
					'<div class="controls">'+
						'<input type="text" maxlength="127" class="required input-xxlarge modelJsName valid" oninput="editImageDetailTwo(this)" value="'+ $(this).find('.t i').text() +'">'+
						'<span class="help-inline"><font color="red">*</font></span>'+
					'</div>'+
					'<label class="control-label">描述:</label>'+
					'<div class="controls">'+
					'<input type="text" maxlength="127" class="required input-xxlarge modelJsName valid" oninput="editImageDetailTwo(this)" value="'+ $(this).find('.t span').text() +'">'+
					'<span class="help-inline"><font color="red">*</font></span>'+
					'</div>'+
					'<label class="control-label">摘要:</label>'+
					'<div class="controls">'+
						'<input type="text" maxlength="127" class="required input-xxlarge modelJsName valid" oninput="editImageDetailTwo(this)" value="'+ $(this).find('.d').text() +'">'+
						'<span class="help-inline"><font color="red">*</font></span>'+
					'</div>'+
					'<label class="control-label">详情:</label>'+
					'<div class="controls">'+
						'<input type="text" maxlength="127" class="required input-xxlarge modelJsName valid" oninput="editImageDetailTwo(this)" value="'+ $(this).find('.sm').text() +'">'+
						'<span class="help-inline"><font color="red">*</font></span>'+
						'<input id="btnSubmit" class="btn btn-primary" type="button" value="删除" onclick="delImageDetailTwo(this)">'+
					'</div>'+
				'</div>';
		FILEUPLOADCOUNT += 1;
	});
	$('#model-product-detail-list').find('.product-images-list').html(tags);
}



//添加图片详情模板3
function addImageDetailThree(elem){
	var tags = '<div class="control-group">' +
					'<label class="control-label">上传产品图详情模板3:</label>'+
					'<div class="controls" style="position: relative;">'+
						'<input id="modes_image_btn'+ FILEUPLOADCOUNT +'" class="btn btn-primary" type="button" value="选择图片">'+
						'<div class="file-upload-images">选择图片</div>'+
					'</div>'+
					'<label class="control-label">标题:</label>'+
					'<div class="controls">'+
						'<input type="text" maxlength="127" class="required input-xxlarge modelJsName valid" oninput="editImageDetailThree(this)" value="">'+
						'<span class="help-inline"><font color="red">*</font></span>'+
						'<input id="btnSubmit" class="btn btn-primary" type="button" value="删除" onclick="delImageDetailThree(this)">'+
					'</div>'+
				'</div>';
	$(elem).parent('.form-actions').siblings('.product-images-list').append(tags);
	addFileMoreImages();
	FILEUPLOADCOUNT += 1;
}

//删除图片详情模板3
function delImageDetailThree(elem){
	$(elem).parents('.control-group').remove();
	editImageDetailThree(elem);
}

//更新图片详情模板3
function editImageDetailThree(elem){
	var tags = '';
	var list = $('#model-product-detail-only').find('.product-images-list .control-group');
	$.each($(list),function(i){
		tags += '<div class="md-list">'+
					'<div class="md-img">'+
						'<img src="'+ ($(this).find('.img_info:eq(0) img').attr('src')) +'" class="img-url"/>'+
					'</div>'+
					'<div class="md-img-detail">'+
						'<span class="d">'+ $(this).find('.modelJsName:eq(0)').val() +'</span>'+
					'</div>'+
				'</div>';
	});
	$('#model-main-md-img').html(tags);
}

//初始化图片详情模板3
function initImageDetailThree(){
	var list = $('#model-main-md-img').find('.md-list');
	var tags = '';
	$.each($(list),function(i){
		tags += '<div class="control-group">' +
					'<label class="control-label">上传产品图详情模板3:</label>'+
					'<div class="controls" style="position: relative;">'+
						'<input id="modes_image_btn'+ FILEUPLOADCOUNT +'" class="btn btn-primary" type="button" value="选择图片">'+
						'<div class="file-upload-images">'+
							'<div class="img_info">'+
		            			'<span class="close-model-Icon"></span>'+
		            			'<img src="'+ $(this).find('.img-url').attr('src') +'"  height="120px" width="120px">'+
		        			'</div>'+
						'</div>'+
					'</div>'+
					'<label class="control-label">标题:</label>'+
					'<div class="controls">'+
						'<input type="text" maxlength="127" class="required input-xxlarge modelJsName valid" oninput="editImageDetailThree(this)" value="'+ $(this).find('.d').text() +'">'+
						'<span class="help-inline"><font color="red">*</font></span>'+
						'<input id="btnSubmit" class="btn btn-primary" type="button" value="删除" onclick="delImageDetailThree(this)">'+
					'</div>'+
				'</div>';
		FILEUPLOADCOUNT += 1;
	});
	$('#model-product-detail-only').find('.product-images-list').html(tags);
}

// 头部两张图片 添加和修改
function addTwoImgTop(){
	var list = $('#modes_image_btn0').siblings('.product-images-list').find('.img_info');
	var tags = '';
	$.each($(list),function(){
		tags += '<img src="'+ $(this).find('img').attr('src') +'"/>';
	});
	$('#model-main-md-topImg').html(tags);
}

// 初始化头部两张图片
function initTwoImgTop(){
	var list = $('#model-main-md-topImg').find('img');
	var tags = '';
	$.each($(list),function(){
		tags += '<div class="img_info">'+
					'<span class="close-model-Icon"></span>'+
					'<img src="'+ $(this).attr('src') +'"  height="120px" width="120px">'+
				'</div>';
	});
	$('#modes_image_btn0').siblings('.product-images-list').html(tags);
}

//尾部多张图片 添加和修改
function addMoreImgBottom(){
	var list = $('#modes_image_btn1').find('.img_info');
	var tags = '';
	$.each($(list),function(){
		tags += '<div class="md-img">'+
					'<img src="'+ $(this).attr('src') +'" class="img-url"/>'+
				'</div>';
	});
	$('#model-main-onlyimg-list').siblings('.product-images-list').html(tags);
}

// 初始化尾部多张图片 
function initMoreImgBottom(){
	var list = $('#model-main-onlyimg-list').siblings('.product-images-list').find('md-img');
	var tags = '';
	$.each($(list),function(){
		tags += '<div class="img_info">'+
					'<span class="close-model-Icon"></span>'+
					'<img src="'+ $(this).find('img').attr('src') +'"  height="120px" width="120px">'+
				'</div>';
	});
	$('#modes_image_btn1').html(tags);
}

function uuidFileName() {
    var s = [];
    var hexDigits = "0123456789abcdef";
    for (var i = 0; i < 36; i++) {
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
    }
    s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
    s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
    s[8] = s[13] = s[18] = s[23] = "-";
 
    var uuid = s.join("");
    return uuid;
}

// 初始化上传控件
function fileMoreImages(){
	var listArr = []
	for(var i = 0; i < FILEUPLOADCOUNT; i++){
		listArr.push('modes_image_btn'+i);
	}
	
	$.each(listArr,function(i){
		var FileLoadObj = uuidFileName();
		FileLoadObj = new plupload.Uploader({ //实例化一个plupload上传对象
			browse_button : listArr[i],
			url : $('#materialcommenId').val(),
			multi_selection:false,
			auto_start : true,
			flash_swf_url : $('#flash_swf_url_id').val(),
			silverlight_xap_url : $('#silverlight_xap_url_id').val(),
			filters: {
				mime_types : [{ title : "图片文件", extensions : "jpg,gif,png" }],//只允许上传图片文件
				max_file_size : '10240kb',
				prevent_duplicates : true 
			},
			multipart_params: {
				fileType: 'image'
			}
		});
		
		FileLoadObj.init(); //初始化
		
		FileLoadObj.bind('FilesAdded',function(uploader,files){
			if(files.length>0){
				uploader.start();
			}
		});
		
		FileLoadObj.bind('Error',function(uploader,errObject){
			if(errObject.code!=-602){
				showTip(errObject.message,"info");
			}
		});
		
		FileLoadObj.bind('FileUploaded',function(uploader,file,responseObject){
			var response = $.parseJSON(responseObject.response);
			var html = '<div class="img_info">'+
							'<span class="close-model-Icon" ></span>'+
							'<img width="120px" height="120px" src="'+uploadUrl+'/oms'+response.url+'"/>'+
						'</div>';
			var id = this.settings.browse_button[0].id
			$('#'+id).siblings('.file-upload-images').append(html);
			
			var callback = $('#'+id).siblings('.product-images-list').attr('callback')+'()';
			eval(callback);  // 更新编辑
		});
	});
}

// 新增上传控件
function addFileMoreImages(){
	var fileId = 'modes_image_btn' + FILEUPLOADCOUNT;
	var FileLoadObj = uuidFileName();
	FileLoadObj = new plupload.Uploader({ //实例化一个plupload上传对象
		browse_button : fileId,
		url : $('#materialcommenId').val(),
		multi_selection:false,
		auto_start : true,
		flash_swf_url : $('#flash_swf_url_id').val(),
		silverlight_xap_url : $('#silverlight_xap_url_id').val(),
		filters: {
			mime_types : [{ title : "图片文件", extensions : "jpg,gif,png" }],//只允许上传图片文件
			max_file_size : '10240kb',
			prevent_duplicates : true 
		},
		multipart_params: {
			fileType: 'image'
		}
	});
	
	FileLoadObj.init(); //初始化
	
	FileLoadObj.bind('FilesAdded',function(uploader,files){
		if(files.length>0){
			uploader.start();
		}
	});
	
	FileLoadObj.bind('Error',function(uploader,errObject){
		if(errObject.code!=-602){
			showTip(errObject.message,"info");
		}
	});
	
	FileLoadObj.bind('FileUploaded',function(uploader,file,responseObject){
		var response = $.parseJSON(responseObject.response);
		var html = '<div class="img_info">'+
						'<span class="close-model-Icon" ></span>'+
						'<img width="120px" height="120px" src="'+uploadUrl+'/oms'+response.url+'"/>'+
					'</div>';
		var id = this.settings.browse_button[0].id
		$('#'+id).siblings('.file-upload-images').append(html);
		
		var callback = $('#'+id).siblings('.product-images-list').attr('callback')+'()';
		eval(callback);  // 更新编辑
	});
}

$('#base_div').on('click','.close-model-Icon',function(e){
	e.stopPropagation();
	var callback = $(this).parents('.product-images-list').attr('callback')+'()';
	$(this).parent('.img_info').remove();
	eval(callback); // 更新编辑
});

initParamsList();
initImageDetailOne();
initImageDetailTwo();
initImageDetailThree();
initTwoImgTop();
initMoreImgBottom();
fileMoreImages();















