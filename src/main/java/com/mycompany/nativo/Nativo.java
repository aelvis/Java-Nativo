package com.mycompany.nativo;

import com.mycompany.nativo.servicios.IProductoService;
import com.mycompany.nativo.servicios.ProductoServiceJDBC;
import com.mycompany.nativo.modelos.Producto;
import com.mycompany.nativo.modelos.Categoria;
import com.mycompany.nativo.modelos.Almacen;
import com.mycompany.nativo.servicios.AlmacenServiceJDBC;
import com.mycompany.nativo.servicios.CategoriaServiceJDBC;
import com.mycompany.nativo.servicios.IAlmacenService;
import com.mycompany.nativo.servicios.ICategoriaService;
import java.util.List;

public class Nativo {

    public static void main(String[] args) {
        IProductoService productoService = new ProductoServiceJDBC();
        ICategoriaService categoriaService = new CategoriaServiceJDBC();
        IAlmacenService almacenService = new AlmacenServiceJDBC();

        try {
            // --- CRUD Categoría ---
            System.out.println("=== CRUD CATEGORÍA ===");

            // CREATE - Crear Categoría (Llamada al Service)
            Categoria nuevaCat = new Categoria("Hogar");
            categoriaService.crearCategoria(nuevaCat);
            System.out.println("Categoría creada: " + nuevaCat);
            
            // READ - Listar Categorías (Llamada al Service)
            List<Categoria> categorias = categoriaService.listarCategorias();
            categorias.forEach(System.out::println);
            
            // ACTUALIZAR - Categorías (Llamada al Service)
            Categoria categoria = categoriaService.buscarCategoria(4);
            categoria.setNombre("Hogar2");
            categoriaService.actualizarCategoria(categoria);

            // --- CRUD Producto (con Categoría) ---
            System.out.println("\n=== CRUD PRODUCTO AMPLIADO ===");
            // CREATE - Crear producto con categoria_id
            Categoria cate = categoriaService.buscarCategoria(4);
            Producto nuevoProducto = new Producto("Smart TV 65", 1050.50, cate);
            productoService.crearProducto(nuevoProducto);
            System.out.println("Producto creado: " + nuevoProducto);
            int nuevoProductoId = nuevoProducto.getId();
            System.out.println("Producto creado: " + nuevoProductoId);
            
            // --- Asociación Producto-Almacén ---
            System.out.println("\n=== ASOCIACIÓN ALMACENES ===");
            Almacen almacen_id = almacenService.buscarAlmacen(1);
            Almacen almacen_id2 = almacenService.buscarAlmacen(2);
            // Asociar almacenes al producto
            productoService.asociarAlmacenAProducto(nuevoProductoId, almacen_id.getId());
            productoService.asociarAlmacenAProducto(nuevoProductoId, almacen_id2.getId());
            System.out.println("Almacenes asociados al producto ID " + nuevoProductoId);

            
            // READ - Buscar producto por ID (debe traer categoría y almacenes)
            System.out.println("\n=== BUSCAR PRODUCTO POR ID (COMPLETO) ===");
            Producto productoEncontrado = productoService.buscarProducto(9);
            System.out.println("Producto encontrado: " + productoEncontrado);

            
            // READ - Listar todos los productos (debe traer categoría y almacenes para todos)
            System.out.println("\n=== LISTAR TODOS LOS PRODUCTOS ===");
            List<Producto> productos = productoService.listarProductos();
            productos.forEach(System.out::println);
            
            
            // UPDATE - Actualizar producto
            System.out.println("\n=== ACTUALIZAR PRODUCTO ===");
            Producto productoEncontrado3 = productoService.buscarProducto(9);
            productoEncontrado3.setNombre("Smart TV 55 - 4K");
            productoEncontrado3.setPrecio(900.00);
            Categoria categ = categoriaService.buscarCategoria(1);
            productoEncontrado3.setCategoriaId(categ.getId());
            productoService.actualizarProducto(productoEncontrado3);
            int nuevoProductoId2 = productoEncontrado3.getId();
            System.out.println("Producto actualizado: " + productoService.buscarProducto(nuevoProductoId2));
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
