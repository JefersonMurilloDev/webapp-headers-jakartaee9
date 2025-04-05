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
import java.util.Optional;

@WebServlet("/buscar-productos")
public class BuscarProductoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImp();
        String nombreProducto = req.getParameter("producto");
        Optional<Producto> encontrado = service.buscarProducto(nombreProducto);
        if (encontrado.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter uot = resp.getWriter()) {

                uot.println("<!DOCTYPE html");
                uot.println("<html>");
                uot.println("<head>");
                uot.println("        <meta charset=\"UTF-8\">");
                uot.println("        <title>Producto Encontrado</title>");
                uot.println("</head>");
                uot.println("   <body>");
                uot.println("       <h1>Producto Encontrado!</h1>");
                uot.println("       <h3>Producto Encontrado "+ encontrado.get().getNombre().toUpperCase() + " El precio $"+ encontrado.get().getPrecio() +"</h3>");
                uot.println("   </body>");
                uot.println("</html>");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Producto no encontrado");
        }
    }
}
