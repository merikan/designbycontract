<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" href="../../resources/css/graytable-img.css" type="text/css" />
<link type="text/css" href="../../resources/css/redmond/redmond.css" rel="Stylesheet" />

<link rel="stylesheet" type="text/css" href="../../resources/css/jTPS.css">

<link type="text/css" href="../../resources/framework/bootstrap/css/bootstrap.css" rel="Stylesheet" />
<script type="text/javascript" src="../../resources/framework/bootstrap/js/bootstrap.js"></script>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.8.2.js"></script>
    <script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>

<style type="text/css">
#nav_menu ul{
margin-left : 0px;
}
</style>



</head>
<body>


	<script type="text/javascript">
		$(document).ready(function() {
			$("#report").button();
		});
		
		
		$(function() {
	        $( "#dialog-message" ).dialog({
	            modal: true,
	            buttons: {
	                Ok: function() {
	                    $( this ).dialog( "close" );
	                }
	            }
	        });
	    });
		
	</script>
	
	<div id="dialog-message" title="Download complete">
    <p>
        <span class="ui-icon ui-icon-circle-check" style="float: left; margin: 0 7px 50px 0;"></span>
        Your files have downloaded successfully into the My Downloads folder.
    </p>
    <p>
        Currently using <b>36% of your storage space</b>.
    </p>
</div>

	<div style="width: 800px; margin-left: auto; margin-right: auto;">

		<c:if test="${form.message != null}">
			<div class="alert alert-success">
				<button type="button" class="close" data-dismiss="alert">×</button>
				<c:out value="${form.message}" />
			</div>
		</c:if>


		<form:form method="post" action="upload" modelAttribute="form" enctype="multipart/form-data">

			<table class="form" style="width: 800px; margin-left: auto; margin-right: auto;">
				<tr>
					<th colspan="4">Upload Transactions</th>
				</tr>
				<tr>
					<td width="50px;" align="left">File</td>
					<td width="50px;" align="left"><form:input path="file" type="file" /></td>
					<td width="50px;" align="left"><button class="btn btn-inverse" type="submit">Upload</button></td>
					<td></td>
				</tr>
				<tr>
					<td>
					<td colspan="3">Upload your transactions from a bank csv file.
					</th>
				</tr>

			</table>
		</form:form>
	</div>
	<div id="pop">
	daslkdsfklklfds
	fdsdfsklfdksl
	fdslfl;dsl;dfs
	</div>
</body>
</html>