<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>视频库</title>
	<meta name="decorator" content="default"/>
	
	<style type="text/css">
	.container{
	padding: 10px 30px;
    width: 100%;
    min-height: 800px;
    background: #fff;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
	}
	
	.a-upload{
                padding: 2px 10px;
                /*height: 34px;*/
                line-height: 28px;
                position: relative;
                cursor: pointer;
                color: #fff;
                background-color: #23B7E5;
                border-color: #23B7E5;
                border-radius: 4px;
                overflow: hidden;
                display: inline-block;
                *display: inline;
                *zoom: 1;
                position: relative;
               	top:12px
            }
            .a-upload input{
                position: absolute;
                font-size: 100px;
                right: 0;
                top: 0;
                opacity: 0;
                filter: alpha(opacity=0);
                cursor: pointer
            }
            .a-upload:hover{
                color: #FFFFFF;
                background: #337ab7;
                border-color: #204d74;
                text-decoration: none;
            }
	
	</style>
</head>
<body>
<div class="container">
<div style="float: right;">
	<form id="searchForm" action="${ctx}/cm/materialVideo/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="parentId" name="parentId" type="hidden" value="${findMaterialVideoPage.parentId}"/>
		<input id="parentIds" name="parentIds" type="hidden" value="${findMaterialVideoPage.parentIds}"/>
		<li class="btns"><input name="videoName" placeholder="请输入文件名" type="text" value="${videoName}"/></li>
		<li class="btns"><input id="search"  class="btn btn-primary" type="submit" value="查询"/></li><li class="btns">	
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<li class="btns">
			<input id="upload"  class="btn btn-primary" type="button" value="上传文件"/>
			<c:if test="${findMaterialVideoPage.parentId eq '0'}">
			<input id="newFolder"  class="btn btn-primary" type="button" value="新建文件夹"/>
			</c:if>
			<input id="delete" class="btn btn-primary" type="button" value="删除"/>
		</li>
	</form>
</div>
<ul class="nav nav-tabs">
	<li class="active"><a href="${ctx}/cm/materialVideo/">视频库</a></li>
	<c:if test="${findMaterialVideoPage.parentId != '0' }">
	<li ><a href="${ctx}/cm/materialVideo/">返回</a></li>
	</c:if>
</ul>
	
	<input id="repMsg" name="repMsg" style="display:none" value="${repMsg}"/>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th width="20">全选<input type="checkbox" id="allcheck"/></th>
				<th>文件名</th>
				<th>大小</th>
				<th>修改日期</th>
				<th>创建组织</th>
			</tr>
		</thead>
		<tbody id="infolist">
		<c:forEach items="${page.list}" var="item" varStatus="number"> 
			<tr>		
				<td width="20">
					<input type="checkbox"  value="${item.code}" data-parentId="${item.parentId }" data-videoName="${item.videoName }" data-folderName="${item.folderName }" data-merchantNo="${item.merchantNo }"/>
				</td>
				<td>
					<c:choose>
						<c:when test="${not empty item.videoName }">
							<c:if test="${fns:startsWith(item.firstView,'http')}">
								<img  width="48px" height="48px" src="${item.firstView}">
							</c:if>
							<c:if test="${!fns:startsWith(item.firstView,'http')}">
								<img  width="48px" height="48px" src="${fns:getUploadUrl()}${item.firstView}">
							</c:if>
							${item.videoName }
						</c:when>
						<c:otherwise>
							<img class="folder" src="${ctxStatic }/admin/images/folder.png"/>
							${item.folderName }
						</c:otherwise>
					</c:choose>
					
				</td>
				<td>
					 ${item.size}
					 <c:if test="${not empty item.size}">
					 KB
					 </c:if>
				</td>
				<td>
				<fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
				<c:choose>
					<c:when test="${empty item.officeName}">
						总管账号
					</c:when>
					<c:otherwise>
						${item.officeName}
					</c:otherwise>
				</c:choose>	
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
<script type="text/javascript">
		//是否包含字符串
		function isContains(str, substr) {
		    return str.indexOf(substr) >= 0;
		}
		
		$(document).ready(function() {
			var repMsg=$("#repMsg").val();
			var parentId = $("#parentId").val();
			if(repMsg){
				if(isContains(repMsg,"成功")&& (isContains(repMsg,"保存")||isContains(repMsg,"编辑"))){
					window.parent.frames[0].location.href="${ctx}/cm/materialVideo/list?parentId="+parentId;
					top.$.jBox.close(true);
				}else{
					showTip(repMsg);
					$("#repMsg").val("");
				}
			} 
		});
		
		$(".folder").click(function(){
			var code = $(this).parent().parent('tr').find('input[type="checkbox"]').val();
			location.href = "${ctx}/cm/materialVideo/list?parentId="+code;
		})
		
		$("#upload").click(function(){
			var parentId = $("#parentId").val();
			location.href = "${ctx}/cm/materialVideo/upload?parentId="+parentId;
			
		})
		
		
		$("#newFolder").click(function(){
			var parentId = $("#parentId").val();
			top.$.jBox.open("iframe:${ctx}/cm/materialVideo/form?parentId="+parentId,"", 380, 140,{
				id:666,
				top:'30%',
				title:'新建文件夹',
				//height:'auto',
				draggable: true,
				showClose: false,
				showScrolling:false,
				buttons:{},		//去除按钮
				iframeScrolling: 'no',
				loaded:function(h){
				},
				closed: function () { 
				} /* 信息关闭后执行的函数 */
			});
		})
			
		
		$("#delete").click(function(){
			var merchantNo = '${merchantNo}';
			var parentId = $("#parentId").val();
			var fileCodeArr = new Array;
			var result = true;
			$("#infolist :checkbox[checked]").each(function(i){
				fileCodeArr[i] = $(this).val();
				var flag = $(this).attr("data-merchantNo");
				if(merchantNo != null && flag == ''){
					alertx("删除内容中含总管账号创建的信息，请检查后重试!");
					result = false;
					return false;
				}
		    });
			if(!result){
				return false;
			}
			var fileCodes = fileCodeArr.join(',');//转换为逗号隔开的字符串
			if(!fileCodes){
				alertx("请选择文件!");
				return false;
			}
			location.href = "${ctx}/cm/materialVideo/delete?ids="+fileCodes+"&parentId="+parentId;
			
		})
		
		//全选、全不选
	    $("#allcheck").click(function(){
	    	  if(this.checked){   
	    	        $("#infolist :checkbox").prop("checked", true);  
	    	    }else{   
	    			$("#infolist :checkbox").prop("checked", false);
	    	    }  
	    });
		
		
		
</script>
</body>
</html>