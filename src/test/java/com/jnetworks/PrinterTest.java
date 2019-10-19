package com.jnetworks;

import static org.junit.Assert.*;

public class PrinterTest {

    @org.junit.Test
    public void reduceString() {
        Printer printer = new Printer();
        String pageNumbers = "1,2,3,4,5,6,7,8,9,10";
        String expected = "1-10";
        String actual = printer.reduceString(pageNumbers);
        assertEquals(expected, actual);
    }

    @org.junit.Test
    public void reduceString1() {
        Printer printer = new Printer();
        String pageNumbers = "5,4,3,2,1,6,8,7,10,9";
        String expected = "1-10";
        String actual = printer.reduceString(pageNumbers);
        assertEquals(expected, actual);
    }

    @org.junit.Test
    public void reduceString2() {
        Printer printer = new Printer();
        String pageNumbers = "4,3,1,2,8,10,9,6";
        String expected = "1-4,6,8-10";
        String actual = printer.reduceString(pageNumbers);
        assertEquals(expected, actual);
    }
    @org.junit.Test
    public void reduceString3() {
        Printer printer = new Printer();
        String pageNumbers = "4,3,1,2,8,10,9,6,15";
        String expected = "1-4,6,8-10,15";
        String actual = printer.reduceString(pageNumbers);
        assertEquals(expected, actual);
    }

    @org.junit.Test
    public void reduceString4() {
        Printer printer = new Printer();
        String pageNumbers = "4,56,7";
        String expected = "4,7,56";
        String actual = printer.reduceString(pageNumbers);
        assertEquals(expected, actual);
    }
}