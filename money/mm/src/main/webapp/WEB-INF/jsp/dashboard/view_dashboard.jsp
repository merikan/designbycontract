<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<%@ include file="/resources/jsp/taglibs.jspf"%>
<style type="text/css">

.simple tbody {
	border-collapse: collapse;
	border-left-style: solid;
	border-left-width: 1px;
	border-right-style: solid;
	border-right-width: 1px;
	border-color : #c9c9c9;
	
	
}
tbody
{
border-collapse: separate;
}

.dataTables_wrapper .ui-toolbar {
	height: 20px;
}
</style>

<%@ include file="/resources/jsp/imports.jspf"%>

<script type="text/javascript">
	$(document).ready(function() {

		var oTable = $("#accounts").dataTable({
			"iDisplayLength" : 10,
			"bJQueryUI" : true,
			"bLengthChange" : false,
			"bFilter" : false,
			//"sDom": '<"toolbar">',
			"aaSorting" : [ [ 0, "desc" ] ],
			//"sDom": 'R<C><"#buttonPlaceholder">H<"clear"><"ui-toolbar ui-widget-header ui-corner-tl ui-corner-tr ui-helper-clearfix"lfr>t<"ui-toolbar ui-widget-header ui-corner-bl ui-corner-br ui-helper-clearfix"ip>',
			"aoColumns" : [ { "sType" : "num-html"}, 
			                  null, 
			                  null, 
			                  {	type : "text",bRegex : true	}, 
			                  null, 
			                  null ]
		});

	});
</script>



</head>

<div style="float:left">
	<h3>My Accounts</h3>
	<table style="vertical-align:left; width:1000px" class="display" id="accounts">
		<thead>
			<tr>
				<th width="100px" align="left">Account Name</th>
				<th width="100px" align="left">Account Number</th>
				<th width="200px" align="left">Account Description</th>
				<th width="80px" align="left"></th>
				<th width="80px" align="left"></th>
				<th width="80px" align="left"></th>
			</tr>
		</thead>
		<tbody style="vertical-align: center; height: 40px;">
			<c:forEach var="account" items="${form.accounts}">
				<c:url value="/pages/chart/viewByAccount" var="chartUrl">
					<c:param name="accountId" value="${account.id}" />
				</c:url>
				<c:url value="/pages/transaction/viewById" var="transactionsUrl">
					<c:param name="accountId" value="${account.id}" />
				</c:url>
				<c:url value="/pages/budget/view" var="budgetUrl">
					<c:param name="accountId" value="${account.id}" />
				</c:url>
				<tr style="vertical-align: center; height: 40px; padding: 20px;">
					<td>${account.name}</td>
					<td>${account.name}</td>
					<td>${account.description}</td>
					<td><a href="<c:out value="${transactionsUrl}"/>">View Transactions</a></td>
					<td><a href="<c:out value="${budgetUrl}"/>">View Budget</a></td>
					<td><a href="<c:out value="${chartUrl}"/>">View Categories</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</html>