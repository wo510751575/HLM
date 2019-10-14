$(document).ready(function() {
	

	$("#btnAdd1").click(function(){
		var m=$(".gongjiao");
		var gongjiao='<div class="model-product-detail-list gongjiao gongjiao'+m.length+'"><div class="control-group"><label class="control-label">公交名字:</label><div class="controls">'+
		'<input type="text" id="modelTwoName'+m.length+'" maxlength="127" class="required input-xxlarge  valid" value="">'+
		'<span class="help-inline"><font color="red">*</font></span></div><label class="control-label">具体路线:</label><div class="controls">'+
		'<input type="text" id="modelTwoLine'+m.length+'" maxlength="127" class="required input-xxlarge  valid" value="">'+
		'<span class="help-inline"><font color="red">*</font></span></div></div></div>';
		
		$(".form-actions1").before(gongjiao);
		var gc=$(".bus .list").length;
		var gjPic='<div class="list list'+gc+'"><p class="h">八桂领社康中心-公交站</p><p class="xl">8路、323路、336路、352路、b615路、m389路;</p></div>';
		$(".bus").append(gjPic);
	});
	
	$("#btnDel1").click(function(){
		$($(".gongjiao")[$(".gongjiao").length-1]).remove();
		$($(".bus .list")[$(".bus .list").length-1]).remove();
	});
    
	
	$("#btnAdd2").click(function(){
		var m=$(".ditie").length;
		var gongjiao='<div class="model-product-detail-list ditie ditie'+m+'"><div class="control-group"><label class="control-label">地铁名字:</label><div class="controls">'+
		'<input type="text" id="modelTwoditieName'+m+'" maxlength="127" class="required input-xxlarge  valid" value="">'+
		'<span class="help-inline"><font color="red">*</font></span></div><label class="control-label">具体路线:</label><div class="controls">'+
		'<input type="text" id="modelTwoditieLine'+m+'" maxlength="127" class="required input-xxlarge  valid" value="">'+
		'<span class="help-inline"><font color="red">*</font></span></div></div></div>';
		
		$(".form-actions2").before(gongjiao);
		var gc=$(".ditbus .list").length;
		var gjPic='<div class="list list'+gc+'"><p class="h">1.红岭北-地铁站</p><p class="xl">地铁7号线、地铁9号线</p></div>';
		$(".ditbus").append(gjPic);
		});
	
	$("#btnDel2").click(function(){
		$($(".ditie")[$(".ditie").length-1]).remove();
		$($(".ditbus .list")[$(".ditbus .list").length-1]).remove();
	});
	
	
	$("#btnAdd3").click(function(){
		var m=$(".zija").length;
		var gongjiao='<div class="model-product-detail-list zija zija'+m+'"><div class="control-group"><label class="control-label">自驾目的地:</label><div class="controls">'+
		'<input type="text" id="modelTwozijaName'+m+'" maxlength="127" class="required input-xxlarge  valid" value="">'+
		'<span class="help-inline"><font color="red">*</font></span></div><label class="control-label">具体路线:</label><div class="controls">'+
		'<input type="text" id="modelTwozijaLine'+m+'" maxlength="127" class="required input-xxlarge  valid" value="">'+
		'<span class="help-inline"><font color="red">*</font></span></div></div></div>';
		
		$(".form-actions3").before(gongjiao);
		
		var gc=$(".zijiabus .list").length;
		var gjPic='<div class="list list'+gc+'"><p class="h">1、导航目的地</p><p class="xl">深圳市福田区八卦四路新阳大厦首层43号方太旗舰店</p></div>';
		$(".zijiabus").append(gjPic);
		});
	
	$("#btnDel3").click(function(){
		$($(".zija")[$(".zija").length-1]).remove();
		$($(".zijiabus .list")[$(".zijiabus .list").length-1]).remove();
	});
	
	$("#btnAdd4").click(function(){
		var m=$(".chechang").length;
		var gongjiao='<div class="model-product-detail-list chechang chechang'+m+'"><div class="control-group"><label class="control-label">附近停车场:</label>'+
		'<div class="controls"><input type="text" id="stopCar'+m+'" maxlength="127"class="required input-xxlarge  valid" value="">'+
		'<span class="help-inline"><font color="red">*</font></span></div></div></div>';
		
		$(".form-actions4").before(gongjiao);
		
		var gc=$(".stop .list").length;
		var gjPic='<p class="h h'+gc+'">新阳大厦-地上停车场</p>';
		$(".stop .list").append(gjPic);
		});
	
	$("#btnDel4").click(function(){
		$($(".chechang")[$(".chechang").length-1]).remove();
		$($(".stop .list p")[$(".stop .list p").length-1]).remove();
	});
	
	
	$("#modelTwoInfo1").blur(function(){
		$($(".modelTWO .md-t-phone ul li")[0]).find(".n").text($("#modelTwoInfo1").val());
	});
	$("#modelTwoInfo2").blur(function(){
		$($(".modelTWO .md-t-phone ul li")[0]).find(".d").text($("#modelTwoInfo2").val());
	});
	$("#modelTwoInfo3").blur(function(){
		$($(".modelTWO .md-t-phone ul li")[1]).find(".n").text($("#modelTwoInfo3").val());
	});
	$("#modelTwoInfo4").blur(function(){
		$($(".modelTWO .md-t-phone ul li")[1]).find(".d").text($("#modelTwoInfo4").val());
	});
	$("#modelTwoInfo5").blur(function(){
		$($(".modelTWO .md-t-phone ul li")[2]).find(".n").text($("#modelTwoInfo5").val());
	});
	$("#modelTwoInfo6").blur(function(){
		$($(".modelTWO .md-t-phone ul li")[2]).find(".d").text($("#modelTwoInfo6").val());
	});
	
	
	$('div').on('blur','.gongjiao input',function(){
		var tt=$(this).attr("id");
		var t2= tt.split("modelTwoName")[1];
		var t3= tt.split("modelTwoLine")[1];
		if(t2!="" && t2!=undefined){
			if(t2==0){
				t2="";
			}
			$(".modelTWO .bus .list"+t2).find(".h").text($(this).val());
		}
		if(t3!="" && t3!=undefined){
			if(t3==0){
				t3="";
			}
			$(".modelTWO .bus .list"+t3).find(".xl").text($(this).val());
		}
	});
	
	$('div').on('blur','.ditie input',function(){
		var tt=$(this).attr("id");
		var t1= tt.split("modelTwoditieInfo")[1];
		var t2= tt.split("modelTwoditieName")[1];
		var t3= tt.split("modelTwoditieLine")[1];
		if(t1!="" && t1!=undefined){
			$(".modelTWO .ditbus .t").find(".n").text($(this).val());
		}
		if(t2!="" && t2!=undefined){
			if(t2==0){
				t2="";
			}
			$(".modelTWO .ditbus .list"+t2).find(".h").text($(this).val());
		}
		if(t3!="" && t3!=undefined){
			if(t3==0){
				t3="";
			}
			$(".modelTWO .ditbus .list"+t3).find(".xl").text($(this).val());
		}
		
	});
	
	
	$('div').on('blur','.zija input',function(){
		var tt=$(this).attr("id");
		var t1= tt.split("modelTwozijaInfo")[1];
		var t2= tt.split("modelTwozijaName")[1];
		var t3= tt.split("modelTwozijaLine")[1];
		if(t1!="" && t1!=undefined){
			$(".modelTWO .zijiabus  .t").find(".n").text($(this).val());
		}
		if(t2!="" && t2!=undefined){
			if(t2==0){
				t2="";
			}
			$(".modelTWO .zijiabus  .list"+t2).find(".h").text($(this).val());
		}
		if(t3!="" && t3!=undefined){
			if(t3==0){
				t3="";
			}
			$(".modelTWO .zijiabus  .list"+t3).find(".xl").text($(this).val());
		}
	});

	$('div').on('blur','.chechang input',function(){
		var tt=$(this).attr("id");
		var t1= tt.split("stopCar")[1];
		
		if(t1!="" && t1!=undefined){
			if(t1==0){
				t1="";
			}
			$(".modelTWO .stop  .list").find(".h"+t1).text($(this).val());
		}
		
	});
	
	
	
});