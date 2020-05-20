package org.sunit.CarWash.CustomerApp.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.sunit.CarWash.CustomerApp.Data.Bill;
import org.sunit.CarWash.CustomerApp.Data.CREDITDETAIL;
import org.sunit.CarWash.CustomerApp.Data.CustomerDetailTO;
import org.sunit.CarWash.CustomerApp.Data.CustomerTO;
import org.sunit.CarWash.CustomerApp.Services.CreditedBillServices;
import org.sunit.CarWash.CustomerApp.Services.CustomerServices;
import org.sunit.CarWash.CustomerApp.Services.DebitedBillServices;

/**
 * The Class CustomerController.
 */
@RestController
@RequestMapping("/customer")
@Component
public class CustomerController {

	/** The customer services. */
	@Autowired
	CustomerServices customerServices;
	
	@Autowired
	DebitedBillServices debitedBillServices;
	
	@Autowired
	CreditedBillServices creditedBillServices;

	/** The response. */
	ResponseEntity<CustomerTO> response = null;

	/** The responses. */
	ResponseEntity<List<CustomerTO>> responses = null;

	

  /**
   * Save.
   *
   * @param customerTO the customer TO
   * @param modelAndView the model and view
   * @param request the request
   * @return the model and view
   */
  @RequestMapping(value = "/add-customer")
  public ModelAndView save(@ModelAttribute CustomerTO customerTO, ModelAndView modelAndView, WebRequest request) {
    modelAndView.setViewName("user-creation");
    modelAndView.addObject("header", " Add Customer ");
    modelAndView.addObject("customerTO", customerTO);
    return modelAndView;
  }
	/**
	 * Adds the customer.
	 *
	 * @param customerTO the customer TO
	 * @param validateCustomerTO the validate customer TO
	 * @param result the result
	 * @param mav the mav
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add-customer")
	public ModelAndView addCustomer(@ModelAttribute CustomerTO customerTO, @Valid CustomerTO validateCustomerTO,
			BindingResult result, ModelAndView mav, WebRequest request) {
		if (result.hasErrors()) {
			System.err.println("Validation errors while submitting form.");
			mav.setViewName("user-creation");
			mav.addObject("header", " Add Customer ");
			mav.addObject("customerTO", customerTO);
			return mav;
		}
		int custId = customerServices.addCustomer(customerTO).getCustId();
		if (custId != 0) {
			mav.addObject("successMsg", "Customer Added SuccessFully");
			mav.setViewName("user-creation");
			mav.addObject("customerTO",new CustomerTO());
			mav.addObject("header", " Add Customer ");
			System.out.println("Form submitted successfully.");
		
		}
		return mav;
	}

	/**
	 * Gets the all customer.
	 *
	 * @return the all customer
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getAllCustomer")
	public ResponseEntity<List<CustomerTO>> getAllCustomer() {
		List<CustomerTO> allCustomers = customerServices.getAllCustomer();
		return responses = new ResponseEntity<List<CustomerTO>>(allCustomers, HttpStatus.OK);
	}

	/**
	 * Gets the all customer by cust id.
	 *
	 * @param custId the cust id
	 * @return the all customer by cust id
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getCustomer/{custId}")
	public ResponseEntity<Optional<CustomerTO>> getAllCustomerByCustId(@PathVariable int custId) {
		Optional<CustomerTO> customerTO = customerServices.getCustomerById(custId);
		return new ResponseEntity<Optional<CustomerTO>>(customerTO, HttpStatus.OK);
	}

	
	@RequestMapping(method = RequestMethod.POST, value = "/getCustomer/")
	public ResponseEntity<Optional<CustomerTO>> getAllJmeterByCustId(@RequestBody int custId) {
		Optional<CustomerTO> customerTO = customerServices.getCustomerById(custId);
		return new ResponseEntity<Optional<CustomerTO>>(customerTO, HttpStatus.OK);
	}

	
	 /**
 	 * Creates the user view.
 	 *
 	 * @return the model and view
 	 */
 	@GetMapping("/home")
	  public ModelAndView createUserView() {
	      ModelAndView mav = new ModelAndView();
	      mav.setViewName("home");
	      return mav;
	    }
 	
  @GetMapping("/getDetailsPage")
	public ModelAndView getDetailsPage(WebRequest request) {
		StringBuilder custId = new StringBuilder();
		ModelAndView mav = new ModelAndView();

		String billTable = null;
		String creditedBillTable = null;
		if (null != request.getParameter("custId")) {
			custId.append(request.getParameter("custId"));
		} else {
			custId.append("");
		}
		Map<String, CustomerDetailTO> getDetails = getCustomerDetailsByCustId(Integer.parseInt(custId.toString()));
		CustomerDetailTO customerDetailTO = getDetails.get("getDetails");
		Optional<CustomerTO> customerTO = customerDetailTO.getCustomerTO();

		CustomerTO customer = customerTO.get();
		if (null != customerDetailTO) {

			Map<String, List<Bill>> billsMap = customerDetailTO.getBillMap();

			if (!billsMap.isEmpty()) {
				List<Bill> bills = billsMap.get("BILL");
				mav.addObject("billTable", bills);
			}

			Map<String, List<CREDITDETAIL>> creditedBilsMap = customerDetailTO.getcBillMap();

			if (!creditedBilsMap.isEmpty()) {
				List<CREDITDETAIL> bills = creditedBilsMap.get("CREDITDETAIL");
				mav.addObject("creditedTable", bills);
			}
			mav.addObject("custName", customer.getCustFName() + " " + customer.getCustLName());
			mav.addObject("city", customer.getCity());
			mav.addObject("Date", new Date());
			mav.addObject("contactNo", customer.getContact());
			mav.addObject("dueTotal", customerDetailTO.getTotalCBillAmount());
			mav.addObject("Ctotal", customerDetailTO.getTotalCBillAmount());
			mav.addObject("Btotal", customerDetailTO.getTotalBillAmount());
			mav.addObject("duetotal", customerDetailTO.getTotalDue());
			mav.addObject("header", "Transaction Details");

		}
		mav.setViewName("user-info");

		return mav;
	}

	
	
  /**
	 * Gets the all customer by cust id.
	 *
	 * @param custId the cust id
	 * @return the all customer by cust id
	 */
	public Map<String, CustomerDetailTO> getCustomerDetailsByCustId(int custId) {
		
		Optional<CustomerTO> customerTO = customerServices.getCustomerById(custId);
		List<Bill> billTOs = debitedBillServices.getAllBillByCustId(custId);
		List<CREDITDETAIL> cBillTOs = creditedBillServices.getAllCreditedBillByCustId(custId);
		
		Map<String, CustomerDetailTO> output=new HashMap<>();
		Integer billAamount = 0;
		Integer cBillAamount = 0;
		Integer dueamount = 0;
		
		for (Bill bill : billTOs) {
			billAamount = billAamount + bill.getBillAmount();
			dueamount = dueamount + bill.getDue();
		}
		
		for (CREDITDETAIL cbill : cBillTOs) {
			cBillAamount = cBillAamount + cbill.getCreditAmount();
		}
		
		CustomerDetailTO customerDetailTO = new CustomerDetailTO();
		Map<String, List<Bill>> billTOMap = new HashMap<String, List<Bill>>();
		Map<String, List<CREDITDETAIL>> cBillTOMap = new HashMap<String, List<CREDITDETAIL>>();
		billTOMap.put("BILL", billTOs);
		cBillTOMap.put("CREDITDETAIL", cBillTOs);
		
		customerDetailTO.setCustomerTO(customerTO);
		customerDetailTO.setBillMap(billTOMap);
		customerDetailTO.setTotalBillAmount(billAamount);
		customerDetailTO.setTotalCBillAmount(cBillAamount);
		customerDetailTO.setcBillMap(cBillTOMap);
		customerDetailTO.setTotalDue(dueamount);
		output.put("getDetails", customerDetailTO);
		
//		return new ResponseEntity<CustomerDetailTO>(customerDetailTO, HttpStatus.OK);
		return output;
		
	}
 	
}
