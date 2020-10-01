/**	This class implements the logic of the crewpersons GUI.
 *
 */
package org.green_pioneer.cruiseship_management.guicontrollers;

import java.util.Optional;

import org.green_pioneer.cruiseship_management.errorhelper.ErrorHelper;
import org.green_pioneer.cruiseship_management.objectclasses.Crewperson;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class GUICrewpersonsController extends MasterController {
	
	//Elements of the GUI
	@FXML private Label lblCrewpersonNo, lblFirstName, lblLastName, lblDateOfBirth, lblDateOfEmployment, 
		lblGender, lblNationality, lblPosition, lblSalary;
	@FXML private ComboBox<Object> cbPosition;
	@FXML private DatePicker dpDateOfBirth, dpDateOfEmployment;
	@FXML private TextField txtCrewpersonNo, txtNewCrewpersonNo, txtFirstName, txtLastName, txtSalary;
	@FXML private TableView<Crewperson> tblCrewpersons;
	@FXML private TableColumn<Crewperson, Crewperson> tcNumberOfCrewpersons, tcCrewpersonNo, tcFirstName, tcLastName, tcDateOfBirth, 
		tcDateOfEmployment, tcGender, tcNationality, tcPosition, tcSalary;
	@FXML private CheckMenuItem cmiGroupByNationality;

	//	Control variables of this controller.
	private int intFirstDataSet = 0;
	private final int INT_DATA_LIMIT = 25;
	private int intNumberOfDatasets = 0;
	private final int INT_NUMBER_OF_CREWPERSONS = aDBCrewpersonsAccessor.getNumberOfCrewpersons();
	private final int INT_NUMBER_OF_GROUPED_CREWPERSONS_BY_NATIONALITY = aDBCrewpersonsAccessor.getNumberOfGroupedCrewpersonsByNationality();

	//	Variables for the sort modes to check if they are enabled or not.
	boolean boolSortByLastName = false;
	boolean boolGroupByNationality = false;

	// Observablelists to save different data.
	private ObservableList<Crewperson> oblCrewpersons = FXCollections.observableArrayList();

	//	This method is initialized with the program start and runs ONE time only!
	public void initialize() {
		intNumberOfDatasets = INT_NUMBER_OF_CREWPERSONS;
		//	Add and set the items of the comboboxes.
		cbNationality.setItems(oblCountries);
		cbGender.setItems(addGenders());
		cbNationality.setValue(oblCountries.get(0));
		cbGender.setValue(addGenders().get(0));
		cbPosition.setItems(aDBCrewPositionsAccessor.getCrewPositionsOnly());
		cbPosition.setValue(aDBCrewPositionsAccessor.getCrewPositionsOnly().get(0));
		//	Builds the columns of the table by using the attributes of the object class.
		tcCrewpersonNo.setCellValueFactory(new PropertyValueFactory<>("intCrewpersonNo"));
		tcFirstName.setCellValueFactory(new PropertyValueFactory<>("strFirstName"));
		tcLastName.setCellValueFactory(new PropertyValueFactory<>("strLastName"));
		tcDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("strDateOfBirth"));
		tcDateOfEmployment.setCellValueFactory(new PropertyValueFactory<>("strDateOfEmployment"));
		tcGender.setCellValueFactory(new PropertyValueFactory<>("strGender"));
		tcNationality.setCellValueFactory(new PropertyValueFactory<>("strNationality"));
		tcPosition.setCellValueFactory(new PropertyValueFactory<>("strPosition"));
		tcSalary.setCellValueFactory(new PropertyValueFactory<>("dblSalary"));
	}
	
	//Event mouseclicked of the table
	@FXML 
	private void setElementsToSelectedRow() {	
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
			txtCrewpersonNo.setDisable(false);
			txtNewCrewpersonNo.setVisible(true);
			
		} else {
			//Disable edit mode
			super.enableEditMode();
			txtCrewpersonNo.setText(null);
			txtCrewpersonNo.setDisable(true);
			txtNewCrewpersonNo.setVisible(false);
		}
	}

	/** Enables the sorting by price. By browsing through the table with the arrows, this sort mode will be still active
	 * 	until disabling it.
	 */
	@FXML
	private void sortByLastName() {
		boolSortByLastName = true;
		oblCrewpersons.clear();
		oblCrewpersons = aDBCrewpersonsAccessor.sortCrewpersonsByLastName(intFirstDataSet, INT_DATA_LIMIT);
		tblCrewpersons.setItems(oblCrewpersons);
	}

	/** Enables the grouping by nationality. The table is rebuilded for the group by query and will be set to the
	 * 	default table, if this group by mode is disabled.
 	 */
	@FXML
	private void groupByNationality() {
		if(cmiGroupByNationality.isSelected()) {
			intFirstDataSet = 0;
			boolGroupByNationality = true;
			oblCrewpersons.clear();
			tcNumberOfCrewpersons.setVisible(true);
			tcNumberOfCrewpersons.setCellValueFactory(new PropertyValueFactory<>("intNumberOfCrewpersons"));
			tcCrewpersonNo.setVisible(false);
			tcFirstName.setVisible(false);
			tcLastName.setVisible(false);
			tcDateOfBirth.setVisible(false);
			tcDateOfEmployment.setVisible(false);
			tcGender.setVisible(false);
			tcPosition.setVisible(false);
			tcSalary.setVisible(false);
			oblCrewpersons = aDBCrewpersonsAccessor.groupCrewpersonsByNationality(intFirstDataSet, INT_DATA_LIMIT);
			tblCrewpersons.setItems(oblCrewpersons);
			if(INT_NUMBER_OF_GROUPED_CREWPERSONS_BY_NATIONALITY <= INT_DATA_LIMIT) {
				btnPreviousData.setDisable(true);
				btnNextData.setDisable(true);
			} else {
				intNumberOfDatasets = INT_NUMBER_OF_GROUPED_CREWPERSONS_BY_NATIONALITY;
			}
		} else {
			initialize();
			boolGroupByNationality = false;
			oblCrewpersons.clear();
			tcNumberOfCrewpersons.setVisible(false);
			tcCrewpersonNo.setVisible(true);
			tcFirstName.setVisible(true);
			tcLastName.setVisible(true);
			tcDateOfBirth.setVisible(true);
			tcDateOfEmployment.setVisible(true);
			tcGender.setVisible(true);
			tcPosition.setVisible(true);
			tcSalary.setVisible(true);
		}
	}
	
	// - - - Buttons - - -

	//	Browses backward in the table by using the data limit as the unit.
	@FXML
	private void showPreviousData() {
		intFirstDataSet = intFirstDataSet - INT_DATA_LIMIT;
		if(intFirstDataSet <= 0) {
			btnPreviousData.setDisable(true);
		} else if((intFirstDataSet - INT_DATA_LIMIT) <= intNumberOfDatasets) {
			btnNextData.setDisable(false);
		}
		
		if(boolSortByLastName == true || boolGroupByNationality == true) {
			enableSortmode(boolSortByLastName, boolGroupByNationality);
		} else {
			oblCrewpersons.clear();
			oblCrewpersons = aDBCrewpersonsAccessor.getCrewpersonsInObservableList(intFirstDataSet, INT_DATA_LIMIT);
			tblCrewpersons.setItems(oblCrewpersons);
		}
	}

	/**	This method shows the first datasets of the table, depending on the data limit. It also  jumps back to the
	 * 	beginning of the table.
	 */
	@FXML
	private void showAllData() {
		btnNextData.setDisable(false);
		if(!(intFirstDataSet == 0)) {
			btnPreviousData.setDisable(true);
		} else if(intNumberOfDatasets < INT_DATA_LIMIT) {
			btnPreviousData.setDisable(true);
			btnNextData.setDisable(true);
		}
		intFirstDataSet = 0;
		oblCrewpersons.clear();
		oblCrewpersons = aDBCrewpersonsAccessor.getCrewpersonsInObservableList(intFirstDataSet, INT_DATA_LIMIT);
		tblCrewpersons.setItems(oblCrewpersons);
	}

	//	Browses forward in the table by using the data limit as the unit.
	@FXML
	private void showNextData() {
		intFirstDataSet = intFirstDataSet + INT_DATA_LIMIT;
		if ((intFirstDataSet + INT_DATA_LIMIT) >= intNumberOfDatasets) {	
			btnNextData.setDisable(true);
		} else if(intFirstDataSet >= 0) {
			btnPreviousData.setDisable(false);
		}
		
		if(boolSortByLastName == true || boolGroupByNationality == true) {
			enableSortmode(boolSortByLastName, boolGroupByNationality);
		} else {
			oblCrewpersons.clear();
			oblCrewpersons = aDBCrewpersonsAccessor.getCrewpersonsInObservableList(intFirstDataSet, INT_DATA_LIMIT);
			tblCrewpersons.setItems(oblCrewpersons);
		}
	}

	// Inserts a new crewperson in the database.
	@FXML
	private void saveCrewperson() {
		try {
			String strFirstName = txtFirstName.getText();
			String strLastName = txtLastName.getText();
			String strDateOfBirth = String.valueOf(dpDateOfBirth.getValue());
			String strDateOfEmployment = String.valueOf(dpDateOfEmployment.getValue());
			String strGender = cbGender.getSelectionModel().getSelectedItem().toString();
			String strNationality = cbNationality.getSelectionModel().getSelectedItem().toString();
			String strPosition = cbPosition.getSelectionModel().getSelectedItem().toString();
			
			if(strFirstName.equals("") || strLastName.equals("") || strDateOfBirth.equals("") || 
					strDateOfEmployment.equals("") || strGender.equals("") || strNationality.equals("")) {
				ErrorHelper.initErrorDialog("Unable to save a new crewperson!", "One or more textfields were empty!");
				return;
			}
			
			double dblSalary = Double.parseDouble(txtSalary.getText()); 
			aDBCrewpersonsAccessor.insertNewCrewperson(strFirstName, strLastName, strDateOfBirth, strDateOfEmployment, 
					strGender, strNationality, strPosition, dblSalary);
			oblCrewpersons = aDBCrewpersonsAccessor.getCrewpersonsInObservableList(intFirstDataSet, INT_DATA_LIMIT);
			tblCrewpersons.setItems(oblCrewpersons);
		} catch(NumberFormatException nfex) {
			ErrorHelper.initErrorDialog("Unable to save a new crewperson!", "Invalid number format! "
					+ "Please check your entered value!");
			txtSalary.requestFocus();
			txtSalary.selectAll();
		}
	}

	// Updates an existing crewperson in the database.
	@FXML
	private void editCrewperson() {
		try {
			int intCrewpersonNo = Integer.parseInt(txtCrewpersonNo.getText());
			int intNewCrewpersonNo = Integer.parseInt(txtNewCrewpersonNo.getText());
			String strFirstName = txtFirstName.getText();
			String strLastName = txtLastName.getText();
			String strDateOfBirth = String.valueOf(dpDateOfBirth.getValue());
			String strDateOfEmployment = String.valueOf(dpDateOfEmployment.getValue());
			String strGender = cbGender.getSelectionModel().getSelectedItem().toString();
			String strNationality = cbNationality.getSelectionModel().getSelectedItem().toString();
			String strPosition = cbPosition.getSelectionModel().getSelectedItem().toString();
			double dblSalary = Double.parseDouble(txtSalary.getText()); 
			
			if(strFirstName.equals("") || strLastName.equals("") || strDateOfBirth.equals("") || 
					strDateOfEmployment.equals("") || strGender.equals("") || strNationality.equals("")) {
				ErrorHelper.initErrorDialog("Unable to edit a new crewperson!", "One or more textfields were empty!");
			} else {
				if(isCrewpersonExisting(intCrewpersonNo)) {
					Optional<ButtonType> confMessage = ErrorHelper.initConfirmationDialog("Are you sure that you "
							+ "want to update the crewperson?", "");
					if(confMessage.get() == ButtonType.OK) {
						aDBCrewpersonsAccessor.updateCrewperson(intCrewpersonNo, intNewCrewpersonNo, strFirstName, 
								strLastName, strDateOfBirth, strDateOfEmployment, strGender, strNationality, 
								strPosition, dblSalary);
						oblCrewpersons = aDBCrewpersonsAccessor.getCrewpersonsInObservableList(intFirstDataSet, INT_DATA_LIMIT);
						tblCrewpersons.setItems(oblCrewpersons);
					} else {
						return;
					}
				} else {
					ErrorHelper.initErrorDialog("Unable to edit the crewperson!", "The crewperson was not found!");
				}
			}
		} catch(NumberFormatException nfex) {
			ErrorHelper.initErrorDialog("Unable to edit a new crewperson!", "Invalid number format! "
					+ "Please check your entered value!");
		}
	}

	/**	When the user selects a row of the table in the edit mode, every textfield and combobox will be set with the
	 * 	column values of the row.
	 */
	private void onEdit() {
		if(tblCrewpersons.getSelectionModel().getSelectedItem() != null) {
			Crewperson selectedCrewperson = tblCrewpersons.getSelectionModel().getSelectedItem();
			txtCrewpersonNo.setText(String.valueOf(selectedCrewperson.getIntCrewpersonNo()));
			txtNewCrewpersonNo.setText(String.valueOf(selectedCrewperson.getIntCrewpersonNo()));
			txtFirstName.setText(selectedCrewperson.getStrFirstName());
			txtLastName.setText(selectedCrewperson.getStrLastName());
			dpDateOfBirth.setValue(LOCAL_DATE(selectedCrewperson.getStrDateOfBirth()));
			dpDateOfEmployment.setValue(LOCAL_DATE(selectedCrewperson.getStrDateOfEmployment()));
			cbGender.setValue(selectedCrewperson.getStrGender());
			cbNationality.setValue(selectedCrewperson.getStrNationality());
			cbPosition.setValue(selectedCrewperson.getStrPosition());
			txtSalary.setText(String.valueOf(selectedCrewperson.getDblSalary()));
		}
	}

	// Deletes an existing crewperson from the database.
	@FXML
	private void deleteCrewperson() {
		try {
			int intCrewpersonNo = Integer.parseInt(txtCrewpersonNo.getText());
			oblCrewpersons = aDBCrewpersonsAccessor.getCrewpersonsInObservableList(intFirstDataSet, INT_NUMBER_OF_CREWPERSONS);
			
			if(isCrewpersonExisting(intCrewpersonNo)) {
				Optional<ButtonType> confMessage = ErrorHelper.initConfirmationDialog("Are you sure that you want to delete "
						+ "the crewperson?", "This cannot be undone!");
				if(confMessage.get() == ButtonType.OK) {
					aDBCrewpersonsAccessor.deleteCrewperson(intCrewpersonNo);
					oblCrewpersons = aDBCrewpersonsAccessor.getCrewpersonsInObservableList(intFirstDataSet, INT_DATA_LIMIT);
					tblCrewpersons.setItems(oblCrewpersons);
				} else {
					return;
				}
			} else {
				ErrorHelper.initErrorDialog("Unable to delete the crewperson!", "The crewperson was not found!");
			}
		} catch(NumberFormatException nfex) {
			ErrorHelper.initErrorDialog("Unable to delete a new crewperson!", "Invalid number format! "
					+ "Please check your entered values!");
			txtCrewpersonNo.requestFocus();
			txtCrewpersonNo.selectAll();
			
		}
	}

	/**	If the user has activated a sort mode, this method will be called when browsing forard or backward throuth the
	 * 	table. The right sort mode will be selected with the If-Statement. Only one parameter can be true.
	 *
	 * @param boolSortByLastName True, when this sort mode is activated.
	 * @param boolGroupByNationality True, when this sort mode is activated.
	 */
	private void enableSortmode(boolean boolSortByLastName, boolean boolGroupByNationality) {
		if(boolSortByLastName) {
			sortByLastName();
		} else if(boolGroupByNationality) {
			groupByNationality();
		}
	}

	/** Checks if the crewposition with the given crewperson number is existing in the database.
	 *
	 * @param intCrewpersonNo The crewperson number which should be checked for existence in the database.
	 * @return True, if the crewperson is existing in the database.
	 */
	private boolean isCrewpersonExisting(int intCrewpersonNo) {
		ObservableList<Crewperson> oblExistingCrewpersons = FXCollections.observableArrayList();
		oblExistingCrewpersons = aDBCrewpersonsAccessor.getCrewpersonsInObservableList(0, INT_NUMBER_OF_CREWPERSONS);
		
		for(Crewperson aCrewperson : oblExistingCrewpersons) {
			if(intCrewpersonNo == aCrewperson.getIntCrewpersonNo()) {
				return true;
			}
		}
		return false;
	}
}