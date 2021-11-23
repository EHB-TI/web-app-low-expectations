package com.brielage.uitleendienst.controllers;

import java.util.ArrayList;
import java.util.List;

public class RemoveDuplicatesController {
    public static <T> List<T> removeDuplicates(List<T> list)
    {
        List<T> newList = new ArrayList<>();

        for (T t : list) {
            if (!containsId(newList, t)) {
                newList.add(t);
            }
        }
        return newList;
    }

    public static <T> boolean containsId(final List<T> list, final T t){
        return list.stream().anyMatch(x -> x.equals(t));
    }
}
