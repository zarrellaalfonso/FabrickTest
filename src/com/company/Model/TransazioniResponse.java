package com.company.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties
public class TransazioniResponse {
    private String status;
    private ArrayList<String> error;
    private TransazioniPayload payload;

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

    public TransazioniPayload getPayload() {
        return payload;
    }

    public void setPayload(TransazioniPayload payload) {
        this.payload = payload;
    }
}
