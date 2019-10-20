package com.jnetworks;

import com.sun.tracing.ProviderName;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public class PrinterTest {

    @org.junit.Test
    public void reduceString() {
        String pageNumbers = "1,2,3,4,5,6,7,8,9,10";
        String expected = "1-10";
        String actual = Printer.reduceString(pageNumbers);
        assertEquals(expected, actual);
    }

    @org.junit.Test
    public void reduceString1() {
        String pageNumbers = "5,4,3,2,1,6,8,7,10,9";
        String expected = "1-10";
        String actual = Printer.reduceString(pageNumbers);
        assertEquals(expected, actual);
    }

    @org.junit.Test
    public void reduceString2() {
        String pageNumbers = "4,3,1,2,8,10,9,6";
        String expected = "1-4,6,8-10";
        String actual = Printer.reduceString(pageNumbers);
        assertEquals(expected, actual);
    }
    @org.junit.Test
    public void reduceString3() {
        String pageNumbers = "4,3,1,2,8,10,9,6,15";
        String expected = "1-4,6,8-10,15";
        String actual = Printer.reduceString(pageNumbers);
        assertEquals(expected, actual);
    }

    @org.junit.Test
    public void reduceString4() {
        String pageNumbers = "4,56,7";
        String expected = "4,7,56";
        String actual = Printer.reduceString(pageNumbers);
        assertEquals(expected, actual);
    }

    @org.junit.Test
    public void reduceString5() {
        String pageNumbers = "1,2,3,a";
        String expected = "not valid position is 6";
        String actual = Printer.reduceString(pageNumbers);
        assertEquals(expected, actual);
    }
}