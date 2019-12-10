package com.company;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Commission implements Runnable{
    private static final long TIMEWAITING = 3;
    private Exchanger<Unit> exchanger;

    public Commission(Exchanger<Unit> exchanger){
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Graduation Day has arrived");
        try {
            Unit result = exchanger.exchange(Unit.OK, TIMEWAITING, TimeUnit.SECONDS);
            switch (result){
                case DISSERTATION:
                    System.out.println("The graduate defended his "+result.getMsg()+" at 5");
                    break;
                default:
                    System.out.println("- Stupid graduate! You go to the army!!!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("- Stupid graduate! You go to the army!!!");
            try {
                exchanger.exchange(Unit.NOT_OK);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
