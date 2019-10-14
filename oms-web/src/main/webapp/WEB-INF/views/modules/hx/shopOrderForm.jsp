<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${not empty data.code?'补充资料':'补充资料'}</title>
    <script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
    <script src="${ctxStatic}/common/plupload.full.min.js" type="text/javascript"></script>
	<link href="${ctxStatic}/common/select2.css" type="text/css" rel="stylesheet" />
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
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
	img {
	    border: 0 none;
	    height: 80px;
	    max-width: 100%;
	    vertical-align: middle;
	}
	
	#image_btn img{
	float: left;
	}
	.close-Icon{
	position: absolute;
    background: url(/oms-web/static/images/closeImg.png) no-repeat;
    z-index: 100;
    width: 20px;
    height: 20px;
    background-size: 100%;
    right: -7px;
    top: -7px;
       cursor: pointer;
	}
	.img_info{
	position:relative;
	width: 120px;
	height: 120px;
	float: left;
	margin: 10px;
	
	}
    </style>
</head>
<body>
<div class="container">
    <ul class="nav nav-tabs">
        <li ><a href="${ctx}/hx/shop/order/list">服务审核</a></li>
	    <li class="active"><a href="${ctx}/hx/shop/order/form?code=${data.code}">补充资料</a></li> 
    </ul><br/>
    <form id="inputForm" action="${ctx}/hx/shop/order/edit"  method="post" class="form-horizontal">
        
        <input id="code" name="code" type="hidden" value="${data.code}"/>
        
           <div class="control-group">
               <label class="control-label"><span class="help-inline"><font color="red"> *</font></span>门诊名称：</label>
                <div class="controls">
                	 <input style="width: 500px;" type="text"  value="${data.merchantName }" readonly="readonly" disabled="disabled">
                </div>
            </div>
            
            <div class="control-group">
               <label class="control-label"><span class="help-inline"><font color="red"> *</font></span>支付方式：</label>
                <div class="controls">
               		 <c:forEach items="${payTypes}" varStatus="st" var="ele">
               		 	<input id="pt_${st.index}" type="radio" name="payType" value="${ele}" <c:if test="${ele eq data.payType}"> checked="checked" </c:if> >
						<label for="pt_${st.index}">${ele.name }</label>
					</c:forEach>
                     
                </div>
            </div>
            
         	<div class="control-group">
               <label class="control-label">支付流水:</label>
                <div class="controls">
                	 <input name="serialNum" type="text" style="width: 500px;" value="${data.serialNum }" />
                </div>
            </div>
        	
        	<div class="control-group">
               <label class="control-label"><span class="help-inline"><font color="red"> *</font></span>购买服务:</label>
                <div class="controls">
	                <select class="required"  name="serveCode" style="width: 500px;">
		               	<c:set var="serverCodeIsSet" value="false"/>
		                <c:forEach items="${serverinfos}" var="item" varStatus="number"> 
		                	<option value="${item.code}" <c:if test="${item.code eq data.serveCode}">selected="selected" <c:set var="serverCodeIsSet" value="true" /> </c:if>>${item.serverName}</option>
		                </c:forEach>
		               <c:if test="${not serverCodeIsSet}">
		                <option selected="selected" value="${data.code }">${data.serveName }</option>
		               </c:if>
	                </select>
	                <input id="serveNameId" name="serveName" type="hidden" value="${data.serveName }" />
                </div>
            </div>
            
            
        	<div class="control-group">
               <label class="control-label">支付金额:</label>
                <div class="controls">
                	 <input name="amountStr"  type="text"  class="input  number"  style="width: 500px;" value="${data.amount/100 }" />
                </div>
            </div>
        	
        	<div class="control-group">
               <label class="control-label">付款时间:</label>
                <div class="controls">
                	<input id="payTime" name="payTime" type="text" readonly="readonly" maxlength="40" class="input-xlarge Wdate required valid" value="<fmt:formatDate value="${data.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});">
                </div>
            </div>
        
        
          <div class="control-group">
                <label class="control-label"><span class="help-inline"><font color="red">*</font></span>上传凭证:</label>
              <div class="controls"   style="display: flex; align-items: center;">
            		<div id="image_btn" style="border: 1px solid #ddd;width:50%;line-height:100px;text-align:center;overflow: auto;">
                	<c:choose>
                		<c:when test="${data.payCert==null ||  data.payCert==''}">
                			选择图片
                		</c:when>
                		<c:when test="${data.payCert!=null &&  data.payCert!=''}">
                			<c:forEach items="${data.payCert.split(',') }" var="imgaddr">
	                			<div class="img_info">
	                			<span class="close-Icon"></span>
	                				<img src="${fns:getUploadUrl()}${imgaddr}"  height="120px" width="120px">
	                			</div>
                			</c:forEach>
                		</c:when>
                	</c:choose>
		    		</div>
                     <input id="input_image" type="hidden" name="payCert" value="${data.payCert}">
                </div>
            </div>
            <div class="control-group">
               <label class="control-label"><span class="help-inline"><font color="red"> *</font></span>审核备注:</label>
                <div class="controls">
                	 <textarea rows="4" name="remark" maxlength="200" style="width: 500px;">${data.remark}</textarea>
                </div>
                <!-- 审核状态 -->
                <input id="checkStatus" name="status" type="hidden"/>
            </div>
        <div class="form-actions">
            <input id="btnSubmitPass" class="btn btn-primary" type="submit" value="保存并审核通过"/>&nbsp;
            <input id="btnSubmitUnpass" class="btn" type="submit" value="保存并拒绝"/>&nbsp;
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
        </div>
    </form>
    </div>
    
<script>
        
    var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
		browse_button : 'image_btn',
		url : '${ctx}/file/upload?dirName=shoporder',
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
			fileType: 'image'/* ,
			width:1080,
			height:1540 */
		}
	});
	uploader.init(); //初始化
	uploader.bind('FilesAdded',function(uploader,files){
		if(files.length>0){
			uploader.start();
		}
	});
	uploader.bind('Error',function(uploader,errObject){
		if(errObject.code!=-602){
			showTip(errObject.message,"info");
		}
	});
	uploader.bind('FileUploaded',function(uploader,file,responseObject){
			var response = $.parseJSON(responseObject.response);
			var child=$("#image_btn").children();
			var html="";
			if(child.length>0){
				html=$("#image_btn").html();
			}
			html=html+'<div class="img_info"><span class="close-Icon" ></span><img width="120px" height="120px" src="'+uploadUrl+'/oms'+response.url+'"/></div>';
			$("#image_btn").html(html);
			
			$(".close-Icon").on("click",function(e){
				imgClose(this,e);
	        });
	}); 
	
	 $(document).ready(function() {
	        $("#inputForm").validate({
	            submitHandler: function(form){
	            	var show=$("#image_btn").children();
	            	if(!show.length){
	            		alertx("请选择图片!");
	            		return false;
	            	}
	            	if(show.length>5){
	            		alertx("最多5张图片!");
	            		return false;
	            	}
	            	
					var imgInfo="";
					var imgJi=$("#image_btn img");
					for(var i=0;i<imgJi.length;i++){
						if(i==imgJi.length-1){
							imgInfo=imgInfo+imgJi[i].src.split(uploadUrl)[1];
						}else{
							imgInfo=imgInfo+imgJi[i].src.split(uploadUrl)[1]+",";
						}
					}
					$("#input_image").val(imgInfo);
					
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
	         
	        
	        $(".close-Icon").on("click",function(e){
	        	imgClose(this,e);
	        });
	        
	         $("[name='serveCode']").change(function(){
    				$("#serveNameId").val($(this).find("option:selected").text());
        	 }); 
	         
	         $("#btnSubmitPass").on("click",function(e){
	        		$("#checkStatus").val("PASS");
	        	});
        	$("#btnSubmitUnpass").on("click",function(e){
        		$("#checkStatus").val("UNPASS");
        	});
	    });
	 
		function imgClose(event,e){
			$(event).parent().remove();
        	var show=$("#image_btn").children();
        	if(show.length==0){
        		$("#image_btn").html("选择图片");
        	}
        	e.stopPropagation(); 
        	return false;
		}	 
    </script>
    
</body>
</html>