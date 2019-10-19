package com.jnetworks;

import java.util.*;
import java.util.stream.Collectors;

public class Printer {

    public static String reduceString(String pageNumber) {
        List<Integer> sortedPageNumber = splitPageNumber(pageNumber);

        int pageNumbers = sortedPageNumber.size();

        int startPage = sortedPageNumber.get(0);
        int endPage = sortedPageNumber.get(0);

        StringBuilder reducePageNumber = new StringBuilder();

        for (int i = 1; i < pageNumbers; i++) {
            if (sortedPageNumber.get(i - 1) + 1 == sortedPageNumber.get(i)) {
                endPage = sortedPageNumber.get(i);
                if (pageNumbers == i + 1) {
                    reducePageNumber.append(startPage).append("-").append(endPage);
                }
            } else {
                if (startPage == endPage) {
                    reducePageNumber.append(startPage).append(",");
                    startPage = sortedPageNumber.get(i);
                    endPage = sortedPageNumber.get(i);
                    if (pageNumbers == i + 1) {
                        reducePageNumber.append(endPage);
                    }
                } else {
                    reducePageNumber.append(startPage).append("-").append(endPage).append(",");
                    startPage = sortedPageNumber.get(i);
                    endPage = sortedPageNumber.get(i);
                    if (pageNumbers == i + 1) {
                        reducePageNumber.append(endPage);
                    }
                }
            }
        }
        return reducePageNumber.toString();
    }

    private static List<Integer> splitPageNumber(String pageNumber) {
        String[] splitPages = pageNumber.split(",");

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < splitPages.length; i++) {
            list.add(Integer.parseInt(splitPages[i]));
        }
        return list.stream().sorted().distinct().collect(Collectors.toList());
    }
}
