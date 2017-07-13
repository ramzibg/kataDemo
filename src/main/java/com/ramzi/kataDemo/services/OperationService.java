package com.ramzi.kataDemo.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ramzi.kataDemo.dao.OperationDAO;
import com.ramzi.kataDemo.model.Account;
import com.ramzi.kataDemo.model.Client;
import com.ramzi.kataDemo.model.Operation;
/**
 * 
 * @author ramzi ben gharsallah
 *
 */
@Service
public class OperationService {

	@Autowired
	private OperationDAO operationDAO; 
	
	@Transactional
	public Account deposit(Operation ope, Account acc) throws IOException {
		if(ope!=null && ope.getAmount()<0)
			throw new IOException();
		acc.setBalance(acc.getBalance() + ope.getAmount());
		operationDAO.saveOperation(ope);
		Account saveAccount = operationDAO.saveAccount(acc);
		return saveAccount;
	}

	public Account withDrowl(Operation ope, Account acc) throws IOException {
		if(ope!=null && ope.getAmount()<0){
			
			IOException ioException = new IOException("Amount Negative");
			throw ioException;
		}if(ope!=null && ope.getAmount() > acc.getBalance()){
			
			IOException ioException = new IOException("Amount bigger than Balance");
			throw ioException;
		}
		acc.setBalance(acc.getBalance() - ope.getAmount());
		operationDAO.saveOperation(ope);
		Account saveAccount = operationDAO.saveAccount(acc);
		return saveAccount;
	}

	public Account withDrowlAll(Operation ope,Account acc) throws IOException {
		if(acc.getBalance() <= 0){
			IOException ioException = new IOException("Balance is empty");
			throw ioException;
		}
			acc.setBalance(0L);
			return acc;
			
		}

	public List<Operation> getMyHistoryBetweenDates(Client client, Date start, Date end) {
		Account account = operationDAO.getAccount(client);
		List<Operation> listOperation = operationDAO.getListOperation(account, start, end);
		return listOperation;
	}
	
	

}
