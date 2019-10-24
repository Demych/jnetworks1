package com.jnetworks;

import java.util.*;
import java.util.stream.Collectors;

public class Printer {

    public static String reduceString(String pageNumber) {

        if (pageNumber == null || pageNumber.isEmpty()) {
            return "rawPagesNumbers parameter can't be null or empty";
        }

        StringBuilder reducePageNumber = new StringBuilder();

        List<Integer> notValidPositions = validateRaw(pageNumber);
        if (getNotValidPositions(reducePageNumber, notValidPositions))
            return reducePageNumber.toString();


        List<Integer> sortedPageNumber = null;
        try {
            sortedPageNumber = splitPageNumber(pageNumber);
        } catch (Exception e) {
            return "the numbers can't be greater than 2147483647";
        }
        if (sortedPageNumber.size() == 1)
            return sortedPageNumber.get(0).toString();
        reducePageNumber = reduceRawPageNumbers(sortedPageNumber);
        return reducePageNumber.toString();
    }

    private static StringBuilder reduceRawPageNumbers(List<Integer> sortedPageNumber) {
        StringBuilder reducePageNumber = new StringBuilder();
        int pageNumbers = sortedPageNumber.size();

        int startPage = sortedPageNumber.get(0);
        int endPage = sortedPageNumber.get(0);


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
        return reducePageNumber;
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
        try {
            for (int i = 0; i < splitPages.length; i++) {
                list.add(Integer.parseInt(splitPages[i]));
            }
        } catch (NumberFormatException e) {
            throw e;
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
