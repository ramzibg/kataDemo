package com.ramzi.kataDemo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ramzi.kataDemo.model.Account;
import com.ramzi.kataDemo.model.Client;
import com.ramzi.kataDemo.model.Operation;

/**
 * 
 * @author ramzi ben gharsallah
 *
 */
@Component
public class OperationDAO {

	
	@Transactional
	public Account saveAccount( Account acc) {
		return acc;
	}
	@Transactional
	public Operation saveOperation( Operation ope) {
		return ope;
	}
	@Transactional
	public List<Operation> getListOperation( Account acc, Date start,Date end) {
		return new ArrayList<Operation>();
	}
	@Transactional
	public Account getAccount( Client client) {
		return new Account();
	}
	

}
