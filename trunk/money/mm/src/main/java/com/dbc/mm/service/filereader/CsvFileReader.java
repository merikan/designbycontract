package com.dbc.mm.service.filereader;

import java.util.List;

import com.dbc.mm.model.Transaction;

public interface CsvFileReader {

	List<Transaction> getAllTransactions(String filename, String delimiter);
}
