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
				 clock(noWx,pageSize,shopNo,pageNo,status)
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
		clock(noWx,pageSize,shopNo,pageNo,1);
	}
}
function clock(noWx,pageSize,shopNo,pageNo,status)
{
	pageFlag=true;
	$.ajax({
        type:"POST",
        url:$('#ctx').val() + "/im/publicaccount/list",
        data:{noWxZk:noWx,pageSize:Number(pageSize),shopNo:shopNo,pageNo:pageNo,status:status},
        dataType:'JSON',
        success:function(result){
        	var dataInfo=result.data;
        	$("#count").val(result.data.count);
        	if(dataInfo){
        		if(dataInfo.list){
        			var html='';
                	if(dataInfo.list.length>0){
                		for(var i=0;i<dataInfo.list.length;i++){
                			html +='<li onclick="choseTalk(this)"><span class="n">'+'<img alt="" src="'+getHeadAddress(dataInfo.list[i].paLogo)+'">'+'</span>';
                			html +='<div class="d">'+dataInfo.list[i].paName+'</div>';
                			if(dataInfo.list[i].paAlias){
                				html += '<input type="hidden" class="nwa" value="'+dataInfo.list[i].paAlias+'" />';
                			}
                			html += '<input type="hidden" class="nwx" value="'+dataInfo.list[i].paUsername+'" />';
                			html += '<input type="hidden" class="gzhCode" value="'+dataInfo.list[i].code+'" />';
                			if(dataInfo.list[i].status == 1){	//有效
                				html += '<div class="s" onclick="edit(this,\''+dataInfo.list[i].code+'\',0)"><span>设置失效</span></div>';
                			}else if(dataInfo.list[i].status == 0){	//无效
                				html += '<div class="y" onclick="edit(this,\''+dataInfo.list[i].code+'\',1)"><span>设置有效</span></div>';
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

	
function chosePublicAcc(type,event){
	$(event).addClass("friendItemActive").siblings().removeClass("friendItemActive");
	var pageSize=$("#pageSize").val();
	var noWx=$(".shop_list .shopDetail.active .noWx").val();
	var shopNo=$(".shop_list .shopDetail.active .shopNo").val();
	 clock(noWx,pageSize,shopNo,1,type);
}

function edit(event,code,status)
{
	$.ajax({
        type:"POST",
        url:$('#ctx').val() + "/im/publicaccount/edit",
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
        		$(event).parent().remove();
        	}
        }
    });
}

function storeSearch(){
	var searchWords=$(".shop_nav .searchTh").val();
	var shopNo=$(".shop_nav #codeListSec").find("option:selected").val();
	var pmTypeCode=$(".shop_nav .storeClient ").find("option:selected").val();
	var provinceCode=$(".shop_nav .province ").find("option:selected").val();
	var cityCode=$(".shop_nav .shi ").find("option:selected").val();
	var cityAreaCode=$(".shop_nav .qu ").find("option:selected").val();
	window.location.href=$('#ctx').val()+"/im/publicaccount/publicAccountList?searchWords="+searchWords+"&shopNo="+shopNo+"&pmTypeCode="+pmTypeCode+"&provinceCode="+provinceCode+"&cityCode="+cityCode+"&cityAreaCode="+cityAreaCode;
}