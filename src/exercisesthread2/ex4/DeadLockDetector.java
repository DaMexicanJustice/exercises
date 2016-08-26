package exercisesthread2.ex4;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author Lars Mortensen
 */
class DeadLockDetector implements Runnable {

    ThreadMXBean tmxb = ManagementFactory.getThreadMXBean();
    boolean doRun = true;

    public void stop() {
        this.doRun = false;
    }

    @Override
    public void run() {
        while (doRun) {
            long[] threadIds = tmxb.findDeadlockedThreads();
            //ThreadInfo threadInfo[] = tmxb.getThreadInfo(threadIds);

            if (threadIds != null) {
                for (int i = 0; i < threadIds.length; i++) {
                    System.out.println("Deadlock detected: " + threadIds[i]);
                }
            } else {
                stop();
            }
        }
    }
}
