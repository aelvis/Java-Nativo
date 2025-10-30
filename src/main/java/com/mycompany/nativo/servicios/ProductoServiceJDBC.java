package com.mycompany.nativo.servicios;

import com.mycompany.nativo.repositorios.ProductoDAOJDBC;
import com.mycompany.nativo.repositorios.IProductoDAO;
import com.mycompany.nativo.modelos.Producto;
import java.util.List;

public class ProductoServiceJDBC implements IProductoService {
    private IProductoDAO productoDAO;

    public ProductoServiceJDBC() {
        this.productoDAO = new ProductoDAOJDBC();
    }

    @Override
    public void crearProducto(Producto producto) throws Exception {
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre del producto es obligatorio");
        }
        if (producto.getPrecio() <= 0) {
            throw new Exception("El precio debe ser mayor a 0");
        }
        if (producto.getCategoria().getId() <= 0) {
            throw new Exception("Debe seleccionar una categoría válida");
        }
        // Nota: Las validaciones de negocio más profundas (e.g., que la categoriaId exista) 
        // pueden ir aquí, pero se omiten por brevedad.
        productoDAO.crear(producto);
    }

    @Override
    public Producto buscarProducto(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID inválido");
        }
        return productoDAO.obtenerPorId(id);
    }

    @Override
    public List<Producto> listarProductos() throws Exception {
        return productoDAO.obtenerTodos();
    }

    @Override
    public void actualizarProducto(Producto producto) throws Exception {
        if (producto.getId() <= 0) {
            throw new Exception("ID inválido");
        }
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre del producto es obligatorio");
        }
        if (producto.getPrecio() <= 0) {
            throw new Exception("El precio debe ser mayor a 0");
        }
        if (producto.getCategoriaId() <= 0) {
            throw new Exception("Debe seleccionar una categoría válida");
        }
        productoDAO.actualizar(producto);
    }

    @Override
    public void eliminarProducto(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID inválido");
        }
        productoDAO.eliminar(id);
    }

    @Override
    public void asociarAlmacenAProducto(int productoId, int almacenId) throws Exception {
        if (productoId <= 0 || almacenId <= 0) {
            throw new Exception("IDs de producto/almacén inválidos");
        }
        productoDAO.agregarAlmacenAProducto(productoId, almacenId);
    }

    @Override
    public void desasociarAlmacenDeProducto(int productoId, int almacenId) throws Exception {
        if (productoId <= 0 || almacenId <= 0) {
            throw new Exception("IDs de producto/almacén inválidos");
        }
        productoDAO.eliminarAlmacenDeProducto(productoId, almacenId);
    }
}