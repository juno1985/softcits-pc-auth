package org.softcits.auth.service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.softcits.auth.mapper.MbgRoleMapper;
import org.softcits.auth.mapper.MbgUserMapper;
import org.softcits.auth.mapper.UserAndRoleMapper;
import org.softcits.auth.model.MbgUser;
import org.softcits.auth.model.UserAndRole;
import org.softcits.auth.model.UserUpdateFormModel;
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
	@Autowired
	private UserAndRoleMapper userAndRoleMapper;
	public void addUser(String username, String passwd) throws NoSuchAlgorithmException {
		MbgUser mbgUser = new MbgUser();
		mbgUser.setUsername(username);
		mbgUser.setPasswd(SecurityUtil.md5(passwd));
		mbgUser.setCreateTime(new Date());
		mbgUser.setState(StateEnum.INACTIVE.getCode());
		mbgUser.setRoleId(Integer.parseInt(RoleEnum.STAFF.getCode()));
		mbgUserMapper.insert(mbgUser);
	}
	public List<UserAndRole> getAllUsers() {
		
		return userAndRoleMapper.getUsersAndRoles();
	}
	
	public UserUpdateFormModel getUserUpdateForm(String uid) {
		UserUpdateFormModel userUpdateFormModel = new UserUpdateFormModel();
		//通过id查询user
		MbgUser mbgUser = mbgUserMapper.selectByPrimaryKey(Integer.parseInt(uid));
		userUpdateFormModel.setMbgUser(mbgUser);
		//从数据库中取出所有的角色
		userUpdateFormModel.setMbgRole(mbgRoleMapper.selectByExample(null));
		//遍历state的枚举取出所有的状态
		List<String> states = new ArrayList<String>();
		for(StateEnum sm : StateEnum.values()) {
			states.add(sm.getCode());
		}
		userUpdateFormModel.setStates(states);
		return userUpdateFormModel;
	}
	public void updateUser(MbgUser mbgUser) {
		mbgUserMapper.updateByPrimaryKeySelective(mbgUser);
	}

}
