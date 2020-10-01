/**	The function of this class is the deployment of database access to the table tblCrewpersons. It also contains methods
 * 	for adding, updating and deleting data.
 */
package org.green_pioneer.cruiseship_management.dbaccess;

import java.sql.SQLException;

import org.green_pioneer.cruiseship_management.errorhelper.ErrorHelper;
import org.green_pioneer.cruiseship_management.objectclasses.Crewperson;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBAccessCrewpersons extends DBMasterAccessor {

	/** This method returns the crewpersons of the table in an obersavble list, specified by the first and data limit given
	 * 	as a parameter.
	 *
	 * @param intFirstDataSet The first dataset of the table.
	 * @param INT_DATA_LIMIT The number of datasets starting by the first dataset.
	 * @return The observablelist filled with data from the database.
	 */
	public ObservableList<Crewperson> getCrewpersonsInObservableList(int intFirstDataSet, final int INT_DATA_LIMIT) {

		ObservableList<Crewperson> oblCrewpersons = FXCollections.observableArrayList();
		oblCrewpersons.clear();
		
		int intCrewpersonNo;
		String strFirstName;
		String strLastName;
		String strDateOfBirth;
		String strDateOfEmployment;
		String strGender;
		String strNationality;
		String strPosition;
		double dblSalary;

		
		try {
			
			openDB();
			String strSQLStatement = "SELECT * FROM tblcrewpersons ORDER BY tblcrewpersons.crewpersonNo "
					+ "ASC LIMIT ?,? ;";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setInt(1, intFirstDataSet);
			stmt.setInt(2, INT_DATA_LIMIT);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				intCrewpersonNo = rs.getInt(1);
				strFirstName = rs.getString(2);
				strLastName = rs.getString(3);
				strDateOfBirth = rs.getString(4);
				strDateOfEmployment = rs.getString(5);
				strGender = rs.getString(6);
				strNationality = rs.getString(7);
				strPosition = rs.getString(8);
				dblSalary = rs.getDouble(9);
            	oblCrewpersons.add(new Crewperson(intCrewpersonNo, strFirstName, strLastName, strDateOfBirth, 
            			strDateOfEmployment, strGender, strNationality, strPosition, dblSalary));
			}
			closeDB();
		} catch(SQLException ex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to get the crewpersons from the database!");
		}
		return oblCrewpersons;
	}

	/** Inserts a new crewperson in the database.
	 *
	 * @param strFirstName The first name of the new crewperson.
	 * @param strLastName The last name of the new crewperson.
	 * @param strDateOfBirth The date of birth of the new crewperson.
	 * @param strDateOfEmployment The date of employment of the new crewperson.
	 * @param strGender The gender of the new crewperson.
	 * @param strNationality The nationality of the new crewperson.
	 * @param strPosition The position of the new crewperson.
	 * @param dblSalary The salary of the new crewperson.
	 */
	public void insertNewCrewperson(String strFirstName, String strLastName, String strDateOfBirth, 
			String strDateOfEmployment, String strGender, String strNationality, String strPosition, double dblSalary) {
		try {
			openDB();
			String strSQLStatement = "INSERT INTO tblcrewpersons ("
					+ "tblcrewpersons.crewpersonNo, "
					+ "tblcrewpersons.first_name, "
					+ "tblcrewpersons.last_name, "
					+ "tblcrewpersons.date_of_birth, "
					+ "tblcrewpersons.date_of_employment, "
					+ "tblcrewpersons.gender, "
					+ "tblcrewpersons.nationality, "
					+ "tblcrewpersons.position, "
					+ "tblcrewpersons.salary) "
					+ "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?);";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setString(1, strFirstName);
			stmt.setString(2, strLastName);
			stmt.setString(3, strDateOfBirth);
			stmt.setString(4, strDateOfEmployment);
			stmt.setString(5, strGender);
			stmt.setString(6, strNationality);
			stmt.setString(7, strPosition);
			stmt.setDouble(8, dblSalary);
			stmt.execute();
			closeDB();
			ErrorHelper.initInformationDialogWithoutHeader("A new crewperson has been added to the database successfully!");
		} catch(SQLException ex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to add a new crewperson to the database!");
		}
	}

	/**	Updates an existing crewperson in the database.
	 *
	 * @param intCrewpersonNo The number of the crewperson which should be changed by the user. This parameter is required for the
	 * 	 *                   WHERE clause.
	 * @param intNewCrewpersonNo The new crewperson number of the crewperson.
	 * @param strFirstName The new first name of the crewperson.
	 * @param strLastName The new last name of the crewperson.
	 * @param strDateOfBirth The new date of birth of the crewperson.
	 * @param strDateOfEmployment The new date of employment of the crewperson.
	 * @param strGender The new gender of the crewperson.
	 * @param strNationality The new nationality of the crewperson.
	 * @param strPosition The new position of the crewperson.
	 * @param dblSalary The new salary of the crewperson.
	 */
	public void updateCrewperson(int intCrewpersonNo, int intNewCrewpersonNo, String strFirstName, 
			String strLastName, String strDateOfBirth, String strDateOfEmployment, String strGender, 
			String strNationality, String strPosition, double dblSalary) {
		try {
			openDB();
			String strSQLStatement = "UPDATE tblcrewpersons SET "
					+ "tblcrewpersons.crewpersonNo = ?, "
					+ "tblcrewpersons.first_name = ?, "
					+ "tblcrewpersons.last_name = ?, "
					+ "tblcrewpersons.date_of_birth = ?, "
					+ "tblcrewpersons.date_of_employment = ?, "
					+ "tblcrewpersons.gender = ?, "
					+ "tblcrewpersons.nationality = ?, "
					+ "tblcrewpersons.position = ?, "
					+ "tblcrewpersons.salary = ?"
					+ "WHERE tblcrewpersons.crewpersonNo = ?;";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setInt(1, intNewCrewpersonNo);
			stmt.setString(2, strFirstName);
			stmt.setString(3, strLastName);
			stmt.setString(4, strDateOfBirth);
			stmt.setString(5, strDateOfEmployment);
			stmt.setString(6, strGender);
			stmt.setString(7, strNationality);
			stmt.setString(8, strPosition);
			stmt.setDouble(9, dblSalary);
			stmt.setInt(10, intCrewpersonNo);
			stmt.execute();
			closeDB();
			ErrorHelper.initInformationDialogWithoutHeader("The crewperson " + intCrewpersonNo + " was updated successfully"
					+ "in the database!");
		} catch(SQLException ex) { 
			ErrorHelper.initErrorDialogWithoutHeader("Unable to update the crewperson" + intCrewpersonNo + "in the database!");
		}
	}

	/**	Deletes an existing crewperson from the database.
	 *
	 * @param intCrewpersonNo The number of the crewperson which should be deletd from the database.
	 */
	public void deleteCrewperson(int intCrewpersonNo) {
		try {
			openDB();
			String strSQLStatement = "DELETE FROM tblcrewpersons WHERE tblcrewpersons.crewpersonNo = ?;";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setInt(1, intCrewpersonNo);
			stmt.execute();
			closeDB();
			ErrorHelper.initInformationDialogWithoutHeader("The crewperson " + intCrewpersonNo + " was deleted successfully!");
		} catch(SQLException ex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to delete crewperson " + intCrewpersonNo + " from the database!");
		}
	}

	/**	Sorts the crewpersons by their last name.
	 *
	 * @param intFirstDataSet The first dataset of the table.
	 * @param INT_DATA_LIMIT The number of datasets starting by the first dataset.
	 * @return The observablelist filled with the sorted data from the database.
	 */
	public ObservableList<Crewperson> sortCrewpersonsByLastName(int intFirstDataSet, final int INT_DATA_LIMIT) {
		
		ObservableList<Crewperson> oblCrewpersons = FXCollections.observableArrayList();
		oblCrewpersons.clear();
		
		int intCrewpersonNo;
		String strFirstName;
		String strLastName;
		String strDateOfBirth;
		String strDateOfEmployment;
		String strGender;
		String strNationality;
		String strPosition;
		double dblSalary;

		
		try {
			
			openDB();
			String strSQLStatement = "SELECT * FROM tblcrewpersons ORDER BY tblcrewpersons.last_name "
					+ "ASC LIMIT ?,? ;";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setInt(1, intFirstDataSet);
			stmt.setInt(2, INT_DATA_LIMIT);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				intCrewpersonNo = rs.getInt(1);
				strFirstName = rs.getString(2);
				strLastName = rs.getString(3);
				strDateOfBirth = rs.getString(4);
				strDateOfEmployment = rs.getString(5);
				strGender = rs.getString(6);
				strNationality = rs.getString(7);
				strPosition = rs.getString(8);
				dblSalary = rs.getDouble(9);
            	oblCrewpersons.add(new Crewperson(intCrewpersonNo, strFirstName, strLastName, strDateOfBirth, 
            			strDateOfEmployment, strGender, strNationality, strPosition, dblSalary));
			}
			closeDB();
		} catch(SQLException ex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to sort the crewpersons from the database!");
		}
		return oblCrewpersons;
	}

	/** Groups the crewpersons by their nationality.
	 *
	 * @param intFirstDataSet The first dataset of the table.
	 * @param INT_DATA_LIMIT The number of datasets starting by the first dataset.
	 * @return The observablelist filled with the grouped data from the database.
	 */
	public ObservableList<Crewperson> groupCrewpersonsByNationality(int intFirstDataSet, final int INT_DATA_LIMIT) {
		
		ObservableList<Crewperson> oblCrewpersons = FXCollections.observableArrayList();
		oblCrewpersons.clear();
		
		int intNumberOfCrewpersons;
		String strCountryCode;
		
		try {
			
			openDB();
			String strSQLStatement = "SELECT COUNT(*) AS 'number_of_crewpersons', nationality FROM tblcrewpersons "
					+ "GROUP BY tblcrewpersons.nationality ORDER BY number_of_crewpersons DESC LIMIT ?,?;";
			stmt = con.prepareStatement(strSQLStatement);
			stmt.setInt(1, intFirstDataSet);
			stmt.setInt(2, INT_DATA_LIMIT);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				intNumberOfCrewpersons = rs.getInt(1);
				strCountryCode = rs.getString(2);
            	oblCrewpersons.add(new Crewperson(intNumberOfCrewpersons, strCountryCode));
			}
			closeDB();
		} catch(SQLException ex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to get the crewpersons from the database!");
		}
		return oblCrewpersons;
	}

	/** Gets the number of the crewpersons.
	 *
	 * @return The number of the crewpersons as an int number.
	 */
	public int getNumberOfCrewpersons() {
		int intNumberOfCrewpersons = 0;
		
		try {
			openDB();
			String strSQLStatement = "SELECT COUNT(*) FROM tblcrewpersons;";
			stmt = con.prepareStatement(strSQLStatement);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				intNumberOfCrewpersons = rs.getInt(1);
			}
			
			closeDB();
		} catch(SQLException ex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to get the number of crewpersons from the database!");
		}
		return intNumberOfCrewpersons;
	}

	/**	Gets the number of grouped crewperesons by their nationality.
	 *
	 * @return The number of grouped crewpersons by their nationality as an int value.
	 */
	public int getNumberOfGroupedCrewpersonsByNationality() {
		int intNumberOfGroupedCrewpersonsByNationality = 0;
		
		try {
			openDB();
			String strSQLStatement = "SELECT COUNT(*) OVER () AS 'number_of_rows', COUNT(*) "
					+ "AS 'number_of_crewpersons', nationality FROM tblcrewpersons "
					+ "GROUP BY tblcrewpersons.nationality "
					+ "ORDER BY number_of_crewpersons "
					+ "DESC LIMIT 0,25;;";
			stmt = con.prepareStatement(strSQLStatement);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				intNumberOfGroupedCrewpersonsByNationality = rs.getInt(1);
			}
			
			closeDB();
		} catch(SQLException ex) {
			ErrorHelper.initErrorDialogWithoutHeader("Unable to get the number of grouped crewpersons by nationality "
					+ "from the database!");
		}
		return intNumberOfGroupedCrewpersonsByNationality;
	}
}