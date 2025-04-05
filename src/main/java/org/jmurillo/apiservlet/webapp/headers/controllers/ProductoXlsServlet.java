package org.jmurillo.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jmurillo.apiservlet.webapp.headers.models.Producto;
import org.jmurillo.apiservlet.webapp.headers.services.ProductoService;
import org.jmurillo.apiservlet.webapp.headers.services.ProductoServiceImp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet({"/productos.xls", "/productos.html"})
public class ProductoXlsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImp();
        List<Producto> productos = service.listar();

        resp.setContentType("text/html;charset=UTF-8");
        String servletPath = req.getServletPath();
        boolean esXls = servletPath.endsWith(".xls");

        if (esXls) {
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-Disposition", "attachment;filename=productos.xls");
        }
        try (PrintWriter uot = resp.getWriter()) {
            if(!esXls) {
                uot.println("<!DOCTYPE html");
                uot.println("<html>");
                uot.println("<head>");
                uot.println("        <meta charset=\"UTF-8\">");
                uot.println("        <title>List of products</title>");
                uot.println("</head>");
                uot.println("   <body>");
                uot.println("       <h1>Products to import!</h1>");
                uot.println("<p><a href=\"" + req.getContextPath() + "/productos.xls" + "\">sport xls</a></p>");
                uot.println("<p><a href=\"" + req.getContextPath() + "/productos.json" + "\">Getting Json</a></p>");
            }
            uot.println("<table>");
            uot.println("<tr>");
            uot.println("<th>id</th>");
            uot.println("<th>nombre</th>");
            uot.println("<th>tipo</th>");
            uot.println("<th>precio</th>");
            uot.println("</tr>");
            productos.forEach(p -> {
                uot.println("<tr>");
                uot.println("<td>" + p.getId() + "</td>");
                uot.println("<td>" + p.getNombre() + "</td>");
                uot.println("<td>" + p.getTipo() + "</td>");
                uot.println("<td>" + p.getPrecio() + "</td>");
                uot.println("</tr>");
            });
            uot.println("</table>");
            if (!esXls) {
                uot.println("   </body>");
                uot.println("</html>");
            }
        }
    }
}
