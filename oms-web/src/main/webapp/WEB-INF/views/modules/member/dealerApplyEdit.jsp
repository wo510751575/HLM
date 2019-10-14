<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>经销商入驻申请编辑</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
	<script src="${ctxStatic}/common/plupload.full.min.js" type="text/javascript"></script>
    <script type="text/javascript">
    $(document).ready(function() {
    	$.ajax({
            type:"POST",
            url:"${ctx}/sys/area/provinceList",
            data:{},
            success:function(result){
            	for (var i = 0; i < result.length; i++) {
            		if (result[i].code == '${detail.provinceCode}') {
            			$("#provinceCode").append("<option selected value='" + result[i].code + "'>" + result[i].name + "</option>")
					} else {
	            		$("#provinceCode").append("<option value='" + result[i].code + "'>" + result[i].name + "</option>")
					}
				}
            	var provinceCode = $("#provinceCode").val();
            	$.ajax({
                    type:"POST",
                    url:"${ctx}/sys/area/findCityListByProvinceCode",
                    data:{provinceCode:provinceCode},
                    success:function(result){
                    	$("#cityCode").html('');
                    	for (var i = 0; i < result.length; i++) {
                    		if (result[i].code == '${detail.cityCode}') {
                    			$("#cityCode").append("<option selected value='" + result[i].code + "'>" + result[i].name + "</option>")
        					} else {
        	            		$("#cityCode").append("<option value='" + result[i].code + "'>" + result[i].name + "</option>")
        					}
                    		
        				}
                    }
                });
            }
        });	
    	
    	$("#provinceCode").change(function(){
    		var provinceCode = $(this).val();
        	$.ajax({
                type:"POST",
                url:"${ctx}/sys/area/findCityListByProvinceCode",
                data:{provinceCode:provinceCode},
                success:function(result){
                	$("#cityCode").html('');
                	for (var i = 0; i < result.length; i++) {
                		$("#cityCode").append("<option value='" + result[i].code + "'>" + result[i].name + "</option>")
    				}
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
				href="${ctx}/dealerapply/editView?code=${code}">编辑</a></li>
		</ul>
		<br />

		<form id="inputForm" action="${ctx}/dealerapply/edit" method="post"
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
								<td>经销商名称：<input type="text" name="dealerName" value="${detail.dealerName}"></td>
								<td>经销商代码：<input type="text" name="dealerName" value="${detail.dealerCode}"></td>
							</tr>
							<tr>
								<td colspan="2">
									经销商区域：
									<select name="provinceCode" id="provinceCode"></select>
									<select name="cityCode"  id="cityCode"></select>
								</td>
							</tr>
							<tr>
								<td>法人姓名：<input type="text" name="dealerName" value="${detail.legalPersonName}"></td>
								<td>法人身份证号：<input type="text" name="dealerName" value="${detail.legalPersonId}"></td>
							</tr>
							<tr>
								<td>营业执照： 
									<div class="controls">
										<div id="businessLicense"
											style="border: 1px solid #e0e6eb; width: 120px; height: 120px; line-height: 100px; text-align: center">
											<c:if test="${!empty detail.businessLicense}">
												<img width="120px" height="120px"
													src="${fns:getUploadUrl()}${detail.businessLicense}" />
											</c:if>
											<c:if test="${empty detail.businessLicense}">
									                                  选择图片
									       </c:if>
										</div>
										<input type="hidden" id="businessLicense_image" class="required" name="icon"
											value="${detail.businessLicense}" />
					
									</div>
									<%-- <c:if test="${!empty detail.businessLicense}">
										<a href="${fns:getUploadUrl()}${detail.businessLicense}"
											target="_blank"> <img
											src="${fns:getUploadUrl()}${detail.businessLicense}"
											width="80" height="80" title="点击查看大图" />
										</a>
									</c:if> --%>
								</td>
								<td>法人身份证照片： 
									<%-- <c:if
										test="${!empty detail.legalPersonCardFront}">
										<a href="${fns:getUploadUrl()}${detail.legalPersonCardFront}"
											target="_blank"> <img
											src="${fns:getUploadUrl()}${detail.legalPersonCardFront}"
											width="80" height="80" title="点击查看大图" />
										</a>
									</c:if> --%>
									<div class="controls">
										<div id="legalPersonCardFront"
											style="border: 1px solid #e0e6eb; width: 120px; height: 120px; line-height: 100px; text-align: center">
											<c:if test="${!empty detail.legalPersonCardFront}">
												<img width="120px" height="120px"
													src="${fns:getUploadUrl()}${detail.legalPersonCardFront}" />
											</c:if>
											<c:if test="${empty detail.legalPersonCardFront}">
									                                  选择图片
									       </c:if>
										</div>
										<input type="hidden" id="legalPersonCardFront_image" class="required" name="icon"
											value="${detail.legalPersonCardFront}" />
					
									</div>
									&nbsp;&nbsp;&nbsp; 
									<%-- <c:if
										test="${!empty detail.legalPersonCardReverse}">
										<a
											href="${fns:getUploadUrl()}${detail.legalPersonCardReverse}"
											target="_blank"> <img
											src="${fns:getUploadUrl()}${detail.legalPersonCardReverse}"
											width="80" height="80" title="点击查看大图" />
										</a>
									</c:if> --%>
									<div class="controls">
										<div id="legalPersonCardReverse"
											style="border: 1px solid #e0e6eb; width: 120px; height: 120px; line-height: 100px; text-align: center">
											<c:if test="${!empty detail.legalPersonCardReverse}">
												<img width="120px" height="120px"
													src="${fns:getUploadUrl()}${detail.legalPersonCardReverse}" />
											</c:if>
											<c:if test="${empty detail.legalPersonCardReverse}">
									                                  选择图片
									       </c:if>
										</div>
										<input type="hidden" id="legalPersonCardReverse_image" class="required" name="icon"
											value="${detail.legalPersonCardReverse}" />
					
									</div>
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
								<td>经销商负责人姓名：${detail.dealerResponsiblePerson}</td>
							</tr>
							<tr>
								<td colspan="2">经销商负责人电话：${detail.dealerResponsibleMobile}
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
								<td colspan="2">银行支行：${detail.branch}</td>
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
				


				<div class="form-actions">
					<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存"/>&nbsp;
					<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
				</div>
		</form>
	</div>

	<script>

		
		var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
			browse_button : 'businessLicense',
			url : '${ctx}/file/upload?dirName=photo',
			multi_selection:false,
			auto_start : true,
			flash_swf_url : '${ctxStatic}/common/Moxie.swf',
			silverlight_xap_url : '${ctxStatic}/common/Moxie.xap',
			filters: {
			  mime_types : [ //只允许上传图片文件
			    { title : "图片文件", extensions : "jpg,gif,png" }
			  ],
			  max_file_size : '1024kb',
			  prevent_duplicates : true 
			},
			multipart_params: {
				fileType: 'image',
				width:400,
				height:400
			}
		});
		uploader.init(); //初始化
		uploader.bind('FilesAdded',function(uploader,files){
			if(files.length>0){
				uploader.start();
			}
		});
		uploader.bind('Error',function(uploader,errObject){
			if(errObject.code!=-602){
				showTip(errObject.message,"info");
			}
		});
		
		uploader.bind('FileUploaded',function(uploader,file,responseObject){
				var response = $.parseJSON(responseObject.response);
				$("#businessLicense").html('<img width="120px" height="120px" src="'+uploadUrl+'/oms'+response.url+'"/>');
				$("#businessLicense_image").val("/oms" + response.url);
		});
		/* 身份证正面 */
		var uploaderPersonCardFront = new plupload.Uploader({ //实例化一个plupload上传对象
			browse_button : 'legalPersonCardFront',
			url : '${ctx}/file/upload?dirName=photo',
			multi_selection:false,
			auto_start : true,
			flash_swf_url : '${ctxStatic}/common/Moxie.swf',
			silverlight_xap_url : '${ctxStatic}/common/Moxie.xap',
			filters: {
			  mime_types : [ //只允许上传图片文件
			    { title : "图片文件", extensions : "jpg,gif,png" }
			  ],
			  max_file_size : '1024kb',
			  prevent_duplicates : true 
			},
			multipart_params: {
				fileType: 'image',
				width:400,
				height:400
			}
		});
		uploaderPersonCardFront.init(); //初始化
		uploaderPersonCardFront.bind('FilesAdded',function(uploader,files){
			if(files.length>0){
				uploader.start();
			}
		});
		uploaderPersonCardFront.bind('Error',function(uploader,errObject){
			if(errObject.code!=-602){
				showTip(errObject.message,"info");
			}
		});
		
		uploaderPersonCardFront.bind('FileUploaded',function(uploader,file,responseObject){
				var response = $.parseJSON(responseObject.response);
				$("#legalPersonCardFront").html('<img width="120px" height="120px" src="'+uploadUrl+'/oms'+response.url+'"/>');
				$("#legalPersonCardFront_image").val("/oms" + response.url);
		});
		/* 身份证反面 */
		var uploaderPersonCardReverse = new plupload.Uploader({ //实例化一个plupload上传对象
			browse_button : 'legalPersonCardReverse',
			url : '${ctx}/file/upload?dirName=photo',
			multi_selection:false,
			auto_start : true,
			flash_swf_url : '${ctxStatic}/common/Moxie.swf',
			silverlight_xap_url : '${ctxStatic}/common/Moxie.xap',
			filters: {
			  mime_types : [ //只允许上传图片文件
			    { title : "图片文件", extensions : "jpg,gif,png" }
			  ],
			  max_file_size : '1024kb',
			  prevent_duplicates : true 
			},
			multipart_params: {
				fileType: 'image',
				width:400,
				height:400
			}
		});
		uploaderPersonCardReverse.init(); //初始化
		uploaderPersonCardReverse.bind('FilesAdded',function(uploader,files){
			if(files.length>0){
				uploader.start();
			}
		});
		uploaderPersonCardReverse.bind('Error',function(uploader,errObject){
			if(errObject.code!=-602){
				showTip(errObject.message,"info");
			}
		});
		
		uploaderPersonCardReverse.bind('FileUploaded',function(uploader,file,responseObject){
				var response = $.parseJSON(responseObject.response);
				$("#legalPersonCardReverse").html('<img width="120px" height="120px" src="'+uploadUrl+'/oms'+response.url+'"/>');
				$("#legalPersonCardReverse_image").val("/oms" + response.url);
		});

    </script>
</body>
</html>