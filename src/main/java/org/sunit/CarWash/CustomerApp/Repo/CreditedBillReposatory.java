package org.sunit.CarWash.CustomerApp.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.sunit.CarWash.CustomerApp.Data.CREDITDETAIL;

@Repository
public interface CreditedBillReposatory extends CrudRepository<CREDITDETAIL, Integer> {


}
