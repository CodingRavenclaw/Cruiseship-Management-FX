/**	The function of this class is the deployment of database access to the table tblCruises. It also contains methods
 * 	for adding, updating and deleting data.
 */

package org.green_pioneer.cruiseship_management.dbaccess;

import java.sql.SQLException;

import org.green_pioneer.cruiseship_management.errorhelper.ErrorHelper;
import org.green_pioneer.cruiseship_management.objectclasses.Cruise;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBAccessCruises extends DBMasterAccessor {

	/**	This method returns the cruises of the table in an obersavble list, specified by the first and data limit given
	 * 	as a parameter.
	 *
	 * @param intFirstDataSet The first dataset of the table.
	 * @param INT_DATA_LIMIT The number of datasets starting by the first dataset.
	 * @return The observablelist filled with data from the database.
	 */
	public ObservableList<Cruise> getCruisesInObservableList(int intFirstDataSet, final int INT_DATA_LIMIT) {
		
		ObservableList<Cruise> oblCruises = FXCollections.observableArrayList();
		oblCruises.clear();
		
		int intCruiseNo;
		int intPassengerNo;
		int intCabinNo;
		String strOriginPortLOCODE;
		String strDestinationPortLOCODE;
		String strCruiseStart;
		String strCruiseEnd;
		
		try {
			
			openDB();
			String strSQLStatement = "SELECT * FROM tblcruises ORDER BY tblcruises.cruiseNo ASC LIMIT ?,? ;";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setInt(1, intFirstDataSet);
			stmt.setInt(2, INT_DATA_LIMIT);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				intCruiseNo = rs.getInt(1);
				intPassengerNo = rs.getInt(2);
				intCabinNo = rs.getInt(3);
				strOriginPortLOCODE = rs.getString(4);
				strDestinationPortLOCODE = rs.getString(5);
				strCruiseStart = rs.getString(6);
				strCruiseEnd = rs.getString(7);
            	oblCruises.add(new Cruise(intCruiseNo, intPassengerNo, intCabinNo, strOriginPortLOCODE, 
            			strDestinationPortLOCODE, strCruiseStart, strCruiseEnd));
			}
			closeDB();
		} catch(SQLException sqlex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to get the data of tblcruises from the database!");
		}
		return oblCruises;
	}

	/**	Inserts a new cruise in the database.
	 *
	 * @param intCruiseNo The cruise number of the new cruise.
	 * @param intPassengerNo The passenger number of the new cruise.
	 * @param intCabinNo The cabin number of the new cruise.
	 * @param strOriginPortLOCODE The LOCODE of the origin port of the new cruise.
	 * @param strDestinationPortLOCODE The LOCODE of the destination port of the new cruise.
	 * @param strCruiseStart The start date of the cruise.
	 * @param strCruiseEnd The end date of the cruise.
	 */
	public void insertNewCruise(int intCruiseNo, int intPassengerNo, int intCabinNo, String strOriginPortLOCODE,
			String strDestinationPortLOCODE, String strCruiseStart, String strCruiseEnd) {
		try {
			openDB();
			String strSQLStatement = "INSERT INTO tblcruises ("
					+ "tblcruises.cruiseNo, "
					+ "tblcruises.passengerNo, "
					+ "tblcruises.cabinNo, "
					+ "tblcruises.origin_port_LOCODE, "
					+ "tblcruises.destination_port_LOCODE, "
					+ "tblcruises.cruise_start, "
					+ "tblcruises.cruise_end) VALUES (?, ?, ?, ?, ?, ?, ?);";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setInt(1, intCruiseNo);
			stmt.setInt(2, intPassengerNo);
			stmt.setInt(3, intCabinNo);
			stmt.setString(4, strOriginPortLOCODE);
			stmt.setString(5, strDestinationPortLOCODE);
			stmt.setString(6, strCruiseStart);
			stmt.setString(7, strCruiseEnd);
			stmt.execute();
			closeDB();
			ErrorHelper.initInformationDialogWithoutHeader("A new cruise has been added to the database successfully!");
		} catch(SQLException sqlex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to add a new cruise to the database!");
		}
	}

	/**	Updates an existing cruise in the database.
	 *
	 * @param intCruiseNo The number of the cruise which should be changed by the user. This parameter is required for the
	 * 	 *                   WHERE clause.
	 * @param intNewCruiseNo The new number of the cruise.
	 * @param intPassengerNo The number of the passenger in the cruise which should be changed by the user. This parameter is required for the
	 * 	 *                   WHERE clause.
	 * @param intNewPassengerNo The new passenger number of the cruise.
	 * @param intCabinNo The new cabin no of the cruise.
	 * @param strOriginPortLOCODE The new LOCODE of the origin port of the cruise.
	 * @param strDestinationPortLOCODE The new LOCODE of the destination port of the cruise.
	 * @param strCruiseStart The new start date of the cruise.
	 * @param strCruiseEnd The new end date of the cruise.
	 */
	public void updateCruise(int intCruiseNo, int intNewCruiseNo, int intPassengerNo, int intNewPassengerNo, 
			int intCabinNo, String strOriginPortLOCODE, String strDestinationPortLOCODE, String strCruiseStart, 
			String strCruiseEnd) {
		try {
			openDB();
			String strSQLStatement = "UPDATE tblcruises SET "
					+ "tblcruises.cruiseNo = ?, "
					+ "tblcruises.passengerNo = ?, "
					+ "tblcruises.cabinNo = ?, "
					+ "tblcruises.origin_port_LOCODE = ?, "
					+ "tblcruises.destination_port_LOCODE = ?, "
					+ "tblcruises.cruise_start = ?, "
					+ "tblcruises.cruise_end = ? "
					+ "WHERE tblcruises.cruiseNo = ? AND "
					+ "tblcruises.passengerNo = ?;";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setInt(1, intNewCruiseNo);
			stmt.setInt(2, intNewPassengerNo);
			stmt.setInt(3, intCabinNo);
			stmt.setString(4, strOriginPortLOCODE);
			stmt.setString(5, strDestinationPortLOCODE);
			stmt.setString(6, strCruiseStart);
			stmt.setString(7, strCruiseEnd);
			stmt.setInt(8, intCruiseNo);
			stmt.setInt(9, intPassengerNo);
			stmt.execute();
			closeDB();
			ErrorHelper.initInformationDialogWithoutHeader("The cruise " + intCruiseNo + " of passenger " + intPassengerNo + " has been updated "
					+ "successfully!");
		} catch(SQLException sqlex) { 
			ErrorHelper.initErrorDialogWithoutHeader("Unable to update data from tblcruises in the database!");
		}
	}

	/**	Deletes an existing cruise from the database.
	 *
	 * @param intCruiseNo The number of the cruise.
	 * @param intPassengerNo The passenger no of the passenger in the cruise.
	 */
	public void deleteCruise(int intCruiseNo, int intPassengerNo) {
		try {
			openDB();
			String strSQLStatement = "DELETE FROM tblcruises WHERE tblcruises.cruiseNo = ? AND "
					+ "tblcruises.passengerNo = ?;";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setInt(1, intCruiseNo);
			stmt.setInt(2, intPassengerNo);
			stmt.execute();
			closeDB();
			ErrorHelper.initInformationDialogWithoutHeader("The cruise with the No " + intCruiseNo + " of passenger " + 
			intPassengerNo + "has been deleted from the database successfully!");
		} catch(SQLException sqlex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to delete cruise with the No " + intCruiseNo + " of passenger " + 
					intPassengerNo + " from the database!");
		}
	}

	/**	Gets the number of the cruises.
	 *
	 * @return The number of the cruises as an int value.
	 */
	public int getNumberOfCruises() {
		int intNumberOfCruises = 0;
		
		try {
			openDB();
			String strSQLStatement = "SELECT COUNT(*) FROM tblcruises;";
			stmt = con.prepareStatement(strSQLStatement);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				intNumberOfCruises = rs.getInt(1);
			}
			
			closeDB();
		} catch(SQLException sqlex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to get the number of the cruises!");
		}
		return intNumberOfCruises;
	}
}
