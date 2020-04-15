package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleOperations {

    private Connection connection;

    // ESTABLISH CONNECTION
    public static final String DB_NAME = "BankingSystem.db";
    public static final String CONNECTING_STRING = "jdbc:sqlite:" + DB_NAME;

    // TABLE OF CUSTOMERS
    public static final String TABLE_CUSTOMERS = "customers";   // name of table

    public static final String COLUMN_CUSTOMER_ID = "id";      // if of table
    public static final String COLUMN_CUSTOMER_NAME = "name";   // contents of table
    public static final String COLUMN_CASH = "cash";
    public static final String COLUMN_CREDIT_SCORE = "score";

    public static final String COLUMN_CUSTOMER_BANKS = "bank";  // link to another table

    // TABLE OF BANKS
    public static final String TABLE_BANKS = "banks";

    public static final String COLUMN_BANKS_ID = "id";
    public static final String COLUMN_BANK_NAME = "name";
    public static final String COLUMN_BRANCH = "branch";

    public static final String COLUMN_BANK_STATE = "state";

    // TABLE OF STATES
    public static final String TABLE_STATES = "states";
    public static final String COLUMN_STATES_ID = "id";
    public static final String COLUMN_STATE_NAME = "name";


    // OPEN DATABASE
    public void openDB() {

        try {
            connection = DriverManager.getConnection(CONNECTING_STRING);

        } catch (SQLException e) {
            System.out.println("Can't connect to db " + e.getMessage());
        }

    }

    // CLOSE DATABASE
    public void closeDB() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Can't close db " + e.getMessage());
        }
    }


    // ------------------------------- PRINT OUT LIST OF BANKS -----------------------------
    // SELECT banks.name, banks.branch FROM banks ORDER BY banks.name COLLATE NOCASE ASC
    public List<Bank> getBanks() {

        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM banks " +
                                                        "ORDER BY name COLLATE NOCASE ASC");

        // check actual query
        System.out.println(stringBuilder.toString());

        // create statements and result set inside of try() so they will close themselves automatically afterwards
        try (Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery(stringBuilder.toString())) {

            // also add data to the array list to display it in the console
            List<Bank> banks = new ArrayList<>();

            // while there is data from result set
            while(results.next()) {
                Bank bank = new Bank();
                bank.setId(results.getInt(1));
                bank.setName(results.getString(2)); // data from 2nd column of Bank table
                bank.setBranch(results.getString(3));   // data from 3rd column
                // add to array list
                banks.add(bank);

            }
            return banks;


        } catch (SQLException e) {
            System.out.println("Something went wrong " + e.getMessage());
            return null;
        }

    }

    // --------------------- PRINT OUT CUSTOMER NAMES --------------
    // SELECT customer.name,
    public List<AllNames> customerNames() {

        StringBuilder stringBuilder = new StringBuilder("SELECT customers.name, banks.name, banks.branch, states.name FROM customers " +
                                                    "INNER JOIN banks ON banks.id = customers.bank " +
                                                    "INNER JOIN states ON states.id = banks.state " +
                                                    "ORDER BY customers.name COLLATE NOCASE ASC");

        try (Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(stringBuilder.toString())) {

            List<AllNames> allNames = new ArrayList<>();

            while(results.next()) {

                AllNames allNamesList = new AllNames();

                allNamesList.setCustomerName(results.getString(1));
                allNamesList.setBankName(results.getString(2));
                allNamesList.setCityName(results.getString(3));
                allNamesList.setStateName(results.getString(4));

                allNames.add(allNamesList);
            }

            return allNames;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;

        }

    }

    // get names of specific banks
    public List<String> namesByBanks(String bankName) {

        System.out.println(bankName + " members: ");

        StringBuilder stringBuilder = new StringBuilder("SELECT customers.name FROM customers " +
                                                        "INNER JOIN banks ON customers.bank = banks.id " +
                                                        "WHERE banks.name = '" + bankName + "' " +
                                                        "ORDER BY customers.name COLLATE NOCASE ASC");

        try (Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(stringBuilder.toString())) {

            List<String> names = new ArrayList<>();

            while(results.next()) {

                names.add(results.getString(1));

            }
            return names;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }

    }

}
