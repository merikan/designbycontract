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
<link rel="stylesheet" type="text/css" href="../../resources/css/main.css">
<script type="text/javascript" language="javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../../resources/js/jquery/1.82/jquery.dataTables.editable.js"></script>
<script type="text/javascript" src="../../resources/js/jquery/1.82/jquery.jeditable.js"></script>
<script type="text/javascript" src="../../resources/js/jquery/1.82/jquery.validate.js"></script>


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
					<c:forEach var="reportCategory" items="${form.categories}">
						<c:url value="/pages/triage/view" var="url">
						<c:param name="id" value="${transaction.id}" />
						</c:url>
					<b>${reportCategory.category.name}. Total : ${reportCategory.total}</b>
					<br /><br/>
					<table id="transactions">
						<thead>
							<tr valign="middle">
								<th width="40px" align="left" valign="bottom">Id</th>
								<th width="60px" align="left" valign="bottom">Date</th>
								<th width="40px" align="left" valign="bottom">Type</th>
								<th width="250px" align="left" valign="bottom">Description</th>
								<th width="120px" align="center" valign="bottom">Value</th>
								<th width="120px" align="left" valign="bottom">Account Name</th>
								<th width="80px" align="left" valign="bottom">Account Number</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="transaction" items="${reportCategory.transactions}">
								<c:url value="/pages/triage/view" var="url">
									<c:param name="id" value="${transaction.id}" />
								</c:url>
								<tr>
									<td><a href="<c:out value="${url}"/>">${transaction.id}</a></td>
									<td><fmt:formatDate pattern="yyyy-MM-dd" value="${transaction.date}" /></td>
									<td>${transaction.type}</td>
									<td style="max-width:250px; width:250px; white-space: nowrap; overflow:hidden;">${transaction.description}</td>
									<td>${transaction.value}</td>									
									<td>${transaction.accountName}</td>
									<td>${transaction.accountNumber}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<br/>
					</c:forEach>
				</td>
			</tr>
		</table>
				

</div>
</html>