import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Scheduler extends Thread {
  public Semaphore s1;
  public Semaphore s2;
  public Semaphore s3;
  public Semaphore s4;
  public Semaphore breakSem1, breakSem2, breakSem3, breakSem4;

  public SchedThread t1;
  public SchedThread t2;
  public SchedThread t3;
  public SchedThread t4;

  public int t1Missed, t2Missed, t3Missed, t4Missed;

  public Scheduler() {
    t1Missed = 0;
    t2Missed = 0;
    t3Missed = 0;
    t4Missed = 0;
  }

  public boolean checkDeadlines() {
    if (!t1.finished) {
      t1Missed++;
      return false;
    }
    if (!t2.finished) {
      t2Missed++;
      return false;
    }
    if (!t3.finished) {
      t3Missed++;
      return false;
    }
    if (!t4.finished) {
      t4Missed++;
      return false;
    }
    return true;
  }
  public void run() {
    s1 = new Semaphore(0);
    s2 = new Semaphore(0);
    s3 = new Semaphore(0);
    s4 = new Semaphore(0);
    breakSem1 = new Semaphore(0);
    breakSem2 = new Semaphore(0);
    breakSem3 = new Semaphore(0);
    breakSem4 = new Semaphore(0);

    t1 = new SchedThread(Main.workAmtOne, Main.periodOne, s1, breakSem1);
    t2 = new SchedThread(Main.workAmtTwo, Main.periodTwo, s2, breakSem2);
    t3 = new SchedThread(Main.workAmtThree, Main.periodThree, s3, breakSem3);
    t4 = new SchedThread(Main.workAmtFour, Main.periodFour, s4, breakSem4);

    t1.start();
    t2.start();
    t3.start();
    t4.start();

    s1.release();
    s2.release();
    s3.release();
    s4.release();
    int time = 0;
    for (int i = 0; i < 10; i++) { //program runs through 10 periods
      for (int j = 0; j < Main.framePeriod; j++) {
        try {
          Thread.sleep(Main.timeUnit);
        } catch (Exception e) {}

          time++;
          //System.out.println(time);

          if(time % t1.period == 0) {
            if(!t1.finished) {
              t1Missed++;
              t1.counter--;
            }
            s1.release();
          }
          if(time % t2.period == 0) {
            if(!t2.finished) {
              t2Missed++;
              t2.counter--;
            }
            s2.release();
          }
          if(time % t3.period == 0) {
            if(!t3.finished) {
              t3Missed++;
              t3.counter--;
            }
            s3.release();
          }
          if(time % t4.period == 0) {
            if(!t4.finished) {
              t4Missed++;
              t4.counter--;
            }
            s4.release();
          }
      }
    }
    breakSem1.release();
    breakSem2.release();
    breakSem3.release();
    breakSem4.release();
    try {
      t1.join();
      t2.join();
      t3.join();
      t4.join();
    } catch (Exception e) {}

    System.out.println("Thread 1 ===\nPeriod: " + t1.period + "\nCompletions: " + t1.counter + "\nMissed: " + t1Missed);
    System.out.println();
    System.out.println("Thread 2 ===\nPeriod: " + t2.period + "\nCompletions: " + t2.counter + "\nMissed: " + t2Missed);
    System.out.println();
    System.out.println("Thread 3 ===\nPeriod: " + t3.period + "\nCompletions: " + t3.counter + "\nMissed: " + t3Missed);
    System.out.println();
    System.out.println("Thread 4 ===\nPeriod: " + t4.period + "\nCompletions: " + t4.counter + "\nMissed: " + t4Missed);
  }
}
