package org.jmurillo.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/cabeceras-request")
public class CabecerasHttpRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String metodoHttp = req.getMethod();
        String requestUri = req.getRequestURI();
        String requestUrl = req.getRequestURL().toString();
        String contexPath = req.getContextPath();
        String servletPath = req.getServletPath();
        String ipCliente = req.getRemoteAddr();
        String ip = req.getLocalAddr();
        int port = req.getLocalPort();
        String scheme = req.getScheme();
        String host = req.getHeader("host");
        String url = scheme+"://"+host+contexPath+servletPath;
        String url2 = scheme+"://"+ip+":"+port+contexPath+servletPath;

        try (PrintWriter uot = resp.getWriter()) {

            uot.println("<!DOCTYPE html");
            uot.println("<html>");
            uot.println("<head>");
            uot.println("        <meta charset=\"UTF-8\">");
            uot.println("        <title>Cabeceras Http Request</title>");
            uot.println("</head>");
            uot.println("   <body>");
            uot.println("       <h1>Cabeceras Http Request!</h1>");
            uot.println("<ul>");
            uot.println("<li> metodo http: " + metodoHttp + "</li>");
            uot.println("<li> requestUri: " + requestUri + "</li>");
            uot.println("<li> requestUrl: " + requestUrl + "</li>");
            uot.println("<li> contextPath: " + contexPath + "</li>");
            uot.println("<li> servletPath: " + servletPath + "</li>");
            uot.println("<li> IP Local: " + ip + "</li>");
            uot.println("<li> Ip Cliente: " + ipCliente + "</li>");
            uot.println("<li> Port Local: " + port + "</li>");
            uot.println("<li> Scheme: " + scheme + "</li>");
            uot.println("<li> Host: " + host + "</li>");
            uot.println("<li> Construyendo mi URL manual: " + url + "</li>");
            uot.println("<li> Construyendo mi URL manual con la Ip: " + url2 + "</li>");

            Enumeration<String> headerNames = req.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String cabecera = headerNames.nextElement();
                uot.println("<li>" + cabecera + ": "+ req.getHeader(cabecera) + "</>");
            }
            uot.println("</lu>");
            uot.println("   </body>");
            uot.println("</html>");
        }
    }
}
