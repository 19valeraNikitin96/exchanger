package com.company;

public enum Unit {
    OK("Master's degree diploma"), NOT_OK("Army"), DISSERTATION("Master's dissertation");

    private String msg;

    Unit(String msg){
        this.msg = msg;
    }

    public String getMsg(){
        return msg;
    }
}
