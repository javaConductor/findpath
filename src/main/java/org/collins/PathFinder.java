package org.collins;

import org.collins.model.Point;

import java.util.List;


public class PathFinder {

  public List<Point> findPath(List<String> plane) {
    if (plane.isEmpty())
      return null;

    if (!isPlaneRectangular(plane)){
      System.out.println("bad plane: all rows not same length");
      return null;
    }

    Point start = new Point(0, 0);
    Point end = new Point(plane.get(0).length() - 1, plane.size() - 1);

    return _findPath(plane, start, end, List.of());
  }

  private List<Point> _findPath(List<String> plane, Point start, Point end, List<Point> path) {
    // base case
    if (start.equals(end)) {
      path.add(end);
      return path;
    }

    // is this a dead end
    if (plane.get(start.getY()).charAt(start.getX()) == '#') {
      return null;
    }

    ///// recurse down and to the left

    // add this location to the path
    List<Point> newPath = Utils.append(path, start);

    Point right = new Point(start.getX() + 1, start.getY());
    Point down = new Point(start.getX(), start.getY() + 1);

    // can we go down
    if (down.getY() < plane.size()) {
      List<Point> downResult = _findPath(plane, down, end, newPath);
      if (downResult != null) {
        return downResult;
      }
    }

    // can we go right
    if (right.getX() < plane.get(0).length()) {
      List<Point> rightResult = _findPath(plane, right, end, newPath);
      return rightResult;
    }
    return null;
  }

  boolean isPlaneRectangular(List<String> plane) {
  int firstLen = plane.get(0).length();
  return plane.stream()
    .allMatch(s -> s.length() == firstLen);
  }

}
