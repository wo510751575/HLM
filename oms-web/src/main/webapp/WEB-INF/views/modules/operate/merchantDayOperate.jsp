<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>店员列表</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
	.title{
	padding: 10px 30px;
    width: 100%;
    min-height: auto;
    background: #fff;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    font-size: large;
    font-style: inherit;
    text-align: center;
	}
	.container{
	padding: 10px 30px;
    width: 100%;
    min-height: auto;
    background: #fff;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
	}
	.paragraph{
	padding: 10px 30px;
    width: 100%;
    min-height: auto;
    background: #fff;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    text-align: center;
	}
	</style>
</head>
<body>

<div class="title" style="color: red;">
	${data.merchantName}运营数据日报表(${data.queryDate})
</div>
<div class="paragraph">
	第一部分 销售情况
</div>
<div class="container">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<tr>
			    <td width="70px">
					终端总数:
				</td>
				<td width="300px" align="left">
					${data.shopNum}
				</td>
				<td width="70px">
					店员总数:
				</td>
				<td width="300px" align="left">
					${data.guidNum}
				</td>
				<td width="70px">
					今日销售额
				</td>
				<td width="300px" align="left">
					${data.daySale}元
				</td>
			</tr>
	</table>
	</div>

<div class="paragraph">
	第二部分 客户情况
</div>
<div class="container">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>分店名称</th>
				<th>新增客户总数</th>
				<th>新增意向客户</th>
				<th>新增非意向客户</th>
				<th>新增成单客户</th>
				<th>新增暂停客户</th>
				<th>新增未分组客户</th>
				<th>成单率</th>
				<th>销售额</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${data.shopData}" var="shopData"> 
			<tr>
			    <td>
					${shopData.value.shopName}
				</td>
				<td>
					${shopData.value.numPm}
				</td>
				<td>
					${shopData.value.numPmIntention}
				</td>
				<td>
					${shopData.value.numPmNoIntention}
				</td>
				<td>
					${shopData.value.numOrderPm}
				</td>
				<td>
					${shopData.value.numPmAbandon}
				</td>
				<td>
					${shopData.value.numPmUngroup}
				</td>
				<td>				
					${shopData.value.orderRatio}%
				</td>
				<td>				
					${shopData.value.sale}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	
	<div class="paragraph">
	第二部分 工作完成情况
	</div>
	<div class="container">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>分店名称</th>
				<th>昨日跟进客户数</th>
				<th>昨日最高跟进客户次数</th>
				<th>昨日业务任务产生量</th>
				<th>昨日业务任务完成量</th>
				<th>昨日社交任务产生量</th>
				<th>昨日社交任务完成量</th>
				<th>昨日邀约任务完成量</th>
				<th>昨日应邀客户量</th>
				<th>昨日到店客户量</th>
				<th>昨日微信聊天次数</th>
				<th>昨日通话次数</th>
				<th>昨日通话时长</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${data.shopData}" var="shopData"> 
			<tr>
			    <td>
					${shopData.value.shopName}
				</td>
				<td>
					${shopData.value.numPmCf}
				</td>
				<td>
					${shopData.value.maxPmCfNum}
				</td>
				<td>
					${shopData.value.numComTaskProduce}
				</td>
				<td>
					${shopData.value.numComTaskComplete}
				</td>
				<td>
					${shopData.value.numSocialTaskProduce}
				</td>
				<td>
					${shopData.value.numSocialTaskComplete}
				</td>
				<td>				
					${shopData.value.numInviteTaskComplete}
				</td>
				<td>				
					${shopData.value.isInvitePmNum}
				</td>
				<td>				
					${shopData.value.clientExpPmNum}
				</td>
				<td>				
					${shopData.value.numWeChat}
				</td>
				<td>				
					${shopData.value.numCall}
				</td>
				<td>				
					${shopData.value.callTime}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
</body>
</html>