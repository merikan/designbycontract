package com.dbc.mm;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.dbc.mm.model.Transaction;
import com.dbc.mm.service.filereader.CsvFileReader;
import com.dbc.mm.service.filereader.CsvFileReaderImpl;
import com.dbc.mm.service.transaction.TransactionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@TransactionConfiguration(defaultRollback=false)
public class CsvLoader extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	TransactionService transactionService;
	
	@Test
	public void testName() throws Exception {
		CsvFileReader csvFileReader = new CsvFileReaderImpl();
		List<Transaction> transactions = csvFileReader.getAllTransactions("transactions.csv", ",");
		//transactionService.save(transactions);	
	}
	
}
