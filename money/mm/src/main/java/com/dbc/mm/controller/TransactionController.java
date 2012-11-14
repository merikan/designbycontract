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
import com.dbc.mm.model.Account;
import com.dbc.mm.model.Category;
import com.dbc.mm.model.Transaction;
import com.dbc.mm.service.Account.AccountService;
import com.dbc.mm.service.category.CategoryService;
import com.dbc.mm.service.transaction.TransactionService;
import com.dbc.mm.vo.SessionState;

@Controller
@RequestMapping("/transaction")
public class TransactionController extends AbstractApplicationController {

	private static final String JSP = "/transaction/view_transactions";

	protected static Logger logger = Logger.getLogger(TransactionController.class);

	@Autowired
	TransactionService transactionService;

	@Autowired
	AccountService accountService;

	@Autowired
	CategoryService categoryService;

	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view() {
		
		TransactionForm form = new TransactionForm();
	
		SessionState state = new UserContextHolder().currentSecurityContext();	
		Account account = state.getAccount();
		form.setAllCategories(categoryService.findAll());
		form.setAllTransactions(transactionService.findByAccount(account));
		
		return new ModelAndView(JSP, "form", form);
	}
	

	@RequestMapping(value = "/viewById", method = RequestMethod.GET)
	public ModelAndView viewById(@RequestParam("accountId") Long accountId) {
		
		TransactionForm form = new TransactionForm();
	
		Account account = accountService.findOne(accountId);
		form.setAllCategories(categoryService.findAll());
		form.setAllTransactions(transactionService.findByAccount(account));
		
		return new ModelAndView(JSP, "form", form);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/updateTransaction")  
	public @ResponseBody String updateTransaction(@RequestParam("transactionId") Long transactionId, @RequestParam("categoryId") Long categoryId, Model model){
		Transaction t = transactionService.findById(transactionId);
		logger.error("***********************" + t.getId());
		
		Category c = categoryService.findById(categoryId);
		logger.error("*********************" + c.getId());
		t.setCategory(c);
		
		transactionService.save(t);
	    return "value";
	}
	

}