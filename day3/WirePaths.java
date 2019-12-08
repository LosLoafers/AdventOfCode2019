import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.HashMap;
import java.lang.Math;

public class WirePaths {
  private HashMap<String, HashMap<String,Integer>> paths = new HashMap<String, HashMap<String,Integer>>();
  private int currentX = 0;
  private int currentY = 0;
  private int stepCounter = 0;
  private int originIntersection = 1000000;
  private int closestIntersection = 1000000;
  public static void main(String args[]) throws Exception {
    WirePaths wires = new WirePaths();
    wires.traceWires();
    System.out.println("Wire intersection closest to origin: " + wires.getOriginIntersection());
    System.out.println("Wire intersection closest in total distant: " + wires.getClosestIntersection());
  }

  public void traceWires() throws FileNotFoundException {
    try {
      Scanner sc = new Scanner(new File("input.txt"));
      int wireNbr = 1;
      while (sc.hasNextLine()) {
        Scanner path = new Scanner(sc.nextLine());
        path.useDelimiter(",");
        while (path.hasNext()) {
          drawPath(path.next(),wireNbr);
        }
        path.close();
        this.currentX = 0;
        this.currentY = 0;
        this.stepCounter = 0;
        wireNbr++;
      }
      sc.close();
    } catch(FileNotFoundException e) {
      System.out.println("Could not read file");
      System.exit(0);
    }
  }
  
  private void drawPath(String path, int wireNbr) {
    char direction = path.charAt(0);
    int steps = Integer.parseInt(path.substring(1,path.length()));
    for (int i=1;i<steps+1;i++) {
      switch (direction) {
        case 'U':
          this.currentY++;
        break;
        case 'D':
          this.currentY--;
        break;
        case 'L':
          this.currentX--;
        break;
        case 'R':
          this.currentX++;
        break;
        default:
          System.out.println("Not a valid direction: " + direction);
          System.exit(0);
      }
      trace(this.currentX, this.currentY, wireNbr);
    }
  }

  private void trace(int x, int y, int wireNbr) {
      markAsVisited(x, y, wireNbr);
      checkIntersection(x, y, wireNbr);
  } 

  private void markAsVisited(int x, int y, int wireNbr) {
    this.stepCounter++;
    HashMap<String, Integer> visited = this.paths.get(Integer.toString(x) + "," + Integer.toString(y));
    if (visited != null && visited.containsKey(Integer.toString(wireNbr))) {
      return;
    } else if (visited == null) {
      visited = new HashMap<String, Integer>();
    }
    visited.put(Integer.toString(wireNbr), this.stepCounter);
    this.paths.put(Integer.toString(x) + "," + Integer.toString(y),visited);
  }

  private void checkIntersection(int x, int y, int wireNbr) {
    HashMap<String, Integer> visited = this.paths.get(Integer.toString(x) + "," + Integer.toString(y));
    if (visited.keySet().size() > 1) {
      int origCandidate = Math.abs(x) + Math.abs(y);
      this.originIntersection = (origCandidate > 0 && origCandidate < this.originIntersection) ? origCandidate : this.originIntersection;
      int closeCandidate = stepsToIntersection(visited);
      this.closestIntersection = (closeCandidate > 0 && closeCandidate < this.closestIntersection) ? closeCandidate : this.closestIntersection;
    }
  }
  
  private int stepsToIntersection(HashMap<String,Integer> visited) {
    int nbrSteps = 0;
    for (int steps : visited.values()) {
      nbrSteps += steps;
    }
    return nbrSteps;
  }

  public int getOriginIntersection() {
    return this.originIntersection;
  }

  public int getClosestIntersection() {
    return this.closestIntersection;
  }
}
