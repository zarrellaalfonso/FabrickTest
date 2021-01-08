package com.company.Manager;

import com.company.Model.ArgCheckResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ArgsManager {
    private final String noArgs = "No argument passed";
    private final String unavailableOperationPattern = "operation %s unavailable\nAvailable operations:\ngetSaldo\ngetTransazioni\npostBonifico";
    private final String noAccountId = "AccountId missing";
    private final String noStartDate = "Start date missing";
    private final String invalidStartDate = "Start date not valid\nIt should be yyyy-MM-dd";
    private final String noEndDate = "End date missing";
    private final String invalidEndDate = "End date not valid\nIt should be yyyy-MM-dd";
    private final String noPayload = "Payload missing";
    private final String invalidPayload = "Payload not valid\nIt should be a valid json";

    public ArgCheckResponse argsCheck(String[] args) {
        ArgCheckResponse resp = new ArgCheckResponse();
        if(args.length==0) {
            System.out.println(noArgs);
            resp.setOk(false);
            resp.setMsg(noArgs);
            return resp;
        }
        switch(args[0]) {
            case "getSaldo":
                System.out.println("executing getSaldo");
                resp = getSaldoArgsCheck(args);
                break;
            case "getTransazioni":
                System.out.println("executing getTransazioni");
                resp = getTransazioniArgsCheck(args);
                break;
            case "postBonifico":
                System.out.println("executing postBonifico");
                resp = postBonificoArgsCheck(args);
                break;
            default:
                String msg = String.format(unavailableOperationPattern, args[0]);
                System.out.println(msg);
                resp.setOk(false);
                resp.setMsg(msg);
        }
        return resp;
    }

    public ArgCheckResponse getSaldoArgsCheck(String[] args) {
        ArgCheckResponse resp = new ArgCheckResponse();
        resp.setOk(true);
        if(args.length<2) {
            resp.setOk(false);
            resp.setMsg(noAccountId);
            return resp;
        }
        return resp;
    }

    public ArgCheckResponse getTransazioniArgsCheck(String[] args) {
        ArgCheckResponse resp = new ArgCheckResponse();
        resp.setOk(true);
        if(args.length<2) {
            resp.setOk(false);
            resp.setMsg(noAccountId);
            return resp;
        }
        if(args.length<3) {
            resp.setOk(false);
            resp.setMsg(noStartDate);
            return resp;
        }
        if(args.length<4) {
            resp.setOk(false);
            resp.setMsg(noEndDate);
            return resp;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fromDate = sdf.parse(args[2]);
        } catch (ParseException e) {
            resp.setOk(false);
            resp.setMsg(invalidStartDate);
            return resp;
        }
        try {
            Date toDate = sdf.parse(args[3]);
        } catch (ParseException e) {
            resp.setOk(false);
            resp.setMsg(invalidEndDate);
            return resp;
        }
        return resp;
    }

    public ArgCheckResponse postBonificoArgsCheck(String[] args) {
        ArgCheckResponse resp = new ArgCheckResponse();
        resp.setOk(true);
        if(args.length<2) {
            resp.setOk(false);
            resp.setMsg(noAccountId);
            return resp;
        }
        if(args.length<3) {
            resp.setOk(false);
            resp.setMsg(noPayload);
            return resp;
        }
        if(args.length!=3) {
            resp.setOk(false);
            resp.setMsg(invalidPayload);
            return resp;
        }
        return resp;
    }
}
