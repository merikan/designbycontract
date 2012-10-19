<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<link rel="stylesheet" type="text/css" href="../../resources/css/jTPS.css">
<link rel="stylesheet" type="text/css" href="../../resources/css/main.css">


<style type="text/css">
div.dataTables_info {
	float: left;
}

.dataTables_info {
	float: left;
}
</style>
</head>

<%@ include file="/resources/jsp/test_menu.jsp"%>

<div style="width: 1250px">
		<table width="1250" class="jTPS">
			<tr>
				<td style="width: 640px; max-width: 640px; overflow: hidden;" valign="top">
					<h3 style="float: left; position: relative; top: 0;">My Transactions</h3>
					<br />
					<table id="transactions">
						<thead>
							<tr valign="middle">
								<th width="80px" align="left" valign="bottom">Id</th>
								<th width="80px" align="left" valign="bottom">Date</th>
								<th width="120px" align="left" valign="bottom">Type</th>
								<th width="80px" align="left" valign="bottom">Description</th>
								<th width="80px" align="left" valign="bottom">Value</th>
								<th width="80px" align="left" valign="bottom">Balance</th>
								<th width="80px" align="left" valign="bottom">Account Name</th>
								<th width="80px" align="left" valign="bottom">Account Number</th>
								<th width="80px" align="left" valign="bottom">Category</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="transaction" items="${form.allTransactions}">
								<c:url value="/pages/triage/view" var="url">
									<c:param name="id" value="${transaction.id}" />
								</c:url>
								<tr>
									<td><a href="<c:out value="${url}"/>">${transaction.id}</a></td>
									<td><fmt:formatDate pattern="yyyy-MM-dd" value="${transaction.date}" /></td>
									<td>${transaction.type}</td>
									<td>${transaction.description}</td>
									<td>${transaction.value}</td>
									<td>${transaction.balance}</td>
									<td>${transaction.accountName}</td>
									<td>${transaction.accountNumber}</td>
									<td>${transaction.category.name}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</td>
			</tr>
		</table>
				
		<script type="text/javascript">
			jQuery.extend(jQuery.fn.dataTableExt.oSort, {
				"num-html-pre" : function(a) {
					var x = a.replace(/<.*?>/g, "");
					return parseFloat(x);
				},

				"num-html-asc" : function(a, b) {
					return ((a < b) ? -1 : ((a > b) ? 1 : 0));
				},

				"num-html-desc" : function(a, b) {
					return ((a < b) ? 1 : ((a > b) ? -1 : 0));
				}
			});

			$(document).ready(function() {
				$('#transactions').hide();
				$("#transactions").dataTable({
					"iDisplayLength" : 20,
					"bLengthChange" : false,
					"bFilter" : true,
					"aaSorting" : [ [ 0, "desc" ] ],
					"aoColumns" : [ {"sType" : "num-html"}, 
					                null, 
					                null, 
					                {"bSearchable" : false}, 
					                {"bSearchable" : false},
					                null,
					                null,
					                null,
					                null
					               ]
				});

				$('#transactions').show();
			});
		</script>


	</body>
</div>
</html>