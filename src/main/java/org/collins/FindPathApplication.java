package org.collins;

import org.collins.model.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FindPathApplication {

  static PathFinder pathFinder = new PathFinder();

  public static void main(String[] args) {

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
    plane3.add(" # #");
    plane3.add("   #");
    plane3.add("    ");
    plane3.add("# ##");
    plane3.add("    ");

    List<String> plane4 = new ArrayList<>();
    plane4.add("  # ");
    plane4.add("#  #");
    plane4.add("## #");
    plane4.add("    ");
    plane4.add("## #");
    plane4.add("#   ");

    List<String> plane5 = new ArrayList<>();
    plane5.add(" #   ");
    plane5.add("  #  ");
    plane5.add("   # ");
    plane5.add("     ");
    plane5.add("#    ");

    List<String> plane6 = new ArrayList<>();
    plane6.add("____#");
    plane6.add("###_#");
    plane6.add("____#");
    plane6.add("____#");
    plane6.add("_____");

  testPlane(plane1);
    System.out.println("----------------------------------------");
  testPlane(plane2);
    System.out.println("----------------------------------------");
  testPlane(plane3);
    System.out.println("----------------------------------------");
  testPlane(plane4);
    System.out.println("----------------------------------------");
    testPlane(plane5);
    System.out.println("----------------------------------------");
    testPlane(plane6);
    System.out.println("----------------------------------------");
  }

  static void testPlane(List<String> plane) {
    List<Point> result = pathFinder.findPath(plane);
    if (result != null) {
      List<String> newPlane = printResultsPlane(plane, result);
      newPlane.stream()
        .forEach(row -> System.out.printf("｜%s｜\n", row));
    }
  }

  static List<String> printResultsPlane(List<String> plane, List<Point> results) {
    for (Point point : results) {
      StringBuilder sb = new StringBuilder(plane.get(point.getY()));
      sb.setCharAt(point.getX(), '⭕');
      plane.set(point.getY(), sb.toString());
    }
    return plane.stream()
      .map(s -> s.replace(' ', '⬜'))
      .map(s -> s.replace('_', '⬜'))
      .map(s -> s.replace("#", "\uD83D\uDD34"))
      .collect(Collectors.toList());
  }
}
