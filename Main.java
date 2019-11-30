public class Main {

  public static int[][] workGrid;

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
    doWork();
  }
}
