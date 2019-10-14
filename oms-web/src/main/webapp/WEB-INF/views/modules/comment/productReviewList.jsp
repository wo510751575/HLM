<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评论管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {

			$(".replyBtn").click(function(){
				 var code=$(this).attr("data-code");
				 var reviewCode=$(this).attr("data-reviewCode");
				 promptx2("请输入回复内容", "回复",function(){
			    	  var txt=top.$("#txt").val();

			    	  $('#code').val(code);
			    	  $('#reviewCode').val(reviewCode);
			    	  $('#replyContent').val(txt);
			    	  
			    	  var mbrNickname = $("input[name='param.mbrNickname']").val()||'';
			    	  var replyMbrNickname = $("input[name='param.replyMbrNickname']").val()||'';
			    	  var orderNo = $("input[name='param.orderNo']").val()||'';
			    	  $("#mbrNickname").val(mbrNickname);
			    	  $("#replyMbrNickname").val(replyMbrNickname);
			    	  $("#orderNo").val(orderNo);
			    	  
			    	  $('#replyForm').submit();
			    	  
				 });
			  });

			$("#btnSubmit").click(function(){
				/* var orderCode = $("#orderCode").val();
				if(''==orderCode) {
					alert("订单号不能为空");
					return false;
				} */
				$('#pageNo').val('1');
				$('#pageSize').val('10');
				$("#searchForm").submit();
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		function promptx2(title, lable,  href, closed){
			top.$.jBox("<div class='form-search' style='padding:20px;text-align:center;'>" 
					+ "<textarea rows='20' cols='35'  id='txt' name='txt' /></textarea></div>", {
					title: title, submit: function (v, h, f){
			    if (f.txt == '') {
			        top.$.jBox.tip("请输入" + lable + "。", 'error');
			        return false;
			    }
			    if(f.txt.length>500) {
			    	  top.$.jBox.tip("请输入小于500字。", 'error');
			    	  return false;
			      }
				if (typeof href == 'function') {
					href();
				}else{
					resetTip(); //loading();
					location = href + encodeURIComponent(f.txt);
				}
			},closed:function(){
				if (typeof closed == 'function') {
					closed();
				}
			}});
			return false;
		}

	</script>
		<style type="text/css">
	.img-small {
		position: relative;
		z-index:10;
		width: 30px;
		height: 30px;
		margin-left:5px;
	}
	.img-circle {
    width: 40px;
    height: 40px;
    border-radius: 20px;
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
	
	tbody tr td div{
		font-size:12px;
		color:#b7b7b7
		
	}
	.lafen-group {
    	position: relative;
    	margin-left:5px;
	}
	.lafen-group:nth-child(1){
		margin-left:0;
	}
	.flesX{
		display:flex;
	}
	.lafen-group .img-big {
	    position: absolute;
	    left: 50%;
	    top: 50%;
	    margin-top: -75px;
	    margin-left: 35px;
	    opacity: 0;
	    transform: scale(.2, .2);
	    transition: all .2s ease-in-out;
	    width: 130px;
	    height: 130px;
	    z-index:100;
	}
	.lafen-group .img-small:hover + .img-big {
	    transform: scale(1, 1);
	    opacity: 1;
	}
	.lafen-group .img-big img {
	    width: 130px;
	    height: 130px;
	}
	</style>
</head>
<body>
<div class="container">
	
	
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/comment/productReview">评论列表</a></li>
		<%-- <shiro:hasPermission name="comment:productReview:edit">
			<li><a href="${ctx}/comment/productReview/form">评论添加</a></li> 
		</shiro:hasPermission> --%>
	</ul>
	<form id="searchForm" action="${ctx}/comment/productReview" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>评论：</label>
				<input type="text" name="param.content" class="input-medium" maxlength="100" placeholder="评论" value="${findProductReviewAndContentPage.param.content}">
			</li> --%>
			<li><label>评论客户：</label>
				<input type="text" name="param.mbrNickname" class="input-medium" maxlength="100" placeholder="" value="${findProductReviewAndContentPage.param.mbrNickname}">
			</li>
			<li><label>回复导购：</label>
				<input type="text" name="param.replyMbrNickname" class="input-medium" maxlength="100" placeholder="" value="${findProductReviewAndContentPage.param.replyMbrNickname}">
			</li>
			<%-- <li><label>商品名称：</label>
				<input type="text" name="param.productName" class="input-medium" maxlength="100" placeholder="" value="${findProductReviewAndContentPage.param.productName}">
			</li> --%>
			<li><label>订单号：</label>
				<input type="text" name="param.orderNo" class="input-medium" maxlength="100" placeholder="订单号" value="${findProductReviewAndContentPage.param.orderNo}">
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li> 
		</ul> 
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width:300px">评价内容</th>
				<th>晒图</th>
				<th>订单号</th>
				<!-- <th>商品名称</th> -->
				<th>评论客户</th>
				<th>回复导购</th>
				<th style="width:300px">回复内容</th>
				<th>是否展示</th>
				<shiro:hasPermission name="comment:productReview:edit">
				<th style="width:80px">操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>
				<td>
					<c:if test="${item.type eq 'F' }">
						首评：
					</c:if>
					<c:if test="${item.type eq 'S' }">
						追评：
					</c:if>
					${item.content}
					<div>
						<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</div>
				</td>
				<td class="flesX">
					<c:if test="${not empty item.images}">
						<c:set var="imgAddr" value="${fn:split(item.images, ',')}" />
						<c:forEach items="${imgAddr}" var="img">
							<div class="lafen-group">
						    	<img class="img-small" src="${fns:getUploadUrl()}${img}" alt="">
						    	<div class="img-big">
						    		<img  src="${fns:getUploadUrl()}${img}" alt="">
						    	</div>
						    </div>
						</c:forEach>
					</c:if>
				</td>
				<td>${item.orderNo}</td>
				<%-- <td>${item.productName}</td> --%>
				<td>${item.mbrNickname}</td>
				<td>${item.replyMbrNickname}</td>
				<%request.setAttribute("vEnter", "\n");%>
				<td>
					<p>${fn:replace(item.replyContent, vEnter, '<br>')}</p>
					<%-- <a href="javascript:;" class="replyContent"  data-code="${item.code}"  data-reviewCode="${item.replyContent}">${item.replyContent}</a> --%>
					<div>
						<fmt:formatDate value="${item.replyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</div>
				</td>
				<td>
					<c:forEach items="${ProductReviewContentStatus}" var="p">
							<c:choose> 
								<c:when test="${p.value == item.status}">${p.chName}</c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
					</c:forEach>
				</td>	
				<shiro:hasPermission name="comment:productReview:edit">
				<td>
					<c:if test="${'Y' eq item.status}"> 
					<a href="${ctx}/comment/productReview/del?code=${item.code}" onclick="return confirmx('确定删除吗？', this.href)">删除</a>&nbsp;
					</c:if>
					<c:if test="${empty item.replyMbrNo}"> 
						<a href="javascript:;" class="replyBtn"  data-code="${item.code}"  data-reviewCode="${item.reviewCode}">回复</a>
					 </c:if> 
				</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	<form id="replyForm" action="${ctx}/comment/productReview/replyAll" method="post" class="breadcrumb form-search hide">
		<input id="mbrNickname" name="mbrNickname" type="hidden" />
		<input id="replyMbrNickname" name="replyMbrNickname" type="hidden" />
		<input id="orderNo" name="orderNo" type="hidden" />
		
		<input id="code" name="code" type="hidden" />
		<input id="reviewCode" name="reviewCode" type="hidden" />
		<input name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input name="pageSize" type="hidden" value="${page.pageSize}"/>
		<textarea id="replyContent" name="replyContent">
		</textarea>
	</form>
	
	<script type="text/javascript" src="${ctxStatic}/common/clipboard.min.js"></script>
	<script>
    var clipboard = new Clipboard('.copyBtn');

    clipboard.on('success', function(e) {
    	showTip("已将链接复制到粘贴板","info");
    });

    clipboard.on('error', function(e) {
        console.log(e);
    });
    </script>
</div>	
</body>
</html>