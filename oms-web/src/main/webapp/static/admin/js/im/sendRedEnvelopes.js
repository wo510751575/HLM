
var PUSHITEMS = []; // 推送的所有数据

/*网点微信列表*/
function wxNosList(){ 
	$('#wxNosListTable').ImPaging({
		isTable:false,
		success:function(elem,ui,pageData){ // elem-paging,ui-ImPage
			var pageData = pageData || {};
			var url = $('#ctx').val() + '/friendsjob/findShopTerminalList.do';
			var params = {
				assistantNo:$('#assistantNo').val(),
				merchantNo:$('#merchantNo').val(),
			};
			
			$.post(url,params,function(data){
				ui.createTable(elem,function(){ // 创建表格
					if(stopNum>0){
						window.clearInterval(stopQ);
					}
					var rows = data;
					var tags = '';
					if(rows.length == 0) return tags;
					tags += '<ul class="memberNos-list">';
					$.each(rows,function(i){
						if(rows[i].onlineFlag==0){
							tags += '<li class="redLi" data-Info=\''+JSON.stringify(rows[i])+'\' ><label class="wxbox" for="wxsBox'+ i +'"><span><input type="checkbox" class="boxNowx" onchange="cancel()" id="wxsBox'+ i +'" data-nowx='+ rows[i].noWx +'></span>';
							tags += '<span>'+ rows[i].noWx +'</span>';
							tags += '<span>'+ rows[i].wxNickname +'</span>';
							tags += '<span>'+ rows[i].imei +'</span>';
							tags += '<span>'+ rows[i].terminalCode +'</span>';
							if(rows[i].wxBalance!=undefined){
								tags += '<span>'+ rows[i].wxBalance/100 +'</span>';
							}else{
								tags += '<span></span>';
							}
							tags += '</label></li>';
						}else{
							tags += '<li data-Info=\''+JSON.stringify(rows[i])+'\' ><label class="wxbox" for="wxsBox'+ i +'"><span><input type="checkbox" class="boxNowx" onchange="cancel()" id="wxsBox'+ i +'" data-nowx='+ rows[i].noWx +'></span>';
							tags += '<span>'+ rows[i].noWx +'</span>';
							tags += '<span>'+ rows[i].wxNickname +'</span>';
							tags += '<span>'+ rows[i].imei +'</span>';
							tags += '<span>'+ rows[i].terminalCode +'</span>';
							if(rows[i].wxBalance!=undefined){
								tags += '<span>'+ rows[i].wxBalance/100 +'</span>';
							}else{
								tags += '<span></span>';
							}
							tags += '</label></li>';
						}
					});
					tags +='</ul>'
					return tags;
				});
				
			},'json');
		}
	});
}


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
			redpackAmount:{
				required:true,
			},
			remarkRed:{
				rangelength:[0,25],
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
			redpackAmount:{
				required:'红包金额不能为空',
			},
			remarkRed:{
				rangelength:'红包备注不能超过25个字',
			}
		},
		errorPlacement: function(error, element) {  
		    error.appendTo(element.parents('.tag'));  
		}
	});
}
 
function sendFriends(form,executeType){ // 发红包    executeType:执行类型(1：保存任务，2：立即执行)
	var sltNum=$("#sltNum").text();
	var redpackAmount=$("#redpackAmount").val();
	if(sltNum>100){
		$.ConfirmBox({
			msg:"推送对象最多100人。",
			isCancel:false
		});
		return;
	}
	
	var talM=sltNum*redpackAmount;
	if(talM>20000){
		$.ConfirmBox({
			msg:"红包总金额不得超过20000。",
			isCancel:false
		});
		return;
	}
	
	if(executeType=="1"){
		$(".button-list .save").css("background","#6cc4ac");
		$(".button-list .save").css("color","#fff");
		$(".button-list .doNow").css("background","#ececec");
		$(".button-list .doNow").css("color","#2a2a2a");
		$("#cnfirmPay").attr("data-num",1);
	}else if(executeType=="2"){
		$(".button-list .doNow ").css("background","#6cc4ac");
		$(".button-list .doNow ").css("color","#fff");
		$(".button-list .save").css("background","#ececec");
		$(".button-list .save").css("color","#2a2a2a");
		$("#cnfirmPay").attr("data-num",2);
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
	}*/
	$("#confirmM").text($("#meneyAll").text());
	var now=$("#noWxs").val().split(";");
	$("#wxNum").text(now.length);
	
	var ye=YESDATATABLELIST;
	var tidMoney="";
	var noWxGMs=""
	$.each(ye,function(i){
		var sign=JSON.parse(ye[i]);
		tidMoney +=sign.tidCode+"#"+sltNum+"#"+redpackAmount*100+",";
		noWxGMs +=sign.noWx+","
	});
	
	var params={
			tidMoney:tidMoney,
			noWxGMs:noWxGMs
	}
	$.post($('#ctx').val() + '/im/redPackets/verifyMoneySumAmt',params,function(data){
		if(data.tidMoney_msg==""){ //  成功
			var mn="";
			for (var key in data) {  
				if(key !="tidMoney_msg"){
					mn=data[key];
				}
	        }
			 if(executeType=="2" && Number(mn/100)+Number(talM)>20000){
				 $.ConfirmBox({
						msg:"每日最多发红包金额为20000",
						isCancel:false
					});
			 }else{
				 $("#confirm").css("display","flex");
			 }
		}else{ //失败
			$.ConfirmBox({
				msg:data.tidMoney_msg,
				isCancel:false
			});
		}
	},'json');
	
	
}

/*确认密码*/
function payRed(elem){ 
	var confirmPwd=$("#confirmPwd").val();
	if(confirmPwd==""){
		$.ConfirmBox({
			msg:"请输入密码",
			isCancel:false
		});
		return;
	}
	
	$(elem).attr('disabled','disabled');
	
	var url=$('#ctx').val() + '/im/redPackets/verifyLoginPsw';
	
	var params={password:confirmPwd}
	$.post(url,params,function(data){
		if(data.success){ //  成功
			dopay(); 
			// 清空数据
			$('.edit-material').find('input[type=text],textarea').val('');
			$('#sltNum').text(0);
			$('#meneyAll').text(0);
			$("#wxNosListTable input[type=checkbox]").each(function(){$(this).prop("checked",false)});
		}else if(data.logout){
			$.ConfirmBox({
				confirm:function(){
					window.location.href=$("#ctx").val()+"/logout";
				}, // 确定调用方法
				msg:data.msg,
				isCancel:false
			});	
				
		}else{ //失败
			$.ConfirmBox({
				msg:data.msg || "密码错误",
				isCancel:false
			});
		}
		$(elem).removeAttr('disabled');
	},'json');
}

function dopay(){
	var executeType=$("#cnfirmPay").attr("data-num");
	var url = $('#ctx').val() + '/im/redPackets/addRedPackets';
	
	var params = {
		executeType:executeType,
		merchantNo:$('#merchantNo').val(),
		assistantNo:$('#assistantNo').val(),
		redpackContent:$('#remarkRed').val(), //红包备注
		shopNoWxs:$('#noWxs').val() + ';',
		delayTimes:$('#delayTimes').val(),
		executeTime:$('#executeTime').val(),
		amount:$("#redpackAmount").val()*100,
		noWxMapping:$('#noWxMapping').val(),
	};
	
	$.post(url,params,function(data){
		if(data.success){ // 成功
			$.ConfirmBox({
				msg:"新增微信红包任务成功",
				isCancel:false
			});
		}else{ // 失败
			$.ConfirmBox({
				msg:data.msg,
				isCancel:false
			});
		}
		$("#confirm").css("display","none");
		$("#confirmPwd").val("");
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

function cancel(){
	$("#selectAllwx").prop("checked",false);
}

function noWxMapData(wxs,list){ // 拼数据, wx#num#code,code;
	var wxs = wxs; // 微信号数组
	var num = []; // 计数
	var noWxs = [];
	for(var i = 0, len = wxs.length; i < len; i++){
		num[i] = 0;
		noWxs[i] = [];
	}
	$.each(list,function(m){
		var index = wxs.indexOf(list[m].noWxGm);
		if(index != -1){
			num[index] = num[index] + 1;
			noWxs[index].push(list[m].noWx);
		}
	});
	
	var noWxData = [];
	for(var n = 0, len = wxs.length; n < len; n++){
		noWxData.push(wxs[n] + '#' + num[n] + '#' + noWxs[n].join(','));
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
					nickName.push(rows[i].nickNameWx || rows[i].memberName);
				});
				
				var noWxMapping = noWxMapData(wxs.split(';'),rows);
				
				$('#memberNos').html(nickName.join(';'));
				$('#sltNum').html(rows.length);
				$('#noWxMapping').val(noWxMapping);
				if($("#redpackAmount").val()!=""){
					$("#meneyAll").text($("#redpackAmount").val()*rows.length);
				}
			},'json');
		}else{
			var rows = [];
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
				nickName.push(items[i].nickNameWx  || items[i].memberName);
				rows.push(items[i]);
			});
			
			var noWxMapping = noWxMapData(wxs.split(';'),rows);
			
			var memberNosName = nickName.join(';');
//			var memberNosName = (isAppendStatus == 'Y' && memberNosData != '') ? (memberNosData + (nickName.length != 0 ? (';' + nickName.join(';')) : '')) : nickName.join(';');
			
			$('#memberNos').html(memberNosName);
			$('#noWxMapping').val(noWxMapping);
			$('#sltNum').html(memberNosName.split(';').length);
			
			if($("#redpackAmount").val()!=""){
				$("#meneyAll").text($("#redpackAmount").val()*checkedList.length);
			}
		}
		$(id).hide();
		$('#findPmTypeByMerchantNo').val('');
		$('#sex').val('');
		$('#keyWord').val('');
		$('#isAppendStatus').val('');
	}
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
function selectwxAll(elem){
	var isChecked = $(elem).is(':checked');
	$('.boxNowx').prop('checked',isChecked);
}

function sltChecked(){
	$('#selectAllCoupon').prop('checked',false);
}

function changeMoney(event){
	var m=$(event).val();
	if(m<0.01){
		$(event).val(0.01);
	}
	if(!/^([1-9]\d*(\.\d*[1-9])?)|(0\.\d*[1-9])$/.test(m)){
		$.ConfirmBox({
			msg:"请输入合法的红包金额！",
			isCancel:false
		});
	}
	if(m<0.01){
		$(event).val(0.01);
	}
	if(m>200){
		$(event).val(200)
		$.ConfirmBox({
			msg:"单个红包不能超过200！",
			isCancel:false
		});
	}
	var num=$("#sltNum").text();
	var tm=Number($("#redpackAmount").val())*num;
	$("#meneyAll").text(tm);
}

function RecordR(){ //红包记录
	window.location.href=$('#ctx').val() + '/im/redPackets/toRedRecordList';
}

/*刷新余额*/
var stopQ="";
var stopNum=0;
function refreshAmount(){
	var url=$('#ctx').val() + '/im/redPackets/refreshRedPacketBalance';
	var noWxGMs="";
	$("#wxNosListTable li").each(function(){
		noWxGMs +=$(this).find(".boxNowx").attr("data-nowx")+",";
	})
	var params={
		noWxGMs:noWxGMs,
	}
	$.post(url,params,function(data){
		if(data){
			$.ConfirmBox({
				msg:"网点微信刷新请求已发送，2分钟后再来查看！",
				isCancel:false
			});
			stopNum=stopNum+1;
			stopQ=setInterval("wxNosList()",12000);
		}
	},'json');
}

wxNosList();
merchantNoList();// 客户分组