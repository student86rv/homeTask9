package ua.epam.homeTask9.fizzBuzz;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FizzBuzzTest {

    private FizzBuzz fizzBuzz;
    private int maxNumber;
    private String example;

    @Before
    public void init() {
        maxNumber = 20;
        fizzBuzz = new FizzBuzz(maxNumber);
        example = "1 2 fizz 4 buzz fizz 7 8 fizz buzz 11 fizz 13 14 fizzBuzz 16 17 fizz 19 buzz";
    }

    @Test
    public void testFizzBuzz() {
        List<Thread> threads = new ArrayList<>();
        threads.add(new Thread(fizzBuzz::fizzBuzz));
        threads.add(new Thread(fizzBuzz::fizz));
        threads.add(new Thread(fizzBuzz::buzz));
        threads.add(new Thread(fizzBuzz::number));

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        assertEquals(fizzBuzz.getMessage(), example);
    }
}

