

function formValid(form){ // 发送素材验证
	$(form).validate({
		rules:{
			noWxs:{
				required:true
			},
			delayTimes:{
				required:true,
				digits:true
			},
			materialName:{
				required:true,
			},
		},
		messages:{
			noWxs:{
				required:'网点微信号不能为空'
			},
			delayTimes:{
				required:'延迟时间不能为空',
			},
			materialName:{
				required:'素材不能为空',
			},
		},
		errorPlacement: function(error, element) {  
		    error.appendTo(element.parents('.tag'));  
		}
	});
}
 
function sendFriends(elem,form,executeType){ // 发送朋友圈    executeType:执行类型(1：保存任务，2：立即执行)
	if(executeType=="1"){
		$(".button-list .save").css("background","#6cc4ac");
		$(".button-list .save").css("color","#fff");
		$(".button-list .doNow").css("background","#ececec");
		$(".button-list .doNow").css("color","#2a2a2a");
	}else if(executeType=="2"){
		$(".button-list .doNow ").css("background","#6cc4ac");
		$(".button-list .doNow ").css("color","#fff");
		$(".button-list .save").css("background","#ececec");
		$(".button-list .save").css("color","#2a2a2a");
	}
	formValid(form);
	if(!$(form).valid()) return;
	
	var delayTimes = $('#delayTimes').val();
	var minDelayTimes=$("#minDelayTimes").val();
	if(delayTimes < minDelayTimes){
		$('#delayTimes').val(minDelayTimes);
	}else if(delayTimes > 43200){
		$('#delayTimes').val('43200');
	}
	//演示
	/*if(delayTimes < 1){
		$('#delayTimes').val('1');
	}else if(delayTimes > 2147483647){
		$('#delayTimes').val('2147483647');
	}
	*/
	
	$(elem).attr('disabled','disabled');
	var url = $('#ctx').val() + '/friendsjob/addFriendsJob.do';
	
	var params = {
		executeType:executeType,
		merchantNo:$('#merchantNo').val(),
		merchantName:$('#merchantName').val(),
		assistantNo:$('#assistantNo').val(),
		noWxs:$('#noWxs').val(),
		delayTimes:$('#delayTimes').val(),
		materialCategory:$('#materialCategory').val(),
		materialCode:$('#materialCode').val(),
		executeTime:$('#executeTime').val()
	};
	
	$.post(url,params,function(data){
		if(data.result == 0){ // 失败
			$.ConfirmBox({
				msg:data.msg,
				isCancel:false
			});
		}else{ // 成功
			$.ConfirmBox({
				msg:data.msg,
				isCancel:false
			});
			$('.edit-material').find('input[type=text]').val('');
			cancelItems();
			$("#data-table-list tr").removeClass("current");
		}
		$(elem).removeAttr('disabled');
	},'json');
	
}
/*
 * 素材列表
 */
function friendMateriaList(){
	var dataType = $('#friendMateriaButtonList .current').attr('data-type');
	var thead = [];
	var twidth = [];
	if(dataType == 'image'){ // 图片类型
		thead = ['素材','主题','素材类型','内容','图片','时间'];
		twidth = ['','','','35%','210',''];
	}else if(dataType == 'link'){ // 链接类型
		thead = ['主题','标题','素材类型','缩略图','内容','链接','时间'];
		twidth = ['','','','','35%','210',''];
	}else if(dataType == 'video'){ // 链接类型
		thead = ['主题','标题','素材类型','内容','视频','时间'];
		twidth = ['','','','35%','210',''];
	}
	$('#FriendMateriaListTable').ImPaging({
		thead:thead,
		twidth:twidth,
		success:function(elem,ui,pageData){ // elem-paging,ui-ImPage
			var pageData = pageData || {};
			var url = $('#friendMateriaButtonList .current').attr('data-url');
			var params = {
				merchantNo:$('#merchantNo').val(),
				pageNo:pageData.pageNo || 1,
				pageSize:pageData.pageSize || 10,
				conditionStr:$('#conditionStr').val(),
				deleteFlag:0, // 删除标识：0未删除、1已删除
			};
			
			$.post(url,params,function(data){
				ui.createTable(elem,function(){ // 创建表格
					var rows = data.rows;
					if(rows.length == 0) return;
					var tags = '';
					var dataCode = $('#friendMateriaList').attr('data-code');
					$.each(rows,function(i){
						tags += '<tr data-code="'+ rows[i].code +'" data-title="'+ rows[i].title +'" class="'+ (dataCode == rows[i].code ? 'current' : '') +'">';
						
						var imgAddr = rows[i].imgAddr ? rows[i].imgAddr.split(','):[];
						if(dataType == 'image'){ // 图文素材
							if(imgAddr.length == 0){
								tags += '<td>纯文本素材</td>';
							}else{
								tags += '<td>图文素材</td>';
							}
						}
						tags += '<td>'+ rows[i].title +'</td>';
						
						if(dataType == 'link'){ // 链接素材
							tags += '<td>'+ rows[i].shareTitle +'</td>';
						}
						if(dataType == 'video'){ // 视频素材
							tags += '<td>'+ rows[i].shareTitle +'</td>';
						}
						
						var materialType = rows[i].materialType; // 1-朋友圈广告素材 , 2-朋友圈维护素材
						if(materialType == 1){
							tags += '<td>朋友圈广告素材</td>';
						}else if(materialType == 2){
							tags += '<td>朋友圈维护素材</td>';
						}else{
							tags += '<td>&nbsp;</td>';
						}
						if(dataType == 'link') {
							if(rows[i].imageUrl) {
								tags += '<td>';
								var imgAddr = rows[i].imageUrl.split(',');
								$.each(imgAddr,function(m){
									tags += '<img src="'+ ($('#UploadUrl').val() + 'im/' + imgAddr[m]) +'" class="i" />';
								});
								tags += '</td>';
							} else {
								tags += '<td>&nbsp;</td>';
							}
						}
						
						tags += '<td>'+ rows[i].content +'</td>';
						
						if(dataType == 'image'){
							tags += '<td>';
								$.each(imgAddr,function(m){
									tags += '<img src="'+ ($('#UploadUrl').val() + 'im/' + imgAddr[m]) +'" class="i" />';
								});
							tags += '</td>';
						}else if(dataType == 'link'){
							tags += '<td>'+ rows[i].linkUrl +'</td>';
						}else if(dataType == 'video'){ // 链接素材
							
							tags += '<td><video  controls="controls" style="width:200px;margin-left:10px;height:100px"  src="'+  rows[i].linkUrl +'" class="i" /></td>';
						}
						
						tags += '<td>'+ (new Date(rows[i].createDate).format('yyyy-MM-dd hh:mm')) +'</td>';
						tags += '</tr>';
					});
					return tags;
				});
				data.pageNo = pageData.pageNo || 1;
				ui.createPages(elem,data); // 创建页码
			},'json');
		}
	});
}

$('#FriendMateriaListTable').on('click','tbody tr',function(){ // 改变选择存值 
	$('#FriendMateriaListTable tbody tr').removeClass('current');
	$(this).addClass('current');
	$('#friendMateriaList').attr({'data-title':$(this).attr('data-title'),'data-code':$(this).attr('data-code')});
});

$('#friendMateriaButtonList').on('click','li',function(){
	$(this).addClass('current').siblings().removeClass('current');
	var type = $(this).attr('data-type');
	friendMateriaList();
});

/*
 * 选中的素材
 */
function saveFriendMateria(id){
	var dataCode = $(id).attr('data-code');
	if(!dataCode){
		$.ConfirmBox({
			msg:'请选择素材',
			isCancel:false
		});
	}else{
		$('#materialName').val($(id).attr('data-title'));
		$('#materialCode').val($(id).attr('data-code'));
		$('#materialCategory').val($('#friendMateriaButtonList .current').attr('data-value'));
		$(id).hide();
	}
}


friendMateriaList();