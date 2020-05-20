package org.sunit.CarWash.CustomerApp.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.sunit.CarWash.CustomerApp.Data.LoginTO;
import org.sunit.CarWash.CustomerApp.Repo.CreditedBillRepositoryImpl;
import org.sunit.CarWash.CustomerApp.Repo.DebitedBillRepositoryImpl;
import org.sunit.CarWash.CustomerApp.Repo.LoginRepository;

@Service
public class LoginServices {
	
	@Autowired
	LoginRepository loginRepository;
	
	
	@Autowired
	CreditedBillRepositoryImpl creditedBillRepositoryImpl;
	
	@Autowired
	DebitedBillRepositoryImpl debitedBillRepository;

	public ResponseEntity<Map<String, String>> getSignUp(LoginTO loginTO) {
		
		int id=	loginRepository.save(loginTO).getLoginId();
		HttpHeaders headers = null;
		Map<String, String> body = new HashMap<String, String>();
		
		if(id!=0) {
			body.put("successmsg", loginTO.getName() +" Register successfully");
			return ResponseEntity.accepted().headers(headers).body(body);
		}
			
			return ResponseEntity.badRequest().headers(headers).body(body);
	}

	public ResponseEntity<Map<String, String>> getLogin(LoginTO loginTO) {
		HttpHeaders headers = null;
		Map<String, String> body = new HashMap<String, String>();
		
		LoginTO value= loginRepository.findbyusernameAndpassword(loginTO.getUsername(),loginTO.getPassword());
		
		if(null!=value) {
			body.put("name", value.getName());
			body.put("ContactNo", value.getContactNo());
			return ResponseEntity.ok().headers(headers).body(body);
		}
		
		body.put("errmsg", "either username or password is wrong");
		return ResponseEntity.badRequest().headers(headers).body(body);
	}

	public ResponseEntity<Map<String, String>> getDetails() {
		List<LoginTO> value=loginRepository.findAll();
		HttpHeaders headers = null;
		Map<String, String> body = new HashMap<String, String>();
		
		for (LoginTO loginTO : value) {
			body.put("name", loginTO.getName());
			body.put("ContactNo", loginTO.getContactNo());
			
		}
		return ResponseEntity.ok().headers(headers).body(body);
	}

	
	
	
	
}
