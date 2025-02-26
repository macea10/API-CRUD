package com.henrry.Api_crud.Service;

import com.henrry.Api_crud.Models.Producto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final List<Producto> productos = new ArrayList<>();

    // Inicializamos con algunos datos para pruebas
    public ProductoService() {
        productos.add(new Producto(1L, "iPhone 14", 999.99));
        productos.add(new Producto(2L, "Samsung TV",1299.99));
        productos.add(new Producto(3L, "Nike Air Max",79.99));
    }

    /***
     * agregar un producto
     *
     * @param producto
     * @return
     */
    public Producto agregarProducto(Producto producto) {
        producto.setId((long) (productos.size() + 1)); // Simulamos un ID autoincrementable
        productos.add(producto);
        return producto;
    }

    /**
     * listar todo los productos
     *
     * @return
     */
    public List<Producto> listarProductos() {
        return productos;
    }

    /**
     * metodo para obtener un producto por id
     *
     * @param id
     * @return
     */

    public Optional<Producto> obtenerProductoPorId(Long id) {
        return productos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    /**
     * Metodo para actualizar el prodcuto
     * Se utiliza Optional para manejar el caso en el que un producto no se encuentra.
     *
     * @param producto
     * @return
     */
    public Producto actualizarProducto(Producto producto) {
        Optional<Producto> productoExistente = obtenerProductoPorId(producto.getId());
        if (productoExistente.isPresent()) {
            Producto p = productoExistente.get();
            p.setNombre(producto.getNombre());
            p.setPrecio(producto.getPrecio());
            return p;
        } else {
            return null;
        }
    }

    /**
     * METODO PARA ELIMINAR EL PRODUCTO
     *
     * @param id
     */
    public void eliminarProducto(Long id) {
        productos.removeIf(p -> p.getId().equals(id));
    }
}

