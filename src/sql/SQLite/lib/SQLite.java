package sql.SQLite.lib;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connects to and uses a SQLite database
 *
 * @author tips48
 */
public class SQLite extends Database {
    private final String dbLocation;
    private final String fileName;

    /**
     * Creates a new SQLite instance
     *
     * @param dbLocation Location of the Database (Must end in .db)
     */
    public SQLite(String dbLocation, String fileName) {
        this.dbLocation = dbLocation;
        this.fileName = fileName;
    }



    @Override
    public Connection openConnection() throws SQLException,
            ClassNotFoundException {
        if (checkConnection()) {
            return connection;
        }

        File dataFolder = new File(dbLocation);
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        File file = new File(dataFolder, fileName);
        if (!(file.exists())) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Unable to create database!");
            }
        }
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager
                .getConnection("jdbc:sqlite:"
                        + dataFolder + "/"
                        + fileName);
        return connection;
    }
}