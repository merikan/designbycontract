package com.dbc.mm.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dbc.mm.form.DashboardForm;
import com.dbc.mm.model.Category;
import com.dbc.mm.model.Transaction;
import com.dbc.mm.service.category.CategoryService;
import com.dbc.mm.service.transaction.TransactionService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController extends AbstractApplicationController {

	protected static Logger logger = Logger.getLogger(DashboardController.class);

	@Autowired
	TransactionService transactionService;
	
	@Autowired
	CategoryService categoryService;


	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view() {
		logger.debug("**********");
		
		DashboardForm form = new DashboardForm();
		form.setAllCategories(categoryService.findAll());
		form.setAllTransactions(transactionService.findAll());
		
		return new ModelAndView("/dashboard/view_dashboard", "form", form);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/updateTransaction")  
	public @ResponseBody String getMovie(@RequestParam("transactionId") Long transactionId, @RequestParam("categoryId") Long categoryId, Model model){
		Transaction t = transactionService.findById(transactionId);
		logger.error("***********************" + t.getId());
		
		Category c = categoryService.findById(categoryId);
		logger.error("*********************" + c.getId());
		t.setCategory(c);
		
		transactionService.save(t);
	    return "value";
	}
	
	
}