/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nativo.servicios;

import com.mycompany.nativo.repositorios.CategoriaDAOJDBC;
import com.mycompany.nativo.repositorios.ICategoriaDAO;
import com.mycompany.nativo.modelos.Categoria;
import java.util.List;

public class CategoriaServiceJDBC implements ICategoriaService {
    private ICategoriaDAO categoriaDAO;

    public CategoriaServiceJDBC() {
        this.categoriaDAO = new CategoriaDAOJDBC();
    }
       
    @Override
    public void crearCategoria(Categoria categoria) throws Exception {
        if (categoria.getNombre() == null || categoria.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre de la categoría es obligatorio");
        }
        categoriaDAO.crear(categoria);
    }
    
    @Override
    public Categoria buscarCategoria(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID de categoría inválido");
        }
        return categoriaDAO.obtenerPorId(id);
    }

    @Override
    public List<Categoria> listarCategorias() throws Exception {
        return categoriaDAO.obtenerTodos();
    }
    
    @Override
    public void actualizarCategoria(Categoria categoria) throws Exception {
        if (categoria.getId() <= 0) {
            throw new Exception("ID de categoría inválido");
        }
        if (categoria.getNombre() == null || categoria.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre de la categoría es obligatorio");
        }
        categoriaDAO.actualizar(categoria);
    }
    
    @Override
    public void eliminarCategoria(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID de categoría inválido");
        }
        // Nota: Una buena práctica sería verificar si existen productos asociados antes de eliminar.
        categoriaDAO.eliminar(id);
    }
}
