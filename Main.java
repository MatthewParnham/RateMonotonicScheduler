import java.util.*;
import java.io.*;

public class Main {

  public static int[][] workGrid;
  public static int periodOne = 1;
  public static int periodTwo = 2;
  public static int periodThree = 4;
  public static int periodFour = 16;
  public static int workAmtOne = 100;
  public static int workAmtTwo = 200;
  public static int workAmtThree = 400;
  public static int workAmtFour = 1600;

  public static int framePeriod = 16;
  public static int timeUnit = 10; //in ms

  public static void initWorkGrid() {
    workGrid = new int[10][10];
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        workGrid[i][j] = 1;
      }
    }
  }

  public static void doWork() {
    int total = 1;
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 10; j++) {
        total *= workGrid[j][i];
      }
      for (int k = 0; k < 10; k++) {
        total *= workGrid[k][i + 5];
      }
    }
  }

  public static void main(String[] args) {
    initWorkGrid();
    Thread scheduler = new Scheduler();
    scheduler.start();



  }
}
