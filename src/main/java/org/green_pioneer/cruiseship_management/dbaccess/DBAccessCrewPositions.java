/**	The function of this class is the deployment of database access to the table tblCrewpositions. It also contains methods
 * 	for adding, updating and deleting data.
 */

package org.green_pioneer.cruiseship_management.dbaccess;

import java.sql.SQLException;

import org.green_pioneer.cruiseship_management.errorhelper.ErrorHelper;
import org.green_pioneer.cruiseship_management.objectclasses.CrewPosition;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBAccessCrewPositions extends DBMasterAccessor {

	/**	This method returns the crew postions of the table in an obersavble list, specified by the first and data limit given
	 * 	as a parameter.
	 *
	 * @param intFirstDataSet The first dataset of the table.
	 * @param INT_DATA_LIMIT The number of datasets starting by the first dataset.
	 * @return The observablelist filled with data from the database.
	 */
	public ObservableList<CrewPosition> getCrewPositionsInObservableList(int intFirstDataSet, final int INT_DATA_LIMIT) {
	
		ObservableList<CrewPosition> oblCrewPositions = FXCollections.observableArrayList();
		oblCrewPositions.clear();
		
		String strPosition;
		String strDescription;
		
		try {
			
			openDB();
			String strSQLStatement = "SELECT * FROM tblcrewpositions ORDER BY tblcrewpositions.position ASC LIMIT ?,? ;";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setInt(1, intFirstDataSet);
			stmt.setInt(2, INT_DATA_LIMIT);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				strPosition = rs.getString(1);
				strDescription = rs.getString(2);
	        	oblCrewPositions.add(new CrewPosition(strPosition, strDescription));
			}
			closeDB();
		} catch(SQLException sqlex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to get the data of tblcrewposition from the database!");
		}
		return oblCrewPositions;
	}

	/**	Inserts a new crew position in the database.
	 *
	 * @param strPosition The position of the new crew position.
	 * @param strDescription The description of the new crew position.
	 */
	public void insertNewCrewPosition(String strPosition, String strDescription) {
		try {
			openDB();
			String strSQLStatement = "INSERT INTO tblcrewpositions ("
					+ "tblcrewpositions.position, "
					+ "tblcrewpositions.description"
					+ ") VALUES (?, ?);";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setString(1, strPosition);
			stmt.setString(2, strDescription);
			stmt.execute();
			closeDB();
			ErrorHelper.initInformationDialogWithoutHeader("A new crew position has been added to the database successfully!");
		} catch(SQLException sqlex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to add a new crew position type to the database!");
		}
	}

	/**	Updates an existing crew position in the database.
	 *
	 * @param strPosition The position of the crew position which should be changed by the user. This parameter is required for the
	 *                    WHERE clause.
	 * @param strNewPosition The new position of the crew position.
	 * @param strDescription The new description of the crew position.
	 */
	public void updateCrewPosition(String strPosition, String strNewPosition, String strDescription) {
		try {
			openDB();
			String strSQLStatement = "UPDATE tblcrewpositions SET "
					+ "tblcrewpositions.position = ?, "
					+ "tblcrewpositions.description = ? "
					+ "WHERE tblcrewpositions.position = ?;";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setString(1, strNewPosition);
			stmt.setString(2, strDescription);
			stmt.setString(3, strPosition);
			stmt.execute();
			closeDB();
			ErrorHelper.initInformationDialogWithoutHeader("The crew position " + strPosition + " was updated successfully "
					+ "in the database!");
		} catch(SQLException sqlex) { 
			ErrorHelper.initErrorDialogWithoutHeader("Unable to update " + strPosition + " in the database!");
		}
	}

	/** Deletes an exiting crew position from the database.
	 *
	 * @param strPosition The crew position which should be deleted from the database.
	 */
	public void deleteCrewPosition(String strPosition) {
		try {
			openDB();
			String strSQLStatement = "DELETE FROM tblcrewpositions WHERE tblcrewpositions.position = ?;";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setString(1, strPosition);
			stmt.execute();
			closeDB();
			ErrorHelper.initInformationDialogWithoutHeader("The crew position " + strPosition + " was deleted "
					+ "from the database successfully!");
		} catch(SQLException sqlex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to delete crew position " + strPosition + " from the database!");
		}
	}

	/** Gets the number of crew positions.
	 *
	 * @return The number of crew positions as an int value.
	 */
	public int getNumberOfCrewPositions() {
		int intNumberOfCrewPositions = 0;
		
		try {
			openDB();
			String strSQLStatement = "SELECT COUNT(*) FROM tblcrewpositions;";
			stmt = con.prepareStatement(strSQLStatement);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				intNumberOfCrewPositions = rs.getInt(1);
			}
			
			closeDB();
		} catch(SQLException sqlex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to get the number of the crew positions from the database!");
		}
		return intNumberOfCrewPositions;
	}

	/** Gets the crew positions only.
	 *
	 * @return The observablelist with filled data of the crew positions only. This method is required by some
	 * 			comboboxes.
	 */
	public ObservableList<Object> getCrewPositionsOnly() {
		ObservableList<Object> oblCabinTypes = FXCollections.observableArrayList();
		
		try {
			openDB();
			String strSQLStatement = "SELECT * FROM tblcrewpositions;";
			stmt = con.prepareStatement(strSQLStatement);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				oblCabinTypes.add(rs.getString(1));
			}
			
			closeDB();
		} catch(SQLException sqlex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to get the crewposition only from the database!");
		}
		return oblCabinTypes;
	}
}
