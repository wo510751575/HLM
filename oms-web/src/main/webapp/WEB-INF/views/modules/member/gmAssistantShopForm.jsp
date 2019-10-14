<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${not empty data.code?'终端助手管理':'终端助手添加'}</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
	<link href="${ctxStatic}/jquery-select2/3.4/select2.min.css" rel="stylesheet" />
	<script src="${ctxStatic}/jquery-select2/3.4/select2.min.js" type="text/javascript"></script>
    <script type="text/javascript">
    
    $(document).ready(function() {
    	$('.form-group-select-css').select2();
    	bindEvent();
    });


    function bindEvent() {
    	 //管理员
    	$("[name='assistantNo']").change(function(){
        	var select = $(this).find("option:selected");
    		$("#assistantName").val(select.text());
    		$("#loginName").val(select.attr("alt-loginName"));
        });
        $("[name='assistantNo']").change();
    	
    	//提交
    	$('#btnSubmit').click(function(){
        	var flag = true;
    		var sellist = $('select.form-group-select-css');
    		for(var i=0; i<sellist.length; i++) {
				if(sellist[i].value=='') {
					flag = false;
					break;
				}
        	}

    		//需要校验必填
    		if('${data.tidCode}'=='') {
				var tidCodes = $('#tidCodes').val();
				if(tidCodes=='') {
					flag =false;
				}
        	}
        	
        	if(flag){
	    		loading('正在提交，请稍等...');
				$('#inputForm').submit();
        	} else {
        		top.$.jBox.info('请选择');
        	}
        });


    	//选择终端
		$('#btnTerminal').click(function() {
			var select = $("[name='assistantNo']").find("option:selected");
			var assistantNo = select.val();
			var loginName = select.attr("alt-loginName");
			var url = "iframe:${ctx}/member/gmAssistantShop/toBindShopTerminal?assistantNo="+assistantNo;
			console.log(url);
			// 正常打开	
			top.$.jBox.open(url, "选择终端", 880, 560,{//宽高
				id:9527,
				draggable: true,
				showClose: true,
				buttons:{"确定":"ok", "关闭":true}, 
				submit:function(v, h, f){//v表示所点的按钮的返回值，h表示窗口内容的jQuery对象，f表示窗口内容里的form表单键值
	                if (v=="ok"){
	                	var win = h.find('iframe')[0].contentWindow;
	                	var checkBoxs= win.shopChannelCbox;
	                	
	                	var info="";//tidCode//微信号//微信昵称//终端编码
	                	var checkedTid="";	//已选终端
	                	$(checkBoxs).each(function(i){
	                		if(this.checked){
	                			info+= this.value+",";
// 	                			info+= this.dataset.wx+"|";
// 	                			info+= this.dataset.name+"|";
// 	                			info+= this.dataset.tcode+",";
	                			checkedTid += this.dataset.wx+"-"+this.dataset.name+",";
	                		}
	               		 });
						console.log(info);
						if(info) {
							info = info.substring(0, info.lastIndexOf(','));
						}
						if(checkedTid) {
							checkedTid = checkedTid.substring(0, checkedTid.lastIndexOf(','));
						}
	                	$('#tidCodes').val(info);
						
						var html = '<span style="color:#23b7e5">'+checkedTid+'</span><br/>';
						$('.spanSelTipCods').html(html);
	                } 
	            }, 
				iframeScrolling: 'no',
				loaded:function(h){
					top.$('.jbox-container .jbox-title-panel').css("height","35px").css('background','#376EA5');
					top.$('.jbox-container .jbox-title').css('line-height','35px').css("color","#ffffff");
				},
				closed: function () { 
					//window.location.reload();
				} // 信息关闭后执行的函数
			});
	    });
    }
    function tabChange(id,ths){
        $(".tab_div").hide();
        $(".nav-child li").removeClass("active");
        $(id).show();
        $(ths).addClass("active");
    }

   
    
    </script>
    <style type="text/css">
    .nav-child li a{
          line-height: 10px;
    }
    .nav-child li.active a{
          border: 1px dotted #ddd;
          border-bottom-color: transparent;
    }
    .photo-file{
	 	position: absolute;
	    top: 350px;
	    left: 190px;
	    opacity: 0;
	    filter: alpha(opacity:0);
	    cursor:pointer;
	}
	.container{
	padding: 10px 30px;
    width: 100%;
    min-height: 800px;
    background: #fff;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
	}
	.select2-container-multi .select2-choices{
	    width: 281px;
	}


   </style>
</head>
<body>
<div class="container">
    <ul class="nav nav-tabs">
        <li><a href="${ctx}/member/gmAssistantShop">终端助手列表</a></li>
	    <li class="active"><a href="${ctx}/member/gmAssistantShop/form?code=${data.code}">${not empty data.code?'终端助手修改':'终端助手添加'}</a></li> 
    </ul>
    
     <tags:message content="${message}"/>
     <form id="inputForm" action="${ctx}/member/gmAssistantShop/${not empty data.code?'edit':'save'}" method="post" enctype="multipart/form-data" class="form-horizontal">
        <input id="code" name="code" type="hidden" value="${data.code}"/>
         <input id="assistantName" name="assistantName" type="hidden" />
         <input id="loginName" name="loginName" type="hidden" />
        <div id="base_div" class="tab_div">    
        
             <div class="control-group">
                <label class="control-label">选择人员:</label>
                <div class="controls">
	                 <div class="form-group form-group-select2">
						<select name="assistantNo" id="codeListSec" class="required form-group-select-css"  style="width:220px">
				 			<c:forEach items="${users}" var="item">
				 				<option class="MaterialInfo" value="${item.id}"<c:if test="${item.id eq data.assistantNo}">selected="selected"</c:if> alt-loginName="${item.loginName}">${item.name}</option>
				 			</c:forEach>
						</select>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
                </div>
            </div>
                 
           	<!-- 新增加的终端 -->
			<div class="control-group">
                <label class="control-label">选择终端:</label>
                <div class="controls">
	                 <input type="hidden" id="tidCodes" name="tidCodes"  maxlength="127" required="required" class="required input-xxlarge"/>
	                 <span class="spanSelTipCods"></span>
	                 <span style="padding-left:5px;"><input id="btnTerminal" class="btn btn-primary" type="button" value="选择" /></span>
                     <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
        </div>
        <div class="form-actions">
            <input id="btnSubmit" class="btn btn-primary" type="button" value="保 存"/>&nbsp;
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
        </div>
    </form>
    </div>
    
</body>
</html>