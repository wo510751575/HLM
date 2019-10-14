<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>终端推送配置</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/common/plupload.full.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			initView();
			
			$("#label").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					var type=$("#tsType  option:selected").val();
					var g = $("#gzh .textContent").html();
					if(type == 'PA' ){
						if(g.length==0){
							alert("请选择要推送的公众号");
							return false;
						}
					}else if(type == 'SP'){
						if(g.length==0){
							alert("请选择要推送的小程序");
							return false;
						}
					}
					//获取选择的终端展示信息
					var str = "";
					$("#noWxSec").find("option:selected").each(function(i){
					      str += $(this).text()+",";
					});
					var remarks = str.substring(0,str.length-1); //去掉最后面的逗号
					$("#remark").val(remarks);
					
					loading('正在提交，请稍等...');
					$("#btnSubmit").attr("disabled","disabled");form.submit()
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
			
			$('textarea[name="content"]').on('input propertychange', function() {
				preV()
			})

		});
		
		function initView(){
	    	$('.form-group-select-css').select2({
	    		allowClear: true
	    	});
	    }
		
		function preV() {
			var shopUrl = 'http://www.xxx.com/index';
			var shopName = '深圳香蜜湖店';
			var mgrName = '张店长';
			var gmName = '导购张三';
			var emoji = '${emojis}'.split(',');
			
			var content = $('#content').val();
			content = content.replace(/{shopUrl}/g, shopUrl);
			content = content.replace(/{shopName}/g, shopName);
			content = content.replace(/{mgrName}/g, mgrName);
			content = content.replace(/{gmName}/g, gmName);
			
			if (content.indexOf("{emoji}") >= 0) {
				var count = patch('{emoji}',content);
				for (var i = 0; i < count; i++) {
					randomIndex = parseInt(Math.random() * (emoji.length - 1));
					content = content.replace(/{emoji}/, '<img src="${fns:getUploadUrl()}'+emoji[randomIndex]+'" style="width:30px" />');
				}
			}
			
			$('.textContent').html(content);
		}
		function patch(re,s){
			re=eval("/"+re+"/ig")
			return s.match(re).length;
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
		
		#gzh .leftContr{
			width: 460px;
			height: 100px;
			border: 1px solid #ccc;
			padding: 10px;
			word-break: break-all;
			float: left;
		}
		#gzh .choseGzh{
			margin-left: 10px;
		}
		#gzh .controls .headImg{
			width:50px;
			float: left;
		}
		#gzh .controls  .headImg img {
			width: 50px;
			height: 50px;
		}
		
		#gzh .controls .textContent{
			margin-left: 10px;
			margin-top: 10px;
			float: left;
			width: 400px;
		}
		#coupon,#gzh{
			display: none;
		}
		
		.mask-layer {
		    position: absolute;
		    top: 0;
		    left: 0;
		    width: 100%;
		    z-index: 20;
		    height: 100%;
		    background: rgba(0,0,0,0.6);
		    display: none;
		    align-items: center;
		    justify-content: center;
		}
		.mask-layer .sys-variable {
		    width: 80%;
		    display: flex;
		    border: 1px solid #d1d1d1;
		    overflow: auto;
		    background: #fff;
		    min-height: 542px;
		    max-height: 80%;
		}
		.mask-layer .sys-variable .sys-variable-side {
		    flex: 1;
		    position: relative;
		}
		 .mask-layer .sys-variable .top {
		    display: flex;
		    align-items: center;
		    padding: 15px 41px 15px 40px;
		    border-bottom: 1px solid #d1d1d1;
		}
		#wxNosListId .sys-variable .top .wxTitle {
		    color: #2A2A2A;
		    font: 18px;
		}
		.mask-layer .sys-variable .top .search {
		    height: 30px;
		    border: 1px solid #474747;
		    font-size: 16px;
		    border-radius: 30px;
		    display: flex;
		    align-items: center;
		    margin-left: 10px;
		}
		.mask-layer .sys-variable .top .search .ipt {
		    height: 21px;
		    border: 0;
		    font-size: 14px;
		    margin-left: 10px;
		    width: 200px;
		    margin-bottom: 0;
		}
		.mask-layer .sys-variable .top .search .i {
		    height: 16px;
		    height: 16px;
		    margin-right: 10px;
		    cursor: pointer;
		}
		#wxNosListTable ul li {
		    justify-content: space-between;
		    padding: 0 1.5%;
		    height: 60px;
		    border-bottom: 1px solid #959595;
		    margin-bottom: 0;
		    font-size: 13px;
		}
		
		.mask-layer .button-list {
		    text-align: center;
		    margin-top: 16px;
		    margin-bottom: 16px;
		}
		
		 .button-list .g-btn {
		    width: 99px;
		    height: 40px;
		    background: #ececec;
		    color: #2a2a2a;
		    border: 1px solid #e3e3e3;
		    box-sizing: border-box;
		    font-size: 14px;
		    border-radius: 8px;
		}
		 .button-list .b-btn {
		    width: 99px;
		    height: 40px;
		    background: #ececec;
		    color: #2a2a2a;
		    font-size: 14px;
		    border-radius: 8px;
		    border: 0;
		}
		#wxNosListId .sys-variable #wxNosListTable ul li .wxbox span {
		    display: inline-block;
		    width: 33%;
		    text-align: center;
		}
		#wxNosListId .sys-variable #wxNosListTable ul li .wxbox span:first-child{
			width: 10%;
		}
		#wxNosListId .sys-variable #wxNosListTable ul .redLi .wxbox>span {
		    color: red;
		}
		#wxNosListId .sys-variable #wxNosListTable ul li .wxbox {
		    width: 100%;
		    height: 60px;
		    display: flex;
		    align-items: center;
		}
		#wxNosListId ul, li {
		    list-style: none;
		    margin: 0;
		}
		::-webkit-scrollbar {
		    width: 5px;
		    height: 12px;
		    -webkit-border-radius: 40%;
		}
		#noWxs{
			float: left;
			margin-right: 10px;
		}
		.personCardContent,.wxSmallProgramContent{position: absolute;z-index: 2000;width: 70%;min-height: 542px;;background: white;left: 15%;top: 10%; }
		.personCardBox .personCardContent .top,.wxSmallProgramBox .wxSmallProgramContent .top{
		    display: flex;
		    align-items: center;
		    padding: 15px 41px 15px 40px;
		    border-bottom: 1px solid #d1d1d1;
		}
		.personCardBox .personCardContent #cardContent,.wxSmallProgramBox .wxSmallProgramContent #wxSmallContent{
			height: 360px;
			overflow: scroll;
		}
		.personCardBox  .personCardContent .button-list {  margin-top: 16px; margin-bottom:16px;position: absolute;bottom: 0;right: 15px;}
		.personCardBox  .personCardContent .b-btn{ width: 99px; height:40px; background: #ececec; color: #2a2a2a;font-size: 14px; border-radius: 8px; border:0;}
		.personCardBox  .personCardContent .b-btn:hover{ background: #6cc4ac; color:#fff;}
		.personCardBox  .personCardContent .g-btn{ width: 99px; height:40px; background: #ececec; color: #2a2a2a; border:1px solid #e3e3e3; box-sizing:border-box; font-size: 14px; border-radius: 8px;}
		.personCardBox  .personCardContent .g-btn:hover{background: #6cc4ac;color: #fff;}
		.personCardBox  .personCardContent ul{ margin:2%;width:96%; border-top:1px solid #959595; border-right:1px solid #959595;  border-left:1px solid #959595; box-sizing: border-box;}
		.personCardBox  .personCardContent ul li{border-bottom: 1px solid #959595;text-align: center;font-size: 14px;padding: 0;display: flex;padding: 10px 0;}
		.personCardBox  .personCardContent ul li:hover,.personCardBox  .personCardContent ul li.active {background: #def6e0;cursor: pointer;}
		.personCardBox  .personCardContent ul li .n{display: flex;width: 14%;align-items: center;justify-content: center;margin-left: 10px;}
		.personCardBox  .personCardContent ul li .n img{width: 37px;height: 37px;}
		.personCardBox  .personCardContent ul li .d{text-align: left;width: 84%;word-wrap: break-word;display: flex;align-items: center;}
		
		.wxSmallProgramBox  .wxSmallProgramContent .button-list {  margin-top: 16px; margin-bottom:16px;position: absolute;bottom: 0;right: 15px;}
		.wxSmallProgramBox  .wxSmallProgramContent .b-btn{ width: 99px; height:40px; background: #ececec; color: #2a2a2a;font-size: 14px; border-radius: 8px; border:0;}
		.wxSmallProgramBox  .wxSmallProgramContent .b-btn:hover{ background: #6cc4ac; color:#fff;}
		.wxSmallProgramBox  .wxSmallProgramContent .g-btn{ width: 99px; height:40px; background: #ececec; color: #2a2a2a; border:1px solid #e3e3e3; box-sizing:border-box; font-size: 14px; border-radius: 8px;}
		.wxSmallProgramBox  .wxSmallProgramContent .g-btn:hover{background: #6cc4ac;color: #fff;}
		.wxSmallProgramBox  .wxSmallProgramContent ul{ margin:2%;width:96%; border-top:1px solid #959595; border-right:1px solid #959595;  border-left:1px solid #959595; box-sizing: border-box;}
		.wxSmallProgramBox  .wxSmallProgramContent ul li{border-bottom: 1px solid #959595;text-align: center;font-size: 14px;padding: 0;display: flex;padding: 10px 0;}
		.wxSmallProgramBox  .wxSmallProgramContent ul li:hover,.wxSmallProgramBox  .wxSmallProgramContent ul li.active {background: #def6e0;cursor: pointer;}
		.wxSmallProgramBox  .wxSmallProgramContent ul li .n{display: flex;width: 14%;align-items: center;justify-content: center;margin-left: 10px;}
		.wxSmallProgramBox  .wxSmallProgramContent ul li .n img{width: 37px;height: 37px;}
		.wxSmallProgramBox  .wxSmallProgramContent ul li .d{text-align: left;width: 84%;word-wrap: break-word;display: flex;align-items: center;}
		.wxSmallProgramBox .wxSmallProgramContent .top,.personCardBox .personCardContent .top{
			justify-content: space-between;
		}
		.personCardBox .personCardContent .top .querymp{
			width: 300px;
		    display: flex;
		}
		.personCardBox .personCardContent .top .querymp .mp input {
		    width: 60%;
		}
		.personCardBox .personCardContent .top .querymp .doquery {
		    width: 99px;
		    height: 33px;
		    background: #6cc4ac;
		    color: #fff;
		    font-size: 14px;
		    border-radius: 8px;
		    border: 0;
		}
		.personCardBox,.wxSmallProgramBox{position: fixed;z-index: 1000;background: rgba(0, 0, 0, 0.5);width: 100%;height: 100%;top:0;left: 0; display: none;}
		#shopNo,#choseshopName{
			display: none;
		}
		.pagination {
		    width: 89%;
		    padding: 0 5.5%;
		    text-align: right;
		    font-size: 13px;
		    color: #474747;
		    margin-top: 20px;
		    height: 30px;
		    line-height: 30px;
		    margin: 8px 0;
		}
		.pagination p {
		    display: inline-block;
		}
		.pagination select {
		    width: 55px;
		}
		.pagination img {
		    margin-left: 1%;
		    margin-top: -5px;
		    display: inline-block;
		}
		.pageShow {
		    width: 40px;
		    height: 25px;
		    border: 1px solid #d2d2d2;
		    text-align: center;
		}
		#gzh .shareInfo {
		    border: 1px solid #d1d1d1;
		    border-radius: 7px;
		    max-width: 73%;
		    text-align: left;
		    line-height: 26px;
		    cursor: pointer;
		}
		#gzh .sendDec {
		    width: 300px;
		    height: 46px;
		    display: flex;
		    font-size: 15px;
		    color: #9c9c9c;
		    line-height: 22px;
		    padding: 10px;
		}
		#gzh .sendDec img {
		    margin-right: 10px;
		    width: 50px;
		    height: 50px;
		    display: inline-block;
		}
		
		#gzh  .sendDec .sendDecText {
		    width: 70%;
		    word-wrap: break-word;
		    overflow: hidden;
		    text-overflow: ellipsis;
		    display: -webkit-box;
		    -webkit-line-clamp: 3;
		    -webkit-box-orient: vertical;
		}
		#gzh  .sendDecText p {
		    overflow: hidden;
		    text-overflow: ellipsis;
		    display: -webkit-box;
		    -webkit-line-clamp: 2;
		    -webkit-box-orient: vertical;
		}
		#gzh  .sendTitle {
		    font-size: 15px;
		    color: #dbdbdb;
		    border-top: 1px solid #d1d1d1;
		    margin: 0 10px;
		    padding: 0px;
		    border-bottom: 0px;
		    line-height: 24px;
		    text-align: left;
		}
		#gzh  .sendDecText p:first-child {
		    overflow: hidden;
		    text-overflow: ellipsis;
		    display: -webkit-box;
		    -webkit-line-clamp: 1;
		    -webkit-box-orient: vertical;
		}
		
		.wxSmallProgramBox .wxSmallProgramContent .top .queryXcx {
		    width: 425px;
		    display: flex;
		    justify-content: space-around;
		}
		.wxSmallProgramBox .wxSmallProgramContent .top .queryXcx .xcxYhu {
		    display: flex;
		    width: 35%;
		}
		.wxSmallProgramBox .wxSmallProgramContent .top .queryXcx .xcxYhu #queryType {
		    width: 70%;
		}
		.wxSmallProgramBox .wxSmallProgramContent .top .queryXcx .xcxM input {
		    width: 60%;
		}
		.wxSmallProgramBox .wxSmallProgramContent .top .queryXcx .doquery {
		    width: 99px;
		    height: 33px;
		    background: #6cc4ac;
		    color: #fff;
		    font-size: 14px;
		    border-radius: 8px;
		    border: 0;
		}
		.select-medium{
			width: 100%;
		}
	</style>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/member/wxPushConfig/list">终端推送配置列表</a></li>
		<li class="active"><a href="${ctx}/member/wxPushConfig/addView">终端推送配置添加</a></li>
	</ul><br/>
	<form id="inputForm" action="${ctx}/member/wxPushConfig/add" method="post" class="form-horizontal">
		<tags:message content="${message}"/>
		<input type="hidden" name="pushTime" value="CLAIMED" /><!-- 门诊版认领后推送 -->
		<input type="hidden" name="remark" id="remark"/><!-- 电商模式存储别名和微信昵称展示用 -->
		
		<div class="control-group">
			<label class="control-label">请选择推送终端:</label>
			<div class="controls">
				<select class="required form-group-select-css select-medium" name="noWx" id="noWxSec" multiple>
					<c:forEach items="${findShopTerminalReturns}" var="item">
						<option value="${item.noWx}">${item.alias} - ${item.wxNickname}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		
		<%-- <div class="control-group">
			<label class="control-label">推送时间:</label>
			<div class="controls">
				<select style="width: 150px;" name="pushTime" onchange="changeShow(this)">
		             <c:forEach items="${pushTimes}" var="item">
						<option value="${item}">${item.name}</option>
					 </c:forEach>
             	</select>
			</div>
		</div> --%>
		
		<div class="control-group">
			<label class="control-label">类型:</label>
			<div class="controls">
				<select id="tsType" style="width: 150px;" name="type" onchange="show(this)">
	             <c:forEach items="${pushConfigTypes}" var="item">
	             	<c:if test="${item !='GM_CARD'}">
	             		<option value="${item}" >${item.name}</option>
	             	</c:if>
				</c:forEach>
             </select>
			</div>
		</div>
		
		<div id="coupon">	<!-- 优惠券开始 -->
			<div class="control-group">
				<label class="control-label">标题:</label>
				<div class="controls">
					<input  name="shareTitle" class="required  input-xxlarge"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">描述:</label>
				<div class="controls">
					<textarea rows="5"  name="shareDes" maxlength="500" class="required  input-xxlarge"></textarea>
				</div>
			</div>
	        <div class="control-group">
				<label class="control-label">链接:</label>
				<div class="controls">
					<input   name="shareUrl" class="required input-xxlarge"/>
				</div>
			</div>
		</div>		<!-- 优惠券结束 -->
		<div id="gzh">	<!-- 公众号/小程序开始 -->
			<div class="control-group" >
				<label class="control-label">预览:</label>
				<div class="controls">
					<div class="leftContr">
						<div class="headImg">
							<img alt="" src="${ctxStatic}/admin/images/introduce/file.png">
						</div>
						<div class="textContent"></div>
					</div>
					<input class="btn btn-primary choseGzh" onclick="personCardOther(8)" type="button" value="请选择">
				</div>
				
			</div>
		</div>			<!-- 公众号/小程序结束 -->
		<div class="control-group" id="txt_content">
			<label class="control-label">内容:</label>
			<div class="controls">
				<!-- <div class="sendInfo" id="txtContent"  contentEditable="true" ></div> -->
				<textarea rows="5" name="content" id="content"  maxlength="500" class="required  input-xxlarge"></textarea><br/><br/>
				请选择标签变量：
				<input id="shopName" class="btn" type="button" value="终端名称" onclick="addContent('stName')"/>
<!-- 				<input id="mgrName" class="btn" type="button" value="店长姓名" onclick="addContent('mgrName')"/> -->
				<input id="emoji" class="btn" type="button" value="微信表情" onclick="addContent('emoji')"/>
<!-- 				<input id="gmName" class="btn" type="button" value="导购姓名" onclick="addContent('gmName')" style="display:none;"/> -->
			</div>
		</div>
		<div class="control-group" id="preView">
			<label class="control-label">预览:</label>
			<div class="controls">
				<div class="headImg">
					<img alt="" src="${ctxStatic}/admin/images/introduce/file.png">
				</div>
				<div class="textContent"></div>
			</div>
		</div>
		<div class="control-group" id="img_div" style="display: none">
               <label class="control-label">图片:</label>
               <div class="controls">
				<div id="showImgAddr_btn" style="border: 1px solid #e0e6eb; width: 120px; height: 120px; line-height: 100px; text-align: center">
					<c:if test="${!empty data.value}">
						<img width="120px" height="120px" src="${fns:getUploadUrl()}${data.value}" />
					</c:if>
					<c:if test="${empty data.value}">选择图片</c:if>
				</div>
				<input id="img" type="hidden" name="shareIcon" value="${data.value}">
			</div>
        </div>
		<div class="control-group">
			<label class="control-label">排序:</label>
			<div class="controls">
				<input name="sort" value="" maxlength="11" class="required digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态:</label>
			<div class="controls">
				<input id="checkbox" name="status" type="radio"  value="USE" checked="checked" class="required"/>有效
				<input id="checkbox" name="status" type="radio" value="STOP"  class="required"/>无效
			</div>
		</div>
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form>
	
</div>
	
	
	<!-- 联系人名片或公众号名片 -->
		<div class="personCardBox">
			<div class="personCardContent">
				<div class="top">
					<input type="hidden" value="0" class="certFlag"/>
					<span class="n">联系人名片列表</span>
					<div class="querymp">
						<div class="mp">
							<span>名称：</span>
							<input type="text" placeholder="名称" value=""/>
						</div>
						<input type="button" value="查询" class="doquery" onclick="queryCard()"/>
					</div>
				</div>
				<div id="cardContent">
					<ul></ul>
				</div>
				<div class="pagination">
					<p>总计<span class="total"></span>条记录，共<span class="totalPage"></span>页，当前第 <input value="1"  class="pageShow" type="tel"> 页，每页
						<select>
							<option selected="selected">10</option>
							<option>20</option>
							<option>30</option>
							<option>50</option>
						</select>
					条</p>
					<img alt="" onclick="changeCardPage('first')" class="first" src="${ctxStatic}/admin/images/imImages/arrow_back1.png">
					<img alt="" onclick="changeCardPage('prev')" class="prev" src="${ctxStatic}/admin/images/imImages/arrow_double_b1.png">
					<img alt="" onclick="changeCardPage('next')" class="next" src="${ctxStatic}/admin/images/imImages/arrow_doublef1.png">
					<img alt="" onclick="changeCardPage('last')" class="last" src="${ctxStatic}/admin/images/imImages/arrow_font1.png">	
				</div>
				<div class="button-list" style="text-align:right; margin-right:15px;">
		            <input type="button" value="确定" class="save g-btn" onclick="sendSub(this)">
		            <input type="button" value="取消" class="doNow b-btn" onclick="$('.personCardBox').hide()">
		        </div>
			</div>
		</div>
		
		<!-- 小程序 -->
		<div class="wxSmallProgramBox">
			<div class="wxSmallProgramContent">
				<div class="top">
					<input type="hidden" value="0" class="certFlag"/>
					<span class="n">小程序列表</span>
					<div class="queryXcx">
						<div class="xcxYhu">
							<span>类型：</span>
							<select id="queryType">
							</select>
						</div>
						<div class="xcxM">
							<span>名称：</span>
							<input type="text" placeholder="小程序名称" value=""/>
						</div>
						<input type="button" value="查询" class="doquery" onclick="queryXCX()"/>
					</div>
				</div>
				<div id="wxSmallContent">
					<ul></ul>
				</div>
				<div class="pagination">
					<p>总计<span class="total"></span>条记录，共<span class="totalPage"></span>页，当前第 <input value="1"  class="pageShow" type="tel"> 页，每页
						<select>
							<option selected="selected">10</option>
							<option>20</option>
							<option>30</option>
							<option>50</option>
						</select>
					条</p>
					<img alt="" onclick="changeSmallProPage('first')" class="first" src="${ctxStatic}/admin/images/imImages/arrow_back1.png">
					<img alt="" onclick="changeSmallProPage('prev')" class="prev" src="${ctxStatic}/admin/images/imImages/arrow_double_b1.png">
					<img alt="" onclick="changeSmallProPage('next')" class="next" src="${ctxStatic}/admin/images/imImages/arrow_doublef1.png">
					<img alt="" onclick="changeSmallProPage('last')" class="last" src="${ctxStatic}/admin/images/imImages/arrow_font1.png">	
				</div>
				<div class="button-list" style="text-align:right; margin-right:15px;">
		            <input type="button" value="确定" class="save g-btn" onclick="sendSubxcx(this)">
		            <input type="button" value="取消" class="doNow b-btn" onclick="$('.wxSmallProgramBox').hide()">
		        </div>
			</div>
		</div>
		<script type="text/javascript">
		var uploader_show = new plupload.Uploader({ //实例化一个plupload上传对象
			browse_button : 'showImgAddr_btn',
			url : '${ctx}/file/upload?dirName=activity',
			multi_selection:false,
			auto_start : true,
			flash_swf_url : '${ctxStatic}/common/Moxie.swf',
			silverlight_xap_url : '${ctxStatic}/common/Moxie.xap',
			filters: {
			  mime_types : [ //只允许上传图片文件
			    { title : "图片文件", extensions : "jpg,gif,png" }
			  ],
			  max_file_size : '10240kb',
			  prevent_duplicates : true 
			},
			multipart_params: {
				fileType: 'image'
			}
		});
		uploader_show.init(); //初始化
		uploader_show.bind('FilesAdded',function(uploader_show,files){
			if(files.length>0){
				uploader_show.start();	
			}
		});
		uploader_show.bind('Error',function(uploader_show,errObject){
			if(errObject.code!=-602){
				showTip(errObject.message,"info");
			}
		});
		uploader_show.bind('FileUploaded',function(uploader_show,file,responseObject){
				var response = $.parseJSON(responseObject.response);
				$("#showImgAddr_btn").html('<img width="120px" height="120px" src="'+uploadUrl+'/oms'+response.url+'"/>');
				$("#img").val("/oms" + response.url);
		});
		
		/*
		* 表格分页
		*/
		(function($){
			var defaults = {
				success:function(){},
				counts:[10,20,30],
				isTable:true, // 是否是表格
			}
			var ImPage = function(elem,options){
				this.options = options;
				this.counts = options.counts;
				this.isTable = options.isTable;
				this.init(elem,this,options);
			};
			ImPage.prototype = {
				init:function(elem,ui,options){
					$(elem).html('');
					options.success(elem,ui);
				},
				createTable:function(elem,callback){
					var tags = '';
					if(this.isTable){
						var thead = this.options.thead || [];
						var twidth = this.options.twidth || [];
						tags += '<div class="tableFrame"><table class="table">';
						tags += '<thead>';
						for(var i = 0, len = thead.length; i < len; i++){
							tags += '<td width="'+ (twidth[i]||'') +'">'+ thead[i] +'</td>'
						}
						tags += '</thead></div>';
						tags += '<tbody>';
						try{
							tags += callback();
						}catch(e){}
						tags += '</tbody>';
						tags += '</table>';
					}else{
						tags += callback();
					}
					$(elem).find('.imTable').remove();
					$(elem).append(tags);
				},
				createPages:function(elem,data){ // 创建标签
					var counts = this.counts;
					var data = data || {};
					var totalPage = (data.total%data.limit ? parseInt(data.total/data.limit) + 1 : parseInt(data.total/data.limit))
					var tags = '';
					tags += '<div class="lj-imPaging">';
					tags += '<span>总计'+ (data.total || 0) +'条记录</span>';
					tags += '<span>共'+ totalPage +'页</span>';
					tags += '当前第<input type="text" value="'+ data.pageNo +'" class="pageNo" />页&nbsp;';
					tags += '每页<select class="pageSize">';
					for(var i = 0 , len = counts.length; i < len; i++){
						tags += '<option value="'+ counts[i] +'" '+ (counts[i]==data.limit ? 'selected':'') +'>'+ counts[i] +'</option>';
					}
					tags += '</select>条';
					tags += '<input class="first pageBtn" page-no="1" '+ ((data.pageNo == 1)?'disabled="disabled"':'') +'/>';
					tags += '<input class="prev pageBtn" page-no="'+ (parseInt(data.pageNo) - 1) +'" '+ ((data.pageNo == 1)?'disabled="disabled"':'') +'/>';
					tags += '<input class="next pageBtn" page-no="'+ (parseInt(data.pageNo) + 1) +'" '+ ((data.pageNo == totalPage || totalPage == 0)?'disabled="disabled"':'') +'/>';
					tags += '<input class="last pageBtn" page-no="'+ totalPage +'" '+ ((data.pageNo == totalPage || totalPage == 0)?'disabled="disabled"':'') +'/>';
					tags += '</div>';
					$(elem).find('.lj-imPaging').remove();
					$(elem).append(tags);
					this.pageEvent(elem,this,this.options,'.lj-imPaging');
				},
				pageEvent:function(elem,ui,options,btn){
					var self = this;
					$(btn).on('click','.pageBtn',function(){
						$(elem).html('');
						var params = {
							pageNo:$(this).attr('page-no'),
							pageSize:$(this).siblings('.pageSize').val()
						};
						options.success(elem,ui,params);
					});
					$(btn).on('change','.pageSize',function(){
						$(elem).html('');
						var params = {
							pageNo:1,
							pageSize:$(this).val()
						};
						options.success(elem,ui,params);
					});
					$(btn).on('blur','.pageNo',function(){
						$(elem).html('');
						var params = {
							pageNo:$(this).val(),
							pageSize:$(this).siblings('.pageSize').val()
						};
						options.success(elem,ui,params);
					});
				}
			};

			$.fn.ImPaging = function(options){
				var options = $.extend({},defaults,options);
				var imPage = new ImPage(this,options);
			}
		})(jQuery);
		function addContent(id) {
			var oldVal = $('textarea[name=content]').val();
			if (oldVal == undefined) {
				oldVal = '';
			}
			var tag = '{' + id + '}';
			$('textarea[id=content]').val(oldVal + tag);
			/* insertImg('{' + id + '}', "txtContent"); */
			preV();
		}
		function show(obj) {
			if (obj.value == 'GREET') {
				$('#txt_content').css('display', 'block')
				$('#img_div').css('display', 'none')
				$('#preView').css('display', 'block')
				$('#coupon').css('display', 'none')
				$("#gzh").css('display', 'none')
			} else if (obj.value == 'IMAGE') {
				$('#txt_content').css('display', 'none')
				$('#img_div').css('display', 'block')
				$('#preView').css('display', 'none')
				$('#coupon').css('display', 'none')
				$("#gzh").css('display', 'none');
			} else if(obj.value == 'SHARE'){
				$('#coupon').css('display', 'block')
				$('#txt_content').css('display', 'none')
				$('#img_div').css('display', 'block')
				$('#preView').css('display', 'none')
				$("#gzh").css('display', 'none')
			}else if(obj.value == 'PA' || obj.value == 'SP'){
				$("#gzh").css('display', 'block')
				$('#txt_content').css('display', 'none')
				$('#preView').css('display', 'none')
				$('#coupon').css('display', 'none')
				$('#img_div').css('display', 'none')
				if(obj.value == 'PA'){
					$("#gzh .choseGzh").val("请选择公众号");
					$("#gzh .choseGzh").attr("onclick","personCardOther(8)")
				}else{
					$("#gzh .choseGzh").val("请选择小程序");
					$("#gzh .choseGzh").attr("onclick","showXCX()")
				}
			}else{
				$('#txt_content').css('display', 'none')
				$('#img_div').css('display', 'none')
				$('#preView').css('display', 'none')
				$('#coupon').css('display', 'none')
				$("#gzh").css('display', 'none')
			}
			
			$("#gzh .textContent").html("");
			$("#content").val("");
			$("#preView .textContent").html("");
			$("#coupon input[name='shareTitle']").val("");
			$("#coupon textarea[name='shareDes']").val("");
			$("#coupon input[name='shareUrl']").val("");
			$("#img").val("");
			$("#showImgAddr_btn").html("选择图片");
		}
		function changeShow(obj){
			if (obj.value == 'CLAIMED') {	//认领后推送
				var html='<option value="GM_CARD">导购名片</option>';
				$("#tsType  option[value='MGR_CARD']").after(html);
				$("#gmName").css("display", "block");
			} else if (obj.value == 'PUSHNOW') {	//立即推送
				$("#tsType  option[value='GM_CARD']").remove();
				$("#gmName").css("display", "none");
			} 
		}
		var NODATATABLELIST = []; // 所有数据
		
		/*
		 * 搜索网点微信号
		 * type:Y-已选,N-未选
		 */
		function searchWxNoDataList(elem,id){
			 var rows =  NODATATABLELIST;
			var selectRows = []; //搜索匹配项
			var key = $(elem).find('input.ipt').val().replace(/\s/ig,'');
			$.each(rows,function(i){
				var reg = new RegExp('.*' + key + '.*','ig');
				if(reg.test(JSON.parse(rows[i]).noWx)){
					selectRows.push(rows[i]);
				}
			});
			
			var tags = '';
			$.each(selectRows,function(m){
				tags += '<li data-Info=\''+JSON.stringify(selectRows[m])+'\' ><label onclick="checkWX(this)" class="wxbox" for="wxsBox'+ m +'"><span><input type="checkbox" class="boxNowx" onchange="cancel()" id="wxsBox'+ m +'" data-shopNo='+JSON.parse(selectRows[m]).shopNo +' data-shopName='+JSON.parse(selectRows[m]).shopName +' data-nowx='+ JSON.parse(selectRows[m]).noWx +'></span>';
				tags += '<span>'+ JSON.parse(selectRows[m]).noWx +'</span>';
				tags += '<span>'+ JSON.parse(selectRows[m]).shopName +'</span>';
				tags += '<span><img width="50px" height="50px" src="'+uploadUrl+JSON.parse(selectRows[m]).qcord+'"/>' +'</span>';
				tags += '</label></li>';
			});
			
			$(id).find('.memberNos-list').html(tags);
		}
		
		function cancel(){
			$("#selectAllwx").prop("checked",false);
		}
		
		//个人或公众号名片
		function personCardOther(typeCode){
			var url="";
			if(typeCode == 0){		//个人名片
				$(".personCardContent .top .n").text("个人名片");
				$(".personCardContent .top .certFlag").val(0);
				url = "${ctx}/im/personMemberList";
			}else if(typeCode == 8){	//公众号名片
				$(".personCardContent .top .n").text("公众号名片");
				
				$(".personCardContent .top .certFlag").val(8);
				url= "${ctx}/im/publicaccount/list";
			}
			
			var shopNo=$(".selectEnum option:selected").val();
			var noWx=$('#noWxs #wxNo').val();
			if(noWx == ""){
				alert("请先选择微信号");
			}else{
				var n=$(".personCardContent .pagination select").find("option:selected").text();
				var pageNo= $(".personCardContent .pagination .pageShow").val();
				var name=$(".personCardContent .top .mp input").val();
				$.ajax({
			        type:"POST",
			        url:url,
			        data:{noWxZk:noWx,pageSize:Number(n),shopNo:shopNo,pageNo:pageNo,status:1,paName:name,keyWord:name},
			        dataType:'JSON',
			        success:function(result){
			        	var dataInfo="";
			        	if(typeCode == 0){	//个人名片
			        		dataInfo=result.page;
			        	}else if(typeCode == 8){	//公众号名片
			        		dataInfo=result.data;
			        	}
			        	
			        	if(dataInfo){
			                $(".personCardContent .pagination .pageShow").val(dataInfo.pageNo);
			                $(".personCardContent .condition_all .kehuNum").text(dataInfo.count);
			                $(".personCardContent .pagination .total").text(dataInfo.count);
			                var totalPage=Math.ceil(dataInfo.count/dataInfo.pageSize);
			                $(".personCardContent .pagination .totalPage").text(totalPage);
			                
			                var currPage=$(".personCardContent .pagination .pageShow").val();
			                if(totalPage!=undefined & currPage!=undefined & Number(currPage)<Number(totalPage) && Number(currPage)!=1){
			                	$(".personCardContent .pagination .first").attr("src","${ctxStatic}/admin/images/imImages/arrow_back.png");
			            		$(".personCardContent .pagination .prev").attr("src","${ctxStatic}/admin/images/imImages/arrow_double_b.png");
			            		$(".personCardContent .pagination .next").attr("src","${ctxStatic}/admin/images/imImages/arrow_doublef.png");
			            		$(".personCardContent .pagination .last").attr("src","${ctxStatic}/admin/images/imImages/arrow_font.png");
			            	}else if(Number(currPage)==Number(totalPage)  && totalPage!=1){
			            		$(".personCardContent .pagination .first").attr("src","${ctxStatic}/admin/images/imImages/arrow_back.png");
			            		$(".personCardContent .pagination .prev").attr("src","${ctxStatic}/admin/images/imImages/arrow_double_b.png");
			            		$(".personCardContent .pagination .next").attr("src","${ctxStatic}/admin/images/imImages/arrow_doublef1.png");
			            		$(".personCardContent .pagination .last").attr("src","${ctxStatic}/admin/images/imImages/arrow_font1.png");
			            	}else  if(Number(currPage)==1  && totalPage>1){
			                	$(".personCardContent .pagination .first").attr("src","${ctxStatic}/admin/images/imImages/arrow_back1.png");
			            		$(".personCardContent .pagination .prev").attr("src","${ctxStatic}/admin/images/imImages/arrow_double_b1.png");
			            		$(".personCardContent .pagination .next").attr("src","${ctxStatic}/admin/images/imImages/arrow_doublef.png");
			            		$(".personCardContent .pagination .last").attr("src","${ctxStatic}/admin/images/imImages/arrow_font.png");
			            	}else{
			            		$(".personCardContent .pagination .first").attr("src","${ctxStatic}/admin/images/imImages/arrow_back1.png");
			            		$(".personCardContent .pagination .prev").attr("src","${ctxStatic}/admin/images/imImages/arrow_double_b1.png");
			            		$(".personCardContent .pagination .next").attr("src","${ctxStatic}/admin/images/imImages/arrow_doublef1.png");
			            		$(".personCardContent .pagination .last").attr("src","${ctxStatic}/admin/images/imImages/arrow_font1.png")
			            	}
			        		if(dataInfo.list){
			        			var html='';
			                	if(dataInfo.list.length>0){
			                		for(var i=0;i<dataInfo.list.length;i++){
			                			html +='<li onclick="choseTalk(this)"><span class="n">'+'<img alt="" src="'+dataInfo.list[i].paLogo+'">'+'</span>';
		                    			html +='<div class="d">'+dataInfo.list[i].paName+'</div>';
		                    			if(dataInfo.list[i].paAlias){
		                    				html += '<input type="hidden" class="nwa" value="'+dataInfo.list[i].paAlias+'" />';
		                    			}
		                    			html += '<input type="hidden" class="nwx" value="'+dataInfo.list[i].paUsername+'" />';
		                    			html += '<input type="hidden" class="gzhCode" value="'+dataInfo.list[i].code+'" />';
		                    			html += '</li>';
			                		}
			                	}
			        		    $("#cardContent ul").html(html);
			        		    
			        			$(".personCardBox").css("display","flex");
			        		}else{
			            		alert("没有公众号")
			            	}
			        	}else{
			        		alert("没有公众号")
			        	}
			        	
					    $(".emoticon .allCard").hide();	
			        }
			    });
			}
			
		}
		
		$(".personCardContent .pageShow").change(function(){
			var total=$(".personCardContent .pagination .totalPage").text();
			var page=$(".personCardContent .pageShow").val();
			var reg= /^[0-9]*[1-9][0-9]*$/;
			if(reg.test(page)){
				if(Number(page)<=Number(total) && Number(page)>0){
					$(".personCardContent .pageShow").val(Number(page));
				}else{
					$(".personCardContent .pageShow").val(1);
				}
				 personCardOther($(".certFlag").val());
			}else{
				if(Number(page)>0 && Number(page)<=Number(total)){
					$(".personCardContent .pageShow").val(Math.floor(Number(page)));
				}else{
					$(".personCardContent .pageShow").val(1);
				}
				personCardOther($(".certFlag").val());
			}
		});
		
		$(".personCardContent .pagination select").change(function(){
			var n=$(this).find("option:selected").text();
			var t=Number($(".personCardContent .pagination .total").text());
			var totalPage=Math.ceil(t/Number(n));
	        $(".personCardContent .pagination .totalPage").text(totalPage);
	        $(".personCardContent .pageShow").val(1);
	        personCardOther($(".certFlag").val());
		});
		
		function changeCardPage(info){
			var pageNo=$(".personCardContent .pagination .pageShow").val();
			if(info=="first"){
				pageNo=1;
			}else if(info=="prev"){
				if(pageNo>1){
					pageNo=pageNo-1;
				}
			}else if(info=="next"){
				var last=$(".personCardContent .pagination .totalPage").text();
				if(pageNo<Number(last)){
					pageNo=Number(pageNo)+1;
				}
			}else if(info=="last"){
				pageNo=$(".personCardContent .pagination .totalPage").text();
			}
			$(".personCardContent .pagination .pageShow").val(pageNo);
			personCardOther($(".certFlag").val());
		}
		
		function queryCard(){
			var typeCode = $(".certFlag").val();
			$(".personCardContent .pagination .pageShow").val(1);
			personCardOther(typeCode);
		}
		function choseTalk(event){
			$(event).siblings().removeClass("active");
			$(event).addClass("active");
		}
		function sendSub(){
			var img=$("#cardContent li.active .n img").attr("src");
			var name=$("#cardContent li.active .d").text();
			var gzhCode=$("#cardContent li.active .gzhCode").val();
			var html='<div class="shareInfo"><div class="sendDec">';
			html +='<img alt="" src="'+img+'"><div class="sendDecText" style="display: flex;align-items: center;">';
			html +='<p>'+name+'</p></div></div><p class="sendTitle">公众号名片</p></div>';
			html +='<input type="hidden" value="'+gzhCode+'" name="shareCode">';
			
			$("#gzh .textContent").html(html);
			$(".personCardBox").css("display","none");
		}
		function sendSubxcx(){
			var img=$("#wxSmallContent li.active .n img").attr("src");
			var name=$("#wxSmallContent li.active .d").text();
			var spcode=$("#wxSmallContent li.active .spcode").val();
			var html='<div class="shareInfo"><div class="sendDec">';
			html +='<img alt="" src="'+img+'"><div class="sendDecText" style="display: flex;align-items: center;">';
			html +='<p>'+name+'</p></div></div><p class="sendTitle">小程序名片</p></div>';
			html +='<input type="hidden" value="'+spcode+'" name="shareCode">';
			
			$("#gzh .textContent").html(html);
			$(".wxSmallProgramBox").css("display","none");
		}
		
		/*显示小程序*/
		function showXCX(){
			var url="${ctx}/im/smallprogram/list";
			
			var spName=$(".wxSmallProgramContent .queryXcx .xcxM input").val();
			var n=$(".wxSmallProgramBox .pagination select").find("option:selected").text();
			var pageNo= $(".wxSmallProgramBox .pagination .pageShow").val();
			var type=$(".wxSmallProgramContent .queryXcx .xcxYhu option:selected").val();
			var shopNo=$(".selectEnum option:selected").val();
			var noWx=$('#noWxs #wxNo').val();
			if(noWx == ""){
				alert("请先选择微信号");
			}else{
				$.ajax({
			        type:"POST",
			        url:url,
			        data:{noWxZk:noWx,pageSize:Number(n),shopNo:shopNo,pageNo:pageNo,status:1,type:type,spName:spName},
			        dataType:'JSON',
			        success:function(result){
			        	var xcxType=result.data.smallProgramTypes;
			        	var xcxTypeHtml="<option value=''>全部</option>";
			        	for(var key in xcxType){
			        		xcxTypeHtml +="<option value='"+key+"'>"+xcxType[key]+"</option>";
			        	}
			        	$("#queryType").html(xcxTypeHtml);
			        	var dataInfo=result.data.page;
			        	if(dataInfo){
			                $(".wxSmallProgramContent .pagination .pageShow").val(dataInfo.pageNo);
			                $(".wxSmallProgramContent .condition_all .kehuNum").text(dataInfo.count);
			                $(".wxSmallProgramContent .pagination .total").text(dataInfo.count);
			                var totalPage=Math.ceil(dataInfo.count/dataInfo.pageSize);
			                $(".wxSmallProgramContent .pagination .totalPage").text(totalPage);
			                
			                var currPage=$(".wxSmallProgramContent .pagination .pageShow").val();
			                if(totalPage!=undefined & currPage!=undefined & Number(currPage)<Number(totalPage) && Number(currPage)!=1){
			                	$(".wxSmallProgramContent .pagination .first").attr("src","${ctxStatic}/admin/images/imImages/arrow_back.png");
			            		$(".wxSmallProgramContent .pagination .prev").attr("src","${ctxStatic}/admin/images/imImages/arrow_double_b.png");
			            		$(".wxSmallProgramContent .pagination .next").attr("src","${ctxStatic}/admin/images/imImages/arrow_doublef.png");
			            		$(".wxSmallProgramContent .pagination .last").attr("src","${ctxStatic}/admin/images/imImages/arrow_font.png");
			            	}else if(Number(currPage)==Number(totalPage)  && totalPage!=1){
			            		$(".wxSmallProgramContent .pagination .first").attr("src","${ctxStatic}/admin/images/imImages/arrow_back.png");
			            		$(".wxSmallProgramContent .pagination .prev").attr("src","${ctxStatic}/admin/images/imImages/arrow_double_b.png");
			            		$(".wxSmallProgramContent .pagination .next").attr("src","${ctxStatic}/admin/images/imImages/arrow_doublef1.png");
			            		$(".wxSmallProgramContent .pagination .last").attr("src","${ctxStatic}/admin/images/imImages/arrow_font1.png");
			            	}else  if(Number(currPage)==1  && totalPage>1){
			                	$(".wxSmallProgramContent .pagination .first").attr("src","${ctxStatic}/admin/images/imImages/arrow_back1.png");
			            		$(".wxSmallProgramContent .pagination .prev").attr("src","${ctxStatic}/admin/images/imImages/arrow_double_b1.png");
			            		$(".wxSmallProgramContent .pagination .next").attr("src","${ctxStatic}/admin/images/imImages/arrow_doublef.png");
			            		$(".wxSmallProgramContent .pagination .last").attr("src","${ctxStatic}/admin/images/imImages/arrow_font.png");
			            	}else{
			            		$(".wxSmallProgramContent .pagination .first").attr("src","${ctxStatic}/admin/images/imImages/arrow_back1.png");
			            		$(".wxSmallProgramContent .pagination .prev").attr("src","${ctxStatic}/admin/images/imImages/arrow_double_b1.png");
			            		$(".wxSmallProgramContent .pagination .next").attr("src","${ctxStatic}/admin/images/imImages/arrow_doublef1.png");
			            		$(".wxSmallProgramContent .pagination .last").attr("src","${ctxStatic}/admin/images/imImages/arrow_font1.png")
			            	}
			        		if(dataInfo.list){
			        			var html='';
			                	if(dataInfo.list.length>0){
			                		for(var i=0;i<dataInfo.list.length;i++){
			                			html +='<li onclick="choseTalk(this)"><span class="n">'+'<img alt="" src="'+dataInfo.list[i].spLogo+'">'+'</span>';
			                			html +='<div class="d">'+dataInfo.list[i].spName+'</div>';
			                			html += '<input type="hidden" class="spDesc" value="'+dataInfo.list[i].spDesc.replace(/"/g,"").replace(/'/g,"")+'" />';
			                			html += '<input type="hidden" class="spcode" value="'+dataInfo.list[i].code+'" />';
			                			html += '</li>';
			                			
			                		}
			                	}
			                	 $("#wxSmallContent ul").html(html);
			        		    
			        			$(".wxSmallProgramBox").css("display","flex");
			        		}else{
			            		alert("没有小程序")
			            	}
			        	}else{
			        		alert("没有小程序")
			        	}
			        	
					    $(".emoticon .allCard").hide();	
			        }
			    });
			}
			
		}
		
		$(".wxSmallProgramContent .pageShow").change(function(){
			var total=$(".wxSmallProgramContent .pagination .totalPage").text();
			var page=$(".wxSmallProgramContent .pageShow").val();
			var reg= /^[0-9]*[1-9][0-9]*$/;
			if(reg.test(page)){
				if(Number(page)<=Number(total) && Number(page)>0){
					$(".wxSmallProgramContent .pageShow").val(Number(page));
				}else{
					$(".wxSmallProgramContent .pageShow").val(1);
				}
				showXCX();
			}else{
				if(Number(page)>0 && Number(page)<=Number(total)){
					$(".wxSmallProgramContent .pageShow").val(Math.floor(Number(page)));
				}else{
					$(".wxSmallProgramContent .pageShow").val(1);
				}
				showXCX();
			}
		});
		
		$(".wxSmallProgramContent .pagination select").change(function(){
			var n=$(this).find("option:selected").text();
			var t=Number($(".wxSmallProgramContent .pagination .total").text());
			var totalPage=Math.ceil(t/Number(n));
	        $(".wxSmallProgramContent .pagination .totalPage").text(totalPage);
	        $(".wxSmallProgramContent .pageShow").val(1);
	        showXCX();
		});
		
		function changeSmallProPage(info){
			var pageNo=$(".wxSmallProgramContent .pagination .pageShow").val();
			if(info=="first"){
				pageNo=1;
			}else if(info=="prev"){
				if(pageNo>1){
					pageNo=pageNo-1;
				}
			}else if(info=="next"){
				var last=$(".wxSmallProgramContent .pagination .totalPage").text();
				if(pageNo<Number(last)){
					pageNo=Number(pageNo)+1;
				}
			}else if(info=="last"){
				pageNo=$(".wxSmallProgramContent .pagination .totalPage").text();
			}
			$(".wxSmallProgramContent .pagination .pageShow").val(pageNo);
			showXCX();
		}
		
		function queryXCX(){
			showXCX();
		}
		function checkWX(event){
			$(event).parent().siblings().find("label .boxNowx").prop("checked",false);
		}
	</script>
</body>
</html>