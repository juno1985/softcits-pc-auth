package org.softcits.auth.model;

import java.util.List;

public class UserUpdateFormModel {

	private MbgUser mbgUser;
	private List<MbgRole> mbgRole;
	private List<String> states;

	public MbgUser getMbgUser() {
		return mbgUser;
	}

	public void setMbgUser(MbgUser mbgUser) {
		this.mbgUser = mbgUser;
	}

	public List<MbgRole> getMbgRole() {
		return mbgRole;
	}

	public void setMbgRole(List<MbgRole> mbgRole) {
		this.mbgRole = mbgRole;
	}

	public List<String> getStates() {
		return states;
	}

	public void setStates(List<String> states) {
		this.states = states;
	}

}