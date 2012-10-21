package com.dbc.mm.service.filereader;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import au.com.bytecode.opencsv.CSVReader;

import com.dbc.mm.model.Transaction;

public class CsvFileReaderImpl implements CsvFileReader {

	@Override
	public List<Transaction> getAllTransactions(String filename,
			String delimiter) {
		FileReader fileReader = getFileReader(filename);
		CSVReader reader = new CSVReader(fileReader);
		List<Transaction> transactions = getTransactions(reader);
		return transactions;
	}

	private List<Transaction> getTransactions(CSVReader reader) {
		try {
			List<String[]> myEntries = reader.readAll();
			List<Transaction> transactions = processLines(myEntries);
			return transactions;
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private List<Transaction> processLines(List<String[]> lines) {
		List<Transaction> transactions = new ArrayList<>();
		for (String[] line : lines)
		{
			if (line.length == 8)
			{
				Transaction transaction = processLine(line);
				transactions.add(transaction);
			}
		}
		return transactions;
	}

	private Transaction processLine(String[] line) {
		Transaction transaction = new Transaction();
		transaction.setDate(getDate(line[0]));
		transaction.setType(line[1]);
		transaction.setDescription(line[2]);
		transaction.setValue(new BigDecimal(line[3]));
		transaction.setBalance(new BigDecimal(line[4]));
		transaction.setAccountName(line[5]);
		transaction.setAccountNumber(line[6]);
		System.out.println(transaction);
		return transaction;
	}

	private Date getDate(String dateString) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date d;
		try {
			d = format.parse(dateString);
			return d;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	private FileReader getFileReader(String filename) {
		try {

			Resource resource = new ClassPathResource(filename);
			FileReader fileReader = new FileReader(resource.getFile());
			return fileReader;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
