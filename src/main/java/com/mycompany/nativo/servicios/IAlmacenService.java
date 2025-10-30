/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.nativo.servicios;

import com.mycompany.nativo.modelos.Almacen;
import java.util.List;

public interface IAlmacenService {
    void crearAlmacen(Almacen almacen) throws Exception;
    Almacen buscarAlmacen(int id) throws Exception;
    List<Almacen> listarAlmacenes() throws Exception;
    void actualizarAlmacen(Almacen almacen) throws Exception;
    void eliminarAlmacen(int id) throws Exception;
}
