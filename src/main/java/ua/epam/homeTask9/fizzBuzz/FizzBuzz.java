package ua.epam.homeTask9.fizzBuzz;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class FizzBuzz {

    private int maxNumber;
    private AtomicInteger counter = new AtomicInteger(1);
    private StringBuilder message = new StringBuilder();

    private Semaphore fizzBuzzSem = new Semaphore(1);
    private Semaphore fizzSem = new Semaphore(0);
    private Semaphore buzzSem = new Semaphore(0);
    private Semaphore numSem = new Semaphore(0);

    public FizzBuzz(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public void fizzBuzz() {
        while (counter.get() <= maxNumber) {
            try {
                fizzBuzzSem.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (counter.get() > maxNumber) {
                return;
            }
            if (counter.get() % 5 == 0 && counter.get() % 3 == 0) {
                message.append("fizzBuzz ");
                counter.incrementAndGet();
            }
            fizzSem.release();
            buzzSem.release();
            numSem.release();
        }
    }

    public void fizz() {
        while (counter.get() <= maxNumber) {
            try {
                fizzSem.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (counter.get() > maxNumber) {
                return;
            }
            if (counter.get() % 3 == 0 && counter.get() % 5 != 0) {
                message.append("fizz ");
                counter.incrementAndGet();
            }
            fizzBuzzSem.release();
            buzzSem.release();
            numSem.release();
        }
    }

    public void buzz() {
        while (counter.get() <= maxNumber) {
            try {
                buzzSem.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (counter.get() > maxNumber) {
                return;
            }
            if (counter.get() % 5 == 0 && counter.get() % 3 != 0) {
                message.append("buzz ");
                counter.incrementAndGet();
            }
            fizzBuzzSem.release();
            fizzSem.release();
            numSem.release();
        }
    }

    public void number() {
        while (counter.get() <= maxNumber) {
            try {
                numSem.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (counter.get() > maxNumber) {
                return;
            }
            if (counter.get() % 3 != 0 && counter.get() % 5 != 0) {
                message.append(counter.get());
                message.append(" ");
                counter.incrementAndGet();
            }
            fizzBuzzSem.release();
            fizzSem.release();
            buzzSem.release();
        }
    }

    public String getMessage() {
        return message.toString().trim();
    }
}
