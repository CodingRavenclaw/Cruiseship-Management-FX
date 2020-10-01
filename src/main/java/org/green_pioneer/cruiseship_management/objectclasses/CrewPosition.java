package org.green_pioneer.cruiseship_management.objectclasses;

public class CrewPosition {

	private String strPosition;
	private String strDescription;
	
	
	
	public CrewPosition() {
		
	}
	
	public CrewPosition(String strPosition, String strDescription) {
		super();
		this.strPosition = strPosition;
		this.strDescription = strDescription;
	}

	
	
	public String getStrPosition() {
		return strPosition;
	}

	public void setStrPosition(String strPosition) {
		this.strPosition = strPosition;
	}

	public String getStrDescription() {
		return strDescription;
	}

	public void setStrDescription(String strDescription) {
		this.strDescription = strDescription;
	}
	
	
	
}
