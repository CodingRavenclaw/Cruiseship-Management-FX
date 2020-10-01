/**	This class implements the logic of the cruises GUI.
 *
 */
package org.green_pioneer.cruiseship_management.guicontrollers;

import java.time.LocalDate;
import java.util.Optional;

import org.green_pioneer.cruiseship_management.errorhelper.ErrorHelper;
import org.green_pioneer.cruiseship_management.objectclasses.Cabin;
import org.green_pioneer.cruiseship_management.objectclasses.Cruise;
import org.green_pioneer.cruiseship_management.objectclasses.Passenger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class GUICruisesController extends MasterController {
	
	//Elements of the GUI
	@FXML private Label lblCruiseNo, lblPassengerNo, lblCabinNo, lblOriginPort, lblDestinationPort, lblCruiseStart, lblCruiseEnd;
	@FXML private TextField txtCruiseNo, txtNewCruiseNo, txtPassengerNo, txtNewPassengerNo, txtCabinNo;
	@FXML private DatePicker dpCruiseStart, dpCruiseEnd;
	@FXML private ComboBox<Object> cbOriginPort, cbDestinationPort;
	@FXML private TableView<Cruise> tblCruises;
	@FXML private TableColumn<Cruise, Cruise> tcCruiseNo, tcPassengerNo, tcCabinNo, tcOriginPortLOCODE, 
		tcDestinationPortLOCODE, tcCruiseStart, tcCruiseEnd;

	//	Control variables of this controller.
	private int intFirstDataSet = 0;
	private final int INT_DATA_LIMIT = 25;
	private final int INT_NUMBER_OF_CRUISES = aDBCruisesAccessor.getNumberOfCruises();

	// Observablelists to save different data.
	private ObservableList<Cruise> oblCruises = FXCollections.observableArrayList();

	//	This method is initialized with the program start and runs ONE time only!
	public void initialize() {
		//	Add and set the items of the comboboxes and datepickers.
		cbOriginPort.setItems(aDBPortsAccessor.getPortLOCODESOnly());
		cbOriginPort.setValue(aDBPortsAccessor.getPortLOCODESOnly().get(0));
		cbDestinationPort.setItems(aDBPortsAccessor.getPortLOCODESOnly());
		cbDestinationPort.setValue(aDBPortsAccessor.getPortLOCODESOnly().get(0));
		dpCruiseStart.setValue(LocalDate.now());
		dpCruiseEnd.setValue(LocalDate.now());

		//	Builds the columns of the table by using the attributes of the object class.
		tcCruiseNo.setCellValueFactory(new PropertyValueFactory<>("intCruiseNo"));
		tcPassengerNo.setCellValueFactory(new PropertyValueFactory<>("intPassengerNo"));
		tcCabinNo.setCellValueFactory(new PropertyValueFactory<>("intCabinNo"));
		tcOriginPortLOCODE.setCellValueFactory(new PropertyValueFactory<>("strOriginPortLOCODE"));
		tcDestinationPortLOCODE.setCellValueFactory(new PropertyValueFactory<>("strDestinationPortLOCODE"));
		tcCruiseStart.setCellValueFactory(new PropertyValueFactory<>("strCruiseStart"));
		tcCruiseEnd.setCellValueFactory(new PropertyValueFactory<>("strCruiseEnd"));
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
	
	// - - - Menu bar - - -
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
			txtNewCruiseNo.setVisible(true);
			txtNewPassengerNo.setVisible(true);
		} else {
			//Disable edit mode
			super.enableEditMode();
			txtNewCruiseNo.setVisible(false);
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
		} else if((intFirstDataSet - INT_DATA_LIMIT) <= INT_NUMBER_OF_CRUISES) {
			btnNextData.setDisable(false);
		}
		oblCruises.clear();
		oblCruises = aDBCruisesAccessor.getCruisesInObservableList(intFirstDataSet, INT_DATA_LIMIT);
		tblCruises.setItems(oblCruises);
	}

	/**	This method shows the first datasets of the table, depending on the data limit. It also jumps back to the
	 *  beginning of the table.
	 */
	@FXML
	void showAllData() {
		btnNextData.setDisable(false);
		if(!(intFirstDataSet == 0)) {
			btnPreviousData.setDisable(true);
		} else if(INT_NUMBER_OF_CRUISES < INT_DATA_LIMIT) {
			btnPreviousData.setDisable(true);
			btnNextData.setDisable(true);
		}
		intFirstDataSet = 0;
		oblCruises.clear();
		oblCruises = aDBCruisesAccessor.getCruisesInObservableList(intFirstDataSet, INT_DATA_LIMIT);
		tblCruises.setItems(oblCruises);
	}

	//	Browses forward in the table by using the data limit as the unit.
	@FXML
	void showNextData() {
		intFirstDataSet = intFirstDataSet + INT_DATA_LIMIT;
		if ((intFirstDataSet + INT_DATA_LIMIT) >= INT_NUMBER_OF_CRUISES) {	
			btnNextData.setDisable(true);
		} else if(intFirstDataSet >= 0) {
			btnPreviousData.setDisable(false);
		}
		oblCruises.clear();
		oblCruises = aDBCruisesAccessor.getCruisesInObservableList(intFirstDataSet, INT_DATA_LIMIT);
		tblCruises.setItems(oblCruises);
	}

	// Inserts a new cabin in the database.
	@FXML
	void saveCruise() {
		try {
			int intCruiseNo = Integer.parseInt(txtCruiseNo.getText());
			int intPassengerNo = Integer.parseInt(txtPassengerNo.getText());
			int intCabinNo = Integer.parseInt(txtCabinNo.getText());
			String strOriginPortLOCODE = cbOriginPort.getSelectionModel().getSelectedItem().toString();
			String strDestinationPortLOCODE = cbDestinationPort.getSelectionModel().getSelectedItem().toString();
			String strCruiseStart = String.valueOf(dpCruiseStart.getValue());
			String strCruiseEnd = String.valueOf(dpCruiseEnd.getValue());
			
			if(strOriginPortLOCODE.equals("") || strDestinationPortLOCODE.equals("") || strCruiseStart.equals("") ||
					strCruiseEnd.equals("")) {
				ErrorHelper.initErrorDialog("Unable to save a new cruise!", "One or more textfields were empty!");
			} else if(isPassengerExisting(intPassengerNo) && isCabinExisting(intCabinNo)) {
				aDBCruisesAccessor.insertNewCruise(intCruiseNo, intPassengerNo, intCabinNo, strOriginPortLOCODE, 
						strDestinationPortLOCODE, strCruiseStart, strCruiseEnd);
				oblCruises = aDBCruisesAccessor.getCruisesInObservableList(intFirstDataSet, INT_DATA_LIMIT);
				tblCruises.setItems(oblCruises);
			} else {
				ErrorHelper.initErrorDialog("Unable to save a new cruise!", "Either the passenger or the cabin is not existing!");
			}
		} catch(NumberFormatException nfex) {
			ErrorHelper.initErrorDialog("Invalid number formats or empty textfields!", "Please check your entered values!");
		}
	}

	// Updates an existing cabin in the database.
	@FXML
	void editCruise() {
		try {
			int intCruiseNo = Integer.parseInt(txtCruiseNo.getText());
			int intNewCruiseNo = Integer.parseInt(txtNewCruiseNo.getText());
			int intPassengerNo = Integer.parseInt(txtPassengerNo.getText());
			int intNewPassengerNo = Integer.parseInt(txtNewPassengerNo.getText());
			int intCabinNo = Integer.parseInt(txtCabinNo.getText());
			String strOriginPortLOCODE = cbOriginPort.getSelectionModel().getSelectedItem().toString();
			String strDestinationPortLOCODE = cbDestinationPort.getSelectionModel().getSelectedItem().toString();
			String strCruiseStart = String.valueOf(dpCruiseStart.getValue());
			String strCruiseEnd = String.valueOf(dpCruiseEnd.getValue());
			
			if(strOriginPortLOCODE.equals("") || strDestinationPortLOCODE.equals("") || strCruiseStart.equals("") ||
					strCruiseEnd.equals("")) {
				ErrorHelper.initErrorDialog("Unable to save a new cruise!", "One or more textfields were empty!");
			} else if(isCruiseExisting(intCruiseNo) && isPassengerExisting(intPassengerNo) && 
					isPassengerExisting(intNewPassengerNo) && isCabinExisting(intCabinNo)) {
				Optional<ButtonType> confMessage = ErrorHelper.initConfirmationDialog("Are you sure that you want to update "
						+ "the cruise?", "");
				if(confMessage.get() == ButtonType.OK) {
					aDBCruisesAccessor.updateCruise(intCruiseNo, intNewCruiseNo, intPassengerNo, intNewPassengerNo, intCabinNo, 
							strOriginPortLOCODE, strDestinationPortLOCODE, strCruiseStart, strCruiseEnd);
					oblCruises = aDBCruisesAccessor.getCruisesInObservableList(intFirstDataSet, INT_DATA_LIMIT);
					tblCruises.setItems(oblCruises);
					return;
				} else {
					return;
				}
			} else {
				ErrorHelper.initErrorDialog("Unable to edit the cruise!", "Either the cruise, the passenger or the cabin "
						+ "is not existing!");
			}
		} catch(NumberFormatException nfex) {
			ErrorHelper.initErrorDialog("Invalid number formats or empty textfields!", "Please check your entered values!");
		}

	}

	/**	When the user selects a row of the table in the edit mode, every textfield and combobox will be set with the
	 * 	column values of the row.
	 */
	void onEdit() {
		if(tblCruises.getSelectionModel().getSelectedItem() != null) {
			Cruise selectedCruise = tblCruises.getSelectionModel().getSelectedItem();
			txtCruiseNo.setText(String.valueOf(selectedCruise.getIntCruiseNo()));
			txtNewCruiseNo.setText(String.valueOf(selectedCruise.getIntCruiseNo()));
			txtPassengerNo.setText(String.valueOf(selectedCruise.getIntPassengerNo()));
			txtNewPassengerNo.setText(String.valueOf(selectedCruise.getIntPassengerNo()));
			txtCabinNo.setText(String.valueOf(selectedCruise.getIntCabinNo()));
			cbOriginPort.setValue(selectedCruise.getStrOriginPortLOCODE());
			cbDestinationPort.setValue(selectedCruise.getStrDestinationPortLOCODE());
			dpCruiseStart.setValue(LOCAL_DATE(String.valueOf(selectedCruise.getStrCruiseStart())));
			dpCruiseEnd.setValue(LOCAL_DATE(String.valueOf(selectedCruise.getStrCruiseEnd())));
		}
	}

	// Deletes an existing cabin from the database.
	@FXML
	void deleteCruise() {
		try {
			int intCruiseNo = Integer.parseInt(txtCruiseNo.getText());
			int intPassengerNo = Integer.parseInt(txtPassengerNo.getText());
			if(isCruiseExisting(intCruiseNo) && isPassengerExisting(intPassengerNo)) {
				Optional<ButtonType> confMessage = ErrorHelper.initConfirmationDialog("Are you sure that you want to delete "
						+ "the cruise?", "This cannot be undone!");
				if(confMessage.get() == ButtonType.OK) {
					aDBCruisesAccessor.deleteCruise(intCruiseNo, intPassengerNo);
					oblCruises = aDBCruisesAccessor.getCruisesInObservableList(intFirstDataSet, INT_DATA_LIMIT);
					tblCruises.setItems(oblCruises);
				} else {
					return;
				}
			} else {
				ErrorHelper.initErrorDialog("Unable to delete the cruise " + intCruiseNo + " from passenger " + 
						intPassengerNo + "!", "Either the passenger or the cruise was not found!");
			}
		} catch(NumberFormatException nfex) {
			ErrorHelper.initErrorDialog("Invalid number formats or empty textfields!", "Please check your entered values!");
		}

	}

	/**	Checks if a cruise with the given cruise number is existing in the database.
	 *
	 * @param intCruiseNo The cruise number which should be checked for existence in the database.
	 * @return True, if the cruise is existing in the database.
	 */
	private boolean isCruiseExisting(int intCruiseNo) {
		ObservableList<Cruise> oblExistingCruises = FXCollections.observableArrayList();
		oblExistingCruises = aDBCruisesAccessor.getCruisesInObservableList(0, INT_NUMBER_OF_CRUISES);
		for(Cruise aCruise : oblExistingCruises) {
			if(intCruiseNo == aCruise.getIntCruiseNo()) {
				return true;
			}
		}
		return false;
	}

	/**	Checks if a passenger with the given passenger number is existing in the database..
	 *
	 * @param intPassengerNo The passenger number which should be checked for existence in the database.
	 * @return True, if the passenger is existing in the database.
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

	/**	Checks if a cabin with the given cabin number is existing in the database.
	 *
	 * @param intCabinNo The cabin number which should be checked for existence in the database.
	 * @return True, if the cabin is existing in the database.
	 */
	private boolean isCabinExisting(int intCabinNo) {
		final int INT_NUMBER_OF_CABINS = aDBCabinsAccessor.getNumberOfCabins();
		ObservableList<Cabin> oblExistingCabins = FXCollections.observableArrayList();
		
		oblExistingCabins = aDBCabinsAccessor.getCabinsInObservableList(0, INT_NUMBER_OF_CABINS);
		for(Cabin aCabin : oblExistingCabins) {
			if(intCabinNo == aCabin.getIntCabinNo()) {
				return true;
			}
		}
		return false;
	}
}
