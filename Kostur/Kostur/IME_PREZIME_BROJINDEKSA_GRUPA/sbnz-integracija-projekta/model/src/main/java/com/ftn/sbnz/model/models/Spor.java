package com.ftn.sbnz.model.models;

public class Spor {
    private String tip; // npr. "faktura", "ugovor", "šteta"
    private double iznos;
    private boolean rokIstekao;
    private boolean dokaziDostupni;
    private boolean formalniZahtevPoslat;

    // NOVO: broj dokaza
    private int brojDokaza;
    private boolean svedokPrisutan;
    private boolean pozitivanPresedan;
    private double verovatnoca;

    public Spor() {
    }

//    public Spor(String tip, double iznos, boolean rokIstekao, boolean dokaziDostupni, boolean formalniZahtevPoslat) {
//        this.tip = tip;
//        this.iznos = iznos;
//        this.rokIstekao = rokIstekao;
//        this.dokaziDostupni = dokaziDostupni;
//        this.formalniZahtevPoslat = formalniZahtevPoslat;
//    }

    public Spor(String tip, double iznos, boolean rokIstekao, boolean dokaziDostupni, boolean formalniZahtevPoslat, boolean svedokPrisutan,boolean pozitivanPresedan ) {
        this.tip = tip;
        this.iznos = iznos;
        this.rokIstekao = rokIstekao;
        this.dokaziDostupni = dokaziDostupni;
        this.formalniZahtevPoslat = formalniZahtevPoslat;
        this.brojDokaza = 0;
        this.svedokPrisutan = svedokPrisutan;
        this.pozitivanPresedan = pozitivanPresedan;
        this.verovatnoca = 0;
    }

    // GETTERI I SETTERI
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

    public int getBrojDokaza() { return brojDokaza; }
    public void setBrojDokaza(int brojDokaza) { this.brojDokaza = brojDokaza; }

    public boolean isSvedokPrisutan() { return svedokPrisutan; }
    public void setSvedokPrisutan(boolean svedokPrisutan) { this.svedokPrisutan = svedokPrisutan; }

    public boolean isPozitivanPresedan() { return pozitivanPresedan; }
    public void setPozitivanPresedan(boolean pozitivanPresedan) { this.pozitivanPresedan = pozitivanPresedan; }

    public double getVerovatnoca() { return verovatnoca; }
    public void setVerovatnoca(double verovatnoca) { this.verovatnoca = verovatnoca; }

    @Override
    public String toString() {
        return "Spor{" +
                "tip='" + tip + '\'' +
                ", iznos=" + iznos +
                ", rokIstekao=" + rokIstekao +
                ", dokaziDostupni=" + dokaziDostupni +
                ", formalniZahtevPoslat=" + formalniZahtevPoslat +
                ", brojDokaza=" + brojDokaza +
                ", svedokPrisutan=" + svedokPrisutan +
                ", pozitivanPresedan=" + pozitivanPresedan +
                ", verovatnoca=" + verovatnoca +
                '}';
    }
}

