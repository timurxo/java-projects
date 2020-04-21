package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleOperations2 {

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


    // PROTECTION
    private PreparedStatement queryCustomerView;
    // PREPARED TRANSACTION
    private PreparedStatement insertIntoStates;
    private PreparedStatement queryStates;


    // -------------------- OPEN DATABASE --------------------
    public void openDB() {

        try {
            connection = DriverManager.getConnection(CONNECTING_STRING);

            // protected query
            queryCustomerView = connection.prepareStatement("SELECT name, cash, score " +
                    "FROM customers_list WHERE score = ?");

            // protected transaction (insert states)
            insertIntoStates = connection.prepareStatement("INSERT INTO states (name) VALUES (?)");
            // check if state is already in the list
            queryStates = connection.prepareStatement("SELECT name FROM states WHERE name =?");

        } catch (SQLException e) {
            System.out.println("Can't connect to db " + e.getMessage());
        }

    }

    // -------------------- CLOSE DATABASE --------------------
    public void closeDB() {
        try {
            // close prepared statement
            queryCustomerView.close();

            // close transaction
            insertIntoStates.close();

            connection.close();

        } catch (SQLException e) {
            System.out.println("Can't close db " + e.getMessage());
        }
    }


    // -------------------- CREATE VIEW FOR CUSTOMERS --------------------
    public void createViewForCustomers() {

        try (Statement statement = connection.createStatement()) {

            statement.execute("CREATE VIEW IF NOT EXISTS customers_list AS " +
                    "SELECT name, cash, score FROM customers " +
                    "ORDER BY name");


        } catch (SQLException e) {
            System.out.println("Wasn't able to create a view " + e.getMessage());
        }

    }


    // -------------------- PREPARED STATEMENT QUERY FOR CUSTOMERS --------------------
    public List<Customer> queryViewForCustomers(int score) {

        try {
            // use prepared statement
            queryCustomerView.setInt(1, score); // 1 - position of placeholder
            // we look for the first occurrence of = sign in query

            ResultSet results = queryCustomerView.executeQuery();

            List<Customer> customers = new ArrayList<>();

            while (results.next()) {
                Customer customer = new Customer();
                customer.setCustomerName(results.getString(1));
                customer.setCash(results.getInt(2));
                customer.setScore(results.getInt(3));
                customers.add(customer);

            }

            return customers;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }


    // -------------------- INSERT STATE --------------------
    public void insertState(String name) {

        try {
            // START TRANSACTION
            connection.setAutoCommit(false);

            queryStates.setString(1, name);

            ResultSet results = queryStates.executeQuery();

            // if we already don't have the state with the same name in the table
            if (!results.next()) {
                // insert name in the query
                insertIntoStates.setString(1, name);
            } else {
                throw new SQLException("You already have this state in the list");
            }

            connection.commit();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

            try {
                connection.rollback(); // get back to the old version of table
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());
            }
            // RETURN TO DEFAULT AUTO COMMIT BEHAVIOR - HAPPENING IF EITHER TRANSACTION SUCCEEDS OR FAILS
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {

            }
        }


    }


    // -------------------- PREPARED STATEMENT QUERY FOR STATES --------------------
    public List<State> queryStates() {

        StringBuilder stringBuilder = new StringBuilder("SELECT id, name FROM states ORDER BY id ");

        try (Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery(stringBuilder.toString())) {


            List<State> states = new ArrayList<>();

            while (results.next()) {
                State state = new State();
                state.setId(results.getInt(1));
                state.setName(results.getString(2));

                states.add(state);

            }

            return states;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

}
