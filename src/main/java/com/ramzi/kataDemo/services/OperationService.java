package com.ramzi.kataDemo.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ramzi.kataDemo.dao.OperationDAO;
import com.ramzi.kataDemo.exception.KataDemoException;
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
	
	/**
	 * Methode deposit amount in account
	 * 
	 * @param ope
	 * @param acc
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public Account deposit(Operation ope, Account acc) throws KataDemoException {
		if(ope!=null && ope.getAmount()<0)
			throw new KataDemoException();
		acc.setBalance(acc.getBalance() + ope.getAmount());
		operationDAO.saveOperation(ope);
		Account saveAccount = operationDAO.saveAccount(acc);
		return saveAccount;
	}

	/**
	 * Methode withdrow amount from account
	 * 
	 * @param ope
	 * @param acc
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public Account withDrowl(Operation ope, Account acc) throws KataDemoException {
		if(ope!=null && ope.getAmount()<0){
			
			KataDemoException exception = new KataDemoException("Amount Negative");
			throw exception;
		}if(ope!=null && ope.getAmount() > acc.getBalance()){
			
			KataDemoException exception = new KataDemoException("Amount bigger than Balance");
			throw exception;
		}
		acc.setBalance(acc.getBalance() - ope.getAmount());
		operationDAO.saveOperation(ope);
		Account saveAccount = operationDAO.saveAccount(acc);
		return saveAccount;
	}

	/**
	 * Methode withdrow all solde from account
	 * 
	 * @param ope
	 * @param acc
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public Account withDrowlAll(Operation ope,Account acc) throws KataDemoException {
		if(acc.getBalance() <= 0){
			KataDemoException exception = new KataDemoException("Balance is empty");
			throw exception;
		}
			acc.setBalance(0L);
			return acc;
			
		}

	/**
	 * Methode gives history of operation between two dates
	 * 
	 * @param client
	 * @param start
	 * @param end
	 * @return
	 */
	@Transactional
	public List<Operation> getMyHistoryBetweenDates(Client client, Date start, Date end) {
		Account account = operationDAO.getAccount(client);
		List<Operation> listOperation = operationDAO.getListOperation(account, start, end);
		return listOperation;
	}
	
	

}
