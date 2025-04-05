package org.jmurillo.apiservlet.webapp.headers.services;

import org.jmurillo.apiservlet.webapp.headers.models.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImp implements ProductoService {
    @Override
    public List<Producto> listar() {
        return Arrays.asList(
                new Producto(1L, "notebook", "computacion", 2000000),
                new Producto(2L, "Lenovo Ideapack", "computacion", 1890000),
                new Producto(3L, "Cama Comfort", "home", 1200000),
                new Producto(4L, "Harmario Comfort", "home", 500000),
                new Producto(5L, "play Station", "gaming", 1200000),
                new Producto(6L, "Nintendo", "gaming", 900000)
        );
    }

    @Override
    public Optional<Producto> buscarProducto(String nombre) {
        return this.listar().stream()
                .filter(p ->{
                    if (nombre == null || nombre.isBlank()) {
                        return false;
                    }
                    return p.getNombre().toLowerCase().contains(nombre.toLowerCase());
                })
               .findFirst();
    }
}
