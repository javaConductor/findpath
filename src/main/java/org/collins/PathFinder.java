package org.collins;

import org.collins.model.Point;

import java.util.*;


public class PathFinder {

  public List<Point> findPath(List<String> grid) {
    if (grid.isEmpty())
      return null;

    if (!isGridRectangular(grid)){
      System.out.println("bad grid: all rows not same length");
      return null;
    }

    Point start = new Point(0, 0);
    Point end = new Point(grid.get(0).length() - 1, grid.size() - 1);

    Map<Point, List<Point>> resultCache = new HashMap<>();

    List<Point> result = _findPath(grid, start, end, new ArrayList<>(), resultCache);
    resultCache.forEach((point, points) -> System.out.printf("\nresultCache pt: %s ---> %s\n", point, points));
    return result;
  }

  private List<Point> _findPath(List<String> grid, Point start, Point end, List<Point> path, Map<Point, List<Point>> resultCache) {

    System.out.println("Visiting: "+ start);
    // base case
    if (start.equals(end)) {
      path.add(end);
      return path;
    }

    // is this a dead end
    if (grid.get(start.getY()).charAt(start.getX()) == '#') {
      resultCache.put(start, null);
      return null;
    }

    if (resultCache.containsKey(start))
      return resultCache.get(start);

    ///// recurse down and to the left

    // add this location to the path
 //     List<Point> newPath = Utils.append(path, start);
    List<Point> newPath = new ArrayList<>();
    newPath.add( start);

    Point right = new Point(start.getX() + 1, start.getY());
    Point down = new Point(start.getX(), start.getY() + 1);
    Point left = new Point(start.getX() - 1, start.getY());
    Point up = new Point(start.getX(), start.getY() - 1);

    List<List<Point>> allResults = new ArrayList<>();

    // can we go down
    List<Point> downResult = null;
    if (down.getY() < grid.size()) {
      downResult = _findPath(grid, down, end, newPath, resultCache);
      if(downResult != null)
        allResults.add(downResult);
    }

    // can we go right
    List<Point> rightResult = null;
    if (right.getX() < grid.get(0).length()) {
      rightResult = _findPath(grid, right, end, newPath, resultCache);
      if(rightResult != null)
        allResults.add(rightResult);
    }
//
//    // can we go left
//    List<Point> leftResult = null;
//    if (left.getX() > 0 && !path.contains(left)) {
//      leftResult = _findPath(grid, left, end, newPath, resultCache);
//    }

//    // can we go up
//    List<Point> upResult = null;
//    if (start.getY() > 0 && !path.contains(up)) {
//      upResult = _findPath(grid, up, end, newPath, resultCache);
//    }

    List<Point> results = allResults.stream().max(Comparator.comparing(List::size)).get();
    resultCache.put(start, results);
    return Utils.concat(path, results);
  }

  boolean isGridRectangular(List<String> grid) {
  int firstLen = grid.get(0).length();
  return grid.stream()
    .allMatch(s -> s.length() == firstLen);
  }

}
