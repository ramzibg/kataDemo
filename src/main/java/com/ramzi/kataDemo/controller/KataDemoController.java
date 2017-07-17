package com.ramzi.kataDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ramzi.kataDemo.services.OperationService;

/**
 * 
 * @author Ramzi ben gharsallah
 *
 */

@RestController
public class KataDemoController extends SpringBootServletInitializer {

	@Autowired
	private OperationService operationService;

	/**
	 * show history of operations URL : http://localhost:8080/history
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/history")
	String history() {
		// operationService.getMyHistoryBetweenDates(client, start, end);
		return "History of operations";
	}

	/**
	 * deposit amount URL : http://localhost:8080/deposit?amount=55
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/deposit")
	String deposit(@RequestParam("amount") String amount) {
		return "Deposit is OK for amount :" + amount;
	}

	/**
	 * withdrowl amount URL : http://localhost:8080/withdrowl/33
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/withdrowl/{amount}")
	String withdrowl(@PathVariable("amount") String amount) {
		return "WithDrowl is OK for amount :" + amount;
	}

}
