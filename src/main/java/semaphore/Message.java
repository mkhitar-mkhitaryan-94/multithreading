package semaphore;

import java.util.concurrent.Semaphore;

public class Message {
    String message;
    Semaphore playerSemaphore = new Semaphore(1);
    Semaphore echoSemaphore = new Semaphore(0);


    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void say(String message) {
        try {
            playerSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.setMessage(message);
        echoSemaphore.release();
        System.out.println("Sent " + this.getMessage());
    }

    public void reply() {
        try {
            echoSemaphore.acquire();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
           e.printStackTrace();
        }
        System.out.println("Received " + this.getMessage());
        playerSemaphore.release();
    }
}
