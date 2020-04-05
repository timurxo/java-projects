package com.example;

import java.util.HashMap;
import java.util.Map;

public class Witcher implements Info {

    private String name;
    private int health;
    private int level;
    private String weapon;

    public Witcher(String name, int health, int level) {
        this.name = name;
        this.health = health;
        this.level = level;
        this.weapon = "Sword & Magic";
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

    public String getWeapon() {
        return weapon;
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

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }


    @Override
    public Map<Integer, String> save() {
        Map<Integer, String> witcherData = new HashMap<>();

        witcherData.put(0, this.name);
        witcherData.put(1, String.valueOf(this.health));
        witcherData.put(2, String.valueOf(this.level));
        witcherData.put(3, this.weapon);

        return witcherData;
    }

    @Override
    public void retrieve(Map<Integer, String> witcherInfo) {
        this.name = witcherInfo.get(0);
        this.health = Integer.parseInt(witcherInfo.get(1));
        this.level = Integer.parseInt(witcherInfo.get(2));
        this.weapon = witcherInfo.get(3);
    }

    @Override
    public String toString() {
        return "Your witcher is: " + this.name + "\n" +
                "Your health is: " + this.health + "\n" +
                "Your level is: " + this.level + "\n" +
                "Your weapon is: " + this.weapon + "\n";
    }

}
