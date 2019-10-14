<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>创建链接素材</title>
<meta name="decorator" content="default" />
<meta http-equiv=”content-type” content=”text/html; charset=UTF-8″ />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link href="${ctxStatic}/admin/css/im.css?v=11" type="text/css" rel="stylesheet" />
</head>
<body>
<input type="hidden" value="mt" id="LEFTNAVTYPEID"/><!-- 左边菜单类型 -->
<input type="hidden" value="link" id="MATERIALLISTTYPEID"/><!-- 素材类型列表 -->

<div class="lj-main">
	<%@ include file="/WEB-INF/views/modules/im/leftNav.jsp"%>
	<%@ include file="/WEB-INF/views/modules/im/materialList.jsp"%>
		
	<div class="lj-right telescopicButton">
		<img src="${ctxStatic}/admin/images/imImages/hb-icon.png" class="telescopicImage"/>
		<form action="" id="formFriend" method="post">
			<input type="hidden" id="code" name="code" value="${data.code }" />
			<input type="hidden" value="${merchantNo }" name="merchantNo"/>
	        <div class="edit-material">
	            <div class="tag">
	                <span class="n"><i>*</i>链接主题：</span>
	                <input type="text" class="ipt" id="title" name="title" value="${data.title }"/>
	            </div>
	              <div class="tag">
	                <span class="n"><i>*</i>链接标题：</span>
	                <input type="text" class="ipt" id="shareTitle" name="shareTitle" value="${data.shareTitle }"/>
	            </div>
	            <div class="tag">
	                <span class="n"><i>*</i>分享描述：</span>
	                <div>
	                    <textarea class="area" id="content" name="content" onkeyup="contentTxt()">${data.content }</textarea>
	                    <p class="tip">支持变量，发送时自动动态替换</p>
	                </div>
	            </div>
	            <div class="tag">
	                <span class="n" style="line-height: 44px;">缩略图：</span>
	                <div class="">
	                    <div>
	                    	<span class="s-btn">选择图片
		                        <input type="file" id="fileId" class="file-btn" value="" name="imgFiles" multiple="multiple" onchange="ajaxFileUpload({fileId:'fileId',imgId:'#imgListId',max:1,isCompress:true})"/>
		                        <input type="hidden" value="" name="imageUrl" class="imgAddr" id="imageUrl"/>
	                    	</span>
	                    </div>
	                    <p class="tip-num">只能选择1张</p>
	                    
	                    <ul class="img-list" id="imgListId">
	                    	<c:if test="${not empty data.imageUrl}">
		                    	<c:set value="${fn:split(data.imageUrl,',')}" var="img" />
		                    	
			                    <c:forEach items="${img}" var="s">
			                        <li data-src="${s}">
			                            <img src="${fns:getUploadUrl()}im/${s}" class="i" />
			                            <span class="del" onclick="$(this).parent('li').remove()"></span>
			                        </li>
								</c:forEach>
	                    	</c:if>
	                    </ul>
	                </div>
	            </div>
	            <div class="tag">
	                <span class="n"><i>*</i>链接：</span>
	                <input type="text" class="ipt" id="linkUrl" name="linkUrl" value="${data.linkUrl }"/>
	            </div>
	            <div class="tag">
	                <span class="n">素材类型：</span>
	                <select class="ipt" id="materialType" name="materialType">
	                    <option value="">请选择</option>
	                    <option value="1" <c:if test="${data.materialType eq 1}">selected</c:if>>朋友圈广告素材</option>
	                    <option value="2" <c:if test="${data.materialType eq 2}">selected</c:if>>朋友圈维护素材</option>
	                </select>
	            </div>
	            <div class="tag" style="align-items:center;">
	                <span class="n">自动评论：</span>
	                <div class="radio">
	                    <input type="radio" id="rdo1" class="rdo" name="autoComment" value="1" <c:if test="${data.autoComment eq 1}">checked</c:if>/>
	                    <label for="rdo1">是</label>
	                </div>
	                <div class="radio">
	                    <input type="radio" id="rdo2" class="rdo" name="autoComment" value="0" <c:if test="${data.autoComment ne 1}">checked</c:if>/>
	                    <label for="rdo2">否</label>
	                </div>
	                <p class="tip" style="line-height: 54px;">是否自动增加首条评论</p>
	            </div>
	            <div class="tag commentContent" id="tagCommentContent" style="display:<c:if test='${data.autoComment eq 1 }'>flex;</c:if>" >
	                <span class="n">评论内容：</span>
	                <div>
	                    <textarea class="area" onkeyup="commentContentTxt()" id="commentContent" name="commentContent">${data.commentContent }</textarea>
	                    <p class="tip">自动评论内容，为空时使用分享描述内容评论</p>
	                </div>
	            </div>
	            <div class="button-list" style="margin-top:50px;">
	                <input type="button" value="保存" class="b-btn" onclick="saveFriendsLink('#formFriend','${data.code }')"/>
	            </div>
	        </div>
		</form>
    </div>
    <div class="varExplain" onclick="$('#varExplainId').css('display','flex')">
    	<img src="${ctxStatic}/admin/images/imImages/question-icon.png" class="i"/>
    	<span class="n">变量说明</span>
    </div>
    <%@ include file="/WEB-INF/views/modules/im/varExplain.jsp"%> <!-- 变量说明 -->
</div>
<script src="${ctxStatic}/admin/js/ajaxfileupload.js?v=11" type="text/javascript"></script>
<script src="${ctxStatic}/admin/js/im/imGlobal.js?v=11" type="text/javascript"></script>
<script src="${ctxStatic}/admin/js/im/friendslinkmateriaViewH5.js?v=11" type="text/javascript"></script>
</body>
</html>