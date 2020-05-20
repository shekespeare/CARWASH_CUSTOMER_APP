package org.sunit.CarWash.CustomerApp.Services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sunit.CarWash.CustomerApp.Data.Bill;
import org.sunit.CarWash.CustomerApp.Repo.CustomerRepository;
import org.sunit.CarWash.CustomerApp.Repo.DebitedBillRepository;
import org.sunit.CarWash.CustomerApp.Repo.DebitedBillRepositoryImpl;

@Service
public class DebitedBillServices {
	
	@Autowired
	DebitedBillRepository debitedBillRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired

	DebitedBillRepositoryImpl debitedBillRepositoryImpl;
	
	@Transactional
	public String addDebitedBill(Bill debitedBillTO) {
		debitedBillTO.setDue(debitedBillTO.getBillAmount());
		debitedBillTO.setClearFlag("F");
		String billId=debitedBillRepository.save(debitedBillTO).getBillId().toString();
		customerRepository.updateTotalDue(Long.valueOf(debitedBillTO.getBillAmount()), debitedBillTO.getCustId());
    return billId;
	}
	
	public List<Bill> getAllBillByCustId(Integer custId) {
		List<Bill> bills= debitedBillRepositoryImpl.getBillsByCustId(custId);
		return bills;
	}
	
	
	public List<Bill> getAllBill(Bill debitedBillTO) {
		return (List<Bill>) debitedBillRepository.findAll();
	}
}
