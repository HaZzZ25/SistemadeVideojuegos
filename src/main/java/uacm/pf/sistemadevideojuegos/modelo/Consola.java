package uacm.pf.sistemadevideojuegos.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "consolas")
public class Consola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idConsola;

    @Column(nullable = false)
    private String nombre;

    private String marca;
    private double precio;
    private int stock;

    public Consola() {}

    public Consola(int idConsola, String nombre, String marca, double precio, int stock) {
        this.idConsola = idConsola;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
    }

    public int getIdConsola() { return idConsola; }
    public void setIdConsola(int idConsola) { this.idConsola = idConsola; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}