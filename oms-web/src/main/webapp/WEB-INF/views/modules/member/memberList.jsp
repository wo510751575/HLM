<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>客户管理</title>
<meta name="decorator" content="default" />
<link href="${ctxStatic}/common/select2.css" type="text/css"
	rel="stylesheet" />
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var jBoxConfig = {};
						jBoxConfig.defaults = {
							width : 380,
							height : 'auto',
						}
						$.jBox.setDefaults(jBoxConfig);

						var importMsg = $("#importMsg").val();
						if (importMsg) {
							$.jBox.success(importMsg, '提示');
							$("#importMsg").val("");
						}

						//重置
						$("#btnReset").click(
								function() {
									$("#searchForm").find("input[type='text']")
											.val("");
									$("#searchForm").find("select option")
											.prop('selected', '');
								});

						$('.form-group-select-css').select2();
						//分配列表
						$('.allot_view')
								.click(
										function() {
											var shopNo = $(this).data("shopno");
											var noWx = $(this).data("nowx");
											var code = $(this).data("code");
											var memberNoGm = $(this).data(
													"membernogm");

											// 正常打开	
											top.$.jBox
													.open(
															"iframe:${ctx}/member/allotView?shopNo="
																	+ shopNo
																	+ "&code="
																	+ code
																	+ "&noWx="
																	+ noWx
																	+ "&memberNoGm="
																	+ memberNoGm,
															"导购列表",
															520,
															400,
															{//宽高
																id : 9527,
																draggable : true,
																showClose : true,
																buttons : {}, //去除按钮
																iframeScrolling : 'no',
																loaded : function(
																		h) {
																	top
																			.$(
																					'.jbox-container .jbox-title-panel')
																			.css(
																					"height",
																					"35px")
																			.css(
																					'background',
																					'#376EA5');
																	top
																			.$(
																					'.jbox-container .jbox-title')
																			.css(
																					'line-height',
																					'35px')
																			.css(
																					"color",
																					"#ffffff");
																},
																closed : function() {
																} /* 信息关闭后执行的函数 */
															});
										});

						//客户详情
						$('.view_btn')
								.click(
										function() {
											var id = $(this).attr("data-id");

											// 正常打开	
											top.$.jBox
													.open(
															"iframe:${ctx}/member/view?code="
																	+ id,
															"客户详情",
															920,
															730,
															{//宽高
																id : 9527,
																draggable : true,
																showClose : true,
																buttons : {}, //去除按钮
																iframeScrolling : 'no',
																loaded : function(
																		h) {
																	top
																			.$(
																					'.jbox-container .jbox-title-panel')
																			.css(
																					"height",
																					"35px")
																			.css(
																					'background',
																					'#376EA5');
																	top
																			.$(
																					'.jbox-container .jbox-title')
																			.css(
																					'line-height',
																					'35px')
																			.css(
																					"color",
																					"#ffffff");
																},
																closed : function() {
																} /* 信息关闭后执行的函数 */
															});
										});

						$("#btnExport")
								.click(
										function() {
											if (!$("#infolist tr").length) {
												alertx("没有数据无法导出!");
												return false;
											}
											top.$.jBox
													.confirm(
															"确认要导出客户数据吗？",
															"系统提示",
															function(v, h, f) {
																if (v == "ok") {
																	$(
																			"#searchForm")
																			.attr(
																					"action",
																					"${ctx}/member/export");
																	$(
																			"#searchForm")
																			.submit();
																}
															},
															{
																buttonsFocus : 1
															});
										});
						$("#btnImport")
								.click(
										function() {
											$
													.jBox(
															$("#importBox")
																	.html(),
															{
																title : "导入数据",
																buttons : {
																	"关闭" : true
																},
																bottomText : "导入文件不能超过10M，仅允许导入“xls”或“xlsx”格式文件！"
															});
										});
						//批量添加标签
						$("#btnBatchAddTags")
								.click(
										function() {
											//获取勾选的值
											var memberNoArr = new Array;
											var shopWxArr = new Array;
											$("#infolist :checkbox[checked]")
													.each(
															function(i) {
																memberNoArr[i] = $(
																		this)
																		.val();
																shopWxArr[i] = $(
																		this)
																		.attr(
																				"data-shopWx");
															});
											var memberNos = memberNoArr
													.join(',');//转换为逗号隔开的字符串
											if (!memberNos) {
												alertx("请选择客户!");
												return false;
											}
											var shopWx = shopWxArr[0];

											// 正常打开	
											top.$.jBox
													.open(
															"iframe:${ctx}/member/tagView?shopWx="
																	+ shopWx
																	+ "&single=0&memberNos="
																	+ memberNos
																	+ "&merchantNo=${merchantNo}",
															"标签选择",
															520,
															400,
															{//宽高
																id : 1111,
																draggable : true,
																showClose : true,
																buttons : {}, //去除按钮
																iframeScrolling : 'no',
																loaded : function(
																		h) {
																	top
																			.$(
																					'.jbox-container .jbox-title-panel')
																			.css(
																					"height",
																					"35px")
																			.css(
																					'background',
																					'#376EA5');
																	top
																			.$(
																					'.jbox-container .jbox-title')
																			.css(
																					'line-height',
																					'35px')
																			.css(
																					"color",
																					"#ffffff");
																},
																closed : function() {
																}
															});
										});
						//批量分组
						$("#btnBatchChangePmType")
								.click(
										function() {
											//获取勾选的值
											var pmCodeArr = new Array;
											$("#infolist :checkbox[checked]")
													.each(
															function(i) {
																pmCodeArr[i] = $(
																		this)
																		.data(
																				"code");
															});
											var pmCodes = pmCodeArr.join(',');//转换为逗号隔开的字符串
											if (!pmCodes) {
												alertx("请选择客户!");
												return false;
											}

											// 正常打开	
											top.$.jBox
													.open(
															"iframe:${ctx}/member/batchChangePmTypeSelect?pmCodes="
																	+ pmCodes
																	+ "&merchantNo=${merchantNo}",
															"批量分组",
															520,
															230,
															{//宽高
																id : 1111,
																draggable : true,
																showClose : true,
																buttons : {}, //去除按钮
																iframeScrolling : 'no',
																loaded : function(
																		h) {
																	top
																			.$(
																					'.jbox-container .jbox-title-panel')
																			.css(
																					"height",
																					"35px")
																			.css(
																					'background',
																					'#376EA5');
																	top
																			.$(
																					'.jbox-container .jbox-title')
																			.css(
																					'line-height',
																					'35px')
																			.css(
																					"color",
																					"#ffffff");
																},
																closed : function() {
																}
															});
										});

						//添加标签
						$(".addTag_btn")
								.click(
										function() {
											var memberNos = $(this).data(
													"member");
											var shopWx = $(this).attr(
													"data-shopWx");
											// 正常打开	
											top.$.jBox
													.open(
															"iframe:${ctx}/member/tagView?shopWx="
																	+ shopWx
																	+ "&single=1&memberNos="
																	+ memberNos,
															"标签选择",
															520,
															400,
															{//宽高
																id : 1112,
																draggable : true,
																showClose : true,
																buttons : {}, //去除按钮
																iframeScrolling : 'no',
																loaded : function(
																		h) {
																	top
																			.$(
																					'.jbox-container .jbox-title-panel')
																			.css(
																					"height",
																					"35px")
																			.css(
																					'background',
																					'#376EA5');
																	top
																			.$(
																					'.jbox-container .jbox-title')
																			.css(
																					'line-height',
																					'35px')
																			.css(
																					"color",
																					"#ffffff");
																},
																closed : function() {
																}
															});
										});

						//添加好友
						$(".addFriends_btn").click(function() {
							var code = $(this).data("basecode");
							var memberNoGM = $(this).data("membernogm");

							var url = '${ctx}/member/doListApplayFriend';
							var params = {
								code : code,
								memberNoGM : memberNoGM
							};
							$.post(url, params, function(data) {
								if (data.success) {
									$.jBox.success('好友申请已发出', '提示');
								} else {
									$.jBox.info(data.msg, '友情提示');
								}
							}, 'json');
						});

						//全选、全不选
						$("#allcheck").click(
								function() {
									if (this.checked) {
										$("#infolist :checkbox").prop(
												"checked", true);
									} else {
										$("#infolist :checkbox").prop(
												"checked", false);
									}
								});

						//设置全选复选框
						$("#infolist :checkbox").click(function() {
							allchk();
						});
						//发送素材
						$("#sendMaterial")
								.click(
										function() {
											var chk = 0;
											$("#infolist :checkbox")
													.each(
															function() {
																if ($(this)
																		.prop(
																				"checked") == true) {
																	chk++;
																}
															});
											if (chk == 0) {
												showTip("请选择要发送的客户!", "info");
											} else {
												//获取勾选的值
												var valArr = new Array;
												$(
														"#infolist :checkbox[checked]")
														.each(
																function(i) {
																	valArr[i] = $(
																			this)
																			.val();
																});
												var vals = valArr.join(',');//转换为逗号隔开的字符串

												//弹出框
												top.$.jBox
														.open(
																"",
																"素材选择",
																920,
																730,
																{//宽高
																	id : 9527,
																	draggable : true,
																	showClose : true,
																	buttons : {}, //去除按钮
																	iframeScrolling : 'no',
																	loaded : function(
																			h) {
																		top
																				.$(
																						'.jbox-container .jbox-title-panel')
																				.css(
																						"height",
																						"35px")
																				.css(
																						'background',
																						'#376EA5');
																		top
																				.$(
																						'.jbox-container .jbox-title')
																				.css(
																						'line-height',
																						'35px')
																				.css(
																						"color",
																						"#ffffff");
																	},
																	closed : function() {
																	} /* 信息关闭后执行的函数 */
																});
											}
										});

						//发送短信
						$("#sendSms")
								.click(
										function() {
											var chk = 0;
											$("#infolist :checkbox")
													.each(
															function() {
																if ($(this)
																		.prop(
																				"checked") == true) {
																	chk++;
																}
															});
											if (chk == 0) {
												showTip("请选择要发送的客户!", "info");
											} else {
												//获取勾选的值
												var valArr = new Array;
												$(
														"#infolist :checkbox[checked]")
														.each(
																function(i) {
																	valArr[i] = $(
																			this)
																			.val();
																});
												var vals = valArr.join(',');//转换为逗号隔开的字符串

												//弹出框
												top.$.jBox
														.open(
																"",
																"素材选择",
																920,
																730,
																{//宽高
																	id : 9527,
																	draggable : true,
																	showClose : true,
																	buttons : {}, //去除按钮
																	iframeScrolling : 'no',
																	loaded : function(
																			h) {
																		top
																				.$(
																						'.jbox-container .jbox-title-panel')
																				.css(
																						"height",
																						"35px")
																				.css(
																						'background',
																						'#376EA5');
																		top
																				.$(
																						'.jbox-container .jbox-title')
																				.css(
																						'line-height',
																						'35px')
																				.css(
																						"color",
																						"#ffffff");
																	},
																	closed : function() {
																	} /* 信息关闭后执行的函数 */
																});
											}
										});

						//聊天记录验证
						$(".close").click(function() {
							$("#passwords").hide();
						});

$(".btn-default").click(function() {
var noWx = $(this).attr("data-wx");
// var memberNoGm = $(this).attr("data-memberNoGm");
var noWxGm = $(this).data("nowxgm");
var html = "<div style='padding:10px;'>查看密码：<input type='password' id='password' name='password' /></div>";
$.jBox(html,{
		title : "请输入查看密码？",
		submit : function(v, h, f) {
			if (f.password == '') {
				$.jBox.tip("请输入您的密码。",'error',{focusId : "password"}); // 关闭设置 yourname 为焦点
				return false;
			} else {
				$.ajax({
						type : "POST",
						url : "${ctx}/weixin/chatInfo/psw",
						data : {
							psw : f.password
						},
						success : function(result) {
							if (result) {
// 								window.location.href = "${ctx}/weixin/imChatInfo?talker= "+ noWx+ "&memberNoGm="+ memberNoGm;
								imChat(noWxGm,noWx);
							} else {
								showTip("密码错误！","error");
							}
						}
					});
			}
			return true;
		}
	});
});

						//批量取消认领
						$("#btnCancleBinds")
								.click(
										function() {
											var chk = 0;
											$("#infolist :checkbox")
													.each(
															function() {
																if ($(this)
																		.prop(
																				"checked") == true) {
																	chk++;
																}
															});
											if (chk == 0) {
												showTip("请选择要取消的客户!", "info");
											} else {
												top.$.jBox
														.confirm(
																"确认要批量取消认领这些客户吗？",
																"系统提示",
																function(v, h,
																		f) {
																	if (v == "ok") {
																		//获取勾选的值
																		var list = new Array();
																		$(
																				"#infolist :checkbox[checked]")
																				.each(
																						function(
																								i) {
																							var map = {};
																							var gmNo = $(
																									this)
																									.attr(
																											"data-gmNo");
																							var wxNo = $(
																									this)
																									.attr(
																											"data-wxNo");
																							var noWxGm = $(
																									this)
																									.attr(
																											"data-shopWx");
																							map = {
																								"gmNo" : gmNo,
																								"wxNo" : wxNo,
																								"noWxGm" : noWxGm
																							};
																							list
																									.push(map);
																						});
																		$
																				.ajax({
																					type : "POST",
																					url : "${ctx}/member/cancleBingFriends",
																					data : {
																						list : list
																					},
																					success : function(
																							data) {
																						if (data.result) {
																							showTip("操作成功");
																							setTimeout(
																									function() {
																										window.location
																												.reload();
																									},
																									2000);
																						} else {
																							showTip(
																									data.msg,
																									"error");
																						}
																					}
																				});
																	}
																},
																{
																	buttonsFocus : 1
																});

											}
										});
					});

	function allchk() {
		var chknum = $("#infolist :checkbox").size();//选项总个数
		var chk = 0;
		$("#infolist :checkbox").each(function() {
			if ($(this).prop("checked") == true) {
				chk++;
			}
		});
		if (chknum == chk) {//全选
			$("#allcheck").prop("checked", true);
		} else {//不全选
			$("#allcheck").prop("checked", false);
		}
	}
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").attr("action", "${ctx}/member/list/");
		$("#searchForm").submit();
		return false;
	}
	function CancelQuery() {
		$(':input', '#searchForm').not(':button, :submit, :reset').val('')
				.removeAttr('checked').removeAttr('selected');

		// 			$("#pageNo").val(1);
		// 			$("#searchForm").submit();
	}
</script>
<style type="text/css">
.img-circle {
	width: 40px;
	height: 40px;
	border-radius: 20px;
	vertical-align: middle;
}

#wechart_info .personal_header .hrf {
	width: 35px;
	height: 18px;
	margin: 0px 15px;
	background: url("${ctxStatic}/images/hrf.png") no-repeat 50%;
}

.container {
	padding: 10px 30px;
	width: 100%;
	min-height: 800px;
	background: #fff;
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
}

#myModal {
	position: fixed;
	top: 10%;
	left: 50%;
	z-index: 1050;
	width: 560px;
	margin-left: -280px;
	background-color: #fff;
	border: 1px solid #999;
	border: 1px solid rgba(0, 0, 0, 0.3);
	-webkit-border-radius: 6px;
	-moz-border-radius: 6px;
	border-radius: 6px;
	outline: 0;
	-webkit-box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);
	-moz-box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);
	box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);
	-webkit-background-clip: padding-box;
	-moz-background-clip: padding-box;
	background-clip: padding-box;
}

.img-circle {
	width: 40px;
	height: 40px;
	border-radius: 20px;
	vertical-align: middle;
}

.modal.fade.in {
	top: 5%;
}

::-webkit-scrollbar {
	width: 4px;
	height: 4px;
}

.top1 {
	text-align: center;
	background: black;
	padding: 10px;
	border-radius: 5px;
	opacity: 0.2;
	color: white;
}

#chatInfo {
	position: fixed;
	top: 0px;
	width: 500px;
	height: 100%;
	background: url("${ctxStatic}/images/iphone2.png") no-repeat;
	background-size: 100% 100%;
	left: 25%;
	z-index: 100;
}

#chatInfo #chatDetail {
	width: 337px;
	height: 60%;
	position: relative;
	z-index: 200;
	margin-left: 82px;
	top: 18%;
	overflow: auto;
	overflow-x: hidden;
	background-color: rgb(236, 234, 234);
}

#chatDetail ul {
	width: 97%;
	height: auto;
	list-style: none;
	margin: 2%;
	overflow: hidden;
}

#chatDetail ul li {
	width: 93%;
	margin-bottom: 10px;
	overflow: hidden;
}

.mem_head {
	width: 40px;
	height: 40px;
}

.mem_info {
	width: 77%;
	margin: 0 2%;
}

.left {
	float: left;
}

.right {
	float: right;
}

.mem_name_right {
	text-align: right;
}

.send_info, .send_info_right {
	padding: 9px;
	background: #a7da58;
	border-radius: 5px;
	display: inline-block;
	position: relative;
	color: black;
	word-break: break-all;
}

.send_info {
	background: #fff;
}

.send_info:after {
	content: '\25E3';
	position: absolute;
	display: inline-block;
	color: #fff;
	top: 7px;
	left: -2px;
	width: 10px;
	height: 10px;
	font-size: 19px;
	line-height: 20px;
	-webkit-transform: rotate(45deg);
	-moz-transform: rotate(45deg);
	-ms-transform: rotate(45deg);
	-o-transform: rotate(45deg);
}

.send_info_right:after {
	content: '\25E3';
	position: absolute;
	display: inline-block;
	color: #a7da58;
	top: 13px;
	right: 0px;
	width: 10px;
	height: 10px;
	font-size: 19px;
	line-height: 20px;
	-webkit-transform: rotate(225deg);
	-moz-transform: rotate(225deg);
	-ms-transform: rotate(225deg);
	-o-transform: rotate(225deg);
}

.audio-box {
	padding-left: 20px;
	margin-left: 10px;
	display: inline-block;
	width: 60%;
	height: 40px;
	line-height: 42px;
	border-radius: 5px;
	border: 1px solid #eeeeee;
	background: #fff;
	position: relative;
}

.audio-box:before {
	content: '';
	position: absolute;
	left: -9px;
	top: 50%;
	margin-top: -7.5px;
	width: 15px;
	height: 15px;
	border: 1px solid #eee;
	transform: rotate(45deg);
	background: #fff;
	border-color: transparent transparent #eee #eee;
}

.audio-box_right {
	padding-left: 20px;
	display: inline-block;
	width: 60%;
	height: 40px;
	line-height: 42px;
	border-radius: 5px;
	border: 1px solid #a7da58;
	background: #a7da58;
	position: relative;
	float: right;
}

.audio-box_right:before {
	content: '';
	position: absolute;
	right: -3px;
	top: 50%;
	margin-top: -7.5px;
	width: 7px;
	height: 7px;
	border: 1px solid #eee;
	transform: rotate(225deg);
	background: #a7da58;
	border-color: transparent transparent #a7da58 #a7da58;
}

.icon-audio {
	margin-left: 20px;
	display: inline-block;
	width: 13px;
	height: 23px;
	background: url(${ctxStatic}/images/audio_icon01.png) no-repeat;
	background-size: 300% 100%;
	vertical-align: middle;
}

.icon-audio_right {
	margin-right: 10px;
	display: inline-block;
	width: 13px;
	height: 23px;
	background: url(${ctxStatic}/images/audio_icon02.png) no-repeat;
	vertical-align: middle;
}

.ml10 {
	margin-left: 7px;
}

.ml10_right {
	margin-right: 7px;
	float: right;
	margin-top: 12px;
}

.video-ifram {
	width: 80%;
	height: 95px;
	background: url(${ctxStatic}/images/player_icon.png) no-repeat center
		center;
	background-color: #222222;
}

#loading {
	width: 36px;
	height: 36px;
	margin: 10px auto;
	border-radius: 50%;
	overflow: hidden;
	background: url(${ctxStatic}/images/loading.gif) no-repeat center center;
	display: none;
}

#chatInfobox {
	position: fixed;
	z-index: 12;
	width: 100%;
	height: 100%;
	top: 0;
	display: none;
}

.title_name {
	position: absolute;
	top: 14.5%;
	color: white;
	font-size: 15px;
	text-align: center;
	width: 100%;
}

.nav_icon {
	width: 100%;
	border-top: 1px solid #ccc;
	margin: 0px;
	font-size: 11px;
}

.local span {
	display: inline-block;
	margin-top: 0px;
}

.local span:nth-child(1) {
	width: 25%;
}

.local span:nth-child(1) img {
	margin-top: -10px;
}

.local span:nth-child(2) {
	width: 75%;
}
</style>
</head>
<body>
	<div class="container">
		<input id="importMsg" name="importMsg" style="display: none"
			value="${importMsg}" />
		<div id="importBox" class="hide">
			<form id="importForm" action="${ctx}/member/import" method="post"
				enctype="multipart/form-data"
				style="padding-left: 20px; text-align: center;" class="form-search"
				onsubmit="loading('正在导入，请稍等...');">
				<br /> <input id="uploadFile" name="file" type="file"
					style="width: 330px" /><br />
				<br /> <input id="btnImportSubmit" class="btn btn-primary"
					type="submit" value="   导    入   " /> <a
					href="${ctx}/member/import/template">下载模板</a>

			</form>
		</div>

		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx}/member/list/">客户列表</a></li>
		</ul>


		<form id="searchForm" action="${ctx}/member/list/" method="post"
			class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
			<input id="pageSize" name="pageSize" type="hidden"
				value="${page.pageSize}" />
			<ul class="ul-form">
				<li><label>客户姓名：</label> <input type="text" name="memberName"
					value="${paramMember.memberName}" class="input-medium"
					maxlength="100" placeholder="客户姓名/备注名"></li>
				<li><label>手机号：</label> 
				<input type="text" name="mobile" value="${paramMember.mobile}" class="input-medium" maxlength="11" placeholder="手机号"></li>
				<!-- 			<li><label>旺旺：</label> -->
				<%-- 				<input type="text" name="noWw" value="${paramMember.noWw}" class="input-medium" maxlength="11" placeholder="旺旺"> --%>
				<!-- 			</li> -->
				<li><label>所在地区：</label> <tags:treeselect id="area"
						name="areaId" value="${areaId}" labelName="areaName"
						labelValue="${areaName}" title="所在地区" url="/sys/area/treeData"
						cssClass="required" /></li>
				<li><label>注册时间：</label> <input id="beginDate" name="startTime"
					type="text" readonly="readonly" maxlength="20"
					class="input-mini Wdate"
					value="<fmt:formatDate value="${paramMember.startTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
					-- <input id="endDate" name="endTime" type="text"
					readonly="readonly" maxlength="20" class="input-mini Wdate"
					value="<fmt:formatDate value="${paramMember.endTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />&nbsp;&nbsp;
				</li>
			</ul>
			<ul class="ul-form">
				<li><label>客户分组：</label> <select type="text" id="typeCode"
					name="pmTypeCode" class="input-medium" style="width: 180px">
						<option value="">全部</option>
						<c:forEach var="pmType" items="${pmTypeList}">
							<option value="${pmType.code }"
								<c:if test="${pmType.code eq paramMember.typeCode}">selected="selected"</c:if>>
								${pmType.typeName}</option>
						</c:forEach>
				</select></li>
				<li><label>客户微信：</label> <input type="text" name="noWx"
					value="${paramMember.noWx }" class="input-medium"
					placeholder="客户微信"></li>
				<li><label>终端微信：</label> <input type="text" name="shopWx"
					value="${paramMember.shopWx }" class="input-medium"
					placeholder="终端微信"></li>
				<li><label>所属导购：</label> <input type="text" name="memberNameGm"
					value="${paramMember.memberNameGm }" class="input-medium"
					placeholder="所属导购"></li>
				<li class="btns"><input id="btnSubmit" class="btn btn-primary"
					type="submit" value="查询" onclick="return page();" /></li>
				<li class="btns"><input id="btnReset" class="btn btn-primary"
					type="button" value="重置" /></li>
				<!-- 	<li class="btns"><input id="sendMaterial" class="btn btn-primary" type="reset"  value="批量发送素材"/></li>
			<li class="btns"><input id="sendSms" class="btn btn-primary" type="reset"  value="批量发送短信"/></li> -->
				<li class="btns"><input id="btnExport" class="btn btn-primary"
					type="button" value="导出" /></li>
				<!-- 			<li class="btns"><input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li> -->
				<li class="btns"><input id="btnBatchAddTags"
					class="btn btn-primary" type="button" value="批量添加标签" /></li>
				<li class="btns"><input id="btnCancleBinds"
					class="btn btn-primary" type="button" value="批量取消认领" /></li>
				<!-- 			<li class="btns"><input id="btnBatchChangePmType" class="btn btn-primary" type="button" value="批量分组"/></li> -->
				<li class="clearfix"></li>
			</ul>
		</form>
		<tags:message content="${message}" />
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>全选<input type="checkbox" id="allcheck" /></th>
					<th style="min-width: 45px;">头像</th>
					<th>客户姓名</th>
					<th>微信号</th>
					<th>微信昵称</th>
					<th>手机号</th>
					<th>旺旺</th>
					<th>性别</th>
					<th>所属导购</th>
					<th>所属终端</th>
					<th>所在地区</th>
					<th>分类</th>
					<th>标签</th>
					<th>添加方式</th>
					<th>注册时间</th>
					<shiro:hasPermission name="member:member:edit">
						<th>操作</th>
					</shiro:hasPermission>
				</tr>
			</thead>
			<tbody id="infolist">
				<c:forEach items="${page.list}" var="member" varStatus="number">
					<tr>
						<td><input type="checkbox" value="${member.memberNo}"
							data-shopWx="${member.shopWx }" data-code="${member.code}"
							data-gmNo="${member.memberNoGm}" data-wxNo="${member.noWx}" /></td>
						<td><c:if test="${empty member.headAddress}">
								<img class="img-circle"
									src="${ctxStatic}/admin/images/introduce/file.png">
							</c:if> <c:if test="${not empty member.headAddress}">
								<c:if test="${fns:startsWith(member.headAddress,'http')}">
									<img class="img-circle" src="${member.headAddress}">
								</c:if>
								<c:if test="${!fns:startsWith(member.headAddress,'http')}">
									<img class="img-circle"
										src="${fns:getUploadUrl()}${member.headAddress}">
								</c:if>
							</c:if></td>

						<td>${member.memberName}</td>
						<td>${empty member.noWxAlias?member.noWx:member.noWxAlias}</td>
						<td>${member.nickNameWx}</td>
						<td>
							${fn:replace(member.mobile,",","<br>")}
						</td>
						<td>${member.noWw}</td>
						<td><c:if test="${member.sex=='MALE'}">男</c:if> <c:if
								test="${member.sex=='FEMALE'}">女</c:if></td>
						<td>${member.memberNameGm}</td>
						<td>${member.bomName}</td>
						<td>
							${fns:getAreaName(member.provinceCode)}${fns:getAreaName(member.cityCode)}${fns:getAreaName(member.cityAreaCode)}
						</td>
						<td>${member.pmTypeName}</td>
						<td>${member.remark}</td>
						<td><c:if test="${member.addType eq 1}">导购扫码添加</c:if> <c:if
								test="${member.addType eq 2}">客户扫码添加</c:if> <c:if
								test="${member.addType eq 3}">导购手动新增</c:if> <c:if
								test="${member.addType eq 4}">微信自动导入</c:if> <c:if
								test="${member.addType eq 5}">手机号添加</c:if> <c:if
								test="${member.addType eq 6}">微信号添加</c:if> <c:if
								test="${member.addType eq 7}">QQ号添加</c:if></td>
						<td><fmt:formatDate value="${member.createDate}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<shiro:hasPermission name="member:member:edit">
							<td>
								<c:if test="${!empty member.noWx}">
									<a href="javascript:;" data-wx="${member.noWx}" data-memberNoGm="${member.memberNoGm}" data-nowxgm="${member.shopWx}" class="btn-default">聊天记录</a>
								</c:if> <%-- 	<a href="${ctx}/weixin/friendsInfo?authorid=${member.noWx}">朋友圈</a> --%>
								<a href="javascript:;" class="view_btn" data-id="${member.code}">查看</a>
								<a href="javascript:;" class="addTag_btn"
								data-member="${member.memberNo}" data-shopWx="${member.shopWx}">添加标签</a>
								<c:if test="${not empty member.mobile and empty member.noWx}">
									<a href="javascript:;" class="addFriends_btn"
										data-basecode="${member.baseCode}"
										data-memberNoGm="${member.memberNoGm}">添加好友</a>
								</c:if></td>
						</shiro:hasPermission>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
	</div>

	<div id="chatInfobox">
		<input id="lastPage" type="hidden" />
		<input id="next" type="hidden" />
		<input id="chatNoWxGm" type="hidden" />
		<input id="chatNoWx" type="hidden" />
		<div id="chatInfo">
			<div class="title_name"></div>
			<div id="chatDetail">
				<div id="loading"></div>
				<ul id="chatDetailUl">
				</ul>
			</div>
		</div>
	</div>
	<script type="text/javascript">
$(document).ready(function() {
		
//滑动向后台请求分页
$('#chatDetail').on('scroll',function() {
	var sct = $('#chatDetail').scrollTop();
	var next = $("#next").val();
	var lastPage = $("#lastPage").val();
	var noWxGm=$("#chatNoWxGm").val();
	var noWx= $("#chatNoWx").val();
	
	if (sct == 0 && lastPage == "false") {
		$("#loading").css("display", "block");
		$.ajax({
					type : "POST",
					url : "${ctx}/weixin/imChatInfo/view",
					data : {
						noWxGm : noWxGm,
						noWx : noWx,
						pageNo : next,
						pageSize : 10
					},
					dataType : 'JSON',
					success : function(result) {
						$("#loading").css("display","none");
						var html = "";
						$("#next").val(result.next);
						$("#lastPage").val(
										result.lastPage);
						result = result.list;
						if (result != undefined
								&& result.length > 0) {
							for (var i = result.length - 1; i >= 0; i--) {
								if (isEmptyObject(result[i].gmPhoto)) {
									result[i].gmPhoto = "/oms-web/static/admin/images/introduce/file.png";
								}
								if (result[i].senderFlag == 1) { //issend==1表示是自己发的信息
									var info1 = '';
									if (result[i].type == '1'
											|| result[i].type == '50'
											|| result[i].type == '10000'
											|| result[i].type == '318767153') {
										info1 = '<span class="send_info_right right">'
												+ result[i].content
												+ '</span>';
									} else if (result[i].type == '49'
											|| result[i].type == '490'
											|| result[i].type == '491'
											|| result[i].type == '492'
											|| result[i].type == '493') {
										var sType = '';
										if (result[i].type == '490')
											sType = '素材';
										else if (result[i].type == '491')
											sType = '优惠券';
										else if (result[i].type == '492')
											sType = '活动';
										else if (result[i].type == '493')
											sType = '名片';
										info1 = '<span class="send_info_right right">'
												+ '<a href="'+result[i].shareUrl+'" target="_blank" title="'+result[i].shareTitle+'">[分享'
												+ sType
												+ ']</a>'
												+ '</span>';
									} else if (result[i].type == '3'
											|| result[i].type == '47') {
										var resourcesPath = '';
										if (result[i].resourcesPath) {
											resourcesPath = result[i].resourcesPath
										} else {
											resourcesPath = '${ctxStatic}/images/defaultImg.png';
										}
										info1 = '<span class="send_info_right right"> <img src="'+resourcesPath+'" width="100px"/></span>';
									} else if (result[i].type == '34') {
										info1 = '<div class="audio-centent"><div class="audio-box_right"><span class="icon-audio"></span>'
												+ '<span class="">语音标题</span></div><span class="ml10_right"></span></div>'
									} else if (result[i].type == '43') {
										info1 = '<div class="video-ifram"></div></div></div><audio controls="controls" class="audioMedia" style="display: none" src=""></audio>'
									} else if (result[i].type == '42') {
										info1 = '<span class="send_info_right right"> <div>'
												+ result[i].content
												+ '<p class="nav_icon">个人名片</p></div></span>';
									} else if (result[i].type == '48') {
										info1 = '<span class="send_info_right right"> <div class="local"><span><img src="${ctxStatic}/images/location.png" width="40px"></span><span>'
												+ result[i].content
														.substring(
																result[i].content
																		.indexOf('label="') + 7,
																result[i].content
																		.indexOf('" poiname'))
												+ '</span></div></span>';
									} else if (result[i].type == '10000') {
										html = html
												+ '<li class="top1">'
												+ result[i].content
												+ '</li>'
										continue;
									}
									html = html
											+ '<li class="right"><div class="mem_head right"><img src="'+uploadUrl+result[i].gmPhoto+ 
        				 '" /></div><div class="mem_info right">'
											+ info1
											+ '</div></li>'
								} else if (result[i].senderFlag == 2) { //issend==2表示是对方发的信息
									var info = ""
									if (result[i].type == '1'
											|| result[i].type == '50'
											|| result[i].type == '10000'
											|| result[i].type == '318767153') {
										info = '<span class="send_info">'
												+ result[i].content
												+ '</span>';
									} else if (result[i].type == '49') {
										info = '<span class="send_info">'
												+ '<a href="'+result[i].shareUrl+'" target="_blank" title="'+result[i].shareTitle+'">[分享]</a>'
												+ '</span>';
									} else if (result[i].type == '3'
											|| result[i].type == '47') {
										var resourcesPath = '';
										if (result[i].resourcesPath) {
											resourcesPath = result[i].resourcesPath
										} else {
											resourcesPath = '${ctxStatic}/images/defaultImg.png';
										}
										info = '<span class="send_info"> <img src="'+resourcesPath+'" width="100px"/></span>';
									} else if (result[i].type == '34') {
										info = '<div class="audio-centent"><div class="audio-box"><span class="">语音标题</span>'
												+ '<span class="icon-audio"></span></div><span class="ml10"></span></div><audio controls="controls" class="audioMedia" style="display: none" src=""></audio>'
									} else if (result[i].type == '43') {
										info = '<div class="video-ifram"></div>'
									} else if (result[i].type == '42') {
										info = '<span class="send_info"> <div>'
												+ result[i].content
												+ '<p class="nav_icon">个人名片</p></div></span>';
									} else if (result[i].type == '48') {
										info1 = '<span class="send_info"> <div class="local"><span><img src="${ctxStatic}/images/location.png" width="40px"></span><span>'
												+ result[i].content
														.substring(
																result[i].content
																		.indexOf('label="') + 7,
																result[i].content
																		.indexOf('" poiname'))
												+ '</span></div></span>';
									} else if (result[i].type == '10000') {
										html = html
												+ '<li class="top1">'
												+ result[i].content
												+ '</li>'
										continue;
									}
									html = html
											+ '<li class="left"><div class="mem_head left"> <img src="'+result[i].pmPhoto+ 
        				 '" /></div><div class="mem_info left">'
											+ info
											+ '</div></li>'
								}
							}

							$("#chatDetail ul").prepend(html);
						}
					}
				});
	}
});

	$("#chatInfobox").click(function(e) {
		var target = $(e.target);
		if (target.closest('#chatInfo').length == 0) {
			$("#chatInfobox").hide();
		}
	});
});


//聊天详情
function imChat(noWxGm,noWx) {
	$("#chatDetail ul li").remove();
	$("#chatNoWxGm").val(noWxGm);
	$("#chatNoWx").val(noWx);
	
	$.ajax({
		type : "POST",
		url : "${ctx}/weixin/imChatInfo/view",
		data : {
			noWxGm : noWxGm,
			noWx : noWx,
			pageSize : 10/* ,
			memberNoGm : memberNo */
		},
		dataType : 'JSON',
		success : function(result) {
			var html = "";
			$("#next").val(result.next);
			$("#lastPage").val(result.lastPage);
			result = result.list;
			if (result != undefined && result.length > 0) {
				$(".title_name").text(result[0].pmNickName);
				for (var i = result.length - 1; i >= 0; i--) {
					if (isEmptyObject(result[i].gmPhoto)) {
						result[i].gmPhoto = "/oms-web/static/admin/images/introduce/file.png";
					}
					if (result[i].senderFlag == 1) { //issend==1表示是自己(导购)发的信息

						var info1 = '';
						//消息类型 1文本 3图片 34语音 43视频 42名片 47图片表情 48定位 49分享 50视频聊天 10000系统信息（添加好友）318767153系统安全提示
						//导购 IM新增类型 490素材 491优惠券 492活动 493名片
						if (result[i].type == '1'
								|| result[i].type == '50'
								|| result[i].type == '318767153') {
							info1 = '<span class="send_info_right right">'
									+ result[i].content
									+ '</span>';
						} else if (result[i].type == '49'
								|| result[i].type == '490'
								|| result[i].type == '491'
								|| result[i].type == '492'
								|| result[i].type == '493') {
							var sType = '';
							if (result[i].type == '490')
								sType = '素材';
							else if (result[i].type == '491')
								sType = '优惠券';
							else if (result[i].type == '492')
								sType = '活动';
							else if (result[i].type == '493')
								sType = '名片';
							info1 = '<span class="send_info_right right">'
									+ '<a href="'+result[i].shareUrl+'" target="_blank" title="'+result[i].shareTitle+'">[分享'
									+ sType
									+ ']</a>'
									+ '</span>';
						} else if (result[i].type == '3'
								|| result[i].type == '47') {
							var resourcesPath = '';
							if (result[i].resourcesPath) {
								resourcesPath = result[i].resourcesPath
							} else {
								resourcesPath = '${ctxStatic}/images/defaultImg.png';
							}
							info1 = '<span class="send_info_right right"> <img src="'+resourcesPath+'" width="100px"/></span>';
						} else if (result[i].type == '34') {
							info1 = '<div class="audio-centent"><div class="audio-box_right"><span class="icon-audio_right"></span>'
									+ '<span class="">语音标题</span></div><span class="ml10_right"></span></div>'
						} else if (result[i].type == '43') {
							info1 = '<div class="video-ifram"></div></div></div><audio controls="controls" class="audioMedia" style="display: none" src=""></audio>'
						} else if (result[i].type == '42') {
							info1 = '<span class="send_info_right right"> <div>'
									+ result[i].content
									+ '<p class="nav_icon">个人名片</p></div></span>';
						} else if (result[i].type == '48') {
							info1 = '<span class="send_info_right right"> <div class="local"><span><img src="${ctxStatic}/images/location.png" width="40px"></span><span>'
									+ result[i].content
											.substring(
													result[i].content
															.indexOf('label="') + 7,
													result[i].content
															.indexOf('" poiname'))
									+ '</span></div></span>';
						} else if (result[i].type == '10000') {
							html = html
									+ '<li>'
									+ result[i].content
									+ '</li>'
							continue;
						}
						html = html
								+ '<li class="right"><div class="mem_head right"><img src="'+uploadUrl+result[i].gmPhoto+ 
				 '" /></div><div class="mem_info right">'
								+ info1
								+ '</div></li>'
					} else if (result[i].senderFlag == 2) { //issend==2表示是对方(客户)发的信息
						var info = ""
						if (result[i].type == '1'
								|| result[i].type == '50'
								|| result[i].type == '318767153') {
							info = '<span class="send_info">'
									+ result[i].content
									+ '</span>';
						} else if (result[i].type == '49') {
							info = '<span class="send_info">'
									+ '<a href="'+result[i].shareUrl+'" target="_blank" title="'+result[i].shareTitle+'">[分享]</a>'
									+ '</span>';
						} else if (result[i].type == '3'
								|| result[i].type == '47') {
							var resourcesPath = '';
							if (result[i].resourcesPath) {
								resourcesPath = result[i].resourcesPath
							} else {
								resourcesPath = '${ctxStatic}/images/defaultImg.png';
							}
							info = '<span class="send_info"> <img src="'+resourcesPath+'" width="100px"/></span>';
						} else if (result[i].type == '34') {
							info = '<div class="audio-centent"><div class="audio-box"><span class="">语音标题</span>'
									+ '<span class="icon-audio"></span></div><span class="ml10"></span></div><audio controls="controls" class="audioMedia" style="display: none" src=""></audio>'
						} else if (result[i].type == '43') {
							info = '<div class="video-ifram"></div>'
						} else if (result[i].type == '42') {
							info = '<span class="send_info"> <div>'
									+ result[i].content
									+ '<p class="nav_icon">个人名片</p></div></span>';
						} else if (result[i].type == '48') {
							info1 = '<span class="send_info"> <div class="local"><span><img src="${ctxStatic}/images/location.png" width="40px"></span><span>'
									+ result[i].content
											.substring(
													result[i].content
															.indexOf('label="') + 7,
													result[i].content
															.indexOf('" poiname'))
									+ '</span></div></span>';
						} else if (result[i].type == '10000') {
							html = html
									+ '<li class="top1">'
									+ result[i].content
									+ '</li>'
							continue;
						}
						html = html+ '<li class="left"><div class="mem_head left"> <img src="'+result[i].pmPhoto+ 
				 '" /></div><div class="mem_info left">'
								+ info
								+ '</div></li>'
					}
				}
				$("#chatDetail ul").append(html);
				var h = document.getElementById('chatDetailUl').scrollHeight;
				$('#chatDetail').scrollTop(750);
			} else {
				showTip("网络异常!","info");
			}
		}
	});
	$("#chatInfobox").css("display","block");
}

function isEmptyObject(e) {
	var t;
	for (t in e)
		return !1;
	return !0
}
</script>
</body>
</html>