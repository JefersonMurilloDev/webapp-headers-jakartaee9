package org.jmurillo.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/hora-actualizada")
public class HoraActualizadaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setHeader("refresh", "1");
        LocalTime hora = LocalTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");
        try (PrintWriter uot = resp.getWriter()) {

            uot.println("<!DOCTYPE html");
            uot.println("<html>");
            uot.println("<head>");
            uot.println("        <meta charset=\"UTF-8\">");
            uot.println("        <title>La Hora Actualizada</title>");
            uot.println("</head>");
            uot.println("   <body>");
            uot.println("       <h1>La Hora Actualizada!</h1>");
            uot.println("<h3>" + hora.format(df) + "</h3>");
            uot.println("   </body>");
            uot.println("</html>");
        }
    }
}
