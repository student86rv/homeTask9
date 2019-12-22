package ua.epam.homeTask9.foo;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FooTest {

    private Foo foo;
    private String example;

    @Before
    public void init() {
        foo = new Foo();
        example = "firstsecondthird";
    }

    @Test
    public void test123() {
        List<Thread> threads = new ArrayList<>();
        threads.add(new Thread(foo::first));
        threads.add(new Thread(foo::second));
        threads.add(new Thread(foo::third));

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
        assertEquals(foo.getMessage(), example);
    }

    @Test
    public void test132() {
        List<Thread> threads = new ArrayList<>();
        threads.add(new Thread(foo::first));
        threads.add(new Thread(foo::third));
        threads.add(new Thread(foo::second));

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
        assertEquals(foo.getMessage(), example);
    }

    @Test
    public void test321() {
        List<Thread> threads = new ArrayList<>();
        threads.add(new Thread(foo::third));
        threads.add(new Thread(foo::second));
        threads.add(new Thread(foo::first));

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
        assertEquals(foo.getMessage(), example);
    }
}
