package org.collins;

import org.collins.model.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindPathApplication {

  static PathFinder pathFinder = new PathFinder();

  public static void main(String args[]) {

    List<String> plane1 = new ArrayList<>();
    plane1.add("          ");
    plane1.add("          ");
    plane1.add("          ");
    plane1.add(" #########");
    plane1.add("          ");

    List<String> plane2 = new ArrayList<>();
    plane2.add(" #  ");
    plane2.add(" #  ");
    plane2.add(" #  ");
    plane2.add("    ");

    List<String> plane3 = new ArrayList<>();
    plane3.add(" #  ");
    plane3.add(" #  #");
    plane3.add("   #");
    plane3.add("    ");
    plane3.add("# ##");
    plane3.add("    ");

  testPlane(plane1);
    System.out.println("----------------------------------------");
  testPlane(plane2);
    System.out.println("----------------------------------------");
  testPlane(plane3);
    System.out.println("----------------------------------------");
  }
static void testPlane(List<String> plane){

  List<Point> result = pathFinder.findPath(plane);
  if (result != null){
    List<String> newPlane = printResultsPlane(plane, result);
    newPlane.stream()
      .forEach(row -> System.out.printf("|%s|\n", row ));
  }
}

  static void printResults(List<Point> results){
    results.stream()
      .forEach(point -> System.out.println(point));
  }

  static List<String> printResultsPlane(List<String> plane, List<Point> results){
    List<String> newPlane = new ArrayList<>(plane.size());
    for (Point point : results) {
      StringBuilder sb = new StringBuilder(plane.get(point.getY()));
      sb.setCharAt(point.getX(), '*');
      //newPlane = Utils.append(newPlane, sb.toString());
      plane.set(point.getY(), sb.toString());
    }
    return plane;
  }
}
