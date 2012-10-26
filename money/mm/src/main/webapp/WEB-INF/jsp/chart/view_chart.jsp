<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<%@ include file="/resources/jsp/imports.jspf"%>

<style type="text/css">

body
{

overflow-y: hidden ;
}


table.display tr.even {
	background-color: #eeffee;
}

tr
{
	background-color : white;
	line-height : 10px;
}

td 
{
	height:10px;
	font: 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
}

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

		chart = new Highcharts.Chart(options);
		var dataset = null;
		$.post('/mm/pages/chart/getChartData', function(data, success) {
			var a = jQuery.parseJSON(data);
			chart.series[0].setData(a);
		});

		var oTable = $("#transactions").dataTable({
			"iDisplayLength" : 15,
			"bLengthChange" : false,
			"bFilter" : true,
			"aaSorting" : [ [ 0, "desc" ] ],
			"aoColumns" : [ null, null, {
				type : "text",
				bRegex : true
			}, null, {
				type : "select",
				bRegex : true
			} ]
		});
		
		("#category_list").dataTable({
			 "sPaginationType": "full_numbers"
		});		
		
		$('#main').show();

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

<div id="main">
	<div id="lhs" style="width: 400px; height: 400px; margin-left: 10px;  float: left;">
		<div id="container"></div>
		<table id="category_list" class="display" width="400px">
			<thead align="left">
				<tr>
					<th >Category</th>
					<th>Total</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="reportCategory" items="${form.categories}">
					<tr>
						<td><a href="/mm/pages/chart/view?id=${reportCategory.category.id}">${reportCategory.category.name}</a></td>
						<td>${reportCategory.total}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>		
	</div>

	<div id="summary" style="width: 800px; height: 800px; margin-left:10px;  margin-right: 10px; float: right;">

		<table  class="display" width="800px" id="transactions">
			<thead>
				<tr valign="middle">
					<th width="60px" align="left" valign="bottom">Date</th>
					<th width="40px" align="left" valign="bottom">Type</th>
					<th width="350px" align="left" valign="bottom">Description</th>
					<th width="120px" align="center" valign="bottom">Value</th>
					<th width="80px" align="left" valign="bottom">Category</th>
				</tr>
			</thead>
			<tbody >
				<c:forEach var="transaction" items="${form.allTransactions}">
					<tr style="vertical-align: center; height: 40px; padding: 20px;">
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${transaction.date}" /></td>
						<td>${transaction.type}</td>
						<td style="max-width: 250px; width: 250px; white-space: nowrap; overflow: hidden;">${transaction.description}</td>
						<td>${transaction.value}</td>
						<td style="vertical-align: top;"><select class="trans" id="${transaction.id}">
								<option value="-1">None</option>
								<c:forEach var="category" items="${form.allCategories}">
									<option value="${category.id}" ${category.id == transaction.category.id ? 'selected' : ''}>${category.name}</option>
								</c:forEach>
						</select></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
</div>

</html>