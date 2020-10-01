package org.green_pioneer.cruiseship_management.objectclasses;

public class Cruise {

	private int intCruiseNo;
	private int intPassengerNo;
	private int intCabinNo;
	private String strOriginPortLOCODE;
	private String strDestinationPortLOCODE;
	private String strCruiseStart;
	private String strCruiseEnd;
	
	
	
	public Cruise() {
		
	}
	
	public Cruise(int intCruiseNo, int intPassengerNo, int intCabinNo, String strOriginPortLOCODE,
			String strDestinationPortLOCODE, String strCruiseStart, String strCruiseEnd) {
		super();
		this.intCruiseNo = intCruiseNo;
		this.intPassengerNo = intPassengerNo;
		this.intCabinNo = intCabinNo;
		this.strOriginPortLOCODE = strOriginPortLOCODE;
		this.strDestinationPortLOCODE = strDestinationPortLOCODE;
		this.strCruiseStart = strCruiseStart;
		this.strCruiseEnd = strCruiseEnd;
	}

	
	
	public int getIntCruiseNo() {
		return intCruiseNo;
	}

	public void setIntCruiseNo(int intCruiseNo) {
		this.intCruiseNo = intCruiseNo;
	}

	public int getIntPassengerNo() {
		return intPassengerNo;
	}

	public void setIntPassengerNo(int intPassengerNo) {
		this.intPassengerNo = intPassengerNo;
	}

	public int getIntCabinNo() {
		return intCabinNo;
	}

	public void setIntCabinNo(int intCabinNo) {
		this.intCabinNo = intCabinNo;
	}

	public String getStrOriginPortLOCODE() {
		return strOriginPortLOCODE;
	}

	public void setStrOriginPortLOCODE(String strOriginPortLOCODE) {
		this.strOriginPortLOCODE = strOriginPortLOCODE;
	}

	public String getStrDestinationPortLOCODE() {
		return strDestinationPortLOCODE;
	}

	public void setStrDestinationPortLOCODE(String strDestinationPortLOCODE) {
		this.strDestinationPortLOCODE = strDestinationPortLOCODE;
	}

	public String getStrCruiseStart() {
		return strCruiseStart;
	}

	public void setStrCruiseStart(String strCruiseStart) {
		this.strCruiseStart = strCruiseStart;
	}

	public String getStrCruiseEnd() {
		return strCruiseEnd;
	}

	public void setStrCruiseEnd(String strCruiseEnd) {
		this.strCruiseEnd = strCruiseEnd;
	}
	
}
