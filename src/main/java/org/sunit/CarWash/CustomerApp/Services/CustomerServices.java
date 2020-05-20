package org.sunit.CarWash.CustomerApp.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sunit.CarWash.CustomerApp.Data.CustomerTO;
import org.sunit.CarWash.CustomerApp.Repo.CustomerRepository;

@Service
public class CustomerServices {
	
	@Autowired
	CustomerRepository customerReposatory;
	
	public CustomerTO addCustomer(CustomerTO customerTO) {
		CustomerTO addedCustomer = customerReposatory.save(customerTO);
		return addedCustomer;
	}

	public List<CustomerTO> getAllCustomer() {
		return (List<CustomerTO>) customerReposatory.findAll();
	}
	
	public Optional<CustomerTO> getCustomerById(int custId){
		return customerReposatory.findById(custId);
	}
}
