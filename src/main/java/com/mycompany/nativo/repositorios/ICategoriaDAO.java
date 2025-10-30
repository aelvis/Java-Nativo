package com.mycompany.nativo.repositorios;

import com.mycompany.nativo.modelos.Categoria;
import java.util.List;

public interface ICategoriaDAO {
    void crear(Categoria categoria) throws Exception;
    Categoria obtenerPorId(int id) throws Exception;
    List<Categoria> obtenerTodos() throws Exception;
    void actualizar(Categoria categoria) throws Exception;
    void eliminar(int id) throws Exception;
}