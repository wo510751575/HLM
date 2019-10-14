<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>到店体验客户</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/common/Transferstyle.css" type="text/css" rel="stylesheet" />
	<link href="${ctxStatic}/admin/css/im.css?v=11" type="text/css" rel="stylesheet" />

	<style type="text/css">
		select{
			/*清除select默认样式*/
			appearance:none;
			 -moz-appearance:none;
			 -webkit-appearance:none;
			 -ms-appearance:none;
			 border:1px solid #CCC;
			 width:250px;
			 height:44px;
			 /*在选择框的最右侧中间显示小箭头图片*/
 		 	background: url("http://ourjs.github.io/static/2015/arrow.png") no-repeat scroll right center transparent;
 			/*为下拉小箭头留出一点位置，避免被文字覆盖*/
 			padding-right: 14px;
			/*ie下,原默认的箭头样式还是会显示，所以这里把自定义的样式给去除了*/
		 	background:#fff\9;
		  color:#666;
			padding:8px;
		 	outline:none;
		}
		/*清除ie的默认选择框样式清除，隐藏下拉箭头*/
		select::-ms-expand { display: none; }
		body{background-color: #fff!important;}
		.sureBtn{width: 80px!important;height: 42px;border: none;margin-top: 30px;border-radius: 8px;background-color: #6cc4ac;color: #fff;font-size: 20px;cursor: pointer;}
		.sureBtn:hover{background-color: #69e0c0;color: #f1f1f1;}
		.ty-transfer-btn-toright{background: url('${ctxStatic}/images/right.png') no-repeat center !important;}
		.ty-transfer-btn-toleft{background: url('${ctxStatic}/images/left.png') no-repeat center !important;}
		/* #showContent{width: 100%;height: 100%;background-color: #c3c3c3;position: fixed;left: 0;top: 0;} */
		#showBox{width: 300px;height: 150px;background-color: #f1f1f1;border-radius: 8px;text-align: center;
			position: absolute;left: 50%;top: 50%;margin-left: -150px;margin-top: -75px;display: none;padding-top: 40px;box-sizing: border-box;}
			#showBox p{color: #3e3535;font-size: 22px;}
	</style>
</head>
<body>
	<div class="container">
		<div class="ty-transfer" id="ued-transfer-1">
	<div class=" ty-transfer-list transfer-list-left">
			<div class="ty-transfer-list-head">
				<div class="selectBox">终端：
						<select class="" id="selectShop1">
							<option value="0">--请选择终端--</option>
							<c:forEach items="${list}" var="shop" varStatus="number"> 
							 <option value="${shop.noWx}">${shop.alias}-${shop.nickname}</option>
							</c:forEach>
						</select>
						<select class="" id="selectLeft1">
						</select>
				</div>
			</div>
			<div class="ty-transfer-list-foot">
					<div class="ty-tree-div">
							<div class="tyc-check-blue fl">
									<input type="checkbox" class="transfer-all-check" id="tyc-check-blue">
									<span></span>
							</div>
							<div class="ty-tree-text">
									全选
							</div>
					</div>
			</div>
			<div class="ty-transfer-list-body">
					<ul class="ty-tree-select" id="left-box">

					</ul>
			</div>

	</div>
	<div class="ty-transfer-operation">
			<span class="ty-transfer-btn-toright to-switch"></span>
			<span class="ty-transfer-btn-toleft to-switch"></span>
			<input type="button" value="确定" class="sureBtn" onclick="saveBtn()"/>
	</div>
	<div class="ty-transfer-list transfer-list-right">
			<div class="ty-transfer-list-head">
				<div class="selectBox">
					<select class="" id="selectLeft2">
					</select>
				</div>
			</div>
			<div class="ty-transfer-list-foot">
					<div class="ty-tree-div">
							<div class="tyc-check-blue fl">
									<input type="checkbox" class="transfer-all-check" id="tyc-check-blue">
									<span>
									</span>
							</div>
							<div class="ty-tree-text">
									全选
							</div>
					</div>
			</div>
			<div class="ty-transfer-list-body"  id="mydiv">
					<ul class="ty-tree-select">

					</ul>
			</div>
	</div>
	<div class="clearboth">
	</div>        
</div>
</div>
<div id="showContent">
	<div id="showBox">
		<p>请勾选客户</p>
		<input type="button" value="确定" class="sureBtn" onclick="hideBox()"/>
	</div>
</div>
	<script src="${ctxStatic}/admin/js/jquery.js?v=11" charset="utf-8"></script>
	<script src="${ctxStatic}/common/Transfer.js?v=11" charset="utf-8"></script>
	<script type="text/javascript">
	//穿梭框
		$("#ued-transfer-1").transferItem();

	var hasMemberArr =[]  			//客户数组
	var checkedLeftArr = []		// 左边选择的客户
	var checkedRightArr =[]		//右边选择的客户
	//获取导购列表
	$(function(){

	});

	//确定
	function saveBtn(){
		
		var checkedList = $('.memberNosBox:checked');
		var items = []	//勾选的数据
        var str = "";
		$.each(checkedList,function(){
				var item = JSON.parse($(this).attr('data-item'));
				items.push(item);
		});
		var str = items.join(",");
		var sourceGmNo=$("#selectLeft1").val();
		var gmName = $("#selectLeft2 option:selected").text();
		var newGmNo=$("#selectLeft2").val();
		var noWxGm = $("#selectShop1 option:selected").val();
		
		if(str == "" ){
			alert("请选择要转移的客户");
			return false;
		}
		
		$.ajax({
			type : "POST",
			url : "${ctx}/member/transfer",
			dataType : "text",
			data : {sourceGmNo:sourceGmNo,newGmNo:newGmNo,gmName:gmName,memberNos:str,noWxGm:noWxGm},
			success : function(msg){

                var data=JSON.parse(msg);
                if(data.type==0){
                	alert("转移成功!");
                }
                if(data.type==1){
                	alert(data.msg);
                }
                window.location.reload();
			},error:function(){
			         alert("转移失败","error");
			} } );
		
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
	function hideBox(){
		$("#showBox").hide()
	}
	
	
	//添加选择事项
	$(function(){
		
		$("#selectShop1").change(function(){
			  var sNo=$("#selectShop1").val();
				$.ajax({
					type : "POST",
					url : "${ctx}/member/findGuidMemberByShop",
					dataType : "text",
					data : {noWx:$("#selectShop1").val()},
					success : function(msg){
		
		                var data=JSON.parse(msg);
		                var str="";
		                $("#selectLeft1").empty();
		                $("#selectLeft2").empty();
		                $("#selectLeft1").append("<option value='0'>--请选择导购--</option>");
		                $("#selectLeft2").append("<option value='0'>--请选择导购--</option>");
		                for(var i=0;i<data.length;i++){
		                    $("#selectLeft1").append("<option value='"+data[i].assistantNo+"'>"+data[i].assistantName+"</option>");
		                    $("#selectLeft2").append("<option value='"+data[i].assistantNo+"'>"+data[i].assistantName+"</option>");
		               };
		
					},error:function(){
					         alert("获取数据失败","error");
					} } );
		});
		
		$("#selectLeft1").change(function(){
			
			  var gmNo=$("#selectLeft1").val();
			  var noWxGm = $("#selectShop1").val();
				$.ajax({
					type : "POST",
					url : "${ctx}/member/findMemberByGuid",
					dataType : "text",
					data : {gmNo:gmNo,noWxGm:noWxGm},
					success : function(msg){
		
		                var data=JSON.parse(msg);
		                var str="";
		                $("#left-box").empty();
		                
		            	var html = '';
		    			var checkedAll = $('#selectAllCouponLeft').is(':checked');
		    			$.each(data,function(i){
		    				html += '<li>';
		    				html += '<div class="ty-tree-div">';
		    				html += '<label class="tyue-checkbox-wrapper">';
		    				html += '<span class="tyue-checkbox">';
		    				html += '<input type="checkbox" data-item=\''+ JSON.stringify(data[i].memberNo) +'\' class="tyue-checkbox-input memberNosBox" id="tyue-checkbox-blue">';
		    				html += '<span class="tyue-checkbox-circle"></span>';
		    				html += '</span>';
		    				html += '<span class="tyue-checkbox-txt">'+ data[i].memberName +'</span>';
		    				html += '</label>';
		    				html += '</div>';
		    				html += '</li>';
		    			});
		    			$("#left-box").append(html);
		    			
		    	
			
		
					},error:function(){
					         alert("获取数据失败","error");
					} } );
		
		});
		
      
		
		
	});
	</script>
</body>
</html>
