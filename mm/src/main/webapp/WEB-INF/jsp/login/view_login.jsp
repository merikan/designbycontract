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
<link type="text/css" href="../../resources/css/redmond/redmond.css" rel="Stylesheet" />




</head>
<body>
	<div class="form">

		<script type="text/javascript">
			$(document).ready(function() {
				$("input", "#buttons").button();
			});
		</script>

	<%@ include file="/resources/jsp/test_menu.jsp"%>
		
		<form:form method="post" action="/mm/pages/login/submit" modelAttribute="form">
			<div style="margin-left: auto ;  margin-right: auto ; padding-top:50px; padding-bottom:50px; width:600px; vertical-align: center" id="form">
				<div style="width:100%; height:30px; font-size:15px;  color:red; float:left"> <c:out value="${form.message}"/></div>
				<h3 style="font-size: 20px; font-weight:bold;">Login</h3>
				<table>
					<tr>
						<td>SID</td>
						<td><form:input  accesskey="l" id="username" path="username" /></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><form:password accesskey="p" id="password" path="password" /></td>
					</tr>
					<tr>
						<td colspan="2" align="right">
							<div id="buttons" style="float: right; postion: relative; margin-left: 10px; margin-right: 10px; margin-bottom: 10px;">
								<input accesskey="" style="margin-left: 20px;" type="submit" value="Login">
							</div>
						</td>
				</table>
			</div>
		</form:form>
	</div>
</body>

</html>
