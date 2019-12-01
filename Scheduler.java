import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Scheduler extends Thread {
  public Semaphore s1;
  public Semaphore s2;
  public Semaphore s3;
  public Semaphore s4;

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
    s1 = new Semaphore(1);
    s2 = new Semaphore(0);
    s3 = new Semaphore(0);
    s4 = new Semaphore(0);

    t1 = new SchedThread(Main.workAmtOne, Main.periodOne, s1);
    t2 = new SchedThread(Main.workAmtTwo, Main.periodTwo, s2);
    t3 = new SchedThread(Main.workAmtThree, Main.periodThree, s3);
    t4 = new SchedThread(Main.workAmtFour, Main.periodFour, s4);

    t1.start();
    t2.start();
    t3.start();
    t4.start();

    try {
      Thread.sleep(1000);
      s2.release();
      Thread.sleep(1000);
      s3.release();
      Thread.sleep(1000);
      s4.release();
    } catch(Exception e) {}

    for (int i = 0; i < 10; i++) { //program runs through 10 periods
      for (int j = 0; j < Main.framePeriod; j++) {

      }
    }
    try {
      t1.join();
      t2.join();
      t3.join();
      t4.join();
    } catch (Exception e) {}
  }
}
