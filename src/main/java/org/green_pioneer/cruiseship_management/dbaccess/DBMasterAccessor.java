/**	This class manages the connection to the MySQL database. It is inherited to every database access class to obtain
 *  their access to the methods to open and close the connection to the database.
 */

package org.green_pioneer.cruiseship_management.dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.green_pioneer.cruiseship_management.errorhelper.ErrorHelper;

public abstract class DBMasterAccessor {

	protected PreparedStatement stmt;
	protected Connection con;
	protected ResultSet rs = null;

    //  Opens a connection to the database.
    public void openDB() {
    	
        try {
            String url = "jdbc:mysql://localhost/greenpioneer?&serverTimezone=UTC";
            con = DriverManager.getConnection(url, "root", "");
        } catch (SQLException sqlex) {
            System.out.println(sqlex);
            ErrorHelper.initErrorDialogWithoutHeader("Unable to connect "
            		+ "to the database!");
        }
    }

    //  Closes a connection to the database.
    public void closeDB() {
        try {
            this.stmt.close();
            this.con.close();
            rs = null;
        } catch (SQLException sqlex) {
        	ErrorHelper.initErrorDialogWithoutHeader("Unable to close the "
        			+ "connection to the database!");
        }
    }
}