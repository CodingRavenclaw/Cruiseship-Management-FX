/** This class implements the root object (in this case: an observable list) for the microstream database.
 *
 */
package org.green_pioneer.cruiseship_management.microstreamdb;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.green_pioneer.cruiseship_management.objectclasses.Cabin;

public class RootCabins {

    public ObservableList<Cabin> oblCabins = FXCollections.observableArrayList();

}
