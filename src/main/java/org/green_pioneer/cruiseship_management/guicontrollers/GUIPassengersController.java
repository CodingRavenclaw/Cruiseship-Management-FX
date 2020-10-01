/**	This class implements the logic of the passenger GUI.
 *
 */
package org.green_pioneer.cruiseship_management.guicontrollers;

import java.time.LocalDate;
import java.util.Optional;

import org.green_pioneer.cruiseship_management.errorhelper.ErrorHelper;
import org.green_pioneer.cruiseship_management.objectclasses.Passenger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class GUIPassengersController extends MasterController {
	
	//Elements of the GUI
	@FXML private Label lblPassengerNo, lblFirstName, lblLastName, lblGender, lblDateOfBirth, lblNationality;
	@FXML private TextField txtPassengerNo, txtNewPassengerNo, txtFirstName, txtLastName;
	@FXML private TableView<Passenger> tblPassengers;
	@FXML private TableColumn<Passenger, Passenger> tcPassengerNo, tcFirstName, tcLastName, tcGender, tcDateOfBirth,
		tcNationality;

	//	Control variables of this controller.
	private int intFirstDataSet = 0;
	private final int INT_DATA_LIMIT = 25;
	private final int INT_NUMBER_OF_PASSENGERS = aDBPassengersAccessor.getNumberOfPassengers();

	// Observablelists to save different data.
	private ObservableList<Passenger> oblPassengers = FXCollections.observableArrayList();

	//	Variables for the sort modes to check if they are enabled or not.
	boolean boolSortByLastName = false;

	//	This method is initialized with the program start and runs ONE time only!
	public void initialize() {
		//	Add and set the items of the comboboxes.
		cbGender.setItems(addGenders());
		cbGender.setValue(addGenders().get(0));
		cbNationality.setItems(oblCountries);
		cbNationality.setValue(oblCountries.get(0));
		dpDateOfBirth.setValue(LocalDate.now());

		//	Builds the columns of the table by using the attributes of the object class.
		tcPassengerNo.setCellValueFactory(new PropertyValueFactory<>("intPassengerNo"));
		tcFirstName.setCellValueFactory(new PropertyValueFactory<>("strFirstName"));
		tcLastName.setCellValueFactory(new PropertyValueFactory<>("strLastName"));
		tcGender.setCellValueFactory(new PropertyValueFactory<>("strGender"));
		tcDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("strDateOfBirth"));
		tcNationality.setCellValueFactory(new PropertyValueFactory<>("strNationality"));
	}

	/** Event mouseclicked of the table. If the user selects a row of the table, every textfield gets the values
	 * 	of it. This feature is implemented in the method onEdit().
	 */
	@FXML 
	void setElementsToSelectedRow() {	
		if(cmiEnableEditMode.isSelected()) {
			onEdit();
		}
	}
	
	
	// - - - MenuBar - - - 
		
		// Show data

	/** Enables the sorting by the last name. By browsing through the table with the arrows, this sort mode will be
	 *  still active until disabling it.
	 */
	@FXML
	void sortByLastName() {
		boolSortByLastName = true;
		oblPassengers.clear();
		oblPassengers = aDBPassengersAccessor.sortByLastName(intFirstDataSet, INT_DATA_LIMIT);
		tblPassengers.setItems(oblPassengers);
	}
	
		// Settings -> Enable edit mode

	/**	Enables the edit mode of the program which allows data manipulation operations like updating and
	 * 	deleting data. The textfields of the primary keys are enabled as well, because they use auto increment by
	 * 	default.
	 */
	@FXML
	void enableEditMode() {
		if(cmiEnableEditMode.isSelected()) {
			//Enable edit mode
			super.enableEditMode();
			txtPassengerNo.setDisable(false);
			txtNewPassengerNo.setVisible(true);
		} else {
			//Disable edit mode
			super.enableEditMode();
			txtPassengerNo.setText(null);
			txtPassengerNo.setDisable(true);
			txtNewPassengerNo.setVisible(false);
		}
	}
	
	// - - - Buttons - - -

	//	Browses backward in the table by using the data limit as the unit.
	@FXML
	void showPreviousData() {
		intFirstDataSet = intFirstDataSet - INT_DATA_LIMIT;
		if(intFirstDataSet <= 0) {
			btnPreviousData.setDisable(true);
		} else if((intFirstDataSet - INT_DATA_LIMIT) <= INT_NUMBER_OF_PASSENGERS) {
			btnNextData.setDisable(false);
		}
		
		if(boolSortByLastName) {
			sortByLastName();
		} else {
			oblPassengers.clear();
			oblPassengers = aDBPassengersAccessor.getPassengersInObservableList(intFirstDataSet, INT_DATA_LIMIT);
			tblPassengers.setItems(oblPassengers);
		}
	}

	/**	This method shows the first datasets of the table, depending on the data limit. It also disables the sort modes
	 * 	if one was activated and jumps back to the beginning of the table.
	 */
	@FXML
	void showAllData() {
		boolSortByLastName = false;
		
		btnNextData.setDisable(false);
		if(!(intFirstDataSet == 0)) {
			btnPreviousData.setDisable(true);
		} else if(INT_NUMBER_OF_PASSENGERS < INT_DATA_LIMIT) {
			btnPreviousData.setDisable(true);
			btnNextData.setDisable(true);
		}
		intFirstDataSet = 0;
		oblPassengers.clear();
		oblPassengers = aDBPassengersAccessor.getPassengersInObservableList(intFirstDataSet, INT_DATA_LIMIT);
		tblPassengers.setItems(oblPassengers);
	}

	//	Browses forward in the table by using the data limit as the unit.
	@FXML
	void showNextData() {
		intFirstDataSet = intFirstDataSet + INT_DATA_LIMIT;
		if ((intFirstDataSet + INT_DATA_LIMIT) >= INT_NUMBER_OF_PASSENGERS) {	
			btnNextData.setDisable(true);
		} else if(intFirstDataSet >= 0) {
			btnPreviousData.setDisable(false);
		}
		
		if(boolSortByLastName) {
			sortByLastName();
		} else {
			oblPassengers.clear();
			oblPassengers = aDBPassengersAccessor.getPassengersInObservableList(intFirstDataSet, INT_DATA_LIMIT);
			tblPassengers.setItems(oblPassengers);
		}
	}

	// 	Inserts a new cabin in the database.
	@FXML
	void savePassenger() {
		String strFirstName = txtFirstName.getText();
		String strLastName = txtLastName.getText();
		String strGender = cbGender.getSelectionModel().getSelectedItem().toString();
		String strDateOfBirth = String.valueOf(dpDateOfBirth.getValue());
		String strNationality = cbNationality.getSelectionModel().getSelectedItem().toString();
		
		if(strFirstName.equals("") || strLastName.equals("") || strGender.equals("") || strDateOfBirth.equals("") ||
				strNationality.equals("")) {
			ErrorHelper.initErrorDialog("Unable to save a new passenger!", "One or more textfields were empty!");
		} else {
			aDBPassengersAccessor.insertNewPassenger(strFirstName, strLastName, strGender, 
					strDateOfBirth, strNationality);
			oblPassengers = aDBPassengersAccessor.getPassengersInObservableList(intFirstDataSet, INT_DATA_LIMIT);
			tblPassengers.setItems(oblPassengers);
		}
	}

	// Updates an existing cabin in the database.
	@FXML
	void editPassenger() {
		try {
			int intPassengerNo = Integer.parseInt(txtPassengerNo.getText());
			int intNewPassengerNo = Integer.parseInt(txtNewPassengerNo.getText());
			String strFirstName = txtFirstName.getText();
			String strLastName = txtLastName.getText();
			String strGender = cbGender.getSelectionModel().getSelectedItem().toString();
			String strDateOfBirth = String.valueOf(dpDateOfBirth.getValue());
			String strNationality = cbNationality.getSelectionModel().getSelectedItem().toString();
			
			if(strFirstName.equals("") || strLastName.equals("") || strGender.equals("") || strDateOfBirth.equals("") ||
					strNationality.equals("")) {
				ErrorHelper.initErrorDialog("Unable to edit the passenger!", "One or more textfields were empty!");
			} else if(isPassengerExisting(intPassengerNo)) {
				Optional<ButtonType> confMessage = ErrorHelper.initConfirmationDialog("Are you sure that you want to update "
						+ "the passenger?", "");
				if(confMessage.get() == ButtonType.OK) {
					aDBPassengersAccessor.updatePassenger(intPassengerNo, intNewPassengerNo, strFirstName, strLastName, strGender, strDateOfBirth, strNationality);
					oblPassengers = aDBPassengersAccessor.getPassengersInObservableList(intFirstDataSet, INT_DATA_LIMIT);
					tblPassengers.setItems(oblPassengers);
				} else {
					return;
				}
			} else {
				ErrorHelper.initErrorDialog("Unable to edit the passenger!", "The passenger was not found!");
			}
		} catch(NumberFormatException nfex) {
			ErrorHelper.initErrorDialog("Unable to edit the passenger!", "Invalid number formats or empty textfields! Please "
					+ "check your entered values!");
		}
	}

	/**	When the user selects a row of the table in the edit mode, every textfield and combobox will be set with the
	 * 	column values of the row.
	 */
	void onEdit() {
		if(tblPassengers.getSelectionModel().getSelectedItem() != null) {
			Passenger selectedPassenger = tblPassengers.getSelectionModel().getSelectedItem();
			txtPassengerNo.setText(String.valueOf(selectedPassenger.getIntPassengerNo()));
			txtNewPassengerNo.setText(String.valueOf(selectedPassenger.getIntPassengerNo()));
			txtFirstName.setText(selectedPassenger.getStrFirstName());
			txtLastName.setText(selectedPassenger.getStrLastName());
			cbGender.setValue(selectedPassenger.getStrGender());
			dpDateOfBirth.setValue(LOCAL_DATE(selectedPassenger.getStrDateOfBirth()));
			cbNationality.setValue(selectedPassenger.getStrNationality());
		}
	}

	// Deletes an existing cabin from the database.
	@FXML
	void deletePassenger() {
		try {
			int intPassengerNo = Integer.parseInt(txtPassengerNo.getText());
			if(isPassengerExisting(intPassengerNo)) {
				Optional<ButtonType> confMessage = ErrorHelper.initConfirmationDialog("Are you sure that you want to "
						+ "delete the passenger?", "This cannot be undone!");
				if(confMessage.get() == ButtonType.OK) {
					aDBPassengersAccessor.deletePassenger(intPassengerNo);
					oblPassengers = aDBPassengersAccessor.getPassengersInObservableList(intFirstDataSet, INT_DATA_LIMIT);
					tblPassengers.setItems(oblPassengers);
				} else {
					return;
				}
			} else {
				ErrorHelper.initErrorDialog("Unable to delete the passenger!", "The passenger was not found!");
			}
		} catch(NumberFormatException nfex) {
			ErrorHelper.initErrorDialog("Unable to delete the passenger!", "Invalid number formats or empty textfield! "
					+ "Please check your entered value!");
		}

	}

	/**	Checks if the passenger with the given passenger number is existing in the database.
	 *
	 * @param intPassengerNo The passenger number which should be checked for existence in the database.
	 * @return True, if the cabin is existing in the database.
	 */
	private boolean isPassengerExisting(int intPassengerNo) {
		final int INT_NUMBER_OF_PASSENGERS = aDBPassengersAccessor.getNumberOfPassengers();
		ObservableList<Passenger> oblExistingPassengers = FXCollections.observableArrayList();
		
		oblExistingPassengers = aDBPassengersAccessor.getPassengersInObservableList(0, INT_NUMBER_OF_PASSENGERS);
		for(Passenger aPassenger : oblExistingPassengers) {
			if(intPassengerNo == aPassenger.getIntPassengerNo()) {
				return true;
			}
		}
		return false;
	}
}
