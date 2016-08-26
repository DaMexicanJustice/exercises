/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercisesthread1.ex2;

/**
 *
 * @author Patrick Johansen
 */
public class Exercise2 {

    public static void main(String[] args) {

        Exercise2 ex2 = new Exercise2();
        Even even = ex2.new Even();

        new Thread(() -> {
            //System.out.println("Thread 1");
            int value = even.next();
            if (value % 2 == 0) {
                System.out.println("Value is even: " + value);
            } else {
                System.out.println("Value is odd: " + value);
            }
        }).start();

        new Thread(() -> {
            //System.out.println("Thread 2");
            int value = even.next();
            if (value % 2 == 0) {
                System.out.println("Value is even: " + value);
            } else {
                System.out.println("Value is odd: " + value);
            }
        }).start();
        
        /* Explain what happens?
        
            Any thread can be interrupted and paused at any time thanks to the
        scheduler being in charge of when threads are allowed to run. As a result
        we have no control and cannot say when either thread is calling the next
        method on the even object. Even if thread '1' goes first, it can be stopped
        in the middle of its list of instructions and thread 2 takes over and vice
        versa. The problem is known as the "race condition". 
        
        --> From Stackoverflow
        A race condition occurs when two or more threads can access shared data
        and they try to change it at the same time. Because the thread scheduling 
        algorithm can swap between threads at any time, you don't know the order
        in which the threads will attempt to access the shared data. 
        Therefore, the result of the change in data is dependent on the thread 
        scheduling algorithm, i.e. both threads are "racing" to access/change 
        the data. <--
        */
        
        /* How common is the problem 
        
            The problem is very common and as explained in the previous question
            it happens when multiple threads have access to the same shared data/
            memory/hardware. 
            The race condition only occurs when you have more than 1 thread. 
        */
        
    }
    
    /* Add synchronized to this method to make it atomic, so only 1 thread has
     access to it at any time */
    public class Even {

        public int n = 0;

        synchronized public int next() {
            n++;
            n++;
            return n;
        }
    }
}
