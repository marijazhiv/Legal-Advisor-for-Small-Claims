package com.ftn.sbnz.model.models;

public class Spor {
    private String tip; // npr. "faktura", "ugovor", "šteta"
    private double iznos;
    private boolean rokIstekao;
    private boolean dokaziDostupni;
    private boolean formalniZahtevPoslat;

    public Spor() {
    }

    public Spor(String tip, double iznos, boolean rokIstekao, boolean dokaziDostupni, boolean formalniZahtevPoslat) {
        this.tip = tip;
        this.iznos = iznos;
        this.rokIstekao = rokIstekao;
        this.dokaziDostupni = dokaziDostupni;
        this.formalniZahtevPoslat = formalniZahtevPoslat;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public boolean isRokIstekao() {
        return rokIstekao;
    }

    public void setRokIstekao(boolean rokIstekao) {
        this.rokIstekao = rokIstekao;
    }

    public boolean isDokaziDostupni() {
        return dokaziDostupni;
    }

    public void setDokaziDostupni(boolean dokaziDostupni) {
        this.dokaziDostupni = dokaziDostupni;
    }

    public boolean isFormalniZahtevPoslat() {
        return formalniZahtevPoslat;
    }

    public void setFormalniZahtevPoslat(boolean formalniZahtevPoslat) {
        this.formalniZahtevPoslat = formalniZahtevPoslat;
    }

    @Override
    public String toString() {
        return "Spor{" +
                "tip='" + tip + '\'' +
                ", iznos=" + iznos +
                ", rokIstekao=" + rokIstekao +
                ", dokaziDostupni=" + dokaziDostupni +
                ", formalniZahtevPoslat=" + formalniZahtevPoslat +
                '}';
    }
}

