package org.green_pioneer.cruiseship_management.objectclasses;

public class Passenger extends Person {

	private int intPassengerNo;
	
	
	
	public Passenger() {
		
	}

	public Passenger(int intPassengerNo, String strFirstName, String strLastName, String strGender,
			String strDateOfBirth, String strNationality) {
		super(strFirstName, strLastName, strDateOfBirth, strGender, strNationality);
		this.intPassengerNo = intPassengerNo;
	}

	
	
	public int getIntPassengerNo() {
		return intPassengerNo;
	}

	public void setIntPassengerNo(int intPassengerNo) {
		this.intPassengerNo = intPassengerNo;
	}
	
}
