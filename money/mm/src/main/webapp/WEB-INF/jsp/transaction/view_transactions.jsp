<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<%@ include file="/resources/jsp/taglibs.jspf"%>
<style type="text/css">


</style>



<%@ include file="/resources/jsp/imports.jspf"%>

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

		var oTable = $("#transactions").dataTable({
			"iDisplayLength" : 10,
			"bJQueryUI" : true,
			"bLengthChange" : false,
			"bFilter" : true,
			//"sDom": '<"toolbar">',
			"aaSorting" : [ [ 0, "desc" ] ],
			//"sDom": 'R<C><"#buttonPlaceholder">H<"clear"><"ui-toolbar ui-widget-header ui-corner-tl ui-corner-tr ui-helper-clearfix"lfr>t<"ui-toolbar ui-widget-header ui-corner-bl ui-corner-br ui-helper-clearfix"ip>',
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
		    
		   $(".fg-toolbar").first().append($("#jd"));   
		   
		   /* $(".th").css("border-style","solid");
		   $(".th").css("border-width","2px");
		    */ 
		$('#transactions').show();
		
	
		        	 					
	});
	
	function fnGetSelected( oTable )
	{
	    return oTable.$('tr.row_selected');
	}
</script>



</head>

<div>
		<table id="main" width="100%">
			<tr>
				<td style="width: 640px; max-width: 640px; overflow: hidden;" valign="top">
					<h3 style="font-size:16px; margin-top : 0px; margin-bottom : 0px; float: left;">My Transactions</h3>
					
					<div id="mytable">
					
					<table class="simple display" id="transactions">
						<thead >
							<tr>
								<th style="border-left:solid; border-width : 1px; border-left-color: gray" width="40px" align="left" valign="bottom">Id</th>
								<th width="60px" align="left" valign="bottom">Date</th>
								<th width="40px" align="left" valign="bottom">Type</th>
								<th width="250px" align="left" valign="bottom">Description</th>
								<th width="120px" align="center" valign="bottom">Value</th>
								<th width="120px" align="left" valign="bottom">Account Name</th>
								<th width="80px" align="left" valign="bottom">Account Number</th>
								<th style="border-right:solid; border-width : 1px; border-right-color: gray" width="80px" align="left" valign="bottom">Category</th>
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
									<td align="right">${transaction.value}</td>									
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
					</div>
				</td>
			</tr>
		</table>

</div>
<div id="jd" style="float:right; width:300px; vertical-align:middle; padding-right:100px;">
Category : 
<select >
	<c:forEach var="category" items="${form.allCategories}">
		<option value="${category.id}" >${category.name}</option>
	</c:forEach>
</select>
<a id="update" href="javascript:void(0)">Update Selected</a>
</div>
				
</div>
</html>