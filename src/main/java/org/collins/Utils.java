package org.collins;

import org.collins.model.Point;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

  public static <T> List<T> append(List<T> list, T newElement){
    return Stream.concat(list.stream(), Stream.of(newElement))
      .collect(Collectors.toList());
  }

  public static <T> List<T> concat(List<T> list1, List<T> list2){
    return Stream.concat(list1.stream(), list2.stream())
      .collect(Collectors.toList());
  }

//
//  public static <T> List<T> insert(List<T> list, int index, T newElement){
//
//    return Stream.concat(list.stream(), Stream.of(newElement))
//      .collect(Collectors.toList());
//  }



}
