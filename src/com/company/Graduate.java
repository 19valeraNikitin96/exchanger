package com.company;

import java.util.Random;
import java.util.concurrent.Exchanger;

public class Graduate implements Runnable {
    private Exchanger<Unit> exchanger;

    public Graduate(Exchanger<Unit> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        System.out.println("Graduate started to write dissertation");
        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(9000) + 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Graduate has finished dissertation");
        try {
            Unit result = exchanger.exchange(Unit.DISSERTATION);
            switch (result) {
                case OK:
                    System.out.println("- F*ck yeeah! I did it!!! I get " + result.getMsg());
                    break;
                default:
                    System.out.println(" - I must to fly to another country urgently!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
