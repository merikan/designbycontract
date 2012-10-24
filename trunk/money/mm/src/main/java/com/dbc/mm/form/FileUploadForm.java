package com.dbc.mm.form;

import org.springframework.web.multipart.commons.CommonsMultipartFile;


public class FileUploadForm {

	private String message;
	
	private CommonsMultipartFile file;

	public CommonsMultipartFile getFile() {
		return file;
	}

	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
