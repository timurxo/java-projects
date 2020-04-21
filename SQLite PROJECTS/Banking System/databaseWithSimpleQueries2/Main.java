package com.example;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        SimpleOperations2 simpleOperations = new SimpleOperations2();

        simpleOperations.openDB();

        // -------------------- CREATE VIEW FOR CUSTOMERS --------------------
        simpleOperations.createViewForCustomers();

        // -------------------- GET DATA BY USING PREPARED STATEMENT --------------------
        List<Customer> customers = simpleOperations.queryViewForCustomers(900);

        for(Customer cus: customers) {
            System.out.println(cus.getCustomerName());
        }

        System.out.println();

        // -------------------- INSERT NEW STATE --------------------
        simpleOperations.insertState("Nebraska");

        // -------------------- GET DATA FROM STATES --------------------
        List<State> states = simpleOperations.queryStates();

        for(State st: states) {
            System.out.println(st.getId() + ": " + st.getName());
        }



        simpleOperations.closeDB();

    }
}
