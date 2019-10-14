<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
	    <meta name="viewport" content="width=device-width,initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	    <title>会员福利系统</title>
	    <link rel="stylesheet" href="${ctx}/activity/scanTheLandingPage/css/layout.css" />
	   	<script src="${ctx}/activity/scanTheLandingPage/js/jquery-1.7.2.min.js" type="text/javascript" charset="utf-8"></script>
	   	<script src="${ctx}/activity/scanTheLandingPage/js/wail.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<style>
	.content{background-color: #fff;}
	.content_bg{width: 100%;height: 7.46rem;background: url("${ctx}/activity/scanTheLandingPage/img/bg.jpg") no-repeat;background-size: 100% 100%;padding-top: 0.8rem;box-sizing: border-box;}
	.content_bg .userInfo{width: 1.7rem;height: 1.7rem;border-radius: 50%;background-color: #fff;margin: 0 auto;padding: 0.04rem;box-sizing: border-box;}
	.content_bg .userInfo img{display: block;border-radius: 50%;width: 100%;}
	.content_bg .user_name{font-size: 0.3rem;color: #fff;text-align: center;margin-top: 0.1rem;}
	.text_box{width: 6.9rem;height: 5rem;background: #fff;border-radius: 0.1rem;box-shadow: 0 0.1rem 0.2rem 0 rgba(0,0,0,0.10);
		position: relative;bottom: 3.5rem;margin: 0 auto;padding-top: 0.4rem;box-sizing: border-box;}
	.text_box .text_title p{text-align: center;color: #333;font-size: 0.24rem;line-height: 2;}
	.text_box .text_title p:first-child{color: #000;font-size: 0.36rem;margin-bottom: 0.1rem;}
	.text_box .text_input{width: 6.2rem;margin: 0 auto;margin-top: 0.3rem;border: 1px solid #d8d8d8;border-radius: 22px;height: 0.8rem;line-height: 0.8rem;
								text-indent: 0.5rem;display: block;font-size: 0.3rem;color: #333;}
	.text_box .submit_btn{width: 6.2rem;margin: 0 auto;margin-top: 0.4rem;background-color: #d8d8d8;border-radius: 22px;height: 0.8rem;line-height: 0.8rem;
								text-align: center;display: block;color: #fff;font-size: 0.3rem;}
	.btn_click{background-color: #f43846!important;}
	.btn_disabled{background-color: #d8d8d8!important;}
	.popBox{z-index: 9999;background: rgba(0,0,0,0.7);filter: alpha(opacity=0);width: 100%;height: 100%;position: fixed;left: 0;top: 0;display: none;}
	.popBox .popBg{width: 5.7rem;height: 7.87rem;background: url("${ctx}/activity/scanTheLandingPage/img/hongbaotanchuang.png")no-repeat;background-size: 100% 100%;margin: 0 auto;margin-top: 2.4rem;position: relative;}
	.popBox .popBg .scan{width: 3.4rem;height: 3.4rem;display: inline-block;position: absolute;left: 1.14rem;top: 0.57rem;}
	.close{width: 1rem;height: 1rem;display:block;margin: 0 auto;margin-top: 0.3rem;}
	</style>
	<body>
	<div class="content">
		<div class="content_bg">
			<div class="userInfo">
				<img src="${ctx}/activity/scanTheLandingPage/img/logo1.png" alt="">
			</div>
			<p class="user_name">PLUM</p>
		</div>
		<div class="text_box">
			<div class="text_title">
				<p>成为PLUM会员流程</p>
				<p>1.输入您购买PLUM商品的收货手机号</p>
				<p>2.按提示添加微信领取随机红包及500M豆</p>
			</div><input class="text_input" id="noWx" name="noWx" type="hidden" value='${noWx}'/><input class="text_input" id="noWxShop" value='${noWxShop}' type="hidden"/>
				<input class="text_input" id="phone" type="text" placeholder="请输入手机号码" onfocus="this.placeholder=''" onblur="this.placeholder='请输入手机号码 '">
				<input class="submit_btn" type="button" name="" value="提交" disabled onclick="submitInfo()">
		</div>
	</div>
	<!--<div class="popBox">
		<div class="popBg">
			<img class="scan" src="img/ewm.png" alt="">
		</div>
		<img class="close" src="img/off.png" onclick="closePopBox()">
	</div>-->
	<script type="text/javascript">
		function submitInfo(){
			var phone = $.trim($("#phone").val());
			var noWx = $.trim($("#noWx").val());
			var noWxShop = $.trim($("#noWxShop").val());
			if(!phone.match(/^1[3456789]\d{9}$/)){
				alert("请输入正确的手机号码")
				return false;
			}
			 $.ajax({
			 	url: '${ctx}/h5/selectJudePhone.do',
			 	data:{phone:phone,noWx:noWx,noWxShop:noWxShop},
			 	type: 'post',
			 	dataType: 'json',
			 	error:function(){
			 		alert("网络错误");
			 	},
			 	success:function(data){
			 	//	alert(data.returnObject);
			 		if(data.returnObject.result == true || data.returnObject.result == 'true'){
			 			alert("恭喜您加入PLUM会员，24小时内会发您微信红包和M豆哦，由于领红包人数过多，请您耐心等一下哈 也请持续关注我们的优质内容！");
						 setTimeout(function(){
						   //这个可以关闭安卓系统的手机页面
						   document.addEventListener('WeixinJSBridgeReady', function(){ WeixinJSBridge.call('closeWindow'); }, false);
						   //这个可以关闭ios系统的手机页面
						   WeixinJSBridge.call('closeWindow');
						 }, 500)
			 		}else{
			 			alert(data.returnObject.message)
			 			//alert("您输入的手机号与收货手机号不一致")
			 		}
			 	},
			 })

		};
//		function closePopBox(){
//			$(".popBox").hide()
//			$("#phone").val('')
//			$(".submit_btn").prop("disabled",true)
//			$(".submit_btn").removeClass("btn_click").addClass("btn_disabled")
//		};
		$(function(){
			$("#phone").on("input change",function(){
				var phone = $.trim($("#phone").val());
				if(phone){
					$(".submit_btn").prop("disabled",false)
					$(".submit_btn").removeClass("btn_disabled").addClass("btn_click")
				}else{
					$(".submit_btn").prop("disabled",true)
					$(".submit_btn").removeClass("btn_click").addClass("btn_disabled")
				}
			})
		})

	</script>
	</body>
</html>
