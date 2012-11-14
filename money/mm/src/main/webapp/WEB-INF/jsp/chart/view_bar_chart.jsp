<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/jsp/taglibs.jspf"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<%@ include file="/resources/jsp/imports.jspf"%>

<style type="text/css">
.simple tbody {
	border-collapse: collapse;
	border-left-style: solid;
	border-left-width: 1px;
	border-right-style: solid;
	border-right-width: 1px;
	border-color: #c9c9c9;
}

tbody {
	border-collapse: separate;
}

.dataTables_wrapper .ui-toolbar {
	height: 20px;
}
</style>


<script type="text/javascript">
	var chart;
	$(document).ready(function() {
		$('#main').hide();
		var options = {
			"series" : [ {
				"name" : "Categories",
				"type" : "column",
				"pointWidth" : 15,
				"color" : "#C6D9E7",
				"borderColor" : "#8BB6D9",
				"shadow" : true,
			} ],
			"title" : {
				"text" : 'Spending By Category'
			},
			"legend" : {
				"layout" : "vertical",
				"style" : {
					"left" : "auto",
					"bottom" : "auto",
					"right" : "auto",
					"top" : "auto"
				}
			},
			"chart" : {
				"renderTo" : "container"
			},
			"credits" : {
				"enabled" : false
			},
			 "yAxis": {
	                "min": 0,
	                "title" : {
	                    "text": 'Cost £'
	                }
	            }
		};

		chart = new Highcharts.Chart(options);
		var dataset = null;
		$.post('/mm/pages/chart/getBarChartData', function(data, success) {
			chart.series[0].setData(data);		
		});

		
		$.post('/mm/pages/chart/getBarChartCategories', function(data, success) {
			chart.xAxis[0].setCategories(data);
 		});
		
		
		
		var oTable = $("#transactions").dataTable({
			"iDisplayLength" : 15,
			"bJQueryUI" : true,
			"bLengthChange" : false,
			"bFilter" : false
		});

		var oTable = $("#categoryList").dataTable({
			"iDisplayLength" : 15,
			"bJQueryUI" : true,
			"bLengthChange" : false,
			"bFilter" : false
		});

		$('#main').show();

		//$('#bigchart').show();

	});

	function updateTransactions() {
		alert("sdfsdfsdf");
		/* $.post('/mm/pages/chart/updateTransactions', function(data, success) {
			var a = jQuery.parseJSON(data);
			chart.series[0].setData(a);
		}); */
		return false;

	}
</script>

</head>

<body>


	<div style="margin-bottom: 20px; margin-top: 5px; width: 100%; height:  400px; float: left; border-style: solid; border-width: 1px;"
		id="container"></div>



</body>