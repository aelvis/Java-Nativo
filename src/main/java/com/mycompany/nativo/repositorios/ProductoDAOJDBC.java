package com.mycompany.nativo.repositorios;

import com.mycompany.nativo.Config;
import com.mycompany.nativo.modelos.Producto;
import com.mycompany.nativo.modelos.Categoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOJDBC implements IProductoDAO {

    // Helper para mapear un ResultSet a un objeto Producto
    private Producto mapearProducto(ResultSet rs) throws SQLException {
        
        Categoria categoria = new Categoria(rs.getString("cat_nombre"));
        Producto producto = new Producto(rs.getString("nombre"),rs.getDouble("precio"),categoria);
        producto.setCategoriaId(rs.getInt("categoria_id"));
        return producto;
    }
    
    // Helper para cargar almacenes de un producto
    private void cargarAlmacenes(Producto producto) throws Exception {
        IAlmacenDAO almacenDAO = new AlmacenDAOJDBC();
        producto.setAlmacenes(almacenDAO.obtenerAlmacenesPorProductoId(producto.getId()));
    }

    // Consulta base con JOIN para traer la categoría
    private static final String SELECT_BASE = 
        "SELECT p.id, p.nombre, p.precio, p.categoria_id, c.nombre AS cat_nombre " +
        "FROM productos p " +
        "LEFT JOIN categoria c ON p.categoria_id = c.id";

    @Override
    public void crear(Producto producto) throws Exception {
        String sql = "INSERT INTO productos (nombre, precio, categoria_id) VALUES (?, ?, ?)";
        
        try (Connection conn = Config.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, producto.getNombre());
            stmt.setDouble(2, producto.getPrecio());
            stmt.setInt(3, producto.getCategoria().getId());
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    producto.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public Producto obtenerPorId(int id) throws Exception {
        String sql = SELECT_BASE + " WHERE p.id = ?";
        
        try (Connection conn = Config.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Producto producto = mapearProducto(rs);
                    cargarAlmacenes(producto); // Cargar los almacenes asociados
                    return producto;
                }
            }
        }
        return null;
    }

    @Override
    public List<Producto> obtenerTodos() throws Exception {
        List<Producto> productos = new ArrayList<>();
        String sql = SELECT_BASE;
        
        try (Connection conn = Config.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Producto producto = mapearProducto(rs);
                cargarAlmacenes(producto); // Cargar los almacenes asociados
                productos.add(producto);
            }
        }
        return productos;
    }

    @Override
    public void actualizar(Producto producto) throws Exception {
        String sql = "UPDATE productos SET nombre = ?, precio = ?, categoria_id = ? WHERE id = ?";
        
        try (Connection conn = Config.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, producto.getNombre());
            stmt.setDouble(2, producto.getPrecio());
            stmt.setInt(3, producto.getCategoriaId()); // Usa el ID simple para la actualización
            stmt.setInt(4, producto.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws Exception {
        // La tabla 'producto_almacen' tiene ON DELETE CASCADE, por lo que no es necesario
        // borrar los registros de relación manualmente, pero es buena práctica verificar.
        String sql = "DELETE FROM productos WHERE id = ?";
        
        try (Connection conn = Config.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    // Métodos para la relación Producto-Almacén
    
    @Override
    public void agregarAlmacenAProducto(int productoId, int almacenId) throws Exception {
        String sql = "INSERT INTO producto_almacen (producto_id, almacen_id) VALUES (?, ?)";
        try (Connection conn = Config.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productoId);
            stmt.setInt(2, almacenId);
            stmt.executeUpdate();
        } catch (SQLException e) {
             // Si el par (productoId, almacenId) ya existe, MySQL lanza una excepción de duplicado.
             // Aquí puedes decidir si ignorarla o relanzarla.
             if (!e.getMessage().contains("Duplicate entry")) {
                throw new Exception("Error al agregar almacén a producto", e);
             }
        }
    }

    @Override
    public void eliminarAlmacenDeProducto(int productoId, int almacenId) throws Exception {
        String sql = "DELETE FROM producto_almacen WHERE producto_id = ? AND almacen_id = ?";
        try (Connection conn = Config.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productoId);
            stmt.setInt(2, almacenId);
            stmt.executeUpdate();
        }
    }
}