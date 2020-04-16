package com.example;

import java.util.ArrayList;
import java.util.List;

public class MainTest {

    public static void main(String[] args) {

        SimpleOperations simpleOperations = new SimpleOperations();

        simpleOperations.openDB();

        // -------------------- BANK, BRANCH --------------------
        List<Bank> banks = simpleOperations.getBanks();
        // loop through the list
        for(Bank bank : banks) {
            System.out.println("Bank: " + bank.getName() + " with branch in " + bank.getBranch());
        }

        System.out.println();

        // ------------------- NAMES: CUSTOMER, BANK, CITY, STATE ------------------------------
        List<AllNames> allNames = simpleOperations.customerNames();
        // loop through the list
        for(AllNames x : allNames) {
            System.out.println("Customer: " + x.getCustomerName() + "; Bank: " + x.getBankName() +
                                "; City: " + x.getCityName() + "; State: " + x.getStateName());
        }

        System.out.println();

        // ------------------- CUSTOMERS BY BANK NAME -------------------------
        List<String> customersByBank = simpleOperations.namesByBanks("Citigroup");

        for(String customers: customersByBank) {
            System.out.println(customers);
        }

        System.out.println();

        List<String> customersByBank1 = simpleOperations.namesByBanks("Bank of America");
        for(String customers: customersByBank1) {
            System.out.println(customers);
        }
        
        simpleOperations.closeDB();

    }

}
