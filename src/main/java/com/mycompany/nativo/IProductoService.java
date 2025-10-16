/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.nativo;

import java.util.List;

public interface IProductoService {
    void crearProducto(Producto producto) throws Exception;
    Producto buscarProducto(int id) throws Exception;
    List<Producto> listarProductos() throws Exception;
    void actualizarProducto(Producto producto) throws Exception;
    void eliminarProducto(int id) throws Exception;
}