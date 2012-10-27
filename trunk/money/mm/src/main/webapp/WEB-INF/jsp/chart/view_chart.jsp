<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<%@ include file="/resources/jsp/imports.jspf"%>

<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
<style type="text/css">


.dataTables_wrapper .ui-toolbar {
	height:20px;
}



</style>


<script type="text/javascript">
	var chart;
	$(document).ready(function() {
		//$('#main').hide();
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
				"text" : null
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
			}
		};



		var bigoptions = {
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
					"text" : null
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
					"renderTo" : "bigchart"
				},
				"credits" : {
					"enabled" : false
				}
			};

		
		chart = new Highcharts.Chart(options);
		bigchart = new Highcharts.Chart(bigoptions);
		var dataset = null;
		$.post('/mm/pages/chart/getChartData', function(data, success) {
			var a = jQuery.parseJSON(data);
			chart.series[0].setData(a);
			bigchart.series[0].setData(a);
			
		});

		var oTable = $("#transactions").dataTable({
			"iDisplayLength" : 15,
			"bJQueryUI" : true,
			"bLengthChange" : false,
			"bFilter" : false
		});

		$('#showchart').click(function() 
				{
			$( "#bigchart" ).dialog	({
			            autoOpen: true,
			            height: 900,
			            width: 900,
			            modal: true
			        });
		});			
		$('#bigchart').hide();
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

<div id="lhs"
	style="width: 400px; height: 400px; float: left;">
	<div style="width: 480px; height: 400px;  float: left;" id="container">
		
	</div>
	<div style="top : -50px;float:left; width:50px"><a style="float:left; width:50px" id="showchart" href="javascript:void(0)">Enlarge</a></div>
	<table id="category_list" class="display" width="400px">
	
		<thead align="left">
			<tr>
				<th>Category</th>
				<th>Total</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="reportCategory" items="${form.categories}">
				<tr>
					<td><a
						href="/mm/pages/chart/view?id=${reportCategory.category.id}">${reportCategory.category.name}</a></td>
					<td>${reportCategory.total}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<div id="summary"
	style="width: 700px; height: 700px; margin-right: 10px; float: right;">
	<table class="display" width="700px" id="transactions">
		<thead>
			<tr valign="middle">
				<th width="80px" align="left" valign="bottom">Date</th>
				<th width="30px" align="left" valign="bottom">Type</th>
				<th width="350px" align="left" valign="bottom">Description</th>
				<th width="120px" align="center" valign="bottom">Value</th>
				<th width="80px" align="left" valign="bottom">Category</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="transaction" items="${form.allTransactions}">
				<tr style="vertical-align: center; height: 40px; padding: 20px;">
					<td><fmt:formatDate pattern="yyyy-MM-dd"
							value="${transaction.date}" /></td>
					<td>${transaction.type}</td>
					<td
						style="max-width: 250px; width: 250px; white-space: nowrap; overflow: hidden;">${transaction.description}</td>
					<td>${transaction.value}</td>
					<td style="vertical-align: top;"><select class="trans"
						id="${transaction.id}">
							<option value="-1">None</option>
							<c:forEach var="category" items="${form.allCategories}">
								<option value="${category.id}"
									${category.id == transaction.category.id ? 'selected' : ''}>${category.name}</option>
							</c:forEach>
					</select></td>

				</tr>
			</c:forEach>
		</tbody>
	</table>


</div>

<div style="width:800px; height:800px" id="p"/></div>
<div id="bigchart" title="Categories Chart"  style="width:800px; height:800px"></div>
	

</body>