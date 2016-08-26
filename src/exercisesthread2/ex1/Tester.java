/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercisesthread2.ex1;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patrick Johansen
 */
public class Tester {

    public static void main(String[] args) {

        System.out.println("Avilable Processors: " + Runtime.getRuntime().availableProcessors() + "\n --------------");

        int sum = 0;
        long parallelRunTime = 0;

        try {
            MyThread t1 = new MyThread("https://fronter.com/cphbusiness/design_images/images.php/Classic/login/fronter_big-logo.png");
            MyThread t2 = new MyThread("https://fronter.com/cphbusiness/design_images/images.php/Classic/login/folder-image-transp.png");
            MyThread t3 = new MyThread("https://fronter.com/volY12-cache/cache/img/design_images/Classic/other_images/button_bg.png");
            // Sequential
            long start = System.nanoTime();
            t1.run();
            t2.run();
            t3.run();
            
            long end = System.nanoTime();

            // Parallel
            t1.start();
            t1.join();
            t2.start();
            t2.join();
            t3.start();
            t3.join();
            
            parallelRunTime += t1.getLifeTime();
            parallelRunTime += t2.getLifeTime();
            parallelRunTime += t3.getLifeTime();
            
            System.out.println("Time Sequental: " + (end - start) + "\n -------------");
            System.out.println("Time Parallel: " + (parallelRunTime - start) + "\n --------------");

            sum += t1.getBytes();
            sum += t2.getBytes();
            sum += t3.getBytes();

            System.out.println("The total sum of the images is: " + sum  + "\n --------------");
            
            System.out.println("The parallel operation with threads takes longer \n "
                    + "than the sequential solution. This is mostly because of the .join() \n "
                    + "that I call on the threads. We wait for the previous thread \n"
                    + "to finish before we execute the next, which takes a lot of time \n"
                    + "Since the order we finish in is important we have no choice \n"
                    + "but to prevent an error of the race condition type");

        } catch (IOException ex) {
            System.out.println("Java was too stupid to execute this code" + "\n --------------");
        } catch (InterruptedException ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
