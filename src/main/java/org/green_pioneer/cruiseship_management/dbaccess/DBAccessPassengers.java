/**	The function of this class is the deployment of database access to the table tblpassengers. It also contains methods
 * 	for adding, updating and deleting data.
 */

package org.green_pioneer.cruiseship_management.dbaccess;

import java.sql.SQLException;

import org.green_pioneer.cruiseship_management.errorhelper.ErrorHelper;
import org.green_pioneer.cruiseship_management.objectclasses.Passenger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBAccessPassengers extends DBMasterAccessor {

	/**	This method returns the passengers of the table in an obersavble list, specified by the first and data limit given
	 * 	as a parameter.
	 *
	 * @param intFirstDataSet The first dataset of the table.
	 * @param INT_DATA_LIMIT The number of datasets starting by the first dataset.
	 * @return The observablelist filled with data from the database.
	 */
	public ObservableList<Passenger> getPassengersInObservableList(int intFirstDataSet, final int INT_DATA_LIMIT) {
		
		ObservableList<Passenger> oblPassengers = FXCollections.observableArrayList();
		oblPassengers.clear();
		
		int intPassengerNo; 
		String strFirstName; 
		String strLastName; 
		String strGender;
		String strDateOfBirth;
		String strNationality;
		
		try {
			
			openDB();
			String strSQLStatement = "SELECT * FROM tblpassengers ORDER BY "
					+ "tblpassengers.passengerNo ASC LIMIT ?,? ;";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setInt(1, intFirstDataSet);
			stmt.setInt(2, INT_DATA_LIMIT);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
            	intPassengerNo = rs.getInt(1);
            	strFirstName = rs.getString(2);
            	strLastName = rs.getString(3);
            	strGender = rs.getString(4);
            	strDateOfBirth = rs.getString(5);
            	strNationality = rs.getString(6);
            	oblPassengers.add(new Passenger(intPassengerNo, strFirstName, strLastName, strGender, strDateOfBirth,
            			strNationality));
			}
			closeDB();
		} catch(SQLException sqlex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to get the passengers from the database!");
		}
		return oblPassengers;
	}

	/**	Inserts a new passenger in the database.
	 *
	 * @param strFirstName The first name of the new passenger.
	 * @param strLastName The last name of the new passenger.
	 * @param strGender The gender of the new passenger.
	 * @param strDateOfBirth The date of birth of the new passenger.
	 * @param strNationality The nationality of the new passenger.
	 */
	public void insertNewPassenger( String strFirstName, String strLastName, String strGender,
			String strDateOfBirth, String strNationality) {
		try {
			openDB();
			String strSQLStatement = "INSERT INTO tblpassengers ("
					+ "tblpassengers.passengerNo, "
					+ "tblpassengers.first_name, "
					+ "tblpassengers.last_name, "
					+ "tblpassengers.gender, "
					+ "tblpassengers.date_of_birth, "
					+ "tblpassengers.nationality) "
					+ "VALUES (NULL, ?, ?, ?, ?, ?);";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setString(1, strFirstName);
			stmt.setString(2, strLastName);
			stmt.setString(3, strGender);
			stmt.setString(4, strDateOfBirth);
			stmt.setString(5, strNationality);
			stmt.execute();
			closeDB();
			ErrorHelper.initInformationDialogWithoutHeader("A new passenger has been added to the database successfully!");
		} catch(SQLException sqlex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to add a new passenger to the database!");
		}
	}

	/** Updates an exisitng passenger in the database.
	 *
	 * @param intPassengerNo The number of the cabin which should be changed by the user. This parameter is required for the
	 * 	 *                   WHERE clause.
	 * @param intNewPassengerNo The new passenger number of the passenger.
	 * @param strFirstName The new first name of the passenger.
	 * @param strLastName The new last name of the passenger.
	 * @param strGender The new gender of the passenger.
	 * @param strDateOfBirth The new date of birth of the passenger.
	 * @param strNationality The new nationality of the passenger.
	 */
	public void updatePassenger(int intPassengerNo, int intNewPassengerNo, String strFirstName, String strLastName, 
			String strGender, String strDateOfBirth, String strNationality) {
		try {
			openDB();
			String strSQLStatement = "UPDATE tblpassengers SET "
					+ "tblpassengers.passengerNo = ?, "
					+ "tblpassengers.first_name = ?, "
					+ "tblpassengers.last_name = ?, "
					+ "tblpassengers.gender = ?, "
					+ "tblpassengers.date_of_birth = ?, "
					+ "tblpassengers.nationality = ? "
					+ "WHERE tblpassengers.passengerNo = ?;";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setInt(1, intNewPassengerNo);
			stmt.setString(2, strFirstName);
			stmt.setString(3, strLastName);
			stmt.setString(4, strGender);
			stmt.setString(5, strDateOfBirth);
			stmt.setString(6, strNationality);
			stmt.setInt(7, intPassengerNo);
			stmt.execute();
			closeDB();
			ErrorHelper.initInformationDialogWithoutHeader("The passenger " + intPassengerNo + " has been "
					+ "updated successfully in the database!");
		} catch(SQLException sqlex) { 
			ErrorHelper.initErrorDialogWithoutHeader("Unable to update data from tblpassengers in the database!");
		}
	}

	/**	Deletes an existing passenger of the database.
	 *
	 * @param intPassengerNo The passenger number of the passenger which should be deleted from the database.
	 */
	public void deletePassenger(int intPassengerNo) {
		try {
			openDB();
			String strSQLStatement = "DELETE FROM tblpassengers WHERE tblpassengers.passengerNo = ?;";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setInt(1, intPassengerNo);
			stmt.execute();
			closeDB();
			ErrorHelper.initInformationDialogWithoutHeader("The passenger " + intPassengerNo + " has been "
					+ "deleted successfully from the database!");
		} catch(SQLException sqlex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to delete passenger " + intPassengerNo + " from the database!");
		}
	}

	/**	Gets the number of the passengers.
	 *
	 * @return The number of the passengers as an int value.
	 */
	public int getNumberOfPassengers() {
		int getNumberOfPassengers = 0;
		
		try {
			openDB();
			String strSQLStatement = "SELECT COUNT(*) FROM tblpassengers;";
			stmt = con.prepareStatement(strSQLStatement);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				getNumberOfPassengers = rs.getInt(1);
			}
			
			closeDB();
		} catch(SQLException sqlex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to get the number of passengers from the database!");
		}
		return getNumberOfPassengers;
	}

	/** Sorts the passengers by their last name.
	 *
	 * @param intFirstDataSet The first dataset of the table.
	 * @param INT_DATA_LIMIT The number of datasets starting by the first dataset.
	 * @return The observablelist filled with the sorted data from the database.
	 */
	public ObservableList<Passenger> sortByLastName(int intFirstDataSet, final int INT_DATA_LIMIT) {
		
		ObservableList<Passenger> oblSortedPassengers = FXCollections.observableArrayList();
		oblSortedPassengers.clear();
		
		int intPassengerNo; 
		String strFirstName; 
		String strLastName; 
		String strGender;
		String strDateOfBirth;
		String strNationality;
		
		try {
			
			openDB();
			String strSQLStatement = "SELECT * FROM tblpassengers ORDER BY tblpassengers.last_name ASC LIMIT ?,? ;";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setInt(1, intFirstDataSet);
			stmt.setInt(2, INT_DATA_LIMIT);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
            	intPassengerNo = rs.getInt(1);
            	strFirstName = rs.getString(2);
            	strLastName = rs.getString(3);
            	strGender = rs.getString(4);
            	strDateOfBirth = rs.getString(5);
            	strNationality = rs.getString(6);
            	oblSortedPassengers.add(new Passenger(intPassengerNo, strFirstName, strLastName, strGender, strDateOfBirth,
            			strNationality));
			}
			closeDB();
		} catch(SQLException ex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to sort the passengers in the database!");
		}
		return oblSortedPassengers;
	}
}
