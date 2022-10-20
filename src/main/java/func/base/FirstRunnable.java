package func.base;

public class FirstRunnable implements Runnable {
    @Override
    public void run() {
        int pr = 1;
        for (int i = 1; i < 20; i++) {
            pr *= i;

        }
        System.out.println("p = " + pr);

    }
}
