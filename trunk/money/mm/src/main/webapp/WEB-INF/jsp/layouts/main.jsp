<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><decorator:title default="mm" /></title>
<link rel="stylesheet" href="<c:url value="/resources/css/main.css" />" type="text/css" media="screen, projection, print" />

<!--[if lt IE 8]>
	        <link rel="stylesheet" href="<c:url value="/resources/css/ie.css" />" type="text/css" media="screen, projection" />
	<![endif]-->

<link rel="stylesheet" href="../../resources/css/menu_styles.css" type="text/css" />
<link type="text/css" href="../../resources/css/redmond/redmond.css"	rel="Stylesheet" />
<link  href="../../resources/css/jTPS.css" type="text/css" rel="Stylesheet"	/>

<script language="JavaScript" type="text/javascript" src="../../resources/js/jTPS.js"></script>
<script language="JavaScript" type="text/javascript" src="../../resources/js/jquery/1.82/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="../../resources/js/jquery/1.82/jquery-ui-1.8.3.min.js"></script>
<script type="text/javascript" src="../../resources/js/jquery/1.82/jquery.datatables-1.9.4.js"></script>
<decorator:head />
</head>
<body>

	<div style="height:70px; width:1250px; background-color: black;" >
		<a style="float:right" href="/mm/pages/dashboard/view"></a>
	</div>

	<decorator:body />

	<div class="footer" align="center">
		Version 1.0 | Copyright © 2012 Design By Contract. All rights reserved.		
	</div>
	<div id="spinner" class="spinner" style="display: none;"></div>

</body>

</html>