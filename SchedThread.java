import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class SchedThread extends Thread {
  public int workAmt;
  public int period;
  public Semaphore sem;
  public boolean finished;
  public int counter;

  public SchedThread(int workAmt, int period, Semaphore sem) {
    this.workAmt = workAmt;
    this.period = period;
    this.sem = sem;
    this.finished = false;
    this.counter = 0;
  }

  public void work() {
    try {
      while(!finished) {
        sem.acquire();
        System.out.println(period);
        for (int i = 0; i < workAmt; i++) {
          Main.doWork();
        }
        finished = true;
        counter++;
      }
    } catch(Exception e) {}
  }

  public void run() {

    finished = false;
    work();
  }
}
