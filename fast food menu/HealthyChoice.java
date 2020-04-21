package com.example;

/*
    allows add healthy choice at fast food chain
 */

public class HealthyChoice extends FastFoodMenu{

    public HealthyChoice(String firstChoice, String secondChoice, double totalPrice) {
        super(firstChoice, secondChoice, "Salad", "coffee", totalPrice);
    }


    @Override
    public void addOneMore(String additional1, double price) {
        super.addOneMore("apple", 1.00);
    }

    @Override
    public void addTwoMore(String additional1, String additional2, double price) {
        super.addTwoMore(additional1, additional2, 30.00);
    }
}
