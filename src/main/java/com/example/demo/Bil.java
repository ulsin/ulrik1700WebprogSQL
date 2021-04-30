package com.example.demo;

public class Bil {
    private String merke;
    private String modell;

    public Bil(String merke, String modell) {
        this.merke = merke;
        this.modell = modell;
    }

    // empty contructor to make it a bean
    public Bil() {

    }

    public String getMerke() {
        return merke;
    }

    public void setMerke(String merke) {
        this.merke = merke;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }
}
