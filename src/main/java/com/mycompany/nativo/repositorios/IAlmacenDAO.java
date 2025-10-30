package com.mycompany.nativo.repositorios;

import com.mycompany.nativo.modelos.Almacen;
import java.util.List;

public interface IAlmacenDAO {
    void crear(Almacen almacen) throws Exception;
    Almacen obtenerPorId(int id) throws Exception;
    List<Almacen> obtenerTodos() throws Exception;
    void actualizar(Almacen almacen) throws Exception;
    void eliminar(int id) throws Exception;
    
    // Método para obtener los almacenes de un producto específico
    List<Almacen> obtenerAlmacenesPorProductoId(int productoId) throws Exception;
}