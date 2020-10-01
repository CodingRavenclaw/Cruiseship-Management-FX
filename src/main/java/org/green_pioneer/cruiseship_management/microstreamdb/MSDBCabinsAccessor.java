/**	This class implements the operation methods for a Microstream database for the cabins (e.g. creating the database,
 *  adding objects to it, updating objects of it and delete objects from it).
 *
 */
package org.green_pioneer.cruiseship_management.microstreamdb;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import one.microstream.storage.types.EmbeddedStorage;
import one.microstream.storage.types.EmbeddedStorageManager;
import org.green_pioneer.cruiseship_management.dbaccess.DBAccessCabins;
import org.green_pioneer.cruiseship_management.objectclasses.Cabin;

import java.util.*;

public class MSDBCabinsAccessor {

    //Init storage manager
    private static RootCabins aCabinRoot = new RootCabins();
    private DBAccessCabins aDBCabinsAccessor = new DBAccessCabins();

    // Creates a Microstream database.
    public void createDatabase() {

        //Root instance
        final EmbeddedStorageManager storageManager = EmbeddedStorage.start();

        //Get data from old system (in this case: MySQL)
        aCabinRoot.oblCabins.clear();
        aCabinRoot.oblCabins = aDBCabinsAccessor.getCabinsInObservableList(0, 1000);
        storageManager.setRoot(aCabinRoot);
        aCabinRoot.oblCabins.forEach(System.out::println);
        storageManager.storeRoot();
        storageManager.shutdown();
    }


    /** This method returns the cabins of the graph in an observable list, specified by the first and data limit given
     * 	as a parameter.
     *
     * @param intFirstIndex The first dataset of the graph.
     * @param intLastIndex The last index of the graph.
     * @return The observablelist filled with data from the Microstream database.
     */
    public ObservableList<Cabin> getCabinRootList(int intFirstIndex, int intLastIndex) {
        ObservableList<Cabin> limitedOblCabins = FXCollections.observableArrayList();
        limitedOblCabins.clear();

        if(aCabinRoot == null) {
            System.out.println("Keine Objekte enthalten!");
        } else {
            if(intLastIndex > aCabinRoot.oblCabins.size()) {
                intLastIndex = aCabinRoot.oblCabins.size();
            } else if(intFirstIndex < 0) {
                intFirstIndex = 0;
                intLastIndex = 24;
            }
            for(int intIndexcounter = intFirstIndex; intIndexcounter < intLastIndex; intIndexcounter++) {
                limitedOblCabins.add(aCabinRoot.oblCabins.get(intIndexcounter));
            }
        }
        return limitedOblCabins;
    }

    /** Insert a new cabin in the microstream database.
     *
     * @param intCabinNo The no of the cabin. Remember: It mustn't be 13!
     * @param strType The type of the cabin.
     * @param strDeck The deck on where the cabin is.
     * @param intMaxPassengerCapacity The max passenger capacity of the cabin.
     * @param dblSizeInSqm The size in sqm of the cabin.
     * @param intNoOfBeds The number of beds inside the cabin.
     * @param intNoOfToilets The no of the toilets inside the cabin.
     * @param dblPricePerPerson The price per person and per night for the cabin.
     */
    public void insertNewCabin(int intCabinNo, String strType, String strDeck, int intMaxPassengerCapacity,
                               double dblSizeInSqm, int intNoOfBeds, int intNoOfToilets, double dblPricePerPerson) {
        EmbeddedStorageManager storageManager = EmbeddedStorage.start();
        Cabin aNewCabin = new Cabin(intCabinNo, strType, strDeck, intMaxPassengerCapacity, dblSizeInSqm, intNoOfBeds, intNoOfToilets, dblPricePerPerson);
        aCabinRoot.oblCabins.add(aNewCabin);
        storageManager.store(aCabinRoot.oblCabins);
        storageManager.shutdown();
    }

    /** Updates an existing cabin in the microstream database.
     *
     * @param intCabinNo The number of the cabin which should be changed by the user. This parameter is required for the
     * 	 * 	 *           stream to find the cabin.
     * @param intNewCabinNo The new cabin number of the cabin.
     * @param strType The new type of the cabin.
     * @param strDeck The new deck of the cabin.
     * @param intMaxPassengerCapacity The new max passenger capacity of the cabin.
     * @param dblSizeInSqm The new size in sqm of the cabin.
     * @param intNoOfBeds The new number of beds of the cabin.
     * @param intNoOfToilets The new number of toilets of the cabin.
     * @param dblPricePerPerson The new price per person and per night of the cabin.
     */
    public void updateCabin(int intCabinNo, int intNewCabinNo, String strType, String strDeck, int intMaxPassengerCapacity,
                            double dblSizeInSqm, int intNoOfBeds, int intNoOfToilets, double dblPricePerPerson) {
        EmbeddedStorageManager storageManager = EmbeddedStorage.start();

        Cabin cabinToUpdate = aCabinRoot.oblCabins.stream()
                .filter(aCabin -> intCabinNo == aCabin.getIntCabinNo())
                .findAny()
                .orElse(null);

        cabinToUpdate.setIntCabinNo(intNewCabinNo);
        cabinToUpdate.setStrType(strType);
        cabinToUpdate.setStrDeck(strDeck);
        cabinToUpdate.setIntMaxPassengerCapacity(intMaxPassengerCapacity);
        cabinToUpdate.setDblSizeInSqM(dblSizeInSqm);
        cabinToUpdate.setIntNoOfBeds(intNoOfBeds);
        cabinToUpdate.setIntNoOfToilets(intNoOfToilets);
        cabinToUpdate.setDblPricePerPersonInEUR(dblPricePerPerson);
        storageManager.store(cabinToUpdate);
        storageManager.shutdown();
    }

    /** Deletes a cabin from the Microstream database
     *
     * @param intCabinNo the number of the cabin which should be deleted
     */
    public void deleteCabin(int intCabinNo) {
        EmbeddedStorageManager storageManager = EmbeddedStorage.start();
        Cabin cabinToDelete = aCabinRoot.oblCabins.stream()
                .filter(aCabin -> intCabinNo == aCabin.getIntCabinNo())
                .findAny()
                .orElse(null);
        aCabinRoot.oblCabins.remove(cabinToDelete);
        storageManager.store(aCabinRoot.oblCabins);
        storageManager.shutdown();
    }

    /** Sorts the cabins by their price and returns the sorted observable list.
     *
     * @param intFirstIndex The first index of the graph which should be sorted.
     * @param intLastIndex The last index of the graph which should be sorted.
     * @return The observable list with the sorted data.
     */
    public ObservableList<Cabin> sortByPrice(int intFirstIndex, int intLastIndex) {
        ObservableList<Cabin> oblCabinsToSortByPrice = FXCollections.observableArrayList();
        ObservableList<Cabin> oblSortedCabinsByPrice = FXCollections.observableArrayList();
        oblCabinsToSortByPrice.addAll((aCabinRoot.oblCabins));
        oblCabinsToSortByPrice.sort(Comparator.comparingDouble(Cabin::getDblPricePerPersonInEUR));
        if(intLastIndex > oblCabinsToSortByPrice.size()) {
            intLastIndex = oblCabinsToSortByPrice.size();
        } else if(intFirstIndex < 0) {
            intFirstIndex = 0;
            intLastIndex = 24;
        }
        oblSortedCabinsByPrice.clear();
        for(int intIndexcounter = intFirstIndex; intIndexcounter < intLastIndex; intIndexcounter++) {
            oblSortedCabinsByPrice.add(oblCabinsToSortByPrice.get(intIndexcounter));
        }
        return oblSortedCabinsByPrice;
    }

    /** Sort the list by the size in sqm
     *
     * @param intFirstIndex The first index of the list
     * @param intLastIndex The last index of the list
     * @return Returns a sortedlist by the type observablelist to fill a table with it
     */
    public ObservableList<Cabin> sortBySizeInSqm(int intFirstIndex, int intLastIndex) {
        ObservableList<Cabin> oblCabinsToSortBySizeInSqm = FXCollections.observableArrayList();
        ObservableList<Cabin> oblSortedCabinsBySizeInSqm = FXCollections.observableArrayList();
        oblCabinsToSortBySizeInSqm.addAll((aCabinRoot.oblCabins));
        oblCabinsToSortBySizeInSqm.sort(Comparator.comparingDouble(Cabin::getDblSizeInSqM));
        if(intLastIndex > oblCabinsToSortBySizeInSqm.size()) {
            intLastIndex = oblCabinsToSortBySizeInSqm.size();
        } else if(intFirstIndex < 0) {
            intFirstIndex = 0;
            intLastIndex = 24;
        }
        oblSortedCabinsBySizeInSqm.clear();
        for(int intIndexcounter = intFirstIndex; intIndexcounter < intLastIndex; intIndexcounter++) {
            oblSortedCabinsBySizeInSqm.add(oblCabinsToSortBySizeInSqm.get(intIndexcounter));
        }
        return oblSortedCabinsBySizeInSqm;
    }

    /** Finds the cabin by its number.
     *
     * @param intCabinNo The cabin number of the cabin which should be checked for existence.
     * @return The observable list with the founded cabin.
     */
    public ObservableList<Cabin> findCabinByCabinNo(int intCabinNo) {
        ObservableList<Cabin> oblFoundedCabin = FXCollections.observableArrayList();
        Cabin foundedCabin = aCabinRoot.oblCabins.stream()
                .filter(aCabin -> intCabinNo == aCabin.getIntCabinNo())
                .findAny()
                .orElse(null);
        oblFoundedCabin.add(foundedCabin);
        return oblFoundedCabin;
    }

    /** Returns the number of cabins which are stored in the database.
     *
     * @return The number of cabins as an int value.
     */
    public int getNumberOfCabins() {
        return aCabinRoot.oblCabins.size();
    }

    /** Returns the highest cabin number of the database.
     *
     * @return The highest cabin number as an int value.
     */
    public int getHighestCabinNo() {
        Cabin cabinWithHighestCabinNo = aCabinRoot.oblCabins.stream()
                .max(Comparator.comparingInt(Cabin::getIntCabinNo))
                .get();
        int intHighestCabinNo = cabinWithHighestCabinNo.getIntCabinNo();
        return intHighestCabinNo;
    }

    /** Returns the cabin with the highest passenger capacity of the database.
     *
     * @return The highest passenger capacity as an int value.
     */
    public int getMaxPassengerCapacity() {
        Cabin cabinWithMaxPassengerCapacity = aCabinRoot.oblCabins.stream()
                .max(Comparator.comparingInt(Cabin::getIntMaxPassengerCapacity))
                .get();
        int intMaxPassengerCapacity = cabinWithMaxPassengerCapacity.getIntMaxPassengerCapacity();
        return intMaxPassengerCapacity;
    }

    /** Returns the cabin with the highest size in sqm of the database.
     *
     * @return The highest size in sqm as a double value.
     */
    public double getMaxSizeInSqm() {
        Cabin cabinWithMaxSizeInSqm = aCabinRoot.oblCabins.stream()
                .max(Comparator.comparingDouble(Cabin::getDblSizeInSqM))
                .get();
        double dblMaxSizeInSqm = cabinWithMaxSizeInSqm.getDblSizeInSqM();
        return dblMaxSizeInSqm;
    }

    /** Returns the cabin with the highest number of beds of the database.
     *
     * @return The highest number of beds as a int value.
     */
    public int getMaxNoOfBeds() {
        Cabin cabinWithMaxNoOfBeds = aCabinRoot.oblCabins.stream()
                .max(Comparator.comparingInt(Cabin::getIntNoOfBeds))
                .get();
        int intMaxNoOfBeds = cabinWithMaxNoOfBeds.getIntNoOfBeds();
        return intMaxNoOfBeds;
    }

    /** Returns the cabin with the highest number of toilets of the database.
     *
     * @return The highest number of toilets as an int value.
     */
    public int getMaxNoOfToilets() {
        Cabin cabinWithMaxNoOfToilets = aCabinRoot.oblCabins.stream()
                .max(Comparator.comparingInt(Cabin::getIntNoOfToilets))
                .get();
        int intMaxNoOfToilets = cabinWithMaxNoOfToilets.getIntNoOfToilets();
        return intMaxNoOfToilets;
    }

}