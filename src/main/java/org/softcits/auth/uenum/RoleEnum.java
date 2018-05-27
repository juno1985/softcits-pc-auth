package org.softcits.auth.uenum;

public enum RoleEnum implements AuthEnum {
	MANAGER(2), ADMIN(1),STAFF(3);
	private RoleEnum(Integer rid) {
		this.rid = rid;
	}
	private Integer rid;
	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return this.rid.toString();
	}

}
