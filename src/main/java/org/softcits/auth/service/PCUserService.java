package org.softcits.auth.service;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.softcits.auth.mapper.MbgRoleMapper;
import org.softcits.auth.mapper.MbgUserMapper;
import org.softcits.auth.model.MbgUser;
import org.softcits.pc.mgt.common.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.softcits.auth.uenum.StateEnum;
import org.softcits.auth.uenum.RoleEnum;
@Service
//需要加入事务管理
@Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
public class PCUserService {
	@Autowired
	private MbgUserMapper mbgUserMapper;
	@Autowired
	private MbgRoleMapper mbgRoleMapper;
	public void addUser(String username, String passwd) throws NoSuchAlgorithmException {
		MbgUser mbgUser = new MbgUser();
		mbgUser.setUsername(username);
		mbgUser.setPasswd(SecurityUtil.md5(passwd));
		mbgUser.setCreateTime(new Date());
		mbgUser.setState(StateEnum.INACTIVE.getCode());
		mbgUser.setRoleId(Integer.parseInt(RoleEnum.STAFF.getCode()));
		mbgUserMapper.insert(mbgUser);
	}

}