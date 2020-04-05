package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Monsters implements Info {

    private String name;
    private int health;
    private int level;

    public Monsters(String name, int health, int level) {
        this.name = name;
        this.health = health;
        this.level = level;
    }

    // ------------------------- GETTERS AND SETTERS --------------------------

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getLevel() {
        return level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public Map<Integer, String> save() {
        // save data in map
        Map<Integer, String> monsterData = new HashMap<>();

        monsterData.put(0, this.name);
        monsterData.put(1, String.valueOf(this.health));
        monsterData.put(2, String.valueOf(this.level));

        return monsterData;
    }

    // retrieve data and convert stuff if necessary
    @Override
    public void retrieve(Map<Integer, String> monsterData) {
        this.name = monsterData.get(0); // get value at key = 0
        this.health = Integer.parseInt(monsterData.get(1));
        this.level = Integer.parseInt(monsterData.get(2));
    }

    // to print out data
    @Override
    public String toString() {
        return "Monster you facing is: " + this.name + "\n" +
                "Current health: " + this.health + "\n" +
                "Level: " + this.level + "\n";
    }
}
