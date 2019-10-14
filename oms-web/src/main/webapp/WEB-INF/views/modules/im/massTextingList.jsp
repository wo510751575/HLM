<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>群发列表</title>
<meta name="decorator" content="default" />
<meta http-equiv=”content-type” content=”text/html; charset=UTF-8″ />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link href="${ctxStatic}/admin/css/im.css?v=11" type="text/css" rel="stylesheet" />
</head>

<body>
	<style type="text/css">
		.search-btn{display: block;font-size: 14px;background: #6cc4ac;height: 32px;color: #fff;line-height: 32px;text-align: center;border-radius: 8px;border: 0;margin-left: 20px;padding: 3px 10px;}
		.tdImg{width: 40px;}
		.tableFrame tr td{border-right: 1px solid #bdb9b9;}
	</style>
<input type="hidden" value="bk" id="LEFTNAVTYPEID"/><!-- 左边菜单类型 -->
<input type="hidden" value="massTexting" id="MATERIALLISTTYPEID"/><!-- 素材类型列表 -->
<input type="hidden" id="ctxStatic" value="${ctxStatic }" />
<input type="hidden" value="${ctx }" id="ctx" />
<input type="hidden" value="${merchantNo }" id="merchantNo" />
<input type="hidden" value="${assistantNo }" id="assistantNo"/>
<div class="lj-main">
	<%@ include file="/WEB-INF/views/modules/im/leftNav.jsp"%>
	<%@ include file="/WEB-INF/views/modules/im/friendsList.jsp"%>

	<div class="lj-right telescopicButton">
		<img src="${ctxStatic}/admin/images/imImages/hb-icon.png" class="telescopicImage"/>
	    <div class="search">
	        <span class="n">群发时间：</span>
	        <input id="startDate" name="startDate" type="text" placeholder="起始时间" readonly="readonly" maxlength="20" class="ipt input-mini Wdate doTime"
						value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true,maxDate:'#F{$dp.$D(\'endDate\',{d:0})}'});"/>

	        <span class="z">至</span>
	        <input id="endDate" name="endDate" type="text" placeholder="结束时间" readonly="readonly" maxlength="20" class="ipt input-mini Wdate doTime"
						value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true,minDate:'#F{$dp.$D(\'startDate\',{d:0})}'});"/>
							<span class="n" style="margin-left:6%;">群发内容：</span>
						<div class="s" style="margin-left:0;">
	            <input type="text" class="s-ipt" id="conditionStr" name="conditionStr"/>
	            <%-- <img src="${ctxStatic}/admin/images/imImages/search.png" class="s-btn" onclick="listData()"/> --%>
	        </div>
					<span class="search-btn" onclick="listData()">查询</span>
	    </div>
	    <div class="data-table" id="data-table-list"></div>
	</div>
</div>
<script src="${ctxStatic}/admin/js/im/imGlobal.js?v=11" type="text/javascript"></script>
<script src="${ctxStatic}/admin/js/im/massTextingList.js?v=11" type="text/javascript"></script>
</body>
</html>
