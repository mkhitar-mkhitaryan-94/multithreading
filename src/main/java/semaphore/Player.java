package semaphore;

import java.util.Arrays;
import java.util.List;

public class Player implements Runnable {
    Message message;
    List<String> phrases = Arrays.asList("For the alliance", "For the horde", "Java is easy!");

    public Player(Message message) {
        this.message = message;
        new Thread(this,"Player").start();
    }

    @Override
    public void run() {
        while (true) {
            Double random = Math.random() * phrases.size();
            message.say(phrases.get(random.intValue()));
        }
    }
}
