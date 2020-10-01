/**	This class implements a method for switching the scenes in the application (in this case: the different GUIs of the
 * 	application).
 *
 */
package org.green_pioneer.cruiseship_management.guicontrollers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class SceneController {

	/**	Switches the scenes in the application.
	 *
	 * @param aMenuBar The menubar of the window with the event to switch the scene.
	 * @param strFolderpath The path of the .fxml-file.
	 * @param strFxmlFileName The name of the .fxml-file without the ending.
	 */
	public void switchSceneWithMenuBar(MenuBar aMenuBar, String strFolderpath, String strFxmlFileName) {
		Stage aStage;
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getClassLoader().getResource(strFolderpath + strFxmlFileName + ".fxml"));
			aStage = (Stage) aMenuBar.getScene().getWindow();
			Scene scene = new Scene(root);
			aStage.setScene(scene);
			aStage.show();
		} catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
}
