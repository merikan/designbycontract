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

</head>
<body>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#report").button();
		});
	</script>

	<div style="width: 800px; margin-left: auto; margin-right: auto;">

		<div style="height: 30px; font-size: 15px; color: green; ">
		</div>

		<form:form method="post" action="upload" modelAttribute="form" enctype="multipart/form-data">

			<table class="form" style="width: 800px; margin-left: auto; margin-right: auto;">
				<tr>
					<th colspan="2">Upload Transactions</th>
				</tr>
				<tr>
					<td>File</td>
					<td><form:input path="file" type="file"/></td>
				</tr>
				<tr>
					<td>
					<input accesskey="" style="margin-left: 20px;" type="submit" value="Submit">
				</td>
				</tr>											
			</table>
		</form:form>
	</div>
</body>
</html>