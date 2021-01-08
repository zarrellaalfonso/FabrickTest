package com.company;

import com.company.Manager.ArgsManager;
import com.company.Manager.OperationManager;
import com.company.Model.ArgCheckResponse;

public class Main {

    public static void main(String[] args) {
        String finalMsg;
        ArgsManager argsManager = new ArgsManager();
        ArgCheckResponse resp = argsManager.argsCheck(args);
        if(resp.isOk()) {
            OperationManager om = new OperationManager();
            om.operationSwitch(args);
            System.out.println("Run ok");
        } else {
            System.out.println("Run ko");
        }
        System.out.println(resp.getMsg());
    }
}
