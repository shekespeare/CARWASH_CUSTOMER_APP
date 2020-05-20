package org.sunit.CarWash.CustomerApp.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.sunit.CarWash.CustomerApp.Data.LoginTO;

@Repository
public interface LoginRepository extends JpaRepository<LoginTO, Integer> {
	
	@Query("SELECT t FROM Login t where t.username = :username AND t.password = :password")
	public LoginTO findbyusernameAndpassword(@Param("username") String username, @Param("password")String password);

	     

}
