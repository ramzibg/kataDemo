package com.ramzi.kataDemo.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ramzi.kataDemo.dao.OperationDAO;
import com.ramzi.kataDemo.enums.OperationType;
import com.ramzi.kataDemo.model.Account;
import com.ramzi.kataDemo.model.Client;
import com.ramzi.kataDemo.model.Operation;
import com.ramzi.kataDemo.services.OperationService;

/**
 * 
 * @author ramzi ben gharsallah
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class OperationServiceTest {

	@InjectMocks
	OperationService operationService;
	@InjectMocks
	Account acc;
	@InjectMocks
	Operation ope;

	@Mock
	OperationDAO operationDAO;

	private List<Client> clients = new ArrayList<Client>();
	private List<Account> accounts = new ArrayList<Account>();
	private List<Operation> operations = new ArrayList<Operation>();

	@Test
	public void depositInAccountTest() throws Exception {
		acc.setBalance(1000L);
		ope.setAmount(1000L);
		ope.setAccount(acc);
		ope.setOperationDate(new Date());
		ope.setOperationType(OperationType.DEPOSIT);

		Mockito.when(operationDAO.saveOperation(ope)).thenReturn(ope);
		Mockito.when(operationDAO.saveAccount(acc)).thenReturn(acc);
		Account accountNew = operationService.deposit(ope, acc);

		Assertions.assertThat(accountNew.getBalance()).isEqualTo(2000L);
		Mockito.verify(operationDAO).saveOperation(ope);
		Mockito.verify(operationDAO).saveAccount(acc);
	}

	@Test
	public void depositNegativeInAccountTest() throws Exception {
		acc.setBalance(1000L);
		ope.setAmount(-1000L);
		ope.setOperationType(OperationType.DEPOSIT);
		Assertions.assertThatThrownBy(() -> operationService.deposit(ope, acc)).isInstanceOf(Exception.class);
	}

	@Test
	public void withDrowlFromAccountTest() throws Exception {
		acc.setBalance(1000L);
		ope.setAmount(500L);
		ope.setOperationType(OperationType.WITHDRAWL);
		ope.setAccount(acc);
		ope.setOperationDate(new Date());

		Mockito.when(operationDAO.saveOperation(ope)).thenReturn(ope);
		Mockito.when(operationDAO.saveAccount(acc)).thenReturn(acc);

		Account accountNew = operationService.withDrowl(ope, acc);

		Assert.assertEquals(accountNew.getBalance().longValue(), 500L);
		Mockito.verify(operationDAO).saveOperation(ope);
		Mockito.verify(operationDAO).saveAccount(acc);
	}

	@Test
	public void withDrowlAllFromAccountTest() throws Exception {
		acc.setBalance(1000L);
		ope.setOperationType(OperationType.WITHDRAWL);
		Account accountNew = operationService.withDrowlAll(ope, acc);
		Assert.assertEquals(accountNew.getBalance().longValue(), 0L);
	}

	@Test
	public void withDrowlAllEmptyFromAccountTest() throws Exception {
		acc.setBalance(0L);
		ope.setOperationType(OperationType.WITHDRAWL);
		Assertions.assertThatThrownBy(() -> operationService.withDrowlAll(ope, acc)).isInstanceOf(Exception.class)
				.hasMessage("Balance is empty");
	}

	@Test
	public void withDrowlNegFromAccountTest() throws Exception {
		acc.setBalance(1000L);
		ope.setAmount(-1000L);
		Assertions.assertThatThrownBy(() -> operationService.withDrowl(ope, acc)).isInstanceOf(Exception.class)
				.hasMessage("Amount Negative");
	}

	@Test
	public void withDrowlSupBalanceFromAccountTest() throws Exception {
		acc.setBalance(1000L);
		ope.setAmount(1500L);
		ope.setOperationType(OperationType.WITHDRAWL);
		Assertions.assertThatThrownBy(() -> operationService.withDrowl(ope, acc)).isInstanceOf(Exception.class)
				.hasMessage("Amount bigger than Balance");
	}

	@Test
	public void getMyHistoryTest() throws Exception {

		Calendar cal = Calendar.getInstance();
		Date end = new Date();
		cal.setTime(end);
		cal.add(Calendar.DATE, -5);
		Date start = cal.getTime();

		Client client = clients.get(1);
		Account myAccount = accounts.stream().filter(acou -> acou.getClient() == client).findFirst().orElse(null);
		List<Operation> resOpe = operations.stream().filter(opera -> opera.getAccount() == myAccount)
				.collect(Collectors.toList());

		Mockito.when(operationDAO.getAccount(client)).thenReturn(myAccount);
		Assert.assertNotNull(myAccount);
		Mockito.when(operationDAO.getListOperation(myAccount, start, end)).thenReturn(resOpe);
		Assert.assertEquals(resOpe.size(), 2);

		operationService.getMyHistoryBetweenDates(client, start, end);

		Mockito.verify(operationDAO).getAccount(client);
		Mockito.verify(operationDAO).getListOperation(myAccount, start, end);
	}

	@org.junit.Before
	public void createOperation() {
		for (int i = 1; i < 6; i++) {
			Client client = new Client();
			Account account = new Account();
			Operation depo = new Operation();
			Operation drow = new Operation();

			client.setId(new Long(i));
			client.setFirstName("fname_" + i);
			client.setLastName("lname_" + i);

			account.setClient(client);
			account.setBalance(1000L);

			depo.setAccount(account);
			depo.setAmount(200L * i);
			depo.setBalance(account.getBalance() + depo.getAmount());
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, -i * 4);
			depo.setOperationDate(cal.getTime());
			depo.setOperationType(OperationType.DEPOSIT);
			account.setBalance(depo.getBalance());

			drow.setAccount(account);
			drow.setAmount(50L * i);
			drow.setBalance(account.getBalance() - drow.getAmount());
			Calendar cal2 = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, -i * 3);
			drow.setOperationDate(cal2.getTime());
			drow.setOperationType(OperationType.WITHDRAWL);
			account.setBalance(drow.getBalance());

			clients.add(client);
			accounts.add(account);
			operations.add(depo);
			operations.add(drow);
		}

	}

}
