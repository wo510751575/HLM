<!-- 网点微信选择插件 -->
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
	assistantNoList();
	$('#lVarWxno').on('click','table tbody tr',function(){
		$(this).toggleClass('current');
	});
})
var shopTid_Pre = "shopTid_";
/*
 * 网点微信号列表
 */
function assistantNoList(){
	$('#data-table-list').ImPaging({thead:['网点微信号','网点微信昵称','终端号'],
		success:function(elem,ui,pageData){ // elem-paging,ui-ImPage
			
			var pageData = pageData || {};
// 			var shopTerminalByGmNo=$('#shopTerminalByGmNo').val();
// 			var rows = sessionStorage.getItem(shopTid_Pre+shopTerminalByGmNo);
// 			if(rows){
// 				rows =JSON.parse(rows);
// 				var tags = '';
				
// 				ui.createTable(elem,function(){ // 创建表格
// 					$.each(rows,function(i){
// 						if(rows[i].onlineFlag==0){
// 							tags += '<tr class="redTr" data-nowx="'+ rows[i].noWx +'">';
// 						}else{
// 							tags += '<tr data-nowx="'+ rows[i].noWx +'>';
// 						}
// 						tags += '<td>';
// 						tags +=rows[i].alias?rows[i].alias:rows[i].noWx;
// 						tags +='</td>';
// 						tags += '<td>'+ rows[i].wxNickname +'</td>';
// 						tags += '<td>'+ rows[i].terminalCode +'</td>';
// 						tags += '</tr>';
// 					});
// 					return tags;
// 				});
// 			}else{
				$.post($('#ctx').val() + '/friendsjob/findShopTerminalList.do',function(data){
					ui.createTable(elem,function(){ // 创建表格
						rows = data;
// 						sessionStorage.setItem(shopTid_Pre+shopTerminalByGmNo,JSON.stringify(data));
						var tags = '';
						if(rows.length == 0) return tags;
						$('#nSelect').text(rows.length);
						$.each(rows,function(i){
							if(rows[i].onlineFlag==0){
								tags += '<tr class="redTr" data-nowx="'+ rows[i].noWx +'">';
							}else{
								tags += '<tr data-nowx="'+ rows[i].noWx +'">';
							}
							tags += '<td>';
							tags +=rows[i].alias?rows[i].alias:rows[i].noWx;
							tags +='</td>';
							tags += '<td>'+ rows[i].wxNickname +'</td>';
							tags += '<td>'+ rows[i].terminalCode +'</td>';
							tags += '</tr>';
						});
						return tags;
					});
				},'json');
// 			}
		}
	});

	$('#select-data-table-list').ImPaging({
		thead:['网点微信号','网点微信昵称','终端号'],
		success:function(elem,ui,pageData){ // elem-paging,ui-ImPage
			ui.createTable(elem,function(){
				return '';
			});
		}
	});
	
	$('#nSelect').text($("#data-table-list").find('tbody tr').length);	//未选数量
	$('#ySelect').text($("#select-data-table-list").find('tbody tr').length);//已选数量
	
	
}

/*
 * 选中的项
 */
function selectItems(){
	var lSelected = $("#data-table-list").find('table tr.current');		//左边选中的
	$.each(lSelected,function(){
		$("#select-data-table-list").find('table tbody').append($(this));	//追加到右边
		$('#nSelect').text(function(n,txt){
			return parseInt(txt)==0?0: parseInt(txt)- 1;
		});
		$('#ySelect').text(function(n,txt){
			return parseInt(txt) + 1;
		});
	})
}

/*
 * 取消选中的项
 */
function cancelItems(){
	var rSelected = $("#select-data-table-list").find('table tr.current');		//右边选中的
	$.each(rSelected,function(){
		$("#data-table-list").find('table tbody').append($(this));	//追加到左边
		$('#nSelect').text(function(n,txt){
			return parseInt(txt) + 1;
		});
		$('#ySelect').text(function(n,txt){
			return parseInt(txt)==0?0:parseInt(txt) - 1;
		});
	})
}

/*
 * 搜索网点微信号
 * type:Y-已选,N-未选
 */
function searchWxNoDataList(elem,type,id,numId){
	var rows = $(id).find('tbody tr');
	var selectRows = []; //搜索匹配项
	var key = $(elem).siblings('input.ipt').val().replace(/\s/ig,'');
	if(!key){
		assistantNoList();
		return;
	}
	$.each(rows,function(i){
		var reg = new RegExp('.*' + key + '.*','ig');
		var noWx = $(this).find("td")[0].innerText;	//第一列微信号
		var nick = $(this).find("td")[1].innerText;	//第二列昵称
		var tCode = $(this).find("td")[2].innerText;	//第三列终端号
		if(reg.test(noWx) || reg.test(nick) || reg.test(tCode)){
			selectRows.push(rows[i]);
		}
	});

	var tags = '';

	//因选中的标签为tr标签，不带自身的tr标签，所以往上级获取tr标签
	$.each(selectRows,function(m){
		tags += selectRows[m].outerHTML;
	});

	$(id).find('table tbody').html(tags);
	$(numId).text(selectRows.length);
}
	
/*
 * 保存选中的网点微信号
 */
function saveWxno(){
	 var selectTr = $("#select-data-table-list").find('table tbody tr');
	 if(selectTr.length == 0){
	 	$.ConfirmBox({
				msg:"请选择网点微信号",
				isCancel:false
			});
	 	return
	 }
	 if(selectTr.length > 1){
	 	$.ConfirmBox({
				msg:"最多选择一个网点微信号",
				isCancel:false
			});
	 	return
	 }
	 
	 var wxNoArr = [];
	 var alias; //展示的别名
	 $.each(selectTr,function(){	//多选
	  	wxNoArr.push($(this).attr('data-nowx'));
	  	alias = $(this).find('td')[0].innerText;
	  });
	 
	 
	 $('#noWxs').val(wxNoArr.join(';'));		//赋值隐藏域
	 $('#alias').val(alias);		//赋值展示别名
	 
	$('#lVarWxno').hide();

	//选择微信号 ， 清除推送对象数据
	$('#memberNos').val('');
	$('#sltNum').html('0');
	$('#noWxMapping').val('');
}
</script>

<%-- <input type="hidden" id="shopTerminalByGmNo" value="<shiro:principal property="id"/>"/> --%>
<div class="mask-layer" id="lVarWxno">
    <div class="sys-variable">
        <div class="sys-variable-side">
            <div class="top">
                <span class="n">未选(<span id="nSelect">0</span>)</span>
                <div class="search">
                    <input type="text" class="ipt" placeholder="终端号/网点微信号"/>
                    <img src="${ctxStatic}/admin/images/imImages/search.png" class="i" onclick="searchWxNoDataList(this,'N','#data-table-list','#nSelect')"/>
                </div>
            </div>
            <div class="data-table" id="data-table-list"></div>
        </div>
        <div class="sys-variable-center">
            <input type="button" class="r-btn" onclick="selectItems()"/>
            <input type="button" class="l-btn" onclick="cancelItems()"/>
        </div>
        <div class="sys-variable-side">
            <div class="top">
                <span class="n">已选(<span id="ySelect">0</span>)</span>
                <div class="search">
                    <input type="text" class="ipt" placeholder="终端号/网点微信号"/>
                    <img src="${ctxStatic}/admin/images/imImages/search.png" class="i" onclick="searchWxNoDataList(this,'Y','#select-data-table-list','#ySelect')"/>
                </div>
            </div>
            <div class="data-table" id="select-data-table-list" style="min-height:390px;"></div>
            <div class="button-list" style="text-align:right; margin-right:15px;">
	            <input type="button" value="确定" class="save g-btn" onclick="saveWxno()"/>
	            <input type="button" value="取消" class="doNow b-btn" onclick="$('#lVarWxno').hide()"/>
	        </div>
        </div>
    </div>
</div>