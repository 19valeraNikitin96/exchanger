package com.company;

import java.util.concurrent.Exchanger;

public class Main {

    public static void main(String[] args) {
        Exchanger<Unit> exchanger = new Exchanger<>();
        new Thread(new Graduate(exchanger)).start();
        new Thread(new Commission(exchanger)).start();
    }
}
