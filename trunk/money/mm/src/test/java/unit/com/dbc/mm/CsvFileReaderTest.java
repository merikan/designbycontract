package com.dbc.mm;

import org.junit.Test;

import com.dbc.mm.service.filereader.CsvFileReader;
import com.dbc.mm.service.filereader.CsvFileReaderImpl;


public class CsvFileReaderTest {
	
	@Test
	public void testName() throws Exception {
		CsvFileReader csvFileReader = new CsvFileReaderImpl();
		csvFileReader.getAllTransactions("transactions.csv", ",");
	}

}
