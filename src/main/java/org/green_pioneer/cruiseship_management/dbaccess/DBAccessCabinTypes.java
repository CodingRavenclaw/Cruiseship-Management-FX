/**	The function of this class is the deployment of database access to the table tblcabin types. It also contains methods
 * 	for adding, updating and deleting data.
 */
package org.green_pioneer.cruiseship_management.dbaccess;

import java.sql.SQLException;

import org.green_pioneer.cruiseship_management.errorhelper.ErrorHelper;
import org.green_pioneer.cruiseship_management.objectclasses.CabinType;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBAccessCabinTypes extends DBMasterAccessor {

	/**	This method returns the cabin types of the table in an obersavble list, specified by the first and data limit given
	 * 	as a parameter.
	 *
	 * @param intFirstDataSet The first dataset of the table.
	 * @param INT_DATA_LIMIT The number of datasets starting by the first dataset.
	 * @return The observablelist filled with data from the database.
	 */
	public ObservableList<CabinType> getCabinTypesInObservableList(int intFirstDataSet, final int INT_DATA_LIMIT) {
		
		ObservableList<CabinType> oblCabinTypes = FXCollections.observableArrayList();
		oblCabinTypes.clear();
		
		String strType;
		String strDescription;
		String strEquipment;
		
		try {
			
			openDB();
			String strSQLStatement = "SELECT * FROM tblcabintypes ORDER BY tblcabintypes.type ASC LIMIT ?,? ;";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setInt(1, intFirstDataSet);
			stmt.setInt(2, INT_DATA_LIMIT);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				strType = rs.getString(1);
				strDescription = rs.getString(2);
				strEquipment = rs.getString(3);
            	oblCabinTypes.add(new CabinType(strType, strDescription, strEquipment));
			}
			closeDB();
		} catch(SQLException sqlex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to get the cabin types from the database!");
		}
		return oblCabinTypes;
	}

	/**	Inserts a new cabin type in the database.
	 *
	 * @param strType The type of the new cabin type.
	 * @param strDescription The description of the new cabin type.
	 * @param strEquipment The equipment of the new cabin type.
	 */
	public void insertNewCabinType(String strType, String strDescription, String strEquipment) {
		try {
			openDB();
			String strSQLStatement = "INSERT INTO tblcabintypes (tblcabintypes.type, tblcabintypes.description, "
					+ "tblcabintypes.equipment) VALUES (?, ?, ?);";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setString(1, strType);
			stmt.setString(2, strDescription);
			stmt.setString(3, strEquipment);
			stmt.execute();
			closeDB();
			ErrorHelper.initInformationDialogWithoutHeader("A new cabin type has been added to the database "
					+ "successfully!"); 
		} catch(SQLException sqlex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to add a new cabin type to the database!");
		}
	}

	/**	Updates an existing cabin type in the database.
	 *
	 * @param strType The type of the cabin type which should be changed by the user. This parameter is required for the
	 * 	 *            WHERE clause.
	 * @param strNewType The new type of the cabin type.
	 * @param strDescription The new description of the cabin type.
	 * @param strEquipment The new equipment of the cabin type.
	 */
	public void updateCabinType(String strType, String strNewType, String strDescription, String strEquipment) {
		try {
			openDB();
			String strSQLStatement = "UPDATE tblcabintypes SET tblcabintypes.type = ?, tblcabintypes.description = ?, "
					+ "tblcabintypes.equipment = ? WHERE tblcabintypes.type = ?;";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setString(1, strNewType);
			stmt.setString(2, strDescription);
			stmt.setString(3, strEquipment);
			stmt.setString(4, strType);
			stmt.execute();
			closeDB();
			ErrorHelper.initInformationDialogWithoutHeader("The cabin type " + strType + " was updated successfully!");
		} catch(SQLException sqlex) { 
			ErrorHelper.initErrorDialogWithoutHeader("Unable to update the cabin type from the database!");
		}
	}

	/**	Deletes an existing cabin type from the database.
	 *
	 * @param strType The type of the cabin type which should be deleted from the database.
	 */
	public void deleteCabinType(String strType) {
		try {
			openDB();
			String strSQLStatement = "DELETE FROM tblcabintypes WHERE tblcabintypes.type = ?;";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setString(1, strType);
			stmt.execute();
			closeDB();
			ErrorHelper.initInformationDialogWithoutHeader("The cabin type " + strType + " has been deleted "
					+ "successfully from the database!");
		} catch(SQLException sqlex) { 
			ErrorHelper.initErrorDialogWithoutHeader("Unable to delete the cabin type from the database!");
		}
	}

	/**	Returns the number of cabin types.
	 *
	 * @return The number of cabin types as an int value.
	 */
	public int getNumberOfCabinTypes() {
		int intNumberOfCabinTypes = 0;
		
		try {
			openDB();
			String strSQLStatement = "SELECT COUNT(*) FROM tblcabintypes;";
			stmt = con.prepareStatement(strSQLStatement);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				intNumberOfCabinTypes = rs.getInt(1);
			}
			
			closeDB();
		} catch(SQLException sqlex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to count the number of cabin types "
					+ "from the database!");
		}
		return intNumberOfCabinTypes;
	}

	/** Returns an oversablelist of the cabin types. This method is required by some comboboxes.
	 *
	 * @return An observablelist filled with the cabin types.
	 */
	public ObservableList<Object> getCabinTypesOnly() {
		ObservableList<Object> oblCabinTypes = FXCollections.observableArrayList();
		
		try {
			openDB();
			String strSQLStatement = "SELECT * FROM tblcabintypes;";
			stmt = con.prepareStatement(strSQLStatement);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				oblCabinTypes.add(rs.getString(1));
			}
			
			closeDB();
		} catch(SQLException sqlex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to get the cabin types only "
					+ "from the database!");
		}
		return oblCabinTypes;
	}
	
}