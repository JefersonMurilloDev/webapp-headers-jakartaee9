package org.jmurillo.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    final static String USERNAME = "admin@gmail.com";
    final static String PASSWORD = "12345";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username.equals(USERNAME) && password.equals(PASSWORD)) {
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter uot = resp.getWriter()) {

                uot.println("<!DOCTYPE html");
                uot.println("<html>");
                uot.println("<head>");
                uot.println("        <meta charset=\"UTF-8\">");
                uot.println("        <title>Login Correcto</title>");
                uot.println("</head>");
                uot.println("   <body>");
                uot.println("       <h1>Login Correcto!</h1>");
                uot.println("       <h3>Bienvenido, " + username + "!</h3>");
                uot.println("   </body>");
                uot.println("</html>");
            }

        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no esta autorizado para acceder.");
        }
    }
}
