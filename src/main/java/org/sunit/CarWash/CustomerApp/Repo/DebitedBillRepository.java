package org.sunit.CarWash.CustomerApp.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.sunit.CarWash.CustomerApp.Data.Bill;

@Repository
public interface DebitedBillRepository extends CrudRepository<Bill, Integer> {

	@Query("select custId, custFName, custLName, billAmount, due, clearFlag, dateCount from Bill  where custId=:custId")
	List<Object> getBillsByCustId(@Param("custId") Integer custId);

}
