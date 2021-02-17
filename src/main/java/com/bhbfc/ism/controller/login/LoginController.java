package com.bhbfc.ism.controller.login;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bhbfc.ism.dao.login.LoginDao;
import com.bhbfc.ism.model.login.Login;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {
	@Autowired
    LoginDao dao;
	
	@GetMapping("/user/{userId}")
	public List<Login> getUser(@PathVariable String userId){
		
		List<Login> login = dao.getUser(userId);
		
		if(login==null) { 
			throw new RuntimeException("Student id "+userId+" not found!");
		}
		
		return login;
		
	}
	
	@RequestMapping(value = "/logIn", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> logIn(@RequestParam String userId, @RequestParam String password) throws Exception {
		HashMap<String, Object> response = new LinkedHashMap<String, Object>();
		
		Login login = dao.logIn(userId, password);
		
		response.put("outCode", login.getOutCode());
		response.put("outMessage", login.getOutMessage());
		
		return response;
	}
}
