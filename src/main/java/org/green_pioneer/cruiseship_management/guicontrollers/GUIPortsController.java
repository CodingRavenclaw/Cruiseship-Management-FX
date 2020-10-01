/**	This class implements the logic of the ports GUI.
 *
 */
package org.green_pioneer.cruiseship_management.guicontrollers;

import java.util.Optional;

import org.green_pioneer.cruiseship_management.errorhelper.ErrorHelper;
import org.green_pioneer.cruiseship_management.objectclasses.Port;

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

public class GUIPortsController extends MasterController {
	
	//Elements of the GUI
	@FXML private Label lblLOCODE, lblName, lblLocation, lblCountry, lblWaters, lblDegreeLat, lblArcminuteLat, 
		lblArcsecondLat, lblCardinalPointLat, lblDegreeLong, lblArcminuteLong, lblArcsecondLong, 
		lblCardinalPointLong, lblOperator;
	@FXML private TextField txtLOCODE, txtNewLOCODE, txtName, txtLocation, txtWaters, txtDegreeLat, txtArcminuteLat, 
		txtArcsecondLat, txtDegreeLong, txtArcminuteLong, txtArcsecondLong, txtOperator;
	@FXML private ComboBox<Object> cbCountry, cbCardinalPointLat, cbCardinalPointLong;
	@FXML private TableView<Port> tblPorts;
	@FXML private TableColumn<Port, Port> tcLOCODE, tcName, tcLocation, tcCountry, tcWaters, tcDegreeLat, 
		tcArcminuteLat, tcArcsecondLat, tcCardinalPointLat, tcDegreeLong, tcArcminuteLong, tcArcsecondLong,
		tcCardinalPointLong, tcOperator;

	//	Control variables of this controller.
	private int intFirstDataSet = 0;
	private final int INT_DATA_LIMIT = 25;
	private final int INT_NUMBER_OF_PORTS = aDBPortsAccessor.getNumberOfPorts();

	//	Access object to get access to the Microstream database.
	private ObservableList<Port> oblPorts = FXCollections.observableArrayList();
	private ObservableList<Object> oblCardinalPointsLat = FXCollections.observableArrayList();
	private ObservableList<Object> oblCardinalPointsLong = FXCollections.observableArrayList();
	
	public void initialize() {
		//	Add and set the items of the comboboxes.
		cbCountry.setItems(oblCountries);
		cbCountry.setValue(oblCountries.get(0));

		oblCardinalPointsLat.add("S");
		oblCardinalPointsLat.add("N");
		cbCardinalPointLat.setItems(oblCardinalPointsLat);
		cbCardinalPointLat.setValue(oblCardinalPointsLat.get(0));
		
		oblCardinalPointsLong.add("E");
		oblCardinalPointsLong.add("W");
		cbCardinalPointLong.setItems(oblCardinalPointsLong);
		cbCardinalPointLong.setValue(oblCardinalPointsLong.get(0));

		//	Builds the columns of the table by using the attributes of the object class.
		tcLOCODE.setCellValueFactory(new PropertyValueFactory<>("strLOCODE"));
		tcName.setCellValueFactory(new PropertyValueFactory<>("strName"));
		tcLocation.setCellValueFactory(new PropertyValueFactory<>("strLocation"));
		tcCountry.setCellValueFactory(new PropertyValueFactory<>("strCountry"));
		tcWaters.setCellValueFactory(new PropertyValueFactory<>("strWaters"));
		tcDegreeLat.setCellValueFactory(new PropertyValueFactory<>("dblDegreeLat"));
		tcArcminuteLat.setCellValueFactory(new PropertyValueFactory<>("dblArcminuteLat"));
		tcArcsecondLat.setCellValueFactory(new PropertyValueFactory<>("dblArcsecondLat"));
		tcCardinalPointLat.setCellValueFactory(new PropertyValueFactory<>("strCardinalPointLat"));
		tcDegreeLong.setCellValueFactory(new PropertyValueFactory<>("dblDegreeLong"));
		tcArcminuteLong.setCellValueFactory(new PropertyValueFactory<>("dblArcminuteLong"));
		tcArcsecondLong.setCellValueFactory(new PropertyValueFactory<>("dblArcsecondLong"));
		tcCardinalPointLong.setCellValueFactory(new PropertyValueFactory<>("strCardinalPointLong"));
		tcOperator.setCellValueFactory(new PropertyValueFactory<>("strOperator"));
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
			txtNewLOCODE.setVisible(true);
		} else {
			//Disable edit mode
			super.enableEditMode();
			txtNewLOCODE.setVisible(false);
		}
	}
	
	// - - - Buttons - - -

	//	Browses backward in the table by using the data limit as the unit.
	@FXML
	void showPreviousData() {
		intFirstDataSet = intFirstDataSet - INT_DATA_LIMIT;
		if(intFirstDataSet <= 0) {
			btnPreviousData.setDisable(true);
		} else if((intFirstDataSet - INT_DATA_LIMIT) <= INT_NUMBER_OF_PORTS) {
			btnNextData.setDisable(false);
		}
		oblPorts.clear();
		oblPorts = aDBPortsAccessor.getPortsInObersableList(intFirstDataSet, INT_DATA_LIMIT);
		tblPorts.setItems(oblPorts);
	}

	/**	This method shows the first datasets of the table, depending on the data limit. It also disables the sort modes
	 * 	if one was activated and jumps back to the beginning of the table.
	 */
	@FXML
	void showAllData() {
		btnNextData.setDisable(false);
		if(!(intFirstDataSet == 0)) {
			btnPreviousData.setDisable(true);
		} else if(INT_NUMBER_OF_PORTS < INT_DATA_LIMIT) {
			btnPreviousData.setDisable(true);
			btnNextData.setDisable(true);
		}
		intFirstDataSet = 0;
		oblPorts.clear();
		oblPorts = aDBPortsAccessor.getPortsInObersableList(intFirstDataSet, INT_DATA_LIMIT);
		tblPorts.setItems(oblPorts);
	}

	//	Browses forward in the table by using the data limit as the unit.
	@FXML
	void showNextData() {
		intFirstDataSet = intFirstDataSet + INT_DATA_LIMIT;
		if ((intFirstDataSet + INT_DATA_LIMIT) >= INT_NUMBER_OF_PORTS) {	
			btnNextData.setDisable(true);
		} else if(intFirstDataSet >= 0) {
			btnPreviousData.setDisable(false);
		}
		oblPorts.clear();
		oblPorts = aDBPortsAccessor.getPortsInObersableList(intFirstDataSet, INT_DATA_LIMIT);
		tblPorts.setItems(oblPorts);
	}

	// Inserts a new cabin in the database.
	@FXML
	void savePort() {
		try {
			String strLOCODE = txtLOCODE.getText();
			String strName = txtName.getText();
			String strLocation = txtLocation.getText();
			String strCountry = cbCountry.getSelectionModel().getSelectedItem().toString();
			String strWaters = txtWaters.getText();
			double dblDegreeLat = Double.parseDouble(txtDegreeLat.getText());
			double dblArcminuteLat = Double.parseDouble(txtArcminuteLat.getText());
			double dblArcsecondLat = Double.parseDouble(txtArcsecondLat.getText());
			String strCardinalPointLat = cbCardinalPointLat.getSelectionModel().getSelectedItem().toString();
			double dblDegreeLong = Double.parseDouble(txtDegreeLong.getText());
			double dblArcminuteLong = Double.parseDouble(txtArcminuteLong.getText());
			double dblArcsecondLong = Double.parseDouble(txtArcsecondLong.getText());
			String strCardinalPointLong = cbCardinalPointLong.getSelectionModel().getSelectedItem().toString();
			String strOperator = txtOperator.getText();
			
			if(strLOCODE.equals("") || strName.equals("") || strLocation.equals("") || strCountry.equals("") || 
					strWaters.equals("") || strCardinalPointLat.equals("") || strCardinalPointLong.equals("") || 
					strOperator.equals("")) {
				ErrorHelper.initErrorDialog("Unable to save a new port!", "One or more textfields were empty!");
			} else if(isDegreeLatValid(dblDegreeLat) == false || isArcminuteAndArcsecondValid(dblArcminuteLat, 
					dblArcsecondLat) == false) {
				ErrorHelper.initErrorDialog("Unable to save a new port!", "Invalid values in the latitude coordinates! "
						+ "Please check your entered values!");
			} else if(isDegreeLongValid(dblDegreeLong) == false || isArcminuteAndArcsecondValid(dblArcminuteLong, 
					dblArcsecondLong) == false) {
				ErrorHelper.initErrorDialog("Unable to save a new port!", "Invalid values in the longitude coordinates! "
						+ "Please check your entered values!");
			} else {
				aDBPortsAccessor.insertNewPort(strLOCODE, strName, strLocation, strCountry, strWaters, dblDegreeLat, 
					dblArcminuteLat, dblArcsecondLat, strCardinalPointLat, dblDegreeLong, dblArcminuteLong, 
					dblArcsecondLong, strCardinalPointLong, strOperator);
				oblPorts = aDBPortsAccessor.getPortsInObersableList(intFirstDataSet, INT_DATA_LIMIT);
				tblPorts.setItems(oblPorts);
			}
		} catch(NumberFormatException nfex) {
			ErrorHelper.initErrorDialog("Unable to save a new port!", "Invalid number formats or empty textfields! Please "
					+ "check your entered values!");
		}

	}

	// Updates an existing cabin in the database.
	@FXML
	void editPort() {
		String strLOCODE = txtLOCODE.getText();
		String strNewLOCODE = txtNewLOCODE.getText();
		String strName = txtName.getText();
		String strLocation = txtLocation.getText();
		String strCountry = cbCountry.getSelectionModel().getSelectedItem().toString();
		String strWaters = txtWaters.getText();
		double dblDegreeLat = Double.parseDouble(txtDegreeLat.getText());
		double dblArcminuteLat = Double.parseDouble(txtArcminuteLat.getText());
		double dblArcsecondLat = Double.parseDouble(txtArcsecondLat.getText());
		String strCardinalPointLat = cbCardinalPointLat.getSelectionModel().getSelectedItem().toString();
		double dblDegreeLong = Double.parseDouble(txtDegreeLong.getText());
		double dblArcminuteLong = Double.parseDouble(txtArcminuteLong.getText());
		double dblArcsecondLong = Double.parseDouble(txtArcsecondLong.getText());
		String strCardinalPointLong = cbCardinalPointLong.getSelectionModel().getSelectedItem().toString();
		String strOperator = txtOperator.getText();
		
		if(strLOCODE.equals("") || strNewLOCODE.equals("") || strName.equals("") || strLocation.equals("") || 
				strCountry.equals("") || strWaters.equals("") || strCardinalPointLat.equals("") || 
				strCardinalPointLong.equals("") || strOperator.equals("")) {
			ErrorHelper.initErrorDialog("Unable to edit the port!", "One or more textfields were empty!");
		} else if(isDegreeLatValid(dblDegreeLat) == false || isArcminuteAndArcsecondValid(dblArcminuteLat, 
				dblArcsecondLat) == false) {
			ErrorHelper.initErrorDialog("Unable to edit the port!", "Invalid values in the latitude coordinates! "
					+ "Please check your entered values!");
		} else if(isDegreeLongValid(dblDegreeLong) == false || isArcminuteAndArcsecondValid(dblArcminuteLong, 
				dblArcsecondLong) == false) {
			ErrorHelper.initErrorDialog("Unable to edit the port!", "Invalid values in the longitude coordinates! "
					+ "Please check your entered values!");
		} else if(isPortExisting(strLOCODE)) {
			Optional<ButtonType> confMessage = ErrorHelper.initConfirmationDialog("Are you sure that you want to edit "
					+ "the port?", "");
			if(confMessage.get() == ButtonType.OK) {
				aDBPortsAccessor.updatePort(strLOCODE, strNewLOCODE, strName, strLocation, strCountry, strWaters, 
						dblDegreeLat, dblArcminuteLat, dblArcsecondLat, strCardinalPointLat, dblDegreeLong, dblArcminuteLong, 
						dblArcsecondLong, strCardinalPointLong, strOperator);
				oblPorts = aDBPortsAccessor.getPortsInObersableList(intFirstDataSet, INT_DATA_LIMIT);
				tblPorts.setItems(oblPorts);
			} else {
				return;
			}
		} else {
			ErrorHelper.initErrorDialog("Unable to edit the port!", "The port was not found!");
		}
		

	}

	/**	When the user selects a row of the table in the edit mode, every textfield and combobox will be set with the
	 * 	column values of the row.
	 */
	void onEdit() {
		if(tblPorts.getSelectionModel().getSelectedItem() != null) {
			Port selectedPort = tblPorts.getSelectionModel().getSelectedItem();
			txtLOCODE.setText(selectedPort.getStrLOCODE());
			txtNewLOCODE.setText(selectedPort.getStrLOCODE());
			txtName.setText(selectedPort.getStrName());
			txtLocation.setText(selectedPort.getStrLocation());
			cbCountry.setValue(String.valueOf(selectedPort.getStrCountry()));
			txtWaters.setText(selectedPort.getStrWaters());
			txtDegreeLat.setText(String.valueOf(selectedPort.getDblDegreeLat()));
			txtArcminuteLat.setText(String.valueOf(selectedPort.getDblArcminuteLat()));
			txtArcsecondLat.setText(String.valueOf(selectedPort.getDblArcsecondLat()));
			cbCardinalPointLat.setValue(selectedPort.getStrCardinalPointLat());
			txtDegreeLong.setText(String.valueOf(selectedPort.getDblDegreeLong()));
			txtArcminuteLong.setText(String.valueOf(selectedPort.getDblArcminuteLong()));
			txtArcsecondLong.setText(String.valueOf(selectedPort.getDblArcsecondLong()));
			cbCardinalPointLong.setValue(selectedPort.getStrCardinalPointLong());
			txtOperator.setText(selectedPort.getStrOperator());
		}
	}

	// Deletes an existing cabin from the database.
	@FXML
	void deletePort() {
		String strLOCODE = txtLOCODE.getText();
		
		if(strLOCODE.equals("")) {
			ErrorHelper.initErrorDialog("Unable to delete the port!", "The textfield was empty!");
		} else if(isPortExisting(strLOCODE)) {
			Optional<ButtonType> confMessage = ErrorHelper.initConfirmationDialog("Are you sure that you want to "
					+ "delete the port?", "This cannot be undone!");
			if(confMessage.get() == ButtonType.OK) {
				aDBPortsAccessor.deletePort(strLOCODE);
				oblPorts = aDBPortsAccessor.getPortsInObersableList(intFirstDataSet, INT_DATA_LIMIT);
				tblPorts.setItems(oblPorts);
			} else {
				return;
			}
		} else {
			ErrorHelper.initErrorDialog("Unable to delete the port!", "The port was not found!");
		}
	}

	/**	Checks if the port with the given port number is existing in the database.
	 *
	 * @param strLOCODE The port number which should be checked for existence in the database.
	 * @return True, if the port is existing in the database.
	 */
	private boolean isPortExisting(String strLOCODE) {
		ObservableList<Port> oblExistingPorts = FXCollections.observableArrayList();
		oblExistingPorts = aDBPortsAccessor.getPortsInObersableList(0, INT_NUMBER_OF_PORTS);
		
		for(Port aPort : oblExistingPorts) {
			if(strLOCODE.equals(aPort.getStrLOCODE())) {
				return true;
			}
		}
		return false;
	}

	/**	Checks if the lat. degree is valid when inserting a new port in the database. It must be between 0 and
	 * 	90.
	 *
	 * @param dblDegreeLat The lat. degree which should be validated.
	 * @return True, if the lat. degree is valid.
	 */
	private boolean isDegreeLatValid(double dblDegreeLat) {
		if(0 <= dblDegreeLat && dblDegreeLat <= 90) {
			return true;
		} 
		return false;
	}

	/**	Checks if the long. degree is valid when inserting a new port in the database. It must be between 0 and
	 * 	180.
	 *
	 * @param dblDegreeLong The long. degree which should be validated.
	 * @return True, if the long. degree is valid.
	 */
	private boolean isDegreeLongValid(double dblDegreeLong) {
		if(0 <= dblDegreeLong && dblDegreeLong <= 180) {
			return true;
		}
		return false;
	}

	/** Checks if the arcminutes and arcseconds are valid when inserting a new port in the database. It must be
	 * 	between 0 and 60.
	 *
	 * @param dblArcminute The arcminutes which should be validated.
	 * @param dblArcsecond The arcseconds which should be validated.
	 * @return True, if both of them are valid.
	 */
	private boolean isArcminuteAndArcsecondValid(double dblArcminute, double dblArcsecond) {
		if((0 <= dblArcminute && dblArcminute <= 60) && (0 <= dblArcsecond && dblArcsecond <= 60)) {
			return true;
		}
		return false;
	}
}