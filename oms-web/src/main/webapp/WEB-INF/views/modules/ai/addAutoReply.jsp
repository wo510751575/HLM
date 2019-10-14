<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>新增自动回复</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	  function open2(){
		var gmNo = $("#gmNo").val();
		var memberGmName =  $("#gmNo option:selected").text();
      	$.ajax({
              type:"POST",
              dataType:"json",
              url:"${ctx}/ai/merchantPreProblem/insertGmAutoStatus",
              data:{gmNo:gmNo,memberGmName:memberGmName},
              success:function(result){
                 alert(result.message);
                 window.location.href="${ctx}/ai/merchantPreProblem/selectAutoReplyList";
              },
              error: function (XMLHttpRequest, textStatus, errorThrown) {
                  // 状态码
                  console.log(XMLHttpRequest.status);
                  // 状态
                  console.log(XMLHttpRequest.readyState);
                  // 错误信息   
                  console.log(textStatus);
              }
          });
      }
	</script>
	<style type="text/css">
		#preView .controls{
			width: 460px;
			height: 100px;
			border: 1px solid #ccc;
			padding: 10px;
			word-break: break-all;
		}
		#preView .controls .headImg{
			width:50px;
			float: left;
		}
		#preView .controls  .headImg img {
			width: 50px;
			height: 50px;
		}
		
		#preView .controls .textContent{
			margin-left: 10px;
			margin-top: 10px;
			float: left;
			width: 400px;
		}
		
	</style>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs">
	    <li ><a href="${ctx}/ai/merchantPreProblem/">商家预设问题列表</a></li>		
			<li><a href="${ctx}/ai/merchantPreProblem/form">商家预设问题添加</a></li>	
		<li ><a href="${ctx}/ai/merchantPreProblem/selectAutoReplyList">自动回复列表</a></li>
		<li class="active"><a href="${ctx}/ai/merchantPreProblem/toAddAutoReply">新增</a></li>
	</ul><br/>

		<div class="control-group">
			<label class="control-label"></label>
			<div class="controls">
				导购:<select style="width: 150px;" name="gmNo" id="gmNo">
	             <c:forEach items="${list}" var="item">
					<option value="${item.memberNo}" >${item.memberName}</option>
				</c:forEach>
             </select>
             
             <label class="control-label">状态: 开启ai自动回复</label>
			</div>
		</div>

		

		<div class="form-actions">
			<input class="btn btn-primary" type="button" onclick="javascript:open2();" value="保 存"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</div>

</body>
</html>