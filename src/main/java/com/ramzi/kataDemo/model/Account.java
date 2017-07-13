package com.ramzi.kataDemo.model;

/**
 * 
 * @author ramzi ben gharsallah
 *
 */
public class Account {
	
	private Long id;
	private Long balance;
	private Client client;
	
	
	
	public Account(Long id, Long balance, Client client) {
		this.id = id;
		this.balance = balance;
		this.client = client;
	}
	public Account() {
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBalance() {
		return balance;
	}
	public void setBalance(Long balance) {
		this.balance = balance;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	

}
