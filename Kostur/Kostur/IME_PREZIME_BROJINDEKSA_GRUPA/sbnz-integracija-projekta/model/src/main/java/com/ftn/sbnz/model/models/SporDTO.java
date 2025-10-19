package com.ftn.sbnz.model.models;

public class SporDTO {
    private String tip;
    private double iznos;
    private boolean rokIstekao;
    private boolean dokaziDostupni;
    private boolean formalniZahtevPoslat;
    private boolean svedokPrisutan;
    private boolean pozitivanPresedan;

    public SporDTO() {}

    public String getTip() { return tip; }
    public void setTip(String tip) { this.tip = tip; }
    public double getIznos() { return iznos; }
    public void setIznos(double iznos) { this.iznos = iznos; }
    public boolean isRokIstekao() { return rokIstekao; }
    public void setRokIstekao(boolean rokIstekao) { this.rokIstekao = rokIstekao; }
    public boolean isDokaziDostupni() { return dokaziDostupni; }
    public void setDokaziDostupni(boolean dokaziDostupni) { this.dokaziDostupni = dokaziDostupni; }
    public boolean isFormalniZahtevPoslat() { return formalniZahtevPoslat; }
    public void setFormalniZahtevPoslat(boolean formalniZahtevPoslat) { this.formalniZahtevPoslat = formalniZahtevPoslat; }
    public boolean isSvedokPrisutan() { return svedokPrisutan; }

    public boolean isPozitivanPresedan() {
        return pozitivanPresedan;
    }
}

