package semaphore;

public class Main2 {
    public static void main(String[] args) {
        Message message = new Message("hey");
        new Echo(message);
        new Player(message);
    }
}
