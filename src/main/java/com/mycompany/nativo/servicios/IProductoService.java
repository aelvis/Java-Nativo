package com.mycompany.nativo.servicios;

import com.mycompany.nativo.modelos.Producto;
import java.util.List;

public interface IProductoService {
    void crearProducto(Producto producto) throws Exception;
    Producto buscarProducto(int id) throws Exception;
    List<Producto> listarProductos() throws Exception;
    void actualizarProducto(Producto producto) throws Exception;
    void eliminarProducto(int id) throws Exception;
    
    // Nuevos métodos para manejar almacenes del producto
    void asociarAlmacenAProducto(int productoId, int almacenId) throws Exception;
    void desasociarAlmacenDeProducto(int productoId, int almacenId) throws Exception;
}