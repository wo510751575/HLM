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
	    <title>碧生源控股有限公司</title>
	    <link rel="stylesheet" href="${ctx}/activity/showScanCode/css/layout.css" />
	   	<script src="${ctx}/activity/showScanCode/js/jquery-1.7.2.min.js" type="text/javascript" charset="utf-8"></script>
	   	<script src="${ctx}/activity/showScanCode/js/wail.js" type="text/javascript" charset="utf-8"></script>
	   	
<script type="text/javascript">

$(document).ready(function(){ 
	checkCookie();
}); 
	function setCookie(cname,cvalue,exdays){
	    var d = new Date();
	    d.setTime(d.getTime()+(exdays*24*60*60*1000));
	    var expires = "expires="+d.toGMTString();
	    document.cookie = cname+"="+cvalue+"; "+expires;
	}
	function getCookie(cname){
	    var name = cname + "=";
	    var ca = document.cookie.split(';');
	    for(var i=0; i<ca.length; i++) {
	        var c = ca[i].trim();
	        if (c.indexOf(name)==0) { return c.substring(name.length,c.length); }
	    }
	    return "";
	}
	function checkCookie(){
	    var user=getCookie("oldaddress");
	    if (user!=""){
	        alert("欢迎 再次访问");
	       //设置图片
	    	$("#img_a").attr("src", user); 
	    }
	    else {
	        //user = prompt("请输入你的名字:","");
	          user = $('#useraddr').val();
	          if (user=="" && user==null){
	        	  alert('商家未配置二维码');
	          }
	          //设置图片
	          $("#img_a").attr("src", user); 
	          if (user!="" && user!=null){
	        	  
	            setCookie("oldaddress",user,360);
	        }
	    }
	}
	</script>
	</head>
	<style>
	body{background-color: #d55c51;}
	.head{margin-top: 0.2rem;text-align: center;}
	.head p,.bottom p{font-size: 0.38rem;color: #ffc9a1;}
	.text{display: flex;justify-content: space-between;width: 5rem;margin: 0 auto;margin-top: 0.4rem}
	.text span{font-size: 0.5rem;color: #ffc9a1;}
	.text_open{margin: 0 auto;margin-top: 0.7rem;text-align: center;}
	.text_open .outerRing{width: 2.2rem;height: 2.2rem;border-radius: 50%;background-color: #e6c39b;text-align: center;line-height: 2.2rem;margin: 0 auto;align-items: center;padding-top: 0.2rem;box-sizing: border-box;}
	.text_open .innerRing{width: 1.8rem;height: 1.8rem;border-radius: 50%;border: 1px solid #b1886b;margin: 0 auto;line-height: 1.8rem}
	.text_open .innerRing span{font-size: 1.2rem;font-weight: bold;color: #666;}
	.codeBox{width: 4rem;height: 4rem;margin: 0 auto;margin-top: 0.8rem;}
	.codeBox img{width: 100%;height: 100%;display: block;}
	.bottom{position: fixed;bottom: 0.5rem;left: 1.85rem;text-align: center;}
	</style>

	<body>
	<input id="useraddr" type="hidden" value="${code}"/>
	<div class="content">
		<div class="head">
			<p>碧生源控股有限公司</p>
			<p>给你发红包</p>
		</div>
		<div class="text">
				<span>恭喜发财</span><span>大吉大利</span>
		</div>
		<div class="text_open">
				<div class="outerRing">
						<div class="innerRing">
							<span>開</span>
						</div>
				</div>
		</div>
		<div class="codeBox">
			<img src="${code}" id="img_a" alt=""/>
		</div>
		<div class="bottom">
			<p>长按识别二维码领红包</p>
			<p>看看你的手气>></p>
		</div>
	</div>
	<script type="text/javascript">


	</script>
	</body>
</html>
