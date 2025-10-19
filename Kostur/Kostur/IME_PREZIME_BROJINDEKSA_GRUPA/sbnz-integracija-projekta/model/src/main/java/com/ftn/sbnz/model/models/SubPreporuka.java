package com.ftn.sbnz.model.models;


public class SubPreporuka {
    private String akcija;
    private int bonus;

    public SubPreporuka(String akcija, int bonus) {
        this.akcija = akcija;
        this.bonus = bonus;
    }

    public String getAkcija() { return akcija; }
    public int getBonus() { return bonus; }
}
