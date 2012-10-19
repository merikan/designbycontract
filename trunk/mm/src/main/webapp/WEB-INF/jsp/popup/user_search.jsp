<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="http://fonts.googleapis.com/css?family=Ubuntu+Condensed" rel="stylesheet" type="text/css" />
<link href="http://fonts.googleapis.com/css?family=Kreon" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" href="../../resources/css/style.css" />
<link rel="stylesheet" href="../../resources/css/menu_styles.css" type="text/css" />
<link rel="stylesheet" href="../../resources/css/table.css" type="text/css" />
<script type="text/javascript" src="../../resources/js/popup/popup.js"></script>
<link rel="stylesheet" href="../../resources/css/table.css" type="text/css" />
<link type="text/css" href="../../resources/css/redmond/redmond.css" rel="Stylesheet"/>

</head>
<body >
	<div class="form">
	
		<script type="text/javascript">
			$(document).ready(function() {
				$( "#search_button" ).button();
				$( "input", "#buttons" ).button();
			});
				
		</script>
	
		<form:form method="post" action="/mm/pages/user/popupsearch"  modelAttribute="form"> 
		<form:hidden id="fieldId" path="fieldId"/>
		<div class="form">
			<table>
				<tr style="vertical-align: left">
					<th width="100px" align="left">Business Unit</th>
					<th> 
						<form:select itemLabel="code" itemValue="id" path="businessUnitId" items="${form.businessUnits}"/>
						<input style="font-size: 13px;" id="search_button" type="submit" value="Search">
					</th>
				</tr>				
			</table>
		</div>
		<div id="show-users">
			<table>
				<tr>
					<th align="left">Id</th>
					<th align="left">SID</th>
					<th align="left">First Name</th>
					<th align="left">Last Name</th>
					<th align="left">Email</th>
					<th align="left">View</th>
				</tr>
				<c:forEach var="user" items="${form.allUsers}" varStatus="i">
					<tr class="${((i.count-1) % 2) == 0 ? 'even' : 'odd'}">
						<td>${user.id}</td>
						<td>${user.sid}</td>
						<td>${user.firstName}</td>
						<td>${user.lastName}</td>
						<td>${user.email}</td>
						<td><a href="<c:out value="javascript:returnValues('${user.id}','${user.displayName}','${form.fieldId}')"/>">Select</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="form" id="buttons">			
			<input type="button" onclick="window.parent.closeIframe(null);" value="Close">			
		</div>

		</form:form>
	</div>
</body>

</html>
