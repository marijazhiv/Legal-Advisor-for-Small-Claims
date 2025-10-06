package com.ftn.sbnz.model.models;

public class Preporuka {
    private String akcija; // npr. "nagodba", "tužba", "formalni zahtev"
    private int procenatUspeha;

    public Preporuka() {
    }

    public Preporuka(String akcija, int procenatUspeha) {
        this.akcija = akcija;
        this.procenatUspeha = procenatUspeha;
    }

    public String getAkcija() {
        return akcija;
    }

    public void setAkcija(String akcija) {
        this.akcija = akcija;
    }

    public int getProcenatUspeha() {
        return procenatUspeha;
    }

    public void setProcenatUspeha(int procenatUspeha) {
        this.procenatUspeha = procenatUspeha;
    }

    @Override
    public String toString() {
        return "Preporuka{" +
                "akcija='" + akcija + '\'' +
                ", procenatUspeha=" + procenatUspeha +
                '}';
    }
}


