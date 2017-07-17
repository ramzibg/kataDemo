package com.ramzi.kataDemo.dao;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ramzi.kataDemo.model.Account;
import com.ramzi.kataDemo.model.Client;
import com.ramzi.kataDemo.model.Operation;

/**
 * 
 * @author ramzi ben gharsallah
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class OperationDAOTest {

	@InjectMocks
	OperationDAO operationDAO;
	@Mock
	Account acc;
	@Mock
	Operation ope;
	@Mock
	Client client;

	@Test
	public void saveAccountTest() {
		Account saveAccount = operationDAO.saveAccount(acc);
		Assert.assertNotNull(saveAccount);
	}

	@Test
	public void saveOperationTest() {
		Operation saveope = operationDAO.saveOperation(ope);
		Assert.assertNotNull(saveope);
	}

	@Test
	public void getListOperationTest() {
		Date start = Mockito.mock(Date.class);
		Date end = Mockito.mock(Date.class);
		List<Operation> listOperation = operationDAO.getListOperation(acc, start, end);
		Assert.assertNotNull(listOperation);

	}

	@Test
	public void getClientTest() {
		Account account = operationDAO.getAccount(client);
		Assert.assertNotNull(account);
	}

}
