package org.green_pioneer.cruiseship_management.objectclasses;

public class Cabin {

	private int 	intCabinNo;
	private String 	strType;
	private String 	strDeck;
	private int 	intMaxPassengerCapacity;
	private double 	dblSizeInSqM;
	private int		intNoOfBeds;
	private int 	intNoOfToilets;
	private double 	dblPricePerPersonInEUR;
	
	public Cabin() {
		
	}
	
	public Cabin(String strType, String strDeck, int intMaxPassengerCapacity, double dblSizeInSqM, int intNoOfBeds,
			int intNoOfToilets, double dblPricePerPersonInEUR) {
		this.strType = strType;
		this.strDeck = strDeck;
		this.intMaxPassengerCapacity = intMaxPassengerCapacity;
		this.dblSizeInSqM = dblSizeInSqM;
		this.intNoOfBeds = intNoOfBeds;
		this.intNoOfToilets = intNoOfToilets;
		this.dblPricePerPersonInEUR = dblPricePerPersonInEUR;
	}
	
	public Cabin(int intCabinNo, String strType, String strDeck, int intMaxPassengerCapacity, double dblSizeInSqM,
			int intNoOfBeds, int intNoOfToilets, double dblPricePerPersonInEUR) {
		this.intCabinNo = intCabinNo;
		this.strType = strType;
		this.strDeck = strDeck;
		this.intMaxPassengerCapacity = intMaxPassengerCapacity;
		this.dblSizeInSqM = dblSizeInSqM;
		this.intNoOfBeds = intNoOfBeds;
		this.intNoOfToilets = intNoOfToilets;
		this.dblPricePerPersonInEUR = dblPricePerPersonInEUR;
	}
	

	
	public int getIntCabinNo() {
		return intCabinNo;
	}

	public void setIntCabinNo(int intCabinNo) {
		this.intCabinNo = intCabinNo;
	}

	public String getStrType() {
		return strType;
	}

	public void setStrType(String strType) {
		this.strType = strType;
	}

	public String getStrDeck() {
		return strDeck;
	}

	public void setStrDeck(String strDeck) {
		this.strDeck = strDeck;
	}

	public int getIntMaxPassengerCapacity() {
		return intMaxPassengerCapacity;
	}

	public void setIntMaxPassengerCapacity(int intMaxPassengerCapacity) {
		this.intMaxPassengerCapacity = intMaxPassengerCapacity;
	}

	public double getDblSizeInSqM() {
		return dblSizeInSqM;
	}

	public void setDblSizeInSqM(double dblSizeInSqM) {
		this.dblSizeInSqM = dblSizeInSqM;
	}

	public int getIntNoOfBeds() {
		return intNoOfBeds;
	}

	public void setIntNoOfBeds(int intNoOfBeds) {
		this.intNoOfBeds = intNoOfBeds;
	}

	public int getIntNoOfToilets() {
		return intNoOfToilets;
	}

	public void setIntNoOfToilets(int intNoOfToilets) {
		this.intNoOfToilets = intNoOfToilets;
	}

	public double getDblPricePerPersonInEUR() {
		return dblPricePerPersonInEUR;
	}

	public void setDblPricePerPersonInEUR(double dblPricePerPersonInEUR) {
		this.dblPricePerPersonInEUR = dblPricePerPersonInEUR;
	}

	@Override
	public String toString() {
		return "Cabin No: " + this.intCabinNo + " " + "Type: " + this.getStrType() + " " + "Deck: " + this.strDeck + " " + "Max passenger capacity:  " + this.getIntMaxPassengerCapacity() + " " +
				"No of beds: " + this.getIntNoOfBeds() + " " + "Number of toilets: " + this.getIntNoOfToilets() + " " + "Price per person in euro: " + this.getDblPricePerPersonInEUR();
	}

	
}
