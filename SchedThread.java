import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class SchedThread extends Thread {
  public int workAmt;
  public int period;
  public Semaphore sem;
  public boolean finished;
  public int counter;
  public Semaphore scheduleSemaphore;

  public SchedThread(int workAmt, int period, Semaphore sem, Semaphore scheduleSemaphore) {
    this.workAmt = workAmt;
    this.period = period;
    this.sem = sem;
    this.finished = false;
    this.counter = 0;
    this.scheduleSemaphore = scheduleSemaphore;
  }

  public void work() {
    try {
      sem.acquire();
      finished = false;
      while(!finished) {

        //System.out.println(period);
        for (int i = 0; i < workAmt; i++) {
          Main.doWork();
        }
        finished = true;
        counter++;
      }
    } catch(Exception e) {}
  }

  public void run() {
    while(true) {
      work();
      if (scheduleSemaphore.tryAcquire()) {
        counter--;
        break;
      }
    }

  }
}
