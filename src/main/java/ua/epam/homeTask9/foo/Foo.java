package ua.epam.homeTask9.foo;

public class Foo {

    private boolean firstFinished = false;
    private boolean secondFinished = false;

    private StringBuilder message = new StringBuilder();

    public synchronized void first() {
        message.append("first");
        firstFinished = true;
        notifyAll();
    }

    public synchronized void second() {
        while (!firstFinished)
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        message.append("second");
        secondFinished = true;
        notify();
    }

    public synchronized void third() {
        while (!secondFinished)
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        message.append("third");
        notify();
    }

    public String getMessage() {
        return message.toString();
    }
}
