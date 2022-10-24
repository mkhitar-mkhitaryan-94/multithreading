package phaser;

import java.util.concurrent.Phaser;

public class Main2 {
    public static void main(String[] args) {
        Phaser phaser = new Phaser();

        new Thread(new CookingThread(phaser, "pot")).start();
        new Thread(new CookingThread(phaser, "knife")).start();
        new Thread(new CookingThread(phaser, "rope")).start();

        phaser.register();

        int phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Faza "  +  phase + " zavershena");

        phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Faza "  +  phase + " zavershena");

        phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Faza "  +  phase + " zavershena");

        phaser.arriveAndDeregister();

        if (phaser.isTerminated()){
            System.out.println("Fazer zavershil rabotu");
        }
    }
}
