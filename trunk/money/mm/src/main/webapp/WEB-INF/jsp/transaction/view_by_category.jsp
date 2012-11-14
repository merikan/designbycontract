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
	$(document).ready(function() {

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

	});
</script>

</head>

<body>
<div>
	<div id="lhs" style="margin-left: 20px; width: 400px; height: 100%; float: left;">
		<table id="categoryList" class="simple display" width="400px">
			<thead align="left">
				<tr>
					<th>Category</th>
					<th>Total</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="reportCategory" items="${form.categories}">
					<tr>
						<td><a href="/mm/pages/transaction/viewByCategoryId?id=${reportCategory.category.id}">${reportCategory.category.name}</a></td>
						<td>${reportCategory.total}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div id="rhs" style="width: 700px; height: 100%; float: right;">
		<table style="width: 700px;" class="display simple" id="transactions">
			<thead>
				<tr valign="middle">
					<th valign="bottom">Date</th>
					<th width="30px" align="left" valign="bottom">Type</th>
					<th width="250px" align="left" valign="bottom">Description</th>
					<th width="120px" align="center" valign="bottom">Value</th>
					<th width="150px">Category</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="transaction" items="${form.allTransactions}">
					<tr style="width: 700px; height: 40px;">
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${transaction.date}" /></td>
						<td>${transaction.type}</td>
						<td style="width: 250px; white-space: nowrap; overflow: hidden;">${transaction.description}</td>
						<td>${transaction.value}</td>
						<td style="vertical-align: top;"><select style="width: 150px;" class="trans" id="${transaction.id}">
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
</body>