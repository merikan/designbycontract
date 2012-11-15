package com.dbc.mm.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dbc.mm.form.ReportForm;
import com.dbc.mm.model.Category;
import com.dbc.mm.model.Transaction;
import com.dbc.mm.service.category.CategoryService;
import com.dbc.mm.service.report.ReportService;
import com.dbc.mm.service.transaction.TransactionService;
import com.dbc.mm.vo.ReportCategory;

@Controller
@RequestMapping("/report")
public class ReportController extends AbstractApplicationController {

	protected static Logger logger = Logger.getLogger(ReportController.class);

	@Autowired
	TransactionService transactionService;
	
	@Autowired
	CategoryService categoryService;
	
	
	@Autowired
	ReportService reportService;	


	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view() {
		logger.debug("**********");
		
		ReportForm form = new ReportForm();
		List<Category> categories = categoryService.findAll();
				
		form.setAllCategories(categories);
		
		List<ReportCategory> reportCategories = reportService.getReportCategories();
		form.setCategories(reportCategories);		
		
		return new ModelAndView("/report/view_report", "form", form);
	}
	


	
	
}