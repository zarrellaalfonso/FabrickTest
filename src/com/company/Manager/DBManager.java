package com.company.Manager;

import com.company.Model.Transazione;
import com.company.Model.TransazioniPayload;

public class DBManager {
    public void insertTransazioniPayLoad(TransazioniPayload payload) {
        if(payload!=null && payload.getList().size()>0) {
            for (Transazione trans: payload.getList()) {
                insertTransazione(trans);
            }
        }
        System.out.println("No transaction to save");
    }

    public void insertTransazione(Transazione trans) {

        System.out.println("Transaction " + trans.getTransactionId() + " saved");
    }
}
