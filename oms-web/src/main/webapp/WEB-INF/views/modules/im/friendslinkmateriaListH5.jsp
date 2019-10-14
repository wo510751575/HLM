	<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>链接素材</title>
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
	    <div class="top">
	        <span class="n">链接素材</span>
	        <a href="${ctx }/im/friendsLinkMaterialForm?code=" class="btn" >创建链接素材</a>
	    </div>
	    <div class="search">
	        <span class="n">创建日期：</span>
	        <input id="startDate" name="startDate" type="text" placeholder="起始时间" readonly="readonly" maxlength="20" class="ipt input-mini Wdate doTime"
						value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true,maxDate:'#F{$dp.$D(\'endDate\',{d:0})}'});"/>
	        
	        <span class="z">至</span>
	        <input id="endDate" name="endDate" type="text" placeholder="结束时间" readonly="readonly" maxlength="20" class="ipt input-mini Wdate doTime"
						value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true,minDate:'#F{$dp.$D(\'startDate\',{d:0})}'});"/>
	        <div class="s">
	            <input type="text" class="s-ipt" placeholder="标题/描述" id="conditionStr" name="conditionStr"/>
	            <img src="${ctxStatic}/admin/images/imImages/search.png" class="s-btn" onclick="listData()"/>
	        </div>
	    </div>
	    <div class="data-table" id="data-table-list"></div>
	</div>
</div>
<script src="${ctxStatic}/admin/js/im/imGlobal.js?v=11" type="text/javascript"></script>
<script src="${ctxStatic}/admin/js/im/friendslinkmateriaListH5.js?v=11" type="text/javascript"></script>
</body>
</html>