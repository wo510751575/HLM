function toast(data){
		var data = data || {};
		var prompt_st = null;
		var prompt = $('#promptMsg');

		if(prompt.length == 0){
			var tags = '<div id="promptMsg"><span class="prom"></span></div>';
			$('body').append(tags);
			prompt = $('#promptMsg');
		}

		var	prompt = prompt;
		prompt.css({
			position:'fixed',
			top:'0',
			left:'0',
			width:'100%',
			height:'100%',
			'z-index':'1000',
			display:'flex',
			'justify-content':'center',
			'align-items':'center',
			background:'rgba(0,0,0,0)',
		}).find('.prom').css({
			padding:'10px 20px',
			background:'rgba(0,0,0,0.8)',
			color:'#fff',
			opacity:'0',
			'font-size':'14px',
			'max-width':'80%',
			'border-radius':'8px',
		});

		prompt.find('.prom').html(data.msg).animate({
			opacity:'1'
		},500);

		prompt_st = setTimeout(function(){
			prompt.find('.prom').animate({opacity:'0'},500,function(){
				$(this).parent().remove();
				clearTimeout(prompt_st);
			});
		},data.delay || 2000);
	}

function getQueryString(name) { // 获取url的参数
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null)return  decodeURI(r[2]); return '';
}

function initData(){ // 用户A判断是否已领取过
	
	var getParams = JSON.parse(getQueryString('params') || '{}');
	
	var url = '/oms-web/cp/h5coupon/findCouponStatus';
	var params = {
		ruleNo:getParams.ruleNo,
		memberNo:getParams.memberNo,	
	};
	
	var href = location.href;
	if(href.indexOf('&') != -1){
		location.href = href.substring(0,href.indexOf('&'));
	}
	
	var wConfig = {
		link:'/oms-web/cp/h5coupon/findCouponInviteDetail?noWxShop='+ $('#noWxShop').val() +'&ruleNo='+ getParams.ruleNo +'&memberNo='+getParams.memberNo+'&memberNoGm='+getParams.memberNoGm	
	}
	
	wxConfig(wConfig);
	
	$.post(url,params,function(data){
		
		setTimeout(function(){
			$('#app').show();
			$('body').css({'background':'url('+ $('#ctxStatic').val() +'/admin/images/couponnew/opl.png?v=1) no-repeat','background-size':'cover'});
		},500);
		
		var tags = ''
		if(data.result){
			$('#couponNo').val(data.couponNo || '');
			$('#couponName').val(data.couponName || '');
			if(data.own){ // 已领过
				tags += '<div class="pwd">';
				tags += '<img src="'+ $('#ctxStatic').val() +'/admin/images/couponnew/gds.png" class="w"/>';
				tags += '<div class="f">券码： '+ data.couponNo +'</div>';
				tags += '<img src="'+ $('#ctxStatic').val() +'/admin/images/couponnew/fdr.png" class="w"/>';
				if(!data.expire){ // 过期
					if(data.couponStatus!='UNUSED'){ // 已使用
						tags += '<img src="'+ $('#ctxStatic').val() +'/admin/images/couponnew/fdsgh.png" class="icon"/>';
					}
				}else{ // 没过期
					tags += '<img src="'+ $('#ctxStatic').val() +'/admin/images/couponnew/sssd.png" class="icon"/>';
				}
				
				tags += '</div>';
				
				if(data.couponStatus=='UNUSED' && !data.expire){ // 未使用  且 没过期
					tags += '<input type="button" value="立即使用" class="btn" onclick="useCoupon(this)"/>';
				}else{ // 已使用
//					tags += '<input type="button" value="分享" class="btn" onclick="$(\'#wxShareTip\').show()"/>';
				}
			}else{ // 未领过
				tags += '<input type="button" value="立即领取" class="btn" onclick="receiveCoupon(this)"/>';
			}
		}
		$('#couponButton').html(tags);
	},'json');
	
}

function initDataB(){ // 用户B判断是否已领取过
	var getParams = JSON.parse(getQueryString('params') || '{}');
	
	var url = '/oms-web/cp/h5coupon/findCouponStatus';
	var params = {
		ruleNo:getParams.ruleNo,
		addFriendsCode:getParams.addFriendsCode,	
	};
	var wConfig = {
		link:'/oms-web/cp/h5coupon/findCouponOtherInviteDetail?noWxShop='+ $('#noWxShop').val() +'&ruleNo='+ getParams.ruleNo +'&memberNo='+getParams.memberNo+'&addFriendsCode='+getParams.addFriendsCode+'&memberNoGm='+getParams.memberNoGm	
	};
	
	wxConfig(wConfig);
	
	$.post(url,params,function(data){
		
		var tags = '';
		if(data.result){ // 已使用
			
			$('#couponNo').val(data.couponNo || '');
			
			tags += '<div class="pwd">';
			tags += '<img src="'+ $('#ctxStatic').val() +'/admin/images/couponnew/gds.png" class="w"/>';
			tags += '<div class="f">券码： '+ data.couponNo +'</div>';
			tags += '<img src="'+ $('#ctxStatic').val() +'/admin/images/couponnew/fdr.png" class="w"/>';
			if(data.couponStatus!='UNUSED'){ // 已使用
				tags += '<img src="'+ $('#ctxStatic').val() +'/admin/images/couponnew/fdsgh.png" class="icon"/>';
			}
			
			if(!data.expire){ // 没过期
				if(data.couponStatus!='UNUSED'){ // 已使用
					tags += '<img src="'+ $('#ctxStatic').val() +'/admin/images/couponnew/fdsgh.png" class="icon"/>';
				}
			}else{ // 过期
				tags += '<img src="'+ $('#ctxStatic').val() +'/admin/images/couponnew/sssd.png" class="icon"/>';
			}
			
			tags += '</div>';
			
			if(data.couponStatus=='UNUSED' && !data.expire){ // 未使用 且 没过期
				tags += '<input type="button" value="立即使用" class="btn" onclick="useCouponB(this)" />';
			}else{ // 已使用
//				tags += '<input type="button" value="分享" class="btn" onclick="$(\'#wxShareTip\').show()"/>';
			}
		}
		$('#couponButton').html(tags);
	},'json');
	
}

function useCoupon(elem){ // 立即使用
	var getParams = JSON.parse(getQueryString('params') || '{}');
	
	var url = '/oms-web/cp/h5coupon/couponConsumption';
	var params = {
		ruleNo:getParams.ruleNo,
		couponNo:$('#couponNo').val(),
		memberNoGm:getParams.memberNoGm,
		memberNo:getParams.memberNo,
		couponName:$('#couponName').val(),
	};
	$.post(url,params,function(data){
		if(data.result){
			if(data.data == 'USED'){
				$(elem).siblings('.pwd').append('<img src="'+ $('#ctxStatic').val() +'/admin/images/couponnew/fdsgh.png" class="icon"/>');
				$(elem).remove();
//				$(elem).attr({'value':'分享','onclick':'$(\'#wxShareTip\').show()'});
				toast({msg:'使用成功'});
			}else if(data.data == 'EXPIRED'){
				$(elem).siblings('.pwd').append('<img src="'+ $('#ctxStatic').val() +'/admin/images/couponnew/sssd.png" class="icon"/>');
				$(elem).remove();
				toast({msg:'已过期'});
			}else if(data.data == 'NONE'){
				toast({msg:'优惠券不存在'});
			}else{
				toast({msg:'暂不能使用'});
			}
		}else{
			toast({msg:data.msg});
		}
	},'json');
}

function useCouponB(elem){ // 立即使用
	
	var getParams = JSON.parse(getQueryString('params') || '{}');
	
	var url = '/oms-web/cp/h5coupon/couponConsumption';
	var params = {
		ruleNo:getParams.ruleNo,
		couponNo:$('#couponNo').val(),
		memberNoGm:getParams.memberNoGm,
		memberNo:getParams.memberNo,
		addFriendsCode:getParams.addFriendsCode,
		couponName:getParams.couponName,
	};
	$.post(url,params,function(data){
		if(data.result){
			if(data.data == 'USED'){
				$(elem).siblings('.pwd').append('<img src="'+ $('#ctxStatic').val() +'/admin/images/couponnew/fdsgh.png" class="icon"/>');
				$(elem).remove();
//				$(elem).attr({'value':'分享','onclick':'$(\'#wxShareTip\').show()'});
				toast({msg:'使用成功'});
			}else if(data.data == 'EXPIRED'){
				$(elem).siblings('.pwd').append('<img src="'+ $('#ctxStatic').val() +'/admin/images/couponnew/sssd.png" class="icon"/>');
				$(elem).remove();
				toast({msg:'已过期'});
			}else if(data.data == 'NONE'){
				toast({msg:'优惠券不存在'});
			}else{
				toast({msg:'暂不能使用'});
			}
		}else{
			toast({msg:data.msg});
		}
	},'json');
}

function receiveCoupon(elem){ // 用户立即领取
	var getParams = JSON.parse(getQueryString('params') || '{}');
	
	var url = '/oms-web/cp/h5coupon/memberGainCoupon';
	var params = {
		ruleNo:getParams.ruleNo,
		memberNo:getParams.memberNo,
		memberNoGm:getParams.memberNoGm,
		shopNo:getParams.shopNo || '',
		couponCode:$('#couponCode').val(),
		noWxGm:getParams.noWxGm || ''
	};
	$.post(url,params,function(data){
		
		if(data.status){
			var data = data.data;
			if(data.couponStatus == 'NONE'){ // 优惠券不存在
				toast({msg:' 优惠券不存在'});
			}else if(data.couponStatus == 'EXPIRED'){ // 已过期  
				toast({msg:' 已过期 '});
			}else{
				toast({msg:' 领取成功'});
				var tags = '';
				tags += '<div class="pwd">';
				tags += '<img src="'+ $('#ctxStatic').val() +'/admin/images/couponnew/gds.png" class="w"/>';
				tags += '<div class="f">券码： '+ data.couponNo +'</div>';
				tags += '<img src="'+ $('#ctxStatic').val() +'/admin/images/couponnew/fdr.png" class="w"/>';
				tags += '</div>';
				
				tags += '<input type="button" value="立即使用" class="btn" onclick="useCoupon(this)"/>';
				
				$('#couponButton').html(tags);
				$('#couponNo').val(data.couponNo); // 优惠券编号
				$('#couponName').val(data.couponName); // 优惠券名
			}
		}else{
			toast({msg:data.message});	
		}
	},'json');
}

function receiveCouponB(elem){ // 用户B立即领取
	var getParams = JSON.parse(getQueryString('params') || '{}');
	
	var url = '/oms-web/cp/h5coupon/memberGainCoupon';
	var params = {
		addFriendsCode:getParams.addFriendsCode || '',
		couponCode:$('#couponCode').val(),
		noWxGm:getParams.noWxGm || '',
		ShareStatus:getParams.ShareStatus || '',
		merchantNo:getParams.merchantNo || '',
		shopNo:getParams.shopNo || '',
	};
	
	var reg = /^\d{4}$/;
	if(!reg.test(params.couponCode) && params.couponCode != ''){
		toast({msg:'请输入4位数字邀请码'});
		return;
	}
	
	$.post(url,params,function(data){
		if(data.status){
			var data = data.data;
			var ruleNo = data.code;
			var couponNo = data.couponNo;
			var memberNo = data.memberNo || '';
			var memberNoGm = data.memberNoGm || '';
			var couponName = data.couponName || '';
			
			var noWxShop = getParams.noWxGm;
			var merchantNo = getParams.merchantNo;
			var addFriendsCode = getParams.addFriendsCode;
			
			
			localStorage[getParams.ts] = JSON.stringify(data || {});
			
			var urlParams = {
				ruleNo:ruleNo,
				noWxShop:noWxShop,
				addFriendsCode:addFriendsCode,
				couponNo:couponNo,
				memberNo:memberNo,
				memberNoGm:memberNoGm,
				couponName:couponName,
				merchantNo:merchantNo,
			};
			
			location.replace('/oms-web/cp/h5coupon/couponInviteResult?params='+JSON.stringify(urlParams)); 
		}else{
			toast({msg:data.message});
		}
	},'json');
}

function receiveInitCouponB(){ // 缓存
	var getParams = JSON.parse(getQueryString('params') || '{}');
	var data = localStorage[getParams.ts];
	if(data){
		var data = JSON.parse(data);
		var ruleNo = data.code;
		var memberNo = data.memberNo || '';
		var memberNoGm = data.memberNoGm || '';
		var couponNo = data.couponNo;
		var couponName = data.couponName || '';
		var noWxShop = getParams.noWxGm;
		var addFriendsCode = getParams.addFriendsCode;
		var merchantNo = getParams.merchantNo;
		
		var urlParams = {
			ruleNo:ruleNo,
			noWxShop:noWxShop,
			addFriendsCode:addFriendsCode,
			couponNo:couponNo,
			memberNo:memberNo,
			memberNoGm:memberNoGm,
			couponName:couponName,
			merchantNo:merchantNo,
		};
		setTimeout(function(){
			location.replace('/oms-web/cp/h5coupon/couponInviteResult?params='+JSON.stringify(urlParams)); 
//			$('#app').show();
		},0);
	}else{
		setTimeout(function(){
			$('#app').show();
			$('body').css({'background':'url('+ $('#ctxStatic').val() +'/admin/images/couponnew/opl.png?v=1) no-repeat','background-size':'cover'});
		},500);
	}
}

function wxConfig(conf){
	var getParams = JSON.parse(getQueryString('params') || '{}');
	
	var url = '/oms-web/cp/h5coupon/couponJsconfig';
	var params = {
		merchantCode:getParams.merchantNo || '',
		url:location.href,
	};
	
	$.post(url,params,function(data){
		var data = data || {};
		wx.config({
//			debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			appId: data.appId, // 必填，公众号的唯一标识
			timestamp:data.timestamp , // 必填，生成签名的时间戳
			nonceStr: data.nonceStr, // 必填，生成签名的随机串
			signature: data.signature,// 必填，签名，见附录1
			jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ','onMenuShareWeibo','onMenuShareWeibo','onMenuShareQZone'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		});
		
		var imgUrl = '';
		var logo = $('.logo img').attr('src');
		if(logo == ''){
			imgUrl = 'http://diyun.lingjukeji.com/app/logo/jukeimlogo.png';
		}else{
			imgUrl = location.origin + logo;
		}
		
		var config = {
			title: $('title').text(), // 分享标题
		    desc: '省钱、优惠是王道', // 分享描述
		    link: location.origin + conf.link, // 分享链接
		    imgUrl: imgUrl, // 分享图标
		    type: '', // 分享类型,music、video或link，不填默认为link
		    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
		};
		wx.ready(function(){
			wx.onMenuShareTimeline({ // 分享到朋友圈
			    title: config.title, // 分享标题
			    link: config.link, // 分享链接
			    imgUrl: config.imgUrl, // 分享图标
			    success:function(res){},
			    cancel:function(res){},
			});	
			
			wx.onMenuShareAppMessage({ // 分享给朋友
			    title: config.title, // 分享标题
			    desc: config.desc, // 分享描述
			    link: config.link, // 分享链接
			    imgUrl: config.imgUrl, // 分享图标
			    type: config.type, // 分享类型,music、video或link，不填默认为link
			    dataUrl: config.dataUrl, // 如果type是music或video，则要提供数据链接，默认为空
			    success:function(res){
			    },
			    cancel:function(res){
			    },
			});
			
			wx.onMenuShareQQ({ // 分享到QQ
			    title: config.title, // 分享标题
			    desc: config.desc, // 分享描述
			    link: config.link, // 分享链接
			    imgUrl: config.imgUrl, // 分享图标
			    success:function(res){},
			    cancel:function(res){},
			});
			
			wx.onMenuShareWeibo({ // 分享到腾讯微博
			    title: config.title, // 分享标题
			    desc: config.desc, // 分享描述
			    link: config.link, // 分享链接
			    imgUrl: config.imgUrl, // 分享图标
			    success:function(res){},
			    cancel:function(res){},
			});
			
			wx.onMenuShareQZone({ // 分享到QQ空间
			    title: config.title, // 分享标题
			    desc: config.desc, // 分享描述
			    link: config.link, // 分享链接
			    imgUrl: config.imgUrl, // 分享图标
			    success:function(res){},
			    cancel:function(res){},
			});
		});
		
		wx.error(function(res){
		});
	},'json');
}

function repalceUrl(){}

try{
	$('#couponRemark').html($('#couponRemark').html().replace(/(\r)?\n/ig,'<br>'));
}catch(e){}