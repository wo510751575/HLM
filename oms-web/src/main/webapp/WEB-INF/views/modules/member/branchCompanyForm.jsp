<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>分公司修改</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
	<script src="${ctxStatic}/common/plupload.full.min.js" type="text/javascript"></script>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<link href="${ctxStatic}/common/select2.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript">
    $(document).ready(function() {
    	
    	//初始化终端显示
    	initShop();
    	
    	var code=$("#code").val();
		if(code) {
			$('.upd').removeClass('hide');
		}
    	
    	//绑定使用范围
		$('#btnTerminal').click(function() {
			var companyno=$(this).data("companyno");
			
			var  selectShopNo = $("#shopNos").val();//已选择的终端编号
			window.localStorage.setItem("c_shopNo",selectShopNo);
			window.localStorage.setItem("c_shopName",$("#shopName").val());
			
			// 正常打开	
			var url = "iframe:${ctx}/sys/office/shopShowList?bindFlag=Y&companyNo="+companyno;
			top.$.jBox.open(url,"终端分配", 880, 560,{//宽高
				id:9527,
				draggable: true,
				showClose: true,
				buttons:{"确定":"ok", "关闭":true}, submit:function(v, h, f){
	                /* if (v=="ok"){
	                	var win = h.find("iframe")[0].contentWindow
	                	var form=win.document.getElementById("submitForm");
	                	var shopInfos=win.submitAjax();
	                	$("#shopNos").val(shopInfos.shopNos);
		                $("#shopNames").val(shopInfos.shopNames);
		                initShop();
	                }  */
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
	    });//使用范围 end
    	
    	$.ajax({
            type:"POST",
            url:"${ctx}/sys/area/provinceList",
            data:{},
            success:function(result){
            	for (var i = 0; i < result.length; i++) {
            		if (result[i].code == '${data.provinceCode}') {
            			$("#provinceCode").append("<option selected value='" + result[i].code + "'>" + result[i].name + "</option>")
            			$("#provinceName").val(result[i].name);
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
                    		if (result[i].code == '${data.cityCode}') {
                    			$("#cityCode").append("<option selected value='" + result[i].code + "'>" + result[i].name + "</option>")
                    			$("#cityName").val(result[i].name);
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
    		var selectedProvinceName = $("#provinceCode").find("option:selected").text();
    		$("#provinceName").val(selectedProvinceName);
        	$.ajax({
                type:"POST",
                url:"${ctx}/sys/area/findCityListByProvinceCode",
                data:{provinceCode:provinceCode},
                success:function(result){
                	$("#cityCode").html('');
                	for (var i = 0; i < result.length; i++) {
                		if (i == 0) {
                			$("#cityName").val(result[i].name);
						}
                		$("#cityCode").append("<option value='" + result[i].code + "'>" + result[i].name + "</option>")
    				}
                }
            });	
		});
    	
    	$("#cityCode").change(function(){
    		var selectedCityName = $("#cityCode").find("option:selected").text();
    		$("#cityName").val(selectedCityName);
		});
    	
    	
        $("#inputForm").validate({
            submitHandler: function(form){
                $("#btnSubmit").attr("disabled","disabled");form.submit()
                return false;
            },
            errorContainer: "#messageBox",
            errorPlacement: function(error, element) {
                $("#messageBox").text("输入有误，请先更正。");
                if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
                    error.appendTo(element.parent().parent());
                } else {
                    error.insertAfter(element);
                }
            }
        });
        
    });

    function tabChange(id,ths){
        $(".tab_div").hide();
        $(".nav-child li").removeClass("active");
        $(id).show();
        $(ths).addClass("active");
    }
	//初始化终端显示
    function initShop(){
    	var sn=$("#shopNames").val();
    	var content=sn.split(",");
    	var shopHtml='';
    	for(var i=0;i<content.length;i++){
    		if(content[i]){
    			shopHtml+='<label style="border: 1px solid #ccc;"> &nbsp; '+content[i]+' &nbsp; </label>'+'&nbsp;&nbsp;';
    		}
    	}
    	$("#shopContent").html(shopHtml);
    	
    }
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
	
	.select2-container-multi .select2-choices{
	    width: 281px;
	}
	
	.container{
	padding: 10px 30px;
    width: 100%;
    min-height: 800px;
    background: #fff;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
	}
    </style>
</head>
<body>
<div class="container">
    <ul class="nav nav-tabs">
        <li><a href="${ctx}/member/branchCompany/">经销商列表</a></li>
	    <li class="active"><a href="${ctx}/member/branchCompany/form?code=${data.code}">经销商编辑</a></li> 
    </ul><br/>
    <form id="inputForm" action="${ctx}/member/branchCompany/edit" method="post" enctype="multipart/form-data" class="form-horizontal">
        <input id="code" name="code" type="hidden" value="${data.code}"/>
        <input id="companyNo" name="companyNo" type="hidden" value="${data.companyNo}"/>
        <input id="shopNos" name="shopNos" type="hidden" value="${shopNos}"/>
        <input id="shopNames" name="shopNames" type="hidden" value="${shopNames}"/>
        <tags:message content="${message}"/>
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
								<td>经销商名称：
									<input type="text" id="companyName" name="companyName"  maxlength="100"  class="required" value="${data.companyName}"/>
									<span class="help-inline"><font color="red">*</font></span>
								</td>
								<td>经销商代码：
									<input type="text" id="dealerCode" name="dealerCode"  maxlength="100"  class="required" value="${data.dealerCode}"/>
									<span class="help-inline"><font color="red">*</font></span>
								</td>
								
							</tr>
							<tr>
								<td>状态：
									<c:choose>
						             	<c:when test="${not empty data.status}">
						             		<input id="checkbox" name="status" type="radio"  value="NORMAL" <c:if test="${data.status=='NORMAL' }">checked="checked"</c:if>  class="required"/>正常
						           			 <input id="checkbox" name="status" type="radio" value="CANCEL" <c:if test="${data.status=='CANCEL' }">checked="checked"</c:if> class="required"/>注销       
						             	</c:when>
						             	<c:otherwise>
						             		<input id="checkbox" name="status" type="radio"  value="NORMAL" checked="checked" class="required"/>正常
						            		<input id="checkbox" name="status" type="radio" value="CANCEL"  class="required"/>注销     
						             	</c:otherwise>
						             </c:choose>
						             <span class="help-inline"><font color="red">*</font></span>
								</td>
								<td>
									经销商区域：
									<select name="provinceCode" id="provinceCode"></select>
									<input id="provinceName" name="provinceName" type="hidden"/>
									<select name="cityCode"  id="cityCode"></select>
									<input id="cityName" name="cityName" type="hidden"/>
								</td>
							</tr>
							<tr>
								<td>法人姓名：<input type="text" id="legalPersonName" name="legalPersonName"  maxlength="10"   value="${data.legalPersonName}"/><span class="help-inline"><font color="red">*</font></span></td>
								<td>法人身份证号：<input type="text" id="legalPersonId" name="legalPersonId"  maxlength="18"   value="${data.legalPersonId}"/><span class="help-inline"><font color="red">*</font></span></td>
							</tr>
							<tr>
								<td>营业执照： 
									<div class="controls">
										<div id="businessLicense"
											style="border: 1px solid #e0e6eb; width: 120px; height: 120px; line-height: 100px; text-align: center">
											<c:if test="${!empty data.businessLicense}">
												<img width="120px" height="120px"
													src="${fns:getUploadUrl()}${data.businessLicense}" />
											</c:if>
											<c:if test="${empty data.businessLicense}">
									                                  选择图片
									       </c:if>
										</div>
										<input type="hidden" id="businessLicense_image" class="required" name="businessLicense"
											value="${data.businessLicense}" />
					
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
											<c:if test="${!empty data.legalPersonCardFront}">
												<img width="120px" height="120px"
													src="${fns:getUploadUrl()}${data.legalPersonCardFront}" />
											</c:if>
											<c:if test="${empty data.legalPersonCardFront}">
									                                  选择图片
									       </c:if>
										</div>
										<input type="hidden" id="legalPersonCardFront_image" class="required" name="legalPersonCardFront"
											value="${data.legalPersonCardFront}" />
					
									</div>
									&nbsp; 
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
											<c:if test="${!empty data.legalPersonCardReverse}">
												<img width="120px" height="120px"
													src="${fns:getUploadUrl()}${data.legalPersonCardReverse}" />
											</c:if>
											<c:if test="${empty data.legalPersonCardReverse}">
									                                  选择图片
									       </c:if>
										</div>
										<input type="hidden" id="legalPersonCardReverse_image" class="required" name="legalPersonCardReverse"
											value="${data.legalPersonCardReverse}" />
					
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
								<td>芝华仕业务对接人：<input type="text" id="businessPerson" name="businessPerson"  maxlength="10"   value="${data.businessPerson}"/><span class="help-inline"><font color="red">*</font></span></td>
								<td>负责人姓名：<input type="text" id="dealerResponsiblePerson" name="dealerResponsiblePerson"  maxlength="10"   value="${data.dealerResponsiblePerson}"/><span class="help-inline"><font color="red">*</font></span></td>
							</tr>
							<tr>
								<td colspan="2">负责人电话：<input type="text" id="dealerResponsibleMobile" name="dealerResponsibleMobile"  maxlength="11"  value="${data.dealerResponsibleMobile}"/><span class="help-inline"><font color="red">*</font></span>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				
			        <div class="control-group upd hide">
		               <label class="control-label"><button type="button" id="btnTerminal" data-companyno="${data.companyNo}">选择终端:</button></label>
		               <div class="controls" id="shopContent">
		               </div>
			        </div>

        
        <div class="form-actions">
            <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
        </div>
    </form>
    </div>
    <script>

		
		var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
			browse_button : 'businessLicense',
			url : '${ctx}/file/upload?dirName=credImg',
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
			url : '${ctx}/file/upload?dirName=credImg',
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
			url : '${ctx}/file/upload?dirName=credImg',
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