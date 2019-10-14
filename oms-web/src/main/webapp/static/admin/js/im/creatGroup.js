
var PUSHITEMS = []; // 推送的所有数据

function formValid(form){ // 发送素材验证
	$(form).validate({
		rules:{
			noWxs:{
				required:true
			},
			memberNos:{
				required:true
			},
			roomNickName:{
				required:true
			}
		},
		messages:{
			noWxs:{
				required:'网点微信号不能为空'
			},
			memberNos:{
				required:'推送对象不能为空'
			},
			roomNickName:{
				required:'微信群昵称不能为空'
			}
		},
		errorPlacement: function(error, element) {
		    error.appendTo(element.parents('.tag'));
		}
	});
}

function sendFriends(elem,form,executeType){ 
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

	$(elem).attr('disabled','disabled');

	var url = $('#ctx').val() + '/im/chatroom/createCharRoom'; //创建群

	var params = {
		noWxZk:$('#noWxs').val(),
		roomNickName: $('#roomNickName').val(),
		userNames: $('#noWxMapping').val(),

	};
	//console.log(params)
	$.post(url,params,function(data){
		if(data.result === true){ // 成功
			$.ConfirmBox({
				msg:"微信群创建成功",
				isCancel:false
			});
			// 清空数据
			$('.edit-material').find('input[type=text],textarea').val('');
			$('#sltNum').text(0);
			cancelItems();
			$("#data-table-list tr").removeClass("current");

		}else{ 
			$.ConfirmBox({
				msg:data.msg,
				isCancel:false
			});
		}
		$(elem).removeAttr('disabled');
	},'json');

}

$('#FriendMateriaListTable').on('click','tbody tr',function(){ // 改变选择存值
	$('#FriendMateriaListTable tbody tr').removeClass('current');
	$(this).addClass('current');
	$('#friendMateriaList').attr({'data-title':$(this).attr('data-title'),'data-code':$(this).attr('data-code')});
});

$('#friendMateriaButtonList').on('click','li',function(){
	$(this).addClass('current').siblings().removeClass('current');
	var type = $(this).attr('data-type');
	memberNosList();
});

function noWxMapData(wxs,list){ // 拼数据, wx#num#code,code#noWx,noWx;
	var wxs = wxs; // 微信号数组
	var num = []; // 计数
	var codes = [];
	var noWx = []; // 微信号  noWxAlias || noWx
	for(var i = 0, len = wxs.length; i < len; i++){
		num[i] = 0;
		codes[i] = [];
		noWx[i] = [];
	}
	$.each(list,function(m){
		var index = wxs.indexOf(list[m].noWxGm);
		if(index != -1){
			num[index] = num[index] + 1;
			codes[index].push(list[m].code);
			noWx[index].push(list[m].noWx);
		}
	});

	var noWxData = [];
	for(var n = 0, len = wxs.length; n < len; n++){
		noWxData.push(wxs[n] + '#' + num[n] + '#' + codes[n].join(',') + '#' + noWx[n].join(','));
	}
	return noWxData.join(';');
}

function uniq(array){//去重
    var temp = []; //一个新的临时数组
    for(var i = 0; i < array.length; i++){
        if(temp.indexOf(array[i]) == -1){
            temp.push(array[i]);
        }
    }
    return temp;
}

/*
 * 勾选的客户
 */
function saveMemberNos(id){
	var checkedList = $('.memberNosBox:checked');
	var noWxMappingData = $('#noWxMapping').val();
	var memberNosData = $('#memberNos').val();
	var isAppendStatus = $('#isAppendStatus').val(); // 是否追加

	if(checkedList.length == 0){
		$.ConfirmBox({
			msg:'请选择推送对象',
			isCancel:false
		});
		return;
	}
	if(checkedList.length < 2){
		$.ConfirmBox({
			msg:'最少需要2人才能建群',
			isCancel:false
		});
		return;
	}
	if(checkedList.length > 39){
		$.ConfirmBox({
			msg:'最多勾选39名客户',
			isCancel:false
		});
		return;
	}
	var nickName = [];
	var wxNameArr = [];
	
	var rows = []; // 保存去重后的数据
	var items = []; // 保存选中的所有的项

		$.each(checkedList,function(i){
			var item = JSON.parse($(this).attr('data-item'));
			wxNameArr.push(item.noWx);
			nickName.push(item.nickNameWx);
			rows.push(item);
		});
		wxNameArr = uniq(wxNameArr)
		nickName = uniq(nickName)

		var memberNosName = nickName.join(';');

		$('#memberNos').val(memberNosName);
		$('#noWxMapping').val(wxNameArr);
		$("#sltNum").html(checkedList.length);			//已选多少人
		
		//重置条件
		$('#findPmTypeByMerchantNo').val('');
		$('#sex').val('');
		$('#keyWord').val('');
		$('#isAppendStatus').val('');
		
		$(id).hide();
}

function merchantNoList(){ // 客户分组
	var url = $('#ctx').val() + '/couponmultipush/findPmTypeByMerchantNo';
	var params = {
		merchantNo:$('#merchantNo').val()
	};
	$.post(url,params,function(data){
		if(data && data.length != 0){
			var tags = '<option value="">请选择客户分组</option>';
			$.each(data,function(i){
				tags += '<option value="'+ data[i].code +'">'+ data[i].typeName +'</option>';
			});
			$('#findPmTypeByMerchantNo').html(tags);
		}
	},'json');
}

function selectAll(elem){
	var isChecked = $(elem).is(':checked');
	$('.memberNosBox').prop('checked',isChecked);
}


merchantNoList();// 客户分组
