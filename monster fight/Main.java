package com.example;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        Monsters monsters = new Monsters("Werewolf", 100, 10);

        saveStuff(monsters);

        //System.out.println( monsters.save() );
        System.out.println(monsters.toString());

        Witcher witcher = new Witcher("Geralt", 90, 15);

        System.out.println(witcher.toString());

        options(monsters, witcher);


    }

    private static void battleResult(Monsters monsters, Witcher witcher) {

        int witchersLevel = witcher.getLevel();
        int witchersHealth = witcher.getHealth();

        int monstersLevel = monsters.getLevel();
        int monstersHealth = monsters.getHealth();

        if ((witchersLevel > monstersLevel)
                || (monstersHealth * 5 < witchersHealth)) {
            System.out.println("Witcher slayed the monster!");

        } else if (((witchersLevel > monstersLevel) && (monstersHealth > witchersHealth * 5))
                || (witchersLevel * 2 < monstersLevel)) {
            System.out.println("Witcher is dead...");

        } else {
            System.out.println("Idk smth else happens");
        }

    }

    private static void options(Monsters monsters, Witcher witcher) {
        System.out.println("Do you want to fight a monster? ");
        System.out.println("0: Yes " + "\n" +
                "1: No " + "\n" +
                "9: Quit " + "\n");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 0:
                System.out.println("You fighting a monster...");
                addDelay(2);

                battleResult(monsters, witcher);
                break;
            case 1:
                System.out.println("You escaping the monster...");
                addDelay(2);

                System.out.println("You're safe!");
                addDelay(1);

                System.out.println("For now...");
                break;
            case 9:
                System.out.println("You quitting the application...");
                break;
            default:
                System.out.println("Yo");
                break;

        }
    }

    private static void addDelay(int delay) {
        // create delay
        try {
            TimeUnit.SECONDS.sleep(delay);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void saveStuff(Info object) {
        for (int i = 0; i < object.save().size(); i++) {
            //   System.out.println("Saving data " + object.save().get(i));
        }
    }


}
