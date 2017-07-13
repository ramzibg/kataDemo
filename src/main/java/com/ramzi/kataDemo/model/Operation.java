package com.ramzi.kataDemo.model;

import java.util.Date;

import com.ramzi.kataDemo.enums.OperationType;
/**
 * 
 * @author ramzi ben gharsallah
 *
 */
public class Operation {
	
	private Long id;
	private Date operationDate;
	private Long amount;
	private Long balance;
	private Account account;
	private OperationType operationType;
	
	
	
	public Operation(Long id, Date operationDate, Long amount, Long balance, Account account,OperationType operationType) {
		this.id = id;
		this.operationDate = operationDate;
		this.amount = amount;
		this.balance = balance;
		this.account = account;
		this.operationType = operationType;
	}
	
	public Operation() {
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Long getBalance() {
		return balance;
	}
	public void setBalance(Long balance) {
		this.balance = balance;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public OperationType getOperationType() {
		return operationType;
	}
	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	
	
	
}
