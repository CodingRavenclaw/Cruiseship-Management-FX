/**	This class implements the logic of the cabin types GUI.
 *
 */
package org.green_pioneer.cruiseship_management.guicontrollers;

import java.util.Optional;

import org.green_pioneer.cruiseship_management.errorhelper.ErrorHelper;
import org.green_pioneer.cruiseship_management.objectclasses.CabinType;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class GUICabinTypesController extends MasterController {
	
	//Elements of the GUI
	@FXML private Label lblCabinType, lblDescription, lblEquipment;
	@FXML private TextField txtCabinType, txtNewCabinType, txtDescription, txtEquipment;
	@FXML private TableView<CabinType> tblCabinTypes;
	@FXML private TableColumn<CabinType, CabinType> tcType, tcDescription, tcEquipment;

	//	Control variables of this controller
	private int intFirstDataSet = 0;
	private final int INT_DATA_LIMIT = 25;
	private final int INT_NUMBER_OF_CABIN_TYPES = aDBCabinTypesAccessor.getNumberOfCabinTypes();

	// Observablelists to save different data.
	private ObservableList<CabinType> oblCabinTypes = FXCollections.observableArrayList();

	//	This method is initialized with the program start and runs ONE time only!
	public void initialize() {
		//	Builds the columns of the table by using the attributes of the object class.
		tcType.setCellValueFactory(new PropertyValueFactory<>("strType"));
		tcDescription.setCellValueFactory(new PropertyValueFactory<>("strDescription"));
		tcEquipment.setCellValueFactory(new PropertyValueFactory<>("strEquipment"));
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
			txtNewCabinType.setVisible(true);
		} else {
			//Disable edit mode
			super.enableEditMode();
			txtNewCabinType.setVisible(false);
		}
	}
	
	// - - - Buttons - - -

	//	Browses backward in the table by using the data limit as the unit.
	@FXML
	private void showPreviousData() {
		intFirstDataSet = intFirstDataSet - 25;
		if(intFirstDataSet <= 0) {
			btnPreviousData.setDisable(true);
		} else if((intFirstDataSet - INT_DATA_LIMIT) <= INT_NUMBER_OF_CABIN_TYPES) {
			btnNextData.setDisable(false);
		}
		oblCabinTypes.clear();
		oblCabinTypes = aDBCabinTypesAccessor.getCabinTypesInObservableList(intFirstDataSet, INT_DATA_LIMIT);
		tblCabinTypes.setItems(oblCabinTypes);
	}

	/**	This method shows the first datasets of the table, depending on the data limit. It also jumps back to the
	 * 	beginning of the table.
	 */
	@FXML
	private void showAllData() {
		btnNextData.setDisable(false);
		if(!(intFirstDataSet == 0)) {
			btnPreviousData.setDisable(true);
		} else if(INT_NUMBER_OF_CABIN_TYPES < INT_DATA_LIMIT) {
			btnPreviousData.setDisable(true);
			btnNextData.setDisable(true);
		}
		intFirstDataSet = 0;
		oblCabinTypes.clear();
		oblCabinTypes = aDBCabinTypesAccessor.getCabinTypesInObservableList(intFirstDataSet, INT_DATA_LIMIT);
		tblCabinTypes.setItems(oblCabinTypes);
	}

	//	Browses forward in the table by using the data limit as the unit.
	@FXML
	private void showNextData() {
		intFirstDataSet = intFirstDataSet + 25;
		if ((intFirstDataSet + INT_DATA_LIMIT) >= INT_NUMBER_OF_CABIN_TYPES) {	
			btnNextData.setDisable(true);
		} else if(intFirstDataSet >= 0) {
			btnPreviousData.setDisable(false);
		}
		oblCabinTypes.clear();
		oblCabinTypes = aDBCabinTypesAccessor.getCabinTypesInObservableList(intFirstDataSet, INT_DATA_LIMIT);
		tblCabinTypes.setItems(oblCabinTypes);
	}

	// Inserts a new cabin type in the database.
	@FXML
	private void saveCabinType() {
		String strCabinType = txtCabinType.getText();
		String strDescription = txtDescription.getText();
		String strEquipment = txtEquipment.getText();
		
		if(strCabinType.equals("") || strDescription.equals("") || strEquipment.equals("")) {
			ErrorHelper.initErrorDialog("Unable to save a new cabin type!", "One or more textfields were empty!");
		} else {
			aDBCabinTypesAccessor.insertNewCabinType(strCabinType, strDescription, strEquipment);
			oblCabinTypes = aDBCabinTypesAccessor.getCabinTypesInObservableList(intFirstDataSet, INT_DATA_LIMIT);
			tblCabinTypes.setItems(oblCabinTypes);
		}
	}

	// Updates an existing cabin type in the database.
	@FXML
	private void editCabinType() {
		String strCabinType = txtCabinType.getText();
		String strNewCabinType = txtNewCabinType.getText();
		String strDescription = txtDescription.getText();
		String strEquipment = txtEquipment.getText();		
		
		if(strCabinType.equals("") || strNewCabinType.equals("") || strDescription.equals("") || strEquipment.equals("")) {
			ErrorHelper.initErrorDialog("Unable to save a new cabin type!", "One or more textfields were empty!");
			return;
		} else {
			if(isCabinTypeExisting(strCabinType)) {
				Optional<ButtonType> confMessage = ErrorHelper.initConfirmationDialog("Are you sure that you want to update "
						+ "the cabin type?", "");
				if(confMessage.get() == ButtonType.OK) {
					aDBCabinTypesAccessor.updateCabinType(strCabinType, strNewCabinType, strDescription, strEquipment);
					oblCabinTypes = aDBCabinTypesAccessor.getCabinTypesInObservableList(intFirstDataSet, INT_DATA_LIMIT);
					tblCabinTypes.setItems(oblCabinTypes);
				} else {
					return;
				}
			} else {
				ErrorHelper.initErrorDialog("Unable to edit cabin type!", "The cabin type was not found!");
			}
		}
	}

	/**	When the user selects a row of the table in the edit mode, every textfield and combobox will be set with the
	 * 	column values of the row.
	 */
	private void onEdit() {
		if(tblCabinTypes.getSelectionModel().getSelectedItem() != null) {
			CabinType selectedCabinType = tblCabinTypes.getSelectionModel().getSelectedItem();
			txtCabinType.setText(selectedCabinType.getStrType());;
			txtNewCabinType.setText(selectedCabinType.getStrType());
			txtDescription.setText(selectedCabinType.getStrDescription());
			txtEquipment.setText(selectedCabinType.getStrEquipment());
		}
	}

	// Deletes an existing cabin from the database.
	@FXML
	private void deleteCabinType() {
		oblCabinTypes = aDBCabinTypesAccessor.getCabinTypesInObservableList(intFirstDataSet, INT_NUMBER_OF_CABIN_TYPES);
		String strCabinType = txtCabinType.getText();
		
		if(isCabinTypeExisting(strCabinType)) {
			Optional<ButtonType> confMessage = ErrorHelper.initConfirmationDialog("Are you sure that you want to delete "
					+ "the cabin type?", "This cannot be undone!");
			if(confMessage.get() == ButtonType.OK) {
				aDBCabinTypesAccessor.deleteCabinType(strCabinType);
				oblCabinTypes = aDBCabinTypesAccessor.getCabinTypesInObservableList(intFirstDataSet, INT_DATA_LIMIT);
				tblCabinTypes.setItems(oblCabinTypes);
			} else {
				return;
			}
		} else {
			ErrorHelper.initErrorDialog("Unable to delete cabin type!", "The cabin type was not found!");
		}
	}

	/**	Check if the cabin type with the given cabin type is existing in the database.
	 *
	 * @param strCabinType The cabin type which should be checked for existence in the database.
	 * @return True, if the cabin type is existing in the database.
	 */
	private boolean isCabinTypeExisting(String strCabinType) {
		ObservableList<CabinType> oblExistingCabinTypes = FXCollections.observableArrayList();
		oblExistingCabinTypes = aDBCabinTypesAccessor.getCabinTypesInObservableList(0, INT_NUMBER_OF_CABIN_TYPES);
		
		for(CabinType aCabinType : oblExistingCabinTypes) {
			if(strCabinType.equals(aCabinType.getStrType())) {
				return true;
			}
		}
		return false;
	}
}
