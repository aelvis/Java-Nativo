/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nativo.repositorios;

import com.mycompany.nativo.modelos.Producto;
import java.util.List;

/**
 *
 * @author Home
 */
public interface IProductoDAO {
    void crear(Producto producto) throws Exception;
    Producto obtenerPorId(int id) throws Exception;
    List<Producto> obtenerTodos() throws Exception;
    void actualizar(Producto producto) throws Exception;
    void eliminar(int id) throws Exception;
    
    void agregarAlmacenAProducto(int productoId, int almacenId) throws Exception;
    void eliminarAlmacenDeProducto(int productoId, int almacenId) throws Exception;
}
