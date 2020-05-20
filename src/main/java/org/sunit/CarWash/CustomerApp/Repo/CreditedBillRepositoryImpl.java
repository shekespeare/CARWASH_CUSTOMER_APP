package org.sunit.CarWash.CustomerApp.Repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.sunit.CarWash.CustomerApp.Data.CREDITDETAIL;
@Repository
public class CreditedBillRepositoryImpl {
	
	@PersistenceContext
	EntityManager entityManager;


	public List<CREDITDETAIL> getBillsByCustId(int custId) {
		List<CREDITDETAIL> postDTOs = entityManager.createQuery(
				"Select new CREDITDETAIL(cBillId, custId, custFName, custLName, creditAmount, creditDate) from CREDITDETAIL where custId="
						+ custId + " Order by creditDate")
				.getResultList();

		return postDTOs;
	}

}
