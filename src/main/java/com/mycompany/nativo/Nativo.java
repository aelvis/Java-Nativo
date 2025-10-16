/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.nativo;

import java.util.List;

/**
 *
 * @author Home
 */
public class Nativo {

    public static void main(String[] args) {
        IProductoService service = new ProductoServiceJDBC();
                try {
            // CREATE - Crear producto
            System.out.println("=== CREAR PRODUCTO ===");
            Producto nuevoProducto = new Producto("Laptop", 1500.00);
            service.crearProducto(nuevoProducto);
            System.out.println("Producto creado: " + nuevoProducto);

            // READ - Leer todos los productos
            System.out.println("\n=== LISTAR PRODUCTOS ===");
                    List<Producto> productos = service.listarProductos();
            productos.forEach(System.out::println);

            // READ - Leer un producto por ID
            System.out.println("\n=== BUSCAR PRODUCTO POR ID ===");
            Producto producto = service.buscarProducto(1);
            System.out.println("Producto encontrado: " + producto);

            // UPDATE - Actualizar producto
            System.out.println("\n=== ACTUALIZAR PRODUCTO ===");
            if (producto != null) {
                producto.setNombre("Laptop Gaming");
                producto.setPrecio(2000.00);
                service.actualizarProducto(producto);
                System.out.println("Producto actualizado: " + producto);
            }

            // DELETE - Eliminar producto
            System.out.println("\n=== ELIMINAR PRODUCTO ===");
            service.eliminarProducto(1);
            System.out.println("Producto eliminado");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
