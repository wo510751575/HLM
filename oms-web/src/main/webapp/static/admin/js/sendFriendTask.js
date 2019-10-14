$(document).ready(function() {
	
	$("#inAdd").click(function(){
		 var p = $(".notChose .active");
		 p.clone().appendTo(".chose ul");
		 p.remove();
	});
	
	$("#inRemove").click(function(){
		 var p = $(".chose .active");
		 p.clone().appendTo(".notChose ul");
		 p.remove();
	});
	
	$(".sendF .f_center .x").click(function(){
		if($(this).hasClass("comment")){
			window.location.href=$("#ctx").val()+"/im/friendList";
			$(this).addClass("active");
			$(".sendF .f_center .friend").removeClass("active");
		}else{
			window.location.href=$("#ctx").val()+"/friendsjob/sendFriends";
			$(this).addClass("active");
			$(".sendF .f_center .comment").removeClass("active");
		}
	});
	
});

function check(event){
	if($(event).hasClass("active")){
		$(event).removeClass("active");
	}else{
		$(event).addClass("active");
	}
}

function showNo(){
	$("#varWxno").show();
	
}