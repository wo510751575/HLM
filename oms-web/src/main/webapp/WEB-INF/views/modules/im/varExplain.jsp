<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="mask-layer" id="varExplainId">
    <div class="sys-variable">
	    <div class="sys-variable-side">
	        <div class="top">
	            <span class="n">素材变量</span>
	            <span class="s">来源</span>
	            <select class="slt" name="sysFlag" id="sysFlag" onchange="varExplainListData()">
	                <option value="">全部</option>
	                <option value="1">系统变量</option>
	                <option value="0">自定义</option>
	            </select>
	            <div class="search">
	                <input type="text" class="ipt" placeholder="名称/变量值" name="conditionStr" id="conditionStr"/>
	                <img src="${ctxStatic}/admin/images/imImages/search.png" class="i" onclick="varExplainListData()"/>
	            </div>
	        </div>
	        <div class="data-table" id="varExplainDataTable" style="height:400px;overflow: auto;"></div>
	        <div class="button-list">
	            <input type="button" value="关闭" class="b-btn" onclick="$('#varExplainId').hide()">
	        </div>
	    </div>
    </div>
</div>