package com.company.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties
public class SaldoResponse {
    private String status;
    private ArrayList<String> error;
    private Saldo payload;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<String> getError() {
        return error;
    }

    public void setError(ArrayList<String> error) {
        this.error = error;
    }

    public Saldo getPayload() {
        return payload;
    }

    public void setPayload(Saldo payload) {
        this.payload = payload;
    }
}
