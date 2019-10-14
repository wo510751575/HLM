<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>首页</title>
<meta name="decorator" content="default" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

	<style type="text/css">
	.section{margin: 30px;}
	.section .head_total{margin-bottom: 20px;}
	.section .head_total .head_text{font-size: 14px;color: #989898;}
	.section .head_total .head_title1{font-size: 18px;color: #000;margin-right: 10px;}
	.section .head_numBox{width: 100%;overflow: hidden;height: 80px;display: flex;}
	.section .head_numBox>div{flex:1;border: 1px solid #d1d1d1;align-items: center;text-align: center;padding-top: 15px;}
	.section .head_numBox>div:nth-child(1),.section .head_numBox>div:nth-child(2),.section .head_numBox>div:nth-child(3){border-right: none;}
	.section .head_numBox .icon{display: inline-block;width: 50px;height: 50px;vertical-align: text-bottom;margin-right: 10px;}
	.section .head_numBox .icon_mendian{background: url("${ctxStatic}/admin/images/mendian.png")no-repeat;background-size: 100% 100%;background-position: center center;;}
	.section .head_numBox .icon_yuangong{background: url("${ctxStatic}/admin/images/yuangong.png")no-repeat;background-size: 100% 100%;background-position: center center;;}
	.section .head_numBox .icon_weixin{background: url("${ctxStatic}/admin/images/weixin.png")no-repeat;background-size: 100% 100%;background-position: center center;;}
	.section .head_numBox .icon_kehu{background: url("${ctxStatic}/admin/images/kehu.png")no-repeat;background-size: 100% 100%;background-position: center center;;}
	.section .head_numBox .icon_right{display: inline-block;text-align: left;}
	.section .head_numBox .icon_right p{font-size: 16px;}
	.section .head_numBox .icon_right>p:nth-child(1){font-weight: bold;font-size: 22px;}
	.section .head_numBox .icon_right>p:nth-child(2){font-weight: 550;}
	.parts-container,.parts-container * {box-sizing: border-box;overflow: hidden;}
	.parts-container {margin: 0 0 20px 0;color: #666;padding: 20px 0 10px 20px;border:1px solid #d1d1d1;}
	.parts-container .default-panel {display: inline-block;background: #fff;vertical-align: top;overflow: hidden}
	.parts02 .default-panel .panel-body {height: 300px;}
	.parts-container .default-panel {display: inline-block;background: #fff;vertical-align: top;overflow: hidden}
	.parts01 .default-panel {height: 170px}
	.parts02 .default-panel .panel-body {height: 300px}
	.parts03{padding-bottom: 30px;}
	.parts03_head{margin-bottom:100px;}
	.parts03 .default-panel .panel-body {height: 355px}
	.parts03 span{padding: 5px 5px;display: inline-block;font-size: 16px;margin-right: 20px;}
	.parts03 .actived{color: #60bd00;border-bottom:1px solid #60bd00;}
	.parts03 .box1 .addTableBox{display: flex;}
	.parts03 .box1 .addTableBox .box_href,.safeTableBox .box_href{width: 50%;border: 1px solid #d1d1d1;}

	.parts03 .box1 .box_href .table_top{border-bottom: 1px solid #d1d1d1;display: flex;justify-content: space-between;height: 40px;line-height: 40px;}
	.parts03 .box1 .box_href .table_top span{color: #333;font-size: 14px;}
 	#downTable1:hover,#downTable2:hover{cursor: pointer;color: #1236f1}
	.parts03 .box1 .box_href table{width: 100%;box-sizing: border-box;}
	.parts03 .box1 .box_href table thead{color: #333;padding: 0 5px;}
	.parts03 .box1 .box_href table tbody{padding: 0 5px;}
	.parts03 .box1 .box_href table tr{border-bottom: 1px solid #d1d1d1;height: 40px;line-height: 40px;}
	.parts03 .box1 .box_href table td{text-align: center;}


	.parts03 .box1 .safeTableBox{display: none;}
	</style>
</head>

<body>
	<input type="hidden" id="ctxStatic" value="${ctxStatic }" />
<div class="section">
	<div style="margin-bottom:20px;">
		<div class="head_total">
			<div class="">
				<p class="head_text"><span class="head_title1">运营概览</span><span>最新统计时间：</span><span><fmt:formatDate value="${time }" pattern="yyyy-MM-dd" /></span></p>
			</div>
		</div>
		<div class="head_numBox">
			<%-- <div class="">
				<i class="icon_mendian icon"></i>
				<div class="icon_right">
					<p id="shopCount">${shopCount }</p>
					<p>门诊</p>
				</div>
			</div> --%>
			<div class="">
				<i class="icon_yuangong icon"></i>
				<div class="icon_right">
					<p id="guidCount">${ guidCount}</p>
					<p>员工</p>
				</div>
			</div>
			<div class="">
				<i class="icon_weixin icon"></i>
				<div class="icon_right">
					<p id="weixin">${shopTerminalCount }</p>
					<p>微信</p>
				</div>
			</div>
			<div class="">
				<i class="icon_kehu icon"></i>
				<div class="icon_right">
					<p id="customer">${addFriendsCount }</p>
					<p>客户</p>
				</div>
			</div>
		</div>
	</div>

	<div class="">
		<div class="head_total">
			<div class="">
				<p class="head_text"><span class="head_title1">新增客户量</span></p>
			</div>
		</div>
		<div class="parts-container parts02">
		
		    <div class="control-group">
	                <label class="control-label">选择终端:</label>
	                <div class="controls">
	                     <input type="hidden" id="wxNos"/>
		                 <input type="hidden" id="tidCodes" name="tidCodes"  maxlength="127" required="required" class="required input-xxlarge"/>
		                 <span class="spanSelTipCods">
		                 	<!-- <a>xxxxx</a> &nbsp; <a>xxxxx</a> -->
		                 </span>
		                 <span style="padding-left:5px;"><input id="btnTerminal" class="btn btn-primary" type="button" value="选择" /></span>
	                </div>
	        </div>
			<select id="chartSelect" name="">
				<option value="interval30">最近30天</option>
				<option value="interval7">最近7天</option>		
				<option value="00">自定义时间</option>
			</select>
			<input type="text" id="date1" style="display:none;height:30px;" placeholder="请选择时间">
			<div class="default-panel" style="width: 100%;">
				<div class="panel-body " style="padding: 10px 0 0 10px">
					<div id="coupon-info" style="width: 100%; height: 250px; display: inline-block; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; position: relative; background-color: transparent;">
					</div>
				</div>
			</div>
		</div>
	</div>
		
	<div class="parts03">
		<div class="parts03_head">
			<div class="tabBox1">
				<span class="actived">新增客户排行榜</span>
				<span>维护客户排行榜</span>
				<select id="tableSelect" name="">
				<option value="intervalAll">历史</option>
				<option value="interval1">昨天</option>
					<option value="interval7">最近7天</option>
					<option value="interval30">最近30天</option>
					<option value="00">自定义时间</option>
				</select>
				<input type="text" id="date2" style="display:none;" placeholder="请选择时间">
			</div>
			<div class="box1">
				<div class="addTableBox">
					<div class="box_href" style="border-right:none;">
						<div class="table_top">
							<span>门诊新增客户&nbsp;&nbsp;TOP10</span>
							<span id="downTable1" title="下载表格"><a href="javascript:exportExcel(1)">下载表格</a></span>
						</div>

						<table id="addTable1">
							<thead>
								<tr>
									<th>排名</th>
									<th>终端微信</th>
									<th>新增客户量</th>
								</tr>
							</thead>
							<tbody id="tbody1">

							<c:forEach items="${addlist}" var="addFriends" varStatus="number">
							<tr>
							<td>${number.index + 1}</td>
							<td>${addFriends.noWx }</td>
							<td>${addFriends.count }</td>
							</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>

					<div class="box_href">
						<div class="table_top">
							<span>导购新增客户&nbsp;&nbsp;TOP10</span>
							<span id="downTable2" title="下载表格"><a href="javascript:exportExcel(3)">下载表格</a></span>
						</div>
						<table id="addTable2">
							<thead>
								<tr>
									<th>排名</th>
									<th>导购名称</th>
									<th>新增客户量</th>
								</tr>
							</thead>
							<tbody id="tbody2">
							<c:forEach items="${gmAddlist}" var="addFriends" varStatus="number">
							<tr>
							<td>${number.index + 1}</td>
							<td>${addFriends.memberGmName }</td>
							<td>${addFriends.count }</td>
							</tr>
							</c:forEach>
							</tbody>
							</tbody>
						</table>
					</div>
				</div>
				<%--  --%>
				<div class="safeTableBox">
					<div style="display:flex;">
					<div class="box_href" style="border-right:none;">
						<div class="table_top">
							<span>门诊维护客户&nbsp;&nbsp;TOP10</span>
							<span id="downTable3" title="下载表格"><a href="javascript:exportExcel(2)">下载表格</a></span>
						</div>

						<table id="addTable3">
							<thead>
								<tr>
									<th>排名</th>
									<th>终端微信</th>
									<th>维护客户量</th>
								</tr>
							</thead>
							<tbody id="tbody3">
							<c:forEach items="${servicelist}" var="addFriends" varStatus="number">
							<tr>
							<td>${number.index + 1}</td>
							<td>${addFriends.noWx }</td>
							<td>${addFriends.count }</td>
							</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>

					<div class="box_href">
						<div class="table_top">
							<span>导购维护客户&nbsp;&nbsp;TOP10</span>
							<span id="downTable4" title="下载表格"><a href="javascript:exportExcel(4)">下载表格</a></span>
						</div>
						<table id="addTable4">
							<thead>
								<tr>
									<th>排名</th>
									<th>导购名称</th>
									<th>维护客户量</th>
								</tr>
							</thead>
							<tbody id="tbody4">
							<c:forEach items="${gmServicelist}" var="addFriends" varStatus="number">
							<tr>
							<td>${number.index + 1}</td>
							<td>${addFriends.memberGmName }</td>
							<td>${addFriends.count }</td>
							</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			</div>
		</div>
	</div>


</div>
<script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
<script type="text/javascript" src="${ctxStatic}/admin/js/index/jquery.table2excel.js"></script>
<script type="text/javascript" src="${ctxStatic}/admin/js/index/laydate.js"></script>
<script type="text/javascript">


			var arrDays = []
			var arrNums = []
		//7天
		var sevenDays=[];
		var sevenNums=[]
		// 最近30天
		var thirtyDays = []
		var thirtyNums = []
		//自定义时间
		var userDefinedDays = []
		var userDefinedNums = []
		var g = echarts.init(document.getElementById("coupon-info"));
		$(document).ready(function() {									
				 $.ajax({
						url : "${ctx}/st/countAddFriends/getGroupByTime",//请求的url地址Integer pro_id,String LandedUuid
						dataType : "json", //返回格式为json
						async : false, //请求是否异步，默认为异步，这也是ajax重要特性
						//contentType : "application/x-www-form-urlencoded; charset=UTF-8",
						data : {intervalTime:'interval30'},
						type : "POST", //请求方式
						success : function(data){							
							console.log(data)							
							arrDays = data.time
							arrNums = data.counts
							var yesterday = {
								title : {
									text : '新增客户量',
									textStyle : {
										color : "#3C8DBC",
										fontSize : 14,
										fontWeight : "normal"
									}
								},
								borderWidth : 1,
								color : [ "#3C8DBC"],//曲线颜色
								tooltip : {
									trigger : "axis"
								},								
								grid : {
									left : "0",
									right : "50px",
									bottom : "10px",
									itemStyle : {
										normal : {
											shadowColor : "rgba(0, 0, 0, 0.5)",
											shadowBlur : 10
										}
									},
									containLabel : true
								},
								toolbox : {
									right:"20px",
							   			feature : {
							   				saveAsImage : {
													show:true,
													pixelRatio: 1,
													title:"保存为图片",
													type: "jpeg",
													lang:['点击保存']
												}
							   			}
								},
								xAxis : [{//X轴设置
									type : "category",
									boundaryGap : false,
									axisLine : {
										lineStyle : {
											color : "#81d0fd"
										}
									},
									splitLine : {
										lineStyle : {
											color : "#f6f6f6"
										}
									},
									data : arrDays
								}],
								yAxis : [ {//Y轴设置
									type : "value",
									axisLine : {
										lineStyle : {
											color : "#81d0fd"
										}
									},
									splitLine : {
										lineStyle : {
											color : "#f6f6f6"
										}
									}
								} ],
								series: [
										{//图表样式
												 name: "按小时",
												 type: "line",
												 smooth: true,	//平滑曲线
												 data: arrNums
										 }				
									 ]
							};
							g.setOption(yesterday);	

						}
					})
			      
					
					
					
			//曲线图		
		    $('#chartSelect').on('change', function() {
		    	var val = $(this).val();
		 		chartSelect(val);
		    });
		    
				$('#tableSelect').on('change', function() {
					 var val = $(this).val();
					 if(val == 00){//自定义
						 $("#date2").show()
					 }else{
						 $("#date2").hide()						 
						 //	_ajax(val)
					 }					 					
					 var params={};
					 if(val == 'intervalAll'){
						 
						    params["intervalTime"] = "intervalAll";
					 }
					 if(val == 'interval1'){
						 
						    params["intervalTime"] = "interval1";
					 }
					 if(val == "interval7"){
					    params["intervalTime"] = "interval7";
					 }
					 if(val == 'interval30'){
						    params["intervalTime"] = "interval30";
					 }
					  if(val == "00"){
						  return false;
						    params["startTime"] = "";
						    params["endTime"] = "";
					} 
					 					
					 $.ajax({
							url : "${ctx}/st/countAddFriends/getAddFriendOrder",//请求的url地址Integer pro_id,String LandedUuid
							dataType : "json", //返回格式为json
							async : false, //请求是否异步，默认为异步，这也是ajax重要特性
							//contentType : "application/x-www-form-urlencoded; charset=UTF-8",
							data : params,
							type : "POST", //请求方式
							success : function(data){
							       console.log(data);
							       $("#tbody1").html("")
							       $("#tbody2").html("")
							       $("#tbody3").html("")
							       $("#tbody4").html("")
							       
							       var aa1 = data.shopAddlist;
							       var html1 = '';
									$.each(aa1,function(i){
										html1 += "<tr>";
										html1 += "<td>"+(i+1)+"</td>";
										html1 += "<td>"+aa1[i].noWx+"</td>";
										html1 += "<td>"+aa1[i].count+"</td>";
										html1 += "</tr>";
									})
									$("#tbody1").append(html1)
									
									var aa2 = data.gmAddlist
									var html2 = '';
									$.each(aa2,function(i){
										html2 += "<tr>";
										html2 += "<td>"+(i+1)+"</td>";
										html2 += "<td>"+aa2[i].memberGmName+"</td>";
										html2 += "<td>"+aa2[i].count+"</td>";
										html2 += "</tr>";
									})									
									$("#tbody2").append(html2) 
									
									var aa3 = data.shopServicelist
									var html3 = '';
									$.each(aa3,function(i){
										html3 += "<tr>";
										html3 += "<td>"+(i+1)+"</td>";
										html3 += "<td>"+aa3[i].noWx+"</td>";
										html3 += "<td>"+aa3[i].count+"</td>";
										html3 += "</tr>";
									})									
									$("#tbody3").append(html3)
									
									var aa4 = data.gmServicelist
									var html4 = '';
									$.each(aa4,function(i){
										html4 += "<tr>";
										html4 += "<td>"+(i+1)+"</td>";
										html4 += "<td>"+aa4[i].memberGmName+"</td>";
										html4 += "<td>"+aa4[i].count+"</td>";
										html4 += "</tr>";
									})									
									$("#tbody4").append(html4)
							}
						})

			 });
					
				
				
			 laydate.render({
				 elem: '#date1',
				 position: 'fixed',
				 range: true,		//选择时间段
				 theme: '#393D49', //主题颜色
				 showBottom: true,//显示底部栏
				 done: function(value, date){
					 var startTime = value.substring(0,10)//开始日期
					 var endTime = value.substring(13)//结束日期
					 getOneData(2,2,startTime,endTime)
					 // if(startTime == endTime){
						//  console.log(111)
					 // }      
						var yesterday = {
							title : {
								text : '新增客户量',
								textStyle : {
									color : "#3C8DBC",
									fontSize : 14,
									fontWeight : "normal"
								}
							},
							borderWidth : 1,
							color : [ "#3C8DBC"],//曲线颜色
							tooltip : {
								trigger : "axis"
							},								
							grid : {
								left : "0",
								right : "50px",
								bottom : "10px",
								itemStyle : {
									normal : {
										shadowColor : "rgba(0, 0, 0, 0.5)",
										shadowBlur : 10
									}
								},
								containLabel : true
							},
							toolbox : {
								right:"20px",
						   			feature : {
						   				saveAsImage : {
												show:true,
												pixelRatio: 1,
												title:"保存为图片",
												type: "jpeg",
												lang:['点击保存']
											}
						   			}
							},
							xAxis : [{//X轴设置
								type : "category",
								boundaryGap : false,
								axisLine : {
									lineStyle : {
										color : "#81d0fd"
									}
								},
								splitLine : {
									lineStyle : {
										color : "#f6f6f6"
									}
								},
								data : arrDays
							}],
							yAxis : [ {//Y轴设置
								type : "value",
								axisLine : {
									lineStyle : {
										color : "#81d0fd"
									}
								},
								splitLine : {
									lineStyle : {
										color : "#f6f6f6"
									}
								}
							} ],
							series: [
									{//图表样式
											 name: "按小时",
											 type: "line",
											 smooth: true,	//平滑曲线
											 data: arrNums
									 }				
								 ]
						};
						g.setOption(yesterday);	
					 }
			 });
				laydate.render({
				  elem: '#date2',
				  position: 'fixed',
					range: true,		//选择时间段
					theme: '#393D49', //主题颜色
					showBottom: true,//隐藏底部栏
					done: function(value, date){
						 var startTime = value.substring(0,10)//开始日期
						 var endTime = value.substring(13)//结束日期
						 
						$.ajax({
							url : "${ctx}/st/countAddFriends/getAddFriendOrder",//请求的url地址Integer pro_id,String LandedUuid
							dataType : "json", //返回格式为json
							async : false, //请求是否异步，默认为异步，这也是ajax重要特性
							//contentType : "application/x-www-form-urlencoded; charset=UTF-8",
							data : {startTime: startTime,endTime:endTime,},
							type : "POST", //请求方式
							success : function(data){
							       console.log(data);
							       $("#tbody1").html("")
							       $("#tbody2").html("")
							       $("#tbody3").html("")
							       $("#tbody4").html("")
							       
							       var aa1 = data.shopAddlist;
							       var html1 = '';
									$.each(aa1,function(i){
										html1 += "<tr>";
										html1 += "<td>"+(i+1)+"</td>";
										html1 += "<td>"+aa1[i].noWx+"</td>";
										html1 += "<td>"+aa1[i].count+"</td>";
										html1 += "</tr>";
									})
									$("#tbody1").append(html1)
									
									var aa2 = data.gmAddlist;
									var html2 = '';
									$.each(aa2,function(i){
										html2 += "<tr>";
										html2 += "<td>"+(i+1)+"</td>";
										html2 += "<td>"+aa2[i].memberGmName+"</td>";
										html2 += "<td>"+aa2[i].count+"</td>";
										html2 += "</tr>";
									})									
									$("#tbody2").append(html2) 
									
									var aa3 = data.shopServicelist;
									var html3 = '';
									$.each(aa3,function(i){
										html3 += "<tr>";
										html3 += "<td>"+(i+1)+"</td>";
										html3 += "<td>"+aa3[i].noWx+"</td>";
										html3 += "<td>"+aa3[i].count+"</td>";
										html3 += "</tr>";
									})									
									$("#tbody3").append(html3)
									
									var aa4 = data.gmServicelist;
									var html4 = '';
									$.each(aa4,function(i){
										html4 += "<tr>";
										html4 += "<td>"+(i+1)+"</td>";
										html4 += "<td>"+aa4[i].memberGmName+"</td>";
										html4 += "<td>"+aa4[i].count+"</td>";
										html4 += "</tr>";
									})									
									$("#tbody4").append(html4);
									
							}
						})
					  }
				});
				
				
				
				//选择终端
				$('#btnTerminal').click(function() {
					window.localStorage.setItem('info', $('#tidCodes').val());
			
					var url = "iframe:${ctx}/member/gmAssistantShop/toSelectShopTerminal";
		
					// 正常打开	
					top.$.jBox.open(url, "选择终端", 880, 560,{//宽高
						id:9527,
						draggable: true,
						showClose: true,
						buttons:{"确定":"ok", "关闭":true}, submit:function(v, h, f){
			                if (v=="ok"){
			                	var win = h.find('iframe')[0].contentWindow
			                	var form=win.document.getElementById('submitForm');
			                	//$("#btnSubmit").attr("disabled","disabled");form.submit()
			                	win.submitAjax();
			                	var info = window.localStorage.getItem('info');
								console.log(info);
								if(null==info || info=='') {
									//top.$.jBox.info('请选择');
									return false;
								}
			                	$('#tidCodes').val(info);
								var html = '';
								var arr = info.split(',');
								var wxNos = "";
								for(var i=0; i<arr.length; i++) {
									var childArr = arr[i].split('|');
									html += '<span style="color:#23b7e5">'+childArr[1]+'</span><br/>';
									if(i == 0){
										wxNos = childArr[1];
									}else{
										wxNos =  wxNos +"," +childArr[1];
									}
									

								}
								$('.spanSelTipCods').html(html);
								$('#wxNos').val(wxNos);
								var chatselectVal = $('#chartSelect').val();
								chartSelect(chatselectVal);
			                } 
			            }, 
						iframeScrolling: 'no',
						loaded:function(h){
							top.$('.jbox-container .jbox-title-panel').css("height","35px").css('background','#376EA5');
							top.$('.jbox-container .jbox-title').css('line-height','35px').css("color","#ffffff");
						},
						closed: function () { 
							//window.location.reload();
						} // 信息关闭后执行的函数
					});
			    });

		});

		
		
 		 //获取 昨天 7天 30天数据
		
		//切换表格
		$(".tabBox1 span").click(function(){
				var index = $(this).index();
				$(".box1").children("div").eq(index).show().siblings().hide();
				$(this).addClass("actived").siblings().removeClass("actived")
		})
	
			 function getOneData(dateCount,dateType,startTime,endTime){
				  var params = {};
				 if(dateType == 1){
					 params.intervalTime = dateCount;
					 params.startTime = '';
					 params.endTime = '';
				 }
				 if(dateType == 2){
					 params.intervalTime = '';
					 params.startTime = startTime;
					 params.endTime = endTime;
				 }
                 params.noWx=$("#wxNos").val();
				 $.ajax({
						url : "${ctx}/st/countAddFriends/getGroupByTime",//请求的url地址Integer pro_id,String LandedUuid
						dataType : "json", //返回格式为json
						async : false, //请求是否异步，默认为异步，这也是ajax重要特性
						//contentType : "application/x-www-form-urlencoded; charset=UTF-8",
						data : params,
						type : "POST", //请求方式
						success : function(data){
							console.log(data)
							arrDays = []
							arrNums = []
							arrDays = data.time
							arrNums = data.counts
						
						}
					})
			 } 
 		 
 		 
 		 //曲线图var val = $(this).val();
 		 function chartSelect(val){

			if(val == 00){//自定义
				$("#date1").show()

			}else if(val == "interval7"){	//昨天
			  		getOneData(val,1,3,3)//	获取7天数据
					console.log(arrDays)
					console.log(arrNums)
					$("#date1").hide()
					g.clear();//清空画布 再重新渲染数据
					var sevenDaysCharts = {
						title : {
							text : '新增客户量',
							textStyle : {
								color : "#3C8DBC",
								fontSize : 14,
								fontWeight : "normal"
							}
						},
						borderWidth : 1,
						color : [ "#3C8DBC" ],//曲线颜色
						tooltip : {
							trigger : "axis"
						},									
						grid : {
							left : "0",
							right : "50px",
							bottom : "10px",
							itemStyle : {
								normal : {
									shadowColor : "rgba(0, 0, 0, 0.5)",
									shadowBlur : 10
								}
							},
							containLabel : true
						},
						toolbox : {
							right:"20px",
									feature : {
										saveAsImage : {
											show:true,
											pixelRatio: 1,
											title:"保存为图片",
											type: "jpeg",
											lang:['点击保存']
										}
									}
						},
						xAxis : [{//X轴设置
							type : "category",
							boundaryGap : false,
							axisLine : {
								lineStyle : {
									color : "#81d0fd"
								}
							},
							splitLine : {
								lineStyle : {
									color : "#f6f6f6"
								}
							},
							data : arrDays
						}],
						yAxis : [ {//Y轴设置
							type : "value",
							axisLine : {
								lineStyle : {
									color : "#81d0fd"
								}
							},
							splitLine : {
								lineStyle : {
									color : "#f6f6f6"
								}
							}
						} ],
						series: [
								{//图表样式
										 name: "新增客户量",
										 type: "line",
										 smooth: true,	//平滑曲线
										 data: arrNums
								 },
							 ]
					};
				g.setOption(sevenDaysCharts)
			}else{		//30天
				$("#date1").hide()
				getOneData(val,1,3,3)//获取30天数据
				g.clear();
				var thirtyDaysCharts = {
					title : {
						text : '新增客户量',
						textStyle : {
							color : "#3C8DBC",
							fontSize : 14,
							fontWeight : "normal"
						}
					},
					borderWidth : 1,
					color : [ "#3C8DBC" ],//曲线颜色
					tooltip : {
						trigger : "axis"
					},								
					grid : {
						left : "0",
						right : "50px",
						bottom : "10px",
						itemStyle : {
							normal : {
								shadowColor : "rgba(0, 0, 0, 0.5)",
								shadowBlur : 10
							}
						},
						containLabel : true
					},
					toolbox : {
						right:"20px",
				   			feature : {
				   				saveAsImage : {
										show:true,
										pixelRatio: 1,
										title:"保存为图片",
										type: "jpeg",
										lang:['点击保存']
									}
				   			}
					},
					xAxis : [{//X轴设置
						type : "category",
						boundaryGap : false,
						axisLine : {
							lineStyle : {
								color : "#81d0fd"
							}
						},
						splitLine : {
							lineStyle : {
								color : "#f6f6f6"
							}
						},
						data : arrDays
					}],
					yAxis : [ {//Y轴设置
						type : "value",
						axisLine : {
							lineStyle : {
								color : "#81d0fd"
							}
						},
						splitLine : {
							lineStyle : {
								color : "#f6f6f6"
							}
						}
					} ],
					series: [
							{//图表样式
									 name: "新增客户量",
									 type: "line",
									 smooth: true,	//平滑曲线
									 data: arrNums
							 },
						 ]
				};
				g.setOption(thirtyDaysCharts);
			//	_ajax(val)
			}
 		 }
 		 
 		 function exportExcel(type){
 			 
 			
 			 var val =$("#tableSelect").val();
 			
			  if(val == "00"){
				  var value = $("#date2").val();
				  var startTime = value.substring(0,10)//开始日期
					 var endTime = value.substring(13)//结束日期
				  window.location.href="${ctx}/st/countAddFriends/exportExcel?intervalTime="+val+"&type="+type+"&startTime="+startTime+"&endTime="+endTime;
				  return true;
			  }
			  window.location.href="${ctx}/st/countAddFriends/exportExcel?intervalTime="+val+"&type="+type;
 			 
 		 }

</script>
</body>
</html>
