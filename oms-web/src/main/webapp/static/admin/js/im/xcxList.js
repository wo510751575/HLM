var pageFlag=true;
var scrollHeight=true;
$(document).ready(function() {
	var pageNo=$("#pageNo").val();
	var pageSize=$("#pageSize").val();
	var noWx=$(".shop_list .shopDetail.active .noWx").val();
	var shopNo=$(".shop_list .shopDetail.active .shopNo").val();
	if(noWx){
		clock(noWx,pageSize,shopNo,pageNo,1);
	}
	
	$(".shop_nav .storeClient").change(function(){
		storeSearch();
	});
	
	$(".shop_nav #codeListSec").change(function(){
		storeSearch();
	});
	$(".shop_nav select.province").change(function(){
		storeSearch();
	});
	$(".shop_nav select.qu").change(function(){
		storeSearch();
	});
	$(".shop_nav select.shi").change(function(){
		storeSearch();
	});
	
	
	$('.sendF #gzhScroll').on('scroll',function(){
		var dh = $('.sendF #gzhScroll').height(); // document height
		var ch=$(".con_right ul:visible").eq(0).height(); //内容高度
		var sh = $('.sendF #gzhScroll').scrollTop();
		if(ch-dh - sh< 0 && pageFlag && scrollHeight && sh != 0){		
			var tt=$("#pageNo").val()*$("#pageSize").val();
			
			var index = $('#topfS .friendItem li.friendItemActive').index();
//			var count = (index == 0 ? $("#AllCount").val() : $("#count").val());
			var status = "";
			if(index==0){
				status = 1;
			}else if(index==1){
				status = 0;
			}
			var count=$("#count").val();
			scrollHeight=false;
			if(tt<Number(count)){
				var pageNo=Number($("#pageNo").val())+1;
				console.log(pageNo)
				$("#pageNo").val(pageNo);
				var pageSize=$("#pageSize").val();
				var noWx=$(".shop_list .shopDetail.active .noWx").val();
				var shopNo=$(".shop_list .shopDetail.active .shopNo").val();
				var type=$("#topfS .queryXcx .xcxYhu option:selected").val();
				var spName=$("#topfS .queryXcx .xcxM input").val();
				 clock(noWx,pageSize,shopNo,pageNo,status,type,spName)
			}else{
				pageFlag = false;
			}
			
		}
	});
});
function queryInfo(event){
	if(event){
		$(event).addClass("active");
		$(event).siblings().removeClass("active");
		$("#pageNo").val(1);
		$('.sendF #gzhScroll').scrollTop(0);
	}
	var pageNo=$("#pageNo").val();
	var pageSize=$("#pageSize").val();
	var noWx=$(".shop_list .shopDetail.active .noWx").val();
	var shopNo=$(".shop_list .shopDetail.active .shopNo").val();
	if(noWx){
		$($("#topfS .friendItem li")[0]).addClass("friendItemActive").siblings().removeClass("friendItemActive");
		clock(noWx,pageSize,shopNo,pageNo,1);
	}
}
function clock(noWx,pageSize,shopNo,pageNo,status,type,spName)
{
	pageFlag=true;
	$.ajax({
        type:"POST",
        url:$('#ctx').val() + "/im/smallprogram/list",
        data:{noWxZk:noWx,pageSize:Number(pageSize),shopNo:shopNo,pageNo:pageNo,status:status,type:type,spName:spName},
        dataType:'JSON',
        success:function(result){
        	$("#count").val(result.data.page.count);
        	var dataInfo=result.data.page;
        	var xcxType=result.data.smallProgramTypes;
        	var xcxTypeHtml="<option value=''>全部</option>";
        	for(var key in xcxType){
        		xcxTypeHtml +="<option value='"+key+"'>"+xcxType[key]+"</option>";
        	}
        	$("#xcxType").html(xcxTypeHtml);
        	if(type!=undefined){	//选中type
        		var choseType="<option value=''>全部</option>";
        		for(var key in xcxType){
        			if(type == key){
        				choseType +="<option selected='selected' value='"+key+"'>"+xcxType[key]+"</option>";
        			}else{
        				choseType +="<option value='"+key+"'>"+xcxType[key]+"</option>";
        			}
            	}
        		$("#queryType").html(choseType);
        	}else{
        		$("#queryType").html(xcxTypeHtml);
        	}
        	
        	if(dataInfo){
        		if(dataInfo.list){
        			var html='';
                	if(dataInfo.list.length>0){
                		for(var i=0;i<dataInfo.list.length;i++){
                			html +='<li onclick="choseTalk(this)"><span class="n">'+'<img alt="" src="'+getHeadAddress(dataInfo.list[i].spLogo)+'">'+'</span>';
                			html +='<div class="d">'+dataInfo.list[i].spName+'</div>';
                			if(dataInfo.list[i].paAlias){
                				html += '<input type="hidden" class="nwa" value="'+dataInfo.list[i].paAlias+'" />';
                			}
                			html += '<input type="hidden" class="nwx" value="'+dataInfo.list[i].paUsername+'" />';
                			html += '<input type="hidden" class="gzhCode" value="'+dataInfo.list[i].code+'" />';
                			if(dataInfo.list[i].status == 1){	//有效
                				html += '<div class="s"><span  onclick="edit(this,\''+dataInfo.list[i].code+'\',0)">设置失效</span>&nbsp;&nbsp;<span onclick="showEditBox(\''+dataInfo.list[i].code+'\',\''+dataInfo.list[i].status+'\',\''+dataInfo.list[i].spUrl+'\',\''+dataInfo.list[i].spDesc.replace(/"/g,"").replace(/'/g,"")+'\',\''+dataInfo.list[i].type+'\')">编辑</span></div>';
                			}else if(dataInfo.list[i].status == 0){	//无效
                				html += '<div class="y"><span  onclick="edit(this,\''+dataInfo.list[i].code+'\',1)">设置有效</span>&nbsp;&nbsp;<span onclick="showEditBox(\''+dataInfo.list[i].code+'\',\''+dataInfo.list[i].status+'\',\''+dataInfo.list[i].spUrl+'\',\''+dataInfo.list[i].spDesc.replace(/"/g,"").replace(/'/g,"")+'\',\''+dataInfo.list[i].type+'\')">编辑</span></div>';
                			}
                			
                			html += '</li>';
                			
                		}
                		scrollHeight=true;
                	}
                	if(pageNo>1){
                		$("#gzhScroll ul").append(html);
                	}else{
                		$("#gzhScroll ul").html(html);
                	}
        		   
        		    
        		}else{
        			$("#gzhScroll ul").html("");
        		}
        	}
        }
    });
}	

	
function chosePublicAcc(status,event){
	$(event).addClass("friendItemActive").siblings().removeClass("friendItemActive");
	var pageSize=$("#pageSize").val();
	var noWx=$(".shop_list .shopDetail.active .noWx").val();
	var shopNo=$(".shop_list .shopDetail.active .shopNo").val();
	 clock(noWx,pageSize,shopNo,1,status);
}

function edit(event,code,status)
{
	$.ajax({
        type:"POST",
        url:$('#ctx').val() + "/im/smallprogram/edit",
        data:{code:code,status:status},
        dataType:'JSON',
        success:function(result){
        	if(result){
        		var msg ="设置成功。";
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
     				msg:msg // 提示文字
     			};
         		 ConfirmBox(options);
        		$(event).parent().parent().remove();
        	}
        }
    });
}

function storeSearch(){
	var searchWords=$(".shop_nav .searchTh").val();
	var pmTypeCode=$(".shop_nav .storeClient ").find("option:selected").val();
	window.location.href=$('#ctx').val()+"/im/smallprogram/xcxList?searchWords="+searchWords+"&pmTypeCode="+pmTypeCode;
}

/*展示编辑框*/
function showEditBox(code,status,spUrl,spDesc,type){
	$(".xcxEdit #imgListId").html("<img src='"+spUrl+"' />")
	if(status==1){
		$(".xcxEdit .valid").attr("checked","true");
	}else{
		$(".xcxEdit .unvalid").attr("checked","true");
	}
	$("#xcxType option").each(function(){
		if($(this).val()==type){
			$(this).attr("selected","selected");
		}
	});	
	$("#formFriend .xcxCode").val(code);
	$(".xcxEdit textarea").val(spDesc);
	$("#xcxDetail").css("display","flex");
}


/*
* 多图片上传
* {
*   fileId:'', 上传按钮的id
*   imgId:'', 图片列表id
*   max:'5', 最大上传张数
*   isCompress:true 是否压缩
* }
*/
function ajaxFileUploadnew(data) {
	var data = data || {};
	var files = document.getElementById(data.fileId).files;
	var imgCount = $(data.imgId).find('img').length;
	if(imgCount + files.length > data.max){ // 超过最大张数
		$.ConfirmBox({
			msg:'最多不能超过'+data.max+'张图',
			isCancel:false,
		});
		$('#'+data.fileId).val('');
		return;
	}

	var flag = true;

	for(var i = 0, len = files.length; i < len; i++){ // 图片类型判断
		var reg = /(jpg|jpeg|bmp|png)/ig;
		var type = files[i].type;
		if(!reg.test(type)){
			flag = false;
			$.ConfirmBox({
				msg:files[i].name + '格式不正确，只能上传jpg,jpeg,bmp,png的图片',
				isCancel:false,
			});
			$('#'+data.fileId).val('');
			break;
		}
	}
	if(!flag) return;
	
	$.ajaxFileUpload({
		url: $('#ctx').val() + '/cm/friendsimagemateria/uploadImg', //用于文件上传的服务器端请求地址
		secureuri: false, //是否需要安全协议，一般设置为false
		fileElementId: data.fileId, //文件上传域的ID
		dataType: 'json', //返回值类型 一般设置为json
		type:'post',
		data:{
			fileType: 'image'
		},
		success:function(res, status) {//服务器成功响应处理函数
			if(res.success){
				var list = res.imgAddr.split(',');
				var tags = '';
				var url = $('#UploadUrl').val() + 'im/' +list[0];
				tags += '<img src="'+ url +'" class="i"><span class="del" onclick="$(this).parent(\'li\').remove()"></span>';;
				$(data.imgId).html(tags);
				$('#'+data.fileId).val('');
				$("#imgAddr").val(url);
			}
		}
	})
}


function saveFriendMateria(id){
	var spUrl=$("#imgAddr").val();
	var type=$("#xcxType option:selected").val();
	var status=$("input:radio[name='status']:checked").val();
	var spDesc=$(".xcxEdit textarea").val();
	var code=$("#formFriend .xcxCode").val();
	$.ajax({
        type:"POST",
        url:$('#ctx').val() + '/im/smallprogram/edit',
        data:{code:code,status:status,spUrl:spUrl,type:type,spDesc:spDesc},
        dataType:'JSON',
        success:function(result){
        	if(result){
        		var msg ="编辑成功。";
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
     				msg:msg // 提示文字
     			};
         		 ConfirmBox(options);
         		$('#xcxDetail').hide();
         		
         		var spName=$("#topfS .queryXcx .xcxM input").val();
         		var type=$("#topfS .queryXcx .xcxYhu option:selected").val();
         		var pageSize=$("#pageSize").val();
         		var noWx=$(".shop_list .shopDetail.active .noWx").val();
         		var shopNo=$(".shop_list .shopDetail.active .shopNo").val();
         		var status=$('#topfS .friendItem li.friendItemActive').index();
         		if(status == 0){
         			status =1;
         		}else if(status == 1){
         			status =0;
         		}
         		clock(noWx,pageSize,shopNo,1,status,type,spName);
//         		if((status == 0 && $(".friendItem .friendItemActive").attr("data-info")=='1')||(status == 1 && $(".friendItem .friendItemActive").attr("data-info")=='0')){
//         			$("#gzhScroll li.active").remove();
//         		}
        	}
        }
    });
}

/*搜索小程序*/
function queryXCX(){
	var spName=$("#topfS .queryXcx .xcxM input").val();
	var type=$("#topfS .queryXcx .xcxYhu option:selected").val();
	
	var pageSize=$("#pageSize").val();
	var noWx=$(".shop_list .shopDetail.active .noWx").val();
	var shopNo=$(".shop_list .shopDetail.active .shopNo").val();
	clock(noWx,pageSize,shopNo,1,1,type,spName);
}