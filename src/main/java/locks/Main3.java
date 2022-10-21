package locks;

import java.util.HashMap;
import java.util.Random;

public class Main3 {
    private static HashMap<String, String> map = new HashMap<>();
    private static final SaveDictionary saveDictionary = new SaveDictionary();

    public static void main(String[] args) throws InterruptedException {
        initMap();
        Thread t1 = new Thread(new Reader(), "Reader 1");
        Thread t11 = new Thread(new Reader(), "Reader 2");
        Thread t12 = new Thread(new Reader(), "Reader 3");
        Thread t13 = new Thread(new Reader(), "Reader 4");
        Thread t14 = new Thread(new Reader(), "Reader 5");
        Thread t15 = new Thread(new Reader(), "Reader 6");
        Thread t2 = new Thread(new WriterA());
        Thread t3 = new Thread(new WriterB());
        t1.start();
        t11.start();
        t12.start();
        t13.start();
        t14.start();
        t15.start();
        t2.start();
        t3.start();
        t1.join();
        t11.join();
        t12.join();
        t13.join();
        t14.join();
        t15.join();
    }

    static void initMap() {
        map.put("Java", "The best programming language");
        map.put("Hello, World!", "The most used phrase in the programming world");
        map.put("Deploy on Prod", "The reason of most stressful situations");
        map.put("It is simple", "A several sprints task");
    }


    static class Reader implements Runnable {

        @Override
        public void run() {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " is reading value ");
                System.out.println(saveDictionary.getAll());
                try {
                    Thread.sleep((long) Math.random() * 999 + 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class WriterA implements Runnable {
        Random random = new Random();
        Object[] values = map.keySet().toArray(new String[0]);

        @Override
        public void run() {
            while (true) {
                if (values.length > 0) {
                    String randomKey = (String) values[random.nextInt(values.length)];
                    saveDictionary.put(randomKey, map.get(randomKey));
                    System.out.println("Added value " + randomKey + " " + map.get(randomKey));
                }
                try {
                    Thread.sleep((long) Math.random() * 999 + 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class WriterB implements Runnable {
        Random random = new Random();
        Object[] values = map.keySet().toArray(new String[0]);

        @Override
        public void run() {
            while (true) {
                if (values.length > 0) {
                    String randomKey = (String) values[random.nextInt(values.length)];
                    saveDictionary.put(randomKey, map.get(randomKey));
                    System.out.println("Added value " + randomKey + " " + map.get(randomKey));
                }
                try {
                    Thread.sleep((long) Math.random() * 999 + 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
