package com.dbc.mm.form;

import org.springframework.web.multipart.commons.CommonsMultipartFile;


public class FileUploadForm {

	private CommonsMultipartFile file;

	public CommonsMultipartFile getFile() {
		return file;
	}

	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
}
