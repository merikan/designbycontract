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

import com.dbc.mm.form.TransactionForm;
import com.dbc.mm.model.Category;
import com.dbc.mm.model.Transaction;
import com.dbc.mm.service.category.CategoryService;
import com.dbc.mm.service.transaction.TransactionService;

@Controller
@RequestMapping("/transaction")
public class TransactionController extends AbstractApplicationController {

	private static final String JSP = "/dashboard/view_dashboard";

	protected static Logger logger = Logger.getLogger(TransactionController.class);

	@Autowired
	TransactionService transactionService;
	
	@Autowired
	CategoryService categoryService;


	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view() {
		logger.debug("**********");
		
		TransactionForm form = new TransactionForm();
		
		form.setAllCategories(categoryService.findAll());
		form.setAllTransactions(transactionService.findAll());
		
		//form.setAllCategories(categories);
		
		return new ModelAndView(JSP, "form", form);
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