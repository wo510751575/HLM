<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工作事项管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//入口操作
			$("#inAdd").on('click', function() {
				  var p = $("#availableSec").find(".chose");
			        $(p).append('<input type="text"  class="freValue" placeholder="跟进频率"  value=""/>')
			        p.clone().appendTo("#inSec");
			        p.remove();
			        $("#inSec li").removeClass("chose");
		      });

		      $("#inRemove").on('click', function() {
		    	  var p = $("#inSec").find(".chose");
			        $(p).find(".freValue").remove()
			        p.clone().appendTo("#availableSec");
			        p.remove();
			        $("#availableSec li").removeClass("chose");
		      });
		      
		      $("#btnSubmit").click(function(){
		    	  var count= $("#inSec li");
			    	 if(count.length>0){
			    		 for(var i=0;i<count.length;i++){
			    			var info= $(count[i]).find(".comTaskChooses").val()+","+$(count[i]).find(".freValue").val();
			    			$("#searchForm").next().clone().appendTo(count[i]);
			    			$(count[i]).find(".Chooses").val(info);
			    		 }
			    	 }
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
	<form id="searchForm" action="${ctx}/baseConfig/workTaskChoose/save" method="post" class="breadcrumb form-search">
	<div style="clear: both;">
			<div class="cameraBox cameraBoxLeft" style="float: left;">
                    <p>可选择的工作事项</p>
                    <ul id="availableSec" class="form-control">
                    <c:forEach items="${workTaskList}" var="item">
                    		<li value="${item.code}" onclick="check(this)">${item.taskName}
                    		<input  value="${item.code}" class="comTaskChooses"  style="display: none;" />
                    		</li>
                    	</c:forEach>
                    </ul>
                </div>
                <div class="changeBox" style="float: left;margin-top: 160px;">
                    	<input id="inAdd" class="btn btn-primary" type="button" value="添加"/>
                    	<input id="inRemove" class="btn btn-primary" type="button" value="移除"/>
                    </div>
                <div class="cameraBox cameraBoxLeft" style="float: left;">
		 			<p>已选择的工作事项</p>
                    <ul id="inSec" class="form-control">
                    <c:forEach items="${choosePageDtos}" var="item">
                    		<li onclick="check(this)">${item.nameList}<input type="text"  class="freValue" placeholder="跟进频率"  value="${item.freValue}"/>
                    		<input value="${item.codeList}" class="comTaskChooses"  style="display: none;" />
                    		</li>
                    	</c:forEach>
                    </ul>
	 				</div>
		<div class="form-actions" style="float: left;margin-top: 140px;">
            <input id="btnSubmit" class="btn btn-primary" type="button" value="保 存"/>&nbsp;
        </div>
        </div>
     </form>
       <input name="workTaskLists" value="" class="Chooses"   style="display: none;" />
     </div>
</body>
</html>