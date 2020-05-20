package org.sunit.CarWash.CustomerApp.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.sunit.CarWash.CustomerApp.Data.CustomerTO;
import org.sunit.CarWash.CustomerApp.Data.LoginTO;
import org.sunit.CarWash.CustomerApp.Services.CreditedBillServices;
import org.sunit.CarWash.CustomerApp.Services.CustomerServices;
import org.sunit.CarWash.CustomerApp.Services.DebitedBillServices;
import org.sunit.CarWash.CustomerApp.Services.LoginServices;

@RestController
@CrossOrigin(value="*")
@RequestMapping("/admin")
@Component
public class AdminController {

	/** The customer services. */
	@Autowired
	CustomerServices customerServices;
	
	@Autowired
	DebitedBillServices debitedBillServices;
	
	@Autowired
	CreditedBillServices creditedBillServices;
	
	@Autowired
	LoginServices loginServices;
	
	
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
	

	
	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public ResponseEntity<Map<String, String>> getLogin(@RequestBody LoginTO loginTO) {
		return loginServices.getLogin(loginTO);
	}

	
	@RequestMapping(method = RequestMethod.POST, value = "/signup")
	public ResponseEntity<Map<String, String>> getSignUp(@RequestBody LoginTO loginTO) {
		
		return loginServices.getSignUp(loginTO);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/check")
	public ResponseEntity<Map<String, String>> getLogin() {
		return loginServices.getDetails();
	}
}
