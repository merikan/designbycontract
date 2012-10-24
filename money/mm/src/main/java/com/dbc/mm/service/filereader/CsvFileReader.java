package com.dbc.mm.service.filereader;

import java.io.InputStreamReader;
import java.util.List;

import com.dbc.mm.model.Transaction;

public interface CsvFileReader {

	List<Transaction> getAllTransactions(String filename, String delimiter);

	List<Transaction> getAllTransactions(InputStreamReader is, String delimiter);
}
