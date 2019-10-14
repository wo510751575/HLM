/*
 * 表单验证 
 */
function formValid(form){
	var contentLength = $('#content').val();
	var commentContentVal = $('#commentContent').val();
	if(contentLength.length>1800){
		alert('您输入的内容过长，不能超过1800字符');
		return;
	}else if(commentContentVal.length>1000){
		alert('您输入的内容过长，不能超过1000字符');
		return;
	}else{
		$(form).validate({
			rules:{
				title:{
					required:true
				},
				content:{
					required:true,
					remote: {
					    url: $('#ctx').val() + '/cm/friendsimagemateria/checkMaterialVal',     //后台处理程序
					    type: 'post',               //数据发送方式
					    dataType: 'json',           //接受数据格式   
					    data: {                     //要传递的数据
					    	content: function() {
					            return $("#content").val();
					        },
					        merchantNo: function() {
					        	return $("#merchantNo").val();
					        },
					    }
					}
				
				},
			},
			messages:{
				title:{
					required:'图片主题不能为空'
				},
				content:{
					required:'分享描述不能为空',
					remote:'分享描述-定义的变量组合数小于系统要求的最小值 1000，请增加变量或者变量值数'
				},
			},
			errorPlacement: function(error, element) {  
			    error.appendTo(element.parents('.tag'));  
			}
		});
	}
}

function saveFriendsImage(form,code){
	var contentLength = $('#content').val();
	var commentContentVal = $('#commentContent').val();
	var title=$("#title").val();
	if(title.length>100){
		alert('您输入的标题过长，不能超过100字符');
		return;
	}
	if(contentLength.length>1800){
		alert('您输入的内容过长，不能超过1800字符');
		return;
	}else if(commentContentVal.length>1000){
		alert('您输入的评论内容过长，不能超过1000字符');
		return;
	}else{
		var imgAddr = [];
		$.each($('#imgListId').find('li'),function(){
			imgAddr.push($(this).attr('data-src'));
		});
		$('#imgAddr').val(imgAddr.join(','));
		
		formValid(form); 
		
		if(!$(form).valid()) return; // 验证不通过
		var conts=$("#content").val().split("\n");
		var cc="";
		var t=0;
		for(var i=0;i<conts.length;i++){
			if(conts[i]==""&& t<1){
				cc+="\n";
				t=t+1;
			}else if(conts[i]==""){
				continue;
			}
			if(conts[i]!=""){
				cc+=conts[i]+"\n";
				t=0;
			}
		}
		$("#content").val(cc);
		if(code == ''){ // 新增
			$(form).attr('action',$('#ctx').val() + '/cm/friendsimagemateria/saveH5');
		}else{ // 修改
			$(form).attr('action',$('#ctx').val() + '/cm/friendsimagemateria/updateH5');
		}
		$(form).submit();
	}

}

$('input[name=autoComment]').on('change',function(){
	var val = $(this).val();
	if(val == 1){
		$('#tagCommentContent').css('display','flex').find('.area').val('');
	}else{
		$('#tagCommentContent').hide().find('.area').val('');
	}
});

function varExplainListData(){
	$('#varExplainDataTable').ImPaging({
		thead:['变量名称','变量值','值个数','最后更新时间'],
		twidth:['','600','',''],
		success:function(elem,ui,pageData){ // elem-paging,ui-ImPage
			var pageData = pageData || {};
			var url = $('#ctx').val() + '/cm/materialVariable/returnList';
			var params = {
					merchantNo:$('#merchantNo').val(),
					pageNo:pageData.pageNo || 1,
					pageSize:pageData.pageSize || 100000000,
					conditionStr:$('#conditionStr').val(),
					sysFlag:$('#sysFlag').val(),
			};
			
			$.post(url,params,function(data){
				ui.createTable(elem,function(){ // 创建表格
					var rows = data.rows;
					if(rows.length == 0) return;
					var tags = '';
					$.each(rows,function(i){
						tags += '<tr>';
						tags += '<td>'+ rows[i].varName +'</td>';
						var varContent = rows[i].varContent.split('$');
						tags += '<td>';
						$.each(varContent,function(m){
							tags += '<span class="varContent">'+ varContent[m] +'</span>';
						});
						tags += '</td>';
						tags += '<td>'+ rows[i].varCount +'</td>';
						tags += '<td>'+ (new Date(rows[i].createDate).format('yyyy-MM-dd hh:mm')) +'</td>';
						tags += '</tr>';
					});
					return tags;
				});
			},'json');
		}
	});
}

function contentTxt() { //分享描述输入框长度限制
	var contentLength = $('#content').val();
	if(contentLength.length>1800){
		alert('您输入的内容过长，不能超过1800字符')
	}
}
function commentContentTxt() {
	var commentContentVal = $('#commentContent').val();
	if(commentContentVal.length>1000){
		alert('您输入的评论内容过长，不能超过1000字符')
	}
}

varExplainListData();

