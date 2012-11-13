package com.dbc.mm.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Transaction {
	
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", date=" + date + ", type=" + type
				+ ", description=" + description + ", value=" + value
				+ ", balance=" + balance + ", accountName=" + accountName
				+ ", accountNumber=" + accountNumber + ", category=" + category
				+ "]";
	}
	@Id
	@Column(name="transaction_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Date date;
	private String type;
	private String description;
	private BigDecimal value;
	private BigDecimal balance;
	private String accountName;
	private String accountNumber;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="category_id", referencedColumnName = "category_id")
	private Category category;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="account_id")
	private Account account;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
}
