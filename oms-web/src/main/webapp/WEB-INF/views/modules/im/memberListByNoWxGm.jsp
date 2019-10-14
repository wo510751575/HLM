<!-- 根据网点微信选择人员插件 -->
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script type="text/javascript">
$(document).ready(function() {

});
var hasCheckedMember = []	//已经勾选的客户
/*
 * 推送对象列表   isAppend：Y 追加  否则替换
 */
function memberNosList(isAppend){
	if($('#noWxs').val() == ''){ // 未选择微信号
		$.ConfirmBox({
			msg:'请选择网点微信号',
			isCancel:false
		});
		return;
	}
	$('#memberNosListId').css('display','flex');
	$('#isAppendStatus').val(isAppend || $('#isAppendStatus').val() || '');
	$('#memberNosListTable').ImPaging({
		isTable:false,
		counts:[5000],
		success:function(elem,ui,pageData){ // elem-paging,ui-ImPage
			var pageData = pageData || {};
			var url = $('#ctx').val() + '/couponmultipush/personMemberList';
			var params = {
				pageNo:pageData.pageNo || 1,
				pageSize:5000,
				keyWord:$('#keyWord').val(),
				sex:$('#sex').val(),
				typeCode:$('#findPmTypeByMerchantNo').val(),
				noWx:$('#noWxs').val()
			};

			$.post(url,params,function(data){
				ui.createTable(elem,function(){ // 创建表格
					var rows = data.page.list;
					if(!rows || rows.length == 0) return '';
					var tags = '';
					var html = '';
					tags += '<ul class="memberNos-list" style="height:360px; overflow:auto;">';
					var checkedAll = $('#selectAllCoupon').is(':checked');
					$.each(rows,function(i){
						tags += '<li>';
						if(hasCheckedMember && hasCheckedMember.length >0){
							for(var j=0;j<hasCheckedMember.length;j++){
								if(rows[i].noWx == hasCheckedMember[j]){
									html = '<input type="checkbox" '+ 'checked' +' data-item=\''+ JSON.stringify(rows[i]) +'\' noWxGm="'+ rows[i].noWxGm +'" nicknamewx="'+ rows[i].nickNameWx +'" class="memberNosBox" id="memberNosBox'+ i +'" class="memberNosBox" onchange="sltChecked()"/>';
									break;
								}else{
									html = '<input type="checkbox" '+ (checkedAll ? 'checked="checked"' : '') +' data-item=\''+ JSON.stringify(rows[i]) +'\' noWxGm="'+ rows[i].noWxGm +'" nicknamewx="'+ rows[i].nickNameWx +'" class="memberNosBox" id="memberNosBox'+ i +'" class="memberNosBox" onchange="sltChecked()"/>';
								}
							}
						}else{
							html = '<input type="checkbox" '+ (checkedAll ? 'checked="checked"' : '') +' data-item=\''+ JSON.stringify(rows[i]) +'\' noWxGm="'+ rows[i].noWxGm +'" nicknamewx="'+ rows[i].nickNameWx +'" class="memberNosBox" id="memberNosBox'+ i +'" class="memberNosBox" onchange="sltChecked()"/>';
						}
						tags += html
						tags += '<label class="fmb" for="memberNosBox'+ i +'">';
						tags += '<div class="i" style="background:url('+ rows[i].headAddress +') no-repeat; background-size:cover;"></div>';
						tags += '<div class="r">';
						tags += '<span class="n">'+ rows[i].memberName +'（'+ rows[i].nickNameWx +' '+ new Date(rows[i].createDate).format('yyyy-MM-dd') +'）</span>';
						tags += '<div class="dt">(';
						if(rows[i].pmTypeName){
							tags += '<span>客户分组：'+ rows[i].pmTypeName +'; </span>';
						}
						if(rows[i].addtype){
							/*添加方式*/
		                 	var addtype = "";
		             		if(1 == info[i].addType){
		             			addtype = "导购扫码添加";
		             		}else if(2 == info[i].addType){
		             			addtype = "客户扫码添加";
		             		}else if(3 == info[i].addType){
		             			addtype = "导购手动新增";
		             		}else if(4 == info[i].addType){
		             			addtype = "微信自动导入";
		             		}else if(5 == info[i].addType){
		             			addtype = "手机号添加";
		             		}else if(6 == info[i].addType){
		             			addtype = "微信号添加";
		             		}else if(7 == info[i].addType){
		             			addtype = "QQ号添加";
		             		}
							tags += '<span>添加方式：'+ addtype +'; </span>';
						}
						if(rows[i].memberNameGm){
							tags += '<span>所属导购：'+ rows[i].memberNameGm +'; </span>';
						}
						if(rows[i].noWxAlias || rows[i].noWx){
							tags += '<span>微信号：'+ (rows[i].noWxAlias || rows[i].noWx) +'; </span>';
						}
						if(rows[i].mobile){
							tags += '<span>手机号：'+ rows[i].mobile +'; </span>';
						}
						tags += ')</div>';
						tags += '</div>';
						tags += '</label>';
						tags += '</li>';
					});
					tags += '</ul>'
					return tags;
				});
				var data = data.page;
				data.pageNo = pageData.pageNo || 1;
				data.limit = data.pageSize;
				data.total = data.count;
				data.start = (data.pageNo-1) * data.pageSize;
				ui.createPages(elem,data); // 创建页码
			},'json');
		}
	});
}
</script>

<input type="hidden" id="shopTerminalByGmNo" value="<shiro:principal property="id"/>"/>

<div class="mask-layer" id="memberNosListId">
	    <div class="sys-variable">
	        <div class="sys-variable-side">
	            <div class="top">
<!-- 	                <select id="findPmTypeByMerchantNo" style="margin-right:10px" onchange="memberNosList()"></select> -->
	                <select id="sex" name="sex" style="margin-right:10px;" onchange="memberNosList()">
	                	<option value="">请选择性别</option>
	                	<option value="MALE">男</option>
	                	<option value="FEMALE">女</option>
	                	<option value="UNKNOWN">未知</option>
	                </select>
	                <div class="search">
	                    <input type="text" class="ipt" placeholder="客户姓名/微信昵称/备注/微信号" id="keyWord" value=""/>
	                    <img onclick="memberNosList()" src="${ctxStatic}/admin/images/imImages/search.png" class="i" />
	                </div>
	            </div>
	            <div class="selectAllCoupon">
					<input type="checkbox" id="selectAllCoupon" class="chk" onchange="selectAll(this)"/>
					<label for="selectAllCoupon">全选</label>
				</div>
	            <div id="memberNosListTable"  style="min-height:300px;">
	            	<ul class="memberNos-list" style="height:360px; overflow:auto;">

		            	</ul>
	            </div>
	            <div class="button-list" style="text-align:right; margin-right:15px;">
		            <input type="button" value="确定" class="save g-btn" onclick="saveMemberNos('#memberNosListId')"/>
		            <input type="button" value="取消" class="doNow b-btn" onclick="$('#memberNosListId').hide()"/>
		        </div>
	        </div>
	    </div>
	</div>