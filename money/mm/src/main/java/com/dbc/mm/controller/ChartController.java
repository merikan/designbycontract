package com.dbc.mm.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dbc.mm.form.ChartForm;
import com.dbc.mm.model.Account;
import com.dbc.mm.model.Category;
import com.dbc.mm.service.Account.AccountService;
import com.dbc.mm.service.category.CategoryService;
import com.dbc.mm.service.report.ReportService;
import com.dbc.mm.service.transaction.TransactionService;
import com.dbc.mm.vo.ReportCategory;

@Controller
@RequestMapping("/chart")
public class ChartController extends AbstractApplicationController {

	protected static Logger logger = Logger.getLogger(ChartController.class);

	@Autowired
	ReportService reportService;

	@Autowired
	AccountService accountService;
	
	@Autowired
	CategoryService categoryService;

	@Autowired
	TransactionService transactionService;

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view() {

		ChartForm form = new ChartForm();
		Account account = new UserContextHolder().currentSecurityContext().getAccount();
		
		form.setAllTransactions(transactionService.findByAccount(account));

		populateForm(form);

		return new ModelAndView("/chart/view_chart", "form", form);
	}

	@RequestMapping(value = "/viewBarChart", method = RequestMethod.GET)
	public ModelAndView viewBarChart() {

		ChartForm form = new ChartForm();
		Account account = new UserContextHolder().currentSecurityContext().getAccount();
		
		form.setAllTransactions(transactionService.findByAccount(account));

		populateForm(form);

		return new ModelAndView("/chart/view_bar_chart", "form", form);
	}
	

	private void populateForm(ChartForm form) {
		List<Category> categories = categoryService.findAll();
		form.setAllCategories(categories);

		List<ReportCategory> reportCategories = reportService.getReportCategories();
		form.setCategories(reportCategories);
	}

	
	@RequestMapping(value = "/viewById", method = RequestMethod.GET)
	public ModelAndView viewById(@RequestParam(value = "id", required = true) Long categoryId) {

		ChartForm form = new ChartForm();
		Account account = new UserContextHolder().currentSecurityContext().getAccount();
		
		Category category = categoryService.findById(categoryId);
		form.setAllTransactions(transactionService.findByAccountAndCategory(account, category));

		populateForm(form);

		return new ModelAndView("/chart/view_chart", "form", form);
	}

	
	@RequestMapping(value = "/viewByAccount", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam(value = "accountId", required = true) Long accountId) {

		ChartForm form = new ChartForm();		
		Account account = accountService.findOne(accountId);
		
		new UserContextHolder().currentSecurityContext().setAccount(account);
		form.setAllTransactions(transactionService.findByAccount(account));

		populateForm(form);

		return new ModelAndView("/chart/view_chart", "form", form);
	}

	private String getChartData(List<ReportCategory> reportCategories) {
		StringBuilder data = new StringBuilder("[");
		boolean first = true;
		for (ReportCategory rc : reportCategories) {
			if (!first) {
				data.append(",");
			}
			data.append("[\"");
			data.append(rc.getCategory().getName());
			data.append("\",");
			data.append(getPositiveValue(rc.getTotal()));
			data.append("]");
			first = false;
		}
		data.append("]");
		return data.toString();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getChartData")
	@ResponseBody
	public String getChartData() {
		List<ReportCategory> reportCategories = reportService.getReportCategories();
		String data = getChartData(reportCategories);
		logger.error("****************" + data);
		return data;
		// "[[\"Firefox\",45.0],[\"IE\",26.8],[\"Chrome\",12.8],[\"Saf\",8.5],[\"Opera\",6.2],[\"Others\",0.7]]";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/getBarChartData")
	@ResponseBody
	public List<Long> getBarChartData() {
		List<ReportCategory> reportCategories = reportService.getReportCategories();
		
		Collections.sort(reportCategories);
		List<Long> data = new ArrayList<Long>();
		
		for (ReportCategory rc : reportCategories)
		{
			data.add(rc.getTotal().longValue());
		}
		return data;

	}

	@RequestMapping(method = RequestMethod.POST, value = "/getBarChartCategories")
	@ResponseBody
	public List<String> getBarChartCategories() {
		
		List<ReportCategory> reportCategories = reportService.getReportCategories();
		
		Collections.sort(reportCategories);
		List<String> data = new ArrayList<String>();
		
		for (ReportCategory rc : reportCategories)
		{
			data.add(rc.getCategory().getName());
		}
		return data;
	}
	
	private BigDecimal getPositiveValue(BigDecimal b) {
		if (!isNegative(b)) {
			return b;
		}
		return b.negate();
	}

	public static boolean isNegative(BigDecimal b) {
		return b.signum() == -1;
	}

}