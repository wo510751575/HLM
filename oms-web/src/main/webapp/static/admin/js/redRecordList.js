$(document).ready(function() {
	var noWx=$("#noWx1").val();
	console.log(noWx);
	var firstwxNickname1=$("#wxNickname1").val();
	
	$(".shop_nav .storeClient").change(function(){
		storeSearch();
	});
	$(".shop_nav #codeListSec").change(function(){
		storeSearch();
	});
	$(".shop_nav select.province").change(function(){
		storeSearch();
	});
	$(".shop_nav select.qu").change(function(){
		storeSearch();
	});
	$(".shop_nav select.shi").change(function(){
		storeSearch();
	});
	
	window.localStorage.setItem("headImgLeft", $(".left_nav .member img").attr("src"));

	
});



//红包历史记录
function redList(noWx,year,shopName,addr){
	var year = $('.selectTimeBox .timeBox').find('option:selected').val();
	$('#table-list').ImPaging({
		thead:['创建时间','发送时间','客户昵称','金额','状态'],
		success:function(elem,ui,pageData){ // elem-paging,ui-ImPage
			var pageData = pageData || {};
			var url = $('#ctx').val() + "/im/redPackets/getRedRecordList";
			var params = {
					noWxShop:noWx,
					searchYear:year,
					pageNo:pageData.pageNo || 1,
					pageSize:pageData.pageSize || 10,
			};
			
			$.post(url,params,function(res){
				var data = res.data;
				ui.createTable(elem,function(){ // 创建表格
					var rows = data.rows;
					if(rows.length == 0) return;
					var tags = '';
					
					$.each(rows,function(i){
						
						var statustype="";
		         		if(rows[i].status==0){
		         			statustype="未发送";
		         		}else if(rows[i].status==1){
		         			statustype="发送中";
		         		}else if(rows[i].status==2){
		         			statustype="已发送";
		         		}else if(rows[i].status==3){
		         			if(rows[i].errorMsg==''||rows[i].errorMsg==null){
		         				statustype='<span class="fontColor" onclick="resend(\''+rows[i].code+'\',event,this)">'+'重发'+'</span>';
		         			}else{
		         				statustype='<span class="fontColor" onclick="resend(\''+rows[i].code+'\',event,this)">'+'重发'+'</span>'+'（'+ rows[i].errorMsg +'）';
		         			}
		         		}else if(rows[i].status==4){
		         			statustype="已领取";
		         		}else if(rows[i].status==5){
		         			statustype="已退回";
		         		}
						
						tags += '<tr>';
						if(rows[i].createDate){
							tags += '<td class="grayColor">'+ (new Date(rows[i].createDate).format('yyyy-MM-dd hh:mm')) +'</td>';
						}else{
							tags += '<td class="grayColor"></td>';
						}
						if(rows[i].sendDate){
							tags += '<td class="grayColor">'+ (new Date(rows[i].sendDate).format('yyyy-MM-dd hh:mm')) +'</td>';	
						}else{
							tags += '<td class="grayColor"></td>';	
						}
						tags += '<td>'+ rows[i].memberName +'</td>';
						tags += '<td>'+ rows[i].amount/100+ '元' +'</td>';
						tags += '<td class="grayColor" style="width:30%;">'+ statustype +'</td>';
						tags += '</tr>';
					});
					return tags;
				});
				
				data.pageNo = pageData.pageNo || 1;
				ui.createPages(elem,data); // 创建页码
				
				$('#noWx1').val(noWx);
				$('.contentInfo p span').find('.totalNum').text(data.total+'个');
				$('.contentInfo p span').find('.amount').text(Number(res.successMoney/100)+'元');
				$('.midBox .photo').find('img').attr('src',addr);
				$('.midBox .contentInfo').find('h4').text(shopName);
			},'json');
		}
	});
}

function backRed(){//返回发红包页面
	window.location.href= $('#ctx').val() + '/im/redPackets/show';
}

//function redList(noWx,year,shopName,addr){
//	//console.log(noWx,year,shopName);
//	var year = $('.bottomListBox .timeBox').find('option:selected').val();
//	$.ajax({
//		type:'POST',
//		url:$('#ctx').val() + "/im/redPackets/getRedRecordList",
//		data:{
//			noWxShop:noWx,
//			searchYear:year,
//			pageNo:1,
//			pageSize:10,
//		},
//		dataType:'json',
//		success:function(res){
//			console.log(res);
//			if(res.success==true){
//				if(res.data.rows){
//					var html ='';
//					var allAmount = 0;
//					for(var i=0;i<res.data.rows.length;i++){
//						allAmount += Number(res.data.rows[i].amount);
//						var statustype="";
//		         		if(res.data.rows[i].status==0){
//		         			statustype="未发送";
//		         		}else if(res.data.rows[i].status==1){
//		         			statustype="发送中";
//		         		}else if(res.data.rows[i].status==2){
//		         			statustype="已发送";
//		         		}else if(res.data.rows[i].status==3){
//		         			if(res.data.rows[i].errorMsg==''||res.data.rows[i].errorMsg==null){
//		         				statustype='<span class="fontColor" onclick="resend(\''+res.data.rows[i].code+'\',event,this)">'+'重发'+'</span>';
//		         			}else{
//		         				statustype='<span class="fontColor" onclick="resend(\''+res.data.rows[i].code+'\',event,this)">'+'重发'+'</span>'+'（'+ res.data.rows[i].errorMsg +'）';
//		         			}
//		         		}else if(res.data.rows[i].status==4){
//		         			statustype="已领取";
//		         		}else if(res.data.rows[i].status==5){
//		         			statustype="已退回";
//		         		}
//						html+='<tr><td class="grayColor">'+ (new Date(res.data.rows[i].sendDate).format('yyyy-MM-dd'))+'</td><td>'+ res.data.rows[i].memberName+'</td><td>'+res.data.rows[i].amount/100+ '元'+'</td><td class="grayColor" style="width:30%;">'
//						+statustype+'</td></tr>';
//					}
//					$('#noWx1').val(noWx);
//					$('.contentInfo p span').find('.totalNum').text(res.data.total+'个');
//					$('.contentInfo p span').find('.amount').text(allAmount/100+'元');
//					$('.bottomListBox table').find('tbody').html(html);
//				}else{
//					$('.bottomListBox table').find('tbody').html('');
//				}	
//			}
//		},
//		error:function(){
//			console.log('接口请求失败')
//		}
//	})
//	$('.midBox .photo').find('img').attr('src',addr);
//	$('.midBox .contentInfo').find('h4').text(shopName);
//}


//重新发送
function resend(code,event){
	$.ajax({
		type:'POST',
		url:$('#ctx').val() + "/im/redPackets/reHandler",
		data:{
			code:code,
		},
		dataType:'json',
		success:function(res){
			console.log(res);
			console.log($(event.target).text());
			$(event.target).parent('td').text('发送中');
		},
		error:function(){
			console.log('接口请求失败')
		}
	})
}

//选择时间（年份）
function selectTime(){
	var noWx = $('#noWx1').val();
	var year = $('.selectTimeBox .timeBox').find('option:selected').val();
	console.log(noWx,year);
	redList(noWx,year);
}


function storeSearch(){
	var searchWords=$(".shop_nav .searchTh").val();
	var shopNo=$(".shop_nav #codeListSec").find("option:selected").val();
	var pmTypeCode=$(".shop_nav .storeClient ").find("option:selected").val();
	var provinceCode=$(".shop_nav .province ").find("option:selected").val();
	var cityCode=$(".shop_nav .shi ").find("option:selected").val();
	
	var cityAreaCode=$(".shop_nav .qu ").find("option:selected").val();
//	window.location.href=$('#ctx').val()+"/im/list?searchWords="+searchWords+"&shopNo="+shopNo+"&pmTypeCode="+pmTypeCode+"&provinceCode="+provinceCode+"&cityCode="+cityCode+"&cityAreaCode="+cityAreaCode;
	window.location.href=$('#ctx').val()+"/im/redPackets/toRedRecordList?searchWords="+searchWords+"&shopNo="+shopNo+"&pmTypeCode="+pmTypeCode+"&provinceCode="+provinceCode+"&cityCode="+cityCode+"&cityAreaCode="+cityAreaCode;
}


/*重置查询*/
function resetQuery(){
	$(".shop_list .active").click();
}

		
		




