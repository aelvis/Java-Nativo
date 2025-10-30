
package com.mycompany.nativo.servicios;

import com.mycompany.nativo.repositorios.AlmacenDAOJDBC;
import com.mycompany.nativo.repositorios.IAlmacenDAO;
import com.mycompany.nativo.modelos.Almacen;
import java.util.List;

public class AlmacenServiceJDBC implements IAlmacenService {
    private IAlmacenDAO almacenDAO;

    public AlmacenServiceJDBC() {
        this.almacenDAO = new AlmacenDAOJDBC();
    }
    
    // Implementación de la lógica de negocio (validaciones) y llamadas al DAO
    
    @Override
    public void crearAlmacen(Almacen almacen) throws Exception {
        if (almacen.getNombre() == null || almacen.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre del almacén es obligatorio");
        }
        almacenDAO.crear(almacen);
    }
    
    @Override
    public Almacen buscarAlmacen(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID de almacén inválido");
        }
        return almacenDAO.obtenerPorId(id);
    }

    @Override
    public List<Almacen> listarAlmacenes() throws Exception {
        return almacenDAO.obtenerTodos();
    }
    
    @Override
    public void actualizarAlmacen(Almacen almacen) throws Exception {
        if (almacen.getId() <= 0) {
            throw new Exception("ID de almacén inválido");
        }
        if (almacen.getNombre() == null || almacen.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre del almacén es obligatorio");
        }
        almacenDAO.actualizar(almacen);
    }
    
    @Override
    public void eliminarAlmacen(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID de almacén inválido");
        }
        // Nota: La BD debe manejar la eliminación en cascada de 'producto_almacen'.
        almacenDAO.eliminar(id);
    }
}
