package org.softcits.auth.controller;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;

import org.softcits.auth.model.MbgUser;
import org.softcits.auth.model.UserAndRole;
import org.softcits.auth.model.UserUpdateFormModel;
import org.softcits.auth.service.PCUserService;
import org.softcits.pc.mgt.common.SoftcitsJsonUtil;
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
	
	@RequestMapping(path="/user/getAll", method=RequestMethod.GET)
	public ResponseEntity<String> getAllUsers(){
		List<UserAndRole> uList = userSerivce.getAllUsers();
		String users_json = SoftcitsJsonUtil.objectToJson(uList);
		String result = "get_users(" + users_json + ")";
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	/**
	 * 同时支持restfull或跨域请求的js代码片段
	 * @param uid
	 * @param callback 跨域js函数名
	 * @return
	 * 
	 * restfull 请求link: http://localhost:8883/auth/user/3/update
	 * js跨域请求link:     http://localhost:8883/auth/user/3/update?callback=user_form_query
	 */
	@RequestMapping(path="/user/{uid}/update", method=RequestMethod.GET)
	public ResponseEntity<String> getUserUpdateForm(@PathVariable String uid, @RequestParam(required=false) String callback){
		UserUpdateFormModel userUpdateFormModel = userSerivce.getUserUpdateForm(uid);
		String result = SoftcitsJsonUtil.objectToJson(userUpdateFormModel);
		if(!StringUtils.isEmpty(callback)) {
			result = callback + "(" + result + ")";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@RequestMapping(path="/user/update", method=RequestMethod.POST)
	public ResponseEntity<String> updateUser(@RequestBody MbgUser mbgUser){
		userSerivce.updateUser(mbgUser);
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}
	
	@RequestMapping(path="/user/login", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> login(@RequestParam String username, @RequestParam String passwd){
		String userJson = null;
		try {
			userJson = userSerivce.login(username, passwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(userJson !=  null)
			return new ResponseEntity<>(userJson, HttpStatus.OK);
		else
			return new ResponseEntity<>("Login Failed", HttpStatus.BAD_REQUEST);
	}
	
	//支持JSONP访问
	@RequestMapping(path="/{token}/token", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> getUserByToken(@PathVariable String token, @RequestParam(required=false) String callback){
		String result = userSerivce.getUserByToken(token);
		//如果是JSONP访问
		if(!StringUtils.isEmpty(callback)) {
			//如果用户存在redis中
			if(!StringUtils.isEmpty(result)) {
				result = callback + "(" + result + ")";
			}
			
		}
		else if(StringUtils.isEmpty(result)){
			
			return new ResponseEntity<>("Non-login", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@RequestMapping(path="/{token}/logout", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> logout(@PathVariable String token){
		userSerivce.logout(token);
		return new ResponseEntity<>("Logout Success", HttpStatus.OK);
	}
}
