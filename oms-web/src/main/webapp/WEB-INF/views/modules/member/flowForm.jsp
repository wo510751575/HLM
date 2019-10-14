<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${not empty data.code?'修改粉丝活码':'新增粉丝活码'}</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>
	<script src="${ctxStatic}/common/plupload.full.min.js" type="text/javascript"></script>
    <script type="text/javascript">

    </script>
    <style type="text/css">
    .nav-child li a{
          line-height: 10px;
    }
    .nav-child li.active a{
          border: 1px dotted #ddd;
          border-bottom-color: transparent;
    }
    .photo-file{
	 	position: absolute;
	    top: 350px;
	    left: 190px;
	    opacity: 0;
	    filter: alpha(opacity:0);
	    cursor:pointer;
	}
	img {
	    border: 0 none;
	    height: 80px;
	    max-width: 100%;
	    vertical-align: middle;
	}
	.pre-container {
    float: right;
    width: 354px;
    height: 630px;
    background: url(${ctxStatic}/images/iphone.png) no-repeat;
    background-size: 100% auto;
	}

	.container{
	padding: 10px 30px;
    width: 100%;
    min-height: 800px;
    background: #fff;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
	}
  #base_div{margin: 20px 0 0 50px;}
  label.control-label{vertical-align: top;}
#memberNosListId{display: none;}
.selectAllCoupon ul{display: flex;background: #6CC4AC;height: 38px;padding: 0 1.5%;align-items: center;margin: 0 auto;}
.selectAllCoupon ul li{color: #fff;font-size: 14px;text-align: center;width: 40%;list-style: none;}
.selectAllCoupon ul li:first-child{width: 15%;}
#memberNosListTable{height: 300px;overflow: auto;border: 1px solid #d1d1d1;margin: 0 auto;}
#memberNosListTable ul{margin: 0;}
#memberNosListTable ul li{padding: 0 1.5%;height: 40px;border-bottom: 1px solid #d1d1d1;justify-content: space-between;list-style: none;}
#memberNosListTable ul li label{width: 100%;height: 40px;display: flex;align-items: center;}
#memberNosListTable ul li span{width: 40%;text-align: center;display: inline-block;}
#memberNosListTable ul li span:first-child{width: 15%;}
.button-list{padding: 5px 10px;width: 100%;border: 1px solid #d1d1d1;box-sizing: border-box;border-top: none;}



    </style>
</head>
<body>
<div class="container">
    <ul class="nav nav-tabs">
    	<li ><a href="${ctx}/member/shopTerminal/selectFlowWx">活码列表</a></li>
			<li class="active"><a href="${ctx}/member/shopTerminal/toAddShopTerminalFlowPage">新增粉丝活码</a></li>
    </ul><br/>

        <%-- <input id="code" name="code" type="hidden" value="${data.code}"/>
        <input id="tempId" name="tempId" type="hidden" value="${temp.value}"/>
        <tags:message content="${message}"/> --%>
<input type="hidden" id="code" name="code" value="${pmFlowQcode.code}" />
<input type="hidden" id="flowNoWx" name="flowNoWx" value="${pmFlowQcode.flowWxNo}" />
        <div id="base_div" class="tab_div">
            <div class="control-group">
                <label class="control-label">活码名称：</label>
                <div class="controls" style="display:inline-block;">
                    <input type="text" id="title" name="title" value="${pmFlowQcode.pmName}"  maxlength="60" class="required input-xxlarge"/>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
            </div>
            <div  class="control-group">
              <label class="control-label">选择微信：</label>
              <div class="controls" style="display:inline-block;">
                <input type="hidden" id="noWxMapping" value="" />
                <textarea class="ipt" readonly="readonly" id="memberNos" name="memberNos" style="width: 460px;height:80px;"></textarea>
                <input id="chooseWeiXin" class="btn btn-primary" type="button" value="选择" onclick="openMemberBox()"/>&nbsp;
                <span class="help-inline"><font color="red">*最多添加30个【系统只展示已配置二维码的终端】</font></span>
                  <p class="sltNum">已选微信（<span id="sltNum">0</span>人）</p>
              </div>
            </div>
            <div class="control-group" id="memberNosListId">
              <div class="controls" style="width:50%;min-width:400px;max-width:600px;margin-left:70px;">
                <div class="selectAllCoupon">
                  <ul>
                    <li>
                      <input type="checkbox" id="selectAllCoupon" class="chk" onchange="selectAll(this)"/>
                      <label for="selectAllCoupon">全选</label>
                    </li>
                    <li>微信号</li>
                    <li>微信昵称</li>
                  </ul>
                </div>
                <div id="memberNosListTable"  style="height:300px;overflow:auto">
                  <ul>
                    <%-- <li>
                      <label>
                        <span>  <input type="checkbox"  class="memberNosBox" onchange="sltChecked()"></span>
                        <span>微信号1</span>
                        <span>微信昵称1</span>
                      </label>
                    </li> --%>
                  </ul>
                </div>
  	            <div class="button-list" style="text-align:right; margin-right:15px;">
                  <input type="button" value="取消" class="doNow b-btn" onclick="closeMemberBox()"/>
  		            <input type="button" value="确定" class="save g-btn" onclick="saveMemberNos('#memberNosListId')"/>
  		          </div>
              </div>
            </div>
            <div class="form-actions" style="margin-left:70px;">
                <input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="submitFrom(this)"/>&nbsp;
                <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
            </div>
        </div>

   </div>


  
  
  <script>
  var memberListData = [//客户数据
    {noWx: "111",noWx: "微信昵称1", id: "1"},
    {noWx: "2222",noWx: "微信昵称2", id: "2"},
    {noWx: "3333",noWx: "微信昵称3", id: "3"},
    {noWx: "4444",noWx: "微信昵称4", id: "4"}
  ]
  
  var code=$("#code").val();
  
  
  
  
  //查询终端微信号
  $(document).ready(function() {

		  //首次添加
		  $.ajax({
		  	type : "POST",
		  	url : "${ctx}/member/shopTerminal/findShopTermianlToFlow",
		  	dataType : "text",
		
		  	success : function(msg){
		  		memberListData=JSON.parse(msg);
		  	},error:function(){
		  	         alert("获取数据失败","error");
		  	} } );
      
		  if(code != null && code!=""){
			  $('#memberNos').val($('#flowNoWx').val());
		  }
		  
  	
  });
  var checkedArr = [] //勾选的数组
  //打开弹窗
  function openMemberBox(){
      $('#memberNosListId').show();
      $("#chooseWeiXin").prop("disabled",true)
      var checkedAll = $('#selectAllCoupon').is(':checked');
      var html = "";
      var tags = "";
      var rows = memberListData
      var hasCheckedArr = JSON.parse(sessionStorage.getItem("checkedArr"))
      console.log(hasCheckedArr)
      $.each(rows,function(i){
        html += "<li><label>";

        if(hasCheckedArr && hasCheckedArr.length > 0){
          for(var j=0;j<hasCheckedArr.length;j++){
            if(rows[i].noWx == hasCheckedArr[j].noWx){
              tags = '<input type="checkbox" '+ 'checked' +' data-item=\''+ JSON.stringify(rows[i]) +'\' noWx="'+ rows[i].noWx +'" nicknamewx="'+ rows[i].wxNickname +'" class="memberNosBox" id="memberNosBox'+ i +'" class="memberNosBox" onchange="sltChecked()"/>';
              break;
            }else{
              tags = '<input type="checkbox" '+ (checkedAll ? 'checked="checked"' : '') +' data-item=\''+ JSON.stringify(rows[i]) +'\' noWx="'+ rows[i].noWx +'" nicknamewx="'+ rows[i].wxNickname +'" class="memberNosBox" id="memberNosBox'+ i +'" class="memberNosBox" onchange="sltChecked()"/>';
            }
          }
        }else{
          tags = '<span><input type="checkbox" '+ (checkedAll ? 'checked="checked"' : '') +' data-item=\''+ JSON.stringify(rows[i]) +'\' noWx="'+ rows[i].noWx +'" nicknamewx="'+ rows[i].wxNickname +'" class="memberNosBox" id="memberNosBox'+ i +'" class="memberNosBox" onchange="sltChecked()"/></span>';
        }
        html += tags;
        html += '<span>'+rows[i].noWx+'</span>';
        html += '<span>'+rows[i].wxNickname+'</span>';
        html += "</label></li>";
      })
      $("#memberNosListTable ul").append(html);
      
      
      //属于编辑内容
      if(code != null && code!=""){
		  $('#memberNos').val($('#flowNoWx').val());
		  var flowNoWx = $('#flowNoWx').val().split(',');
		  
		  $("input[type='checkbox']").each(function(){ 
			
			  for( var index = 0; index < flowNoWx.length; index ++){
	
				  if($(this).attr("noWx") ==flowNoWx[index]){
					  $(this).prop("checked",true);
				  };
			  }
		  });
		  
	  }

  }

    //勾选的客户
    function saveMemberNos(id){
        var checkedList = $('.memberNosBox:checked');
      	var selectAll = $('#selectAllCoupon').is(':checked');

        var noWxMappingData = $('#noWxMapping').val();
        var memberNosData = $('#memberNos').val();
        if(checkedList.length == 0 && !selectAll){
          alertx("请选择微信!");
        }else{
            $('#selectAllCoupon').prop('checked',false);
            var wxNameArr = []
            var nickNameArr = []
            var checkNum = $("input[type='checkbox']:checked").length // 勾选客户数量
            if(selectAll){ // 全选
              if(checkNum > 30){
                alertx("最多选择30个微信");
                return false;
              }
              //全选 就是所有客户数据
              var rows = memberListData
             
              $.each(rows,function(i){
      					wxNameArr.push(rows[i].noWx);
      					nickNameArr.push(rows[i].noWx);
      				});
              window.sessionStorage.setItem("checkedArr",JSON.stringify(rows));
              $('#memberNos').val(wxNameArr.join(','));
      				$('#noWxMapping').val(wxNameArr.join(','));
              $('#sltNum').html(rows.length);

          }else{  //非全选
            var rows = []; // 保存去重后的数据
      			var items = []; // 保存选中的所有的项

      			items = JSON.parse(sessionStorage.getItem("checkedArr"))
      			console.log(items)
            items = dupRemoval(items); // 列表去重
            
            $.each(items,function(i){
      				wxNameArr.push(items[i].noWx);
      				nickNameArr.push(items[i].noWx);
      			});
            console.log(nickNameArr);
            $('#memberNos').val(wxNameArr.join(","));
      			$('#noWxMapping').val(wxNameArr.join(','));
      			$('#sltNum').html((items.length));
          }
      }
        $(id).hide();
        $("#chooseWeiXin").prop("disabled",false)
        $("#memberNosListTable ul").html('')
  }
    //单选
    function sltChecked(){
      checkedArr = []
      $('#selectAllCoupon').prop('checked',false);
      var checkNum = $("input[type='checkbox']:checked").length // 勾选客户数量
      //console.log(checkNum)
      if(checkNum > 30){
          alertx("最多选择30个微信");
        return false;
      }
      var checkedList = $('.memberNosBox:checked');
      $.each(checkedList,function(){
        var item = JSON.parse($(this).attr('data-item'));
        checkedArr.push(item);
      });
      checkedArr = dupRemoval(checkedArr)//去重
      window.sessionStorage.setItem("checkedArr",JSON.stringify(checkedArr));

    }
    //全选
    function selectAll(elem){
      var isChecked = $(elem).is(':checked');
      $('.memberNosBox').prop('checked',isChecked);
    }
    //关闭弹窗
    function closeMemberBox(){
      $('#memberNosListId').hide()
      $("#chooseWeiXin").prop("disabled",false)
      $("#memberNosListTable ul").html('')
    }
    function tabChange(id,ths){
        $(".tab_div").hide();
        $(".nav-child li").removeClass("active");
        $(id).show();
        $(ths).addClass("active");
    }
    //提交
    function submitFrom(elem){
      if($("#title").val() == ''){
        alert("活码名称不能为空！");
        return false;
      }
      if($("#memberNos").val() == ''){
        alert("微信号不能为空！");
        return false;
      }
      $(elem).attr('disabled','disabled');
      //客户微信号参数
      var flowWxNo = $("#memberNos").val();
   
      //客户微信昵称参数
      var pmName = $("#title").val();

      var params ={};
      params["pmName"]=pmName;
      params["flowWxNo"]=flowWxNo;
      var url = '';
      
      //新增
      if(code == null || code == ''){
			  	$.ajax({
			  		type : "POST",
			  		url : "${ctx}/member/shopTerminal/addFlowWxPm",
			  		dataType : "text",
			  		data : params,
			  		success : function(msg){
			  			window.sessionStorage.removeItem('checkedArr');
			  			$(elem).attr('disabled', false);
			  			alert('成功添加');
			  			window.location.href='${ctx}/member/shopTerminal/selectFlowWx';
			  		},error:function(){
			  		         alert("获取数据失败","error");
			  		       $(elem).attr('disabled', false);
			  		} } );
  	
      }
      
      //修改
      if(code != null && code != ''){
    	  params["code"] = code;
		  	$.ajax({
		  		type : "POST",
		  		url : "${ctx}/member/shopTerminal/editFlowWxPm",
		  		dataType : "text",
		  		data : params,
		  		success : function(msg){
		  			window.sessionStorage.removeItem('checkedArr');
		  			$(elem).attr('disabled', false);
		  			alert('修改成功');
		  			window.location.href='${ctx}/member/shopTerminal/selectFlowWx';
		  		},error:function(){
		  		         alert("获取数据失败","error");
		  		       $(elem).attr('disabled', false);
		  		} } );

      }
      

    };

    function dupRemoval(array){ // 数组去重
    	var rows = []; // 保存去重后的list
    	var wxs = []; // 保存去重后的微信号
    	$.each(array,function(i){
    		if(wxs.indexOf(array[i].noWx) == -1){ // 不存在添加
    			rows.push(array[i]);
    			wxs.push(array[i].noWx);
    		}else{ // 存在,跳出循环
    			return true;
    		}
    	});
    	return rows;
    }
    </script>
</body>
</html>
