<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>详细资料</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {

			$(".tab_chick td").click(function(){
				var idt=$(this).attr("data-tab").split("#")[1];
				$("#"+idt).show();
				$(this).addClass("chicked");
				$("#"+idt).siblings().hide();
				$(this).siblings().removeClass("chicked");
			});

		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function CancelQuery(){
			$(':input','#searchForm')
			 .not(':button, :submit, :reset')
			 .val('')
			 .removeAttr('checked')
			 .removeAttr('selected');

			$("#pageNo").val(1);
			$("#searchForm").submit();
        }
	</script>
	<style type="text/css">
		.table-list thead tr {
    background: #eeeeee;
    color: #666;
}
.table-list {
    font-size: 14px;
    width: 100%;
    font-size: 14px;
    border: 1px solid #e8e8e8;
    border-bottom: none;
}
@font-face {
    font-family:'ZenIcon';
    src: url('../fonts/zenicon.woff');
}


.text-default {
    color: #63a8eb;
}

.customer_category {
    border-bottom: 1px solid #e8e8e8;
    margin-bottom: 20px;
}

.customer_category a {
    display: inline-block;
    text-align: center;
    padding: 2px 20px 10px 20px;
    margin-right: 20px;
    font-size: 14px;
}

.customer_category .focus {
    color: #63a8eb;
    margin-bottom: -1px;
    border-bottom: 1px solid #63a8eb;
    font-size: 14px;
}

.customer_category #startDate {
    width: 120px;
    margin-right: 5px;
}

.customer_category #endDate {
    width: 120px;
    margin-right: 15px;
}

.cus_list_content {
    background: #fff;
    border-radius: 2px;
    -webkit-box-sizing: border-box;
    padding: 30px
}

.export-button {
    margin-left: 0px;
    width: 110px;
    background-color: white;
    color: #666666;
    border: 1px solid #999999;
    height: 34px;
}

.user_ico {
    padding: 0;
}

.user_ico img {
    display: inline-block;
    vertical-align: middle;
    width: 35px;
    height: 35px;
    border-radius: 50%;
}

.data-dia, .data-dia > .fix {
    position: fixed;
    top: 0px;
    left: 0px;
    height: 100%;
    z-index: 1001;
    max-height: 100%;
    overflow-y: auto;
}

.data-dia {
    display: none;
}

.data-dia > .fix {
    z-index: 1001;
    margin: 0px;
    background-color: rgba(0, 0, 0, 0.5);
}

.data-dia .data-content {
    width: 640px;
    padding: 10px 30px;
    text-align: left;
    position: relative;
    border-radius: 2px;
    margin: auto;
    z-index: 1002;
    background-color: #fff;
}

.tab_chick table tr .chicked {
    border-bottom: 1px solid #63a8eb;
    color: #63a8eb;
}

.tab_chick table tr td {
    vertical-align: middle;
    font-size: 14px;
    cursor: pointer;
}

.page-header {
    font-size: 25px;
    padding: 12.5px 0px;
    margin-bottom: 12.5px;
    border-bottom: 1px solid #ddd;
}

.data-dia, .data-dia > .fix {
    position: fixed;
}

.data-dia .data-content {
    text-align: center;
}

.data-dia .dia_header {
    padding: 15px 0;
    border-bottom: 1px solid #ddd;
    color: #333;
    text-align: center;
    font-weight:normal;
    font-size:25px;
    line-height:1;
}

.data-dia .dia_header .close_log {
    margin-top: 10px;
    height: 15px;
    width: 16px;
    position: absolute;
    top: 17px;
    right: 30px;
}

.data-dia p {
    padding-top: 20px;
}

.data-dia p:nth-of-type(2) {
}

.data-dia span {
    color: #999999;
}

.data-dia select {
    width: 110px;
}

.data-dia .data-content {
    width: 450px;
}

.data-dia .data-content input {
    width: 270px;
}

.data-dia button {
    cursor: pointer;
    font-size: 16px;
}

.data-dia .cancel {
    background-color: #2bc589;
    color: white;
    margin-bottom: 20px;
}

.data-dia .confirm {
    background-color: #2bc589;
}

#user_info .data-content {
    box-sizing:border-box;
    min-width: 917px;
    min-height: 80%;
    margin: 0 auto;
}

.data-dia img {
    width: 100px;
    height: 100px;
    border-radius: 50%;
}

.data-dia .list_table {
    margin: auto;
    border: 1px solid #ddd;
}

.data-dia table td {
    text-align: center
}

.data-dia table td button {
    font-size: 12px
}

.data-dia table {
    width: 100%;
    table-layout:fixed;
}

.tab_chick table tr {
    height: 40px;
    border-bottom: 1px solid #ddd
}

.tab_chick table tr td {
    vertical-align: middle;
    font-size: 14px;
    cursor: pointer;
}

.tab_chick table tr .chicked {
    border-bottom: 1px solid #63a8eb;
    color: #63a8eb
}

.data-dia .data-content .pageNav input {
    width: auto;
}

.data-dia .data-content .pageNav input[type=text] {
    width: 30px
}

.data-dia .data-content .pageNav {
    margin: 30px 0 20px 0;
}

#personal .personal_title {
    margin: 0 auto;
    height: 60px;
}

#personal .personal_title li {
    display: inline-block;
    width: 135px;
    text-align: center;
    height: 60px;
    line-height: 60px;
}

#personal .personal_title li b {
    font-weight: bold;
}

.tab_chick_list > div {
    display: none;
}

#wechart_info {
    padding-top: 30px;
}

#wechart_info .personal_header {
    height: 100px;
    display:flex;
    justify-content:center;
    align-items:center
}

#wechart_info .personal_header .small_head{
    width:60px;
    height:60px;
    overflow:hidden;
    border-radius:60px;
}

#wechart_info .personal_header .hrf{
    width:35px;
    height:18px;
    margin:0px 15px;
    background:url("${ctxStatic}/images/hrf.png") no-repeat 50%;
}

#no_info img {
    border-radius: 0;
    width: 166px;
    height: 220px;
}

.table-list {
    margin-bottom: 20px;
    table-layout: fixed;

}

.table-list tbody tr td {
    padding: 12px 5px;
    vertical-align: middle;
    word-wrap:break-word;
}

.table-list tbody tr td:nth-of-type(4) {
    /*overflow: hidden;*/
    /*text-overflow: ellipsis;*/
    /*white-space: nowrap;*/
    word-wrap: break-word;
}



.data-dia .change-table tbody tr:nth-of-type(2n) {
    background-color: #fff
}

.data-dia .change-table tbody tr td:nth-of-type(2n-1) {
    background: #f8f8f8;
}

#wechart_info .change-table tbody tr td:nth-of-type(2n) {
    text-align: left;
    padding-left: 20px;
}

.tab_chick table tr td, .tab_chick_list .table-list tbody tr td {
    height: 20px;
    vertical-align: middle;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}

.page_header {
    font-size: 32px;
    font-weight: normal;
    line-height: 1;
    padding-bottom: 40px;
}

.search-group .btn-default {
    margin-left: 0;
}

.info_data {
    width: 100%;
    margin:20px 0 40px 0;
    display: flex;
}

.info_data > div:not(:last-of-type):before {
    position: absolute;
    content: '';
    width: 2px;
    height: 40px;
    background: #edf2fb;
    right: 0px;
    top: 10px;
}

.info_data_list {
    width: 25%;
    text-align: center;
    position: relative;
}

.info_data_list * {
    font-weight: normal;
}

.info_data_list h2 {
    color: #63a8eb;
    font-size: 26px;
}

.info_data_list span {
    font-size: 14px;
}

#wechart_info .wechart_info_table {
    display: flex;
    justify-content: center;
}

#wechart_info .wechart_info_table .table-list {
    width: 410px;
}


.open_url{
    color:#63a8eb;
}

.wechat_title{
    font-weight:normal;
    font-size:14px;
    line-height:1;
    margin:30px 0;
}

.wechat_title span{
    color:#666;
}

.wechat_title span:nth-of-type(1){
    margin-right:20px;
}

.phone-content {
    margin-bottom: 20px;
    color: #666;
    text-align: left;
}

.phone-content .title {
    line-height: 1;
    font-weight: bold;
}

.phone-list {
    margin-top: 20px;
    list-style: none;
}

.phone-list li {
    margin-top: 10px;
    line-height: 1;
}

.pl-mark,
span.pl-mark {
    margin: 0 5px;
    color: #666;
    font-weight: bold;
}




.tab_chick_list{
    /*display:flex;*/
    justify-content:center;
    /*align-items:center;*/
    min-height:500px;
}

.table-list thead tr th{
    padding: 10px 2px;
}

#label .label_title{
    margin-top:30px;
    line-height: 1;
    font-size:14px;
    font-weight:normal;
    color:#666;
    text-align:left;
}

#label{
    height:500px;
    width:100%;
}

#label .label_content{
    text-align:left;
}

#label .place{
    display: flex;
    justify-content:flex-start;
}

#label .place>div{
    width:205px;
    text-align:left;
    position:relative;
}

#label .place>div .label_place:before{
    content:url("../images/point.png");
    width: 10px;
    height:15px;
    position: absolute;
    left: 12px;
    top: 10px;
}

#label .label_content span,#label .place>div .label_place{
    padding: 0 30px;
    position:relative;
    margin-top:20px;
    text-align:center;
    display:inline-block;
    /*width:115px;*/
    height:34px;
    border-radius:17px;
    border:1px solid #e8e8e8;
    color:#666;
    line-height: 34px;
    margin-right: 20px;
    cursor:pointer;
}

.label_place i{
    position: absolute;
    top: 1px;
    right: 10px;
    color: #999;
    font-style: normal;
}

#label .place>div .label_place:hover i{
    color: #63a8eb;
}

#label .label_content span:hover,#label .place>div .label_place:hover{
    border: 1px solid #63a8eb;
    color: #63a8eb;
}

.js-qr-check{
    display: inline-block;
    width: 16px;
    height: 16px;
    background: url("../images/check.png") 0 0 no-repeat;
    cursor: pointer;
    vertical-align:middle;
}

.choice-all{
    margin-bottom:30px;
    padding-left: 25px;
}

.choice-all h2{
    font-weight:normal;
    font-size:14px;
    display:inline-block;
    margin:0 20px;
}

.choice-all h2 strong{
    font-weight:normal;
    color: #63a8eb;
}

.choice-all input[type=button]{
    width:110px;
    height:34px;
    background:transparent;
    border:1px solid #ddd;
    color:#666;
}

.choice-all input[type=button]:hover{
    border: 1px solid #63a8eb;
    color: #63a8eb;
}

.checked{
    background-position: 0 -16px;
}

.pageNav .choice_nub{
    margin-left:30px;
    position:relative;
    cursor:pointer;
}

.pageNav .choice_nub:after{
    content:'';
    position:absolute;
    right:-15px;
    top:3px;
    width:0px;
    height:0px;
    border:5px solid transparent;
    border-bottom:5px solid #666;
}

.pageNav{
    position: relative;
}

.pageNav .choice_page{
    position:absolute;
    bottom: 30px;
    right: -80px;
    box-sizing:border-box;
    display:none;
    width:212px;
    height:141px;
    border:1px solid rgba(0, 0, 0, 0.14902);
    background:#fff;
}

.pageNav .choice_page h3{
    width:70px;
    height:28px;
    float:left;
    display:inline-block;
    font-size: 12px;
    padding: 5px 25px 5px 15px;
    font-weight:normal;
    border-right: 1px dotted #ddd;
    border-bottom: 1px dotted #ddd;
    cursor:pointer;
    position:relative;
}

.pageNav .choice_page .active{
    background: #f1f1f1;
    font-weight: bold;
}

.pageNav .choice_page .active:before{
    content: '\e60d';
    font-family: ZenIcon;
    font-size: 14px;
    position: absolute;
    right: 8px;
    top: 4px;
    display: block;
    color: #808080;
    font-weight: normal;
}

.pageNav .show{
    display:inline-block;
}
.search-group .wd110{
    width:110px;
}

#bulk .data-content{
    box-sizing:border-box;
    width:696px;
    height:640px;
}

#bulk .data_content{
    margin-top:30px;
}

#bulk .data_content .search-group .textarea{
    display:inline-block;
    width: 462px;
    height: 204px;
    vertical-align: top;
    border:1px solid #ddd;
    font-size: 14px;
    text-align: left;
    padding: 10px;
    outline:none;
}

#bulk .data_content .search-group .content_nub{
    position:absolute;
    font-size:14px;
    bottom: 5px;
    right: 5px;
}

.textarea_header{
    position:relative;
    display:inline-block;
    width: 462px;
    height: 204px;
    margin-left:10px;
    vertical-align:top;
    margin-top:40px;
}

.textarea_header:after{
    content:'';
    position:relative;
    display:inline-block;
    width: 462px;
    height: 40px;
    top:-244px;
    left:0;
    background-color:#efefef;
}

.page_link{
    position: absolute;
    background:url('../images/link.png') no-repeat 50%;
    width: 40px;
    height: 40px;
    top: -40px;
    z-index: 2;
    left: 10px;
}

#bulk .data_content h3{
    display:block;
    box-sizing:border-box;
    padding-left:10px;
    line-height:1;
    font-size:14px;
    margin-bottom:20px;
    font-weight:normal;
    text-align:left;
}

#bulk .footbtn{
    position: absolute;
    bottom: 30px;
    left: 50%;
    margin-left: -50px;
}

#link .data-content{
    box-sizing:border-box;
    width:480px;
    height:240px;
}

#link .link_input{
    width: 380px;
    margin-top:40px;
}

.choice-all .error{
    font-size:14px;
    /*display:block;*/
    font-style:normal;
    margin-left:30px;
    color:#f3734f;
    margin-top:10px;
    display:none;
}
.no-data {
    height: 400px!important;
    background: url("../images/noinfo.png") center center no-repeat;
}

#noSelect{
    color: #f3734f;
    font-size: 14px;
    position: absolute;
    width: 200px;
    font-style: normal;
    left: 50px;
    display: none;
    top: -40px;
    z-index: 1;
    height: 40px;
    line-height: 40px;
}

.tracks_header{
    margin-bottom:30px;
    margin-top:10px;
    display:flex;
    flex-wrap: wrap;
}

.tracks_content {
    height: 450px;
    overflow: auto;
    max-height: 500px;
}

.tracks_header a{
    display: inline-block;
    position: relative;
    text-align: left;
    width: 153px;
    height: 34px;
    margin: 20px 20px 0 0;
    border: 1px solid transparent;
    line-height: 32px;
    box-sizing: border-box;
    padding-left: 36px;
    border-radius:5px;
    color:#666;
}

.tracks_header a:nth-of-type(5n){
    margin-right:0;
}

.tracks_header a i{
    background-image:url("../images/01.png");
    position: absolute;
    display:inline-block;
    left:7px;
    top: 3.5px;
    width:26px;
    height:26px;
    overflow:hidden;
}

.all i{
    background-position:0 0;
}

.attention i{
    background-position:-26px 0;
}

.cance i{
    background-position:-52px 0;
}

.card i{
    background-position:-78px 0;
}

.binding i{
    background-position:-104px 0;
}

.receive i{
    background-position:0 -26px;
}

.collect i{
    background-position:-26px -26px;
}

.menu i{
    background-position:-52px -26px;
}

.participate i{
    background-position:-78px -26px;
}

.communication i{
    background-position:-104px -26px;
}

.questionnaire i{
    background-position:0 -52px;
}

.tracks_header .all:hover{
    border-color:#63a8eb;
}

.tracks_header .all:hover,.tracks_header .all:hover span{
    color:#63a8eb;
}

.tracks_header .attention:hover{
    border-color:#8bd9eb;
}

.tracks_header .attention:hover,.tracks_header .attention:hover span{
    color:#8bd9eb;
}

.tracks_header .cance:hover{
    border-color:#fe6f86;
}

.tracks_header .cance:hover,.tracks_header .cance:hover span{
    color:#fe6f86;
}

.tracks_header .card:hover{
    border-color:#f5926e;
}

.tracks_header .card:hover,.tracks_header .card:hover span{
    color:#f5926e;
}

.tracks_header .binding:hover{
    border-color:#b1de80;
}

.tracks_header .binding:hover,.tracks_header .binding:hover span{
    color:#b1de80;
}

.tracks_header .receive:hover{
    border-color:#ad86c3;
}

.tracks_header .receive:hover,.tracks_header .receive:hover span{
    color:#ad86c3;
}

.tracks_header .collect:hover{
    border-color:#57dfbb;
}

.tracks_header .collect:hover,.tracks_header .collect:hover span{
    color:#57dfbb;
}

.tracks_header .menu:hover{
    border-color:#f886b9;
}

.tracks_header .menu:hover,.tracks_header .menu:hover span{
    color:#f886b9;
}

.tracks_header .participate:hover{
    border-color:#dedd63;
}

.tracks_header .participate:hover,.tracks_header .participate:hover span{
    color:#dedd63;
}

.tracks_header .communication:hover{
    border-color:#8ba2eb;
}

.tracks_header .communication:hover,.tracks_header .communication:hover span{
    color:#8ba2eb;
}

span.clickMore {
    display: none;
    cursor: pointer;
    color: rgba(99, 168, 235,1);
}

#no_info {
    display: none;
}

button{
    outline: none;
}
.flow-head{
    margin: 30px 0 0 0;
}
.head-left{
    float: left;
}
.head-right{
    float: right;
}
.head-items{
    display: inline-block;
    line-height: 34px;
    width: 170px;
    text-align: left;
}
.head-items span{
    color: #63a8eb;
}
.noData{
    display: inline-block;
    width: 100%;
    height: 400px;
    background: url(../images/noinfo.png) center center no-repeat;
}

.btn-back {
    float:right;
    margin-top: -14px;
    display: inline-block;
    width: 110px;
    height: 34px;
    line-height: 34px;
    text-align: center;
    border: 1px solid #cccccc;
    border-radius: 2px;
    color: #666;
    cursor: pointer;
}

.text-back{
    float:right;
    margin-top: -14px;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 80px;
    height: 34px;
    line-height: 17px;
    text-align: center;
    border-radius: 2px;
    color: #63a8eb;
}

.fail{
    color: #f94a05;
}

.dia-blue{
    color: #63a8eb;
}
.dialog-p{
    margin-bottom: 10px;
}
.dialog-p:last-child{
    margin-bottom: 0;
}


.choose-tag-panel {
    max-height: 410px;
    overflow: auto;
}

.choose-tag-title {
    font-size: 14px;
    font-weight: normal;
}

.choose-tag-body {
    font-size: 0;
}

.choose-tag-body .tag-items {
    margin-top: 20px;
    font-size: 14px;
}

.choose-tag-body .tag-items.active {
    background: #63a8eb;
    color: #fff;
    border-color: #63a8eb;
}

.tag-search-group {
    margin-bottom: 0;
}

.tag-search-group label {
    float: left;
    line-height: 34px;
}

.tags-group {
    width: 100%;
    padding-left: 65px;
    margin-left: 10px;
}

.tag-items {
    display: inline-block;
    margin-right: 20px;
    padding: 10px 28px;
    line-height: 1;
    font-size: 14px;
    border: 1px solid #ddd;
    border-radius: 16.5px;
    cursor: pointer;
    position: relative;
}

.tags-group .tag-items {
    margin-bottom: 20px;
}

.tag-items.dashed {
    border-style: dashed;
}

.tag-items:last-of-type {
   margin-right: 0;
}

.close-icon {
    display: none;
    width: 20px;
    height: 20px;
    background: url("../images/tag_colose.png") no-repeat 0 0;
    position: absolute;
    right: 0;
    top: -8px;
}

.tag-items:hover .close-icon{
    display: inline-block;
}


.modal ,
.modal * {
    box-sizing: border-box;
}

.modal {
    display: none;
    position: fixed;
    top: 0px;
    left: 0px;
    width: 100%;
    height: 100%;
    z-index: 1001;
    max-height: 100%;
    overflow-y: auto;
}

.modal-fix {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0,0,0,.6);
}

.modal .modal-dialog {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    overflow: auto;
    padding: 10px 30px;
    text-align: left;
    position: relative;
    left: 0;
    top: 0;
    border-radius: 2px;
    margin: auto;
    z-index: 1002;
}

.modal-content {
    padding: 10px 30px;
    width: 580px;
    margin: auto;
    background: #fff;
    border-radius: 5px;
}

.modal-header {
    position: relative;
    border-bottom: 1px solid #e8e8e8;
    height: 55px;
}

.modal-header .modal-title {
    color: #333;
    line-height: 55px;
    font-size: 25px;
    text-align: center;
    font-weight: normal;
    box-sizing: border-box;
    display: block;
}
.modal-body {
    padding: 30px 0 0 0;
}

.modal-footer {
    font-size: 0;
    text-align: center;
    margin: 30px 0 20px 0px;
}

.modal-footer button {
    margin: 0 20px;
    font-size: 16px;
    width: 130px;
    height: 40px;
    line-height: 40px;
    border-radius: 3px;
    cursor: pointer;
    border: none;
    outline: none;
}

.btn-cancel {
    color: #666;
    background: #ddd;
}

.btn-save {
    margin-left: 20px;
    color: #fff;
    background: #63a8eb;
}
.btn-save:hover{
    background: #548ce5;
}
.modal-close {
    display: inline-block;
    width: 15px;
    height: 15px;
    background: url('../images/close.png') no-repeat;
    position: absolute;
    right: 0;
    top: 20px;
    cursor: pointer;
}



.upActive  {
    position: relative;
}

.upSearch {
    height: 109px;
    overflow: hidden;
}

.toggleUpDown {
    padding: 0 10px;
    position: absolute;
    bottom: 20px;
    right: 0;
    cursor: pointer;
    font-size: 14px;
    color: #63a8eb;
}

.icon-up {
    display: inline-block;
    width: 7px;
    height: 7px;
    border: 1px solid #63a8eb;
    border-color: #63a8eb #63a8eb transparent transparent;
    transform: rotate(-45deg) translateY(5px);
}

.icon-down {
    display: inline-block;
    width: 7px;
    height: 7px;
    border: 1px solid #63a8eb;
    border-color: #63a8eb #63a8eb transparent transparent;
    transform: rotate(135deg) translate(-5px);
}


/******lbsMap start **********************/
.lbsMap {
    text-align: left;
}

.lbs-tips span.text-default {
    color: #63a8eb;
}

.lbs-contain {
    margin-top: 30px;
    display: table;
    width: 100%;
}

.lbs.left,.lbs-right {
    display: table-cell;
    width: 50%;
}

.usually-table {
    margin: 0 0 30px 0;
    width: 100%;
}

.usually-table thead tr th {
    padding: 0 0 10px 0;
    font-weight: normal;
    text-align: left;
    font-size: 14px;
}

.address-items {
    margin-top: 10px;
    width: 145px;
    height: 34px;
    font-size: 14px;
    line-height: 34px;
    border: 1px solid #ddd;
    border-radius: 3px;
}

.address-items span {
    color: #666;!important;
}

.address-items .first {
    display: inline-block;
    width: 110px;
    text-align: center;
    border-right: 1px solid #ddd;
}

.address-items .second {
    display: inline-block;
    width: 33px;
    text-align: center;
}

.mapPoint-wrap {
    padding-right: 20px;
    max-height: 500px;
    overflow: auto;
}

.mapPoint-container {


}

.mapPoint-container .point-map{
    width: 100%;
    height: 200px;
}

.point-map  img {
    width: auto;!important;
    height: auto;!important;
    border-radius: 0;!important;
}

.mapPoint-list {
    padding: 20px 0;
    border-bottom: 1px solid #ddd;
}

.mapPoint-title span {
    color: #666;!important;
    margin-right: 20px;
}

.point-address {
    margin-top: 10px;
}

.point-icon {
    display: inline-block;
    margin-right: 10px;
    width: 15px;
    height: 20px;
    vertical-align: bottom;
    background: url('../images/lbs-icon.png') no-repeat 0  ;
}

.point-map {
    margin-top: 10px;
}

.lbs-addMored {
    text-align: center;
    color: #63a8eb;
    cursor: pointer;
}
/******lbsMap end **********************/
.radio-group {
    display: inline-block;
    font-size: 14px;
}

.radio-group input[type="radio"] {
    margin: 0;
    display: inline-block;
    width: 16px;
    height: 17px;
    position: absolute;
    top: 0;
    left: 0;
    opacity: 0;
    z-index: 3;
    cursor: pointer;
}

.radio-group input[type="radio"]:checked+.radio-btn {
    background-position: 0 1px;
}

.radio-group .radio-input {
    display: inline-block;
    cursor: pointer;
    width: 16px;
    height: 17px;
    padding-left: 22px;
    position: relative;
    top: 3px;
}

.radio-group .radio-btn {
    display: inline-block;
    width: 16px;
    height: 17px;
    background: url('../images/radio.png') no-repeat 0 -17px;
    background-size: 16px;
    position: absolute;
    top: 0;
    left: 0;
}
#jbox{
	top:80px !important;
}
.table thead th{
text-align: center;
}
.tab_chick table tr td, .tab_chick_list .table-list tbody tr td.noSpace{
	height: 20px;
    vertical-align: middle;
    white-space: normal;
    overflow:visible;
    text-align: left;
}
	</style>
</head>
<body>
<div class="data-dia" id="user_info" style="display: flex;">

	<!-- 详细资料 -->
	<div class="data-content">
        <input type="hidden" id="pfOpenId" name="pfOpenId" value="ovaDDju4IFxxD92nJcIr9d_lRYF0">
        <h1 class="dia_header">
            		详细资料
            <a class="close_log" href="javascript:;"></a>
        </h1>
        <div class="tab_chick">
            <table>
                <tbody><tr>
                    <td class="chicked" data-tab="#wechart_info">个人资料</td>
                    <td data-tab="#label" class="">标签</td>
                </tr>
            </tbody></table>
        </div>
        <div class="tab_chick_list">

            <div id="wechart_info" style="display: block;">
                <div class="personal_header">
                		<c:if test="${empty member.headAddress}">
							<img class="img-circle" src="${ctxStatic}/admin/images/introduce/file.png">
						</c:if>
						<c:if test="${not empty member.headAddress}">
							<c:if test="${fns:startsWith(member.headAddress,'http')}">
								<img id="headImg" src="${member.headAddress}">
							</c:if>
							<c:if test="${!fns:startsWith(member.headAddress,'http')}">
								<img id="headImg" src="${fns:getUploadUrl()}${member.headAddress}">
							</c:if>
						</c:if>
		                    <i class="hrf"></i>
		                    <c:if test="${empty member.gmPhoto}">
								<img class="small_head" id="staffHeadImg" src="${ctxStatic}/admin/images/introduce/file.png">
							</c:if>
							<c:if test="${not empty member.gmPhoto}">
								<c:if test="${fns:startsWith(member.gmPhoto,'http')}">
									<img class="small_head" id="staffHeadImg" src="${member.gmPhoto}">
								</c:if>
								<c:if test="${!fns:startsWith(member.gmPhoto,'http')}">
									<img class="small_head" id="staffHeadImg" src="${fns:getUploadUrl()}${member.gmPhoto}">
								</c:if>
							</c:if>
                </div>
                	<h4 class="wechat_title">
                		<c:if test="${empty member.memberNoGm}">
                			当前未绑定导购
                		</c:if>
                		<c:if test="${not empty member.memberNoGm}">
                			最近绑定导购:
                		</c:if>
                		<span id="staffName">${member.memberNameGm}</span>
                	</h4>


                <div class="wechart_info_table">
                    <table class="table-list" style="margin-right:30px">
                        <thead>
                            <tr>
                                <th colspan="2">微信资料</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td width="100">微信昵称</td>
                                <td width="200" id="wxNickName">${member.nickNameWx}</td>
                            </tr>
                            <tr>
                                <td width="100">微信号</td>
                                <td width="200" >${empty member.noWxAlias?member.noWx:member.noWxAlias}</td>
                            </tr>
                            <tr>
                                <td width="100">性&nbsp;&nbsp;别</td>
                                <td width="200" id="wxSex">
                                	<c:forEach items="${genders}" var="item">
																		<c:if test="${item eq member.sex}">${item.name}</c:if>
																	</c:forEach>
                                </td>
                            </tr>
                            <tr>
                                <td>地&nbsp;&nbsp;区</td>
                                <td id="wxProvince">${member.provinceWx}${member.cityWx}</td>
                            </tr>
                            <tr>
                                <td>客户来源</td>
                                <td id="wxPfSource">
                                	<c:forEach items="${memerSources}" var="item">
																		<c:if test="${item eq member.memberSrc}">${item.name}</c:if>
																	</c:forEach>
                                </td>
                            </tr>
                             <tr>
                                <td width="100">客户分组</td>
                                <td width="200" id="birthDay">${member.pmTypeName}</td>
                            </tr>
                            <tr>
                                <td>录入时间</td>
                                <td id="wxSubscribeDate">
                                	<fmt:formatDate value="${member.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                            </tr>
                            <tr>
                                <td width="100">添加方式</td>
                                <td width="200" id="addType">
                                <c:if test="${member.addType eq 1}">导购扫码添加</c:if>
                                <c:if test="${member.addType eq 2}">客户扫码添加</c:if>
                                <c:if test="${member.addType eq 3}">导购手动新增</c:if>
                                <c:if test="${member.addType eq 4}">微信自动导入</c:if>
                                <c:if test="${member.addType eq 5}">手机号添加</c:if>
			                    <c:if test="${member.addType eq 6}">微信号添加</c:if>
			                    <c:if test="${member.addType eq 7}">QQ号添加</c:if>
                                </td>
                            </tr>
                        </tbody>
                    </table>



                    <table class="table-list" style="margin-right:30px;">
                        <thead>
                            <tr>
                                <th colspan="2">
                                    个人信息
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td width="80">姓&nbsp;&nbsp;名</td>
                                <td width="200" id="userName" class='noSpace'>${member.memberName}</td>
                            </tr>
<!--                             <tr> -->
<%--                                 <td width="100">年&nbsp;&nbsp;龄${member.}</td> --%>
<%--                                 <td width="200" id="sex">${member.age}</td> --%>
<!--                             </tr> -->
                            <tr>
                                <td  >生&nbsp;&nbsp;日</td>
                                <td width="200" id="sex" class='noSpace'>
                                	<fmt:formatDate value="${member.birthday}" pattern="yyyy-MM-dd"/>
                                </td>
                            </tr>

                            <tr>
                                <td >职&nbsp;&nbsp;业</td>
                                <td width="200" id="profession" class='noSpace'>${member.jobName}</td>
                            </tr>
                            <tr>
                                <td >手机号</td>
                                <td width="200" id="mobile" class='noSpace'>${member.mobile}</td>
                            </tr>
                             <tr>
                            <td >所属导购</td>
                                <td width="200" id="memberNameGm" class='noSpace'>${member.memberNameGm}</td>
                            </tr>
                            <%--  <tr>
                                <td>导购电话</td>
                                <td width="200" id="mobileGm" class='noSpace'>${member.mobileGm}</td>
                            </tr>
                            <tr>
                                <td>所需产品</td>
                                <td width="200" id="bomName" class='noSpace'>${member.bomName}</td>
                            </tr>
                            <tr>
                                <td >客户特质</td>
                                <td width="200" id="clientSpecial" class='noSpace'>${member.clientSpecial}</td>
                            </tr>
                            <tr>
                                <td>所在楼盘</td>
                                <td id="region" class='noSpace'>${fns:getAreaName(member.provinceCode)}${fns:getAreaName(member.cityCode)}${fns:getAreaName(member.cityAreaCode)}${member.houses}</td>
                            </tr>
                            <tr>
                                <td>装修进度</td>
                                <td id="detailAddress" class='noSpace'>
                                	<c:forEach items="${spruceUpTypes}" var="item">
																		<c:if test="${item eq member.spruceUp}">${item.name}</c:if>
																	</c:forEach>
                                </td>
                            </tr>

                            <tr>
                                <td >对比品牌</td>
                                <td width="200" id="brandCompare" class='noSpace'>${member.brandCompare}</td>
                            </tr> --%>
                             <tr>
                            <td>备注</td>
	                                <td class='noSpace'>${member.remark}</td>
                            </tr>
                        </tbody>
                    </table>

										<table class="table-list">
												<thead>
														<tr>
																<th colspan="2">扩展信息</th>
														</tr>
												</thead>
												<tbody>
													<tr>
															<td width="100">真实姓名</td>
															<td width="200">${pmExt.realName}</td>
													</tr>
														<tr>
																<td width="100">届&nbsp;&nbsp;别</td>
																<td width="200">${pmExt.remark1}</td>
														</tr>
														<%-- <tr>
																<td width="100">手机号</td>
																<td width="200">${pmExt.mobile}</td>
														</tr>												 --%>

												</tbody>
										</table>




                </div>
            </div>
            <!-- <div id="hasstore" style="display: none;">
                <table class="table-list mt30" id="myCollect">
                    <thead>
                        <tr>
                            <th>名称</th>
                            <th>类型</th>
                            <th>收藏时间</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody><tr><td colspan="4" class="no-data"></td></tr></tbody>
                </table>
                <div class="pageNav" id="pageNavMyCollect">
                </div>
            </div> -->

            <div id="label" style="display: none;">
                <h2 class="label_title">系统标签</h2>
                <div class="label_content" id="systemLable">
                	<c:forEach items="${publicLabel}" var="item">
                			<span>${item.LABEL_NAME}</span>
                	</c:forEach>
                </div>

                <h2 class="label_title">商家自定义标签</h2>
                <div class="label_content" id="customLable">
                	<c:forEach items="${merchantLabel}" var="item">
                			<span>${item.labelName}</span>
                	</c:forEach>
                </div>
            </div>




            <!-- <div id="flow_records" style="display: none;">
                <div class="flow-head clearfix">
                    <div class="head-left">
                        <div class="head-items">
                            流量币余额：<span id="balance">0</span>
                        </div>
                        <div class="head-items">
                            累计收入：<span id="incomeTotal">0</span>
                        </div>
                        <div class="head-items">
                            累计支出：<span id="costTotal">0</span>
                        </div>
                    </div>
                    <div class="head-right">
                        <div class="search-group" style="margin-right: 30px">
                            <label for="">类型</label>
                            <select name="flowCurrencyType" id="flowCurrencyType">
                                <option value="">全部</option>
                                <option value="1">收入</option>
                                <option value="2">支出</option>
                            </select>
                        </div>
                        <div class="search-group" style="margin-right: 0">
                            <button class="btn-default" style="margin-right: 0" onclick="queryFlowRecords()">查询</button>
                        </div>
                    </div>
                </div>
                <table id="flowLog" class="table-list">
                    <thead>
                    <tr>
                        <th>时间</th>
                        <th>事项</th>
                        <th>收支</th>
                    </tr>
                    </thead>
                    <tbody><tr><td colspan="3" class="no-data"></td></tr></tbody>
                </table>
                <div id="flowPagination" class="pageNav mt30"></div>
            </div> -->


<!--             <div id="transaction_record" style="display: none;"></div> -->
<!--             <div id="points_record" style="display: none;"></div> -->
<!--             <div id="refill_records" style="display: none;"></div> -->

           <!-- 跟进记录 -->
           <%-- <div id="followup_record" style="display: none;margin-top: 10px">
           	<table  class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>时间</th>
				<th>跟进类型</th>
				<th>跟进内容</th>
				<th>未成单原因</th>
				<th>下一次跟进时间</th>
				<th>下一次任务类型</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${cfList}" var="cfList" varStatus="number">
			<tr>
				<td>
					<fmt:formatDate value="${cfList.followTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:forEach items="${FollowType}" var="FollowType">
					<c:if test="${FollowType eq cfList.followType}">${FollowType.name}</c:if>
					</c:forEach>
				</td>
				<td>
					${cfList.followInfo}
				</td>
				<td>
					${cfList.notDealReason}
				</td>
				<td>
					<fmt:formatDate value="${cfList.nextDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cfList.taskName}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
   </div> --%>

    <!-- 维护记录 -->
          <%--  <div id="maintenance_record" style="display: none;margin-top: 10px">
           	<table  class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>时间</th>
				<th>维护类型</th>
				<th>维护内容</th>
				<th>下一次维护时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${ckList}" var="ckList" varStatus="number">
			<tr>
				<td>
					<fmt:formatDate value="${ckList.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:forEach items="${KeepType}" var="KeepType">
						<c:if test="${KeepType == 'PHONE'}">电话记录</c:if>
						<c:if test="${KeepType == 'WECHAT'}">沟通任务</c:if>
						<c:if test="${KeepType == 'SMS'}">短信记录</c:if>
						<c:if test="${KeepType == 'SHOP'}">终端接待</c:if>
						<c:if test="${KeepType == 'SYSTEM'}">系统</c:if>
					</c:forEach>
				</td>
				<td>
					${ckList.keepContent}
				</td>
				<td>
					<fmt:formatDate value="${ckList.nextDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>

			</tr>
		</c:forEach>
		</tbody>
	</table>

           </div> --%>

    <!-- 成单记录 -->
           <%-- <div id="buy_record" style="display: none;margin-top: 10px">
           	<table  class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>时间</th>
				<th>成单金额</th>
				<th>跟进次数</th>
				<th>送货时间</th>
				<th>购买商品</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${Clientlist}" var="Clientlist" varStatus="number">
				<tr>
					<td>
						<fmt:formatDate value="${Clientlist.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<fmt:formatNumber value="${Clientlist.orderAmount/100}" pattern="#,##0.00"/>元
					</td>
					<td>
						${Clientlist.followNum}
					</td>
					<td>
						<fmt:formatDate value="${Clientlist.deliverTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
					${Clientlist.remark2}
					</td>
					<td>
						${Clientlist.remark}
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

           </div> --%>

    <!-- 暂停跟进记录 -->
           <%-- <div id="giveup_record" style="display: none;margin-top: 10px">
           	<table  class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>暂停跟进时间</th>
				<th>暂停跟进次数</th>
				<th>所需产品</th>
				<th>暂停跟进原因</th>
				<th>跟进次数</th>
				<th>下次回访时间</th>
				<th>审批状态</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${giveuplist}" var="giveuplist" varStatus="number">
			<tr>

				<td>
					<fmt:formatDate value="${giveuplist.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>第${number.count }次暂停跟进</td>
				<td>
					${giveuplist.bomName}
				</td>
				<td>
				     ${giveuplist.resean}
				</td>
				<td>
					${giveuplist.followNum }
				</td>
				<td>
					<fmt:formatDate value="${giveuplist.followDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				<c:forEach items="${CheckResult}" var="CheckResult">
				<c:if test="${CheckResult eq giveuplist.checkResult}">${CheckResult.name}</c:if>
				</c:forEach>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

           </div> --%>

        </div>
    </div>
    </div>
</body>
</html>
