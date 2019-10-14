<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>运营简报</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#form').find('.btn-generator').on('click',function(){
			$(this).addClass('hoverd').siblings().removeClass('hoverd');
		})
	});

</script>
<style type="text/css">
	.operate_title{
		font-size:14px;
		margin:20px 0;
	}
	.operate_box{
		width:90%;
		height:400px;
	}
	.container{
		width:100%;
		padding-left:20px;
	}
	.page_header{
		margin-top:20px;
	}
	.btn-generator {
	    width: 90px;
	    height: 34px;
	    color: #666;
	    font-size: 14px;
	    text-align: center;
	    background: transparent;
	    border-radius: 2px;
	    border: 1px solid #ddd;
	    box-sizing: border-box;
	    margin-right: 20px;
	}
	.search-group{
		display:flex;
		margin-top:20px;
	}
	.search-group input.hoverd {
	    border: 1px solid #63a8eb;
	    color: #63a8eb;
	}
	.search-group select, .search-group input[type="text"] {
	    padding: 0 10px;
	    width: 174px;
	    height: 34px;
	    border: 1px solid #dddddd;
	}
	.btn-default {
	    width: 110px;
	    height: 34px;
	    color: #fff;
	    font-size: 14px;
	    text-align: center;
	    background: #63a8eb;
	    border-radius: 2px;
	    border: none;
	    box-sizing: border-box;
	    margin-left:15px;
	}
	.label_font{
		line-height:40px;
		margin:0 10px;
	}
	</style>
</head>
<body>
	
	<div class="container">
	<h1 class="page_header">运营简报</h1>
	
	<div class="top_stores">
				<form id="form" onkeydown="if(event.keyCode==13)return false">
					<div class="search-group">
						 <input class="btn-generator hoverd" id="yesterday" type="button"
							value="昨日"> <input class="btn-generator" id="storeSeven"
							type="button" value="最近7天"> <input class="btn-generator"
							id="storeThirty" type="button" value="最近30天"> <input
							id="beginDate" name="beginDate" type="text" readonly="readonly"
							maxlength="20" class="input-mini Wdate"
							value="<fmt:formatDate value="${log.beginDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						<label class="label_font">至</label> <input id="endDate" name="endDate" type="text"
							readonly="readonly" maxlength="20" class="input-mini Wdate"
							value="<fmt:formatDate value="${log.endDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						
						<input class="ml0 btn-default" type="button" onclick="storeRanking(1)" id="search" value="查询">
					</div>
		
				</form>
			</div>
		
	<h4 class="operate_title">客户数据图表</h4>
	<div id="data_customer" class='operate_box'></div>
	<script>
	$(function(){
		 // 基于准备好的dom，初始化echarts实例
       var myChart = echarts.init(document.getElementById('data_customer'));

       // 指定图表的配置项和数据
       option = {
	
		    tooltip : {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'cross',
		            label: {
		                backgroundColor: '#6a7985'
		            }
		        }
		    },
		    legend: {
		    	selectedMode:false , //选择模式,默认开启值域开关
		        data:['非意向客户','意向客户','紧急客户','交叉客户','暂停跟进客户','成单客户']
		    },
		    /* toolbox: {
		        feature: {
		            saveAsImage: {}
		        }
		    }, */
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data : ['周一','周二','周三','周四','周五','周六','周日']
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		        {
		            name:'非意向客户',
		            type:'line',
		            smooth:true,
		            //stack: '总量',
		            //areaStyle: {normal: {}},
		            data:[120, 132, 101, 134, 90, 230, 210]
		        },
		        {
		            name:'意向客户',
		            type:'line',
		            smooth:true,
		            //stack: '总量',
		            //areaStyle: {normal: {}},
		            data:[220, 182, 191, 234, 290, 330, 310]
		        },
		        {
		            name:'紧急客户',
		            type:'line',
		            smooth:true,
		            //stack: '总量',
		            //areaStyle: {normal: {}},
		            data:[150, 232, 201, 154, 190, 330, 410]
		        },
		        {
		            name:'交叉客户',
		            type:'line',
		            smooth:true,
		            //stack: '总量',
		            //areaStyle: {normal: {}},
		            data:[320, 332, 301, 334, 390, 330, 320]
		        },
		        {
		            name:'暂停跟进客户',
		            type:'line',
		            smooth:true,
		            //stack: '总量',
		            //areaStyle: {normal: {}},
		            data:[426, 468, 369, 430, 466, 399, 364]
		        },
		        {
		            name:'成单客户',
		            type:'line',
		            smooth:true,
		            //stack: '总量',
		            label: {
		                normal: {
		                    show: true,
		                    position: 'top'
		                }
		            },
		            //areaStyle: {normal: {}},
		            data:[820, 932, 901, 934, 1290, 1330, 1320]
		        }
		    ]
		};

       // 使用刚指定的配置项和数据显示图表。
       myChart.setOption(option);
       window.onresize=myChart.resize;
	})
	</script>
	
	
	<h4 class="operate_title">终端数据图表</h4>
	<div id="data_stores" class='operate_box'></div>
	<script>
	$(function(){
		 // 基于准备好的dom，初始化echarts实例
       var myChart = echarts.init(document.getElementById('data_stores'));

       // 指定图表的配置项和数据
       option = {
	
		    tooltip : {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'cross',
		            label: {
		                backgroundColor: '#6a7985'
		            }
		        }
		    },
		    legend: {
		    	selectedMode:false , //选择模式,默认开启值域开关
		        data:['客户总数','意向客户数','新增客户数','成单客户数','暂停跟进客户数','到店体验客户数']
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data : ['周一','周二','周三','周四','周五','周六','周日']
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		        {
		            name:'客户总数',
		            type:'line',
		            smooth:true,
		            data:[263, 236, 326, 365, 226, 230, 366]
		        },
		        {
		            name:'意向客户数',
		            type:'line',
		            smooth:true,
		            data:[220, 182, 191, 234, 290, 330, 310]
		        },
		        {
		            name:'新增客户数',
		            type:'line',
		            smooth:true,
		            data:[150, 232, 201, 154, 190, 330, 410]
		        },
		        {
		            name:'成单客户数',
		            type:'line',
		            smooth:true,
		            data:[654, 759, 698, 759, 862, 596, 763]
		        },
		        {
		            name:'暂停跟进客户数',
		            type:'line',
		            smooth:true,
		            data:[426, 468, 369, 430, 466, 399, 364]
		        },
		        {
		            name:'到店体验客户数',
		            type:'line',
		            smooth:true,
		            label: {
		                normal: {
		                    show: true,
		                    position: 'top'
		                }
		            },
		            data:[625, 932, 901, 934, 995, 759, 865]
		        }
		    ]
		};

       // 使用刚指定的配置项和数据显示图表。
       myChart.setOption(option);
       window.onresize=myChart.resize;
	})
	</script>
	
	
	<h4 class="operate_title">员工数据统计</h4>
	
	<div id="data_area" class='operate_box'></div>
	<script>
	$(function(){
		 // 基于准备好的dom，初始化echarts实例
       var myChart = echarts.init(document.getElementById('data_area'));

       // 指定图表的配置项和数据
      option = {
		    color: ['#3398DB'],
		    title: {
                text: '员工数量',
                left: 'left'
            },
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis : [
		        {
		            type : 'category',
		            data : ['员工总数','华南地区','华北地区','华中地区','华东地图','东北地区'],
		            axisTick: {
		                alignWithLabel: true
		            }
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		        {
		            name:'员工数量',
		            type:'bar',
		            barWidth: '30%',
		            data:[390, 52, 200, 230, 142, 136, 199]
		        }
		    ]
		};


       // 使用刚指定的配置项和数据显示图表。
       myChart.setOption(option);
       window.onresize=myChart.resize;
	})
	</script>
	
	<div id="data_sex" class='operate_box'></div>
	<script type='text/javascript'>
					$(function(){
						 // 基于准备好的dom，初始化echarts实例
				        var myChart = echarts.init(document.getElementById('data_sex'));

				        // 指定图表的配置项和数据
				        option = {
			        		title: {
			                    text: '员工性别比例',
			                    left: 'left'
			                },
						    tooltip : {
						        trigger: 'item',
						        formatter: "{a} <br/>{b} : {c} ({d}%)"
						    },
						    legend: {
						    	orient: 'horizontal',
					            y: 'top',
						        left: 'center',
						        selectedMode:false , //选择模式,默认开启值域开关
						        data: ['女性员工数','男性员工数']
						    },
						    series : [
						        {
						            name: '员工性别比例',
						            type: 'pie',
						            radius : '55%',
						            center: ['50%', '60%'],
						            data:[
						                {value:335, name:'女性员工数'},
						                {value:310, name:'男性员工数'},
						            ],
						            itemStyle: {
						                emphasis: {
						                    shadowBlur: 10,
						                    shadowOffsetX: 0,
						                    shadowColor: 'rgba(0, 0, 0, 0.5)'
						                }
						            }
						        }
						    ]
						};

				        // 使用刚指定的配置项和数据显示图表。
				        myChart.setOption(option);
				        window.onresize=myChart.resize;
					})
				</script>
				
				
				<div id="data_age" class='operate_box'></div>
	<script type='text/javascript'>
					$(function(){
						 // 基于准备好的dom，初始化echarts实例
				        var myChart = echarts.init(document.getElementById('data_age'));

				        // 指定图表的配置项和数据
				        option = {
			        		title: {
			                    text: '员工年龄比例',
			                    left: 'left'
			                },
						    tooltip : {
						        trigger: 'item',
						        formatter: "{a} <br/>{b} : {c} ({d}%)"
						    },
						    legend: {
						    	orient: 'horizontal',
					            y: 'top',
						        left: 'center',
						        selectedMode:false , //选择模式,默认开启值域开关
						        data: ['20-30岁员工','30-40岁员工','40-50岁员工','50-60岁员工',]
						    },
						    series : [
						        {
						            name: '员工年龄比例',
						            type: 'pie',
						            radius : '55%',
						            center: ['50%', '60%'],
						            data:[
						                {value:335, name:'20-30岁员工'},
						                {value:310, name:'30-40岁员工'},
						                {value:236, name:'40-50岁员工'},
						                {value:126, name:'50-60岁员工'},
						            ],
						            itemStyle: {
						                emphasis: {
						                    shadowBlur: 10,
						                    shadowOffsetX: 0,
						                    shadowColor: 'rgba(0, 0, 0, 0.5)'
						                }
						            }
						        }
						    ]
						};

				        // 使用刚指定的配置项和数据显示图表。
				        myChart.setOption(option);
				        window.onresize=myChart.resize;
					})
				</script>
	
	
	</div>
	
</body>
</html>