<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>首页</title>
<meta name="decorator" content="default" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
<script type="text/javascript">
	//意向客户
	var intentionDays = [];
	var intentionNums = [];

	//客户数据图表
	var customeDays = [];
	var intentionNum = [];
	var otherNum = [];
	var urgencyNum = [];
	var repeatNum = [];
	var giveUpNum = [];
	var successNum = [];
	var intentionNumNo = [];

	//体验客户图表
	var experienceDays = [];
	var experienceNums = [];

	//总成交额
	var couponDays = [];
	var couponNums = [];

	$(document).ready(function() {
		queryByDate(30);

		$(".datelist .yesterday").click(function() {
			queryByDate(1);
			$(this).addClass("active");
			$(this).siblings().removeClass("active");
		});
		$(".datelist .seven").click(function() {
			queryByDate(7);
			$(this).addClass("active");
			$(this).siblings().removeClass("active");
		});
		$(".datelist .month").click(function() {
			queryByDate(30);
			$(this).addClass("active");
			$(this).siblings().removeClass("active");
		});

	});

	function queryByDate(dateCount) {
		//意向客户
		$.ajax({
			url : "${ctx}/index/findIntentionPmList",//请求的url地址Integer pro_id,String LandedUuid
			dataType : "json", //返回格式为json
			async : false, //请求是否异步，默认为异步，这也是ajax重要特性
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			data : {
				"startTime" : startTime(dateCount),
				"endTime" : endTime(),
			}, //参数值
			type : "POST", //请求方式
			success : function(data) {
				intentionDays = data.daysts;
				intentionNums = data.nums;
				$("#intentionCustomerCount").text(data.numTotle);

				var option1 = j1.getOption();
				option1.xAxis[0].data = intentionDays;
				option1.series[0].data = intentionNums;
				j1.setOption(option1);
			}
		});

			//客户数据图表
			$
					.ajax({
						url : "${ctx}/index/findPmTypeTotalList",//请求的url地址Integer pro_id,String LandedUuid
						dataType : "json", //返回格式为json
						async : false, //请求是否异步，默认为异步，这也是ajax重要特性
						contentType : "application/x-www-form-urlencoded; charset=UTF-8",
						data : {
							"startTime" : startTime(dateCount),
							"endTime" : endTime(),
						}, //参数值
						type : "POST", //请求方式
						success : function(data) {
							intentionNum = data.intentionNum;
							otherNum = data.otherNum;
							urgencyNum = data.urgencyNum;
							repeatNum = data.repeatNum;
							giveUpNum = data.giveUpNum;
							successNum = data.successNum;
							customeDays = data.daysts;
							intentionNumNo = data.intentionNumNo;

							$("#CustomerCount").text(data.numPmTotle);
							var option2 = myChart.getOption();
							option2.xAxis[0].data = customeDays;
							option2.series[0].data = otherNum;
							option2.series[1].data = intentionNum;
							option2.series[2].data = urgencyNum;
							option2.series[3].data = repeatNum;
							option2.series[4].data = giveUpNum;
							option2.series[5].data = successNum;
							option2.series[6].data = intentionNumNo;
							myChart.setOption(option2);
						}
					});

			//到店客户体验统计
			$
					.ajax({
						url : "${ctx}/index/client",//请求的url地址Integer pro_id,String LandedUuid
						dataType : "json", //返回格式为json
						async : false, //请求是否异步，默认为异步，这也是ajax重要特性
						contentType : "application/x-www-form-urlencoded; charset=UTF-8",
						data : {
							"startTime" : startTime(dateCount),
							"endTime" : endTime(),
						}, //参数值
						type : "POST", //请求方式
						success : function(data) {
							experienceDays = data.datas;
							experienceNums = data.numAdds;
							$("#experienceCustomerCount").text(data.nums);
							var option = j.getOption();
							option.xAxis[0].data = experienceDays;
							option.series[0].data = experienceNums;
							j.setOption(option);
						}
					});

		//客户数据统计分析
		$.ajax({
			url : "${ctx}/index/findSaleList",//请求的url地址Integer pro_id,String LandedUuid
			dataType : "json", //返回格式为json
			async : false, //请求是否异步，默认为异步，这也是ajax重要特性
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			data : {
				"startTime" : startTime(dateCount),
				"endTime" : endTime(),
			}, //参数值
			type : "POST", //请求方式
			success : function(data) {
				couponDays = data.daysts;
				couponNums = data.nums;
				var option4 = g.getOption();
				if (data.numTotle != undefined) {
					option4.title[0].text = "总成交额￥" + data.numTotle;
				} else {
					option4.title[0].text = "总成交额￥0";
				}

				option4.xAxis[0].data = couponDays;
				option4.series[0].data = couponNums;
				g.setOption(option4);
			}
		});
		//导购与客户沟通次数统计
		$('#btnSubmit')
				.bind(
						'click',
						function() {
							var shopName = $('#shopName').val();
							var memberNamegm = $('#memberNamegm').val();
							var startTime = $('#startTime').val();
							var endTime = $('#endTime').val();
							$
									.post(
											"${ctx}/weixin/imChatInfo/listStatistics",
											{
												'shopName' : shopName,
												'memberNamegm' : memberNamegm,
												'startTime' : startTime,
												'endTime' : endTime
											},
											function(data) {
												if (data) {
													var infolist = $('#infolist');
													infolist.empty();
													for (var i = 0; i < data.length; i++) {
														infolist
																.append("<tr><td>"
																		+ data[i].chatdate
																		+ "</td><td>"
																		+ data[i].shopName
																		+ "</td><td>"
																		+ data[i].memberNamegm
																		+ "</td><td>"
																		+ data[i].peoplecount
																		+ "</td></tr>");
													}
													if (data.length < 1) {
														infolist.empty();
														infolist
																.append("<tr><td colspan='4'>暂无数据...</td></tr>");
													}
												} else {
													var infolist = $('#infolist');
													infolist
															.append("<tr><td colspan='4'>暂无数据...</td></tr>");
												}
											});
						}).click();
		//查询已登录用户的名下分店
		$.post("${ctx}/member/shop/findShopsByUser", {}, function(data) {
			if (data) {
				var shopNameSel = $('#shopName');
				shopNameSel.empty();
				shopNameSel.append("<option value=''>默认</option>");
				for (var i = 0; i < data.length; i++) {//直接使用分店名称查询
					shopNameSel.append("<option value="+data[i].shopName+">"
							+ data[i].shopName + "</option>");
				}
			}
		});
	}

	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	function CancelQuery() {
		$(':input', '#searchForm').not(':button, :submit, :reset').val('')
				.removeAttr('checked').removeAttr('selected');

		$("#pageNo").val(1);
		$("#searchForm").submit();
	}
</script>
<style type="text/css">
.parts-container, .parts-container * {
	box-sizing: border-box
}

.parts-container {
	margin: 0 0 20px 0;
	color: #666
}

.parts-container .default-panel {
	display: inline-block;
	background: #fff;
	vertical-align: top;
	overflow: hidden
}

.parts01 .default-panel {
	height: 170px
}

.parts02 .default-panel .panel-body {
	height: 300px
}

.parts03 .default-panel .panel-body {
	height: 355px
}

.panel-body {
	padding: 30px;
	background: #fff;
	position: relative
}

.data-left p {
	line-height: 3
}

.data-left h2 {
	font-size: 32px;
	line-height: 1;
	font-weight: normal
}

.operation-data {
	list-style: none;
	height: 300px;
	position: absolute;
	top: 95px;
	right: 400px;
}

.operation-data li {
	display: table;
	width: 100%;
	height: 25%;
	color: #fff;
	font-size: 26px
}

.operation-data li p {
	display: table-cell;
	padding: 0 30px 0 40px;
	width: 100%;
	height: 100%;
	font-family: 'Arial';
	vertical-align: middle
}

.operation-data li:first-of-type p {
	padding-left: 30px
}

.operation-data li .big {
	font-size: 30px;
	font-family: 'Arial'
}

.operation-data li small {
	margin-left: 10px;
	font-size: 16px
}

.operation-data li.first {
	background: #63a8eb
}

.operation-data li.second {
	background: #81c0fd
}

.operation-data li.third {
	background: #a2d1fe
}

.operation-data li.four {
	background: #c2e1ff
}

.totallist {
	list-style: none;
	padding: 0 20px 20px 0
}

.totallist li {
	display: inline-block;
	width: 128px;
	text-align: center;
	position: relative;
	vertical-align: top
}

.totallist li:first-of-type:before {
	content: '';
	width: 2px;
	height: 60%;
	background: #edf2fb;
	position: absolute;
	top: 25%;
	right: 0
}

.totallist li p:first-of-type {
	color: #81d0fd;
	font-size: 32px;
	font-family: Arial
}

.totallist li p:last-of-type {
	line-height: 2
}

.totallist li:first-of-type p:first-of-type {
	color: #63a8eb
}

.datelist {
	position: absolute;
	bottom: 0;
	left: 8px
}

.datelist span {
	display: inline-block;
	padding: 22px 21px;
	position: relative;
	cursor: pointer
}

.datelist span.active {
	color: #3b94e2
}

.datelist span.active:before {
	content: '';
	position: absolute;
	left: 0;
	bottom: 0;
	width: 100%;
	height: 2px;
	background: #3b94e2
}

.panel-heading {
	padding: 0 20px;
	background: #d4eaff
}

.panel-title {
	font-size: 16px;
	line-height: 44px;
	font-weight: normal;
	color: #666
}

.title-tips {
	float: right;
	font-size: 22px;
	color: #4a6699;
	font-family: 'Arial'
}

.title-tips small {
	margin-left: 10px;
	font-size: 14px;
	color: #4b5264
}

.fr {
	float: right;
}

.parts-container {
	overflow: hidden;
}
</style>
</head>
<body>
	<div id="section" style="margin-left: 0px;">

		<div class="parts-container parts01">
			<div class="default-panel" style="width: 100%;">
				<div class="panel-body" style="height: 100%">
					<div class="data-content clearfix">
						<div class="data-left fl">
							<h2>运营概览</h2>
							<p id="statsDate">
								最新统计时间：
								<fmt:formatDate value="${time }" pattern="yyyy-MM-dd" />
							</p>
						</div>
						<ul class="operation-data" style="width: 28%">
							<!-- 						<li class="first"> -->
							<!-- 							<p> -->
							<!-- 								<span class="big" id="payableAmount">￥170000</span><small>总成交额</small> -->
							<!-- 							</p> -->
							<!-- 						</li> -->
						</ul>
						<div class="data-right fr">
							<ul class="totallist">
								<li>
									<p id="storeCount">${data.shopTotle }</p>
									<p>终端总数</p>
								</li>
								<li>
									<p id="staffCount">${data.SUM}</p>
									<p>店员总数</p>
								</li>
							</ul>
						</div>
						<!-- 						<div class="datelist"> 段志鹏	2017-08-10 去除-->
						<!-- 							<span class=" yesterday">昨天</span> <span class="seven active">最近7天</span> <span class="month">最近30天</span> -->
						<!-- 						</div> -->
					</div>
				</div>
			</div>
		</div>

		<div class="parts-container parts02">
			<div class="default-panel" style="width: 100%;">
				<div class="panel-body " style="padding: 20px 0 0 30px">
					<div id="coupon-info"
						style="width: 100%; height: 250px; display: inline-block; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; position: relative; background-color: transparent;">
					</div>
				</div>
			</div>
		</div>

		<div class="parts-container parts03">

				<div class="default-panel " style="width: 49%; float: left;">
					<div class="panel-heading">
						<h2 class="panel-title">
							体验客户数据 <span class="title-tips"><span
								id="experienceCustomerCount"></span><small>净增客户</small></span>
						</h2>
					</div>
					<div class="panel-body ">
						<div id="customer-data"
							style="height: 300px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; position: relative; background-color: transparent;"></div>

					</div>
				</div>

			<div class="default-panel " style="width: 49%; float: right;">
				<div class="panel-heading">
					<h2 class="panel-title">
						新增客户数据 <span class="title-tips"><span
							id="intentionCustomerCount"></span><small>净增客户</small></span>
					</h2>
				</div>
				<div class="panel-body ">
					<div id="customer-data1"
						style="height: 300px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; position: relative; background-color: transparent;"></div>

				</div>
			</div>

		</div>

			<div class="parts-container parts03">
				<div class="default-panel " style="width: 100%; float: left;">
					<div class="panel-heading">
						<h2 class="panel-title">
							客户数据图表 <span class="title-tips"><span id="CustomerCount"></span><small>客户总数</small></span>
						</h2>
					</div>
					<div class="panel-body ">
						<div id="data_customer"
							style="height: 300px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; position: relative; background-color: transparent;"></div>
					</div>
				</div>
			</div>
		<script>
			// 基于准备好的dom，初始化echarts实例
			var myChart = echarts
					.init(document.getElementById('data_customer'));

			// 指定图表的配置项和数据
			option = {

				tooltip : {
					trigger : 'axis',
					axisPointer : {
						type : 'cross',
						label : {
							backgroundColor : '#6a7985'
						}
					}
				},
				legend : {
					selectedMode : false, //选择模式,默认开启值域开关 
					data : [ '非意向客户', '意向客户(到店)', '紧急客户', '交叉客户', '暂停跟进客户',
							'意向客户(未到店)', '成单客户' ]
				},
				/* toolbox: {
				    feature: {
				        saveAsImage: {}
				    }
				}, */
				grid : {
					left : '3%',
					right : '4%',
					bottom : '3%',
					containLabel : true
				},
				xAxis : [ {
					type : 'category',
					boundaryGap : false,
					data : customeDays
				} ],
				yAxis : [ {
					type : 'value'
				} ],

				series : [ {
					name : '非意向客户',
					type : 'line',
					smooth : true,
					//stack: '总量',
					// 		            areaStyle: {normal: {}},
					data : otherNum
				}, {
					name : '意向客户(到店)',
					type : 'line',
					smooth : true,
					//stack: '总量',
					// 		            areaStyle: {normal: {}},
					data : intentionNum
				}, {
					name : '紧急客户',
					type : 'line',
					smooth : true,
					//stack: '总量',
					// 		            areaStyle: {normal: {}},
					data : urgencyNum
				}, {
					name : '交叉客户',
					type : 'line',
					smooth : true,
					//stack: '总量',
					// 		            areaStyle: {normal: {}},
					data : repeatNum
				}, {
					name : '暂停跟进客户',
					type : 'line',
					smooth : true,
					//stack: '总量',
					// 		            areaStyle: {normal: {}},
					data : giveUpNum
				}, {
					name : '成单客户',
					type : 'line',
					smooth : true,
					data : successNum

				}, {

					name : '意向客户(未到店)',
					type : 'line',
					smooth : true,
					//stack: '总量',
					// 		            areaStyle: {normal: {}},
					label : {
						normal : {
							show : true,
							position : 'top'
						}
					},
					// 		            areaStyle: {normal: {}},

					data : intentionNumNo
				} ]
			};

			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
			window.onresize = function() {
				myChart.resize();
			}

			var g = echarts.init(document.getElementById("coupon-info"));
			var c = {
				title : {
					text : '',
					textStyle : {
						color : "#3C8DBC",
						fontSize : 18,
						fontWeight : "normal"
					}
				},
				borderWidth : 1,
				color : [ "#2fcdd0", "#bca7e5", "#ec8413", "#84e6f1", "#dddddd" ],
				tooltip : {
					trigger : "axis"
				},
				legend : {
					top : "0",
					right : "30px",
					data : [ "成交额" ],
					textStyle : {
						color : "#888"
					}
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
				//    			feature : {
				//    				saveAsImage : {}
				//    			}
				},
				xAxis : [ {
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
					data : couponDays
				} ],
				yAxis : [ {
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
				series : [ {
					name : "成交额",
					type : "line",
					smooth : true,
					data : couponNums
				} ]
			};
			g.setOption(c);

			var j = echarts.init(document.getElementById("customer-data"));
			var e = {
				color : [ "#81d0fd" ],
				tooltip : {
					trigger : "axis"
				},
				grid : {
					left : "0%",
					right : "20px",
					bottom : "20px",
					borderWidth : 0,
					backgroundColor : "rgba(255,255,255,1)",
					itemStyle : {
						normal : {
							shadowColor : "rgba(0, 0, 0, 0.5)",
							shadowBlur : 10
						}
					},
					containLabel : true
				},
				legend : {
					data : [ "净增客户（人）数" ],
					textStyle : {
						color : "#888"
					}
				},
				xAxis : [ {
					type : "category",
					data : experienceDays,
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
				yAxis : [ {
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
				series : [ {
					name : "净增客户（人）数",
					type : "bar",
					barWidth : 20,
					data : experienceNums
				} ]
			};
			j.setOption(e);

			var j1 = echarts.init(document.getElementById("customer-data1"));
			var e1 = {
				color : [ "#81d0fd" ],
				tooltip : {
					trigger : "axis"
				},
				grid : {
					left : "0%",
					right : "0",
					bottom : "20px",
					borderWidth : 0,
					backgroundColor : "rgba(255,255,255,1)",
					itemStyle : {
						normal : {
							shadowColor : "rgba(0, 0, 0, 0.5)",
							shadowBlur : 10
						}
					},
					containLabel : true
				},
				legend : {
					data : [ "净增客户（人）数" ],
					textStyle : {
						color : "#888"
					}
				},
				xAxis : [ {
					type : "category",
					data : intentionDays,
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
				yAxis : [ {
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
					},
					minInterval : 1
				} ],
				series : [ {
					name : "净增客户（人）数",
					type : "bar",
					barWidth : 20,
					data : intentionNums
				} ]
			};
			j1.setOption(e1);

			top.$('#mainFrame').css('height', '1800px');
		</script>
	</div>
	</div>
	</div>

	</div>
</body>
</html>
