$(document).ready(function() {
	applayFriendList("first");
	$(".pagination select").change(function(){
		var n=$(this).find("option:selected").text();
		$("#pageSize").val(Number(n));
		var t=Number($(".pagination .total").text());
		var totalPage=Math.ceil(t/Number(n));
        $(".pagination .totalPage").text(totalPage);
        applayFriendList("first");
	});

	$(".pageShow").change(function(){
		var total=$(".pagination .totalPage").text();
		var page=$(".pageShow").val();
		var reg= /^[0-9]*[1-9][0-9]*$/;
		if(reg.test(page)){
			if(Number(page)<=Number(total) && Number(page)>0){
				$("#pageNo").val( Number(page));

			}else{
				$(".pageShow").val(1);
				$("#pageNo").val( 1);
			}
			applayFriendList();
		}else{
			if(Number(page)>0 && Number(page)<=Number(total)){
				$(".pageShow").val(Math.floor(Number(page)));
				$("#pageNo").val( Math.floor(Number(page)));
			}else{
				$(".pageShow").val(1);
				$("#pageNo").val( 1);
			}
			applayFriendList();
		}
	});
})
//搜索好友
function applayFriendList(info){
	pageNo= $("#pageNo").val()|| 1;
	pageSize=$("#pageSize").val() || 10;

	if(info=="first"){
		pageNo=1;
	}else if(info=="prev"){
		if(pageNo>1){
			pageNo=pageNo-1;
		}
	}else if(info=="next"){
		var last=$(".pagination .totalPage").text();
		if(pageNo<Number(last)){
			pageNo=Number(pageNo)+1;
		}
	}else if(info=="last"){
		pageNo=$(".pagination .totalPage").text();
	}
	$.ajax({
        type:"POST",
        url:$('#ctx').val()+"/im/applayFriendList",
        data:{pageNo:pageNo,pageSize:pageSize},
        dataType:'JSON',
        success:function(result){
        	$("#pageNo").val(result.data.pageNo);
        	$("#pageSize").val(result.data.pageSize);
        	$("#count").val(result.data.count);
        	 $(".pagination .pageShow").val(result.data.pageNo);
        	 $(".condition_all .kehuNum").text(result.data.count);
             $(".pagination .total").text(result.data.count);
             var totalPage=Math.ceil(result.data.count/result.data.pageSize);
             $(".pagination .totalPage").text(totalPage);
         	var currPage=$(".pagination .pageShow").val();
         	if(totalPage!=undefined & currPage!=undefined & Number(currPage)<Number(totalPage) && Number(currPage)!=1){
            	$(".pagination .first").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_back.png");
        		$(".pagination .prev").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_double_b.png");
        		$(".pagination .next").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_doublef.png");
        		$(".pagination .last").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_font.png");
        	}else if(Number(currPage)==Number(totalPage)  && totalPage!=1){
        		$(".pagination .first").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_back.png");
        		$(".pagination .prev").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_double_b.png");
        		$(".pagination .next").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_doublef1.png");
        		$(".pagination .last").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_font1.png");
        	}else  if(Number(currPage)==1  && totalPage>1){
            	$(".pagination .first").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_back1.png");
        		$(".pagination .prev").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_double_b1.png");
        		$(".pagination .next").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_doublef.png");
        		$(".pagination .last").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_font.png");
        	}else{
        		$(".pagination .first").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_back1.png");
        		$(".pagination .prev").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_double_b1.png");
        		$(".pagination .next").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_doublef1.png");
        		$(".pagination .last").attr("src",$("#ctxStatic").val()+"/admin/images/imImages/arrow_font1.png")
        	}
        	var res=result.data;
        	if(res!=undefined){
        		var html='';
        		if(res.list.length>0){
        			for(var i=0;i<res.list.length;i++){
        				html +='<tr><td><img alt="" src="'+getHeadAddress(res.list[i].headAddress)+'"><span>';
        				if(!res.list[i].nickName){
        					html +=res.list[i].memberName+'</span></td>';
        				}else{
        					html +=res.list[i].nickName+'</span></td>';
        				}
        				html +='<td class="infoFrom"><p>由微信'+res.list[i].aliasGm+'申请</p>';
        				html +='<p class="time">'+new Date(res.list[i].acceptTime).format("yyyy-MM-dd hh:mm")+'</p>';
        				html +='</td><td>';
        				if(res.list[i].addStatus=="Y"){
        					html +='已添加';
        				}else{
        					html +='<input type="button" class="i-btn" value="添加好友" onclick="addFriends(\''+res.list[i].code+'\')">';
        				}
        				html +='</td></tr>';
        			}
        			$(".frdContent table").html(html);

        		}
        	}

        }
    });
}

function addFriends(code){
	$.ajax({
        type:"POST",
        url:$('#ctx').val()+"/im/doListApplayFriend",
        data:{code:code},
        dataType:'JSON',
        success:function(result){
        	if(result.success){
        		$.ConfirmBox({
        			msg:'已发送好友请求。',
        			isCancel:false
        		});
        	}else{
        		$.ConfirmBox({
        			msg:result.msg,
        			isCancel:false
        		});

        	}

        }
    });
}
