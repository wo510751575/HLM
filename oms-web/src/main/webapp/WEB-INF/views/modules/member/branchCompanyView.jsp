<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>分公司修改</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<link href="${ctxStatic}/common/select2.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript">
    $(document).ready(function() {
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
	    <li class="active"><a href="${ctx}/member/branchCompany/view?code=${data.code}">经销商详情</a></li> 
    </ul><br/>
    <form id="inputForm" action="" method="post" enctype="multipart/form-data" class="form-horizontal">
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
								<td>经销商名称：${data.companyName}</td>
								<td>经销商代码：${data.dealerCode}</td>
								<td>状态：
									<c:choose>
						             	<c:when test="${not empty data.status}">
						             		<input disabled="disabled" id="checkbox" name="status" type="radio"  value="NORMAL" <c:if test="${data.status=='NORMAL' }">checked="checked"</c:if>  class="required"/>正常
						           			 <input disabled="disabled" id="checkbox" name="status" type="radio" value="CANCEL" <c:if test="${data.status=='CANCEL' }">checked="checked"</c:if> class="required"/>注销       
						             	</c:when>
						             	<c:otherwise>
						             		<input disabled="disabled" id="checkbox" name="status" type="radio"  value="NORMAL" checked="checked" class="required"/>正常
						            		<input disabled="disabled" id="checkbox" name="status" type="radio" value="CANCEL"  class="required"/>注销     
						             	</c:otherwise>
						             </c:choose>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									经销商区域：${data.provinceName}${data.cityName}</td>
							</tr>
							<tr>
								<td>法人姓名：${data.legalPersonName}</td>
								<td>法人身份证号：${data.legalPersonId}</td>
							</tr>
							<tr>
								<td>营业执照： <c:if test="${!empty data.businessLicense}">
										<a href="${fns:getUploadUrl()}${data.businessLicense}"
											target="_blank"> <img
											src="${fns:getUploadUrl()}${data.businessLicense}"
											width="80" height="80" title="点击查看大图" />
										</a>
									</c:if>
								</td>
								<td>法人身份证照片： <c:if
										test="${!empty data.legalPersonCardFront}">
										<a href="${fns:getUploadUrl()}${data.legalPersonCardFront}"
											target="_blank"> <img
											src="${fns:getUploadUrl()}${data.legalPersonCardFront}"
											width="80" height="80" title="点击查看大图" />
										</a>
									</c:if>&nbsp;&nbsp;&nbsp; <c:if
										test="${!empty data.legalPersonCardReverse}">
										<a
											href="${fns:getUploadUrl()}${data.legalPersonCardReverse}"
											target="_blank"> <img
											src="${fns:getUploadUrl()}${data.legalPersonCardReverse}"
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
								<td>芝华仕业务对接人：${data.businessPerson}</td>
								<td>负责人姓名：${data.dealerResponsiblePerson}</td>
							</tr>
							<tr>
								<td colspan="2">负责人电话：${data.dealerResponsibleMobile}
								</td>
							</tr>
						</tbody>
					</table>
				</div>

				<!-- 财务信息 -->
				<c:forEach items="${data.accountList}" var="item" varStatus="number">
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
								<td>银行账号：${item.bankcardNo}</td>
								<td>所属银行：${item.bankName}</td>
							</tr>
							<tr>
								<td colspan="2">银行支行：${item.branch}</td>
							</tr>
						</tbody>
					</table>
				</div>
				</c:forEach>
        
        
        
        
        <div class="form-actions">
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
        </div>
    </form>
    </div>
</body>
</html>