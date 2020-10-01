/**	The function of this class is the deployment of database access to the table tblPorts. It also contains methods
 * 	for adding, updating and deleting data.
 */
package org.green_pioneer.cruiseship_management.dbaccess;

import java.sql.SQLException;

import org.green_pioneer.cruiseship_management.errorhelper.ErrorHelper;
import org.green_pioneer.cruiseship_management.objectclasses.Port;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBAccessPorts extends DBMasterAccessor {

	/**	This method returns the ports of the table in an obersavble list, specified by the first and data limit given
	 * 	as a parameter.
	 *
	 * @param intFirstDataSet The first dataset of the table.
	 * @param INT_DATA_LIMIT The number of datasets starting by the first dataset.
	 * @return The observablelist filled with data from the database.
	 */
	public ObservableList<Port> getPortsInObersableList(int intFirstDataSet, final int INT_DATA_LIMIT) {
		
		ObservableList<Port> oblPorts = FXCollections.observableArrayList();
		oblPorts.clear();
		
		String strLOCODE;
		String strName;
		String strLocation;
		String strCountry;
		String strWaters;
		double dblDegreeLat;
		double dblArcminuteLat;
		double dblArcsecondLat;
		String strCardinalPointLat;
		double dblDegreeLong;
		double dblArcminuteLong;
		double dblArcsecondLong;
		String strCardinalPointLong;
		String strOperator;
		
		try {
			
			openDB();
			String strSQLStatement = "SELECT * FROM tblports ORDER BY tblports.LOCODE ASC LIMIT ?,? ;";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setInt(1, intFirstDataSet);
			stmt.setInt(2, INT_DATA_LIMIT);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				strLOCODE = rs.getString(1);
				strName = rs.getString(2);
				strLocation = rs.getString(3);
				strCountry = rs.getString(4);
				strWaters = rs.getString(5);
				dblDegreeLat = rs.getDouble(6);
				dblArcminuteLat = rs.getDouble(7);
				dblArcsecondLat = rs.getDouble(8);
				strCardinalPointLat = rs.getString(9);
				dblDegreeLong = rs.getDouble(10);
				dblArcminuteLong = rs.getDouble(11);
				dblArcsecondLong = rs.getDouble(12);
				strCardinalPointLong = rs.getString(13);
				strOperator = rs.getString(14);
				
            	oblPorts.add(new Port(strLOCODE, strName, strLocation, strCountry, strWaters, dblDegreeLat, 
            			dblArcminuteLat, dblArcsecondLat, strCardinalPointLat, dblDegreeLong, dblArcminuteLong, 
            			dblArcsecondLong, strCardinalPointLong, strOperator));
			}
			closeDB();
		} catch(SQLException sqlex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to get the data of tblports from the database!");
		}
		return oblPorts;
	}

	/** Inserts a new port in the database.
	 *
	 * @param strLOCODE The LOCODE of the new port.
	 * @param strName The name of the new port.
	 * @param strLocation The location of the new port.
	 * @param strCountry The country of the new port.
	 * @param strWaters The waters of the new port.
	 * @param dblDegreeLat The lat. degree of the new port.
	 * @param dblArcminuteLat The lat. arcminutes of the new port.
	 * @param dblArcsecondLat The lat. arcseconds of the new port.
	 * @param strCardinalPointLat The lat. carinal point of the new port.
	 * @param dblDegreeLong The long. degree of the new port.
	 * @param dblArcminuteLong The long. arcminutes of the new port.
	 * @param dblArcsecondLong The long. arcseconds of the new port.
	 * @param strCardinalPointLong The long. cardinal point of the new port.
	 * @param strOperator The operator of the new port.
	 */
	public void insertNewPort(String strLOCODE, String strName, String strLocation, String strCountry, 
			String strWaters, double dblDegreeLat, double dblArcminuteLat, double dblArcsecondLat, 
			String strCardinalPointLat, double dblDegreeLong, double dblArcminuteLong, double dblArcsecondLong, 
			String strCardinalPointLong, String strOperator) {
		try {
			openDB();
			String strSQLStatement = "INSERT INTO tblports ("
					+ "tblports.LOCODE, "
					+ "tblports.name, "
					+ "tblports.location, "
					+ "tblports.country, "
					+ "tblports.waters, "
					+ "tblports.degree_lat, "
					+ "tblports.arcminute_lat, "
					+ "tblports.arcsecond_lat, "
					+ "tblports.cardinal_point_lat, "
					+ "tblports.degree_long, "
					+ "tblports.arcminute_long, "
					+ "tblports.arcsecond_long, "
					+ "tblports.cardinal_point_long, "
					+ "tblports.operator) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setString(1, strLOCODE);
			stmt.setString(2, strName);
			stmt.setString(3, strLocation);
			stmt.setString(4, strCountry);
			stmt.setString(5, strWaters);
			stmt.setDouble(6, dblDegreeLat);
			stmt.setDouble(7, dblArcminuteLat);
			stmt.setDouble(8, dblArcsecondLat);
			stmt.setString(9, strCardinalPointLat);
			stmt.setDouble(10, dblDegreeLong);
			stmt.setDouble(11, dblArcminuteLong);
			stmt.setDouble(12, dblArcsecondLong);
			stmt.setString(13, strCardinalPointLong);
			stmt.setString(14, strOperator);
			stmt.execute();
			closeDB();
			ErrorHelper.initInformationDialogWithoutHeader("A new port has been added to the database successfully!");
		} catch(SQLException sqlex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to add a new port to the database!");
		}
	}

	/** Updates an existing port in the database.
	 *
	 * @param strLOCODE The LOCODE of the port which should be changed by the user. This parameter is requierd by the
	 *                  WHERE clause.
	 * @param strNewLOCODE The new LOCODE of the port.
	 * @param strName The new name of the port.
	 * @param strLocation The new location of the port.
	 * @param strCountry The new country of the port.
	 * @param strWaters The new waters of the port.
	 * @param dblDegreeLat The new lat. degree of the port.
	 * @param dblArcminuteLat The new lat. arcminutes of the port.
	 * @param dblArcsecondLat The new lat. arcseconds of the port.
	 * @param strCardinalPointLat The new lat. cardinal point of the port.
	 * @param dblDegreeLong The new long. degree of the port.
	 * @param dblArcminuteLong The new long. arcminutes of the port.
	 * @param dblArcsecondLong The new long. arcseconds of the port.
	 * @param strCardinalPointLong The new long. cardinal point of the port.
	 * @param strOperator The new operator of the port.
	 */
	public void updatePort(String strLOCODE, String strNewLOCODE, String strName, String strLocation, 
			String strCountry, String strWaters, double dblDegreeLat, double dblArcminuteLat, double dblArcsecondLat, 
			String strCardinalPointLat, double dblDegreeLong, double dblArcminuteLong, double dblArcsecondLong, 
			String strCardinalPointLong, String strOperator) {
		try {
			openDB();
			String strSQLStatement = "UPDATE tblports SET "
					+ "tblports.LOCODE = ?, "
					+ "tblports.name = ?, "
					+ "tblports.location = ?,"
					+ "tblports.country = ?, "
					+ "tblports.waters = ?, "
					+ "tblports.degree_lat = ?, "
					+ "tblports.arcminute_lat = ?, "
					+ "tblports.arcsecond_lat = ?, "
					+ "tblports.cardinal_point_lat = ?, "
					+ "tblports.degree_long = ?, "
					+ "tblports.arcminute_long = ?, "
					+ "tblports.arcsecond_long = ?, "
					+ "tblports.cardinal_point_long = ?, "
					+ "tblports.operator = ? "
					+ "WHERE tblports.LOCODE = ?;";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setString(1, strNewLOCODE);
			stmt.setString(2, strName);
			stmt.setString(3, strLocation);
			stmt.setString(4, strCountry);
			stmt.setString(5, strWaters);
			stmt.setDouble(6, dblDegreeLat);
			stmt.setDouble(7, dblArcminuteLat);
			stmt.setDouble(8, dblArcsecondLat);
			stmt.setString(9, strCardinalPointLat);
			stmt.setDouble(10, dblDegreeLong);
			stmt.setDouble(11, dblArcminuteLong);
			stmt.setDouble(12, dblArcsecondLong);
			stmt.setString(13, strCardinalPointLong);
			stmt.setString(14, strOperator);
			stmt.setString(15, strLOCODE);
			stmt.execute();
			closeDB();
			ErrorHelper.initInformationDialogWithoutHeader("The port " + strLOCODE + " has been updated successfully "
					+ "in the database!");
		} catch(SQLException sqlex) { 
			ErrorHelper.initErrorDialogWithoutHeader("Unable to update data from tblports in the database!");
		}
	}

	/** Delets an existing port from the database.
	 *
	 * @param strLOCODE The LOCODE of the port which should be deleted from the database.
	 */
	public void deletePort(String strLOCODE) {
		try {
			openDB();
			String strSQLStatement = "DELETE FROM tblports WHERE tblports.LOCODE = ?;";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setString(1, strLOCODE);
			stmt.execute();
			closeDB();
			ErrorHelper.initInformationDialogWithoutHeader("The port " + strLOCODE + " was deleted successfully from "
					+ "the database!");
		} catch(SQLException sqlex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to delete port " + strLOCODE + " from the database!");
		}
	}

	/**	Gets the numbers of the ports.
	 *
	 * @return The numbers of the ports as an int value.
	 */
	public int getNumberOfPorts() {
		int intNumberOfPorts = 0;
		
		try {
			openDB();
			String strSQLStatement = "SELECT COUNT(*) FROM tblports;";
			stmt = con.prepareStatement(strSQLStatement);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				intNumberOfPorts = rs.getInt(1);
			}
			
			closeDB();
		} catch(SQLException sqlex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to get the number of the ports from the database!");
		}
		return intNumberOfPorts;
	}

	/** Gets the LOCODES of the ports only. This method is required by some comboboxes.
	 *
	 * @return The observablelist filled with the LOCODES of the ports only.
	 */
	public ObservableList<Object> getPortLOCODESOnly() {
		ObservableList<Object> oblLOCODES = FXCollections.observableArrayList();
		
		try {
			openDB();
			String strSQLStatement = "SELECT * FROM tblports;";
			stmt = con.prepareStatement(strSQLStatement);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				oblLOCODES.add(rs.getString(1));
			}
			
			closeDB();
		} catch(SQLException ex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to get the port LOCODES only from the database!");
		}
		return oblLOCODES;
	}
}
