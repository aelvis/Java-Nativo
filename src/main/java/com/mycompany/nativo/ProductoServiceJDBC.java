/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nativo;

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
        productoDAO.actualizar(producto);
    }

    @Override
    public void eliminarProducto(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID inválido");
        }
        productoDAO.eliminar(id);
    }
}
