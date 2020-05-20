package org.sunit.CarWash.CustomerApp.Repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.sunit.CarWash.CustomerApp.Data.CustomerTO;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerTO, Integer> {
	
	@Transactional
	@Modifying
	@Query("Update Customer set totalDue = totalDue +:billAmount where custId =:custId")
	void updateTotalDue(@Param("billAmount") Long billAmount, @Param("custId") Integer custId);

	@Transactional
	@Modifying
	@Query("Update Customer set totalDue = totalDue -:creditAmount where custId =:custId")
	void updateTotalBillByCustId(@Param("creditAmount") Long creditAmount,@Param("custId") Integer custId);
	

}
