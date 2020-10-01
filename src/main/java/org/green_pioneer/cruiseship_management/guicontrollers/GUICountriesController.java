/**	This class implements the logic of the countries GUI.
 *
 */
package org.green_pioneer.cruiseship_management.guicontrollers;

import java.util.Optional;

import org.green_pioneer.cruiseship_management.dbaccess.DBAccessCountries;
import org.green_pioneer.cruiseship_management.errorhelper.ErrorHelper;
import org.green_pioneer.cruiseship_management.objectclasses.Country;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class GUICountriesController extends MasterController {
	
	//Elements of the GUI
	@FXML private Label lblCountryCode, lblCountryNameEN, lblCountryNameDE;
	@FXML private TextField txtCountryCode, txtNewCountryCode, txtCountryNameEN, txtCountryNameDE;
	@FXML private TableView<Country> tblCountries;
	@FXML private TableColumn<Country, Country> tcCountryCode, tcCountryNameEN, tcCountryNameDE;

	//	Control variables of this controller
	private int intFirstDataSet = 0;
	private final int INT_DATA_LIMIT = 25;
	private final int INT_NUMBER_OF_COUNTRIES = aDBCountriesAccessor.getNumberOfCountries();

	// Observablelists to save different data.
	private ObservableList<Country> oblCountries = FXCollections.observableArrayList();

	//	This method is initialized with the program start and runs ONE time only!
	public void initialize() {
		//	Builds the columns of the table by using the attributes of the object class.
		tcCountryCode.setCellValueFactory(new PropertyValueFactory<>("strCode"));
		tcCountryNameEN.setCellValueFactory(new PropertyValueFactory<>("strNameEN"));
		tcCountryNameDE.setCellValueFactory(new PropertyValueFactory<>("strNameDE"));
	}

	/** Event mouseclicked of the table. If the user selects a row of the table, every textfield gets the values
	 * 	of it. This feature is implemented in the method onEdit().
	 */
	@FXML 
	private void setElementsToSelectedRow() {	
		if(cmiEnableEditMode.isSelected()) {
			onEdit();
		}
	}
	
	// - - - Menu bar - - -
		// Settings -> Enable edit mode

	/**	Enables the edit mode of the program which allows data manipulation operations like updating and
	 * 	deleting data.
	 */
	@FXML
	void enableEditMode() {
		if(cmiEnableEditMode.isSelected()) {
			//Enable edit mode
			super.enableEditMode();
			txtNewCountryCode.setVisible(true);
		} else {
			//Disable edit mode
			super.enableEditMode();
			txtNewCountryCode.setVisible(false);
		}
	}
	
		// Search data

	//	Searches the database by the LOCODE.
	@FXML 
	private void searchByCode() {
		String strCountryCode = txtCountryCode.getText();
		if(strCountryCode.equals("")) {
			ErrorHelper.initErrorDialog("Unable to find the country!", "The textfield Country code was empty!");
		} else {
			oblCountries = aDBCountriesAccessor.searchByCode(strCountryCode);
			tblCountries.setItems(oblCountries);
		}
	}

	//	Searches the database by the English name of a country.
	@FXML
	private void searchByNameEN() {
		String strCountryNameEN = txtCountryNameEN.getText();
		if(strCountryNameEN.equals("")) {
			ErrorHelper.initErrorDialog("Unable to find the country!", "The textfield Country code was empty!");
		} else {
			oblCountries = aDBCountriesAccessor.searchByNameEN(strCountryNameEN,  INT_DATA_LIMIT);
			tblCountries.setItems(oblCountries);
		}
	}
	
	// - - - Buttons - - -

	//	Browses backward in the table by using the data limit as the unit.
	@FXML
	private void showPreviousData() {
		intFirstDataSet = intFirstDataSet - INT_DATA_LIMIT;
		if(intFirstDataSet <= 0) {
			btnPreviousData.setDisable(true);
		} else if((intFirstDataSet - INT_DATA_LIMIT) <= INT_NUMBER_OF_COUNTRIES) {
			btnNextData.setDisable(false);
		}
		oblCountries.clear();
		oblCountries = aDBCountriesAccessor.getCountriesInObservableList(intFirstDataSet, INT_DATA_LIMIT);
		tblCountries.setItems(oblCountries);
	}

	/**	This method shows the first datasets of the table, depending on the data limit. It also disables the sort modes
	 * 	if one was activated and jumps back to the beginning of the table.
	 */
	@FXML
	private void showAllData() {
		btnNextData.setDisable(false);
		if(!(intFirstDataSet == 0)) {
			btnPreviousData.setDisable(true);
		} else if(INT_NUMBER_OF_COUNTRIES < INT_DATA_LIMIT) {
			btnPreviousData.setDisable(true);
			btnNextData.setDisable(true);
		}
		intFirstDataSet = 0;
		oblCountries.clear();
		oblCountries = aDBCountriesAccessor.getCountriesInObservableList(intFirstDataSet, INT_DATA_LIMIT);
		tblCountries.setItems(oblCountries);
	}

	//	Browses forward in the table by using the data limit as the unit.
	@FXML
	private void showNextData() {
		intFirstDataSet = intFirstDataSet + INT_DATA_LIMIT;
		if ((intFirstDataSet + INT_DATA_LIMIT) >= INT_NUMBER_OF_COUNTRIES) {	
			btnNextData.setDisable(true);
		} else if(intFirstDataSet >= 0) {
			btnPreviousData.setDisable(false);
		}
		oblCountries.clear();
		oblCountries = aDBCountriesAccessor.getCountriesInObservableList(intFirstDataSet, INT_DATA_LIMIT);
		tblCountries.setItems(oblCountries);
	}

	// Inserts a new country in the database.
	@FXML
	private void saveCountry() {
		String strCountryCode = txtCountryCode.getText();
		String strCountryNameEN = txtCountryNameEN.getText();
		String strCountryNameDE = txtCountryNameDE.getText();
		
		if(strCountryCode.equals("") || strCountryNameEN.equals("") || strCountryNameDE.equals("")) {
			ErrorHelper.initErrorDialog("Unable to add a new country!", "One or more textfields were empty!");
		} else {
			oblCountries.add(new Country(strCountryCode, strCountryNameEN, strCountryNameDE));
			aDBCountriesAccessor.insertNewCountry(strCountryCode, strCountryNameEN, strCountryNameDE);
			oblCountries = aDBCountriesAccessor.getCountriesInObservableList(intFirstDataSet, INT_DATA_LIMIT);
			tblCountries.setItems(oblCountries);
		}
	}

	// Updates an existing country in the database.
	@FXML
	private void editCountry() {
		oblCountries = aDBCountriesAccessor.getCountriesInObservableList(intFirstDataSet, INT_NUMBER_OF_COUNTRIES);
		String strCountryCode = txtCountryCode.getText();
		String strNewCountryCode = txtNewCountryCode.getText();
		String strCountryNameEN = txtCountryNameEN.getText();
		String strCountryNameDE = txtCountryNameDE.getText();
				
		if(strCountryCode.equals("") || strNewCountryCode.equals("") || strCountryNameEN.equals("") || strCountryNameDE.equals("")) {
			ErrorHelper.initErrorDialog("Unable to edit a country!", "One or more textfields were empty!");
		} else {
			if(isCountryExisting(strCountryCode)) {
				Optional<ButtonType> confMessage = ErrorHelper.initConfirmationDialog("Are you sure that you want to update "
						+ "the country?", "");
				if(confMessage.get() == ButtonType.OK) {
					aDBCountriesAccessor.updateCountry(strCountryCode, strNewCountryCode, strCountryNameEN, strCountryNameDE);
					oblCountries = aDBCountriesAccessor.getCountriesInObservableList(intFirstDataSet, INT_DATA_LIMIT);
					tblCountries.setItems(oblCountries);
				} else {
					return;
				}
			} else {
				ErrorHelper.initErrorDialog("Unable to edit the country!", "The country was not found!");
			}
		}
	}

	/**	When the user selects a row of the table in the edit mode, every textfield and combobox will be set with the
	 * 	column values of the row.
	 */
	private void onEdit() {
		if(tblCountries.getSelectionModel().getSelectedItem() != null) {
			Country selectedCountry = tblCountries.getSelectionModel().getSelectedItem();
			txtCountryCode.setText(selectedCountry.getStrCode());;
			txtNewCountryCode.setText(selectedCountry.getStrCode());
			txtCountryNameEN.setText(selectedCountry.getStrNameEN());
			txtCountryNameDE.setText(selectedCountry.getStrNameDE());
		}
	}

	// Deletes an existing cabin from the database.
	@FXML
	private void deleteCountry() {
		oblCountries = aDBCountriesAccessor.getCountriesInObservableList(intFirstDataSet, INT_NUMBER_OF_COUNTRIES);
		String strCountryCode = txtCountryCode.getText();
		
		if(strCountryCode.equals("")) {
			ErrorHelper.initErrorDialog("Unable to delete the country!", "The textfield Country code is empty!");
		} else {
			if(isCountryExisting(strCountryCode)) {
				Optional<ButtonType> confMessage = ErrorHelper.initConfirmationDialog("Are you sure that you want to delete "
						+ "the country?", "This cannot be undone!");
				if(confMessage.get() == ButtonType.OK) {
					aDBCountriesAccessor.deleteCountry(strCountryCode);
					oblCountries = aDBCountriesAccessor.getCountriesInObservableList(intFirstDataSet, INT_DATA_LIMIT);
					tblCountries.setItems(oblCountries);
					return;
				} else {
					return;
				}
			} else {
				ErrorHelper.initErrorDialog("Unable to delete the country!", "The country was not found!");
			}
		}
	}

	/**	Checks if the country with the given LOCODE is existing in the database.
	 *
	 * @param strCountryCode The LOCODE which should be checked for existance in the database.
	 * @return True, if the country is existing in the database.
	 */
	private boolean isCountryExisting(String strCountryCode) {
		ObservableList<Country> oblExistingCountries = FXCollections.observableArrayList();
		oblExistingCountries = aDBCountriesAccessor.getCountriesInObservableList(0, INT_NUMBER_OF_COUNTRIES);
		
		for(Country aCountry : oblExistingCountries) {
			if(strCountryCode.equals(aCountry.getStrCode())) {
				return true;
			}
		}
		return false;
	}
}