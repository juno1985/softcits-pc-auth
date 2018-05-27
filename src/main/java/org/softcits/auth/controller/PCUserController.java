package org.softcits.auth.controller;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.softcits.auth.service.PCUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
@Controller
public class PCUserController {
	@Autowired
	private PCUserService userSerivce;

	@RequestMapping(path="/user/add", method=RequestMethod.POST)
	public ResponseEntity<Map> addUser(@RequestParam(required=true) String username,
			@RequestParam(required=true) String passwd,
			@RequestParam(required=true) String repasswd
			) throws NoSuchAlgorithmException{
		Map<String, String> result = new HashMap<String, String>();
		//如果两次输入密码不一致
		if(!passwd.equals(repasswd)) {
			result.put("msg", "password not sync");
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		
		userSerivce.addUser(username, passwd);
		result.put("msg", "success");
		return new ResponseEntity<>(result, HttpStatus.OK);
		
	}
}
