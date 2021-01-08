package com.company.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties
public class TransazioniPayload {
    private ArrayList<Transazione> list;

    public ArrayList<Transazione> getList() {
        return list;
    }

    public void setList(ArrayList<Transazione> list) {
        this.list = list;
    }
}
