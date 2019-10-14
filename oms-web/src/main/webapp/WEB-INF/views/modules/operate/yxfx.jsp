<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>统计分析</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
<script type="text/javascript" src="${ctxStatic}/echarts/china.js"></script>
<script type="text/javascript">
//客户数据统计分析
var intentionNum=0;
var otherNum=0;
var giveUpNum=0;
var successNum=0;
var intentionNumNo=0;
var talkDate =[];
var talkNum =[];
//客户年龄结构统计
var numAgeTen=0;
var numAgeTwenty=0;
var numAgeThirty=0;
var numAgeForty=0;
var numAgeFifty=0;
//客户性别
var numMale=0;
var numFemale=0;
var numUnKnown=0;
//销售漏斗
var xsldData=${fns:toJson(xsldData)};
//员工年龄
var numAgeTwentys=0;
var numAgeThirtys=0;
var numAgeFortys=0;
var numAgeFiftys=0;
//客户分布
var provinces =${fns:toJson(provinces)};
$(document).ready(function() {
	  queryByDate();
	
	/*
	  $(".datelist .yesterday").click(function(){
		queryByDate(1);
		$(this).addClass("active");
		$(this).siblings().removeClass("active");
	});
	$(".datelist .seven").click(function(){
		queryByDate(7);
		$(this).addClass("active");
		$(this).siblings().removeClass("active");
	});
	$(".datelist .month").click(function(){
		queryByDate(30);
		$(this).addClass("active");
		$(this).siblings().removeClass("active");
	});   */
});

function queryByDate(){
	//客户数据统计分析
	$.ajax({
		url : "${ctx}/operate/findPmTypeTotalList",//请求的url地址Integer pro_id,String LandedUuid
		dataType : "json", //返回格式为json
		async : false, //请求是否异步，默认为异步，这也是ajax重要特性
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		data : {
		}, //参数值的·
		type : "POST", //请求方式
		success : function(data) {
			data.intentionNum!=null? intentionNum=data.intentionNum :intentionNum=0;
			data.intentionNumNo!=null ? intentionNumNo=data.intentionNumNo :intentionNumNo=0;
			data.otherNum!=null? otherNum=data.otherNum :otherNum=0;
			data.giveUpNum!=null? giveUpNum=data.giveUpNum :giveUpNum=0;
			data.successNum!=null? successNum=data.successNum :successNum=0;
			
			var option1=myChart1.getOption();
			option1.series[0].data[0].value = otherNum;
			option1.series[0].data[1].value = intentionNum; 
			option1.series[0].data[2].value = giveUpNum; 
			option1.series[0].data[3].value = successNum; 
			option1.series[0].data[4].value = intentionNumNo; 
           myChart1.setOption(option1);
		}
	});
	
/* //客户咨询
	$.ajax({
		url : "${ctx}/operate/findPmTalkTotalList",//请求的url地址Integer pro_id,String LandedUuid
		dataType : "json", //返回格式为json
		async : false, //请求是否异步，默认为异步，这也是ajax重要特性
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		data : {
		}, //参数值
		type : "POST", //请求方式
		success : function(data) {
			talkDate = data.daysts;
			talkNum = data.nums;
			
			var option2=myChart2.getOption();
			option2.series[0].data = talkNum;
			option2.angleAxis[0].data = talkDate;
			myChart2.setOption(option2);
		}
	});   */
	
	//客户年龄结构
	$.ajax({
		url : "${ctx}/operate/findClientAnalyzeList",//请求的url地址Integer pro_id,String LandedUuid
		dataType : "json", //返回格式为json
		async : false, //请求是否异步，默认为异步，这也是ajax重要特性
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		data : {
		}, //参数值
		type : "POST", //请求方式
		success : function(data) {
			if(data.length>0){
				for(var i = 0; i < data.length; i++){
					if(data[i].NUM_AGE_TEN!=null){
						numAgeTen += data[i].NUM_AGE_TEN;	
					}
					if(data[i].NUM_AGE_TWENTY!=null){
						numAgeTwenty += data[i].NUM_AGE_TWENTY;
					}
				    if(data[i].NUM_AGE_THIRTY!=null){
				    	numAgeThirty += data[i].NUM_AGE_THIRTY;
				    }
				    if(data[i].NUM_AGE_FORTY!=null){
				       numAgeForty += data[i].NUM_AGE_FORTY;
				    }
					if(data[i].NUM_AGE_FIFTY!=null){
						numAgeFifty += data[i].NUM_AGE_FIFTY;
					}
				}
			}
			var option3=myChart3.getOption();
			option3.series[1].data[0].value =numAgeTen;
			option3.series[1].data[1].value=numAgeTwenty;
			option3.series[1].data[2].value =numAgeThirty;
			option3.series[1].data[3].value =numAgeForty;
			option3.series[1].data[4].value =numAgeFifty;
			myChart3.setOption(option3);
		}
	});
	
	//客户性别
	$.ajax({
		url : "${ctx}/operate/findClientAnalyze",//请求的url地址Integer pro_id,String LandedUuid
		dataType : "json", //返回格式为json
		async : false, //请求是否异步，默认为异步，这也是ajax重要特性
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		data : {
		}, //参数值
		type : "POST", //请求方式
		success : function(data) {
			if(data!=null && data.length>0){
				numMale=data[0].numMale;
				numFemale=data[0].numFemale;
				numUnKnown=data[0].numUnKnown;
			}
			var option4=myChart4.getOption();
			option4.series[1].data[0].value =numMale;
			option4.series[1].data[1].value=numFemale;
			option4.series[1].data[2].value=numUnKnown;
			myChart4.setOption(option4);
		}
	});
	
	//员工年龄
	$.ajax({
		url : "${ctx}/operate/findEmployeeAnalyzeList",//请求的url地址Integer pro_id,String LandedUuid
		dataType : "json", //返回格式为json
		async : false, //请求是否异步，默认为异步，这也是ajax重要特性
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		data : {
		}, //参数值
		type : "POST", //请求方式
		success : function(data) {
			if(data!=null){
		
			if(data.NUM_AGE_TWENTY!=null){
			 numAgeTwentys=data.NUM_AGE_TWENTY;
			}
			if(data.NUM_AGE_THIRTY !=null){
			 numAgeThirtys=data.NUM_AGE_THIRTY;
			}
			if(data.NUM_AGE_FORTY!=null){
			 numAgeFortys=data.NUM_AGE_FORTY;
			}
			if(data.NUM_AGE_FIFTY!=null){
			 numAgeFiftys=data.NUM_AGE_FIFTY;
			}
			}
			var option7=myChart7.getOption();
			option7.series[0].data[0].value =numAgeTwentys;
			option7.series[0].data[1].value=numAgeThirtys;
			option7.series[0].data[2].value=numAgeFortys;
			option7.series[0].data[3].value=numAgeFiftys;			
			myChart7.setOption(option7);
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
    		$("#pageNo").val(1);
		$("#searchForm").submit();
	}
</script>
<style type="text/css">
	.operate_title{
		font-size:14px;
		margin:10px 0;
	}
	.operate_box{
		width:90%;
		height:400px;
	}
	.container{
		width:100%;
		padding-left:30px;
	}
	.page_header{
	margin-top:20px;
	font-size: 32px;
    line-height: 1;
    font-weight: normal;
	}
	.datelist {
    margin-top: 30px;
    margin-bottom: 20px;
	}
	.datelist span.active {
    color: #3b94e2;
	}
	.datelist span {
    display: inline-block;
    padding: 22px 21px;
    position: relative;
    cursor: pointer;
	}
	.data-left h2 {
    font-size: 32px;
    line-height: 1;
    font-weight: normal;
	}
	.parts-container {
    margin: 0 0 20px 0;
    color: #666;
	}
	.panel-body {
	    padding: 30px;
	    background: #fff;
	    position: relative;
	}
	.datelist span.active:before {
    content: '';
    position: absolute;
    left: 0;
    bottom: 0;
    width: 100%;
    height: 2px;
    background: #3b94e2;
	}
	.parts-container {
    overflow: hidden;
	}
	#customer_sex,.operate_box{
	margin-top: 40px;
	}
	</style>
</head>
<body>
	
	<div class="container">
	<div class="data-content clearfix">
				<div class="data-left fl">
					<h2>统计分析</h2>
				</div>
<!-- 				<div class="datelist"> -->
<!-- 					<span class=" yesterday">昨天</span> <span class="seven active">最近7天</span> <span class="month">最近30天</span> -->
<!-- 				</div> -->
			</div>
	<div id="analysis_customer" class='operate_box'></div>
	<!-- <h4 class="operate_title">客户类型统计分析</h4>
	<div id="new_customer" class='operate_box'></div>
	<script>
	$(function(){
		 // 基于准备好的dom，初始化echarts实例
       var myChart = echarts.init(document.getElementById('new_customer'));

       // 指定图表的配置项和数据
       option = {
    		   title: {
	                text: '客户类型统计分析',
	                left: 'left'
	            },
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
		        data:['新增客户','意向客户','跟进客户','暂停跟进客户','成单客户']
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
		            name:'新增客户',
		            type:'line',
		            stack: '总量',
		            areaStyle: {normal: {}},
		            data:[120, 132, 101, 134, 90, 230, 210]
		        },
		        {
		            name:'意向客户',
		            type:'line',
		            stack: '总量',
		            areaStyle: {normal: {}},
		            data:[220, 182, 191, 234, 290, 330, 310]
		        },
		        {
		            name:'跟进客户',
		            type:'line',
		            stack: '总量',
		            areaStyle: {normal: {}},
		            data:[150, 232, 201, 154, 190, 330, 410]
		        },
		        {
		            name:'暂停跟进客户',
		            type:'line',
		            stack: '总量',
		            areaStyle: {normal: {}},
		            data:[320, 332, 301, 334, 390, 330, 320]
		        },
		        {
		            name:'成单客户',
		            type:'line',
		            stack: '总量',
		            label: {
		                normal: {
		                    show: true,
		                    position: 'top'
		                }
		            },
		            areaStyle: {normal: {}},
		            data:[820, 932, 901, 934, 1290, 1330, 1320]
		        }
		    ]
		};

       // 使用刚指定的配置项和数据显示图表。
       myChart.setOption(option);
	})
	</script> -->
	
	<!-- <div id="behavior analysis" class='operate_box'></div> -->
	<div class="parts-container parts03">
		  <div class="default-panel " style="width: 49%;float: left;">
	<div id="customer_portrait" class='operate_box'></div>
   </div>
   
   <div class="default-panel " style="width: 49%;float: right;">
	<div id="customer_sex" class='operate_box'></div>
   </div>
		
	</div>
	
	<div id="funnel" class='operate_box'></div>
	<div id="customer_map" class='operate_box' style="height:600px;"></div>
	
	<div class="parts-container parts03">
		    <div class="default-panel " style="width: 49%;float: left;">
		        <div class="panel-heading">
		        </div>
		        <div class="panel-body ">
		            <div id="data_age" style=" height: 500px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; position: relative; background-color: transparent;" ></div>

		        </div>
		    </div>
		    
		    <div class="default-panel " style="width: 49%;float: right;">
		        <div class="panel-heading">
		        </div>
		        <div class="panel-body ">
		            <div id="data_sex" style="height: 500px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; position: relative; background-color: transparent;" ></div>
		            <script type='text/javascript'>
					$(function(){
						
						var female=${data.femaleGuid + data.areaTotle};
						var male=${data.maleGuid + data.maleArea};
						var unknow=${data.unknownGuid + data.femaleArea};
						 // 基于准备好的dom，初始化echarts实例
				        var sexChart = echarts.init(document.getElementById('data_sex'));

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
						    	 y: 'bottom',
						        left: 'center',
						        selectedMode:false , //选择模式,默认开启值域开关
						        data: ['女性员工数','男性员工数','未知性别数']
						    },
						    series : [
						        {
						            name: '员工性别比例',
						            type: 'pie',
						            radius : '60%',
						            center: ['50%', '50%'],
						            data:[
						                {value:female, name:'女性员工数'},
						                {value:male, name:'男性员工数'},
						                {value:unknow, name:'未知性别数'},
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
				        sexChart.setOption(option);
				        /* window.onresize=function(){
				        	sexChart.resize();
				        } */
				       
					})
				</script>
		        </div>
		    </div>
		</div>
	
	</div>
<script type='text/javascript'>
	 // 基于准备好的dom，初始化echarts实例
       var myChart1 = echarts.init(document.getElementById('analysis_customer'));

       // 指定图表的配置项和数据
       var option1 = {
      		 title: {
                text: '客户数据统计分析',
                left: 'left'
            },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {d}%"
	    },
	    legend: {
	    	orient: 'horizontal',
            y: 'bottom',
	        left: 'center',
	        selectedMode:false , //选择模式,默认开启值域开关
	        data: ['非意向客户','意向到店客户','暂停跟进客户','成单客户','意向未到店客户']
	    },
	    series : [
	        {
	            name: '总客户量',
	            type: 'pie',
	            radius : '60%',
	            center: ['50%', '50%'],
	            data:[
	                {value:otherNum, name:'非意向客户'},
	                {value:intentionNum, name:'意向到店客户'},
	                {value:giveUpNum, name:'暂停跟进客户'},
	                {value:successNum, name:'成单客户'},
	                {value:intentionNumNo,name:'意向未到店客户'}
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
    myChart1.setOption(option1);
    
 // 基于准备好的dom，初始化echarts实例
   /*  var myChart2 = echarts.init(document.getElementById('behavior analysis'));
 
    // 指定图表的配置项和数据
		var option2={
				title: {
	                text: '24小时客户咨询比例',
	                left: 'left'
	            },
	          tooltip: {
		              formatter: function (params) {
		              return params.value;
		               },
	           },
	            angleAxis: {
		                  axisTick:false,    //坐标轴刻度的显示
		                  type: 'category',
		                  boundaryGap: false,
		                  splitLine: {  
		                      show: true,
		                      lineStyle: {
		                          color: '#5e7aba',
		                          type: 'solid'
		                      }
		                  },
							data: talkDate,
		                  z: 1,
		                        axisLine: {
		                      lineStyle: {
		                          type: 'solid',
		                          color: '#5e7aba',//左边线的颜色
		                          width:'2'//坐标线的宽度
		                      }
		                  },
		                  axisLabel: {
		                      textStyle: {
		                          color: '#b0b0b0',//坐标值得具体的颜色
		                      }
		                  }
		              },
           xAxis : [
           {
                   show:false, 
               }
           ],
            yAxis : [
           {
                   show:false, 
               }
           ],
           radiusAxis: {
             splitLine: {
                   show: true,
                   lineStyle: {
                       color: '#5e7aba',
                       type: 'solid'
                   }
               }, 
               axisLabel: {
                    show:false
               }
           },
           polar: {
           },
           series: [{
               type: 'bar',
				data: talkNum,
               coordinateSystem: 'polar',
               itemStyle: {   
               //通常情况下：
               normal:{//每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
                   color: function (params){
                       var colorList = ['#506993','#506993','#506993','#506993','#506993','#506993','#506993','#506993','#506993','#506993','#506993','#506993','#506993','#506993','#506993','#506993','#506993','#506993','#506993','#506993','#506993','#506993','#506993','#506993'];
                       return colorList[params.dataIndex];
                   }
               },
           },
           }],
           color:['#506993'],
       }   */
	  // 使用刚指定的配置项和数据显示图表。
      // myChart2.setOption(option2);
    
		// 基于准备好的dom，初始化echarts实例
      var myChart3 = echarts.init(document.getElementById('customer_portrait'));

      // 指定图表的配置项和数据
	var option3 = {
			 title: {
	                text: '客户年龄结构',
	                left: 'left'
	            },
	          tooltip: {
	              trigger: 'item',
	              formatter: "{a} <br/>{b}: {c} ({d}%)"
	          },
	          legend: {
	              orient: 'vertical',
	              x: 'left',
	              
	          },         
	          series: [
	              {
	                  name:'客户年龄结构',
	                  type:'pie',
	                  selectedMode: 'single',
	                  radius: [0, '30%'],

	                  label: {
	                      normal: {
	                          position: 'inner'
	                      }
	                  },
	                  labelLine: {
	                      normal: {
	                          show: true
	                      }
	                  },  
	              },
	              {
	                  name:'客户年龄结构',
	                  type:'pie',
	                  radius: ['52%', '70%'],
 	                  data:[
	                      {value:numAgeTen, name:'10-19岁'},
	                      {value:numAgeTwenty, name:'20-29岁'},
 	                      {value:numAgeThirty, name:'30-39岁'},
 	                      {value:numAgeForty, name:'40-49岁'},
 	                      {value:numAgeFifty, name:'50-59岁'},
 	                  ]
	       
	              }
	          ]
	      };
      
	  // 使用刚指定的配置项和数据显示图表。
       myChart3.setOption(option3);
	  
       var myChart4 = echarts.init(document.getElementById('customer_sex'));

       // 指定图表的配置项和数据
		 option4 = {
				 title: {
		                text: '客户性别结构',
		                left: 'left'
		            },
		          tooltip: {
		              trigger: 'item',
		              formatter: "{a} <br/>{b}: {c} ({d}%)"
		          },
		          legend: {
		              orient: 'vertical',
		              x: 'left',
		              
		          },        
		          series: [
		              {
		                  name:'客户性别结构',
		                  type:'pie',
		                  selectedMode: 'single',
		                  radius: [0, '30%'],

		                  label: {
		                      normal: {
		                          position: 'inner'
		                      }
		                  },
		                  labelLine: {
		                      normal: {
		                          show: true
		                      }
		                  },  
		              },
		              {
		                  name:'客户性别结构',
		                  type:'pie',
			              radius: ['52%', '70%'],

		                  data:[
		                      {value:numMale, name:'男性客户数'},
		                      {value:numFemale, name:'女性客户数'},
		                      {value:numUnKnown, name:'未知性别数'}
		                  ]
		              }
		          ]
		      };
       
		  // 使用刚指定的配置项和数据显示图表。
	       myChart4.setOption(option4);
    
		// 基于准备好的dom，初始化echarts实例
	       var myChart5 = echarts.init(document.getElementById('funnel'));
		
	       var option5 = {
	    		    title: {
	    		        text: '销售漏斗统计分析',
	    		        left: 'left'
	    		    },
	    		    tooltip: {
	    		        trigger: 'item',
	    		        formatter: "{a} <br/>{b} : {c}"
	    		    },
	    		  
	    		    legend: {
	    		    	selectedMode:false , //选择模式,默认开启值域开关
	    		        data: ['客户总量','意向客户','成单']
	    		    },
	    		    calculable: true,
	    		    series: [
	    		        {
	    		            name:'漏斗图',
	    		            type:'funnel',
	    		            left: 'center',
	    		            top: 60,
	    		            //x2: 80,
	    		            bottom: 60,
	    		            width: '100%',
	    		            // height: {totalHeight} - y - y2,
 	    		            min: 0,
 	    		            max: 60,
 	    		            minSize: '0%',
	    		            maxSize: '60%',
	    		            sort: 'descending',
	    		            gap: 2,
	    		            label: {
	    		                normal: {
	    		                    show: true,
	    		                    position: 'outter'
	    		                },
	    		                emphasis: {
	    		                    textStyle: {
	    		                        fontSize: 20
	    		                    }
	    		                }
	    		            },
	    		            labelLine: {
	    		                normal: {
	    		                    length: 10,
	    		                    lineStyle: {
	    		                        width: 1,
	    		                        type: 'solid'
	    		                    }
	    		                }
	    		            },
	    		            itemStyle: {
	    		                normal: {
	    		                    borderColor: '#fff',
	    		                    borderWidth: 1
	    		                }
	    		            },
	    		            data: xsldData
	    		
	    		        }
	    		    ]
	    		};

		
	       // 使用刚指定的配置项和数据显示图表。
	       myChart5.setOption(option5);
	       
	    // 基于准备好的dom，初始化echarts实例
	       var myChart6 = echarts.init(document.getElementById('customer_map'));
	       function randomData() {
	    	    return Math.round(Math.random()*1000);
	    	}

	    	var option6 = {
	    	    title: {
	    	        text: '客户分布',
	    	        left: 'left'
	    	    },
	    	    tooltip: {
	    	        trigger: 'item'
	    	    },
	    	    legend: {
	    	        orient: 'vertical',
	    	        left: 'left',
	    	    },
	    	    visualMap: {
	    	        min: 0,
	    	        max: 2500,
	    	        left: 'left',
	    	        top: 'bottom',
	    	        text: ['高','低'],           // 文本，默认为数值文本
	    	        calculable: true
	    	    },
	    	    toolbox: {
	    	        show: true,
	    	        orient: 'vertical',
	    	        left: 'right',
	    	        top: 'center'
	    	    },
	    	    series: [
	    	        {
	    	            name: '客户分布',
	    	            type: 'map',
	    	            mapType: 'china',
	    	            roam: false,
	    	            label: {
	    	                normal: {
	    	                    show: true
	    	                },
	    	                emphasis: {
	    	                    show: true
	    	                }
	    	            },
	    	            data:provinces
// 	[
// 	    	                {name: '北京',value: randomData() },
// 	    	                {name: '天津',value: randomData() },
// 	    	                {name: '上海',value: randomData() },
// 	    	                {name: '重庆',value: randomData() },
// 	    	                {name: '河北',value: randomData() },
// 	    	                {name: '河南',value: randomData() },
// 	    	                {name: '云南',value: randomData() },
// 	    	                {name: '辽宁',value: randomData() },
// 	    	                {name: '黑龙江',value: randomData() },
// 	    	                {name: '湖南',value: randomData() },
// 	    	                {name: '安徽',value: randomData() },
// 	    	                {name: '山东',value: randomData() },
// 	    	                {name: '新疆',value: randomData() },
// 	    	                {name: '江苏',value: randomData() },
// 	    	                {name: '浙江',value: randomData() },
// 	    	                {name: '江西',value: randomData() },
// 	    	                {name: '湖北',value: randomData() },
// 	    	                {name: '广西',value: randomData() },
// 	    	                {name: '甘肃',value: randomData() },
// 	    	                {name: '山西',value: randomData() },
// 	    	                {name: '内蒙古',value: randomData() },
// 	    	                {name: '陕西',value: randomData() },
// 	    	                {name: '吉林',value: randomData() },
// 	    	                {name: '福建',value: randomData() },
// 	    	                {name: '贵州',value: randomData() },
// 	    	                {name: '广东',value: randomData() },
// 	    	                {name: '青海',value: randomData() },
// 	    	                {name: '西藏',value: randomData() },
// 	    	                {name: '四川',value: randomData() },
// 	    	                {name: '宁夏',value: randomData() },
// 	    	                {name: '海南',value: randomData() },
// 	    	                {name: '台湾',value: randomData() },
// 	    	                {name: '香港',value: randomData() },
// 	    	                {name: '澳门',value: randomData() }
// 	    	            ]
	    	        }
	    	    ]
	    	};               
		
	    // 使用刚指定的配置项和数据显示图表。
	       myChart6.setOption(option6);
	    
			 // 基于准备好的dom，初始化echarts实例
	        var myChart7 = echarts.init(document.getElementById('data_age'));

	        // 指定图表的配置项和数据
	        option7 = {
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
			    	 y: 'bottom',
			        left: 'center',
			        selectedMode:false , //选择模式,默认开启值域开关
			        data: ['20-30岁员工','30-40岁员工','40-50岁员工','50-60岁员工',]
			    },
			    series : [
			        {
			            name: '员工年龄比例',
			            type: 'pie',
			            radius : '60%',
			            center: ['50%', '50%'],
			            data:[			                  
			                {value:numAgeTwenty, name:'20-30岁员工'},
			                {value:numAgeThirty, name:'30-40岁员工'},
			                {value:numAgeForty, name:'40-50岁员工'},
			                {value:numAgeFifty, name:'50-60岁员工'},
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
	        myChart7.setOption(option7);
</script>
</body>
</html>