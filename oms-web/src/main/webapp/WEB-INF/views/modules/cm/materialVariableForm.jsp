<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${not empty data.code?'素材变量修改':'素材变量添加'}</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/editor/init.js"></script>
	<script type="text/javascript"src="${ctx}/static/common/js/jquery.lSelect.js"></script>
    <script type="text/javascript">
    $(document).ready(function() {
    	
        $("#inputForm").validate({
            submitHandler: function(form){
                $("#btnSubmit").attr("disabled","disabled");form.submit()
                return false;
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
        
    });
    
    

    ;(function($){ // 弹出框
    	var defaultstt = {
    		confirm:function(){}, // 确定调用方法
    		cancel:function(){}, // 取消调用方法
    		confirmBtn:{ // 确定按钮文本和背景
    			text:'确定',
    			background:'#1E90FF',
    			color:'#2a2a2a'
    		},
    		cancelBtn:{ // 取消按钮文本和背景
    			text:'取消',
    			background:'#b3b3b3',
    			color:'#fff'
    		},
    		isCancel:true, // 是否显示取消按钮
    		msg:'' // 提示文字
    	};
    	var ConfirmRe = function(options){
    		this.init(options);
    	};
    	ConfirmRe.prototype = {
    		init:function(options){
    			var self = this;
    			$('body').append(this.createTags(options));

    			$('.promitConfirmButton').on('click',function(ev){
    				$(this).parents('.promitMsgLayerList').remove();
    				options.confirm();
    			});
    			if(options.isCancel) { // 是否有取消按钮
    				$('.promitCancleButton').on('click',function(ev){
    					$(this).parents('.promitMsgLayerList').remove();
    					options.cancel();
    				});
    			}
    		},
    		createTags:function(options){
    			var tags='<div class="text-center promitMsgLayerList" style="position: absolute;z-index: 9999;background: rgba(0, 0, 0, 0.5);width: 100%;height: 100%;top:0;left: 0;">';
    			tags +=	'<div style="width: 426px;height: 200px;background: #fff;border-radius: 5px;    margin: 100px auto;">';
    			tags +=	'<div style="width: 100%;height: 130px;font-size: 16px;line-height: 25px;display: flex;align-items: center;" class="text-center">'+options.msg+'</div>';
    			tags +=	'<div style="width: 80%;height: 70px;display: flex;padding: 0 10%;justify-content:space-around;align-items:center;">';
    			tags +=	'<span class="text-center promitConfirmButton" style="line-height: 40px;width: 130px;height: 40px;border: 1px solid #d1d1d1;border-radius: 5px;color:'+ options.confirmBtn.color +';background:#1E90FF">'+options.confirmBtn.text +'</span>';
    			if(options.isCancel){ // 是否有取消按钮
    				tags +='<span class="text-center promitCancleButton" style="line-height: 40px;width: 130px;height: 40px;border: 1px solid #d1d1d1;border-radius: 5px;color: '+ options.cancelBtn.color +';background: '+ options.cancelBtn.background +'">'+ options.cancelBtn.text +'</span>';
    			}
    			tags +=	'</div></div></div>';
    			return tags;
    		}
    	};

    	ConfirmBox = function(options){
    		var options = $.extend({},defaultstt,options);
    		new ConfirmRe(options);
    	};
    })(jQuery);
    
    function upperCases(code){
		   var varName=$("#varName").val();
		   $.ajax({
			   type:"POST",
		        url:"${ctx}/cm/materialVariable/editVarName",
		        data:{varName:varName},
		        success:function(result){
		        	if(result=='false'){
		        		//showTip("请使用'$'{}包含变量名称,你输入的格式不正确!请重新输入变量名称！");
		        		//alert(("请使用'$'{}包含变量名称,你输入的格式不正确!请重新输入变量名称！"));
		        		
		        		var vrMsg = "请使用'$'{xxx}包含变量名称,你输入的格式不正确!请重新输入变量名称！";
		        		 options = {
		    				confirm:function(){
		    				}, // 确定调用方法
		    				cancel:function(){}, // 取消调用方法
		    				confirmBtn:{ // 确定按钮文本和背景
		    					text:'确定',
		    					background:'#00b204',
		    					color:'#fff'
		    				},
		    				cancelBtn:{ // 取消按钮文本和背景
		    					text:'取消',
		    					background:'#fff',
		    				},
		    				isCancel:false, // 是否显示取消按钮
		    				msg:vrMsg // 提示文字
		    			};
		    	 ConfirmBox(options);
		        	}
		        }
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

   </style>
</head>
<body>
<div class="container">
    <ul class="nav nav-tabs">
        <li><a href="${ctx}/cm/materialVariable/">素材变量列表</a></li>
	    <li class="active"><a href="${ctx}/cm/materialVariable/form?code=${data.code}">${not empty data.code?'素材变量修改':'素材变量添加'}</a></li> 
    </ul>
     <tags:message content="${message}"/>
     <form id="inputForm" action="${ctx}/cm/materialVariable/${not empty data.code?'edit':'save'}" method="post" enctype="multipart/form-data" class="form-horizontal">
        <input id="code" name="code" type="hidden" value="${data.code}"/>
        <div id="base_div" class="tab_div">                   
            <div class="control-group">
                <label class="control-label">变量名称:</label>
                <div class="controls">
                    <input type="text" id="varName" name="varName" onblur="upperCases('${data.varName}')" <c:if test="${data.sysFlag eq 1}">readonly="readonly"</c:if> maxlength="30" class="required input-xlarge" placeholder="\${xxxx}包围的变量名" value="${data.varName}"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            
            <div class="control-group">
                <label class="control-label">变量内容:</label>
                <div class="controls">
                 <textarea class="editor input-xxlarge required" rows="5" maxlength="3000" <c:if test="${data.sysFlag eq 1}">readonly="readonly"</c:if> placeholder="使用 $ 隔开每个变量内容" name="varContent" >${data.varContent }</textarea>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            
            
        </div>
        <div class="form-actions">
            <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
        </div>
    </form>
    </div>
</body>
</html>