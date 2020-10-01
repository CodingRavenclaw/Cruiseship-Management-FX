/**	𝓒𝓻𝓾𝓲𝓼𝓮𝓼𝓱𝓲𝓹 𝓶𝓪𝓷𝓪𝓰𝓮𝓶𝓮𝓷𝓽
 *
 *	This class starts the application. But you need to import the MySQL-Database first and configure it in the
 *	class DBMasterAccessor.
 */
package org.green_pioneer.cruiseship_management;

import org.green_pioneer.cruiseship_management.guicontrollers.GUICabinsController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.green_pioneer.cruiseship_management.microstreamdb.MSDBCabinsAccessor;


public class Main extends Application {
	

	@Override
	public void start(Stage primaryStage) {
		try {
			MSDBCabinsAccessor aMSDBCabinsAccessor = new MSDBCabinsAccessor();
			aMSDBCabinsAccessor.createDatabase();
			GUICabinsController aGUICabinsController = new GUICabinsController();
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/GUICabins.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			aGUICabinsController.initialize();
		} catch(Exception ex) {
			System.out.println("Error..." + ex);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
