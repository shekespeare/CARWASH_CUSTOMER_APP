package org.sunit.CarWash.CustomerApp.Repo;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.sunit.CarWash.CustomerApp.Data.Bill;
import org.sunit.CarWash.CustomerApp.Data.CREDITDETAIL;

@Component
public class DebitedBillRepositoryImpl {

	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	DebitedBillRepository debitedBillRepository;

	public List<Bill> getBillsByCustId(Integer custId) {

		List<Bill> postDTOs = entityManager.createQuery(
				"Select new Bill(billId, custId, custFName, custLName, billAmount, billDate, due, clearFlag, billClearDate, dateCount) from Bill where custId="
						+ custId + " Order by billDate")
				.getResultList();

		return postDTOs;
	}

	@Transactional
	@Modifying
	public boolean updateTotalBillOfCust(Integer billAmount, Integer custId) {
		Query query = entityManager.createQuery("Update Customer set totalDue =totalDue+ :billAmount where custId = :custId");
		query.setParameter("billAmount", Long.valueOf(billAmount));
		query.setParameter("custId", custId);
		int rowsUpdated = query.executeUpdate();
		return true;
	}

	public void maintainDebitedBill(CREDITDETAIL creditedBillTO) {
		Integer custId = creditedBillTO.getCustId();
		Integer amount = creditedBillTO.getCreditAmount();
		Date cDate = creditedBillTO.getCreditDate();
		Query query = entityManager.createQuery(
				"Select new Bill(billId, custId, custFName, custLName, billAmount, billDate, due, clearFlag, billClearDate, dateCount) from Bill where custId= :custId and clearFlag = 'F' order by billDate ASC");
		query.setParameter("custId", custId);
		List<Bill> postDTOs = query.getResultList();
		
		for (Bill pendingFBill : postDTOs) {
			if (amount >= pendingFBill.getDue()) {
				pendingFBill.setBillClearDate(cDate);
				pendingFBill.setClearFlag("T");
				Integer dayDiff = getDateDiff(cDate, pendingFBill.getBillDate(), TimeUnit.DAYS);
				pendingFBill.setDateCount(dayDiff);
				amount = amount - pendingFBill.getDue();
				pendingFBill.setDue(0);
				// call method for Update Bill
				debitedBillRepository.save(pendingFBill);
			} else {
				//pendingFBill.setBillClearDate(cDate);
				Integer due = pendingFBill.getDue() - amount;
				pendingFBill.setDue(due);
				pendingFBill.setClearFlag("F");
				// call method to update bill
				debitedBillRepository.save(pendingFBill);
				break;
			}
		}
	}
	
	public static Integer getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date1.getTime() - date2.getTime() ;
	    return (int) timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
}
