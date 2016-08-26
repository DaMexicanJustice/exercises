package exercisesthread2.ex4;

public class Tester {
  public static void main(String[] args) {
      try {
      ResourceContainer resources = new ResourceContainer();
      ResourceUser1 t1 = new ResourceUser1(resources);
      ResourceUser2 t2 = new ResourceUser2(resources);
      Runnable r = () -> {
          DeadLockDetector dld = new DeadLockDetector();
          dld.run();
      };
      
      /* If this revealed a deadlock: identify and solve
      
        t1 and t2 deadlocks.  
        The issue was the order in which the resourceUser accessed the methods.
        I moved around the execution order in resourceUser2 so that it matches
        resourceUser1. 
      
      */
      
      Thread dldT = new Thread(r);
      dldT.start(); 
      
      t1.start();
      t2.start();
      t1.join();
      t2.join();
      
      
      System.out.println("Done");
      System.out.println("Words produced: "+resources.getResourceWords().size());
      System.out.println("Numbers produced: "+resources.getResourceNumbers().size());
      } catch (InterruptedException ex) {
          System.out.println(ex.getMessage());
      }
  }
}
