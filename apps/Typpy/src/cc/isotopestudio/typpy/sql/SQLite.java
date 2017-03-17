package cc.isotopestudio.typpy.sql;
/*
 * Created by david on 2017/2/17.
 * Copyright ISOTOPE Studio
 */

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
    private final String dataFolderName;
    private final String filename;

    /**
     * Creates a new SQLite instance
     *
     * @param dataFolder Location of the Database
     * @param filename (Must end in .db)
     */
    public SQLite(String dataFolder, String filename) {
        this.dataFolderName = dataFolder;
        this.filename = filename;
    }

    @Override
    public Connection openConnection() throws SQLException,
            ClassNotFoundException {
        if (checkConnection()) {
            return connection;
        }

        File dataFolder = new File(dataFolderName);
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        File file = new File(dataFolder, filename);
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
                        + filename);
        return connection;
    }
}