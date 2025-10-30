/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nativo.modelos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Home
 */
public class Producto {

    private int id;
    private String nombre;
    private double precio;
    private Categoria categoria;
    private int categoriaId;
    private List<Almacen> almacenes;

    // Constructores
    public Producto() {
        this.almacenes = new ArrayList<>();
    }


    public Producto(String nombre, double precio, int categoriaId) {
        this();
        this.nombre = nombre;
        this.precio = precio;
        this.categoriaId = categoriaId;
    }
    
    public Producto(String nombre, double precio, Categoria categoria) {
        this();
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public List<Almacen> getAlmacenes() {
        return almacenes;
    }

    public void setAlmacenes(List<Almacen> almacenes) {
        this.almacenes = almacenes;
    }

    @Override
    public String toString() {
        return "Producto{"
                + "id=" + id
                + ", nombre='" + nombre + '\''
                + ", precio=" + precio
                + ", categoria=" + (categoria != null ? categoria.getNombre() : "N/A")
                + ", almacenes=" + almacenes.stream().map(Almacen::getNombre).toList()
                + // Muestra solo los nombres de almacenes
                '}';
    }
}
