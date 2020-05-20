package org.sunit.CarWash.CustomerApp.Controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.sunit.CarWash.CustomerApp.Data.CREDITDETAIL;
import org.sunit.CarWash.CustomerApp.Services.CreditedBillServices;
import org.thymeleaf.util.StringUtils;


@RestController
@RequestMapping("/creditedBill")
@Component
public class CreditedBillController {
	
	@Autowired
	CreditedBillServices creditedBillServices;
	
	@RequestMapping("/addCreditedBill")
	public String addCreditedBill(WebRequest request) {
		CREDITDETAIL creditedBillTO = new CREDITDETAIL();
        //custFName=hello&custLName=srio&creditAmount=1000&creditDate=24-10-1990
		creditedBillTO.setCustFName(request.getParameter("custFName"));
		creditedBillTO.setCustLName(request.getParameter("custLName"));
		creditedBillTO.setCreditAmount(Integer.parseInt(request.getParameter("creditAmount")));
		creditedBillTO.setCustId(Integer.parseInt(request.getParameter("custId")));

		try {
			creditedBillTO.setCreditDate(request.getParameter("creditDate"));
		} catch (ParseException e) {
			return "Date is not in correct formate please enter in DD-MM-YYYY formate";
		}
		String billId = creditedBillServices.addCreditedBill(creditedBillTO);
		if (!StringUtils.isEmptyOrWhitespace(billId)) {
			return "Account is Credited successFully";
		}
		return "Somthing went wrong";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getCreditedBills/{custId}")
	public List<CREDITDETAIL> getCreditedBillsOfCustomer(@PathVariable int custId){
		return creditedBillServices.getAllCreditedBillByCustId(custId);
	}
	
	@RequestMapping("/getCreditedBills")
	public List<CREDITDETAIL> getAllCreditedBills(){
		return creditedBillServices.getAllCreditedBill();
	}
	

	

	
}