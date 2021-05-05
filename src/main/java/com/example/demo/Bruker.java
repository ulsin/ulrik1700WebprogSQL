package com.example.demo;

public class Bruker {
    private String navn;
    private String passord;

    public Bruker(String navn, String passord) {
        this.navn = navn;
        this.passord = passord;
    }

    public Bruker() {

    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }
}
