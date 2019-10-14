<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>跟进项目管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//添加
			$("#inAdd").on('click', function() {
		        var p = $("#availableSec").find("option:selected");
		        p.clone().appendTo("#inSec");
		        p.remove();
		      });
			//移除
		      $("#inRemove").on('click', function() {
		        var p = $("#inSec").find("option:selected");
		        p.clone().appendTo("#availableSec");
		        p.remove();
		      });
		      $("#btnSubmit").click(function(){
		    	  $("#inSec option").prop("selected",true);
		    	  $("#searchForm").submit();
		    	  showTip("保存成功!","info");
		      })
		});
		function check(event){
			var chose= $(event).attr("class");
	    	 if(chose=="chose"){
	    		 $(event).removeClass("chose");
	    	 }else{
	    		 $(event).attr("class","chose");
	    	 }
		}
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
	<style type="text/css">
	select {
	    width: 170px;
	}
	.cameraBottom label {
	    padding-top: 7px;
	    margin-bottom: 0;
	    text-align: right;
	}
	.col-sm-3 {
    width: 20%;
    float: left;
}
.col-sm-6 {
    width: 50%;
    float: left;
}
.form-group {
    height: 36px;
}
.main_right .imgbox .icon {
	    background: url(${ctxStatic}/admin/images/video-green.png) no-repeat;
	    cursor: auto;
	    z-index: 1;
	}
	canvas { 
	    position: absolute;
	    top: 0px;
    }
    .container{
	padding: 10px 30px;
    width: 100%;
    min-height: 800px;
    background: #fff;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
	}
	#inSec,#availableSec{
	list-style: none;
	height: 360px;
	width:200px;
	border: 1px solid #e0e6eb;
	border-radius: 4px;
	overflow-x: hidden;
    overflow-y: scroll;
    padding: 4px 6px;
     margin: 0px;
	}
	#inSec li,#availableSec li{
    cursor: pointer;
    line-height: 25px;
	}
	.freValue{
	width: 60px;
	margin-left: 5px;
	height: 10px !important;
	margin-top: -1px;
	}
	.chose{
	background:#0f7ff7;
	 color:#fff;
	}
	</style>
</head>
<body>
<div class="container">
	<tags:message content="${message}"/>
	<form id="searchForm" action="${ctx}/baseConfig/stList/cfAnalyzeChooseSave" method="post" class="breadcrumb form-search">
	<div style="clear: both;">
			<div class="cameraBox cameraBoxLeft" style="float: left;">
                    <p>可选择的报表项目</p>
	                   	<select class="form-control " id="availableSec" multiple="multiple" style="height: 360px;">
	                    	<c:forEach items="${stList}" var="item">
	                    		<option value="${item.code}">${item.nameList}</option>
	                    	</c:forEach>
	                    </select>
                </div>
                <div class="changeBox" style="float: left;margin-top: 160px;">
                    	<input id="inAdd" class="btn btn-primary" type="button" value="添加"/>
                    	<input id="inRemove" class="btn btn-primary" type="button" value="移除"/>
                    </div>
                <div class="cameraBox cameraBoxLeft" style="float: left;">
		 			<p>已选择的报表项目</p>
		 				<select class="form-control " id="inSec" name="stLists" multiple="multiple" style="height: 360px;">
	                    	<c:forEach items="${cfAnalyzeChooseDto}" var="item">
	                    		<option value="${item.codeList}">${item.nameList}</option>
	                    	</c:forEach>
	                    </select>
	 				</div>
		<div class="form-actions" style="float: left;margin-top: 140px;">
            <input id="btnSubmit" class="btn btn-primary" type="button" value="保 存"/>&nbsp;
        </div>
        </div>
     </form>
     </div>
</body>
</html>