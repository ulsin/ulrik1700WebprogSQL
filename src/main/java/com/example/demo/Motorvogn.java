package com.example.demo;

public class Motorvogn {

    private String personNr;
    private String navn;
    private String adresse;
    private String regNr;
    private String merke;
    private String biltype;

    public Motorvogn(String personNr, String navn, String adresse, String regNr, String merke, String biltype) {
        this.personNr = personNr;
        this.navn = navn;
        this.adresse = adresse;
        this.regNr = regNr;
        this.merke = merke;
        this.biltype = biltype;
    }

//    dubious empty constructor, need for being a bean!
    public Motorvogn() {

    }

    public String getPersonNr() {
        return personNr;
    }

    public void setPersonNr(String personNr) {
        this.personNr = personNr;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getRegNr() {
        return regNr;
    }

    public void setRegNr(String regNr) {
        this.regNr = regNr;
    }

    public String getMerke() {
        return merke;
    }

    public void setMerke(String merke) {
        this.merke = merke;
    }

    public String getBiltype() {
        return biltype;
    }

    public void setBiltype(String biltype) {
        this.biltype = biltype;
    }

}
