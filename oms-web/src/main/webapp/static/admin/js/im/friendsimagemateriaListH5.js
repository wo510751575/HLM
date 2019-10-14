function listData(){
	$('#data-table-list').ImPaging({
		thead:['分享图片','图片标题','素材类型','分享描述','操作项'],
		twidth:['200','','','220','100'],
		success:function(elem,ui,pageData){ // elem-paging,ui-ImPage
			var pageData = pageData || {};
			var url = $('#ctx').val() + '/cm/friendsimagemateria/list';
			var params = {
					merchantNo:$('#merchantNo').val(),
					pageNo:pageData.pageNo || 1,
					pageSize:pageData.pageSize || 10,
					startDate:$('#startDate').val(),
					endDate:$('#endDate').val(),
					conditionStr:$('#conditionStr').val(),
					deleteFlag:0, // 删除标识：0未删除、1已删除
			};
			
			$.post(url,params,function(data){
				ui.createTable(elem,function(){ // 创建表格
					var rows = data.rows;
					if(rows.length == 0) return;
					var tags = '';
					$.each(rows,function(i){
						debugger;
						tags += '<tr>';
						tags += '<td>';
						var imgAddr = rows[i].imgAddr ? rows[i].imgAddr.split(','):[];
						$.each(imgAddr,function(m){
							tags += '<img src="'+ ($('#UploadUrl').val() + 'im/' + imgAddr[m]) +'" class="i" />';
						});
						tags += '</td>';
						tags += '<td>'+ rows[i].title +'</td>';
						var materialType = rows[i].materialType; // 1-朋友圈广告素材 , 2-朋友圈维护素材
						if(materialType == 1){
							tags += '<td>朋友圈广告素材</td>';
						}else if(materialType == 2){
							tags += '<td>朋友圈维护素材</td>';
						}else{
							tags += '<td>&nbsp;</td>';
						}
						tags += '<td>'+ rows[i].content +'</td>';
						tags += '<td>';
						tags += '<a href="'+ $('#ctx').val() +'/cm/friendsimagemateria/viewH5?code='+ rows[i].code +'" class="edit"></a>';
						tags += '<a href="javascript:void(0)" class="del" onclick="delMateriaItem(this,\''+ rows[i].code +'\')"></a>';
						tags += '</td>';
						tags += '</tr>';
					});
					return tags;
				});
				
				data.pageNo = pageData.pageNo || 1;
				ui.createPages(elem,data); // 创建页码
			},'json');
		}
	});
}

function delMateriaItem(elem,code){
	$.ConfirmBox({
		msg:'确定删除?',
		confirm:function(){ // 确定后执行
			var merchantNo = $('#merchantNo').val()
			var url = $('#ctx').val() + '/cm/friendsimagemateria/deleteH5a';
			
			var params = {
				code:code,
				merchantNo:merchantNo
			};
			$.post(url,params,function(data){
				if(data.success){ // 删除成功
					$(elem).parents('tr').remove();
				}else{ // 删除失败
					$.ConfirmBox({
						msg:data.msg,
						isCancel:false
					})
				}
			},'json');
		},
	})
}

listData(); // 初始化数据 


