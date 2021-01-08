package com.company.Manager;

import com.company.Model.SaldoResponse;
import com.company.Model.TransazioniResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class OperationManager {

    private final String getSaldoUrl = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/%s";
    private final String getTransazioniUrl = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/%s/transactions?fromAccountingDate=%s&toAccountingDate=%s";
    private final String postBonificoUrl = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/%s/payments/money-transfers";
    private final String authSchema = "S2S";
    private final String apiKey = "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP";

    public void operationSwitch(String[] args) {
        switch(args[0]) {
            case "getSaldo":
                System.out.println("executing getSaldo");
                getSaldo(args, getSaldoUrl);
                break;
            case "getTransazioni":
                System.out.println("executing getTransazioni");
                getTransazioni(args, getTransazioniUrl);
                break;
            case "postBonifico":
                System.out.println("executing postBonifico");
                postBonifico(args, postBonificoUrl);
                break;
        }
    }

    public void getSaldo(String[] args, String urlPattern) {
        String urlCompleto = String.format(urlPattern,args[1]);
        System.out.println("Url invocato: " + urlCompleto);
        try {
            URL url = new URL(urlCompleto);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Auth-Schema", authSchema);
            conn.setRequestProperty("Api-Key", apiKey);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            int status = conn.getResponseCode();
            System.out.println("Connection response code: " + status);
            Reader streamReader = null;
            if(status > 299) {
                streamReader = new InputStreamReader(conn.getErrorStream());
            } else {
                streamReader = new InputStreamReader(conn.getInputStream());
            }
            BufferedReader resp = new BufferedReader(streamReader);
            String respLine;
            StringBuffer respFullText = new StringBuffer();
            while((respLine = resp.readLine())!=null) {
                System.out.println(respLine);
                respFullText.append(respLine);
            }
            System.out.println("Risposta: " + respFullText);
            streamReader.close();
            conn.disconnect();
            ObjectMapper om = new ObjectMapper();
            SaldoResponse objResp = om.readValue(respFullText.toString(),SaldoResponse.class);
            System.out.println("Iban letto: " + objResp.getPayload().getIban());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getTransazioni(String[] args, String urlPattern) {
        String urlCompleto = String.format(urlPattern,args[1],args[2],args[3]);
        System.out.println(urlCompleto);
        try {
            URL url = new URL(urlCompleto);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Auth-Schema", authSchema);
            conn.setRequestProperty("Api-Key", apiKey);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            int status = conn.getResponseCode();
            System.out.println("Connection response code: " + status);
            Reader streamReader = null;
            if(status > 299) {
                streamReader = new InputStreamReader(conn.getErrorStream());
            } else {
                streamReader = new InputStreamReader(conn.getInputStream());
            }
            BufferedReader resp = new BufferedReader(streamReader);
            String respLine;
            StringBuffer respFullText = new StringBuffer();
            while((respLine = resp.readLine())!=null) {
                System.out.println(respLine);
                respFullText.append(respLine);
            }
            System.out.println("Risposta: " + respFullText);
            streamReader.close();
            conn.disconnect();
            ObjectMapper om = new ObjectMapper();
            TransazioniResponse objResp = om.readValue(respFullText.toString(), TransazioniResponse.class);
            System.out.println("Descrizione letta: " + objResp.getPayload().getList().get(0).getDescription());
            System.out.println("Tipo letto: " + objResp.getPayload().getList().get(0).getType().getValue());
            DBManager dbm = new DBManager();
            dbm.insertTransazioniPayLoad(objResp.getPayload());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void postBonifico(String[] args, String urlPattern) {
        String urlCompleto = String.format(urlPattern,args[1]);
        System.out.println(urlCompleto);
        String jsonString = "{\"creditor\":{\"name\":\"JohnDoe\",\"account\":{\"accountCode\":\"IT23A0336844430152923804660\",\"bicCode\":\"SELBIT2BXXX\"},\"address\":{\"address\":null,\"city\":null,\"countryCode\":null}},\"executionDate\":\"2019-04-01\",\"uri\":\"REMITTANCE_INFORMATION\",\"description\":\"Paymentinvoice75/2017\",\"amount\":800,\"currency\":\"EUR\",\"isUrgent\":false,\"isInstant\":false,\"feeType\":\"SHA\",\"feeAccountId\":\"45685475\",\"taxRelief\":{\"taxReliefId\":\"L449\",\"isCondoUpgrade\":false,\"creditorFiscalCode\":\"56258745832\",\"beneficiaryType\":\"NATURAL_PERSON\",\"naturalPersonBeneficiary\":{\"fiscalCode1\":\"MRLFNC81L04A859L\",\"fiscalCode2\":null,\"fiscalCode3\":null,\"fiscalCode4\":null,\"fiscalCode5\":null},\"legalPersonBeneficiary\":{\"fiscalCode\":null,\"legalRepresentativeFiscalCode\":null}}}";
        try {
            URL url = new URL(urlCompleto);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Auth-Schema", authSchema);
            conn.setRequestProperty("Api-Key", apiKey);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
                os.write(jsonString.getBytes());
                os.flush();
                os.close();
            int status = conn.getResponseCode();
            System.out.println("Connection response code: " + status);
            Reader streamReader = null;
            if(status != 201) {
                streamReader = new InputStreamReader(conn.getErrorStream());
            } else {
                streamReader = new InputStreamReader(conn.getInputStream());
            }
            BufferedReader resp = new BufferedReader(streamReader);
            String respLine;
            StringBuffer respFullText = new StringBuffer();
            while((respLine = resp.readLine())!=null) {
                System.out.println(respLine);
                respFullText.append(respLine);
            }
            System.out.println("Risposta: " + respFullText);
            streamReader.close();
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
