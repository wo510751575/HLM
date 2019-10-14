<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
	<title><sitemesh:title/></title>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<sitemesh:head/>
</head>
<body>
	<sitemesh:body/>
	<script type="text/javascript">
$(document).ready(function(){
  if(window.parent != window){
	  if ( typeof(page_name) == "undefined" && top.document.getElementById("mainFrame")!=null && top.document.getElementById("jbox-iframe")==null) {
// 		  var h=(document.body.clientHeight+310);
		var dW = top.document.body.clientWidth;
		var h = 0;
		if(dW > 995){
			h=(top.document.body.clientHeight - 150);
		}else if(dW > 966 && dW < 995){
			h=(top.document.body.clientHeight - top.document.getElementById('header-navbar').clientHeight -top.document.getElementById('nav-col').clientHeight - 30);
		}else if(dW > 912 && dW < 966){
			h=(top.document.body.clientHeight - top.document.getElementById('header-navbar').clientHeight * 2 -30);
		}else{
			h = (top.document.body.clientHeight - top.document.getElementById('header-navbar').clientHeight - top.document.getElementById('nav-col').clientHeight -30)
		}
		  //var h=(top.document.body.clientHeight - top.document.getElementById('header-navbar').clientHeight - top.document.getElementById('nav-col').clientHeight -30);
		  //var h=(top.document.body.clientHeight - top.document.getElementById('header-navbar').clientHeight - top.document.getElementById('nav-col').clientHeight -30);
		  //alert(top.document.body.clientHeight)
		  //alert(top.document.getElementById('header-navbar').clientHeight)
		  //alert(top.document.getElementById('nav-col').clientHeight)
		  //var h=(top.document.body.clientHeight - 150);
		  /* if(h<900){
			  h=900;
		  } */
		  top.document.getElementById("mainFrame").style.height = h+"px";
	  }
  }
 });
</script>
</body>
</html>