<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>标签选择</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/jquery/jquery.form.js" type="text/javascript"></script>

	<style type="text/css">
		.img-circle {
    width: 40px;
    height: 40px;
    border-radius: 20px;
    vertical-align: middle;
}
	.container{
	padding:0 10px 70px 10px;
    width: 100%;
    min-height: 800px;
    background: #fff;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
	}
		#myModal{
		position: fixed;
	    top: 10%;
	    left: 50%;
	    z-index: 1050;
	    width: 560px;
	    margin-left: -280px;
	    background-color: #fff;
	    border: 1px solid #999;
	    border: 1px solid rgba(0,0,0,0.3);
	    -webkit-border-radius: 6px;
	    -moz-border-radius: 6px;
	    border-radius: 6px;
	    outline: 0;
	    -webkit-box-shadow: 0 3px 7px rgba(0,0,0,0.3);
	    -moz-box-shadow: 0 3px 7px rgba(0,0,0,0.3);
	    box-shadow: 0 3px 7px rgba(0,0,0,0.3);
	    -webkit-background-clip: padding-box;
	    -moz-background-clip: padding-box;
	    background-clip: padding-box;
		}
		
		.container .tabBox{
			width:100%;
		}
		.container .tabBox ul{
			list-style:none;
		}
		.container .tabBox ul li{
			height:30px;
			line-height:30px;
			text-align:center;
			padding:0 10px;
			border:1px #666666 solid;
			border-radius:5px;
			margin: 10px 15px;
			float:left;
			cursor: pointer;
		}
		.container .tabBox ul li.active{
			background: #22b6e4;
			color: #ffffff;
		}
		
		.container .cancelBox{
			position: absolute;
			left:0;
			bottom:0;
			background:#ffffff;
			display:flex;
			justify-content: center;
    		align-items: center;
			width:100%;
			height:50px;
			line-height:50px;
			border-top:1px #bdb1b1 solid;
		}
		
		.container .cancelBox span{
			display:inline-block;
			width:60px;
			height:24px;
			line-height: 24px;
			text-align:center;
			border:1px #666666 solid;
			border-radius:5px;
			margin:0 10px;
			cursor: default;
		}
		
	</style>
</head>
<body>
<div class="container">
	
	<form id="batchChangePmTypeForm" method="post" >
	</form>
	<tags:message content="${message}"/>
	<!-- labels:商户下的所有标签,selectedLabels:需要高亮显示的{code:"",labelName:""} -->
	<div class="tabBox" id="tabBox">
		<ul>
			<c:forEach items="${pmTypeList }" var="pmType">
				<li data-code="${pmType.code }">${pmType.typeName }</li>
			</c:forEach>
		</ul>
	</div>
	
	<div class="cancelBox">
		<span onclick="top.$.jBox.close(true);">取消</span>
		<span onclick="submitBtn()">确定</span>
	</div>
</div>
<script type="text/javascript">
function submitBtn(){
	var pmTypeCode = $('#tabBox .active').attr('data-code');//选中的分组code
	var pmCodes = '${pmCodes}';
    
    $("#batchChangePmTypeForm").attr("action","${ctx}/member/batchChangePmType?merchantNo=${merchantNo}&pmCodes="+pmCodes+"&pmTypeCode="+pmTypeCode);
    $("#batchChangePmTypeForm").ajaxSubmit(function(option){
        window.parent.frames[0].location.href="${ctx}/member/list/";//刷新指定iframe
        setTimeout(function(){
			top.$.jBox.tip(option.msg, option.type==1?"error":"info", {opacity:0,timeout:2000});//提示信息
		},2000);
		setTimeout(function(){
			top.$.jBox.close(true);//关闭窗口
		},500);
    });
}



$(document).ready(function() {
    //选择分组
    $('.tabBox').on('click','li',function () {
    	if(!$(this).hasClass('active')) {
    		$('.tabBox .active').each(function(index, obj){
    			$(obj).toggleClass('active');
            });
    		$(this).toggleClass('active');
    	}
    });
});
</script>
</body>
</html>