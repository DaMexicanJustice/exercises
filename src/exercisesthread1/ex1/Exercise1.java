/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercisesthread1.ex1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guna
 */
public class Exercise1 {

    private static boolean timeLimitMet = false;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        
        
        Thread1 t1 = new Thread1();
        t1.start();
        t1.join();
        Thread2 t2 = new Thread2();
        t2.start();
        t2.join();
        Thread3 t3 = new Thread3();
        t3.start();
        //t3.join();
        
        try {
            Thread.sleep(10000);
            timeLimitMet = true;
        } catch (InterruptedException ex) {
            
        } finally {
            System.out.println("\n ------------------- \n Thread exercise done.");
        }
        
    }

    public static class Thread1 extends Thread {

        private long sum = 0;

        @Override
        synchronized public void run() {
            System.out.println("Starting thread 1 \n ------------------------ \n");
            for (long l = 0; l < 1000000000; l++) {
                sum += l;
            }
            System.out.println(sum + "\n");
        }
    }

    public static class Thread2 extends Thread {
        
        @Override
        synchronized public void run() {
            System.out.println("Starting thread 2 \n ------------------------ \n");
            for (int i = 1; i <= 5; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                }
            }
        }
        
    }

    public static class Thread3 extends Thread {
        
        private short start = 10;
        
        @Override
        synchronized public void run() {
            System.out.println("Starting thread 3 \n ------------------------ \n");
            while (timeLimitMet == false) {
            
                System.out.println(start);
                start++;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    
                }
            
            }
        }

    }

}
