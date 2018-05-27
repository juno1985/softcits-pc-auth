package org.softcits.auth.uenum;

public enum StateEnum implements AuthEnum {
	
	ACTIVE("active"), INACTIVE("inactive");

	private String code;
	private StateEnum(String code) {
		this.code = code;
	}
	@Override
	public String getCode() {
		
		return this.code;
	}

}
