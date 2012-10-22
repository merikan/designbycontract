package com.dbc.mm.controller;

import java.math.BigDecimal;
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
import com.dbc.mm.service.transaction.TransactionService;
import com.dbc.mm.vo.ReportCategory;

@Controller
@RequestMapping("/chart")
public class ChartController extends AbstractApplicationController {

	protected static Logger logger = Logger.getLogger(ChartController.class);

	@Autowired
	TransactionService transactionService;
	
	@Autowired
	CategoryService categoryService;


	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view() {

		return new ModelAndView("/chart/view_chart");
	}
	
	
}