package com.dbc.mm.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dbc.mm.form.DashboardForm;
import com.dbc.mm.model.Account;
import com.dbc.mm.model.User;
import com.dbc.mm.service.Account.AccountService;
import com.dbc.mm.vo.SessionState;

@Controller
@RequestMapping("/dashboard")
public class DashboardController extends AbstractApplicationController {

	private static final String JSP = "/dashboard/view_dashboard";

	protected static Logger logger = Logger.getLogger(DashboardController.class);

	@Autowired
	AccountService accountService;
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view() {
		
		SessionState state = new UserContextHolder().currentSecurityContext();	
		User user = state.getLoggedOnUser();
		
		DashboardForm form = new DashboardForm();

		List<Account> accounts = accountService.findByUser(user);
		form.setAccounts(accounts);
		
		return new ModelAndView(JSP, "form", form);
	}
	

}