/**	The function of this class is the deployment of database access to the table tblcountries. It also contains methods
 * 	for adding, updating and deleting data.
 */
package org.green_pioneer.cruiseship_management.dbaccess;

import java.sql.SQLException;

import org.green_pioneer.cruiseship_management.errorhelper.ErrorHelper;
import org.green_pioneer.cruiseship_management.objectclasses.Country;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBAccessCountries extends DBMasterAccessor {

	/**	This method returns the countries of the table in an obersavble list, specified by the first and data limit given
	 * 	as a parameter.
	 *
	 * @param intFirstDataSet The first dataset of the table.
	 * @param INT_DATA_LIMIT The number of datasets starting by the first dataset.
	 * @return The observablelist filled with data from the database.
	 */
	public ObservableList<Country> getCountriesInObservableList(int intFirstDataSet, final int INT_DATA_LIMIT) {
		
		ObservableList<Country> oblCountries = FXCollections.observableArrayList();
		oblCountries.clear();
		
		String strCode;
		String strNameEN;
		String strNameDE;
		
		try {
			
			openDB();
			String strSQLStatement = "SELECT * FROM tblcountries ORDER BY tblcountries.code ASC LIMIT ?,? ;";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setInt(1, intFirstDataSet);
			stmt.setInt(2, INT_DATA_LIMIT);

			rs = stmt.executeQuery();

			while(rs.next()) {
				
				strCode = rs.getString(1);
				strNameEN = rs.getString(2);
				strNameDE = rs.getString(3);
            	oblCountries.add(new Country(strCode, strNameEN, strNameDE));
			}

			closeDB();
		} catch(SQLException sqlex) {
			ErrorHelper.initInformationDialogWithoutHeader("Unable to get the data of tblcountries from the database!");
		}
		return oblCountries;
	}

	/** Inserts a new country in the database.
	 *
	 * @param strCode The country code of the new country.
	 * @param strNameEN The English name of the new country.
	 * @param strNameDE The German name of the new country.
	 */
	public void insertNewCountry(String strCode, String strNameEN, String strNameDE) {
		try {
			openDB();
			String strSQLStatement = "INSERT INTO tblcountries (tblcountries.code, tblcountries.en, "
					+ "tblcountries.de) VALUES (?, ?, ?);";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setString(1, strCode);
			stmt.setString(2, strNameEN);
			stmt.setString(3, strNameDE);
			stmt.execute();
			closeDB();
			ErrorHelper.initInformationDialogWithoutHeader("A new country has been added to the database successfully!");
		} catch(SQLException sqlex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to add a new country to the database!");
		}
	}

	/** Updates an existing country in the database.
	 *
	 * @param strCode The country code of the country which should be changed by the user. This parameter is required for the
	 * 	 *            WHERE clause.
	 * @param strNewCode The new country code of the country.
	 * @param strNameEN The new English name of the country.
	 * @param strNameDE The new German name of the country.
	 */
	public void updateCountry(String strCode, String strNewCode, String strNameEN, String strNameDE) {
		try {
			openDB();
			String strSQLStatement = "UPDATE tblcountries SET tblcountries.code = ?, tblcountries.en = ?, "
					+ "tblcountries.de = ? WHERE tblcountries.code = ?;";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setString(1, strNewCode);
			stmt.setString(2, strNameEN);
			stmt.setString(3, strNameDE);
			stmt.setString(4, strCode);
			stmt.execute();
			closeDB();
			ErrorHelper.initInformationDialogWithoutHeader("The country " + strCode + " has been updated successfully "
					+ "in the database!");
		} catch(SQLException sqlex) { 
			ErrorHelper.initErrorDialogWithoutHeader("Unable to update data from tblcountry from the database!");
		}
	}

	/**	Deletes a country from the database.
	 *
	 * @param strCode The country code of the country which should be deleted from the database.
	 */
	public void deleteCountry(String strCode) {
		try {
			openDB();
			String strSQLStatement = "DELETE FROM tblcountries WHERE tblcountries.code = ?;";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setString(1, strCode);
			stmt.execute();
			closeDB();
			ErrorHelper.initInformationDialogWithoutHeader("The country " + strCode + " was deleted successfully!");
		} catch(SQLException sqlex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to delete country " + strCode + " from the database!");
		}
	}

	/**	Returns an obersavblelist with the country codes only. This method is required by some comboboxes.
	 *
	 * @return An observablelist filled with the country codes of the database.
	 */
	public ObservableList<Object> getCountryCodesOnly() {
		ObservableList<Object> oblCountryCodes = FXCollections.observableArrayList();
		
		try {
			openDB();
			String strSQLStatement = "SELECT * FROM tblCountries;";
			stmt = con.prepareStatement(strSQLStatement);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				oblCountryCodes.add(rs.getString(1));
			}
			
			closeDB();
		} catch(SQLException sqlex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to get the country codes only from the database!");
		}
		return oblCountryCodes;
		
	}

	/**	Returns the number of the countries.
	 *
	 * @return The number of the countries as an int value.
	 */
	public int getNumberOfCountries() {
		int intNumberOfCountries = 0;
		
		try {
			openDB();
			String strSQLStatement = "SELECT COUNT(*) FROM tblcountries;";
			stmt = con.prepareStatement(strSQLStatement);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				intNumberOfCountries = rs.getInt(1);
			}
			
			closeDB();
		} catch(SQLException sqlex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to get the number of countries from the database!");
		}
		return intNumberOfCountries;
	}

	/**	Searches the database with the given country code and returns the founded country.
	 *
	 * @param strCode The country code of the country which should be searched in the database.
	 * @return An observable list filled with the founded country.
	 */
	public ObservableList<Country> searchByCode(String strCode) {
		
		ObservableList<Country> oblFoundCountries = FXCollections.observableArrayList();
		oblFoundCountries.clear();

		String strNameEN;
		String strNameDE;
		
		try {
			
			openDB();
			String strSQLStatement = "SELECT * FROM tblcountries WHERE tblcountries.code LIKE ? "
					+ "ORDER BY tblcountries.code ASC;";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setString(1, strCode);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				strCode = rs.getString(1);
				strNameEN = rs.getString(2);
				strNameDE = rs.getString(3);
            	oblFoundCountries.add(new Country(strCode, strNameEN, strNameDE));
			}
			closeDB();
		} catch(SQLException sqlex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to find the country in the database!");
		}
		return oblFoundCountries;
	}

	/** Searches the database with the given English name of the country.
	 *
	 * @param strNameEN The English country name of the country which should be searched in the database.
	 * @param INT_DATA_LIMIT The number of countries which should be shown in the table of the GUI.
	 * @return An observablelist filled with the founded countries.
	 */
	public ObservableList<Country> searchByNameEN(String strNameEN, final int INT_DATA_LIMIT) {
		
		ObservableList<Country> oblFoundCountries = FXCollections.observableArrayList();
		oblFoundCountries.clear();
		
		String strCode;
		String strNameDE;
		
		try {
			
			openDB();
			String strSQLStatement = "SELECT * FROM tblcountries WHERE tblcountries.en LIKE ? "
					+ "ORDER BY tblcountries.code ASC LIMIT 0,? ;";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setString(1, "%" + strNameEN + "%");
			stmt.setInt(3, INT_DATA_LIMIT);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				strCode = rs.getString(1);
				strNameEN = rs.getString(2);
				strNameDE = rs.getString(3);
            	oblFoundCountries.add(new Country(strCode, strNameEN, strNameDE));
			}
			closeDB();
		} catch(SQLException sqlex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to find the country in the database!");
		}
		return oblFoundCountries;
	}
	
}
