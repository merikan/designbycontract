package com.dbc.mm.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dbc.mm.form.FileUploadForm;
import com.dbc.mm.model.Transaction;
import com.dbc.mm.service.filereader.CsvFileReader;
import com.dbc.mm.service.filereader.CsvFileReaderImpl;
import com.dbc.mm.service.transaction.TransactionService;

@Controller
@RequestMapping("/file")
public class FileUploadController {

	@Autowired
	TransactionService transactionService;
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view() {

		FileUploadForm form = new FileUploadForm();
		return new ModelAndView("/upload/upload_file", "form", form);
	}
	

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView upload(@ModelAttribute("form") FileUploadForm form) {


		CommonsMultipartFile cmf = form.getFile();

		FileItem fi = cmf.getFileItem();
		try {
			InputStreamReader isr = new InputStreamReader(fi.getInputStream());
			CsvFileReader csvFileReader = new CsvFileReaderImpl();
			List<Transaction> transactions = csvFileReader.getAllTransactions(isr, ",");
			transactionService.save(transactions);

		} catch (IOException e) {
			throw new RuntimeException();
		}
		
		return new ModelAndView("/upload/upload_file", "form", form);
	}

}
