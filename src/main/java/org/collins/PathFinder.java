package org.collins;

import org.collins.model.Point;

import java.util.List;


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

    return _findPath(grid, start, end, List.of());
  }

  private List<Point> _findPath(List<String> grid, Point start, Point end, List<Point> path) {
    // base case
    if (start.equals(end)) {
      path.add(end);
      return path;
    }

    // is this a dead end
    if (grid.get(start.getY()).charAt(start.getX()) == '#') {
      return null;
    }

    ///// recurse down and to the left

    // add this location to the path
    List<Point> newPath = Utils.append(path, start);

    Point right = new Point(start.getX() + 1, start.getY());
    Point down = new Point(start.getX(), start.getY() + 1);

    List<Point> downResult = null;
    // can we go down
    if (down.getY() < grid.size()) {
      downResult = _findPath(grid, down, end, newPath);
    }
    List<Point> rightResult = null;
    // can we go right
    if (right.getX() < grid.get(0).length()) {
      rightResult = _findPath(grid, right, end, newPath);
    }

    if (rightResult == null){
      return downResult;
    }
    if(downResult == null){
      return rightResult;
    }

    if (downResult.size() < rightResult.size()){
      return downResult;
    }

    return rightResult;
  }

  boolean isGridRectangular(List<String> grid) {
  int firstLen = grid.get(0).length();
  return grid.stream()
    .allMatch(s -> s.length() == firstLen);
  }

}
