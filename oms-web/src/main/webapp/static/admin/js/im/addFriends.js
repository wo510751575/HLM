$(document).ready(function() {
});
var MECHANTLIST = []; // 未选的所有数据
var num=0;	//搜索次数

//搜索好友
function searchF(count,form){
	formValid(form);
	if(!$(form).valid()) return;
	var noWxGm=$("#noWxs").val();
	var seachInfo=$("#seachInfo").val();
	if(count==''){
		count=0;
	}
	$("#loadFlag").css("display","flex");
	$.ajax({
        type:"POST",
        url:$('#ctx').val()+"/im/doSearchFriend",
        data:{noWxGm:noWxGm,qrCode:seachInfo,count:count},
        dataType:'text',
        success:function(result){
        	var res=JSON.parse(result);
        	if(res.success && num<4){
        		if(res.data!="" && res.data!= undefined){
        			var html='';
        			html +='<div class="search_result">';
        			if(res.data.headAddress!=undefined && res.data.headAddress!='' && res.data.headAddress!=null){
        				html +='<img alt="" src="'+getHeadAddress(res.data.headAddress)+'">';
        			}else{
        				html +='<img alt="" src="'+$('#UploadUrl').val() + $('#ctxStatic').val() + '/admin/images/introduce/file.png'+'">';
        			}
        			if(!res.data.nickNameWx){	
    					html +='<span class="wxName">'+res.data.memberName+'</span></div>';
    				}else{
    					html +='<span class="wxName">'+res.data.nickNameWx+'</span></div>';
    				}
        			if($(".frdContent .button-list").length>0){
        				$(".frdContent .button-list").remove();
        			}
        			if(res.msg!="" && res.msg!=undefined){
        				html +='<p class="hint">'+res.msg+'</p>';
        			}else{
        				$("#formFriendSearch").data('tempdata',JSON.stringify(res.data));
        				var btHtml='<div class="button-list" style="margin-top:50px;">';
            			btHtml +='<input type="button" value="添加好友" class="b-btn" onclick=\'doApplayFriend()\'></div>';
            			$(".frdContent .result").after(btHtml);
        			}
        			$(".frdContent .result .reInfo").html(html);
        			$("#loadFlag").hide();
        			num=0;
        		}else{
        			if(res.msg!="" && res.msg!=undefined){
        				num=0; 
                		$(".frdContent .result .search_result").remove();
                		if($(".frdContent .button-list").length>0){
            				$(".frdContent .button-list").remove();
            			}
                		if($(".frdContent .result .reInfo").html().length>0){
            				$(".frdContent .result .reInfo").html("");
            			}
                		$("#loadFlag").hide();
                		$.ConfirmBox({
                			msg:res.msg,
                			isCancel:false
                		});
        			}else{
        				num=num+1;
        				setTimeout("searchF(" + num + ",'#formFriendSearch')",3000);
        			}
        		}
        		
        	}else{
        		num=0; 
        		$(".frdContent .result .search_result").remove();
        		if($(".frdContent .button-list").length>0){
    				$(".frdContent .button-list").remove();
    			}
        		var  html='<span>该用户不存在</span>';
        		$(".frdContent .result .reInfo").html(html);
        		$("#loadFlag").hide();
        	}
        },
        error:function(){
        	$("#loadFlag").hide();
        	$.ConfirmBox({
     			msg:'系统异常',
     			isCancel:false,
     		})
        	
        	 
        }
    });
}

$('#mechantList').on('click','tbody tr',function(){ // 改变选择存值 
	$('#mechantList tbody tr').removeClass('current');
	$(this).addClass('current');
	$('#mechantList').attr({'data-title':$(this).attr('data-title'),'data-code':$(this).attr('data-code')});
});

/*
 * 选中的终端
 */
function saveFriendMateria(id){
	var dataCode = $(id).find("tr.current").attr('data-nowx');
	if(!dataCode){
		$.ConfirmBox({
			msg:'请选择终端',
			isCancel:false
		});
	}else{
		$(".frdContent .mcWxNo").val(dataCode);
		$(id).hide();
	}
}


function formValid(form){ // 发送素材验证
	$(form).validate({
		rules:{
			alias:{
				required:true
			},
			seachInfo:{
				required:true,
			}
		},
		messages:{
			alias:{
				required:'微信号不能为空'
			},
			seachInfo:{
				required:'搜索信息不能为空',
			}
			
		},
		errorPlacement: function(error, element) {  
		    error.appendTo(element.parents('.tag'));  
		}
	});
}

function doApplayFriend(data){
	var data=$("#formFriendSearch").data('tempdata');
	var d=JSON.parse(data);
	d["noWxGm"]=$("#noWxs").val();
	d["validation"]=$("#validation").val();
	d["nickRemark"]=$("#nickRemark").val();
	d["mobile"]=$("#mobile").val();
	d["labelName"]=$("#labelName").val();
	
	$.ajax({
        type:"POST",
        url:$('#ctx').val()+"/im/doApplayFriend",
        data:d,
        dataType:'JSON',
        success:function(result){
        	console.log(result);
        	if(result.success){
        		$.ConfirmBox({
        			msg:'已发送好友请求',
        			isCancel:false
        		});
        	}else{
        		var msg=result.msg;
        		if(msg.indexOf("-->")>0){
        			msg=msg.split("-->")[1];
        		}
        		$.ConfirmBox({
        			msg:msg,
        			isCancel:false
        		});
        	}
        }
    });
}