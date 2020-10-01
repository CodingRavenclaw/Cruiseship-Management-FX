package org.green_pioneer.cruiseship_management.objectclasses;

public class Port {

	private String strLOCODE;
	private String strName;
	private String strLocation;
	private String strCountry;
	private String strWaters;
	private double dblDegreeLat;
	private double dblArcminuteLat;
	private double dblArcsecondLat;
	private String strCardinalPointLat;
	private double dblDegreeLong;
	private double dblArcminuteLong;
	private double dblArcsecondLong;
	private String strCardinalPointLong;
	private String strOperator;
	
	
	
	public Port(String strLOCODE, String strName, String strLocation, String strCountry, String strWaters,
			double dblDegreeLat, double dblArcminuteLat, double dblArcsecondLat, String strCardinalPointLat,
			double dblDegreeLong, double dblArcminuteLong, double dblArcsecondLong, String strCardinalPointLong,
			String strOperator) {
		this.strLOCODE = strLOCODE;
		this.strName = strName;
		this.strLocation = strLocation;
		this.strCountry = strCountry;
		this.strWaters = strWaters;
		this.dblDegreeLat = dblDegreeLat;
		this.dblArcminuteLat = dblArcminuteLat;
		this.dblArcsecondLat = dblArcsecondLat;
		this.strCardinalPointLat = strCardinalPointLat;
		this.dblDegreeLong = dblDegreeLong;
		this.dblArcminuteLong = dblArcminuteLong;
		this.dblArcsecondLong = dblArcsecondLong;
		this.strCardinalPointLong = strCardinalPointLong;
		this.strOperator = strOperator;
	}

	
	
	public String getStrLOCODE() {
		return strLOCODE;
	}

	public void setStrLOCODE(String strLOCODE) {
		this.strLOCODE = strLOCODE;
	}

	public String getStrName() {
		return strName;
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}

	public String getStrLocation() {
		return strLocation;
	}

	public void setStrLocation(String strLocation) {
		this.strLocation = strLocation;
	}

	public String getStrCountry() {
		return strCountry;
	}

	public void setStrCountry(String strCountry) {
		this.strCountry = strCountry;
	}

	public String getStrWaters() {
		return strWaters;
	}

	public void setStrWaters(String strWaters) {
		this.strWaters = strWaters;
	}

	public double getDblDegreeLat() {
		return dblDegreeLat;
	}

	public void setDblDegreeLat(double dblDegreeLat) {
		this.dblDegreeLat = dblDegreeLat;
	}

	public double getDblArcminuteLat() {
		return dblArcminuteLat;
	}

	public void setDblArcminuteLat(double dblArcminuteLat) {
		this.dblArcminuteLat = dblArcminuteLat;
	}

	public double getDblArcsecondLat() {
		return dblArcsecondLat;
	}

	public void setDblArcsecondLat(double dblArcsecondLat) {
		this.dblArcsecondLat = dblArcsecondLat;
	}

	public String getStrCardinalPointLat() {
		return strCardinalPointLat;
	}

	public void setStrCardinalPointLat(String strCardinalPointLat) {
		this.strCardinalPointLat = strCardinalPointLat;
	}

	public double getDblDegreeLong() {
		return dblDegreeLong;
	}

	public void setDblDegreeLong(double dblDegreeLong) {
		this.dblDegreeLong = dblDegreeLong;
	}

	public double getDblArcminuteLong() {
		return dblArcminuteLong;
	}

	public void setDblArcminuteLong(double dblArcminuteLong) {
		this.dblArcminuteLong = dblArcminuteLong;
	}

	public double getDblArcsecondLong() {
		return dblArcsecondLong;
	}

	public void setDblArcsecondLong(double dblArcsecondLong) {
		this.dblArcsecondLong = dblArcsecondLong;
	}

	public String getStrCardinalPointLong() {
		return strCardinalPointLong;
	}

	public void setStrCardinalPointLong(String strCardinalPointLong) {
		this.strCardinalPointLong = strCardinalPointLong;
	}

	public String getStrOperator() {
		return strOperator;
	}

	public void setStrOperator(String strOperator) {
		this.strOperator = strOperator;
	}
	
	
}
