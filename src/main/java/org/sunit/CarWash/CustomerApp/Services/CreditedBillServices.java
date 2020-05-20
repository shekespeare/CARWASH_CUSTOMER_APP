package org.sunit.CarWash.CustomerApp.Services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sunit.CarWash.CustomerApp.Data.CREDITDETAIL;
import org.sunit.CarWash.CustomerApp.Repo.CreditedBillReposatory;
import org.sunit.CarWash.CustomerApp.Repo.CreditedBillRepositoryImpl;
import org.sunit.CarWash.CustomerApp.Repo.CustomerRepository;
import org.sunit.CarWash.CustomerApp.Repo.DebitedBillRepositoryImpl;

@Service
public class CreditedBillServices {
	
	@Autowired
	CreditedBillReposatory creditedBillReposatory;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CreditedBillRepositoryImpl creditedBillRepositoryImpl;
	
	@Autowired
	DebitedBillRepositoryImpl debitedBillRepository;

	@Transactional
	public String addCreditedBill(CREDITDETAIL creditedBillTO) {
		String billId= creditedBillReposatory.save(creditedBillTO).getcBillId().toString();
		customerRepository.updateTotalBillByCustId(Long.valueOf(creditedBillTO.getCreditAmount()), creditedBillTO.getCustId());
		maintainDebitedBill(creditedBillTO);
    return billId;
	}

	private void maintainDebitedBill(CREDITDETAIL creditedBillTO) {
		debitedBillRepository.maintainDebitedBill(creditedBillTO);
	}

	public List<CREDITDETAIL> getAllCreditedBill() {
		return (List<CREDITDETAIL>) creditedBillReposatory.findAll();
	}

	public List<CREDITDETAIL> getAllCreditedBillByCustId(int custId) {
		List<CREDITDETAIL> bills = creditedBillRepositoryImpl.getBillsByCustId(custId);
		return bills;
	}
	
}
