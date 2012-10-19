package com.dbc.mm.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dbc.mm.form.DashboardForm;
import com.dbc.mm.service.transaction.TransactionService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController extends AbstractApplicationController {

	protected static Logger logger = Logger.getLogger(DashboardController.class);

	@Autowired
	TransactionService transactionService;

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view() {
		logger.debug("**********");
		DashboardForm form = new DashboardForm();
		form.setAllTransactions(transactionService.findAll());
		return new ModelAndView("/dashboard/view_dashboard", "form", form);
	}
}