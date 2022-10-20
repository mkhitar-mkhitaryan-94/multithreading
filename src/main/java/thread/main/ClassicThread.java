package thread.main;

public class ClassicThread extends Thread {
    private final FileResource resource;

    public ClassicThread(String name, FileResource resource) {
        super(name);
        this.resource = resource;
    }

    public void bookWriting() {
        for (int i = 0; i < 5; i++) {
            resource.writeString(i);
        }
    }

    @Override
    public void run() {
        bookWriting();
    }
}
