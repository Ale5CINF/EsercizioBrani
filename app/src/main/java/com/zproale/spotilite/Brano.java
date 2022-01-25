package com.zproale.spotilite;
import java.util.Date;

public class Brano {
    private String titolo;
    private String autore;
    private String genere;

    public Brano(String titolo, String autore, String genere) {
        this.titolo = titolo;
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getGenere() {
        return genere;
    }

}
