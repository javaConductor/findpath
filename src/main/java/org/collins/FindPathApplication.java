package org.collins;

import org.collins.model.Point;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FindPathApplication {

  static PathFinder pathFinder = new PathFinder();

  public static void main(String[] args) {
    // get filename from the args
    if (args.length < 1)
      return;
    String filename = args[0];
    List<String> grid = null;

    try {
      // read grid
      grid = Files.readAllLines(new File(filename).toPath(), Charset.defaultCharset());
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (grid.isEmpty()){
      System.out.printf("grid is empty: %s%n", filename);
      return;
    }
    testGrid(grid);
    System.out.println("_".repeat(grid.get(0).length()*2));
  }

  static void testGrid(List<String> grid) {
    List<Point> result = pathFinder.findPath(grid);
    if (result != null) {
      List<String> newGrid = printResultsGrid(grid, result);
      newGrid
        .forEach(row -> System.out.printf("｜%s｜\n", row));
    }
  }

  static List<String> printResultsGrid(List<String> grid, List<Point> results) {
    System.out.printf("%d steps.\n", results.size());
    for (Point point : results) {
      StringBuilder sb = new StringBuilder(grid.get(point.getY()));
      sb.setCharAt(point.getX(), '⭕');
      grid.set(point.getY(), sb.toString());
    }
    return grid.stream()
      .map(s -> s.replace(" ", "\uD83D\uDD34"))
      .map(s -> s.replace("_", "\uD83D\uDD34"))
      .map(s -> s.replace("#", "\uD83D\uDFE5"))
      .collect(Collectors.toList());
  }
}
