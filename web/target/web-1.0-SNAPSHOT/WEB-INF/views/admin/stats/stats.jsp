<%--
  Created by IntelliJ IDEA.
  User: Mr.YiQuan
  Date: 2020-06-08
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../commons/header.jsp"%>
<%@include file="/commons/global.jsp" %>
<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <div id="wu-toolbar">
        <div class="wu-toolbar-button">

            <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="statsByDay()" plain="true">按日统计</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-bullet-edit" onclick="statsByMonth()" plain="true">按月统计</a>

        </div>
    </div>
    <!-- End of toolbar -->
<div class="easyui-accordion" style="width:960px;height:600px;">
	<div title="营业统计分析" iconCls="icon-chart-curve" style="overflow:auto;padding:10px;">
		 <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    	<div id="charts-div" style="width: 800px;height:500px;"></div>
	</div>
</div>
</div>
<!-- Begin of easyui-dialog -->

<script type="text/javascript" src="${staticPath}/static/admin/adm_hotelplus/echarts/js/echarts.common.min.js"></script>
<!-- End of easyui-dialog -->
<script type="text/javascript">
$(document).ready(function(){
	statsByDay();
});
function statsByMonth(){
	getData('month');	
}
function statsByDay(){
	getData('day');	
}
function getData(type){
	$.ajax({
		url:'${staticPath}/stats/get_stats',
		type:'post',
		dataType:'json',
		data:{type:type},
		success:function(data){
			if(data.type == 'success'){
				var title = '营业统计分析';
				if(type == 'month'){
					title = title + '(按月统计)';
				}else{
					title = title + '(按日统计)';
				}
				var option = {
					    title: {
					        text: title
					    },
					    xAxis: {
					        type: 'category',
					        boundaryGap: false,
					        data: data.content.keyList
					    },
					    tooltip : {
					        trigger: 'axis',
					        axisPointer: {
					            type: 'cross',
					            label: {
					                backgroundColor: '#6a7985'
					            }
					        }
				    	},
					    yAxis: {
					        type: 'value'
					    },
					    series: [{
					        data: data.content.valueList,
					        type: 'line',
					        areaStyle: {}
					    }]
				};
				myChart.setOption(option);
			}else{
				alert(data.msg)
			}
		}
	});
}
// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('charts-div'));

// 指定图表的配置项和数据

// 使用刚指定的配置项和数据显示图表。

	
	
	
	
	
</script>