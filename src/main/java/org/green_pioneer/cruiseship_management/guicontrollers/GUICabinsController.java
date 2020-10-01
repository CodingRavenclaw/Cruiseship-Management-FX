/**	This class implements the logic of the cabins GUI.
 *
 */
package org.green_pioneer.cruiseship_management.guicontrollers;

import java.util.Optional;

import org.green_pioneer.cruiseship_management.errorhelper.ErrorHelper;
import org.green_pioneer.cruiseship_management.microstreamdb.MSDBCabinsAccessor;
import org.green_pioneer.cruiseship_management.objectclasses.Cabin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class GUICabinsController extends MasterController {
	
	// Elements of the GUI.
	@FXML private Label lblCabinNo, lblType, lblDeck, lblMaxPassengerCapacity, lblSizeInSqm, lblNoOfBeds, 
		lblNoOfToilets, lblPricePerPerson;
	@FXML private ComboBox<Object> cbCabinType, cbDeck;
	@FXML private TextField txtCabinNo, txtNewCabinNo, txtMaxPassengerCapacity, txtSizeInSqm, txtNoOfBeds, 
		txtNoOfToilets, txtPricePerPerson;
	@FXML private TableView<Cabin> tblCabins;
	@FXML private TableColumn<Cabin, Cabin> tcCabinNo, tcType, tcDeck, tcMaxPassengerCapacity, tcSizeInSqm, 
		tcNoOfBeds, tcNoOfToilets, tcPricePerPerson;

	//	Access object to get access to the Microstream database.
	MSDBCabinsAccessor aMSDBCabinsAccessor = new MSDBCabinsAccessor();

	//	Control variables of this controller.
	private int intFirstIndex = 0;
	private int intLastIndex = 0;
	private final int INT_DATA_LIMIT = 25;
	private final int INT_MAX_NUMBER_OF_CABINS = 115;
	private final int INT_NUMBER_OF_CABINS = aMSDBCabinsAccessor.getNumberOfCabins();
	private final int INT_MAX_PASSENGER_CAPACITY = aMSDBCabinsAccessor.getMaxPassengerCapacity();
	private final double DBL_MAX_SIZE_IN_SQ_M = aMSDBCabinsAccessor.getMaxSizeInSqm();
	private final int INT_MAX_NO_OF_BEDS = aMSDBCabinsAccessor.getMaxNoOfBeds();
	private final int INT_MAX_NO_OF_TOILETS = aMSDBCabinsAccessor.getMaxNoOfToilets();
	private int intHighestCabinNo = aMSDBCabinsAccessor.getHighestCabinNo();

	//	Variables for the sort modes to check if they are enabled or not.
	boolean boolSortByPrice = false;
	boolean boolSortBySizeInSqm = false;

	// Observablelists to save different data.
	private ObservableList<Object> oblDecks = FXCollections.observableArrayList();
	private ObservableList<Cabin> oblCabins = FXCollections.observableArrayList();

	//	This method is initialized with the program start and runs ONE time only!
	public void initialize() {
		//	Add and set the items of the comboboxes.
		oblDecks.add("A");
		oblDecks.add("B");
		oblDecks.add("C");
		oblDecks.add("D");
		cbDeck.setItems(oblDecks);
		cbDeck.setValue(oblDecks.get(0));
		cbCabinType.setItems(aDBCabinTypesAccessor.getCabinTypesOnly());
		cbCabinType.setValue(aDBCabinTypesAccessor.getCabinTypesOnly().get(0));

		//	Builds the columns of the table by using the attributes of the object class.
		tcCabinNo.setCellValueFactory(new PropertyValueFactory<>("intCabinNo"));
		tcType.setCellValueFactory(new PropertyValueFactory<>("strType"));
		tcDeck.setCellValueFactory(new PropertyValueFactory<>("strDeck"));
		tcMaxPassengerCapacity.setCellValueFactory(new PropertyValueFactory<>("intMaxPassengerCapacity"));
		tcSizeInSqm.setCellValueFactory(new PropertyValueFactory<>("dblSizeInSqM"));
		tcNoOfBeds.setCellValueFactory(new PropertyValueFactory<>("intNoOfBeds"));
		tcNoOfToilets.setCellValueFactory(new PropertyValueFactory<>("intNoOfToilets"));
		tcPricePerPerson.setCellValueFactory(new PropertyValueFactory<>("dblPricePerPersonInEUR"));
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
	
	
	// - - - Menu Bar - - - 
	
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
			txtCabinNo.setDisable(false);
			txtNewCabinNo.setVisible(true);
		} else {
			//Disable edit mode
			super.enableEditMode();
			txtCabinNo.setText(null);
			txtCabinNo.setDisable(true);
			txtNewCabinNo.setVisible(false);
		}
	}
	
		// Show data

	/** Enables the sorting by price. By browsing through the table with the arrows, this sort mode will be still active
	 * 	until disabling it.
	 */
	@FXML
	private void sortByPrice() {
		boolSortByPrice = true;
		oblCabins.clear();
		oblCabins = aMSDBCabinsAccessor.sortByPrice(intFirstIndex, intLastIndex);
		tblCabins.setItems(oblCabins);
	}

	/** Enables the sorting by size in sqm. By browsing through the table with the arrows, this sort mode will be still active
	 * 	until disabling it.
	 */
	@FXML
	private void sortBySizeInSqm() {
		boolSortBySizeInSqm = true;
		oblCabins.clear();
		oblCabins = aMSDBCabinsAccessor.sortBySizeInSqm(intFirstIndex, intLastIndex);
		tblCabins.setItems(oblCabins);
	}
	
	
		// Search data

	//	Searches the database by the cabin number. The user must enter the edit mode first to enter a cabin number.
	@FXML
	private void searchByCabinNo() {
		try {
			int intCabinNo = Integer.parseInt(txtCabinNo.getText());
			oblCabins = aMSDBCabinsAccessor.findCabinByCabinNo(intCabinNo);
			if(oblCabins.get(0) == null) {
				ErrorHelper.initErrorDialog("Unable to find cabin!", "The cabin was not found!");
			} else {
				tblCabins.setItems(oblCabins);
				tblCabins.getSelectionModel().select(0);
			}
		} catch(NumberFormatException nfex) {
			ErrorHelper.initErrorDialog("Invalid number format or empty textfield!", "Please check your entered value!");
		}
	}
	
	// - - - Buttons - - -

	//	Browses backward in the table by using the data limit as the unit.
	@FXML
	private void showPreviousData() {
		intFirstIndex = intFirstIndex - INT_DATA_LIMIT;
		intLastIndex = intLastIndex - INT_DATA_LIMIT;
		if(intFirstIndex <= 0) {
			btnPreviousData.setDisable(true);
		} else if((intFirstIndex - INT_DATA_LIMIT) <= INT_NUMBER_OF_CABINS) {
			btnNextData.setDisable(false);
		}
		
		if(boolSortByPrice == true || boolSortBySizeInSqm == true) {
			enableSortmode(boolSortByPrice, boolSortBySizeInSqm);
		} else {
			oblCabins.clear();
			oblCabins = aMSDBCabinsAccessor.getCabinRootList(intFirstIndex, intLastIndex);
			tblCabins.setItems(oblCabins);
		}
	}

	/**	This method shows the first datasets of the table, depending on the data limit. It also disables the sort modes
	 * 	if one was activated and jumps back to the beginning of the table.
	 */
	@FXML
	private void showAllData() {
		boolSortByPrice = false;
		boolSortBySizeInSqm = false;
		btnNextData.setDisable(false);
		if(!(intFirstIndex == 0)) {
			btnPreviousData.setDisable(true);
		} else if(INT_NUMBER_OF_CABINS < INT_DATA_LIMIT) {
			btnPreviousData.setDisable(true);
			btnNextData.setDisable(true);
		}
		intFirstIndex = 0;
		intLastIndex = 24;
		oblCabins.clear();
		oblCabins = aMSDBCabinsAccessor.getCabinRootList(intFirstIndex, intLastIndex);
		tblCabins.setItems(oblCabins);
	}

	//	Browses forward in the table by using the data limit as the unit.
	@FXML
	private void showNextData() {
		intFirstIndex = intLastIndex;
		intLastIndex = intLastIndex + INT_DATA_LIMIT;
		if ((intFirstIndex + INT_DATA_LIMIT) >= INT_NUMBER_OF_CABINS) {
			btnNextData.setDisable(true);
		} else if(intFirstIndex >= 0) {
			btnPreviousData.setDisable(false);
		}

		if(boolSortByPrice == true || boolSortBySizeInSqm == true) {
			enableSortmode(boolSortByPrice, boolSortBySizeInSqm);
		} else {
			oblCabins.clear();
			oblCabins = aMSDBCabinsAccessor.getCabinRootList(intFirstIndex, intLastIndex);
			tblCabins.setItems(oblCabins);
		}
	}

	// Inserts a new cabin in the database.
	@FXML
	private void saveCabin() {
		try {
			if(aMSDBCabinsAccessor.getNumberOfCabins() >= INT_MAX_NUMBER_OF_CABINS) {
				ErrorHelper.initErrorDialog("Unable to save the cabin!", "The max cabin capacity of this ship " +
						"has been reached!");
			} else {
				intHighestCabinNo++;
				String strType = cbCabinType.getSelectionModel().getSelectedItem().toString();
				String strDeck = cbDeck.getSelectionModel().getSelectedItem().toString();
				int intMaxPassengerCapacity = Integer.parseInt(txtMaxPassengerCapacity.getText());
				double dblSizeInSqm = Double.parseDouble(txtSizeInSqm.getText());
				int intNoOfBeds = Integer.parseInt(txtNoOfBeds.getText());
				int intNoOfToilets = Integer.parseInt(txtNoOfToilets.getText());
				double dblPricePerPerson = Double.parseDouble(txtPricePerPerson.getText());

				// This program mustn't be able to save a cabin with the number 13!
				if(intHighestCabinNo == 13) {
					intHighestCabinNo++;
					aDBCabinsAccessor.insertNewCabin(strType, strDeck, intMaxPassengerCapacity, dblSizeInSqm, intNoOfBeds, intNoOfToilets, dblPricePerPerson);
					aMSDBCabinsAccessor.insertNewCabin(intHighestCabinNo, strType, strDeck, intMaxPassengerCapacity, dblSizeInSqm, intNoOfBeds, intNoOfToilets, dblPricePerPerson);
					oblCabins.clear();
					oblCabins = aMSDBCabinsAccessor.getCabinRootList(intFirstIndex, intLastIndex);
					tblCabins.setItems(oblCabins);
				} else if(isPassengerCapacityValid(intMaxPassengerCapacity) == false || isSizeInSqmValid(dblSizeInSqm) == false ||
						isNoOfBedsValid(intNoOfBeds) == false || isNoOfToiletsValid(intNoOfToilets) == false) {
					//The code blocks for interacting with the user are inside the varification method of the if statement above
				} else {
					aDBCabinsAccessor.insertNewCabin(strType, strDeck, intMaxPassengerCapacity, dblSizeInSqm, intNoOfBeds, intNoOfToilets, dblPricePerPerson);
					aMSDBCabinsAccessor.insertNewCabin(intHighestCabinNo, strType, strDeck, intMaxPassengerCapacity, dblSizeInSqm, intNoOfBeds, intNoOfToilets, dblPricePerPerson);
					oblCabins.clear();
					oblCabins = aMSDBCabinsAccessor.getCabinRootList(intFirstIndex, intLastIndex);
					tblCabins.setItems(oblCabins);
				}
			}
		} catch(NumberFormatException nfex) {
			ErrorHelper.initErrorDialog("Invalid number format or empty textfields!", "Please check your entered values!");
		}
	}

	// Updates an existing cabin in the database.
	@FXML
	private void editCabin() {
		try {
			int intCabinNo = Integer.parseInt(txtCabinNo.getText());
			int intNewCabinNo = Integer.parseInt(txtNewCabinNo.getText());
			String strType = cbCabinType.getSelectionModel().getSelectedItem().toString();
			String strDeck = cbDeck.getSelectionModel().getSelectedItem().toString();
			int intMaxPassengerCapacity = Integer.parseInt(txtMaxPassengerCapacity.getText());
			double dblSizeInSqm = Double.parseDouble(txtSizeInSqm.getText());
			int intNoOfBeds = Integer.parseInt(txtNoOfBeds.getText());
			int intNoOfToilets = Integer.parseInt(txtNoOfToilets.getText());
			double dblPricePerPerson = Double.parseDouble(txtPricePerPerson.getText());

			// This program mustn't be able to save a cabin with the number 13!
			if(intNewCabinNo == 13) {
				ErrorHelper.initErrorDialog("Unable to save cabin!", "A cabin with the no 13 mustn't be "
						+ "on board!");
				txtNewCabinNo.requestFocus();
				txtNewCabinNo.selectAll();
			} else if(isPassengerCapacityValid(intMaxPassengerCapacity) == false || isSizeInSqmValid(dblSizeInSqm) == false ||
					isNoOfBedsValid(intNoOfBeds) == false || isNoOfToiletsValid(intNoOfToilets) == false) {
				//The code blocks for interacting with the user are inside the varification method of the if statement above
			} else if(isCabinExisting(intCabinNo)) {
				Optional<ButtonType> confMessage = ErrorHelper.initConfirmationDialog("Are you sure that you want to update "
						+ "the cabin?", "");
				if(confMessage.get() == ButtonType.OK) {
					aDBCabinsAccessor.updateCabin(intCabinNo, intNewCabinNo, strType, strDeck, intMaxPassengerCapacity, dblSizeInSqm, intNoOfBeds, intNoOfToilets, dblPricePerPerson);
					aMSDBCabinsAccessor.updateCabin(intCabinNo, intNewCabinNo, strType, strDeck, intMaxPassengerCapacity, dblSizeInSqm, intNoOfBeds, intNoOfToilets, dblPricePerPerson);
					oblCabins.clear();
					oblCabins = aMSDBCabinsAccessor.getCabinRootList(intFirstIndex, intLastIndex);
					tblCabins.setItems(oblCabins);
				} else {
					return;
				}
			} else {
				ErrorHelper.initErrorDialog("Unable to edit cabin!", "The cabin was not found!");
			}
			
		} catch(NumberFormatException nfex) {
			ErrorHelper.initErrorDialog("Invalid number format or empty textfields!", "Please check your entered values!");
		}
	}

	/**	When the user selects a row of the table in the edit mode, every textfield and combobox will be set with the
	 * 	column values of the row.
	 */
	private void onEdit() {
		if(tblCabins.getSelectionModel().getSelectedItem() != null) {
			Cabin selectedCabin = tblCabins.getSelectionModel().getSelectedItem();
			txtCabinNo.setText(String.valueOf(selectedCabin.getIntCabinNo()));
			txtNewCabinNo.setText(String.valueOf(selectedCabin.getIntCabinNo()));
			cbCabinType.setValue(selectedCabin.getStrType());
			cbDeck.setValue(selectedCabin.getStrDeck());
			txtMaxPassengerCapacity.setText(String.valueOf(selectedCabin.getIntMaxPassengerCapacity()));
			txtSizeInSqm.setText(String.valueOf(selectedCabin.getDblSizeInSqM()));
			txtNoOfBeds.setText(String.valueOf(selectedCabin.getIntNoOfBeds()));
			txtNoOfToilets.setText(String.valueOf(selectedCabin.getIntNoOfToilets()));
			txtPricePerPerson.setText(String.valueOf(selectedCabin.getDblPricePerPersonInEUR()));
		}
	}

	// Deletes an existing cabin from the database.
	@FXML
	private void deleteCabin() {
		try {
			int intCabinNo = Integer.parseInt(txtCabinNo.getText());
			
			if(isCabinExisting(intCabinNo)) {
				Optional<ButtonType> confMessage = ErrorHelper.initConfirmationDialog("Are you sure that you want to delete "
						+ "the cabin?", "This cannot be undone!");
				if(confMessage.get() == ButtonType.OK) {
					aDBCabinsAccessor.deleteCabin(intCabinNo);
					aMSDBCabinsAccessor.deleteCabin(intCabinNo);
					oblCabins.clear();
					oblCabins = aMSDBCabinsAccessor.getCabinRootList(intFirstIndex, intLastIndex);
					tblCabins.setItems(oblCabins);
				} else {
					return;
				}
			} else {
				ErrorHelper.initErrorDialog("Unable to delete the cabin!", "The cabin was not found!");
			}
		} catch(NumberFormatException nfex) {
			ErrorHelper.initErrorDialog("Invalid number format or empty textfields!", "Please check your entered values!");
		}
	}

	/**	If the user has activated a sort mode, this method will be called when browsing forard or backward throuth the
	 * 	table. The right sort mode will be selected with the If-Statement. Only one parameter can be true.
	 *
	 * @param boolSortByPrice True, when this sort mode is activated.
	 * @param boolSortBySizeInSqm True, when this sort mode is activated.
	 */
	private void enableSortmode(boolean boolSortByPrice, boolean boolSortBySizeInSqm) {
		if(boolSortByPrice) {
			sortByPrice();
		} else if(boolSortBySizeInSqm) {
			sortBySizeInSqm();
		}
	}

	/**	Checks if the max passenger capacity is valid when inserting a new cabin in the database. The value of the
	 * 	final variable INT_MAX_PASSENGER_CAPACITY contains the biggest value of the max passenger capacity in the table.
	 *
 	 * @param intMaxPassengerCapacity The entered max passenger capacity of the user from the textfield.
	 * @return True, if the capacity is valid.
	 */
	private boolean isPassengerCapacityValid(int intMaxPassengerCapacity) {
		if(intMaxPassengerCapacity > INT_MAX_PASSENGER_CAPACITY) {
			Optional<ButtonType> confMessage = ErrorHelper.initConfirmationDialog("Your entered passenger capacity "
					+ "is bigger than the max passenger capacity!", "Are you sure to proceed?");
			if(confMessage.get() == ButtonType.OK) {
				return true;
			} else {
				txtMaxPassengerCapacity.requestFocus();
				txtMaxPassengerCapacity.selectAll();
				return false;
			}
		}
		return true;
	}

	/**	Checks if the size in sqm is valid when inserting a new cabin in the database. The value of the
	 * 	final variable DBL_MAX_SIZE_IN_SQ_M contains the biggest value of the size in sqm in the table.
	 *
	 * @param dblSizeInSqm The entered size in sqm of the user from the textfield.
	 * @return True, if the size in sqm is valid.
	 */
	private boolean isSizeInSqmValid(double dblSizeInSqm) {
		if(dblSizeInSqm > DBL_MAX_SIZE_IN_SQ_M) {
			Optional<ButtonType> confMessage = ErrorHelper.initConfirmationDialog("Your entered size in sq m "
					+ "is bigger than th max size in sq m!", "Are you sure to proceed?");
			if(confMessage.get() == ButtonType.OK) {
				return true;
			} else {
				txtSizeInSqm.requestFocus();
				txtSizeInSqm.selectAll();
				return false;
			}
		}
		return true;
	}

	/**	Checks if the number of beds is valid when inserting a new cabin in the database. The value of the
	 * 	final variable INT_MAX_NO_OF_BEDS contains the biggest value of the size in sqm in the table.
	 *
	 * @param intNoOfBeds The entered number of beds of the user from the textfield.
	 * @return True, if the number of beds is valid.
	 */
	private boolean isNoOfBedsValid(int intNoOfBeds) {
		if(intNoOfBeds > INT_MAX_NO_OF_BEDS) {
			Optional<ButtonType> confMessage = ErrorHelper.initConfirmationDialog("Your entered no of beds "
					+ "is bigger than the max no of beds!", "Are you sure to proceed?");
			if(confMessage.get() == ButtonType.OK) {
				return true;
			} else {
				txtNoOfBeds.requestFocus();
				txtNoOfBeds.selectAll();
				return false;
			}
		}
		return true;
	}

	/**	Checks if the number of toilets is valid when inserting a new cabin in the database. The value of the
	 * 	final variable INT_MAX_NO_OF_TOILETS contains the biggest value of the size in sqm in the table.
	 *
	 * @param intNoOfToilets The entered number of toilets of the user from the textfield.
	 * @return True, if the number of toilets is valid.
	 */
	private boolean isNoOfToiletsValid(int intNoOfToilets) {
		if(intNoOfToilets > INT_MAX_NO_OF_TOILETS) {
			Optional<ButtonType> confMessage = ErrorHelper.initConfirmationDialog("Your entered no of toilets "
					+ "is bigger than the max no of toilets!", "Are you sure to proceed?");
			if(confMessage.get() == ButtonType.OK) {
				return true;
			} else {
				txtNoOfToilets.requestFocus();
				txtNoOfToilets.selectAll();
				return false;
			}
		}
		return true;
	}

	/**	Checks if the cabin with the given cabin number is existing in the database.
	 *
	 * @param intCabinNo The cabin number which should be checked for existence in the database.
	 * @return True, if the cabin is existing in the database.
	 */
	private boolean isCabinExisting(int intCabinNo) {
		final int INT_HIGHEST_CABIN_NO = aMSDBCabinsAccessor.getHighestCabinNo();
		ObservableList<Cabin> oblExistingCabins = FXCollections.observableArrayList();
		oblExistingCabins = aMSDBCabinsAccessor.getCabinRootList(0, INT_HIGHEST_CABIN_NO);
		
		for(Cabin aCabin : oblExistingCabins) {
			if(intCabinNo == aCabin.getIntCabinNo()) {
				return true;
			}
		}
		return false;
	}
}