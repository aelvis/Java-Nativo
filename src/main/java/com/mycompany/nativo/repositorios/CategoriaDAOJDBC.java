package com.mycompany.nativo.repositorios;

import com.mycompany.nativo.Config;
import com.mycompany.nativo.modelos.Categoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOJDBC implements ICategoriaDAO {

    @Override
    public void crear(Categoria categoria) throws Exception {
        String sql = "INSERT INTO categoria (nombre) VALUES (?)";
        try (Connection conn = Config.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, categoria.getNombre());
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    categoria.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public Categoria obtenerPorId(int id) throws Exception {
        String sql = "SELECT * FROM categoria WHERE id = ?";
        try (Connection conn = Config.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Categoria(rs.getString("nombre"));
                }
            }
        }
        return null;
    }

    @Override
    public List<Categoria> obtenerTodos() throws Exception {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria";
        
        try (Connection conn = Config.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                categorias.add(new Categoria(rs.getString("nombre")));
            }
        }
        return categorias;
    }

    @Override
    public void actualizar(Categoria categoria) throws Exception {
        String sql = "UPDATE categoria SET nombre = ? WHERE id = ?";
        
        try (Connection conn = Config.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, categoria.getNombre());
            stmt.setInt(2, categoria.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws Exception {
        String sql = "DELETE FROM categoria WHERE id = ?";
        
        try (Connection conn = Config.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}