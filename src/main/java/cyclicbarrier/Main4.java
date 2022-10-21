package cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

public class Main4 {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,new Teleport());
        System.out.println("Jdem gruppu");
        new Thread(new HeroicPlayer(cyclicBarrier,"Damager1")).start();
        new Thread(new HeroicPlayer(cyclicBarrier,"Damager2")).start();
        new Thread(new HeroicPlayer(cyclicBarrier,"Damager3")).start();
        new Thread(new HeroicPlayer(cyclicBarrier,"Damager4")).start();
        new Thread(new HeroicPlayer(cyclicBarrier,"Damager5")).start();
    }
}

class Teleport implements Runnable{

    @Override
    public void run() {
        System.out.println("Teleportiruem vsex v dank! ");
    }
}
