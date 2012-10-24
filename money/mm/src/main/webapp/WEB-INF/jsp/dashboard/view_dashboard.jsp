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
<script type="text/javascript" language="javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>


<style type="text/css">

#nav {
	color: black;
	margin: auto;
	text-align: center;
}

.footer {
	background: #54301A;
	background-color : black;
	color: #ffffff;
	clear: both;
	font-size: 0.8em;
	margin-top: 1.5em;
	padding: 1em;
	min-height: 1em;
}

#nav ul li:hover>ul {
	background-color : #4F2E1A;
	display: block;
}

.row_selected
{
	background-color: #cfcfcf;
}

#bollocks tr
{
	color:black;
	background-color: black;
}

#odd
{
	color:black;
	background-color: black;
}

#row_selected

even bollocks
{
	color:black;
	background-color: black;
}


.bollocks tr
{
	color:black;
	background-color: black;
}

div.dataTables_info {
	float: left;
}

.dataTables_info {
	float: left;
}

tr
{
	background-color : white;
}

td, th {
border : 0;
	border-color : #cfcfcf;

}
</style>
</head>

<div style="width: 1250px">
		<table id="main" width="1250" class="jTPS">
			<tr>
				<td style="width: 640px; max-width: 640px; overflow: hidden;" valign="top">
					<h3 style="font-size:16px; float: left; position: relative; width:100%; padding-top : 20px; padding-bottom : 20px;">My Transactions</h3>
					
					<div style="float:right;">
					Category : 
					<select id="jd" >
						<c:forEach var="category" items="${form.allCategories}">
							<option value="${category.id}" >${category.name}</option>
						</c:forEach>
					</select>
					<a id="update" href="javascript:void(0)">Update Selected</a>
					</div>
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
								<th width="80px" align="left" valign="bottom">Category</th>
							</tr>
						</thead>
						<tbody style="vertical-align:center; height:40px;">
							<c:forEach var="transaction" items="${form.allTransactions}">
								<c:url value="/pages/triage/view" var="url">
									<c:param name="id" value="${transaction.id}" />
								</c:url>
								<tr style="vertical-align:center; height:40px; padding : 20px;">
									<td><a href="<c:out value="${url}"/>">${transaction.id}</a></td>
									<td><fmt:formatDate pattern="yyyy-MM-dd" value="${transaction.date}" /></td>
									<td>${transaction.type}</td>
									<td style="max-width:250px; width:250px; white-space: nowrap; overflow:hidden;">${transaction.description}</td>
									<td>${transaction.value}</td>									
									<td>${transaction.accountName}</td>
									<td>${transaction.accountNumber}</td>
									<td style="vertical-align:top;">
										<select class="trans" id="${transaction.id}" >
											<option value="-1" >None</option>
											<c:forEach var="category" items="${form.allCategories}">
												<option value="${category.id}" ${category.id == transaction.category.id ? 'selected' : ''}>${category.name}</option>
											</c:forEach>
										</select>
									</td>

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
				$('.trans').change(function() 
						{
							$.post('/mm/pages/dashboard/updateTransaction?transactionId=' + $(this).attr('id') + "&categoryId=" + $(this).attr('value'));
						});
				

				$('#update').click(function() 
						{
					var category = $('#jd').val();
					var anSelected = fnGetSelected( oTable );
					var $row = $(anSelected);
					var $sel = $row.find('select').val(category);
					$row.find('select').trigger("change"); 
					anSelected.trigger("click");
						});				
				
				$('#main').hide();
				var oTable = $("#transactions").dataTable({
					"iDisplayLength" : 15,
					"bLengthChange" : false,
					"bFilter" : true,
					"aaSorting" : [ [ 0, "desc" ] ],
					"aoColumns" : [ {"sType" : "num-html"}, 
					                null, 
					                null, 
					                { type: "text", bRegex:true }, 
					                null,
					                null,
					                null,
					                { type: "select", bRegex:true }
					               ]					               
				});
				    /* Add/remove class to a row when clicked on */
				   $('#transactions tr').click( function() {
				        $(this).toggleClass('row_selected');
			        
				    } );

				$('#main').show();
				        	 					
			});
			
			function fnGetSelected( oTable )
			{
			    return oTable.$('tr.row_selected');
			}
		</script>
</div>
</html>