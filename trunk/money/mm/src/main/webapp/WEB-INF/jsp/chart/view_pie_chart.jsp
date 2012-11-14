<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/jsp/taglibs.jspf"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<%@ include file="/resources/jsp/imports.jspf"%>


<script type="text/javascript">
	var chart;
	$(document).ready(function() {
		$('#main').hide();
		var options = {
			"series" : [ {
				"name" : "Values",
				"type" : "pie",
				"sliced" : true,
				"pointWidth" : 15,
				"color" : "#C6D9E7",
				"borderColor" : "#8BB6D9",
				"shadow" : true,
			} ],
			"title" : {
				"text" : "Spending By Category"
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
				"renderTo" : "container",
			},
			"credits" : {
				"enabled" : false
			}
		};


		chart = new Highcharts.Chart(options);

		var dataset = null;
		$.post('/mm/pages/chart/getChartData', function(data, success) {
			var a = jQuery.parseJSON(data);
			chart.series[0].setData(a);
			bigchart.series[0].setData(a);

		});
		
		$('#main').show();


	});
</script>

</head>

<body>

	<div id="container" style="border-style: solid; border-width: 1px;  margin-left: auto ;  margin-right: auto; width :600px; height : 600px; "></div>

</body>