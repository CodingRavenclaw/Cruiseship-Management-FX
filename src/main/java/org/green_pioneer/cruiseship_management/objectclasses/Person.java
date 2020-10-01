package org.green_pioneer.cruiseship_management.objectclasses;

public abstract class Person {

	protected String strFirstName;
	protected String strLastName;
	protected String strDateOfBirth;
	protected String strGender;
	protected String strNationality;
	
	
	
	public Person() {
		
	}

	public Person(String strFirstName, String strLastName, String strDateOfBirth, String strGender,
			String strNationality) {
		this.strFirstName = strFirstName;
		this.strLastName = strLastName;
		this.strDateOfBirth = strDateOfBirth;
		this.strGender = strGender;
		this.strNationality = strNationality;
	}

	
	
	public String getStrFirstName() {
		return strFirstName;
	}

	public void setStrFirstName(String strFirstName) {
		this.strFirstName = strFirstName;
	}

	public String getStrLastName() {
		return strLastName;
	}

	public void setStrLastName(String strLastName) {
		this.strLastName = strLastName;
	}

	public String getStrDateOfBirth() {
		return strDateOfBirth;
	}

	public void setStrDateOfBirth(String strDateOfBirth) {
		this.strDateOfBirth = strDateOfBirth;
	}

	public String getStrGender() {
		return strGender;
	}

	public void setStrGender(String strGender) {
		this.strGender = strGender;
	}

	public String getStrNationality() {
		return strNationality;
	}

	public void setStrNationality(String strNationality) {
		this.strNationality = strNationality;
	}

}
