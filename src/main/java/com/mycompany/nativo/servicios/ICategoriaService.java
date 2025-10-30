package com.mycompany.nativo.servicios;

import com.mycompany.nativo.modelos.Categoria;
import java.util.List;

public interface ICategoriaService {
    void crearCategoria(Categoria categoria) throws Exception;
    Categoria buscarCategoria(int id) throws Exception;
    List<Categoria> listarCategorias() throws Exception;
    void actualizarCategoria(Categoria categoria) throws Exception;
    void eliminarCategoria(int id) throws Exception;
}