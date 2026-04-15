package uacm.pf.sistemadevideojuegos.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "videojuegos")
public class Videojuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVideojuego;

    @Column(nullable = false)
    private String nombre;

    private String genero;
    private String plataforma;
    private double precio;
    private int stock;

    public Videojuego() {}

    public Videojuego(int idVideojuego, String nombre, String genero, String plataforma, double precio, int stock) {
        this.idVideojuego = idVideojuego;
        this.nombre = nombre;
        this.genero = genero;
        this.plataforma = plataforma;
        this.precio = precio;
        this.stock = stock;
    }

    public int getIdVideojuego() { return idVideojuego; }
    public void setIdVideojuego(int idVideojuego) { this.idVideojuego = idVideojuego; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getPlataforma() { return plataforma; }
    public void setPlataforma(String plataforma) { this.plataforma = plataforma; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    // El toString del JComboBox ya no es estrictamente necesario en web, pero lo puedes dejar sin problema
}