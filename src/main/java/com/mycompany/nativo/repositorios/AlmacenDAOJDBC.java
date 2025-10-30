package com.mycompany.nativo.repositorios;

import com.mycompany.nativo.Config;
import com.mycompany.nativo.modelos.Almacen;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlmacenDAOJDBC implements IAlmacenDAO {

    @Override
    public void crear(Almacen almacen) throws Exception {
        String sql = "INSERT INTO almacen (nombre) VALUES (?)";
        try (Connection conn = Config.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, almacen.getNombre());
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    almacen.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public Almacen obtenerPorId(int id) throws Exception {
        String sql = "SELECT * FROM almacen WHERE id = ?";
        try (Connection conn = Config.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Almacen(rs.getString("nombre"));
                }
            }
        }
        return null;
    }

    @Override
    public List<Almacen> obtenerTodos() throws Exception {
        List<Almacen> almacenes = new ArrayList<>();
        String sql = "SELECT * FROM almacen";
        
        try (Connection conn = Config.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                almacenes.add(new Almacen(rs.getString("nombre")));
            }
        }
        return almacenes;
    }

    @Override
    public void actualizar(Almacen almacen) throws Exception {
        String sql = "UPDATE almacen SET nombre = ? WHERE id = ?";
        
        try (Connection conn = Config.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, almacen.getNombre());
            stmt.setInt(2, almacen.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws Exception {
        String sql = "DELETE FROM almacen WHERE id = ?";
        
        try (Connection conn = Config.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    @Override
    public List<Almacen> obtenerAlmacenesPorProductoId(int productoId) throws Exception {
        List<Almacen> almacenes = new ArrayList<>();
        String sql = "SELECT a.id, a.nombre FROM almacen a " +
                     "JOIN producto_almacen pa ON a.id = pa.almacen_id " +
                     "WHERE pa.producto_id = ?";
        
        try (Connection conn = Config.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, productoId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    almacenes.add(new Almacen(rs.getString("nombre")));
                }
            }
        }
        return almacenes;
    }
}