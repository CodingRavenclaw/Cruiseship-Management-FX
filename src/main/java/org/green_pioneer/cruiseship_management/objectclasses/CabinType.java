package org.green_pioneer.cruiseship_management.objectclasses;

public class CabinType {

	private String strType;
	private String strDescription;
	private String strEquipment;
	
	public CabinType(String strType, String strDescription, String strEquipment) {
		this.strType = strType;
		this.strDescription = strDescription;
		this.strEquipment = strEquipment;
	}

	
	
	public String getStrType() {
		return strType;
	}

	public void setStrType(String strType) {
		this.strType = strType;
	}

	public String getStrDescription() {
		return strDescription;
	}

	public void setStrDescription(String strDescription) {
		this.strDescription = strDescription;
	}

	public String getStrEquipment() {
		return strEquipment;
	}

	public void setStrEquipment(String strEquipment) {
		this.strEquipment = strEquipment;
	}
	
	
}
