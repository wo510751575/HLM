
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
			couponRuleCode:{
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
			memberNos:{
				required:'推送对象不能为空'
			},
			couponRuleCode:{
				required:'优惠券不能为空'
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
	
	$(elem).attr('disabled','disabled');
	
	var url = $('#ctx').val() + '/couponmultipush/addCouponMultiPush';
	
	var params = {
		executeType:executeType,
		merchantNo:$('#merchantNo').val(),
		merchantName:$('#merchantName').val(),
		assistantNo:$('#assistantNo').val(),
		shopNoWxs:$('#noWxs').val() + ';',
		delayTimes:$('#delayTimes').val(),
		executeTime:$('#executeTime').val(),
		couponRuleCode:$('#couponRuleCode').attr('data-code'),
		noWxMapping:$('#noWxMapping').val(),
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
			// 清空数据
			$('.edit-material').find('input[type=text],textarea').val('');
			$('#sltNum').text(0);
			cancelItems();
			$("#data-table-list tr").removeClass("current");
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

function dupRemoval(array){ // 数组去重
	var rows = []; // 保存去重后的list
	var wxs = []; // 保存去重后的微信号
	$.each(array,function(i){
		if(wxs.indexOf(array[i].noWx) == -1){ // 不存在添加
			rows.push(array[i]);
			wxs.push(array[i].noWx);
		}else{ // 存在,跳出循环
			return true;
		}
	});
	return rows;
}

/*
 * 选中的素材
 */
function saveMemberNos(id){
	var checkedList = $('.memberNosBox:checked');
	var selectAll = $('#selectAllCoupon').is(':checked');
	
	var noWxMappingData = $('#noWxMapping').val();
	var memberNosData = $('#memberNos').val();
	var isAppendStatus = $('#isAppendStatus').val(); // 是否追加
	
	if(checkedList.length == 0 && !selectAll){
		$.ConfirmBox({
			msg:'请选择推送对象',
			isCancel:false
		});
	}else{
		$('#selectAllCoupon').prop('checked',false);
		var nickName = [];
		var wxs = $('#noWxs').val();
		if(selectAll){ // 全选
			var url = $('#ctx').val() + '/couponmultipush/personMemberList';
			var params = {
				keyWord:$('#keyWord').val(),
				sex:$('#sex').val(),
				typeCode:$('#findPmTypeByMerchantNo').val(),
				noWx:$('#noWxs').val(),
			};
			
			$.post(url,params,function(data){
				var rows = data.page.list;
				if(!rows || rows.length == 0) return;
				
				PUSHITEMS = rows; // 缓存选中的列表
				
				rows = dupRemoval(PUSHITEMS); // 列表去重
				
				$.each(rows,function(i){
					nickName.push(rows[i].nickNameWx);
				});
				
				var noWxMapping = noWxMapData(wxs.split(';'),rows);
				
				$('#memberNos').val(nickName.join(';'));
				$('#sltNum').html(rows.length);
				$('#noWxMapping').val(noWxMapping);
			},'json');
		}else{
			var rows = []; // 保存去重后的数据
			var items = []; // 保存选中的所有的项
			
			$.each(checkedList,function(){
				var item = JSON.parse($(this).attr('data-item'));
				items.push(item);
			});
			
			PUSHITEMS = (isAppendStatus == 'Y' ? PUSHITEMS.concat(items) : items); // 缓存选中的列表
			
			items = dupRemoval(PUSHITEMS); // 列表去重
			
			$.each(items,function(i){
				/*if(isAppendStatus == 'Y' && noWxMappingData.indexOf(items[i].noWx) != -1){ // 追加且已存在
					return true; // 跳出本次循环
				}*/
				nickName.push(items[i].nickNameWx);
				rows.push(items[i]);
			});
			
			var noWxMapping = noWxMapData(wxs.split(';'),rows);
			
			var memberNosName = nickName.join(';');
//			var memberNosName = (isAppendStatus == 'Y' && memberNosData != '') ? (memberNosData + (nickName.length != 0 ? (';' + nickName.join(';')) : '')) : nickName.join(';');
			
			$('#memberNos').val(memberNosName);
			$('#noWxMapping').val(noWxMapping);
			$('#sltNum').html(memberNosName.split(';').length);
		}
		$(id).hide();
		$('#findPmTypeByMerchantNo').val('');
		$('#sex').val('');
		$('#keyWord').val('');
		$('#isAppendStatus').val('');
	}
}


/*
 * 优惠券列表
 */
function couponList(){
	
	if($('#noWxs').val() == ''){ // 未选择微信号
		$.ConfirmBox({
			msg:'请选择网点微信号',
			isCancel:false
		});
		return;
	}
	
	$('#couponRuleCodeId').show();
	
	var url = $('#ctx').val() + '/couponmultipush/findCouponRuleList';
	var params = {
		merchantNo:$('#merchantNo').val(),
		noWxShops:$('#noWxs').val()+';'
	};
	$.post(url,params,function(data){
		if(data && data.length != 0){
			var tags = '';
			
			$.each(data,function(i){
				tags += '<div class="hd_list_det '+ (i==0?'current':'') +'" data-url="'+ data[i].shareUrl +'" data-code="'+ data[i].code +'" onclick="sltCoupon(this,\''+ data[i].shareUrl +'\')">'
							+'<div class="rollList">'
								+'<div class="roll_left text-center">'
									+'<p>￥<span>'+ data[i].couponNotes +'</span></p>'
									+'<p class="oneLine oneLineName">消费满'+ data[i].couponMax +'减'+ data[i].couponNotes +'元</p>'
								+'</div>'
								+'<div class="roll_right">'
									+'<p>'+ data[i].couponType +'</p>'
									+'<p class="oneLine">使用范围：'+ data[i].shopName +'</p>'
								+'</div>'
							+'</div>'
						+'</div>';
			});
			$('#couponRuleCodeId .hd_list').html(tags);
			$('#couponIframeId').attr('src',data[0].shareUrl);
		}
	},'json');
}

function sltCoupon(elem,shareUrl){ // 选择优惠券
	$('#couponIframeId').attr('src',shareUrl);
	$(elem).addClass('current').siblings().removeClass('current');
}

function confirmCoupon(elem){ // 确定选择优惠券
	var code = $(elem).find('.current').attr('data-code');
	var name = $(elem).find('.current .oneLineName').text();
	$('#couponRuleCode').val(name).attr('data-code',code);
	$(elem).hide();
	
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

function sltChecked(){
	$('#selectAllCoupon').prop('checked',false);
}

merchantNoList();// 客户分组