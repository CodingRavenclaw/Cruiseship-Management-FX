/**	The function of this class is the deployment of database access to the table tblcabins. It also contains methods
 * 	for adding, updating and deleting data.
 */

package org.green_pioneer.cruiseship_management.dbaccess;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.green_pioneer.cruiseship_management.errorhelper.ErrorHelper;
import org.green_pioneer.cruiseship_management.objectclasses.Cabin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class DBAccessCabins extends DBMasterAccessor {

	/**	This method returns the cabins of the table in an obersavble list, specified by the first and data limit given
	 * 	as a parameter.
	 *
	 * @param intFirstDataSet The first dataset of the table.
	 * @param INT_DATA_LIMIT The number of datasets starting by the first dataset.
	 * @return The observablelist filled with data from the database.
	 */
	public ObservableList<Cabin> getCabinsInObservableList(int intFirstDataSet, final int INT_DATA_LIMIT) {

		ObservableList<Cabin> oblCabins = FXCollections.observableArrayList();
		oblCabins.clear();

		int 	intCabinNo;
		String 	strType;
		String 	strDeck;
		int 	intMaxPassengerCapacity;
		double 	dblSizeInSqM;
		int		intNoOfBeds;
		int 	intNoOfToilets;
		double 	dblPricePerPersonInEUR;
	
		try {
			
			openDB();
			String strSQLStatement = "SELECT * FROM tblcabins ORDER BY tblcabins.cabinNo ASC LIMIT ?,? ;";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setInt(1, intFirstDataSet);
			stmt.setInt(2, INT_DATA_LIMIT);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
            	intCabinNo = rs.getInt(1);
            	strType = rs.getString(2);
            	strDeck = rs.getString(3);
            	intMaxPassengerCapacity = rs.getInt(4);
            	dblSizeInSqM = rs.getDouble(5);
            	intNoOfBeds = rs.getInt(6);
            	intNoOfToilets = rs.getInt(7);
            	dblPricePerPersonInEUR = rs.getInt(8);
            	oblCabins.add(new Cabin(intCabinNo, strType, strDeck, intMaxPassengerCapacity, dblSizeInSqM, 
            			intNoOfBeds, intNoOfToilets, dblPricePerPersonInEUR));
			}
			closeDB();
		} catch(SQLException ex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to get the cabins from the database!");
		}
		return oblCabins;
	}

	/**	Inserts a new cabin in the database.
	 *
	 * @param strType The type of the new cabin.
	 * @param strDeck The deck of the new cabin.
	 * @param intMaxPassengerCapacity The max passenger capacity of the new cabin.
	 * @param dblSizeInSqm The size in sqm of the new cabin.
	 * @param intNoOfBeds The no of beds of the new cabin.
	 * @param intNoOfToilets The no of toilets of the new cabin.
	 * @param dblPricePerPerson the price per person and per night of the new cabin.
	 */
	public void insertNewCabin(String strType, String strDeck, int intMaxPassengerCapacity, 
			double dblSizeInSqm, int intNoOfBeds, int intNoOfToilets, double dblPricePerPerson) {
		try {
			openDB();
			String strSQLStatement = "INSERT INTO tblcabins (tblcabins.cabinNo, tblcabins.type, tblcabins.deck, "
					+ "tblcabins.max_passenger_capacity, tblcabins.size_in_sq_m, tblcabins.no_of_beds, "
					+ "tblcabins.no_of_toilets, tblcabins.price_per_person_in_EUR) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?);";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setString(1, strType);
			stmt.setString(2, strDeck);
			stmt.setInt(3, intMaxPassengerCapacity);
			stmt.setDouble(4, dblSizeInSqm);
			stmt.setInt(5, intNoOfBeds);
			stmt.setInt(6, intNoOfToilets);
			stmt.setDouble(7, dblPricePerPerson);
			stmt.execute();
			closeDB();
			ErrorHelper.initInformationDialogWithoutHeader("A new cabin has been added to the database successfully!");
		} catch(SQLException ex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to add a new cabin to the database!");
		}
	}

	/**	Updates an existing cabin in the database.
	 *
	 * @param intCabinNo The number of the cabin which should be changed by the user. This parameter is required for the
	 *                   WHERE clause.
	 * @param intNewCabinNo The new cabin number of the cabin.
	 * @param strType The new type of the cabin.
	 * @param strDeck The new deck of the cabin.
	 * @param intMaxPassengerCapacity The new max passenger capacity of the cabin.
	 * @param dblSizeInSqm The new size in sqm of the cabin.
	 * @param intNoOfBeds The new no of beds of the cabin.
	 * @param intNoOfToilets The new no of toilets of the cabin.
	 * @param dblPricePerPerson The new price per person and per night of the cabin.
	 */
	public void updateCabin(int intCabinNo, int intNewCabinNo, String strType, String strDeck, int intMaxPassengerCapacity, 
			double dblSizeInSqm, int intNoOfBeds, int intNoOfToilets, double dblPricePerPerson) {
		try {
			openDB();
			String strSQLStatement = "UPDATE tblcabins SET tblcabins.cabinNo = ?, tblcabins.type = ?, tblcabins.deck = ?, "
					+ "tblcabins.max_passenger_capacity = ?, tblcabins.size_in_sq_m = ?, tblcabins.no_of_beds = ?, "
					+ "tblcabins.no_of_toilets = ?, tblcabins.price_per_person_in_EUR = ? WHERE tblcabins.cabinNo = ?;";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setInt(1, intNewCabinNo);
			stmt.setString(2, strType);
			stmt.setString(3, strDeck);
			stmt.setInt(4, intMaxPassengerCapacity);
			stmt.setDouble(5, dblSizeInSqm);
			stmt.setInt(6, intNoOfBeds);
			stmt.setInt(7, intNoOfToilets);
			stmt.setDouble(8, dblPricePerPerson);
			stmt.setInt(9, intCabinNo);
			stmt.execute();
			closeDB();
			ErrorHelper.initInformationDialogWithoutHeader("The cabin " + intCabinNo + " has been updated "
					+ "in the database successfully!");
		} catch(SQLIntegrityConstraintViolationException ex) {
			ErrorHelper.initErrorDialog("Unable update the cabin!", "One or more columns having values from "
					+ "their parent tables as a foreign key! Change them in the specific table!");
		} catch(SQLException ex) { 
			ErrorHelper.initErrorDialogWithoutHeader("Unable to update the cabin " + intCabinNo + " from the"
					+ "database!");
		}
	}

	/**	Deletes an existing cabin from the database.
	 *
	 * @param intCabinNo The number of the cabin which should be deleted from the database.
	 */
	public void deleteCabin(int intCabinNo) {
		try {
			openDB();
			String strSQLStatement = "DELETE FROM tblcabins WHERE tblcabins.cabinNo = ?;";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setInt(1, intCabinNo);
			stmt.execute();
			closeDB();
			ErrorHelper.initInformationDialogWithoutHeader("The cabin " + intCabinNo + " has been deleted "
					+ "from the database successfully!");
		} catch(SQLException ex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to delete cabin " + intCabinNo + "from the"
					+ "database!");
		}
	}

	/**	Returns the number of cabins with a COUNT statement.
	 *
	 * @return The number of cabins as an int.
	 */
	public int getNumberOfCabins() {
		int intNumberOfCabins = 0;
		
		try {
			openDB();
			String strSQLStatement = "SELECT COUNT(*) FROM tblcabins;";
			stmt = con.prepareStatement(strSQLStatement);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				intNumberOfCabins = rs.getInt(1);
			}
			
			closeDB();
		} catch(SQLException ex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to get the number of cabins from the database!");
		}
		return intNumberOfCabins;
	}

	/**	There were more methods of database operations (e.g. get the highest passenger capacity of a cabin, get the price
	 * 	of the most expensive cabin, etc.) but they have been replaced by MicrostreamDB. You can find them in the class
	 * 	MSDBCabinsAccessor.java in the package microstreamdb.
	 */
}
