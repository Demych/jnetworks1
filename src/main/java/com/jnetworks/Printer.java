package com.jnetworks;

import java.util.*;
import java.util.stream.Collectors;

public class Printer {

    public static String reduceString(String pageNumber) {

        StringBuilder reducePageNumber = new StringBuilder();

        List<Integer> notValidPositions = validateRaw(pageNumber);
        if (getNotValidPositions(reducePageNumber, notValidPositions))
            return reducePageNumber.toString();

        List<Integer> sortedPageNumber = splitPageNumber(pageNumber);
        int pageNumbers = sortedPageNumber.size();

        int startPage = sortedPageNumber.get(0);
        int endPage = sortedPageNumber.get(0);


        reduceRawPageNumbers(reducePageNumber, sortedPageNumber, pageNumbers, startPage, endPage);
        return reducePageNumber.toString();
    }

    private static void reduceRawPageNumbers(StringBuilder reducePageNumber, List<Integer> sortedPageNumber, int pageNumbers, int startPage, int endPage) {
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
    }

    private static boolean getNotValidPositions(StringBuilder reducePageNumber, List<Integer> notValidPositions) {
        if (!notValidPositions.isEmpty()) {
            reducePageNumber.append("not valid position is");
            notValidPositions.forEach(position -> reducePageNumber.append(" ").append(position));
            return true;
        }
        return false;
    }

    private static List<Integer> splitPageNumber(String pageNumber) {
        String[] splitPages = pageNumber.split(",");

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < splitPages.length; i++) {
            list.add(Integer.parseInt(splitPages[i]));
        }
        return list.stream().sorted().distinct().collect(Collectors.toList());
    }

    private static List<Integer> validateRaw(String rawPageNumbers) {
        char var;
        int rawLenght = rawPageNumbers.length();
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < rawLenght; i++) {
            var = rawPageNumbers.charAt(i);
            if ((var >= '0' && var <= '9') || var == ',') {
                continue;
            } else {
                positions.add(i);
            }
        }
        return positions;
    }
}
