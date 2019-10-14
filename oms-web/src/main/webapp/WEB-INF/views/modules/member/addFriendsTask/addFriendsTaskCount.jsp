<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>任务统计</title>
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
	.section .head_numBox .icon_mendian{background: url("${ctxStatic}/admin/images/mobile.png")no-repeat;background-size: 100% 100%;background-position: center center;;}
	.section .head_numBox .icon_yuangong{background: url("${ctxStatic}/admin/images/zhixing.png")no-repeat;background-size: 100% 100%;background-position: center center;;}
	.section .head_numBox .icon_weixin{background: url("${ctxStatic}/admin/images/yijia.png")no-repeat;background-size: 100% 100%;background-position: center center;;}
	.section .head_numBox .icon_kehu{background: url("${ctxStatic}/admin/images/zhuanhua.png")no-repeat;background-size: 100% 100%;background-position: center center;;}
	.section .head_numBox .icon_renwu{background: url("${ctxStatic}/admin/images/weixin.png")no-repeat;background-size: 100% 100%;background-position: center center;;}

	.section .head_numBox .icon_right{display: inline-block;text-align: left;}
	.section .head_numBox .icon_right p{font-size: 16px;}
	.section .head_numBox .icon_right>p:nth-child(1){font-weight: bold;font-size: 22px;}
	.section .head_numBox .icon_right>p:nth-child(2){font-weight: 550;}
	.parts01 .default-panel {height: 170px}
	.parts03{padding-bottom: 30px;}
	.parts03_head{margin-bottom:100px;}
	.parts03 .default-panel .panel-body {height: 355px}
	.parts03 span{padding: 5px 5px;display: inline-block;font-size: 16px;margin-right: 20px;}
	.parts03 .actived{color: #60bd00;border-bottom:1px solid #60bd00;}
	.parts03 .box1 .addTableBox .box_href{width: 100%;border: 1px solid #d1d1d1;}
	.parts03 .box1 .box_href .table_top{border-bottom: 1px solid #d1d1d1;display: flex;justify-content: space-between;height: 40px;line-height: 40px;}
	.parts03 .box1 .box_href .table_top span{color: #333;font-size: 14px;}
 	#downTable1:hover{cursor: pointer;color: #1236f1}
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
			<div class="">
				<i class="icon_mendian icon"></i>
				<div class="icon_right">
					<p id="shopCount">${detail.totalMobile }</p>
					<p>手机号码总量</p>
				</div>
			</div>
			<div class="">
				<i class="icon_yuangong icon"></i>
				<div class="icon_right">
					<p id="guidCount">${ detail.totalAlreadyExcMobile}</p>
					<p>已执行手机号码</p>
				</div>
			</div>
			<div class="">
				<i class="icon_weixin icon"></i>
				<div class="icon_right">
					<p id="weixin">${detail.successMobile }</p>
					<p>已加微信</p>
				</div>
			</div>
			<div class="">
				<i class="icon_kehu icon"></i>
				<div class="icon_right">
					<p id="customer">${detail.trans }%</p>
					<p>转化率</p>
				</div>
			</div>
			<div class="">
				<i class="icon_renwu icon"></i>
				<div class="icon_right">
					<p id="customer">${detail.wxGmCount }</p>
					<p>任务微信量</p>
				</div>
			</div>
		</div>
	</div>

	<div class="parts03">
		<div class="parts03_head">
			<div class="tabBox1">
				<span class="actived">任务排行榜</span>
			<!--<select id="tableSelect" name="">
				<option value="intervalAll">历史</option>
				<option value="interval1">昨天</option>
					<option value="interval7">最近7天</option>
					<option value="interval30">最近30天</option>
					<option value="00">自定义时间</option>
				</select> 
				<span id="date2" style="display:none;">
					<input id="startTime" name="startTime" type="text" style="width:130px;"   readonly="readonly" class="input-mini Wdate"
					value="<fmt:formatDate value="${paramManager.startTime}" pattern="yyyy-MM-dd"/>" placeholder="开始日期"/>
					--
					<input id="endTime" name="endTime" type="text" style="width:130px;"  readonly="readonly" class="input-mini Wdate"
					value="<fmt:formatDate value="${paramManager.endTime}" pattern="yyyy-MM-dd"/>" placeholder="结束日期"/>
				</span>
-->
			</div>
			<div class="box1">
				<div class="addTableBox">
					<div class="box_href">
						<div class="table_top">
							<span>新增客户排行榜</span>
						<!-- 	<span id="downTable1" title="下载表格"><a href="${ctx}/st/countAddFriends/exportExcel?type=1">下载表格</a></span>  -->
						</div>
						<table id="addTable1">
							<thead>
								<tr>
									<th>排名</th>
									<th>任务名称</th>
									<th>任务微信</th>
									<th>执行任务量</th>
									<th>新增客户量</th>
									<th>转化率</th>
								</tr>
							</thead>
							<tbody id="tbody1">
							<c:forEach items="${page.list}" var="addFriends" varStatus="number">
								<tr>
									<td>${number.index+1}</td>
									<td>${addFriends.name }</td>
									<td>${addFriends.noWxArrays }</td>
									<td>${addFriends.completeNum }</td>
									<td>${addFriends.successNum }</td>
									<td>${empty addFriends.trans?'0.00':addFriends.trans}%</td>
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
<%-- <script type="text/javascript" src="${ctxStatic}/admin/js/index/jquery.table2excel.js"></script> --%>
<script type="text/javascript">
		$(document).ready(function() {
				$('#tableSelect').on('change', function() {
					 var val = $(this).val();
					 if(val == 00){//自定义
						 $("#date2").show()
					 }else{
						 $("#date2").hide()
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
							       var aa1 = data.shopAddlist;
							       var html1 = '';
									$.each(aa1,function(i){
										html1 += "<tr>";
										html1 += "<td>"+(i+1)+"</td>";
										html1 += "<td>"+aa1[i].shopName+"</td>";
										html1 += "<td>"+aa1[i].count+"</td>";
										html1 += "</tr>";
									})
									$("#tbody1").append(html1)
							}
						})

			 });

			 $("#startTime").click(function () {
		        WdatePicker({
		            dateFmt: 'yyyy-MM-dd',
								maxDate: '#F{$dp.$D(\'endTime\')}',//最大日期不能大于结束日期
		            readOnly: true,
								isShowClear:true,
								isShowOK: false,//不显示确定按钮
								onpicked:function(val){
									 console.log(val.el.realValue)

								}
		        })
		    })
		    $("#endTime").click(function () {
		        WdatePicker({
		            dateFmt: 'yyyy-MM-dd',
								minDate: '#F{$dp.$D(\'startTime\')}',//最小日期不能小于开始日期
		            readOnly: true,
								isShowClear:true,
								isShowOK: false,//不显示确定按钮
								onpicked:function(val){
									 if($("#startTime").val()){
										 var sTime = $("#startTime").val()
										 var eTime = val.el.realValue
										 console.log(sTime,eTime)
										 		// $.ajax({
										 		// 	url : "${ctx}/st/countAddFriends/getAddFriendOrder",//请求的url地址Integer pro_id,String LandedUuid
										 		// 	dataType : "json", //返回格式为json
										 		// 	async : false, //请求是否异步，默认为异步，这也是ajax重要特性
										 		// 	//contentType : "application/x-www-form-urlencoded; charset=UTF-8",
										 		// 	data : {startTime: sTime,endTime:eTime,},
										 		// 	type : "POST", //请求方式
										 		// 	success : function(data){
										 		// 	       console.log(data);
										 		// 	       $("#tbody1").html("")
										 		// 	       var aa1 = data.shopAddlist;
										 		// 	       var html = '';
										 		// 			$.each(aa1,function(i){
										 		// 				html += "<tr>";
										 		// 				html += "<td>"+(i+1)+"</td>";
										 		// 				html += "<td>"+aa1[i].shopName+"</td>";
										 		// 				html += "<td>"+aa1[i].count+"</td>";
										 		// 				html += "</tr>";
										 		// 			})
										 		// 			$("#tbody1").append(html)
										 		// 	}
										 		// })
									 }
								}
		        })
		    })
		});


</script>
</body>
</html>
