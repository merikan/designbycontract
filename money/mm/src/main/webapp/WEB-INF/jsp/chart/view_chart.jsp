<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<link rel="stylesheet" type="text/css"
	href="../../resources/css/jTPS.css">
<link rel="stylesheet" type="text/css"
	href="../../resources/css/main.css">
<link rel="stylesheet" type="text/css"
	href="../../resources/css/main.css">
<script type="text/javascript" language="javascript"
	src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="../../resources/js/jquery/1.82/jquery.dataTables.editable.js"></script>
<script type="text/javascript"
	src="../../resources/js/jquery/1.82/jquery.jeditable.js"></script>
<script type="text/javascript"
	src="../../resources/js/jquery/1.82/jquery.validate.js"></script>

<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script>

<script type="text/javascript">



var chart;
$(document).ready(function () {
    
	var options = 
{
   "series":[
      {
         "name":"Values",
         "type":"pie",
         "sliced":true,
         "pointWidth":15,
         "color":"#C6D9E7",
         "borderColor":"#8BB6D9",
         "shadow":true,
      }
   ],
   "title":{
      "text":null
   },
   "legend":{
      "layout":"vertical",
      "style":{
         "left":"auto",
         "bottom":"auto",
         "right":"auto",
         "top":"auto"
      }
   },
   "chart":{
      "renderTo":"container"
   },
   "credits":{
      "enabled":false
   }
};
	

	
    chart = new Highcharts.Chart(options);
    var dataset = null;
    $.post('/mm/pages/dashboard/getChartData', function(data,success) {
    	  alert('Data' + data);
    	  alert('Success' + success);
    	    var a = jQuery.parseJSON(data);
    	    alert('A' + a);
    	    chart.series[0].setData(a);

    	});
  
    
    
});

</script>


<style type="text/css">
div.dataTables_info {
	float: left;
}

.dataTables_info {
	float: left;
}
</style>
</head>




	<div id="container"
		style="min-width: 400px; height: 400px; margin: 0 auto"></div>





</html>