/*
*
*新建群发
*
*/

var PUSHITEMS = []; // 推送的所有数据
var hasCheckedMember = []	//已经勾选的客户

var memberNosArr = []			//客户编号数组
var memberNamesArr = []			//客户名称数组



function formValid(form){ // 发送素材验证
	$(form).validate({
		rules:{
			noWxs:{
				required:true
			},
			memberNos:{
				required:true
			}
		},
		messages:{
			noWxs:{
				required:'网点微信号不能为空'
			},
			memberNos:{
				required:'群发对象不能为空'
			}
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
	var content = $('#roomNickName').val() // 群发内容
	var imgUrl = $("#imgListId li").find("img").attr("src")	//图片地址
	var type;													//類型
	//console.log(imgUrl)
	//console.log(content)
	if(imgUrl == undefined && content == ''){
			$.ConfirmBox({
				msg:"群发内容或者图片不能为空",
				isCancel:false
			});
			return
	}
	if(imgUrl != undefined && content != ''){
			$.ConfirmBox({
				msg:"一次只能发送文字或者图片",
				isCancel:false
			});
			return
	}
	if(content){
		type = 1
	}else{
		type = 3
	}
	formValid(form);
	if(!$(form).valid()) return;

	$(elem).attr('disabled','disabled');
	var url = $('#ctx').val() + '/im/groupChatInfo/save'; //创建群
	var params = {
		 type: type,														//	1文本    3图片
		 memberNos: memberNosArr.join(","),			//	客户编号
		 memberNames:	memberNamesArr.join(","),	//	客户名称
		 memberNoWxs:  $('#noWxMapping').val(),			//	客户微信号
		 content: content,				//		文字内容
		resourcesPath:  imgUrl,		//图片路径
		noWxGm: 	$('#noWxs').val(),					//导购微信号
	};
	//console.log(params)
	$.post(url,params,function(data){
		debugger;
		if(data.result === true){ // 成功
			$.ConfirmBox({
				msg:"新建群发成功",
				isCancel:false
			});
			// 清空数据
			$('.edit-material').find('input[type=text],textarea').val('');
			$('#sltNum').text(0);
			cancelItems();
			$("#data-table-list tr").removeClass("current");
			$("#imgListId").find('li').remove()
			memberNosArr = []
			memberNamesArr = []
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


function uniq(array){
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
	var selectAll = $('#selectAllCoupon').is(':checked');

	var noWxMappingData = $('#noWxMapping').val();
	var memberNosData = $('#memberNos').val();
	var isAppendStatus = $('#isAppendStatus').val(); // 是否追加

		$('#selectAllCoupon').prop('checked',false);
		var nickName = [];
		var wxNameArr = [];
		var wxs = $('#noWxs').val();
		var checkNum = $("input[type='checkbox']:checked").length // 勾选客户数量
		if(checkNum > 200){
			$.ConfirmBox({
				msg:'最多勾选200名客户',
				isCancel:false
			});
			return;
		}
		
		var rows = []; // 保存去重后的数据
		var items = []; // 缓存的所有的项

		$.each(checkedList,function(i){
			var item = JSON.parse($(this).attr('data-item'));
			wxNameArr.push(item.noWx);
			nickName.push(item.nickNameWx);
			memberNosArr.push(item.memberNo);
			memberNamesArr.push(item.memberName);
			
			rows.push(item);
		});
		wxNameArr = uniq(wxNameArr);
		nickName = uniq(nickName);
		memberNosArr = uniq(memberNosArr);
		memberNamesArr = uniq(memberNamesArr);
		
		var noWxMapping = noWxMapData(wxs.split(';'),rows);

		var memberNosName = nickName.join(';');

		$('#memberNos').val(memberNosName);
		$('#noWxMapping').val(wxNameArr);
		$("#sltNum").html(checkedList.length);			//已选多少人
		
		sessionStorage.removeItem("checkArr");
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
