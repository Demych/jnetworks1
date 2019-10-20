package com.jnetworks;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/reducedPageNumbers")
public class PrinterServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rawPageNumbers = req.getParameter("rawPageNumbers");
        String reducedRawPageNumbers = Printer.reduceString(rawPageNumbers);
        PrintWriter writer = resp.getWriter();
        writer.print(reducedRawPageNumbers);
        writer.close();
    }
}
