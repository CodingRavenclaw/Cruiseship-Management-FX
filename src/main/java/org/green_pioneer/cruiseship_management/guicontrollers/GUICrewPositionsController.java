/**	This class implements the logic of the crew positions GUI.
 *
 */
package org.green_pioneer.cruiseship_management.guicontrollers;

import java.util.Optional;

import org.green_pioneer.cruiseship_management.dbaccess.DBAccessCrewPositions;
import org.green_pioneer.cruiseship_management.errorhelper.ErrorHelper;
import org.green_pioneer.cruiseship_management.objectclasses.CrewPosition;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class GUICrewPositionsController extends MasterController {
	
	//Elements of the GUI
	@FXML private Label lblPosition, lblDescription;
	@FXML private TextField txtPosition, txtNewPosition, txtDescription;
	@FXML private TableView<CrewPosition> tblCrewPositions;
	@FXML private TableColumn<CrewPosition, CrewPosition> tcPosition, tcDescription;

	//	Control variables of this controller.
	private int intFirstDataSet = 0;
	private final int INT_DATA_LIMIT = 25;
	private final int INT_NUMBER_OF_CREW_POSITIONS = aDBCrewPositionsAccessor.getNumberOfCrewPositions();

	// Observablelists to save different data.
	private ObservableList<CrewPosition> oblCrewPositions = FXCollections.observableArrayList();

	//	This method is initialized with the program start and runs ONE time only!
	public void initialize() {
		//	Builds the columns of the table by using the attributes of the object class.
		tcPosition.setCellValueFactory(new PropertyValueFactory<>("strPosition"));
		tcDescription.setCellValueFactory(new PropertyValueFactory<>("strDescription"));
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
			txtNewPosition.setVisible(true);
		} else {
			//Disable edit mode
			super.enableEditMode();
			txtNewPosition.setVisible(false);
		}
	}
	
	// - - - Buttons - - -

	//	Browses backward in the table by using the data limit as the unit.
	@FXML
	void showPreviousData() {
		intFirstDataSet = intFirstDataSet - 25;
		if(intFirstDataSet <= 0) {
			btnPreviousData.setDisable(true);
		} else if((intFirstDataSet - INT_DATA_LIMIT) <= INT_NUMBER_OF_CREW_POSITIONS) {
			btnNextData.setDisable(false);
		}
		oblCrewPositions.clear();
		oblCrewPositions = aDBCrewPositionsAccessor.getCrewPositionsInObservableList(intFirstDataSet, INT_DATA_LIMIT);
		tblCrewPositions.setItems(oblCrewPositions);
	}

	/**	This method shows the first datasets of the table, depending on the data limit. It also  jumps back to the
	 *  beginning of the table.
	 */
	@FXML
	void showAllData() {
		btnNextData.setDisable(false);
		if(!(intFirstDataSet == 0)) {
			btnPreviousData.setDisable(true);
		} else if(INT_NUMBER_OF_CREW_POSITIONS < INT_DATA_LIMIT) {
			btnPreviousData.setDisable(true);
			btnNextData.setDisable(true);
		}
		intFirstDataSet = 0;
		oblCrewPositions.clear();
		oblCrewPositions = aDBCrewPositionsAccessor.getCrewPositionsInObservableList(intFirstDataSet, INT_DATA_LIMIT);
		tblCrewPositions.setItems(oblCrewPositions);
	}

	//	Browses forward in the table by using the data limit as the unit.
	@FXML
	void showNextData() {
		intFirstDataSet = intFirstDataSet + 25;
		if ((intFirstDataSet + INT_DATA_LIMIT) >= INT_NUMBER_OF_CREW_POSITIONS) {	
			btnNextData.setDisable(true);
		} else if(intFirstDataSet >= 0) {
			btnPreviousData.setDisable(false);
		}
		oblCrewPositions.clear();
		oblCrewPositions = aDBCrewPositionsAccessor.getCrewPositionsInObservableList(intFirstDataSet, INT_DATA_LIMIT);
		tblCrewPositions.setItems(oblCrewPositions);
	}

	// Inserts a new cabin in the database.
	@FXML
	void saveCrewPosition() {
		String strPosition = txtPosition.getText();
		String strDescription = txtDescription.getText();
		
		if(strPosition.equals("") || strDescription.equals("")) {
			ErrorHelper.initErrorDialog("Unable to save a new crew position!", "One or more textfields were empty!");
			return;
		} else {
			aDBCrewPositionsAccessor.insertNewCrewPosition(strPosition, strDescription);
			oblCrewPositions = aDBCrewPositionsAccessor.getCrewPositionsInObservableList(intFirstDataSet, INT_DATA_LIMIT);
			tblCrewPositions.setItems(oblCrewPositions);
		}
	}

	// Updates an existing cabin in the database.
	@FXML
	void editCrewPosition() {
		
		String strPosition = txtPosition.getText();
		String strNewPosition = txtNewPosition.getText();
		String strDescription = txtDescription.getText();
		
		if(strPosition.equals("") || strDescription.equals("") || strNewPosition.equals("")) {
			ErrorHelper.initErrorDialog("Unable to save a new crew position!", "One or more textfields were empty!");
			return;
		} else {
			if(isCrewPositionExisting(strPosition)) {
				Optional<ButtonType> confMessage = ErrorHelper.initConfirmationDialog("Are you sure that you want to update "
						+ "the crew position?", "");
				if(confMessage.get() == ButtonType.OK) {
					aDBCrewPositionsAccessor.updateCrewPosition(strPosition, strNewPosition, strDescription);
					oblCrewPositions = aDBCrewPositionsAccessor.getCrewPositionsInObservableList(intFirstDataSet, INT_DATA_LIMIT);
					tblCrewPositions.setItems(oblCrewPositions);
				} else {
					return;
				}
			} else {
				ErrorHelper.initErrorDialog("Unable to edit the crew position!", "The crew position was not found!");
			}
		}
	}

	/**	When the user selects a row of the table in the edit mode, every textfield and combobox will be set with the
	 * 	column values of the row.
	 */
	void onEdit() {
		if(tblCrewPositions.getSelectionModel().getSelectedItem() != null) {
			CrewPosition selectedPosition = tblCrewPositions.getSelectionModel().getSelectedItem();
			txtPosition.setText(selectedPosition.getStrPosition());
			txtNewPosition.setText(selectedPosition.getStrPosition());
			txtDescription.setText(selectedPosition.getStrDescription());
		}
	}

	// Deletes an existing cabin from the database.
	@FXML
	void deleteCrewPosition() {
		String strPosition = txtPosition.getText();
		
		if(strPosition.equals("")) {
			ErrorHelper.initErrorDialog("Unable to delete the crew position!", "The textfield was empty!");
		} else {
			if(isCrewPositionExisting(strPosition)) {
				Optional<ButtonType> confMessage = ErrorHelper.initConfirmationDialog("Are you sure that you want to "
						+ "delete the crew position?", "This cannot be undone!");
				if(confMessage.get() == ButtonType.OK) {
					aDBCrewPositionsAccessor.deleteCrewPosition(strPosition);
					oblCrewPositions = aDBCrewPositionsAccessor.getCrewPositionsInObservableList(intFirstDataSet, INT_DATA_LIMIT);
					tblCrewPositions.setItems(oblCrewPositions);
				} else {
					return;
				}
			} else {
				ErrorHelper.initErrorDialog("Unable to delete the crew position!", "The crew position was not found!");
			}
		}	
	}

	/**	Checks if the crew position with the given crew position is existing in the database.
	 *
	 * @param strCrewPosition The crew position which should be checked for existaence in the database.
	 * @return True, if the crew position is existing in the database.
	 */
	private boolean isCrewPositionExisting(String strCrewPosition) {
		ObservableList<CrewPosition> oblExistingCrewPositions = FXCollections.observableArrayList();
		oblExistingCrewPositions = aDBCrewPositionsAccessor.getCrewPositionsInObservableList(0, INT_NUMBER_OF_CREW_POSITIONS);
		
		for(CrewPosition aCrewPosition : oblExistingCrewPositions) {
			if(strCrewPosition.equals(aCrewPosition.getStrPosition())) {
				return true;
			}
		}
		return false;		
	}	
}