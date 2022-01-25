package com.zproale.spotilite;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GestioneBrani {
    private ArrayList<Brano> brani;
    public GestioneBrani() {
        //Istanzio array list dei brani
        brani = new ArrayList<Brano>();
    }

    public boolean addBrano(Brano brano){
        //Aggiungo il brano
        brani.add(brano);
        return true;
    }
    public JSONObject findBrano(String titolo, String autore) throws JSONException {
        //Faccio un ciclo per cercare dentro il brano
        JSONObject branofind = new JSONObject();
        for (Brano brano : brani) {
            if(titolo.equals(brano.getTitolo()) || autore.equals(brano.getAutore())){
                branofind.put("status", true);
                branofind.put("titolo", brano.getTitolo());
                branofind.put("brano", brano.getTitolo());
                return branofind;
            }
        }
        branofind.put("status", false);
        return branofind;
    }

    public boolean isEmptyString(String string){
        if(string == null || string.isEmpty() || string.trim().isEmpty()){
            return true;
        }
        return false;
    }

    public String listaSong(){
        StringBuilder sbSong = new StringBuilder();
        for (Brano brc : brani ){
            sbSong.append(brc.getTitolo()+"-");
            sbSong.append(brc.getGenere());
            sbSong.append("\n");
        }
        return sbSong.toString();
    }
}

