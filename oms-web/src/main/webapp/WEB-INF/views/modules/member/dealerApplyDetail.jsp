<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>经销商入驻申请详情</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
	<script src="${ctxStatic}/common/plupload.full.min.js" type="text/javascript"></script>
    <script type="text/javascript">
    $(document).ready(function() {
    	$("#btnSubmit").click(function(){
			var selectAudit1 = $("#auditResult1").prop("checked");
			var selectAudit2 = $("#auditResult2").prop("checked");
			
			if(!selectAudit1 && !selectAudit2){
				alertx("请选择审核意见!");
				return false;
			}
			var auditResult;
			if(selectAudit1) {
				auditResult = 1;
			} else {
				auditResult = 0;
			}
			var remark=$("#remark").val()||"";
			loading("正在提交，请稍等...");
        	var code = '${code}';
        	$.ajax({
                type:"POST",
                url:"${ctx}/dealerapply/audit",
                data:{code:code,auditResult:auditResult,remark:remark},
                success:function(result){
                	closeLoading();
                	$(".myModalClose").click();
                	showTip3(result.msg);
                },
                error : function(error) {  
                	closeLoading();
                	$(".myModalClose").click();
                	showTip3(error.responseText);
                }
            });	
		});
		
		//提示并延迟刷新(使用setTimeout第三个参数)
		function showTip3(mess) {
			resetTip();
        	setTimeout(function(){
        		top.$.jBox.tip(mess, 'info' , {opacity:0,timeout:2000});
        	}, 500,setTimeout(function() {
                window.location.href = '/oms-web/a/dealerapply/list/';
            }, 2500));
		}
    });
    </script>
    <style type="text/css">
    .nav-child li a{
          line-height: 10px;
    }
    .nav-child li.active a{
          border: 1px dotted #ddd;
          border-bottom-color: transparent;
    }
    .photo-file{
	 	position: absolute;
	    top: 350px;
	    left: 190px;
	    opacity: 0;
	    filter: alpha(opacity:0);
	    cursor:pointer;
	}
	img {
	    border: 0 none;
	    height: 80px;
	    max-width: 100%;
	    vertical-align: middle;
	}
	.container{
	padding: 10px 30px;
    width: 100%;
    min-height: 800px;
    background: #fff;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
	}
	#image_btn img{
	float: left;
	}
	.close-Icon{
	position: absolute;
    background: url(/oms-web/static/images/closeImg.png) no-repeat;
    z-index: 100;
    width: 20px;
    height: 20px;
    background-size: 100%;
    right: -7px;
    top: -7px;
       cursor: pointer;
	}
	.img_info{
	position:relative;
	width: 120px;
	height: 120px;
	float: left;
	margin: 10px;
	
	}
	
	table {
		width:70%;margin-left:100px;
	}
	
	.font-bold{font-size：16px;    font-weight: bolder; margin-left:-10px;}
    </style>
</head>
<body>
	<div class="container">
		<ul class="nav nav-tabs">
			<li><a href="${ctx}/dealerapply/list/">申请列表</a></li>
			<li class="active"><a
				href="${ctx}/dealerapply/detail?code=${code}">详情</a></li>
		</ul>
		<br />

		<form id="inputForm" action="${ctx}/dealerapply/audit" method="post"
			class="form-horizontal">
			<input id="code" name="code" type="hidden" value="${code}" />
			<tags:message content="${message}" />
			<div id="base_div" class="tab_div">

				<!-- 资质 -->
				<div class="control-group">
					<table id="contentTable" border="0"
						class="table table-striped table-bordered table-condensed"
						style="width: 80%">
						<thead>
							<tr>
								<th colspan="2">资质</th>
							</tr>
						</thead>
						<tbody id="tbody1">
							<tr>
								<td>经销商名称：${detail.dealerName}</td>
								<td>经销商代码：${detail.dealerCode}</td>
							</tr>
							<tr>
								<td colspan="2">
									经销商区域：${detail.provinceName}${detail.cityName}</td>
							</tr>
							<tr>
								<td>法人姓名：${detail.legalPersonName}</td>
								<td>法人身份证号：${detail.legalPersonId}</td>
							</tr>
							<tr>
								<td>营业执照： <c:if test="${!empty detail.businessLicense}">
										<a href="${fns:getUploadUrl()}${detail.businessLicense}"
											target="_blank"> <img
											src="${fns:getUploadUrl()}${detail.businessLicense}"
											width="80" height="80" title="点击查看大图" />
										</a>
									</c:if>
								</td>
								<td>法人身份证照片： <c:if
										test="${!empty detail.legalPersonCardFront}">
										<a href="${fns:getUploadUrl()}${detail.legalPersonCardFront}"
											target="_blank"> <img
											src="${fns:getUploadUrl()}${detail.legalPersonCardFront}"
											width="80" height="80" title="点击查看大图" />
										</a>
									</c:if>&nbsp;&nbsp;&nbsp; <c:if
										test="${!empty detail.legalPersonCardReverse}">
										<a
											href="${fns:getUploadUrl()}${detail.legalPersonCardReverse}"
											target="_blank"> <img
											src="${fns:getUploadUrl()}${detail.legalPersonCardReverse}"
											width="80" height="80" title="点击查看大图" />
										</a>
									</c:if>
								</td>
							</tr>
						</tbody>
					</table>
				</div>

				<!-- 联系方式 -->
				<div class="control-group">
					<table id="contentTable" border="0"
						class="table table-striped table-bordered table-condensed"
						style="width: 80%">
						<thead>
							<tr>
								<th colspan="2">联系方式</th>
							</tr>
						</thead>
						<tbody id="tbody1">
							<tr>
								<td>芝华仕业务对接人：${detail.businessPerson}</td>
								<td>负责人姓名：${detail.dealerResponsiblePerson}</td>
							</tr>
							<tr>
								<td colspan="2">负责人电话：${detail.dealerResponsibleMobile}
								</td>
							</tr>
						</tbody>
					</table>
				</div>

				<!-- 财务信息 -->
				<div class="control-group">
					<table id="contentTable" border="0"
						class="table table-striped table-bordered table-condensed"
						style="width: 80%">
						<thead>
							<tr>
								<th colspan="2">财务信息</th>
							</tr>
						</thead>
						<tbody id="tbody1">
							<tr>
								<td>银行账号：${detail.bankcardNo}</td>
								<td>所属银行：${detail.bankName}</td>
							</tr>
							<tr>
								<td>开户行所在地：${detail.bankProvinceName}${detail.bankCityName}</td>
								<td>银行支行：${detail.branch}</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 终端列表 -->
				<c:forEach items="${detail.shopList}" var="item" varStatus="number">
					<div class="control-group">
						<table id="contentTable" border="0"
							class="table table-striped table-bordered table-condensed"
							style="width: 80%">
							<thead>
								<tr>
									<th colspan="2">终端${number.count }</th>
								</tr>
							</thead>
							<tbody id="tbody1">
								<tr>
									<td>终端代码：${item.shopNoMerchant}</td>
									<td>终端名称：${item.shopName}</td>
								</tr>
								<tr>
									<td>
										终端地址：${item.provinceName}${item.cityName}${item.areaName}</td>
									<td>详细地址：${item.detailAddr}</td>
								</tr>
								<tr>
									<td>终端图片： <c:if test="${!empty item.logoAddr}">
											<a href="${fns:getUploadUrl()}${item.logoAddr}"
												target="_blank"> <img
												src="${fns:getUploadUrl()}${item.logoAddr}" width="80"
												height="80" title="点击查看大图" />
											</a>
										</c:if>
									</td>
									<td>终端经营范围：${item.bizScope}</td>
								</tr>
								<tr>
									<td>店长姓名：${item.shopManagerName}</td>
									<td>店长联系方式：${item.shopManagerMobile}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</c:forEach>
				<!-- 附件 -->
				<div class="control-group">
					<table id="contentTable" border="0"
						class="table table-striped table-bordered table-condensed"
						style="width: 80%">
						<thead>
							<tr>
								<th>附件</th>
							</tr>
						</thead>
						<tbody id="tbody1">
							<tr>
								<td>
								<c:if test="${empty detail.attachment}">
									无附件
								</c:if>
								<c:if test="${not empty detail.attachment}">
									附件： <c:choose>
										<c:when test="${detail.attachmentIsImg}">
											<a href="${fns:getUploadUrl()}${detail.attachment}"
												target="_blank"> <img
												src="${fns:getUploadUrl()}${detail.attachment}" width="80"
												height="80" title="点击查看大图" />
											</a>
										</c:when>
										<c:otherwise>
											<a href="${fns:getUploadUrl()}${detail.attachment}"
												target="_blank" title="点击下载">${detail.attachment}</a>
										</c:otherwise>
									</c:choose>
								</c:if>
								</td>
							</tr>
						</tbody>
					</table>
				</div>

				<!-- 审核信息 -->
				<div class="control-group">
					<table id="contentTable" border="0"
						class="table table-striped table-bordered table-condensed"
						style="width: 80%">
						<thead>
							<tr>
								<th colspan="2">审核信息</th>
							</tr>
						</thead>
						<c:choose>
							<c:when test="${detail.applyStatus == 0}">
								<tbody id="tbody1">
									<tr>
										<td colspan="2">审核意见： 
											<input type="radio" name="auditResult" id="auditResult1">通过 &nbsp;&nbsp;&nbsp;
											<input type="radio" name="auditResult" id="auditResult2">不通过
										</td>
									</tr>
									<tr>
										<td colspan="2">备注： <textarea style="width:500px;height:60px" id="remark" name="remark"></textarea>
										</td>
									</tr>
								</tbody>
							</c:when>
							<c:otherwise>
								<tbody id="tbody1">
									<tr>
										<td>审核意见： <c:choose>
												<c:when test="${detail.applyStatus == 1}">
												通过
											</c:when>
												<c:otherwise>
												不通过
											</c:otherwise>
											</c:choose>
										</td>
									</tr>
									<tr>
										<td>备注：${detail.remark}</td>
									</tr>
									<tr>
										<td>审核人：${detail.auditName}</td>
									</tr>
									<tr>
										<td>审核时间：<fmt:formatDate value="${detail.auditTime}"
												pattern="yyyy-MM-dd HH:mm:ss" />
										</td>
									</tr>
								</tbody>
							</c:otherwise>
						</c:choose>

					</table>
				</div>


				<div class="form-actions">
					
					<c:choose>
						<c:when test="${detail.applyStatus == 0}">
							<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存"/>&nbsp;
							<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
						</c:when>
						<c:otherwise>
							<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
						</c:otherwise>
					</c:choose>
				</div>
		</form>
	</div>

	<script>
		var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
			browse_button : 'image_btn',
			url : '${ctx}/file/upload?dirName=activity',
			multi_selection : false,
			auto_start : true,
			flash_swf_url : '${ctxStatic}/common/Moxie.swf',
			silverlight_xap_url : '${ctxStatic}/common/Moxie.xap',
			filters : {
				mime_types : [ //只允许上传图片文件
				{
					title : "图片文件",
					extensions : "jpg,gif,png"
				} ],
				max_file_size : '10240kb',
				prevent_duplicates : true
			},
			multipart_params : {
				fileType : 'image',
				width : 1080,
				height : 1540
			}
		});
		uploader.init(); //初始化
		uploader.bind('FilesAdded', function(uploader, files) {
			if (files.length > 0) {
				uploader.start();
			}
		});
		uploader.bind('Error', function(uploader, errObject) {
			if (errObject.code != -602) {
				showTip(errObject.message, "info");
			}
		});
		uploader
				.bind(
						'FileUploaded',
						function(uploader, file, responseObject) {
							var response = $.parseJSON(responseObject.response);
							var child = $("#image_btn").children();
							var html = "";
							if (child.length > 0) {
								html = $("#image_btn").html();
							}
							html = html
									+ '<div class="img_info"><span class="close-Icon" ></span><img width="120px" height="120px" src="'+uploadUrl+'/oms'+response.url+'"/></div>';
							$("#image_btn").html(html);

							$(".close-Icon").on("click", function(e) {
								imgClose(this, e);
							});
							// 			$("#image_btn").html('<img width="120px" height="120px" src="'+uploadUrl+'/oms'+response.url+'"/>');
							// 			$("#input_image").val("/oms" + response.url);
						});

		var uploader_show = new plupload.Uploader({ //实例化一个plupload上传对象
			browse_button : 'showImgAddr_btn',
			url : '${ctx}/file/upload?dirName=activity',
			multi_selection : false,
			auto_start : true,
			flash_swf_url : '${ctxStatic}/common/Moxie.swf',
			silverlight_xap_url : '${ctxStatic}/common/Moxie.xap',
			filters : {
				mime_types : [ //只允许上传图片文件
				{
					title : "图片文件",
					extensions : "jpg,gif,png"
				} ],
				max_file_size : '10240kb',
				prevent_duplicates : true
			},
			multipart_params : {
				fileType : 'image',
				width : 1080,
				height : 500
			}
		});
		uploader_show.init(); //初始化
		uploader_show.bind('FilesAdded', function(uploader_show, files) {
			if (files.length > 0) {
				uploader_show.start();
			}
		});
		uploader_show.bind('Error', function(uploader_show, errObject) {
			if (errObject.code != -602) {
				showTip(errObject.message, "info");
			}
		});
		uploader_show
				.bind(
						'FileUploaded',
						function(uploader_show, file, responseObject) {
							var response = $.parseJSON(responseObject.response);
							$("#showImgAddr_btn")
									.html(
											'<img width="120px" height="120px" src="'+uploadUrl+'/oms'+response.url+'"/>');
							$("#input_showImgAddr").val("/oms" + response.url);
						});

		$(document)
				.ready(
						function() {

							$("#inputForm")
									.validate(
											{
												submitHandler : function(form) {
													var imgInfo = "";
													var imgJi = $("#image_btn img");
													for (var i = 0; i < imgJi.length; i++) {
														if (i == imgJi.length - 1) {
															imgInfo = imgInfo
																	+ imgJi[i].src
																			.split(uploadUrl)[1];
														} else {
															imgInfo = imgInfo
																	+ imgJi[i].src
																			.split(uploadUrl)[1]
																	+ ",";
														}
													}
													$("#input_image").val(
															imgInfo);
													$("#btnSubmit").attr(
															"disabled",
															"disabled");
													form.submit()
													return false;
												},
												errorContainer : "#messageBox",
												errorPlacement : function(
														error, element) {
													$("#messageBox").text(
															"输入有误，请先更正。");
													if (element.is(":checkbox")
															|| element
																	.is(":radio")
															|| element
																	.parent()
																	.is(
																			".input-append")) {
														error.appendTo(element
																.parent()
																.parent());
													} else {
														error
																.insertAfter(element);
													}
												}
											});
							$("#materialTypeCode").change(
									function() {
										$("#materialTypeName").val(
												$(this).find("option:selected")
														.text());
									});

							$(".close-Icon").on("click", function(e) {
								imgClose(this, e);
							});
						});

		function imgClose(event, e) {
			$(event).parent().remove();
			var show = $("#image_btn").children();
			if (show.length == 0) {
				$("#image_btn").html("选择图片");
			}
			e.stopPropagation();
			return false;
		}
		function tabChange(id, ths) {
			$(".tab_div").hide();
			$(".nav-child li").removeClass("active");
			$(id).show();
			$(ths).addClass("active");
		}
	</script>
</body>
</html>