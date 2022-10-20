package thread.main;

import java.io.IOException;

public class SynchroMain {
    public static void main(String[] args) throws IOException {
        FileResource res = new FileResource("str.txt");
        ClassicThread thread1 = new ClassicThread("Tolstoy",res);
        ClassicThread thread2 = new ClassicThread("Dickens",res);
        thread1.start();
        thread2.start();
    }
}
