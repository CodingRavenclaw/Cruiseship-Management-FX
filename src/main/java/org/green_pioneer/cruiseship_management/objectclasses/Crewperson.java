package org.green_pioneer.cruiseship_management.objectclasses;

public class Crewperson extends Person {

	private int intCrewpersonNo;
	private String strDateOfEmployment;
	private String strPosition;
	private double dblSalary;
	
	private int intNumberOfCrewpersons;
	
	public Crewperson() {
		
	}

	public Crewperson(int intNumberOfCrewpersons, String strNationality) {
		this.intNumberOfCrewpersons = intNumberOfCrewpersons;
		this.strNationality = strNationality;
	}
	
	public Crewperson(String strFirstName, String strLastName, String strDateOfBirth, String strDateOfEmployment,
			String strGender, String strNationality, String strPosition, double dblSalary) {
		super(strFirstName, strLastName, strDateOfBirth, strGender, strNationality);
		this.strDateOfEmployment = strDateOfEmployment;
		this.strPosition = strPosition;
		this.dblSalary = dblSalary;
	}

	public Crewperson(int intCrewpersonNo, String strFirstName, String strLastName, String strDateOfBirth,
			String strDateOfEmployment, String strGender, String strNationality, String strPosition, double dblSalary) {
		super(strFirstName, strLastName, strDateOfBirth, strGender, strNationality);
		this.intCrewpersonNo = intCrewpersonNo;
		this.strDateOfEmployment = strDateOfEmployment;
		this.strPosition = strPosition;
		this.dblSalary = dblSalary;
	}

	
	
	public int getIntCrewpersonNo() {
		return intCrewpersonNo;
	}

	public void setIntCrewpersonNo(int intCrewpersonNo) {
		this.intCrewpersonNo = intCrewpersonNo;
	}

	public String getStrDateOfEmployment() {
		return strDateOfEmployment;
	}

	public void setStrDateOfEmployment(String strDateOfEmployment) {
		this.strDateOfEmployment = strDateOfEmployment;
	}

	public String getStrPosition() {
		return strPosition;
	}

	public void setStrPosition(String strPosition) {
		this.strPosition = strPosition;
	}

	public double getDblSalary() {
		return dblSalary;
	}

	public void setDblSalary(double dblSalary) {
		this.dblSalary = dblSalary;
	}

	
	
	public int getIntNumberOfCrewpersons() {
		return intNumberOfCrewpersons;
	}

	public void setIntNumberOfCrewpersons(int intNumberOfCrewpersons) {
		this.intNumberOfCrewpersons = intNumberOfCrewpersons;
	}
	
}
