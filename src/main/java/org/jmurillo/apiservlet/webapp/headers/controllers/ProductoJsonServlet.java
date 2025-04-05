package org.jmurillo.apiservlet.webapp.headers.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
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

@WebServlet("/productos.json")  // API endpoint for JSON response of products
public class ProductoJsonServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream inputStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        Producto producto = mapper.readValue(inputStream, Producto.class);
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter uot = resp.getWriter()) {

            uot.println("<!DOCTYPE html");
            uot.println("<html>");
            uot.println("<head>");
            uot.println("        <meta charset=\"UTF-8\">");
            uot.println("        <title>Detalle de producto desde Json</title>");
            uot.println("</head>");
            uot.println("   <body>");
            uot.println("       <h1>Detalle de producto desde Json!</h1>");
            uot.println("<ul>");
            uot.println("<li>ID: " + producto.getId() + "</li>");
            uot.println("<li>Nombre: " + producto.getNombre() + "</li>");
            uot.println("<li>Tipo: " + producto.getTipo() + "</li>");
            uot.println("<li>Precio: " + producto.getPrecio() + "</li>");
            uot.println("</ul>");
            uot.println("   </body>");
            uot.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImp();
        List<Producto> productos = service.listar();
        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = mapper.writeValueAsString(productos);
        resp.setContentType("application/json");
        resp.getWriter().write(jsonResponse);
    }
}
