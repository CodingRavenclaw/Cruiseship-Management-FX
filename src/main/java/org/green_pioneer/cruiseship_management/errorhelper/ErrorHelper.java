/**	This class provides methods to create information windows for the user. They are necessary for interacting
 * 	with the programm if error occur during the use of it.
 */
package org.green_pioneer.cruiseship_management.errorhelper;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class ErrorHelper {

	private static Alert aConfirmationAlert = new Alert(AlertType.CONFIRMATION);
	private static Alert anInformationAlert = new Alert(AlertType.INFORMATION);
	private static Alert aWarningAlert = new Alert(AlertType.WARNING);
	private static Alert anErrorAlert = new Alert(AlertType.ERROR);

	/**	Inits a confirmation dialog window wherein the user can choose an option (OK or Cancel).
	 *
	 * @param strHeaderText The header text of the confirmation dialog window.
	 * @param strContentText The content text of the confirmation dialog window.
	 * @return The decision of the user.
	 */
	public static Optional<ButtonType> initConfirmationDialog(String strHeaderText, 
			String strContentText) {

		aConfirmationAlert.setTitle("Confirmation");
		aConfirmationAlert.setHeaderText(strHeaderText);
		aConfirmationAlert.setContentText(strContentText);
		Optional<ButtonType> result = aConfirmationAlert.showAndWait();
		return result;
	}

	/**	Inits an information dialog window.
	 *
	 * @param strHeaderText The header text of the information dialog window.
	 * @param strContentText The content text of the information dialog window.
	 */
	public static void initInformationDialog(String strHeaderText, String strContentText) {
		anInformationAlert.setTitle("Information");
		anInformationAlert.setHeaderText(strHeaderText);
		anInformationAlert.setContentText(strContentText);
		anInformationAlert.showAndWait();
	}

	/** Inits an information dialog window without a header text.
	 *
	 * @param strContentText The content text of the information dialog window.
	 */
	public static void initInformationDialogWithoutHeader(String strContentText) {
		anInformationAlert.setTitle("Information");
		anInformationAlert.setHeaderText(null);
		anInformationAlert.setContentText(strContentText);
		anInformationAlert.showAndWait();
	}

	/** Inits a warning dialog window.
	 *
	 * @param strHeaderText The header text of the warning dialog window.
	 * @param strContentText The content text of the warning dialog window.
	 */
	public static void initWarningDialog(String strHeaderText, String strContentText) {
		aWarningAlert.setTitle("Warning");
		aWarningAlert.setHeaderText(strHeaderText);
		aWarningAlert.setContentText(strContentText);
		aWarningAlert.showAndWait();
	}

	/**	Inits an error dialog window.
	 *
	 * @param strHeaderText The header text of the error dialog window.
	 * @param strContentText The content text of the error dialog window.
	 */
	public static void initErrorDialog(String strHeaderText, String strContentText) {
		anErrorAlert.setTitle("Error");
		anErrorAlert.setHeaderText(strHeaderText);
		anErrorAlert.setContentText(strContentText);
		anErrorAlert.showAndWait();
	}

	/**	Inits an error dialog window without the header text.
	 *
	 * @param strContentText The content text of the error dialog window.
	 */
	public static void initErrorDialogWithoutHeader(String strContentText) {
		anErrorAlert.setTitle("Error");
		anErrorAlert.setHeaderText(null);
		anErrorAlert.setContentText(strContentText);
		anErrorAlert.showAndWait();
	}

}
