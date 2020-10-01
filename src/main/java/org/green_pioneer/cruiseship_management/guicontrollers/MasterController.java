/**	This class implements every element which are needed by every controller class of this application. It is helpful
 * 	to avoid redundant and duplicate classes and variables inside the application.
 *
 */
package org.green_pioneer.cruiseship_management.guicontrollers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.green_pioneer.cruiseship_management.dbaccess.DBAccessCabinTypes;
import org.green_pioneer.cruiseship_management.dbaccess.DBAccessCabins;
import org.green_pioneer.cruiseship_management.dbaccess.DBAccessCountries;
import org.green_pioneer.cruiseship_management.dbaccess.DBAccessCrewPositions;
import org.green_pioneer.cruiseship_management.dbaccess.DBAccessCrewpersons;
import org.green_pioneer.cruiseship_management.dbaccess.DBAccessCruises;
import org.green_pioneer.cruiseship_management.dbaccess.DBAccessPassengers;
import org.green_pioneer.cruiseship_management.dbaccess.DBAccessPorts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import org.green_pioneer.cruiseship_management.microstreamdb.MSDBCabinsAccessor;

public abstract class MasterController {

	// Elements of the GUI which are located and needed in (nearly) every controller class.
	@FXML protected MenuBar mbCruiseManagement;
	@FXML protected MenuItem miCabins, miCabinTypes, miCountries, miCrewpersons, miCrewpositions, miCruiseHistory, miCruises, miPassengers, miPorts;
	@FXML protected CheckMenuItem cmiEnableEditMode;
	@FXML protected Label lblHeadline;
	@FXML protected Button btnPreviousData, btnShowData, btnNextData, btnSave, btnEdit, btnDelete;
	@FXML protected ComboBox<Object> cbGender, cbNationality;
	@FXML protected DatePicker dpDateOfBirth;
	@FXML protected TableView<Object> tblOutput;

	// Access object for the scene controller to switch scenes.
	SceneController aSceneController = new SceneController();

	//	Access objects to get access to the database connectors.
	protected DBAccessCabins aDBCabinsAccessor = new DBAccessCabins();
	protected DBAccessCabinTypes aDBCabinTypesAccessor = new DBAccessCabinTypes();
	protected DBAccessCountries aDBCountriesAccessor = new DBAccessCountries();
	protected DBAccessCrewpersons aDBCrewpersonsAccessor = new DBAccessCrewpersons();
	protected DBAccessCrewPositions aDBCrewPositionsAccessor = new DBAccessCrewPositions();
	protected DBAccessCruises aDBCruisesAccessor = new DBAccessCruises();
	protected DBAccessPassengers aDBPassengersAccessor = new DBAccessPassengers();
	protected DBAccessPorts aDBPortsAccessor = new DBAccessPorts();

	// Path of the folder where the fxml-files are stored.
	protected String strFolderPathToFXMLFiles = "fxml/";

	// Names of the fxml-Files.
	protected String strGUICabins = "GUICabins";
	protected String strGUICabinTypes = "GUICabinTypes";
	protected String strGUICountries = "GUICountries";
	protected String strGUICrewpersons = "GUICrewpersons";
	protected String strGUICrewPositions = "GUICrewPositions";
	protected String strGUICruises = "GUICruises";
	protected String strGUIPassengers = "GUIPassengers";
	protected String strGUIPorts = "GUIPorts";

	// Observablelist for the countries.
	protected ObservableList<Object> oblCountries = aDBCountriesAccessor.getCountryCodesOnly();

	/**	Adds the existing genders an observablelist and returns ist.
	 *
	 * @return The observablelist filled with genders.
	 */
	public ObservableList<Object> addGenders() {
		ObservableList<Object> oblGenders = FXCollections.observableArrayList();
		
		oblGenders.add("m");
		oblGenders.add("f");
		
		return oblGenders;
	}
	
	public void initialize() {

	}
	
	// - - - Menu bar - - - 
		// Settings -> Language
	
	@FXML
	void setLanguageEN() {
		
	}
	
	@FXML
	void setLanguageDE() {
		
	}
	
		// Settings -> Enable edit mode
	
	@FXML
	void enableEditMode() {
		if(cmiEnableEditMode.isSelected()) {
			//Enable edit mode
			btnEdit.setDisable(false);
			btnDelete.setDisable(false);
		} else {
			//Disable edit mode
			btnEdit.setDisable(true);
			btnDelete.setDisable(true);
		}
	}
	
	// Manage

	/**	These methods implement the logic of the switching GUIs and runs the initialize() method when the GUi has been
	 * 	switched successfully.
	 */
	@FXML
	void switchToCabins() {
		aSceneController.switchSceneWithMenuBar(mbCruiseManagement, strFolderPathToFXMLFiles, strGUICabins);
		initialize();
		
	}
	
	@FXML
	void switchToCabinTypes() {
		aSceneController.switchSceneWithMenuBar(mbCruiseManagement, strFolderPathToFXMLFiles, strGUICabinTypes);
		initialize();
	}
	
	@FXML
	void switchToCountries() {
		aSceneController.switchSceneWithMenuBar(mbCruiseManagement, strFolderPathToFXMLFiles, strGUICountries);
		initialize();
	}
	
	@FXML
	void switchToCrewpersons() {
		aSceneController.switchSceneWithMenuBar(mbCruiseManagement, strFolderPathToFXMLFiles, strGUICrewpersons);
		initialize();
	}
	
	@FXML
	void switchToCrewPositions() {
		aSceneController.switchSceneWithMenuBar(mbCruiseManagement, strFolderPathToFXMLFiles, strGUICrewPositions);
		initialize();
	}
	
	@FXML
	void switchToCruises() {
		aSceneController.switchSceneWithMenuBar(mbCruiseManagement, strFolderPathToFXMLFiles, strGUICruises);
		initialize();
	}
	
	@FXML
	void switchToPassengers() {
		aSceneController.switchSceneWithMenuBar(mbCruiseManagement, strFolderPathToFXMLFiles, strGUIPassengers);
		initialize();
	}
	
	@FXML
	void switchToPorts() {
		aSceneController.switchSceneWithMenuBar(mbCruiseManagement, strFolderPathToFXMLFiles, strGUIPorts);
		initialize();
	}

	/**	Formats the date for the database (yyyy-MM-dd).
	 *
	 * @param dateString A date which should be formatted.
	 * @return The formatted date with the pattern yyyy-MM-dd.
	 */
	public static final LocalDate LOCAL_DATE (String dateString){
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate localDate = LocalDate.parse(dateString, formatter);
	    return localDate;
	}
}
