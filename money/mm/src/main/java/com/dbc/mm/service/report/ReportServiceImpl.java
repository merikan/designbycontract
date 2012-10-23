package com.dbc.mm.service.report;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbc.mm.model.Category;
import com.dbc.mm.model.Transaction;
import com.dbc.mm.service.category.CategoryService;
import com.dbc.mm.service.transaction.TransactionService;
import com.dbc.mm.vo.ReportCategory;

@Service
@Transactional
public class ReportServiceImpl implements ReportService{

	protected static Logger logger = Logger.getLogger(ReportServiceImpl.class);
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	CategoryService categoryService;

	
	@Override
	public List<ReportCategory> getReportCategories() {
		List<Category> categories = categoryService.findAll();
		List<Transaction> transactions = transactionService.findAll();
		

		
		List<ReportCategory> reportCategories = new ArrayList<ReportCategory>();
		for (Category category: categories)
		{
		
			List<Transaction> t = getTransactionsForCategory(transactions, category);
			
			if (t.size() > 0 && (!category.getName().equals("Wages")))
			{
				logger.error("Adding " + category.getName());
				ReportCategory rc = new ReportCategory();
				rc.setCategory(category);
				rc.setTransactions(t);
				rc.setTotal(getTotal(t));
				reportCategories.add(rc);
			}
		}
		return reportCategories;
	}
	
	private BigDecimal getTotal(List<Transaction> transactions) {
		BigDecimal total = new BigDecimal(0);
		for (Transaction t : transactions)
		{
			total = total.add(t.getValue());
		}
		return total;
		
	}

	private List<Transaction> getTransactionsForCategory(List<Transaction> transactions, Category category) {
		List<Transaction> tc = new ArrayList<Transaction>();
		for (Transaction t : transactions)
		{
			Category c = t.getCategory();
			if (c!=null && c.getId().equals(category.getId()))
			{
				tc.add(t);
			}
		}
		return tc;
	}


}
