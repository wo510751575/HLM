
Date.prototype.format = function (f) { //date_format
	var o = {
		"M+": this.getMonth() + 1, //month
		"d+": this.getDate(),    //day
		"h+": this.getHours(),   //hour
		"m+": this.getMinutes(), //minute
		"s+": this.getSeconds(), //second
		"q+": Math.floor((this.getMonth() + 3) / 3),  //quarter
		"S": this.getMilliseconds() //millisecond
	}
	if (/(y+)/.test(f)) f = f.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(f)) f = f.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
	return f
};

var itemsConfig = {
	pageNo : 1, // 页码(第一页为1)
	dataPrev: '',
	pageFlag: false,
	totalItems:0,
	colors:$('#colors').val() || '#f31d5f,#f31d5f,#f31d5f,#fff', // 默认美发     色值  0-项目边框色, 1-项目文字色 , 2-满意度背景色, 3-满意度字体色
	emptyImgs:$('#getUploadUrl').val() + ($('#emptyImgs').val() || ($('#ctxStatic').val() + '/admin/images/icon_empty_data.png')), // 默认美发
	appName:$('#appName').val() || 'haircut' 
};

function getConsumeMember(pageNo){ // 获取导购和顾客编号    客户端
//	var url = 'http://192.168.6.74:8080/api/hcmember/findClientActionList.do';
	var url = '/oms-web/hc/clientConsume/viewH5';
	
	var consumeMember = {
		pageNo:pageNo,
		pageSize:10,
		memberNo:$('#memberNo').val(),
		memberNoGm:$('#memberNoGm').val(),
		appName:$('#appName').val()
	};
	
	$.post(url,consumeMember,function(data){
		var list = data.rows;
		if(list){
			itemsConfig.totalItems += list.length;
			if(itemsConfig.totalItems >= data.total){
				$('#loadMore').remove();
			}else{
				$('#loadMore').show();
			}
		}
		
		var color = itemsConfig.colors.split(','); // 色值
		var emptyImg = itemsConfig.emptyImgs; // 空白页图标
		consumeList(list,pageNo,color,emptyImg);
	},'json');	
}

function consumeList(list,pageNo,color,emptyImg){
	var tags = '';
	if(!list){
		if(pageNo == 1){ // 显示空白页提示
			var tags = '<div class="empty" id="empty" style="background:#fff; height:100vh;">'+
							'<div class="empty-img"><img src="' + emptyImg + '"/></div>'+
							'<div class="empty-f">暂时没有数据</div>'+
						'</div>';
			
//			$('#empty').css('display','flex');
			$('.main').append(tags);
			$('#loadMore').remove();
		}
		itemsConfig.pageFlag = false;
		return;
	}

	$.each(list,function(i){
		var date = new Date(list[i].actionTime).format('yyyy.MM.dd');
		
		if(i > 0){
			itemsConfig.dataPrev = new Date(list[i-1].actionTime).format('yyyy.MM.dd');
		}
		
		if(itemsConfig.dataPrev == '' || date != itemsConfig.dataPrev){
			tags += '<div class="d">'+ date +'</div>';
		}
			tags += '<div class="content">';
				
			tags += '<div class="rt-list">';
				tags += '<div class="rt">';
					var projectJson = [];
					if(list[i].projectJson){
						projectJson = list[i].projectJson.replace(/[\[\]]/ig,'').split(',');
					}
					
					if(list[i].actionType == 'A'){
						tags += '<div class="dz">到店后未成交</div>';
					}
					if(list[i].actionType == 'B'){
						tags += '<div class="dz">应邀后未到店</div>';
					}
					if(list[i].actionType == 'P'){
						tags += '<div class="dz">到店后成交</div>';
					}
					
					if(list[i].personName){
						tags += '<div class="type">';
							tags += '<span class="tp" style="border:1px solid '+ color[0] +'; color:'+ color[1] +'">'+ list[i].personName +'</span>';
						tags += '</div>';
					}
					
					if(list[i].projectJson){
						$.each(projectJson,function(m){
							tags += '<div class="type">';
							var proList = projectJson[m].replace(/[\"]/ig,'').split('|');
							$.each(proList,function(n){
								tags += '<span class="tp" style="border:1px solid '+ color[0] +'; color:'+ color[1] +'">'+ proList[n] +'</span>';
							});
							tags += '</div>';
						});
					}
					if(list[i].remark || list[i].imgAddr){
						tags += '<div class="cnt-f">';
						
						tags += '<div class="t">';
							tags += '<span>'+ new Date(list[i].actionTime).format('hh:mm') +'</span>';
							tags += '<p class="circleF"><span class="circle"></span></p>';
							tags += '<span class="left-icon"></span>';
						tags += '</div>';
						
						if(list[i].remark){
							tags += '<div class="cnt">'+ list[i].remark +'</div>';
						}
						if(list[i].imgAddr){
							var imgAddr = list[i].imgAddr.split(',');
							tags += '<ul class="img-list beauty-images-list">';
								$.each(imgAddr,function(g){
									tags += '<li onclick="searchLagerImage(this)"><img src="'+ $('#getUploadUrl').val() + imgAddr[g] +'"></li>';
								});
							tags += '</ul>';
						}
						tags += '</div>';
					}else{
						tags += '<div class="cnt-f" style="border:0; padding:0 10px; margin-top:0;">';
						
						tags += '<div class="t">';
							tags += '<span>'+ new Date(list[i].actionTime).format('hh:mm') +'</span>';
							tags += '<p class="circleF"><span class="circle"></span></p>';
						tags += '</div>';
						
						tags += '</div>';
					}
					
				if(list[i].satisfaction != null){
					tags += '<div class="myd">';
						tags += '<span class="kh">客户满意度：</span>';
						if(list[i].satisfaction == 0){
							tags += '<span class="tp" style="background:'+ color[2] +'; color:'+ color[3] +'">差评</span>';
						}else if(list[i].satisfaction == 3){
							tags += '<span class="tp" style="background:'+ color[2] +'; color:'+ color[3] +'">一般</span>';
						}else{
							tags += '<span class="tp" style="background:'+ color[2] +'; color:'+ color[3] +'">满意</span>';
						}
					tags += '</div>';
				}
				if(list[i].nextDiaTime){
					tags += '<div class="nt">下次复诊时间：'+ (new Date(list[i].nextDiaTime.replace(/\-/g,'/').replace(/\.\d*/,'')).format('yyyy-MM-dd hh:mm')) +'</div>';
				}
				if(list[i].amtAll){ // 全部金额
					tags += '<div class="nt">总金额：'+ Number(list[i].amtAll/100).toFixed(2) +'元</div>';
				}
				if(list[i].tradeAmt && list[i].actionType == 'P'){ // 报价 ：actionType=A 　, 交易金额:actionType=P
					tags += '<div class="nt">交易金额：'+ Number(list[i].tradeAmt/100).toFixed(2) +'元</div>';
				}
				if(list[i].tradeAmt && list[i].actionType == 'A'){ // 报价 ：actionType=A 　, 交易金额:actionType=P
					tags += '<div class="nt">报价：'+ Number(list[i].tradeAmt/100).toFixed(2) +'元</div>';
				}
				tags += '</div>';
			tags += '</div>';
		tags += '</div>';
	});
	$('#beauty-main').append(tags);
	var wid = $('.beauty-images-list:eq(0) li:eq(0)').width();
	$('.beauty-images-list li').css({'height':wid+'px'});
	itemsConfig.pageFlag = true;
}


function loadNextData(){ // 滚动加载下一页
	$(document).on('scroll',function(){
		var dh = $(document).height(); // document height
		var sh = $(document).scrollTop();
		var wh = window.innerHeight || document.documentElement.clientHeight; // screen height
		if(dh - sh - wh == 0 && itemsConfig.pageFlag){
			itemsConfig.pageFlag = false;
			itemsConfig.pageNo += 1;
			getConsumeMember(itemsConfig.pageNo);
		}
	});
}

function searchLagerImage(elem){
    var li = $(elem).parent().find('li');
    var imgArr = [];
    var index = $(elem).index();
    
    $.each($(li),function(){
    	var src = $(this).find('img').attr('src');
    	imgArr.push(src);
    });
    
	try{ // 客户端
		var data = {
			index:index,
			imgList:imgArr.join(',')
		};
		window.webInterface.showLargeImage(JSON.stringify(data));
		
    }catch(e){ // h5分享
	    var tags = '';
	    tags += '<div class="swiper-container-frame" onclick="$(this).remove()"><div class="swiper-container">';
	        tags += '<div class="swiper-wrapper">';
	            $.each($(li),function(i){
		            tags += '<div class="swiper-slide">'+ $(this).html() +'</div>';
	            });
	        tags += '</div>';
        tags += '<div class="swiper-pagination"></div>';
	    tags += '</div></div>';
	    $('#beauty-main').append(tags);
		var swiper = new Swiper('.swiper-container',{
			initialSlide:index,
			pagination: '.swiper-pagination',
		});
    }
}

function initLoad(){ // 初始化判断
	try{ // 客户端
		window.webInterface.isClientSys();
		if(itemsConfig.totalItems != 0){
			$('#loadMore').show();
		}
    }catch(e){ // 分享页
    	$('#loadMore').remove();
    	loadNextData();
    }
}

function loadNextPage(){
	if(itemsConfig.pageFlag){
		itemsConfig.pageNo += 1;
		getConsumeMember(itemsConfig.pageNo);
	}
}

initLoad();
getConsumeMember(itemsConfig.pageNo);
