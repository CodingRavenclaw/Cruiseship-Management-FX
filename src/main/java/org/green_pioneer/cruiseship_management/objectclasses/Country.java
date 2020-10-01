package org.green_pioneer.cruiseship_management.objectclasses;

public class Country {

	private String strCode;
	private String strNameEN;
	private String strNameDE;
	
	public Country() {
		
	}
	
	public Country(String strCode, String strNameEN, String strNameDE) {
		this.strCode = strCode;
		this.strNameEN = strNameEN;
		this.strNameDE = strNameDE;
	}

	
	
	public String getStrCode() {
		return strCode;
	}

	public void setStrCode(String strCode) {
		this.strCode = strCode;
	}

	public String getStrNameEN() {
		return strNameEN;
	}

	public void setStrNameEN(String strNameEN) {
		this.strNameEN = strNameEN;
	}

	public String getStrNameDE() {
		return strNameDE;
	}

	public void setStrNameDE(String strNameDE) {
		this.strNameDE = strNameDE;
	}
	
}
