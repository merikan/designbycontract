function openDialog(fieldId, businessUnitId) {
	
	var params = "?businessUnit=" + businessUnitId;
	params += "&fieldId=" + fieldId;
	$('#userSearch').attr("src", "/icon/pages/user/popup" + params);
	$("#dialog-form").dialog("open");
}

function openGuidance(controlId) {
	var params = "?controlId=" + controlId;
	$('#controlGuidance').attr("src", "/icon/pages/control/popup" + params);
	$("#dialog-form").dialog("open");
}

function returnValues(userId, name, fieldId)
{
	values = new Object();
	values.userId=userId;
	values.name=name;
	values.fieldId=fieldId;
	window.parent.closeIframe(values);
	return true;
}

function closeIframe(returnValues) {
	if (returnValues != null) {

		returnedId = returnValues.userId;
		returnedName = returnValues.name;
		returnedFieldId = returnValues.fieldId;
		
		var fieldId = '#' + returnedFieldId + "Id";
		$(fieldId).val(returnedId);
		
		var fieldDisplayName = '#' + returnedFieldId + "DisplayName";	
		$(fieldDisplayName).val(returnedName);

		
		/*		if (returnedId != idField.value) {
			idField.value = returnedId;
			nameField.value = returnedName;
		}*/
	}	
	
	$('#dialog-form').dialog('close');
	return true;
}

$(function() {

	$("#dialog-form").dialog({
		autoOpen : false,
		height : 500,
		width : 700,
		resizable : false,
		draggable : false,		
		modal : true
	});

});
