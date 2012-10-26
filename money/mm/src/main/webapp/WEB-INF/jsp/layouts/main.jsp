<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><decorator:title default="mm" /></title>

<decorator:head />
</head>
<body >

	<div style="height:70px; width:100%; background-color: black;" >
		<a style="float:right" href="/mm/pages/dashboard/view"></a>
	</div>
	
	<%@ include file="/resources/jsp/test_menu.jsp"%>
	
	<decorator:body />

	<div id="footer" class="footer" align="center">
		Version 1.0 | Copyright © 2012 Design By Contract. All rights reserved.		
	</div>
	<div id="spinner" class="spinner" style="background-color:black; display: none;"></div>

</body>

</html>