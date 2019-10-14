function listData(){
	$('#data-table-list').ImPaging({
		thead:['时间','收信人','群发内容','操作'],
		twidth:['130','300','','80'],
		success:function(elem,ui,pageData){ // elem-paging,ui-ImPage
			var pageData = pageData || {};
			var url = $('#ctx').val() + '/im/groupChatInfo/listJson';
      var startTime = $('#startDate').val();
      var endTime = $('#endDate').val();
      var content = $("#conditionStr").val();
			var params = {
					pageNo:pageData.pageNo || 1,
					pageSize:pageData.pageSize || 10,
			};
      if(startTime){
        params.startTime = startTime
      }
      if(endTime){
        params.endTime = endTime
      }
      if(content){
        params.content = content
      }
			$.post(url,params,function(data){
				ui.createTable(elem,function(){ // 创建表格
					var rows = data.data.list;
					if(rows.length == 0) return;
					var tags = '';
					$.each(rows,function(i){
						tags += '<tr>';
						tags += '<td>'+ (new Date(rows[i].createDate).format('yyyy-MM-dd hh:mm')) +'</td>';
						tags += '<td>'+ rows[i].memberNames +'</td>';
            if(rows[i].content){
              tags +=  '<td>'+ rows[i].content +'</td>'
            }
            if(rows[i].resourcesPath){
              tags +=  '<td><img class="tdImg" src="'+rows[i].resourcesPath+'" onclick="onceAgain(this,\''+ rows[i].code +'\')" ></td>'
            }
						tags += '<td>';
						tags += '<a href="javascript:void(0)" onclick="onceAgain(this,\''+ rows[i].code +'\')">再发一条</a>';
						tags += '</td>';
						tags += '</tr>';
					});
					return tags;
				});
        var data = data.data;
				data.pageNo = data.pageNo || 1;
				data.limit = data.pageSize;
				data.total = data.count;
				data.start = (data.pageNo-1) * data.pageSize;
				ui.createPages(elem,data); // 创建页码
			},'json');
		}
	});
}

function onceAgain(elem,code){
	$.ConfirmBox({
		msg:'确定再发一条?',
		confirm:function(){ // 确定后执行
			var merchantNo = $('#merchantNo').val()
			var url = $('#ctx').val() + '/im/groupChatInfo/sendChatMessage';
			var params = {
				code:code,
			//	merchantNo:merchantNo
			};
			$.post(url,params,function(data){
				if(data.result === true){ // 成功
          $.ConfirmBox({
						msg:"群发消息成功",
						isCancel:false
					})
				//	$(elem).parents('tr').remove();
				}else{ // 失败
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
